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
/**
 * @author Biswajit - 06-June-2018 INFO_7554 This class Perform To Export 
 * TIF Image To TIF Format From Document Type Of Workitem In IWM
 */
public class INFO_7554 extends SuperClassIWM {

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
	public void testSameWorkItemInUserQueue() {

		DOMConfigurator.configure("log4j.xml");
		ATUReports.setTestCaseReqCoverage("INFO-7554 This Scenario will export TIF image to TIF format from document type of workitem in IWM");
		ATUReports.setAuthorInfo("Biswajit", "June-2018", "1.0");
		try {

			Log.startTestCase("INFO-7554 This Scenario will export TIF image to TIF format from document type of workitem in IWM");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String downloadPath = ExcelLib.getCellValue(xlpath, sheet, 30, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("VerifyImageDPITest",true);
			ATUReports.add("VerifyImageDPITest",true);
			Log.info("VerifyImageDPITest");
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

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			util.wait(time);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			util.wait(time);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			util.wait(time);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);

				String path = new File("src\\test\\resources").getAbsolutePath();

				String imagePath = path + "\\150dpi.tiff";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);

				wdp.getContentUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedFile());

				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add new page upload button clicked", true);
				ATUReports.add("Add new page upload button clicked", true);
				util.wait(time);
				util.wait(time);
				util.wait(time);

				wdp.getSelectPageCheckbox().click();
				Reporter.log("checkbox button clicked", true);
				ATUReports.add("checkbox button clicked", true);
				util.wait(time);

				wdp.getExportImage().click();
				Reporter.log("Export Image button clicked", true);
				ATUReports.add("Export Image button clicked", true);
				util.wait(time);
				util.wait(time);

				util.jclick(wdp.getexportTIF());
				Reporter.log("Export to TIF button clicked", true);
				ATUReports.add("Export to TIF button clicked", true);
				util.wait(time);

				util.jclick(wdp.getDownloadButton());
				Reporter.log("TIF format document successfully downloaded", true);
				ATUReports.add("TIF format document successfully downloaded", true);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
			}

			else
			{
				Reporter.log("Add new Page  window is NOT displayed", true);
				ATUReports.add("Add new Page  window is NOT displayed", true);
				Log.info("Add new Page  window is NOT displayed");
				Assert.fail("Add new Page window is NOT displayed");
			}


			Reporter.log("TIFF File is downloaded successfully.", true);
			ATUReports.add("TIFF File is downloaded successfully.", true);
			Log.info("TIFF File is downloaded successfully. ");
			ATUReports.add("Verify export TIF image to TIF format from document type of workitem in IWM", "","150 * 150 DPI of image should be displayed",
					"150 * 150 DPI of image is displayed", true);


			util.waitForPageToLoad();
			util.wait(time);

			File filePath=wdp.getLatestFilefromDir(downloadPath);

			Reporter.log("Get Latest File : "+filePath, true);
			ATUReports.add("Get Latest File : "+filePath, true);
			Log.info("Get Latest File : "+filePath);

			String imageFileName=(wdp.getLatestFilefromDir(downloadPath)).toString().substring(28);
			Reporter.log("imageFileName : "+imageFileName, true);
			Log.info("imageFileName : "+imageFileName);

			boolean filePresence=wdp.isFileDownloaded(downloadPath,imageFileName);
			if(filePresence)
			{
				Reporter.log("File is Present : "+imageFileName, true);
				ATUReports.add("File is Present : "+imageFileName, true);
				Log.info("File is Present : "+imageFileName);
			}
			else				
			{
				Reporter.log("File is not Present", true);
				ATUReports.add("File is not Present", true);
				Log.info("File is not Present");
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Exporting TIF image to TIF format from document type of workitem is failed.", true);
			ATUReports.add("Exporting TIF image to TIF format from document type of workitem is failed", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Exporting TIF image to TIF format from document type of workitem is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO-7554 export TIF image to TIF format from document type of workitem in IWM is Failed");
		}
	}

}

