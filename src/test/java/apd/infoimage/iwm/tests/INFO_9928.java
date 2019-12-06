package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
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
 * INFO-9928
 * This class verifies Document Duplicate of Document type of Workitem and uploading file through Content
 */
public class INFO_9928 extends SuperClassIWM{

	@BeforeMethod
	public void setUp() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown() {
		hp.logoutApp();
	}


	@SuppressWarnings("deprecation")
	@Test(enabled = true,priority=1,groups={"DocumentDuplicate"})
	public void testVerifyDocumentWorkitemContentDocumentDuplicate(){
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9928 is for Verifying Document Duplicate of Document type of Workitem and uploading file through Content");
		ATUReports.setAuthorInfo("Suman","MAR-2018","0.3"); 
		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_9928_VerifyDocumentWorkitemContentDocumentDuplicateTest");

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
			String dupStr=ExcelLib.getCellValue(xlpath,sheet,8,1);	
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			int numberOne = ExcelLib.getCellValueInt(xlpath,sheet,83,1);			

			Reporter.log("VerifyDocumentWorkitemContentDocumentDuplicateTest",true);
			ATUReports.add("VerifyDocumentWorkitemContentDocumentDuplicateTest",true);
			Log.info("VerifyDocumentWorkitemContentDocumentDuplicateTest");
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
					Reporter.log("Selected TIF file name validation is successful", true);
					ATUReports.add("Selected TIF file name validation is successful", true);
					Log.info("Selected TIF file name validation is successful");
				}
				else
				{
					Reporter.log("Selected TIF file name validation is failed.", true);
					ATUReports.add("Selected TIF file name validation is failed.", true);
					Log.info("Selected TIF file name validation is failed.");
					Assert.fail("Selected TIF file name validation is failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked",true);
				ATUReports.add("Add New Page Upload Button is clicked",true);
				Log.info("Add New Page Upload Button is clicked");
				util.waitForPageToLoad();

				//Validate the document is listed under content  and no of pages in the document
				try
				{
					int contentList=wdp.getContentPageNo();
					if(contentList >= numberOne)
					{
						Reporter.log("1 Page is listed under content on the side bar ", true);	
						ATUReports.add("1 Page is listed under content on the side bar ", true);
						Log.info("1 Page is listed under content on the side bar ");
					}
					else
					{
						Reporter.log("1 Page is NOT listed under content on the side bar", true);
						ATUReports.add("1 Page is NOT listed under content on the side bar", true);
						Log.info("1 Page is NOT listed under content on the side bar");
						Assert.fail("1 Page is NOT listed under content on the side bar");
					}

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
			util.wait(time);
			util.wait(time);
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
			cwp.searchByNameInWorkitemList(workitem + dupStr);
			Reporter.log("Searching By Workitem Name In Workitem tab",true);
			ATUReports.add("Searching By Workitem Name In Workitem tab",true);
			Log.info("Searching By Workitem Name In Workitem tab");

			boolean duplicateWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem+dupStr));
			if(duplicateWorkitemPresence){
				Reporter.log("Duplicate Workitem is present in grid",true);
				ATUReports.add("Duplicate Workitem is present in grid",true);
				Log.info("Duplicate Workitem is present in grid");
				ATUReports.add("Verify Document Duplicate of Document Workitem with Content", "","Document Duplicate of Document Workitem with Content should be displayed",
						"Document Duplicate of Document Workitem with Content is displayed", true);
			}else{
				Reporter.log("Duplicate Workitem is not present in grid",true);
				Log.info("Duplicate Workitem is not present in grid");
				ATUReports.add("Duplicate Workitem is not present in grid",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Duplicate Workitem is not present in grid ");
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify Document Workitem Content Document Duplicate test is failed", true);
			Log.info("Verify Document Workitem Content Document Duplicate test is failed");
			ATUReports.add("Verify Document Workitem Content Document Duplicate test is failed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Document Workitem Content Document Duplicate test is failed");		
		}		
		finally {
			Log.endTestCase("INFO_9928_VerifyDocumentWorkitemContentDocumentDuplicateTest");
		}
	}
}