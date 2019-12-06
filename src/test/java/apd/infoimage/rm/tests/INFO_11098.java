package apd.infoimage.rm.tests;

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
 * @author SumanGaK  - 28-Jun-2018
 * INFO-11098
 * This class will Verify Duplicate user name is displayed in Audit log
 **/
public class INFO_11098 extends SuperClassIWM {

	@BeforeMethod
	public void beforMethod() {
		Driver.driver.get(rmProp.getProperty("rmUrl"));
		util.waitForPageToLoad();
		rmlp.loginToRM("userid", "pwd", "domain");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod() {
		rmhp.logoutOfRM();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testVerifyDuplicateUserNameDisplayInAuditLog() {	
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-11098  is to Verify Duplicate user name is displayed in Audit log");
		ATUReports.setAuthorInfo("SumanGaK", "JUN-2018", "0.3");

		try {
			Log.startTestCase("INFO_11098_VerifyDuplicateUserNameDisplayInAuditLogTest");

			rmProp = new Properties();
			rmProp.load(new FileInputStream("src/main/resources/rmData.properties"));

			String sheet = rmProp.getProperty("sheet");
			String xlpath = rmProp.getProperty("xlpath");

			String objectType=ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String str = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String seriesName = str + util.getSysDate(0, date);
			String userName = rmProp.getProperty("userid");

			Reporter.log("User is in Homepage of RM", true);
			ATUReports.add("User is in Homepage of RM", true);
			Log.info("User is in Homepage of RM");

			rmhp.getFilePlanTab().click();
			Reporter.log("File Plan Tab is clicked", true);
			ATUReports.add("File Plan Tab is clicked", true);
			Log.info("File Plan Tab is clicked");

			rmfpp.getAddSeriesButton().click();
			Reporter.log("Add Series Button is clicked", true);
			ATUReports.add("Add Series Button is clicked", true);
			Log.info("Add Series Button is clicked");

			rmfpp.getSeriesName_TF().sendKeys(seriesName);
			Reporter.log("Series Name is sent to text box", true);
			ATUReports.add("Series Name is sent to text box", true);
			Log.info("Series Name is sent to text box");

			rmfpp.getSeriesTitle_TF().sendKeys(seriesName);
			Reporter.log("Series Name is sent to Title text box", true);
			ATUReports.add("Series Name is sent to Title text box", true);
			Log.info("Series Name is sent to Title text box");

			rmfpp.getSeriesDescription_TF().sendKeys(seriesName);
			Reporter.log("Series Name is sent to Description text box", true);
			ATUReports.add("Series Name is sent to Description text box", true);
			Log.info("Series Name is sent to Description text box");

			rmfpp.getAddSeries_SaveBtn().click();
			Reporter.log("Add Series Save Button is clicked", true);
			ATUReports.add("Add Series Save Button is clicked", true);
			Log.info("Add Series Save Button is clicked");

			rmhp.getAuditLogTab().click();
			Reporter.log("Audit Log Tab is clicked", true);
			ATUReports.add("Audit Log Tab is clicked", true);
			Log.info("Audit Log Tab is clicked");

			Select selectObjectType=new Select(rmalp.getObjectTypeDropdown());
			selectObjectType.selectByValue(objectType);
			Reporter.log("Object Type selected is "+objectType, true);
			Log.info("Object Type selected is "+objectType);
			ATUReports.add("Object Type selected is "+objectType, true);

			rmalp.getGoButtonInAuditLog().click();
			Reporter.log("Go Button In Audit Log is clicked", true);
			Log.info("Go Button In Audit Log is clicked");
			ATUReports.add("Go Button In Audit Log is clicked", true);

			String userText= rmalp.getFirstRowFirstCell().getText();
			if(userText.equals(userName))
			{
				Reporter.log("User name is not duplicated in Audit Log", true);
				Log.info("User name is not duplicated in Audit Log");
				ATUReports.add("User name is not duplicated in Audit Log", true);
			}			
			else
			{
				Reporter.log("User name is duplicated in Audit Log", true);
				Log.info("User name is duplicated in Audit Log");
				ATUReports.add("VerifyDuplicateUserNameDisplayInAuditLog test failed",LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("VerifyDuplicateUserNameDisplayInAuditLog test failed");
				ATUReports.add("User name is duplicated in Audit Log", true);
			}
			Assert.assertEquals(userText, userName, "User name is duplicated in Audit Log");			

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Log.error("VerifyDuplicateUserNameDisplayInAuditLog test failed " + e.getMessage());
			ATUReports.add("Failed to verify DuplicateUserNameDisplayInAuditLog test in rm.",LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Reporter.log("VerifyDuplicateUserNameDisplayInAuditLog test failed", true);
			Assert.fail("VerifyDuplicateUserNameDisplayInAuditLog test failed");
		}finally{
			Log.endTestCase("INFO_11098_VerifyDuplicateUserNameDisplayInAuditLogTest");			
		}

	}
}

