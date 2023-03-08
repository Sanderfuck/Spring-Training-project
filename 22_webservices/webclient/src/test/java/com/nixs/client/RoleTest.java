package com.nixs.client;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RoleTest extends AbstractTest {
    private static final int DEFAULT_QUANTITY_ROLES = 2;

    @Test
    public void shouldReturnRolesByName() {
        String adminName = "ADMIN";
        String userName = "USER";

        given().contentType(ContentType.JSON)
                .when().get("/api/roles/" + adminName)
                .then().statusCode(200)
                .body("name", equalTo(adminName));

        given().contentType(ContentType.JSON)
                .when().get("/api/roles/" + userName)
                .then().statusCode(200)
                .body("name", equalTo(userName));
    }

    @Test
    public void shouldReturnRoles() {
        List<Role> roles = given().contentType(ContentType.JSON)
                .when().get("/api/roles")
                .then().statusCode(200)
                .extract().jsonPath()
                .getList(".", Role.class);

        Assert.assertEquals(DEFAULT_QUANTITY_ROLES, roles.size());
    }
}
