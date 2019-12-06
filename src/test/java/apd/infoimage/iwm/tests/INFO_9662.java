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
 * INFO-9662
 * This class Verifies keyboard shortcuts for creation of the workitem
 */
public class INFO_9662 extends SuperClassIWM{


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
	public void testVerifyKeyboardShortcutForCreateWorkitem()
	{
		System.out.println("I entered apd.infoimage.iwm.tests.INFO_9662_VerifyKeyboardShortcutForCreateWorkitemTest");	

		Log.startTestCase("INFO_9662_VerifyKeyboardShortcutForCreateWorkitemTest");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9662 is for Verifying keyboard shortcuts for creating the workitem");
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

			Reporter.log("VerifyKeyboardShortcutForCreateWorkitem",true);
			ATUReports.add("VerifyKeyboardShortcutForCreateWorkitem",true);
			Log.info("VerifyKeyboardShortcutForCreateWorkitem");

			Reporter.log("Workitem : "+workitem,true);			
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);

			util.wait(time);

			Actions action = new Actions(Driver.driver); 

			action.keyDown(Keys.CONTROL);
			action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u0063')).perform();

			action.keyUp(Keys.CONTROL);
			action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u0063')).perform();

			util.wait(time);
			util.waitForPageToLoad();

			boolean createWorkitemWindowPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkitemCreate_win());
			if(createWorkitemWindowPresence){
				Reporter.log("Create Workitem window box is present",true);
				ATUReports.add("Create Workitem window box is present",true);
				Log.info("Create Workitem window box is present");

				ATUReports.add("Verify Create workitem window using shortkut key", "","Create workitem window should be displayed",
						"Create workitem window is displayed", true);
				util.wait(time);

				Reporter.log("Creation of Workitem",true);
				ATUReports.add("Creation of Workitem",true);
				Log.info("Creation of Workitem");
				util.wait(time);

				cwp.getWorkItemname_TF().clear();
				cwp.getWorkItemname_TF().sendKeys(workitem);
				Reporter.log(" Workitem name inserted",true);
				ATUReports.add(" Workitem name inserted",true);
				Log.info(" Workitem name inserted");

				Select sel = new Select(cwp.getClassName_dd());
				sel.selectByValue(className);
				Reporter.log("ClassName selected as "+className,true);
				ATUReports.add("ClassName selected as "+className,true);
				Log.info("ClassName selected as "+className);

				util.wait(time);

				Select sel1 = new Select(cwp.getWorkitemtype_dd());
				sel1.selectByValue(workitemType);
				Reporter.log("WorkitemType selected as "+workitemType,true);
				ATUReports.add("WorkitemType selected as "+workitemType,true);
				Log.info("WorkitemType selected as "+workitemType);
				util.wait(time);
				cwp.getCreateWorkitem_submitbtn().click();
				Reporter.log("Create Workitem submit button clicked",true);
				ATUReports.add("Create Workitem submit button clicked",true);
				Log.info("Create Workitem submit button clicked");

				util.waitForPageToLoad();
			}else{
				Reporter.log("Create Workitem window box not present",true);
				ATUReports.add("Create Workitem window box not present",true);
				Log.info("Create Workitem window box not present");
				Assert.fail("Create Workitem window box not present ");
			}
			System.out.println("About to Exit apd.infoimage.iwm.tests.INFO_9662_VerifyKeyboardShortcutForCreateWorkitemTest");			

		}
		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("failed to Verify keyboard shortcuts for creating the workitem",
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyKeyboardShortcutForCreateWorkitem test is failed");
		}
		finally {
			Log.endTestCase("INFO_9662_VerifyKeyboardShortcutForCreateWorkitemTest");
		}
	}
}