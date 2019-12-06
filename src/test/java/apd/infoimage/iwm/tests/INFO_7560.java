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
 * @Zypher Id: INFO-7560 This Scenario will export TIF image to GIF format from
 *         document type of workitem in IWM.
 */
public class INFO_7560 extends SuperClassIWM {

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
	public void testSameWorkIteminUserQueue() {

		Log.startTestCase(
				"INFO-7560 This Scenario will export TIF image to GIF format from document type of workitem in IWM");
		ATUReports.setTestCaseReqCoverage(
				"INFO-7560 This Scenario will export TIF image to GIF format from document type of workitem in IWM");
		ATUReports.setAuthorInfo("Avnish", "MAY-2018", "1.0");
		try {
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
		

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed ", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");
			Thread.sleep(3000);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			Log.info("Content field is clicked");
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

				String tiffPath = path + "\\150dpi.tiff";
				Reporter.log("img path " + tiffPath, true);
				ATUReports.add("img path " + tiffPath, true);

				wdp.getContentUploadField().sendKeys(tiffPath);
				Reporter.log("Content upload button is clicked and the tiff file is uploaded", true);
				ATUReports.add("Content upload button is clicked and the tiff file is uploaded", true);
				Log.info("Content upload button is clicked and the tiff file is uploaded");

				util.waitForElementEnabled(wdp.getSelectedFile());
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				Thread.sleep(8000);

				wdp.getSelectPageCheckbox().click();
				Reporter.log("checkbox is clicked", true);
				ATUReports.add("checkbox is clicked", true);
				Log.info("checkbox is clicked");
				Thread.sleep(2000);

				wdp.getExportImage().click();
				Reporter.log("click on export", true);
				ATUReports.add("click on export", true);
				Log.info("click on export");

				Thread.sleep(5000);
				util.jclick(wdp.getUploadGIF());
				Reporter.log("clicked on GIF checkbox", true);
				ATUReports.add("clicked on GIF checkbox", true);
				Log.info("clicked on GIF checkbox");
				Thread.sleep(2000);
				util.jclick(wdp.getDownloadButton());
				Reporter.log("clicked on download Button", true);
				ATUReports.add("clicked on download Button", true);
				Log.info("clicked on download Button");
				Thread.sleep(9000);

			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Importing text type file is failed.", true);
			ATUReports.add("Verify Importing text type file is failed.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing text type file is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO-7560 export TIF image to GIF format from document type of workitem in IWM is Failed");
		}
	}

}
