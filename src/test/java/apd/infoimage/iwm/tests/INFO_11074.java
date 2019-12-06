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

public class INFO_11074 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1)
	public void testVerifyWarningMessage() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-11074 is for Test to verify Renaming the folder type of workitem while passing supported character in the Workitem Name field");
		ATUReports.setAuthorInfo("GuptaPr2", "july-2018", "0.3");

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
			String witemName = "new" + util.getSysDate(0, "yyDDMMhhmmss");
			String newWitemName = "New/" + util.getSysDate(0, "yyDDMMhhmmss");
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Class Name : verify Renaming the folder type of workitem while passing supported character in the Workitem Name field", true);
			ATUReports.add("Class Name :verify Renaming the folder type of workitem while passing supported character in the Workitem Name field", true);
			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getCheckBoxWorkItem(workitem).click();
			Reporter.log("Selected: " + witemName + " to rename the workitem", true);
			ATUReports.add("Selected: " + witemName + " to rename the workitem", true);

			// Click on the rename workitem button
			cwp.getRenameWorkItemBtn().click();
			util.wait(time);
			boolean renameWinPresent = util.verifyObjectPresentReturnsBool(cwp.getRenameWorkItemWin());

			if (renameWinPresent) {
				Reporter.log("Rename Workitem dialog box is open", true);
				ATUReports.add("Rename Workitem dialog box is open", true);
			} else {
				Reporter.log("Rename Work item dialog box is not open", true);
				ATUReports.add("Rename Work item dialog box is not open", true);
				Assert.fail("Rename Work item dialog box is not open");
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
			Reporter.log(" verify Renaming the folder type of workitem while passing supported character in the Workitem Name field type test failed", true);
			ATUReports.add(" verify Renaming the folder type of workitem while passing supported character in the Workitem Name field test failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("verify Renaming the folder type of workitem while passing supported character in the Workitem Name field");
			Assert.fail(" verify Renaming the folder type of workitem while passing supported character in the Workitem Name field");
		} finally {
			Log.endTestCase("INFO_11074_Test to verify Renaming the folder type of workitem while passing supported character in the Workitem Name field");
		}
	}
}
