package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class StaffMemberWeight {
    private String idStaffMember;
    private double weight;

    public StaffMemberWeight(String idStaffMember, double weight) {
        this.idStaffMember = idStaffMember;
        this.weight = weight;
    }


    public String getIdStaffMember() {
        return idStaffMember;
    }

    public void setIdStaffMember(String idStaffMember) {
        this.idStaffMember = idStaffMember;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffMemberWeight that = (StaffMemberWeight) o;
        return weight == that.weight &&
                Objects.equals(idStaffMember, that.idStaffMember);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStaffMember, weight);
    }

    @Override
    public String toString() {
        return "StaffMemberWeight{" +
                "idStaffMember='" + idStaffMember + '\'' +
                ", weight=" + weight +
                '}';
    }
}
