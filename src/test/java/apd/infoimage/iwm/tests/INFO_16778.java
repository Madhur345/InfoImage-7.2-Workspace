package apd.infoimage.iwm.tests;
/**
 * @author DashBisw INFO_16778  This class is To verify 
 * "The Search Results Without Search Criteria In The Nested Search" 28/11/2018.
 */
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

public class INFO_16778 extends SuperClassIWM{
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
	public void testVerifyTheSearchResultsWithoutSearchCriteriaInTheNestedSearch() {
		Log.startTestCase("INFO_16778_VerifyReserveAvailabilityUnderActionsWhenWorkitemRetrivedFromSearch");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_16778  is To verify"
					+ "Verify The Search Results Without Search Criteria In The Nested Search");
			ATUReports.setAuthorInfo("Biswajit", "Nov-2018", "0.3");

		
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
			String terms=ExcelLib.getCellValue(xlpath, sheet2, 7, 1);					
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
			util.waitForElementEnabled(sp.getSearchBtnInnestedSearch());
			util.wait(time);
			
			
			sp.getSearchBtnInnestedSearch().click();
			Reporter.log("Search button is clicked", true);
			Log.info("Search button is clicked");
			ATUReports.add("Search button is clicked", true);
			
			util.wait(time);
			boolean f9 = util.verifyObjectPresentReturnsBool(sp.getNoResultMsg());
			if(f9){
				Reporter.log("QUery Result NOT found message is dispalyed after searching without passing query string",true);
				ATUReports.add("QUery Result NOT found message is dispalyed after searching without passing query string", true);
				Log.info("QUery Result NOT found message is dispalyed after searching without passing query string");
			}else{
				ATUReports.add("Nested query using < operator test failed", 
						LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Nested query using <(Less Than) operator test failed ");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add("Failed to Verify The Search Results Without Search Criteria In The Nested Search", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("Failed to Verify The Search Results Without Search Criteria In The Nested Search");
		}
		finally {
			Log.endTestCase("INFO_16778_VerifyReserveAvailabilityUnderActionsWhenWorkitemRetrivedFromSearch");
		}
	}
}
