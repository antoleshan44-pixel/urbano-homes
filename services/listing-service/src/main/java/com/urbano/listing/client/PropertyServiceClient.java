package com.urbano.listing.client;

import com.urbano.listing.dto.VacantUnitDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "property-service", fallback = PropertyServiceFallback.class)
public interface PropertyServiceClient {

    @GetMapping("/internal/vacant-units")
    List<VacantUnitDto> getVacantUnits();
}
