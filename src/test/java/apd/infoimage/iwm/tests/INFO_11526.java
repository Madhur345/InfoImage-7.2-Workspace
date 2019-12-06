package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.support.ui.Select;
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
 * @author DashBisw INFO_11122 This class is To Verify The Workitem History
 *         While Creating The Workitem. 20/08/2018
 */
public class INFO_11526 extends SuperClassIWM {

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
	@Test
	public void testVerifyAlertMsgByTypingUnSupportedCharacterInWorkitemName() {
		Log.startTestCase("INFO_11526_TestToVerifyTheWorkitemHistoryWhileCreatingTheWorkitem ");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_11526  is Test to verify "
					+ "The Workitem History While Creating The Workitem");
			ATUReports.setAuthorInfo("Biswajit", "Aug-2018", "0.3");

			// Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str1 = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);

			StringBuilder builder = new StringBuilder();
			builder.append("str1*%/\\<>");
			String workitem = builder.toString();

			util.waitForElementEnabled(cwp.getCreateWorkitem_btn());
			boolean f = util.verifyObjectPresentReturnsBool(cwp.getCreateWorkitem_btn());
			if (f) {
				Reporter.log("Create Workitem page box present", true);
				Log.info("Create Workitem page box present");
			} else {
				Reporter.log("Create Workitem page box not present", true);
				Assert.fail("Create Workitem page box not present ");
			}

			cwp.getCreateWorkitem_btn().click();
			Thread.sleep(2000);

			f = util.verifyObjectPresentReturnsBool(cwp.getWorkitemCreate_win());
			if (f) {
				Reporter.log("Create Workitem window box is present", true);
				Log.info("Create Workitem window box is present");
			} else {
				Reporter.log("Create Workitem window box not present", true);
				Assert.fail("Create Workitem window box not present ");
			}
			cwp.getWorkItemname_TF().clear();
			cwp.getWorkItemname_TF().sendKeys(workitem);
			Reporter.log(" Workitem name inserted", true);
			Log.info(" Workitem name inserted");
			Select sel = new Select(cwp.getClassName_dd());
			sel.selectByValue(className);
			Reporter.log("ClassName selected as " + className, true);
			Log.info("ClassName selected as " + className);
			Thread.sleep(2000);

			Select sel1 = new Select(cwp.getWorkitemtype_dd());
			sel1.selectByValue(workitemType);
			Reporter.log("WorkitemType selected as " + workitemType, true);
			Thread.sleep(2000);
			cwp.getCreateWorkitem_submitbtn().click();
			Reporter.log("Create Workitem submit button clicked", true);
			Log.info("Create Workitem submit button clicked");
			util.waitForPageToLoad();
			util.wait(5000);

			boolean alertPresent = util
					.verifyObjectEnabledReturnsBool(cwp.getErrorMsgForUnSupportedCharsInCreateworkitem());
			if (alertPresent) {
				String message = cwp.getErrorMsgForUnSupportedCharsInCreateworkitem().getText();
				Reporter.log("Alert message dispalyed is " + message, true);
				Log.info("Alert message dispalyed is " + message);
				ATUReports.add("Alert message dispalyed is " + message, true);
			} else {
				Reporter.log("Alert message is not dispalyed for unsupported characters for Create workitem ", true);
				Log.info("Alert message is not dispalyed for unsupported characters for Create workitem");
				Assert.fail("Alert message is not dispalyed for unsupported characters for Create workitem");
			}

			cwp.getWorkitemClose_button().click();
			Reporter.log("Create Workitem window closed", true);
			ATUReports.add("Create Workitem window closed", true);
			Log.info("Create Workitem window closed");
			Thread.sleep(2000);

			cwp.getCreateWorkitem_btn().click();
			Thread.sleep(2000);

			f = util.verifyObjectPresentReturnsBool(cwp.getWorkitemCreate_win());
			if (f) {
				Reporter.log("Create Workitem window will be displayed without previous error message", true);
				Log.info("Create Workitem window will be displayed without previous error message");
				ATUReports.add("Create Workitem window will be displayed without previous error message", true);
			} else {
				Reporter.log("Create Workitem window box not present", true);
				Assert.fail("Create Workitem window box not present ");
			}

		}

		catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add("Create Workitem window will be displayed with previous error message", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Create Workitem window will be displayed without previous error message");

		}

		finally {
			Log.endTestCase("INFO_11526_TestToVerifyTheWorkitemHistoryWhileCreatingTheWorkitem ");

		}
	}
}