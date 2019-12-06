package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

public class INFO_6316 extends SuperClassIWM{

	/**
	 * @author SumanGaK
	 * INFO-6316
	 * This class checks Workitems should not be displayed as read-only
	 */
	@BeforeMethod
	public void setUp(){
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown(){
		hp.logoutApp();
	}


	@SuppressWarnings("deprecation")
	@Test(enabled =true,priority=1,groups={"Workitem"})
	public void testVerifyWorkitemsNotDisplayingReadOnlyModeFromQueuesToWorkitems()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-6316 is for Verifying Workitems should not be displayed as read-only");
		ATUReports.setAuthorInfo("Suman","OCT-2017","0.3"); 

		try{
			Log.startTestCase("Verifying Workitems should not be displayed as read-only");

			Reporter.log("Class Name : VerifyWorkitemsNotDisplayingReadOnlyModeFromQueuesToWorkitemsTest",true);
			ATUReports.add("Class Name : VerifyWorkitemsNotDisplayingReadOnlyModeFromQueuesToWorkitemsTest",true);
			Log.info("Class Name : VerifyWorkitemsNotDisplayingReadOnlyModeFromQueuesToWorkitemsTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);				
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);	
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);


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

			Reporter.log("Document: "+workitem+" has been created",true);
			ATUReports.add("Document: "+workitem+" has been created",true);
			Log.info("Document: "+workitem+" has been created");
			Reporter.log("Sending Workitem to Workflow",true);
			ATUReports.add("Sending Workitem to Workflow",true);
			Log.info("Sending Workitem to Workflow");

			cwp.sendWorkItemToDefaultQueue(workitem);

			util.waitForPageToLoad();	
			Reporter.log("Retrieving Workitem",true);
			ATUReports.add("Retrieving Workitem",true);
			Log.info("Retrieving Workitem");

			ip.retrieveWorkItem(workitem);

			util.wait(time);
			cwp.getDocumentType(workitem);

			util.waitForPageToLoad();	 	  
			util.wait(time);
		}
		catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute Workitems Mode test",true);
			ATUReports.add("Verifying Workitems should not be displayed as read-only",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute Workitems Mode test");
		}
		finally
		{			
			Log.endTestCase("Verifying Workitems should not be displayed as read-only");
		}
	}
}
