package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
 * INFO-8777
 * This class will perform 'Auto view' functionality for a workitem from any queue by sending the workitems to default queue
 */
public class INFO_8777 extends SuperClassIWM{

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
	@Test(enabled =true,groups={"GetNext"})
	public void performAutoViewBySendingToDefaultQueue()
	{
		Log.startTestCase("INFO_8777_PerformAutoViewBySendingToDefaultQueue");
		try
		{
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8777  is for Performing 'Auto view' functionality for a workitem "
					+ "from any queue by sending the workitems to default queue");
			ATUReports.setAuthorInfo("Jayashri","APR-2018","0.3");

			//Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			
			int noOfWitems=2;
			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String[] witems=new String[noOfWitems];

			int createProgress=1;
			//Create 1 workitem and send to default queue
			for(int witemCount=0;witemCount<=noOfWitems-1;witemCount++)
			{
				String workitem = str+util.getSysDate(0, date);
				witems[witemCount]=workitem;

				String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
				String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);

				Reporter.log("Creation of Workitem",true);
				ATUReports.add("Creation of Workitem",true);
				cwp.CreateWorkitem(workitem,className,workitemType);
				Reporter.log("Sending Workitem to Workflow",true);
				ATUReports.add("Sending Workitem to Workflow",true);
				Thread.sleep(2000);
				cwp.sendWorkItemToDefaultQueue(workitem);
				Thread.sleep(2000);
				util.waitForPageToLoad();

				if(createProgress!=noOfWitems)
				{
					createProgress++;
					ip.getWorkItemsTab().click();
					Reporter.log("WorkItem tab is clicked",true);
					ATUReports.add("WorkItem tab is clicked",true);

					Thread.sleep(2000);
					util.waitForPageToLoad();
				}
			}
			ip.getNextONButton().click();
			Reporter.log("get Next Button is clicked",true);
			ATUReports.add("get Next Button is clicked",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			util.jclick(ip.getNextAutoOpenCheckbox());
			Reporter.log("get Next Auto Open Checkbox is clicked",true);
			ATUReports.add("get Next Auto Open Checkbox is clicked",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();
			
			ip.getRequiredNoOfWorkitems().clear();
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().sendKeys(String.valueOf(noOfWitems));
			Reporter.log("get Next Required No Of Workitems is sent to textbox ",true);
			ATUReports.add("get Next Required No Of Workitems is sent to textbox ",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			util.jclick(ip.getSaveButton());
			Reporter.log("SaveButton is clicked",true);
			ATUReports.add("SaveButton is clicked",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ATUReports.add(" Set the required no of workitem In Get Next","No Of workitems ="+noOfWitems,
					"No should be set to "+noOfWitems, "No is set to "+noOfWitems,true);
			
			util.jclick(ip.getDataEntry());
			Reporter.log("Data Entry is clicked",true);
			ATUReports.add("Data Entry is clicked",true);
			Thread.sleep(3000);
			util.waitForPageToLoad();

			util.jclick(ip.getFirstWorkitem());
			Thread.sleep(2000);
			util.waitForPageToLoad();

			List<WebElement> closeIcons=Driver.driver.findElements(By.xpath("//li/i[contains(@class,'icon-remove tabClose getNextWorkitemClose')]"));
			int count=closeIcons.size();

			System.out.println("no of opened workitems ="+count);
			
			for(int k=1;k<=noOfWitems;k++)
			{				
				wdp.getActionsDropDown().click();
				Reporter.log("Actions Drop Down is clicked", true);
				ATUReports.add("Actions Drop Down is clicked", true);
				Thread.sleep(2000);
				util.waitForPageToLoad();

				wdp.getSendToDefaultOption().click();
				Reporter.log("Send To Default Option is selected", true);
				ATUReports.add("Send To Default Option is selected", true);
				Thread.sleep(2000);
				util.waitForPageToLoad();
				
				Driver.driver.navigate().refresh();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				continue;

			}
			ATUReports.add("Send all opened workitems to default","","All workitems should be sent to default queue", 
					"All the opened workitems are sent to defalt queue", true);
			
			Thread.sleep(3000);
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
			Reporter.log("Get Next option is changed to off state", true);
			Thread.sleep(5000);
			util.waitForPageToLoad();
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			
			ATUReports.add("Failed to 'Auto view' functionality for a workitem from any queue by sending the workitems to default queue", 
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Failed to perform 'Auto view' functionality for a workitem from any queue by sending the workitems to default queue");
			
		}
		finally {
			Log.endTestCase("INFO_8777_PerformAutoViewBySendingToDefaultQueue");


		}
	}

}