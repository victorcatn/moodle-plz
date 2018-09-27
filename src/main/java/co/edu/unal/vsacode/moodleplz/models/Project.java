package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
public class Project {
    @Id
    private String id;
    private String name;

    private Date startDate;
    private Date endDate;

    private List<SkillScore> neededSkills = new ArrayList<>();
    private List<KnowledgeScore> neededKnowledges = new ArrayList<>();


    private String assignedGroupId;

    public Project(String name, Date startDate, List<SkillScore> neededSkills, List<KnowledgeScore> neededKnowledges) {
        this.startDate = startDate;
        this.name = name;
        if(neededSkills != null){this.neededSkills = neededSkills;}
        if(neededKnowledges != null){this.neededKnowledges = neededKnowledges;}
    }

    public Project() {
    }

    public String getAssignedGroupId() {
        return assignedGroupId;
    }

    public void setAssignedGroupId(String assignedGroupId) {
        this.assignedGroupId = assignedGroupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SkillScore> getNeededSkills() {
        return neededSkills;
    }

    public void setNeededSkill(List<SkillScore> neededSkill) {
        this.neededSkills = neededSkill;
    }

    public List<SkillScore> addSNeededSkill(SkillScore skillScore){
        this.neededSkills.add(skillScore);
        return neededSkills;
    }

    public List<KnowledgeScore> getNeededKnowledges() {
        return neededKnowledges;
    }

    public void setNeededKnowledge(List<KnowledgeScore> neededKnowledges) {
        this.neededKnowledges = neededKnowledges;
    }

    public List<KnowledgeScore> addSNeededKnowledge(KnowledgeScore knowledgeScore){
        this.neededKnowledges.add(knowledgeScore);
        return neededKnowledges;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", neededSkills=" + neededSkills +
                ", neededKnowledges=" + neededKnowledges +
                ", assignedGroupId='" + assignedGroupId + '\'' +
                '}';
    }
}
