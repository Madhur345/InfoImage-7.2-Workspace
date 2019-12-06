package apd.infoimage.iwm.tests;

/**
 * @author DashBisw INFO_11122 This class is To Verify Verify The Alert Message Is Displayed In Red Colour. 20/08/2018
 */
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

public class INFO_11559 extends SuperClassIWM {

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
	public void verifyTheAlertMessageIsDisplayedInRedColour() {

		Log.startTestCase("VerifyTheAlertMessageIsDisplayedInRedColour");
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-11559  is to Verify The Alert Message Is Displayed In Red Colour");
		ATUReports.setAuthorInfo("Biswajit", "AUG-2018", "0.3");

		String witemName = "str" + util.getSysDate(0, "yyDDMMhhmmss");

		try {
			// Create a workitem

			cwp.CreateWorkitem(witemName, "archive", "Document");
			Reporter.log("Documet: " + witemName + " has been created", true);
			ATUReports.add("Documet: " + witemName + " has been created", true);
			Log.info(" Workitem has been created");

			// Select the created workitem
			cwp.getCheckBoxWorkItem(witemName).click();
			Reporter.log("Selected: " + witemName + " to rename the workitem", true);
			ATUReports.add("Selected: " + witemName + " to rename the workitem", true);
			Log.info("Selected: " + witemName + " to rename the workitem");

			// Click on the rename workitem button
			cwp.getRenameWorkItemBtn().click();
			Thread.sleep(3000);
			boolean renameWinPresent = util.verifyObjectPresentReturnsBool(cwp.getRenameWorkItemWin());

			if (renameWinPresent) {
				Reporter.log("Rename Workitem dialog box is open", true);
				ATUReports.add("Rename Workitem dialog box is open", true);
				Log.info("Rename Workitem dialog box is open");
			} else {
				Reporter.log("Rename Work item dialog box is not open", true);
				ATUReports.add("Rename Work item dialog box is not open", true);
				Assert.fail("Rename Work item dialog box is not open");
				Log.info("Rename Work item dialog box is not open");
			}

			// Rename the work item
			cwp.getNewWorkItemName_TF().clear();
			Thread.sleep(2000);
			Log.info("Cleared the workitem name field");
			ATUReports.add("Cleared the workitem name field", true);

			util.jclick(cwp.getRenameWorkItemAcceptBtn());
			util.waitForPageToLoad();
			Log.info("Rename WorkItem Accept Button clicked");
			ATUReports.add("Rename WorkItem Accept Button clicked", true);

			String alertmsg = "This field is required";
			String alertmsg1 = cwp.getemptyRenameWorkItemAlert().getText();

			if (alertmsg.equals(alertmsg1)) {
				ATUReports.add("Alert message is displaying 'This field is required'", true);
				Log.info("Alert message is displaying 'This field is required'");
				Reporter.log("Alert message is displaying 'This field is required'", true);

			} else {
				ATUReports.add("Alert message is not coming", true);
				Log.info("Alert message is not coming.");
				Reporter.log("Alert message is not coming.", true);
			}

		} catch (Exception exc) {
			exc.printStackTrace();
			Log.error(exc.getMessage());
			ATUReports.add("Rename workitem failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			ATUReports.add("failed to execute test INFO_3701_RenameWorkItem", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));

		} finally {
			Log.endTestCase("VerifyTheAlertMessageIsDisplayedInRedColour");
		}
	}
}
