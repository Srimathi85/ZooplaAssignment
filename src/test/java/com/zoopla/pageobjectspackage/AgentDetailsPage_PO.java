package com.zoopla.pageobjectspackage;

import com.zoopla.utility.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AgentDetailsPage_PO extends TestBase {
    WebDriver driver;

    public AgentDetailsPage_PO(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver,this);

    }

}
