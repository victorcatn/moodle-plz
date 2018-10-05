package co.edu.unal.vsacode.moodleplz.controllers;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

@RestController
public class SessionController {
    /*@RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }*/

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request){
        String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
    }

    @RequestMapping("/token")
    public Map<String,String> token(HttpSession session) {
        return Collections.singletonMap("token", session.getId());
    }

    @RequestMapping("/login")
    public boolean login(@RequestBody User user){
        return user.getUsername().equals("document") && user.getPassword().equals("password");
    }
}
