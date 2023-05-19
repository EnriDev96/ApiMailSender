package com.mail.sender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MailFileDTO {
    private String[] toUser;
    private String subject;
    private String message;
    private MultipartFile file;
}
