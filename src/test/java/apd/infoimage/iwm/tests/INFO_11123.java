package apd.infoimage.iwm.tests;

/**
 * @author DashBisw INFO_11123 This class is To Performing Document Duplicate Operation 
 * With Same Different Class For Document Type Of Workitem Without Index Fields Defined 
 * in detailed view Page". 13/08/2018
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

public class INFO_11123 extends SuperClassIWM {
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
	@Test(enabled = true, priority = 1, groups = { "DocumentDuplicate" })
	public void performingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitem() {

		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO_11123 Performing Document Duplicate Operation With Same Different Class For Document Type Of Workitem Without Index Fields Defined in Detailed View Page");
		ATUReports.setAuthorInfo("DashBiswa ", "Aug-2018", "0");

		try {
			Log.startTestCase(
					"INFO_11123_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWithoutIndexFieldsDefinedinDetailedViewPage");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String duplicateworkitem = workitem + "new";
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log(
					"Class Name : INFO_11123_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWithoutIndexFieldsDefinedinDetailedViewPage",
					true);
			ATUReports.add(
					"Class Name : INFO_11123_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWithoutIndexFieldsDefinedinDetailedViewPage",
					true);

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("workitem Created sucessfully", true);
			ATUReports.add("workitem Created sucessfully", true);
			Log.info("workitem Created sucessfully");
			Thread.sleep(5000);

			// Select the created workitem
			cwp.getCheckBoxWorkItem(workitem).click();
			Reporter.log("Selected: " + workitem + " to rename the workitem", true);
			ATUReports.add("Selected: " + workitem + " to rename the workitem", true);
			Log.info("workitem checkbox seleted");

			wdp.getwiView().click();
			Reporter.log("WorkItem successfully viewed", true);
			ATUReports.add("WorkItem successfully viewed", true);
			Log.info("WorkItem successfully viewed");
			Thread.sleep(2000);

			wdp.getActionsDropDown().click();
			Reporter.log("Action drop down Button clicked", true);
			ATUReports.add("Action drop down  Button clicked", true);
			Log.info("Action drop down  Button clicked");

			wdp.getDocumentDuplicateOption().click();
			Reporter.log("clicked on Document Duplicate Link", true);
			ATUReports.add("clicked on Document Duplicate Link", true);
			Log.info("clicked on Document Duplicate Link");
			Thread.sleep(3000);

			wdp.getDocumentDuplicateWorkitemNameTextBox().clear();
			Reporter.log("clearing the Document Duplicate textbox", true);
			ATUReports.add("clearing the Document Duplicate textbox", true);
			Log.info("clearing the Document Duplicate textbox");

			wdp.getDocumentDuplicateWorkitemNameTextBox().sendKeys(duplicateworkitem);
			Reporter.log("passing the duplicate workitem name", true);
			ATUReports.add("passing the duplicate workitem name", true);
			Log.info("passing the duplicate workitem name");
			Thread.sleep(3000);

			wdp.getinvoice().click();
			Reporter.log("clicking on the new class name", true);
			ATUReports.add("clicking on the new class name", true);
			Log.info("clicking on the new class name");
			Thread.sleep(3000);

			wdp.getIncludeFormDataCheckBox().click();
			Reporter.log("Include Form Data CheckBox clicked", true);
			ATUReports.add("clicking on the new class name", true);
			Log.info("clicking on the new class name");

			String alertmsg = "* Please fill the mandatory fields to include form data";
			String alertmsg1 = wdp.getmandatoryFieldError().getText();

			if (alertmsg.equals(alertmsg1)) {
				ATUReports.add("Alert message is displaying * Please fill the mandatory fields to include form data",
						true);
				Reporter.log("Alert message is displaying * Please fill the mandatory fields to include form data",
						true);
				Log.info("Alert message is displaying '* Please fill the mandatory fields to include form data");

			} else {
				ATUReports.add("Alert message is not coming", true);
				Reporter.log("Alert message is not coming", true);
				Log.info("Alert message is not coming.");
			}
			wdp.getDuplicateButton().click();
			Reporter.log("clicked on Document Duplicate button", true);
			ATUReports.add("clicked on Document Duplicate button", true);
			Log.info("clicked on Document Duplicate button");
			util.waitForPageToLoad();
			util.wait(time);
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("INFO-11123 Failed", true);
			Log.info("INFO-11123 Failed");
			ATUReports.add("INFO-11123 Failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing text type file is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase(
					"INFO_11123_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWithoutIndexFieldsDefinedinDetailedViewPage");
		}
	}

}
