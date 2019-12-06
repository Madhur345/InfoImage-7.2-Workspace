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
 * Zypher Id :INFO-9034
 * Following class will verify 'send to default' option for the workitems when Get Next is in on state
 */

public class INFO_9034 extends SuperClassIWM{
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
	public void testVerifySendToDefaultWhenGetNextInOnState()
	{
		Log.startTestCase("INFO_9034_GetNextverifySendToDefaultOption");
		try
		{
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-9034 To verify 'send to default' option for the workitems when Get Next is in on state");
			ATUReports.setAuthorInfo("Jayashri","APR-2018","0.3");
			
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
			cwp.CreateWorkitem(workitem,className,workitemType);
			Reporter.log("Sending Workitem to Workflow",true);
			ATUReports.add("Sending Workitem to Workflow",true);

			Thread.sleep(2000);
			cwp.sendWorkItemToDefaultQueue(workitem);
			Thread.sleep(2000);
			util.waitForPageToLoad();
			
			ip.getNextONButton().click();
			ATUReports.add("Verify Get Next option is in On state", "", 
					"Get Next option should be in On state", "Get Next option is changed to On state", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();
			
			if (ip.getNextAutoOpenCheckbox().isSelected()) {
				
			}
			else
			{
			ip.getNextAutoOpenCheckbox().click();
			Reporter.log("get Next Auto Open Checkbox is clicked",true);
			ATUReports.add("get Next Auto Open Checkbox is clicked",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();
			}

			ip.getRequiredNoOfWorkitems().clear();
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().sendKeys("10");
			Reporter.log("get Next Required No Of Workitems is sent to textbox",true);
			ATUReports.add("get Next Required No Of Workitems is sent to textbox",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getSaveButton().click();
			Reporter.log("SaveButton is clicked",true);
			ATUReports.add("SaveButton is clicked",true);

			Thread.sleep(2000);
			util.waitForPageToLoad();
			
			ATUReports.add(" Set the required no of workitem In Get Next to 10","No Of workitems ="+10,
					"No should be set to 10", "No is set to 10",true);
			
			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked",true);
			ATUReports.add("Data Entry is clicked",true);
			Thread.sleep(3000);
			util.waitForPageToLoad();

			Reporter.log("Click the first workitem", true);
			ATUReports.add("Click the first workitem", true);
			ip.getFirstWorkitem().click();
			Thread.sleep(2000);
			util.waitForPageToLoad();

			Thread.sleep(2000);
			util.waitForPageToLoad();
			wdp.getActionsDropDown().click();
			Reporter.log("Actions Drop Down is clicked", true);
			ATUReports.add("Actions Drop Down is clicked", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			if(wdp.getSendToDefaultOption().isDisplayed())
			{
			Reporter.log("Send To Default Option is dispalyed", true);
			ATUReports.add("Send to default option is dispalyed when Get Next option is On", "",
					"Send to default option should be present", "Send to default option is displayed ",true);
			}
			else
			{
				Assert.fail("Send To Default Option is Not dispalyed");
			}
			
			hp.getInbox().click();
			util.waitForPageToLoad();
			util.wait(10000);
			ip.getRequiredNoOfWorkitems().clear();
			ATUReports.add("get Next Required No Of Workitems is cleared",true);
			Reporter.log("get Next Required No Of Workitems is cleared",true);
			Thread.sleep(3000);
			
			ip.getNextAutoOpenCheckbox().click();
			Reporter.log("get Next Auto Open Checkbox is clicked",true);
			ATUReports.add("get Next Auto Open Checkbox is clicked",true);
			Thread.sleep(3000);
			
			ip.getSaveButton().click();
			Reporter.log("SaveButton is clicked",true);
			ATUReports.add("SaveButton is clicked",true);
			Thread.sleep(3000);
			
			ip.getNextOFFButton().click();
			ATUReports.add("Get Next option is changed to off state", true);
			Thread.sleep(5000);
			util.waitForPageToLoad();
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed to execute Verify send to default option when get next is in On state");
			ATUReports.add("Failed to execute Verify send to default option when get next is in On state", 
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			
		}
		finally {
			Log.endTestCase("INFO_9034_GetNextverifySendToDefaultOption");


		}
	}

}

