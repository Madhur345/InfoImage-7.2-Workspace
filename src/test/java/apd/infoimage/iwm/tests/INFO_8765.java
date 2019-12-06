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
 * @author DashBisw INFO-8765 This Class is Performing GetNext Functionality For
 *         MultipleWorkItems By Disabling Autoview And Sending The Workitem To
 *         Default Queue. 30/04/2018
 */
public class INFO_8765 extends SuperClassIWM {
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
		Log.startTestCase(
				"INFO_8765_PerformingGetNextFunctionalityForMultipleWorkItemsByDisablingAutoviewAndSendingTheWorkitemToDefaultQueue");

		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8765  is To verify"
					+ " performing GetNext functionality for multiple workitems in default queue.");
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
			ATUReports.add("Verify Get Next option is in On state", "", "Get Next option should be in On state",
					"Get Next option is changed to On state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			if (ip.getNextAutoOpenCheckbox().isSelected()) {

			} else {
				ip.getNextAutoOpenCheckbox().click();
				Reporter.log("get Next Auto Open Checkbox is clicked", true);
				ATUReports.add("get Next Auto Open Checkbox is clicked", true);
				Thread.sleep(2000);
				util.waitForPageToLoad();
			}

			ip.getSaveButton().click();
			Reporter.log("SaveButton clicked successfully ", true);
			ATUReports.add("SaveButton clicked successfully ", true);
			Log.info("SaveButton clicked successfully");
			Thread.sleep(2000);

			ip.getDataEntry().click();
			Reporter.log("DataEntry inbox opened successfully ", true);
			ATUReports.add("DataEntry inbox opened successfully ", true);
			Log.info("DataEntry inbox opened successfully ");
			Thread.sleep(2000);

			String workvalue = ip.getDataEntryFirstWorkItem().getText();
			ip.getDataEntryFirstWorkItem().click();
			Thread.sleep(5000);

			ip.getActions().click();
			Reporter.log("Actions dropdown field successfully opened ", true);
			ATUReports.add("Actions dropdown field successfully opened", true);
			Log.info("Actions dropdown field successfully opened");
			Thread.sleep(2000);

			ip.getSendToDefault().click();
			Reporter.log("send the workitem to default", true);
			ATUReports.add("send the workitem to default", true);
			Log.info("send the workitem to default");
			util.waitForPageToLoad();
			Thread.sleep(10000);

			hp.getInbox().click();
			Reporter.log("Inbox tab clicked successfully ", true);
			ATUReports.add("Inbox tab clicked successfully ", true);
			Log.info("2nd workitem successfully sent to default queue");
			Thread.sleep(2000);

			ip.getInvoiceProcessing().click();
			Reporter.log("Invoice Processing inbox clicked successfully ", true);
			ATUReports.add("Invoice Processing inbox clicked successfully ", true);
			Log.info("Invoice Processing inbox clicked successfully");
			util.waitForPageToLoad();

			// To check whether workitem is present in the List
			int num = ip.getworkItemList().size();
			System.out.println("num - " + num);
			String status = "";

			for (int i = 1; i <= num; i++) {
				String str1 = Driver.driver
						.findElement(By.xpath("//table[@id='workitemTable']/tbody/tr[" + i + "]/td[2]/span/a/font"))
						.getText();
				if (str1.equalsIgnoreCase(workvalue)) {
					status += String.valueOf(false);
				} else {
					status += String.valueOf(true);
				}

			}

			if (status.contains("false")) {

				ATUReports.add("selected workitem is present in the List", true);
				Log.info("selected workitem is present in the List");
			} else {

				ATUReports.add("selected workitem is Not present in the List", false);
				Log.info("selected workitem is Not present in the List");
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
					"Failed to Performing GetNext Functionality For  MultipleWorkItems By Disabling Autoview And Sending The Workitem To Default Queue.",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		} finally {
			Log.endTestCase(
					"INFO_8765_PerformingGetNextFunctionalityForMultipleWorkItemsByDisablingAutoviewAndSendingTheWorkitemToDefaultQueue");

		}
	}
}
