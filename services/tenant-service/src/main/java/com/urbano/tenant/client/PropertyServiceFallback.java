package com.urbano.tenant.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PropertyServiceFallback implements PropertyServiceClient {

    @Override
    public Object getProperty(UUID id) {
        log.warn("Property service unavailable - getProperty");
        return null;
    }

    @Override
    public Object getUnit(UUID id) {
        log.warn("Property service unavailable - getUnit");
        return null;
    }

    @Override
    public Object updateUnitStatus(UUID id, Object request) {
        log.warn("Property service unavailable - updateUnitStatus");
        return null;
    }
}
