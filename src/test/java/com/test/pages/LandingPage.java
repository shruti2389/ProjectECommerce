package com.test.pages;

import com.test.utilities.BrowserFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LandingPage extends BasePage {

    private Logger LOG = Logger.getLogger(LandingPage.class);

    private By loginMainButtonBy = By.tagName("button");
    private By loginModalBody = By.className("modal-body");
    private By userNameBy = By.id("email");
    private By passwordBy = By.id("password");
    private By loginContinueButtonBy = By.id("login-continue");
    private By loginModalButtonBy = By.id("login");
    private By zipCodeBar = By.className("zipcode-enter");
    private By chatNameBy = By.id("prechat0Field");
//    private By chatNameBy = By.id("offline0Field");
    private By chatEmailBy = By.id("prechat1Field");
//private By chatEmailBy = By.id("offline1Field");
    private By chatPhoneBy = By.id("prechat2Field");
    private By chatButtonBy = By.id("tawkchat-minified-box");
    private By chatBoxBy = By.id("tawkchat-maximized-wrapper");
    private By twitterLogo=By.xpath("//a[@href ='"+configData.getTwitterURL()+"']");
    private By myAccountBy = By.className("my-account-dropdown");
    private By findButtonBy = By.className("homepage-start-shopping");

    private static final String CLICK_OPEN = "open";
    private static final String CLICK_CLOSE = "close";

    public LandingPage() {
        super();
        theWebDriver =
                BrowserFactory.startApplication(theWebDriver, configData.getBrowser());
        theWebDriver.get(configData.getURL());
        wait = new WebDriverWait(theWebDriver,30);
        wait.until(ExpectedConditions.presenceOfElementLocated(findButtonBy));
    }

    public void clickMainLogin() {
        theWebElement = theWebDriver.findElement(loginMainButtonBy);
        theWebElement.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(loginModalBody));
    }

    public void enterEmail(String aUserName) {
        wait.until(ExpectedConditions.elementToBeClickable(userNameBy));
        theWebElement = theWebDriver.findElement(userNameBy);
        theWebElement.sendKeys(aUserName);
    }

    public void enterPassword(String password) {

        theWebElement = theWebDriver.findElement(passwordBy);
        theWebElement.sendKeys(configData.getPassword());
    }

    public void clickLoginContinue() {
        theWebElement = theWebDriver.findElement(loginContinueButtonBy);
//        LOG.debug("The element: "+theWebElement.getText());
        theWebElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(passwordBy));
    }

    public void clickLoginAfterCredentials() {
        theWebElement = theWebDriver.findElement(loginModalButtonBy);
        theWebElement.click();

    }
    public boolean isMyAccountPresent(){

        wait.until(ExpectedConditions.elementToBeClickable(myAccountBy));
        takeScreenShot("MyAccount");
        theWebDriver.close();
        return true;
    }


    public void enterZipCode(String zipCode) {
        theWebElement = theWebDriver.findElement(zipCodeBar);
        theWebElement.sendKeys(configData.getZipCode());
        LOG.debug("The zipcode is entered");
    }

    public void clickFind() {
        theWebElement = theWebDriver.findElement(findButtonBy);
        theWebElement.click();
        LOG.debug("Find button is clicked");
        wait.until(ExpectedConditions.presenceOfElementLocated(loginModalBody));
    }

    public void openChatBox() {
        // make sure we are on main page
       theWebDriver.switchTo().defaultContent();
       clickChatBox(CLICK_OPEN);
    }

    public void closeChatBox() {
        // make sure we are on main page
        theWebDriver.switchTo().defaultContent();
        clickChatBox(CLICK_CLOSE);
        theWebDriver.close();
    }

    private void clickChatBox(String operation){
        LOG.debug("===  Inside the click chat box method");
        List<WebElement> iFrames = theWebDriver.findElements(iFrameBy);
        Iterator<WebElement> iterator = iFrames.iterator();
        LOG.debug("total frames: " + iFrames.size());
        boolean clicked=false;
        while (iterator.hasNext()){
            if(!clicked) {
                theWebElement = getNeverStaleIframe(iterator.next(), theWebDriver);
                if (theWebElement.isDisplayed()) {
                    String iFrameId = theWebElement.getAttribute(attributeId);
                    LOG.debug("iFrame displayed: " + iFrameId);
                    theWebDriver.switchTo().frame(theWebElement);
                    LOG.debug("iFrame switched: " + iFrameId);
                    if (theWebDriver.findElements(chatButtonBy).size() == 1) {
                        switch (operation) {
                            case CLICK_CLOSE:
                                if (theWebDriver.findElement(chatButtonBy)
                                        .getAttribute(attributeClass).contains(CLICK_OPEN)) {
                                    theWebDriver.findElement(chatButtonBy).click();
                                    LOG.debug("clicked: "+iFrameId);
                                    clicked = true;
                                }
                                break;

                            case CLICK_OPEN:
                                if (!theWebDriver.findElement(chatButtonBy)
                                        .getAttribute(attributeClass).contains(CLICK_OPEN)) {
                                    theWebDriver.findElement(chatButtonBy).click();
                                    LOG.debug("clicked: "+iFrameId);
                                    clicked = true;
                                }
                                break;
                        }
                    }
                }
            }else{
                break;
            }
        }
        theWebDriver.switchTo().defaultContent();
    }

    private WebElement getChatBox(){
        LOG.debug("===  Inside the get chat box method");
        List<WebElement> iFrames = theWebDriver.findElements(iFrameBy);
        Iterator<WebElement> iterator = iFrames.iterator();
        LOG.debug("total frames: " + iFrames.size());

        while (iterator.hasNext()){

            theWebElement = getNeverStaleIframe(iterator.next(), theWebDriver);
            if (theWebElement.isDisplayed()) {
                String iFrameId = theWebElement.getAttribute(attributeId);
                LOG.debug("iFrame displayed: " + iFrameId);
                theWebDriver.switchTo().frame(theWebElement);
                LOG.debug("iFrame switched: " + iFrameId);
                if (theWebDriver.findElements(chatBoxBy).size() == 1) {
                    theWebElement = theWebDriver.findElement(chatBoxBy);
                    break;
                }
            }
        }
        return theWebElement;
    }

    public void enterCredsInChatBox(){
        theWebElement = getChatBox();
        enterNameInChatBox(theWebElement, configData.getName());
        enterEmailInChatBox(theWebElement, configData.getEmail());
        takeScreenShot("ChatBox");
//        enterPhoneNumberInChatBox(theWebElement, configData.getNumber());
    }
    private void enterNameInChatBox(WebElement chatBox, String name) {
        WebElement nameTxtfield = chatBox.findElement(chatNameBy);
        nameTxtfield.sendKeys(name);
        LOG.debug("The User entered" + name);
    }
    private void enterEmailInChatBox(WebElement chatBox, String email) {
        WebElement phoneNumberTxtfield = chatBox.findElement(chatEmailBy);
        phoneNumberTxtfield.sendKeys(email);
        LOG.debug("The User entered" + email);
    }
