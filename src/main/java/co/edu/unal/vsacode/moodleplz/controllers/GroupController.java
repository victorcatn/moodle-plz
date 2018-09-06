package co.edu.unal.vsacode.moodleplz.controllers;

import co.edu.unal.vsacode.moodleplz.models.Group;
import co.edu.unal.vsacode.moodleplz.models.Project;
import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import co.edu.unal.vsacode.moodleplz.services.GroupService;
import co.edu.unal.vsacode.moodleplz.services.StaffMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/generate")
    Group generateGroup(@RequestBody Project project) {
        return groupService.generateGroup(project);
    }

    @PostMapping
    Group createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }
}
