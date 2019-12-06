package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
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
 * @author SumanGaK - 25-Jan-2018
 * INFO_4993
 * This class will verify the Upload Functionality With Page Refresh for Video File in IWM.
 */
public class INFO_4993 extends SuperClassIWM{

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
	@Test(enabled=true,priority=1,groups={"UploadFile"})
	public void testUploadFunctionalityWithPageRefresh()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-4993  is To verify" + "Verify Upload Functionality With Page Refresh");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try
		{
			Log.startTestCase("INFO_4993_VerifyUploadFunctionalityWithPageRefreshTest");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			String vidPath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,32,1);

			Reporter.log("VerifyUploadFunctionalityWithPageRefreshTest : testUploadFunctionalityWithPageRefresh()",true);
			ATUReports.add("VerifyUploadFunctionalityWithPageRefreshTest : testUploadFunctionalityWithPageRefresh()",true);
			Log.info("VerifyUploadFunctionalityWithPageRefreshTest : testUploadFunctionalityWithPageRefresh()");

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
			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView is displayed", true);
			ATUReports.add("WorkItemDetailView is displayed", true);
			Log.info("WorkItemDetailView is displayed");

			wdp.getImportsItem().click();
			Reporter.log("Imports Tab is clicked",true);
			ATUReports.add("Imports Tab is clicked",true);
			Log.info("Imports Tab is clicked");

			wdp.getmedia_Import().click();
			Reporter.log("Add Media Type File Import Button is clicked",true);
			ATUReports.add("Add Media Type File Import Button is clicked",true);
			Log.info("Add Media Type File Import Button is clicked");

			boolean addNewMediaFileWinPresent=util.verifyObjectPresentReturnsBool(wdp.getmedia_Import());
			if(addNewMediaFileWinPresent)
			{
				Reporter.log("Add new Media File window is displayed", true);
				ATUReports.add("Add new Media File window is displayed", true);
				Log.info("Add new Media File window is displayed");

				Reporter.log("vid path "+vidPath,true);
				ATUReports.add("vid path "+vidPath,true);
				Log.info("vid path "+vidPath);

				wdp.getVideoUploadField().sendKeys(vidPath);
				util.waitForPageToLoad();
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				Driver.driver.navigate().refresh();

				wdp.isElementPresent(wdp.getAddImportIcon());

				if(wdp.isElementPresent(wdp.getAddImportIcon()))
				{
					Reporter.log("Application is not continuing upload of video", true);
					ATUReports.add("Application is not continuing upload of video",true);
					ATUReports.add("Performs Verify Upload Functionality With Page Refresh", "","Verify Upload Functionality With Page Refresh should be Done",
							"Verify Upload Functionality With Page Refresh is Done", true);
				}
				else{
					Reporter.log("Application is continuing upload of video",true);
				}

				util.wait(time);
				util.waitForPageToLoad();	
			}
			else
			{
				Reporter.log("Add new Media file window is NOT displayed", true);
				ATUReports.add("Add new Media file window is NOT displayed", true);
				Assert.fail("Add new Media file window is NOT displayed");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify the Upload Functionality With Page Refresh test failed", true);
			ATUReports.add("Verify the Upload Functionality With Page Refresh",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify the Upload Functionality With Page Refresh test failed");
		}
		finally
		{
			Log.endTestCase("INFO_4993_VerifyUploadFunctionalityWithPageRefreshTest");
		}
	}
}

