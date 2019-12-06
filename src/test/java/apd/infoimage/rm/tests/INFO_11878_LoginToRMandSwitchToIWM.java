package apd.infoimage.rm.tests;

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
 * @author PradhanJ
 * INFO-11878
 * RM: Login to RM and Switch to IWM (Vice-versa)
 */
public class INFO_11878_LoginToRMandSwitchToIWM extends SuperClassIWM {

	@BeforeMethod
	public void beforMethod() {
		Driver.driver.get(rmProp.getProperty("rmUrl"));
		util.waitForPageToLoad();
		rmlp.loginToRM("uid", "pwd", "domain");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod() {
		rmhp.logoutOfRM();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testLoginToRMandSwitchToIWM() {
		try {
			Log.startTestCase("INFO_11878_LoginToRMandSwitchToIWM");

			ATUReports.setTestCaseReqCoverage(
					"This Scenario INFO-11878  is to Login to RM and Switch to IWM (Vice-versa) ");

			ATUReports.setAuthorInfo("Jayashri", "JUNE-2018", "0.3");

			Reporter.log("User is in Homepage of RM", true);
			ATUReports.add("User is in Homepage of RM", true);
			Log.info("User is in Homepage of RM");

			rmhp.getMySitesField().click();
			Reporter.log("MySites Link is clicked", true);
			ATUReports.add("MySites Link is clicked", true);
			Log.info("MySites Link is clicked");

			rmhp.getIwmSite().click();
			Reporter.log("IWM site is clicked", true);
			ATUReports.add("IWM site is clicked", true);
			Log.info("IWM site is clicked");
			util.waitForPageToLoad();

			boolean f = util.verifyObjectPresentReturnsBool(hp.getWorkItemTab());
			System.out.println("boolean value is " + f);

			if (f) {
				Reporter.log("User is switched to IWM application", true);
				Log.info("User is switched to IWM application");
				ATUReports.add("Switch to IWM application ", "",
						"User should be switched to IWM application Successfully",
						"User is switched to IWM application Successfully", true);
			} else {

				Reporter.log("Switching to IWM application failed", true);
				ATUReports.add("Switching to IWM application failed", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Log.error("Switching to IWM application failed");
				Assert.fail("Switching to IWM application failed");
			}

			hp.getMySitesField().click();
			Reporter.log("MySites Link is clicked in IWM application", true);
			ATUReports.add("MySites Link is clicked", true);
			Log.info("MySites Link is clicked");

			hp.getRmSite().click();
			Reporter.log("RM site is clicked from IWM application", true);
			ATUReports.add("RM site is clicked", true);
			Log.info("RM site is clicked");
			util.waitForPageToLoad();

			boolean f1 = util.verifyObjectPresentReturnsBool(rmhp.getDashboardTab());
			System.out.println("boolean value is " + f1);

			if (f1) {
				Reporter.log("User is switched to RM application", true);
				Log.info("User is switched to RM application");
				ATUReports.add("Switch to RM application ", "",
						"User should be switched to RM application Successfully",
						"User is switched to RM application Successfully", true);
			} else {

				Reporter.log("Switching to RM application failed", true);
				ATUReports.add("Switching to RM application failed", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Log.error("Switching to RM application failed");
				Assert.fail("Switching to RM application failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Login to RM and Switch to IWM (Vice-versa) test failed " + e.getMessage());
			Reporter.log("Login to RM and Switch to IWM (Vice-versa) test failed", true);
			Assert.fail("Login to RM and Switch to IWM (Vice-versa) test failed");
		}
		finally {
			Log.endTestCase("INFO_11878_LoginToRMandSwitchToIWM");
		}
	}
}
