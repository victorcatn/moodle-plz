package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Document
public class Algorithm {

    private List<StaffMember> staffMembers;
    private Project project;

    /*perfectMatch: The staff members have all needed skills and  knowledges (promedio and one skill with a good score)*/
    private List<StaffMember> perfectMatch;

    /*suggestedStaffMember: The staff members have all needed skills and knowledges (promedio)*/
    private List<StaffMember> suggestedStaffMember;

    /*others: The staff members have at last one knowledge*/
    private List<StaffMember> others;

    /*nonSuggestedStaffMember: don't have any needed skills and knowledges*/
    private List<StaffMember> nonSuggestedStaffMember;

    //Weighs of skills and knowledge
    private final double skillWeight = 0.3;
    private final double knowledgeWeight = 0.7;

    //Weighs of each node
    private final double node1Weight = 0.30;
    private final double node2Weight = 0.30;
    private final double node3Weight = 0.35;

    //The maximun differences between the skills and knowledges of a project and a staff member is 90 points
    //but this differences is too much for 100% so we take half of that value to comparate the differeces
    //between the skills and knowledges of a project and skills and knowledges of a staff member
    private final double percentage100Comparator = 45.0;


    private HashMap<String, Double> memberWeightMap;
    public HashMap<String, Double> node1WeightMap;
    public HashMap<String, Double> node2WeightMap;
    public HashMap<String, Double> node3WeightMap;

    public Algorithm(){

    }

    public Algorithm(List<StaffMember> staffMembers, Project project) {
        this.staffMembers = staffMembers;
        this.project = project;

        this.node1WeightMap = node1(staffMembers, project);
        this.node2WeightMap = node2(staffMembers, project);
        this.node3WeightMap = node3(staffMembers, project);
        this.memberWeightMap = memberWeightMapResume(staffMembers);

    }


    /**
     * Assign a weight according to the skills and knowledge that the staff member has and that the project needs,
     * it only compare if the member has them
     * @param staffMembers list of all staff members in the database
     * @param project the project that need a group
     * @return A hashmap<IdMember, weigh> have the weigh of the node1
     */
    public HashMap<String, Double> node1(List<StaffMember> staffMembers, Project project){

        //Take 1 divided for number of needed an divided by 2, this because it only can amount 1 = 100%
        double weightOfEachSkill = ((1.0/(double)project.getNeededSkills().size())/2);
        double weightOfEachKnowledge = ((1.0/(double)project.getNeededKnowledges().size())/2);

        HashMap<String, Double> node1WeightMap = new HashMap<>();

        for(StaffMember staffMember: staffMembers) {
            node1WeightMap.put(staffMember.getId(), 0.0);

            for (SkillScore projectSkillScore : project.getNeededSkills()) {
                for(SkillScore memberSkillScore:staffMember.getSkills()){
                    if(memberSkillScore.getSkillId().equals(projectSkillScore.getSkillId())){
                        node1WeightMap.put(staffMember.getId(), node1WeightMap.get(staffMember.getId())+weightOfEachSkill);
                    }
                }
            }

            for (KnowledgeScore projectKnowledgeScore : project.getNeededKnowledges()) {
                for(KnowledgeScore memberKnowledgeScore:staffMember.getKnowledges()){
                    if(memberKnowledgeScore.getKnowledgeId().equals(projectKnowledgeScore.getKnowledgeId())){
                        node1WeightMap.put(staffMember.getId(), node1WeightMap.get(staffMember.getId())+weightOfEachKnowledge);
                    }
                }
            }

        }

        return node1WeightMap;

    }

