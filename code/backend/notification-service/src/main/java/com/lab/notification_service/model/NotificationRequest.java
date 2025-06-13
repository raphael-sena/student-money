package com.lab.notification_service.model;

import lombok.Data;

@Data
public class NotificationRequest {
    private String recipientId;
    private String recipientEmail;
    private String recipientPhone;
    private NotificationType type;
    private String subject;
    private String content;
    private NotificationChannel channel;
}
