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

/**
 * @author SumanGaK  - 10-May-2018
 * INFO-7526
 * This class will Verify Document Workitem , AddImports and Document Duplicate with Different Name and Different Class
 **/
@SuppressWarnings("deprecation")
public class INFO_7526 extends SuperClassIWM{

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


	@Test(enabled = true,priority=1,groups={"DocumentDuplicate"})
	public void testVerifyDocumentWorkitemAddImportsDifferentNameDifferentClassDocumentDuplicate(){

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7526 is for Performing Document Duplicate of Document Workitem , AddImports with Different Name and Different Class");
		ATUReports.setAuthorInfo("Suman","MAY-2018","0.3"); 

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
			String newstr=ExcelLib.getCellValue(xlpath,sheet,20,1);
			String newWorkitem = newstr+util.getSysDate(0, date);
			String dupStr=ExcelLib.getCellValue(xlpath,sheet,8,1);
			String fileName = ExcelLib.getCellValue(xlpath,sheet,18,1);
			String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,5,1);
			String secondImagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,28,1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			int numberOne = ExcelLib.getCellValueInt(xlpath,sheet,83,1);
			String newClassName = ExcelLib.getCellValue(xlpath,sheet,86,1);

			Reporter.log("Class Name : VerifyDocumentWorkitemAddImportsDifferentNameDifferentClassDocumentDuplicateTest",true);
			ATUReports.add("Class Name : VerifyDocumentWorkitemAddImportsDifferentNameDifferentClassDocumentDuplicateTest",true);
			Log.info("Class Name : VerifyDocumentWorkitemAddImportsDifferentNameDifferentClassDocumentDuplicateTest");

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
			Reporter.log("WorkItem is clicked",true);
			ATUReports.add("WorkItem is clicked",true);
			Log.info("WorkItem is clicked");

			Reporter.log("WorkItemDetailView displayed",true);
			ATUReports.add("WorkItemDetailView displayed",true);
			Log.info("WorkItemDetailView displayed");

			util.wait(time);			
			util.waitForPageToLoad();

			wdp.getImports_win().click();
			Reporter.log("Imports Tab is clicked",true);
			ATUReports.add("Imports Tab is clicked",true);
			Log.info("Imports Tab is clicked");
			util.wait(time);

			wdp.getAddImportIcon().click();
			Reporter.log("Add new import icon is clicked", true);
			ATUReports.add("Add new import icon is clicked", true);
			Log.info("Add new import icon is clicked");

			util.wait(time);
			boolean addNewImportsWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if(addNewImportsWinPresent)
			{
				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new import window is displayed", true);
				Log.info("Add new import window is displayed");

				util.wait(time);
				util.wait(time);

				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);
				Log.info("img path "+imagePath);

				wdp.getImportUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				util.wait(time);

