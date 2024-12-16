package com.automation.tests;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.base.BaseSalesForce;
import com.automation.utility.ExtentReportsUtility;

public class UserMenuDropDown   extends BaseSalesForce{
private Logger mylog = LogManager.getLogger(UserMenuDropDown  .class);
	//WebDriverWait wait;
	
	@BeforeMethod
	public void setUpTestLogger() {
		ExtentReportsUtility report = ExtentReportsUtility.getInstance();
		report.startExtentReport();
		report.startSingleTestReport("AccountTests");
	}
	

	@Test
	  public void select_Usermenu_Dropdown() {
	        login_Salesforce();

	        WebElement userMenu = driver.findElement(By.id("userNavButton"));

	        // Check if the user menu is displayed
	        if (userMenu.isDisplayed()) {
	        	 mylog.info ("User menu is available.");
	        } else {
	            mylog.error("User menu is not available.");
	        }

	        clickElement(userMenu,"userMenu");

	        WebElement profileOption = driver.findElement(By.linkText("My Profile"));
	        WebElement settingsOption = driver.findElement(By.linkText("My Settings"));
	        WebElement developerConsoleOption = driver.findElement(By.linkText("Developer Console"));
	        WebElement logoutOption = driver.findElement(By.linkText("Logout"));
	        WebElement lightningOption = driver.findElement(By.linkText("Switch to Lightning Experience"));

	        Assert.assertTrue(profileOption.isDisplayed(), "Profile option is missing.");
	        Assert.assertTrue(settingsOption.isDisplayed(), "Settings option is missing.");
	        Assert.assertTrue(developerConsoleOption.isDisplayed(), "Developer Console option is missing.");
	        Assert.assertTrue(logoutOption.isDisplayed(), "Logout option is missing.");
	        Assert.assertTrue(lightningOption.isDisplayed(), "Switch to Lightning Experience option is missing.");

	    }
	

@Test
public void MyProfileSelection() {
	
	 login_Salesforce();
	 WebElement userMenu = driver.findElement(By.id("userNavButton"));
     clickElement(userMenu,"userMenu");
     
        WebElement myProfile = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));
        selectElement(myProfile,"myProfile");
                  
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img")));
        clickElement(editButton, "Edit Button");
        
        WebElement aboutTab = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
        aboutTab.click();
        
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        enterText(lastNameField, "V", "Last Name");
        
        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]"));
        clickElement(saveButton, "Save All Button");
        
        WebElement postLink = driver.findElement(By.linkText("Post"));
        clickElement(postLink, "Post Link");
        
        WebElement postTextArea = driver.findElement(By.xpath("//textarea[@class='postTextArea']"));
        enterText(postTextArea, "This is a test post.", "Post Text Area");
        
        WebElement shareButton = driver.findElement(By.xpath("//input[@value='Share']"));
        clickElement(shareButton, "Share Button");

      
        WebElement fileLink = driver.findElement(By.linkText("File"));
        clickElement(fileLink, "File Link");
        
        WebElement uploadButton = driver.findElement(By.id("uploadFileButton"));
        clickElement(uploadButton, "Upload File Button");
        
        WebElement chooseFileButton = driver.findElement(By.id("chooseFile"));
        chooseFileButton.sendKeys("/path/to/your/file"); // Provide actual file path
        
     
        WebElement profilePhoto = driver.findElement(By.id("myprofilephoto"));
        mouseOverOnElement(profilePhoto, "Profile Photo");
        
        WebElement addPhotoLink = driver.findElement(By.linkText("Add Photo"));
        clickElement(addPhotoLink, "Add Photo Link");
        
        WebElement choosePhotoButton = driver.findElement(By.id("chooseFile"));
        choosePhotoButton.sendKeys("/path/to/your/photo");  // Provide the actual path of the image file
            
    }

