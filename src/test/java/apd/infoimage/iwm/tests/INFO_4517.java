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
 * @author SumanGaK - 04-Jan-2018 
 * INFO-4517 
 * This class will verify the Workflow variables are displayed in the workitems in the queue
 */
public class INFO_4517 extends SuperClassIWM {

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
	@Test(enabled = true, groups = { "Inbox" })
	public void testVerifyWorkflowVariablesDisplayedInQueue() {

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-4517  is To verify" + "Verify Workflow Variables Displayed In Queue");
		ATUReports.setAuthorInfo("SumanGaK", "MAY-2018", "0.3");

		try {
			Log.startTestCase("INFO_4517_VerifyWorkflowVariablesDisplayedInQueueTest");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String viewstr = ExcelLib.getCellValue(xlpath,sheet,77,1);	
			String viewName = viewstr + util.getSysDate(0, date);	
			String string = ExcelLib.getCellValue(xlpath,sheet,78,1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("VerifyWorkflowVariablesDisplayedInQueueTest : testVerifyWorkflowVariablesDisplayedInQueue()",	true);
			ATUReports.add("VerifyWorkflowVariablesDisplayedInQueueTest : testVerifyWorkflowVariablesDisplayedInQueue()",	true);
			Log.info("VerifyWorkflowVariablesDisplayedInQueueTest : testVerifyWorkflowVariablesDisplayedInQueue()");

			util.waitForPageToLoad();
			util.wait(time);
			hp.clickUserPreferenceTab();
			Reporter.log("User Preferences Tab is clicked.", true);
			ATUReports.add("User Preferences Tab is clicked.", true);
			Log.info("User Preferences Tab is clicked.");

			util.waitForPageToLoad();
			util.wait(time);

			upp.getInboxRadioBtn().click();
			Reporter.log("Inbox Radio Button is clicked.", true);
			ATUReports.add("Add new Media file window is NOT displayed", true);
			Log.info("Add new Media file window is NOT displayed");
			util.wait(time);

			upp.getViewNameTextbox().sendKeys(viewName);
			Reporter.log("View Name is passed to text box.", true);
			ATUReports.add("View Name is passed to text box.", true);
			Log.info("View Name is passed to text box.");

			util.wait(time);
			upp.selectFieldFromAvailable(string);
			Reporter.log("Workflow variables column name in Available Fields is moved to Selected", true);
			ATUReports.add("Workflow variables column name in Available Fields is moved to Selected", true);
			Log.info("Workflow variables column name in Available Fields is moved to Selected");
			util.wait(time);
			upp.getCreateViewBtn().click();
			Reporter.log("Create View Button is clicked.", true);
			ATUReports.add("Create View Button is clicked.", true);
			Log.info("Create View Button is clicked.");

			util.waitForPageToLoad();
			util.wait(time);

			upp.getCreatedViewNameRadioInbox(viewName).click();
			Reporter.log("Created View Name Radio Button is clicked.", true);
			ATUReports.add("Created View Name Radio Button is clicked.", true);
			Log.info("Created View Name Radio Button is clicked.");

			upp.getApplyViewBtn().click();
			Reporter.log("Apply View Button is clicked.", true);
			ATUReports.add("Apply View Button is clicked.", true);
			Log.info("Apply View Button is clicked.");

			hp.getInbox().click();
			Reporter.log("Inbox is clicked.", true);
			ATUReports.add("Inbox is clicked.", true);
			Log.info("Inbox is clicked.");

			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked.", true);
			ATUReports.add("Data Entry is clicked.", true);
			Log.info("Data Entry is clicked.");

			util.wait(time);
			boolean queryFieldPresence = util.verifyObjectPresentReturnsBool(ip.getQueryFieldAsINTVAR1Column());
			if (queryFieldPresence) {

				Reporter.log("Query field INTVAR1 is displayed as column in search result", true);
				ATUReports.add("Query field INTVAR1 is displayed as column in search result", true);
				Log.info("Query field INTVAR1 is displayed as column in search result");
			} else {
				Reporter.log("Query field INTVAR1 is not displayed as column in search result", true);
				ATUReports.add("Query field INTVAR1 is not displayed as column in search result", true);
				Log.info("Query field INTVAR1 is not displayed as column in search result");
				Assert.fail("Query field INTVAR1 is not displayed as column in search result");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify Workflow Variables Displayed In Queue test failed.", true);
			ATUReports.add("Verify Workflow Variables Displayed In Queue", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Workflow Variables Displayed In Queue test failed.");
		} 
		finally {
			Log.endTestCase("INFO_4517_VerifyWorkflowVariablesDisplayedInQueueTest");
		}
	}
}
