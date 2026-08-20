package com.urbano.listing.repository;

import com.urbano.listing.entity.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, UUID> {
    Page<Listing> findByPropertyId(UUID propertyId, Pageable pageable);
    Page<Listing> findByStatus(String status, Pageable pageable);
    Page<Listing> findByPublishedTrue(Pageable pageable);
}
