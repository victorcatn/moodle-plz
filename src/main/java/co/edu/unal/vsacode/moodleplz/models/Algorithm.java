package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Algorithm {

    private List<StaffMember> perfectMatch;
    private List<StaffMember> suggestedStaffMember;
    private List<StaffMember> nonSuggestedStaffMember;

    public Algorithm(){

    }

    public Algorithm(List<StaffMember> perfectMatch, List<StaffMember> suggestedStaffMember, List<StaffMember> nonSuggestedStaffMember) {
        this.perfectMatch = perfectMatch;
        this.suggestedStaffMember = suggestedStaffMember;
        this.nonSuggestedStaffMember = nonSuggestedStaffMember;
    }

    public List<StaffMember> getPerfectMatch() {
        return perfectMatch;
    }

    public void setPerfectMatch(List<StaffMember> perfectMatch) {
        this.perfectMatch = perfectMatch;
    }

    public List<StaffMember> getSuggestedStaffMember() {
        return suggestedStaffMember;
    }

    public void setSuggestedStaffMember(List<StaffMember> suggestedStaffMember) {
        this.suggestedStaffMember = suggestedStaffMember;
    }

    public List<StaffMember> getNonSuggestedStaffMember() {
        return nonSuggestedStaffMember;
    }

    public void setNonSuggestedStaffMember(List<StaffMember> nonSuggestedStaffMember) {
        this.nonSuggestedStaffMember = nonSuggestedStaffMember;
    }

    @Override
    public String toString() {
        return "Algorithm{" +
                "perfectMatch=" + perfectMatch +
                ", suggestedStaffMember=" + suggestedStaffMember +
                ", nonSuggestedStaffMember=" + nonSuggestedStaffMember +
                '}';
    }
}
