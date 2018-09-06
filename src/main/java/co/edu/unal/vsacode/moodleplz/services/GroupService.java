package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.Group;
import co.edu.unal.vsacode.moodleplz.models.Project;
import co.edu.unal.vsacode.moodleplz.models.Skill;
import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import co.edu.unal.vsacode.moodleplz.repositories.GroupRepository;
import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository repository;

    @Autowired
    private StaffMemberRepository staffMemberRepository;

    public Group generateGroup(Project project){

        List<StaffMember> members = new ArrayList<>();

        for(Skill s : project.getNeededSkill()){
            members.add(staffMemberRepository.findBySkillsContaining(s));
        }

        Group group = new Group( members,project);
        return createGroup(group);
    }

    public Group createGroup(Group group){
        return repository.save(group);
    }
}
