package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
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
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author DashBisw INFO-8744 This class will Perform "GetNext" Functionality
 *         For MultipleWorkItems
 */
public class INFO_8744 extends SuperClassIWM {
	@BeforeMethod
	public void beforMethod() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod() {
		// hp.logoutApp();
		System.out.println("Application successfully logged out");
	}

	/**
	 * This class will Perform "GetNext" Functionality For MultipleWorkItems.
	 * 
	 * @author Biswajit 24/04/2018
	 */

	@SuppressWarnings("deprecation")
	@Test(groups = { "GetNext" })
	public void performingGetNextFunctionalityForMultipleWorkItems() {
		String workitem = "str" + util.getSysDate(0, "yyDDMMhhmmss");
		Log.startTestCase("INFO_8744_PerformingGetNextFunctionalityForMultipleWorkItems");
		try {

			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8744  is To verify"
					+ " performing GetNext functionality for multiple workitems in default queue.");
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
			Thread.sleep(5000);

			ip.getDataEntry1().click();
			Reporter.log("DataEntry inbox opened successfully", true);
			ATUReports.add("DataEntry inbox opened successfully", true);
			Log.info("DataEntry inbox opened successfully");
			Thread.sleep(5000);

			String clickedText = ip.getQueueWorkItem1().getText();
			System.out.println(clickedText);
			util.waitForPageToLoad();
			Thread.sleep(5000);

			hp.getWorkItemTab().click();
			Reporter.log("WorkItemTab clicked successfully", true);
			ATUReports.add("WorkItemTab clicked successfully", true);
			Log.info("WorkItemTab clicked successfully");
			Thread.sleep(2000);

			// To check whether workitem is present in the List
			int num = ip.getworkItemList().size();
			String status = "";

			for (int i = 1; i <= num; i++) {
				String str = Driver.driver
						.findElement(By.xpath("//table[@id='workitemTable']/tbody/tr[" + i + "]/td[2]/span/a/font"))
						.getText();
				if (str.equalsIgnoreCase(clickedText)) {
					status += String.valueOf(false);
				} else {
					status += String.valueOf(true);
				}

			}

			if (status.contains("false")) {
				Reporter.log("selected workitem is retrieved to the workitems list", true);
				ATUReports.add("selected workitem is retrieved to the workitems list", true);
				Log.info("selected workitem is retrieved to the workitems list");
			} else {
				Reporter.log("None of the workitems will be retrieved to the workitems list ", true);
				ATUReports.add("None of the workitems will be retrieved to the workitems list", false);
				Log.info("None of the workitems will be retrieved to the workitems list");
			}

			hp.getInbox().click();
			util.waitForPageToLoad();
			ip.getNextOFFButton().click();
			ATUReports.add("Get Next option is changed to off state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();
			hp.logoutApp();

		}

		catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			// ATUReports.add("performing GetNext functionality for multiple workitems in
			// default queue", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		} finally {

			Log.endTestCase("INFO_8744_PerformingGetNextFunctionalityForMultipleWorkItems");
		}
	}
}