    /**
     * Assign a weight according to the difference between the skills and knowledge scores of the project and the staff
     * member, is like node1 but here it compare the score and its difference.
     * @param staffMembers list of all staff members in the database
     * @param project the project that need a group
     * @return A hashmap<IdMember, weigh> have the weigh of the node2
     */
    public HashMap<String, Double> node2(List<StaffMember> staffMembers, Project project){

        //Take 1 divided for number of needed an divided by 2, this because it only can amount 1 = 100%
        double weightOfEachSkill = ((1.0/(double)project.getNeededSkills().size())/2);
        double weightOfEachKnowledge = ((1.0/(double)project.getNeededKnowledges().size())/2);

        HashMap<String, Double> nodeWeightMap = new HashMap<>();
        HashMap<String, Double> nodeWeightMapSkill = new HashMap<>();
        HashMap<String, Double> nodeWeightMapKnowledge = new HashMap<>();

        /*Se revisa por cada staff member el porcentaje de diferencia de cada skill y knowledge con respecto al proyecto*/
        for(StaffMember staffMember: staffMembers) {

            nodeWeightMapSkill.put(staffMember.getId(), 0.0);
            for (SkillScore projectSkillScore : project.getNeededSkills()) {
                for(SkillScore memberSkillScore:staffMember.getSkills()){ //here we can do a comparator
                    if(memberSkillScore.getSkillId().equals(projectSkillScore.getSkillId())
                            && memberSkillScore.getScore()>projectSkillScore.getScore()){
                        double difference = (Math.abs(memberSkillScore.getScore()-projectSkillScore.getScore()));
                        if(difference<=this.percentage100Comparator){
                            double percentageDifference = weightOfEachSkill*difference/this.percentage100Comparator;
                            nodeWeightMapSkill.put(staffMember.getId(), nodeWeightMapSkill.get(staffMember.getId())+percentageDifference);
                        }
                    }
                }
            }

            nodeWeightMapKnowledge.put(staffMember.getId(), 0.0);
            for (KnowledgeScore projectKnowledgeScore : project.getNeededKnowledges()) {
                for(KnowledgeScore memberKnowledgeScore:staffMember.getKnowledges()){
                    if(memberKnowledgeScore.getKnowledgeId().equals(projectKnowledgeScore.getKnowledgeId())
                            && memberKnowledgeScore.getScore()>projectKnowledgeScore.getScore()){
                        double difference = (Math.abs(memberKnowledgeScore.getScore()-projectKnowledgeScore.getScore()));
                        if(difference<=this.percentage100Comparator){
                            double percentageDifference = weightOfEachKnowledge*difference/this.percentage100Comparator;
                            nodeWeightMapKnowledge.put(staffMember.getId(), nodeWeightMapKnowledge.get(staffMember.getId())+percentageDifference);
                        }
                    }
                }
            }

            nodeWeightMap.put(staffMember.getId(), nodeWeightMapSkill.get(staffMember.getId())+nodeWeightMapKnowledge.get(staffMember.getId()));

        }

        return nodeWeightMap;
    }

