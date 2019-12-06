package apd.infoimage.rm.tests;

import java.io.FileInputStream;
import java.util.Properties;

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

public class INFO_10741_NotificationforScheduler extends SuperClassIWM {

	@BeforeMethod
	public void beforMethod() {
		Driver.driver.get(rmProp.getProperty("rmUrl"));
		util.waitForPageToLoad();
		rmlp.loginToRM("userid", "pwd", "domain");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod() {
		rmhp.logoutOfRM();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testVerifyRunSchedulerPopUp() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-10741 is to Verify Valid Pop-up is displayed when Run Scheduler link is clicked");
		ATUReports.setAuthorInfo("GuptaPr2", "JUL-2018", "0.3");

		try {
			Log.startTestCase("INFO_10741_VerifyNotificationforScheduler");

			rmProp = new Properties();
			rmProp.load(new FileInputStream("src/main/resources/rmData.properties"));

			Reporter.log("User is in Homepage of RM", true);
			ATUReports.add("User is in Homepage of RM", true);
			Log.info("User is in Homepage of RM");

			rmhp.getRunSchedulerLink().click();
			Reporter.log("Run Scheduler Link is clicked", true);
			ATUReports.add("Run Scheduler Link is clicked", true);
			Log.info("Run Scheduler Link is clicked");

			util.waitForPageToLoad();
			util.waitForElementEnabled(rmhp.getRunSchedulerSuccessMessage());

			if (rmhp.getRunSchedulerSuccessMessage().isEnabled()) {
				String popUpMessage = rmhp.getRunSchedulerSuccessMessage().getText();
				if (popUpMessage.contains("Scheduler start initiated successfully")) {
					Reporter.log("Pop Up Message is Displayed", true);
					Log.info("Pop Up Message is Displayed");
					ATUReports.add("Pop Up Message is Displayed", true);
				} else {
					Reporter.log("Pop Up Message is not Displayed", true);
					Log.info("Pop Up Message is not Displayed");
					ATUReports.add("Pop Up Message is not Displayed", true);
					ATUReports.add("VerifyNotificationforScheduler test failed", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("VerifyNotificationforScheduler test failed");
				}
			} else {
				Reporter.log("Run Scheduler Success Message is not Displayed", true);
				ATUReports.add("Run Scheduler Success Message is not Displayed", true);
				Log.info("Run Scheduler Success Message is not Displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Log.error("VerifyRunSchedulerPopUp test failed " + e.getMessage());
			ATUReports.add("Failed to Verify Run Scheduler Pop Up test in rm.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Reporter.log("VerifyNotificationforScheduler test failed", true);
			Assert.fail("VerifyNotificationforScheduler test failed");
		} finally {
			Log.endTestCase("INFO_10741_VerifyRunSchedulerPopUpTest");
		}
	}
}


