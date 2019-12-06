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
 * @author SinghAvn 26-NOV-2018 INFO-17267 This class will verify the reserve
 *         alert when the reserve on workitem fails.
 **/
public class INFO_17267 extends SuperClassIWM {
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
	public void reserveAlert() {

		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-17267 will verify the reserve alert when the reserve on workitem fails.");
		ATUReports.setAuthorInfo("Avnish ", "NOV-2018", "0");

		try {
			Log.startTestCase(
					"INFO_17267_Test_verify_the_reserve_alert_when_the_reserve_on_workitem");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			Reporter.log(
					"Class Name : INFO_17267_Test_verify_the_reserve_alert_when_the_reserve_on_workitem",
					true);
			ATUReports.add(
					"Class Name : INFO_17267_Test_verify_the_reserve_alert_when_the_reserve_on_workitem",
					true);

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");
			util.wait(time);
			ip.getCheckBoxWorkItemName(workitem).click();
			Reporter.log("Workitem Check Box to is clicked", true);
			ATUReports.add("Workitem Check Box to is clicked", true);
			Log.info("Workitem Check Box to is clicked");
			
			cwp.getSendWorkItemButton().click();
			Reporter.log("send workitem button is clicked", true);
			ATUReports.add("send workitem button is clicked", true);
			Log.info("send workitem button is clicked");
				
			cwp.getSendButton().click();
			Reporter.log("send button is clicked", true);
			ATUReports.add("send button is clicked", true);
			Log.info("send button is clicked");
			util.wait(time);
			
			hp.getSearchTab().click();
			Reporter.log("Search tab is clicked", true);
			ATUReports.add("Search tab is clicked", true);
			Log.info("Search tab is clicked");
			util.wait(time);
			
			sp.getSearchByNameTab().click();
			Reporter.log("SearchByName  tab is clicked", true);
			ATUReports.add("SearchByName tab is clicked", true);
			Log.info("SearchByName tab is clicked");
			util.wait(time);
					
			sp.getwNameSearchTextbox().sendKeys(workitem);
			Reporter.log("writing in the textbox", true);
			ATUReports.add("writing in the textbox", true);
			Log.info("writing in the textbox");
			util.wait(time);
			
			sp.getSbnSearchBtn().click();
			Reporter.log("Searchbutton is clicked", true);
			ATUReports.add("Searchbutton is clicked", true);
			Log.info("Searchbutton is clicked");
			util.wait(time);
		
			
			sp.getsearchByNameCheckBox().click();
			Reporter.log("checkbox is clicked", true);
			ATUReports.add("checkbox is clicked", true);
			Log.info("checkbox is clicked");
			util.wait(time);
			
			util.jclick(sp.getRetrieveButton());
			Reporter.log("retrieve button is clicked", true);
			ATUReports.add("retrieve button is clicked", true);
			Log.info("retrieve button is clicked");
			util.wait(time);
			
		    hp.getWorkItemTab().click();
		    Reporter.log("workitem tab is clicked", true);
			ATUReports.add("workitem tab is clicked", true);
			Log.info("workitem tab is clicked");
		    util.wait(time);
		    
		    
		    
		    cwp.searchByNameInWorkitemList(workitem);
		    
		    cwp.getWorkItemName(workitem).click();
		    Reporter.log("workitem is opened", true);
			ATUReports.add("workitem is opened", true);
			Log.info("workitem is opened");
		    
			wdp.getActionsDropDown().click();
			Reporter.log("Action drop down is clicked", true);
			ATUReports.add("Action drop down is clicked", true);
			Log.info("Action drop down is clicked");
			util.wait(time);
			
			wdp.getReserveOption().click();
			Reporter.log("reserve button is clicked", true);
			ATUReports.add("reserve button is clicked", true);
			Log.info("reserve button is clicked");
			util.wait(time);
			
			wdp.getErrorMessage().click();
			util.wait(time);
			
			//validation
			String message = wdp.getErrorMessage().getText();
			if(message.equalsIgnoreCase("Workitem is WIP in this domain."));
			{
				System.out.println(" workitem is wip in domain Alert should is displayed");
			}
			 
	
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("INFO_17267_Test_verify_the_reserve_alert_when_the_reserve_on_workitem  Failed", true);
			Log.info("INFO_17267_Test_verify_the_reserve_alert_when_the_reserve_on_workitem Failed");
			ATUReports.add("INFO_17267_Test_verify_the_reserve_alert_when_the_reserve_on_workitem Failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("INFO_17267_Test_verify_the_reserve_alert_when_the_reserve_on_workitem Failed");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase(
					"INFO_17267_Test_verify_the_reserve_alert_when_the_reserve_on_workitem");
		}
	}
}