@Test
 public void my_Settings () {
	 
	 login_Salesforce();
	 
	 WebElement userMenu = driver.findElement(By.id("userNavButton"));
     clickElement(userMenu,"userMenu");
     
     WebElement mySettings = driver.findElement(By.linkText("My Settings"));
     clickElement( mySettings," mySettings");
		
		WebElement personalLink = driver.findElement(By.id("PersonalInfo_font"));
     clickElement(personalLink,"personalLink");
     
     WebElement loginHistoryLink = driver.findElement(By.id("LoginHistory_font"));
     clickElement(loginHistoryLink,"loginHistoryLink");
     
     WebElement downloadLoginHistory = driver.findElement(By.xpath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a"));
 	 clickElement(downloadLoginHistory,"downloadLoginHistory");
      
        Assert.assertTrue(driver.findElement(By.linkText("Download login history")).isDisplayed(), "Download login history link not found");

        driver.findElement(By.linkText("Display & Layout")).click();
        driver.findElement(By.linkText("Customize My Tabs")).click();

        WebElement customAppDropdown = driver.findElement(By.id("p4"));
        customAppDropdown.sendKeys("Salesforce Chatter");

        WebElement availableTabs = driver.findElement(By.id("duel_select_0"));
        availableTabs.sendKeys("Reports");

        driver.findElement(By.xpath("//img[@alt='Add']")).click();

        WebElement selectedTabs = driver.findElement(By.id("duel_select_1"));
        Assert.assertTrue(selectedTabs.getText().contains("Reports"), "Reports tab not added to Selected Tabs");

        // Email > My Email Settings
        driver.findElement(By.linkText("Email")).click();
        driver.findElement(By.linkText("My Email Settings")).click();

        WebElement emailName = driver.findElement(By.id("sender_name"));
        WebElement emailAddress = driver.findElement(By.id("sender_email"));
        WebElement bccRadio = driver.findElement(By.id("auto_bcc1"));
        WebElement saveButton = driver.findElement(By.name("save"));

        emailName.clear();
        emailName.sendKeys("Test Email Name");
        emailAddress.clear();
        emailAddress.sendKeys("test@example.com");
        bccRadio.click();
        saveButton.click();

        Assert.assertTrue(driver.getTitle().contains("My Settings"), "My Settings page not displayed after saving email settings");

        WebElement calendarAndRemindersLink = driver.findElement(By.linkText("Calendar & Reminders"));
		clickElement(calendarAndRemindersLink, "calendarAndRemindersLink");
    
		WebElement activityRemindersLink = driver.findElement(By.linkText("Activity Reminders"));
		clickElement(activityRemindersLink, "activityRemindersLink");
  
        WebElement testReminderButton = driver.findElement(By.name("test"));
        clickElement(testReminderButton, "testReminderButton"); 

        // Assert test reminder popup
        String parentWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Assert.assertTrue(driver.getTitle().contains("Reminder"), "Reminder window not displayed");
        driver.close();  
        driver.switchTo().window(parentWindow);
    }

@Test
public void developers_Console() {
	
	 login_Salesforce();
	
	 WebElement userMenu = driver.findElement(By.id("userNavButton"));
    clickElement(userMenu,"userMenu");

     WebElement myDeveloperConsole = driver.findElement(By.xpath("//a[contains(text(),'Developer Console')]"));
     clickElement(myDeveloperConsole,"myDeveloperConsole");

     String mainWindow = driver.getWindowHandle();

     Set<String> allWindows = driver.getWindowHandles();
     for (String window : allWindows) {
         if (!window.equals(mainWindow)) {
             driver.switchTo().window(window);
             Assert.assertTrue(driver.getTitle().contains("Developer Console"), "Developer Console window is not opened");
             driver.close(); // Close the Developer Console window
             break;
         }
     }

     driver.switchTo().window(mainWindow);

     Assert.assertTrue(driver.getTitle().contains("Salesforce"), "Failed to switch back to the main window");
 }

@Test
public void test_Logout_From_UserMenu() {
	
	 login_Salesforce();
   
    WebElement userMenu = driver.findElement(By.id("userNavButton"));
    userMenu.click();

    logout();
	
}

}
