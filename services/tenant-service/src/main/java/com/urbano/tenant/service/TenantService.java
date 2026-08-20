package com.urbano.tenant.service;

import com.urbano.common.dto.PagedResponse;
import com.urbano.common.exception.ResourceNotFoundException;
import com.urbano.tenant.dto.TenantDto;
import com.urbano.tenant.dto.TenantRequest;
import com.urbano.tenant.entity.Tenant;
import com.urbano.tenant.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;

    @Transactional
    public TenantDto createTenant(TenantRequest request) {
        if (tenantRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (tenantRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone number already exists");
        }

        Tenant tenant = new Tenant();
        tenant.setFirstName(request.getFirstName());
        tenant.setLastName(request.getLastName());
        tenant.setEmail(request.getEmail());
        tenant.setPhone(request.getPhone());
        tenant.setAddress(request.getAddress());
        tenant.setCity(request.getCity());
        tenant.setState(request.getState());
        tenant.setZipCode(request.getZipCode());
        tenant.setCountry(request.getCountry());
        tenant.setIsActive(true);
        tenant.setCreatedAt(LocalDateTime.now());

        tenant = tenantRepository.save(tenant);
        log.info("Tenant created: {}", tenant.getId());
        return mapToDto(tenant);
    }

    public TenantDto getTenant(UUID id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));
        return mapToDto(tenant);
    }

    public PagedResponse<TenantDto> getAllTenants(int page, int size) {
        Page<Tenant> tenantPage = tenantRepository.findByDeletedAtIsNull(PageRequest.of(page, size));
        List<TenantDto> content = tenantPage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<TenantDto>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(tenantPage.getTotalElements())
                .totalPages(tenantPage.getTotalPages())
                .first(tenantPage.isFirst())
                .last(tenantPage.isLast())
                .build();
    }

    @Transactional
    public TenantDto updateTenant(UUID id, TenantRequest request) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));

        tenant.setFirstName(request.getFirstName());
        tenant.setLastName(request.getLastName());
        tenant.setPhone(request.getPhone());
        tenant.setAddress(request.getAddress());
        tenant.setCity(request.getCity());
        tenant.setState(request.getState());
        tenant.setZipCode(request.getZipCode());
        tenant.setCountry(request.getCountry());

        tenant = tenantRepository.save(tenant);
        log.info("Tenant updated: {}", tenant.getId());
        return mapToDto(tenant);
    }

    @Transactional
    public void deleteTenant(UUID id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));
        tenant.setDeletedAt(LocalDateTime.now());
        tenantRepository.save(tenant);
        log.info("Tenant deleted: {}", id);
    }

    private TenantDto mapToDto(Tenant tenant) {
        return TenantDto.builder()
                .id(tenant.getId())
                .firstName(tenant.getFirstName())
                .lastName(tenant.getLastName())
                .email(tenant.getEmail())
                .phone(tenant.getPhone())
                .address(tenant.getAddress())
                .city(tenant.getCity())
                .state(tenant.getState())
                .zipCode(tenant.getZipCode())
                .country(tenant.getCountry())
                .isActive(tenant.getIsActive())
                .createdAt(tenant.getCreatedAt())
                .updatedAt(tenant.getUpdatedAt())
                .build();
    }
}
