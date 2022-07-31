package com.test.pexapark;

import com.test.pexapark.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest extends BaseTest {

    @Autowired
    public HomePage homePage;

    @BeforeEach
    void openWebsite(){
        homePage.goToHomePage();
    }

    @Test
    void should_display_homepage() {
        assertEquals(homePage.getTitleText(), "Home");
    }

    @Test
    void should_display_content_homepage() {
        assertEquals(homePage.getContentTextList().size(), 4);
    }
}
