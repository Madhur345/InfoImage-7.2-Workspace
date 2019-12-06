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
 * @author SumanGaK  - 13-Jun-2018
 * INFO-9610
 * This class will Verify keyboard shortcuts for Export Of Images in workitem
 */
public class INFO_9610 extends SuperClassIWM{


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
	public void testVerifyExportImagesUsingKeyboardShortcuts()
	{
		Log.startTestCase("INFO_9610_VerifyExportImagesUsingKeyboardShortcutsTest");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9610 is for Verifying Export Images Using Keyboard Shortcuts");
		ATUReports.setAuthorInfo("Suman","JUN-2018","0.3"); 

		try
		{
			Log.startTestCase("INFO_9610_VerifyExportImagesUsingKeyboardShortcutsTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String fileName = ExcelLib.getCellValue(xlpath,sheet,18,1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);


			Reporter.log("INFO_9610_VerifyExportImagesUsingKeyboardShortcutsTest",true);
			ATUReports.add("INFO_9610_VerifyExportImagesUsingKeyboardShortcutsTest",true);
			Log.info("INFO_9610_VerifyExportImagesUsingKeyboardShortcutsTest");

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
			Reporter.log("WorkItemDetailView is displayed",true);
			ATUReports.add("WorkItemDetailView is displayed",true);
			Log.info("WorkItemDetailView is displayed");
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

				Thread.sleep(5000);				

				String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,5,1);
				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);
				//Validate document name in the add new page window				

				boolean docNameInAddNewPageWin=wdp.getSelectedFile().getText().equals(fileName);
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected multipage file name validation successful.", true);
					ATUReports.add("Selected multipage file name validation successful.", true);
					Log.info("Selected multipage file name validation successful.");
				}
				else
				{
					Reporter.log("Selected multipage file name validation failed.", true);
					ATUReports.add("Selected multipage file name validation failed.", true);
					Assert.fail("Selected multipage file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				util.waitForPageToLoad();

				//Validate the document is listed under content  and no of pages in the document
				try
				{
					int contentList=wdp.getContentPageNo();

					Reporter.log("No of Pages in the multiple page tif file is : "+contentList, true);
					ATUReports.add("No of Pages in the multiple page tif file is : "+contentList, true);

					util.waitForPageToLoad();

					util.wait(time);

					wdp.getSelectAllCheckbox().click();
					Reporter.log("Select All Checkbox is clicked",true);
					ATUReports.add("Select All Checkbox is clicked",true);
					Log.info("Select All Checkbox is clicked");
					util.wait(time);
					util.wait(time);

					Actions action = new Actions(Driver.driver); 
					action.keyDown(Keys.CONTROL);
					action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u0065')).perform();

					action.keyUp(Keys.CONTROL);
					action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u0065')).perform();

					util.wait(time);
					util.wait(time);
					wdp.getExportDownloadLink().click();

					//Check  whether  Export  Window  is  Present  or  not
					boolean exportWindowPresent=util.verifyObjectPresentReturnsBool(wdp.getExportDownloadLink());

					if(!exportWindowPresent)
					{
						Reporter.log("Export window is displayed", true);
						ATUReports.add("Export window is displayed", true);						
						Log.info("Export window is displayed");						
						util.wait(time);

						ATUReports.add("Verify Export Of Images in workitem using shortkut key", "","Export window should be displayed",
								"Export window is displayed", true);
					}
					else{
						Reporter.log("Export window is not displayed",true);
						ATUReports.add("Export window is not displayed",true);
						Log.info("Export window is not displayed");
						Assert.fail("Export window is not displayed");
					}

					util.waitForPageToLoad();									
					util.wait(time);			
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					Reporter.log("Validation of added  TIF file is failed", true);
					ATUReports.add("Validation of added  TIF file is failed", true);
					Assert.fail("Validation of added  TIF file is failed");
				}
			}
			else{
				Reporter.log("Add new Page in workitem window is not present",true);
				ATUReports.add("Add new Page in workitem window is not present",true);
				Assert.fail("Add new Page in workitem window is not present ");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("failed to Verify keyboard shortcuts for Export Of Images in workitem",
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify keyboard shortcuts for Export Of Images in workitem test is failed");
		}
		finally
		{
			Log.endTestCase("INFO_9610_VerifyExportImagesUsingKeyboardShortcutsTest");
		}
	}
}