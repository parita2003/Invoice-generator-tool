package com.parita.notification.dto;

import lombok.Data;

@Data
public class NotificationRequest {

    private Long userId;

    private String templateCode;
}