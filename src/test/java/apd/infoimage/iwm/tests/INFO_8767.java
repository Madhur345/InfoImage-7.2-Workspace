package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
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
 * @author SumanGaK  -  28-Mar-2018
 * INFO-8767
 * This test method Performs 'Get Next' functionality for a workitem in any queue
 */
public class INFO_8767 extends SuperClassIWM
{
	@BeforeMethod
	public void setUp(){
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName1", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown(){
		hp.logoutApp();
	}


	@SuppressWarnings("deprecation")
	@Test(enabled =true , priority=1,groups={"GetNext"})
	public void testGetNextWorkitemQueue()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-8767 is for Performing 'Get Next' functionality for a workitem in any queue");
		ATUReports.setAuthorInfo("Suman","MAR-2018","0.3"); 

		try{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_8767_GetNextWorkitemQueueTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			int getNextCount = ExcelLib.getCellValueInt(xlpath, sheet, 71, 1);   
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);   

			Reporter.log("Creation of Workitem",true);
			ATUReports.add("Creation of Workitem",true);
			Log.info("Creation of Workitem");

			cwp.CreateWorkitem(workitem,className,workitemType);
			Reporter.log("Sending Workitem to Workflow",true);
			ATUReports.add("Sending Workitem to Workflow",true);
			Log.info("Sending Workitem to Workflow");

			util.wait(time);
			cwp.sendWorkItemToDefaultQueue(workitem);
			util.wait(time);
			util.waitForPageToLoad();					

			ip.getNextONButton().click();
			Reporter.log("get Next Button is clicked",true);
			ATUReports.add("get Next Button is clicked",true);
			Log.info("get Next Button is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().clear();
			util.wait(time);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().sendKeys(""+getNextCount);
			Reporter.log("get Next Required No Of Workitems is sent to textbox",true);
			ATUReports.add("get Next Required No Of Workitems is sent to textbox",true);
			Log.info("get Next Required No Of Workitems is sent to textbox");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getSaveButton().click();
			Reporter.log("SaveButton is clicked",true);
			ATUReports.add("SaveButton is clicked",true);
			Log.info("SaveButton is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked",true);
			ATUReports.add("Data Entry is clicked",true);
			Log.info("Data Entry is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getNextOFFButton().click();
			Reporter.log("get Next Button is clicked and turned off", true);
			ATUReports.add("get Next Button is clicked and turned off", true);
			Log.info("get Next Button is clicked and turned off");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getWorkItemsTab().click();
			Reporter.log("WorkItem tab is clicked",true);
			ATUReports.add("WorkItem tab is clicked",true);
			Log.info("WorkItem tab is clicked");

		}
		catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute GetNextWorkitemQueue test",true);
			ATUReports.add("failed to execute GetNextWorkitemQueue test",true);
			Assert.fail("failed to execute GetNextWorkitemQueue test");
		}

		finally {
			Log.endTestCase("INFO_8767_GetNextWorkitemQueueTest");
		}
	}
	
}