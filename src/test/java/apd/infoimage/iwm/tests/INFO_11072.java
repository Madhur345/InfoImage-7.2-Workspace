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

public class INFO_11072 extends SuperClassIWM {

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
	public void VerifyAlertMessage() {
		Log.startTestCase(
				"INFO_11072_VerifyWarningMessageAtTheTimeCreatingFolderTypeWorkItemYouTypeAUnSupportedCharacterInTheWorkitemNameField");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_11072  is To verify"
					+ "Alert Message At The Time Creating Folder Type WorkItem You Type A Not Supported Special Character In The Workitem Name Field");
			ATUReports.setAuthorInfo("Biswajit", "JULY-2018", "0.4");

			// Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str1 = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);
			String viewstr = ExcelLib.getCellValue(xlpath, sheet, 45, 1);
			String viewName = viewstr + util.getSysDate(0, date);
			String columnName = ExcelLib.getCellValue(xlpath, sheet, 46, 1);
			String columnName1 = ExcelLib.getCellValue(xlpath, sheet, 48, 1);
			String columnName2 = ExcelLib.getCellValue(xlpath, sheet, 49, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);

			StringBuilder builder = new StringBuilder();
			builder.append("str1*%/\\<>" + util.getSysDate(0, date));
			String workitem = builder.toString();

			
			util.waitForElementEnabled(cwp.getCreateWorkitem_btn());
			boolean f = util.verifyObjectPresentReturnsBool(cwp.getCreateWorkitem_btn());
			if (f) {
				Reporter.log("Create Workitem page box present", true);
				ATUReports.add("Create Workitem page box present", true);
				Log.info("Create Workitem page box present");
			} else {
				Reporter.log("Create Workitem page box not present", true);
				ATUReports.add("Create Workitem page box not present", true);
				Assert.fail("Create Workitem page box not present ");
			}

			cwp.getCreateWorkitem_btn().click();
			Thread.sleep(2000);

			f = util.verifyObjectPresentReturnsBool(cwp.getWorkitemCreate_win());
			if (f) {
				Reporter.log("Create Workitem window box is present", true);
				ATUReports.add("Create Workitem window box is present", true);
				Log.info("Create Workitem window box is present");
			} else {
				Reporter.log("Create Workitem window box not present", true);
				ATUReports.add("Create Workitem window box not present", true);
				Assert.fail("Create Workitem window box not present ");
			}
			cwp.getWorkItemname_TF().clear();
			cwp.getWorkItemname_TF().sendKeys(workitem);
			Reporter.log(" Workitem name inserted", true);
			ATUReports.add("Workitem name inserted", true);
			Log.info(" Workitem name inserted");
			
			Select sel = new Select(cwp.getClassName_dd());
			sel.selectByValue(className);
			Reporter.log("ClassName selected as " + className, true);
			Log.info("ClassName selected as " + className);
			ATUReports.add("ClassName selected as \" + className", true);
			Thread.sleep(2000);

			Select sel1 = new Select(cwp.getWorkitemtype_dd());
			sel1.selectByValue(workitemType);
			Reporter.log("WorkitemType selected as " + workitemType, true);
			ATUReports.add("WorkitemType selected as \" + workitemType", true);
			Thread.sleep(2000);

			cwp.getCreateWorkitem_submitbtn().click();
			Reporter.log("Create Workitem submit button clicked", true);
			Log.info("Create Workitem submit button clicked");
			ATUReports.add("Create Workitem submit button clicked", true);
			util.waitForPageToLoad();
			util.wait(5000);

			

			String alertmsg = "/*%\\<> Characters are not allowed.";
			String alertmsg1 = hp.getWIWarningMessage().getText();

			if (alertmsg.equals(alertmsg1)) {
				ATUReports.add("Alert message is displaying '/*%\\\\<> Characters are not allowed.", true);
				Log.info("Alert message is displaying '/*%\\\\<> Characters are not allowed.");
				Reporter.log("Alert message is displaying '/*%\\\\<> Characters are not allowed.", true);

			} else {
				ATUReports.add("Alert message is not coming", true);
				Log.info("Alert message is not coming.");
				Reporter.log("Alert message is not coming.", true);
			}

			hp.getDocumentDuplicateCloseButton();
			ATUReports.add("create workitem popup page successfully closed", true);
			Log.info("create workitem popup page successfully closed");
			Reporter.log("create workitem popup page successfully closed", true);

		} catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add(
					" Alert Message At The Time Creating Folder Type WorkItem You Type A Not Supported Special Character In The Workitem Name Field",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		}

		finally {
			Log.endTestCase(
					"INFO_11072_VerifyWarningMessageAtTheTimeCreatingFolderTypeWorkItemYouTypeAUnSupportedCharacterInTheWorkitemNameField");

		}
	}
}
