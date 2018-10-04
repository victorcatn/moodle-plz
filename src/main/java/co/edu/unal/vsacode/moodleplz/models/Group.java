package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document
public class Group {

    @Id
    private String id;
    private List<String> membersId;
    private String projectId;
    //private String name; TODO



    public Group() {
    }

    public Group(List<String> membersId, String projectId) {
        this.membersId = membersId;
        this.projectId = projectId;
    }

    public List<String> getMembersId() {
        return membersId;
    }

    public void setMembersId(List<String> membersId) {
        this.membersId = membersId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", membersId=" + membersId +
                ", projectId='" + projectId + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) &&
                Objects.equals(membersId, group.membersId) &&
                Objects.equals(projectId, group.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, membersId, projectId);
    }
}
