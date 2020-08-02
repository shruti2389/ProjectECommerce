package com.test.utilities;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class BrowserPropertiesProvider {

    private static final Logger LOG = Logger.getLogger(BrowserPropertiesProvider.class);
    Properties theProperties;
    public BrowserPropertiesProvider() {

        File configFile =
                new File(getClass().getResource("/config/browser.properties").getFile());
        try {
            LOG.debug("== Loading the config file :: Begin  ==");
            FileInputStream fis = new FileInputStream(configFile);
            theProperties = new Properties();
            theProperties.load(fis);
            LOG.debug("== Loading the config file :: End  ==");
            LOG.debug("== The properties:  ==");
            LOG.debug("   FirefoxDriver: "+ getFirefoxDriver());
            LOG.debug("   FirefoxDriverLocation: "+ getFirefoxDriverLocation());
            LOG.debug("   ChromeDriver: "+ getChromeDriver());
            LOG.debug("   ChromeDriverLocation: "+ getChromeDriverLocation());
        } catch (Exception e) {
            LOG.debug("Not able to access config data file");
        }


    }

    public String getFirefoxDriver() {
        return theProperties.getProperty("firefoxDriver");
    }
    public String getFirefoxDriverLocation() {
        return theProperties.getProperty("firefoxDriverLocation");
    }
    public String getChromeDriver() {
        return theProperties.getProperty("chromeDriver");
    }
    public String getChromeDriverLocation() {
        return theProperties.getProperty("chromeDriverLocation");
    }
}