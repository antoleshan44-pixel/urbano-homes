package com.urbano.e2e;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthFlowTest extends E2ETestBase {

    @Test
    public void testHealthCheck() {
        given()
                .get("/actuator/health")
                .then()
                .statusCode(200);
    }
}