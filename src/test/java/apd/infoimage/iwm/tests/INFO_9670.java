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
 * @author SumanGaK  - 18-Apr-2018
 * INFO-9670
 * This class will Verify keyboard shortcuts for adding new page in workitem
 */
public class INFO_9670 extends SuperClassIWM{

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
	public void testVerifyKeyboardShortcutsForAddingNewPageInWorkitem()
	{
		Log.startTestCase("INFO_9670_VerifyKeyboardShortcutsForAddingNewPageInWorkitemTest");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9670  is for Verifying keyboard shortcuts for adding new page in workitem");
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

			Reporter.log("VerifyKeyboardShortcutsForAddingNewPageInWorkitemTest",true);
			ATUReports.add("VerifyKeyboardShortcutsForAddingNewPageInWorkitemTest",true);
			Log.info("VerifyKeyboardShortcutsForAddingNewPageInWorkitemTest");

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
			wdp.getContentField().click();
			Reporter.log("Content Tab is clicked",true);
			ATUReports.add("Content Tab is clicked",true);
			Log.info("Content Tab is clicked");

			util.wait(time);

			Actions action = new Actions(Driver.driver); 
			action.keyDown(Keys.CONTROL);
			action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u006E')).perform();

			action.keyUp(Keys.CONTROL);
			action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u006E')).perform();

			util.wait(time);
			util.waitForPageToLoad();

			//Check whether Adding New Page for workitem window is appearing or not
			boolean addNewPageWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if(addNewPageWinPresent)
			{
				Reporter.log("Add new Page in workitem window is displayed", true);
				ATUReports.add("Add new Page in workitem window is displayed", true);
				Log.info("Add new Page in workitem window is displayed");

				util.wait(time);

				ATUReports.add("Verify Add new Page in workitem window using shortkut key", "","Add new Page in workitem window should be displayed",
						"Add new Page in workitem window is displayed", true);
			}
			else{
				Reporter.log("Add new Page in workitem window is not present",true);
				ATUReports.add("Add new Page in workitem window is not present",true);
				Log.info("Add new Page in workitem window is not present");
				Assert.fail("Add new Page in workitem window is not present ");
			}

			util.wait(time);
			util.wait(time);
			util.wait(time);
			wdp.getCloseBtn().click();
			Reporter.log("Close Button is clicked.", true);
			ATUReports.add("Close Button is clicked.", true);
			Log.info("Close Button is clicked.");
			Reporter.log("Add new Page in workitem window is closed.", true);
			ATUReports.add("Add new Page in workitem window is closed.", true);
			Log.info("Add new Page in workitem window is closed.");

			util.waitForPageToLoad();									
			util.wait(time);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("failed to Verify keyboard shortcuts for Add new Page in workitem window",
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Keyboard Shortcuts For Add new Page in workitem window test is failed");
		}
		finally
		{
			Log.endTestCase("INFO_9670_VerifyKeyboardShortcutsForAddingNewPageInWorkitemTest");
		}
	}

}