package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.controllers.MongoDBController;
import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffMemberServices {

    @Autowired
    private MongoDBController repository;

    public StaffMember saveStaffMemberOnMongoDB(StaffMember staffMember){
        repository.save(staffMember);
        return staffMember;
    }

}
