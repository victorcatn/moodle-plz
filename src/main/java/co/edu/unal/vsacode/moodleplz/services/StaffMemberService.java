package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberRepository;
import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StaffMemberService {

    @Autowired
    private StaffMemberRepository repository;

    public StaffMemberService(){
    }

    public List<StaffMember> getStaffMembers(){
        return repository.findAll();
    }

    public Optional<StaffMember> getStaffMember(String id) {
        return repository.findById(id);
    }

    public StaffMember saveStaffMember(StaffMember staffMember){
        return repository.save(staffMember);
    }

    public void deleteStaffMember(StaffMember staffMember){
        repository.delete(staffMember);
    }

}

