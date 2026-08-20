package com.urbano.payment.dto;

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
public class PaymentRequest {
    private UUID tenantId;
    private UUID propertyId;
    private UUID unitId;
    private UUID leaseId;
    private Double amount;
    private String currency;
    private String paymentMethod;
    private String referenceNumber;
    private LocalDateTime dueDate;
    private String description;
}
