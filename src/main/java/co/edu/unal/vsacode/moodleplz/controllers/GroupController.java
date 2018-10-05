package co.edu.unal.vsacode.moodleplz.controllers;

import co.edu.unal.vsacode.moodleplz.models.Group;
import co.edu.unal.vsacode.moodleplz.models.Project;
import co.edu.unal.vsacode.moodleplz.services.EmailService;
import co.edu.unal.vsacode.moodleplz.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    List<Group> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping("/{id}")
    Group getGroup(@PathVariable String id) {
        return groupService.getGroup(id);
    }

    @PostMapping("/generate")
    Group generateGroup(@RequestBody Project project) {
        return groupService.generateGroup(project);
    }

    @PostMapping
    Group createGroup(@RequestBody Group group) {
        Group newGroup = groupService.createGroup(group);
        emailService.createGroupNotification(newGroup);
        return newGroup;
    }

    @PutMapping("/{id}")
    Group editGroup(@RequestBody Group newGroup, @PathVariable String id) {
        Group oldGroup = groupService.getGroup(id);
        emailService.updateGroupNotification(newGroup, oldGroup);
        return groupService.updateGroup(newGroup, id);
    }

    @DeleteMapping("/{id}")
    void deleteGroup(@PathVariable String id) {
        emailService.deleteGroupNotification(groupService.getGroup(id));
        groupService.deleteGroup(id);
    }
}
