package com.urbano.crm.repository;

import com.urbano.crm.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    Optional<Contact> findByEmail(String email);
    boolean existsByEmail(String email);
    Page<Contact> findByType(String type, Pageable pageable);
    Page<Contact> findByIsActiveTrue(Pageable pageable);
}
