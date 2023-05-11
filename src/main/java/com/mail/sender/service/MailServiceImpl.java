package com.mail.sender.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class MailServiceImpl implements MailService{

    @Value("${email.sender}")
    private String emailUser;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String[] toUser, String subject, String text) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("bryan.orellana.est@tecazuay.edu.ec");
        mailMessage.setTo(toUser);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        javaMailSender.send(mailMessage);

    }

    @Override
    public void sendEmailWithFile(String[] toUser, String subject, String text, File file) {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(emailUser);
            mimeMessageHelper.setTo(toUser);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text);
            mimeMessageHelper.addAttachment(file.getName(), file);

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
