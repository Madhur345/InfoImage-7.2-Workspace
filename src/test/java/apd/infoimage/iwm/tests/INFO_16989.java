package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

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
 * @author PradhanJ INFO-16989 Test to verify the availability of send to
 *         default under the actions in the workitem detailsview page.
 */
public class INFO_16989 extends SuperClassIWM {

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
	@Test(enabled = true)
	public void testVerifySendToDefaultUnderActionInWorkitemDetailPage() {

		ATUReports.setTestCaseReqCoverage(
				"This Scenario is to Test to verify the availability of send to default "
				+ "under the actions in the workitem detailsview page.");
		ATUReports.setAuthorInfo("PradhanJ", "NOV-2018", "0.3");
		try {
			Log.startTestCase("INFO_16989_VerifySendToDefaultUnderActionInWorkitemDetailPage");
			DOMConfigurator.configure("log4j.xml");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Log.info("Workitem : " + workitem);

			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Log.info("Class Name : " + className);

			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Log.info("Workitem Type : " + workitemType);

			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			Log.info("Creation of Workitem");
			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			cwp.searchByNameInWorkitemList(workitem);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("Open the workitem details page", true);
			ATUReports.add("Open the workitem details page", true);
			Log.info("Open the workitem details page");
			util.waitForElementEnabled(wdp.getActionButton());
			util.wait(10000);

			wdp.getActionButton().click();
			Reporter.log("Click on Actions button in workitem details page", true);
			ATUReports.add("Click on Actions button in workitem details page", true);
			Log.info("Click on Actions button in workitem details page");
			
			boolean sendToDefaultAvailable=util.verifyObjectPresentReturnsBool(wdp.getSendToDefaultOption());
			
			if(sendToDefaultAvailable)
			{
				Reporter.log("Send to default option is available in Actions button in workitem details page", true);
				ATUReports.add("Send to default option is available in Actions button in workitem details page", true);
				Log.info("Send to default option is available in Actions button in workitem details page");
			}
			else
			{
				Reporter.log("Send to default option is NOT available in Actions button in workitem details page", true);
				ATUReports.add("Send to default option is NOT available in Actions button in workitem details page", true);
				Log.error("Send to default option is NOT available in Actions button in workitem details page");
				Assert.fail("Send to default option is NOT available in Actions button in workitem details page");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add("Failed to verify the availability of send to"
					+ "  default under the actions in the workitem detailsview page.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("Failed to verify the availability of send to"
					+ "  under the actions in the workitem detailsview page.");
		} finally {

			Log.endTestCase("INFO_16989_VerifySendToDefaultUnderActionInWorkitemDetailPage");
		}
	}

}
