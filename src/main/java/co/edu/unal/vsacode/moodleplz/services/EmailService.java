package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.MailTemplate;
import co.edu.unal.vsacode.moodleplz.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
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

    @Autowired
    private MailTemplate mailTemplate;

    public EmailService(){

    }


    //-----------------------------------------------Group notification-----------------------------------------------//

    /**
     * Sends an email to all members of the new group
     * @param newGroup the group created with all the members
     */
    @Async
    public void createGroupNotification(Group newGroup){
        String message = mailTemplate.newGroupTemplate(newGroup);
        String subject = "[Moodle plz] New group created";
        ArrayList<String> to = getEmailbyId(newGroup.getMembersId());

        try {
            sendEmail(to, subject, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Compare the old group with the new group and select al the members that left the group, the new members and the members
     * that wasn´t modified, when choose the members send a special notification to each one
     * @param updatedGroup the new group with new membess and members don´t modified
     * @param oldGroup the old group with the members that left the group and members don´t modified
     */
    public void updateGroupNotification(Group updatedGroup, Group oldGroup){

        if(!updatedGroup.equals(oldGroup)) {
            List<String> idMembersLeft = new ArrayList<>();
            List<String> idMembersAdd = new ArrayList<>();
            List<String> idMembersInform = new ArrayList<>();

            for (String idmemberOldGroup : oldGroup.getMembersId()) {
                if (updatedGroup.getMembersId().contains(idmemberOldGroup)) {
                    idMembersInform.add(idmemberOldGroup);
                } else {
                    idMembersLeft.add(idmemberOldGroup);
                }
            }
            for (String idMemberUpdatedGroup : updatedGroup.getMembersId()) {
                if (!(oldGroup.getMembersId().contains(idMemberUpdatedGroup))) {
                    idMembersAdd.add(idMemberUpdatedGroup);
                }
            }

            if (!idMembersLeft.isEmpty()) {
                updatedGroupNotificationLeft(idMembersLeft, updatedGroup);
            }
            if (!idMembersAdd.isEmpty()) {
                updatedGroupNotificationAdd(idMembersAdd, updatedGroup);
            }
            if (!idMembersInform.isEmpty()) {
                updatedGroupNotificationInform(idMembersInform, updatedGroup);
            }
        }

    }

    /**
     * Sends an email notification to a members of a gruop that was deleted
     * @param deletedGroup the deleted group
     */
    public void deleteGroupNotification(Group deletedGroup){
        String message = mailTemplate.deletedGroupTemplate(deletedGroup);
        String subject = "[Moodle plz] Group deleted";
        ArrayList<String> to = getEmailbyId(deletedGroup.getMembersId());

        try {
            sendEmail(to, subject, message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Sends an email notification to the members that leave the group
     * @param toLeft list with the id members that left the group
     * @param group the group of the members
     */
    private void updatedGroupNotificationLeft(List<String> toLeft, Group group){
        String messageLeft = mailTemplate.leftMembersTemplate(group);
        String subjectLeft = "[Moodle plz] Group '"+ group.getId()+"' changes";
        ArrayList<String> to = getEmailbyId(toLeft);
        try {
            sendEmail(to, subjectLeft, messageLeft);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends an email notification to the members that have been added to the group
     * @param toAdded list with the id members that was added to the group
     * @param group the group of the members
     */
    private void updatedGroupNotificationAdd(List<String> toAdded, Group group){
        String messageAdded = mailTemplate.addedMembersTemplate(group);
        String subjectAdded = "[Moodle plz] Group '"+group.getId()+"' changes";
        ArrayList<String> to = getEmailbyId(toAdded);

        try {
            sendEmail(to, subjectAdded, messageAdded);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Sends an email notification to the members that wasn´t modified showing the list of members
     * @param toInform id members to inform
     * @param group the members group
     */
    private void updatedGroupNotificationInform(List<String> toInform, Group group){

        String messageInform = mailTemplate.informMembersTemplate(group);

        String subjectInform = "[Moodle plz] Group '"+group.getId()+"' changes";
        ArrayList<String> to = getEmailbyId(toInform);

        try {
            sendEmail(to, subjectInform, messageInform);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //-----------------------------------------------skill notification-----------------------------------------------//

    /**
     * Inform to all staff members that a skill was added in the database
     * @param newSkill the new skill
     */
    public void newSkill(Skill newSkill){
        String message = mailTemplate.newSkillTemplate(newSkill);
        String subject = "[Moodle plz] New skill added to database";
        ArrayList<String> to = new ArrayList<>();

        List<StaffMember> staffMembers = staffMemberService.getStaffMembers();
        staffMembers.forEach(staffMember -> to.add(staffMember.getEmail()));

        try {
            sendEmail(to, subject, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inform to all staff members that a skill was modified
     * @param newSkill the modified skill
     * @param oldSkill the unmodified skill
     */
    public void updatedSkill(Skill newSkill, Skill oldSkill){
        if(!oldSkill.equals(newSkill)) {
            String message = mailTemplate.updateSkillTemplate(newSkill, oldSkill);
            String subject = "[Moodle plz] Skill '" + oldSkill.getId() + "' updated";

            ArrayList<String> to = new ArrayList<>();

            List<StaffMember> staffMembers = staffMemberService.getStaffMembers();
            staffMembers.forEach(staffMember -> to.add(staffMember.getEmail()));
            try {
                sendEmail(to, subject, message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inform to all staff members that a skill was deleted
     * @param deletedSkill the deleted skill
     */
    public void deleteSkill(Skill deletedSkill){
        String message = mailTemplate.deletSkillTemplate(deletedSkill);

        String subject = "[Moodle plz] Skill '" + deletedSkill.getId() +"' deleted";

        ArrayList<String> to = new ArrayList<>();

        List<StaffMember> staffMembers = staffMemberService.getStaffMembers();
        staffMembers.forEach(staffMember -> to.add(staffMember.getEmail()));
        try {
            sendEmail(to, subject, message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //---------------------------------------------knowledge notification---------------------------------------------//

    /**
     * Inform to all staff members that a knowledge was added in the database
     * @param newKnowledge the new knowledge
     */
    public void newKnowledge(Knowledge newKnowledge){
        String message = mailTemplate.newKnowledgeTemplate(newKnowledge);
        String subject = "[Moodle plz] New knowledge added to database";
        ArrayList<String> to = new ArrayList<>();

        List<StaffMember> staffMembers = staffMemberService.getStaffMembers();
        staffMembers.forEach(staffMember -> to.add(staffMember.getEmail()));

        try {
            sendEmail(to, subject, message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Inform to all staff members that a knowledge was modified
     * @param newKnowledge the modified knowledge
     * @param oldKnowledge the unmodified knowledge
     */
    public void updatedKnowledge(Knowledge newKnowledge, Knowledge oldKnowledge){
        if(!oldKnowledge.equals(newKnowledge)) {
            String message = mailTemplate.updateKnowledgeTemplate(newKnowledge, oldKnowledge);
            String subject = "[Moodle plz] knowledge '" + oldKnowledge.getId() + "' updated";

            ArrayList<String> to = new ArrayList<>();

            List<StaffMember> staffMembers = staffMemberService.getStaffMembers();
            staffMembers.forEach(staffMember -> to.add(staffMember.getEmail()));

            try {
                sendEmail(to, subject, message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inform to all staff members that a knowledge was deleted
     * @param deletedKnowledge the deleted knowledge
     */
    public void deleteKnowledge(Knowledge deletedKnowledge){
        String message = mailTemplate.deleteKnowledgeTemplate(deletedKnowledge);

        String subject = "[Moodle plz] knowledge '" + deletedKnowledge.getId() +"' deleted";

        ArrayList<String> to = new ArrayList<>();

        List<StaffMember> staffMembers = staffMemberService.getStaffMembers();
        staffMembers.forEach(staffMember -> to.add(staffMember.getEmail()));

        try {
            sendEmail(to, subject, message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------project notification----------------------------------------------//

    /**
     * Inform to all members of the assigend group to the project that the project was modified
     * @param newProject the new project
     * @param oldProject the project without modifies
     */
    public void updatedProject(Project newProject, Project oldProject){
        if(!oldProject.equals(newProject)) {
            String message = mailTemplate.updatedProjectTemplate(newProject, oldProject);

            String subject = "[Moodle plz] project '" + oldProject.getId() + "' updated";

            ArrayList<String> to = getEmailbyId(groupService.getGroup(newProject.getAssignedGroupId()).getMembersId());

            try {
                sendEmail(to, subject, message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Notify to the assigend group that the project was deleted
     * @param deletedProject
     */
    public void deletedProject(Project deletedProject){
            Group assignedGroup = groupService.getGroup(deletedProject.getAssignedGroupId());
            if(assignedGroup != null) {
                String message = mailTemplate.deleteProjectTemplate(deletedProject);

                String subject = "[Moodle plz] project '" + deletedProject.getId() + "' deleted";

                ArrayList<String> to = getEmailbyId(assignedGroup.getMembersId());

                try {
                    sendEmail(to, subject, message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


    }

    /**
     * Inform to the old assigend group that was removed of the project
     * @param oldGroup old assigend group to the project
     * @param project the project
     */
    public void changesAssigendGroup(Group oldGroup, Project project){

        String message =  mailTemplate.changesAssigendGroupTemplate(project);

        String subjectLeft = "[Moodle plz] Project '"+ project.getId()+"' changes";
        ArrayList<String> to = getEmailbyId(oldGroup.getMembersId());

        try {
            sendEmail(to, subjectLeft, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //--------------------------------------------staffmember notification--------------------------------------------//

    /**
     * Sends an email notification, it confirms that the staff member was register in the system
     * @param staffMember the new staff member
     */
    public void registerStaffMemberProfile(StaffMember staffMember){

        String message = mailTemplate.newStaffMemberTemplate(staffMember);

        String subject = "[Moodle plz] Profile saved";
        ArrayList<String> to = new ArrayList<>();
        to.add(staffMember.getEmail());

        try {
            sendEmail(to, subject, message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Sends an email that notify the profile update
     * @param newProfile the updated staff member
     * @param oldProfile the old profile of the staff member
     */
    public void updateStaffMemberProfile(StaffMember newProfile, StaffMember oldProfile){
        if(!newProfile.equals(oldProfile)) {
            String message = mailTemplate.updatedStaffMemberTemplate(newProfile, oldProfile);

            String subject = "[Moodle plz] Profile updated";
            ArrayList<String> to = new ArrayList<>();
            to.add(newProfile.getEmail());

            try {
                sendEmail(to, subject, message);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Sends an email to a staff member that says "you are fired"
     * @param staffMember the fired staff member
     */
    public void deleteStaffMemberProfile(StaffMember staffMember){
        String message = mailTemplate.deleteStaffMemberTemplate(staffMember);

        String subject = "[Moodle plz] Profile disabled";
        ArrayList<String> to = new ArrayList<>();
        to.add(staffMember.getEmail());

        try {
            sendEmail(to, subject, message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    //---------------------------------------------------Miscellany---------------------------------------------------//

    /**
     * Send an email using springappt.noreply@gmail.com
     * @param to the staff members or staff memer that will send the email
     * @param subject the subject of the email
     * @param text the messge of the email
     */
    private void sendEmail(List<String> to , String subject, String text) throws Exception{

        MimeMessage message = emailSender.createMimeMessage();

        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to.toArray(new String[0]));
        helper.setText(text, true);
        helper.setSubject(subject);
        new Thread(() -> emailSender.send(message)).start();
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
