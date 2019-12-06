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
 * @Zypher Id: INFO-7580 This Scenario will export multiple PDF images to TIF
 *         format from document type of workitem in IWM.
 */
public class INFO_7580 extends SuperClassIWM {

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
	public void pdfToMultiPagePdf() {

		Log.startTestCase(
				"INFO-7586 This Scenario will export JPG,TIF and PDF images to multipage TIF image format from document type of workitem in IWM");
		ATUReports.setTestCaseReqCoverage(
				"INFO-7586 This Scenario will export JPG,TIF and PDF images to multipage TIF image format from document type of workitem in IWM");
		ATUReports.setAuthorInfo("AVNISH", "JUNE-2018", "1.0");
		try {
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);

			wdp.getContentField().click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");
			Thread.sleep(3000);

			wdp.getAddNewPageIcon().click();
			Reporter.log("Click on add new page", true);
			ATUReports.add("Click on add new page", true);
			Log.info("Click on add new page");
			util.waitForPageToLoad();
			Thread.sleep(2000);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				String path = new File("src\\test\\resources").getAbsolutePath();
				String pdfPath = path + "\\SampleDoc.pdf";

				Reporter.log("img path " + pdfPath, true);
				ATUReports.add("img path " + pdfPath, true);

				wdp.getContentUploadField().sendKeys(pdfPath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(5000);

				/*wdp.getAddNewPageIcon().click();
				Reporter.log("Click on add new page", true);
				ATUReports.add("Click on add new page", true);
				Log.info("Click on add new page");

				wdp.getContentUploadField().sendKeys(pdfPath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				Thread.sleep(5000);*/

				wdp.getSelectPageCheckbox().click();
				Reporter.log("checkbox is clicked", true);
				ATUReports.add("checkbox is clicked", true);
				Log.info("checkbox is clicked");
				Thread.sleep(2000);

				wdp.getSelectPageCheckbox2().click();
				Reporter.log("checkbox is clicked", true);
				ATUReports.add("checkbox is clicked", true);
				Log.info("checkbox is clicked");
				Thread.sleep(2000);

				wdp.getExportImage().click();
				Reporter.log("Export button is clicked", true);
				ATUReports.add("Export button is clicked", true);
				Log.info("Export button is clicked");
				Thread.sleep(2000);
				util.jclick(wdp.getUploadTIF());
				Reporter.log("TIF checkbox is checked", true);
				ATUReports.add("TIF checkbox is checked", true);
				Log.info("TIF  checkbox is checked");
				Thread.sleep(2000);

				util.jclick(wdp.getDownloadButton());
				Reporter.log("download button is clicked", true);
				ATUReports.add("download button is clicked", true);
				Log.info("download button is clicked");
				Thread.sleep(2000);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Importing text type file is failed.", true);
			ATUReports.add("Verify Importing text type file is failed.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing text type file is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase(
					"INFO-7580  export PDF to multipage TIF image format from document type of workitem in IWM is Failed");
		}
	}

}
