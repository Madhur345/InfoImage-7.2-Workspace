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

public class INFO_11058 extends SuperClassIWM {

	/**
	 * @author SinghAvn INFO-11058 Test to verify alert message at the time you type
	 *         a not supported character in the Workitem Name field
	 */

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
	@Test(enabled = true, priority = 1)
	public void testVerifyFolderWorkitemRetrieveFromInboxDocumentDuplicate() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-11058 Test to verify alert message at the time you type a not supported character in the Workitem Name field");
		ATUReports.setAuthorInfo("SinghAvn", "July-2018", "0.3");

		try {
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			String witemName = "new" + util.getSysDate(0, "yyDDMMhhmmss");
			String newWitemName = "*,%,/,\\,<,>" + util.getSysDate(0, "yyDDMMhhmmss");
			String errorMessage = " Characters are not allowed.";

			Reporter.log("Class Name :To_Verify_Alert_With_Non_Supported_Character", true);
			ATUReports.add("Class Name :To_Verify_Alert_With_Non_Supported_Character", true);
			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			cwp.CreateWorkitemClickSubmit(newWitemName, className, workitemType);

			if (errorMessage.equalsIgnoreCase(cwp.getErrorMsgForUnSupportedCharsInCreateworkitem().getText()))
				;
			{
				System.err.println(
						"Error Message is Present: " + cwp.getErrorMsgForUnSupportedCharsInCreateworkitem().getText());
			}

		}

		catch (Exception e) {
			Reporter.log(
					" verify Renaming the folder type of workitem while passing supported character in the Workitem Name field type test failed",
					true);
			ATUReports.add(
					" verify Renaming the folder type of workitem while passing supported character in the Workitem Name field test failed",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info(
					"verify Renaming the folder type of workitem while passing supported character in the Workitem Name field");
			Assert.fail(
					" verify Renaming the folder type of workitem while passing supported character in the Workitem Name field");
		} finally {
			Log.endTestCase(
					"INFO-11058 Test to verify alert message at the time you type a not supported character in the Workitem Name field");
		}
	}

}
