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
 * @author GuptaPr2 - INFO-7546 Verifying error message when trying to duplicate
 *         document/folder type of workitem without having form fields defined
 *         and enabling 'include form fields'
 */

public class INFO_7546 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1, groups = { "DocumentDuplicate" })
	public void VerifyingErrorMessageWhenTryingToDuplicateDocumentFolderTypeOfWorkitemWithoutHavingFormFields() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7546 is for Verifying error message when trying to duplicate document/folder type of workitem without having form fields defined and enabling 'include form fields'");
		ATUReports.setAuthorInfo("Princi", "May-2018", "0.3");

		try {
			Log.startTestCase("INFO_7546_VerifyingErrorMessageWhenTryingToDuplicateDocumentFolderTypeOfWorkitemWithoutHavingFormFieldsDefinedAndEnablingIncludeFormFields");
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

			Reporter.log("VerifyingErrorMessageWhenTryingToDuplicateDocumentFolderTypeOfWorkitemWithoutHavingFormFieldsDefinedAndEnablingIncludeFormFields", true);
			ATUReports.add("VerifyingErrorMessageWhenTryingToDuplicateDocumentFolderTypeOfWorkitemWithoutHavingFormFieldsDefinedAndEnablingIncludeFormFields", true);
			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			util.wait(time);
			util.waitForPageToLoad();

			hp.getSearchFieldInWorkitemTab().clear();
			Reporter.log("Search Field In Workitem Tab is cleared", true);
			ATUReports.add("Search Field In Workitem Tab is cleared", true);

			hp.searchByNameInWorkitemTabAndDisplay(workitem);
			Reporter.log("Workitem is Searched and Displayed", true);
			ATUReports.add("Workitem is Searched and Displayed", true);

			hp.getFirstRowFirstCell().click();
			Reporter.log("Action Button of Workitem is clicked", true);
			ATUReports.add("Action Button of Workitem is clicked", true);

			hp.getDocumentDuplicateOption().click();
			Reporter.log("Document Duplicate Option is clicked", true);
			ATUReports.add("Document Duplicate Option is clicked", true);
			Thread.sleep(7000);

			hp.getIncludeFormDataCheckBox().click();
			Reporter.log("Include Form Data CheckBox is checked", true);
			ATUReports.add("Include Form Data CheckBox is checked", true);

			String errormsg = "please fill the mandatory fields to include form data";
			String errormsg1 = cwp.getErrorMsgForMandatoryField().getText();

			if (errormsg.equals(errormsg1)) {
				Reporter.log("Error message is displaying 'please fill the mandatory fields to include form data.'", true);
				ATUReports.add("Error message is displaying 'please fill the mandatory fields to include form data.'", true);
				Log.info("Error message is displaying 'please fill the mandatory fields to include form data.");

			} else {
				Reporter.log("Error message is not coming", true);
				ATUReports.add("Error message is not coming", true);
				Log.info("Error message is not coming.");
			}

			boolean documentDuplicateOptionDisplay = wdp.getDocumentDuplicateOption().isDisplayed();
			System.out.println("++++++++++++++++++++++++++++++");

			if(documentDuplicateOptionDisplay) {
				Reporter.log("Duplicate button is displayed", true);
				ATUReports.add("Duplicate button is displayed", true);
				Log.info("Duplicate button is displayed");
				Assert.fail("Duplicate button is displayed");
			} else {
				Reporter.log("Duplicate button is not displayed", true);
				ATUReports.add("Duplicate button is not displayed", true);
				Log.info("Duplicate button is not displayed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());

			Reporter.log("Verify Folder Workitem With Out Form Fields include form data Document Duplicate Test is failed.", true);
			ATUReports.add("Verify Folder Workitem With Out Form Fields include form data  Document Duplicate Test is failed.",	LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Folder Workitem With Out Form Fields include form data Document Duplicate Test is failed.");
		} finally {
			Log.endTestCase("INFO_7546_VerifyingErrorMessageWhenTryingToDuplicateDocumentFolderTypeOfWorkitemWithoutHavingFormFieldsDefinedAndEnablingIncludeFormFields");
		}
	}
}
