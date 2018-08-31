package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public class StaffMember {
    @Id
    private String id;

    private String idCard;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private List<Skill> skills;
    private List<Knowledge> knowledges;
    private List<Group> groups;

    public StaffMember(String idCard, String email, String password, String name, String lastName,
                       List<Skill> skills, List<Knowledge> knowledges, List<Group> groups) {
        this.idCard = idCard;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.skills = skills;
        this.knowledges = knowledges;
        this.groups = groups;
    }

    public StaffMember(String idCard, String email, String password, String name, String lastName){
        this.idCard = idCard;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    public StaffMember(String idCard, String email, String name, String lastName){
        this.idCard = idCard;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public List<Knowledge> getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(List<Knowledge> knowledges) {
        this.knowledges = knowledges;
    }

    @Override
    public String toString(){
        return String.format(
                "Customer[id=%s, Identification Card='%s', Name='%s', lastName='%s', Email='%s']",
                id, idCard, name,lastName, email);
    }

}
