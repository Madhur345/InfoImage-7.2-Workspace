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
 * INFO_5111
 * This class Retrieves and reserves workitem
 */
public class INFO_5111 extends SuperClassIWM {
	@BeforeMethod
	public void beforMethod() {
		Driver.driver.get(prop.getProperty("appUrl"));
//		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
//		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod() {
		hp.logoutApp();
	}

	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 1, groups = { "Workitem" })
	public void testRetrieveAndReserve() {

		ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "Retrieves and reserves workitem");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try {
			Log.startTestCase("Retrieves and reserves workitem");

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

			Reporter.log("RetrieveAndReserveTest : testRetrieveAndReserve()", true);
			ATUReports.add("RetrieveAndReserveTest : testRetrieveAndReserve()", true);
			Log.info("RetrieveAndReserveTest : testRetrieveAndReserve()");

			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);

			cwp.CreateWorkitem(workitem,className,workitemType);

			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");

			Reporter.log("Document: "+workitem+" has been created",true);
			ATUReports.add("Document: "+workitem+" has been created",true);
			Log.info("Document: "+workitem+" has been created");

			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");

			cwp.sendWorkItemToDefaultQueue(workitem);

//			util.waitForPageToLoad();
			Reporter.log("Retrieving Workitem", true);
			ATUReports.add("Retrieving Workitem", true);
			Log.info("Retrieving Workitem");

			ip.retrieveWorkItem(workitem);

			util.wait(time);
			Reporter.log("Reserving Workitem", true);
			ATUReports.add("Reserving Workitem", true);
			Log.info("Reserving Workitem");

			cwp.reserveWorkItem(workitem);

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
			Reporter.log("Retrieving Workitem From Search", true);
			ATUReports.add("Retrieving Workitem From Search", true);
			Log.info("Retrieving Workitem From Search");

			sp.retrieveWorkItemFromSearch(workitem);

			util.waitForPageToLoad();
			Reporter.log("Checking Reserve for Workitem By another user", true);
			ATUReports.add("Checking Reserve for Workitem By another user", true);
			Log.info("Checking Reserve for Workitem By another user");

			cwp.reserveVerifyWorkItem(workitem);

			util.waitForPageToLoad();
			util.wait(time);

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute RetrieveAndReserve test", true);
			ATUReports.add("execute RetrieveAndReserve test", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute RetrieveAndReserve test");
		} finally {

			Log.endTestCase("Retrieves and reserves workitem");
		}
	}

	/**
	 * This test method Retrieves , reserves workitem and send into Workflow
	 * 
	 * @author SumanGaK
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = false, priority = 2, groups = { "Workitem" })
	public void testRetrieveReserveAndSend() {		

		ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "test Retrieve Reserve And Send");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try {
			Log.startTestCase("test Retrieve Reserve And Send");
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
			String firstQueue = ExcelLib.getCellValue(xlpath, sheet, 53, 1);

			Reporter.log("RetrieveAndReserveTest : testRetrieveReserveAndSend()", true);
			ATUReports.add("RetrieveAndReserveTest : testRetrieveReserveAndSend()", true);
			Log.info("RetrieveAndReserveTest : testRetrieveReserveAndSend()");

			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);

			cwp.CreateWorkitem(workitem,className,workitemType);

			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");

			Reporter.log("Document: "+workitem+" has been created",true);
			ATUReports.add("Document: "+workitem+" has been created",true);
			Log.info("Document: "+workitem+" has been created");

			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");

			cwp.sendWorkItemToDefaultQueue(workitem);

			util.wait(time);

			util.waitForPageToLoad();
			Reporter.log("Retrieving Workitem", true);
			ATUReports.add("Retrieving Workitem", true);
			Log.info("Retrieving Workitem");

			ip.retrieveWorkItem(workitem);

			util.wait(time);
			Reporter.log("Reserving Workitem", true);
			ATUReports.add("Reserving Workitem", true);
			Log.info("Reserving Workitem");

			cwp.reserveWorkItem(workitem);
			util.wait(time);
			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");

			cwp.sendWorkItemToDesiredQueue(workitem, firstQueue);
			util.wait(time);

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute RetrieveReserveAndSend test", true);
			ATUReports.add("execute Retrieve Reserve And Send test", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute RetrieveReserveAndSend test");
		} finally {
			Log.endTestCase("Retrieves and reserves workitem");
		}
	}
}
