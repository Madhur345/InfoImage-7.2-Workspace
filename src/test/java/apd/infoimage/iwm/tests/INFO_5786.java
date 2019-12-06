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
 * @author PradhanJ
 * INFO-5786
 * To test the Nested Query in IWM for workitems in Domain level by passing 
 * "ATTRIBUTES" and "WFIVARS" column names by joining two tables in SQL query string that is passed as an input
 */
public class INFO_5786 extends SuperClassIWM{

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
	public void testNestedQueryInDomainlevelByPassingaTTRIBUTESandWFIVARSinQueryString() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-5786 is Test to verify the Nested search functionality "
				+ "in Domain level by passing ATTRIBUTES and WFIVARS column names by joining two tables in SQL query string ");
 
		ATUReports.setAuthorInfo("Jayashri", "JUNE-2018", "0.3");

		try {
			Log.startTestCase(
					"INFO_5786_NestedQueryInDomainlevelByPassingaTTRIBUTESandWFIVARSinQueryString");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet2="Sheet_J";
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String nestedQuery=ExcelLib.getCellValue(xlpath, sheet2, 18, 1);
								
			int time=3000;
			
			String idCode = randomNo.getRandomNo(6);
			Reporter.log("id_code value is "+idCode, true);
			
			String invoiceNo=randomNo.getRandomNo(4);
			Reporter.log("invoice_no value is "+invoiceNo, true);
			
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
			
			sp.getNestedSearchTab().click();
			Reporter.log("Nested Search tab is selected", true);
			Log.info("Nested Search tab is selected");
			ATUReports.add("Nested Search tab is selected", true);
			
			Select selectQueryType=new Select(sp.getQueryTypeDropdown());
			selectQueryType.selectByValue(nestedQuery);
			Reporter.log("Nested Query selected is "+nestedQuery, true);
			Log.info("Nested Query selected is "+nestedQuery);
			ATUReports.add("Nested Query selected is "+nestedQuery, true);
			
			sp.getIdCodeFieldInNestedSearch().clear();
			sp.getIdCodeFieldInNestedSearch().sendKeys(idCode);
			Reporter.log("ID_CODE value is entered", true);
			Log.info("ID_CODE value is entered");
			ATUReports.add("ID_CODE value is entered", true);			
			util.wait(time);
			
			sp.getInvoiceNoFieldInNestedSearch().clear();
			sp.getInvoiceNoFieldInNestedSearch().sendKeys(invoiceNo);
			Reporter.log("Invoice No value is entered", true);
			Log.info("Invoice No value is entered");
			ATUReports.add("Invoice No value is entered", true);
			
			sp.getSearchBtnInnestedSearch().click();
			Reporter.log("Search button is clicked", true);
			Log.info("Search button is clicked");
			ATUReports.add("Search button is clicked", true);
			
			sp.searchWorkitemByNameInSearchpage(workitem);
			util.wait(time);
			boolean f9 = util.verifyObjectPresentReturnsBool(sp.getWorkItemName(workitem));
			if(f9){
				Reporter.log("Workitem is searched successfully in Domain level by passing ATTRIBUTES and WFIVARS "
						+ "column names by joining two tables in SQL query string ",true);
				ATUReports.add("Workitem is searched successfully in Domain level by passing ATTRIBUTES and WFIVARS", true);
				Log.info("Workitem is searched successfully in Domain level by passing ATTRIBUTES and WFIVARS");
			}else{
				ATUReports.add("Nested query in Domain level by passing ATTRIBUTES and WFIVARS column names by joining"
						+ " two tables in SQL query string  test failed", 
						LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Nested query in Domain level by passing ATTRIBUTES and WFIVARS column "
						+ "names by joining two tables in SQL query string  test failed ");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add(
					"Failed to verify the Nested search functionality in Domain level "
					+ "by passing ATTRIBUTES and WFIVARS column names by joining "
					+ "two tables in SQL query string  using query builder in iwm.",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail(" Nested search functionality in Domain level by passing ATTRIBUTES and WFIVARS column names by joining test failed");
		}
		finally {
			Log.endTestCase("INFO_5786_NestedQueryInDomainlevelByPassingaTTRIBUTESandWFIVARSinQueryString");
			
		}
	}
}

