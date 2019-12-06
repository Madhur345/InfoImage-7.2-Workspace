package apd.infoimage.iwm.tests;

import java.io.File;

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
 * @author PradhanJ INFO-2788 To verify the Loose of image issue in the viewer
 *         after screen refresh
 */
public class INFO_2788 extends SuperClassIWM {
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
	@Test(enabled = true, priority = 1, groups = { "UploadFile" })
	public void testVerifyImageInViewerAfterRefresh() {
		try {
			ATUReports.setTestCaseReqCoverage(
					"This Scenario INFO-2788  is To verify the Loose of image issue in the viewer after screen refresh");
			ATUReports.setAuthorInfo("Jayashri", "MAY-2018", "0.3");

			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_2788_VerifyImageInViewerAfterRefresh");

			Reporter.log("RotateImageRightAndZoomSliderTest : testRotateImageRightAndZoomSlider()", true);
			String xlpath = "src\\test\\resources\\testData.xlsx";
			String sheet = "Sheet1";

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);
			int time = 3000;

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);

			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			cwp.CreateWorkitem(workitem, className, workitemType);
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

				String tifFilePath = new File("src\\test\\resources").getAbsolutePath();

				String imagePath = tifFilePath + "\\singlePage.tif";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("singlePage.tif");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected singlepage file name validation successful.", true);
					ATUReports.add("Selected singlepage file name validation successful.", true);
				} else {
					Reporter.log("Selected singlepage file name validation failed.", true);
					Assert.fail("Selected singlepage file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				util.waitForPageToLoad();
				util.waitForElementEnabled(wdp.getFormfields_win());
				util.wait(15000);
				
				wdp.getFormfields_win().click();
				ATUReports.add("Formfields window is clicked", true);
				util.wait(time);

				wdp.getIdcode_TF().clear();
				wdp.getIdcode_TF().sendKeys(idCodeStr);
				wdp.getUpdate_btn().click();
				util.waitForPageToLoad();
				util.wait(15000);
				Reporter.log("Form field is updated and page is refreshed", true);
				ATUReports.add("Form field is updated and page is refreshed", true);

				boolean pageDisplayed = util.verifyObjectPresentReturnsBool(wdp.getPageCount());
				if (pageDisplayed) {
					Reporter.log("Image is dispalyed after uypdating form field and refreshing the page ", true);
					ATUReports.add("Image is dispalyed after uypdating form field and refreshing the page ", true);
				} else {
					ATUReports.add("Image is NOT dispalyed after page is refreshed", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Image is NOT dispalyed after page is refreshed");
				}
			}

			else {
				Reporter.log("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add("Failed to execute verify Image after page refresh", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Failed to execute verify Image after page refresh");

		} finally {
			Log.endTestCase("INFO_2788_VerifyImageInViewerAfterRefresh");
		}
	}

}
