package apd.infoimage.iwm.tests;

import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author SumanGaK INFO-2798 This class verifies User can view query fields in
 *         search result when searched using refined search.
 */
public class INFO_2798 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1, groups = { "Search" })
	public void testViewQueryFieldsInRefinedSearchResult() {

		ATUReports.setTestCaseReqCoverage("This class verifies User can view query fields "
				+ "in search result when searched using refined search.");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try {
			Log.startTestCase("INFO_2798_ViewQueryFieldsInRefinedSearchResultTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String queryType = prop.getProperty("queryType");
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);
			String invoiceNoStr = ExcelLib.getCellValue(xlpath, sheet, 10, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Log.info("Workitem : " + workitem);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Log.info("Class Name : " + className);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Log.info("Workitem Type : " + workitemType);

			cwp.CreateWorkitem(workitem, className, workitemType);

			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");
			Reporter.log("Document: " + workitem + " has been created", true);
			ATUReports.add("Document: " + workitem + " has been created", true);
			Log.info("Document: " + workitem + " has been created");
			util.wait(time);

			Reporter.log("Selection of Workitem", true);
			ATUReports.add("Selection of Workitem", true);
			Log.info("Selection of Workitem");
			cwp.getWorkItemName(workitem).click();
			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Updation of Formfield", true);
			ATUReports.add("Updation of Formfield", true);
			Log.info("Updation of Formfield");

			wdp.getFormfields_win().click();
			util.wait(time);
			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys(idCodeStr);
			Reporter.log("Idcode has been written", true);
			ATUReports.add("Idcode has been written", true);
			Log.info("Idcode has been written");
			util.wait(time);

			wdp.getInvoiceno_TF().clear();
			wdp.getInvoiceno_TF().sendKeys(invoiceNoStr);
			Reporter.log("Invoice Number has been written", true);
			ATUReports.add("Invoice Number has been written", true);
			Log.info("Invoice Number has been written");
			util.wait(time);

			util.jclick(wdp.getUpdate_btn());
			Reporter.log("Update Button has been Clicked", true);
			ATUReports.add("Update Button has been Clicked", true);
			Log.info("Update Button has been Clicked");
			util.waitForPageToLoad();
			util.wait(time);
			util.wait(time);
			util.wait(time);
			util.waitForElementEnabled(hp.getSearchTab());

			util.jclick(hp.getSearchTab());
			Reporter.log("Search Tab is clicked ", true);
			ATUReports.add("Search Tab is clicked ", true);
			Log.info("Search Tab is clicked ");

			util.waitForPageToLoad();
			util.wait(time);
			sp.getRefinedSearch().click();
			Reporter.log("Refined Search is clicked ", true);
			ATUReports.add("Refined Search is clicked ", true);
			Log.info("Refined Search is clicked ");

			util.waitForPageToLoad();
			util.wait(time);

			Select sele = new Select(sp.getSelectQueryType());
			sele.selectByValue(queryType);

			Reporter.log("Option selected as : " + queryType, true);
			ATUReports.add("Option selected as : " + queryType, true);
			Log.info("Option selected as : " + queryType);

			util.wait(time);

			sp.getIdCode().sendKeys(idCodeStr);
			sp.getInvoiceNo().sendKeys(invoiceNoStr);
			Reporter.log("ID_Code and Invoice_No values entered in the search fields", true);
			ATUReports.add("ID_Code and Invoice_No values entered in the search fields", true);
			Log.info("ID_Code and Invoice_No values entered in the search fields");

			sp.getRefinedSearchButton().click();
			Reporter.log("Search Button is clicked", true);
			ATUReports.add("Search Button is clicked", true);
			Log.info("Search Button is clicked");

			util.waitForPageToLoad();
			util.wait(time);

			boolean idCodeColumnPresence = util.verifyObjectPresentReturnsBool(sp.getQueryFieldAsIdCodeColumn());
			if (idCodeColumnPresence) {
				Reporter.log("Query field ID_CODE is displayed as column in search result", true);
				ATUReports.add("Query field ID_CODE is displayed as column in search result", true);
				Log.info("Query field ID_CODE is displayed as column in search result");
			} else {

				Reporter.log("Query field ID_CODE is not displayed as column in search result", true);
				Assert.fail("Query field ID_CODE is not displayed as column in search result");
			}
			util.wait(time);
			boolean invoiceNoColumnPresence = util.verifyObjectPresentReturnsBool(sp.getQueryFieldAsInvoiceNoColumn());
			if (invoiceNoColumnPresence) {
				Reporter.log("Query field INVOICE_NO is displayed as column in search result", true);
				ATUReports.add("Query field INVOICE_NO is displayed as column in search result", true);
				Log.info("Query field INVOICE_NO is displayed as column in search result");
			} else {
				Reporter.log("Query field INVOICE_NO is not displayed as column in search result", true);
				Assert.fail("Query field INVOICE_NO is not displayed as column in search result");
			}

			util.wait(time);

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute View Query Fields In Refined Search Result test", true);
			ATUReports.add("failed to execute View Query Fields In Refined Search Result test", true);
			Assert.fail("failed to execute View Query Fields In Refined Search Result test");

		} finally {
			Log.endTestCase("INFO_2798_ViewQueryFieldsInRefinedSearchResultTest");
		}
	}
}