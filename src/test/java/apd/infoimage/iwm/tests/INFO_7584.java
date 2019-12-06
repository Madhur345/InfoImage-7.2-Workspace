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
 * @author SumanGaK - 04-May-2018 
 * INFO-7584 
 * This class will Verify To export Multiple JPG images to multipage PDF image format from document type
 * of workitem in IWM
 */
public class INFO_7584
extends SuperClassIWM {

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
	public void testVerifyExportMultipleJPGImagesToMultipagePDFImageFormatFromDocumentWorkitem() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7584 is for Verifying To export Multiple JPG images to multipage PDF image format from document type of workitem in IWM");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try {
			Log.startTestCase("INFO_7584_VerifyExportMultipleJPGImagesToMultipagePDFImageFormatFromDocumentWorkitemTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String imagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 27, 1);
			String secondImagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 29, 1);
			String downloadPath = ExcelLib.getCellValue(xlpath, sheet, 30, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Class Name : INFO_7584_VerifyExportMultipleJPGImagesToMultipagePDFImageFormatFromDocumentWorkitemTest",true);
			ATUReports.add("Class Name : INFO_7584_VerifyExportMultipleJPGImagesToMultipagePDFImageFormatFromDocumentWorkitemTest",true);
			Log.info("Class Name : INFO_7584_VerifyExportMultipleJPGImagesToMultipagePDFImageFormatFromDocumentWorkitemTest");

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

			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");

			util.wait(time);
			wdp.getContentField().click();
			util.wait(time);
			wdp.getAddNewPageIcon().click();
			util.wait(time);
			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				util.wait(time);

				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				Log.info("img path " + imagePath);

				wdp.getContentUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				util.waitForPageToLoad();

				wdp.getAddNewPageIcon().click();
				util.wait(time);

				Reporter.log("second Image Path : " + secondImagePath, true);
				ATUReports.add("second Image Path : " + secondImagePath, true);
				Log.info("second Image Path : " + secondImagePath);

				wdp.getContentUploadField().sendKeys(secondImagePath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				util.waitForPageToLoad();

				util.wait(time);

				wdp.getSelectAllCheckbox().click();
				Reporter.log("Select All Checkbox is clicked", true);
				ATUReports.add("Select All Checkbox is clicked", true);
				Log.info("Select All Checkbox is clicked");
				util.wait(time);

				wdp.getExportImage().click();
				Reporter.log("Export button is clicked", true);
				ATUReports.add("Export button is clicked", true);
				Log.info("Export button is clicked");

				util.wait(time);
				util.jclick(wdp.getDownloadButton());
				Reporter.log("download button is clicked ", true);
				ATUReports.add("download button is clicked", true);
				Log.info("download button is clicked");
				util.wait(time);

				util.waitForPageToLoad();
				util.wait(time);

				File filePath=wdp.getLatestFilefromDir(downloadPath);

				Reporter.log("Get Latest File : "+filePath, true);
				ATUReports.add("Get Latest File : "+filePath, true);
				Log.info("Get Latest File : "+filePath);

				String fileName=(wdp.getLatestFilefromDir(downloadPath)).toString().substring(28);
				Reporter.log("fileName : "+fileName, true);
				ATUReports.add("fileName : "+fileName, true);
				Log.info("fileName : "+fileName);

				boolean filePresence=wdp.isFileDownloaded(downloadPath,fileName);
				if(filePresence)
				{
					Reporter.log("File is Present : "+fileName, true);
					ATUReports.add("File is Present : "+fileName, true);
					Log.info("File is Present : "+fileName);
				}
				else				
				{
					Reporter.log("File is not Present", true);
					ATUReports.add("File is not Present", true);
					Log.info("File is not Present");
				}
			} else {
				Reporter.log("Add new Page in workitem window is not present", true);
				Log.info("Add new Page in workitem window is not present");
				ATUReports.add("Add new Page in workitem window is not present", LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Add new Page in workitem window is not present ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add("failed to Verify ExportJPG_TIFAndPDFImages To Multipage PDF Image Format From Document Workitem",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyExportJPG_TIFAndPDFImagesToMultipagePDFImageFormatFromDocumentWorkitem is failed");
		} finally {
			Log.endTestCase("INFO_7584_VerifyExportMultipleJPGImagesToMultipagePDFImageFormatFromDocumentWorkitemTest");
		}
	}
}
