package com.parita.notification.dto;

import lombok.Data;

@Data
public class GenerateInvoiceWebhookResponse {

   private Long userId;

   private String templateCode;
}