package com.parita.notification.controller;

import com.parita.notification.dto.NotificationRequest;
import com.parita.notification.dto.NotificationResponse;
import com.parita.notification.service.NotificationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<NotificationResponse> sendNotification(
            @RequestBody NotificationRequest request) {

        NotificationResponse response =
                notificationService.sendNotification(request);

        return ResponseEntity.ok(response);
    }

    @GetMappin("/generateinvoice")
    public ResponseEntity<GenerateInvoiceWebookResponse>
}