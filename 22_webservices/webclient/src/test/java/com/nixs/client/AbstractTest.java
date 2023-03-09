package com.nixs.client;

import io.restassured.RestAssured;
import org.awaitility.Awaitility;
import org.junit.BeforeClass;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {
    static final String BASE_URL = "http://app:8080";

    @BeforeClass
    public static void setup() {
        Awaitility
                .await()
                .atMost(1, TimeUnit.MINUTES)
                .until(() -> {
                    try {
                        return RestAssured.get(BASE_URL).statusCode() == 200;
                    } catch (Exception e) {
                        return false;
                    }
                });
    }
}