package com.urbano.maintenance.client;

import com.urbano.maintenance.dto.UnitResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "property-service", fallback = PropertyServiceFallback.class)
public interface PropertyServiceClient {

    @GetMapping("/api/units/{id}")
    UnitResponse getUnit(@PathVariable("id") UUID id);

    @PutMapping("/api/units/{id}/status")
    void updateUnitStatus(@PathVariable("id") UUID id, @RequestParam String status);
}
