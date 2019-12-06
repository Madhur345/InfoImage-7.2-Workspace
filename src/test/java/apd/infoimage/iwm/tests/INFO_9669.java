package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
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

/**
 * @author SumanGaK  - 12-Apr-2018
 * INFO-9669
 * This class will Verify keyboard shortcuts for displaying the workitem history
 */
public class INFO_9669 extends SuperClassIWM{


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
	@Test(enabled=true,priority=1,groups={"KeyboardShortcut"})
	public void testVerifyKeyboardShortcutsForWorkitemHistory()
	{
		Log.startTestCase("INFO_9669_VerifyKeyboardShortcutsForWorkitemHistoryTest");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9669  is for Verifying keyboard shortcuts for displaying the workitem history");
		ATUReports.setAuthorInfo("Suman","APR-2018","0.3"); 

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
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);


			Reporter.log("VerifyKeyboardShortcutsForWorkitemHistoryTest",true);
			ATUReports.add("VerifyKeyboardShortcutsForWorkitemHistoryTest",true);
			Log.info("VerifyKeyboardShortcutsForWorkitemHistoryTest");

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

			Reporter.log("Sending Workitem to Workflow and Validate that workitem is present in the data entry grid",true);
			ATUReports.add("Sending Workitem to Workflow and Validate that workitem is present in the data entry grid",true);
			Log.info("Sending Workitem to Workflow and Validate that workitem is present in the data entry grid");
			cwp.sendWorkItemToDefaultQueue(workitem);

			util.waitForPageToLoad();

			Reporter.log("Retreiving Workitem",true);
			ATUReports.add("Retreiving Workitem",true);
			Log.info("Retreiving Workitem");

			ip.retrieveWorkItem(workitem);

			//Navigate to workitem history window for the retrieved workitem  

			cwp.getCheckBoxWorkItemName(workitem).click();

			Reporter.log("Clicking WorkItem History Button",true);
			ATUReports.add("Clicking WorkItem History Button",true);
			Log.info("Clicking WorkItem History Button");
			util.wait(time);

			Actions action = new Actions(Driver.driver); 
			action.keyDown(Keys.CONTROL);
			action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u0068')).perform();

			action.keyUp(Keys.CONTROL);
			action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u0068')).perform();

			util.wait(time);
			util.waitForPageToLoad();

			Reporter.log("Searching For Workitem History In Workitem tab",true);
			ATUReports.add("Searching For Workitem History In Workitem tab",true);
			Log.info("Searching For Workitem History In Workitem tab");

			boolean wHistoryWinPresent=util.verifyObjectPresentReturnsBool(cwp.getWorkflowHistoryWin());
			if(wHistoryWinPresent)
			{
				Reporter.log("Workitem history window is present", true);
				ATUReports.add("Workitem history window is present", true);
				Log.info("Workitem history window is present");
				ATUReports.add("Verify workitem history window using shortkut key", "","workitem history window should be displayed",
						"workitem history window is displayed", true);
			}
			else
			{
				Reporter.log("Workitem history window is not present", true);
				ATUReports.add("Workitem history window is not present", true);
				Log.info("Workitem history window is not present");
				Assert.fail("Workitem history window is not present");
			}
		}

		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("failed to Verify keyboard shortcuts for displaying the workitem history window",
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyKeyboardShortcutsForWorkitemHistory test is failed");
		}
		finally
		{
			Log.endTestCase("INFO_9669_VerifyKeyboardShortcutsForWorkitemHistoryTest");
		}
	}
}