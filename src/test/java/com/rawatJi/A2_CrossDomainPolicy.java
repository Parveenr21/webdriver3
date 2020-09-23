package com.rawatJi;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;




public class A2_CrossDomainPolicy {

    WebDriver driver;

    @Test
    public void test() throws InterruptedException {

        /*
         * IF we have created a firefox profile say "webdriverProfile" to be
         * used while running automation then we can access or read that in code
         * by using following code
         *
         * ProfilesIni profile = new ProfilesIni();
         * FirefoxProfile myprofile =profile.getProfile("webdriverProfile");
         * //Settting a prefernce
         * myprofile.setAcceptUntrustedCertificates(true);
         * driver=new FirefoxDriver(myprofile);
         *
         *
         */

        // IMP!!!!!!! The below command will not create a new profile but it
        // will create an object of the current profile to
        // set the preferences so that we can run the test as per our need


        System.setProperty("webdriver.gecko.driver","/Users/parveenrawat/Downloads/geckodriver");

        DesiredCapabilities cap = DesiredCapabilities.firefox();
        FirefoxProfile objectOfCurrentProfile = new FirefoxProfile();

        FirefoxOptions opt=new FirefoxOptions();


        //Now firefox profile has to be set at firefox options
        opt.setProfile(objectOfCurrentProfile);
        opt.setAcceptInsecureCerts(true);//profile.setAcceptUntrustedCertificates(true); deprecated
        opt.setBinary("/Applications/Firefox Developer Edition.app/Contents/MacOS/firefox");


        /*
        INFO: Using `new FirefoxOptions()` is preferred to `DesiredCapabilities.firefox()`
         */


        opt.merge(cap);


        try {





            driver=new FirefoxDriver(opt);     // deprecated  driver = new FirefoxDriver(cap);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // ((RemoteWebDriver)driver)

            System.out.println(((RemoteWebDriver) driver).getCapabilities());

            // driver.get("http://blog.codinghorror.com/double-click-must-die/");

            driver.get("http://www.w3schools.com/");
            Actions acc= new Actions(driver);

            //acc.moveToElement(driver.findElement(By.xpath("//a[contains(.,'Categories')]"))).perform();
            acc.moveToElement(driver.findElement(By.xpath("//a[contains(.,'Categories')]"))).click().perform();


            //new Actions(driver).moveToElement(driver.findElement(By.xpath("//a[contains(.,'Categories')]"))).perform();

            driver.findElement(By.xpath("//a[contains(@href,'Grenade')]")).click();

            driver.findElement(By.xpath("//input[@name='txtnesletetr']")).clear();
            driver.findElement(By.xpath("//input[@name='txtnesletetr']")).sendKeys(Keys.getKeyFromUnicode('a'));



            driver.findElement(By.xpath("//input[@value='Subscribe']")).click();

            Alert alt = driver.switchTo().alert();

            alt.accept();

            // Thread.sleep..here timein milliseconds..
            // Thread.sleep(33333);

            // now it will click on TWITTTER image and redirected to twitter
            // domain
            // I was able to move to another domain easily

            // Webdriver has overcome the same origin policy issue

            driver.findElement(By.xpath("//img[@alt='twitter_icon']")).click();

            for (String currentWindow : driver.getWindowHandles()) {
                driver.switchTo().window(currentWindow);

                if (driver.getTitle().contains("Twitter"))
                    break;

            }

            driver.findElement(
                    By.xpath("//button[@class='user-actions-follow-button js-follow-btn follow-button btn']")).click();

            Thread.sleep(15000);
        }

        catch(Exception e)
        {

            e.printStackTrace();
        }

        finally {

            driver.quit();	//quit here as exception .. is there and close will not work to close it
        }

    }

    @AfterTest
    public void tearDown() {
        if (driver != null)
            driver.close();

        // driver.quit();

    }

}
