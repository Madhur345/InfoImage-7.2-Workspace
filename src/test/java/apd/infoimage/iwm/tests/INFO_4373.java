package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

/**
 * @author SumanGaK - 15-Jan-2018
 * INFO_4373
 * This class will create a Create a view for workItems and validate the same in Inbox Page
 */

public class INFO_4373 extends SuperClassIWM {

	@BeforeMethod
	public void beforMethod() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod() {
		hp.logoutApp();
	}


	@SuppressWarnings("deprecation")
	@Test(enabled = true, groups = { "Search" })
	public void testVerifySearchFunctionalityWithWorksetValueInInbox() {
		ATUReports.setTestCaseReqCoverage("This Scenario will create a Create a view for workItems and validate the same in Inbox Page");
		ATUReports.setAuthorInfo("SumanGaK", "APR-2018", "0.3");
		try {
			Log.startTestCase("INFO_4373_VerifySearchFunctionalityWithWorksetValueInInboxTest");

			Reporter.log("VerifySearchFunctionalityWithWorksetValueInInboxTest : testVerifySearchFunctionalityWithWorksetValueInInbox()", true);
			ATUReports.add("VerifySearchFunctionalityWithWorksetValueInInboxTest : testVerifySearchFunctionalityWithWorksetValueInInbox()",	true);
			Log.info("VerifySearchFunctionalityWithWorksetValueInInboxTest : testVerifySearchFunctionalityWithWorksetValueInInbox()");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");
			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String viewString=ExcelLib.getCellValue(xlpath,sheet,77,1);
			String viewName = viewString + util.getSysDate(0, date);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			int columnsCount = ExcelLib.getCellValueInt(xlpath, sheet, 76, 1);
			String worksetName = ExcelLib.getCellValue(xlpath,sheet,89,1);


			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);

			cwp.CreateWorkitem(workitem,className,workitemType);

			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");

			Reporter.log("Document: "+workitem+" has been created",true);
			ATUReports.add("Document: "+workitem+" has been created",true);
			Log.info("Document: "+workitem+" has been created");

			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");

			util.wait(time);
			cwp.sendWorkItemToDefaultQueue(workitem);
			util.wait(time);
			util.waitForPageToLoad();

			util.waitForPageToLoad();
			util.wait(time);
			hp.clickUserPreferenceTab();

			util.waitForPageToLoad();
			util.wait(time);

			upp.getInboxRadioBtn().click();
			Reporter.log("Inbox Radio Button is clicked.", true);
			ATUReports.add("Inbox Radio Button is clicked.", true);
			Log.info("Inbox Radio Button is clicked.");

			util.wait(time);
			upp.getViewNameTextbox().sendKeys(viewName);
			Reporter.log("View Name is passed to text box.", true);
			ATUReports.add("View Name is passed to text box.", true);
			Log.info("View Name is passed to text box.");

			util.wait(time);
			upp.selectFieldsFromAvailable("CLASS", "OBJECT_ID", "WORKSET");
			Reporter.log("Workflow variables column names in Available Fields are moved to Selected", true);
			ATUReports.add("Workflow variables column names in Available Fields are moved to Selected", true);
			Log.info("Workflow variables column names in Available Fields are moved to Selected");

			String column[] = { "CLASS", "OBJECT_ID", "WORKSET" };

			util.wait(time);
			upp.getCreateViewBtn().click();
			Reporter.log("Create View Button is clicked.", true);
			ATUReports.add("Create View Button is clicked.", true);
			Log.info("Create View Button is clicked.");

			util.waitForPageToLoad();
			util.wait(time);

			upp.getCreatedViewNameRadioInbox(viewName).click();
			Reporter.log("Created View Name Radio Button is clicked.", true);
			ATUReports.add("Created View Name Radio Button is clicked.", true);
			Log.info("Created View Name Radio Button is clicked.");

			upp.getApplyViewBtn().click();
			Reporter.log("Apply View Button is clicked.", true);
			ATUReports.add("Apply View Button is clicked.", true);
			Log.info("Apply View Button is clicked.");

			hp.getInbox().click();
			Reporter.log("Inbox is clicked.", true);
			ATUReports.add("Inbox is clicked.", true);
			Log.info("Inbox is clicked.");

			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked.", true);
			ATUReports.add("Data Entry is clicked.", true);
			Log.info("Data Entry is clicked.");

			util.wait(time);

			Select select = new Select(ip.getInboxDropDownForSearch());
			// int count=select.getOptions().size();
			int found = 0;

			List<WebElement> allOptions = select.getOptions();

			for (int j = 0; j < columnsCount; j++) {
				for (int i = 0; i < allOptions.size(); i++) {
					String columnname = allOptions.get(i).getText();
					if (column[j].equalsIgnoreCase(columnname)) {
						found++;
					}
				}
				if (found == 0) {
					System.out.println("Assigned field : " + column[j] + " is not displayed in the dropdown list.");
				} else {
					System.out.println("Assigned field : " + column[j] + " is displayed in the dropdown list.");
				}
			}

			util.wait(time);
			util.waitForPageToLoad();

			Select sel = new Select(hp.getNumOfRowsDropDown());
			sel.selectByValue("100");
			util.wait(time);

			select.selectByVisibleText("WORKSET");
			util.wait(time);

			ip.getSearchFieldInDataEntry().sendKeys(worksetName);

			Actions actions = new Actions(Driver.driver);
			actions.sendKeys(Keys.ENTER).perform();

			ip.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");
			
			util.waitForPageToLoad();
			util.wait(time);
			hp.clickUserPreferenceTab();
			Reporter.log("User Preferences Tab is clicked.", true);
			ATUReports.add("User Preferences Tab is clicked.", true);
			Log.info("User Preferences Tab is clicked.");

			util.waitForPageToLoad();
			util.wait(time);

			upp.getInboxRadioBtn().click();
			Reporter.log("Inbox Radio Button is clicked.", true);
			ATUReports.add("Inbox Radio Button is clicked.", true);
			Log.info("Inbox Radio Button is clicked.");

			util.wait(time);
			util.waitForPageToLoad();
			util.wait(time);

			upp.getCreatedViewNameRadioInbox(viewName).click();
			Reporter.log("Created View Name Radio Button is clicked.", true);
			ATUReports.add("Created View Name Radio Button is clicked.", true);
			Log.info("Created View Name Radio Button is clicked.");

			upp.getDeleteViewBtn().click();
			Reporter.log("Delete View Button is clicked.", true);
			ATUReports.add("Delete View Button is clicked.", true);
			Log.info("Delete View Button is clicked.");
			
			util.wait(time);
			util.waitForPageToLoad();
			
			upp.getAlertOKButton().click();
			Reporter.log("Alert OK Button is clicked.", true);
			ATUReports.add("Alert OK Button is clicked.", true);
			Log.info("Alert OK Button is clicked.");
			
			util.wait(time);
			util.waitForPageToLoad();

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify Search Functionality With Workset Value In Inbox test failed.", true);
			ATUReports.add("Verify Search Functionality With Workset Value In Inbox test failed.", true);
			ATUReports.add("failed to execute test INFO_4373_VerifySearchFunctionalityWithWorksetValueInInboxTest", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Search Functionality With Workset Value In Inbox test failed.");
		} finally {
			Log.endTestCase("INFO_4373_VerifySearchFunctionalityWithWorksetValueInInboxTest");
		}
	}
}
