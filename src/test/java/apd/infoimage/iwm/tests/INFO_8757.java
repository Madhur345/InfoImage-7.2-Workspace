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
 * @author DashBisw INFO-8757 This class will verify Get Next' functionality for
 *         multiple workitems in My personal queue
 */
public class INFO_8757 extends SuperClassIWM {
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
	 * This method is for performing GetNext functionality for multiple workitems in
	 * My personal queue.
	 * 
	 * @author Biswajit 19/04/2018
	 */
	@SuppressWarnings("deprecation")
	@Test(groups = { "GetNext" })
	public void performingGetNext() {
		Log.startTestCase("INFO_8757_PerformingGetNextFunctionality");
		String workitem = "Biswa" + util.getSysDate(0, "yyDDMMhhmmss");

		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8757  is To verify"
					+ " performing GetNext functionality for multiple workitems in My personal queue.");
			ATUReports.setAuthorInfo("Biswajit", "APR-2018", "0.3");

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("1st workitem Created sucessfully", true);
			ATUReports.add("1st workitem Created sucessfully", true);
			Log.info("1st workitem Created sucessfully");
			Thread.sleep(2000);

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String selectuserfromqueue = prop.getProperty("selectuserfromqueue");

			cwp.sendToAnyOtherUserQueue(workitem, selectuserfromqueue.trim(), "Faxin");
			Reporter.log("workitem suceess Successfully  sent to other user ", true);
			ATUReports.add("workitem suceess Successfully  sent to other user", true);
			Log.info("workitem suceess Successfully  sent to other user");
			Driver.driver.navigate().refresh();

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("2nd workitem Created sucessfully", true);
			ATUReports.add("2nd workitem Created sucessfully", true);
			Log.info("2nd workitem Created sucessfully");
			Thread.sleep(2000);

			cwp.sendToAnyOtherUserQueue(workitem, selectuserfromqueue.trim(), "Faxin");
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
			util.waitForPageToLoad();

			ip.getNextONButton().click();
			ATUReports.add("Verify Get Next option is in On state", "", "Get Next option should be in On state",
					"Get Next option is changed to On state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			/*
			 * if(ip.getAutoViewOn().isSelected()) {
			 * 
			 * } else {
			 */
			ip.getautoViewOn().click();
			Reporter.log("Autoview Button clicked Successfully ", true);
			ATUReports.add("Autoview Button clicked Successfully ", true);
			Log.info("Autoview Button clicked Successfully ");
			Thread.sleep(3000);
			/* } */

			ip.getSaveButton().click();
			Reporter.log("SaveButton clicked Successfully ", true);
			ATUReports.add("SaveButton clicked Successfully ", true);
			Log.info("SaveButton clicked Successfully ");
			Thread.sleep(2000);

			ip.getmyPersonal().click();
			Reporter.log("myPersonal inbox opened Successfully ", true);
			ATUReports.add("myPersonal inbox opened Successfully ", true);
			Log.info("myPersonal inbox opened Successfully  ");
			Thread.sleep(2000);

			String clickedText = ip.getQueueWorkItem1().getText();
			System.out.println(clickedText);
			ip.getQueueWorkItem1().click();
			Reporter.log(" First work Item clicked successfully ", true);
			ATUReports.add("First work Item clicked successfully ", true);
			Log.info("First work Item clicked successfully");
			util.waitForPageToLoad();
			Thread.sleep(5000);

			hp.getWorkItemTab().click();
			Reporter.log("WorkItemTab clicked Successfully", true);
			ATUReports.add("WorkItemTab clicked Successfully.", true);
			Log.info("WorkItemTab clicked Successfully");
			Thread.sleep(2000);

			// To check wheather workitem is present in the List
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
				System.out.println("selected workitem is present in the List");
				ATUReports.add("selected workitem is present in the List", true);
				Log.info("selected workitem is present in the List ");
			} else {
				System.out.println("selected workitem is Not present in the List");
				ATUReports.add("selected workitem is Not present in the List", false);
				Log.info("selected workitem is Not present in the List ");
			}
			hp.getInbox().click();
			util.waitForPageToLoad();

			hp.getInbox().click();
			util.waitForPageToLoad();
			ip.getNextOFFButton().click();
			ATUReports.add("Get Next option is changed to off state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();
		}

		catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add("performing GetNext functionality for multiple workitems in My personal queue", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		}

		finally {
			Log.endTestCase("INFO_8757_PerformingGetNextFunctionality");

		}
	}
}
