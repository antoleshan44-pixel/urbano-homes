package com.urbano.payment.dto;

import com.urbano.common.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private UUID id;
    private UUID tenantId;
    private UUID propertyId;
    private UUID unitId;
    private UUID leaseId;
    private Double amount;
    private String currency;
    private String paymentMethod;
    private String referenceNumber;
    private PaymentStatus status;
    private LocalDateTime paymentDate;
    private LocalDateTime dueDate;
    private String description;
    private boolean isReconciled;
    private LocalDateTime reconciledAt;
    private String mpesaReceiptNumber;
    private LocalDateTime transactionDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
