package com.zoopla.testcases;

import com.zoopla.TestDataProvider.Dataprovider;
import com.zoopla.pageobjectspackage.HomePage_PO;
import com.zoopla.pageobjectspackage.PropertyListingPage_PO;
import com.zoopla.utility.TestBase;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class PropertyListingPageFunctionality extends TestBase {
    PropertyListingPage_PO oPOPropertyListingPage;
    Logger log=Logger.getLogger(getClass().getSimpleName());
    @BeforeMethod
    public void launchBrowserApplication(Method testName) throws IOException {
        driverWeb=oSelUtility.launchApp();
        oPOPropertyListingPage=new PropertyListingPage_PO(driverWeb);
        oSelUtility.goToWebPage(oComUtility.readPropertiesFile("pageTitles","zooplaWebPageUrl"));
        System.out.println("Driver****"+driverWeb.getTitle());
        log.info(driverWeb);
        log.info("App is launching");
        oSelUtility.createReport(testName.getName());
    }
    @AfterMethod
    public void closeBrowserApplication(){
        oSelUtility.closeBrowser();
    }
    @AfterTest
    public  void closeReport(){
        oSelUtility.closeReport();
    }
    @Test(dataProvider = "location_data",dataProviderClass = Dataprovider.class)
    public void getPropertyDetails_Testcase1(String sLocation) throws Exception {

    }
}
