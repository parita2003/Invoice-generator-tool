package com.parita.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String templateCode;

    private String recipientEmail;

    @Column(columnDefinition = "TEXT")
    private String finalMessage;

    private String status;

    private LocalDateTime createdAt;
}