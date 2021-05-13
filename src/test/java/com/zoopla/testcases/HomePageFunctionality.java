package com.zoopla.testcases;

import com.zoopla.TestDataProvider.Dataprovider;
import com.zoopla.pageobjectspackage.AgentDetailsPage_PO;
import com.zoopla.pageobjectspackage.HomePage_PO;
import com.zoopla.pageobjectspackage.PropertyDetailsPage_PO;
import com.zoopla.pageobjectspackage.PropertyListingPage_PO;
import com.zoopla.utility.CustomListener;
import com.zoopla.utility.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
@Listeners(CustomListener.class)
public class HomePageFunctionality extends TestBase {

    HomePage_PO oPOHomePage;
    PropertyDetailsPage_PO oPropertyDetailsPage;
    PropertyListingPage_PO oPropertyListingPage;
    AgentDetailsPage_PO oAgentPage;
    Logger log=Logger.getLogger(getClass().getSimpleName());
    @BeforeMethod
    public void launchBrowserApplication(Method testName) throws IOException {
        driverWeb=oSelUtility.launchApp();
       oPOHomePage=new HomePage_PO(driverWeb);

        oSelUtility.goToWebPage(oComUtility.readPropertiesFile("pageTitles","zooplaWebPageUrl"));
        System.out.println("Driver****"+driverWeb.getTitle());
        log.info(driverWeb);
        log.info("App is launching");
        oSelUtility.createReport(testName.getName());
    }
    @AfterMethod
    /*public void closeBrowserApplication(){
        oSelUtility.closeBrowser();
    }*/
    @AfterTest
    public  void closeReport(){
        oSelUtility.closeReport();
    }
    @Test(dataProvider = "location_data",dataProviderClass = Dataprovider.class)
    public void getPropertyDetails_Testcase1(String sLocation) throws Exception {
       oPOHomePage.enterSearchLocation(sLocation);
        Assert.assertEquals(oSelUtility.getPageTitle(driverWeb),oComUtility.readPropertiesFile("pageTitles","propertyListingPage"));
        oPropertyListingPage=oPOHomePage.clickOnSearchButton();

    oPropertyDetailsPage=oPropertyListingPage.sortPropertyDetails();
oAgentPage=oPropertyDetailsPage.getAgentDetails();

    }
}
