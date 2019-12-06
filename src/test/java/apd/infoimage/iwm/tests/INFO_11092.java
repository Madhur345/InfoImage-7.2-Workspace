package apd.infoimage.iwm.tests;

/**
 * @author DashBisw INFO_11092  This class is To verify 
 * "Rename The Retrieved Document Type Of Workitem While 
 * Passing Supported Character Passing In The Workitem Name Field. 02/08/2018
 */
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

public class INFO_11092 extends SuperClassIWM {

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
	@Test
	public void VerifyAlertMessage() {
		Log.startTestCase(
				"INFO_11092_VerifyRenameTheRetrievedDocumentTypeOfWorkitemWhilePassingSupportedCharacterPassingInTheWorkitemNameField");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_11092  is To verify"
					+ "Verify Rename The Retrieved Document Type Of Workitem While Passing Supported Character Passing In The Workitem Name Field");
			ATUReports.setAuthorInfo("Biswajit", "JULY-2018", "0.3");

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
			/*
			 * String viewstr = ExcelLib.getCellValue(xlpath, sheet, 45, 1); String viewName
			 * = viewstr + util.getSysDate(0, date); String columnName =
			 * ExcelLib.getCellValue(xlpath, sheet, 46, 1); String columnName1 =
			 * ExcelLib.getCellValue(xlpath, sheet, 48, 1); String columnName2 =
			 * ExcelLib.getCellValue(xlpath, sheet, 49, 1); String idCodeStr =
			 * ExcelLib.getCellValue(xlpath, sheet, 9, 1);
			 */

			StringBuilder builder = new StringBuilder();
			builder.append("str1!@#$^&(){}?");
			String newworkitem = builder.toString();

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("workitem Created sucessfully", true);
			ATUReports.add("workitem Created sucessfully", true);
			Log.info("workitem Created sucessfully");
			Thread.sleep(5000);

			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("workitem successfully sent to default queue", true);
			ATUReports.add("workitem successfully sent to default queue", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.retrieveWorkItem(workitem);
			Reporter.log("Retrieving Workitem", true);
			ATUReports.add("Retrieving Workitem", true);
			Log.info("Retrieving Workitem");

			// Select the created workitem
			cwp.getCheckBoxWorkItem(workitem).click();
			Reporter.log("Selected: " + workitem + " to rename the workitem.", true);
			ATUReports.add("Selected: " + workitem + " to rename the workitem", true);
			Log.info("workitem checkbox seleted");

			// Click on the rename workitem button
			cwp.getRenameWorkItemBtn().click();
			Thread.sleep(3000);
			boolean renameWinPresent = util.verifyObjectPresentReturnsBool(cwp.getRenameWorkItemWin());

			if (renameWinPresent) {
				Reporter.log("Rename Workitem dialog box is open", true);
				ATUReports.add("Rename Workitem dialog box is open", true);
				Log.info("Rename Workitem dialog box is open");
			} else {
				Reporter.log("Rename Work item dialog box is not open", true);
				ATUReports.add("Rename Work item dialog box is not open", true);
				Assert.fail("Rename Work item dialog box is not open");
				Log.info("Rename Work item dialog box is not open");
			}

			Reporter.log("New workItem name is " + newworkitem, true);
			ATUReports.add("New workItem name is " + newworkitem, true);
			Thread.sleep(2000);

			// Rename the work item
			cwp.getNewWorkItemName_TF().clear();
			Thread.sleep(2000);
			cwp.getNewWorkItemName_TF().sendKeys(newworkitem);

			Thread.sleep(2000);
			util.jclick(cwp.getRenameWorkItemAcceptBtn());
			ATUReports.add("Rename accept button clicked", true);
			Reporter.log("Rename accept button clicked", true);
			Log.info("Rename accept button clicked");

			Reporter.log("Alert message is not coming", true);
			ATUReports.add("Alert message is not coming.", true);
			Log.info("Alert message is not coming.");
			Thread.sleep(5000);

		}

		catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add(
					"Verify Rename The Retrieved Document Type Of Workitem While Passing Supported Character Passing In The Workitem Name Field",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		}

		finally {
			Log.endTestCase(
					"INFO_11092_VerifyRenameTheRetrievedDocumentTypeOfWorkitemWhilePassingSupportedCharacterPassingInTheWorkitemNameField");

		}
	}
}
