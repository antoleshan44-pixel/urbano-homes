package com.urbano.maintenance.controller;

import com.urbano.common.dto.PagedResponse;
import com.urbano.common.enums.MaintenanceStatus;
import com.urbano.maintenance.dto.MaintenanceRequestDto;
import com.urbano.maintenance.dto.MaintenanceRequestRequest;
import com.urbano.maintenance.service.MaintenanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<MaintenanceRequestDto> createRequest(@Valid @RequestBody MaintenanceRequestRequest request) {
        return ResponseEntity.ok(maintenanceService.createRequest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceRequestDto> getRequest(@PathVariable UUID id) {
        return ResponseEntity.ok(maintenanceService.getRequest(id));
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<PagedResponse<MaintenanceRequestDto>> getRequestsByProperty(
            @PathVariable UUID propertyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(maintenanceService.getRequestsByProperty(propertyId, page, size));
    }

    @GetMapping("/unit/{unitId}")
    public ResponseEntity<PagedResponse<MaintenanceRequestDto>> getRequestsByUnit(
            @PathVariable UUID unitId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(maintenanceService.getRequestsByUnit(unitId, page, size));
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<PagedResponse<MaintenanceRequestDto>> getRequestsByTenant(
            @PathVariable UUID tenantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(maintenanceService.getRequestsByTenant(tenantId, page, size));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<PagedResponse<MaintenanceRequestDto>> getRequestsByStatus(
            @PathVariable MaintenanceStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(maintenanceService.getRequestsByStatus(status, page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceRequestDto> updateRequest(
            @PathVariable UUID id,
            @Valid @RequestBody MaintenanceRequestRequest request) {
        return ResponseEntity.ok(maintenanceService.updateRequest(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<MaintenanceRequestDto> updateStatus(
            @PathVariable UUID id,
            @RequestParam MaintenanceStatus status) {
        return ResponseEntity.ok(maintenanceService.updateStatus(id, status));
    }

    @PostMapping("/{id}/assign")
    public ResponseEntity<MaintenanceRequestDto> assignRequest(
            @PathVariable UUID id,
            @RequestParam UUID assignedTo) {
        return ResponseEntity.ok(maintenanceService.assignRequest(id, assignedTo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable UUID id) {
        maintenanceService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}
