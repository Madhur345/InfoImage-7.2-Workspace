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
 * @author SumanGaK - 9-Sep-2018
 * INFO_12731
 * This class verify 'Recent search' history using 'Search By Name' for document type of workitem under Archive level
 * Pre-requisite : Enterprise setting should be enabled in registry
 */
public class INFO_12731 extends SuperClassIWM {
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
	@Test(enabled = true, priority = 1, groups = { "Search" })
	public void testVerifyRecentSearchHistoryUsingSearchByNameForDocumentWorkitemUnderArchiveLevel() {

		ATUReports.setTestCaseReqCoverage("This Scenario is To verify 'Recent search' history using 'Search By Name' for document type of workitem under Archive level");
		ATUReports.setAuthorInfo("Suman", "SEP-2018", "0.3");
		try {
			Log.startTestCase("INFO_12731_VerifyRecentSearchHistoryUsingSearchByNameForDocumentWorkitemUnderArchiveLevelTest");
			Reporter.log("INFO_12731_VerifyRecentSearchHistoryUsingSearchByNameForDocumentWorkitemUnderArchiveLevelTest", true);
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String scope = ExcelLib.getCellValue(xlpath, sheet, 82, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Class Name : INFO_12731_VerifyRecentSearchHistoryUsingSearchByNameForDocumentWorkitemUnderArchiveLevelTest", true);
			ATUReports.add("Class Name : INFO_12731_VerifyRecentSearchHistoryUsingSearchByNameForDocumentWorkitemUnderArchiveLevelTest", true);
			Log.info("Class Name : INFO_12731_VerifyRecentSearchHistoryUsingSearchByNameForDocumentWorkitemUnderArchiveLevelTest");

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

			util.wait(time);

			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			cwp.sendWorkItemToDefaultQueue(workitem);

			util.waitForPageToLoad();
			util.wait(time);

			util.waitForPageToLoad();

			hp.getSearchTab().click();
			Reporter.log("Clicked on Search Tab", true);
			ATUReports.add("Clicked on Search Tab", true);
			Log.info("Clicked on Search Tab");
			util.wait(time);
			util.wait(time);

			sp.getSearchByNameTab().click();
			Reporter.log("Clicked on SearchByName", true);
			ATUReports.add("Clicked on SearchByName", true);
			Log.info("Clicked on SearchByName");
			util.wait(time);
			
			Select sel = new Select(sp.getScope());
			sel.selectByValue(scope);
			Reporter.log("Option selected as " + scope, true);
			ATUReports.add("Option selected as " + scope, true);
			Log.info("Option selected as " + scope);
			util.wait(time);
			
			sp.getSbnTextBox().sendKeys(workitem);
			Reporter.log("Workitem name sent to text box", true);
			ATUReports.add("Workitem name sent to text box", true);
			Log.info("Workitem name sent to text box");
			
			sp.getSbnSearchBtn().click();
			Reporter.log("Clicked on Search Button", true);
			ATUReports.add("Clicked on Search Button", true);
			Log.info("Clicked on Search Button");
			util.wait(time);
			util.wait(time);

			boolean workitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if(workitemPresence) {
				Reporter.log("Workitem is present in grid", true);
				ATUReports.add("Workitem is present in grid", true);
				Log.info("Workitem is present in grid");
			} else {
				Reporter.log("Workitem is not present in grid", true);
				Assert.fail("Workitem is not present in grid ");
			}

			util.waitForPageToLoad();	

			sp.getHistoryOfSearch().click();
			Reporter.log("History Of SearchByName is clicked", true);
			ATUReports.add("History Of SearchByName is clicked", true);
			Log.info("History Of SearchByName is clicked");

			util.wait(time);
			util.waitForPageToLoad();	
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute VerifyRecentSearchHistoryUsingSearchByNameForDocumentWorkitemUnderArchiveLevel test", true);
			ATUReports.add("failed to execute VerifyRecentSearchHistoryUsingSearchByNameForDocumentWorkitemUnderArchiveLevel test", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute VerifyRecentSearchHistoryUsingSearchByNameForDocumentWorkitemUnderArchiveLevel test");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO_12731_VerifyRecentSearchHistoryUsingSearchByNameForDocumentWorkitemUnderArchiveLevelTest");
		}
	}	
}


