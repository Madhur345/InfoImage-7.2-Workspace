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

public class INFO_11112 extends SuperClassIWM {

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
				"INFO_11112_VerifyUpdateFormFieldsWithoutEnteringMandatoryFieldsForDocumentTypeOfWorkiteminWorkitemsList");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_11112  is To verify"
					+ "Update Form Fields Without Entering Mandatory Fields For Document Type Of Workitem in Workitems List");
			ATUReports.setAuthorInfo("Biswajit", "JULY-2018", "0.3");

			// Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str1 = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str1 + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String viewstr = ExcelLib.getCellValue(xlpath, sheet, 45, 1);
			String viewName = viewstr + util.getSysDate(0, date);
			String columnName = ExcelLib.getCellValue(xlpath, sheet, 46, 1);
			String columnName1 = ExcelLib.getCellValue(xlpath, sheet, 48, 1);
			String columnName2 = ExcelLib.getCellValue(xlpath, sheet, 49, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			StringBuilder builder = new StringBuilder();
			builder.append("str1!@#$^&(){}?");
			String newworkitem = builder.toString();

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("workitem Created sucessfully", true);
			ATUReports.add("workitem Created sucessfully", true);
			Log.info("workitem Created sucessfully");
			Thread.sleep(5000);

			// Select the created workitem
			cwp.getCheckBoxWorkItem(workitem).click();
			Reporter.log("Selected: " + workitem + " to rename the workitem", true);
			ATUReports.add("Selected: " + workitem + " to rename the workitem", true);
			Log.info("workitem checkbox seleted");

			wdp.getActionBtn().click();
			Reporter.log("Action Button clicked", true);
			ATUReports.add("Action Button clicked", true);
			Log.info("Action Button clicked");

			wdp.getformField().click();
			Reporter.log("Formfield Button clicked", true);
			ATUReports.add("Formfield Button clicked", true);
			Log.info("Formfield Button clicked");
			util.waitForelementEnable();
			Thread.sleep(10000);
			util.wait(time);

			wdp.getUpdate_btn().click();
			Reporter.log("Update Button clicked", true);
			ATUReports.add("Update Button clicked", true);
			Log.info("Update Button clicked");
			Thread.sleep(2000);

			String alertmsg = "Required Fields : ID_CODE*";
			String alertmsg1 = wdp.getRequiredFieldError().getText();

			if (alertmsg.equals(alertmsg1)) {
				ATUReports.add("Alert message is displaying 'Required Fields : ID_CODE*", true);
				Reporter.log("Alert message is displaying 'Required Fields : ID_CODE*", true);
				Log.info("Alert message is displaying 'Required Fields : ID_CODE*");

			} else {
				ATUReports.add("Alert message is not coming", true);
				Reporter.log("Alert message is not coming", true);
				Log.info("Alert message is not coming");
			}

			//
			wdp.getclosebtn().click();
			Reporter.log("close Button clicked", true);
			ATUReports.add("close Button clicked", true);
			Log.info("close Button clicked");
			Thread.sleep(2000);
		}

		catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add(
					"Update Form Fields Without Entering Mandatory Fields For Document Type Of Workitem in Workitems List",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		}

		finally {
			Log.endTestCase(
					"INFO_11112_VerifyUpdateFormFieldsWithoutEnteringMandatoryFieldsForDocumentTypeOfWorkiteminWorkitemsList");

		}
	}
}
