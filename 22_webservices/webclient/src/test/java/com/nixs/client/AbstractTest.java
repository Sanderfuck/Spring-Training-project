package com.nixs.client;

import io.restassured.RestAssured;
import org.awaitility.Awaitility;
import org.junit.BeforeClass;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.DEFAULT_URI;

public abstract class AbstractTest {

    @BeforeClass
    public static void setup() {
        String baseUri = Optional.ofNullable(System.getenv("WEBSERVER_BASE_URI")).orElse(DEFAULT_URI);
        RestAssured.baseURI = baseUri;

        Awaitility
                .await()
                .atMost(1, TimeUnit.MINUTES)
                .until(() -> {
                    try {
                        return RestAssured.get(baseUri).statusCode() == 200;
                    } catch (Exception e) {
                        return false;
                    }
                });
    }
}
