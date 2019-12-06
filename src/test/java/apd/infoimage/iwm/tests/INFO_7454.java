package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
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
 * @author SumanGaK - 16-May-2018
 * INFO-7454
 * This class Performs Sending Multiple workitems to User Queue and checking the same workitems in that user Queue
 */
public class INFO_7454 extends SuperClassIWM
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
	}


	@SuppressWarnings("deprecation")
	@Test(enabled =true , priority=1,groups={"Inbox"})
	public void testSendingMultipleWorkitemsToUserQueueAndChecking()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7454 is for Perform Sending Multiple workitems to User Queue and checking the same workitems in that user Queue");
		ATUReports.setAuthorInfo("Suman","MAY-2018","0.3"); 
		try{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_7454_SendingMultipleWorkitemsToUserQueueAndCheckingTest");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");
			String userQueueOption=prop.getProperty("userName");
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("SendingMultipleWorkitemsToUserQueueAndCheckingTest",true);
			ATUReports.add("SendingMultipleWorkitemsToUserQueueAndCheckingTest",true);
			Log.info("SendingMultipleWorkitemsToUserQueueAndCheckingTest");

			int wiCountVal=ExcelLib.getCellValueInt(xlpath,sheet,15,1);
			String[] workitems=new String[20];
			String sendWorkitem = null , sentWorkitem = null;

			for(int loopCounter=1;loopCounter<=wiCountVal;loopCounter++)
			{	
				String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
				String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
				String workitem = str+util.getSysDate(0, date);
				String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
				String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);				

				Reporter.log("Creation of Workitem",true);
				ATUReports.add("Creation of Workitem",true);
				Log.info("Creation of Workitem");

				cwp.CreateWorkitem(workitem,className,workitemType);
				workitems[loopCounter-1]=workitem;
			}

			//cwp.getNameSearchTextbox().clear();

			Reporter.log("Sending Workitem to Workflow",true);
			ATUReports.add("Sending Workitem to Workflow",true);
			util.wait(time);
			for(int sendLoopCounter=wiCountVal;sendLoopCounter>0;sendLoopCounter--)
			{
				sendWorkitem = workitems[sendLoopCounter-1];
				Reporter.log(sendWorkitem,true);
				util.wait(time);
				cwp.getCheckBoxWorkItemName(sendWorkitem).click();

				util.wait(time);
				cwp.getNameSearchTextbox().clear();	
				cwp.getNameSearchTextbox().sendKeys(Keys.ENTER);
			}

			Reporter.log("CheckBox of Workitems to be sent to User Queue is clicked",true);
			ATUReports.add("CheckBox of Workitems to be sent to User Queue is clicked",true);
			Log.info("CheckBox of Workitems to be sent to User Queue is clicked");

			util.wait(time);
			cwp.getSendWorkItemButton().click();
			Reporter.log("Send WorkItem Button is clicked",true);
			ATUReports.add("Send WorkItem Button is clicked",true);
			Log.info("Send WorkItem Button is clicked");

			util.wait(time);
			boolean sendWorkitemWindowBoxPresence = util.verifyObjectPresentReturnsBool(cwp.getSendToWorkflow());
			if(sendWorkitemWindowBoxPresence){
				Reporter.log("Send Workitem window box is present",true);
				ATUReports.add("Send Workitem window box is present",true);
				Log.info("Send Workitem window box is present");
			}else{
				Reporter.log("Send Workitem window box not present",true);
				Assert.fail("Send Workitem window box not present ");
			}


			cwp.getUserQueueRadioButton().click();
			Reporter.log("User Queue Radio Button is clicked",true);

			Select sel5 = new Select(cwp.getUserNameListBox());
			sel5.selectByValue(userQueueOption);
			Reporter.log("User Queue Option selected as "+userQueueOption,true);
			ATUReports.add("User Queue Option selected as "+userQueueOption,true);
			Log.info("User Queue Option selected as "+userQueueOption);

			util.wait(time);

			cwp.getSendButton().click();
			Reporter.log("Send Button is clicked",true);
			ATUReports.add("Send Button is clicked",true);
			Log.info("Send Button is clicked");

			util.waitForPageToLoad();
			util.wait(time);	

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
			Thread.sleep(5000);
			util.waitForPageToLoad();

			for(int sentLoopCounter=0;sentLoopCounter<wiCountVal;sentLoopCounter++)
			{
				sentWorkitem = workitems[sentLoopCounter];
				Reporter.log(sentWorkitem,true);
				util.wait(time);
				ip.searchByNameInMyPersonalQueue(sentWorkitem);

				util.wait(time);
				util.wait(time);

				Reporter.log("Searching Workitem By Name In Data Entry ",true);
				ATUReports.add("Searching Workitem By Name In Data Entry ",true);
				Log.info("Searching Workitem By Name In Data Entry ");

				boolean sentWorkitemPresence = util.verifyObjectPresentReturnsBool(ip.getWorkItemName(sentWorkitem));
				if(sentWorkitemPresence){
					Reporter.log("Sent Workitem is present in grid",true);
					ATUReports.add("Verify Sent workitem present in Data entry", "workitem Name: "+sentWorkitem,
							"Workitem should be present in the list", "Workitem is successfully dispalyed under Data Entry", true);
				}else{
					Reporter.log("Sent Workitem is not present in grid",true);	
					Assert.fail("Sent Workitem is not present in grid");
				}
				ip.getSearchFieldInMyPersonalQueue().clear();
				util.wait(time);
				ip.getSearchFieldInMyPersonalQueue().sendKeys(Keys.ENTER);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("SendingMultipleWorkitemsToUserQueueAndCheckingTest is failed.", true);
			ATUReports.add("SendingMultipleWorkitemsToUserQueueAndCheckingTest is failed.",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("SendingMultipleWorkitemsToUserQueueAndCheckingTest is failed.");

		}
		finally {
			Log.endTestCase("INFO_7454_SendingMultipleWorkitemsToUserQueueAndCheckingTest");
		}
	}
}