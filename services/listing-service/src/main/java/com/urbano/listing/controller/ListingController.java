package com.urbano.listing.controller;

import com.urbano.common.dto.PagedResponse;
import com.urbano.listing.dto.ListingDto;
import com.urbano.listing.dto.ListingRequest;
import com.urbano.listing.service.ListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @PostMapping
    public ResponseEntity<ListingDto> createListing(@Valid @RequestBody ListingRequest request) {
        return ResponseEntity.ok(listingService.createListing(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingDto> getListing(@PathVariable UUID id) {
        return ResponseEntity.ok(listingService.getListing(id));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<ListingDto>> getAllListings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(listingService.getAllListings(page, size));
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<PagedResponse<ListingDto>> getListingsByProperty(
            @PathVariable UUID propertyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(listingService.getListingsByProperty(propertyId, page, size));
    }

    @GetMapping("/active")
    public ResponseEntity<PagedResponse<ListingDto>> getActiveListings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(listingService.getActiveListings(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListingDto> updateListing(
            @PathVariable UUID id,
            @Valid @RequestBody ListingRequest request) {
        return ResponseEntity.ok(listingService.updateListing(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable UUID id) {
        listingService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ListingDto> updateListingStatus(
            @PathVariable UUID id,
            @RequestParam String status) {
        return ResponseEntity.ok(listingService.updateListingStatus(id, status));
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<ListingDto> publishListing(@PathVariable UUID id) {
        return ResponseEntity.ok(listingService.publishListing(id));
    }

    @PostMapping("/{id}/unpublish")
    public ResponseEntity<ListingDto> unpublishListing(@PathVariable UUID id) {
        return ResponseEntity.ok(listingService.unpublishListing(id));
    }
}
