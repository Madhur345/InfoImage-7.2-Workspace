package apd.infoimage.iwm.tests;

import java.io.File;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class INFO_10707 extends SuperClassIWM {

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

		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("INFO-7586 This Scenario will export JPG,TIF and PDF images to multipage TIF image format from document type of workitem in IWM");
		ATUReports.setTestCaseReqCoverage("INFO-7586 This Scenario will export JPG,TIF and PDF images to multipage TIF image format from document type of workitem in IWM");
		ATUReports.setAuthorInfo("Princy", "MAY-2018", "1.0");
		try {

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");
			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);

			/*
			 * wdp.getContentField().click(); Reporter.log("WorkItemDetailView displayed",
			 * true); ATUReports.add("WorkItemDetailView displayed", true);
			 * Log.info("WorkItemDetailView displayed"); Thread.sleep(3000);
			 * 
			 * wdp.getAddNewPageIcon().click(); Reporter.log("Click on add new page", true);
			 * ATUReports.add("Click on add new page", true);
			 * Log.info("Click on add new page"); util.waitForPageToLoad();
			 * Thread.sleep(2000);
			 */
			wdp.getContentField().click();
			wdp.getAddNewPageIcon().click();

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				String path = new File("src\\test\\resources").getAbsolutePath();

				String tiffPath = path + "\\150dpi.tiff";
				String jpgPath = path + "\\Unisys.jpg";
				String pdfPath = path + "\\SampleDoc.pdf";

				Reporter.log("img path " + tiffPath, true);
				ATUReports.add("img path " + tiffPath, true);

				wdp.getContentUploadField().sendKeys(tiffPath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				wdp.getAddNewPageUploadBtn().click();
				Thread.sleep(5000);

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
					"INFO-7586  Export JPG,TIF and PDF images to multipage TIF image format from document type of workitem in IWM is Failed");
		}
	}

}
