package apd.infoimage.iwm.tests;

import org.apache.log4j.xml.DOMConfigurator;
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
 * @author Singh Avn
 * @Zypher Id: INFO-7084 This Scenario will check sending One WorkItem in User
 *         Queue and will check same work item in User Queue
 */
public class INFO_7084 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1)
	public void sendingOneWorkItemToUserQueue() {
		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_7084_Sending_One_WorkitemtoUserQueue");
			ATUReports.setTestCaseReqCoverage(
					"This Scenario INFO-7084 will check sending One WorkItem in User Queue and will check same work item in User Queue ");
			ATUReports.setAuthorInfo("Avnish", "MAY-2018", "1.0");
			// Fetch the test data
			String xlpath = "src\\test\\resources\\testData.xlsx";
			String sheet = "Sheet1";
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			int time=3000;
			
			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			Log.info("Creation of Workitem");

			cwp.sendWorkItemToUserQueue(workitem, prop.getProperty("userName"));
			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");

			util.wait(time);
			util.jclick(hp.getInbox());
			util.waitForPageToLoad();
			Reporter.log("Navigating to inbox page", true);
			ATUReports.add("Navigating to inbox page", true);
			Log.info("Navigating to inbox page");

			util.jclick(ip.getMyPersonalQueue());
			util.waitForPageToLoad();
			Reporter.log("My Personal Link is clicked", true);
			ATUReports.add("My Personal Link is clicked", true);
			Log.info("My Personal Link is clicked");

			ip.searchByNameInMyPersonalQueue(workitem);
			Reporter.log("Searching By Workitem Name In MyPersonal Queue", true);
			ATUReports.add("Searching By Workitem Name In MyPersonal Queue", true);
			Log.info("Searching By Workitem Name In MyPersonal Queue");

			util.waitForPageToLoad();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Sending One Workitem to User Queue failed");
			ATUReports.add("Sending One Workitem to User Queue Next failed", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("Sending One Workitem to User Queue failed");
			Log.error(e.getMessage());

		} finally {
			Log.endTestCase("INFO_7084_Sending_One_WorkitemtoUserQueue");
			Log.info("INFO_7084_Sending_One_WorkitemtoUserQueue");
		}

	}

}
