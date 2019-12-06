package apd.infoimage.iwm.tests;

import java.io.File;

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
 * @author SinghAvn
 * @Zypher Id: INFO-7579 This Scenario will export multiple TIF images to TIF
 *         format from document type of work item in IWM.
 */
public class INFO_7579 extends SuperClassIWM {

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
	public void exportTiffTomultipageTiff() {

		Log.startTestCase(
				"INFO-7579 This Scenario will export multiple TIF images to multipage TIF image format from document type of workitem in IWM");
		ATUReports.setTestCaseReqCoverage(
				"INFO-7579 This Scenario will export multiple TIF images to multipage TIF image format from document type of workitem in IWM");
		ATUReports.setAuthorInfo("AVNISH", "JUNE-2018", "1.0");
		try {
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			int time = 3000;
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			/*cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			util.waitForPageToLoad();
			util.waitForElementEnabled(wdp.getContentField());
			wait(time);

			wdp.getContentField().click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");
			util.waitForElementEnabled(wdp.getAddNewPageIcon());
			
			wdp.getAddNewPageIcon().click();
			Reporter.log("Click on add new page", true);
			ATUReports.add("Click on add new page", true);
			Log.info("Click on add new page");
			util.waitForPageToLoad();
			wait(time);*/

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked.", true);
			Thread.sleep(3000);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			Thread.sleep(2000);
			
			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				String path = new File("src\\test\\resources").getAbsolutePath();
				String tiffPath = path + "\\FakePage2.tif";

				Reporter.log("path " + tiffPath, true);
				ATUReports.add("path " + tiffPath, true);

				wdp.getContentUploadField().sendKeys(tiffPath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				wdp.getAddNewPageIcon().click();
				Reporter.log("Click on add new page", true);
				ATUReports.add("Click on add new page", true);
				Log.info("Click on add new page");
				util.waitForPageToLoad();
				Thread.sleep(2000);

				wdp.getContentUploadField().sendKeys(tiffPath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				util.waitForPageToLoad();
				util.wait(time);

				util.jclick(wdp.getSelectAllCheckbox());
				Reporter.log("checkbox is clicked", true);
				ATUReports.add("checkbox is clicked", true);
				Log.info("checkbox is clicked");
				util.wait(time);

				wdp.getExportImage().click();
				Reporter.log("Export button is clicked", true);
				ATUReports.add("Export button is clicked", true);
				Log.info("Export button is clicked");
				util.wait(time);
				util.jclick(wdp.getUploadTIF());
				Reporter.log("TIF checkbox is checked", true);
				ATUReports.add("TIF checkbox is checked", true);
				Log.info("TIF  checkbox is checked");
				util.wait(time);

				util.jclick(wdp.getDownloadButton());
				Reporter.log("download button is clicked", true);
				ATUReports.add("download button is clicked", true);
				Log.info("download button is clicked");
				util.waitForPageToLoad();
				util.wait(time);
				
				Reporter.log("INFO-7579 To export tiff to multipage TIF image format from document type of workitem in IWM is successfull", true);
				Log.info("INFO-7579 To export tiff to multipage TIF image format from document type of workitem in IWM is");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("INFO-7579 is failed", true);
			ATUReports.add("INFO-7579 is failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("INFO-7579 is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase(
					"INFO-7579 To export tiff to multipage TIF image format from document type of workitem in IWM is Failed");
		}
	}

}
