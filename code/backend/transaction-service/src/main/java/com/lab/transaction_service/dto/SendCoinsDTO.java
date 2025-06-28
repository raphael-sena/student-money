package com.lab.transaction_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class SendCoinsDTO {
    @NotBlank(message = "Sender ID is required")
    private String senderId;

    @NotBlank(message = "Recipient ID is required")
    private String recipientId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Description is required")
    private String description;
} 