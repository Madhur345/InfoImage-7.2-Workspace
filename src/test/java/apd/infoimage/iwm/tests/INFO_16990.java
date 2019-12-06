package apd.infoimage.iwm.tests;

/**
 * @author DashBisw INFO_16990  This class is To verify 
 * "The Availability Of Send To Default Under The Actions In The Workitem Details view Page When Workitem
 *  Opened From The Inbox". 28/11/2018
 */
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
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

public class INFO_16990 extends SuperClassIWM {
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

	@Test
	public void performingAutoViewFunctionalityForMultipleWorkitemsfromAnyQueueWithGetNextModeSwitchedOff() {
		Log.startTestCase(
				"INFO_16990_VerifyTheAvailabilityOfSendToDefaultUnderTheActionsInTheWorkitemDetailsviewPageWhenWorkitemOpenedFromTheInbox");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8761  is To verify"
					+ " The Availability Of Send To Default Under The Actions In The Workitem Details view Page When Workitem Opened From The Inbox");
			ATUReports.setAuthorInfo("Biswajit", "Nov-2018", "0.3");

			// Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str1 = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str1 + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String viewstr = ExcelLib.getCellValue(xlpath, sheet, 45, 1);
			String viewName = viewstr + util.getSysDate(0, date);
			String columnName = ExcelLib.getCellValue(xlpath, sheet, 46, 1);
			String columnName1 = ExcelLib.getCellValue(xlpath, sheet, 48, 1);
			String columnName2 = ExcelLib.getCellValue(xlpath, sheet, 49, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");
			Thread.sleep(2000);

			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("workitem successfully sent to default queue", true);
			ATUReports.add("workitem successfully sent to default queue", true);
			Log.info("workitem successfully sent to default queue");
			Thread.sleep(2000);
			util.waitForPageToLoad();

			hp.getInbox().click();
			Reporter.log("Inbox tab clicked successfully", true);
			ATUReports.add("Inbox tab clicked successfully", true);
			Log.info("Inbox tab clicked successfully");
			Thread.sleep(2000);

			ip.getDataEntry().click();
			Reporter.log("DataEntry inbox opened successfully ", true);
			ATUReports.add("DataEntry inbox opened successfully ", true);
			Log.info("DataEntry inbox opened successfully ");
			Thread.sleep(2000);

			ip.getDataEntryFirstWorkItem().click();
			Reporter.log("First work Item clicked successfully", true);
			ATUReports.add("First work Item clicked successfully", true);
			Log.info("First work Item clicked successfully");
			Thread.sleep(5000);

			util.waitForPageToLoad();
			wdp.getActionsDropDown().click();
			Reporter.log("Actions Drop Down is clicked", true);
			ATUReports.add("Actions Drop Down is clicked", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			wdp.isElementPresent(wdp.getSendToDefaultOption());

			if (wdp.isElementPresent(wdp.getSendToDefaultOption())) {
				Reporter.log("SendToDefault Option is Displayed in Actions Menu", true);
				ATUReports.add("SendToDefault Option is Displayed in Actions Menu", true);
				Log.info("SendToDefault Option is Displayed in Actions Menu");
				ATUReports.add(
						"Performs verify the availability of send to default under the actions in the workitem details view page when workitem retrieved from the search",
						"", "Send To Default Option should not be Displayed", "SendToDefault Option is not Displayed",
						true);
			} else {
				Reporter.log("SendToDefault Option is not Displayed in Actions Menu", true);
				Log.info("SendToDefault Option is not Displayed in Actions Menu");
			}

		}

		catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add(
					"Failed to Verify The Availability Of Send To Default Under The Actions In The Workitem Details view Page When Workitem Opened From The Inbox",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info(
					"Failed to Verify The Availability Of Send To Default Under The Actions In The Workitem Details view Page When Workitem Opened From The Inbox");

		}

		finally {
			Log.endTestCase(
					"INFO_16990_VerifyTheAvailabilityOfSendToDefaultUnderTheActionsInTheWorkitemDetailsviewPageWhenWorkitemOpenedFromTheInbox");

		}
	}

}
