package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
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
 * @Zypher Id: INFO-9033
 * This Scenario will verify the default value in Get Next when Get Next is in on state
 */

public class INFO_9033 extends SuperClassIWM{

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
	@Test(enabled =true,priority=1,groups={"GetNext"})
	public void testGetNextverifyDefaultvalue()
	{
		Log.startTestCase("INFO_9033_GetNextverifyDefaultvalue");
		try
		{
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-9033  is to verify the default value in Get Next when Get Next is in on state ");
			ATUReports.setAuthorInfo("Jayashri","APR-2018","1.0");

			//Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);

			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);
			String invoiceNoStr = ExcelLib.getCellValue(xlpath, sheet, 10, 1);

			Reporter.log("Creation of Workitem",true);
			ATUReports.add("Creation of Workitem",true);
			cwp.CreateWorkitem(workitem,className,workitemType);
			Reporter.log("Sending Workitem to Workflow",true);
			ATUReports.add("Sending Workitem to Workflow",true);
			Thread.sleep(2000);
			cwp.sendWorkItemToDefaultQueue(workitem);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getNextONButton().click();
			Reporter.log("get Next Button is clicked",true);
			ATUReports.add("get Next Button is clicked",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			String defValue=ip.getRequiredNoOfWorkitems().getAttribute("value");
			System.out.println(defValue);

			if(defValue.equals("3"))
			{
				Reporter.log("Default value is 3 in Get Next when Get Next is in on state.",true);
				ATUReports.add("Verify default value of Get Next","","Default value should be 3", "Default value of Get Next is 3",true);
			}
			else
			{
				Reporter.log("Default value is not displayed as 3",true);
				Assert.fail("Default value is not displayed as 3");
			}
			
			util.waitForElementEnabled(ip.getNextOFFButton());
			Thread.sleep(5000);
			ip.getNextOFFButton().click();
			Reporter.log("get Next Button is clicked",true);
			ATUReports.add("get Next Button is clicked",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Verify default value of Get Next failed");
			ATUReports.add("Verify default value of Get Next failed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
		}
		finally {
			Log.endTestCase("INFO_9033_GetNextverifyDefaultvalue");


		}
	}

}


