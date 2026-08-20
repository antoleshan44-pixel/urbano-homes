package com.urbano.tenant.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "property-service", fallback = PropertyServiceFallback.class)
public interface PropertyServiceClient {

    @GetMapping("/api/properties/{id}")
    Object getProperty(@PathVariable("id") UUID id);

    @GetMapping("/api/units/{id}")
    Object getUnit(@PathVariable("id") UUID id);

    @PostMapping("/api/units/{id}/status")
    Object updateUnitStatus(@PathVariable("id") UUID id, @RequestBody Object request);
}
