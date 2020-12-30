package com.rawatJi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.XpiDriverService;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class A3_DoubleClick {

    WebDriver driver;
    FirefoxOptions opt;


    @BeforeSuite
    public void setup()
    {
        System.setProperty("webdriver.gecko.driver","/Users/pkrawat/Downloads/geckodriver");

        DesiredCapabilities cap= DesiredCapabilities.firefox();
        cap.setCapability("marionette", true); // RUN by keeping it true only..though..implicitly it is TRUE only.
        cap.setCapability("firefox_binary", "/Applications/Firefox Developer Edition.app/Contents/MacOS/firefox");

        FirefoxProfile currentProfile = new FirefoxProfile();
        currentProfile.setAcceptUntrustedCertificates(true);


        opt=new FirefoxOptions();
        opt.setProfile(currentProfile);
        opt.merge(cap);

    }
    @BeforeMethod
    public void prepareTest()
    {
        driver=new FirefoxDriver(opt);
    }


    @Test
    public void test() throws InterruptedException
    {








        //driver.get("http://blog.codinghorror.com/double-click-must-die/");

        driver.get("http://www.google.com");

        Thread.sleep(5000);

        Actions action = new Actions(driver);

        //Double click statement written there
        action.doubleClick(driver.findElement(By.xpath("//*[@id='gbqfba']"))).perform();


        WebElement a= driver.findElement(By.linkText("dd"));


    }

    @AfterTest
    public void tearDown()
    {

        driver.close();


    }






}