package com.test.pexapark.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AssetsTable {

    private final WebDriver driver;

    private final String rowsSelector = "body > main > div > table > tbody:nth-child(2) > tr";

    public AssetsTable(WebDriver driver) {
        this.driver = driver;
    }

    public Optional<AssetsTableRow> findRowByName(String name) {
        return getRows().stream()
                .filter(row -> row.getName().equals(name))
                .findFirst();
    }

    private List<AssetsTableRow> getRows() {
        List<WebElement> rows = driver.findElements(By.cssSelector(rowsSelector));
        return rows.stream()
                .map(this::mapToRow)
                .collect(Collectors.toList());
    }

    private AssetsTableRow mapToRow(WebElement rowWebElement) {
        String name = rowWebElement.findElement(By.cssSelector("td:nth-child(1)")).getText();
        String capacityFactor = rowWebElement.findElement(By.cssSelector("td:nth-child(2)")).getText();
        WebElement editButton = rowWebElement.findElement(By.cssSelector("td:nth-child(3)"));
        WebElement deleteButton = rowWebElement.findElement(By.cssSelector("td:nth-child(4)"));

        return new AssetsTableRow(name, capacityFactor, editButton, deleteButton);

    }
}
