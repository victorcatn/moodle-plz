package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class StaffMember {

    @Id
    private String id;
    private String document;
    private String email;
    private String name;
    private String lastName;
    private String state;
    private String password;
    private String typeStaffMember;
    private List<Skill> skills;
    private List<Knowledge> knowledge;
    private List<Group> groups;

    public StaffMember(){

    }
    public StaffMember(String document, String email, String name, String lastName, String state, String password, String typeStaffMember) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.state = state;
        this.password = password;
        this.typeStaffMember = typeStaffMember;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Knowledge> getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(List<Knowledge> knowledge) {
        this.knowledge = knowledge;
    }

    public String getId() {
        return id;
    }


    public String getTypeStaffMember() {
        return typeStaffMember;
    }

    public void setTypeStaffMember(String typeStaffMember) {
        this.typeStaffMember = typeStaffMember;
    }

    @Override
    public String toString() {
        return "StaffMember{" +
                "id='" + id + '\'' +
                ", document='" + document + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", state='" + state + '\'' +
                ", typeStaffMember='" + typeStaffMember + '\'' +
                ", skills=" + skills +
                ", knowledge=" + knowledge +
                ", groups=" + groups +
                '}';
    }
}
