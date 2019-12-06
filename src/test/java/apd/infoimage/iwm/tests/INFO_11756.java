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
 * @author PradhanJ Test to verify the refreshment of document viewer while
 *         adding 'tif/pdf/jpg' files for retrieved document type of workitem
 *         from MyPersonalQueue
 */
public class INFO_11756
		extends SuperClassIWM {
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
	@Test(enabled = true, groups = { "DocumentViewer" })
	public void testRefreshmentOfDocViewerByAddingJPGfileForRetrievedWitemFromMyPersonalQueue() {
		Log.startTestCase("INFO_11756_RefreshmentOfDocViewerByAddingJPGfileForRetrievedWitemFromMyPersonalQueue");
		try {
			ATUReports.setTestCaseReqCoverage(
					"This Scenario INFO-11756   Test to verify the refreshment of document viewer while"
							+ " adding 'tif/pdf/jpg' files for retrieved document type of workitem from MyPersonalQueue	 ");

			ATUReports.setAuthorInfo("Jayashri", "AUG-2018", "0.3");

			// Fetch the test data
			String xlpath = "src\\test\\resources\\testData.xlsx";
			String sheet = "Sheet1";
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			Log.info("Creation of Workitem");

			cwp.sendWorkItemToUserQueue(workitem, prop.getProperty("userName"));
			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			
			hp.navigateToInbox();

			ip.getMyPersonalQueue().click();
			Reporter.log("My Personal Link is clicked", true);
			ATUReports.add("My Personal Link is clicked", true);
			Log.info("My Personal Link is clicked");
			util.waitForElementEnabled(ip.getSearchFieldInDataEntry());

			ip.searchByNameInMyPersonalQueue(workitem);
			Reporter.log("Searching By Workitem Name In MyPersonal Queue", true);
			ATUReports.add("Searching By Workitem Name In MyPersonal Queue", true);
			Log.info("Searching By Workitem Name In MyPersonal Queue");
			util.waitForPageToLoad();

			ip.retrieveWorkItem(workitem);
			Reporter.log("Workitem is retrived from my personal queue", true);
			ATUReports.add("Workitem is retrived from my personal queue", true);
			Log.info("Workitem is retrived from my personal queue");
			
/*
			hp.getWorkItemTab().click();
			Reporter.log("Navigating to workitem List page", true);
			ATUReports.add("Navigating to workitem List page", true);
			Log.info("Navigating to workitem List page");

			cwp.searchByNameInWorkitemList(workitem);
			Reporter.log("Workitem  retrieved from the personal queue is in the workitem list", true);
			ATUReports.add("Workitem  retrieved from the personal queue is in the workitem list", true);
			Log.info("Workitem  retrieved from the personal queue is in the workitem list");*/

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			util.waitForPageToLoad();
			util.waitForElementEnabled(wdp.getContentField());

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			util.waitForElementEnabled(wdp.getAddNewPageIcon());

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			util.waitForElementEnabled(wdp.getAddNewPageWin());

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);

				String tifFilePath = new File("src\\test\\resources").getAbsolutePath();

				String imagePath = tifFilePath + "\\window.jpg";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				/*Thread.sleep(3000);*/

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("window.jpg");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected JPG file name validation successfull.", true);
					ATUReports.add("Selected JPG file name validation successfull.", true);
				} else {
					Reporter.log("Selected JPG file name validation failed.", true);
					ATUReports.add("Selected JPG file name validation failed.", true);
					Assert.fail("Selected JPG file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				// Validate the document is listed under content and no of pages
				// in the document
				try {
					int contentList = wdp.getContentPageNo();
					if (contentList >= 1) {
						Reporter.log("1 Page is listed under content on the side bar ", true);
						ATUReports.add("1 Page is listed under content on the side bar ", true);
					} else {
						Reporter.log("1 Page is NOT listed under content on the side bar", true);
						ATUReports.add("1 Page is NOT listed under content on the side bar", true);
						Assert.fail("1 Page is NOT listed under content on the side bar");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Validation of  added  JPG file failed", true);
					ATUReports.add("Validation of  added  JPG file failed", true);
					Assert.fail("Validation of  added  JPG file failed");
					Log.error(e1.getMessage());

				}
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}

			Thread.sleep(3000);
			Reporter.log("validation of refreshment of document viewer is successfull "
					+ "after adding JPG file to retrieved workitem from my personal queue", true);
			ATUReports.add("validation of refreshment of document viewer is successfull "
					+ "after adding JPG file to retrieved workitem from my personal queue", true);
			Log.info("validation of refreshment of document viewer is successfull "
					+ "after adding JPG file to retrieved workitem from my personal queue");

		} catch (Exception e) {
			Reporter.log("validation of refreshment of document viewer is FAILED "
					+ "after adding JPG file to retrieved workitem from my personal queue", true);
			ATUReports.add(
					"validation of refreshment of document viewer is FAILED "
							+ "after adding JPG file to retrieved workitem from my personal queue",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("validation of refreshment of document viewer is FAILED "
					+ "after adding JPG file to retrieved workitem from my personal queue");
		} finally {
			Log.endTestCase("INFO_11756_RefreshmentOfDocViewerByAddingJPGfileForRetrievedWitemFromMyPersonalQueue");
		}
	}
}