				boolean docNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals(fileName);
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected tif file name validation is successful.", true);
					ATUReports.add("Selected tif file name validation is successful.", true);
					Log.info("Selected tif file name validation is successful.");
				}
				else
				{
					Reporter.log("Selected tif file name validation is failed.", true);
					ATUReports.add("Selected tif file name validation is failed.", true);
					Log.info("Selected tif file name validation is failed.");
					Assert.fail("Selected tif file name validation is failed.");
				}
				util.wait(time);

				wdp.getImportUploadBtn().click();
				Reporter.log("Import Upload Button is clicked", true);
				ATUReports.add("Import Upload Button is clicked", true);
				Log.info("Import Upload Button is clicked");
				util.waitForPageToLoad();
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);

				//Validate the document is listed under content  and no of pages in the document
				try
				{
					int importList=wdp.getContentImportNo();
					if(importList >= numberOne)
					{
						Reporter.log("Tif File is imported successfully. ", true);
						ATUReports.add("Tif File is imported successfully. ", true);
						Log.info("Tif File is imported successfully. ");
					}
					else
					{
						Reporter.log("Tif File is NOT imported successfully.", true);
						ATUReports.add("Tif File is NOT imported successfully.", true);
						Log.info("Tif File is NOT imported successfully.");
						Assert.fail("Tif File is NOT imported successfully.");
					}
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					Reporter.log("Tif File is NOT imported successfully.", true);
					ATUReports.add("Tif File is NOT imported successfully.", true);
					Log.info("Tif File is NOT imported successfully.");
					Assert.fail("Tif File is NOT imported successfully.");
				}



				util.jclick(wdp.getAddImportIcon());
				util.wait(time);
				wdp.getImportUploadField().sendKeys(secondImagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				util.wait(time);

				wdp.getImportUploadBtn().click();
				util.waitForPageToLoad();
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

				cwp.searchByNameInWorkitemList(newWorkitem+dupStr);
				Reporter.log("Searching By Workitem Name In Workitem tab",true);
				ATUReports.add("Searching By Workitem Name In Workitem tab",true);
				Log.info("Searching By Workitem Name In Workitem tab");


				boolean duplicateWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(newWorkitem+dupStr));

				if(duplicateWorkitemPresence){
					Reporter.log("Duplicate Workitem is present in grid",true);
					ATUReports.add("Duplicate Workitem is present in grid",true);
					Log.info("Duplicate Workitem is present in grid");
					ATUReports.add("Perform Document Workitem Add Imports Different Name Different Class Document Duplicate", "","Document Duplicate of Document Workitem , Add Imports with Different Name and Different Class should be displayed",
							"Document Duplicate of Document Workitem , Add Imports with Different Name and Different Class is displayed", true);
				}else{
					Reporter.log("Duplicate Workitem is not present in grid",true);
					Log.info("Duplicate Workitem is not present in grid");
					ATUReports.add("Duplicate Workitem is not present in grid",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Duplicate Workitem is not present in grid ");
				}

				hp.getSearchFieldInWorkitemTab().sendKeys(Keys.ENTER);
				util.wait(time);

				cwp.getActionsBtn().click();
				Reporter.log("Actions Button is Clicked",true);
				ATUReports.add("Actions Button is Clicked",true);
				Log.info("Actions Button is Clicked");
				cwp.getActionsMetaData().click();

				Reporter.log("Meta Data is Clicked",true);
				ATUReports.add("Meta Data is Clicked",true);
				Log.info("Meta Data is Clicked");
				String newClass=cwp.getNewClassName().getText().substring(10);

				Reporter.log("New Class Name : "+newClass,true);
				ATUReports.add("New Class Name : "+newClass,true);
				Log.info("New Class Name : "+newClass);

				boolean newClassNamePresence = util.verifyObjectPresentReturnsBool(cwp.getWebElementNewClass(newClassName));
				if(newClassNamePresence)
				{
					Reporter.log("New Class Name is in metadata screen in workitems page",true);
					ATUReports.add("New Class Name is in metadata screen in workitems page",true);			 
					Log.info("New Class Name is in metadata screen in workitems page");
				}			
				else
				{
					Reporter.log("New Class Name is not in metadata screen in workitems page",true);
					Log.info("New Class Name is not in metadata screen in workitems page");
					ATUReports.add("New Class Name is not in metadata screen in workitems page",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("New Class Name is not in metadata screen in workitems page");
				}

				cwp.getMetaDataDialogBoxCloseBtn(newWorkitem+dupStr).click();
				Reporter.log("Meta Data Dialog Box Close Button is clicked",true);
				ATUReports.add("Meta Data Dialog Box Close Button is clicked",true);
				Log.info("Meta Data Dialog Box Close Button is clicked");
			}


			else
			{
				Reporter.log("Add new import  window is NOT displayed", true);
				ATUReports.add("Add new import  window is NOT displayed", true);
				Log.info("Add new import  window is NOT displayed");
				Assert.fail("Add new import window is NOT displayed");
			}
		}

		catch(Exception e)
		{
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("VerifyDocumentWorkitemAddImportsDifferentNameDifferentClassDocumentDuplicateTest is failed.", true);
			ATUReports.add("VerifyDocumentWorkitemAddImportsDifferentNameDifferentClassDocumentDuplicateTest is failed.",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("VerifyDocumentWorkitemAddImportsDifferentNameDifferentClassDocumentDuplicateTest is failed.");
			Assert.fail("VerifyDocumentWorkitemAddImportsDifferentNameDifferentClassDocumentDuplicateTest is failed.");
		}
		finally {
			Log.endTestCase("INFO_7526_VerifyDocumentWorkitemAddImportsDifferentNameDifferentClassDocumentDuplicateTest");
		}
	}
	
}