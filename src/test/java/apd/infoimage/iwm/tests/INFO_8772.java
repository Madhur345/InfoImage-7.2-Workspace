package apd.infoimage.iwm.tests;

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
 * @author DashBisw INFO-8772 This Class is Performing 'GetNext' Functionality
 *         For Multiple Workitems In Any Queue By Modifing One Workitem In The
 *         Queue. 07/05/2018
 */

public class INFO_8772 extends SuperClassIWM {

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
	public void performingGetNextfunctionalityforMultipleWorkitemsInAnyQueueByOpeningOneWorkitemInTheQueue() {
		String workitem = "Test" + util.getSysDate(0, "yyDDMMhhmmss");
		Log.startTestCase("INFO_8744_PerformingGetNextFunctionalityForMultipleWorkItems");
		Log.startTestCase(
				"INFO_8772_PerformingGetNextFunctionalityForMultipleWorkitemsInAnyQueueByModifingOneWorkitemInTheQueue");

		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8772  is To verify"
					+ " Performing 'GetNext' Functionality For Multiple Workitems In Any Queue By Modifing One Workitem In The Queue");
			ATUReports.setAuthorInfo("Biswajit", "MAY-2018", "0.3");

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

			ip.getNextStatusOn().click();
			Reporter.log("getNextStatuson button clicked successfully ", true);
			ATUReports.add("getNextStatuson button clicked successfully ", true);
			Log.info("getNextStatuson button clicked successfully");
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getrequiredNoOfWorkitems().clear();
			ip.getrequiredNoOfWorkitems().sendKeys("2");
			Reporter.log("maxitems successfully set ", true);
			ATUReports.add("maxitems successfully set", true);
			Log.info("maxitems successfully set");

			util.waitForElementEnabled(ip.getSaveButton());
			ip.getSaveButton().click();
			Reporter.log("SaveButton clicked successfully ", true);
			ATUReports.add("SaveButton clicked successfully ", true);
			Log.info("SaveButton clicked successfully");
			util.waitForPageToLoad();
			util.waitForElementEnabled(ip.getDataEntry1());

			ip.getDataEntry1().click();
			Reporter.log("DataEntry inbox opened successfully ", true);
			ATUReports.add("DataEntry inbox opened successfully ", true);
			Log.info("SaveButton clicked successfully");
			Thread.sleep(2000);

			String clickedText = ip.getQueueWorkItem1().getText();
			System.out.println(clickedText);
			util.waitForPageToLoad();
			Thread.sleep(5000);

			ip.getQueueWorkItem1().click();
			Reporter.log("First work Item clicked successfully", true);
			ATUReports.add("First work Item clicked successfully", true);
			Log.info("SaveButton clicked successfully");
			Thread.sleep(5000);

			ip.getMaximizeFormField().click();
			Reporter.log("Form field maximized successfully", true);
			ATUReports.add("Form field maximized successfully", true);
			Log.info("Form field maximized successfully");
			Thread.sleep(2000);

			wdp.getCloseWorkitem();
			Thread.sleep(2000);

			hp.getInbox().click();
			Reporter.log("Inbox tab clicked successfully ", true);
			ATUReports.add("Inbox tab clicked successfully ", true);
			Log.info("Inbox tab clicked successfully");
			Thread.sleep(2000);

			ip.getNextStatusOn().click();
			Reporter.log("getNextStatuson button clicked successfully ", true);
			ATUReports.add("getNextStatuson button clicked successfully ", true);
			Log.info("getNextStatuson button clicked successfully");
			Thread.sleep(9000);
			util.waitForPageToLoad();

			ip.getNextStatusOn().click();
			Reporter.log("getNextStatuson button clicked successfully ", true);
			ATUReports.add("getNextStatuson button clicked successfully ", true);
			Log.info("getNextStatuson button clicked successfully");
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getrequiredNoOfWorkitems().clear();
			ip.getrequiredNoOfWorkitems().sendKeys("2");
			Reporter.log("maxitems successfully set ", true);
			ATUReports.add("maxitems successfully set", true);
			Log.info("maxitems successfully set");

			ip.getSaveButton().click();
			Reporter.log("SaveButton clicked successfully ", true);
			ATUReports.add("SaveButton clicked successfully ", true);
			Log.info("SaveButton clicked successfully");
			Thread.sleep(3000);

			ip.getDataEntry1().click();
			Reporter.log("DataEntry inbox opened successfully ", true);
			ATUReports.add("DataEntry inbox opened successfully ", true);
			Log.info("SaveButton clicked successfully");
			Thread.sleep(2000);

			String clickedText1 = ip.getQueueWorkItem1().getText();
			System.out.println(clickedText1);
			util.waitForPageToLoad();
			Thread.sleep(5000);

			ip.getQueueWorkItem1().click();
			Reporter.log("First work Item clicked successfully", true);
			ATUReports.add("First work Item clicked successfully", true);
			Log.info("SaveButton clicked successfully");
			Thread.sleep(5000);

			// ID_CODE text field
			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys("idCodeStr");
			Reporter.log("Idcode has been written", true);
			Log.info("Idcode has been written");
			util.waitForPageToLoad();
			Thread.sleep(3000);

			/*
			 * wdp.getInvoicenoTB().clear(); wdp.getInvoiceno_TF().sendKeys("invoiceNoStr");
			 * Reporter.log("Invoice Number has been written",true);
			 * Log.info("Invoice Number has been written"); util.waitForPageToLoad();
			 * Thread.sleep(3000);
			 */

			wdp.getUpdate_btn().click();
			Reporter.log("Update Button has been Clicked", true);
			Log.info("Update Button has been Clicked");
			util.waitForPageToLoad();
			Thread.sleep(5000);

			hp.getWorkItemTab().click();
			Reporter.log("WorkItemTab clicked successfully", true);
			ATUReports.add("WorkItemTab clicked successfully", true);
			Log.info("WorkItemTab clicked successfully");
			util.waitForPageToLoad();
			Thread.sleep(3000);

			// To check whether workitem is present in the List
			int num = ip.getworkItemList().size();
			String status = "";

			for (int i = 1; i <= num; i++) {
				String string = Driver.driver
						.findElement(By.xpath("//table[@id='workitemTable']/tbody/tr[" + i + "]/td[2]/span/a/font"))
						.getText();
				if (string.equalsIgnoreCase(clickedText)) {
					status += String.valueOf(true);
				} else {
					status += String.valueOf(false);
				}

			}

			if (status.contains("true")) {
				Reporter.log("selected workitem is retrieved to the workitems list", true);
				ATUReports.add("selected workitem is retrieved to the workitems list", true);
				Log.info("selected workitem is retrieved to the workitems list");
			} else {
				Reporter.log("None of the workitems will be retrieved to the workitems list ", true);
				ATUReports.add("None of the workitems will be retrieved to the workitems list", true);
				Log.info("None of the workitems will be retrieved to the workitems list");
			}

			hp.getInbox().click();
			util.waitForPageToLoad();

			ip.getNextStatusOn().click();
			Reporter.log("getNextStatus button off ", true);
			ATUReports.add("getNextStatus button off  ", true);
			Log.info("getNextStatus button off ");
			Thread.sleep(2000);
		} catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add(
					"Performing 'GetNext' Functionality For Multiple Workitems In Any Queue By Modifing One Workitem In The Queue",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		} finally {
			Log.endTestCase(
					"INFO_8772_PerformingGetNextFunctionalityForMultipleWorkitemsInAnyQueueByModifingOneWorkitemInTheQueue");
		}
	}
}
