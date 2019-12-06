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
 * INFO-12188
 * This class sends workitem to New User Queue and checks the same workitem in My Personal Queue Of New User
 */
public class INFO_12188 extends SuperClassIWM
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
		Log.endTestCase("INFO_12188_SendWorkitemToNewUserQueueCheckWorkitemInMyPersonalQueueOfNewUserTest");
	}

	@SuppressWarnings("deprecation")
	@Test(enabled =true,priority=1,groups={"Inbox"})
	public void testSendWorkitemToNewUserQueueCheckWorkitemInMyPersonalQueueOfNewUser()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-12188 is for sending workitem to New User Queue and checks the same workitem in My Personal Queue Of New User");
		ATUReports.setAuthorInfo("Suman","AUG-2018","0.3"); 

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
			String userQueueOption = prop.getProperty("userName");
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);

			Reporter.log("SendWorkitemToNewUserQueueCheckWorkitemInMyPersonalQueueOfNewUserTest",true);
			ATUReports.add("SendWorkitemToNewUserQueueCheckWorkitemInMyPersonalQueueOfNewUserTest",true);
			Log.info("SendWorkitemToNewUserQueueCheckWorkitemInMyPersonalQueueOfNewUserTest");

			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_12188_SendWorkitemToNewUserQueueCheckWorkitemInMyPersonalQueueOfNewUserTest");
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
			/*hp.logoutApp();

			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Logging into another user to whom workitem was sent",true);
			ATUReports.add("Logging into another user to whom workitem was sent",true);
			Log.info("Logging into another user to whom workitem was sent");

			lp.loginToApp("userName1", "password1", "domain1", "role1");

			util.wait(time);
			util.waitForPageToLoad();*/

			util.jclick(hp.getInbox());
			util.waitForPageToLoad();
			Reporter.log("Inbox tab is clicked",true);
			ATUReports.add("Inbox tab is clicked",true);
			Log.info("Inbox tab is clicked");

			util.wait(time);
			util.wait(time);
			util.jclick(ip.getMyPersonalQueue());			

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
			Reporter.log("failed to execute SendWorkitemToNewUserQueueCheckWorkitemInMyPersonalQueueOfNewUser test",true);
			ATUReports.add("failed to execute SendWorkitemToNewUserQueueCheckWorkitemInMyPersonalQueueOfNewUser test",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute SendWorkitemToNewUserQueueCheckWorkitemInMyPersonalQueueOfNewUser test");
		}finally {
			Log.endTestCase("INFO_12188_SendWorkitemToNewUserQueueCheckWorkitemInMyPersonalQueueOfNewUserTest");
		}
	}	   	
}


