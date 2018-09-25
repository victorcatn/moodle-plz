package co.edu.unal.vsacode.moodleplz.services;

import co.edu.unal.vsacode.moodleplz.models.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public EmailService(){

    }

    public void sendEmail(String to , String subject, String text){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("moodleplz-noreply");
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }

    public void createGroupNotification(Group newGroup){
        String message = "Ha sido añadido al grupo: <br>"+newGroup.getId();
        String subject = "Se ha creado un nuevo grupo";

    }





}
