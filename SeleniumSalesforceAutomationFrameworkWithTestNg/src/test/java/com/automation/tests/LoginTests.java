package com.automation.tests;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.BaseSalesForce;
import com.automation.utility.Constants;
import com.automation.utility.ExtentReportsUtility;
import com.automation.utility.PropertiesUtility;

public class LoginTests extends  BaseSalesForce {
	private Logger mylog = LogManager.getLogger(LoginTests .class);
	
    private WebDriverWait wait;
   
  
    @BeforeMethod
	public void setUpTestLogger() {
		ExtentReportsUtility report = ExtentReportsUtility.getInstance();
		report.startExtentReport();
		report.startSingleTestReport("LoginTests");
	}
    

    
	@Test
	public void login_Error_Message() throws InterruptedException {
    	
		String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.SALESFORCELOGIN_PROPERTIES, "username");

    	 WebElement usernameEle = driver.findElement(By.id("username"));
    	 enterText(usernameEle, usernameData, "username");
	 

	    WebElement passwordEle = driver.findElement(By.name("pw"));
	    passwordEle.clear();

	    WebElement buttonEle = driver.findElement(By.id("Login"));
	    buttonEle.click();
	    
	    WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
    }
	    
	   
@Test
public void login_Salesforce() {
    String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.SALESFORCELOGIN_PROPERTIES, "username");
    String passwordData = PropertiesUtility.readDataFromPropertyFile(Constants.SALESFORCELOGIN_PROPERTIES, "password");

    WebElement usernameEle = driver.findElement(By.id("username"));
    enterText(usernameEle, usernameData, "username"); // Pass username from property file

    WebElement passwordEle = driver.findElement(By.name("pw"));
    enterText(passwordEle, passwordData, "password"); // Pass password from property file

    WebElement buttonEle = driver.findElement(By.id("Login"));
    clickElement(buttonEle, "login");

 
}


@Test
public void Check_RememberMe() {
	
         try {
		 String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.SALESFORCELOGIN_PROPERTIES, "username");
		 String passwordData = PropertiesUtility.readDataFromPropertyFile(Constants.SALESFORCELOGIN_PROPERTIES, "password");

		 WebElement usernameEle = driver.findElement(By.id("username"));
		 enterText(usernameEle, usernameData, "username"); 
		 
		 WebElement passwordEle = driver.findElement(By.name("pw"));
		 enterText(passwordEle, passwordData, "password"); 
		 
        WebElement rememberMeCheckbox = driver.findElement(By.id("rememberUn"));
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }

        
        WebElement loginButton = driver.findElement(By.id("Login"));
        clickElement(loginButton,"loginButton");

        
        WebElement userMenu = driver.findElement(By.id("userNavButton"));
       clickElement(userMenu,"userMenu");

        // Wait and click on the logout link
        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        clickElement(logoutLink,"logoutLink");

       
       WebElement displayedUsername = driver.findElement(By.id("idcard-identity"));
        String rememberedUsername = displayedUsername.getText();

       mylog.info("Remembered Username: " + rememberedUsername);
       } catch (Exception e) {
       mylog.error("An error occurred during login test: ", e);
    
}
} 

@Test
public void Forgot_Password1() throws InterruptedException{
	
	 WebElement forgotPasswordLink = driver.findElement(By.id("forgot_password_link"));
	clickElement(forgotPasswordLink,"forgotPasswordLink");
	 
	 String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.SALESFORCELOGIN_PROPERTIES, "username");
	 
	 WebElement usernameEle = driver.findElement(By.id("username"));
	 enterText(usernameEle, usernameData, "username");
	  
    WebElement continueButton = driver.findElement(By.id("continue"));
    clickElement(continueButton,"continueButton.");
    
                
} 

@Test
public void Forgot_Password2() {
    String usernameData = PropertiesUtility.readDataFromPropertyFile(Constants.SALESFORCEERROR_PROPERTIES, "username");
    String passwordData = PropertiesUtility.readDataFromPropertyFile(Constants.SALESFORCEERROR_PROPERTIES, "password");

    WebElement usernameEle = driver.findElement(By.id("username"));
    enterText(usernameEle, usernameData, "username"); 

    WebElement passwordEle = driver.findElement(By.name("pw"));
    enterText(passwordEle, passwordData, "password"); 

    WebElement buttonEle = driver.findElement(By.id("Login"));
    clickElement(buttonEle, "login");

   
    WebElement errorMessage = driver.findElement(By.id("error")); 
    String actualErrorText = errorMessage.getText();
    String expectedErrorText = "Please check your username and password. If you still can't log in, contact your Salesforce administrator."; 
    assertEquals(expectedErrorText, actualErrorText, "Error message did not match expected value after login failure");
}




}
	



