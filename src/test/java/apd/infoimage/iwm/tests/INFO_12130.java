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
 * @author SumanGaK - 05-Sep-2018
 * INFO-12130
 * This class verifies deletion of single TIF/JPG/PNG/PDF/RTF page asynchronously under imports for Folder type of workitem
 */
public class INFO_12130 extends SuperClassIWM{

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
	@Test(enabled = true,priority=1,groups={"Imports"})
	public void testVerifyDeletionOfSinglePageUnderImportsFolderWorkitem(){
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-12130 is for Verifying deletion of single TIF/JPG/PNG/PDF/RTF page asynchronously under imports for Folder type of workitem");
		ATUReports.setAuthorInfo("Suman","SEP-2018","0.3"); 
		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_12130_VerifyDeletionOfSinglePageUnderImportsFolderWorkitemTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");			

			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String fileName = ExcelLib.getCellValue(xlpath,sheet,7,1);
			int time=ExcelLib.getCellValueInt(xlpath,sheet,58,1);
			String foldstr = ExcelLib.getCellValue(xlpath, sheet, 24, 1);
			String folderWorkitem = foldstr + util.getSysDate(0, date);
			String folderWorkitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);		


			Reporter.log("VerifyDeletionOfSinglePageUnderImportsFolderWorkitemTest",true);
			ATUReports.add("VerifyDeletionOfSinglePageUnderImportsFolderWorkitemTest",true);
			Log.info("VerifyDeletionOfSinglePageUnderImportsFolderWorkitemTest");

			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);

			Reporter.log("Workitem Type : " + folderWorkitemType, true);
			ATUReports.add("Workitem Type : " + folderWorkitemType, true);
			Log.info("Workitem Type : " + folderWorkitemType);

			cwp.CreateWorkitem(folderWorkitem, className, folderWorkitemType);
			Reporter.log("Folder: " + folderWorkitem + " has been created", true);
			ATUReports.add("Folder: " + folderWorkitem + " has been created", true);
			Log.info("Folder: " + folderWorkitem + " has been created");

			util.wait(time);

			cwp.getWorkItemName(folderWorkitem).click();
			Reporter.log("WorkItemDetailView is displayed",true);
			ATUReports.add("WorkItemDetailView is displayed",true);
			Log.info("WorkItemDetailView is displayed");
			util.wait(time);
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

				String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,21,1);
				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);
				Log.info("img path "+imagePath);
				wdp.getImportUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				util.wait(time);

				boolean docNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals(fileName);
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected tif file name validation is successful", true);
					ATUReports.add("Selected tif file name validation is successful", true);
					Log.info("Selected tif file name validation is successful");
				}
				else
				{
					Reporter.log("Selected tif file name validation is failed.", true);
					ATUReports.add("Selected tif file name validation is failed.", true);
					Assert.fail("Selected tif file name validation is failed.");
				}
				util.wait(time);

				wdp.getImportUploadBtn().click();
				Reporter.log("Import Upload Button is clicked", true);
				ATUReports.add("Import Upload Button is clicked", true);
				Log.info("Import Upload Button is clicked");
				util.waitForPageToLoad();				
			}
			else
			{
				Reporter.log("Add new import  window is NOT displayed", true);
				ATUReports.add("Add new import  window is NOT displayed", true);
				Log.info("Add new import  window is NOT displayed");
				Assert.fail("Add new import window is NOT displayed");
			}

			util.wait(time);
			util.wait(time);

			util.jclick(wdp.getFirstImportPageCheckbox());
			Reporter.log("Checkbox of import to be deleted is clicked", true);
			ATUReports.add("Checkbox of import to be deleted is clicked", true);
			Log.info("Checkbox of import to be deleted is clicked");
			util.wait(time);

			wdp.getDeleteImportIcon().click();
			Reporter.log("Deletion of the import will be done asynchronously without web page refresh", true);
			ATUReports.add("Deletion of the import will be done asynchronously without web page refresh", true);
			Log.info("Deletion of the import will be done asynchronously without web page refresh");

			util.waitForPageToLoad();
			util.wait(time);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("VerifyDeletionOfSinglePageUnderImportsFolderWorkitem Test is failed.", true);
			ATUReports.add("VerifyDeletionOfSinglePageUnderImportsFolderWorkitem Test is failed.",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyDeletionOfSinglePageUnderImportsFolderWorkitem Test is failed.");
			Log.error(e.getMessage());
		}
		finally {
			Log.endTestCase("INFO_12130_VerifyDeletionOfSinglePageUnderImportsFolderWorkitemTest");
		}
	}

}


