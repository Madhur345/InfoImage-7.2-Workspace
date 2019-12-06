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
 * @author SinghAvn INFO-11059 Test to verify alert message at the time you type
 *         a not supported character in the Workitem Name field while performing
 *         Rename workitem for document type of workitem
 */
public class INFO_11059 extends SuperClassIWM {

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
	public void testVerifyFolderWorkitemRetrieveFromInboxDocumentDuplicate() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-11090 is to verify Renaming the folder type of workitem while passing not supported character in the Workitem Name field");
		ATUReports.setAuthorInfo("SinghAvn", "July-2018", "0.3");

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
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			String witemName = "new" + util.getSysDate(0, "yyDDMMhhmmss");
			String newWitemName = "New@" + util.getSysDate(0, "yyDDMMhhmmss");

			Reporter.log(
					"Class Name : verify Renaming the folder type of workitem while passing not supported character in the Workitem Name field",
					true);
			ATUReports.add(
					"Class Name :verify Renaming the folder type of workitem while passing not supported character in the Workitem Name field",
					true);
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
			Thread.sleep(3000);
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
			Thread.sleep(2000);

			// Rename the work item
			cwp.getNewWorkItemName_TF().clear();
			Thread.sleep(2000);
			cwp.getNewWorkItemName_TF().sendKeys(newWitemName);
			Reporter.log("Entering the new workitem name as" + newWitemName, true);
			ATUReports.add("Entering the new workitem name as" + newWitemName, true);

			Thread.sleep(2000);
			util.jclick(cwp.getRenameWorkItemAcceptBtn());
			Reporter.log("Clicking on the rename button");
			ATUReports.add("Clicking on the rename button", true);
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(newWitemName);
			Reporter.log("Searching for the workitem");
			ATUReports.add("Searching for the workitem", true);
			util.waitForPageToLoad();
			boolean newWItemPresent = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(newWitemName));
			if (newWItemPresent) {
				ATUReports.add("Verify workitem is renamed", "new workitem name is" + newWitemName,
						"new workitem name should displayed", "New workitem name is displayed in the workitem list",
						true);

			}
		}

		catch (Exception e) {
			Reporter.log(
					" verify Renaming the folder type of workitem while passing not supported character in the Workitem Name field type test failed",
					true);
			ATUReports.add(
					" verify Renaming the folder type of workitem while passing not supported character in the Workitem Name field test failed",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info(
					"verify Renaming the folder type of workitem while passing not supported character in the Workitem Name field");
			Assert.fail(
					" verify Renaming the folder type of workitem while passing not supported character in the Workitem Name field");
		} finally {
			Log.endTestCase(
					"INFO_11059_Test to verify Renaming the folder type of workitem while passing not supported character in the Workitem Name field");
		}
	}
}
