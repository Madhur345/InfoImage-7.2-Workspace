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

/**
 * @author DashBisw INFO_11065 This class is To verify "No Alert Message At The
 *         Time You Type A Not Supported Character In The Workitem Name Field
 *         While Performing Rename Folder Type Of Workitem". 30/07/2018
 */
public class INFO_11065 extends SuperClassIWM {

	@BeforeMethod
	public void beforMethod() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod() {
		Log.endTestCase(
				"INFO_11065_VerifyNoAlertMessageAtTheTimeYouTypeANotSupportedCharacterInTheWorkitemNameFieldWhilePerformingRenameWorkitemForFolderTypeOfWorkitem");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void VerifyNoAlertMessage() {
		Log.startTestCase(
				"INFO_11065_VerifyNoAlertMessageAtTheTimeYouTypeANotSupportedCharacterInTheWorkitemNameFieldWhilePerformingRenameWorkitemForFolderTypeOfWorkitem");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_11065  is To verify"
					+ "No Alert Message At The Time You Type A Not Supported Character In The Workitem Name Field While Performing Rename Workitem For Folder Type Of Workitem");
			ATUReports.setAuthorInfo("DashBisw", "JULY-2018", "0.3");

			// Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str1 = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str1 + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);
			String viewstr = ExcelLib.getCellValue(xlpath, sheet, 45, 1);
			String viewName = viewstr + util.getSysDate(0, date);
			String columnName = ExcelLib.getCellValue(xlpath, sheet, 46, 1);
			String columnName1 = ExcelLib.getCellValue(xlpath, sheet, 48, 1);
			String columnName2 = ExcelLib.getCellValue(xlpath, sheet, 49, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);

			StringBuilder builder = new StringBuilder();
			builder.append("str1!@#$^&(){}?");
			String newworkitem = builder.toString();

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("workitem Created successfully", true);
			ATUReports.add("workitem Created successfully", true);
			Log.info("workitem Created sucessfully");
			Thread.sleep(5000);

			// Select the created workitem
			cwp.getCheckBoxWorkItem(workitem).click();
			Reporter.log("Selected: " + workitem + " to rename the workitem", true);
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
					"Verify No Alert Message At The Time You Type A Not Supported Character In The Workitem Name Field While Performing Rename Workitem For Folder Type Of Workitem",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		}

		finally {

			hp.logoutApp();
		}
	}

}