package apd.infoimage.iwm.tests;

import java.io.File;
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
 * @author SinghAvn - 18-June-2018 INFO-7539 This class will Perform 'Document
 *         Duplicate' operation with different class for a document type of
 *         workitem with duplicated pages added where document is filed inside
 *         folder type of workitem
 **/
public class INFO_7539 extends SuperClassIWM {
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
	public void documentDuplicate() {

		ATUReports.setTestCaseReqCoverage(
				"INFO-7539  This class will Perform 'Document Duplicate' operation with different class for a document type of workitem with duplicated pages added where document is filed inside folder type of workitem");
		ATUReports.setAuthorInfo("Avnish ", "JUN-2018", "0");

		try {
			Log.startTestCase(
					"This Scenario INFO-7499 will Perform 'Document Duplicate' operation with same/different class for a document type of workitem with pages imported to the workitem with different formats and notes");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String folderworkitem = "Folder" + util.getSysDate(0, date);
			String duplicateworkitem = workitem + "new";

			Reporter.log(
					"Class Name : INFO_7499_Performing_Document_Duplicate_operation_with_same_class_document_type_of_workitem",
					true);
			ATUReports.add(
					"Class Name : INFO_7499_Performing_Document_Duplicate_operation_with_same_class_document_type_of_workitem",
					true);

			cwp.CreateWorkitem(folderworkitem, "archive", "Folder");
			Reporter.log("CreateWorkitem of folder type operation performed", true);
			ATUReports.add("CreateWorkitem of folder type operation performed", true);
			Log.info("CreateWorkitem of folder type operation performed");
			Thread.sleep(4000);

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");

			util.waitForElementEnabled(wdp.getContents_win());
			wdp.getContents_win().click();
			ATUReports.add("Content Tab is clicked", true);

			util.waitForElementEnabled(wdp.getAddNewPageIcon());
			wdp.getAddNewPageIcon().click();
			ATUReports.add("Add new page icon is clicked", true);
			Thread.sleep(3000);

			String tifFilePath = new File("src\\test\\resources").getAbsolutePath();
			String imagePath = tifFilePath + "\\singlePage.tif";
			Reporter.log("img path " + imagePath, true);

			wdp.getContentUploadField().sendKeys(imagePath);
			Reporter.log("new .tiff file is uploaded", true);
			ATUReports.add("new .tiff file is uploaded", true);
			Log.info("new .tiff file is uploaded");

			util.waitForElementEnabled(wdp.getSelectedFile());
			Reporter.log("waiting for the file to upload", true);
			ATUReports.add("waiting for the file to upload", true);
			Log.info("waiting for the file to upload");

			wdp.getAddNewPageUploadBtn().click();
			Reporter.log("Add new Page upload button is clicked", true);
			ATUReports.add("Add new Page upload button is clicked", true);
			Log.info("Add new Page upload button is clicked");
			Thread.sleep(4000);

			wdp.getSelectPageCheckbox().click();
			Reporter.log("select page checkbox is checked", true);
			ATUReports.add("select page checkbox is checked", true);
			Log.info("select page checkbox is checked");
			Thread.sleep(3000);

			wdp.getCopyPage().click();
			Reporter.log("copy page icon is clicked", true);
			ATUReports.add("copy page icon is clicked", true);
			Log.info("copy page icon is clicked");
			Thread.sleep(3000);

			wdp.getPageCopiedCancelButton().click();
			Reporter.log("cancel icon is clicked", true);
			ATUReports.add("cancel page icon is clicked", true);
			Log.info("cancel icon is clicked");
			Thread.sleep(3000);

			wdp.getPastePage().click();
			Reporter.log("paste page icon is clicked", true);
			ATUReports.add("paste page icon is clicked", true);
			Log.info("paste page icon is clicked");
			Thread.sleep(5000);

			util.jclick(hp.getWorkItemTab());
			Thread.sleep(1000);
			Reporter.log("workitem tab is clicked", true);
			ATUReports.add("workitem tab is clicked", true);
			Log.info("workitem tab is clicked");

			cwp.searchByNameInWorkitemList(workitem);
			Reporter.log("searching the workitem in the List", true);
			ATUReports.add("searching the workitem in the List", true);
			Log.info("searching the workitem in the List");
			Thread.sleep(1000);

			cwp.getCheckBoxWorkItemName(workitem).click();
			Reporter.log("clicking on the workitem checkbox", true);
			ATUReports.add("clicking on the workitem checkbox", true);
			Log.info("clicking on the workitem checkbox");
			Thread.sleep(1000);

			cwp.getFileworkitem_btn().click();
			Reporter.log("clicking on the File workitem button", true);
			ATUReports.add("clicking on the File workitem button", true);
			Log.info("clicking on the File workitem button");
			Thread.sleep(1000);

			cwp.selectByWorkitemInFileWorkItemDropDown(folderworkitem);
			Reporter.log("selecting the folder type of workitem from the drop down", true);
			ATUReports.add("selecting the folder type of workitem from the drop down", true);
			Log.info("selecting the folder type of workitem from the drop down");
			Thread.sleep(1000);

			cwp.searchByNameInWorkitemList(folderworkitem);
			Reporter.log("searching  the folder type of workitem", true);
			ATUReports.add("searching  the folder type of workitem", true);
			Log.info("searching  the folder type of workitem");
			Thread.sleep(1000);

			cwp.getWorkItemName(folderworkitem).click();
			Reporter.log("clicking on the folder type of workitem", true);
			ATUReports.add("clicking on the folder type of workitem", true);
			Log.info("clicking on the folder type of workitem");
			Thread.sleep(1000);

			wdp.getActionButton().click();
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

			wdp.getDifferentInvoiceOption().click();
			Reporter.log("clicking on the new class name", true);
			ATUReports.add("clicking on the new class name", true);
			Log.info("clicking on the new class name");
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
			Reporter.log("INFO-7539 Failed", true);
			Log.info("INFO-7539 Failed");
			ATUReports.add("INFO-7539 Failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase(
					"INFO_7539_Performing_Document_Duplicate_operation_with_same_class_document_type_of_workitem is failed");
		}

	}

}