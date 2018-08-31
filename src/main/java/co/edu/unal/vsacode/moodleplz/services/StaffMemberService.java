package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StaffMemberService {
    private List<StaffMember> staffMembers = new ArrayList<>();

    public StaffMemberService(){
        this.staffMembers.add(new StaffMember("1","1","1","1",
                "Juan","Gomez",null,null,null));

    }

    public List<StaffMember> getStaffMembers(){
        return staffMembers;
    }

    public StaffMember getStaffMember(String id){

        return staffMembers.stream()
                .filter( e -> e.getId().equals(id) )
                .findFirst()
                .orElse(null);
    }

    public StaffMember saveStaffMember(StaffMember staffMember){
        staffMember.setId(UUID.randomUUID().toString());
        staffMembers.add(staffMember);
        return staffMember;
    }

    public StaffMember updateStaffMember(StaffMember newStaffMember, String id){
        StaffMember oldStaffMember = this.getStaffMember(id);

        if(oldStaffMember != null && newStaffMember != null){
            BeanUtils.copyProperties(newStaffMember, oldStaffMember);
        }
        return newStaffMember;
    }
    public void deleteStaffMember(String id){
        staffMembers.remove(this.getStaffMember(id));
    }
}
