package com.test.pexapark;

import com.test.pexapark.pages.AssetsPage;
import com.test.pexapark.pages.HomePage;
import com.test.pexapark.pages.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssetsTest extends BaseTest {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    public AssetsPage assetsPage;

    @Autowired
    public HomePage homePage;

    @Value("${application.url}")
    private String baseURL;

    @BeforeEach
    void openWebsite(){
        homePage.goToHomePage();
        homePage.goToLoginPage();
        loginPage.enterCredentials();
        assetsPage.goToAssetsPage();
    }

    @Test
    void should_create_text_asset() {
        var newAssetName = RandomStringUtils.randomAlphabetic(5);
        assetsPage.createNewAsset(newAssetName, "10");

        assertTrue(assetsPage.getAssetsTable()
                .findRowByName(newAssetName)
                .isPresent());
    }

    @Test
    void should_create_text_numeric_asset() {
        var newAssetName = RandomStringUtils.randomAlphanumeric(5);
        assetsPage.createNewAsset(newAssetName, "10.0");

        assertTrue(assetsPage.getAssetsTable()
                .findRowByName(newAssetName)
                .isPresent());
    }

    @Test
    void should_create_text_numeric_character_asset() {
        var newAssetName = "+!@±$%&^!" + RandomStringUtils.randomAlphanumeric(5);

        assetsPage.createNewAsset(newAssetName, "10");

        assertTrue(assetsPage.getAssetsTable()
                .findRowByName(newAssetName)
                .isPresent());
    }

    @Test
    void should_not_create_short_name_asset() {
        var newAssetName = RandomStringUtils.randomAlphabetic(1);
        assetsPage.createNewAsset(newAssetName, "10");

        assertEquals("Invalid asset name, name too short", assetsPage.getErrorMessage());
    }

    @Test
    void should_not_create_long_name_asset() {
        var newAssetName = RandomStringUtils.randomAlphanumeric(34);
        assetsPage.createNewAsset(newAssetName, "10");

        assertEquals("Invalid asset name, name cannot exceed 33 characters", assetsPage.getErrorMessage());

    }

    @Test
    void should_not_create_asset_with_name_only() {
        var newAssetName = RandomStringUtils.randomAlphabetic(10);
        assetsPage.createAssetName(newAssetName);

        assertEquals("server error", assetsPage.getErrorMessage());
    }

    @Test
    void should_create_asset_with_negative_capacity_factor() {
        var newAssetName = RandomStringUtils.randomAlphanumeric(5);
        assetsPage.createNewAsset(newAssetName, "-4.0");

        assertTrue(assetsPage.getAssetsTable()
                .findRowByName(newAssetName)
                .isPresent());
    }

    @Test
    void should_edit_asset_name() {
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
        assertEquals(String.format("%s/assets/%s", baseURL, newName), driver.getCurrentUrl());
        assertEquals(assetsPage.getThirdElementText(), newName);
    }

    @Test
    void should_delete_asset_with_letters() {}

    @Test
    void should_delete_asset_with_letters_numbers() {}

    @Test
    void should_delete_asset_with_letters_numbers_characaters() {}



}
