package apd.infoimage.iwm.tests;
/**
 * @author DashBisw INFO_17266  This class is To verify 
 * "The Reserve Alert On Performing The Reserve On Workitem 
 * Which Is Locked By Another User". 28/11/2018
 */
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

public class INFO_17266 extends SuperClassIWM{
	
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

		Log.startTestCase(
				"INFO_17266_VerifyTheReserveAlertOnPerformingTheReserveOnWorkitemWhichIsLockedByAnotherUser");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_17266  is To verify"
					+ " The Reserve Alert On Performing The Reserve On Workitem Which Is Locked By Another User");
			ATUReports.setAuthorInfo("Biswajit", "Nov-2018", "0.3");
			
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
			
			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("Sending workitem to default queue",true);
			ATUReports.add("Sending workitem to default queue",true);
			Log.info("Sending workitem to default queue");
			
			
			hp.getSearchTab().click();
			Reporter.log("Navigate to search tab and search by name page",true);
			ATUReports.add("Navigate to search tab and search by name page",true);
			Log.info("Navigate to search tab and search by name page");
			util.waitForPageToLoad();
			util.waitForElementEnabled(sp.getSearchByNameTab());
			util.wait(10000);
			
			sp.getSearchByNameTab().click();
			Reporter.log("Search the workitem using name",true);
			ATUReports.add("Search the workitem using name",true);
			Log.info("Search the workitem using name");
			util.waitForPageToLoad();
			
			sp.getwNameSearchTextbox().sendKeys(workitem);
			sp.getSearchButton().click();
			util.waitForPageToLoad();
		
			sp.getCheckBoxWorkItemName(workitem);
			sp.getRetrieveButton().click();
			Reporter.log("Select the checkbox and Retrive the serached workitem",true);
			ATUReports.add("Select the checkbox and Retrive the serached workitem",true);
			Log.info("Select the checkbox and Retrive the serached workitem");
			util.waitForPageToLoad();
			util.wait(15000);
			
			hp.getWorkItemTab().click();
			Reporter.log("Navigate to workitem tab and search the workitem by name ",true);
			ATUReports.add("Navigate to workitem tab and search the workitem by name",true);
			Log.info("Navigate to workitem tab and search the workitem by name");
			util.waitForPageToLoad();
			util.waitForElementEnabled(cwp.getNameSearchTextbox());
			util.wait(10000);
			
			cwp.searchByNameInWorkitemList(workitem);
			cwp.getActionBtn(workitem).click();
			Reporter.log("Select the workitem and Reserve the workitem",true);
			ATUReports.add("Select the workitem and Reserve the workitem",true);
			Log.info("Select the workitem and Reserve the workitem");
			cwp.getReserveWorkItemButton().click();
			
						}

		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
		} finally {

			Log.endTestCase("INFO_17266_VerifyTheReserveAlertOnPerformingTheReserveOnWorkitemWhichIsLockedByAnotherUser");
		}
	}
}
