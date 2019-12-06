package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class INFO_10664  extends SuperClassIWM {
	@BeforeMethod
	public void setUp() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown() {
		hp.logoutApp();
	}

	@SuppressWarnings("deprecation")
	@Test(enabled = true, groups = { "Workitem" })
	public void VerifyReturntoQueuFunctionalityForFolderTypeOfWorkitem() {
		Log.startTestCase("INFO_10664_VerifyReturntoQueuFunctionalityForWorkitemOfFolderType");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-10674 is for verify 'Return to Queue' functionality  for folder type of workitem");
		ATUReports.setAuthorInfo("GuptaPr2", "Aug-2018", "0.3");
		try {
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String folderWorkitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("VerifyReturntoQueuFunctionalityForWorkitemOfFolderTypeTest", true);
			ATUReports.add("VerifyReturntoQueuFunctionalityForWorkitemOfFolderTypeTest", true);
			Log.info("VerifyReturntoQueuFunctionalityForWorkitemOfFolderTypeTest");

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Log.info("Workitem : " + workitem);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Log.info("Class Name : " + className);
			Reporter.log("Workitem Type : " + folderWorkitemType, true);
			ATUReports.add("Workitem Type : " + folderWorkitemType, true);
			Log.info("Workitem Type : " + folderWorkitemType);

			cwp.CreateWorkitem(workitem, className, folderWorkitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");
			util.wait(time);
			util.waitForPageToLoad();

			hp.getFirstRowFirstCell().click();
			Reporter.log("Action Button of Workitem is clicked", true);
			ATUReports.add("Action Button of Workitem is clicked", true);
			Log.info("Action Button of Workitem is clicked");

			hp.getSendThisWorkitemOption().click();
			Reporter.log("Send This Workitem Option is clicked", true);
			ATUReports.add("Send This Workitem Option is clicked", true);
			Log.info("Send This Workitem Option is clicked");
			util.wait(time);
			cwp.getSendButton().click();
			Reporter.log("Send Button is clicked", true);
			util.waitForPageToLoad();
			util.wait(time);
			hp.getInbox().click();
			Reporter.log("Inbox tab is clicked", true);
			ATUReports.add("Inbox tab is clicked", true);
			util.wait(time);
			util.wait(time);
			if (util.verifyObjectEnabledReturnsBool(ip.getDataEntry())) {
				ip.getDataEntry().click();
				Reporter.log("Data Entry is clicked", true);
				ATUReports.add("Data Entry is clicked", true);
			} else {
				ip.getCustom().click();
				Reporter.log("Custom is clicked", true);
			}

			util.wait(time);
			ip.searchByNameInDataEntry(workitem);
			Reporter.log("Searching Workitem By Name In Data Entry", true);
			Reporter.log("Retrieving Workitem", true);
			ATUReports.add("Retrieving Workitem", true);
			Log.info("Retrieving Workitem");
			ip.retrieveWorkItem(workitem);

			cwp.searchByNameInWorkitemList(workitem);
			hp.getFirstRowFirstCell().click();
			Reporter.log("Action Button of Workitem is clicked", true);
			ATUReports.add("Action Button of Workitem is clicked", true);
			Log.info("Action Button of Workitem is clicked");

			util.wait(time);

            boolean returnToQueueOptionEnabled = hp.getreturnToQueueOption().isEnabled();
			
			if(returnToQueueOptionEnabled) {
				Reporter.log("return To Queue Option is enabled", true);
				ATUReports.add("return To Queue Option is enabled", true);
				Log.info("return To Queue Option is enabled");
			} else {
				Reporter.log("return To Queue Option is not enabled", true);
				ATUReports.add("return To Queue Option is not enabled", true);
				Log.info("return To Queue Option is not enabled");
				Assert.fail("return To Queue Option is not enabled");
			}

		} catch (Exception e) {
			Reporter.log("verify 'Return to Queue' functionality for  folder type of workitem type test failed", true);
			ATUReports.add(" verify 'Return to Queue' functionality for  folder type of workitem test failed",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info(" verify 'Return to Queue' functionality for folder type of workitem type test failed");
			Assert.fail(" verify 'Return to Queue' functionality for folder type of workitem type test failed");
		}

		finally {
			Log.endTestCase("INFO_10664_VerifyReturntoQueuFunctionalityForWorkitemOfFolderType");
		}
	}

}
