package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Group {
    @Id
    private List<StaffMember> members;
    private Project project;
    //private String name; TODO

    public List<StaffMember> getMembers() {
        return members;
    }

    public void setMembers(List<StaffMember> members) {
        this.members = members;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    public Group(List<StaffMember> members, Project project) {
        this.members = members;
        this.project = project;
    }
}
