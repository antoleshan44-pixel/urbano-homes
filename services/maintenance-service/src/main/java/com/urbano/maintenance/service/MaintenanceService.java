package com.urbano.maintenance.service;

import com.urbano.common.dto.PagedResponse;
import com.urbano.common.enums.MaintenanceStatus;
import com.urbano.common.exception.ResourceNotFoundException;
import com.urbano.maintenance.client.PropertyServiceClient;
import com.urbano.maintenance.dto.MaintenanceRequestDto;
import com.urbano.maintenance.dto.MaintenanceRequestRequest;
import com.urbano.maintenance.entity.MaintenanceRequest;
import com.urbano.maintenance.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final PropertyServiceClient propertyServiceClient;

    @Transactional
    public MaintenanceRequestDto createRequest(MaintenanceRequestRequest request) {
        // Verify unit exists
        try {
            propertyServiceClient.getUnit(request.getUnitId());
        } catch (Exception e) {
            log.warn("Unit not found: {}", request.getUnitId());
        }

        MaintenanceRequest maintenance = MaintenanceRequest.builder()
                .propertyId(request.getPropertyId())
                .unitId(request.getUnitId())
                .tenantId(request.getTenantId())
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(MaintenanceStatus.OPEN)
                .createdAt(LocalDateTime.now())
                .build();

        maintenance = maintenanceRepository.save(maintenance);
        log.info("Maintenance request created: {}", maintenance.getId());
        return mapToDto(maintenance);
    }

    public MaintenanceRequestDto getRequest(UUID id) {
        MaintenanceRequest maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance request not found"));
        return mapToDto(maintenance);
    }

    public PagedResponse<MaintenanceRequestDto> getRequestsByProperty(UUID propertyId, int page, int size) {
        Page<MaintenanceRequest> maintenancePage = maintenanceRepository.findByPropertyId(propertyId, PageRequest.of(page, size));
        return mapToPagedResponse(maintenancePage, page, size);
    }

    public PagedResponse<MaintenanceRequestDto> getRequestsByUnit(UUID unitId, int page, int size) {
        Page<MaintenanceRequest> maintenancePage = maintenanceRepository.findByUnitId(unitId, PageRequest.of(page, size));
        return mapToPagedResponse(maintenancePage, page, size);
    }

    public PagedResponse<MaintenanceRequestDto> getRequestsByTenant(UUID tenantId, int page, int size) {
        Page<MaintenanceRequest> maintenancePage = maintenanceRepository.findByTenantId(tenantId, PageRequest.of(page, size));
        return mapToPagedResponse(maintenancePage, page, size);
    }

    // Method for TenantMaintenanceController
    public PagedResponse<MaintenanceRequestDto> getTenantRequests(UUID tenantId, Pageable pageable) {
        Page<MaintenanceRequest> maintenancePage = maintenanceRepository.findByTenantId(tenantId, pageable);
        List<MaintenanceRequestDto> content = maintenancePage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<MaintenanceRequestDto>builder()
                .content(content)
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .totalElements(maintenancePage.getTotalElements())
                .totalPages(maintenancePage.getTotalPages())
                .first(maintenancePage.isFirst())
                .last(maintenancePage.isLast())
                .build();
    }

    // Method for TenantMaintenanceController
    public MaintenanceRequestDto getTenantRequest(UUID tenantId, UUID requestId) {
        MaintenanceRequest maintenance = maintenanceRepository.findByIdAndTenantId(requestId, tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance request not found"));
        return mapToDto(maintenance);
    }

    public PagedResponse<MaintenanceRequestDto> getRequestsByStatus(MaintenanceStatus status, int page, int size) {
        Page<MaintenanceRequest> maintenancePage = maintenanceRepository.findByStatus(status, PageRequest.of(page, size));
        return mapToPagedResponse(maintenancePage, page, size);
    }

    @Transactional
    public MaintenanceRequestDto updateRequest(UUID id, MaintenanceRequestRequest request) {
        MaintenanceRequest maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance request not found"));

        maintenance.setTitle(request.getTitle());
        maintenance.setDescription(request.getDescription());
        maintenance.setPriority(request.getPriority());

        maintenance = maintenanceRepository.save(maintenance);
        log.info("Maintenance request updated: {}", maintenance.getId());
        return mapToDto(maintenance);
    }

    @Transactional
    public MaintenanceRequestDto updateStatus(UUID id, MaintenanceStatus status) {
        MaintenanceRequest maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance request not found"));
        maintenance.setStatus(status);

        if (status == MaintenanceStatus.COMPLETED || status == MaintenanceStatus.RESOLVED) {
            maintenance.setCompletedDate(LocalDateTime.now());
        }

        maintenance = maintenanceRepository.save(maintenance);
        log.info("Maintenance request status updated: {} -> {}", id, status);
        return mapToDto(maintenance);
    }

    @Transactional
    public MaintenanceRequestDto assignRequest(UUID id, UUID assignedTo) {
        MaintenanceRequest maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance request not found"));
        maintenance.setAssignedTo(assignedTo);
        maintenance.setStatus(MaintenanceStatus.IN_PROGRESS);
        maintenance = maintenanceRepository.save(maintenance);
        log.info("Maintenance request assigned: {} -> {}", id, assignedTo);
        return mapToDto(maintenance);
    }

    @Transactional
    public void deleteRequest(UUID id) {
        MaintenanceRequest maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance request not found"));
        maintenance.setStatus(MaintenanceStatus.CANCELLED);
        maintenanceRepository.save(maintenance);
        log.info("Maintenance request cancelled: {}", id);
    }

    private PagedResponse<MaintenanceRequestDto> mapToPagedResponse(Page<MaintenanceRequest> page, int pageNum, int size) {
        List<MaintenanceRequestDto> content = page.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<MaintenanceRequestDto>builder()
                .content(content)
                .page(pageNum)
                .size(size)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }

    private MaintenanceRequestDto mapToDto(MaintenanceRequest maintenance) {
        return MaintenanceRequestDto.builder()
                .id(maintenance.getId())
                .propertyId(maintenance.getPropertyId())
                .unitId(maintenance.getUnitId())
                .tenantId(maintenance.getTenantId())
                .title(maintenance.getTitle())
                .description(maintenance.getDescription())
                .priority(maintenance.getPriority())
                .status(maintenance.getStatus())
                .assignedTo(maintenance.getAssignedTo())
                .scheduledDate(maintenance.getScheduledDate())
                .completedDate(maintenance.getCompletedDate())
                .notes(maintenance.getNotes())
                .createdAt(maintenance.getCreatedAt())
                .updatedAt(maintenance.getUpdatedAt())
                .build();
    }
}
