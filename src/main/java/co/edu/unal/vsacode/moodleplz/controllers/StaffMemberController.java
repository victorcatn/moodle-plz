package co.edu.unal.vsacode.moodleplz.controllers;
import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import co.edu.unal.vsacode.moodleplz.services.EmailService;
import co.edu.unal.vsacode.moodleplz.services.StaffMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staffmembers")
public class StaffMemberController {

    @Autowired
    private StaffMemberService staffMemberService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    List<StaffMember> getStaffMembers() {
        return staffMemberService.getStaffMembers();
    }

    @GetMapping("/{id}")
    StaffMember getStaffMember(@PathVariable String id) {
        return staffMemberService.getStaffMember(id);
    }

    @GetMapping("/bydocument/{document}")
    StaffMember getStaffMemberByDocument(@PathVariable String document) {
        return staffMemberService.getStaffMemberByDocument(document);
    }

    @PostMapping
    StaffMember saveStaffMember(@RequestBody StaffMember staffMember) {
        StaffMember staffMemberSaved = staffMemberService.saveStaffMember(staffMember);
        emailService.registerStaffMemberProfile(staffMemberSaved);
        return staffMemberSaved;
    }

    @PutMapping("/{id}")
    StaffMember editStaffMember(@RequestBody StaffMember newStaffMember, @PathVariable String id) {
        StaffMember staffMemberUpdated = staffMemberService.updateStaffMember(newStaffMember, id);
        emailService.updateStaffMemberProfile(staffMemberUpdated);
        return staffMemberUpdated;
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable String id) {
        emailService.deleteStaffMemberProfile(staffMemberService.getStaffMember(id));
        staffMemberService.deleteStaffMember(id);
    }
}
