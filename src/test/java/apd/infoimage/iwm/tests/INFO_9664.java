package apd.infoimage.iwm.tests;

import java.awt.AWTException;
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
 * INFO-9664
 * This test method Verifies keyboard shortcuts for sending the workitem
 */
public class INFO_9664 extends SuperClassIWM{


	@BeforeMethod
	public void setUp(){
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown() throws AWTException{
		hp.logoutApp();
	}


	@SuppressWarnings("deprecation")
	@Test(enabled=true,priority=1,groups={"KeyboardShortcut"})
	public void testVerifyKeyboardShortcutsForSendingWorkitem()
	{
		Log.startTestCase("INFO_9664_VerifyKeyboardShortcutsForSendingWorkitemTest");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9664 is for Verifying keyboard shortcuts for sending the workitem");
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

			Reporter.log("VerifyKeyboardShortcutsForSendingWorkitemTest",true);
			ATUReports.add("VerifyKeyboardShortcutsForSendingWorkitemTest",true);
			Log.info("VerifyKeyboardShortcutsForSendingWorkitemTest");

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

			cwp.getCheckBoxWorkItem(workitem).click();
			Reporter.log("Clicking checkbox of WorkItem",true);
			ATUReports.add("Clicking checkbox of WorkItem",true);
			Log.info("Clicking checkbox of WorkItem");
			util.wait(time);

			Reporter.log("Sending WorkItem",true);
			ATUReports.add("Sending WorkItem",true);
			Log.info("Sending WorkItem");
			util.wait(time);

			Actions action = new Actions(Driver.driver); 
			action.keyDown(Keys.CONTROL);
			action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u0073')).perform();

			action.keyUp(Keys.CONTROL);
			action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u0073')).perform();

			util.wait(time);
			util.waitForPageToLoad();

			boolean sendWorkitemWindowPresence = util.verifyObjectPresentReturnsBool(cwp.getSendToWorkflow());
			if(sendWorkitemWindowPresence){
				Reporter.log("Send Workitem window box is present",true);
				ATUReports.add("Send Workitem window box is present",true);
				Log.info("Send Workitem window box is present");
				ATUReports.add("Verify Send workitem window using shortkut key", "","Send workitem window should be displayed",
						"Send workitem window is displayed", true);
			}else{
				Reporter.log("Send Workitem window box not present",true);
				ATUReports.add("Send Workitem window box not present",true);
				Log.info("Send Workitem window box not present");
				Assert.fail("Send Workitem window box not present ");
			}

			cwp.getSendButton().click();
			Reporter.log("Send Button is clicked",true);
			ATUReports.add("Send Button is clicked",true);
			Log.info("Send Button is clicked");	

			util.wait(time);
			util.waitForPageToLoad();
			util.wait(time);		

		}
		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("failed to Verify keyboard shortcuts for sending the workitem",
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyKeyboardShortcutsForSendingWorkitem test is failed");
		}
		finally {
			Log.endTestCase("INFO_9664_VerifyKeyboardShortcutsForSendingWorkitemTest");
		}
	}
}