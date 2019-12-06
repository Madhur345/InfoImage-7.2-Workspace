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
 * @Zypher Id: INFO-7087 This Scenario will Create one workitem with imports
 *         attached and will send the same workitem to User Queue and check the
 *         workitem in that user Queue.
 */
public class INFO_7087 extends SuperClassIWM {

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
			ATUReports.setTestCaseReqCoverage(
					"This Scenario INFO-7087 will check sending One WorkItem in User Queue and will check same work item in User Queue ");
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
			Log.info("Workitem to Workflow is created");

			cwp.searchByNameInWorkitemList(workitem);
			Reporter.log("Searching by Name in List of all workitems", true);
			ATUReports.add("Searching by Name in List of all workitems", true);
			Log.info("Searching by Name in List of all workitems");

			cwp.getWorkItemName(workitem).click();
			Reporter.log("Workitem checkbox is checked", true);
			ATUReports.add("Workitem checkbox is checked", true);
			Log.info("Workitem checkbox is checked");

			util.waitForElementEnabled(wdp.getImports_win());
			wdp.getImports_win().click();
			ATUReports.add("Imports Tab is clicked", true);
			Reporter.log("Imports Tab is clicked", true);
			Log.info("Imports Tab is clicked");

			util.waitForElementEnabled(wdp.getAddImportIcon());
			ATUReports.add("AddImport icon is clicked", true);
			Reporter.log("AddImport icon is clicked", true);
			Log.info("AddImport icon is clicked");

			wdp.getAddImportIcon().click();
			ATUReports.add("Add Text Type File Import Button is clicked", true);
			Reporter.log("Add Text Type File Import Button is clicked", true);
			Log.info("Add Text Type File Import Button is clicked");

			boolean addNewImportsWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if (addNewImportsWinPresent) {

				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new import window is displayed", true);
				Log.info("Add new import window is displayed");

				String filePath = new File("src\\test\\resources").getAbsolutePath();
				String textPath = filePath + "\\selenium_Sample.txt";
				Reporter.log("img path " + textPath, true);

				wdp.getImportUploadField().sendKeys(textPath);
				Reporter.log("Upload Button is clicked and Text File is uploaded", true);
				ATUReports.add("Upload Button is clicked and Text File is uploaded", true);
				Log.info("Upload Button is clicked and Text File is uploaded");

				util.waitForElementEnabled(wdp.getSelectedImportFile());
				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new import window is displayed", true);
				Log.info("Add new import window is displayed");

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
				Reporter.log("Upload Button is clicked", true);
				ATUReports.add("Upload Button is clicked", true);
				Log.info("Upload Button is clicked");
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
			util.waitForPageToLoad();
			util.waitForElementEnabled(cwp.getCreateWorkitem_btn());
			
			//Modified By Jayashri
			
			cwp.searchByNameInWorkitemList(workitem);
			cwp.sendWorkItemToUserQueue(workitem, prop.getProperty("userName"));
			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			util.waitForPageToLoad();
			util.waitForElementEnabled(hp.getInbox());
			util.wait(5000);
			
			util.jclick(hp.getInbox());
			Reporter.log("Navigating to inbox page", true);
			ATUReports.add("Navigating to inbox page", true);
			Log.info("Navigating to inbox page");
			util.waitForPageToLoad();
			util.waitForElementEnabled(ip.getMyPersonalQueue());

			ip.getMyPersonalQueue().click();
			Reporter.log("My Personal Link is clicked", true);
			ATUReports.add("My Personal Link is clicked", true);
			Log.info("My Personal Link is clicked");
			util.waitForElementEnabled(ip.getSearchFieldInMyPersonalQueue());

			ip.searchByNameInMyPersonalQueue(workitem);
			util.waitForPageToLoad();
			Reporter.log("Searching By Workitem Name In MyPersonal Queue", true);
			ATUReports.add("Searching By Workitem Name In MyPersonal Queue", true);
			Log.info("Searching By Workitem Name In MyPersonal Queue");

			boolean workitemPresence = util.verifyObjectPresentReturnsBool(ip.getWorkItemName(workitem));
			if (workitemPresence) {
				Reporter.log("Sent Workitem is present in my Personal queue", true);
				ATUReports.add("Verify Sent Workitem is present in my Personal queue", "workitem Name: " + workitem,
						"Workitem should be present in the list", "Workitem is successfully dispalyed under my Personal queue",
						true);
			} else {
				Reporter.log("Sent Workitem is not present in grid", true);
				Assert.fail("Sent Workitem is not present in grid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Workitem with imports is not found in user queue", true);
			ATUReports.add("Workitem with imports is not found in user queue", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Workitem with imports is not found in user queue");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO_7087_Creating_a_workitem_and_sending_the_same_workitem_to_UserQueue");
		}

	}

}
