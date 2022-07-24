package com.test.pexapark.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
public class LoginPage extends LoadableComponent<LoginPage> {

    private WebDriver driver;

    @Value("${credentials.user.name}")
    public String username;

    @Value("${credentials.user.password}")
    public String password;

    @FindBy(css = "input#username")
    private WebElement userNameInput;

    @FindBy(css = "input#pwd")
    private WebElement passwordInput;

    @FindBy(css = "button#submit")
    private WebElement submitButton;

    @Autowired
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        driver.get("https://test-monitor-qa.pexapark.com/login");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assertTrue(url.endsWith("/login"), "Not on the issue entry page: " + url);
    }

    public void enterCredentials() {
        userNameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
    }
}
