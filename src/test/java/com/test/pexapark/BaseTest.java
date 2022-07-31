package com.test.pexapark;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Pexapark.class)
public class BaseTest {

    @Autowired
    protected WebDriver driver;

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
