package com.test.pexapark.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;


@Lazy
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HomePage extends BasePage {

    @FindBy(css = "body > nav")
    private WebElement navigationBar;

    @FindBy(css = "#navbarNav > ul > li:nth-child(2) > a")
    private WebElement loginButton;

    @FindBy(css = "div.card-body")
    private WebElement cardHomePage;

    @FindBy(css = "body > div > h1")
    private WebElement title;

    @FindBy(css = "body > main > div > div > div > div > div > div > ul > li")
    private List<WebElement> contentTextList;

    public void goToHomePage() {
        driver.get(baseURL);
    }

    public HomePage goToLoginPage(){
        wait.until(visibilityOf(navigationBar));
        loginButton.click();
        return this;
    }

    public String getTitleText() {
        return title.getText();
    }

    public List<WebElement> getContentTextList() {
        return contentTextList;
    }
}
