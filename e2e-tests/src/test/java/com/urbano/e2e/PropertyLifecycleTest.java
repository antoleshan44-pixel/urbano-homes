package com.urbano.e2e;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.*;

public class PropertyLifecycleTest extends E2ETestBase {

    private String authToken;
    private UUID propertyId;
    private UUID unitId;

    @BeforeEach
    public void setupTest() {
        // Register and login
        String email = "property-test-" + System.currentTimeMillis() + "@test.com";
        Map<String, String> registerRequest = Map.of(
                "fullName", "Property Test PM",
                "email", email,
                "phone", "0712345678",
                "password", "Password123!",
                "companyName", "Property Test Company"
        );

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(registerRequest)
                .post("/auth/register")
                .then()
                .statusCode(200);

        Map<String, String> loginRequest = Map.of(
                "email", email,
                "password", "Password123!"
        );

        authToken = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken");
    }

    @Test
    public void testFullPropertyLifecycle() {
        // 1. Create property
        Map<String, String> propertyRequest = Map.of(
                "name", "Test Property",
                "location", "Kilimani, Nairobi"
        );

        propertyId = UUID.fromString(RestAssured.given()
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .body(propertyRequest)
                .post("/api/properties")
                .then()
                .statusCode(200)
                .extract()
                .path("id"));

        // 2. Create unit
        Map<String, Object> unitRequest = Map.of(
                "label", "Unit A",
                "bedrooms", 2,
                "rentAmount", 25000,
                "status", "VACANT"
        );

        unitId = UUID.fromString(RestAssured.given()
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .body(unitRequest)
                .post("/api/properties/" + propertyId + "/units")
                .then()
                .statusCode(200)
                .extract()
                .path("id"));

        // 3. Verify not published
        RestAssured.given()
                .header("Authorization", "Bearer " + authToken)
                .get("/api/properties/" + propertyId + "/units/" + unitId)
                .then()
                .statusCode(200)
                .body("published", equalTo(false));

        // 4. Publish unit
        RestAssured.given()
                .header("Authorization", "Bearer " + authToken)
                .put("/api/properties/" + propertyId + "/units/" + unitId + "/publish")
                .then()
                .statusCode(200)
                .body("published", equalTo(true));

        // 5. Verify published
        RestAssured.given()
                .get("/api/public/listings")
                .then()
                .statusCode(200)
                .body("content.find { it.id == '" + unitId + "' }", notNullValue());
    }
}