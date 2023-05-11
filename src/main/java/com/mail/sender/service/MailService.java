package com.mail.sender.service;

import java.io.File;

public interface MailService {

    void sendEmail(String[] toUser, String subject, String text);

    void sendEmailWithFile(String[] toUser, String subject, String text, File file);

}
