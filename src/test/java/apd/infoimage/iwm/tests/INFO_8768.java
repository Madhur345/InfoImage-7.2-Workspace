package apd.infoimage.iwm.tests;

import org.apache.log4j.xml.DOMConfigurator;
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
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author DashBisw INFO-8768 This Class is Performing GetNext Functionality For
 *         MultipleWorkItems In Any Queue For sMultipleTimes. 30/04/2018
 */
public class INFO_8768 extends SuperClassIWM {

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
	public void performingGetNextFunctionalityForMultipleWorkItems() {
		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("INFO_8768_PerformingGetNextFunctionalityForMultipleWorkitemsInAnyQueueForMultipleTimes");

		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8768  is To verify"
					+ " performing GetNext functionality for multiple workitems In Any Queue For MultipleTimes");
			ATUReports.setAuthorInfo("Biswajit", "APR-2018", "0.3");

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("Create Workitem operation performed", true);
			ATUReports.add("Create Workitem operation performed", true);
			Thread.sleep(2000);

			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("Sending Workitem to Workflow", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			hp.getWorkItemTab().click();
			Reporter.log("workitems tab clicked Successfully ", true);
			ATUReports.add("workitems tab clicked Successfully  ", true);
			Thread.sleep(2000);

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("Create Workitem operation performed", true);
			ATUReports.add("Create Workitem operation performed", true);
			Thread.sleep(2000);

			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("Sending Workitem to Workflow", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			hp.getInbox().click();
			Reporter.log("Inbox tab clicked successfully ", true);
			ATUReports.add("Inbox tab clicked successfully ", true);
			Thread.sleep(2000);

			ip.getNextONButton().click();
			ATUReports.add("Verify Get Next option is in On state", "", "Get Next option should be in On state",
					"Get Next option is changed to On state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getSaveButton().click();
			Reporter.log("SaveButton clicked successfully ", true);
			ATUReports.add("SaveButton clicked successfully ", true);
			Thread.sleep(2000);

			ip.getDataEntry().click();
			Reporter.log("DataEntry inbox opened successfully ", true);
			ATUReports.add("DataEntry inbox opened successfully ", true);
			Thread.sleep(2000);

			int num = ip.getworkItemList().size();
			Reporter.log("Three Workitems is displaying ", true);
			ATUReports.add("num - " + num, true);

			ip.getrequiredNoOfWorkitems().clear();
			ip.getrequiredNoOfWorkitems().sendKeys("4");
			ip.getSaveButton().click();
			Reporter.log("SaveButton clicked successfully ", true);
			ATUReports.add("SaveButton clicked successfully ", true);
			Thread.sleep(2000);

			ip.getDataEntry().click();
			util.waitForPageToLoad();
			Reporter.log("DataEntry inbox opened successfully ", true);
			ATUReports.add("DataEntry inbox opened successfully ", true);
			Thread.sleep(2000);

			int num1 = ip.getworkItemList().size();
			System.out.println("num - " + num1);
			Reporter.log("Workitem displaying by according to max workitem selected by user", true);
			ATUReports.add("num - " + num1, true);

			hp.getInbox().click();
			util.waitForPageToLoad();
			ip.getNextOFFButton().click();
			ATUReports.add("Get Next option is changed to off state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();
		} catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add(
					"Failed to Performing GetNext Functionality For  MultipleWorkItems In Any Queue For sMultipleTimes",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		} finally {
			Log.endTestCase("INFO_8768_PerformingGetNextFunctionalityForMultipleWorkitemsInAnyQueueForMultipleTimes");

		}
	}
}
