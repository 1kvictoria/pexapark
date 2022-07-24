package com.test.pexapark.pages;

import com.test.pexapark.component.AssetsTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Component
public class AssetsPage extends LoadableComponent<AssetsPage> {

    @Autowired
    private WebDriver driver;

    @Autowired
    private WebDriverWait wait;

    @FindBy(css = "#name")
    private WebElement assetNameInput;

    @FindBy(css = "#cf")
    private WebElement capacityFactorInput;

    @FindBy(css = "#submit")
    private WebElement submitButton;

    @FindBy(css = "body > main > div > table > tbody:nth-child(2) > tr")
    private WebElement assetNameRow;

    @FindBy(css = "body > main > div > table > tbody:nth-child(2)")
    private WebElement assetTable;

    @FindBy(css = "body > div > h1")
    private WebElement editAssetHeader;

    @FindBy(css = "body > main > div > table > tbody:nth-child(2) > tr:nth-child(8) > td:nth-child(3) > form > button")
    private WebElement thridElementButton;

    @FindBy(css = "body > main > div > table > tbody:nth-child(2) > tr:nth-child(4) > td:nth-child(1)")
    private WebElement thirdElementText;

    @FindBy(css = "body > main > div > table > tbody:nth-child(2) > tr:nth-child(14) > td:nth-child(1)")
    private WebElement lastElementText;

    @FindBy(css = "")
    private WebElement deleteButton;

    private AssetsTable table;

    @Autowired
    public AssetsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        table = new AssetsTable(driver);
    }

    @Override
    protected void load() {
        driver.get("https://test-monitor-qa.pexapark.com/assets");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertTrue(url.endsWith("/assets"), "Not on the issue entry page: " + url);
    }

    public void clickEditSecondAsset() {
        wait.until(visibilityOf(assetTable));
        table.editRow(4);
        wait.until(visibilityOf(editAssetHeader));
    }

    public void editAsset(String assetName, String capacityFactor) {
        wait.until(visibilityOf(editAssetHeader));
        assetNameInput.sendKeys(assetName);
        capacityFactorInput.sendKeys(capacityFactor);
        submitButton.click();
    }

    public void deleteAsset() {

    }


    public void addTexAsset() {

    }

    public String getThirdElementText() {
        return thirdElementText.getText();
    }

    public String getLastElementText() {
        return lastElementText.getText();
    }



}
