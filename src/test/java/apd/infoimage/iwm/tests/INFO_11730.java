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
 * @author PradhanJ
 * INFO-11730
 * Test to verify adding of rtf document for document/folder type of workitem 
 * which is retrieved from Mypersonal Queue in IWM
 */
public class INFO_11730 extends SuperClassIWM{
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
		Log.startTestCase("INFO_11730_AddingRTFdocumentForWorkitemRetrievedFrommyPersonalQueue");
		try {
			ATUReports.setTestCaseReqCoverage(
					"This Scenario INFO-11730    Test to verify adding of rtf document for "
					+ "document/folder type of workitem  which is retrieved from Mypersonal Queue in IWM ");

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

			hp.getInbox().click();
			Reporter.log("Navigating to inbox page", true);
			ATUReports.add("Navigating to inbox page", true);
			Log.info("Navigating to inbox page");

			ip.getMyPersonalQueue().click();
			Reporter.log("My Personal Link is clicked", true);
			ATUReports.add("My Personal Link is clicked", true);
			Log.info("My Personal Link is clicked");

			ip.searchByNameInMyPersonalQueue(workitem);
			Reporter.log("Searching By Workitem Name In MyPersonal Queue", true);
			ATUReports.add("Searching By Workitem Name In MyPersonal Queue", true);
			Log.info("Searching By Workitem Name In MyPersonal Queue");

			util.waitForPageToLoad();

			ip.retrieveWorkItem(workitem);
			Reporter.log("Workitem is retrived from my personal queue", true);
			ATUReports.add("Workitem is retrived from my personal queue", true);
			Log.info("Workitem is retrived from my personal queue");

			hp.getWorkItemTab().click();
			Reporter.log("Navigating to workitem List page", true);
			ATUReports.add("Navigating to workitem List page", true);
			Log.info("Navigating to workitem List page");

			cwp.searchByNameInWorkitemList(workitem);
			Reporter.log("Workitem  retrieved from the personal queue is in the workitem list", true);
			ATUReports.add("Workitem  retrieved from the personal queue is in the workitem list", true);
			Log.info("Workitem  retrieved from the personal queue is in the workitem list");

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);
			
			util.waitForElementEnabled(wdp.getImports_win());
			wdp.getImports_win().click();
			ATUReports.add("Imports Tab is clicked", true);

			util.waitForElementEnabled(wdp.getAddImportIcon());
			wdp.getAddImportIcon().click();
			ATUReports.add("Add Text Type File Import Button is clicked", true);
			Thread.sleep(3000);

			boolean addNewImportsWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if(addNewImportsWinPresent)
			{

				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new Media File window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();
				String imagePath = tifFilePath+"\\rtfSample.rtf";
				Reporter.log("img path "+imagePath,true);

				wdp.getImportUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				Thread.sleep(3000);

				boolean docNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals("rtfSample.rtf");
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected rtf file name validation successfull.", true);
					ATUReports.add("Selected rtf file name validation successful", true);
				}
				else
				{
					Reporter.log("Selected rtf file name validation failed.", true);
					Assert.fail("Selected rtf file name validation failed.");
				}
				Thread.sleep(3000);

				wdp.getImportUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				//Validate the document is listed under content  and no of pages in the document
				try
				{
					int importList=wdp.getContentImportNo();
					if(importList>=1)
					{
						Reporter.log("rtf File is imported successfully. ", true);
						ATUReports.add("rtf File is imported successfully. ", true);
					}
					else
					{
						Reporter.log("rtf File is NOT imported successfully.", true);
						ATUReports.add("rtf File is NOT imported successfully.", true);
						Assert.fail("rtf File is NOT imported successfully.");
					}


				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					Reporter.log("rtf File is NOT imported successfully.", true);
					ATUReports.add("rtf File is NOT imported successfully.", true);
					Assert.fail("rtf File is NOT imported successfully.");
				}
			}
			else
			{
				Reporter.log("Add new import  window is NOT displayed", true);
				ATUReports.add("Add new import  window is NOT displayed", true);
				Assert.fail("Add new import window is NOT displayed");
			}

		} catch (Exception e) {
			Reporter.log("validation of refreshment of document viewer is FAILED "
					+ "after adding rtf file to retrieved workitem from my personal queue", true);
			ATUReports.add(
					"validation of refreshment of document viewer is FAILED "
							+ "after adding rtf file to retrieved workitem from my personal queue",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("validation of refreshment of document viewer is FAILED "
					+ "after adding rtf file to retrieved workitem from my personal queue");
		} finally {
			Log.endTestCase("INFO_11730_AddingRTFdocumentForWorkitemRetrievedFrommyPersonalQueue");
		}
	}
}
