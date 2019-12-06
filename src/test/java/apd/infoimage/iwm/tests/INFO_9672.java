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
 * @author SumanGaK  - 19-Apr-2018
 * INFO-9672
 * This class will Verify keyboard shortcuts for deleting a page in workitem
 */
public class INFO_9672 extends SuperClassIWM{


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
	public void testVerifyKeyboardShortcutsForDeletingPageInWorkitem()
	{
		Log.startTestCase("INFO_9672_VerifyKeyboardShortcutsForDeletingPageInWorkitemTest");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9672 is for Verifying keyboard shortcuts for deleting a page in workitem");
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

			Reporter.log("VerifyKeyboardShortcutsForDeletingPageInWorkitemTest",true);
			ATUReports.add("VerifyKeyboardShortcutsForDeletingPageInWorkitemTest",true);
			Log.info("VerifyKeyboardShortcutsForDeletingPageInWorkitemTest");

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
			Reporter.log("WorkItemDetailView displayed",true);
			ATUReports.add("WorkItemDetailView displayed",true);
			Log.info("WorkItemDetailView displayed");

			util.wait(time);
			wdp.getContentField().click();
			util.wait(time);
			wdp.getAddNewPageIcon().click();
			util.wait(time);
			boolean addNewPageWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if(addNewPageWinPresent)
			{
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");
				util.wait(time);				

				String imagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath,sheet,21,1);
				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);
				Log.info("img path "+imagePath);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);
				//Validate document name in the add new page window				

				boolean docNameInAddNewPageWin=wdp.getSelectedFile().getText().equals(fileName);
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected TIF file name validation is successful", true);
					ATUReports.add("Selected TIF file name validation is successful", true);
					Log.info("Selected TIF file name validation is successful");
				}
				else
				{
					Reporter.log("Selected TIF file name validation is failed.", true);
					ATUReports.add("Selected TIF file name validation is failed.", true);
					Log.info("Selected TIF file name validation is failed.");
					Assert.fail("Selected TIF file name validation is failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked",true);
				ATUReports.add("Add New Page Upload Button is clicked",true);
				Log.info("Add New Page Upload Button is clicked");

				util.waitForPageToLoad();

				wdp.getSelectPageCheckbox().click();

				Reporter.log("Select Page Checkbox is clicked",true);
				ATUReports.add("Select Page Checkbox is clicked",true);
				Log.info("Select Page Checkbox is clicked");

				util.wait(time);

				Actions action = new Actions(Driver.driver); 
				action.keyDown(Keys.CONTROL);
				action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u0064')).perform();

				action.keyUp(Keys.CONTROL);
				action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u0064')).perform();

				util.wait(time);
				util.waitForPageToLoad();

				//Check whether New Page Added for workitem is deleted or not
				boolean addedNewPagePresent=util.verifyObjectPresentReturnsBool(wdp.getAddedNewPagePresence());

				if(!addedNewPagePresent)
				{
					Reporter.log("Added new Page in workitem is deleted", true);
					ATUReports.add("Added new Page in workitem is deleted", true);
					Log.info("Added new Page in workitem is deleted");

					util.wait(time);

					ATUReports.add("Verify Deletion of Added new Page in workitem using shortkut key", "","Added new Page in workitem should be deleted",
							"Added new Page in workitem is deleted", true);
				}
				else{
					Reporter.log("Added new Page in workitem is not deleted",true);
					ATUReports.add("Added new Page in workitem is not deleted",true);
					Log.info("Added new Page in workitem is not deleted");

					Assert.fail("Added new Page in workitem is not deleted");
				}

				util.waitForPageToLoad();									
				util.wait(time);			
			}
			else{
				Reporter.log("Add new Page in workitem window is not present",true);
				ATUReports.add("Add new Page in workitem window is not present",true);
				Log.info("Add new Page in workitem window is not present");

				Assert.fail("Add new Page in workitem window is not present ");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("failed to Verify keyboard shortcuts for Deletion of Added new Page in workitem",
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify keyboard shortcuts for Deletion of Added new Page in workitem test is failed");
		}
		finally
		{
			Log.endTestCase("INFO_9672_VerifyKeyboardShortcutsForDeletingPageInWorkitemTest");
		}
	}
}