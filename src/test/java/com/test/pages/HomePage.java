package com.test.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends BasePage {

    private Logger LOG = Logger.getLogger(HomePage.class);

    private By proceedToCheckoutBy=By.className("proceed-checkout-bottom");
    private By eachSuperDealBy = By.className("each-superdeals-banner");
    private By eachStoreHeaderBy = By.className("each-store-header");
    private By eachStoreBodyBy = By.className("each-store-body");
    private By eachProductBy = By.className("each-product");
    private By productViewBy = By.className("product-view");
    private By cartButtonBy = By.className("cart-new-category");
    private By addtoCartBy = By.className("sp-quantity");
    private By closeCartPopUpBy = By.className("product-popup-close");
    private By removeItemby = By.className("img-cross");

    public HomePage(BasePage theCallingPage){
        theWebDriver = theCallingPage.theWebDriver;
        wait = new WebDriverWait(theWebDriver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(eachSuperDealBy));
        takeScreenShot("HomePage");
    }

    public void visitStore(String storeName){
        List<WebElement> storeHeaders = theWebDriver.findElements(eachStoreHeaderBy);

        for (WebElement aWebElement : storeHeaders){
            LOG.debug("The text is: "+aWebElement.getText());
            if(aWebElement.getText().toLowerCase().contains(storeName.toLowerCase())){
                moveToElement(aWebElement);
                theWebElement = aWebElement;
                break;
            }
        }
        theWebElement.findElement(anchorBy).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(containerBy));
    }

    public void addFirstItemFromFirstStoreToCart(){

        List<WebElement> elementList = theWebDriver.findElements(eachStoreHeaderBy);
        // moving to the second store header
        elementList.iterator().next();
        moveToElement(elementList.iterator().next());
        elementList = theWebDriver.findElements(eachStoreBodyBy);
        elementList = elementList.iterator().next().findElements(eachProductBy);
        elementList.iterator().next().click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productViewBy));
        LOG.debug("The product view "+theWebDriver.findElements(productViewBy).size());
        theWebElement = theWebDriver.findElement(productViewBy);
        LOG.debug("The product :"+ theWebElement.getAttribute(attributeClass));
        LOG.debug("Anchors: "+theWebElement.findElements(By.className("sp-quantity")).size());
        theWebElement.findElement(addtoCartBy).click();
        theWebElement.findElement(closeCartPopUpBy).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(productViewBy));

    }

    public void clickOnCart(){
        moveToElementAndClick(theWebDriver.findElement(cartButtonBy));
        wait.until(ExpectedConditions.visibilityOfElementLocated(proceedToCheckoutBy));
    }
    public void removeItemFromCart(){
        moveToElementAndClick(theWebDriver.findElement(removeItemby));
        takeScreenShot("EmptyCart");
        theWebDriver.close();
    }

    public void proceedToCheckOut() {
        WebElement checkoutElement = theWebDriver.findElement(proceedToCheckoutBy);
        checkoutElement.click();
    }
    public void closePage() throws  InterruptedException{
        super.closeBrowser();
    }
}
