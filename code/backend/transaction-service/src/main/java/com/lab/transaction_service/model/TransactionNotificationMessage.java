package com.lab.transaction_service.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionNotificationMessage {
    private String userId;
    private String userEmail;
    private String userPhone;
    private BigDecimal amount;
    private String description;
    private String transactionType;
} 