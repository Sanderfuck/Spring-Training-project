package com.nixs.client;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserTest extends AbstractTest {
    private static final int DEFAULT_QUANTITY_USERS = 2;
    private static final int CREATE_QUANTITY_USERS = 3;
    private static final long ADMIN_ID = 1;
    private static final long USER_ID = 2;
    private static final String ADMIN_LOGIN = "admin";
    private static final String USER_LOGIN = "user";

    @Test
    public void shouldReturnAdminAndUser() {
        String json = get(BASE_URL + "/api/users").asString();
        UserDto userAdmin = JsonPath.from(json).getObject("[0]", UserDto.class);
        Assert.assertThat(userAdmin.getId(), equalTo(ADMIN_ID));

        UserDto userUser = JsonPath.from(json).getObject("[1]", UserDto.class);
        Assert.assertThat(userUser.getId(), equalTo(USER_ID));

        List<UserDto> users = given().contentType(ContentType.JSON).get(BASE_URL + "/api/users")
                .then()
                .extract()
                .jsonPath()
                .getList(".", UserDto.class);

        Assert.assertEquals(DEFAULT_QUANTITY_USERS, users.size());
        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("nixs.com")));
    }

    @Test
    public void shouldReturnUserById() {

        given().contentType(ContentType.JSON)
                .when().get(BASE_URL + "/api/users" + "/" + ADMIN_ID)
                .then().statusCode(200)
                .body("login", equalTo(ADMIN_LOGIN));

        given().contentType(ContentType.JSON)
                .when().get(BASE_URL + "/api/users" + "/" + USER_ID)
                .then().statusCode(200)
                .body("login", equalTo(USER_LOGIN));
    }

    @Test
    public void shouldCreateUser() {
        UserDto createUser = new UserDto(
                null,
                "test",
                "test",
                "test@nixs.com",
                "First_Test",
                "Last_Test",
                Date.valueOf("2000-10-10"),
                "USER");

        given().contentType(ContentType.JSON)
                .body(createUser)
                .post(BASE_URL + "/api/users")
                .then().statusCode(200);

        List<UserDto> usersAfterCreate = given()
                .contentType(ContentType.JSON)
                .get(BASE_URL + "/api/users")
                .then()
                .extract()
                .jsonPath()
                .getList(".", UserDto.class);

        UserDto getCreatedUser = new UserDto();
        Long idUserWhatWasCreate = 0L;

        for (UserDto userDto : usersAfterCreate) {
            if (userDto.getLogin().equals(createUser.getLogin())) {
                getCreatedUser = userDto;
                idUserWhatWasCreate = userDto.getId();
            }
        }

        Assert.assertEquals(createUser.getLogin(), getCreatedUser.getLogin());
        Assert.assertEquals(CREATE_QUANTITY_USERS, usersAfterCreate.size());

        given()
                .when()
                .delete(BASE_URL + "/api/users/" + idUserWhatWasCreate)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldUpdateUser() {
        String json = get(BASE_URL + "/api/users").asString();

        UserDto tempUser = JsonPath.from(json).getObject("[1]", UserDto.class);

        UserDto userBeforeUpdate = JsonPath.from(json).getObject("[1]", UserDto.class);

        userBeforeUpdate.setFirstName("Test_FirstName");
        userBeforeUpdate.setLastName("Test_LastName");
        userBeforeUpdate.setEmail("test@nixs.com");

        List<UserDto> usersBeforeUpdate = given()
                .contentType(ContentType.JSON)
                .get(BASE_URL + "/api/users")
                .then()
                .extract()
                .jsonPath()
                .getList(".", UserDto.class);

        given().contentType(ContentType.JSON)
                .body(userBeforeUpdate)
                .put(BASE_URL + "/api/users")
                .then().statusCode(200);

        List<UserDto> usersAfterUpdate = given()
                .contentType(ContentType.JSON)
                .get(BASE_URL + "/api/users")
                .then()
                .extract()
                .jsonPath()
                .getList(".", UserDto.class);

        UserDto userAfterUpdate = new UserDto();
        for (UserDto userDto : usersAfterUpdate) {
            if (userDto.getLogin().equals(userBeforeUpdate.getLogin())) {
                userAfterUpdate = userDto;
            }
        }

        Assert.assertEquals(usersBeforeUpdate.size(), usersAfterUpdate.size());
        Assert.assertEquals(userBeforeUpdate.getEmail(), userAfterUpdate.getEmail());
        Assert.assertEquals(userBeforeUpdate.getFirstName(), userAfterUpdate.getFirstName());
        Assert.assertEquals(userBeforeUpdate.getLastName(), userAfterUpdate.getLastName());

        given().contentType(ContentType.JSON)
                .body(tempUser)
                .put(BASE_URL + "/api/users")
                .then().statusCode(200);
    }

    @Test
    public void shouldDeleteUser() {
        UserDto createUser = new UserDto(
                null,
                "test",
                "test",
                "test@nixs.com",
                "First_Test",
                "Last_Test",
                Date.valueOf("2000-10-10"),
                "USER");

        given().contentType(ContentType.JSON)
                .body(createUser)
                .post(BASE_URL + "/api/users")
                .then().statusCode(200);


        List<UserDto> usersBeforeDeleting = given()
                .contentType(ContentType.JSON)
                .get(BASE_URL + "/api/users")
                .then()
                .extract()
                .jsonPath()
                .getList(".", UserDto.class);

        Long idUsershouldDelete = 0L;

        for (UserDto userDto : usersBeforeDeleting) {
            if (userDto.getLogin().equals(createUser.getLogin())) {
                idUsershouldDelete = userDto.getId();
            }
        }

        given()
                .when()
                .delete(BASE_URL + "/api/users/" + idUsershouldDelete)
                .then()
                .statusCode(200);

        List<UserDto> usersAfterDeleting = given()
                .contentType(ContentType.JSON)
                .get(BASE_URL + "/api/users")
                .then()
                .extract()
                .jsonPath()
                .getList(".", UserDto.class);

        Assert.assertEquals(CREATE_QUANTITY_USERS, usersBeforeDeleting.size());
        Assert.assertEquals(DEFAULT_QUANTITY_USERS, usersAfterDeleting.size());
    }
}
