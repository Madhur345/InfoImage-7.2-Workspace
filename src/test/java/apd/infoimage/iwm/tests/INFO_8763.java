package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

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
 * @author DashBisw INFO-8763 This Class is Performing GetNext Functionality For
 *         MultipleWorkItems In Any Queue Without Modifing The Workitem And
 *         Navigating To Workitems List. 25/04/2018
 */
public class INFO_8763 extends SuperClassIWM {

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

	@Test(groups = { "GetNext" })
	public void performingGetNextFunctionalityForMultipleWorkItemsInAnyQueueWithoutModifingTheWorkitemAndNavigatingToWorkitemsList() {
		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase(
				"INFO_8763_PerformingGetNextFunctionalityForMultipleWorkItemsInAnyQueueWithoutModifingTheWorkitemAndNavigatingToWorkitemsList");
		try {

			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8763  is To verify"
					+ " Performing GetNext Functionality For MultipleWorkItems In Any Queue Without Modifing The Workitem And Navigating To Workitems List");
			ATUReports.setAuthorInfo("Biswajit", "APR-2018", "0.3");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem1 = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);

			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);
			String invoiceNoStr = ExcelLib.getCellValue(xlpath, sheet, 10, 1);
			// String duplicateworkitem = workitem + "new";

			Reporter.log("Workitem : " + workitem1, true);
			ATUReports.add("Workitem : " + workitem1, true);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			Log.info("Creation of Workitem");
			cwp.CreateWorkitem(workitem1, className, workitemType);

			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			Thread.sleep(2000);
			cwp.sendWorkItemToDefaultQueue(workitem1);

			util.waitForPageToLoad();

			hp.getWorkItemTab().click();
			Reporter.log("workitems tab clicked Successfully ", true);
			ATUReports.add("workitems tab clicked Successfully  ", true);
			Log.info("workitems tab clicked Successfully ");
			Thread.sleep(2000);

			cwp.CreateWorkitem(workitem1, "archive", "Document");
			Reporter.log("2nd workitem Created sucessfully", true);
			ATUReports.add("2nd workitem Created sucessfully", true);
			Log.info("2nd workitem Created sucessfully");
			Thread.sleep(2000);

			cwp.sendWorkItemToDefaultQueue(workitem1);
			Reporter.log("2nd workitem successfully sent to default queue", true);
			ATUReports.add("2nd workitem successfully sent to default queue", true);
			Log.info("2nd workitem successfully sent to default queue");
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

			ip.getautoViewOn().click();
			Reporter.log("Autoview button clicked successfully", true);
			ATUReports.add("Autoview button clicked successfully", true);
			Log.info("getNextStatuson button clicked successfully");
			Thread.sleep(2000);

			ip.getSaveButton().click();
			Reporter.log("SaveButton clicked successfully", true);
			ATUReports.add("SaveButton clicked successfully", true);
			Log.info("SaveButton clicked successfully");
			Thread.sleep(2000);

			ip.getDataEntry().click();
			Reporter.log("DataEntry inbox opened successfully", true);
			ATUReports.add("DataEntry inbox opened successfully", true);
			Log.info("DataEntry inbox opened successfully");
			util.waitForPageToLoad();

			// Check how many QueueWorkItem is present in list
			int count = ip.getDataEntryworkItemList().size();
			System.out.println("Count - " + count);
			ATUReports.add("Count - " + count, true);

			ArrayList<String> array = new ArrayList<>();

			for (int i = 1; i <= count; i++) {
				String str1 = Driver.driver
						.findElement(By.xpath("//tbody[@id='itemContainer']/tr[" + i + "]/td[2]/span/a/font"))
						.getText();
				array.add(str1);
			}

			String workvalue = ip.getDataEntryFirstWorkItem().getText();
			ip.getDataEntryFirstWorkItem().click();
			Reporter.log("DataEntry First WorkItem clicked successfully", true);
			ATUReports.add("DataEntry First WorkItem clicked successfully", true);
			Log.info("DataEntry First WorkItem clicked successfully");
			Thread.sleep(5000);

			int item = 0;
			while (item < count) {
				if (item < 1) {
					ip.getActions().click();
					Reporter.log("Actions dropdown field successfully opened ", true);
					ATUReports.add("Actions dropdown field successfully opened", true);
					Log.info("Actions dropdown field successfully opened");
					Thread.sleep(2000);

					ip.getSendToDefault().click();
					Reporter.log("send the workitem to default", true);
					ATUReports.add("send the workitem to default", true);
					Log.info("send the workitem to default");
					Thread.sleep(2000);
				}

				if (item >= 1) {
					String str1 = ip.getWorkItemTextValue().getText();
					System.out.println(" Array value  - " + array.get(item).toString());

					if (str1.equalsIgnoreCase(array.get(item).toString())) {
						Reporter.log("Next workitem in the queue is opened", true);
						ATUReports.add("Next workitem in the queue is opened", true);
						Log.info("Next workitem in the queue is opened");
					} else {
						Reporter.log("Next workitem in the queue is not opened", false);
						ATUReports.add("Next workitem in the queue is not opened", true);
						Log.info("Next workitem in the queue is not opened");
					}
				}
				Thread.sleep(2000);
				item++;
			}

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

				if (workvalue.equals(str1)) {
					status += String.valueOf(true);
				} else {
					status += String.valueOf(false);
				}

			}

			if (status.contains("true")) {
				Reporter.log("workitem is retrieved to the workitems list", true);
				ATUReports.add("workitem is retrieved to the workitems list", true);
				Log.info("workitem is retrieved to the workitems list");

			} else {
				Reporter.log("workitem is not retrieved to the workitems list", true);
				ATUReports.add("workitem is not retrieved to the workitems list", true);
				Log.info("workitem is not retrieved to the workitems list");

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
					"Failed to Performing GetNext Functionality For MultipleWorkItems In Any Queue Without Modifing The Workitem And Navigating To Workitems List",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Script failed Assert.");

		}

		finally {
			Log.endTestCase(
					"INFO_8763_PerformingGetNextFunctionalityForMultipleWorkItemsInAnyQueueWithoutModifingTheWorkitemAndNavigatingToWorkitemsList");

		}
	}
}