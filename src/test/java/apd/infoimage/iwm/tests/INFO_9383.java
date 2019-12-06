package apd.infoimage.iwm.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
 * @author PradhanJ
 * INFO-9383
 * To verify the message in the header after uploading mp4
 */
public class INFO_9383 extends SuperClassIWM{
	
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
	@Test(enabled =true,groups={"UploadFile"})
	public void testVerifySuccessMsgAfterUpload() throws FileNotFoundException, IOException
	{
		Log.startTestCase("INFO_9383_VerifySuccessMsgAfterUpload");
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9383  is To verify the message in the header after uploading mp4");					
		ATUReports.setAuthorInfo("Jayashri","APR-2018","0.3");
		
		prop = new Properties();
		prop.load(new FileInputStream("src/main/resources/userData.properties"));

		String sheet = prop.getProperty("sheet");
		String xlpath = prop.getProperty("xlpath");
		
		String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
		String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
		String workitem = str+util.getSysDate(0, date);
		String currentClass = ExcelLib.getCellValue(xlpath,sheet,3,1);
		String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);			
		int time=3000;
		try
		{
		
			Reporter.log("Workitem : "+workitem,true);			
			ATUReports.add("Workitem : "+workitem,true);			
			Reporter.log("Class Name : "+currentClass,true);
			ATUReports.add("Class Name : "+currentClass,true);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			
			Reporter.log("Creation of Workitem",true);
			ATUReports.add("Creation of Workitem",true);
			cwp.CreateWorkitem(workitem,currentClass,workitemType);
			
			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed",true);
			ATUReports.add("WorkItemDetailView displayed", true);
			
			util.waitForElementEnabled(wdp.getImports_win());			
			wdp.getImports_win().click();
			Reporter.log("Imports Tab is clicked",true);
			ATUReports.add("Imports Tab is clicked", true);
			
			util.waitForElementEnabled(wdp.getmedia_Import());
			wdp.getmedia_Import().click();
			Reporter.log("Add Media Type File Import Button is clicked",true);
			ATUReports.add("Add Media Type File Import Button is clicked", true);

			boolean addNewMediaFileWinPresent=util.verifyObjectPresentReturnsBool(wdp.getmedia_Import());
			if(addNewMediaFileWinPresent)
			{
				Reporter.log("Add new Media File window is displayed", true);
				ATUReports.add("Add new Media File window is displayed", true);

				String path = new File("src\\test\\resources").getAbsolutePath();
				String vidPath = path+"\\SampleOneMBFile.mp4";
				
				Reporter.log("vid path "+vidPath,true);
								
				wdp.getVideoUploadField().sendKeys(vidPath);
				util.waitForPageToLoad();
				util.wait(time);
				Reporter.log("mp4 file is uploaded ", true);
				ATUReports.add("mp4 file is uploaded ", true);

				boolean docNameInAddNewMediaFileWin=util.verifyObjectPresentReturnsBool(wdp.getImportedFile());
				if(docNameInAddNewMediaFileWin)
				{
					Reporter.log("Selected media file name validation successful", true);
					ATUReports.add("Selected media file name validation successful", true);
				}
				else
				{
					Reporter.log("Selected media file name validation failed.", true);
					ATUReports.add("Selected media file name validation failed.", LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));

					Assert.fail("Selected media file name validation failed.");
				}
								
			}
			else
			{
				Reporter.log("Add new Media file window is NOT displayed", true);
				ATUReports.add("Add new Media file window is NOT displayed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Add new Media file window is NOT displayed");
			}
		}
		catch (Exception e) {
			
			ATUReports.add("mp4 file upload test failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("mp4 file upload test failed "+ e.getMessage());
			
		}
		finally {
			Log.endTestCase("INFO_9383_VerifySuccessMsgAfterUpload");


		}
	}
}
