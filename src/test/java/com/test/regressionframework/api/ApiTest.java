package com.test.regressionframework.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void testGetReport() {
        given()
                .when()
                .get("/reports")
                .then()
                .statusCode(200);
    }

    @Test
    void testRunTests() {
        given()
                .port(port)   // random port
                .when()
                .post("/run/runAll")   // ✅ correct URL
                .then()
                .statusCode(200);
    }
}