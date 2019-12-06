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
 * @author SinghAvn
 * @Zypher Id: INFO-4400 This Scenario will verify the Upload of video content
 *         in IWM with MPEG2-PS file video.
 */
public class INFO_4400 extends SuperClassIWM {

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
		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_7087_Sending_One_WorkitemtoUserQueue_with_imports");
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-4400 will verify the Upload of video content\r\n"
					+ " *         in IWM with MPEG2-PS file video");
			ATUReports.setAuthorInfo("Avnish", "MAY-2018", "1.0");
			// Fetch the test data
			String xlpath = "src\\test\\resources\\testData.xlsx";
			String sheet = "Sheet1";
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);

			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("Workitem to Workflow is created", true);
			ATUReports.add("Workitem to Workflow is created", true);

			cwp.searchByNameInWorkitemList(workitem);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("Workitem checkbox is checked", true);
			ATUReports.add("Workitem checkbox is checked", true);

			util.waitForElementEnabled(wdp.getImports_win());
			wdp.getImports_win().click();
			ATUReports.add("Imports Tab is clicked", true);

			util.waitForElementEnabled(wdp.getAddImportIcon());
			wdp.getAddImportIcon().click();
			ATUReports.add("Add Text Type File Import Button is clicked", true);

			boolean addNewImportsWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if (addNewImportsWinPresent) {

				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new import window is displayed", true);

				String filePath = new File("src\\test\\resources").getAbsolutePath();
				String textPath = filePath + "\\selenium_Sample.txt";
				Reporter.log("img path " + textPath, true);

				wdp.getImportUploadField().sendKeys(textPath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				Thread.sleep(3000);

				boolean docNameInAddNewPageWin = wdp.getSelectedImportFile().getText().equals("selenium_Sample.txt");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected text file name validation successfull.", true);
					ATUReports.add("Selected text file name validation successful", true);
				} else {
					Reporter.log("Selected text file name validation failed.", true);
					Assert.fail("Selected text file name validation failed.");
				}
				Thread.sleep(3000);

				wdp.getImportUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				// Validate the document is listed under content and no of pages in the document
				try {
					int importList = wdp.getContentImportNo();
					if (importList >= 1) {
						Reporter.log("Text File is imported successfully. ", true);
						ATUReports.add("Text File is imported successfully. ", true);
					} else {
						Reporter.log("Text File is NOT imported successfully.", true);
						ATUReports.add("Text File is NOT imported successfully.", true);
						Assert.fail("Text File is NOT imported successfully.");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Text File is NOT imported successfully.", true);
					ATUReports.add("Text File is NOT imported successfully.", true);
					Assert.fail("Text File is NOT imported successfully.");
				}
			} else {
				Reporter.log("Add new import  window is NOT displayed", true);
				ATUReports.add("Add new import  window is NOT displayed", true);
				Assert.fail("Add new import window is NOT displayed");
			}

			hp.getWorkItemTab().click();
			cwp.sendWorkItemToUserQueue(workitem, prop.getProperty("userName"));
			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);

			hp.getInbox().click();
			Reporter.log("Navigating to inbox page", true);
			ATUReports.add("Navigating to inbox page", true);

			ip.getMyPersonalQueue().click();
			Reporter.log("My Personal Link is clicked", true);
			ATUReports.add("My Personal Link is clicked", true);

			ip.searchByNameInMyPersonalQueue(workitem);
			Reporter.log("Searching By Workitem Name In MyPersonal Queue", true);
			ATUReports.add("Searching By Workitem Name In MyPersonal Queue", true);
			Log.info("Searching By Workitem Name In MyPersonal Queue");

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Importing text type file is failed.", true);
			ATUReports.add("Verify Importing text type file is failed.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing text type file is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO-4400 will verify the Upload of video content in IWM with MPEG2-PS file video");
		}

	}

}
