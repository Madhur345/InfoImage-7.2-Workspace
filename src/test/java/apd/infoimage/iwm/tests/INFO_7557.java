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
 * @author Biswajit - 05-June-2018 INFO_7557 This class Perform To Export 
 * TIF Image To PNG Format From Document Type Of Workitem In IWM
 */
public class INFO_7557  extends SuperClassIWM {

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
		Log.startTestCase(
				"INFO-7557 This Scenario will export TIF image to PNG format from document type of workitem in IWM");
		ATUReports.setTestCaseReqCoverage(
				"INFO-7557 This Scenario will export TIF image to PNG format from document type of workitem in IWM");
		ATUReports.setAuthorInfo("Biswajit", "June-2018", "1.0");
		try {

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			Thread.sleep(3000);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			Thread.sleep(2000);

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
				Thread.sleep(8000);
				
				wdp.getSelectPageCheckbox().click();
				Reporter.log("checkbox button clicked", true);
				ATUReports.add("checkbox button clicked", true);
				Thread.sleep(2000);
				
				wdp.getExportImage().click();
				Reporter.log("Export Image button clicked", true);
				ATUReports.add("Export Image button clicked", true);
				Thread.sleep(5000);
				
				util.jclick(wdp.getuploadJPEG());
				Reporter.log("PNG button clicked", true);
				ATUReports.add("PNG button clicked", true);
				Thread.sleep(2000);
				
				util.jclick(wdp.getDownloadButton());
				Reporter.log("PNG format document successfully downloaded", true);
				ATUReports.add("PNG format document successfully downloaded", true);
				Thread.sleep(20000);

			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Exporting TIF image to PNG format from document type of workitem is failed.", true);
			ATUReports.add("Exporting TIF image to PNG format from document type of workitem is failed", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Exporting TIF image to PNG format from document type of workitem is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO-7557 export TIF image to PNG format from document type of workitem in IWM is Failed");
		}
	}

}

