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


/**
 * @author SinghAvn
 * @Zypher Id: INFO-10931 Test to verify asynchronous calling of single page for
 *         retrieved workitem in IWM
 */
public class INFO_10931 extends SuperClassIWM {

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
	@Test(enabled = true)
	public void performingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitem() {

		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-10931 is to verify asynchronous calling of single page for retrieved workitem in IWM");
		ATUReports.setAuthorInfo("Avnish Singh ", "11-Aug-2018", "0");

		try {
			Log.startTestCase("INFO_10931_To_verify_asynchronous_calling_single_page_retrieved_workitem");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			int time = 3000;

			Reporter.log("Class Name : INFO_10931_To_verify_asynchronous_calling_single_page_retrieved_workitem", true);
			ATUReports.add("Class Name : INFO_10931_To_verify_asynchronous_calling_single_page_retrieved_workitem",
					true);
			cwp.CreateWorkitem(workitem, className, workitemType);

			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			Reporter.log("Document: " + workitem + " has been created", true);
			ATUReports.add("Document: " + workitem + " has been created", true);

			util.wait(time);

			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			cwp.sendWorkItemToDefaultQueue(workitem);

			util.waitForPageToLoad();
			util.wait(time);

			util.waitForPageToLoad();
			Reporter.log("Retrieving Workitem Using Basic Search", true);
			ATUReports.add("Retrieving Workitem Using Basic Search", true);
			Log.info("Retrieving Workitem Using Basic Search");
			sp.retrieveWorkItemFromSearch(workitem);

			util.waitForPageToLoad();

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			Log.info("Content field is clicked");
			ATUReports.add("Content field is clicked.", true);
			util.wait(time);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			util.wait(time);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");
				ATUReports.add("Add new Page window is displayed", true);

				String tifFilePath = new File("src\\test\\resources").getAbsolutePath();

				String imagePath = tifFilePath + "\\samplePng.PNG";
				Reporter.log("img path " + imagePath, true);
				Log.info("img path " + imagePath);
				ATUReports.add("img path " + imagePath, true);

				String urlBeforeUpload = Driver.driver.getCurrentUrl();

				wdp.getContentUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("samplePng.PNG");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected PNG file name validation successfull.", true);
					Log.info("Selected PNG file name validation successfull.");
					ATUReports.add("Selected PNG file name validation successfull.", true);
				} else {
					Reporter.log("Selected PNG file name validation failed.", true);
					ATUReports.add("Selected PNG file name validation failed", true);
					Assert.fail("Selected PNG file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				util.wait(time);

				wdp.getSelectPageCheckbox().click();
				Reporter.log("checkbox button clicked", true);
				ATUReports.add("checkbox button clicked", true);
				util.wait(time);

				wdp.getExportImage().click();
				Reporter.log("Export Image button clicked", true);
				ATUReports.add("Export Image button clicked", true);
				util.wait(time);

				util.jclick(wdp.getUploadPDF());
				Reporter.log("PDF button clicked", true);
				ATUReports.add("PDF button clicked", true);
				Log.info("PDF button clicked");
				util.wait(time);

				util.jclick(wdp.getDownloadButton());
				Reporter.log("PDF format document successfully downloaded", true);
				ATUReports.add("PDF format document successfully downloaded", true);
				Log.info("PDF format document successfully downloaded");
				util.waitForPageToLoad();
				util.wait(time);

				String urlAfterUpload = Driver.driver.getCurrentUrl();
				Assert.assertEquals(urlAfterUpload, urlAfterUpload);
				if (urlBeforeUpload == urlAfterUpload) {
					Reporter.log("INFO_10931_To_verify_asynchronous_calling_single_page passed", true);
					Log.info("INFO_10931_To_verify_asynchronous_calling_single_page passed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("INFO_10931_To_verify_asynchronous_calling_single_page_retrieved_workitem Failed", true);
			Log.info("INFO_10931_To_verify_asynchronous_calling_single_page_retrieved_workitem Failed");
			// ATUReports.add("INFO_10931_To_verify_asynchronous_calling_single_page_retrieved_workitem
			// Failed",
			// LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing text type file is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO_10931_To_verify_asynchronous_calling_single_page_retrieved_workitem");
		}

	}
}
