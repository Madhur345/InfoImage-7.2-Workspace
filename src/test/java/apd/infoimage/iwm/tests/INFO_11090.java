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
 * @author GuptaPr2 INFO_11090_Test_to_
 *         verify_Renaming_the_folder_type_of_workitem_while_passing_supported_character_in_the_Workitem_Name_field
 */
public class INFO_11090 extends SuperClassIWM {

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
	public void VerifyRenamingFolderType() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-11090 is forTest to verify Renaming the folder type of workitem while passing supported character in the Workitem Name field");
		ATUReports.setAuthorInfo("princi", "july-2018", "0.3");

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
			String witemName = "new" + util.getSysDate(0, "yyDDMMhhmmss");
			String newWitemName = "New@" + util.getSysDate(0, "yyDDMMhhmmss");
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

			cwp.searchByNameInWorkitemList(newWitemName);
			util.waitForPageToLoad();		
			
			
			boolean newWItemPresent = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(newWitemName));
			if (newWItemPresent) {
				Reporter.log("new workitem name is present in grid", true);
				ATUReports.add("new workitem name is present in grid", true);
				Log.info("new workitem name is present in grid");
				ATUReports.add("Verify workitem is renamed", "new workitem name is" + newWitemName,	"new workitem name should displayed", "New workitem name is displayed in the workitem list", true);

			} else {
				Reporter.log("new workitem name is not present in grid", true);
				ATUReports.add("new workitem name is not present in grid", true);
				Log.info("new workitem name is not present in grid");
				Assert.fail("new workitem name is not present in grid ");
			}
		}

		catch (Exception e) {
			Reporter.log(" verify Renaming the folder type of workitem while passing supported character in the Workitem Name field type test failed",	true);
			ATUReports.add(" verify Renaming the folder type of workitem while passing supported character in the Workitem Name field test failed",	LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("verify Renaming the folder type of workitem while passing supported character in the Workitem Name field");
			Assert.fail(" verify Renaming the folder type of workitem while passing supported character in the Workitem Name field");
		} finally {
			Log.endTestCase("INFO_11090_Test to verify Renaming the folder type of workitem while passing supported character in the Workitem Name field");
		}
	}
}
