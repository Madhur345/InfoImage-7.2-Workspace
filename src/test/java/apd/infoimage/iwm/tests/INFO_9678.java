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
 * @author SumanGaK  - 23-Apr-2018
 * INFO-9678
 * This class will Verify keyboard shortcuts for deletion of import in workitem
 */
public class INFO_9678 extends SuperClassIWM{


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
	public void testVerifyKeyboardShortcutsForDeletionOfImportInWorkitem()
	{
		Log.startTestCase("INFO_9678_VerifyKeyboardShortcutsForDeletionOfImportInWorkitemTest");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9678 is for Verifying keyboard shortcuts for deletion of import in workitem");
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
			String fileName = ExcelLib.getCellValue(xlpath,sheet,7,1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);
			int numberOne = ExcelLib.getCellValueInt(xlpath,sheet,83,1);			


			Reporter.log("INFO_9678_VerifyKeyboardShortcutsForDeletionOfImportInWorkitemTest",true);
			ATUReports.add("INFO_9678_VerifyKeyboardShortcutsForDeletionOfImportInWorkitemTest",true);
			Log.info("INFO_9678_VerifyKeyboardShortcutsForDeletionOfImportInWorkitemTest");

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

			wdp.getAddImportIcon().click();
			util.wait(time);
			boolean addNewImportsWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if(addNewImportsWinPresent)
			{
				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new import window is displayed", true);
				Log.info("Add new import window is displayed");

				String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,21,1);
				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);
				Log.info("img path "+imagePath);
				wdp.getImportUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				util.wait(time);

				boolean docNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals(fileName);
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected TIF file name validation is successful.", true);
					ATUReports.add("Selected TIF file name validation is successful.", true);
					Log.info("Selected TIF file name validation is successful.");
				}
				else
				{
					Reporter.log("Selected TIF file name validation is failed.", true);
					ATUReports.add("Selected TIF file name validation is failed.", true);
					Log.info("Selected TIF file name validation is failed.");
					Assert.fail("Selected TIF file name validation is failed.");
				}
				util.wait(time);

				wdp.getImportUploadBtn().click();
				util.waitForPageToLoad();

				//Validate the document is listed under content  and no of pages in the document
				try
				{
					int importList=wdp.getContentImportNo();
					if(importList>=numberOne)
					{
						Reporter.log("TIF File is imported successfully. ", true);
						ATUReports.add("TIF File is imported successfully. ", true);
						Log.info("TIF File is imported successfully. ");
					}
					else
					{
						Reporter.log("TIF File is NOT imported successfully.", true);
						ATUReports.add("TIF File is NOT imported successfully.", true);
						Log.info("TIF File is NOT imported successfully.");
						Assert.fail("TIF File is NOT imported successfully.");
					}
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					Reporter.log("TIF File is NOT imported successfully.", true);
					ATUReports.add("TIF File is NOT imported successfully.", true);
					Log.info("TIF File is NOT imported successfully.");
					Assert.fail("TIF File is NOT imported successfully.");
				}

				util.waitForPageToLoad();

				util.wait(time);

				wdp.getSelectAllCheckboxImport().click();

				Reporter.log("Select All Checkbox For Import is clicked",true);
				ATUReports.add("Select All Checkbox For Import is clicked",true);
				Log.info("Select All Checkbox For Import is clicked");
				util.wait(time);

				Actions action = new Actions(Driver.driver); 
				action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u006C')).perform();

				action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u006C')).perform();
			}
			else
			{
				Reporter.log("Add new import  window is NOT displayed", true);
				ATUReports.add("Add new import  window is NOT displayed", true);
				Log.info("Add new import  window is NOT displayed");
				Assert.fail("Add new import window is NOT displayed");
			}

			util.waitForPageToLoad();

			util.wait(time);		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("failed to Verify Keyboard Shortcuts For Zoom In And Zoom Out For A Page Test",
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Keyboard Shortcuts For Zoom In And Zoom Out For A Page test is failed");
		}
		finally
		{
			Log.endTestCase("INFO_9678_VerifyKeyboardShortcutsForDeletionOfImportInWorkitemTest");
		}
	}
}