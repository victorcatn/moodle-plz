package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.Group;
import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import co.edu.unal.vsacode.moodleplz.repositories.GroupRepository;
import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StaffMemberService {

    @Autowired
    private StaffMemberRepository staffMemberRepository;

    private GroupRepository groupRepository;

    @Autowired EmailService emailService;

    public List<StaffMember> getStaffMembers(){
        return staffMemberRepository.findAll();
    }

    public StaffMember getStaffMember(String id) {
        Optional<StaffMember> result = staffMemberRepository.findById(id);
        return result.orElse(null);
    }

    public StaffMember getStaffMemberByDocument(String document) {
        return staffMemberRepository.findByDocument(document);
    }

    public StaffMember saveStaffMember(StaffMember newStaffMember){
        StaffMember staffMember = staffMemberRepository.save(newStaffMember);
        emailService.registerStaffMemberProfile(staffMember);
        return staffMember;
    }

    public StaffMember updateStaffMember(StaffMember staffMember, String id){
        if(!staffMemberRepository.findById(id).isPresent()){
            return null;
        }
        else{
            StaffMember oldStaffMember = getStaffMember(id);
            staffMember.setId(id);
            StaffMember updatedStaffMember = staffMemberRepository.save(staffMember);
            emailService.updateStaffMemberProfile(updatedStaffMember, oldStaffMember);
            return updatedStaffMember;
        }

    }

    public void deleteStaffMember(String id){
        if(staffMemberRepository.findById(id).isPresent()) {
            emailService.deleteStaffMemberProfile(getStaffMember(id));
            staffMemberRepository.deleteById(id);
            for(Group group:groupRepository.findAll()){
                if(group.getMembersId().contains(id)){
                    List<String> idmembers = group.getMembersId();
                    idmembers.remove(id);
                    group.setMembersId(idmembers);
                    groupRepository.save(group);
                }
            }

        }
    }

}

