package apd.infoimage.iwm.tests;

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
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

/**
 * @author SumanGaK
 * INFO-7457
 * This class sends workitem With Imports And Notes to different User Queue and checks the same workitem in new user Queue
 */
public class INFO_9692 extends SuperClassIWM
{
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
		Log.info("User logged out");
		Log.endTestCase("INFO_9692_VerifyKeyboardShortcutsForAddingAndDeletingNotesInWorkitemTest");
	}

	@SuppressWarnings("deprecation")
	@Test(enabled =true,priority=1,groups={"Inbox"})
	public void testVerifyKeyboardShortcutsForAddingAndDeletingNotesInWorkitem()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7457 is for sending workitem With Imports And Notes to different User Queue and checks the same workitem in new user Queue");
		ATUReports.setAuthorInfo("Suman","JUN-2018","0.3"); 

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
			String noteTitle = ExcelLib.getCellValue(xlpath,sheet,9,1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);

			Reporter.log("VerifyKeyboardShortcutsForAddingAndDeletingNotesInWorkitemTest",true);
			ATUReports.add("VerifyKeyboardShortcutsForAddingAndDeletingNotesInWorkitemTest",true);
			Log.info("VerifyKeyboardShortcutsForAddingAndDeletingNotesInWorkitemTest");

			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_9692_VerifyKeyboardShortcutsForAddingAndDeletingNotesInWorkitemTest");
			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);

			Reporter.log("Creation of Workitem",true);
			ATUReports.add("Creation of Workitem",true);
			Log.info("Creation of Workitem");
			cwp.CreateWorkitem(workitem,className,workitemType);
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");

			util.wait(time);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView is displayed",true);
			ATUReports.add("WorkItemDetailView is displayed",true);
			Log.info("WorkItemDetailView is displayed");
			util.wait(time);

			util.wait(time);
			util.waitForPageToLoad();
			util.wait(time);
			wdp.getNotes_win().click();
			Actions action = new Actions(Driver.driver); 
			action.keyDown(Keys.CONTROL);
			action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u0061')).perform();
			// Note title
			wdp.getNoteTitle_TF().click();
			util.wait(time);
			wdp.getNoteTitle_TF().sendKeys(noteTitle);
			Reporter.log("NoteTitle: " + wdp.getNoteTitle_TF() + " has been entered", true);
			// getNotedesc_TA().click();
			util.wait(time);
			Reporter.log("text is entered into the Desc area",true);
			Log.info("text is entered into the Desc area");
			wdp.getNotedesc_TA().sendKeys(noteTitle);
			Reporter.log("Notedesc: " + wdp.getNotedesc_TA() + " has been entered", true);
			wdp.getNoteTitle_TF().click();
			util.wait(time);
			wdp.getNoteTitle_TF().sendKeys(noteTitle);
			Reporter.log("NoteTitle: " + wdp.getNoteTitle_TF() + " has been entered", true);

			// submission of the note
			wdp.getAddnote_btn().click();
			Reporter.log("Addnote: " + wdp.getAddnote_btn() + " has been Clicked", true);
			util.wait(time);

			wdp.getNote1().click();
			Reporter.log("Note1 has been Clicked", true);
			ATUReports.add("Note1 has been Clicked", true);	
			Log.info("Note1 has been Clicked");
			util.wait(time);

			action.keyDown(Keys.CONTROL);
			action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u0078')).perform();

			action.keyUp(Keys.CONTROL);
			action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u0078')).perform();

			boolean notePresence = util.verifyObjectPresentReturnsBool(wdp.getNote1());
			if(notePresence)
			{
				Reporter.log("Note is not deleted", true);
				ATUReports.add("Note is not deleted", true);						
				Log.info("Note is not deleted");
			}	        
			else
			{
				Reporter.log("Note is deleted", true);
				ATUReports.add("Note is deleted", true);						
				Log.info("Note is deleted");
			}		
			util.wait(time);
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute VerifyKeyboardShortcutsForAddingAndDeletingNotesInWorkitem test",true);
			ATUReports.add("failed to execute VerifyKeyboardShortcutsForAddingAndDeletingNotesInWorkitem test",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute VerifyKeyboardShortcutsForAddingAndDeletingNotesInWorkitem test");
		}
	}	   	
}