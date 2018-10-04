package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StaffMemberService {

    @Autowired
    private StaffMemberRepository repository;

    @Autowired EmailService emailService;

    public List<StaffMember> getStaffMembers(){
        return repository.findAll();
    }

    public StaffMember getStaffMember(String id) {
        Optional<StaffMember> result = repository.findById(id);
        return result.orElse(null);
    }

    public StaffMember saveStaffMember(StaffMember newStaffMember){
        StaffMember staffMember = repository.save(newStaffMember);
        emailService.registerStaffMemberProfile(staffMember);
        return staffMember;
    }

    public StaffMember updateStaffMember(StaffMember staffMember, String id){
        if(!repository.findById(id).isPresent()){
            return null;
        }
        else{
            StaffMember oldStaffMember = getStaffMember(id);
            staffMember.setId(id);
            StaffMember updatedStaffMember = repository.save(staffMember);
            emailService.updateStaffMemberProfile(updatedStaffMember, oldStaffMember);
            return updatedStaffMember;
        }

    }

    public void deleteStaffMember(String id){
        if(repository.findById(id).isPresent()) {
            emailService.deleteStaffMemberProfile(getStaffMember(id));
            repository.deleteById(id);
        }
    }

}

