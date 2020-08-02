package com.test.pages;

import com.test.utilities.ConfigDataProvider;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Set;


public class BasePage {

    private final Logger LOG = Logger.getLogger(BasePage.class);
    protected WebDriver theWebDriver;
    protected WebElement theWebElement;
    protected FluentWait<WebDriver> wait;
    protected By anchorBy = By.tagName("a");
    protected By buttonBy = By.tagName("button");
    protected By containerBy = By.className("container");
    protected By spanBy = By.tagName("span");
    protected By disabledBy = By.className("disabled");
    protected String attributeClass = "class";
    protected String attributeId = "id";
    protected String spanTag = "span";
    protected By iFrameBy = By.tagName("iframe");

    public ConfigDataProvider configData=new ConfigDataProvider();

    protected TakesScreenshot takesScreenshot;
    private static final Random rnd = new Random();
    private static final int UPPER_BOUND = 10000000;


    public BasePage() {

    }

    public void scrollDown(){

        JavascriptExecutor js = (JavascriptExecutor) theWebDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }

    public static void closeBrowser(WebDriver thewebDriver){
        thewebDriver.quit();
    }

    protected WebElement getNeverStaleIframe(WebElement iFrame, WebDriver driver) {
        try {
            LOG.debug("iFrame: "+ iFrame.getAttribute("id"));
            return driver.findElement(By.id(iFrame.getAttribute("id")));
        } catch (StaleElementReferenceException e) {
            theWebDriver.switchTo().defaultContent();
            return getNeverStaleIframe(iFrame, driver);
        }
    }
    protected WebElement getNeverStaleButton(WebDriver driver) {
        try {
            return driver.findElement(buttonBy);
        } catch (StaleElementReferenceException e) {
            theWebDriver.switchTo().defaultContent();
            return getNeverStaleButton(driver);
        }
    }
    protected void takeScreenShot(String page){
        takesScreenshot = (TakesScreenshot) theWebDriver;
        try {
            FileUtils.copyFile(takesScreenshot.getScreenshotAs(OutputType.FILE)
                    ,new File("src/test/resources/screenshots/"+page+rnd.nextInt(UPPER_BOUND)+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void moveToElement(WebElement element){

        Actions actions = new Actions(theWebDriver);
        actions.moveToElement(element).perform();

    }
    protected void moveToElementAndClick(WebElement element){

        Actions actions = new Actions(theWebDriver);
        actions.moveToElement(element).click().perform();

    }
    protected void closeBrowser() throws InterruptedException{
        Set<String> windowIds = theWebDriver.getWindowHandles();
        LOG.debug("The size: "+windowIds.size());
        for (String handle : windowIds){
            theWebDriver.switchTo().window(handle);
            Thread.sleep(500);
            theWebDriver.close();
        }
    }
}
