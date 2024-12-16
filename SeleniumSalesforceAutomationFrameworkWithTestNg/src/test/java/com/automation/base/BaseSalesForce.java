package com.automation.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.utility.Constants;
import com.automation.utility.PropertiesUtility;

public class BaseSalesForce extends BaseTest {
	private Logger mylog = LogManager.getLogger(BaseSalesForce.class);
	// public static WebDriver driver = null;
		//public FluentWait<WebDriver> fluentwait;
		//public WebDriverWait wait;
		
	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeMethod(@Optional("chrome") String name) {
		mylog.info("*********************setUpBeforeMethod**************************");
		launchBrowser("chrome");
		String url=PropertiesUtility.readDataFromPropertyFile(Constants.SALESFORCELOGIN_PROPERTIES,"url");
		try {
			goToUrl(url);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@AfterMethod
	public void tearDownAfterMethod() {
		closeDriver();
		mylog.info("****************tearDownAfterTestMethod*******************");
	}
	
	 // Method to login
    public void login_Salesforce() {
    	String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.SALESFORCELOGIN_PROPERTIES,"username");
		String passwordData=PropertiesUtility.readDataFromPropertyFile(Constants.SALESFORCELOGIN_PROPERTIES,"password");
    	
        WebElement usernameEle = driver.findElement(By.id("username"));
        usernameEle.sendKeys("hemapriya@test.sandbox");  
        
        WebElement passwordEle = driver.findElement(By.name("pw"));
        enterText(passwordEle,passwordData, "password");
        
        WebElement buttonEle = driver.findElement(By.id("Login"));
        clickElement( buttonEle,"login button");
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public boolean isAlertPresent() {
	    try {
	        driver.switchTo().alert();
	        return true;
	    } catch (NoAlertPresentException e) {
	        return false;
	    }
	}
    
    public void logout() {
        try {
          
            WebElement profileIcon = driver.findElement(By.id("userNavButton"));
            profileIcon.click();

            WebElement logoutOption = driver.findElement(By.linkText("Logout"));
            logoutOption.click();

            // verification here to ensure logout was successful
            mylog.info("Logout successful");
        } catch (Exception e) {
            mylog.error("Error during logout: " + e.getMessage());
        }
    }
    
    public void sendKeysToElement(WebElement element, String text, String elementName) {
	    try {
	        if (element.isDisplayed() && element.isEnabled()) {
	            element.clear();
	            element.sendKeys(text);
	            System.out.println("Text '" + text + "' entered successfully in " + elementName);
	        } else {
	            System.out.println(elementName + " is not visible or enabled.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error while entering text in " + elementName + ": " + e.getMessage());
	    }
	}
    
    
    public void customizeMyTabs() {
    	mylog.info("customizeMyTabs - Start");
        driver.findElement(By.linkText("Customize My Tabs")).click();
       
        WebElement appDropdown = driver.findElement(By.id("p4"));
        appDropdown.sendKeys("Salesforce Chatter");

        driver.findElement(By.xpath("//select[@id='duel_select_0']/option[text()='Reports']")).click();
        driver.findElement(By.id("duel_select_0_right")).click();
        mylog.info("Successfully Added Reports to Salesforce Chatter");
        mylog.info("customizeMyTabs - End");
    }
    
    public void setEmailSettings(String emailName, String emailAddress) throws InterruptedException {
    	WebElement emailSettings = driver.findElement(By.id("EmailSettings_font"));
    	clickElement(emailSettings, "emailSettings");
    
    	
        WebElement emailNameField = driver.findElement(By.id("sender_name"));
        emailNameField.clear();
        emailNameField.sendKeys(emailName);

        WebElement emailAddressField = driver.findElement(By.id("sender_email"));
        emailAddressField.clear();
        emailAddressField.sendKeys(emailAddress);

        WebElement automaticBccRadio = driver.findElement(By.id("auto_bcc1"));
        clickElement(automaticBccRadio, "automaticBccRadio");
       

        WebElement saveButton = driver.findElement(By.xpath("//td[@id='bottomButtonRow']/input[@value=' Save ' and @name='save']"));
        clickElement(saveButton, "saveButton");
     
        Thread.sleep(3000);
    }
    
    public void setUpBeforeMethod() {
        String url = PropertiesUtility.readDataFromPropertyFile(Constants.SALESFORCELOGIN_PROPERTIES, "url");
        if (url != null) {
            driver.get(url);
        } else {
            Assert.fail("URL not found in properties file");
        }
    }
 

}
