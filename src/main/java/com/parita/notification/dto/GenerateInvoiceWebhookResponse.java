package com.parita.notification.dto;

import lombok.Data;

@Data
public class GenerateInvoiceWebhookResponse {
   public Long userId;
   public String pdfUrl;
   public String invoiceId;
   public String customerId;
}