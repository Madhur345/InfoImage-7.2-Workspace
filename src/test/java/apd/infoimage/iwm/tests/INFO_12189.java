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
 * INFO-12189
 * This class sends workitem to different User Queue,Retrieve,Modify and Send Back To Initial User Queue
 */
public class INFO_12189 extends SuperClassIWM
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
		Log.endTestCase("INFO_7457_SendWorkitemToNewUserQueueRetrieveModifyFromNewUserAndSendBackToInitialUserTest");
	}

	@SuppressWarnings("deprecation")
	@Test(enabled =true,priority=1,groups={"Inbox"})
	public void testSendWorkitemToNewUserQueueRetrieveModifyFromNewUserAndSendBackToInitialUser()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-12189 is for sending workitem to different User Queue,Retrieve,Modify and Send Back To Initial User Queue");
		ATUReports.setAuthorInfo("Suman","JUL-2018","0.3"); 

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
			String initialUserQueueOption = ExcelLib.getCellValue(xlpath,sheet,90,1);
			String fileName = ExcelLib.getCellValue(xlpath,sheet,26,1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);

			Reporter.log("SendWorkitemToNewUserQueueRetrieveModifyFromNewUserAndSendBackToInitialUserTest",true);
			ATUReports.add("SendWorkitemToNewUserQueueRetrieveModifyFromNewUserAndSendBackToInitialUserTest",true);
			Log.info("SendWorkitemToNewUserQueueRetrieveModifyFromNewUserAndSendBackToInitialUserTest");

			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_7457_SendWorkitemToNewUserQueueRetrieveModifyFromNewUserAndSendBackToInitialUserTest");
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

			lp.loginToApp("userName1", "password", "domain", "role1");

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

			boolean workitemPresence = util.verifyObjectPresentReturnsBool(ip.getWorkItemName(workitem));
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
			util.wait(time);

			util.waitForPageToLoad();
			Reporter.log("Retrieving Workitem", true);
			ATUReports.add("Retrieving Workitem", true);
			Log.info("Retrieving Workitem");

			ip.retrieveWorkItem(workitem);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView is displayed",true);
			ATUReports.add("WorkItemDetailView is displayed",true);

			util.wait(time);

			wdp.getContentField().click();
			wdp.getAddNewPageIcon().click();

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if(addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				util.wait(time);
				util.wait(time);

				String contentImagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,25,1);
				Reporter.log("img path "+contentImagePath,true);
				ATUReports.add("img path "+contentImagePath,true);
				Log.info("img path "+contentImagePath);

				wdp.getContentUploadField().sendKeys(contentImagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);


				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals(fileName);
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected singlepage file name validation successful.", true);
					ATUReports.add("Selected singlepage file name validation successful.", true);
					Log.info("Selected singlepage file name validation successful.");
				} else {
					Reporter.log("Selected singlepage file name validation failed.", true);
					ATUReports.add("Selected singlepage file name validation failed.", true);
					Assert.fail("Selected singlepage file name validation failed.");
				}
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.waitForPageToLoad();
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked.", true);
				ATUReports.add("Add New Page Upload Button is clicked.", true);
				Log.info("Add New Page Upload Button is clicked.");

				util.waitForPageToLoad();

				util.wait(time);

				wdp.getWorkItemsTab().click();
				Reporter.log("WorkItems Tab is clicked",true);
				ATUReports.add("WorkItems Tab is clicked",true);
				Log.info("WorkItems Tab is clicked");

				Reporter.log("Sending WorkItem To User Queue",true);
				ATUReports.add("Sending WorkItem To User Queue",true);
				Log.info("Sending WorkItem To User Queue");
				cwp.sendWorkItemToUserQueue(workitem,initialUserQueueOption);

				util.waitForPageToLoad();
				util.wait(time);
				hp.logoutApp();

				util.wait(time);
				util.waitForPageToLoad();
				Reporter.log("Logging into initial user who created workitem",true);
				ATUReports.add("Logging into initial user who created workitem",true);
				Log.info("Logging into initial user who created workitem");

				lp.loginToApp("userName", "password", "domain", "role");

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
				ip.searchByNameInMyPersonalQueue(workitem);

				Reporter.log("Searching By Workitem Name In MyPersonal Queue",true);
				ATUReports.add("Searching By Workitem Name In MyPersonal Queue",true);
				Log.info("Searching By Workitem Name In MyPersonal Queue");

				boolean receivedWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
				if(receivedWorkitemPresence){
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
			else
			{
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Log.info("Add new Page window is NOT displayed");
				Assert.fail("Add new Page window is NOT displayed");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute SendWorkitemToNewUserQueueRetrieveModifyFromNewUserAndSendBackToInitialUserTest",true);
			ATUReports.add("failed to execute SendWorkitemToNewUserQueueRetrieveModifyFromNewUserAndSendBackToInitialUserTest",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute SendWorkitemToNewUserQueueRetrieveModifyFromNewUserAndSendBackToInitialUserTest");
		}
		finally{
			Log.endTestCase("INFO_12189_SendWorkitemToNewUserQueueRetrieveModifyFromNewUserAndSendBackToInitialUserTest");
		}
	}	   	
}
