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
 * @author PradhanJ
 * INFO-11041
 * Test to verify icon indicating that the workitems is in workflow 
 * for document type of workitem which is retrieved from default queue
 */
public class INFO_11041 extends  SuperClassIWM{

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
	@Test(enabled =true,groups={"Workitem"})
	public void testVeifySavingCustomJSForm()
	{
		Log.startTestCase("INFO_11041_VerifyIconForRetrievedWorkitemOfDocumentType");
		ATUReports.setTestCaseReqCoverage("This Scenario will Test to verify icon indicating that the workitems "
				+ "is in workflow for document type of workitem which is retrieved from default queue");
		ATUReports.setAuthorInfo("PradhanJ","JUL-2018","0.3");
		try
		{
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			int time=3000;
			
			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);

			cwp.CreateWorkitem(workitem,className,workitemType);
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");

			util.wait(time);
			Reporter.log("Sending Workitem to Workflow",true);
			util.wait(time);
			cwp.sendWorkItemToDefaultQueue(workitem);
			ATUReports.add("Sending Workitem to Workflow",true);
			Log.info("Sending Workitem to Workflow");
			util.wait(time);
			util.waitForPageToLoad();

			Reporter.log("Retrieving Workitem",true);	
			ATUReports.add("Retrieving Workitem",true);
			Log.info("Retrieving Workitem");
			ip.retrieveWorkItem(workitem);
			
			String worksetValue=cwp.getWorksetColumnValue().getText();
			if(worksetValue.contains("Data Entry"))
			{
				Reporter.log("Workset column value is displayed as Data Entry for the retrieved workitem",true);	
				ATUReports.add("Workset column value is displayed as Data Entry for the retrieved workitem",true);
				Log.info("Workset column value is displayed as Data Entry for the retrieved workitem");
			}
			else
			{
				Reporter.log("Workset column value validation failed", true);
				Log.warn("Workset column value validation failed");
				Assert.fail("Workset column value validation failed");
			}
			
		}
		catch (Exception e) {
			Reporter.log("Verify Icon for retrieved workitem of dovument type test failed",true);	
			ATUReports.add("Verify Icon for retrieved workitem of dovument type test failed", LogAs.FAILED	, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("Verify Icon for retrieved workitem of dovument type test failed");
			Assert.fail("Verify Icon for retrieved workitem of dovument type test failed");
		}
		finally {
			Log.endTestCase("INFO_11041_VerifyIconForRetrievedWorkitemOfDocumentType");
		}
	}
}