package com.mail.sender.controller;

import com.mail.sender.model.MailDTO;
import com.mail.sender.model.MailFileDTO;
import com.mail.sender.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody MailDTO mailDTO) {
        System.out.println("Mensaje recibido: " + mailDTO);
        mailService.sendEmail(mailDTO.getToUser(), mailDTO.getSubject(), mailDTO.getMessage());

        Map<String,String> response = new HashMap<>();
        response.put("estado", "Email sent successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sendFile")
    public ResponseEntity<?> receiveRequestEmailWithFile(@ModelAttribute MailFileDTO mailFileDTO) {
        try {
            String filename = mailFileDTO.getFile().getOriginalFilename();
            Path path = Paths.get("src/main/resources/files/" + filename);

            Files.createDirectories(path.getParent());
            Files.copy(mailFileDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            File file = path.toFile();
            mailService.sendEmailWithFile(mailFileDTO.getToUser(), mailFileDTO.getSubject(), mailFileDTO.getMessage(), file);

            Map<String,String> response = new HashMap<>();
            response.put("estado", "Email sent successfully");
            response.put("archivo", "fileName");

            return ResponseEntity.ok(response);
        }catch (Exception e) {
            throw new RuntimeException("Error al enviar el Email con un archivo." + e.getMessage());
        }
    }


}
