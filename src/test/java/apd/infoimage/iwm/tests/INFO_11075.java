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

public class INFO_11075 extends SuperClassIWM {

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
	public void VerifyWarningMessage() {
		Log.startTestCase("INFO_11075_VerifyIconForRetrievedWorkitemOfDocumentType");
		ATUReports.setTestCaseReqCoverage("This Scenario will Test to verify "
				+ "warning message while performing Rename functionality with unsupported character in the Workitem Name field for retrieved folder type of workitem");
		ATUReports.setAuthorInfo("GuptaPr2", "JUL-2018", "0.3");
		try {
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			String newWitemName = "New%" + util.getSysDate(0, "yyDDMMhhmmss");

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
			Reporter.log("workitem Created sucessfully", true);
			ATUReports.add("workitem Created sucessfully", true);
			Log.info("workitem Created sucessfully");
			util.wait(time);
			util.wait(time);

			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log(" workitem successfully sent to default queue", true);
			ATUReports.add(" workitem successfully sent to default queue", true);
			util.wait(time);
			util.waitForPageToLoad();

			ip.retrieveWorkItem(workitem);
			Reporter.log("Retrieving Workitem", true);
			ATUReports.add("Retrieving Workitem", true);
			Log.info("Retrieving Workitem");

			// Select the created workitem
			cwp.getCheckBoxWorkItem(workitem).click();
			Reporter.log("Selected: " + workitem + " to rename the workitem", true);
			ATUReports.add("Selected: " + workitem + " to rename the workitem", true);
			Log.info("workitem checkbox seleted");

			// Click on the rename workitem button
			cwp.getRenameWorkItemBtn().click();
			util.wait(time);
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

			Reporter.log("New workItem name is " + newWitemName, true);
			ATUReports.add("New workItem name is " + newWitemName, true);
			util.wait(time);

			// Rename the work item
			cwp.getNewWorkItemName_TF().clear();
			util.wait(time);
			cwp.getNewWorkItemName_TF().sendKeys(newWitemName);

			util.wait(time);
			util.jclick(cwp.getRenameWorkItemAcceptBtn());
			ATUReports.add("Rename accept button clicked", true);
			Log.info("Rename accept button clicked");
			util.waitForPageToLoad();

			String alertmsg = "Invalid workitem name passed.";
			String alertmsg1 = cwp.getalertMessage().getText();

			if (alertmsg.equals(alertmsg1)) {
				Reporter.log("Alert message is displaying 'Invalid workitem name passed.'", true);
				ATUReports.add("Alert message is displaying 'Invalid workitem name passed.'", true);
				Log.info("Alert message is displaying 'Invalid workitem name passed.");

			} else {
				Reporter.log("Alert message is not coming", true);
				ATUReports.add("Alert message is not coming", true);
				Log.info("Alert message is not coming.");
			}

		}

		catch (Exception e) {
			Reporter.log(" INFO_11075_VerifyIconForRetrievedWorkitemOfDocumentType test failed", true);
			ATUReports.add("INFO_11075_VerifyIconForRetrievedWorkitemOfDocumentType test failed", LogAs.FAILED,	new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("INFO_11075_VerifyIconForRetrievedWorkitemOfDocumentType test failed");
			Assert.fail("INFO_11075_VerifyIconForRetrievedWorkitemOfDocumentType test failed");
		} finally {
			Log.endTestCase("INFO_11075_VerifyIconForRetrievedWorkitemOfDocumentType");
		}
	}
}