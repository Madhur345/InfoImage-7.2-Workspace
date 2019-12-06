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
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author SumanGaK - 10-Apr-2018
 * INFO_9677
 * This class will add Text type imports to a workitem and validate the same
 */
public class INFO_9677 extends SuperClassIWM {

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
	public void testVerifyKeyboardShortcutsForAddingImportInWorkitem()
	{
		Log.startTestCase("INFO_9677_VerifyKeyboardShortcutsForAddingImportInWorkitemTest");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9677 is for Verifying keyboard shortcuts for adding imports to a workitem and validate the same");
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
			String fileName = ExcelLib.getCellValue(xlpath,sheet,23,1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);

			Reporter.log("VerifyKeyboardShortcutsForAddingImportInWorkitemTest",true);
			ATUReports.add("VerifyKeyboardShortcutsForAddingImportInWorkitemTest",true);
			Log.info("VerifyKeyboardShortcutsForAddingImportInWorkitemTest");

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
			action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u006E')).perform();

			action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u006E')).perform();

			util.wait(time);
			boolean addNewImportsWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if(addNewImportsWinPresent)
			{

				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new import window is displayed", true);
				Log.info("Add new import window is displayed");

				ATUReports.add("Verify Add new import using shortkut key", "","Add new import window should be displayed",
						"Add new import window is displayed", true);
				util.wait(time);				

				String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,22,1);
				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);
				Log.info("img path "+imagePath);
				wdp.getImportUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				util.wait(time);

				boolean docNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals(fileName);
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected text file name validation successful.", true);
					ATUReports.add("Selected text file name validation successful.", true);
					Log.info("Selected text file name validation successful.");
				}
				else
				{
					Reporter.log("Selected text file name validation failed.", true);
					ATUReports.add("Selected text file name validation failed.", true);
					Log.info("Selected text file name validation failed.");
				}
				util.wait(time);				
			}
			else
			{
				Reporter.log("Add new import  window is NOT displayed", true);
				ATUReports.add("Add new import  window is NOT displayed", true);
				Log.info("Add new import  window is NOT displayed");
				Assert.fail("Add new import  window is NOT displayed");
			}

			util.waitForPageToLoad();

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("VerifyKeyboardShortcutsForAddingImportInWorkitemTest is failed.", true);
			ATUReports.add("VerifyKeyboardShortcutsForAddingImportInWorkitemTest is failed.", true);
			Log.info("VerifyKeyboardShortcutsForAddingImportInWorkitemTest is failed.");
			Assert.fail("VerifyKeyboardShortcutsForAddingImportInWorkitemTest is failed.");
		}	
		finally
		{
			Log.endTestCase("INFO_9677_VerifyKeyboardShortcutsForAddingImportInWorkitemTest");
		}
	}

}