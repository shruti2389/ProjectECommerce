package com.test.utilities;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

    private static final Logger LOG = Logger.getLogger(ConfigDataProvider.class);
    private Properties theProperties;

    public ConfigDataProvider() {

        File configFile =
                new File(getClass().getResource("/config/config.properties").getFile());
        try {
            LOG.debug("== Loading the config file :: Begin  ==");
            FileInputStream fis = new FileInputStream(configFile);
            theProperties = new Properties();
            theProperties.load(fis);
            LOG.debug("== Loading the config file :: End  ==");
            LOG.debug("== The properties:  ==");
            LOG.debug("   URL: "+ getURL());
            LOG.debug("   Browser: "+ getBrowser());
        } catch (Exception e) {
            LOG.debug("Not able to access config data file");
        }


    }

//    public String getData() {
//    }

    public String getURL() {
        return theProperties.getProperty("testURL");
    }

    public String getBrowser() {
        return theProperties.getProperty("Browser");
    }
    public String getEmail(){
        return theProperties.getProperty("EmailId");
    }

    public String getName() {
        return theProperties.getProperty("Name");
    }

    public String getNumber() {

        LOG.debug("== The phone number is filled :: End  ==");
        return theProperties.getProperty("PhoneNumber");
    }

    public String getZipCode() {
        LOG.debug("== The zip code is inserted ::   ==" );
        return theProperties.getProperty("Zipcode"); }

    public String getPassword() {
        return theProperties.getProperty("Password");
    }

    public String getTwitterURL() {
        LOG.debug("== Loading the twitter window :: End  ==");
        return theProperties.getProperty("twitterURL");

    }

    public String getStreetAddress() {
        LOG.debug("== Loading the street address :: End  ==");
        return theProperties.getProperty("StreetAddress");

    }


    public String getApartmentAddress() {
        LOG.debug("== Loading the apartment address :: End  ==");
        return theProperties.getProperty("Apartment");

    }

    public String getDeliveryInstructions(){
        LOG.debug("== Loading the Delivery Instructions :: End  ==");
        return theProperties.getProperty("Apartment");
    }

    public String getCheckoutURL(){
        LOG.debug("== The user is about to make payment :: End  ==");
        return theProperties.getProperty("CheckoutURL");
    }
    public String getStoreName(){
        LOG.debug("== Getting the store name: "+theProperties.getProperty("StoreName"));
        return theProperties.getProperty("StoreName");
    }
    public String getSearchItem(){
        return theProperties.getProperty("SearchItem");
    }

}