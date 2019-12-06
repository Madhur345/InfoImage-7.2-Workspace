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

public class INFO_11119 extends SuperClassIWM {
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
				"This Scenario INFO_11119 is Performing Document Duplicate Operation With Same Different Class For Document Type Of Workitem With Index Fields Defined in WorkitemsPage");
		ATUReports.setAuthorInfo("Biswajit ", "Aug-2018", "0");

		try {
			Log.startTestCase(
					"INFO_11119_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWithIndexFieldsDefinedinWorkitemsPage");
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
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			String firstQueue = ExcelLib.getCellValue(xlpath, sheet, 53, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);

			Reporter.log("Class Name : INFO_7527_Performing_Document_Duplicate_operation_with_different_class", true);
			ATUReports.add("Class Name : INFO_7527_Performing_Document_Duplicate_operation_with_different_class", true);

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

			wdp.getActionBtn().click();
			Reporter.log("Action Button clicked", true);
			ATUReports.add("Action Button clicked", true);
			Log.info("Action Button clicked");

			wdp.getformField().click();
			Reporter.log("Formfield open button clicked", true);
			ATUReports.add("Formfield open button clicked.", true);
			Log.info("Formfield open button clicked");
			Thread.sleep(2000);

			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys(idCodeStr);
			Reporter.log("Idcode has been written", true);
			ATUReports.add("Idcode has been written", true);
			Log.info("Idcode has been written");
			Thread.sleep(2000);

			wdp.getupdateFormButton().click();
			Reporter.log("Update Button clicked", true);
			ATUReports.add("Update Button clicked", true);
			Log.info("Update Button clicked");
			Thread.sleep(2000);

			hp.getWorkItemTab().click();
			Reporter.log("WorkItem Tab clicked", true);
			ATUReports.add("WorkItem Tab clicked", true);
			Log.info("WorkItem Tab clicked");

			hp.searchByNameInWorkitemTabAndDisplay(workitem);
			Reporter.log("Searching By Workitem Name And Display In Workitem tab", true);
			ATUReports.add("Searching By Workitem Name And Display In Workitem tab", true);
			boolean createdWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (createdWorkitemPresence) {
				Reporter.log("Created Workitem is present in grid", true);
				ATUReports.add("Created Workitem is present in grid", true);
			} else {
				Reporter.log("Created Workitem is not present in grid", true);
				ATUReports.add("Created Workitem is not present in grid", true);
				Assert.fail("Created Workitem is not present in grid ");
			}

			cwp.getCheckBoxWorkItem(workitem).click();
			Reporter.log("Selected: " + workitem + " to rename the workitem", true);
			ATUReports.add("Selected: " + workitem + " to rename the workitem", true);
			Log.info("workitem checkbox seleted");

			//// button[@class='actionBtn']
			wdp.getActionBtn().click();
			Reporter.log("Action Button clicked", true);
			ATUReports.add("Action Button clicked", true);
			Log.info("Action Button clicked");

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

			// wdp.getInvoiceno_TF().click();

			wdp.getIncludeFormDataCheckBox().click();
			Reporter.log("Include Form Data CheckBox clicked", true);
			ATUReports.add("clicking on the new class name", true);
			Log.info("clicking on the new class name");

			wdp.getDuplicateButton().click();
			Reporter.log("clicked on Document Duplicate button", true);
			ATUReports.add("clicked on Document Duplicate button", true);
			Log.info("clicked on Document Duplicate button");
			Thread.sleep(3000);

			wdp.getWorkItemsTab().click();
			Reporter.log("clicking on workitem tab", true);
			ATUReports.add("clicking on workitem tab", true);
			Log.info("clicking on workitem tab");
			Thread.sleep(3000);

			cwp.searchByNameInWorkitemList(duplicateworkitem);
			Reporter.log("searching the duplicate workitem", true);
			ATUReports.add("searching the duplicate workitem", true);
			Log.info("searching the duplicate workitem");
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("INFO-11119 Failed", true);
			Log.info("INFO-11119 Failed");
			ATUReports.add("INFO-11119 Failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing text type file is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase(
					"INFO_11119_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWithIndexFieldsDefinedinWorkitemsPage");
		}
	}

}
