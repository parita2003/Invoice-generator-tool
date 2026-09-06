package com.parita.notification.dbo;

import lombok.Data;

@Data
public class GenerateInvoicedbo {
   public Long userId;
   public String PdfUrl;
   public String invoiceId;
   public String customerId;
}