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
 * INFO-9668
 * This class will Verify keyboard shortcuts for renaming the workitem
 */
public class INFO_9668 extends SuperClassIWM{


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
	public void testVerifyKeyboardShortcutsForRenamingWorkitem()
	{
		Log.startTestCase("INFO_9668_VerifyKeyboardShortcutsForRenamingWorkitemTest");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9668  is for Verifying keyboard shortcuts for renaming the workitem");
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
			String newstr = ExcelLib.getCellValue(xlpath,sheet,20,1);	
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);

			Reporter.log("VerifyKeyboardShortcutsForRenamingWorkitemTest",true);
			ATUReports.add("VerifyKeyboardShortcutsForRenamingWorkitemTest",true);
			Log.info("VerifyKeyboardShortcutsForRenamingWorkitemTest");

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
			Reporter.log("Renaming WorkItem",true);
			ATUReports.add("Renaming WorkItem",true);
			Log.info("Renaming WorkItem");
			util.wait(time);

			cwp.getCheckBoxWorkItemName(workitem).click();
			Reporter.log("CheckBox of WorkItem is clicked",true);
			ATUReports.add("CheckBox of WorkItem is clicked",true);
			Log.info("CheckBox of WorkItem is clicked");
			util.wait(time);

			Actions action = new Actions(Driver.driver); 
			action.keyDown(Keys.CONTROL);
			action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u006E')).perform();

			action.keyUp(Keys.CONTROL);
			action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u006E')).perform();

			util.wait(time);
			util.waitForPageToLoad();

			boolean renameWinPresent=util.verifyObjectPresentReturnsBool(cwp.getRenameWorkItemWin());

			if(renameWinPresent)
			{
				Reporter.log("Rename Workitem dialog box is open",true);
				ATUReports.add("Rename Workitem dialog box is open",true);
				Log.info("Rename Workitem dialog box is open");
				ATUReports.add("Verify Rename workitem window using shortkut key", "","Rename workitem window should be displayed",
						"Rename workitem window is displayed", true);
			}
			else{
				Reporter.log("Rename Work item dialog box is not open",true);
				ATUReports.add("Rename Work item dialog box is not open",true);
				Log.info("Rename Work item dialog box is not open");
				Assert.fail("Rename Work item dialog box is not open");
			}

			util.wait(time);

			cwp.getNewWorkItemName_TF().clear();
			util.wait(time);
			cwp.getNewWorkItemName_TF().sendKeys(newstr);

			util.wait(time);
			cwp.getRenameWorkItemAcceptBtn().click();
			util.wait(time);

		}
		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("failed to Verify keyboard shortcuts for renaming the workitem",
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyKeyboardShortcutsForRenamingWorkitem test is failed");
		}
		finally
		{
			Log.endTestCase("INFO_9668_VerifyKeyboardShortcutsForRenamingWorkitemTest");
		}
	}
}