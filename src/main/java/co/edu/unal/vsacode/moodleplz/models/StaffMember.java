package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

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

    private List<Skill> skills = new ArrayList<>();
    private List<Knowledge> knowledges = new ArrayList<>();

    private String groupId;

    public StaffMember(String document, String email, String password, String name, String lastName){
        this.document = document;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
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

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Skill> addSkill(Skill skill){
        this.skills.add(skill);
        return skills;
    }

    public List<Knowledge> getKnowledge() {
        return knowledges;
    }

    public void setKnowledges(List<Knowledge> knowledges) {
        this.knowledges = knowledges;
    }

    public List<Knowledge> addKnowledge(Knowledge knowledge){
        this.knowledges.add(knowledge);
        return knowledges;
    }

    public Boolean isHumanResourcesManager() {
        return isHumanResourcesManager;
    }

    public void setHumanResourcesManager(Boolean isHumanResourcesManager) {
        this.isHumanResourcesManager = isHumanResourcesManager;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
