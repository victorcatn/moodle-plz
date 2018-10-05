package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class AlgorithmService {

    private Algorithm algorithm;


    public Algorithm generateAlgorithm(List<StaffMember> staffMembers, Project project){
        algorithm = new Algorithm();

        //Weighs of skills and knowledge
        double skillWeight = 0.3;
        double knowledgeWeight = 0.7;

        //Weighs of each node
        double node1Weight = 0.30;
        double node2Weight = 0.30;
        double node3Weight = 0.35;

        //The maximun differences between the skills and knowledges of a project and a staff member is 90 points
        //but this differences is too much for 100% so we take half of that value to comparate the differeces
        //between the skills and knowledges of a project and skills and knowledges of a staff member
        double percentage100Comparator = 45.0;

        HashMap<String, Double> memberWeightMap =
                memberWeightMapResume(
                        staffMembers,
                        node1(staffMembers, project, skillWeight, knowledgeWeight), node1Weight,
                        node2(staffMembers, project, percentage100Comparator), node2Weight,
                        node3(staffMembers, project), node3Weight);

        fillList(hashMapToSortedList(staffMembers, memberWeightMap),staffMembers);

        return algorithm;
    }

    /**
     * Assign a weight according to the skills and knowledge that the staff member has and that the project needs,
     * it only compare if the member has them
     * @param staffMembers list of all staff members in the database
     * @param project the project that need a group
     * @return A hashmap<IdMember, weigh> have the weigh of the node1
     */
    private HashMap<String, Double> node1(List<StaffMember> staffMembers, Project project, double skillWeight, double knowledgeWeight){

        //Take 1 divided for number of needed and multiply for the general weigh of skills a knowledge,
        // this because it only can amount 1 = 100%
        double weightOfEachSkill = ((1.0/(double)project.getNeededSkills().size())*skillWeight);
        double weightOfEachKnowledge = ((1.0/(double)project.getNeededKnowledges().size())*knowledgeWeight);

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
    private HashMap<String, Double> node2(List<StaffMember> staffMembers, Project project , double percentage100Comparator){

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
                        if(difference<=percentage100Comparator){
                            double percentageDifference = weightOfEachSkill*difference/percentage100Comparator;
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
                        if(difference<=percentage100Comparator){
                            double percentageDifference = weightOfEachKnowledge*difference/percentage100Comparator;
                            nodeWeightMapKnowledge.put(staffMember.getId(), nodeWeightMapKnowledge.get(staffMember.getId())+percentageDifference);
                        }
                    }
                }
            }

            nodeWeightMap.put(staffMember.getId(), nodeWeightMapSkill.get(staffMember.getId())+nodeWeightMapKnowledge.get(staffMember.getId()));

        }

        return nodeWeightMap;
    }

    /**
     * Assign a weight according to the number of times an ability or knowledge appears in the staff members, the
     * skill or knowledge less frequently gets more weight,
     * @param staffMembers the list of staff members in the database
     * @param project the project that need a group
     * @return A hashmap<IdMember, weigh> have the weigh of the node3
     */
    private HashMap<String, Double> node3(List<StaffMember> staffMembers, Project project){

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


    /**
     * Fill the list ot the class algorithm with the staff members with the next criteria:
     * - if weigh is less equal than 20 the staff member is not suggested for the project
     * - if the weigh is more than 20 and less equal to 50, the staff member is other staff member that can be choosen for
     *   the project
     * - id the weigh is more than 50 the staff member is suggested
     * @param staffMemberWeights
     * @param staffMembers
     */
    private void fillList(List<StaffMemberWeight> staffMemberWeights, List<StaffMember> staffMembers){
        for(StaffMemberWeight staffMemberWeight : staffMemberWeights){
            for(StaffMember staffMember : staffMembers){
                if(staffMember.getId().equals(staffMemberWeight.getIdStaffMember()) && staffMember.getAvailable()){
                    if(staffMemberWeight.getWeight()<=0.20){
                        algorithm.addNonSuggestedStaffMember(staffMember);
                    }
                    if(staffMemberWeight.getWeight()>0.20 && staffMemberWeight.getWeight()<=0.50){
                        algorithm.addOthers(staffMember);
                    }
                    else{
                        algorithm.addSuggestedStaffMember(staffMember);
                    }
                }
            }
        }
    }

    /**
     * Add the results of each of the nodes and return it in a single HashMap multiply by the weigh of each node
     * @param staffMembers List of staff members
     * @param node1WeightMap Node1 HasMap
     * @param node1Weight Node1 Weigh
     * @param node2WeightMap Node2 HasMap
     * @param node2Weight Node2 Weigh
     * @param node3WeightMap Node3 HasMap
     * @param node3Weight Node2 Weigh
     * @return HashMap with the sum
     */
    private HashMap<String, Double>  memberWeightMapResume(
            List<StaffMember> staffMembers,
            HashMap<String, Double> node1WeightMap, double node1Weight,
            HashMap<String, Double> node2WeightMap, double node2Weight,
            HashMap<String, Double> node3WeightMap, double node3Weight){
        HashMap<String, Double> memberWeightMap = new HashMap<>();
        for(StaffMember staffMember : staffMembers){
            memberWeightMap.put(staffMember.getId(),
                    node1WeightMap.get(staffMember.getId())*node1Weight
                            + node2WeightMap.get(staffMember.getId())*node2Weight
                            + node3WeightMap.get(staffMember.getId())*node3Weight);
        }
        return memberWeightMap;
    }

    /**
     * Take a hashMap of staff members weigh and return a ordened list by the weight of each staff member from highest
     * to lowest
     * @param staffMembers list of staff members in the database
     * @param memberWeightMap HashMap with the sum of each node
     * @return Ordened list from highest to lowest weigh
     */
    private List<StaffMemberWeight> hashMapToSortedList(List<StaffMember> staffMembers, HashMap<String, Double> memberWeightMap){
        List<StaffMemberWeight> staffMemberWeights = new ArrayList<>();
        for(StaffMember staffMember:staffMembers){
            staffMemberWeights.add(new StaffMemberWeight(staffMember.getId(), memberWeightMap.get(staffMember.getId())));
        }
        Collections.sort(staffMemberWeights, (o1, o2) -> Double.compare(o2.getWeight(), o1.getWeight()));
        return staffMemberWeights;
    }

}
