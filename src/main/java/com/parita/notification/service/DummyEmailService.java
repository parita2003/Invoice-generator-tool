package com.parita.notification.service;

import org.springframework.stereotype.Service;

@Service
public class DummyEmailService {

    public void send(
            String email,
            String subject,
            String body){

        System.out.println("\n========== EMAIL ==========");
        System.out.println("TO : " + email);
        System.out.println("SUBJECT : " + subject);
        System.out.println(body);
        System.out.println("===========================\n");
    }
}