package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public class StaffMember {

    @Id
    private String id;
    private String document;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private List<Skill> skills;
    private List<Knowledge> knowledge;
    private List<Group> groups;

    public StaffMember(){

    }

    public StaffMember(String document, String email, String password, String name, String lastName){
        this.document = document;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
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

    @Override
    public String toString(){
        return String.format(
                "Customer[id=%s, Identification Card='%s', Name='%s', lastName='%s', Email='%s']",
                id, document, name,lastName, email);
    }

}
