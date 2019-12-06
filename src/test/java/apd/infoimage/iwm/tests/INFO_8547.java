package apd.infoimage.iwm.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author PradhanJ INFO-8547 This class will Create a view for workItems and
 *         validate the same
 */
public class INFO_8547 extends SuperClassIWM {

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
	@Test(enabled = true, groups = { "UserPreference" })
	public void createViewForWorkitems() {
		String viewName = "WItemView" + util.getSysDate(0, "yyDDMMhhmmss");
		try {

			ATUReports.setTestCaseReqCoverage(
					"This Scenario INFO-8547  is to Create a view for workItems and validate the same");
			ATUReports.setAuthorInfo("Jayashri", "APR-2018", "0.3");

			Reporter.log("Navigate to user preference tab.", true);
			ATUReports.add("Navigate to user preference tab.", true);

			hp.clickUserPreferenceTab();
			util.waitForPageToLoad();
			Thread.sleep(5000);
			System.out.println("In user preference page.............");
			// Create a view for workitems.

			upp.getWorkitemRadioBtn().click();
			System.out.println("witem radio clicked...........");
			ATUReports.add("witem radio clicked", true);
			Thread.sleep(3000);

			upp.getViewNameTextbox().sendKeys(viewName);
			Reporter.log("View name is entered", true);
			ATUReports.add("View name is entered", true);

			Reporter.log("Select fields from available", true);
			ATUReports.add("Select fields from available", true);
			upp.selectFieldsFromAvailable("ID_CODE", "INVOICE_NO", "TERMS");

			upp.getCreateViewBtn().click();
			Reporter.log("Create View button is clicked", true);
			ATUReports.add("Create View button is clicked", true);
			util.waitForPageToLoad();

			Reporter.log("Validate whether view is created or not", true);
			ATUReports.add("Validate whether view is created or not ", true);
			upp.validateViewCreated(viewName);

			Reporter.log("Delete the created View", true);
			ATUReports.add("Delete the created View	", true);
			upp.deleteView("Workitems", viewName);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Creating view for workitems test failed.", true);
			ATUReports.add("Creating view for workitems test failed", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Creating view for workitems test failed.");
		}
	}
}
