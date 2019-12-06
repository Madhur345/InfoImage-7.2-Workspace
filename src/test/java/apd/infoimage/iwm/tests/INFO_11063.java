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
 * @author DashBisw INFO_11063  This class is To Verify No Alert Message At The Time You Type 
 * A Supported Special Character In The Workitem Name Field". 27/07/2018
 */
public class INFO_11063 extends SuperClassIWM {

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
				"INFO_11063_VerifyNoAlertMessageAtTheTimeYouTypeASupportedSpecialCharacterInTheWorkitemNameField");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_11062  is To verify"
					+ "No Alert Message At The Time You Type A Supported Special Character In The Workitem Name Field");
			ATUReports.setAuthorInfo("DashBisw", "JULY-2018", "0.3");

			// Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str1 = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			//String workitem = str1!,@,#,$,^,&,(,),{,},? + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet,4,1);
			String viewstr = ExcelLib.getCellValue(xlpath, sheet, 45, 1);
			String viewName = viewstr + util.getSysDate(0, date);
			String columnName = ExcelLib.getCellValue(xlpath, sheet, 46, 1);
			String columnName1 = ExcelLib.getCellValue(xlpath, sheet, 48, 1);
			String columnName2 = ExcelLib.getCellValue(xlpath, sheet, 49, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);
			
			StringBuilder builder = new StringBuilder();
	        builder.append("str1!@#$^&(){}?" + util.getSysDate(0, date));
	        String workitem = builder.toString();

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("Alert message is not coming workitem Created sucessfully", true);
			ATUReports.add("Alert message is not coming workitem Created sucessfully", true);
			Log.info("Alert message is not coming workitem Created sucessfully");
			Thread.sleep(5000);

			
		}

		catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add("No Alert Message At The Time You Type A Supported Special Character In The Workitem Name Field", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		}

		finally {
			Log.endTestCase("INFO_11063_VerifyNoAlertMessageAtTheTimeYouTypeASupportedSpecialCharacterInTheWorkitemNameField");

		}
	}
	}


