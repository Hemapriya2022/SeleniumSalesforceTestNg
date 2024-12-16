package com.automation.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.BaseSalesForce;
import com.automation.utility.ExtentReportsUtility;
import com.aventstack.extentreports.Status;

public class OpportunitiesTests  extends BaseSalesForce{
	private Logger mylog = LogManager.getLogger( OpportunitiesTests .class);
	
	@BeforeMethod
	public void setUpTestLogger() {
		ExtentReportsUtility report = ExtentReportsUtility.getInstance();
		report.startExtentReport();
		report.startSingleTestReport("OpportunitiesTests");
	}
	
	@Test
    public void opportunities_drop_down() {
        login_Salesforce();

        try {
            WebElement opportunitiesTab = driver.findElement(By.id("Opportunity_Tab"));
            clickElement(opportunitiesTab, "Opportunities Tab");

            WebElement userDropDown = driver.findElement(By.id("fcf"));
            selectElement(userDropDown, "userDropDown");

            if (userDropDown.isDisplayed()) {
                mylog.info("The opportunities dropdown is present on the page.");
            } else {
              mylog.error("The opportunities dropdown is not present on the page.");
            }
        } catch (Exception e) {
            mylog.error("Failed to locate elements in opportunities dropdown test.");
        }
    }
	@Test
	public void Create_a_new_Opty() {
		
		 login_Salesforce();
		
		 WebElement opportunitiesTab = driver.findElement(By.id("Opportunity_Tab"));
	        clickElement(opportunitiesTab, "opportunitiesTab");
	        
	        WebElement newButton = driver.findElement(By.name("new"));
	       clickElement(newButton,"newButton");

	         WebElement opportunityName = driver.findElement(By.id("opp3"));
	         enterText(opportunityName,"Apple");
	        
	        WebElement accountName = driver.findElement(By.id("opp4"));
	        enterText(accountName,"ABCD ");
	        
	        WebElement CloseDate= driver.findElement(By.id("opp9"));
	        enterText (CloseDate,"10/10/2024");

	        WebElement stageDropdown = driver.findElement(By.id("opp11"));
	        enterText(stageDropdown,"Prospecting");  

	        driver.findElement(By.id("opp12")).sendKeys("30"); 
	        mylog.info("Probability textbox is filled with 30 value");
	        
	        driver.findElement(By.id("opp6")).sendKeys("Web"); 
	        mylog.info("web is selected in Lead source");
	                                                                           
	        driver.findElement(By.id("opp17")).sendKeys(" XYZ"); 
	        mylog.info("xyz is printed in primary campaign source");
	        
	        WebElement saveButton = driver.findElement(By.name("save"));
	        clickElement(saveButton,"saveButton");

	        mylog.info("New Opportunity created successfully.");
	}
	
	@Test
	public void Test_Opportunity_Pipeline_Report() {
		
		 login_Salesforce();
	
	 WebElement opportunitiesTab = driver.findElement(By.id("Opportunity_Tab"));
    clickElement(opportunitiesTab, "opportunitiesTab");
    
   // String reportTitle = driver.getTitle();
   // System.out.println("Report Page Title: " + reportTitle);
    
    WebElement opportunityPipeline = driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]"));
    clickElement(opportunityPipeline, "opportunityPipeline");
    
    String reportTitle = driver.getTitle();
    mylog.info("Report Page Title: " + reportTitle);
    
	}
	
	@Test
	public void Test_Stuck_Opportunities_Report() {
		
		login_Salesforce();
		
		WebElement opportunitiesTab = driver.findElement(By.id("Opportunity_Tab"));
	     clickElement(opportunitiesTab, "opportunitiesTab");
	     
	     WebElement stuckOpportunities = driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[2]/a"));
	     clickElement(stuckOpportunities, "stuckOpportunities");
	     
	     String reportTitle = driver.getTitle();
	     mylog.info("Report Page Title: " + reportTitle);
	}
	@Test
	public void Test_Quarterly_Summary_Report() {
		login_Salesforce();
		 WebElement opportunitiesTab = driver.findElement(By.id("Opportunity_Tab"));
	     clickElement(opportunitiesTab, "opportunitiesTab");
	     
	     WebElement quarterlySummaryLink = driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/h3"));
	     clickElement(quarterlySummaryLink,"quarterlySummaryLink");
	     
	     WebElement intervalDropdown = driver.findElement(By.id("quarter_q")); 
	     selectElement( intervalDropdown, "CurrentFQ"); 

	     WebElement includeDropdown = driver.findElement(By.id("open")); 
	     selectElement( includeDropdown, "CurrentFQ"); 
	     
	    // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reportResults"))); 
	     String reportTitle = driver.getTitle();
	     mylog.info("Report Page Title: " + reportTitle);
	     
	     mylog.info("Report page displayed with results based on search criteria.");
	}
	

}
