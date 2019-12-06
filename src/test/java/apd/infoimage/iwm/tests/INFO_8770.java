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

public class INFO_8770 extends SuperClassIWM{

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
	@Test(groups={"GetNext"})
	public void performingGetNextFunctionalityForWorkitemsInAnyQueueWithMaxItemsAsEmpty() {
		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		DOMConfigurator.configure("log4j.xml");
		Log.startTestCase("INFO_8770_PerformingGetNextFunctionalityForWorkitemsInAnyQueueWithMaxItemsAsEmpty");
		

		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8769  is To verify"
					+ " Performing 'GetNext' Functionality For Workitems In Any Queue With Max Items Set To Zero");
			ATUReports.setAuthorInfo("Biswajit", "APR-2018", "0.3");
			
			
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("First Workitem created successfully", true);
			ATUReports.add("First Workitem created successfully", true);
			Thread.sleep(2000);
			
			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("1st workitem successfully sent to default queue",true);
			ATUReports.add("1st workitem successfully sent to default queue",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			hp.getWorkItemTab().click();
			Reporter.log("workitems tab clicked Successfully ", true);
			ATUReports.add("workitems tab clicked Successfully  ", true);
			Thread.sleep(2000);
			
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("Second Workitem created successfully", true);
			ATUReports.add("Second Workitem created successfully", true);
			Thread.sleep(2000);
			
			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("Second workitem successfully sent to default queue",true);
			ATUReports.add("Second workitem successfully sent to default queue",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();
			
			hp.getInbox().click();
			Reporter.log("Inbox tab clicked successfully ", true);
			ATUReports.add("Inbox tab clicked successfully ", true);
			Thread.sleep(2000);

			ip.getNextONButton().click();
			ATUReports.add("Verify Get Next option is in On state", "", 
					"Get Next option should be in On state", "Get Next option is changed to On state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getrequiredNoOfWorkitems().clear();
			Reporter.log("maxitems textbox is successfully set empty", true);
			ATUReports.add("maxitems textbox is successfully set empty", true);
			
			ip.getSaveButton().click();
			Reporter.log("SaveButton clicked successfully ", true);
			ATUReports.add("SaveButton clicked successfully ", true);
			Thread.sleep(2000);
			
			ip.getDataEntry().click();
			Reporter.log("DataEntry inbox opened successfully ", true);
			ATUReports.add("DataEntry inbox opened successfully ", true);
			Thread.sleep(2000);
	
			int num1 = ip.getworkItemList().size();
			System.out.println("num - " + num1);
			Reporter.log("3 workitems is displaying", true);
			ATUReports.add("3 workitems is displaying ", true);
			
			hp.getWorkItemTab().click();
			Reporter.log("WorkItemTab clicked successfully", true);
			ATUReports.add("WorkItemTab clicked successfully", true);
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

			} else {
				Reporter.log("Created workitem is not retrieved to the workitems list", true);
				ATUReports.add("Created workitem is not retrieved to the workitems list", true);

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
			ATUReports.add("Failed to Performing 'GetNext' Functionality For Workitems In Any Queue With Max Items Set To Zero", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		}
		finally {
			Log.endTestCase("INFO_8770_PerformingGetNextFunctionalityForWorkitemsInAnyQueueWithMaxItemsAsEmpty");
		}
	}
}
