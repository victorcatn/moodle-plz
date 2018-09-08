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

    public LoginService(){

    }

    /**
     * Check if the attempt to enter an account is valid, looking in the repository of staff
     * members the document and password entered through the login, if a match is found with
     * both data then the login is taken as valid.
     * NOTE: The document is an alternative key
     * @param login Object with document and password of the staff member
     * @return If the staff member was found return the staff member, if the staff member wasn´t found return
     * null
     */
    public StaffMember validateLogin(Login login){
        StaffMember staffMember;

        Optional<StaffMember> staffMemberOptional = repository.findByPasswordAndDocument(login.getDocument(),
                login.getPassword());

        staffMember = staffMemberOptional.orElse(null);
        return staffMember;
    }
}
