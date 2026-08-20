package com.urbano.tenant.dto;

import com.urbano.common.enums.LeaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaseResponse {
    private UUID id;
    private UUID tenantId;
    private String tenantName;
    private UUID unitId;
    private String unitLabel;
    private UUID propertyId;
    private String propertyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal rentAmount;
    private LeaseStatus status;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;
}