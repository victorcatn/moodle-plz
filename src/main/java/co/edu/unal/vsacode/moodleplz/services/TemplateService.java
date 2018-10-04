package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateService {

    @Autowired
    private SkillService skillService;

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private StaffMemberService staffMemberService;

    @Autowired
    private ProjectService projectService;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    //--------------------------------------------Group templates services--------------------------------------------//

    public String groupMemberToHTML(Group group){
        String groupHTML = "<p><b>Group members: </b></p>" +
                "<ul>";

        for(String idMember : group.getMembersId()){
            StaffMember staffMember = staffMemberService.getStaffMember(idMember);
            if(staffMember != null) {
                groupHTML += "<li>" + staffMemberInGroupToHTML(staffMember) + "</li>";
            }
        }
        groupHTML += "</ul>";
        return groupHTML;
    }

    public String AssginedProjectToHTML(Group group){
        return projectToHTML(projectService.getProjectById(group.getProjectId()));
    }



    //-------------------------------------------project templates services-------------------------------------------//

    public String projectToHTML(Project project){
        String HTML = "<b>Name: </b>"+project.getName()+"<br>";
        if(project.getStartDate() != null){
            HTML += "<b>Stard date: </b>"+format.format(project.getStartDate())+"<br>";
        }
        if(project.getEndDate() != null){
            HTML += "<b>End date: </b>"+format.format(project.getEndDate())+"<br>";
        }
        HTML += "<b>Added skills: </b> <br>" +
                skillScoreListToHTML(project.getNeededSkills())+"<br>"+"<br>"+
                "<b>Added knowledges: </b> <br>" +
                knowledgeScoreListToHTML(project.getNeededKnowledges())+"<br>"+"<br>";
        return HTML;

    }


    public String projectChangesToHTML(Project newProject, Project oldProject){
        String changesHTML = "<ul>";
        if(!newProject.getName().equals(oldProject.getName())){
            changesHTML += "<li><b>New name: </b>"+newProject.getName()+"</li>";
        }
        if(newProject.getStartDate() != null && oldProject.getStartDate() != null) {
            if (!newProject.getStartDate().equals(oldProject.getStartDate())) {
                changesHTML += "<li><b>new start date: </b>" + format.format(newProject.getStartDate()) + "</li>";
            }
        }
        if(newProject.getEndDate() != null && oldProject.getEndDate() != null) {
            if (!newProject.getEndDate().equals(oldProject.getEndDate())) {
                changesHTML += "<li><b>new start date: </b>" + format.format(newProject.getEndDate()) + "</li>";
            }
        }
        changesHTML += "</ul> <br>";
        if(!newProject.getNeededSkills().equals(oldProject.getNeededSkills())) {
            changesHTML += skillScoresChangesToHTML(newProject.getNeededSkills(), oldProject.getNeededSkills());
        }
        if(!newProject.getNeededKnowledges().equals(oldProject.getNeededKnowledges())) {
            changesHTML += knowledgeScoresChangesToHTML(newProject.getNeededKnowledges(), oldProject.getNeededKnowledges());
        }

        return changesHTML;
    }

    //-----------------------------------------staffmember templates services-----------------------------------------//

    public String staffMemberToHTML(StaffMember staffMember){
        return ("<b>Name: </b>"+staffMember.getName()+" "+staffMember.getLastName()+"<br>"+
                "<b>Document and username: </b>"+staffMember.getDocument()+"<br>"+
                "<b>Password: </b>"+staffMember.getPassword()+"<br>"+
                "<b>Email: </b>"+staffMember.getEmail()+"<br>"+"<br>"+
                "<b>Added skills: </b> <br>" +
                skillScoreListToHTML(staffMember.getSkills())+"<br>"+"<br>"+
                "<b>Added knowledges: </b> <br>" +
                knowledgeScoreListToHTML(staffMember.getKnowledges())+"<br>"+"<br>");
    }

    public String staffMemberChangesToHTML(StaffMember newProfile, StaffMember oldProfile){
        String changesHTML = "<ul>";
        if((!newProfile.getName().equals(oldProfile.getName())) || (!newProfile.getLastName().equals(oldProfile.getLastName()))){
            changesHTML += "<li><b>New name: </b>"+newProfile.getName() +" "+newProfile.getLastName()+"</li>";
        }
        if(!newProfile.getDocument().equals(oldProfile.getDocument())){
            changesHTML += "<li><b>new Document and username: </b>"+newProfile.getDocument()+"</li>";
        }
        if(!newProfile.getPassword().equals(oldProfile.getPassword())){
            changesHTML += "<li><b>new password: </b>"+newProfile.getPassword()+"</li>";
        }
        if(!newProfile.getEmail().equals(oldProfile.getEmail())){
            changesHTML += "<li><b>new email: </b>"+newProfile.getEmail()+"</li>";
        }
        if(!newProfile.getIsHumanResourcesManager() == oldProfile.getIsHumanResourcesManager()){
            changesHTML += "<li><b>human resources management: </b>"+newProfile.getIsHumanResourcesManager()+"</li>";
        }
        changesHTML += "</ul> <br>";
        if(!newProfile.getSkills().equals(oldProfile.getSkills())) {
            changesHTML += skillScoresChangesToHTML(newProfile.getSkills(), oldProfile.getSkills());
        }
        if(!newProfile.getKnowledges().equals(oldProfile.getKnowledges())) {
            changesHTML += knowledgeScoresChangesToHTML(newProfile.getKnowledges(), oldProfile.getKnowledges());
        }

        return changesHTML;


    }

    private String staffMemberInGroupToHTML(StaffMember staffMember){
        return ("<b>Name: </b>"+staffMember.getName()+" "+staffMember.getLastName()+"<br>"+
                "<b>Email: </b>"+staffMember.getEmail()+"<br>"+"<br>"+
                "<b>Added skills: </b> <br>" +
                skillScoreListToHTML(staffMember.getSkills())+"<br>"+"<br>"+
                "<b>Added knowledges: </b> <br>" +
                knowledgeScoreListToHTML(staffMember.getKnowledges())+"<br>"+"<br>");
    }


    //---------------------------------------------------Miscellany---------------------------------------------------//

    private String skillScoresChangesToHTML(List<SkillScore> newSkillScores, List<SkillScore> oldSkillScores){
        List<SkillScore> deletedSkill = new ArrayList<SkillScore>();
        List<SkillScore> addedSkill = new ArrayList<SkillScore>();
        List<SkillScore> updatedSkill = new ArrayList<SkillScore>();

        for(SkillScore oldSkill: oldSkillScores){
            boolean deleted = true;
            boolean updated = false;
            SkillScore skillScore = null;
            for(SkillScore newSkill: newSkillScores){
                if(oldSkill.getSkillId().equals(newSkill.getSkillId()) && oldSkill.getScore()==newSkill.getScore()) {
                    deleted = false;
                }
                else if(oldSkill.getSkillId().equals(newSkill.getSkillId()) && oldSkill.getScore()!=newSkill.getScore()){
                    updated = true;
                    deleted = false;
                    skillScore = newSkill;
                }
            }
            if(deleted && !updated){
                deletedSkill.add(oldSkill);
            }
            if(updated && !deleted){
                updatedSkill.add(skillScore);
            }
        }


        for(SkillScore newSkill: newSkillScores){
            boolean added = true;
            for(SkillScore oldSkill: oldSkillScores){
                if(newSkill.getSkillId().equals(oldSkill.getSkillId()))
                    added = false;
            }
            if(added) addedSkill.add(newSkill);
        }

        if(!deletedSkill.isEmpty() || !addedSkill.isEmpty() || !updatedSkill.isEmpty()) {
                String changesInSkillHTML = "<p><b>Skill changes: </b><br>" +
                    "<ol>";
                String deletedSkillHTML = "";
                String addedSkillHTML = "";
                String updatedSkillHTML = "";
            if (!deletedSkill.isEmpty()) {
                deletedSkillHTML = "<li><p><b>Deleted skills </b></p> <br>" + skillScoreListToHTML(deletedSkill) + "</li>";
            }
            if (!addedSkill.isEmpty()) {
                addedSkillHTML = "<li><p><b>Added skills </b></p> <br>" + skillScoreListToHTML(addedSkill) + "</li>";
            }
            if (!updatedSkill.isEmpty()) {
                updatedSkillHTML = "<li><p><b>Updated skills </b></p> <br>" + skillScoreListToHTML(updatedSkill) + "</li>";
            }
            return (changesInSkillHTML + deletedSkillHTML + addedSkillHTML + updatedSkillHTML +"</ol></p>");
        }
        else{
            return "";
        }



    }

    private String knowledgeScoresChangesToHTML(List<KnowledgeScore> newKnowledgeScores, List<KnowledgeScore> oldKnowledgeScores){
        List<KnowledgeScore> deletedKnowledge = new ArrayList<KnowledgeScore>();
        List<KnowledgeScore> addedKnowledge = new ArrayList<KnowledgeScore>();
        List<KnowledgeScore> updatedKnowledge = new ArrayList<KnowledgeScore>();


        for(KnowledgeScore oldKnowledge: oldKnowledgeScores){
            boolean deleted = true;
            boolean updated = false;
            KnowledgeScore knowledgeScore = null;
            for(KnowledgeScore newKnowledge: newKnowledgeScores){
                if(oldKnowledge.getKnowledgeId().equals(newKnowledge.getKnowledgeId()) && oldKnowledge.getScore()==newKnowledge.getScore()) {
                    deleted = false;
                }
                else if(oldKnowledge.getKnowledgeId().equals(newKnowledge.getKnowledgeId()) && oldKnowledge.getScore()!=newKnowledge.getScore()){
                    updated = true;
                    deleted = false;
                    knowledgeScore = newKnowledge;
                }
            }
            if(deleted && !updated) deletedKnowledge.add(oldKnowledge);
            if(updated && !deleted) updatedKnowledge.add(knowledgeScore);
        }


        for(KnowledgeScore newKnowledge: newKnowledgeScores){
            boolean added = true;
            for(KnowledgeScore oldKnowledge: oldKnowledgeScores){
                if(newKnowledge.getKnowledgeId().equals(oldKnowledge.getKnowledgeId())) {
                    added = false;
                }
            }
            if(added) addedKnowledge.add(newKnowledge);
        }



        if(!deletedKnowledge.isEmpty() || !addedKnowledge.isEmpty() || !updatedKnowledge.isEmpty()) {
            String changesInKnowledgeHTML = "<p><b>knowledge changes: </b> <br>" +
                    "<ol>";
            String deletedKnowledgeHTML = "";
            String addedKnowledgeHTML = "";
            String updatedKnowledgeHTML = "";
            if (!deletedKnowledge.isEmpty()) {
                deletedKnowledgeHTML = "<li><p><b>Deleted knowledges </b></p> <br>" + knowledgeScoreListToHTML(deletedKnowledge) +"</li>";
            }
            if (!addedKnowledge.isEmpty()) {
                addedKnowledgeHTML = "<li><p><b>Added knowledges </b></p> <br>" + knowledgeScoreListToHTML(addedKnowledge) + "</li>";
            }
            if (!updatedKnowledge.isEmpty()) {
                updatedKnowledgeHTML = "<li><p><b>updated knowledges </b></p> <br>" + knowledgeScoreListToHTML(updatedKnowledge) + "</li>";
            }
            return (changesInKnowledgeHTML + deletedKnowledgeHTML + addedKnowledgeHTML + updatedKnowledgeHTML + "</ol></p>");
        }
        else{
            return "";
        }

    }

    private String skillScoreToHTML(SkillScore skillScore){
        return ("<li>"+skillService.getSkillName(skillScore.getSkillId())+", "+skillScore.getScore()+"</li>");
    }

    private String skillScoreListToHTML(List<SkillScore> skillScores){
        String list = "<ul>";
        for(SkillScore skillScore : skillScores){
            list += skillScoreToHTML(skillScore);
        }
        list += "</ul>";
        return list;
    }

    private String knowledgeScoreToHTML(KnowledgeScore knowledgeScore){
        return ("<li>"+knowledgeService.getKnowledgeName(knowledgeScore.getKnowledgeId())+", "+knowledgeScore.getScore()+"</li>");
    }

    private String knowledgeScoreListToHTML(List<KnowledgeScore> skillKnowledges){
        String list = "<ul>";
        for(KnowledgeScore knowledgeScore : skillKnowledges){
            list += knowledgeScoreToHTML(knowledgeScore);
        }
        list += "</ul>";
        return list;
    }
}
