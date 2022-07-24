package com.test.pexapark;

import com.test.pexapark.pages.AssetsPage;
import com.test.pexapark.pages.HomePage;
import com.test.pexapark.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssetsTest {

    @Autowired
    private WebDriver driver;

    @Autowired
    private LoginPage loginPage;

    @Autowired
    public AssetsPage assetsPage;

    @Autowired
    public HomePage homePage;

    @BeforeEach
    void openWebsite(){
        homePage.get();
        homePage.goToLoginPage();
        loginPage.enterCredentials();
    }

    @Test
    void should_add_text_asset() {
        assetsPage.addTexAsset();
    }

    @Test
    void should_add_text_numeric_asset() {}

    @Test
    void should_add_text_numeric_character_asset() {}


    @Test
    void should_edit_asset() {
        String newName = "<-Vic->" + UUID.randomUUID();

        assetsPage.clickEditSecondAsset();
        assetsPage.editAsset(newName, "1234.09835");

        boolean errorIsFound = driver
                .findElement(By.cssSelector("body > main > div > div > h2"))
                .getText()
                .equals("Error");
        if(errorIsFound) {
//          do temp corrective action
        }
        assertEquals("https://test-monitor-qa.pexapark.com/assets/" + newName, driver.getCurrentUrl());
        assertEquals(assetsPage.getThirdElementText(), newName);
    }

    @Test
    void should_delete_asset() {}

}
