package com.urbano.payment.repository;

import com.urbano.payment.entity.ManagementFee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ManagementFeeRepository extends JpaRepository<ManagementFee, UUID> {
    Optional<ManagementFee> findByPropertyId(UUID propertyId);
    Optional<ManagementFee> findByUnitId(UUID unitId);
    Page<ManagementFee> findByPmAccountIdOrderByPeriodDesc(UUID pmAccountId, Pageable pageable);
    Optional<ManagementFee> findByIdAndPmAccountId(UUID id, UUID pmAccountId);
}
