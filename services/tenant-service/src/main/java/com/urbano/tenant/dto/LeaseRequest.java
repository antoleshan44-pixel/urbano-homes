package com.urbano.tenant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaseRequest {
    private UUID tenantId;
    private UUID propertyId;
    private UUID unitId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double rentAmount;
    private String currency;
    private Double securityDeposit;
}
