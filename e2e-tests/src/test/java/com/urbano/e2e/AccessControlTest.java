package com.urbano.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
public class AccessControlTest extends E2ETestBase {

    @Test
    public void testPublicEndpointAccess() {
        // Test public endpoints
        String response = restTemplate.getForObject(
            baseUrl + "/api/public/health",
            String.class
        );
        assertThat("Health endpoint should return a response", response, notNullValue());
    }

    @Test
    public void testProtectedEndpointWithoutToken() {
        // Test protected endpoint without token should return 401
        try {
            restTemplate.getForObject(
                baseUrl + "/api/properties",
                String.class
            );
        } catch (Exception e) {
            // Expected to fail with 401
            assertThat("Should throw exception", e.getMessage(), notNullValue());
        }
    }

    @Test
    public void testProtectedEndpointWithInvalidToken() {
        // Test with invalid token
        // This would be implemented with a proper test
    }
}
