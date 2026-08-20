package com.urbano.tenant.service;

import com.urbano.common.dto.PagedResponse;
import com.urbano.common.enums.LeaseStatus;
import com.urbano.common.exception.ResourceNotFoundException;
import com.urbano.tenant.dto.LeaseDto;
import com.urbano.tenant.dto.LeaseRequest;
import com.urbano.tenant.entity.Lease;
import com.urbano.tenant.entity.Tenant;
import com.urbano.tenant.repository.LeaseRepository;
import com.urbano.tenant.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final TenantRepository tenantRepository;

    @Transactional
    public LeaseDto createLease(LeaseRequest request) {
        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));

        Lease lease = new Lease();
        lease.setTenantId(tenant.getId());
        lease.setPropertyId(request.getPropertyId());
        lease.setUnitId(request.getUnitId());
        lease.setStartDate(request.getStartDate());
        lease.setEndDate(request.getEndDate());
        lease.setRentAmount(request.getRentAmount());
        lease.setCurrency(request.getCurrency());
        lease.setSecurityDeposit(request.getSecurityDeposit());
        lease.setStatus(LeaseStatus.PENDING);

        lease = leaseRepository.save(lease);
        log.info("Lease created: {}", lease.getId());
        return mapToDto(lease);
    }

    public LeaseDto getLease(UUID id) {
        Lease lease = leaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lease not found"));
        return mapToDto(lease);
    }

    public PagedResponse<LeaseDto> getLeasesByTenant(UUID tenantId, int page, int size) {
        Page<Lease> leasePage = leaseRepository.findByTenantId(tenantId, PageRequest.of(page, size));
        List<LeaseDto> content = leasePage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<LeaseDto>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(leasePage.getTotalElements())
                .totalPages(leasePage.getTotalPages())
                .first(leasePage.isFirst())
                .last(leasePage.isLast())
                .build();
    }

    public PagedResponse<LeaseDto> getLeasesByProperty(UUID propertyId, int page, int size) {
        Page<Lease> leasePage = leaseRepository.findByPropertyId(propertyId, PageRequest.of(page, size));
        List<LeaseDto> content = leasePage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<LeaseDto>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(leasePage.getTotalElements())
                .totalPages(leasePage.getTotalPages())
                .first(leasePage.isFirst())
                .last(leasePage.isLast())
                .build();
    }

    public PagedResponse<LeaseDto> getActiveLeases(int page, int size) {
        Page<Lease> leasePage = leaseRepository.findByStatus(LeaseStatus.ACTIVE, PageRequest.of(page, size));
        List<LeaseDto> content = leasePage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<LeaseDto>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(leasePage.getTotalElements())
                .totalPages(leasePage.getTotalPages())
                .first(leasePage.isFirst())
                .last(leasePage.isLast())
                .build();
    }

    @Transactional
    public LeaseDto updateLease(UUID id, LeaseRequest request) {
        Lease lease = leaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lease not found"));

        lease.setStartDate(request.getStartDate());
        lease.setEndDate(request.getEndDate());
        lease.setRentAmount(request.getRentAmount());
        lease.setCurrency(request.getCurrency());
        lease.setSecurityDeposit(request.getSecurityDeposit());

        lease = leaseRepository.save(lease);
        log.info("Lease updated: {}", lease.getId());
        return mapToDto(lease);
    }

    @Transactional
    public void deleteLease(UUID id) {
        Lease lease = leaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lease not found"));
        lease.setStatus(LeaseStatus.CANCELLED);
        leaseRepository.save(lease);
        log.info("Lease cancelled: {}", id);
    }

    @Transactional
    public LeaseDto updateLeaseStatus(UUID id, LeaseStatus status) {
        Lease lease = leaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lease not found"));
        lease.setStatus(status);
        lease = leaseRepository.save(lease);
        return mapToDto(lease);
    }

    @Transactional
    public LeaseDto signLease(UUID id) {
        Lease lease = leaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lease not found"));
        lease.setStatus(LeaseStatus.ACTIVE);
        lease.setSignedAt(LocalDateTime.now());
        lease = leaseRepository.save(lease);
        return mapToDto(lease);
    }

    @Transactional
    public LeaseDto terminateLease(UUID id) {
        Lease lease = leaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lease not found"));
        lease.setStatus(LeaseStatus.TERMINATED);
        lease.setTerminatedAt(LocalDateTime.now());
        lease = leaseRepository.save(lease);
        return mapToDto(lease);
    }

    public LeaseDto getActiveLeaseByUnit(UUID unitId) {
        return leaseRepository.findFirstByUnitIdAndStatusOrderByStartDateDesc(unitId, LeaseStatus.ACTIVE)
                .map(this::mapToDto)
                .orElse(null);
    }

    public LeaseDto getActiveLeaseByTenant(UUID tenantId) {
        return leaseRepository.findFirstByTenantIdAndStatusOrderByStartDateDesc(tenantId, LeaseStatus.ACTIVE)
                .map(this::mapToDto)
                .orElse(null);
    }

    private LeaseDto mapToDto(Lease lease) {
        return LeaseDto.builder()
                .id(lease.getId())
                .tenantId(lease.getTenantId())
                .propertyId(lease.getPropertyId())
                .unitId(lease.getUnitId())
                .startDate(lease.getStartDate())
                .endDate(lease.getEndDate())
                .rentAmount(lease.getRentAmount())
                .currency(lease.getCurrency())
                .securityDeposit(lease.getSecurityDeposit())
                .status(lease.getStatus())
                .signedAt(lease.getSignedAt())
                .terminatedAt(lease.getTerminatedAt())
                .createdAt(lease.getCreatedAt())
                .updatedAt(lease.getUpdatedAt())
                .build();
    }
}
