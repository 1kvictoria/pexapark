package com.test.pexapark.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.WebElement;

@Data
@AllArgsConstructor
public class AssetsTableRow {

    private String name;

    private String capacityFactor;

    private WebElement editButton;

    private WebElement deleteButton;
}
