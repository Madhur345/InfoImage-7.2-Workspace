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

public class INFO_3701 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1, groups = { "Workitem" })
	public void renameWorkItem() {

		Log.startTestCase("renameWorkItem");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-3701  is to Create workitem and rename it");
		ATUReports.setAuthorInfo("Jayashri", "APR-2018", "1.0");

		String witemName = "jay" + util.getSysDate(0, "yyDDMMhhmmss");
		String newWitemName = "jayNew" + util.getSysDate(0, "yyDDMMhhmmss");
		try {
			// Create a workitem

			cwp.CreateWorkitem(witemName, "archive", "Document");
			Reporter.log("Documet: " + witemName + " has been created", true);
			ATUReports.add("Documet: " + witemName + " has been created", true);

			// Select the created workitem
			cwp.getCheckBoxWorkItem(witemName).click();
			Reporter.log("Selected: " + witemName + " to rename the workitem", true);
			ATUReports.add("Selected: " + witemName + " to rename the workitem", true);

			// Click on the rename workitem button
			cwp.getRenameWorkItemBtn().click();
			Thread.sleep(3000);
			boolean renameWinPresent = util.verifyObjectPresentReturnsBool(cwp.getRenameWorkItemWin());

			if (renameWinPresent) {
				Reporter.log("Rename Workitem dialog box is open", true);
				ATUReports.add("Rename Workitem dialog box is open", true);
			} else {
				Reporter.log("Rename Work item dialog box is not open", true);
				ATUReports.add("Rename Work item dialog box is not open", true);
				Assert.fail("Rename Work item dialog box is not open");
			}

			Reporter.log("New workItem name is " + newWitemName, true);
			ATUReports.add("New workItem name is " + newWitemName, true);
			Thread.sleep(2000);

			// Rename the work item
			cwp.getNewWorkItemName_TF().clear();
			Thread.sleep(2000);
			cwp.getNewWorkItemName_TF().sendKeys(newWitemName);

			Thread.sleep(2000);
			util.jclick(cwp.getRenameWorkItemAcceptBtn());
			util.waitForPageToLoad();
			
			cwp.searchByNameInWorkitemList(newWitemName);
			util.waitForPageToLoad();
			boolean newWItemPresent = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(newWitemName));
			if (newWItemPresent) {
				ATUReports.add("Verify workitem is renamed", "new workitem name is" + newWitemName,
						"new workitem name should displayed", "New workitem name is displayed in the workitem list",
						true);

			}
		} catch (Exception exc) {
			exc.printStackTrace();
			Log.error(exc.getMessage());
			ATUReports.add("Rename workitem failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			ATUReports.add("failed to execute test INFO_3701_RenameWorkItem",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));

		} finally {
			Log.endTestCase("renameWorkItem");
		}
	}

	/**
	 * This test method will create a WorkItem and validate Cancelling Rename
	 * workItem functionality
	 *  
	 * @author pradhanJ
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = false, priority = 2)
	public void renameCancelWorkItem() {
		Log.startTestCase("renameCancelWorkItem");
		String witemName = "jay" + util.getSysDate(0, "yyDDMMhhmmss");
		String newWitemName = "jayNew" + util.getSysDate(0, "yyDDMMhhmmss");
		ATUReports.setTestCaseReqCoverage("This Scenario will create a WorkItem and validate Cancelling Rename workItem functionality");
		ATUReports.setAuthorInfo("PradhanJ","APR-2018","0.3"); 
		
		try {
			// Create a workitem

			cwp.CreateWorkitem(witemName, "archive", "Document");
			Reporter.log("Documet: " + witemName + " has been created", true);
			ATUReports.add("Documet: " + witemName + " has been created", true);

			// Select the created workitem
			cwp.getCheckBoxWorkItem(witemName).click();
			Reporter.log("Selected: " + witemName + " to rename", true);
			ATUReports.add("Selected: " + witemName + " to rename", true);
			Thread.sleep(2000);
			// Click on the rename workitem button
			cwp.getRenameWorkItemBtn().click();

			// Check if rename workItem window is opened or not
			boolean renameWinPresent = util.verifyObjectPresentReturnsBool(cwp.getRenameWorkItemWin());

			if (renameWinPresent) {
				Reporter.log("Rename Work item dialog box is open", true);
				ATUReports.add("Rename Work item dialog box is open", true);
			} else {
				Reporter.log("Rename Work item dialog box is not open", true);
				ATUReports.add("Rename Work item dialog box is not open", true);
				Assert.fail("Rename Work item dialog box is not open");
			}

			Reporter.log("New workItem name is " + newWitemName, true);
			ATUReports.add("New workItem name is " + newWitemName, true);

			Thread.sleep(2000);

			// Rename the work item
			cwp.getNewWorkItemName_TF().clear();
			cwp.getNewWorkItemName_TF().sendKeys(newWitemName);
			util.wait(3000);
			
			// Cancel the rename workitem
			util.jclick(cwp.getRenameWorkitemCloseBtn());
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(witemName);
			Thread.sleep(4000);
			boolean oldWItemPresent = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(witemName));
			if (oldWItemPresent) {
				Reporter.log("Work item  " + witemName + " is not renamed to " + newWitemName, true);
				ATUReports.add("Cancelling rename workitem",
						"Old workitem name :" + witemName + " New workitem name: " + newWitemName,
						"Workitem will not be renamed", "Workitem is not renamed", true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add("Cancelling rename workitem", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));

		} finally {

			Log.endTestCase("renameCancelWorkItem");
		}

	}
}
