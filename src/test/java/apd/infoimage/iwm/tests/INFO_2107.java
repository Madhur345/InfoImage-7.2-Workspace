package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;


/**
 * @author SumanGaK
 * INFO-2107s
 * This class Creates ,Retrieve from Inbox ,Updates Workitem and Send To Workflow
 */
public class INFO_2107 extends SuperClassIWM 
{

	@BeforeMethod
	public void beforMethod(){
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod(){
		hp.logoutApp();
	}


	@SuppressWarnings("deprecation")
	@Test(enabled =true , priority=1,groups={"Workitem"})
	public void testRetrieveUpdateAndSendWorkItemToWorkFlow()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario is to Retrieve Update And Send WorkItem To WorkFlow Test");
		ATUReports.setAuthorInfo("Suman","MAR-2018","0.3"); 
		try{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("testRetrieveUpdateAndSendWorkItemToWorkFlow");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String noteTitle = ExcelLib.getCellValue(xlpath,sheet,9,1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);


			Reporter.log("RetrieveUpdateAndSendWorkItemToWorkFlowTest : testRetrieveUpdateAndSendWorkItemToWorkFlow()",true);
			ATUReports.add("RetrieveUpdateAndSendWorkItemToWorkFlowTest : testRetrieveUpdateAndSendWorkItemToWorkFlow()", true);
			Log.info("RetrieveUpdateAndSendWorkItemToWorkFlowTest : testRetrieveUpdateAndSendWorkItemToWorkFlow()");

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

			util.wait(time);
			cwp.sendWorkItemToDefaultQueue(workitem);
			Reporter.log("Sending Workitem to Workflow",true);
			ATUReports.add("Sending Workitem to Workflow",true);
			Log.info("Sending Workitem to Workflow");

			util.wait(time);
			util.waitForPageToLoad();

			Reporter.log("Retrieving Workitem",true);	
			ip.retrieveWorkItem(workitem);
			ATUReports.add("Retrieving Workitem",true);
			Log.info("Retrieving Workitem");

			util.wait(time);
			Reporter.log("Selection of Workitem",true);
			cwp.getWorkItemName(workitem).click();
			ATUReports.add("Selection of Workitem",true);
			Log.info("Selection of Workitem");

			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Updation of Formfield",true);
			wdp.WorkItemDetailView("","","","");
			ATUReports.add("Updation of Formfield",true);
			Log.info("Updation of Formfield");

			util.wait(time);

			util.wait(time);
			util.waitForPageToLoad();
			util.wait(time);
			util.jclick(wdp.getNotes_win());
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

			cwp.sendWorkItemToDefaultQueueInvoiceProcessing(workitem);
			util.wait(time);
			util.waitForPageToLoad();
		}
		catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute RetrieveWorkItemFromInbox test",true);
			ATUReports.add("failed to execute RetrieveWorkItemFromInbox test",true);
			ATUReports.add("execute RetrieveWorkItemFromInbox test",LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute RetrieveWorkItemFromInbox test");
			Log.error(e.getMessage());

		}
		finally {
			Log.endTestCase("testRetrieveUpdateAndSendWorkItemToWorkFlow");
		}

	}


	/**
	 * This test method Creates ,Retrieve from Inbox ,Updates FormFields of Workitem and clicks on Cancel Button
	 * @author SumanGaK
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled =true , priority=1)
	public void testUpdateFormFieldsAndCancel()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario is test Update Form Fields And Cancel");
		ATUReports.setAuthorInfo("Suman","MAR-2018","0.3"); 
		try{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("testUpdateFormFieldsAndCancel");

			Reporter.log("RetrieveUpdateAndSendWorkItemToWorkFlowTest : testUpdateFormFieldsAndCancel()",true);
			ATUReports.add("RetrieveUpdateAndSendWorkItemToWorkFlowTest : testUpdateFormFieldsAndCancel()",true);
			Log.info("RetrieveUpdateAndSendWorkItemToWorkFlowTest : testUpdateFormFieldsAndCancel()");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String workflowQueueOption = ExcelLib.getCellValue(xlpath, sheet, 88, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

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


			Reporter.log("Sending Workitem to Workflow",true);
			ATUReports.add("Sending Workitem to Workflow",true);
			Log.info("Sending Workitem to Workflow");

			util.wait(time);
			cwp.sendWorkItemToDesiredQueue(workitem,workflowQueueOption);
			util.wait(time);
			util.waitForPageToLoad();

			Reporter.log("Retrieving Workitem");
			ATUReports.add("Retrieving Workitem",true);
			Log.info("Retrieving Workitem");

			util.wait(time);
			boolean workitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (workitemPresence) {
				Reporter.log("Workitem to be Retrieved is present in grid", true);
			} else {
				Reporter.log("Workitem to be Retrieved is not present in grid", true);
				Assert.fail("Workitem to be Retrieved is not present in grid ");
			}

			cwp.getCheckBoxWorkItemName(workitem).click();

			util.wait(time);

			ip.getRetrieve().click();
			Thread.sleep(2000);
			ip.getWorkItemTab().click();
			util.waitForPageToLoad();
			boolean retrievedWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (retrievedWorkitemPresence) {
				Reporter.log("Retrieved Workitem is present in grid", true);
			} else {
				Reporter.log("Retrieved Workitem is not present in grid", true);
				Assert.fail("Retrieved Workitem is not present in grid ");
			}
			
			util.wait(time);
			Reporter.log("Selection of Workitem",true);
			ATUReports.add("Selection of Workitem",true);
			Log.info("Selection of Workitem");

			cwp.getWorkItemName(workitem).click();
			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Updation of Formfield",true);
			ATUReports.add("Updation of Formfield",true);
			Log.info("Updation of Formfield");

			wdp.getFormfields_win().click();
			Reporter.log("Form fields Tab is clicked",true);
			ATUReports.add("Form fields Tab is clicked",true);
			Log.info("Form fields Tab is clicked");

			util.wait(time);
			//ID_CODE text field
			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys("");
			Reporter.log("Idcode has been written",true);
			ATUReports.add("Idcode has been written",true);
			Log.info("Idcode has been written");

			util.wait(time);

			wdp.getInvoiceno_TF().clear();
			wdp.getInvoiceno_TF().sendKeys("");
			Reporter.log("Invoice Number has been written",true);
			ATUReports.add("Invoice Number has been written",true);
			Log.info("Invoice Number has been written");

			util.wait(time);

			wdp.getClosingWorkitemXMark().click();
			util.wait(time);

			util.waitForPageToLoad();
			util.wait(time);

			boolean workitemsPage= util.verifyObjectPresentReturnsBool(cwp.getCreateWorkitem_btn());
			if(workitemsPage)
			{
				Reporter.log("Workitems page is displayed.",true);
				ATUReports.add("Workitems page is displayed.",true);
				Log.info("Workitems page is displayed.");
			}
			else
			{
				Reporter.log("Workitems page is not displayed.",true);
				ATUReports.add("Workitems page is not displayed.",true);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute UpdateFormFieldsAndCancel test",true);
			ATUReports.add("execute UpdateFormFieldsAndCancel test",LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute UpdateFormFieldsAndCancel test");
			Log.error(e.getMessage());
		}
		finally {
			Log.endTestCase("testUpdateFormFieldsAndCancel");
		}
	}
}


