package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author SumanGaK - 26-Mar-2018 
 * INFO-8752 
 * This class Performs 'Auto view' functionality for multiple workitems from any queue
 */
public class INFO_8752 extends SuperClassIWM {
	@BeforeMethod
	public void setUp() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName1", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown() {
		hp.logoutApp();
	}

	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 1, groups = { "GetNext" })
	public void testGetNextAutoViewMultipleWorkitems() {
		Log.startTestCase("INFO_8752_GetNextAutoViewMultipleWorkitemsTest");

		try {
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			Reporter.log("GetNextAutoViewMultipleWorkitemsTest", true);
			ATUReports.add("GetNextAutoViewMultipleWorkitemsTest", true);
			Log.info("GetNextAutoViewMultipleWorkitemsTest");

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			int counter = 1;
			int wiCountVal = ExcelLib.getCellValueInt(xlpath, sheet, 15, 1);
			int getNextCount = ExcelLib.getCellValueInt(xlpath, sheet, 71, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);


			for(int loopCounter = 1; loopCounter <= wiCountVal; loopCounter++) {
				String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
				String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
				String workitem = str + util.getSysDate(0, date);
				String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
				String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);

				Reporter.log("Creation of Workitem", true);
				ATUReports.add("Creation of Workitem", true);
				Log.info("Creation of Workitem");

				cwp.CreateWorkitem(workitem, className, workitemType);
				Reporter.log("Sending Workitem to Workflow", true);
				ATUReports.add("Sending Workitem to Workflow", true);
				Log.info("Sending Workitem to Workflow");

				util.wait(time);
				cwp.sendWorkItemToDefaultQueue(workitem);
				util.wait(time);
				util.waitForPageToLoad();

				if (counter != wiCountVal) {
					counter++;
					ip.getWorkItemsTab().click();
					Reporter.log("WorkItem tab is clicked", true);
					ATUReports.add("WorkItem tab is clicked", true);
					Log.info("WorkItem tab is clicked");

					util.wait(time);
					util.waitForPageToLoad();
				}
			}

			ip.getNextONButton().click();
			Reporter.log("get Next Button is clicked", true);
			ATUReports.add("get Next Button is clicked", true);
			Log.info("get Next Button is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getNextAutoOpenCheckbox().click();
			Reporter.log("get Next Auto Open Checkbox is clicked", true);
			ATUReports.add("get Next Auto Open Checkbox is clicked", true);
			Log.info("get Next Auto Open Checkbox is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().clear();
			util.wait(time);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().sendKeys(""+getNextCount);
			Reporter.log("get Next Required No Of Workitems is sent to textbox", true);
			ATUReports.add("get Next Required No Of Workitems is sent to textbox", true);
			Log.info("get Next Required No Of Workitems is sent to textbox");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getSaveButton().click();
			Reporter.log("SaveButton is clicked", true);
			ATUReports.add("SaveButton is clicked", true);
			Log.info("SaveButton is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked", true);
			ATUReports.add("Data Entry is clicked", true);
			Log.info("Data Entry is clicked");
			util.wait(time);
			util.waitForPageToLoad();

			ip.getFirstWorkitem().click();
			util.wait(time);
			util.waitForPageToLoad();

			wdp.getActionsDropDown().click();
			Reporter.log("Actions Drop Down is clicked", true);
			ATUReports.add("Actions Drop Down is clicked", true);
			Log.info("Actions Drop Down is clicked");
			util.wait(time);
			util.waitForPageToLoad();

			wdp.getSendToDefaultOption().click();
			Reporter.log("Send To Default Option is selected", true);
			ATUReports.add("Send To Default Option is selected", true);
			Log.info("Send To Default Option is selected");
			util.wait(time);
			util.waitForPageToLoad();

			Reporter.log("Clicking Close Workitem", true);
			ATUReports.add("Clicking Close Workitem", true);
			Log.info("Clicking Close Workitem");


			while(wdp.isElementPresent(wdp.getCloseWorkitem())) {
				util.wait(time);
				util.waitForPageToLoad();
				wdp.getCloseWorkitem().click();
				Reporter.log("Close Workitem is clicked", true);
				ATUReports.add("Close Workitem is clicked", true);
				Log.info("Close Workitem is clicked");

				util.wait(time);
				util.waitForPageToLoad();

				continue;
			}			

			util.wait(time);
			util.waitForPageToLoad();

			ip.getNextAutoOpenCheckbox().click();
			Reporter.log("get Next Auto Open Checkbox is clicked and turned off", true);
			ATUReports.add("get Next Auto Open Checkbox is clicked and turned off", true);
			Log.info("get Next Auto Open Checkbox is clicked and turned off");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getNextOFFButton().click();
			Reporter.log("get Next Button is clicked and turned off", true);
			ATUReports.add("get Next Button is clicked and turned off", true);
			Log.info("get Next Button is clicked and turned off");

			util.wait(time);
			util.wait(time);
			ip.getWorkItemsTab().click();
			Reporter.log("WorkItem tab is clicked", true);
			ATUReports.add("WorkItem tab is clicked", true);
			Log.info("WorkItem tab is clicked");

			util.wait(time);
			util.waitForPageToLoad();
			util.waitForElementEnabled(hp.getInbox());
			util.wait(time);
			util.jclick(hp.getInbox());
			util.waitForPageToLoad();
			Reporter.log("Inbox tab is clicked", true);
			ATUReports.add("Inbox tab is clicked", true);
			Log.info("Inbox tab is clicked");

			util.wait(time);
			util.waitForPageToLoad();
			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked", true);
			ATUReports.add("Data Entry is clicked", true);
			Log.info("Data Entry is clicked");

			util.waitForPageToLoad();
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute GetNextAutoViewMultipleWorkitems test", true);
			ATUReports.add("failed to execute GetNextAutoViewMultipleWorkitems test", true);
			Assert.fail("failed to execute GetNextAutoViewMultipleWorkitems test");
		} finally {
			Log.endTestCase("INFO_8752_GetNextAutoViewMultipleWorkitemsTest");

		}

	}
}
