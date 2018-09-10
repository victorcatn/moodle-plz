package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.*;
import co.edu.unal.vsacode.moodleplz.repositories.GroupRepository;
import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

        for(Skill s : project.getNeededSkill()){
            StaffMember member = staffMemberRepository.findBySkillsContaining(s);
            if(member != null){
                members.add(member.getId());
            }

        }

        for(Knowledge k : project.getNeededKnowledge()){
            StaffMember member = staffMemberRepository.findByKnowledgesContaining(k);
            if(member != null){
                members.add(member.getId());
            }
        }

        Group group = new Group( members, project.getId());

        return createGroup(group);
    }

    public Group createGroup(Group group){
        for(String memberId : group.getMembersId()){
            StaffMember member = staffMemberService.getStaffMember(memberId);
            member.setGroupId(group.getId());
            staffMemberService.updateStaffMember(member,memberId);
        }
        Project project = projectService.getProject(group.getProjectId()).orElse(null);
        project.setAssignedGroupId(group.getId());
        projectService.saveProject(project);
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
