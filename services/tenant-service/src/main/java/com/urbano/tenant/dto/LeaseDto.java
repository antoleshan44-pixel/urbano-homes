package com.urbano.tenant.dto;

import com.urbano.common.enums.LeaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaseDto {
    private UUID id;
    private UUID tenantId;
    private UUID propertyId;
    private UUID unitId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double rentAmount;
    private String currency;
    private Double securityDeposit;
    private LeaseStatus status;
    private LocalDateTime signedAt;
    private LocalDateTime terminatedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
