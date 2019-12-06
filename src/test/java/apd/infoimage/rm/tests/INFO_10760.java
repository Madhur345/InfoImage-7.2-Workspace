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

/**
 * @author SumanGaK - 28-Jun-2018 
 * INFO-10760 
 * This class will Verify Alert Messages are Deleted
 **/
public class INFO_10760 extends SuperClassIWM {

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
	public void testVerifyDeleteAlertMessages() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-11098  is to Verify Alert Messages are Deleted");
		ATUReports.setAuthorInfo("SumanGaK", "JUN-2018", "0.3");

		try {
			Log.startTestCase("INFO_10760_VerifyDeleteAlertMessagesTest");

			rmProp = new Properties();
			rmProp.load(new FileInputStream("src/main/resources/rmData.properties"));

			Reporter.log("User is in Homepage of RM", true);
			ATUReports.add("User is in Homepage of RM", true);
			Log.info("User is in Homepage of RM");

			rmhp.getAlertMessagesButton().click();
			Reporter.log("Alert Messages Button is clicked", true);
			ATUReports.add("Alert Messages Button is clicked", true);
			Log.info("Alert Messages Button is clicked");

			if(!rmhp.getDeleteAllAlertsButton().isEnabled())
			{
				rmhp.getRunSchedulerLink().click();
				Reporter.log("Run Scheduler Link is clicked", true);
				ATUReports.add("Run Scheduler Link is clicked", true);
				Log.info("Run Scheduler Link is clicked");
			}

			util.waitForPageToLoad();
			Thread.sleep(20000);
			rmhp.getDeleteAllAlertsButton().click();
			Reporter.log("Delete All Alerts Button is clicked", true);
			ATUReports.add("Delete All Alerts Button is clicked", true);
			Log.info("Delete All Alerts Button is clicked");

			rmhp.getDeleteAllAlertsYesButton().click();
			Reporter.log("Delete All Alerts Yes Button is clicked", true);
			ATUReports.add("Delete All Alerts Yes Button is clicked", true);
			Log.info("Delete All Alerts Yes Button is clicked");

			String alertMessagesDetails = rmhp.getFirstRowFirstCell().getText();
			if (alertMessagesDetails.contains("No Alert messages found")){
				Reporter.log("Alert Messages are Deleted", true);
				Log.info("Alert Messages are Deleted");
				ATUReports.add("Alert Messages are Deleted", true);
			} else {
				Reporter.log("Alert Messages are not Deleted", true);
				Log.info("Alert Messages are not Deleted");
				ATUReports.add("Alert Messages are not Deleted", true);
				ATUReports.add("VerifyDeleteAlertMessages test failed", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("VerifyDeleteAlertMessages test failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Log.error("VerifyDeleteAlertMessages test failed " + e.getMessage());
			ATUReports.add("Failed to Verify DeleteAlertMessages test in rm.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Reporter.log("VerifyDeleteAlertMessages test failed", true);
			Assert.fail("VerifyDeleteAlertMessages test failed");
		} finally {
			Log.endTestCase("INFO_10760_VerifyDeleteAlertMessagesTest");
		}
	}	
}
