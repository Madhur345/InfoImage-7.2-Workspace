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

public class INFO_11118 extends SuperClassIWM {

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
	public void VerifyUpdateFormFieldsbyEnteringMandatoryFields() {
		Log.startTestCase(
				"INFO_11118_VerifyUpdateFormFieldsbyEnteringMandatoryFieldsForDocumentTypeOfWorkiteminWorkitemsDetailedView");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_11117  is To verify"
					+ "Update Form Fields Without Entering Mandatory Fields For document Type Of Workitem in workitems details view");
			ATUReports.setAuthorInfo("DashBiswa", "JULY-2018", "0.3");

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
			String invoiceNoStr = ExcelLib.getCellValue(xlpath, sheet, 10, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

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

			wdp.getwiView().click();
			Reporter.log("WorkItem successfully viewed", true);
			ATUReports.add("WorkItem successfully viewed", true);
			Log.info("WorkItem successfully viewed");
			Thread.sleep(2000);

			wdp.getFormfields_win().click();
			Reporter.log("Formfield open button clicked", true);
			ATUReports.add("Formfield open button clicked", true);
			Log.info("Formfield open button clicked");
			Thread.sleep(2000);

			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys(idCodeStr);
			Reporter.log("Idcode has been written", true);
			ATUReports.add("Idcode has been written", true);
			Log.info("Idcode has been written");
			Thread.sleep(2000);

			wdp.getupdateFormButton().click();
			Reporter.log("Update Button clicked", true);
			ATUReports.add("Update Button clicked", true);
			Log.info("Update Button clicked");
			util.waitForelementEnable();
			Thread.sleep(10000);
			util.wait(time);

			hp.getWorkItemTab().click();
			Reporter.log("Workitem tab is clicked", true);
			ATUReports.add("Workitem tab is clicked", true);
			Log.info("Workitem tab is clicked");
			util.waitForPageToLoad();

			hp.searchByNameInWorkitemTabAndDisplay(workitem);
			Reporter.log("Searching By Workitem Name And Display In Workitem tab", true);
			ATUReports.add("Searching By Workitem Name And Display In Workitem tab", true);
			boolean createdWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (createdWorkitemPresence) {
				Reporter.log("Created Workitem is present in grid", true);
				ATUReports.add("Created Workitem is present in grid", true);
			} else {
				Reporter.log("Created Workitem is not present in grid", true);
				ATUReports.add("Created Workitem is not present in grid", true);
				Assert.fail("Created Workitem is not present in grid ");
			}

		}

		catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add(
					"Update Form Fields Without Entering Mandatory Fields For document Type Of Workitem in workitem detailed view",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Reserve WorkItem failed Assert");

		}

		finally {
			Log.endTestCase(
					"INFO_11118_VerifyUpdateFormFieldsbyEnteringMandatoryFieldsForDocumentTypeOfWorkiteminWorkItemDetailedView");

		}
	}
}
