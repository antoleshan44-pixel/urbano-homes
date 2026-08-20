package com.urbano.maintenance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequestRequest {
    private UUID propertyId;
    private UUID unitId;
    private UUID tenantId;
    private String title;
    private String description;
    private String priority; // LOW, MEDIUM, HIGH, URGENT
}
