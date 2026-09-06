package com.parita.notification.controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parita.notification.dto.GenerateInvoiceWebhookResponse;
import com.parita.notification.dto.NotificationRequest;
import com.parita.notification.dto.NotificationResponse;
import com.parita.notification.service.NotificationService;

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

    @GetMapping("/generateinvoice")
    public ResponseEntity<GenerateInvoiceWebhookResponse> generateInvoiceWebhook
    ( @RequestParam("invoiceId") String invoiceId) {

        GenerateInvoiceWebhookResponse response =
                notificationService.generateInvoiceWebhook(invoiceId);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/view-pdf")
public ResponseEntity<byte[]> viewPdfInBrowser() {
    HttpHeaders headers = new HttpHeaders();
    String htmlContent = "<html><body><h1>Hello World</h1><p>This PDF was generated from HTML for free!</p></body></html>";
    byte[] pdfBytes = generatePdfBytes( htmlContent);
            headers.setContentType(MediaType.APPLICATION_PDF);
            // "inline" opens it in the browser tab. Use "attachment" if you want to force an automatic download.
            headers.setContentDispositionFormData("inline", "invoice.pdf"); 
    return new ResponseEntity<>(pdfBytes, headers, org.springframework.http.HttpStatus.OK);
}
}