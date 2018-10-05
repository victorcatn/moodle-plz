package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.Algorithm;
import co.edu.unal.vsacode.moodleplz.models.Group;
import co.edu.unal.vsacode.moodleplz.models.Project;
import co.edu.unal.vsacode.moodleplz.repositories.GroupRepository;
import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberDAL;
import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private EmailService emailService;

    @Autowired
    private AlgorithmService algorithmService;

    public List<Group> getGroups(){
        return repository.findAll();
    }

    public Group getGroup(String id) {
        Optional<Group> result = repository.findById(id);
        return result.orElse(null);
    }


    public Algorithm generateGroup(Project project){

        Algorithm algorithm = algorithmService.generateAlgorithm(staffMemberRepository.findAll(), project);

        return algorithm;
    }

    public Group createGroup(Group group){
        Group savedGroup = repository.save(group);
        Project selectedProject = projectService.getProjectById(savedGroup.getProjectId());
        if(selectedProject.getAssignedGroupId() != null){
            deleteGroup(selectedProject.getAssignedGroupId());
        }
        selectedProject.setAssignedGroupId(savedGroup.getId());
        projectService.saveProject(selectedProject);
        emailService.createGroupNotification(savedGroup);
        return savedGroup;

    }

    public Group updateGroup(Group newGroup, String id){
        if(!repository.findById(id).isPresent()){
            return null;
        }
        else{
            Group oldGroup = getGroup(id);
            newGroup.setId(id);
            Group updatedGroup = repository.save(newGroup);
            emailService.updateGroupNotification(updatedGroup, oldGroup);
            return updatedGroup;
        }

    }

    public void deleteGroup(String id){
        if(repository.findById(id).isPresent()) {
            emailService.deleteGroupNotification(getGroup(id));
            Project project = projectService.getProjectById(getGroup(id).getProjectId());
            project.setAssignedGroupId(null);
            projectService.saveProject(project);
            repository.deleteById(id);
        }
    }
}
