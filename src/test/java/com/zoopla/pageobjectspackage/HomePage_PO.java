package com.zoopla.pageobjectspackage;

import com.zoopla.utility.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class HomePage_PO extends TestBase {
    WebDriver driver;

    public HomePage_PO(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver,this);

    }
    @FindBy(className = "ui-button-secondary")
    WebElement preferenceButton;
    @FindBy(id = "header-location")
    WebElement inputSearch;
    @FindBy(xpath = "//button[contains(text(),'Search')]")
    WebElement search_button;
    public void enterSearchLocation(String sLocation) throws IOException {
oSelUtility.clickOnElement(preferenceButton,"Preference Button");
oSelUtility.sendInput(inputSearch,sLocation,"ENTER",sLocation);

    }
    public PropertyListingPage_PO clickOnSearchButton(){
        oSelUtility.clickOnElement(search_button,"Search Button");
        return new PropertyListingPage_PO(driver);
    }
}
