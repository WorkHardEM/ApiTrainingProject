package com.socks.tests;

import com.socks.api.payloads.UserPayload;
import com.socks.api.services.UserApiService;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.socks.api.conditions.Conditions.bodyField;
import static com.socks.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.not;

public class UsersTest {

    private final UserApiService userApiService = new UserApiService();

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "http://192.168.99.100/";
    }

    @Test
    public void testCanRegisterNewUser() {
        // given
        UserPayload user = new UserPayload()
                .username(RandomStringUtils.randomAlphanumeric(6))
                .email("test@mail.com")
                .password("test123");

        // expect
        userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(blankOrNullString())));
    }

    @Test
    public void testCanNotRegisterSameUserTwice() {
        UserPayload user = new UserPayload()
                .username(RandomStringUtils.randomAlphanumeric(6))
                .email("test@mail.com")
                .password("test123");

        userApiService.registerUser(user)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(blankOrNullString())));

        userApiService.registerUser(user)
                .shouldHave(statusCode(500));
    }
}
