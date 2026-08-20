package com.urbano.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DarajaCallbackRequest {
    private String transactionId;
    private String referenceNumber;
    private Double amount;
    private String currency;
    private String status;
    private String mpesaReceiptNumber;
    private String phoneNumber;
    private String transactionDate;
    private String description;
}
