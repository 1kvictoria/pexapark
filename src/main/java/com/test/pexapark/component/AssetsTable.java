package com.test.pexapark.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AssetsTable {

    public static final Integer COLUMN_NAME = 1;
    public static final Integer COLUMN_CAPACITY_FACTOR = 2;
    public static final Integer COLUMN_EDIT = 3;
    public static final Integer COLUMN_DELETE = 3;

    private final WebDriver driver;

    private final String rowSelector = "body > main > div > table > tbody:nth-child(2) > tr:nth-child(%s) > td:nth-child(%s)";

    public AssetsTable(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getCellAt(int row, int column) {
        String cssQuery = String.format(rowSelector, row, column);
        return driver.findElement(By.cssSelector(cssQuery));
    }

    public String getNameAt(int row) {
        return getCellAt(row, COLUMN_NAME)
                .getText();
    }

    public String getCapacityFactorAt(int row) {
        return getCellAt(row, COLUMN_NAME)
                .getText();
    }

    public void editRow(int row) {
        getCellAt(row, COLUMN_EDIT)
                .findElement(By.cssSelector("form > button"))
                .click();
    }

    public void deleteRow(int row) {
        getCellAt(row, COLUMN_DELETE)
                .findElement(By.cssSelector("form > button"))
                .click();
    }
}
