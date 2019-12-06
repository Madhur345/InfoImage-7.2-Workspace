package apd.infoimage.iwm.tests;

import java.io.File;

import org.apache.http.auth.UsernamePasswordCredentials;
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
 * @author PradhanJ INFO-7228 Creating a document type of workitem and sending
 *         to default queue and Retrieving the same workitem and again
 *         retrieving the same workitem with another user and uploading a
 *         scanned document to the workitem
 */
public class INFO_7228 extends SuperClassIWM {
	@BeforeMethod
	public void setUp() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown() {
		hp.logoutApp();
	}

	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 1)
	public void testRetrieveByDifferentUserAndUploadImage() {

		Log.startTestCase("INFO_7228_RetrieveByDifferentUserAndUploadImage");
		ATUReports.setTestCaseReqCoverage("Creating a document type of workitem and sending to default queue and "
				+ "Retrieving the same workitem and again retrieving the same workitem with another user"
				+ "and uploading a scanned document to the workitem");
		ATUReports.setAuthorInfo("Jayashri", "MAY-2018", "0.3");

		String xlpath = "src\\test\\resources\\testData.xlsx";
		String sheet = "Sheet1";

		String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
		String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
		String workitem = str + util.getSysDate(0, date);
		String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
		String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
		int time = 3000;

		try {

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);

			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			cwp.CreateWorkitem(workitem, className, workitemType);

			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			cwp.sendWorkItemToDefaultQueue(workitem);
			util.waitForPageToLoad();
			Thread.sleep(2000);

			Reporter.log("Retrieving Workitem From Inbox", true);
			ATUReports.add("Retrieving Workitem From Inbox", true);
			ip.retrieveWorkItem(workitem);
			util.waitForPageToLoad();
			util.wait(time);
			
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

				String tifFilePath = new File("src\\test\\resources").getAbsolutePath();

				String imagePath = tifFilePath + "\\singlePage.tif";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				Thread.sleep(3000);

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("singlePage.tif");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected TIF file name validation successfull.", true);
					ATUReports.add("Selected TIF file name validation successfull.", true);
				} else {
					Reporter.log("Selected TIF file name validation failed.", true);
					ATUReports.add("Selected TIF file name validation failed.", true);
					Assert.fail("Selected TIF file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);

				int contentList = wdp.getContentPageNo();
				if (contentList >= 1) {
					Reporter.log("1 Page is listed under content on the side bar ", true);
					ATUReports.add("1 Page is listed under content on the side bar ", true);
				} else {
					Reporter.log("1 Page is NOT listed under content on the side bar", true);
					ATUReports.add("1 Page is NOT listed under content on the side bar", true);
					Assert.fail("1 Page is NOT listed under content on the side bar");
				}
				Reporter.log("Retrieve By Different User And Upload Image test successfull", true);
				ATUReports.add("Retrieve By Different User And Upload Image test successfull", true);
			}
		}

		catch (Exception e1) {
			e1.printStackTrace();
			Log.error(e1.getMessage());
			Reporter.log("Retrieve By Different User And Upload Image test failed", true);
			ATUReports.add("failed to execute test INFO_7228_RetrieveByDifferentUserAndUploadImage", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Validation of  added  TIF file failed");

		}

		finally {
			Log.endTestCase("INFO_7228_RetrieveByDifferentUserAndUploadImage");
		}

	}
}
