package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
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
 * @author SumanGaK  - 11-Jun-2018
 * INFO-7542
 * This class will Perform Document Duplicate operation with different class for a document type of workitem which is retrieved from Search
 **/
public class INFO_7542 extends SuperClassIWM{

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
	public void testVerifyDocumentWorkitemRetrieveFromSearchDifferentClassDocumentDuplicate(){

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7542 is for Performing Document Duplicate operation with different Class for a document type of workitem which is retrieved from Search");
		ATUReports.setAuthorInfo("Suman","JUN-2018","0.3"); 

		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_7542_VerifyDocumentWorkitemRetrieveFromSearchDifferentClassDocumentDuplicateTest");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));


			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String dupStr=ExcelLib.getCellValue(xlpath,sheet,8,1);
			int time=ExcelLib.getCellValueInt(xlpath,sheet,58,1);


			Reporter.log("Class Name : VerifyDocumentWorkitemRetrieveFromSearchDifferentClassDocumentDuplicateTest",true);
			ATUReports.add("Class Name : VerifyDocumentWorkitemRetrieveFromSearchDifferentClassDocumentDuplicateTest",true);
			Log.info("Class Name : VerifyDocumentWorkitemRetrieveFromSearchDifferentClassDocumentDuplicateTest");

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Log.info("Workitem : " + workitem);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Log.info("Class Name : " + className);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Log.info("Workitem Type : " + workitemType);

			cwp.CreateWorkitem(workitem, className, workitemType);

			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			Reporter.log("Document: " + workitem + " has been created", true);
			ATUReports.add("Document: " + workitem + " has been created", true);

			util.wait(time);

			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			cwp.sendWorkItemToDefaultQueue(workitem);

			util.waitForPageToLoad();
			util.wait(time);

			util.waitForPageToLoad();
			Reporter.log("Retrieving Workitem Using Basic Search", true);
			ATUReports.add("Retrieving Workitem Using Basic Search", true);
			Log.info("Retrieving Workitem Using Basic Search");
			sp.retrieveWorkItemFromSearch(workitem);

			util.waitForPageToLoad();			


			hp.getSearchFieldInWorkitemTab().sendKeys(Keys.ENTER);
			util.wait(time);

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
			
			wdp.getDifferentClassOption().click();
			Reporter.log("Different Class Option is selected",true);
			ATUReports.add("Different Class Option is selected",true);
			Log.info("Different Class Option is selected");

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
				ATUReports.add("Verify Document Workitem Retrieve From Search Different Class Document Duplicate Test", "","Document Duplicate of operation with different Class for a document type of workitem which is retrieved from Search should be displayed",
						"Document Duplicate operation with different Class for a document type of workitem which is retrieved from Search is displayed", true);
			}else{
				Reporter.log("Duplicate Workitem is not present in grid",true);
				ATUReports.add("Duplicate Workitem is not present in grid",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Duplicate Workitem is not present in grid ");
			}
			
			hp.getSearchFieldInWorkitemTab().sendKeys(Keys.ENTER);
			util.wait(time);

			cwp.getActionsBtn().click();
			Reporter.log("Actions Button is Clicked", true);
			ATUReports.add("Actions Button is Clicked", true);
			Log.info("Actions Button is Clicked");
			cwp.getActionsMetaData().click();

			Reporter.log("Meta Data is Clicked", true);
			ATUReports.add("Meta Data is Clicked", true);
			Log.info("Meta Data is Clicked");
			String newClass = cwp.getNewClassName().getText().substring(10);

			Reporter.log("New Class Name : " + newClass, true);
			ATUReports.add("New Class Name : " + newClass, true);
			Log.info("New Class Name : " + newClass);

			boolean newClassPresence = util.verifyObjectPresentReturnsBool(cwp.getWebElementNewClass(newClass));
			if (newClassPresence) {
				Reporter.log("New Class Name is in metadata screen in workitems page", true);
				ATUReports.add("New Class Name is in metadata screen in workitems page", true);
				ATUReports.add("Verify Document Workitem Retrieve From Search Different Class Document Duplicate",
						"",
						"Document Duplicate of Document Workitem Retrieved From Search with Different Class should be displayed",
						"Document Duplicate of Document Workitem Retrieved From Search with Different Class is displayed",
						true);
			} else {
				Reporter.log("New Class Name is not in metadata screen in workitems page", true);
				ATUReports.add("New Class Name is not in metadata screen in workitems page", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("New Class Name is not in metadata screen in workitems page");
			}

			cwp.getMetaDataDialogBoxCloseBtn(workitem + dupStr).click();
			Reporter.log("Meta Data Dialog Box Close Button is clicked", true);
			ATUReports.add("Meta Data Dialog Box Close Button is clicked", true);
			Log.info("Meta Data Dialog Box Close Button is clicked");
		}			
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("VerifyDocumentWorkitemRetrieveFromSearchDifferentClassDocumentDuplicateTest is failed.", true);
			ATUReports.add("VerifyDocumentWorkitemRetrieveFromSearchDifferentClassDocumentDuplicateTest is failed.",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyDocumentWorkitemRetrieveFromSearchDifferentClassDocumentDuplicateTest is failed.");
		}
	}
}