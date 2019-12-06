package apd.infoimage.iwm.tests;

import org.testng.Assert;
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
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author SumanGaK - 11-May-2018 
 * INFO-7460 
 * This class sends retrieved workitem to different User Queue and checks the same workitem in new user
 *         Queue
 */
public class INFO_7460
extends SuperClassIWM {
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
		Log.info("User logged out");
		Log.endTestCase(
				"INFO_7460_SendRetrievedWorkitemToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueueTest");
	}

	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 1, groups = { "Inbox" })
	public void testSendRetrievedWorkitemToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueue() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7460 is for sending retrieved workitem to different User Queue and checks the same workitem in new user Queue");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try {
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String userQueueOption = ExcelLib.getCellValue(xlpath, sheet, 19, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("SendRetrievedWorkitemToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueueTest", true);
			ATUReports.add("SendRetrievedWorkitemToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueueTest", true);
			Log.info("SendRetrievedWorkitemToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueueTest");

			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_7460_SendRetrievedWorkitemToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueueTest");
			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Log.info("Workitem : " + workitem);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Log.info("Class Name : " + className);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Log.info("Workitem Type : " + workitemType);

			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			Log.info("Creation of Workitem");
			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");

			util.wait(time);
			util.wait(time);
			cwp.sendWorkItemToDefaultQueue(workitem);

			Reporter.log("Retrieving Workitem From Inbox", true);
			ATUReports.add("Retrieving Workitem From Inbox", true);
			Log.info("Retrieving Workitem From Inbox");

			util.wait(time);
			Reporter.log("Retrieving Workitem", true);
			ATUReports.add("Retrieving Workitem", true);
			Log.info("Retrieving Workitem");

			ip.retrieveWorkItem(workitem);
			util.wait(time);

			Reporter.log("Sending WorkItem To User Queue", true);
			ATUReports.add("Sending WorkItem To User Queue", true);
			Log.info("Sending WorkItem To User Queue");
			cwp.sendWorkItemToUserQueue(workitem, userQueueOption);

			util.waitForPageToLoad();
			util.wait(time);
			hp.logoutApp();
			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Logging into another user to whom workitem was sent", true);
			ATUReports.add("Logging into another user to whom workitem was sent", true);
			Log.info("Logging into another user to whom workitem was sent");

			util.wait(time);
			lp.loginToApp("userName1", "password1", "domain1", "role1");

			util.wait(time);
			util.waitForPageToLoad();

			util.wait(time);
			util.waitForPageToLoad();

			hp.getInbox().click();
			Reporter.log("Inbox tab is clicked", true);
			ATUReports.add("Inbox tab is clicked", true);
			Log.info("Inbox tab is clicked");

			util.wait(time);
			ip.getMyPersonalQueue().click();

			Reporter.log("My Personal Queue link is clicked", true);
			ATUReports.add("My Personal Queue link is clicked", true);
			Log.info("My Personal Queue link is clicked");
			util.wait(time);
			ip.searchByNameInMyPersonalQueue(workitem);

			Reporter.log("Searching By Workitem Name In MyPersonal Queue", true);
			ATUReports.add("Searching By Workitem Name In MyPersonal Queue", true);
			Log.info("Searching By Workitem Name In MyPersonal Queue");

			boolean workitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (workitemPresence) {
				Reporter.log("Workitem is present in grid", true);
				ATUReports.add("Workitem is present in grid", true);
				Log.info("Workitem is present in grid");
				ATUReports.add("Verify retrieved workitem to different User Queue and checks the same workitem in new user Queue",
						"", "Sent workitem in the new user Queue should be displayed",
						"Sent workitem in the new user Queue is displayed", true);
			} else {
				Reporter.log("Workitem is not present in grid", true);
				Log.info("Workitem is not present in grid");
				ATUReports.add("Workitem is not present in grid", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Workitem is not present in grid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute SendRetrievedWorkitemToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueue test",
					true);
			ATUReports.add("failed to execute SendRetrievedWorkitemToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueue test",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute SendRetrievedWorkitemToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueue test");
		}

	}
}
