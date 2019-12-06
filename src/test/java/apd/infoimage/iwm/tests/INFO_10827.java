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
 * @author SinghAvn
 * @Zypher Id: INFO-10827 This Scenario will verify upper case in form fields
 *         for document/folder type of workitem in detailed view which is
 *         retieved from queue
 */
public class INFO_10827 extends SuperClassIWM {

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
	public void testSameWorkIteminUserQueue() {
		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_10827_Test_verify_uppercase_form_document_workitem_detailed_retieved");
			ATUReports.setTestCaseReqCoverage(
					"This Scenario INFO-10827 This Scenario will verify upper case in form fields\r\n"
							+ " *         for document/folder type of workitem in detailed view which is\r\n"
							+ " *         retieved from queue");
			ATUReports.setAuthorInfo("Avnish", "August-2018", "1.0");
			// Fetch the test data
			String xlpath = "src\\test\\resources\\testData.xlsx";
			String sheet = "Sheet1";
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);

			cwp.CreateWorkitemClickSubmit(workitem, className, workitemType);
			Reporter.log("Workitem to Workflow is created", true);
			ATUReports.add("Workitem to Workflow is created", true);
			Log.info("Workitem to Workflow is created");

			cwp.searchByNameInWorkitemList(workitem);
			Reporter.log("Searching by Name in List of all workitems", true);
			ATUReports.add("Searching by Name in List of all workitems", true);
			Log.info("Searching by Name in List of all workitems");

			//cwp.getCheckBox().click();
	
			util.wait(time);

			cwp.sendWorkItemToDefaultQueue(workitem);

			util.waitForPageToLoad();
			util.wait(time);

			util.waitForPageToLoad();
			Reporter.log("Retrieving Workitem Using Basic Search", true);
			ATUReports.add("Retrieving Workitem Using Basic Search", true);
			Log.info("Retrieving Workitem Using Basic Search");
			ip.retrieveWorkItem(workitem);

			util.waitForPageToLoad();
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Importing text type file is failed.", true);
			ATUReports.add("Verify Importing text type file is failed.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing text type file is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO_10827_Test_verify_uppercase_form_document_workitem_detailed_retieved");
		}

	}

}
