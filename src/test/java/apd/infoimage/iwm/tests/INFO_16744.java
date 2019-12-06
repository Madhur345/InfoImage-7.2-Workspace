package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author SumanGaK - 20-Nov-2018 
 * INFO-16744 
 * This class Performs 'Get Next' functionality and verify the Reserve icon availability under the Actions button when workitem opened from inbox. 
 */
public class INFO_16744 extends SuperClassIWM {
	@BeforeMethod
	public void setUp() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown() {
		hp.logoutApp();
	}

	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 1, groups = { "GetNext" })
	public void testVerifyReserveAvailabilityUnderActionsWhenWorkitemOpenedFromInboxWithGetNextEnabled() {
		Log.startTestCase("INFO_16744_VerifyReserveAvailabilityUnderActionsWhenWorkitemOpenedFromInboxWithGetNextEnabledTest");
		try {
			Reporter.log("VerifyReserveAvailabilityUnderActionsWhenWorkitemOpenedFromInboxWithGetNextEnabledTest", true);
			ATUReports.add("VerifyReserveAvailabilityUnderActionsWhenWorkitemOpenedFromInboxWithGetNextEnabledTest", true);
			Log.info("VerifyReserveAvailabilityUnderActionsWhenWorkitemOpenedFromInboxWithGetNextEnabledTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			int counter = 1, arrCounter = 0;
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			int wiCountVal = ExcelLib.getCellValueInt(xlpath, sheet, 15, 1);
			int getNextCount = ExcelLib.getCellValueInt(xlpath, sheet, 71, 1);   
			String[] workitemarr = new String[100];

			for (int loopCounter = 1; loopCounter <= wiCountVal; loopCounter++) {
				String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
				String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
				String workitem = str + util.getSysDate(0, date);
				workitemarr[arrCounter] = workitem;
				arrCounter++;
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
				util.wait(time);
				util.waitForPageToLoad();
			}

			ip.getNextONButton().click();
			Reporter.log("get Next Button is clicked", true);
			ATUReports.add("get Next Button is clicked", true);
			Log.info("get Next Button is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			util.jclick(ip.getNextAutoOpenCheckbox());
			Reporter.log("get Next Auto Open Checkbox is clicked", true);
			ATUReports.add("get Next Auto Open Checkbox is clicked", true);
			Log.info("get Next Auto Open Checkbox is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().clear();
			util.wait(time);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().sendKeys("" + getNextCount);
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

			boolean reserveOptionPresence = wdp.getReserveOption().isDisplayed();

			if(reserveOptionPresence){
				Reporter.log("Reserve Option is present in Actions Menu",true);
				ATUReports.add("Reserve Option is present in Actions Menu",true);
				ATUReports.add("verify the Reserve icon availability under the Actions button when workitem is created and opened", "","Reserve Option should be displayed",
						"Reserve Option is displayed", true);
			}else{
				Reporter.log("Reserve Option is not present in Actions Menu",true);
				ATUReports.add("Reserve Option is not present in Actions Menu",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			}


			util.wait(time);
			util.waitForPageToLoad();

			String reserveOptionEnabled = wdp.getReserveOption().getAttribute("class");
			
			if(reserveOptionEnabled.equalsIgnoreCase("inactiveAction")){
				Reporter.log("Reserve Option is not enabled in Actions Menu",true);
				ATUReports.add("Reserve Option is not enabled in Actions Menu",true);
				ATUReports.add("verify the Reserve icon availability under the Actions button when workitem is created and opened", "","Reserve Option should be enabled",
						"Reserve Option is not enabled", true);
			}else{
				Reporter.log("Reserve Option is enabled in Actions Menu",true);
				ATUReports.add("Reserve Option is enabled in Actions Menu",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			}

			util.wait(time);
			util.waitForPageToLoad();
			
			hp.getInbox().click();
						
			util.wait(time);
			util.waitForPageToLoad();

			util.jclick(ip.getNextAutoOpenCheckbox());
			Reporter.log("get Next Auto Open Checkbox is clicked and Set 'Auto view' mode to off state", true);
			ATUReports.add("get Next Auto Open Checkbox is clicked and Set 'Auto view' mode to off state", true);
			Log.info("get Next Auto Open Checkbox is clicked and Set 'Auto view' mode to off state");

			util.wait(time);
			util.waitForPageToLoad();

			util.wait(time);
			util.wait(time);
	
			ip.getNextOFFButton().click();
			Reporter.log("get Next Button is clicked and turned off", true);
			ATUReports.add("get Next Button is clicked and turned off", true);
			Log.info("get Next Button is clicked and turned off");

			util.wait(time);
			util.waitForPageToLoad();


		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute VerifyReserveAvailabilityUnderActionsWhenWorkitemOpenedFromInboxWithGetNextEnabled test", true);
			ATUReports.add("failed to execute VerifyReserveAvailabilityUnderActionsWhenWorkitemOpenedFromInboxWithGetNextEnabled test", true);

		} finally {
			Log.endTestCase("INFO_16744_VerifyReserveAvailabilityUnderActionsWhenWorkitemOpenedFromInboxWithGetNextEnabledTest");
		}
	}
}