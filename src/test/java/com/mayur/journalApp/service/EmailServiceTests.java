package com.mayur.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class EmailServiceTests {
    @Autowired
    EmailService emailService;
    @Test
    public void javaMailSenderTests(){
        emailService.JavaMailSender("mstambe81@gmail.com", "Email from Journal App", "Hi, please continue app development");
    }
}
