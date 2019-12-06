package apd.infoimage.iwm.tests;

import org.apache.log4j.xml.DOMConfigurator;
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
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author DashBisw INFO-8769 This Class is Performing Performing 'GetNext'
 *         Functionality For Workitems In Any Queue With Max Items Set To Zero.
 *         04/05/2018
 */

public class INFO_8769 extends SuperClassIWM {

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
	public void performingGetNextFunctionalityForWorkitemsInAnyQueueWithMaxTtemsSetToZero() {

		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("INFO_8769_PerformingGetNextFunctionalityForWorkitemsInAnyQueueWithMaxTtemsSetToZero");

		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8769  is To verify"
					+ " Performing 'GetNext' Functionality For Workitems In Any Queue With Max Items Set To Zero");
			ATUReports.setAuthorInfo("Biswajit", "APR-2018", "0.3");

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("1st work item Created sucessfully", true);
			ATUReports.add("1st workitem Created sucessfully", true);
			Log.info("1st workitem Created sucessfully");
			Thread.sleep(2000);

			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("1st workitem successfully sent to default queue", true);
			ATUReports.add("1st workitem successfully sent to default queue", true);
			Log.info("1st workitem successfully sent to default queue");
			Thread.sleep(2000);
			util.waitForPageToLoad();

			hp.getWorkItemTab().click();
			Reporter.log("workitems tab clicked Successfully ", true);
			ATUReports.add("workitems tab clicked Successfully  ", true);
			Log.info("workitems tab clicked Successfully ");
			Thread.sleep(2000);

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("2nd workitem Created sucessfully", true);
			ATUReports.add("2nd workitem Created sucessfully", true);
			Log.info("2nd workitem Created sucessfully");
			Thread.sleep(2000);

			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("2nd workitem successfully sent to default queue", true);
			ATUReports.add("2nd workitem successfully sent to default queue", true);
			Log.info("1st workitem Created sucessfully");
			Thread.sleep(2000);
			util.waitForPageToLoad();

			Thread.sleep(2000);
			util.waitForPageToLoad();

			hp.getInbox().click();
			Reporter.log("Inbox tab clicked successfully ", true);
			ATUReports.add("Inbox tab clicked successfully ", true);
			Log.info("2nd workitem successfully sent to default queue");
			Thread.sleep(2000);

			ip.getNextONButton().click();
			ATUReports.add("Verify Get Next option is in On state", "", 
					"Get Next option should be in On state", "Get Next option is changed to On state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getrequiredNoOfWorkitems().clear();
			ip.getrequiredNoOfWorkitems().sendKeys("0");
			Reporter.log("max items set to 0", true);
			ATUReports.add("max items set to 0", true);
			Log.info("max items set to 0");

			ip.getSaveButton().click();
			Reporter.log("SaveButton clicked successfully ", true);
			ATUReports.add("SaveButton clicked successfully ", true);
			Log.info("SaveButton clicked successfully");
			Thread.sleep(2000);

			ip.getDataEntry().click();
			Reporter.log("DataEntry inbox opened successfully ", true);
			ATUReports.add("DataEntry inbox opened successfully ", true);
			Log.info("DataEntry inbox opened successfully");
			Thread.sleep(2000);

			int num1 = ip.getworkItemList().size();
			System.out.println("num - " + num1);
			Reporter.log("Queue is empty", true);
			ATUReports.add("Queue is empty ", true);
			Log.info("Queue is empty ");

			hp.getWorkItemTab().click();
			Reporter.log("WorkItemTab clicked successfully", true);
			ATUReports.add("WorkItemTab clicked successfully", true);
			Log.info("WorkItemTab clicked successfully");
			Thread.sleep(2000);

			int count1 = ip.getDataEntryworkItemList().size();
			Reporter.log("Count - " + count1);
			ATUReports.add("Count - " + count1, true);

			String status = "";
			for (int i = 1; i <= count1; i++) {
				String str1 = Driver.driver
						.findElement(By.xpath("//tbody[@id='itemContainer']/tr[" + i + "]/td[2]/span/a/font"))
						.getText();

				if (workitem.equals(str1)) {
					status += String.valueOf(true);
				} else {
					status += String.valueOf(false);
				}

			}

			if (status.contains("true")) {
				Reporter.log("Created workitem is retrieved to the workitems list", true);
				ATUReports.add("Created workitem is retrieved to the workitems list", true);
				Log.info("Created workitem is retrieved to the workitems list");

			} else {
				Reporter.log("Created workitem is not retrieved to the workitems list", true);
				ATUReports.add("Created workitem is not retrieved to the workitems list", true);
				Log.info("max items set to 0");

			}
			hp.getInbox().click();
			util.waitForPageToLoad();
			ip.getNextOFFButton().click();
			ATUReports.add("Get Next option is changed to off state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

		} catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add(
					"Failed to Performing 'GetNext' Functionality For Workitems In Any Queue With Max Items Set To Zero",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		} finally {
			Log.endTestCase("INFO_8769_PerformingGetNextFunctionalityForWorkitemsInAnyQueueWithMaxTtemsSetToZero");
		}
	}
}
