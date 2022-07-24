package com.test.pexapark.pages;

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
public class HomePage extends LoadableComponent<HomePage> {

    private WebDriver driver;

    @Autowired
    private WebDriverWait wait;

    @FindBy(css = "body > nav")
    private WebElement navigationBar;

    @FindBy(css = "#navbarNav > ul > li:nth-child(2) > a")
    private WebElement loginButton;

    @FindBy(css = "div.card-body")
    private WebElement cardHomePage;

    @FindBy(css = "body > div > h1")
    private WebElement title;

    @Autowired
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        driver.get("https://test-monitor-qa.pexapark.com/");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertTrue(url.endsWith(".com/"), "Not on the issue entry page: " + url);
    }

    public HomePage goToLoginPage(){
        wait.until(visibilityOf(navigationBar));
        loginButton.click();
        return this;
    }

    public String getTitleText() {
        return title.getText();
    }

}
