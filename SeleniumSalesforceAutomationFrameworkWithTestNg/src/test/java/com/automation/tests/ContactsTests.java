package com.automation.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.BaseSalesForce;
import com.automation.utility.ExtentReportsUtility;

public class ContactsTests extends  BaseSalesForce {
	private Logger mylog = LogManager.getLogger(ContactsTests .class);
	
	 @BeforeMethod
		public void setUpTestLogger() {
			ExtentReportsUtility report = ExtentReportsUtility.getInstance();
			report.startExtentReport();
			report.startSingleTestReport("LoginTests");
		}
	    
	@Test
	public void create_NewContact() {
		
		 
		 login_Salesforce();

	     WebElement contactTab = driver.findElement(By.id("Contact_Tab"));
	     clickElement(contactTab, "contactTab");
	     
	     WebElement newButton = driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
	     clickElement(newButton,"newButton");
	     
	     WebElement lastNameField = driver.findElement(By.id("name_lastcon2"));
	     enterText(lastNameField,  "xyz");

	     WebElement accountNameField = driver.findElement(By.id("con4"));
	     enterText(accountNameField, "ABCD");

	     WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[1]"));
	     clickElement(saveButton, "saveButton");

	     mylog.info("New contact has been created.");
	     
	}
	
	@Test
	public void create_NewView_Contact() {
		
		 
		 login_Salesforce();

	     WebElement contactTab = driver.findElement(By.id("Contact_Tab"));
	     clickElement(contactTab, "contactTab");
	     
	     WebElement newViweLink  = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
	     clickElement(newViweLink , "newViweLink");
	     
	     WebElement viewName = driver.findElement(By.id("fname"));
	     enterText(viewName,"bird");
	     
	     WebElement uniqueName = driver.findElement(By.id("devname"));
	     enterText(uniqueName,"dove");
	     
	     WebElement saveButton = driver.findElement(By.name("save"));
	     clickElement(saveButton, "saveButton");

	     mylog.info ("created view name is displayed in drop down page.");
	     
}
	@Test
	 public void check_Recently_Created_Contact() {
		 
		 login_Salesforce();
		 
	WebElement contactTab = driver.findElement(By.id("Contact_Tab"));
    clickElement(contactTab, "contactTab");

    WebElement recentContactdropDown = driver.findElement(By.id("hotlist_mode"));
    clickElement(recentContactdropDown, "recentContactdropDown");

    Select select = new Select(recentContactdropDown);
    select.selectByVisibleText("Recently Created"); 

    mylog.info ("Recently created contacts displayed.");
}
	@Test
	public void myContacts() {

		 login_Salesforce();

		  WebElement contactTab = driver.findElement(By.id("Contact_Tab"));
		  clickElement(contactTab, "contactTab");
		  
		  WebElement viewDropDown = driver.findElement(By.id("fcf"));
		  clickElement(viewDropDown, "viewDropDown");
		  
		 // WebElement myContacts = driver.findElement(By.xpath("//*[@id=\"fcf\"]/option[5]"));
		 // selectElement( myContacts," myContacts");
		  
		  Select dropdown = new Select(viewDropDown);
		  dropdown.selectByVisibleText("My Contacts");
		  
		  mylog.info ("my contacts view displayed");
}
	
	@Test
	 public void view_ContactName() {

		 login_Salesforce();
		 
		  WebElement contactTab = driver.findElement(By.id("Contact_Tab"));
		  clickElement(contactTab, "contactTab");

		  WebElement contactName = driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr[1]/th[1]"));
		  clickElement(contactName,"contactName");
}
   @Test
   public void check_Error_In_Contact() {
		
	 login_Salesforce();

		  WebElement contactTab = driver.findElement(By.id("Contact_Tab"));
		  clickElement(contactTab, "contactTab");
		  
		  WebElement creatNewView = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		  clickElement(creatNewView,"creatNewView");
		  
		  WebElement uniqueName = driver.findElement(By.id("devname"));
		  enterText(uniqueName,"EFGH");
		  
		  WebElement saveButton = driver.findElement(By.name("save"));
		  clickElement(saveButton,"saveButton");
}
   @Test
   public void cancel_ButtonOperations() {
		
	   login_Salesforce();
		
		  WebElement contactTab = driver.findElement(By.id("Contact_Tab"));
		  clickElement(contactTab, "contactTab");
		  
		  WebElement creatNewView = driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		  clickElement(creatNewView,"creatNewView");
		  
		  WebElement viewName = driver.findElement(By.id("fname"));
		  enterText(viewName,"ABCD");
		  
		  WebElement uniqueName = driver.findElement(By.id("devname"));
		  enterText(uniqueName,"EFGH");
		  
		  WebElement cancelButton = driver.findElement(By.name("cancel"));
		  clickElement(cancelButton,"cancelButton");
		  if("view button is not created" != null ) {
			  mylog.info ("testcase passed");
		  
	}   
		  
	}  
   @Test
   public void checkNewAndSaveButton() {
		
	   login_Salesforce();
	   
	 	  WebElement contactTab = driver.findElement(By.id("Contact_Tab"));
	 	  clickElement(contactTab, "contactTab");
	 	  
	 	  WebElement newButton = driver.findElement(By.name("new"));
	 	  clickElement(newButton,"newButton");
	 	  
	 	  WebElement lastName = driver.findElement(By.id("name_lastcon2"));
	 	  enterText(lastName,"Indian");
	 	  
	 	  WebElement accountName = driver.findElement(By.id("con4"));
	 	  enterText(accountName,"GlobalMedia");
	 	  
	 	  WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[1]"));
	 	  clickElement(saveButton,"saveButton");
	 	  
	 	  String pageTitle = driver.getTitle();
	 	  if (pageTitle.contains("Contact")) {
	 		 mylog.info ("Contact created successfully!");
	       } else {
	    	   mylog.info ("Contact creation failed.");
	       }
	 	  
}
}

