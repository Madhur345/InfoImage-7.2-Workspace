package apd.infoimage.iwm.tests;

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

public class INFO_3702 extends SuperClassIWM {

	/**
	 * @author PradhanJ
	 *
	 */

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

	/**
	 * This test method will send a workitem to default queue and retrive it then
	 * check the workflow history
	 * 
	 * @author PradhanJ
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 1, groups = { "Workitem" })
	public void workitemHistoryTest() {
		String witemName = "jay" + util.getSysDate(0, "yyDDMMhhmmss");
		ATUReports.setTestCaseReqCoverage(
				"This Scenario will send a workitem to default queue and retrive it then check the workflow history");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		try {
			Log.startTestCase("INFO_3702_WorkitemHistoryTest");
			// Create a workitem

			cwp.CreateWorkitem(witemName, "archive", "Document");
			Reporter.log("Documet: " + witemName + " has been created", true);
			ATUReports.add("Documet: " + witemName + " has been created", true);

			Reporter.log("Sending Workitem to Workflow and Validate that workitem is present in the data entry grid\n");
			ATUReports.add(
					"Sending Workitem to Workflow and Validate that workitem is present in the data entry grid\n",
					true);
			cwp.sendWorkItemToDefaultQueue(witemName);

			util.waitForPageToLoad();

			Reporter.log("Retreiving Workitem");
			ATUReports.add("Retreiving Workitem", true);

			ip.retrieveWorkItem(witemName);

			// Navigate to workitem history window for the retrieved workitem

			cwp.getCheckBoxWorkItemName(witemName).click();
			cwp.getWorkItemHistoryBtn().click();

			// Validate the workitem history

			boolean wHistoryWinPresent = util.verifyObjectPresentReturnsBool(cwp.getWorkflowHistoryWin());
			if (wHistoryWinPresent) {
				Reporter.log("Workflow history window is present", true);
				ATUReports.add("Workflow history window is present", true);
				try {
					int noOfRows = cwp.getNoOfRowsInWorkItemHiostory(witemName);
					boolean dstnWorkIntroPresent = util.verifyObjectPresentReturnsBool(cwp.getDstnWorkIntroduction());
					boolean dstnDEorCustomPresent = util.verifyObjectPresentReturnsBool(cwp.getDstnInHistory());
					if (noOfRows == 2) {
						Reporter.log("Two transactions are displayed in the workflow.", true);
						ATUReports.add("Two transactions are displayed in the workflow.", true);

					} else {
						Reporter.log("No of transactions dispalyed in the Workflow history is NOT  correct", true);
						ATUReports.add("No of transactions dispalyed in the Workflow history is NOT  correct", true);
						Assert.fail("No of transactions dispalyed in the Workflow history is NOT  correct");
					}
					if (dstnWorkIntroPresent && dstnDEorCustomPresent) {
						Reporter.log("Destinations in the workflow history validation successful", true);
						ATUReports.add("Destinations in the workflow history validation successful", true);
					} else {
						Reporter.log("Destinations in the workflow history validation faiiled", true);
						ATUReports.add("Destinations in the workflow history validation faiiled", true);
						Assert.fail("Destinations in the workflow history validation faiiled");
					}
				} catch (Exception e) {
					e.printStackTrace();
					Log.error(e.getMessage());
					Reporter.log("Workflow history validation failed", true);
					ATUReports.add("Workflow history validation failed", true);
					ATUReports.add("failed to execute test INFO_3702_WorkitemHistoryTest",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Workflow history validation failed");
				}

				// Navigate back to the IWM home page.

				Thread.sleep(3000);
				cwp.getwFlowHistoryWinCloseBtn().click();
				util.waitForPageToLoad();
				
			} else {
				Reporter.log("Workflow history window is NOT present", true);
				ATUReports.add("Workflow history window is NOT present", true);
				Assert.fail("Workflow history window is NOT present");
			}

		} catch (Exception e1) {
			e1.printStackTrace();
			Log.error(e1.getMessage());
			Reporter.log("Failed to execute  workitemHistoryTest", true);
			ATUReports.add("Failed to execute  workitemHistoryTest", true);
			ATUReports.add("failed to execute test INFO_3702_WorkitemHistoryTest",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Failed to execute workitemHistoryTest");
		}

		finally {
			Log.endTestCase("INFO_3702_WorkitemHistoryTest");
		}
	}
}
