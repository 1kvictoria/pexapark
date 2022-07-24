package com.test.pexapark;

import com.test.pexapark.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomePageTest {

    @Autowired
    public HomePage homePage;

    @BeforeEach
    void openWebsite(){
        homePage.get();
    }

    @Test
    void should_display_homepage() {
        assertEquals(homePage.getTitleText(), "Home");
    }
}
