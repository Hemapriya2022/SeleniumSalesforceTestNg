package com.automation.tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.BaseSalesForce;
import com.automation.utility.ExtentReportsUtility;

public class RandomScenarios extends BaseSalesForce{
	
private Logger mylog = LogManager.getLogger( RandomScenarios .class);
	//WebDriverWait wait;
	
	@BeforeMethod
	public void setUpTestLogger() {
		ExtentReportsUtility report = ExtentReportsUtility.getInstance();
		report.startExtentReport();
		report.startSingleTestReport("AccountTests");
	}
	
	
	    @Test
	    public void verifyTheNames() {
	       
	    	login_Salesforce();
	        
	        WebElement homeTab = driver.findElement(By.id("home_Tab"));
	        homeTab.click();

	        WebElement userNameLink = driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a"));
	        String displayedName = userNameLink.getText();
	        System.out.println("Displayed name: " + displayedName);

	        String expectedName = "Hemapriya Viswanathan";
	       Assert.assertEquals(displayedName, expectedName, "Verification Failed: User name is not displayed correctly.");
	        userNameLink.click();
	        
	        WebElement userProfileHeader = driver.findElement(By.id("tailBreadcrumbNode"));
	        String userProfileText = userProfileHeader.getText();
	        
	        // Assertion for user profile page verification
	        Assert.assertEquals(userProfileText, "User:" + expectedName, "Verification Failed: User profile page text is incorrect.");
	    }
	    
	    
	    @Test
	    public void verify_TheEdited_LastName() {
	    	login_Salesforce();
	        String expectedLastName = "Abcd"; 

	    WebElement homeTab = driver.findElement(By.id("home_Tab"));
	    clickElement(homeTab, "homeTab");

	    WebElement firstNameLastName = driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a"));
	    clickElement(firstNameLastName, "firstNameLastName");

	    String actualTitle = driver.getTitle();
	    mylog.info("Page title is: " + actualTitle);

	    Assert.assertTrue(actualTitle.contains("Hemapriya Viswanathan"), "Page title does not contain the user's name.");
	    
	    WebElement editProfileIcon = driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img"));
	    clickElement(editProfileIcon, "editProfileIcon");
	    Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"contactInfo\"]/div[1]/div")).isDisplayed(), "Contact Info popup not displayed");

