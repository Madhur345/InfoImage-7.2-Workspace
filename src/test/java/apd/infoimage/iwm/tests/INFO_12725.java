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
 * @author SumanGaK - 13-Aug-2018
 * INFO_12725
 * This class verify 'Recent search' history using 'Refined search' for Folder type of workitem under DOMAIN level
 */
public class INFO_12725 extends SuperClassIWM {
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
	public void testVerifyRecentSearchHistoryUsingRefinedSearchForFolderWorkitemUnderDOMAINLevel() {

		ATUReports.setTestCaseReqCoverage("This Scenario is To verify 'Recent search' history using 'Refined Search' for Folder type of workitem under DOMAIN level");
		ATUReports.setAuthorInfo("Suman", "AUG-2018", "0.3");
		try {
			Log.startTestCase("INFO_12725_VerifyRecentSearchHistoryUsingRefinedSearchForFolderWorkitemUnderDOMAINLevelTest");
			Reporter.log("INFO_12725_VerifyRecentSearchHistoryUsingRefinedSearchForFolderWorkitemUnderDOMAINLevelTest", true);
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String queryType=prop.getProperty("queryType");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);
			String idCodeStr=ExcelLib.getCellValue(xlpath,sheet,9,1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Class Name : INFO_12725_VerifyRecentSearchHistoryUsingRefinedSearchForFolderWorkitemUnderDOMAINLevelTest", true);
			ATUReports.add("Class Name : INFO_12725_VerifyRecentSearchHistoryUsingRefinedSearchForFolderWorkitemUnderDOMAINLevelTest", true);
			Log.info("Class Name : INFO_12725_VerifyRecentSearchHistoryUsingRefinedSearchForFolderWorkitemUnderDOMAINLevelTest");

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

			Reporter.log("Folder: " + workitem + " has been created", true);
			ATUReports.add("Folder: " + workitem + " has been created", true);

			util.wait(time);
			Reporter.log("Selection of Workitem",true);
			ATUReports.add("Selection of Workitem",true);
			Log.info("Selection of Workitem");
			cwp.getWorkItemName(workitem).click();
			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Updation of Formfield",true);
			ATUReports.add("Updation of Formfield",true);
			Log.info("Updation of Formfield");

			wdp.getFormfields_win().click();
			util.wait(time);
			//ID_CODE text field
			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys(idCodeStr);
			Reporter.log("Idcode has been written",true);
			ATUReports.add("Idcode has been written",true);
			Log.info("Idcode has been written");
			util.wait(time);
			wdp.getUpdate_btn().click();
			util.wait(time);
			util.jclick(cwp.getWorkItems());
			util.wait(time);
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(workitem);				

			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			cwp.sendWorkItemToDefaultQueue(workitem);

			util.waitForPageToLoad();
			util.wait(time);

			util.waitForPageToLoad();

			Reporter.log("Performing Refined Search", true);
			ATUReports.add("Performing Refined Search", true);
			Log.info("Performing Refined Search");

			hp.getSearchTab().click();
			Reporter.log("Clicked on Search Tab", true);
			ATUReports.add("Clicked on Search Tab", true);
			Log.info("Clicked on Search Tab");
			util.wait(time);
			util.wait(time);

			sp.getRefinedSearch().click();
			Reporter.log("Clicked on Refined Search", true);
			ATUReports.add("Clicked on Refined Search", true);
			Log.info("Clicked on Refined Search");
			util.wait(time);
			
			sp.getRefinedSearchFolderRadio().click();
			Reporter.log("Refined Search Folder Radio button is clicked", true);
			ATUReports.add("Refined Search Folder Radio button is clicked", true);
			Log.info("Refined Search Folder Radio button is clicked");
			util.wait(time);

			Select sel = new Select(sp.getSelectQueryType());
			sel.selectByValue(queryType);
			Reporter.log("Option selected as " + queryType, true);
			ATUReports.add("Option selected as " + queryType, true);
			Log.info("Option selected as " + queryType);
			util.wait(time);			
			
			sp.getIdCode().sendKeys(idCodeStr);
			util.wait(time);			

			sp.getRefinedSearchButton().click();
			Reporter.log("Clicked on Refined Search Button", true);
			ATUReports.add("Clicked on Refined Search Button", true);
			Log.info("Clicked on Refined Search Button");
			util.wait(time);
			
			sp.searchWorkitemByNameInSearchpage(workitem);
			util.wait(time);
			boolean workitemSearchPresence = util.verifyObjectPresentReturnsBool(sp.getWorkItemName(workitem));
			if (workitemSearchPresence) {
				Reporter.log("Workitem using Refined Search is present in grid", true);
				ATUReports.add("Workitem using Refined Search is present in grid", true);
				Log.info("Workitem using Refined Search is present in grid");
			} else {
				Reporter.log("Workitem using Refined Search is not present in grid", true);
				Assert.fail("Workitem using Refined Search is not present in grid ");
			}

			util.wait(time);
			util.wait(time);

			util.waitForPageToLoad();	

			sp.getHistoryOfSearch().click();
			Reporter.log("History Of Refined Search is clicked", true);
			ATUReports.add("History Of Refined Search is clicked", true);
			Log.info("History Of Refined Search is clicked");

			util.wait(time);
			util.waitForPageToLoad();	
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute VerifyRecentSearchHistoryUsingRefinedSearchForDocumentWorkitemUnderDOMAINLevel test", true);
			ATUReports.add("failed to execute VerifyRecentSearchHistoryUsingRefinedSearchForDocumentWorkitemUnderDOMAINLevel test", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute VerifyRecentSearchHistoryUsingRefinedSearchForDocumentWorkitemUnderDOMAINLevel test");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO_12725_VerifyRecentSearchHistoryUsingRefinedSearchForDocumentWorkitemUnderDOMAINLevelTest");
		}
	}
	
}

