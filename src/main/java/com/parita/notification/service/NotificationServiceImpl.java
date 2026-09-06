package com.parita.notification.service;

import org.springframework.stereotype.Service;

import com.parita.notification.dto.GenerateInvoiceWebhookResponse;
import com.parita.notification.dto.NotificationRequest;
import com.parita.notification.dto.NotificationResponse;
import com.parita.notification.entity.Template;
import com.parita.notification.entity.User;
import com.parita.notification.repository.TemplateRepository;
import com.parita.notification.repository.UserRepository;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.helper.W3CDom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
// import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
// import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
// import software.amazon.awssdk.core.sync.RequestBody;
// import software.amazon.awssdk.regions.Region;
// import software.amazon.awssdk.services.s3.S3Client;
// import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
// import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.io.ByteArrayOutputStream;
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
       String html = "<html><body><h1>Hello World</h1><p>This PDF was generated from HTML for free!</p></body></html>";
        try {
        //     convertHtmlToPdf(html, "output.pdf");
            String pdfUrl = convertHtmlAndUploadToS3(html, "output.pdf");
            response.setPdfUrl(pdfUrl);
            System.out.println("PDF generated successfully.");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
      return response;
    }

    public static void convertHtmlToPdf(String htmlContent, String destPdfPath) throws Exception {
        // Step 1: Parse HTML and enforce XML syntax using Jsoup
        Document doc = Jsoup.parse(htmlContent);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        doc.outputSettings().prettyPrint(false);

        // Step 2: Set up output stream and renderer builder
        try (OutputStream os = new FileOutputStream(new File(destPdfPath))) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withW3cDocument(new W3CDom().fromJsoup(doc), "/");
            builder.toStream(os);
            
            // Step 3: Run the conversion
            builder.run();
        }
    }

//     public class PdfS3Service {

    // Replace these values with your actual configuration
//     private static final String BUCKET_NAME = "my-pdf-bucket";
//     private static final String ACCESS_KEY = "YOUR_AWS_ACCESS_KEY";
//     private static final String SECRET_KEY = "YOUR_AWS_SECRET_KEY";
//     private static final Region REGION = Region.US_EAST_1; // Change to your bucket region

    /**
     * Converts HTML to an in-memory PDF and uploads it directly to Amazon S3.
     * Makes the file publicly readable and returns the absolute public URL.
     */
    public static String convertHtmlAndUploadToS3(String htmlContent, String s3FileName) throws Exception {
        // Step 1: Generate PDF completely in-memory (using Jsoup + Open HTML to PDF)
        Document doc = Jsoup.parse(htmlContent);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        doc.outputSettings().prettyPrint(false);

        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withW3cDocument(new W3CDom().fromJsoup(doc), "/");
        builder.toStream(pdfOutputStream);
        builder.run();

        byte[] pdfBytes = pdfOutputStream.toByteArray();

        // Step 2: Initialize AWS S3 Client
        // S3Client s3Client = S3Client.builder()
        //         .region(REGION)
        //         .credentialsProvider(StaticCredentialsProvider.create(
        //                 AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)
        //         ))
        //         .build();

        // Step 3: Prepare the upload request with Public Read permission
        // PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        //         .bucket(BUCKET_NAME)
        //         .key(s3FileName)
        //         .contentType("application/pdf")
        //         .acl(ObjectCannedACL.PUBLIC_READ) // Makes the URL publicly accessible
        //         .build();

        // Execute upload from memory byte array
        // s3Client.putObject(putObjectRequest, RequestBody.fromBytes(pdfBytes));

        // Step 4: Construct and return the permanent public link
        // return String.format("https://%s.s3.%://amazonaws.com", BUCKET_NAME, "", s3FileName);
        return  "temporary-public-link"; // Replace with actual public link after upload        
        }
}
