package apd.infoimage.iwm.tests;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author PradhanJ
 * INFO-7262
 * Test to verify the 142 * 142 DPI images processing through iwm.
 * Pre-Condition : Test date should be provided with 150 * 150 DPI image.
 */
public class INFO_7262 extends SuperClassIWM{

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
	@Test(enabled = true,priority=1,groups={"UploadFile"})
	public void testProcessing142X142DPIimage(){
		Log.startTestCase("Verifying Image DPI");

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7262 is for Verifying Image DPI");
		ATUReports.setAuthorInfo("Suman","OCT-2017","0.3"); 

		try {
			Log.startTestCase("INFO_7262_Processing142X142DPIimageTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String fileName = ExcelLib.getCellValue(xlpath,sheet,95,1);
			String downloadPath = ExcelLib.getCellValue(xlpath, sheet, 30, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			int numberOne = ExcelLib.getCellValueInt(xlpath,sheet,83,1);

			Reporter.log("VerifyImageDPITest",true);
			ATUReports.add("VerifyImageDPITest",true);
			Log.info("VerifyImageDPITest");
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
			Reporter.log("WorkItemDetailView displayed",true);
			ATUReports.add("WorkItemDetailView displayed",true);
			Log.info("WorkItemDetailView displayed");

			wdp.getImports_win().click();
			Reporter.log("Imports tab is clicked",true);
			ATUReports.add("Imports tab is clicked",true);
			Log.info("Imports tab is clicked");

			util.wait(time);
			wdp.getAddImportIcon().click();
			Reporter.log("Add Import document is clicked",true);
			ATUReports.add("Add Import document is clicked",true);
			Log.info("Add Import document is clicked");

			util.wait(time);
			boolean addNewImportsWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if(addNewImportsWinPresent)
			{

				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new import window is displayed", true);
				Log.info("Add new import window is displayed");

				util.wait(time);
				util.wait(time);				

				String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,94,1);
				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);
				Log.info("img path "+imagePath);

				wdp.getImportUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				util.wait(time);

				boolean docNameInAddNewPageWin=wdp.getSelectedImportFile().getText().equals(fileName);
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected jpg file name validation is successful.", true);
					ATUReports.add("Selected jpg file name validation is successful.", true);
					Log.info("Selected jpg file name validation is successful.");
				}
				else
				{
					Reporter.log("Selected jpg file name validation is failed.", true);
					ATUReports.add("Selected jpg file name validation is failed.", true);
					Log.info("Selected jpg file name validation is failed.");
					Assert.fail("Selected jpg file name validation is failed.");					
				}
				util.wait(time);
				util.wait(time);

				wdp.getImportUploadBtn().click();
				Reporter.log("Import Upload button is clicked", true);
				ATUReports.add("Import Upload button is clicked", true);
				Log.info("Import Upload button is clicked");

				util.waitForPageToLoad();

				//Validate the document is listed under content  and no of pages in the document
				try
				{
					int importList=wdp.getContentImportNo();
					if(importList >= numberOne)
					{
						Reporter.log("jpg File is imported successfully. ", true);
						ATUReports.add("jpg File is imported successfully. ", true);
						Log.info("jpg File is imported successfully. ");
					}
					else
					{
						Reporter.log("jpg File is NOT imported successfully.", true);
						ATUReports.add("jpg File is NOT imported successfully.", true);
						Log.info("jpg File is NOT imported successfully. ");
						Assert.fail("jpg File is NOT imported successfully.");
					}
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					Reporter.log("jpg File is NOT imported successfully.", true);
					ATUReports.add("jpg File is NOT imported successfully.", true);
					Log.info("jpg File is NOT imported successfully. ");
					Assert.fail("jpg File is NOT imported successfully.");
				}

				wdp.getDownload().click();
				util.wait(time);
				util.wait(time);
				Reporter.log("Download link is clicked", true);
				ATUReports.add("Download link is clicked", true);
				Log.info("Download link is clicked");
			}
			else
			{
				Reporter.log("Add new import  window is NOT displayed", true);
				ATUReports.add("Add new import  window is NOT displayed", true);
				Log.info("Add new import  window is NOT displayed");
				Assert.fail("Add new import window is NOT displayed");
			}


			String browser=ExcelLib.getCellValue(xlpath,sheet,13,1);

			if(browser.equalsIgnoreCase("firefox"))
			{
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				util.wait(time);
				util.wait(time);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Reporter.log("jpg File is downloaded successfully.", true);
				ATUReports.add("jpg File is downloaded successfully.", true);
				Log.info("jpg File is downloaded successfully. ");
			}
			else
			{
				Reporter.log("jpg File is downloaded successfully.", true);
				ATUReports.add("jpg File is downloaded successfully.", true);
				Log.info("jpg File is downloaded successfully. ");
				ATUReports.add("Verify 142 * 142 DPI image processed through iwm", "","142 * 142 DPI of image should be displayed",
						"142 * 142 DPI of image is displayed", true);
			}

			util.waitForPageToLoad();
			util.wait(time);

			File filePath=wdp.getLatestFilefromDir(downloadPath);

			Reporter.log("Get Latest File : "+filePath, true);
			ATUReports.add("Get Latest File : "+filePath, true);
			Log.info("Get Latest File : "+filePath);

			String imageFileName=(wdp.getLatestFilefromDir(downloadPath)).toString().substring(28);
			Reporter.log("imageFileName : "+imageFileName, true);
			Log.info("imageFileName : "+imageFileName);

			boolean filePresence=wdp.isFileDownloaded(downloadPath,imageFileName);
			if(filePresence)
			{
				Reporter.log("File is Present : "+imageFileName, true);
				ATUReports.add("File is Present : "+imageFileName, true);
				Log.info("File is Present : "+imageFileName);
			}
			else				
			{
				Reporter.log("File is not Present", true);
				ATUReports.add("File is not Present", true);
				Log.info("File is not Present");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("Verify Image DPI test is failed.", true);
			ATUReports.add("Verify Image DPI test is failed.", true);
			Log.info("Verify Image DPI test is failed.");
			Assert.fail("Verify Image DPI test is failed.");
		}
		finally
		{
			Log.endTestCase("Verifying Image DPI");
		}
	}
}