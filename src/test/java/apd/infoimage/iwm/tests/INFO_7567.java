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

/**
 * @author SinghAvn
 * @Zypher Id: INFO-7567 This Scenario is exporting PDF image to GIF format from
 *         document type of workitem in IWM.
 *
 */
public class INFO_7567 extends SuperClassIWM {
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
	public void testexportPDFtoGIFformat() {

		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		DOMConfigurator.configure("log4j.xml");
		int time =2000;
		Log.startTestCase(
				"INFO-7564 This Scenario is exporting PDF image to GIF format from document type of workitem in IWM");
		ATUReports.setTestCaseReqCoverage(
				"INFO-7564 This Scenario is exporting PDF image to GIF format from document type of workitem in IWM");
		ATUReports.setAuthorInfo("Avnish", "JUNE-2018", "1.0");
		try {

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");
			util.wait(time);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			Log.info("Content field is clicked");
			util.wait(time);

			wdp.getAddNewPageIcon().click();
			Reporter.log("Add new Page icon is clicked", true);
			ATUReports.add("Add new Page icon is clicked", true);
			Log.info("Add new Page icon is clicked");
			util.wait(time);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");
				String path = new File("src\\test\\resources").getAbsolutePath();

				String imagePath = path + "\\SampleDoc.pdf";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);

				wdp.getContentUploadField().sendKeys(imagePath);
				Reporter.log("new .pdf is uploaded", true);
				ATUReports.add("new .pdf image is uploaded", true);
				Log.info("new .pdf image is uploaded");

				util.waitForElementEnabled(wdp.getSelectedFile());
				Reporter.log("waiting for the image to upload", true);
				ATUReports.add("waiting for the image to upload", true);
				Log.info("waiting for the image to upload");

				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add new Page upload button is clicked", true);
				ATUReports.add("Add new Page upload button is clicked", true);
				Log.info("Add new Page upload button is clicked");

				util.wait(time);
				wdp.getSelectPageCheckbox().click();
				Reporter.log("select page checkbox is checked", true);
				ATUReports.add("select page checkbox is checked", true);
				Log.info("select page checkbox is checked");

				util.jclick(wdp.getUploadGIF());
				Reporter.log("Export to GIF button clicked", true);
				ATUReports.add("Export to GIF button clicked", true);
				util.wait(time);

				util.wait(time);
				wdp.getExportImage().click();
				Reporter.log("Export button is clicked", true);
				ATUReports.add("Export button is clicked", true);
				Log.info("Export button is clicked");

				util.wait(time);
				util.jclick(wdp.getDownloadButton());
				Reporter.log("download button is clicked", true);
				ATUReports.add("download button is clicked", true);
				Log.info("download button is clicked");
				util.wait(time);

			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("INFO-7567 is failed.", true);
			Log.info("INFO-7567 is failed.");
			ATUReports.add("INFO-7567 is failed.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("INFO-7567 is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase(
					"INFO-7567 This Scenario is exporting PDF image to GIF format from document type of workitem in IWM");
		}
	}
}
