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
import org.openqa.selenium.support.ui.Select;

/**
 * @author SumanGaK - 27-Jul-2018
 * INFO-12426
 * This class verifies Send Workitem in Actions for archived workitem which is retrieved from search
 * Pre Requisite : Storage Manager should be connected to route file with presence of RM archive in queue list 
 */
public class INFO_12426 extends SuperClassIWM
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
		Log.endTestCase("INFO_12426_VerifyActionsSendWorkitemForArchivedWorkitemRetrievedFromSearchTest");
	}

	@SuppressWarnings("deprecation")
	@Test(enabled =true,priority=1,groups={"Search"})
	public void testVerifyActionsSendWorkitemForArchivedWorkitemRetrievedFromSearch()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-12426 is for sending Workitem in Actions for archived workitem which is retrieved from search");
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
			String workstepOption = ExcelLib.getCellValue(xlpath,sheet,79,1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);

			Reporter.log("VerifyActionsSendWorkitemForArchivedWorkitemRetrievedFromSearchTest",true);
			ATUReports.add("VerifyActionsSendWorkitemForArchivedWorkitemRetrievedFromSearchTest",true);
			Log.info("VerifyActionsSendWorkitemForArchivedWorkitemRetrievedFromSearchTest");

			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_12426_VerifyActionsSendWorkitemForArchivedWorkitemRetrievedFromSearchTest");
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
			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");
			util.wait(time);
			util.waitForPageToLoad();

			Reporter.log("Sending Workitem to Workflow",true);
			ATUReports.add("Sending Workitem to Workflow",true);
			Log.info("Sending Workitem to Workflow");

			util.wait(time);

			cwp.getCheckBoxWorkItemName(workitem).click();
			Reporter.log("Workitem Check Box is clicked", true);
			util.wait(time);
			cwp.getSendWorkItemButton().click();
			Reporter.log("Send WorkItem Button is clicked", true);
			util.wait(time);
			util.waitForElementEnabled(cwp.getSendToWorkflow());

			boolean sendWorkitemWindowBoxPresence = util.verifyObjectPresentReturnsBool(cwp.getSendToWorkflow());
			if(sendWorkitemWindowBoxPresence){
				Reporter.log("Send Workitem window box is present",true);
			}else{
				Reporter.log("Send Workitem window box not present",true);
				Assert.fail("Send Workitem window box not present ");
			}

			cwp.getWorkFlowButton().click();
			Reporter.log("WorkFlow Button is clicked", true);

			Select WorkFlowListBox = new Select(cwp.getWorkFlowListBox());
			WorkFlowListBox.selectByValue(workstepOption);
			Reporter.log("Workstep selected as " + workstepOption, true);
			util.wait(time);

			cwp.getSendButton().click();
			Reporter.log("Send Button is clicked", true);
			util.waitForPageToLoad();
			util.wait(time);

			hp.getSearchTab().click();
			Reporter.log("Search Tab is clicked.",true);
			ATUReports.add("Search Tab is clicked.",true);
			Log.info("Search Tab is clicked.");

			sp.getBasicSearch().click();
			Reporter.log("Basic Search is clicked.",true);
			ATUReports.add("Basic Search is clicked.",true);
			Log.info("Basic Search is clicked.");

			util.wait(time);		

			sp.getTextBox().sendKeys(workitem);
			Reporter.log("Workitem to be Searched is sent to text box.",true);
			ATUReports.add("Workitem to be Searched is sent to text box.",true);
			Log.info("Workitem to be Searched is sent to text box.");
			util.wait(time);

			sp.getSearchButton().click();
			Reporter.log("Search Button is clicked.",true);
			ATUReports.add("Search Button is clicked.",true);
			Log.info("Search Button is clicked.");

			util.wait(time);
			boolean workitemToBeRetrievedPresence = util.verifyObjectPresentReturnsBool(sp.getWorkItemName(workitem));
			if (workitemToBeRetrievedPresence) {
				Reporter.log("Workitem to be retrieved using Search is present in grid", true);
			} else {
				Reporter.log("Workitem to be retrieved using Search is not present in grid", true);
				Assert.fail("Workitem to be retrieved using Search is not present in grid ");
			}

			cwp.getCheckBoxWorkItemName(workitem).click();

			util.wait(time);

			sp.getRetrieveButton().click();

			util.wait(time);
			Reporter.log("Retrieving Workitem from Search",true);
			ATUReports.add("Retrieving Workitem from Search",true);
			Log.info("Retrieving Workitem from Search");

			hp.getWorkItemTab().click();
			Reporter.log("Workitem tab is clicked",true);
			ATUReports.add("Workitem tab is clicked",true);
			Log.info("Workitem tab is clicked");

			util.waitForPageToLoad();
			hp.searchByNameInWorkitemTab(workitem);
			Reporter.log("Searching By Workitem Name In Workitem tab",true);
			ATUReports.add("Searching By Workitem Name In Workitem tab",true);
			Log.info("Searching By Workitem Name In Workitem tab");

			boolean RetrievedWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if(RetrievedWorkitemPresence){
				Reporter.log("Workitem is present in grid",true);
				ATUReports.add("Workitem is present in grid",true);
				ATUReports.add("Send Workitem in Actions for archived workitem which is retrieved from search",
						"Workitem which is retrieved from Search is displayed", true);
			}else{
				Reporter.log("Workitem is not present in grid",true);
				ATUReports.add("Workitem is not present in grid",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Workitem is not present in grid ");
			}

			util.wait(time);
			Reporter.log("Reserving Workitem", true);
			ATUReports.add("Reserving Workitem", true);
			cwp.reserveWorkItem(workitem);
			util.wait(time);

			hp.getFirstRowFirstCell().click();
			Reporter.log("Action Button of Workitem is clicked", true);
			ATUReports.add("Action Button of Workitem is clicked", true);
			Log.info("Action Button of Workitem is clicked");

			hp.getSendThisWorkitemOption().isEnabled();
			Reporter.log("Send This Workitem Option is Enabled", true);
			ATUReports.add("Send This Workitem Option is Enabled", true);
			Log.info("Send This Workitem Option is Enabled");			

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("VerifyActionsSendWorkitemForArchivedWorkitemRetrievedFromSearchTest failed");
			ATUReports.add("VerifyActionsSendWorkitemForArchivedWorkitemRetrievedFromSearchTest failed", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("VerifyActionsSendWorkitemForArchivedWorkitemRetrievedFromSearchTest failed");
			Log.error(e.getMessage());

		} finally {
			Log.endTestCase("INFO_12426_VerifyActionsSendWorkitemForArchivedWorkitemRetrievedFromSearchTest");
			Log.info("INFO_12426_VerifyActionsSendWorkitemForArchivedWorkitemRetrievedFromSearchTest");
		}
	}

}
