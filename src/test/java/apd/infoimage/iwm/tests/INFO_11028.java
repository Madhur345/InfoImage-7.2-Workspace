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
 * @author SinghAvn INFO-11028 Test to verify the icon indicating that the
 *         workitems is in workflow by sending retrived document type of
 *         workitem to next default queue
 */
public class INFO_11028 extends SuperClassIWM {
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
	@Test()
	public void testToVerify() {
		Log.startTestCase("INFO_11028_To_Verify_Icon_Indicating_Workitems_Workflow_For_Document_Type");
		ATUReports.setTestCaseReqCoverage("This Scenario will Test to verify icon indicating that the workitems "
				+ "is in workflow for document type of workitem ");
		ATUReports.setAuthorInfo("SinghAvn", "JUL-2018", "0.3");
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
			int time = 5000;

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
			Reporter.log("Sending Workitem to Workflow", true);
			util.wait(time);
			// Send the workitem to default queue
			cwp.sendWorkItemToDefaultQueue(workitem);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			util.wait(time);
			util.waitForPageToLoad();
			// retrieve the same to workitems list
			ip.retrieveWorkItem(workitem);
			ATUReports.add("Retrieving the Workitem", true);
			Log.info("Retrieving the Workitem");
			// Send the retrieved workitem to next default queue
			cwp.sendWorkItemInvoiceProcessing(workitem);
			ATUReports.add("Sending Workitem to DefaultQueue again", true);
			Log.info("Sending Workitem to DefaultQueue again");
			
			

		} catch (Exception e) {
			Reporter.log(
					" INFO_11028_To_Verify_Icon_Indicating_Workitems_Workflow_For_Document_Type failed",
					true);
			ATUReports.add(
					" INFO_11028_To_Verify_Icon_Indicating_Workitems_Workflow_For_Document_Type test failed",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info(
					" INFO_11028_To_Verify_Icon_Indicating_Workitems_Workflow_For_Document_Type test failed");
			Assert.fail(
					"INFO_11028_To_Verify_Icon_Indicating_Workitems_Workflow_For_Document_Type test failed");
		} finally {
			Log.endTestCase(
					"INFO_11028_To_Verify_Icon_Indicating_Workitems_Workflow_For_Document_Type");
		}
	}
}
