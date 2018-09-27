package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private StaffMemberService staffMemberService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private KnowledgeService knowledgeService;

    public EmailService(){

    }


    //-----------------------------------------------Group notification-----------------------------------------------//

    /**
     * Sends an email to all members of the new group
     * @param newGroup the group created with all the members
     */
    public void createGroupNotification(Group newGroup){
        String message = "You have been added to a new group:\n"
                + newGroup.getId() + "\n"
                + "Assigned project: " + newGroup.getProjectId();
        String subject = "[Moodle plz] New group created";
        ArrayList<String> to = getEmailbyId(newGroup.getMembersId());

        sendEmail(to, subject, message);

    }

    /**
     * Compare the old group with the new group and select al the members that left the group, the new members and the members
     * that wasn´t modified, when choose the members send a special notification to each one
     * @param updatedGroup the new group with new membess and members don´t modified
     * @param oldGroup the old group with the members that left the group and members don´t modified
     */
    public void updateGroupNotification(Group updatedGroup, Group oldGroup){

        List<String> idMembersLeft = new ArrayList<>();
        List<String> idMembersAdd = new ArrayList<>();
        List<String> idMembersInform = new ArrayList<>();

        for(String idmemberOldGroup: oldGroup.getMembersId()){
            if(updatedGroup.getMembersId().contains(idmemberOldGroup)){
                idMembersInform.add(idmemberOldGroup);
            }
            else{
                idMembersLeft.add(idmemberOldGroup);
            }
        }
        for(String idMemberUpdatedGroup: updatedGroup.getMembersId()){
            if(!(oldGroup.getMembersId().contains(idMemberUpdatedGroup))){
                idMembersAdd.add(idMemberUpdatedGroup);
            }
        }

        if(!idMembersLeft.isEmpty()){
            updatedGroupNotificationLeft(idMembersLeft, oldGroup);
            if(!idMembersInform.isEmpty()) {
                updatedGroupNotificationInformLeft(idMembersInform, idMembersLeft, oldGroup);
            }
        }
        if(!idMembersAdd.isEmpty()){
            updatedGroupNotificationAdd(idMembersAdd, oldGroup);
            if(!idMembersInform.isEmpty()) {
                updatedGroupNotificationInformAdd(idMembersInform, idMembersAdd, oldGroup);
            }
        }

    }

    /**
     * Sends an email notification to a members of a gruop that was deleted
     * @param deletedGroup the deleted group
     */
    public void deleteGroupNotification(Group deletedGroup){
        String message = "The group "+ deletedGroup.getId()+" have been deleted";
        String subject = "[Moodle plz] Group deleted";
        ArrayList<String> to = getEmailbyId(deletedGroup.getMembersId());

        sendEmail(to, subject, message);
    }

    /**
     * Sends an email notification to the members that leave the group
     * @param toLeft list with the id members that left the group
     * @param group the group of the members
     */
    private void updatedGroupNotificationLeft(List<String> toLeft, Group group){
        String messageLeft = "You have left the group :\n"+
                group.getId();
        String subjectLeft = "[Moodle plz] Group '"+ group.getId()+"' changes";;

        sendEmail(getEmailbyId(toLeft), subjectLeft, messageLeft);
    }

    /**
     * Sends an email notification to the members that have been added to the group
     * @param toAdded list with the id members that was added to the group
     * @param group the group of the members
     */
    private void updatedGroupNotificationAdd(List<String> toAdded, Group group){
        String messageAdded = "You have been added to a group:\n"+
                group.getId();
        String subjectAdded = "[Moodle plz] Group '"+group.getId()+"' changes";;

        sendEmail(getEmailbyId(toAdded), subjectAdded, messageAdded);
    }

    /**
     * Sends an email notification to the members that wasn´t modified informing the members that left the
     * group
     * @param toInform id members to inform
     * @param toLeft id members that leave the group
     * @param group the members group
     */
    private void updatedGroupNotificationInformLeft(List<String> toInform, List<String> toLeft, Group group){

        String messageInform = "The fallows staff members have left the group "+group.getId()+":";

        for(String idMember : toLeft){
            StaffMember staffMember = staffMemberService.getStaffMember(idMember);
            messageInform += "\nDocument: "+ staffMember.getDocument() +
                    " Name: " + staffMember.getName() + " " + staffMember.getLastName();
        }

        String subjectInform = "[Moodle plz] Group '"+group.getId()+"' changes";

        sendEmail(getEmailbyId(toInform), subjectInform, messageInform);
    }

    /**
     * Sends an email notification to the members that wasn´t modified informing the members that was added to the
     * group
     * @param toInform id members to inform
     * @param toAdd id members of the added members in the group
     * @param group the members group
     */
    private void updatedGroupNotificationInformAdd(List<String> toInform, List<String> toAdd, Group group){

        String messageInform = "The fallows staff members have been added to the group "+group.getId()+":";

        for(String idMember : toAdd){
            StaffMember staffMember = staffMemberService.getStaffMember(idMember);
            messageInform += "\nDocument: "+ staffMember.getDocument() +
                    " Name: " + staffMember.getName() + " " + staffMember.getLastName();
        }

        String subjectInform = "[Moodle plz] Group '"+group.getId()+"' changes";

        sendEmail(getEmailbyId(toInform), subjectInform, messageInform);
    }


    //-----------------------------------------------skill notification-----------------------------------------------//

    /**
     * Inform to all staff members that a skill was added in the database
     * @param newSkill the new skill
     */
    public void newSkill(Skill newSkill){
        String message = "New skill have been added to be chosen:\n"
                +"Skill id: " + newSkill.getId() + "\n"
                +"Skill name: "+ newSkill.getName();
        String subject = "[Moodle plz] New skill added to database";
        ArrayList<String> to = new ArrayList<>();

        List<StaffMember> staffMembers = staffMemberService.getStaffMembers();
        staffMembers.forEach(staffMember -> to.add(staffMember.getEmail()));



        sendEmail(to, subject, message);
    }

    /**
     * Inform to all staff members that a skill was modified
     * @param newSkill the modified skill
     * @param oldSkill the unmodified skill
     */
    public void updatedSkill(Skill newSkill, Skill oldSkill){
        String message = "A skill have been updated:\n"
                +"old skill name: "+ oldSkill.getName() + " to " + "new skill name: "+ newSkill.getName();
        String subject = "[Moodle plz] Skill '" + oldSkill.getId() +"' updated";

        ArrayList<String> to = new ArrayList<>();

        List<StaffMember> staffMembers = staffMemberService.getStaffMembers();
        staffMembers.forEach(staffMember -> to.add(staffMember.getEmail()));

        sendEmail(to, subject, message);
    }

    /**
     * Inform to all staff members that a skill was deleted
     * @param deletedSkill the deleted skill
     */
    public void deleteSkill(Skill deletedSkill){
        String message = "A skill have been deleted:\n"
                +"Skill id: " + deletedSkill.getId() + "\n"
                +"Skill name: "+ deletedSkill.getName();

        String subject = "[Moodle plz] Skill '" + deletedSkill.getId() +"' deleted";

        ArrayList<String> to = new ArrayList<>();

        List<StaffMember> staffMembers = staffMemberService.getStaffMembers();
        staffMembers.forEach(staffMember -> to.add(staffMember.getEmail()));

        sendEmail(to, subject, message);
    }


    //-----------------------------------------------skill notification-----------------------------------------------//

    /**
     * Inform to all staff members that a knowledge was added in the database
     * @param newKnowledge the new knowledge
     */
    public void newKnowledge(Knowledge newKnowledge){
        String message = "New knowledge have been added to be chosen:\n"
                +"knowledge id: " + newKnowledge.getId() + "\n"
                +"knowledge name: "+ newKnowledge.getName();
        String subject = "[Moodle plz] New knowledge added to database";
        ArrayList<String> to = new ArrayList<>();

        List<StaffMember> staffMembers = staffMemberService.getStaffMembers();
        staffMembers.forEach(staffMember -> to.add(staffMember.getEmail()));



        sendEmail(to, subject, message);
    }

    /**
     * Inform to all staff members that a knowledge was modified
     * @param newKnowledge the modified knowledge
     * @param oldKnowledge the unmodified knowledge
     */
    public void updatedKnowledge(Knowledge newKnowledge, Knowledge oldKnowledge){
        String message = "A knowledge have been updated:\n"
                +"old knowledge name: "+ oldKnowledge.getName() + " to " + "new knowledge name: "+ newKnowledge.getName();
        String subject = "[Moodle plz] knowledge '" + oldKnowledge.getId() +"' updated";

        ArrayList<String> to = new ArrayList<>();

        List<StaffMember> staffMembers = staffMemberService.getStaffMembers();
        staffMembers.forEach(staffMember -> to.add(staffMember.getEmail()));

        sendEmail(to, subject, message);
    }

    /**
     * Inform to all staff members that a knowledge was deleted
     * @param deletedKnowledge the deleted knowledge
     */
    public void deleteKnowledge(Knowledge deletedKnowledge){
        String message = "A knowledge have been deleted:\n"
                +"knowledge id: " + deletedKnowledge.getId() + "\n"
                +"knowledge name: "+ deletedKnowledge.getName();

        String subject = "[Moodle plz] knowledge '" + deletedKnowledge.getId() +"' deleted";

        ArrayList<String> to = new ArrayList<>();

        List<StaffMember> staffMembers = staffMemberService.getStaffMembers();
        staffMembers.forEach(staffMember -> to.add(staffMember.getEmail()));

        sendEmail(to, subject, message);
    }


    //----------------------------------------------project notification----------------------------------------------//

    /**
     * Inform to all members of the assigend group to the project that the project was modified
     * @param newProject the new project
     * @param oldProject the project without modifies
     */
    public void updatedProject(Project newProject, Project oldProject){
        String message = "A project have been updated:\n \n"
                + "- - - - old project - - - -\n" + toStringProject(oldProject) + "\n" + "\n"
                + "- - - - new project - - - -\n" + toStringProject(newProject);

        String subject = "[Moodle plz] project '" + oldProject.getId() + "' updated";

        ArrayList<String> to = getEmailbyId(groupService.getGroup(newProject.getAssignedGroupId()).getMembersId());

        sendEmail(to, subject, message);
    }

    /**
     * Notify to the assigend group that the project was deleted
     * @param deletedProject
     */
    public void deletedProject(Project deletedProject){
            Group assignedGroup = groupService.getGroup(deletedProject.getAssignedGroupId());
            if(assignedGroup != null) {
                String message = "A project have been deleted:\n"
                        + "project id: " + deletedProject.getId() + "\n"
                        + "project name: " + deletedProject.getName();

                String subject = "[Moodle plz] project '" + deletedProject.getId() + "' deleted";

                ArrayList<String> to = getEmailbyId(assignedGroup.getMembersId());

                sendEmail(to, subject, message);
            }


    }

    /**
     * Inform to the old assigend group that was removed of the project
     * @param oldGroup old assigend group to the project
     * @param project the project
     */
    public void changesAssigendGroup(Group oldGroup, Project project){

        String message =  "Your group was removed of the project: \n"
                + project.getId();

        String subjectLeft = "[Moodle plz] Project '"+ project.getId()+"' changes";
        ArrayList<String> to = getEmailbyId(oldGroup.getMembersId());

        sendEmail(to, subjectLeft, message);
    }




    //--------------------------------------------staffmember notification--------------------------------------------//

    /**
     * Sends an email notification, it confirms that the staff member was register in the system
     * @param staffMember the new staff member
     */
    public void registerStaffMemberProfile(StaffMember staffMember){
        String message = "Your profile was register:\n"
                +"Id: " + staffMember.getId() + "\n"
                +"Username: "+ staffMember.getDocument()+ "\n"
                +"Password: "+staffMember.getPassword();

        String subject = "[Moodle plz] Profile saved";
        ArrayList<String> to = new ArrayList<>();
        to.add(staffMember.getEmail());

        sendEmail(to, subject, message);
    }

    /**
     * Sends an email that notify the profile update
     * @param staffMember the updated staff member
     */
    public void updateStaffMemberProfile(StaffMember staffMember){
        String message = "Your profile was updated:\n"
                +"Id: " + staffMember.getId() + "\n"
                +"Username: "+ staffMember.getDocument()+ "\n"
                +"Password: "+staffMember.getPassword();

        String subject = "[Moodle plz] Profile updated";
        ArrayList<String> to = new ArrayList<>();
        to.add(staffMember.getEmail());

        sendEmail(to, subject, message);
    }

    /**
     * Sends an email to a staff member that says "you are fired"
     * @param staffMember the fired staff member
     */
    public void deleteStaffMemberProfile(StaffMember staffMember){
        String message = "You are fired";

        String subject = "[Moodle plz] Profile disabled";
        ArrayList<String> to = new ArrayList<>();
        to.add(staffMember.getEmail());

        sendEmail(to, subject, message);
    }



    //---------------------------------------------------Miscellany---------------------------------------------------//

    /**
     * Send an email using springappt.noreply@gmail.com
     * @param to the staff members or staff memer that will send the email
     * @param subject the subject of the email
     * @param text the messge of the email
     */
    private void sendEmail(List<String> to , String subject, String text){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to.toArray(new String[0]));
        message.setFrom("moodleplz-noreply");
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }

    /**
     * Take all the id members in a list and return the email of each one
     * @param idMembers list of id members
     * @return list of email
     */
    private ArrayList<String> getEmailbyId(List<String> idMembers){
        ArrayList<String> emails = new ArrayList<>();
        idMembers.forEach((memberId) -> emails.add(staffMemberService.getStaffMember(memberId).getEmail()));
        return emails;
    }

    /**
     * Change a project in to a message
     * @param project
     * @return toString project
     */
    private String toStringProject(Project project) {
        String skills = "needed skills: \n";
        String knowledges = "needed knowledges: \n";

        for(SkillScore skillScore : project.getNeededSkills()){
            skills += " * Name: "+ skillService.getSkillName(skillScore.getSkillId()) + ", Score: " + skillScore.getScore() + "\n";
        }

        for(KnowledgeScore knowledgeScore : project.getNeededKnowledges()){
            knowledges += " * Name: "+ knowledgeService.getKnowledgeName(knowledgeScore.getKnowledgeId()) + ", Score: " + knowledgeScore.getScore() + "\n";
        }

        return  "id='" + project.getId() + '\n' +
                "name='" + project.getName() + '\n' +
                "startDate=" + project.getStartDate() + '\n' +
                "endDate=" + project.getEndDate() + '\n' +
                "assignedGroupId='" + project.getAssignedGroupId() + '\n' + '\n' +
                skills + '\n' +
                knowledges + '\n';
    }






}
