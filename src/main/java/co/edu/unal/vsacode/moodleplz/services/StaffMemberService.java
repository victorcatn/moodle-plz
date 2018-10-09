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

    public List<StaffMember> getStaffMembers(){
        return repository.findAll();
    }

    public StaffMember getStaffMember(String id) {
        Optional<StaffMember> result = repository.findById(id);
        return result.orElse(null);
    }

    public StaffMember getStaffMemberByDocument(String document) {
        StaffMember result = repository.findByDocument(document);
        return result;
    }

    public StaffMember saveStaffMember(StaffMember staffMember){
        return repository.save(staffMember);
    }

    public StaffMember updateStaffMember(StaffMember staffMember, String id){
        if(!repository.findById(id).isPresent()){
            return null;
        }
        else{
            staffMember.setId(id);
        }
        return repository.save(staffMember);
    }

    public void deleteStaffMember(String id){
        repository.deleteById(id);
    }

}

