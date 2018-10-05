package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document
public class StaffMember {

    @Id
    private String id;
    private String document;
    private String email;
    private String password;
    private String name;
    private String lastName;

    private Boolean isHumanResourcesManager; //TODO: separate classes for HRM and staff member

    private List<SkillScore> skills = new ArrayList<>();
    private List<KnowledgeScore> knowledges = new ArrayList<>();



    public StaffMember() {
    }

    public StaffMember(String id, String document, String email, String password, String name, String lastName, Boolean isHumanResourcesManager, List<SkillScore> skills, List<KnowledgeScore> knowledges) {
        this.id = id;
        this.document = document;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.isHumanResourcesManager = isHumanResourcesManager;
        this.skills = skills;
        this.knowledges = knowledges;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<SkillScore> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillScore> skills) {
        this.skills = skills;
    }

    public List<SkillScore> addSkill(SkillScore skill){
        this.skills.add(skill);
        return skills;
    }

    public List<KnowledgeScore> getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(List<KnowledgeScore> knowledges) {
        this.knowledges = knowledges;
    }

    public List<KnowledgeScore> addKnowledge(KnowledgeScore knowledge){
        this.knowledges.add(knowledge);
        return knowledges;
    }

    public Boolean getIsHumanResourcesManager() {
        return isHumanResourcesManager;
    }

    public void setIsHumanResourcesManager(Boolean isHumanResourcesManager) {
        this.isHumanResourcesManager = isHumanResourcesManager;
    }



    @Override
    public String toString() {
        return "StaffMember{" +
                "id='" + id + '\'' +
                ", document='" + document + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isHumanResourcesManager=" + isHumanResourcesManager +
                ", skills=" + skills +
                ", knowledges=" + knowledges +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffMember that = (StaffMember) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(document, that.document) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(name, that.name) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(isHumanResourcesManager, that.isHumanResourcesManager) &&
                Objects.equals(skills, that.skills) &&
                Objects.equals(knowledges, that.knowledges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, document, email, password, name, lastName, isHumanResourcesManager, skills, knowledges);
    }
}
