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
 * INFO-5797
 * To test the nested Query in IWM in Domain level by passing > and < condition 
 * in SQL query string that is passed as an input.
 */
public class INFO_5797 extends SuperClassIWM{
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
	public void testNestedQueryByPassingLessAndGreaterThanQueryString() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-5797 is Test to Test to verify the Nested search functionality "
				+ "by passing > and < condition in SQL query string");
		ATUReports.setAuthorInfo("Jayashri", "JUNE-2018", "0.3");

		try {
			Log.startTestCase(
					"INFO_5797_NestedQueryByPassingLessAndGreaterThanQueryString");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			
			String sheet = prop.getProperty("sheet");
			String sheet2="Sheet_J";
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String nestedQuery=ExcelLib.getCellValue(xlpath, sheet2, 4, 1);
			String idCode=ExcelLib.getCellValue(xlpath, sheet2, 5, 1);
			String operator=ExcelLib.getCellValue(xlpath, sheet2, 9, 1);
			String terms=ExcelLib.getCellValue(xlpath, sheet2, 7, 1);			
			String termsLessThan=ExcelLib.getCellValue(xlpath, sheet2, 10, 1);
			String operatorGreaterThan=ExcelLib.getCellValue(xlpath, sheet2, 11, 1);
			String termsGreaterThan=ExcelLib.getCellValue(xlpath, sheet2, 12, 1);
			int time=3000;
			
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
			Select termsSelect=new Select(cwp.getTermsInFormField());
			termsSelect.selectByValue(terms);			
			cwp.getUpdateFormBtn().click();
			Reporter.log("ID_CODE and Terms field is updatted", true);
			Log.info("ID_CODE and Terms field is updatted");
			ATUReports.add("ID_CODE and Terms field is updatted", true);
			
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
			
			Select selectOperator=new Select(sp.getTermsOperatorDropdown());
			selectOperator.selectByVisibleText(operator);
			Reporter.log(operator+" operator is selected for Terms", true);
			Log.info(operator+" operator is selected for Terms");
			ATUReports.add(operator+" operator is selected for Terms", true);
			
			sp.getTermsFieldInNestedSearch().clear();
			sp.getTermsFieldInNestedSearch().sendKeys(termsLessThan);
			Reporter.log("Terms value is entered", true);
			Log.info("Terms value is entered");
			ATUReports.add("Terms value is entered", true);
			
			sp.getSearchBtnInnestedSearch().click();
			Reporter.log("Search button is clicked", true);
			Log.info("Search button is clicked");
			ATUReports.add("Search button is clicked", true);
			
			sp.searchWorkitemByNameInSearchpage(workitem);
			util.wait(time);
			boolean f9 = util.verifyObjectPresentReturnsBool(sp.getWorkItemName(workitem));
			if(f9){
				Reporter.log("Workitem is searched successfully by Nested query using <(Less Than) operator",true);
				ATUReports.add("Workitem is searched successfully by Nested query using < (Less Than)operator", true);
				Log.info("Workitem is searched successfully by Nested query using <(Less Than) operator");
			}else{
				ATUReports.add("Nested query using < operator test failed", 
						LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Nested query using <(Less Than) operator test failed ");
			}
			sp.getNestedSearchTab().click();
			Reporter.log("Nested Search tab is selected", true);
			Log.info("Nested Search tab is selected");
			ATUReports.add("Nested Search tab is selected", true);			
			
			selectQueryType.selectByValue(nestedQuery);
			Reporter.log("Nested Query selected is "+nestedQuery, true);
			Log.info("Nested Query selected is "+nestedQuery);
			ATUReports.add("Nested Query selected is "+nestedQuery, true);
			
			selectOperator.selectByVisibleText(operatorGreaterThan);
			Reporter.log(operatorGreaterThan+" operator is selected for Terms", true);
			Log.info(operatorGreaterThan+" operator is selected for Terms");
			ATUReports.add(operatorGreaterThan+" operator is selected for Terms", true);
			
			sp.getTermsFieldInNestedSearch().clear();
			sp.getTermsFieldInNestedSearch().sendKeys(termsGreaterThan);
			Reporter.log("Terms value is entered", true);
			Log.info("Terms value is entered");
			ATUReports.add("Terms value is entered", true);
			
			sp.getSearchBtnInnestedSearch().click();
			Reporter.log("Search button is clicked", true);
			Log.info("Search button is clicked");
			ATUReports.add("Search button is clicked", true);
			
			sp.searchWorkitemByNameInSearchpage(workitem);
			util.wait(time);
			boolean workitemFound = util.verifyObjectPresentReturnsBool(sp.getWorkItemName(workitem));
			if(workitemFound){
				Reporter.log("Workitem is searched successfully by Nested query using >(Greater Than) operator",true);
				ATUReports.add("Workitem is searched successfully by Nested query using >(Greater Than)operator", true);
				Log.info("Workitem is searched successfully by Nested query using >(Greater Than) operator");
			}else{
				ATUReports.add("Nested query using >(Greater Than) operator test failed", 
						LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Nested query using >(Greater Than) operator test failed ");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add(
					"Failed to verify the Nested search functionality "
					+ "by passing > and < condition in SQL query string",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail(" Nested search functionality by passing > and < condition in SQL query string test failed");
		}
		finally {
			Log.endTestCase("INFO_5797_NestedQueryByPassingLessAndGreaterThanQueryString");
		}
	}
}
