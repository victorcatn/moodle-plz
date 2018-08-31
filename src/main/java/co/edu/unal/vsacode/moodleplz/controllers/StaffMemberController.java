package co.edu.unal.vsacode.moodleplz.controllers;

import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import co.edu.unal.vsacode.moodleplz.services.StaffMemberServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Staff-member")
public class StaffMemberController {

    @Autowired
    private StaffMemberServices staffServices;

    @GetMapping(value="/{IdCard}/{Email}/{Name}/{LastName}")
    public String SaveStaffMember(@PathVariable("IdCard") String idCard, @PathVariable("Email") String email,
                               @PathVariable("Name") String name, @PathVariable("LastName") String lastName){

        StaffMember newStaffMember = new StaffMember(idCard, email, name, lastName);
        staffServices.saveStaffMemberOnMongoDB(newStaffMember);
        return(newStaffMember.toString());
    }


}
