package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

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

public class INFO_10635 extends SuperClassIWM {
	@BeforeMethod
	public void setUp() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		hp.logoutApp();
	}

	@SuppressWarnings("deprecation")
	@Test(enabled = true, groups = { "Workitem" })
	public void testVeifySendToDefaultfunctionality() {
		Log.startTestCase("INFO_10635_ verify 'Send to default' functionality  for document type of workitem under Actions in workitems page ");
		ATUReports.setTestCaseReqCoverage("This Scenario will Test to  verify  "
				+ " 'Send to default' functionality  for document type of workitem under Actions in workitems page");
		ATUReports.setAuthorInfo("GuptaPr2", "Aug-2018", "0.3");
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
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Log.info("Workitem : " + workitem);
		
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Log.info("Class Name : " + className);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Log.info("Workitem Type : " + workitemType);

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			util.wait(time);
			
			cwp.searchByNameInWorkitemList(workitem);
			hp.getFirstRowFirstCell().click();
			Reporter.log("Action Button of Workitem is clicked", true);
			ATUReports.add("Action Button of Workitem is clicked", true);
			Log.info("Action Button of Workitem is clicked");

			util.wait(time);
			Reporter.log("Sending Workitem to Workflow", true);
			util.wait(time);
			hp.getSendThisWorkitemOption().click();

			util.wait(time);
			boolean sendWindowBoxPresence = util.verifyObjectPresentReturnsBool(cwp.getSendToWorkflow());
			if (sendWindowBoxPresence) {
				Reporter.log("Send Workitem window box is present", true);
			} else {
				Reporter.log("Send Workitem window box not present", true);
				Assert.fail("Send Workitem window box not present ");
			}

			cwp.getSendButton().click();
			Reporter.log("Send Button is clicked", true);
			util.waitForPageToLoad();
			util.waitForElementEnabled(hp.getInbox());
			util.wait(time);
			util.wait(time);

			util.jclick(hp.getInbox());
			util.waitForPageToLoad();
			Reporter.log("Inbox tab is clicked", true);
			ATUReports.add("Inbox tab is clicked", true);
			util.wait(time);
			util.wait(time);
			util.wait(time);


			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked", true);
			ATUReports.add("Data Entry is clicked", true);


			util.waitForPageToLoad();
			util.wait(time);
			util.wait(time);
			util.wait(time);
			ip.searchByNameInDataEntry(workitem);			

			util.wait(time);
			util.waitForPageToLoad();

			Reporter.log("Retrieving Workitem", true);
			ATUReports.add("Retrieving Workitem", true);
			Log.info("Retrieving Workitem");
			ip.retrieveWorkItem(workitem);

			cwp.searchByNameInWorkitemList(workitem);
			hp.getFirstRowFirstCell().click();
			Reporter.log("Action Button of Workitem is clicked", true);
			ATUReports.add("Action Button of Workitem is clicked", true);
			Log.info("Action Button of Workitem is clicked");

			util.wait(time);

			boolean sendThisWorkitemOptionEnabled = hp.getSendThisWorkitemOption().isEnabled();
			System.out.println("++++++++++++++++++++++++++++++++++++++++");

			
			if(sendThisWorkitemOptionEnabled) {
				Reporter.log("Send to default Option is enabled", true);
				ATUReports.add("Send to default Option is enabled", true);
				Log.info("Send to default option is enabled");
			} else {
				Reporter.log("Send to default Option is not enabled", true);
				ATUReports.add("Send to default Option is not enabled", true);
				Log.info("Send to default option is not enabled");
				Assert.fail("Send to default option is not enabled");
			}		

		}

		catch (Exception e) {
			Reporter.log("verify 'Send to default' functionality  for document type of workitem under Actions in workitems page type test failed", true);
			ATUReports.add("verify 'Send to default' functionality  for document type of workitem under Actions in workitems page type test failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("verify 'Send to default' functionality  for document type of workitem under Actions in workitems page test failed");
			Assert.fail("verify 'Send to default' functionality  for document type of workitem under Actions in workitems page test failed");
		} finally {
			Log.endTestCase("INFO_10636_Test to verify 'Send to default' functionality  for document type of workitem under Actions in workitems page");
		}
	}
}