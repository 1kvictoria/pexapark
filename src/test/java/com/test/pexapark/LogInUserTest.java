package com.test.pexapark;

import com.test.pexapark.pages.HomePage;
import com.test.pexapark.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LogInUserTest extends BaseTest {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    public HomePage homePage;

    @BeforeEach
    void openWebsite(){
        homePage.goToHomePage();
    }

    @Test
    void logInExistingUser() {
        homePage.goToLoginPage();
        loginPage.enterCredentials();

    }

}
