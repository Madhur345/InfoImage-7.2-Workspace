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

public class INFO_11076 extends SuperClassIWM {

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
	public void VerifyRenamingDocumentType() {
		Log.startTestCase("INFO_11076_Test to verify creation of Document type of workitem with supported special character in the Workitem Name field");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_11076  is To verify"
					+ "creation of Document type of workitem with supported special character in the Workitem Name field");
			ATUReports.setAuthorInfo("GuptaPr2", "JULY-2018", "0.3");

			// Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = "str1!@#$^&(){}?" + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			cwp.CreateWorkitem(workitem, className, workitemType);
			
			boolean newWItemPresent = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (newWItemPresent) {
				Reporter.log("creation of Document type of workitem with supported special character in the Workitem Name field is Created sucessfully",	true);
				ATUReports.add("creation of Document type of workitem with supported special character in the Workitem Name field is Created sucessfully", true);
				Log.info("creation of Document type of workitem with supported special character in the Workitem Name field is Created sucessfully");
				ATUReports.add("Verify workitem is created", "workitem name is" + workitem,	"workitem name should displayed", "workitem name is displayed in the workitem list", true);

			} else {
				Reporter.log("creation of Document type of workitem with supported special character in the Workitem Name field is not Created sucessfully",	true);
				ATUReports.add("creation of Document type of workitem with supported special character in the Workitem Name field is not Created sucessfully", true);
				Log.info("creation of Document type of workitem with supported special character in the Workitem Name field is not Created sucessfully");
				Assert.fail("creation of Document type of workitem with supported special character in the Workitem Name field is not Created sucessfully");
			}
			util.wait(time);
			util.wait(time);

		}

		catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add("INFO_11076_Test to verify creation of Document type of workitem with supported special character in the Workitem Name field", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Reporter.log("INFO_11076_Test to verify creation of Document type of workitem with supported special character in the Workitem Name field test failed",	true);
			Log.info(" INFO_11076_Test to verify creation of Document type of workitem with supported special character in the Workitem Name field ");
			Assert.fail("INFO_11076_Test to verify creation of Document type of workitem with supported special character in the Workitem Name field");

		}

		finally {
			Log.endTestCase("INFO_11076_Test_to_verify_creation_of_Document_type_of_workitem_with_supported_special_character_in_the_Workitem_Name_field");

		}
	}
}
