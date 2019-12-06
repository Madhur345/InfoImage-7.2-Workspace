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
 * @author SinghAvn - 14-June-2018 INFO-7501 This class will Performing
 *         'Document Duplicate' operation with different class for a document
 *         type of workitem by adding notes to the workitem
 **/
public class INFO_7501 extends SuperClassIWM {
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
	public void testVerifyDocumentWorkitemRetrieveFromSearchDifferentClassDocumentDuplicate() {

		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-7577 is for Performing Performing 'Document Duplicate' operation with different class for a document type of workitem by adding notes to the workitem");
		ATUReports.setAuthorInfo("Avnish ", "JUN-2018", "0");

		try {
			Log.startTestCase(
					"This Scenario INFO-7501 is for Performing Performing 'Document Duplicate' operation with same class for a document type of workitem by adding notes to the workitem");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String duplicateworkitem = workitem + "new";
			/*String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			String firstQueue = ExcelLib.getCellValue(xlpath, sheet, 53, 1);*/

			Reporter.log("Class Name : INFO_7501_Performing_Document_Duplicate_operation_with_same_class", true);
			ATUReports.add("Class Name : INFO_7501_Performing_Document_Duplicate_operation_with_same_class", true);

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");

			wdp.getNotesTab().click();
			Reporter.log("Notes button is clicked", true);
			ATUReports.add("Notes button is clicked", true);
			Log.info("Notes button is clicked");

			wdp.getAddNotes_btn().click();
			Reporter.log("Add new Notes button is clicked", true);
			ATUReports.add("Add new Notes button is clicked", true);
			Log.info("Add new Notes button is clicked");

			wdp.getNoteTitle_TF().sendKeys(workitem);
			Reporter.log("Note title is entered", true);
			ATUReports.add("Note title is entered", true);
			Log.info("Note title is entered");

			wdp.getNotedesc_TA().sendKeys(date);
			Reporter.log("Note description is entered", true);
			ATUReports.add("Note description is entered", true);
			Log.info("Note description is entered");

			wdp.getAddnote_btn().click();
			Reporter.log("Add Note button is clicked", true);
			ATUReports.add("Add Note button is clicked", true);
			Log.info("Add Note button is clicked");
			Thread.sleep(5000);

			util.jclick(wdp.getActionButton());
			Reporter.log("clicked on Action button", true);
			ATUReports.add("clicked on Action button", true);
			Log.info("clicked on Action button");
			Thread.sleep(3000);

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
			Reporter.log("INFO-7501 Failed", true);
			Log.info("INFO-7501 Failed");
			ATUReports.add("INFO-7501 Failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing text type file is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase(
					"This Scenario INFO-7501 is for Performing Performing 'Document Duplicate' operation with same class for a document type of workitem by adding notes to the workitem");
		}
	}

}
