package com.urbano.maintenance.repository;

import com.urbano.common.enums.MaintenanceStatus;
import com.urbano.maintenance.entity.MaintenanceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceRequest, UUID> {
    Page<MaintenanceRequest> findByPropertyId(UUID propertyId, Pageable pageable);
    Page<MaintenanceRequest> findByUnitId(UUID unitId, Pageable pageable);
    Page<MaintenanceRequest> findByTenantId(UUID tenantId, Pageable pageable);
    Page<MaintenanceRequest> findByStatus(MaintenanceStatus status, Pageable pageable);
    Optional<MaintenanceRequest> findByIdAndTenantId(UUID id, UUID tenantId);
}
