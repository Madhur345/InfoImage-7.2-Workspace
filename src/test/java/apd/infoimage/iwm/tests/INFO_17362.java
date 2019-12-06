package apd.infoimage.iwm.tests;

import java.io.File;
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
 * @author PradhanJ INFO-17362 Test to verify the mail functionality in the IWM
 *         details view viewer
 */
public class INFO_17362 extends SuperClassIWM {
	@BeforeMethod
	public void beforMethod() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();

		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod() {
		hp.logoutApp();
	}

	@SuppressWarnings("deprecation")
	@Test(enabled = true)
	public void testVerifyMailFunctionalityInIWMDetailsViewer() {

		ATUReports.setTestCaseReqCoverage("This Scenario is to verify the mail functionality in the IWM"
				+ "details view viewer");
		ATUReports.setAuthorInfo("PradhanJ","NOV-2018","0.3"); 
		try {
			Log.startTestCase("INFO_17362_TestToVerifyMailFunctionalityInIWMDetailsViewer");
			DOMConfigurator.configure("log4j.xml");
				
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");
			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
						
			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);

			Reporter.log("Creation of Workitem",true);
			ATUReports.add("Creation of Workitem",true);
			Log.info("Creation of Workitem");
			cwp.CreateWorkitem(workitem,className,workitemType);
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");
			
			Reporter.log("Selection of Workitem",true);
			ATUReports.add("Selection of Workitem",true);
			Log.info("Selection of Workitem");
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
				Thread.sleep(10000);
				//Validate the document is listed under import  and no of pages in the document
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
			
			Driver.driver.switchTo().frame(wdp.getImportDisplayFrame());
			util.jclick(wdp.getEmailButton());
			Reporter.log("Email button is clicked", true);
			ATUReports.add("Email button is clicked", true);
			
			Driver.driver.switchTo().defaultContent();
			util.waitForElementEnabled(wdp.getWitemDetailsErrMsg());
			boolean errMsg=wdp.getWitemDetailsErrMsg().getText()
					.contains("Currently this feature is only supported in Internet Explorer.");
			
			if(errMsg)
			{
				Reporter.log("While running in chrome browser email functionality says"
						+ "\n Currently this feature is only supported in Internet Explorer.", true);
				ATUReports.add("While running in chrome browser email functionality says"
						+ "\n Currently this feature is only supported in Internet Explorer.", true);
			}
			else
			{
				Reporter.log("Verify email functionality in workitem details viewer test failed", true);
				ATUReports.add("Verify email functionality in workitem details viewer test failed", true);
				Assert.fail("Verify email functionality in workitem details viewer test failed");
			}
			
		}

		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
		} finally {

			Log.endTestCase("INFO_17362_TestToVerifyMailFunctionalityInIWMDetailsViewer");
		}
	}

}
