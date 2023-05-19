package com.mail.sender.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${email.user}")
    private String emailUser;

    @Value("${email.password}")
    private String emailPassword;

    @Bean
    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com"); // host of gmail
        mailSender.setPort(587); // 587 is the default port of gmail
        mailSender.setUsername(emailUser); // email of the sender
        mailSender.setPassword(emailPassword); // password of the sender

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp"); // protocol used to send the email
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
        props.put("mail.debug", "true"); // enable the debug mode --> this is optional, but it helps to see the logs

        return mailSender;
    }
}
