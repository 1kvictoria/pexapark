package com.test.pexapark;

import com.test.pexapark.pages.AssetsPage;
import com.test.pexapark.pages.HomePage;
import com.test.pexapark.pages.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.*;

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
        assetsPage.createNewAsset(newAssetName, "1");

        assertTrue(assetsPage.getAssetsTable()
                .findRowByName(newAssetName)
                .isPresent());
    }

    @Test
    void should_create_text_numeric_asset() {
        var newAssetName = RandomStringUtils.randomAlphanumeric(5);
        assetsPage.createNewAsset(newAssetName, "-9.40");

        assertTrue(assetsPage.isAssetPresent(newAssetName));
    }

    @Test
    void should_create_text_numeric_character_asset() {
        var newAssetName = "+!@±$%&^!" + RandomStringUtils.randomAlphanumeric(5);

        assetsPage.createNewAsset(newAssetName, "1.2");

        assertTrue(assetsPage.getAssetsTable()
                .findRowByName(newAssetName)
                .isPresent());
    }

    @Test
    void should_not_create_short_name_asset() {
        var newAssetName = RandomStringUtils.randomAlphabetic(1);
        assetsPage.createNewAsset(newAssetName, "3.20932");

        assertEquals("Invalid asset name, name too short", assetsPage.getErrorMessage());
    }

    @Test
    void should_not_create_long_name_asset() {
        var newAssetName = RandomStringUtils.randomAlphanumeric(34);
        assetsPage.createNewAsset(newAssetName, "4.999");

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
        var oldAssetName = RandomStringUtils.randomAlphanumeric(5);
        var capacityFactor = "-4.1";
        assetsPage.createNewAsset(oldAssetName, capacityFactor);

        String newAssetName = RandomStringUtils.randomAlphanumeric(9);

        assertFalse(assetsPage.isAssetPresent(newAssetName));

        assetsPage.editAsset(oldAssetName, newAssetName, capacityFactor);

        assertTrue(assetsPage.isAssetPresent(newAssetName));
    }

    @Test
    void should_delete_asset_with_letters() {
        var assetName = RandomStringUtils.randomAlphabetic(6);
        var capacityFactor = "-4.1";
        assetsPage.createNewAsset(assetName, capacityFactor);

        assertTrue(assetsPage.isAssetPresent(assetName));

        assetsPage.deleteAsset(assetName);

        assertFalse(assetsPage.isAssetPresent(assetName));
    }

    @Test
    void should_delete_asset_with_letters_numbers() {
        var assetName = RandomStringUtils.randomAlphanumeric(8);
        var capacityFactor = "-0.1";
        assetsPage.createNewAsset(assetName, capacityFactor);

        assertTrue(assetsPage.isAssetPresent(assetName));

        assetsPage.deleteAsset(assetName);

        assertFalse(assetsPage.isAssetPresent(assetName));
    }
}
