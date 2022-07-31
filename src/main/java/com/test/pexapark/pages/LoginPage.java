package com.test.pexapark.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Lazy
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginPage extends BasePage {

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

    public void enterCredentials() {
        userNameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
    }

}
