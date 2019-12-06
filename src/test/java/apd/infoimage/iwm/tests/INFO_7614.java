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

/**
 * @author SumanGaK - 30-Mar-2018 
 * INFO_7614 
 * This class will send folder type of workitem from one user and checking the Workitem
 */
public class INFO_7614 extends SuperClassIWM {
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
	@Test(enabled = true, priority = 1, groups = { "UploadFile" })
	public void testRedXFolderSecondUserRetrievingFromDefaultQueue() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7614 is for Verifying To send folder type of workitem from one user and checking the Workitem in IWM");
		ATUReports.setAuthorInfo("Suman", "APR-2018", "0.3");

		try {
			Log.startTestCase("INFO_7614_RedXFolderSecondUserRetrievingFromDefaultQueueTest");

			Reporter.log("RedXFolderSecondUserRetrievingFromDefaultQueueTest", true);
			ATUReports.add("RedXFolderSecondUserRetrievingFromDefaultQueueTest", true);
			Log.info("RedXFolderSecondUserRetrievingFromDefaultQueueTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);        

			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			Log.info("Creation of Workitem");

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Log.info("Workitem : " + workitem);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Log.info("Class Name : " + className);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Log.info("Workitem Type : " + workitemType);

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");
			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			util.wait(time);

			cwp.sendWorkItemToDefaultQueue(workitem);

			util.waitForPageToLoad();
			util.wait(time);
			hp.logoutApp();

			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Logging into another user", true);
			ATUReports.add("Logging into another user", true);
			Log.info("Logging into another user");
			lp.loginToApp("userName1", "password1", "domain1", "role1");

			util.wait(time);
			hp.getInbox().click();
			Reporter.log("Retrieving Workitem From Inbox", true);
			ATUReports.add("Retrieving Workitem From Inbox", true);
			Log.info("Retrieving Workitem From Inbox");
			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked", true);
			ATUReports.add("Data Entry is clicked", true);
			Log.info("Data Entry is clicked");
			util.wait(time);
			util.waitForPageToLoad();
			util.wait(time);
			ip.retrieveWorkItem(workitem);
			
			util.wait(time);
			util.wait(time);
			util.waitForPageToLoad();
			util.wait(time);
			cwp.getRedXFolderClass(workitem);

			util.waitForPageToLoad();
			util.wait(time);

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add("failed to execute test", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Reporter.log("failed to execute RedXFolderSecondUserRetrievingFromDefaultQueue test", true);
			Log.info("failed to execute RedXFolderSecondUserRetrievingFromDefaultQueue test");
			ATUReports.add("failed to execute RedXFolderSecondUserRetrievingFromDefaultQueue test", true);
			Assert.fail("failed to execute RedXFolderSecondUserRetrievingFromDefaultQueue test");
		} finally {
			Log.endTestCase("INFO_7614_RedXFolderSecondUserRetrievingFromDefaultQueueTest");
		}

	}

}