//	    WebElement aboutTab = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));//*[@id="aboutTab"]/a
	    WebElement aboutTab = driver.findElement(By.cssSelector("#aboutTab a"));
	    
	    clickElement(aboutTab, "aboutTab");

	    WebElement lastNameField = driver.findElement(By.id("lastName"));
	    lastNameField.clear();
	    sendKeysToElement(lastNameField, expectedLastName);

	    WebElement saveAllButton = driver.findElement(By.id("saveAllButton"));
	    clickElement(saveAllButton, "saveAllButton");

	    WebElement displayedLastNameElement = driver.findElement(By.id("displayedLastName"));
	    String displayedLastName = displayedLastNameElement.getText();
	    Assert.assertEquals(displayedLastName, expectedLastName, "Displayed last name should match the updated last name.");

	    WebElement userMenuElement = driver.findElement(By.id("userMenu"));
	    String userMenuLastName = userMenuElement.getText();
	    Assert.assertTrue(userMenuLastName.contains(expectedLastName), "User menu should contain the updated last name.");

	    WebElement userPageLastNameElement = driver.findElement(By.id("userPageLastName"));
	    String userPageLastName = userPageLastNameElement.getText();
	    Assert.assertEquals(userPageLastName, expectedLastName, "User page last name should match the updated last name.");
	   
	    }
	    
	    @Test
	    public void customizeTabs() {

	    	login_Salesforce();
	    	    	  
	        WebElement allTabsButton = driver.findElement(By.xpath("//img[@title='All Tabs']"));
	        clickElement(allTabsButton,"allTabsButton");

	        WebElement customizeTabsButton = driver.findElement(By.name("customize"));
	        clickElement(customizeTabsButton,"customizeTabsButton");

	        WebElement selectedTab = driver.findElement(By.xpath("//*[@id=\"duel\"]/table/tbody/tr/td[3]/div/label"));   
	        clickElement(selectedTab,"selectedTab");
	        
	        WebElement dropdown =driver.findElement(By.id("duel_select_1"));
	        clickElement (dropdown,"dropdown");
	        
	        // Select by visible text (or by value if needed)
	       // selectDropdown.selectByVisibleText("Stores");

	        
	        WebElement stores = driver.findElement(By.xpath("//*[@id=\"duel_select_1\"]/option[62]"));
	        selectElement(stores,"stores");
	        
	        WebElement removeButton = driver.findElement(By.id("duel_select_0_left"));
	        clickElement(removeButton,"removeButton");

	        WebElement saveButton = driver.findElement(By.name("save"));
	        clickElement(saveButton,"saveButton");

	        WebElement userMenu = driver.findElement(By.id("userNavLabel"));
	        clickElement(userMenu,"userMenu");
	       
	        logout();
	        
	        login_Salesforce();    
	    
	    }
	    
	    @Test
	    public void event_Calender() {
	        
	    	 login_Salesforce();
	        WebElement homeTab = driver.findElement(By.id("home_Tab"));
	        clickElement(homeTab, "homeTab");

	        WebElement currentDateLink = driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a"));
	        clickElement(currentDateLink, "currentDateLink");

	        WebElement timeSlot8PM = driver.findElement(By.xpath("//*[@id=\"p:f:j_id25:j_id61:28:j_id64\"]/a"));
	        clickElement(timeSlot8PM, "timeSlot8PM");

	        WebElement subjectComboIcon = driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img"));
	        clickElement(subjectComboIcon, "subjectComboIcon");

	       
	        WebElement otherOption = driver.findElement(By.xpath("/html/body/div[2]/ul/li[5]"));
	        selectElement(otherOption, "otherOption");

	        WebElement endTimeField = driver.findElement(By.id("EndTimeField"));
	        clickElement(endTimeField, "endTimeField");
	        WebElement select9PM = driver.findElement(By.xpath("//option[@value='21:00']"));
	        clickElement(select9PM, "select9PM");

	        WebElement saveButton = driver.findElement(By.id("SaveButton"));
	        clickElement(saveButton, "saveButton");

	        WebElement savedEvent = driver.findElement(By.linkText("Other"));
	        if (savedEvent.isDisplayed()) {
	        	mylog.info("Event successfully created between 8:00 PM and 9:00 PM.");
	        } else {
	        	mylog.error("Event creation failed.");
	        }
	    }
	    
	    
			
	    	@Test
	        public void weeklyEventBlockingInCalender() {
	            
	    		 login_Salesforce(); 
	    	
	    		 WebElement homeTab = driver.findElement(By.id("home_Tab"));
	 	        clickElement(homeTab, "homeTab");

	            SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMMM dd, yyyy");
	            String expectedDate = sdf.format(new Date());
	            WebElement dateLink = driver.findElement(By.xpath("//a[contains(text(),'" + expectedDate + "')]"));
	            
	            Assert.assertTrue(dateLink.isDisplayed(), "Date link is not displayed as expected.");
	            mylog.info("Date link is displayed correctly: " + expectedDate);
	            dateLink.click();

	            WebElement timeLink = driver.findElement(By.xpath("//a[contains(text(),'4:00 PM')]"));
	            clickElement( timeLink," timeLink");

	            WebElement subjectCombo = driver.findElement(By.xpath("//img[@title='Subject Combo (New Window)']"));
	            clickElement(subjectCombo,"subjectCombo");
	            
	            WebElement otherOption = driver.findElement(By.xpath("//a[text()='Other']"));
	           selectElement(otherOption,"otherOption");

	            WebElement endTimeField = driver.findElement(By.id("EndDateTime_time"));
	           clickElement( endTimeField," endTimeField");
	            
	            WebElement endTimeOption = driver.findElement(By.xpath("//div[@id='timepicker']//li[contains(text(),'7:00 PM')]"));
	           clickElement( endTimeOption," endTimeOption");

	            WebElement recurrenceCheckbox = driver.findElement(By.id("IsRecurrence"));
	            clickElement( recurrenceCheckbox," recurrenceCheckbox");

	            WebElement weeklyRadioButton = driver.findElement(By.id("rectypeftw"));
	           clickElement(weeklyRadioButton,"weeklyRadioButton");

	            Calendar cal = Calendar.getInstance();
	            cal.add(Calendar.WEEK_OF_YEAR, 2);
	            Date endDate = cal.getTime();
	            WebElement endDateField = driver.findElement(By.id("RecurrenceEndDateOnly"));
	            clickElement(endDateField,"endDateField.");
	            WebElement dateToSelect = driver.findElement(By.xpath("//a[text()='" + new SimpleDateFormat("d").format(endDate) + "']"));
	           selectElement(dateToSelect,"dateToSelect");

	            WebElement saveButton = driver.findElement(By.name("save"));
	            clickElement(saveButton,"saveButton");

	            WebElement monthViewIcon = driver.findElement(By.xpath("//img[@title='Month View']"));
	           clickElement( monthViewIcon," monthViewIcon.");

	            WebElement eventInMonthView = driver.findElement(By.xpath("//td[contains(text(), '4:00 PM')]"));
	            Assert.assertTrue(eventInMonthView.isDisplayed(), "The event is not visible in Month View.");
	            mylog.info("Event is successfully added in Month View.");
	        }
}
	    
