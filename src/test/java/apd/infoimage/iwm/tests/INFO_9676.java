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
 * INFO-9676
 * This class will Verify keyboard shortcuts for Adding Video In workitem and validate the same
 */
public class INFO_9676 extends SuperClassIWM {

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
	public void testVerifyKeyboardShortcutsForAddingVideoInWorkitem()
	{
		Log.startTestCase("INFO_9676_VerifyKeyboardShortcutsForAddingVideoInWorkitemTest");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9676 is for Verifying keyboard shortcuts for adding video imports to a workitem and validate the same");
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

			Reporter.log("VerifyKeyboardShortcutsForAddingVideoInWorkitemTest",true);
			ATUReports.add("VerifyKeyboardShortcutsForAddingVideoInWorkitemTest",true);
			Log.info("VerifyKeyboardShortcutsForAddingVideoInWorkitemTest");

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

			Reporter.log("Opening WorkItem",true);
			ATUReports.add("Opening WorkItem",true);
			Log.info("Opening WorkItem");

			util.wait(time);

			cwp.getWorkItemName(workitem).click();

			Reporter.log("WorkItemDetailView is displayed",true);
			ATUReports.add("WorkItemDetailView is displayed",true);
			Log.info("WorkItemDetailView is displayed");

			util.wait(time);

			wdp.getImports_win().click();
			util.wait(time);

			Actions action = new Actions(Driver.driver); 
			action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u006D')).perform();
			action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u006D')).perform();

			util.wait(time);
			boolean addNewMediaFileWinPresent=util.verifyObjectPresentReturnsBool(wdp.getmedia_Import());
			if(addNewMediaFileWinPresent)
			{
				Reporter.log("Add new Media File window is displayed", true);
				ATUReports.add("Add new Media File window is displayed", true);
				Log.info("Add new Media File window is displayed");

				util.wait(time);
				wdp.getVideoCloseBtn().click();
				Reporter.log("Close Button is clicked.", true);
				ATUReports.add("Close Button is clicked.", true);
				Log.info("Close Button is clicked.");

				Reporter.log("Add media type import is closed.", true);
				ATUReports.add("Add media type import is closed.", true);
				Log.info("Add media type import is closed.");

				Reporter.log("Video should not get uploaded in the video tab.", true);				
				ATUReports.add("Video should not get uploaded in the video tab.", true);				
				Log.info("Video should not get uploaded in the video tab.");				

				util.waitForPageToLoad();

				ATUReports.add("Verify Add new Media File window using shortkut key", "","Add new Media File window should be displayed",
						"Add new Media File in workitem window is displayed", true);
			}
			else
			{
				Reporter.log("Add new Media file window is NOT displayed", true);
				ATUReports.add("Add new Media file window is NOT displayed", true);
				Log.info("Add new Media file window is NOT displayed");
				Assert.fail("Add new Media file window is NOT displayed");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("failed to Verify Keyboard Shortcuts For Adding Video In Workitem Test",
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Keyboard Shortcuts For Adding Video In Workitem test failed");
		}
		finally
		{
			Log.endTestCase("INFO_9676_VerifyKeyboardShortcutsForAddingVideoInWorkitemTest");
		}
	}
}