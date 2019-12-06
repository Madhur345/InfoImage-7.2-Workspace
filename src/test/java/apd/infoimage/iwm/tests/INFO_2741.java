package apd.infoimage.iwm.tests;

import java.io.File;

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
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author PradhanJ
 * This test method will add Text type imports to a workitem and validate the same
 */
public class INFO_2741 extends SuperClassIWM {


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

	/**
	 * This test method will add Text type imports to a workitem and validate the same
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled=true,groups={"UploadFile"})
	public void testAddTextTypeImportToWorkitem()
	{
		String workitem = "Jay"+util.getSysDate(0, "yyDDMMhhmmss");
		ATUReports.setTestCaseReqCoverage("This Scenario is test Add Text Type Import To Workitem");
		ATUReports.setAuthorInfo("PradhanJ","MAR-2018","0.3"); 
		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("testAddTextTypeImportToWorkitem");
			
			cwp.CreateWorkitem(workitem,"archive","Document");
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed",true);
			ATUReports.add("WorkItemDetailView displayed", true);

			util.waitForElementEnabled(wdp.getImports_win());
			wdp.getImports_win().click();
			ATUReports.add("Imports Tab is clicked", true);

			util.waitForElementEnabled(wdp.getAddImportIcon());
			wdp.getAddImportIcon().click();
			ATUReports.add("Add Text Type File Import Button is clicked", true);
			Thread.sleep(3000);

			boolean addNewImportsWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if(addNewImportsWinPresent)
			{

				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new Media File window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();
				String imagePath = tifFilePath+"\\selenium_Sample.txt";
				Reporter.log("img path "+imagePath,true);

				wdp.getImportUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				Thread.sleep(3000);

				boolean docNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals("selenium_Sample.txt");
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected text file name validation successfull.", true);
					ATUReports.add("Selected text file name validation successful", true);
				}
				else
				{
					Reporter.log("Selected text file name validation failed.", true);
					Assert.fail("Selected text file name validation failed.");
				}
				Thread.sleep(3000);

				wdp.getImportUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				//Validate the document is listed under content  and no of pages in the document
				try
				{
					int importList=wdp.getContentImportNo();
					if(importList>=1)
					{
						Reporter.log("Text File is imported successfully. ", true);
						ATUReports.add("Text File is imported successfully. ", true);
					}
					else
					{
						Reporter.log("Text File is NOT imported successfully.", true);
						ATUReports.add("Text File is NOT imported successfully.", true);
						Assert.fail("Text File is NOT imported successfully.");
					}


				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					Reporter.log("Text File is NOT imported successfully.", true);
					ATUReports.add("Text File is NOT imported successfully.", true);
					Assert.fail("Text File is NOT imported successfully.");
				}
			}
			else
			{
				Reporter.log("Add new import  window is NOT displayed", true);
				ATUReports.add("Add new import  window is NOT displayed", true);
				Assert.fail("Add new import window is NOT displayed");
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("Importing text type file is failed.", true);
			ATUReports.add("Verify Importing text type file is failed.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing text type file is failed.");
			Log.error(e.getMessage());
		}
		finally {
			Log.endTestCase("testAddTextTypeImportToWorkitem");
		}
	}

	/**
	 * This test method will add TIF type imports to a workitem and validate the same
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled=false,groups={"UploadFile"})
	public void testAddTifTypeImportToWorkitem()
	{
		String workitem = "Jay"+util.getSysDate(0, "yyDDMMhhmmss");
		ATUReports.setTestCaseReqCoverage("This Scenario is test Add Tif Type Import To Workitem");
		ATUReports.setAuthorInfo("Suman","MAR-2018","0.3"); 
		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("testAddTifTypeImportToWorkitem");
			
			cwp.CreateWorkitem(workitem,"archive","Document");
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed",true);
			ATUReports.add("WorkItemDetailView displayed", true);

			util.waitForElementEnabled(wdp.getImports_win());
			wdp.getImports_win().click();
			ATUReports.add("Imports Tab is clicked", true);

			util.waitForElementEnabled(wdp.getAddImportIcon());
			wdp.getAddImportIcon().click();
			ATUReports.add("Add Text Type File Import Button is clicked", true);
			Thread.sleep(3000);

			boolean addNewImportsWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if(addNewImportsWinPresent)
			{
				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new Media File window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();
				String imagePath = tifFilePath+"\\singlePage.tif";
				Reporter.log("img path "+imagePath,true);

				wdp.getImportUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				Thread.sleep(3000);

				boolean docNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals("singlePage.tif");
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected TIF file name validation successfull.", true);
					ATUReports.add("Selected TIF file name validation successful", true);
				}
				else
				{
					Reporter.log("Selected TIF file name validation failed.", true);
					ATUReports.add("Selected TIF file name validation failed.", true);
					Assert.fail("Selected TIF file name validation failed.");
				}
				Thread.sleep(3000);

				wdp.getImportUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				//Validate the document is listed under content  and no of pages in the document
				try
				{
					int importList=wdp.getContentImportNo();
					if(importList>=1)
					{
						Reporter.log("TIF File is imported successfully. ", true);
						ATUReports.add("Selected TIF file  validation successful", true);
					}
					else
					{
						Reporter.log("TIF File is NOT imported successfully.", true);
						ATUReports.add("TIF File is NOT imported successfully.", true);
						Assert.fail("TIF File is NOT imported successfully.");
					}


				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					Reporter.log("TIF File is NOT imported successfully.", true);
					Assert.fail("TIF File is NOT imported successfully.");
				}
			}
			else
			{
				Reporter.log("Add new import  window is NOT displayed", true);
				ATUReports.add("Add new import  window is NOT displayed", true);
				Assert.fail("Add new import window is NOT displayed");
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("Importing TIF type file is failed.", true);
			ATUReports.add("Verify Importing TIF type file is failed.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing TIF type file is failed.");
			Log.error(e.getMessage());
		}
		
		finally {
			Log.endTestCase("testAddTifTypeImportToWorkitem");
		}
	}


	/**
	 * This test method will add PDF type imports to a workitem and validate the same
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled=false,groups={"UploadFile"})
	public void testAddPdfTypeImportToWorkitem()
	{
		String workitem = "Jay"+util.getSysDate(0, "yyDDMMhhmmss");
		ATUReports.setTestCaseReqCoverage("This Scenario is test Add Pdf Type Import To Workitem");
		ATUReports.setAuthorInfo("Suman","MAR-2018","0.3");
		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("testAddPdfTypeImportToWorkitem");
			
			cwp.CreateWorkitem(workitem,"archive","Document");
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed",true);
			ATUReports.add("WorkItemDetailView displayed", true);

			util.waitForElementEnabled(wdp.getImports_win());
			wdp.getImports_win().click();
			ATUReports.add("Imports Tab is clicked", true);

			util.waitForElementEnabled(wdp.getAddImportIcon());
			wdp.getAddImportIcon().click();
			ATUReports.add("Add Text Type File Import Button is clicked", true);
			Thread.sleep(3000);

			boolean addNewImportsWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if(addNewImportsWinPresent)
			{                                                        
				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new Media File window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();
				String imagePath = tifFilePath+"\\SampleDoc.pdf";
				Reporter.log("img path "+imagePath,true);

				wdp.getImportUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				Thread.sleep(3000);

				boolean docNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals("SampleDoc.pdf");
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected Pdf file name validation successfull.", true);
					ATUReports.add("Selected Pdf file name validation successful", true);
				}
				else
				{
					Reporter.log("Selected Pdf file name validation failed.", true);
					Assert.fail("Selected Pdf file name validation failed.");
				}
				Thread.sleep(3000);

				wdp.getImportUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				//Validate the document is listed under content  and no of pages in the document
				try
				{
					int importList=wdp.getContentImportNo();
					if(importList>=1)
					{
						Reporter.log("Pdf File is imported successfully. ", true);
						ATUReports.add("Selected Pdf file  validation successful", true);
					}
					else
					{
						Reporter.log("Pdf File is NOT imported successfully.", true);
						Assert.fail("Pdf File is NOT imported successfully.");
					}


				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					Reporter.log("Pdf File is NOT imported successfully.", true);
					Assert.fail("Pdf File is NOT imported successfully.");
				}
			}
			else
			{
				Reporter.log("Add new import  window is NOT displayed", true);
				Assert.fail("Add new import window is NOT displayed");
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("Importing Pdf type file is failed.", true);
			ATUReports.add("Verify Importing Pdf type file is failed.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing Pdf type file is failed.");
			Log.error(e.getMessage());
		}
		
		finally {
			Log.endTestCase("testAddTifTypeImportToWorkitem");
		}
	}

	/**
	 * This test method will add Excel type imports to a workitem and validate the same
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled=false,groups={"UploadFile"})
	public void testAddExcelTypeImportToWorkitem()
	{
		String workitem = "Jay"+util.getSysDate(0, "yyDDMMhhmmss");
		ATUReports.setTestCaseReqCoverage("This Scenario is test Add Excel Type Import To Workitem");
		ATUReports.setAuthorInfo("Suman","MAR-2018","0.3");
		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("testAddExcelTypeImportToWorkitem");
			
			cwp.CreateWorkitem(workitem,"archive","Document");
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed",true);
			ATUReports.add("WorkItemDetailView displayed", true);

			util.waitForElementEnabled(wdp.getImports_win());
			wdp.getImports_win().click();
			ATUReports.add("Imports Tab is clicked", true);

			util.waitForElementEnabled(wdp.getAddImportIcon());
			wdp.getAddImportIcon().click();
			ATUReports.add("Add Text Type File Import Button is clicked", true);
			Thread.sleep(3000);

			boolean addNewImportsWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if(addNewImportsWinPresent)
			{

				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new Media File window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();
				String imagePath = tifFilePath+"\\testdata.xlsx";
				Reporter.log("img path "+imagePath,true);

				wdp.getImportUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				Thread.sleep(3000);

				boolean docNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals("testdata.xlsx");
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected Excel file name validation successfull.", true);
					ATUReports.add("Selected Excel file name validation successful", true);
				}
				else
				{
					Reporter.log("Selected Excel file name validation failed.", true);
					ATUReports.add("Selected Excel file name validation failed.", true);
					Assert.fail("Selected Excel file name validation failed.");
				}
				Thread.sleep(3000);

				wdp.getImportUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				//Validate the document is listed under content  and no of pages in the document
				try
				{
					int importList=wdp.getContentImportNo();
					if(importList>=1)
					{
						Reporter.log("Excel File is imported successfully. ", true);
						ATUReports.add("Selected Excel file  validation successful", true);
					}
					else
					{
						Reporter.log("Excel File is NOT imported successfully.", true);
						ATUReports.add("Excel File is NOT imported successfully.", true);
						Assert.fail("Excel File is NOT imported successfully.");
					}


				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					Reporter.log("Excel File is NOT imported successfully.", true);
					Assert.fail("Excel File is NOT imported successfully.");
				}
			}
			else
			{
				Reporter.log("Add new import  window is NOT displayed", true);
				Assert.fail("Add new import window is NOT displayed");
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("Importing Excel type file is failed.", true);
			ATUReports.add("Verify Importing Excel type file is failed.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing Excel type file is failed.");
			Log.error(e.getMessage());
		}
		
		finally {
			Log.endTestCase("testAddExcelTypeImportToWorkitem");
		}

	}


}

