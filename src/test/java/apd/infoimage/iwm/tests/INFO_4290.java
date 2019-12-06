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
 * @author PradhanJ INFO-4290 Test to verify the Nested search functionality
 *         using in iwm.
 */
public class INFO_4290 extends SuperClassIWM {

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
	public void testVerifyRefinedSearchUsingNestedSearchInIWM() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-4290 is Test to verify the "
				+ "Nested search functionality using refined search in iwm");
		ATUReports.setAuthorInfo("Jayashri", "JUNE-2018", "0.3");

		try {
			Log.startTestCase("INFO_4290_VerifyRefinedSearchUsingNestedSearchInIWM");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet2 = "Sheet_J";
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String QueryForRefinedSearch = ExcelLib.getCellValue(xlpath, sheet2, 20, 1);
			String idCode = ExcelLib.getCellValue(xlpath, sheet2, 2, 1);
			String invoiceNo = ExcelLib.getCellValue(xlpath, sheet2, 19, 1);
			int time = 3000;

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getActionBtn(workitem).click();
			cwp.getFormFiledsBtn().click();
			Reporter.log("FormFields window is opened for the created workitem", true);
			Log.info("FormFields window is opened for the created workitem");
			ATUReports.add("FormFields window is opened for the created workitem", true);

			cwp.getID_CODE_Tf().sendKeys(idCode);
			cwp.getInvoiceNoInFormFields().sendKeys(invoiceNo);
			cwp.getUpdateFormBtn().click();
			Reporter.log("ID_CODE and Invoice No field is updated", true);
			Log.info("ID_CODE and Invoice No field is updated");
			ATUReports.add("ID_CODE and Invoice No field is updated", true);

			hp.getSearchTab().click();
			Reporter.log("Search Tab is clicked", true);
			Log.info("Search Tab is clicked");
			ATUReports.add("Search Tab is clicked", true);

			sp.getRefinedSearch().click();
			Reporter.log("Refined Search tab is selected", true);
			Log.info("Refined Search tab is selected");
			ATUReports.add("Refined Search tab is selected", true);

			Select selectQueryType = new Select(sp.getSelectQueryType());
			selectQueryType.selectByValue(QueryForRefinedSearch);
			Reporter.log("Refined search Query selected is " + QueryForRefinedSearch, true);
			Log.info("Refined search Query selected is " + QueryForRefinedSearch);
			ATUReports.add("Refined search Query selected is " + QueryForRefinedSearch, true);

			sp.getIdCode().sendKeys(idCode);
			Reporter.log("ID_CODE value is entered", true);
			Log.info("ID_CODE value is entered");
			ATUReports.add("ID_CODE value is entered", true);

			sp.getInvoiceNo().sendKeys(invoiceNo);
			Reporter.log("Invoice No value is entered", true);
			Log.info("Invoice No value is entered");
			ATUReports.add("Invoice No value is entered", true);

			sp.getRefinedSearchButton().click();
			Reporter.log("Refined Search button is clicked", true);
			Log.info("Refined Search button is clicked");
			ATUReports.add("Refined Search button is clicked", true);

			sp.searchWorkitemByNameInSearchpage(workitem);
			util.wait(time);
			boolean f9 = util.verifyObjectPresentReturnsBool(sp.getWorkItemName(workitem));
			if (f9) {
				Reporter.log("Refined search is validated successfully", true);
				ATUReports.add("Refined search is validated successfully", true);
				Log.info("Refined search is validated successfully");
			} else {
				ATUReports.add("Refined search validation using Nested query  test failed", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Refined search validation using Nested query  test failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add("Failed to verify the Nested search functionality " + "using refined search.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Nested query validation in Refined search test failed");
		} finally {
			Log.endTestCase("INFO_4290_VerifyRefinedSearchUsingNestedSearchInIWM");

		}
	}
}
