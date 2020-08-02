package com.test.utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BrowserFactory {

    private static final Logger LOG = Logger.getLogger(BrowserFactory.class);
    private static final BrowserPropertiesProvider props = new BrowserPropertiesProvider();
    private static final String FIREFOX_DRIVER_LOCATION = BrowserFactory.class.getResource(props.getFirefoxDriverLocation()).getFile();
    private static final String CHROME_DRIVER_LOCATION = BrowserFactory.class.getResource(props.getChromeDriverLocation()).getFile();
    private static final String BROWSER_FIREFOX = "Firefox";
    private static final String BROWSER_CHROME = "Chrome";
    private static final String BROWSER_EDGE = "Edge";

        public static WebDriver startApplication(WebDriver theWebDriver, String browserName)
        {

            if(browserName.equals(BROWSER_FIREFOX)){

                LOG.debug("firefox browser "+FIREFOX_DRIVER_LOCATION);
                System.setProperty(props.getFirefoxDriver(), FIREFOX_DRIVER_LOCATION);
                theWebDriver=new FirefoxDriver();
                LOG.debug("Opened the firefox browser");

            }
            else if (browserName.equals(BROWSER_CHROME)){
                System.setProperty(props.getChromeDriver(), CHROME_DRIVER_LOCATION);
                theWebDriver=new ChromeDriver();
            }

            else if(browserName.equals(BROWSER_EDGE)){}

            else {

            }

            theWebDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
            theWebDriver.manage().window().maximize();

            return theWebDriver;

    }
}
