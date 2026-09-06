package com.parita.notification.service;

import com.parita.notification.dto.GenerateInvoiceWebhookResponse;
import com.parita.notification.dto.NotificationRequest;
import com.parita.notification.dto.NotificationResponse;

public interface NotificationService {
    NotificationResponse sendNotification(NotificationRequest request);
    GenerateInvoiceWebhookResponse generateInvoiceWebhook(String invoiceId);
    byte[] generatePdfBytes(String htmlContent);
}