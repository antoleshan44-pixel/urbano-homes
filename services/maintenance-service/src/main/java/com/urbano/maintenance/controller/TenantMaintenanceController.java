package com.urbano.maintenance.controller;

import com.urbano.common.dto.PagedResponse;
import com.urbano.maintenance.dto.MaintenanceRequestDto;
import com.urbano.maintenance.dto.MaintenanceRequestRequest;
import com.urbano.maintenance.service.MaintenanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/tenant/maintenance")
@RequiredArgsConstructor
public class TenantMaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<MaintenanceRequestDto> createRequest(@Valid @RequestBody MaintenanceRequestRequest request) {
        return ResponseEntity.ok(maintenanceService.createRequest(request));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<MaintenanceRequestDto>> getTenantRequests(
            @RequestParam UUID tenantId, Pageable pageable) {
        return ResponseEntity.ok(maintenanceService.getTenantRequests(tenantId, pageable));
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<MaintenanceRequestDto> getTenantRequest(
            @RequestParam UUID tenantId, @PathVariable UUID requestId) {
        return ResponseEntity.ok(maintenanceService.getTenantRequest(tenantId, requestId));
    }
}
