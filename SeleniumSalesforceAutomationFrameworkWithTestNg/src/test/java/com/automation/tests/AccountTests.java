package com.automation.tests;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.BaseSalesForce;
import com.automation.utility.ExtentReportsUtility;

public class AccountTests  extends BaseSalesForce{
private Logger mylog = LogManager.getLogger( AccountTests .class);
	//WebDriverWait wait;
	
	@BeforeMethod
	public void setUpTestLogger() {
		ExtentReportsUtility report = ExtentReportsUtility.getInstance();
		report.startExtentReport();
		report.startSingleTestReport("AccountTests");
	}
	
	
	@Test
	public void create_An_Account() {
		
		 login_Salesforce();
		
		 WebElement accountsLink = driver.findElement(By.id("Account_Tab"));
	     clickElement(accountsLink, "accountsLink");
	          		       		      
	      WebElement newButton = driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
		  clickElement(newButton, "newButton");
			        		       		        		       		      
		  WebElement accountName = driver.findElement(By.id("acc2")); 
		  enterText( accountName,"hema");

		  WebElement accountType = driver.findElement(By.id("acc6"));
		  selectElement(accountType   , "Technology Partner");

		  WebElement customerPriority = driver.findElement(By.id("acc6"));
		  selectElement(customerPriority  , "High");

	      WebElement saveButton = driver.findElement(By.name("save"));
	      clickElement(saveButton, "saveButton");
	      
	}
	
	@Test
	public void new_View() {
		
		login_Salesforce();
		
	 WebElement accountsLink = driver.findElement(By.id("Account_Tab"));
    clickElement(accountsLink, "accountsLink");
    
    WebElement newViewLink = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
    clickElement(newViewLink, "newViewLink");
    
    WebElement viewName = driver.findElement(By.id("fname"));
    enterText( viewName, "Hello");
    
    WebElement viewUniqueName = driver.findElement(By.id("devname"));
    enterText( viewUniqueName,"test");
    
    WebElement saveButton = driver.findElement(By.name("save"));
    clickElement( saveButton," saveButton");

 
 
 }
	@Test
	public void edit_View() {
		
	    login_Salesforce();

	    WebDriverWait wait = new WebDriverWait(driver, 30);
	    WebElement accountsLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Account_Tab")));
	    accountsLink.click();

	    WebElement viewDropDown = driver.findElement(By.id("fcf"));
	    selectElement(viewDropDown, "Hello");

	    WebElement editLink = driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[2]/form/div/span/span[2]/a[1]"));
	    clickElement(editLink, "editLink");

	    WebElement viewName = driver.findElement(By.id("fname"));
	    viewName.clear();
	    viewName.sendKeys("book");

	    WebElement filterField = driver.findElement(By.id("fcol1"));
	    selectElement(filterField, "Account Name");

	    WebElement operatorField = driver.findElement(By.id("fop1"));
	    selectElement(operatorField, "contains");

	    WebElement valueField = driver.findElement(By.id("fval1"));
	    valueField.clear();
	    valueField.sendKeys("a");

	    WebElement saveButton = driver.findElement(By.name("save"));
	    saveButton.click();

	    WebElement successMessage = driver.findElement(By.id("successMessageId"));
	    Assert.assertTrue(successMessage.isDisplayed(), "The view was not saved successfully!");
	}

	@Test
	public void merge_Accounts() {
	    login_Salesforce();

	    String expectedMergedAccountText = "Recently Viewed";

	    WebElement accountsLink = driver.findElement(By.id("Account_Tab"));
	    clickElement(accountsLink, "Accounts Link");

	    WebElement mergeAccountsLink = driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a"));
	    clickElement(mergeAccountsLink, "Merge Accounts Link");

	    WebElement accountNameField = driver.findElement(By.xpath("//*[@id=\"srch\"]"));
	    sendKeysToElement(accountNameField, "priya", "Account Name Field");

	    WebElement findAccountsButton = driver.findElement(By.name("srchbutton"));
	    clickElement(findAccountsButton, "Find Accounts Button");

	    mylog.info("Navigating to Merge Accounts page...");
	    mylog.info("Searching for accounts with name: priya");

	    List<WebElement> accountCheckboxes = driver.findElements(By.name("cid"));
	    System.out.println("Accounts checkboxes found: " + accountCheckboxes.size());
	  
	    if (accountCheckboxes.size() >= 2) {
	        accountCheckboxes.get(0).click();
	        accountCheckboxes.get(1).click();

	        WebElement nextButton = driver.findElement(By.name("goNext"));
	        clickElement(nextButton, "Next Button");

	        WebElement mergeButton = driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[2]"));
	        clickElement(mergeButton, "Merge Button");

	        if (isAlertPresent()) {
	            driver.switchTo().alert().accept();
	            mylog.info("Alert handled successfully.");
	        } else {
	        	mylog.info("No alert present after clicking the Merge button.");
	        }

		     WebDriverWait wait = new WebDriverWait(driver, 30);
	        try {
	            WebElement mergeConfirmationPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mergeConfirmationPopUp")));
	            mylog.info("Merge Confirmation Pop-Up appeared.");

	            String actualMergeConfirmationMessage = getTextFromElement(mergeConfirmationPopUp, "AccountMergePopUp");
	   	         Assert.assertTrue(actualMergeConfirmationMessage.contains("Accounts successfully merged"));
	   	         
	             WebElement recentlyViewedSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recentlyViewedAccounts")));
	            String actualRecentlyViewedText = getTextFromElement(recentlyViewedSection, "RecentlyViewedSection");
	            
	            Assert.assertTrue(actualRecentlyViewedText.contains(expectedMergedAccountText));

	        } catch (TimeoutException e) {
	        	mylog.error("Merge confirmation pop-up did not appear within the expected time.");
	            Assert.fail("Merge confirmation pop-up did not appear.");
	        }
	    }
	}
}


