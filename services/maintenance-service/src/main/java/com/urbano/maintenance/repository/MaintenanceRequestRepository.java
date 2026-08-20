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
public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, UUID> {
    Page<MaintenanceRequest> findByPmAccountIdAndDeletedAtIsNullOrderByCreatedAtDesc(UUID pmAccountId, Pageable pageable);

    Page<MaintenanceRequest> findByTenantIdAndDeletedAtIsNullOrderByCreatedAtDesc(UUID tenantId, Pageable pageable);

    Optional<MaintenanceRequest> findByIdAndPmAccountIdAndDeletedAtIsNull(UUID id, UUID pmAccountId);

    Optional<MaintenanceRequest> findByIdAndTenantIdAndDeletedAtIsNull(UUID id, UUID tenantId);
}