package com.rawatJi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class A1_W3_element_nullify_onceDOMchanges {

    WebDriver driver;
    DesiredCapabilities cap;



    @BeforeMethod
    public void setup()
    {



        cap = DesiredCapabilities.firefox();


        //cap.setCapability("firefox_binary", "/Applications/Firefox Developer Edition.app/Contents/MacOS/firefox");

        //The below statement is required when we use marionette TRUE..else legacy one will worl
        //DEFAULT capability is TRUE for marionette.
        //cap.setCapability("marionette", false);	//DEFAULT is true and marionette needs it true.

            System.setProperty("webdriver.gecko.driver","/Users/parveenrawat/Downloads/geckodriver");



    }


    @Test
    public void testMethod1() throws InterruptedException
    {

        try{
            //driver=new FirefoxDriver(cap); This way is deprecated.. use firfox options


            FirefoxOptions opt=new FirefoxOptions();
            opt.setBinary("/Applications/Firefox Developer Edition.app/Contents/MacOS/firefox");
            opt.merge(cap); //use merge method to merge desired cap object to firefox options.

            driver=new FirefoxDriver(opt);

            driver.get("http://www.w3schools.com/");

            Thread.sleep(5000);

            //use RELEATIVE xpath helper in chrome and chrompath to validate

            WebElement we=driver.findElement(By.xpath("//A[@href='/html/default.asp']"));


            System.out.println("###" +we.getTagName() );

            System.out.println("###" + 		we.getText() );


            //we.click();

		/*Once the DOM get changed by element..or even we go to some other page and come back..then
		this stored web element can not be used..we need to run this findby again to find that element
		*/


            driver.get("http://www.w3schools.com/");



            System.out.println("###" +" " );


            System.out.println("###" +we.getTagName() );

            System.out.println("###" + 		we.getText() );

        }
        finally{
            if(driver!=null)
                driver.quit();


        }



    }


}
