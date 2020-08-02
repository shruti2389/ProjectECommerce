package com.test.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage extends BasePage {

    private Logger LOG = Logger.getLogger(CheckOutPage.class);

    private By nextStepOneBy = By.className("step1-next");
    private By nextStepTwoBy = By.className("step2-next");

    private By proceedContainerBy = By.className("total-amount-container");
    private By proceedToCheckoutBy = By.className("proceed-checkout-bottom");
    private By selectedAddressBy = By.className("address-block-selected");


    public CheckOutPage(BasePage theCallingPage) {
        theWebDriver = theCallingPage.theWebDriver;
        wait = new WebDriverWait(theWebDriver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(proceedToCheckoutBy));
        LOG.debug("    ::The Checkout page is open::    ");
    }

    public void clickProceedToCart() {
        for (int i=0;i<=5;i++)
            theWebDriver.findElement(By.className("mini-plus-quantity")).click();
        takeScreenShot("OrderPage");
        theWebElement = theWebDriver.findElement(proceedContainerBy);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(disabledBy));
        theWebElement.findElement(anchorBy).findElement(spanBy).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectedAddressBy));
        LOG.debug("== " + theWebDriver.findElement(buttonBy).getText());
    }

    public void clickNextToConfirmAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(nextStepOneBy));
        getNeverStaleButton(theWebDriver).click();

    }

    public void clickNextToConfirmDelivery() {
        wait.until(ExpectedConditions.elementToBeClickable(nextStepTwoBy));
        getNeverStaleButton(theWebDriver).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.className("pay-button")));
    }
    public void clickBrandLogo(){
        moveToElementAndClick(theWebDriver.findElement(By.className("brand-logo")));
    }

}
