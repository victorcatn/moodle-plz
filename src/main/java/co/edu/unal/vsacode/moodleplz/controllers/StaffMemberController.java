package co.edu.unal.vsacode.moodleplz.controllers;
import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import co.edu.unal.vsacode.moodleplz.services.StaffMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffmembers")
public class StaffMemberController {

    @Autowired
    private StaffMemberService staffMemberService;

    @GetMapping
    List<StaffMember> getStaffMembers() {
        return staffMemberService.getStaffMembers();
    }

    @GetMapping("/{id}")
    StaffMember getStaffMember(@PathVariable String id) {
        return staffMemberService.getStaffMember(id);
    }

    @PostMapping
    StaffMember saveStaffMember(@RequestBody StaffMember staffMember) {
        return staffMemberService.saveStaffMember(staffMember);
    }

    @PutMapping("/{id}")
    StaffMember editStaffMember(@RequestBody StaffMember newStaffMember, @PathVariable String id) {
        return staffMemberService.updateStaffMember(newStaffMember, id);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable String id) {
        staffMemberService.deleteStaffMember(id);
    }
    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable String id) {
        StaffMember staffMember = new StaffMember();
        staffMember.setId(id);
        staffMemberService.deleteStaffMember(staffMember);
    }
}
