package co.edu.unal.vsacode.moodleplz;

import co.edu.unal.vsacode.moodleplz.models.*;
import co.edu.unal.vsacode.moodleplz.services.ProjectService;
import co.edu.unal.vsacode.moodleplz.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailTemplate {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ProjectService projectService;

    private final String warning = "This email is only informative and is for the exclusive use of the recipient, it may contain privileged and / or confidential information. If you are not the recipient, you should delete it immediately. It is notified that misuse, unauthorized disclosure, alteration and / or malicious modification of this message and its attachments are strictly prohibited and may be legally sanctioned. -Moodle-plz does not assume any responsibility for these circumstances-";
    //-----------------------------------------------Group notification-----------------------------------------------//

    public String newGroupTemplate(Group group){
        return ("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> New group </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>You have been assigned to a new group</p>"+
                "<p><b>Assigned project </b>"+templateService.AssginedProjectToHTML(group)+"<br></p>"+
                "<p>"+templateService.groupMemberToHTML(group)+"</p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }

    public String deletedGroupTemplate(Group group){
        return ("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> Deleted group </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>Your group was removed</p>"+
                "<p><b>Assigned project </b>"+templateService.AssginedProjectToHTML(group)+"<br></p>"+
                "<p>"+templateService.groupMemberToHTML(group)+"</p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }

    public String leftMembersTemplate(Group group){
        return("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> Updated group </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>You have left the group "+projectService.getProjectById(group.getProjectId()).getName()+"</p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }

    public String addedMembersTemplate(Group group){
        return ("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> Updated group </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>You have been assigned to a group</p>"+
                "<p><b>Assigned project </b>"+templateService.AssginedProjectToHTML(group)+"<br></p>"+
                "<p>"+templateService.groupMemberToHTML(group)+"</p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }

    public String informMembersTemplate(Group group){
        return ("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> Updated group </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>The members of the group have been reassigned</p>"+
                "<p>"+templateService.groupMemberToHTML(group)+"</p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }

    //-----------------------------------------------knowledge notification-----------------------------------------------//

    public String newKnowledgeTemplate(Knowledge knowledge){
        return ("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> New knowledge </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>A new knowledge have been added, you can add it to your profile now!!</p>"+
                "<p><ul><li> <b>id: </b>"+knowledge.getId()+", <b>name: </b>"+knowledge.getName()+"</li></ul></p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }

    public String deleteKnowledgeTemplate(Knowledge knowledge){
        return ("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> Deleted knowledge </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>A knowledge was deleted of the platform:</p>"+
                "<p><ul><li> <b>id: </b>"+knowledge.getId()+", <b>name: </b>"+knowledge.getName()+"</li></ul></p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }

    public String updateKnowledgeTemplate(Knowledge newknowledge, Knowledge oldknowledge){
        return ("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> Updated knowledge </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>the knowledge with id:" +newknowledge.getId()+ "was updated</p>"+
                "<p><ul><li> <b>old name: </b>"+oldknowledge.getName()+", <b>new name: </b>"+newknowledge.getName()+"</li></ul></p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }


    //-----------------------------------------------skill notification-----------------------------------------------//

    public String newSkillTemplate(Skill skill){
        return ("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> New skill </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>A new skill have been added, you can add it your profile now!!</p>"+
                "<p><ul><li> <b>id: </b>"+skill.getId()+", <b>name: </b>"+skill.getName()+"</li></ul></p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }

    public String deletSkillTemplate(Skill skill){
        return ("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> Deleted skill </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>A skill was deleted of the platform:</p>"+
                "<p><ul><li> <b>id: </b>"+skill.getId()+", <b>name: </b>"+skill.getName()+"</li></ul></p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }

    public String updateSkillTemplate(Skill newSkill, Skill oldSkill){
        return ("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> Updated skill </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>The skill with id:" +newSkill.getId()+ "was updated</p>"+
                "<p><ul><li> <b>old name: </b>"+oldSkill.getName()+", <b>new name: </b>"+newSkill.getName()+"</li></ul></p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }


    //---------------------------------------------project HTML templates---------------------------------------------//

    public String deleteProjectTemplate(Project project){
        return ("<html>"+
                    head()+
                    "<body style='margin: 0; padding: 0;'>"+
                        "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                            "<tr>"+
                                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                                    "<h1> Deleted project </h1>"+
                                "</td>"+
                            "</tr>"+
                            "<tr>"+
                                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                                    "<h2>Greetings: </h2>"+
                                    "<p>Your assigned project was deleted</p>"+
                                    "<p>"+templateService.projectToHTML(project)+"</p>"+
                                "</td>"+
                            "</tr>"+
                            disclouser()+
                        "</table>"+
                    "<body>" +
                "</html>");
    }

    public String updatedProjectTemplate(Project newProject, Project oldProject){
        return ("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> Project updated </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>The project "+oldProject.getName()+"have been updated</p>"+
                "<p>changes: </p>"+
                "<p>"+templateService.projectChangesToHTML(newProject, oldProject)+"</p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }

    public String changesAssigendGroupTemplate(Project project){
        return ("<html>"+
                head()+
                "<body style='margin: 0; padding: 0;'>"+
                "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                "<tr>"+
                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                "<h1> Updated project </h1>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                "<h2>Greetings: </h2>"+
                "<p>Your group was removed of the project"+ project.getName()+"</p>"+
                "<p>"+templateService.projectToHTML(project)+"</p>"+
                "</td>"+
                "</tr>"+
                disclouser()+
                "</table>"+
                "<body>" +
                "</html>");
    }

    //-------------------------------------------staffmember HTML templates-------------------------------------------//

    public String newStaffMemberTemplate(StaffMember staffMember){
        return ("<html>"+
                    head()+
                    "<body style='margin: 0; padding: 0;'>"+
                        "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                            "<tr>"+
                                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                                    "<h1> You are welcome to moodle plz</h1>"+
                                "</td>"+
                            "</tr>"+
                            "<tr>"+
                                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                                    "<h2>Greetings " +staffMember.getName()+": </h2>"+
                                    "<p>Your profile was successfully saved in moodle plz</p>"+
                                    "<p>"+templateService.staffMemberToHTML(staffMember)+"</p>"+
                                "</td>"+
                            "</tr>"+
                            disclouser()+
                        "</table>"+
                    "<body>" +
                "</html>");
    }

    public String deleteStaffMemberTemplate(StaffMember staffMember){
        return ("<html>"+
                    head()+
                    "<body style='margin: 0; padding: 0;'>"+
                        "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                            "<tr>"+
                                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                                    "<h1> You are fired </h1>"+
                                "</td>"+
                            "</tr>"+
                            "<tr>"+
                                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                                    "<h2>Greetings " +staffMember.getName()+": </h2>"+
                                    "<p>Your profile was removed from moodle plz</p>"+
                                    "<p>"+templateService.staffMemberToHTML(staffMember)+"</p>"+
                                "</td>"+
                            "</tr>"+
                            disclouser()+
                        "</table>"+
                    "<body>" +
                "</html>");
    }

    public String updatedStaffMemberTemplate(StaffMember newProfile, StaffMember oldProfile){
        return ("<html>"+
                    head()+
                    "<body style='margin: 0; padding: 0;'>"+
                        "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse;'>"+
                            "<tr>"+
                                "<td align='center' bgcolor='#029fe1' style='padding: 40px 0 30px 0;'>"+
                                "<h1> Profile updated </h1>"+
                                "</td>"+
                            "</tr>"+
                            "<tr>"+
                                "<td bgcolor='#eaeaea' style='padding: 40px 30px 40px 30px;'>"+
                                    "<h2>Greetings " +newProfile.getName()+": </h2>"+
                                    "<p>Your profile was successfully updated in moodle plz</p>"+
                                    "<p>changes: </p>"+
                                    "<p>"+templateService.staffMemberChangesToHTML(newProfile, oldProfile)+"</p>"+
                                "</td>"+
                            "</tr>"+
                            disclouser()+
                        "</table>"+
                    "<body>" +
                "</html>");
    }


    //---------------------------------------------------Miscellany---------------------------------------------------//


    private  String head(){
        return ("<head>"+
                    "<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'/>"+
                    "<style>" +
                        "body {font-family: 'Roboto', sans-serif; font-size: 48px; }" +
                        "h1{color: 'white'}"+
                    "</style>"+
                "</head>");

    }

    private  String disclouser(){
        return ("<tr>"+
                    "<td bgcolor='#777777' style='padding: 30px 30px 30px 30px;'>"+
                        "<p>Sending by moodle plz, don´t reply this message</p>"+
                        "<p>"+warning+"</p>"+
                    "</td>"+
                "</tr>");
    }
}
