package com.urbano.crm.controller;

import com.urbano.common.dto.PagedResponse;
import com.urbano.crm.dto.ContactDto;
import com.urbano.crm.dto.ContactRequest;
import com.urbano.crm.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/crm/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactDto> createContact(@Valid @RequestBody ContactRequest request) {
        return ResponseEntity.ok(contactService.createContact(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContact(@PathVariable UUID id) {
        return ResponseEntity.ok(contactService.getContact(id));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<ContactDto>> getAllContacts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(contactService.getAllContacts(page, size));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<PagedResponse<ContactDto>> getContactsByType(
            @PathVariable String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(contactService.getContactsByType(type, page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> updateContact(
            @PathVariable UUID id,
            @Valid @RequestBody ContactRequest request) {
        return ResponseEntity.ok(contactService.updateContact(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable UUID id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ContactDto> updateContactStatus(
            @PathVariable UUID id,
            @RequestParam boolean active) {
        return ResponseEntity.ok(contactService.updateContactStatus(id, active));
    }

    @PostMapping("/{id}/last-contact")
    public ResponseEntity<ContactDto> updateLastContactDate(@PathVariable UUID id) {
        return ResponseEntity.ok(contactService.updateLastContactDate(id));
    }
}
