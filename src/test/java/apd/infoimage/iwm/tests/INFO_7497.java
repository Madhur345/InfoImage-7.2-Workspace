package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;
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
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author SumanGaK  - 17-May-2018
 * INFO-7497
 * This class will Perform Document Duplicate operation with different name and different class for a document type of workitem with form fields updated and including form data
 **/
public class INFO_7497 extends SuperClassIWM{

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
	public void testVerifyDocumentWorkitemIndexFieldsFormDataIncludeFormDataDifferentNameDifferentClassDocumentDuplicate(){

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7497 is for Performing Document Duplicate operation with different name and different class for a document type of workitem with form fields updated and including form data");
		ATUReports.setAuthorInfo("Suman","MAY-2018","0.3"); 

		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_7497_VerifyDocumentWorkitemIndexFieldsFormDataIncludeFormDataDifferentNameDifferentClassDocumentDuplicateTest");
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
			String newstr=ExcelLib.getCellValue(xlpath,sheet,20,1);
			String newWorkitem = newstr+util.getSysDate(0, date);
			String idCodeStr=ExcelLib.getCellValue(xlpath,sheet,9,1);
			String invoiceNoStr=ExcelLib.getCellValue(xlpath,sheet,10,1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);


			Reporter.log("Class Name : INFO_7497_VerifyDocumentWorkitemIndexFieldsFormDataIncludeFormDataDifferentNameDifferentClassDocumentDuplicateTest",true);
			ATUReports.add("Class Name : INFO_7497_VerifyDocumentWorkitemIndexFieldsFormDataIncludeFormDataDifferentNameDifferentClassDocumentDuplicateTest",true);
			Log.info("Class Name : INFO_7497_VerifyDocumentWorkitemIndexFieldsFormDataIncludeFormDataDifferentNameDifferentClassDocumentDuplicateTest");

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
			util.wait(time);
			//ID_CODE text field
			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys(idCodeStr);
			Reporter.log("Idcode has been written",true);
			ATUReports.add("Idcode has been written",true);
			Log.info("Idcode has been written");
			util.wait(time);

			wdp.getInvoiceno_TF().clear();
			wdp.getInvoiceno_TF().sendKeys(invoiceNoStr);
			Reporter.log("Invoice Number has been written",true);
			ATUReports.add("Invoice Number has been written",true);
			Log.info("Invoice Number has been written");
			util.wait(time);

			wdp.getUpdate_btn().click();
			Reporter.log("Update Button has been Clicked",true);
			ATUReports.add("Update Button has been Clicked",true);
			Log.info("Update Button has been Clicked");
			util.waitForPageToLoad();
			util.wait(time);
			util.wait(time);

			hp.getWorkItemTab().click();

			hp.searchByNameInWorkitemTabAndDisplay(workitem);
			Reporter.log("Searching By Workitem Name And Display In Workitem tab",true);
			ATUReports.add("Searching By Workitem Name And Display In Workitem tab",true);
			Log.info("Searching By Workitem Name And Display In Workitem tab");

			boolean createdWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if(createdWorkitemPresence){
				Reporter.log("Created Workitem is present in grid",true);
				ATUReports.add("Created Workitem is present in grid",true);
				Log.info("Created Workitem is present in grid");
			}else{
				Reporter.log("Created Workitem is not present in grid",true);
				ATUReports.add("Created Workitem is not present in grid",true);
				Log.info("Created Workitem is not present in grid");
				Assert.fail("Created Workitem is not present in grid ");
			}			

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
			ATUReports.add("Actions Drop Down is clicked",true);
			Log.info("Actions Drop Down is clicked");

			wdp.getDocumentDuplicateOption().click();
			Reporter.log("Document Duplicate Option is clicked", true);
			ATUReports.add("Document Duplicate Option is clicked", true);
			Log.info("Document Duplicate Option is clicked");
			util.wait(time);
			util.wait(time);
			util.wait(time);

			wdp.getDocumentDuplicateWorkitemNameTextBox().clear();
			util.wait(time);

			wdp.getDocumentDuplicateWorkitemNameTextBox().sendKeys(newWorkitem+dupStr);
			Reporter.log("Workitem Duplicate Name is entered in text box",true);
			ATUReports.add("Workitem Duplicate Name is entered in text box",true);
			Log.info("Workitem Duplicate Name is entered in text box");
			util.wait(time);

			wdp.getDifferentClassOption().click();
			Reporter.log("Different Class Option is selected",true);
			ATUReports.add("Different Class Option is selected",true);
			Log.info("Different Class Option is selected");
			util.wait(time);

			wdp.getIncludeFormDataCheckBox().click();
			Reporter.log("Include Form Data CheckBox is checked",true);
			ATUReports.add("Include Form Data CheckBox is checked",true);
			Log.info("Include Form Data CheckBox is checked");

			wdp.getDuplicateButton().click();
			Reporter.log("Duplicate Button is clicked",true);
			ATUReports.add("Duplicate Button is clicked",true);
			Log.info("Duplicate Button is clicked");
			util.wait(time);
			util.wait(time);			

			hp.getWorkItemTab().click();
			Reporter.log("Workitem tab is clicked",true);
			ATUReports.add("Workitem tab is clicked",true);
			Log.info("Workitem tab is clicked");
			util.waitForPageToLoad();
			cwp.searchByNameInWorkitemList(newWorkitem + dupStr);
			//hp.searchByNameInWorkitemTab(newWorkitem+dupStr);
			Reporter.log("Searching By Workitem Name In Workitem tab",true);
			ATUReports.add("Searching By Workitem Name In Workitem tab",true);
			Log.info("Searching By Workitem Name In Workitem tab");

			boolean duplicateWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(newWorkitem+dupStr));
			if(duplicateWorkitemPresence){
				Reporter.log("Duplicate Workitem is present in grid",true);
				ATUReports.add("Duplicate Workitem is present in grid",true);
				Log.info("Duplicate Workitem is present in grid");
				ATUReports.add("Verify Document Workitem Index Fields Form Data Include Form Data Different Name Different Class Document Duplicate Test", "","Document Duplicate operation with different name and different class for a document type of workitem with form fields updated and including form data should be displayed",
						"Document Duplicate operation with different name and different class for a document type of workitem with form fields updated and including form data is displayed", true);
			}else{
				Reporter.log("Duplicate Workitem is not present in grid",true);
				ATUReports.add("Duplicate Workitem is not present in grid",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Log.info("Duplicate Workitem is not present in grid");
				Assert.fail("Duplicate Workitem is not present in grid ");
			}		

			hp.getSearchFieldInWorkitemTab().sendKeys(Keys.ENTER);
			util.wait(time);

			cwp.getActionsBtn().click();
			Reporter.log("Actions Button is Clicked",true);
			ATUReports.add("Actions Button is Clicked",true);
			cwp.getActionsMetaData().click();

			Reporter.log("Meta Data is Clicked",true);
			ATUReports.add("Meta Data is Clicked",true);
			String newClass=cwp.getNewClassName().getText().substring(10);

			Reporter.log("New Class Name : "+newClass,true);
			ATUReports.add("New Class Name : "+newClass,true);

			boolean newClassPresence = util.verifyObjectPresentReturnsBool(cwp.getWebElementNewClass(newClass));
			if(newClassPresence)
			{
				Reporter.log("New Class Name is in metadata screen in workitems page",true);
				ATUReports.add("New Class Name is in metadata screen in workitems page",true);			 
			}			
			else
			{
				Reporter.log("New Class Name is not in metadata screen in workitems page",true);
				ATUReports.add("New Class Name is not in metadata screen in workitems page",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("New Class Name is not in metadata screen in workitems page");
			}

			cwp.getMetaDataDialogBoxCloseBtn(newWorkitem+dupStr).click();
			Reporter.log("Meta Data Dialog Box Close Button is clicked",true);
			ATUReports.add("Meta Data Dialog Box Close Button is clicked",true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("VerifyDocumentWorkitemIndexFieldsFormDataIncludeFormDataDifferentNameDifferentClassDocumentDuplicateTest is failed.", true);
			ATUReports.add("VerifyDocumentWorkitemIndexFieldsFormDataIncludeFormDataDifferentNameDifferentClassDocumentDuplicateTest is failed.",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyDocumentWorkitemIndexFieldsFormDataIncludeFormDataDifferentNameDifferentClassDocumentDuplicateTest is failed.");
		}finally{
			Log.info("User logged out.");
			Log.endTestCase("INFO_7497_VerifyDocumentWorkitemIndexFieldsFormDataIncludeFormDataDifferentNameDifferentClassDocumentDuplicateTest");

		}
	}
	
}