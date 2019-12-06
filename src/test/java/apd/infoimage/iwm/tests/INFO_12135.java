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
 * @author SumanGaK - 04-Sep-2018
 * INFO-12135
 * This class verifies deletion of multiple TIF/JPG/PNG/PDF/RTF pages asynchronously under imports when one import is open for folder type of workitem
 */
public class INFO_12135 extends SuperClassIWM{

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
	public void testVerifyDeletionOfMultiplePagesUnderImportsWhenOneImportIsOpenForFolderWorkitem(){
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-12135 is for Verifying deletion of Multiple TIF/JPG/PNG/PDF/RTF pages asynchronously under imports when one import is open for folder type of workitem");
		ATUReports.setAuthorInfo("Suman","SEP-2018","0.3"); 
		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_12135_VerifyDeletionOfMultiplePagesUnderImportsWhenOneImportIsOpenForFolderWorkitemTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");


			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String fileName = ExcelLib.getCellValue(xlpath,sheet,7,1);
			String secondFileName = ExcelLib.getCellValue(xlpath,sheet,26,1);			
			int time=ExcelLib.getCellValueInt(xlpath,sheet,58,1);
			String foldstr = ExcelLib.getCellValue(xlpath, sheet, 24, 1);
			String folderWorkitem = foldstr + util.getSysDate(0, date);
			String folderWorkitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);	
			String thirdFileName = ExcelLib.getCellValue(xlpath, sheet, 14, 1);


			Reporter.log("VerifyDeletionOfMultiplePagesUnderImportsWhenOneImportIsOpenForFolderWorkitemTest",true);
			ATUReports.add("VerifyDeletionOfMultiplePagesUnderImportsWhenOneImportIsOpenForFolderWorkitemTest",true);
			Log.info("VerifyDeletionOfMultiplePagesUnderImportsWhenOneImportIsOpenForFolderWorkitemTest");


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


				util.jclick(wdp.getAddImportIcon());
				Reporter.log("Add new second import icon is clicked", true);
				ATUReports.add("Add new second import icon is clicked", true);
				Log.info("Add new second import icon is clicked");
				util.wait(time);
				boolean addNewSecondImportsWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
				if(addNewSecondImportsWinPresent)
				{

					Reporter.log("Add new import window is displayed", true);
					ATUReports.add("Add new import window is displayed", true);
					Log.info("Add new import window is displayed");
					util.wait(time);
					util.wait(time);

					String secondImagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,25,1);
					Reporter.log("img path "+secondImagePath,true);
					ATUReports.add("img path "+secondImagePath,true);
					Log.info("img path "+secondImagePath);
					wdp.getImportUploadField().sendKeys(secondImagePath);
					util.waitForElementEnabled(wdp.getSelectedImportFile());
					util.wait(time);

					boolean secondDocNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals(secondFileName);
					if(secondDocNameInAddNewPageWin)
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

					util.jclick(wdp.getAddImportIcon());
					Reporter.log("Add new third import icon is clicked", true);
					ATUReports.add("Add new third import icon is clicked", true);
					Log.info("Add new third import icon is clicked");
					util.wait(time);
					boolean addNewThirdImportsWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
					if(addNewThirdImportsWinPresent)
					{

						Reporter.log("Add new import window is displayed", true);
						ATUReports.add("Add new import window is displayed", true);
						Log.info("Add new import window is displayed");
						util.wait(time);
						util.wait(time);

						String thirdImagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,12,1);
						Reporter.log("img path "+thirdImagePath,true);
						ATUReports.add("img path "+thirdImagePath,true);
						Log.info("img path "+thirdImagePath);
						wdp.getImportUploadField().sendKeys(thirdImagePath);
						util.waitForElementEnabled(wdp.getSelectedImportFile());
						util.wait(time);

						boolean thirdDocNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals(thirdFileName);
						if(thirdDocNameInAddNewPageWin)
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
						Reporter.log("Add new Third import  window is NOT displayed", true);
						ATUReports.add("Add new Third import  window is NOT displayed", true);
						Log.info("Add new Third import  window is NOT displayed");
						Assert.fail("Add new Third import window is NOT displayed");
					}				
				}
				else
				{
					Reporter.log("Add new Second import  window is NOT displayed", true);
					ATUReports.add("Add new Second import  window is NOT displayed", true);
					Log.info("Add new Second import  window is NOT displayed");
					Assert.fail("Add new Second import window is NOT displayed");
				}				
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

			util.jclick(wdp.getSecondImportPageCheckbox());

			util.wait(time);

			util.jclick(wdp.getThirdImportPageCheckbox());
			Reporter.log("Checkbox of imports to be deleted is clicked", true);
			ATUReports.add("Checkbox of imports to be deleted is clicked", true);
			Log.info("Checkbox of imports to be deleted is clicked");
			util.wait(time);

			wdp.getDeleteImportIcon().click();
			Reporter.log("Deletion of the import will be done asynchronously without web page refresh and opened import will be displayed to the user", true);
			ATUReports.add("Deletion of the import will be done asynchronously without web page refresh and opened import will be displayed to the user", true);
			Log.info("Deletion of the import will be done asynchronously without web page refresh and opened import will be displayed to the user");

			util.waitForPageToLoad();
			util.wait(time);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("VerifyDeletionOfMultiplePagesUnderImportsWhenOneImportIsOpenForFolderWorkitemTest Test is failed.", true);
			ATUReports.add("VerifyDeletionOfMultiplePagesUnderImportsWhenOneImportIsOpenForFolderWorkitemTest Test is failed.",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyDeletionOfMultiplePagesUnderImportsWhenOneImportIsOpenForFolderWorkitemTest Test is failed.");
			Log.error(e.getMessage());
		}
		finally {
			Log.endTestCase("INFO_12135_VerifyDeletionOfMultiplePagesUnderImportsWhenOneImportIsOpenForFolderWorkitemTest");
		}
	}	
}

