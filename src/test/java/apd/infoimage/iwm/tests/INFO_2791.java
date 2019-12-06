package apd.infoimage.iwm.tests;

import java.io.File;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.interactions.Actions;
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
 * @author PradhanJ INFO-2791 This Scenario is To verify Performing Add Multiple
 *         Page Document And Print All Pages
 */
public class INFO_2791 extends SuperClassIWM {

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
	@Test(enabled = true, groups = { "UploadFile" })
	public void testVerifyPrintCapabilitiesForAllPages() {
		try {
			ATUReports.setTestCaseReqCoverage(
					"This Scenario is To verify" + " Performing Add Multiple Page Document And Print All Pages");
			ATUReports.setAuthorInfo("Jayashri", "MAY-2018", "0.3");

			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_2791_VerifyPrintCapabilitiesForAllPages");

			String xlpath = "src\\test\\resources\\testData.xlsx";
			String sheet = "Sheet1";

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
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

				// Validate the document is listed under content and no of pages
				// in the document

				try {
					int contentList = wdp.getContentPageNo();
					Reporter.log("No of Pages in the multiple page tif file is : " + contentList, true);
					ATUReports.add("No of Pages in the multiple page tif file is : " + contentList, true);
					util.waitForPageToLoad();
					util.wait(time);

					wdp.getPrintIcon().click();
					Reporter.log("Print Icon  is clicked", true);
					ATUReports.add("Print Icon  is clicked", true);
					util.waitForPageToLoad();					
					
					if (wdp.getAllPagesRadioButton().isDisplayed() && wdp.getPrintButton().isDisplayed()) {
						Reporter.log("print all pages option available", true);
						ATUReports.add("print all pages option available", true);						
						util.wait(time);
						
					} else {
						Reporter.log("print all pages option NOT  available", true);
						ATUReports.add("print all pages option NOT available", LogAs.FAILED,
								new CaptureScreen(ScreenshotOf.DESKTOP));
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					ATUReports.add("print all pages option test failed", 
							LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("print all pages option test failed");
				}
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add("Performing Add Multiple Page Document And Print All Pages", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Adding Multiple page document to Workitem and Print All pages test failed");

		} finally {
			Log.endTestCase("INFO_2791_VerifyPrintCapabilitiesForAllPages");
		}
	}

}
