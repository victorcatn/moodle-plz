package co.edu.unal.vsacode.moodleplz.controllers;

import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import co.edu.unal.vsacode.moodleplz.services.StaffMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Staff-member")
public class StaffMemberController {

    @Autowired
    private StaffMemberService staffServices;

    @GetMapping(value="/register-staff-member/{IdCard}-{Email}-{password}-{Name}-{LastName}")
    public String SaveStaffMember(@PathVariable("IdCard") String idCard, @PathVariable("Email") String email,
                               @PathVariable("Name") String name, @PathVariable("LastName") String lastName,
                                  @PathVariable("password") String password){

        StaffMember newStaffMember = new StaffMember(idCard, email, password,name, lastName);
        staffServices.saveStaffMemberOnMongoDB(newStaffMember);
        return(newStaffMember.toString());
    }


}
