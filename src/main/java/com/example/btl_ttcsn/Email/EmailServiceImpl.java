package com.example.btl_ttcsn.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Component
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String gmail;

    @Override
    public String sendPassword(String to, String text) {
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setText(text, true);
            mimeMessageHelper.setSubject("New password!");
            mimeMessageHelper.setFrom(gmail);
            mimeMessageHelper.setTo(to);
            javaMailSender.send(mailMessage);


            return "Sent mail SuccessFully !";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Sent mail Failed !";
        }
    }
}
