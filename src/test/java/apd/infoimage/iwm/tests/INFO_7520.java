package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
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
 * @author SumanGaK - 08-Mar-2018
 * INFO-7520
 * This test method verifies Document Duplicate of Folder type of Workitem which is retrieved from Inbox
 */
public class INFO_7520 extends SuperClassIWM{

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
	@Test(enabled = true,priority=1,groups={"DocumentDuplicate"})
	public void testVerifyFolderWorkitemRetrieveFromInboxDocumentDuplicate()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7520 is for Verifying Document Duplicate of Folder type of Workitem which is retrieved from Inbox");
		ATUReports.setAuthorInfo("Suman","MAR-2018","0.3");

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
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,11,1);		
			String dupStr=ExcelLib.getCellValue(xlpath,sheet,8,1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Class Name : VerifyFolderWorkitemRetrieveFromInboxDocumentDuplicateTest",true);
			ATUReports.add("Class Name : VerifyFolderWorkitemRetrieveFromInboxDocumentDuplicateTest",true);
			Log.info("Class Name : VerifyFolderWorkitemRetrieveFromInboxDocumentDuplicateTest");

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

			Reporter.log("Document: "+workitem+" has been created",true);
			ATUReports.add("Document: "+workitem+" has been created",true);
			Log.info("Document: "+workitem+" has been created");

			Reporter.log("Sending Workitem to Workflow",true);
			ATUReports.add("Sending Workitem to Workflow",true);
			Log.info("Sending Workitem to Workflow");

			util.wait(time);
			cwp.sendWorkItemToDefaultQueue(workitem);
			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Retrieving Workitem From Inbox",true);
			ATUReports.add("Retrieving Workitem From Inbox",true);
			Log.info("Retrieving Workitem From Inbox");

			ip.retrieveWorkItem(workitem);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItem is clicked",true);
			ATUReports.add("WorkItem is clicked",true);
			Log.info("WorkItem is clicked");

			Reporter.log("WorkItemDetailView displayed",true);
			ATUReports.add("WorkItemDetailView displayed",true);
			Log.info("WorkItemDetailView displayed");

			util.wait(time);

			wdp.getActionsDropDown().click();
			Reporter.log("Actions Drop Down is clicked", true);
			ATUReports.add("Actions Drop Down is clicked", true);
			Log.info("Actions Drop Down is clicked");

			wdp.getDocumentDuplicateOption().click();
			Reporter.log("Document Duplicate Option is clicked", true);
			ATUReports.add("Document Duplicate Option is clicked", true);
			Log.info("Document Duplicate Option is clicked");

			util.wait(time);
			util.wait(time);

			wdp.getDocumentDuplicateWorkitemNameTextBox().sendKeys(dupStr);
			Reporter.log("Workitem Duplicate Name is entered in text box",true);
			ATUReports.add("Workitem Duplicate Name is entered in text box",true);
			Log.info("Workitem Duplicate Name is entered in text box");

			util.wait(time);

			wdp.getDuplicateButton().click();
			Reporter.log("Duplicate Button is clicked",true);
			ATUReports.add("Duplicate Button is clicked",true);
			Log.info("Duplicate Button is clicked");

			util.wait(time);

			hp.getWorkItemTab().click();
			Reporter.log("Workitem tab is clicked",true);
			ATUReports.add("Workitem tab is clicked",true);
			Log.info("Workitem tab is clicked");

			util.waitForPageToLoad();
			hp.searchByNameInWorkitemTab(workitem+dupStr);
			Reporter.log("Searching By Workitem Name In Workitem tab",true);
			ATUReports.add("Searching By Workitem Name In Workitem tab",true);
			Log.info("Searching By Workitem Name In Workitem tab");

			boolean duplicateWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem+dupStr));
			if(duplicateWorkitemPresence){
				Reporter.log("Duplicate Workitem is present in grid",true);
				ATUReports.add("Duplicate Workitem is present in grid",true);
				Log.info("Duplicate Workitem is present in grid");
				ATUReports.add("Verify Document Duplicate of Folder type of Workitem which is retrieved from Inbox", "","Document Duplicate of Folder type of Workitem which is retrieved from Inbox should be displayed",
						"Document Duplicate of Folder type of Workitem which is retrieved from Inbox is displayed", true);
			}else{
				Reporter.log("Duplicate Workitem is not present in grid",true);
				ATUReports.add("Duplicate Workitem is not present in grid",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Duplicate Workitem is not present in grid ");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("Verify Folder Workitem Retrieve From Inbox Document Duplicate is failed.", true);
			ATUReports.add("Verify Folder Workitem Retrieve From Inbox Document Duplicate is failed.",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Folder Workitem Retrieve From Inbox Document Duplicate is failed.");
		}
	}
}