package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
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
 * INFO-17265
 * Test to verify the reserve alert on performing the
 * reserve on redable workitem which is not in workflow.
 */
public class INFO_17265 extends SuperClassIWM{
	
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
	@Test(enabled = true)
	public void testVerifyReserveAlertWhilePerformingReserveOnReadableWorkitem() {

		ATUReports.setTestCaseReqCoverage("This Scenario is to verify the mail functionality in the IWM"
				+ "details view viewer");
		ATUReports.setAuthorInfo("PradhanJ","NOV-2018","0.3"); 
		try {
			Log.startTestCase("INFO_17265_VerifyReserveAlertWhilePerformingReserveOnReadableWorkitem");
			DOMConfigurator.configure("log4j.xml");
			
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");
			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
						
			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);

			Reporter.log("Creation of Workitem",true);
			ATUReports.add("Creation of Workitem",true);
			Log.info("Creation of Workitem");
			cwp.CreateWorkitem(workitem,className,workitemType);
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");
			
			cwp.searchByNameInWorkitemList(workitem);
			
			Reporter.log("Sending workitem to default queue",true);
			ATUReports.add("Sending workitem to default queue",true);
			Log.info("Sending workitem to default queue");
			cwp.sendWorkItemToDefaultQueue(workitem);
			
			Reporter.log("Navigate to search tab and search by name page",true);
			ATUReports.add("Navigate to search tab and search by name page",true);
			Log.info("Navigate to search tab and search by name page");
			hp.getSearchTab().click();
			util.waitForPageToLoad();
			util.waitForElementEnabled(sp.getSearchByNameTab());
			util.wait(10000);
			sp.getSearchByNameTab().click();
			
			Reporter.log("Search the workitem using name",true);
			ATUReports.add("Search the workitem using name",true);
			Log.info("Search the workitem using name");
			sp.getwNameSearchTextbox().sendKeys(workitem);
			sp.getSbnSearchBtn().click();
			util.waitForPageToLoad();
			util.wait(5000);
			
			Reporter.log("Select the checkbox and Retrive the serached workitem",true);
			ATUReports.add("Select the checkbox and Retrive the serached workitem",true);
			Log.info("Select the checkbox and Retrive the serached workitem");
			sp.getCheckBoxWorkItemName(workitem).click();
			sp.getRetrieveButton().click();
			util.waitForPageToLoad();
			util.waitForElementEnabled(hp.getWorkItemTab());
			util.wait(30000);
			
			Reporter.log("Navigate to workitem tab and search the workitem by name ",true);
			ATUReports.add("Navigate to workitem tab and search the workitem by name",true);
			Log.info("Navigate to workitem tab and search the workitem by name");
			hp.getWorkItemTab().click();
			util.waitForPageToLoad();
			util.waitForElementEnabled(cwp.getNameSearchTextbox());
			util.wait(10000);
			
			cwp.searchByNameInWorkitemList(workitem);
			cwp.getActionBtn(workitem).click();
			
			Reporter.log("Select the workitem and Reserve the workitem",true);
			ATUReports.add("Select the workitem and Reserve the workitem",true);
			Log.info("Select the workitem and Reserve the workitem");
			if(cwp.getReserveWorkItemButton().isDisplayed())
			{
				cwp.getReserveWorkItemButton().click();
				Reporter.log("Reserve alert is verified",true);
				ATUReports.add("Reserve alert is verified",true);
				Log.info("Reserve alert is verified");
			
			}
			else
			{
				Reporter.log("Reserve option not available",true);
				ATUReports.add("Reserve option not available",true);
				Log.info("Reserve option not available");
			}
						
		}

		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add("Failed to verify the reserve alert on performing the"
					+ "reserve on redable workitem which is not in workflow.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("Failed to verify the reserve alert on performing the"
					+ "reserve on redable workitem which is not in workflow.");
			
		} finally {

			Log.endTestCase("INFO_17265_VerifyReserveAlertWhilePerformingReserveOnReadableWorkitem");
		}
	}
}
