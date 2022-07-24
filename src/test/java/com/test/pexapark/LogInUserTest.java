package com.test.pexapark;

import com.test.pexapark.pages.HomePage;
import com.test.pexapark.pages.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LogInUserTest {

    @Autowired
    private WebDriver driver;

    @Autowired
    private LoginPage loginPage;

    @Autowired
    public HomePage homePage;

    @BeforeEach
    void openWebsite(){
        homePage.get();
    }

    @Test
    void logInExistingUser() {
        homePage.goToLoginPage();
        loginPage.enterCredentials();

    }

//   @AfterAll
//   static void closeBrowser(){
//        driver.quit();
//    }

}
