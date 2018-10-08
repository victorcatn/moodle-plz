package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.StaffMember;
import co.edu.unal.vsacode.moodleplz.repositories.StaffMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MongoStaffMemberDetailsService implements UserDetailsService {

    @Autowired
    private StaffMemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String document){
        if(repository.count() == 0) {
            repository.save(new StaffMember("0000000000","springappt.noreply@gmail.com","Admin","Admin","human resources",true, false));
        }
        StaffMember staffMember = repository.findByDocument(document);
        if(staffMember == null){
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        //return new User(staffMember.getDocument(), staffMember.getPassword(), authorities);

        return User.withDefaultPasswordEncoder().username(staffMember.getDocument()).password(staffMember.getPassword()).roles("USER").build();
//        return User.withUsername(staffMember.getDocument()).password("{noop}user").roles("USER").build();
    }

}
