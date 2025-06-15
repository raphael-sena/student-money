package com.lab.notification_service.model;

import lombok.Data;

@Data
public class TransactionNotificationMessage {
    private String transactionId;
    private String userId;
    private String userEmail;
    private String userPhone;
    private String transactionType;
    private Double amount;
    private String description;
} 