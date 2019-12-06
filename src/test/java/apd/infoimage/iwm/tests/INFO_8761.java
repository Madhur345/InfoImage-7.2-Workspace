package apd.infoimage.iwm.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
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
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author DashBisw INFO-8761 This Class is Performing Auto View Functionality
 *         For Multiple Workitems from Any Queue With "GetNext" Mode Switched
 *         Off. 25/05/2018
 */
public class INFO_8761 extends SuperClassIWM {
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

	@Test
	public void performingAutoViewFunctionalityForMultipleWorkitemsfromAnyQueueWithGetNextModeSwitchedOff() {
		Log.startTestCase(
				"INFO_8761_PerformingAutoViewFunctionalityForMultipleWorkitemsfromAnyQueueWithGetNextModeSwitchedOff");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8761  is To verify"
					+ " Performing AutoView Functionality For Multiple Workitems from Any Queue With GetNext Mode Switched Off");
			ATUReports.setAuthorInfo("Biswajit", "MAY-2018", "0.3");

			// Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str1 = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str1 + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String viewstr = ExcelLib.getCellValue(xlpath, sheet, 45, 1);
			String viewName = viewstr + util.getSysDate(0, date);
			String columnName = ExcelLib.getCellValue(xlpath, sheet, 46, 1);
			String columnName1 = ExcelLib.getCellValue(xlpath, sheet, 48, 1);
			String columnName2 = ExcelLib.getCellValue(xlpath, sheet, 49, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("1st work item Created sucessfully", true);
			ATUReports.add("1st workitem Created sucessfully", true);
			Thread.sleep(2000);

			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("1st workitem successfully sent to default queue", true);
			ATUReports.add("1st workitem successfully sent to default queue", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			hp.getWorkItemTab().click();
			Reporter.log("workitems tab clicked Successfully", true);
			ATUReports.add("workitems tab clicked Successfully", true);
			Thread.sleep(2000);

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("2nd workitem Created sucessfully", true);
			ATUReports.add("2nd workitem Created sucessfully", true);
			Thread.sleep(2000);

			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("2nd workitem successfully sent to default queue", true);
			ATUReports.add("2nd workitem successfully sent to default queue", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			hp.getInbox().click();
			Reporter.log("Inbox tab clicked successfully", true);
			ATUReports.add("Inbox tab clicked successfully", true);
			Thread.sleep(2000);

			ip.getNextONButton().click();
			ATUReports.add("Verify Get Next option is in On state", "", "Get Next option should be in On state",
					"Get Next option is changed to On state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getautoViewOn().click();
			Reporter.log("Autoview button clicked successfully", true);
			ATUReports.add("Autoview button clicked successfully", true);
			Thread.sleep(3000);

			ip.getrequiredNoOfWorkitems().clear();
			Thread.sleep(3000);
			ip.getrequiredNoOfWorkitems().sendKeys("9");
			Thread.sleep(3000);
			Reporter.log("maxitems successfully set ", true);
			ATUReports.add("maxitems successfully set", true);
			Thread.sleep(3000);

			ip.getSaveButton().click();
			Reporter.log("SaveButton clicked successfully ", true);
			ATUReports.add("SaveButton clicked successfully ", true);
			Thread.sleep(5000);

			ip.getDataEntry().click();
			Reporter.log("DataEntry inbox opened successfully ", true);
			ATUReports.add("DataEntry inbox opened successfully ", true);
			Thread.sleep(2000);

			// Check how many QueueWorkItem is present in list
			int count = ip.getQueueWorkItemCount().size();
			System.out.println("Count - " + count);

			ArrayList<String> array = new ArrayList<>();

			for (int i = 1; i <= count; i++) {
				String str = Driver.driver
						.findElement(By.xpath("//tbody[@id='itemContainer']/tr[" + i + "]/td[2]/span/a/font"))
						.getText();
				array.add(str);
			}

			ip.getDataEntryFirstWorkItem().click();
			Reporter.log("First work Item clicked successfully", true);
			ATUReports.add("First work Item clicked successfully", true);
			Thread.sleep(5000);

			try {
				while (wdp.getCloseWorkitem().isDisplayed()) {

					Thread.sleep(2000);
					util.waitForPageToLoad();
					wdp.getActionsDropDown().click();
					Reporter.log("Actions Drop Down is clicked", true);
					ATUReports.add("Actions Drop Down is clicked", true);
					Thread.sleep(2000);
					util.waitForPageToLoad();

					wdp.getSendToDefaultOption().click();
					Reporter.log("Send To Default Option is selected", true);
					ATUReports.add("Send To Default Option is selected", true);
					Thread.sleep(2000);
					util.waitForPageToLoad();

					continue;

				}

			} catch (Exception e) {
				System.out.println("No workitems to display in Data Entry");
				ATUReports.add("No workitems to display in Data Entry", true);
			}

			hp.getInbox().click();
			Reporter.log("Inbox tab clicked successfully ", true);
			ATUReports.add("Inbox tab clicked successfully ", true);
			Thread.sleep(2000);

			ip.getInvoiceProcessing().click();
			Reporter.log("Invoice Processing inbox clicked successfully ", true);
			ATUReports.add("Invoice Processing inbox clicked successfully ", true);
			util.waitForPageToLoad();

			// Check how many QueueWorkItem is present in list
			int count1 = ip.getQueueWorkItemCount().size();
			System.out.println("Count - " + count);

			ArrayList<String> array1 = new ArrayList<>();

			for (int i = 1; i <= count1; i++) {
				String str = Driver.driver
						.findElement(By.xpath("//tbody[@id='itemContainer']/tr[" + i + "]/td[2]/span/a/font"))
						.getText();
				array.add(str);
			}

			ip.getDataEntryFirstWorkItem().click();
			Reporter.log("First work Item clicked successfully in Invoice processing", true);
			ATUReports.add("First work Item clicked successfully in Invoice processing.", true);
			Thread.sleep(5000);

			try {
				while (wdp.getCloseWorkitem().isDisplayed()) {
					Thread.sleep(2000);
					util.waitForPageToLoad();
					wdp.getActionsDropDown().click();
					Reporter.log("Actions Drop Down is clicked", true);
					ATUReports.add("Actions Drop Down is clicked", true);
					Thread.sleep(2000);
					util.waitForPageToLoad();

					wdp.getSendToDefaultOption().click();
					Reporter.log("Send To Default Option is selected", true);
					ATUReports.add("Send To Default Option is selected", true);
					Thread.sleep(2000);
					util.waitForPageToLoad();
					continue;

				}
			}

			catch (Exception e) {
				System.out.println("No workitems to display in Invoice processing");
				ATUReports.add("No workitems to display in Invoice processing", true);
			}

			hp.getInbox().click();
			util.waitForPageToLoad();
			ip.getNextOFFButton().click();
			ATUReports.add("Get Next option is changed to off state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

		}

		catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add(
					"Performing AutoView Functionality For Multiple Workitems from Any Queue With GetNext Mode Switched Off",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		}

		finally {
			Log.endTestCase(
					"INFO_8761_PerformingAutoViewFunctionalityForMultipleWorkitemsfromAnyQueueWithGetNextModeSwitchedOff");

		}
	}

}