    public HashMap<String, Double> node3(List<StaffMember> staffMembers, Project project){

        HashMap<String, Integer> modeSkill = new HashMap<>();
        HashMap<String, Integer> modeKnowledge = new HashMap<>();

        HashMap<String, Double> nodeWeightMap = new HashMap<>();
        HashMap<String, Double> nodeWeightMapSkill = new HashMap<>();
        HashMap<String, Double> nodeWeightMapKnowledge = new HashMap<>();

        for(SkillScore projectSkillScore : project.getNeededSkills()){
            modeSkill.put(projectSkillScore.getSkillId(), 0);
        }

        for(KnowledgeScore projectKnowledgeScore : project.getNeededKnowledges()){
            modeKnowledge.put(projectKnowledgeScore.getKnowledgeId(), 0);
        }

        for(StaffMember staffMember : staffMembers){


            for(SkillScore memberSkillScore : staffMember.getSkills()){
                if(modeSkill.containsKey(memberSkillScore.getSkillId())){
                    modeSkill.put(memberSkillScore.getSkillId(), modeSkill.get(memberSkillScore.getSkillId())+1);
                }
            }
            for(KnowledgeScore memberKnowledgeScore : staffMember.getKnowledges()){
                if(modeKnowledge.containsKey(memberKnowledgeScore.getKnowledgeId())){
                    modeKnowledge.put(memberKnowledgeScore.getKnowledgeId(), modeKnowledge.get(memberKnowledgeScore.getKnowledgeId())+1);
                }
            }
        }

        for(StaffMember staffMember : staffMembers){

            int nSkill = 0;
            for(SkillScore skillScore : staffMember.getSkills()){
                for(SkillScore projectSkillScore : project.getNeededSkills()){
                    if(skillScore.getSkillId().equals(projectSkillScore.getSkillId())) nSkill += 1;
                }
            }


            nodeWeightMapSkill.put(staffMember.getId(), 0.0);
            for(SkillScore memberSkillScore : staffMember.getSkills()){
                if(modeSkill.containsKey(memberSkillScore.getSkillId())){
                    double weighEachSkill = (1.0/(double)modeSkill.get(memberSkillScore.getSkillId()));
                    nodeWeightMapSkill.put(staffMember.getId(), nodeWeightMapSkill.get(staffMember.getId())+weighEachSkill);
                }
            }

            int nKnowledge = 0;
            for(KnowledgeScore knowledgeScore : staffMember.getKnowledges()){
                for(KnowledgeScore projectKnowledgeScore : project.getNeededKnowledges()){
                    if(knowledgeScore.getKnowledgeId().equals(projectKnowledgeScore.getKnowledgeId())) nKnowledge += 1;
                }
            }

            nodeWeightMapKnowledge.put(staffMember.getId(), 0.0);
            for(KnowledgeScore memberKnowledgeScore : staffMember.getKnowledges()){
                if(modeKnowledge.containsKey(memberKnowledgeScore.getKnowledgeId())){
                    double weighEachKnowledge = (1.0/(double)modeKnowledge.get(memberKnowledgeScore.getKnowledgeId()));
                    nodeWeightMapKnowledge.put(staffMember.getId(), nodeWeightMapKnowledge.get(staffMember.getId())+weighEachKnowledge);
                }
            }

            if(nSkill==0) nSkill = 1;
            if(nKnowledge==0) nKnowledge = 1;

            nodeWeightMap.put(staffMember.getId(),
                    ((nodeWeightMapSkill.get(staffMember.getId())/nSkill)+(nodeWeightMapKnowledge.get(staffMember.getId())/nKnowledge))/2.0 );
        }

        return nodeWeightMap;
    }


    public List<StaffMember> getStaffMembers() {
        return staffMembers;
    }

    public void setStaffMembers(List<StaffMember> staffMembers) {
        this.staffMembers = staffMembers;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

    public List<StaffMember> getOthers() {
        return others;
    }

    public void setOthers(List<StaffMember> others) {
        this.others = others;
    }

    public List<StaffMember> getNonSuggestedStaffMember() {
        return nonSuggestedStaffMember;
    }

    public void setNonSuggestedStaffMember(List<StaffMember> nonSuggestedStaffMember) {
        this.nonSuggestedStaffMember = nonSuggestedStaffMember;
    }

    public HashMap<String, Double> getMemberWeightMap() {
        return memberWeightMap;
    }

    public void setMemberWeightMap(HashMap<String, Double> memberWeightMap) {
        this.memberWeightMap = memberWeightMap;
    }

    @Override
    public String toString() {
        return "Algorithm{" +
                "staffMembers=" + staffMembers +
                ", project=" + project +
                ", perfectMatch=" + perfectMatch +
                ", suggestedStaffMember=" + suggestedStaffMember +
                ", others=" + others +
                ", nonSuggestedStaffMember=" + nonSuggestedStaffMember +
                ", memberWeightMap=" + memberWeightMap +
                '}';
    }

    private HashMap<String, Double>  memberWeightMapResume(List<StaffMember> staffMembers){
        HashMap<String, Double> memberWeightMap = new HashMap<>();
        for(StaffMember staffMember : staffMembers){
            memberWeightMap.put(staffMember.getId(),
                    node1WeightMap.get(staffMember.getId())*this.node1Weight
                    + node2WeightMap.get(staffMember.getId())*this.node2Weight
                    + node3WeightMap.get(staffMember.getId())*this.node3Weight);
        }
        return memberWeightMap;
    }

    private List<StaffMemberWeight> hashMapToSortedList(){
        List<StaffMemberWeight> staffMemberWeights = new ArrayList<>();
        for(StaffMember staffMember:this.staffMembers){
            staffMemberWeights.add(new StaffMemberWeight(staffMember.getId(), this.memberWeightMap.get(staffMember.getId())));
        }
        Collections.sort(staffMemberWeights, (o1, o2) -> Double.compare(o2.getWeight(), o1.getWeight()));
        return staffMemberWeights;
    }

}
