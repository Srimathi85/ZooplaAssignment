package com.zoopla.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.zoopla.webdrivers.WebDriManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class SeleniumUtility extends TestBase{
    public static WebDriver driver;
    WebDriManager oWebDriManager=new WebDriManager();

    public static ExtentReports extentReports;
    public  static ExtentHtmlReporter htmlReport;
    public  static ExtentTest extentTest=null;
    static {
        String addDate=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        extentReports=new ExtentReports();
        htmlReport=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\Zoopla_Reports"+addDate+".html");
        extentReports.attachReporter(htmlReport);

    }
    public void createReport(String  sTestName){
        extentTest= extentReports.createTest(sTestName);
    }
    public void closeReport(){
        extentReports.flush();
    }
    public WebDriver launchApp(){
        oWebDriManager.launchApp(sBrowser);
        return oWebDriManager.getWebDriver();
    }
    public  void goToWebPage(String webURL){
        driverWeb=oWebDriManager.getWebDriver();
        System.out.println("-------"+driverWeb);
        driverWeb.get(webURL);
        log.info("Zoopla   is launched");
    }
    public   void closeBrowser(){
        driverWeb.close();
        extentTest.info("Browser is closed");
        log.info("Zoopla  is closed");
    }

    public void launchBrowser(String webUrl) throws IOException {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get(webUrl);
        extentTest.info(webUrl+"Browser is launched");
        // verifyPage(driver.getTitle(),readPropertiesFile("Page_Title","login.title"),"SalesForce Login");

    }
    public boolean isDisplayed(WebElement element, String sElementName){

        return element.isDisplayed();
    }
    public void quitApp(){
        oWebDriManager.quitApp();
    }
    public void waitForElementVisible(WebElement webElement, int iSeconds, WebDriver driver){
        try {
            WebDriverWait eWait = new WebDriverWait(driver, iSeconds);
            eWait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
        }
    }
    public void waitForElementVisible(List<WebElement> webElement, int iSeconds, WebDriver driver){
        try {
            WebDriverWait eWait = new WebDriverWait(driver, iSeconds);
            eWait.until(ExpectedConditions.visibilityOf(webElement.get(0)));
        } catch (Exception e) {
        }
    }
    public void sendInput(WebElement webElement,String textToEnter,String keyToEnter,String elementName) throws IOException {
        webElement.sendKeys(textToEnter, Keys.ENTER);
            extentTest.info(elementName+"  is entered");

    }
    public  void clickOnElement(WebElement webElement,String elementName){
        webElement.click();
        extentTest.info(elementName+"  is clicked ");
    }
    public  void verifyDropDown(WebElement element,String expectedDropdown,String  dropdownName) throws IOException {
        int count=0;
        String[] exDropDownArray=expectedDropdown.split(",");
        for (int index = 0; index < exDropDownArray.length; index++) {
            if (element.getText().contains(exDropDownArray[index])) {
                count++;
            } else
                break;
        }
        // Assert.assertEquals(count,exDropDownArray.length);
        if(count== exDropDownArray.length){
            extentTest.pass(dropdownName + " is displayed");

        }else{
            extentTest.fail(dropdownName + " is  not displayed");}
        extentTest.addScreenCaptureFromPath(addScreenshot());
    }
    public boolean verifyStrings(String actualText,String expectedText,String elementName) throws IOException {

        if(!actualText.equalsIgnoreCase(expectedText)){
            extentTest.fail(elementName+" is not displayed");
            extentTest.addScreenCaptureFromPath(addScreenshot());
            log.info(elementName+" is not displayed");
            return false;
        }
        else {extentTest.pass(elementName+" is  displayed");
            log.info(elementName+" is displayed");
            return true;
        }
    }
    public  void verifySelectedItemInDropDown(WebElement element,String targetItem,String message){
        Select select = new Select(element);
        WebElement option = select.getFirstSelectedOption();
        System.out.println("Selected option : "+option.getText());
        //Assert.assertEquals(option.getText(),targetItem);
        if(option.getText().equalsIgnoreCase(targetItem)){
            extentTest.pass(message + " is selected");

        }else
            extentTest.fail(message + " is selected");
    }
    public void selectOptionInDropDown(WebElement dropdownLocator,String sOption){
        Select dropdown=new Select(dropdownLocator);
        dropdown.selectByVisibleText(sOption);
        extentTest.info(sOption+"  is selected in DropDown");
    }
    public boolean validateCheckBox(WebElement webElement,String elementName) throws IOException {
        if(!webElement.isSelected()){
            extentTest.fail(elementName+" is not selected");
            extentTest.addScreenCaptureFromPath(addScreenshot());
            return false;
        }
        else {
            extentTest.pass(elementName+" is selected");

            return true;
        }
    }
    public  String addScreenshot() throws IOException {

        TakesScreenshot screenshot = (TakesScreenshot) driverWeb;
        String addDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String destPath = System.getProperty("user.dir") + "\\Reports\\Screenshots\\" + addDate + ".PNG";
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(destPath);
        FileUtils.copyFile(sourceFile, destFile);
        extentTest.info("Screenshot is captured ");
        return destPath;
    }
    public String switchWindow(String sCurrentWindowID, Set<String > windowsIDSet){
        String sNewWindowID="";
        for (String sWindowID:windowsIDSet) {
            if(!sWindowID.equals(sCurrentWindowID)){
                sNewWindowID=sWindowID;
            }
        }return sNewWindowID;
    }
    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
    public void displayListElementsValue(List<WebElement> oElementsList,String sMessage){
        System.out.println("Elements value in the list  : ");
        for (WebElement element:oElementsList) {
            System.out.println(element.getText());
        }
        extentTest.info(sMessage +" are displayed");
    }
    public WebElement selectElementInList(List<WebElement> oElementsList,int iIndex,String sMessage){
         return oElementsList.get(iIndex);
    }
    public String getElementValueInList(WebElement oWebElement){
        return oWebElement.getText();
    }
}
