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
 * INFO_9675
 * This test method will add a multiple page tif file to a workitem , validate the same and Verify keyboard shortcuts for splitting of page in workitem
 */
public class INFO_9675 extends SuperClassIWM{


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
	public void testVerifyKeyboardShortcutsSplittingPageInWorkitem()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9675  is for Verifying keyboard shortcuts for splitting of page in workitem");
		ATUReports.setAuthorInfo("Suman","APR-2018","0.3"); 

		try
		{
			Log.startTestCase("INFO_9675_VerifyKeyboardShortcutsSplittingPageInWorkitemTest");

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

			Reporter.log("VerifyKeyboardShortcutsSplittingPageInWorkitemTest",true);
			ATUReports.add("VerifyKeyboardShortcutsSplittingPageInWorkitemTest",true);
			Log.info("VerifyKeyboardShortcutsSplittingPageInWorkitemTest");

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
			Reporter.log("Content Tab is clicked",true);
			ATUReports.add("Content Tab is clicked",true);
			Log.info("Content Tab is clicked");
			util.wait(time);
			wdp.getAddNewPageIcon().click();
			Reporter.log("Add New Page is clicked",true);
			ATUReports.add("Add New Page is clicked",true);
			Log.info("Add New Page is clicked");

			util.wait(time);
			boolean addNewPageWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if(addNewPageWinPresent)
			{
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				util.wait(time);				

				String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,5,1);

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
					Reporter.log("Selected multipage file name validation successful.", true);
					ATUReports.add("Selected multipage file name validation successful.", true);
					Log.info("Selected multipage file name validation successful.");
				}
				else
				{
					Reporter.log("Selected multipage file name validation failed.", true);
					ATUReports.add("Selected multipage file name validation failed.", true);
					Log.info("Selected multipage file name validation failed.");
					Assert.fail("Selected multipage file name validation failed.");
				}
				util.wait(time);
				util.wait(time);
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
					Log.info("No of Pages in the multiple page tif file is : "+contentList);

					util.waitForPageToLoad();

					util.wait(time);

					wdp.getSelectAllCheckbox().click();
					Reporter.log("Select All Checkbox is clicked",true);
					ATUReports.add("Select All Checkbox is clicked",true);
					Log.info("Select All Checkbox is clicked");

					util.wait(time);

					Actions action = new Actions(Driver.driver); 
					action.keyDown(Keys.CONTROL);
					action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u0070')).perform();

					action.keyUp(Keys.CONTROL);
					action.keyUp(Keys.ALT).sendKeys(String.valueOf('\u0070')).perform();

					boolean splitPageWindowPresent=util.verifyObjectPresentReturnsBool(wdp.getProceedButton());

					if(splitPageWindowPresent)
					{
						Reporter.log("Split workitem window is displayed", true);
						ATUReports.add("Split workitem window is displayed", true);
						Log.info("Split workitem window is displayed");
						ATUReports.add("Verify Split page in workitem using shortkut key", "","Split workitem window should be displayed",
								"Split workitem window is displayed", true);
					}
					else
					{
						Reporter.log("Split workitem window is not displayed", true);
						ATUReports.add("Split workitem window is not displayed", true);
						Log.info("Split workitem window is not displayed");
						Assert.fail("Split workitem window is not displayed");
					}

					wdp.getCancelButton().click();

				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					Reporter.log("Validation of added  TIF file is failed", true);
					ATUReports.add("Validation of added  TIF file is failed", true);
					Log.info("Validation of added  TIF file is failed");
					Assert.fail("Validation of added  TIF file is failed");
				}
			}
			else
			{
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Log.info("Add new Page window is NOT displayed");
				Assert.fail("Add new Page window is NOT displayed");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("Verify Keyboard Shortcuts Splitting Page In Workitem test is failed", true);
			ATUReports.add("Verify Keyboard Shortcuts Splitting Page In Workitem test is failed", true);
			Log.info("Verify Keyboard Shortcuts Splitting Page In Workitem test is failed");
			Assert.fail("Verify Keyboard Shortcuts Splitting Page In Workitem test is failed");
		}
		finally
		{
			Log.endTestCase("INFO_9675_VerifyKeyboardShortcutsSplittingPageInWorkitemTest");
		}
	}
}