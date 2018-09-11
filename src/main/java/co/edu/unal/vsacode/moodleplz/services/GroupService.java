package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.*;
import co.edu.unal.vsacode.moodleplz.repositories.GroupRepository;
import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberDAL;
import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository repository;

    @Autowired
    private StaffMemberRepository staffMemberRepository;

    @Autowired
    private StaffMemberDAL staffMemberDAL;

    @Autowired
    private StaffMemberService staffMemberService;

    @Autowired
    private ProjectService projectService;

    public List<Group> getGroups(){
        return repository.findAll();
    }

    public Group getGroup(String id) {
        Optional<Group> result = repository.findById(id);
        return result.orElse(null);
    }

    public Group generateGroup(Project project){

        List<String> members = new ArrayList<>();

        for(SkillScore s : project.getNeededSkills()){
            StaffMember member = staffMemberDAL.findBySatisfiedSkill(s);

            if(member != null){
                members.add(member.getId());
            }

        }

        for(KnowledgeScore k : project.getNeededKnowledges()){
            StaffMember member = staffMemberDAL.findBySatisfiedKnowledge(k);
            if(member != null){
                members.add(member.getId());
            }
        }

        Group group = new Group( members, project.getId()); //TODO give a list of members to choose the wanted

        return group;
    }

    public Group createGroup(Group group){
        return repository.save(group);
    }

    public Group updateGroup(Group newGroup, String id){
        if(!repository.findById(id).isPresent()){
            return null;
        }
        return repository.save(newGroup);
    }

    public void deleteGroup(String id){
        repository.deleteById(id);
    }
}
