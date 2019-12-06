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
public class INFO_8551 extends SuperClassIWM {
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
	 * This test method will create a Create a view for Inbox and validate the same
	 * 
	 * @throws Exception
	 */

	@Test(enabled = true, groups = { "UserPreference" })
	public void createViewForInbox() {
		String viewName = "InboxView"+ util.getSysDate(0, "yyDDMMhhmmss");
		try {
			// Navigate to user preference tab.
			hp.clickUserPreferenceTab();
			Thread.sleep(5000);

			Reporter.log("In user preference page.............",true);
			ATUReports.add("In user preference page.............", true);
			Log.info("In user preference page.............");
			// Create a view for Inbox.

			upp.getInboxRadioBtn().click();
			Reporter.log("inbox radio clicked...........",true);
			ATUReports.add("inbox radio clicked...........", true);
			Log.info("inbox radio clicked...........");
			Thread.sleep(5000);

			upp.getViewNameTextbox().sendKeys(viewName);

			// Select fields from available

			upp.selectFieldsFromAvailable("CLASS", "DOMAIN", "OBJECT_ID");

			upp.getCreateViewBtn().click();
			util.waitForPageToLoad();

			// Validate whether view is created or not
			upp.validateViewCreated(viewName);

			// Delete the created View
			//upp.deleteView("Inbox", viewName);
			upp.deleteView("Inbox", viewName);

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Creating view for workitems test failed.", true);
			Log.error("Creating view for workitems test failed."+e.getMessage());
			Assert.fail("Creating view for workitems test failed.");
		}
	}
}
