package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Algorithm {

    /*suggestedStaffMember: The staff members have all needed skills and knowledges (promedio)*/
    private List<StaffMember> suggestedStaffMember;

    /*others: The staff members have at last one knowledge*/
    private List<StaffMember> others;

    /*nonSuggestedStaffMember: don't have any needed skills and knowledges*/
    private List<StaffMember> nonSuggestedStaffMember;

    public Algorithm(){
        suggestedStaffMember = new ArrayList<>();
        others = new ArrayList<>();
        nonSuggestedStaffMember = new ArrayList<>();
    }


    public List<StaffMember> getSuggestedStaffMember() {
        return suggestedStaffMember;
    }

    public void setSuggestedStaffMember(List<StaffMember> suggestedStaffMember) {
        this.suggestedStaffMember = suggestedStaffMember;
    }

    public void addSuggestedStaffMember(StaffMember staffMember){
        this.suggestedStaffMember.add(staffMember);
    }

    public List<StaffMember> getOthers() {
        return others;
    }

    public void setOthers(List<StaffMember> others) {
        this.others = others;
    }

    public void addOthers(StaffMember staffMember) {
        this.others.add(staffMember);
    }

    public List<StaffMember> getNonSuggestedStaffMember() {
        return nonSuggestedStaffMember;
    }

    public void setNonSuggestedStaffMember(List<StaffMember> nonSuggestedStaffMember) {
        this.nonSuggestedStaffMember = nonSuggestedStaffMember;
    }

    public void addNonSuggestedStaffMember(StaffMember staffMember) {
        this.nonSuggestedStaffMember.add(staffMember);
    }

    @Override
    public String toString() {
        return "Algorithm{" +
                "suggestedStaffMember=" + suggestedStaffMember +
                ", others=" + others +
                ", nonSuggestedStaffMember=" + nonSuggestedStaffMember +
                '}';
    }
}
