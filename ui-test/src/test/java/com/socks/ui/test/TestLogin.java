package com.socks.ui.test;

import com.socks.api.conditions.Conditions;
import com.socks.api.payloads.UserPayload;
import com.socks.api.services.UserApiService;
import com.socks.ui.LoggedUserPage;
import com.socks.ui.MainPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class TestLogin extends BaseUITest {

    private final UserApiService userApiService = new UserApiService();

    @Test
    public void userCanLoginWithValidCredentials() {
        // given
        UserPayload userPayload = new UserPayload()
                .username(randomAlphanumeric(6))
                .password("test123")
                .email("test@mail.com");

        userApiService.registerUser(userPayload)
                .shouldHave(Conditions.statusCode(200));

        // when
        MainPage.open()
            .loginAs(userPayload.username(), userPayload.password());

        LoggedUserPage loggedUserPage = at(LoggedUserPage.class);

        loggedUserPage.logoutBtn().shouldHave(text("Logout"));
        loggedUserPage.userName().shouldHave(text("Logged in as")); // 01:35:00
    }
}