//    private void enterPhoneNumberInChatBox(WebElement chatBox, String phoneNumber) {
//        WebElement phoneNumberTxtfield = chatBox.findElement(chatPhoneBy);
//        phoneNumberTxtfield.sendKeys(phoneNumber);
//        LOG.debug("The User entered" + phoneNumber);
//    }

    public void scrollDownLandingPage(){
        scrollDown();
    }

    public void clickTwitterLogo() throws InterruptedException {
        WebElement twitterLogoElement=theWebDriver.findElement(twitterLogo);
        twitterLogoElement.click();
        LOG.debug("Twitter logo clicked");

        theWebDriver.getWindowHandle();
        LOG.debug(theWebDriver.getTitle());
        Set<String> windowIds = theWebDriver.getWindowHandles();
        LOG.debug("The size: "+windowIds.size());
        for (String handle : windowIds){
            LOG.debug("The window is: "+handle);
            theWebDriver.getWindowHandles();
            theWebDriver.switchTo().window(handle);
            Thread.sleep(1200);
            LOG.debug("  ::The current window is:: "+theWebDriver.getTitle());
            String currentUrl=theWebDriver.getCurrentUrl();
            LOG.debug(currentUrl);

           }
        }

    public void validateNewUrl() throws InterruptedException {
        String currentUrl=theWebDriver.getCurrentUrl();
        LOG.debug(currentUrl);
        takeScreenShot("Twitter");
        Assert.assertEquals(currentUrl,configData.getTwitterURL());
        closeBrowser();
    }
}


