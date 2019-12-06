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

/**
 * @author DashBisw INFO-8756 This class will verify GetNextMode Off
 *         functionality
 */
public class INFO_8756 extends SuperClassIWM {

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
	@Test(groups = { "GetNext" })
	public void VerifyGetNextModeOff() {
		Log.startTestCase("INFO_8756_VerifyGetNextMode");
		String workitem = "dashbisw" + util.getSysDate(0, "yyDDMMhhmmss");

		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8756  is To verify"
					+ " 'Get Next' mode is turned off when we switch to workitems and back to the Inbox");
			ATUReports.setAuthorInfo("Biswajit", "APR-2018", "0.3");

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log(" workitem Created sucessfully", true);
			ATUReports.add("workitem Created sucessfully", true);
			Log.info("workitem Created sucessfully");
			Thread.sleep(2000);

			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("workitem successfully sent to default queue", true);
			ATUReports.add("workitem successfully sent to default queue", true);
			Log.info("workitem successfully sent to default queue");
			Thread.sleep(2000);
			util.waitForPageToLoad();

			hp.getInbox().click();
			Reporter.log("Inbox tab clicked successfully ", true);
			ATUReports.add("Inbox tab clicked successfully ", true);
			Log.info("Inbox tab clicked successfully");
			Thread.sleep(2000);

			ip.getNextONButton().click();
			ATUReports.add("Verify Get Next option is in On state", "", "Get Next option should be in On state",
					"Get Next option is changed to On state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getSaveButton().click();
			Reporter.log("SaveButton clicked successfully", true);
			ATUReports.add("SaveButton clicked successfully", true);
			Log.info("SaveButton clicked successfully");
			Thread.sleep(3000);

			hp.getWorkItemTab().click();
			Reporter.log("WorkItemTab clicked successfully", true);
			ATUReports.add("WorkItemTab clicked successfully", true);
			Log.info("WorkItemTab clicked successfully");
			Thread.sleep(3000);

			hp.getInbox().click();
			Reporter.log("Inbox tab clicked successfully ", true);
			ATUReports.add("Inbox tab clicked successfully ", true);
			Log.info("Inbox tab clicked successfully");
			Thread.sleep(2000);

			String status = ip.getNextStatusOn().getAttribute("class");
			if (status.isEmpty()) {
				Reporter.log("Verified 'Get Next' button is in off mode", true);
				ATUReports.add("Verified 'Get Next' button is in off mode", true);
				Log.info("Verified 'Get Next' button is in off mode");

			}

			hp.getInbox().click();
			util.waitForPageToLoad();
			ip.getNextOFFButton().click();
			ATUReports.add("Get Next option is changed to off state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

		}

		catch (Exception e) {
			Reporter.log("Failed to Verify 'Get Next' is switched off " + e.getMessage());
			ATUReports.add("Failed to Verify Get Next is switched off", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Get Next is switched off test failed");

		}

		finally {
			Log.endTestCase("INFO_8756_VerifyGetNextMode");

		}
	}
}
