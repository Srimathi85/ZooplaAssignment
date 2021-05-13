package com.zoopla.pageobjectspackage;

import com.zoopla.utility.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PropertyListingPage_PO extends TestBase {
    WebDriver driver;

    public PropertyListingPage_PO(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver,this);
        System.out.println("driver--------"+driver);
    }
    @FindBy(id = "sort-order-dropdown")
   // @FindBy(xpath = "//select[@name='results_sort']")
    WebElement dropdown;
    @FindBy(xpath = "//div[@class='css-1e28vvi-PriceContainer e2uk8e8']")
    List<WebElement> property_price;
    @FindBy(xpath = "//a[@data-testid='listing-details-link']")
    List<WebElement> propertyDetails;


    public PropertyDetailsPage_PO sortPropertyDetails(){
        oSelUtility.waitForElementVisible(dropdown, 5,driver);
        //oSelUtility.selectOptionInDropDown(dropdown,"Highest price");
        Select options=new Select(dropdown);
        options.selectByVisibleText("Highest price");

        oSelUtility.waitForElementVisible(property_price, 5,driver);
oSelUtility.displayListElementsValue(property_price,"Property price List");
        System.out.println("Fifth property details:"+oSelUtility.getElementValueInList(oSelUtility.selectElementInList(propertyDetails,4," The Fifth property ")));
        oSelUtility.clickOnElement(oSelUtility.selectElementInList(propertyDetails,4,"The Fifth property "),"The Fifth property");
   return new PropertyDetailsPage_PO(driver);
    }

}
