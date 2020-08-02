package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StorePage extends BasePage{

    private By searchButtonBy = By.className("search-button");

    public StorePage(BasePage theCallingPage){
        theWebDriver = theCallingPage.theWebDriver;
        wait = new WebDriverWait(theWebDriver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(containerBy));
        takeScreenShot("StorePage");
    }

    public void searchItem(String itemName){
        getSearchButton().click();

    }
    public WebElement getSearchButton(){
        List<WebElement> searchElements = theWebDriver.findElements(searchButtonBy);
        for( WebElement aWebElement : searchElements){
            if(aWebElement.getTagName().equals(spanTag)){
                theWebElement = aWebElement;
                break;
            }
        }
        return theWebElement;
    }
}
