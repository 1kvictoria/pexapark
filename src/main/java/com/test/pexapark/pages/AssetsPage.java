package com.test.pexapark.pages;

import com.test.pexapark.component.AssetsTable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Lazy
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AssetsPage extends BasePage {

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
    private WebElement thirdElementButton;

    @FindBy(css = "body > main > div > table > tbody:nth-child(2) > tr:nth-child(4) > td:nth-child(1)")
    private WebElement thirdElementText;

    @FindBy(css = "body > main > div > table > tbody:nth-child(2) > tr:nth-child(14) > td:nth-child(1)")
    private WebElement lastElementText;

    @FindBy(css = "body > main > div > div > p")
    private WebElement errorMessageBody;

    @FindBy(css = "body > main > div > form")
    private WebElement addAssetForm;

    private AssetsTable table;

    public AssetsTable getAssetsTable() {
        return table;
    }

    @PostConstruct
    public void init() {
        super.init();
        table = new AssetsTable(driver);
    }

    public void goToAssetsPage() {
        driver.get(String.format("%s/assets", baseURL));
    }

    public void clickEditSecondAsset() {
        wait.until(visibilityOf(assetTable));
        table.editRow(4);
        wait.until(visibilityOf(editAssetHeader));
    }

    public void editAsset(String assetName, String capacityFactor) {
        wait.until(visibilityOf(addAssetForm));

        assetNameInput.sendKeys(assetName);
        capacityFactorInput.sendKeys(capacityFactor);

        scrollToAndClick(submitButton);
    }

    public void editAssetName(String assetName) {
        wait.until(visibilityOf(editAssetHeader));
        driver.findElement(By.cssSelector("#e-ewtwetwt"));
        assetNameInput.sendKeys(assetName);

        scrollToAndClick(submitButton);
    }

    public void setEditAssetHeader_asset_capacity_factor() {
    }


    public void deleteAsset() {
    }

    public void createNewAsset(String assetName, String capacityFactor) {
        wait.until(visibilityOf(editAssetHeader));
        assetNameInput.sendKeys(assetName);
        capacityFactorInput.sendKeys(capacityFactor);

        scrollToAndClick(submitButton);
    }

    public void createAssetName(String assetName) {
        wait.until(visibilityOf(editAssetHeader));
        assetNameInput.sendKeys(assetName);

        scrollToAndClick(submitButton);
    }

//  WORKAROUND: using javascript .click() to resolve the "Element is not clickable at point" problem
//  TODO: find neater way to solve this
    private void scrollToAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public String getThirdElementText() {
        return thirdElementText.getText();
    }

    public String getErrorMessage() {
        return errorMessageBody.getText();
    }

}
