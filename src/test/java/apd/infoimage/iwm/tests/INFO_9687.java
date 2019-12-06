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
 * @author SumanGaK
 * INFO-9687
 * This class will Verify keyboard shortcuts for closing the workitem
 *
 */
public class INFO_9687 extends SuperClassIWM{


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
	public void testVerifyKeyboardShortcutsForClosingWorkitem()
	{
		Log.startTestCase("INFO_9687_VerifyKeyboardShortcutsForClosingWorkitemTest");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9687  is for Verifying keyboard shortcuts for closing the workitem");
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


			Reporter.log("VerifyKeyboardShortcutsForClosingWorkitemTest",true);
			ATUReports.add("VerifyKeyboardShortcutsForClosingWorkitemTest",true);
			Log.info("VerifyKeyboardShortcutsForClosingWorkitemTest");

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

			cwp.getWorkItemName(workitem).click();

			Reporter.log("WorkItemDetailView is displayed",true);
			ATUReports.add("WorkItemDetailView is displayed",true);
			Log.info("WorkItemDetailView is displayed");
			util.wait(time);

			Actions action = new Actions(Driver.driver); 

			action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u0071')).perform();	

			action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u0071')).perform();			

			util.wait(time);

			util.waitForPageToLoad();

			boolean workitemCloseStatus=util.verifyObjectPresentReturnsBool(wdp.getClosingWorkitemXMark());


			if(!workitemCloseStatus)
			{
				Reporter.log("Workitem is closed", true);
				ATUReports.add("Workitem is closed", true);
				Log.info("Workitem is closed");
				ATUReports.add("Verify Close workitem window using shortkut key", "","Close workitem window should be displayed",
						"Close workitem window is displayed", true);
			}

			else
			{
				Reporter.log("Workitem is not closed", true);
				ATUReports.add("Workitem is not closed", true);
				Log.info("Workitem is not closed");
				Assert.fail("Workitem is not closed");
			}

			util.wait(time);
			util.waitForPageToLoad();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("failed to Verify keyboard shortcuts for closing the workitem",
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyKeyboardShortcutsForClosingWorkitem test is failed");
		}
		finally
		{
			Log.endTestCase("INFO_9687_VerifyKeyboardShortcutsForClosingWorkitemTest");
		}
	}
	
}