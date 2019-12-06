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

public class INFO_10694 extends SuperClassIWM {
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
	public void testVeifyReturtoQueuefunctionality() {
		Log.startTestCase("INFO_10694_ verify 'Return to Queue' functionality for unreserved workitem in workitems page");
		ATUReports.setTestCaseReqCoverage("This Scenario will Test to  verify  "
				+ "'Return to Queue' functionality for unreserved workitem in workitems page");
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
			boolean returnToQueueOptionEnabled = hp.getreturnToQueueOption().isEnabled();
						
			if(returnToQueueOptionEnabled) {
				Reporter.log("return To Queue Option is enabled", true);
				ATUReports.add("return To Queue Option is enabled", true);
				Log.info("return To Queue Option is enabled");
			} else {
				Reporter.log("return To Queue Option is not enabled", true);
				ATUReports.add("return To Queue Option is not enabled", true);
				Log.info("return To Queue Option is not enabled");
				Assert.fail("return To Queue Option is not enabled");
			}

		}

		catch (Exception e) {
			Reporter.log(" verify 'Return to Queue' functionality for unreserved workitem in workitems page type test failed", true);
			ATUReports.add(" verify 'Return to Queue' functionality for unreserved workitem in workitems page type test failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info(" verify 'Return to Queue' functionality for unreserved workitem in workitems page type test failed");
			Assert.fail(" verify 'Return to Queue' functionality for unreserved workitem in workitems page type test failed");
		} finally {
			Log.endTestCase("INFO_10694_Test to verify 'Return to Queue' functionality for unreserved workitem in workitems page");
		}
	}
}
