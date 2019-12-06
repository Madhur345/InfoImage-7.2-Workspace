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

public class INFO_10639 extends SuperClassIWM {
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
	@Test(enabled = true, groups = { "Workitem" })
	public void testVeifySendToDefaultfunctionality() {
		Log.startTestCase("INFO_10639_ verify 'Send to default' functionality under Actions in workitems page for unreserved document/folder type of workitem page ");
		ATUReports.setTestCaseReqCoverage("This Scenario will Test to  verify  "
				+ "'Send to default' functionality for unreserved workitem in workitems page");
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
			Reporter.log("Sending Workitem to Workflow", true);
			util.wait(time);
			cwp.sendWorkItemToDefaultQueue(workitem);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
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

			boolean sendToDefaultOptionEnabled = hp.getSendThisWorkitemOption().isEnabled();
			System.out.println("++++++++++++++++++++++++++++++++++++++++");

			if(sendToDefaultOptionEnabled) {
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
			Reporter.log("verify 'Send to default' functionality under Actions in workitems page for unreserved document/folder type of workitem page type test failed", true);
			ATUReports.add(" verify 'Send to default' functionality under Actions in workitems page for unreserved document/folder type of workitem page type test failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("verify 'Send to default' functionality under Actions in workitems page for unreserved document/folder type of workitem page type test failed");
			Assert.fail("verify 'Send to default' functionality under Actions in workitems page for unreserved document/folder type of workitem page type test failed");
		} finally {
			Log.endTestCase("INFO_10639_Test to verify 'Send to default' functionality under Actions in workitems page for unreserved document/folder type of workitem");
		}
	}
}



