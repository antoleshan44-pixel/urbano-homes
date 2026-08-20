package com.urbano.crm.service;

import com.urbano.common.dto.PagedResponse;
import com.urbano.common.exception.ResourceNotFoundException;
import com.urbano.crm.dto.ContactDto;
import com.urbano.crm.dto.ContactRequest;
import com.urbano.crm.entity.Contact;
import com.urbano.crm.repository.ContactRepository;
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
public class ContactService {

    private final ContactRepository contactRepository;

    @Transactional
    public ContactDto createContact(ContactRequest request) {
        if (contactRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Contact contact = Contact.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .mobile(request.getMobile())
                .type(request.getType())
                .company(request.getCompany())
                .position(request.getPosition())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .zipCode(request.getZipCode())
                .country(request.getCountry())
                .preferredContactMethod(request.getPreferredContactMethod())
                .notes(request.getNotes())
                .assignedTo(request.getAssignedTo())
                .isActive(true)
                .source(request.getSource())
                .createdAt(LocalDateTime.now())
                .build();

        contact = contactRepository.save(contact);
        log.info("Contact created: {}", contact.getId());
        return mapToDto(contact);
    }

    public ContactDto getContact(UUID id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
        return mapToDto(contact);
    }

    public PagedResponse<ContactDto> getAllContacts(int page, int size) {
        Page<Contact> contactPage = contactRepository.findAll(PageRequest.of(page, size));
        List<ContactDto> content = contactPage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<ContactDto>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(contactPage.getTotalElements())
                .totalPages(contactPage.getTotalPages())
                .first(contactPage.isFirst())
                .last(contactPage.isLast())
                .build();
    }

    public PagedResponse<ContactDto> getContactsByType(String type, int page, int size) {
        Page<Contact> contactPage = contactRepository.findByType(type, PageRequest.of(page, size));
        List<ContactDto> content = contactPage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PagedResponse.<ContactDto>builder()
                .content(content)
                .page(page)
                .size(size)
                .totalElements(contactPage.getTotalElements())
                .totalPages(contactPage.getTotalPages())
                .first(contactPage.isFirst())
                .last(contactPage.isLast())
                .build();
    }

    @Transactional
    public ContactDto updateContact(UUID id, ContactRequest request) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setPhone(request.getPhone());
        contact.setMobile(request.getMobile());
        contact.setType(request.getType());
        contact.setCompany(request.getCompany());
        contact.setPosition(request.getPosition());
        contact.setAddress(request.getAddress());
        contact.setCity(request.getCity());
        contact.setState(request.getState());
        contact.setZipCode(request.getZipCode());
        contact.setCountry(request.getCountry());
        contact.setPreferredContactMethod(request.getPreferredContactMethod());
        contact.setNotes(request.getNotes());
        contact.setAssignedTo(request.getAssignedTo());
        contact.setSource(request.getSource());

        contact = contactRepository.save(contact);
        log.info("Contact updated: {}", contact.getId());
        return mapToDto(contact);
    }

    @Transactional
    public void deleteContact(UUID id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
        contact.setActive(false);
        contactRepository.save(contact);
        log.info("Contact deactivated: {}", id);
    }

    @Transactional
    public ContactDto updateContactStatus(UUID id, boolean active) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
        contact.setActive(active);
        contact = contactRepository.save(contact);
        return mapToDto(contact);
    }

    @Transactional
    public ContactDto updateLastContactDate(UUID id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
        contact.setLastContactDate(LocalDateTime.now());
        contact = contactRepository.save(contact);
        return mapToDto(contact);
    }

    private ContactDto mapToDto(Contact contact) {
        return ContactDto.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .mobile(contact.getMobile())
                .type(contact.getType())
                .company(contact.getCompany())
                .position(contact.getPosition())
                .address(contact.getAddress())
                .city(contact.getCity())
                .state(contact.getState())
                .zipCode(contact.getZipCode())
                .country(contact.getCountry())
                .preferredContactMethod(contact.getPreferredContactMethod())
                .notes(contact.getNotes())
                .assignedTo(contact.getAssignedTo())
                .isActive(contact.isActive())
                .lastContactDate(contact.getLastContactDate())
                .source(contact.getSource())
                .createdAt(contact.getCreatedAt())
                .updatedAt(contact.getUpdatedAt())
                .build();
    }
}
