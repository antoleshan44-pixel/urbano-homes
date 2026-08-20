package com.urbano.tenant.repository;

import com.urbano.common.enums.LeaseStatus;
import com.urbano.tenant.entity.Lease;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, UUID> {
    Page<Lease> findByTenantId(UUID tenantId, Pageable pageable);
    Page<Lease> findByPropertyId(UUID propertyId, Pageable pageable);
    Page<Lease> findByStatus(LeaseStatus status, Pageable pageable);
    List<Lease> findByStatusAndEndDateBefore(LeaseStatus status, java.time.LocalDate date);
    Optional<Lease> findFirstByUnitIdAndStatusOrderByStartDateDesc(UUID unitId, LeaseStatus status);
    Optional<Lease> findFirstByTenantIdAndStatusOrderByStartDateDesc(UUID tenantId, LeaseStatus status);
}
