package com.urbano.e2e;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class E2ETestBase {

    protected static final String BASE_URL = System.getenv().getOrDefault(
            "E2E_BASE_URL", "http://localhost:8080"
    );

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}