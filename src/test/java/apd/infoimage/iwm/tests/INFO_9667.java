package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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
 * @author SumanGaK  - 13-Apr-2018
 * INFO-9667
 * This class Verifies keyboard shortcuts for filing of the workitem
 */
public class INFO_9667 extends SuperClassIWM{


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
	public void testVerifyKeyboardShortcutForFilingWorkitem()
	{
		Log.startTestCase("INFO_9667_VerifyKeyboardShortcutForFilingWorkitemTest");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9667  is for Verifying keyboard shortcuts for filing of the workitem");
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
			String folderWorkitemType = ExcelLib.getCellValue(xlpath,sheet,11,1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);

			Reporter.log("Filing Workitem",true);
			ATUReports.add("Filing Workitem",true);
			Log.info("Filing Workitem");

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

			cwp.CreateWorkitem(workitem,className,folderWorkitemType);
			Reporter.log("Create Folder Workitem operation performed",true);
			ATUReports.add("Create Folder Workitem operation performed",true);
			Log.info("Create Folder Workitem operation performed");

			cwp.getCheckBoxWorkItem(workitem).click();
			Reporter.log("Clicking checkbox of WorkItem",true);
			ATUReports.add("Clicking checkbox of WorkItem",true);
			Log.info("Clicking checkbox of WorkItem");
			util.wait(time);

			Reporter.log("Clicking Filing WorkItem Button through key board shortcut",true);
			ATUReports.add("Clicking Filing WorkItem Button through key board shortcut",true);
			Log.info("Clicking Filing WorkItem Button through key board shortcut");
			util.wait(time);

			Actions action = new Actions(Driver.driver); 
			action.keyDown(Keys.CONTROL);
			action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u0066')).perform();

			action.keyUp(Keys.CONTROL);
			action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u0066')).perform();

			util.wait(time);
			util.waitForPageToLoad();

			Reporter.log("Searching For Filing Workitem window In Workitem tab",true);
			ATUReports.add("Searching For Filing Workitem window In Workitem tab",true);
			Log.info("Searching For Filing Workitem window In Workitem tab");

			boolean filingWorkitemWindowPresence = util.verifyObjectPresentReturnsBool(cwp.getFileWorkItem_win());
			if(filingWorkitemWindowPresence){
				Reporter.log("Filing Workitem window is present in grid",true);
				ATUReports.add("Filing Workitem window is present in grid",true);
				Log.info("Filing Workitem window is present in grid");

				ATUReports.add("Verify Filing workitem window using shortkut key", "","Filing workitem window should be displayed",
						"Filing workitem window is displayed", true);
			}else{
				Reporter.log("Filing Workitem window is not present in grid",true);
				ATUReports.add("Filing Workitem window is not present in grid",true);
				Log.info("Filing Workitem window is not present in grid");
				Assert.fail("Filing Workitem window is not present in grid");
			}

			Select sel = new Select(cwp.getSelectFolderDropDown());
			sel.selectByVisibleText(workitem);
			Reporter.log("Selected destination folder as: "+workitem,true);
			ATUReports.add("Selected destination folder as: "+workitem,true);
			Log.info("Selected destination folder as: "+workitem);

			util.wait(time);
			cwp.getFileWorkitemAcceptBtn().click();
			util.waitForPageToLoad();
			util.wait(time);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("failed to Verify keyboard shortcuts for filing the workitem",
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyKeyboardShortcutForFilingWorkitem test is failed");
		}
		finally
		{
			Log.endTestCase("INFO_9667_VerifyKeyboardShortcutForFilingWorkitemTest");
		}
	}
	
}