package apd.infoimage.iwm.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author PradhanJ
 *
 */
public class INFO_8594 extends SuperClassIWM {

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
	 * This test method will create a Create a view for Search and validate the same
	 * 
	 * @throws Exception
	 */

	@Test(enabled = true, groups = { "UserPreference" })
	public void createViewForSearch() {
		String viewName = "searchView" + util.getSysDate(0, "yyDDMMhhmmss");
		try {
			// Navigate to user preference tab.
			hp.clickUserPreferenceTab();

			Thread.sleep(5000);

			System.out.println("In user preference page.............");
			// Create a view for Search.

			upp.getSearchRadioBtn().click();
			System.out.println("search radio clicked...........");

			Thread.sleep(3000);

			upp.getViewNameTextbox().sendKeys(viewName);

			// Select fields from available

			upp.selectFieldsFromAvailable("ID_CODE", "INVOICE_NO", "TERMS");
			upp.getCreateViewBtn().click();
			util.waitForPageToLoad();

			// Validate whether view is created or not
			upp.validateViewCreated(viewName);

			// Delete the created View
			upp.deleteView("Search", viewName);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Creating view for workitems test failed.", true);
			Assert.fail("Creating view for workitems test failed.");
		}
	}
}
