package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.support.ui.Select;
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
 * @author SumanGaK - 26-Jul-2018 
 * INFO-12424 
 * This class verifies Send Workitem To My Personal Queue using Actions
 */
public class INFO_12424 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1, groups = { "Actions" })
	public void testVerifyActionsSendWorkitemToMyPersonalQueue() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-12424 is for Verifying Send Workitem To My Personal Queue using Actions");
		ATUReports.setAuthorInfo("Suman", "JUL-2018", "0.3");

		try {
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

			Reporter.log("VerifyActionsSendWorkitemToMyPersonalQueueTest", true);
			ATUReports.add("VerifyActionsSendWorkitemToMyPersonalQueueTest", true);
			Log.info("VerifyActionsSendWorkitemToMyPersonalQueueTest");

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

			boolean sendWorkitemWindowBoxPresence = util.verifyObjectPresentReturnsBool(cwp.getSendToWorkflow());
			if(sendWorkitemWindowBoxPresence){
				Reporter.log("Send Workitem window box is present",true);
			}else{
				Reporter.log("Send Workitem window box not present",true);
				Assert.fail("Send Workitem window box not present ");
			}

			cwp.getUserQueueRadioButton().click();
			Reporter.log("User Queue Radio Button is clicked",true);

			Select userNameListBox = new Select(cwp.getUserNameListBox());
			userNameListBox.selectByValue(prop.getProperty("userName"));
			Reporter.log("User Queue Option selected as "+prop.getProperty("userName"),true);
			util.wait(time);

			cwp.getSendButton().click();
			Reporter.log("Send Button is clicked",true);
			util.waitForPageToLoad();
			util.wait(time);	

			hp.getInbox().click();
			Reporter.log("Navigating to inbox page", true);
			ATUReports.add("Navigating to inbox page", true);
			Log.info("Navigating to inbox page");

			ip.getMyPersonalQueue().click();
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
			Assert.fail("Verify Actions Send Workitem To My Personal Queue failed");
			ATUReports.add("Verify Actions Send Workitem To My Personal Queue failed", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("Verify Actions Send Workitem To My Personal Queue failed");
			Log.error(e.getMessage());

		} finally {
			Log.endTestCase("INFO_12424_VerifyActionsSendWorkitemToMyPersonalQueueTest");
			Log.info("INFO_12424_VerifyActionsSendWorkitemToMyPersonalQueueTest");
		}
	}
}