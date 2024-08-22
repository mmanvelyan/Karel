package org.karel.karel.user;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendActivationLink(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@example.com");
        message.setTo(email);
        message.setSubject("Activation link");
        message.setText("Your activation link : " +
                                    "localhost:8080/activate/" + token);
        emailSender.send(message);
    }
}
