package com.automation.tests;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.BaseSalesForce;
import com.automation.utility.ExtentReportsUtility;

public class LeadsTests extends  BaseSalesForce {
	private Logger mylog = LogManager.getLogger(LeadsTests .class);
	private WebDriverWait wait;
	
	 @BeforeMethod
		public void setUpTestLogger() {
			ExtentReportsUtility report = ExtentReportsUtility.getInstance();
			report.startExtentReport();
			report.startSingleTestReport("LoginTests");
		}
	@Test
	 public void leadsTab() {
			
			 login_Salesforce();	
		    
		    WebElement leadsTab = driver.findElement(By.xpath("//a[contains(@title,'Leads')]"));
		    if (leadsTab.isDisplayed()) {
		       mylog.info("Leads tab is visible.");
		    } else {
		        mylog.error("Leads tab is not visible.");
		    }
		    
		    WebElement leadTabLink = driver.findElement(By.id("Lead_Tab"));
		    clickElement(leadTabLink, "leadTabLink");

		    // Perform logout
		    WebElement userMenu = driver.findElement(By.id("userNav"));
		    clickElement(userMenu,"userMenu");
		    
		    WebElement logout = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]")); // Adjust text as needed
		    clickElement(logout,"logout");
		    
		   
		}
	@Test	    
	public void validate_ViewDropdown_Content() {
    	
		login_Salesforce();
	    	
	    WebDriverWait wait = new WebDriverWait(driver, 10);

	    WebElement leadsTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("Lead_Tab")));
	    leadsTab.click();

	    WebElement viewDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fcf")));
	    
	    List<WebElement> options = viewDropdown.findElements(By.tagName("option"));
	    mylog.info("View dropdown options:");
	    
	    for (WebElement option : options) {
	        mylog.info(option.getText());
	    }
	    
	    logout();	   


}
	@Test
	 public void default_View() {
	      
		 login_Salesforce();
	        
	        
	        WebElement leadsTab = driver.findElement(By.id("Lead_Tab"));
	        clickElement(leadsTab, "Leads Tab");
	        
	        WebElement leadsHomePage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tryLexDialogX")));
	        leadsHomePage.click(); 
	        
	        
	        Select viewDropdown = new Select(driver.findElement(By.id("fcf")));
	        viewDropdown.selectByVisibleText("Today's Leads");

	        String selectedView = viewDropdown.getFirstSelectedOption().getText();
	        Assert.assertEquals(selectedView, "Today's Leads", "Today's Leads should be selected from the dropdown!");

	        WebElement userNavButton = driver.findElement(By.id("userNavLabel"));
	        clickElement(userNavButton, "User Navigation");
	        WebElement logoutButton = driver.findElement(By.xpath("//a[contains(text(), 'Logout')]"));
	        clickElement(logoutButton, "Logout Button");

	        
	        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Login")));
	        Assert.assertTrue(loginButton.isDisplayed(), "Login page should be displayed after logout!");

	        login_Salesforce();

	        leadsTab = driver.findElement(By.id("Lead_Tab"));
	        clickElement(leadsTab, "Leads Tab");

	        leadsHomePage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tryLexDialogX")));
	        leadsHomePage.click(); // close pop-up again

	        WebElement goButton = driver.findElement(By.name("go"));
	        clickElement(goButton, "Go Button");

	       WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultsSection")));
	        Assert.assertTrue(result.isDisplayed(), "Default view 'Today's Leads' should be displayed!");

	 }	
	@Test
	 public void todays_Lead() {
			
			
		 login_Salesforce();
		    WebElement leadTabLink = driver.findElement(By.id("Lead_Tab"));
		    clickElement(leadTabLink, "leadTabLink");
		    
		    WebElement viewDrobDown = driver.findElement(By.id("fcf")); 
		    clickElement(viewDrobDown,"viewDrobDown");
		    
		    WebElement todaysLeadsOption = driver.findElement(By.xpath("//*[@id=\"fcf\"]/option[4]"));
		    selectElement(todaysLeadsOption,"todaysLeadsOption");

	        WebElement goButton = driver.findElement(By.name("go"));
	        clickElement(goButton,"goButton");
	       
	       	    
	        logout();
	        
	 }
	 
	 @Test
	 public void lead_Creation() {

		 login_Salesforce();
		    WebElement leadTabLink = driver.findElement(By.id("Lead_Tab"));
		    clickElement(leadTabLink, "leadTabLink");

		    WebElement newButton = driver.findElement(By.name("new"));
		    clickElement(newButton, "newButton");

		    WebElement lastNameField = driver.findElement(By.id("name_lastlea2"));
		    lastNameField.sendKeys("ABCD");

		    WebElement companyField = driver.findElement(By.id("lea3"));
		    companyField.sendKeys("ABCD");

		    WebElement saveButton = driver.findElement(By.xpath("//input[@title='Save']"));
		    clickElement(saveButton, "saveButton");

		    mylog.info("New lead has been created.");
		    
		    logout();
	 }
	 
}
	        
