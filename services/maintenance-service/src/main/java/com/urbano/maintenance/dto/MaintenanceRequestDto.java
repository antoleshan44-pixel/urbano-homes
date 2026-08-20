package com.urbano.maintenance.dto;

import com.urbano.common.enums.MaintenanceStatus;
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
public class MaintenanceRequestDto {
    private UUID id;
    private UUID propertyId;
    private UUID unitId;
    private UUID tenantId;
    private String title;
    private String description;
    private String priority;
    private MaintenanceStatus status;
    private UUID assignedTo;
    private LocalDateTime scheduledDate;
    private LocalDateTime completedDate;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
