package com.parita.notification.service;

import org.springframework.stereotype.Service;

import com.parita.notification.dto.GenerateInvoiceWebhookResponse;
import com.parita.notification.dto.NotificationRequest;
import com.parita.notification.dto.NotificationResponse;
import com.parita.notification.entity.Template;
import com.parita.notification.entity.User;
import com.parita.notification.repository.TemplateRepository;
import com.parita.notification.repository.UserRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final UserRepository userRepository;
    private final TemplateRepository templateRepository;

    public NotificationServiceImpl(
            UserRepository userRepository,
            TemplateRepository templateRepository) {

        this.userRepository = userRepository;
        this.templateRepository = templateRepository;
    }

    @Override
    public NotificationResponse sendNotification(
            NotificationRequest request) {
        System.out.println("DATABASE INFO = " + userRepository.getDatabaseInfo());
        System.out.println("DEBUG userId = " + request.getUserId());
        System.out.println("========== DB DEBUG ==========");

        System.out.println(
                "DATABASE = " + userRepository.getDatabaseInfo()
        );

        System.out.println(
                "USER COUNT = " + userRepository.countUsers()
        );

        System.out.println(
                "USER IDS = " + userRepository.getUserIds()
        );

        System.out.println(
                "REQUEST USER ID = " + request.getUserId()
        );

        System.out.println("==============================");
//        System.out.println("DEBUG userId = " + request.getUserId());
        // 1. Get user from database
//        User user = userRepository.findById(request.getUserId())
//                .orElseThrow(() ->
//                        new RuntimeException("User not found"));
        User user = userRepository.findUserById(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // 2. Get template from database
        Template template =
                templateRepository
                        .findByTemplateCode(request.getTemplateCode())
                        .orElseThrow(() ->
                                new RuntimeException("Template not found"));

        // 3. Print user data
        System.out.println("\n========== USER ==========");
        System.out.println("User ID    : " + user.getId());
        System.out.println("First Name : " + user.getFirstName());
        System.out.println("Last Name  : " + user.getLastName());
        System.out.println("Email      : " + user.getEmail());
        System.out.println("Mobile     : " + user.getMobile());

        // 4. Print template data
        System.out.println("\n========== TEMPLATE ==========");
        System.out.println("Template Code : " + template.getTemplateCode());
        System.out.println("Subject       : " + template.getSubject());
        System.out.println("Body          : " + template.getBody());

        System.out.println("===============================\n");

        // Temporary response
        return new NotificationResponse(
                "TEST-001",
                "SUCCESS",
                "User and template fetched successfully"
        );
    }

    @Override
    public GenerateInvoiceWebhookResponse generateInvoiceWebhook(String invoiceId){
      GenerateInvoiceWebhookResponse response = new GenerateInvoiceWebhookResponse();  
      return response;
    }
}