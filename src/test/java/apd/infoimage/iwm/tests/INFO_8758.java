package apd.infoimage.iwm.tests;

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
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author DashBisw INFO-8758 This Class is Performing 'Auto view' functionality
 *         for multiple workitems in My personal queue . 19/04/2018
 */

public class INFO_8758 extends SuperClassIWM {
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
	public void performingAutoviewMypersonalqueue() {
		Log.startTestCase("INFO_8758_PerformingAutoviewfunctionality");

		String workitem = "Biswa" + util.getSysDate(0, "yyDDMMhhmmss");

		try {

			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8758  is To verify"
					+ " Performing 'Auto view' functionality for multiple workitems in My personal queue ");
			ATUReports.setAuthorInfo("Biswajit", "APR-2018", "0.3");

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("1st workitem Created sucessfully", true);
			ATUReports.add("1st workitem Created sucessfully", true);
			Log.info("1st workitem Created sucessfully");
			Thread.sleep(2000);

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String selectuserfromqueue = prop.getProperty("selectuserfromqueue");

			cwp.sendWorkItemToUserQueue(workitem, selectuserfromqueue);
			Reporter.log("workitem suceess Successfully  sent to other user ", true);
			ATUReports.add("workitem suceess Successfully  sent to other user", true);
			Log.info("workitem suceess Successfully  sent to other user");
			Driver.driver.navigate().refresh();

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("2nd workitem Created sucessfully", true);
			ATUReports.add("2nd workitem Created sucessfully", true);
			Log.info("2nd workitem Created sucessfully");
			Thread.sleep(3000);

			cwp.sendWorkItemToUserQueue(workitem, selectuserfromqueue);
			Reporter.log("workitem suceess Successfully  sent to other user ", true);
			ATUReports.add("workitem suceess Successfully  sent to other user", true);
			Log.info("workitem suceess Successfully  sent to other user");
			Driver.driver.navigate().refresh();
			util.waitForPageToLoad();
			hp.logoutApp();
			Thread.sleep(2000);

			lp.loginToApp("userName1", "password1", "domain", "role");
			Reporter.log("Another user logged in Successfully ", true);
			ATUReports.add("Another user logged in Successfully ", true);
			Log.info("Another user logged in Successfully ");
			util.waitForPageToLoad();

			hp.getInbox().click();
			Reporter.log("Inbox tab clicked Successfully ", true);
			ATUReports.add("Inbox tab clicked Successfully ", true);
			Log.info("Inbox tab clicked Successfully  ");

			ip.getNextStatusOn().click();
			Reporter.log("getNextStatuson button clicked Successfully ", true);
			ATUReports.add("getNextStatuson button clicked Successfully ", true);
			Log.info("getNextStatuson button clicked Successfully");
			Thread.sleep(2000);

			ip.getautoViewOn().click();
			Reporter.log("Autoview Button clicked Successfully ", true);
			ATUReports.add("Autoview Button clicked Successfully ", true);
			Log.info("Autoview Button clicked Successfully ");
			Thread.sleep(3000);

			ip.getSaveButton().click();
			Reporter.log("SaveButton clicked Successfully ", true);
			ATUReports.add("SaveButton clicked Successfully ", true);
			Log.info("SaveButton clicked Successfully ");
			Thread.sleep(2000);

			ip.getmyPersonal().click();
			Reporter.log("myPersonal inbox opened successfully", true);
			ATUReports.add("myPersonal inbox opened successfully", true);
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

			ip.getQueueWorkItem1().click();
			Reporter.log("First work Item clicked successfully", true);
			ATUReports.add("First work Item clicked successfully", true);
			Thread.sleep(5000);

			int item = 0;
			while (item < count) {
				if (item < 1) {
					ip.getWorkItemCloseBt().click();
					Reporter.log("First work Item Closeed successfully", true);
					ATUReports.add("First work Item Closeed successfully", true);
				}

				if (item >= 1) {
					String str = ip.getWorkItemTextValue().getText();
					System.out.println(" Array value  - " + array.get(item).toString());

					if (str.equalsIgnoreCase(array.get(item).toString())) {
						Reporter.log("As per the queue list Next work item comes to this place ", true);
						ATUReports.add("As per the queue list Next work item comes to this place ", true);
					} else {
						Reporter.log("As per the queue list Next work item didn't come to this place", false);
						ATUReports.add("As per the queue list Next work item didn't come to this place", false);
					}
				}
				Thread.sleep(2000);
				item++;
			}

			hp.getInbox().click();
			util.waitForPageToLoad();
			ip.getNextOFFButton().click();
			Reporter.log("get Next Button is clicked", true);
			ATUReports.add("get Next Button is clicked", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();
		}

		catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add("Failed to Performing 'Auto view' functionality for multiple workitems in My personal queue",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Script failed Assert.");

		} finally {
			Log.endTestCase("INFO_8758_PerformingAutoviewfunctionality");
		}

	}

}
