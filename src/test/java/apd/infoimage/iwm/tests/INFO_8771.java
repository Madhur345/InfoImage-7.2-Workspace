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
 * @author DashBisw INFO-8771 This Class is Performing GetNext functionality for Multiple Workitems In Any Queue By Opening One Workitem In The Queue.
 *          07/05/2018.
 */

public class INFO_8771 extends SuperClassIWM{

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

	
	
	@Test(groups={"GetNext"})
	public void performingGetNextfunctionalityforMultipleWorkitemsInAnyQueueByOpeningOneWorkitemInTheQueue() {
		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("performingGetNextfunctionalityforMultipleWorkitemsInAnyQueueByOpeningOneWorkitemInTheQueue");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8771  is To verify"
					+ " Performing 'GetNext' Functionality For Workitems In Any Queue By Opening One Workitem In The Queue");
			ATUReports.setAuthorInfo("Biswajit", "MAY-2018", "0.3");
			
			
			
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("1st work item Created sucessfully",true);
			ATUReports.add("1st workitem Created sucessfully",true);
			Log.info("1st workitem Created sucessfully");
			Thread.sleep(2000);
			
			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("1st workitem successfully sent to default queue",true);
			ATUReports.add("1st workitem successfully sent to default queue",true);
			Log.info("1st workitem successfully sent to default queue");
			Thread.sleep(2000);
			util.waitForPageToLoad();

			hp.getWorkItemTab().click();
			Reporter.log("workitems tab clicked Successfully ", true);
			ATUReports.add("workitems tab clicked Successfully  ", true);
			Log.info("workitems tab clicked Successfully ");
			Thread.sleep(2000);
			
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("2nd workitem Created sucessfully",true);
			ATUReports.add("2nd workitem Created sucessfully",true);
			Log.info("2nd workitem Created sucessfully");
			Thread.sleep(2000);
			
			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("2nd workitem successfully sent to default queue",true);
			ATUReports.add("2nd workitem successfully sent to default queue",true);
			Log.info("2nd workitem successfully sent to default queue");
			Thread.sleep(2000);
			util.waitForPageToLoad();
			
			hp.getInbox().click();
			Reporter.log("Inbox tab clicked successfully ", true);
			ATUReports.add("Inbox tab clicked successfully ", true);
			Log.info("Inbox tab clicked successfully");
			Thread.sleep(2000);

			ip.getNextONButton().click();
			ATUReports.add("Verify Get Next option is in On state", "", 
					"Get Next option should be in On state", "Get Next option is changed to On state", true);
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
			Log.info("SaveButton clicked successfully ");
			Thread.sleep(2000);
			
			ip.getDataEntry().click();
			Reporter.log("DataEntry inbox opened successfully ", true);
			ATUReports.add("DataEntry inbox opened successfully ", true);
			Log.info("DataEntry inbox opened successfully");
			Thread.sleep(2000);
			
			String clickedText = ip.getQueueWorkItem1().getText();
			System.out.println(clickedText);
			util.waitForPageToLoad();
			Thread.sleep(5000);
	
			ip.getQueueWorkItem1().click();
			Reporter.log("First work Item clicked successfully", true);
			ATUReports.add("First work Item clicked successfully", true);
			Log.info("First work Item clicked successfully");
			Thread.sleep(5000);
			
			hp.getWorkItemTab().click();
			Reporter.log("WorkItemTab clicked successfully", true);
			ATUReports.add("WorkItemTab clicked successfully", true);
			Log.info("WorkItemTab clicked successfully");
			Thread.sleep(3000);
			
			// To check whether workitem is present in the List
			int num = ip.getworkItemList().size();
			String status = "";

			for (int i = 1; i <= num; i++) {
				String string = Driver.driver
						.findElement(By.xpath("//table[@id='workitemTable']/tbody/tr[" + i + "]/td[2]/span/a/font"))
						.getText();
				if (string.equalsIgnoreCase(clickedText)) {
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
				ATUReports.add("None of the workitems will be retrieved to the workitems list", true);
				Log.info("None of the workitems will be retrieved to the workitems list");
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
			ATUReports.add("Performing 'GetNext' Functionality For Workitems In Any Queue By Opening One Workitem In The Queue", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		}
		finally {
			Log.endTestCase("performingGetNextfunctionalityforMultipleWorkitemsInAnyQueueByOpeningOneWorkitemInTheQueue");
		}
	}
}
