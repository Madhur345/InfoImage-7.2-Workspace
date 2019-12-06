package apd.infoimage.iwm.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
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

public class INFO_7586 extends SuperClassIWM {

	@BeforeMethod
	public void beforMethod() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod() {
		hp.logoutApp();
	}

	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 1)
	public void testVerifyExportJPG_TIFAndPDFImagesToMultipageTIFImageFormatFromDocumentWorkitem() throws InterruptedException {

		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("INFO-7586 This Scenario will export JPG,TIF and PDF images to multipage TIF image format from document type of workitem in IWM");
		ATUReports.setTestCaseReqCoverage("INFO-7586 This Scenario will export JPG,TIF and PDF images to multipage TIF image format from document type of workitem in IWM");
		ATUReports.setAuthorInfo("Princy", "MAY-2018", "1.0");
		try {
			Log.startTestCase("INFO_7586_To_export_JPG_TIF_and_PDF_images_to_multipage_TIF_image_format_from_document_type_of_workitem_in_IWM");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String imagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 5, 1);
			String secondImagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 27, 1);
			String thirdImagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 28, 1);
			String downloadPath = ExcelLib.getCellValue(xlpath, sheet, 30, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Class Name : INFO_7586_To_export_JPG_TIF_and_PDF_images_to_multipage_TIF_image_format_from_document_type_of_workitem_in_IWMTest",	true);
			ATUReports.add("Class Name : INFO_7586_To_export_JPG_TIF_and_PDF_images_to_multipage_TIF_image_format_from_document_type_of_workitem_in_IWMTest", true);
			Log.info("Class Name : INFO_7586_To_export_JPG_TIF_and_PDF_images_to_multipage_TIF_image_format_from_document_type_of_workitem_in_IWMTest");

			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);

			cwp.CreateWorkitem(workitem,className,workitemType);

			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");

			Reporter.log("Document: "+workitem+" has been created",true);
			ATUReports.add("Document: "+workitem+" has been created",true);
			Log.info("Document: "+workitem+" has been created");
			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView is displayed", true);
			ATUReports.add("WorkItemDetailView is displayed", true);
			Log.info("WorkItemDetailView is displayed");

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

				wdp.getAddNewPageIcon().click();
				util.wait(time);

				Reporter.log("third Image Path : " + thirdImagePath, true);
				ATUReports.add("third Image Path : " + thirdImagePath, true);
				Log.info("third Image Path : " + thirdImagePath);

				wdp.getContentUploadField().sendKeys(thirdImagePath);

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
				util.wait(time);

				wdp.getExportImage().click();
				Reporter.log("Export Image Icon is clicked", true);
				Log.info("Export Image Icon is clicked");
				ATUReports.add("Export Image Icon is clicked", true);
				util.wait(time);

				util.jclick(wdp.getUploadTIF());
				Reporter.log("TIF radio button is clicked", true);
				Log.info("TIF radio button is clicked");
				ATUReports.add("TIF radio button is clicked", true);

				util.wait(time);
				util.jclick(wdp.getDownloadButton());
				Reporter.log("Download link is clicked", true);
				Log.info("Download link is clicked");
				ATUReports.add("Download link is clicked", true);

				util.waitForPageToLoad();
				util.wait(time);

				File filePath=wdp.getLatestFilefromDir(downloadPath);

				Reporter.log("Get Latest File : "+filePath, true);
				ATUReports.add("Get Latest File : "+filePath, true);
				Log.info("Get Latest File : "+filePath);

				String fileName=(wdp.getLatestFilefromDir(downloadPath)).toString().substring(28);
				Reporter.log("fileName : "+fileName, true);

				boolean filePresence=wdp.isFileDownloaded(downloadPath,fileName);
				if(filePresence)
				{
					Reporter.log("File is Present : "+fileName, true);
					ATUReports.add("File is Present : "+fileName, true);
					Log.info("File is Present : "+fileName);
					
					Reporter.log("Multipage TIF file is downloaded successfully", true);
					Log.info("Multipage TIF file is downloaded successfully");
					ATUReports.add("Multipage TIF file is downloaded successfully", true);

				}
				else				
				{
					Reporter.log("File is not Present", true);
					ATUReports.add("File is not Present", true);
					Log.info("File is not Present");
				}

			} else {
				Reporter.log("Add new Page in workitem window is not present", true);
				ATUReports.add("Add new Page in workitem window is not present", true);
				Log.info("Add new Page in workitem window is not present");
				Assert.fail("Add new Page in workitem window is not present ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add("failed to Verify ExportJPG_TIFAndPDFImages To Multipage TIF Image Format From Document Workitem",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyExportJPG_TIFAndPDFImagesToMultipageTIFImageFormatFromDocumentWorkitem is failed");
		} finally {
			Log.endTestCase("INFO_7586_VerifyExportJPG_TIFAndPDFImagesToMultipageTIFImageFormatFromDocumentWorkitemTest");
		}
	}
}
