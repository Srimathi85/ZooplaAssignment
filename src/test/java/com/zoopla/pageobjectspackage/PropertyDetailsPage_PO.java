package com.zoopla.pageobjectspackage;

import com.zoopla.utility.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PropertyDetailsPage_PO extends TestBase {
    WebDriver driver;

    public PropertyDetailsPage_PO(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver,this);

    }
@FindBy(xpath = "//img[contains(@class,'StyledImg-Image-AgentImage')]")
WebElement agentDetails_fifthProperty;
public AgentDetailsPage_PO getAgentDetails(){
    oSelUtility.clickOnElement(agentDetails_fifthProperty,"Agent Details");
    return new AgentDetailsPage_PO(driver);
}

}
