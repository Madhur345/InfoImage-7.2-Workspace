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

/**
 * @author PradhanJ
 *
 */
public class INFO_8564 extends SuperClassIWM {

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
	 * This test method will create a Create a view for for Inbox ,apply and
	 * validate the same in Inbox page
	 * 
	 * @throws Exception
	 */

	@SuppressWarnings("deprecation")
	@Test(groups = { "UserPreference" })
	public void testApplyInboxView() {
		String viewName = "InboxView" + util.getSysDate(0, "yyDDMMhhmmss");
		try {
			// Navigate to user preference tab.
			hp.clickUserPreferenceTab();
			util.waitForPageToLoad();
			// Thread.sleep(5000);
			System.out.println("In user preference page.............");
			ATUReports.add("In user preference page.............", true);
			Log.info("In user preference page.............");
			// Create a view for Inbox.

			upp.getInboxRadioBtn().click();
			System.out.println("inbox radio clicked...........");
			ATUReports.add("inbox radio clicked...........", true);
			Log.info("inbox radio clicked...........");
			Thread.sleep(3000);

			upp.getViewNameTextbox().sendKeys(viewName);

			// Select fields from available
			upp.selectFieldsFromAvailable("CLASS", "DOMAIN", "OBJECT_ID");
			upp.getCreateViewBtn().click();
			util.waitForPageToLoad();

			// Validate whether view is created or not
			upp.validateViewCreated(viewName);

			// Apply the created inbox view.

			upp.getCreatedViewNameRadio(viewName).click();
			upp.getApplyViewBtn().click();

			util.waitForPageToLoad();
			boolean applySuccessMsgPresent = util.verifyObjectPresentReturnsBool(upp.getSuccessMsg());
			if (applySuccessMsgPresent) {
				Reporter.log(" Inbox View is applied successfully.", true);
				ATUReports.add("Inbox View is applied successfully", true);
				Log.info("Inbox View is applied successfully");
			} else {
				Reporter.log("Inbox View is not applied", true);
				Assert.fail("Inbox View is not applied");
			}
			// validate the applied view in Inbox list page
			try {
				hp.getInbox().click();
				util.waitForPageToLoad();

				ip.getDataEntry().click();

				util.waitForPageToLoad();

				boolean verifyHeaders = ip.verifyHeadersInInbox();

				if (verifyHeaders) {
					Reporter.log("Inbox view is applied and verified " + "successfully in workitem list page", true);
					ATUReports.add("Inbox view is applied and verified " + "successfully in workitem list page", true);
					Log.info("Inbox view is applied and verified " + "successfully in workitem list page");
				} else {
					Reporter.log("Inbox view verification failed.", true);
					Assert.fail("Inbox view verification failed.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				Reporter.log("Inbox view verification failed.", true);
				Log.error("Inbox view verification failed." + e.getMessage());
			}

			// Navigate to user preference page and Delete the created View for Inbox
			
			hp.getUserPreferencesTab().click();
			util.waitForPageToLoad();

			upp.deleteView("Inbox", viewName);

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Applying view for inbox test failed.", true);
			Log.error("Applying view for inbox test failed." + e.getMessage());
			Assert.fail("Applying view for inbox test failed.");
		}
	}
}
