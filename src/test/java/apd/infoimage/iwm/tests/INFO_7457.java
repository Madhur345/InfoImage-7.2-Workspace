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

/**
 * @author SumanGaK
 * INFO-7457
 * This class sends workitem With Imports And Notes to different User Queue and checks the same workitem in new user Queue
 */
public class INFO_7457 extends SuperClassIWM
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
		Log.endTestCase("INFO_7457_SendWorkitemWithImportsAndNotesToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueueTest");
	}

	@SuppressWarnings("deprecation")
	@Test(enabled =true,priority=1,groups={"Inbox"})
	public void testSendWorkitemWithImportsAndNotesToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueue()
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
			String userQueueOption = ExcelLib.getCellValue(xlpath,sheet,19,1);
			String fileName = ExcelLib.getCellValue(xlpath,sheet,18,1);
			String noteTitle = ExcelLib.getCellValue(xlpath,sheet,9,1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);

			Reporter.log("SendWorkitemWithImportsAndNotesToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueueTest",true);
			ATUReports.add("SendWorkitemWithImportsAndNotesToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueueTest",true);
			Log.info("SendWorkitemWithImportsAndNotesToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueueTest");

			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_7457_SendWorkitemWithImportsAndNotesToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueueTest");
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
			util.wait(time);
			wdp.getImports_win().click();
			Reporter.log("Imports Tab is clicked",true);
			ATUReports.add("Imports Tab is clicked",true);
			util.wait(time);
			wdp.getAddImportIcon().click();
			Reporter.log("Add new import icon is clicked", true);
			ATUReports.add("Add new import icon is clicked", true);
			util.wait(time);
			boolean addNewImportsWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if(addNewImportsWinPresent)
			{

				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new import window is displayed", true);
				Thread.sleep(5000);

				String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,5,1);
				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);
				wdp.getImportUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				util.wait(time);

				boolean docNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals(fileName);
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected tif file name validation is successful", true);
					ATUReports.add("Selected tif file name validation is successful", true);
				}
				else
				{
					Reporter.log("Selected tif file name validation is failed.", true);
					ATUReports.add("Selected tif file name validation is failed.", true);
					Assert.fail("Selected tif file name validation is failed.");
				}
				util.wait(time);

				wdp.getImportUploadBtn().click();
				Reporter.log("Import Upload Button is clicked", true);
				ATUReports.add("Import Upload Button is clicked", true);
				util.waitForPageToLoad();

				//Validate the document is listed under content  and no of pages in the document
				try
				{
					int importList=wdp.getContentImportNo();
					if(importList>=1)
					{
						Reporter.log("Tif File is imported successfully. ", true);
						ATUReports.add("Tif File is imported successfully. ", true);
					}
					else
					{
						Reporter.log("Tif File is NOT imported successfully.", true);
						ATUReports.add("Tif File is NOT imported successfully", true);
						Assert.fail("Tif File is NOT imported successfully.");
					}
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					Reporter.log("Tif File is NOT imported successfully.", true);
					ATUReports.add("Tif File is NOT imported successfully.", true);
					Assert.fail("Tif File is NOT imported successfully.");
				}
			}
			else
			{
				Reporter.log("Add new import  window is NOT displayed", true);
				ATUReports.add("Add new import  window is NOT displayed", true);
				Assert.fail("Add new import window is NOT displayed");
			}
		
			util.wait(time);
			util.waitForPageToLoad();
			util.wait(time);
			wdp.getNotes_win().click();
			wdp.getAddNotes_btn().click();
			Reporter.log("Notes_win: " + wdp.getNotes_win() + " has been Clicked", true);
			// Note title
			wdp.getNoteTitle_TF().click();
			util.wait(time);
			wdp.getNoteTitle_TF().sendKeys(noteTitle);
			Reporter.log("NoteTitle: " + wdp.getNoteTitle_TF() + " has been entered", true);
			// getNotedesc_TA().click();
			util.wait(time);
			Reporter.log("text is entered into the Desc area",true);
			wdp.getNotedesc_TA().sendKeys(noteTitle);
			Reporter.log("Notedesc: " + wdp.getNotedesc_TA() + " has been entered", true);
			// submission of the note
			wdp.getAddnote_btn().click();
			Reporter.log("Addnote: " + wdp.getAddnote_btn() + " has been Clicked", true);
			util.wait(time);
			
			wdp.getWorkItemsTab().click();
			Reporter.log("WorkItems Tab is clicked",true);
			ATUReports.add("WorkItems Tab is clicked",true);
			Log.info("WorkItems Tab is clicked");
			
			Reporter.log("Sending WorkItem To User Queue",true);
			ATUReports.add("Sending WorkItem To User Queue",true);
			Log.info("Sending WorkItem To User Queue");
			cwp.sendWorkItemToUserQueue(workitem,userQueueOption);

			util.waitForPageToLoad();
			util.wait(time);
			hp.logoutApp();

			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Logging into another user to whom workitem was sent",true);
			ATUReports.add("Logging into another user to whom workitem was sent",true);
			Log.info("Logging into another user to whom workitem was sent");

			lp.loginToApp("userName1", "password1", "domain1", "role1");

			util.wait(time);
			util.waitForPageToLoad();

			hp.getInbox().click();
			Reporter.log("Inbox tab is clicked",true);
			ATUReports.add("Inbox tab is clicked",true);
			Log.info("Inbox tab is clicked");

			util.wait(time);
                        util.wait(time);
			ip.getMyPersonalQueue().click();			

			Reporter.log("My Personal Queue link is clicked",true);
			ATUReports.add("My Personal Queue link is clicked",true);
			Log.info("My Personal Queue link is clicked");
			util.wait(time);
                        util.wait(time);
			ip.searchByNameInMyPersonalQueue(workitem);

			Reporter.log("Searching By Workitem Name In MyPersonal Queue",true);
			ATUReports.add("Searching By Workitem Name In MyPersonal Queue",true);
			Log.info("Searching By Workitem Name In MyPersonal Queue");

			boolean workitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if(workitemPresence){
				Reporter.log("Workitem is present in grid",true);
				ATUReports.add("Workitem is present in grid",true);
				Log.info("Workitem is present in grid");
				ATUReports.add("Verify retrieved workitem to different User Queue and checks the same workitem in new user Queue", "","Sent workitem in the new user Queue should be displayed",
						"Sent workitem in the new user Queue is displayed", true);
			}else{
				Reporter.log("Workitem is not present in grid",true);
				Log.info("Workitem is not present in grid");
				ATUReports.add("Workitem is not present in grid",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Workitem is not present in grid");
			}					 	 
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute SendWorkitemWithImportsAndNotesToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueue test",true);
			ATUReports.add("failed to execute SendWorkitemWithImportsAndNotesToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueue test",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute SendWorkitemWithImportsAndNotesToDifferentUserQueueAndCheckingSameWorkitemInThatUserQueue test");
		}
	}	   	
}