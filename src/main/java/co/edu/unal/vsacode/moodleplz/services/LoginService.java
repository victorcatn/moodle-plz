package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.Login;
import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private StaffMemberRepository repository;

    public StaffMember validateLogin(Login login){
        StaffMember staffMember;

        Optional<StaffMember> staffMemberOptional = repository.findByPasswordAndDocument(login.getDocument(),
                login.getPassword());

        if (staffMemberOptional.isPresent()) {
            staffMember = staffMemberOptional.get();
        }
        else{
            staffMember = null;
        }
        return staffMember;
    }
}
