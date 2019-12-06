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
 * @author SinghAvn 13 -Jun-2018 INFO-7577 This class will Perform Document
 *         Duplicate operation with different class for a document type of
 *         workitem which is retrieved from Search
 **/
public class INFO_7577 extends SuperClassIWM {
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
	public void exportDuplicateImage() {

		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-7577 is for Performing Document Duplicate operation with different Class for a document type of workitem which is retrieved from Search");
		ATUReports.setAuthorInfo("Avnish ", "JUN-2018", "0");

		try {
			Log.startTestCase(
					"INFO_7577_To_add_an_image_to_one_workitem_and_export_duplicate_image_of_the_imported_image");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String duplicateworkitem = workitem + "new";
			Reporter.log(
					"Class Name : INFO_7577_To_add_an_image_to_one_workitem_and_export_duplicate_image_of_the_imported_image",
					true);
			ATUReports.add(
					"Class Name : INFO_7577_To_add_an_image_to_one_workitem_and_export_duplicate_image_of_the_imported_image",
					true);

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			Log.info("Content field is clicked");

			wdp.getAddNewPageIcon().click();
			Reporter.log("Add new Page icon is clicked", true);
			ATUReports.add("Add new Page icon is clicked", true);
			Log.info("Add new Page icon is clicked");

			Reporter.log("Add new Page window is displayed", true);
			ATUReports.add("Add new Page window is displayed", true);
			Log.info("Add new Page window is displayed");

			String path = new File("src\\test\\resources").getAbsolutePath();
			String imagePath = path + "\\150dpi.tiff";
			Reporter.log("img path " + imagePath, true);
			ATUReports.add("img path " + imagePath, true);

			Thread.sleep(5000);
			wdp.getContentUploadField().sendKeys(imagePath);
			Reporter.log("new .pdf file is uploaded", true);
			ATUReports.add("new .pdf image is uploaded", true);
			Log.info("new .pdf file image is uploaded");

			util.waitForElementEnabled(wdp.getSelectedFile());
			Reporter.log("waiting for the image to upload", true);
			ATUReports.add("waiting for the image to upload", true);
			Log.info("waiting for the image to upload");

			wdp.getAddNewPageUploadBtn().click();
			Reporter.log("Add new Page upload button is clicked", true);
			ATUReports.add("Add new Page upload button is clicked", true);
			Log.info("Add new Page upload button is clicked");

			util.jclick(wdp.getSelectPageCheckbox());
			Reporter.log("select page checkbox is checked", true);
			ATUReports.add("select page checkbox is checked", true);
			Log.info("select page checkbox is checked");

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
			Reporter.log("INFO-7577 Failed", true);
			Log.info("INFO-7577 Failed");
			ATUReports.add("INFO-7577 Failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing text type file is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase(
					"INFO-7577 This Scenario will Perform Document Duplicate operation with different class for a document type workitem which is retrieved from Search");
		}
	}
}
