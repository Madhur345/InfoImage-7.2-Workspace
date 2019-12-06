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
 * @author SumanGaK
 * INFO_12186
 * This class Sends Document Workitem To Default Queue and Retrieve Using Basic Search
 */
public class INFO_12186 extends SuperClassIWM {
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
	@Test(enabled = true, priority = 1, groups = { "Search" })
	public void testSendDocumentWorkitemToDefaultQueueRetrieveUsingBasicSearch() {

		ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "Retrieves and reserves workitem");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");
		try {
			Log.startTestCase("INFO_12186_SendDocumentWorkitemToDefaultQueueRetrieveUsingBasicSearchTest");
			Reporter.log("SendDocumentWorkitemToDefaultQueueRetrieveUsingBasicSearchTest : testSendDocumentWorkitemToDefaultQueueRetrieveUsingBasicSearch()", true);
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

			Reporter.log("Class Name : SendDocumentWorkitemToDefaultQueueRetrieveUsingBasicSearchTest", true);
			ATUReports.add("Class Name : SendDocumentWorkitemToDefaultQueueRetrieveUsingBasicSearchTest", true);
			Log.info("Class Name : SendDocumentWorkitemToDefaultQueueRetrieveUsingBasicSearchTest");

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

			Reporter.log("Document: " + workitem + " has been created", true);
			ATUReports.add("Document: " + workitem + " has been created", true);

			util.wait(time);

			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			cwp.sendWorkItemToDefaultQueue(workitem);

			util.waitForPageToLoad();
			util.wait(time);

			util.waitForPageToLoad();
			Reporter.log("Retrieving Workitem Using Basic Search", true);
			ATUReports.add("Retrieving Workitem Using Basic Search", true);
			Log.info("Retrieving Workitem Using Basic Search");
			sp.retrieveWorkItemFromSearch(workitem);

			util.waitForPageToLoad();			

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute SendDocumentWorkitemToDefaultQueueRetrieveUsingBasicSearch test", true);
			ATUReports.add("failed to execute SendDocumentWorkitemToDefaultQueueRetrieveUsingBasicSearch test", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute SendDocumentWorkitemToDefaultQueueRetrieveUsingBasicSearch test");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO_12186_SendDocumentWorkitemToDefaultQueueRetrieveUsingBasicSearchTest");
		}
	}	

}

