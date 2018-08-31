package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberRepository;
import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffMemberService {

    @Autowired
    private StaffMemberRepository repository;

    public StaffMember saveStaffMemberOnMongoDB(StaffMember staffMember){
        repository.save(staffMember);
        return staffMember;
    }

}
