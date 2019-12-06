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
 * INFO-8790
 * This class will check Performing 'Get Next' functionality for multiple workitems in any queue for multiple times
 */
public class INFO_8790 extends SuperClassIWM{

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
	public void testGetNextInAnyQueueForMultipleTimes()
	{
		Log.startTestCase("INFO_8790_GetNextInAnyQueueForMultipleTimes");
		try
		{
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8790  is for Performing 'Get Next' functionality for multiple "
					+ "workitems in any queue for multiple times");
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
			int noOfWitems=3;
			
			String[] witems=new String[noOfWitems];

			int createProgress=1;
			//Create 1 workitem and send to default queue
			for(int witemCount=0;witemCount<=noOfWitems-1;witemCount++)
			{
				String workitem1 = str+util.getSysDate(0, date);
				witems[witemCount]=workitem1;

				String className1 = ExcelLib.getCellValue(xlpath,sheet,3,1);
				String workitemType1 = ExcelLib.getCellValue(xlpath,sheet,4,1);

				Reporter.log("Creation of Workitem",true);
				ATUReports.add("Creation of Workitem",true);
				cwp.CreateWorkitem(workitem1,className1,workitemType1);
				Reporter.log("Sending Workitem to Workflow",true);
				ATUReports.add("Sending Workitem to Workflow",true);
				Thread.sleep(2000);
				cwp.sendWorkItemToDefaultQueue(workitem1);
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

			Thread.sleep(5000);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().clear();
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().sendKeys("3");
			Reporter.log("get Next Required No Of Workitems is sent to textbox ",true);
			ATUReports.add("get Next Required No Of Workitems is sent to textbox ",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getSaveButton().click();
			Reporter.log("SaveButton is clicked",true);
			ATUReports.add("SaveButton is clicked",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ATUReports.add(" Set the required no of workitem In Get Next to 5","No Of workitems ="+5,
					"No should be set to 5", "No is set to 5",true);

			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked",true);
			ATUReports.add("Data Entry is clicked",true);
			Thread.sleep(3000);
			util.waitForPageToLoad();

			Reporter.log("Verify 5 workitems are dispalyed in data entry",true);
			ATUReports.add("Verify 5 workitems are dispalyed in data entry",true);

			List<WebElement> witemsInDataEntry=Driver.driver.findElements(By.xpath("//font[contains(@class,'WorkitemName')]"));
			int witemsCount=witemsInDataEntry.size();
			if(witemsCount==3)
			{
				Reporter.log("No of Workitems are  Data entry is verified successfully", true);
				ATUReports.add("Verify 5 workitems are in data entry", "No Of workitems ="+witemsCount, 
						"5 workitems should be in Data Entry", "5 Workitems are in Data entry", true);
			}
			else
			{
				Reporter.log("No of workitems in Data Entry doesn't match with the count",true);
				Assert.fail("No of workitems in Data Entry doesn't match with the count");
			}

			Reporter.log("Navigate to workitems tab and validate those workitems are not in workitems list", true);	
			ATUReports.add("Navigate to workitems tab and validate those workitems are not in workitems list", true);	
			hp.getWorkItemTab().click();
			util.waitForElementEnabled(cwp.getNameSearchTextbox());
			Thread.sleep(2000);

			Reporter.log("Search for any workitems in workitem list", true);
			try
			{
				String workitemNameToSearch=witems[2];
				cwp.searchByNameInWorkitemList(workitemNameToSearch);
				boolean witemPresent=util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitemNameToSearch));

				if(witemPresent==true)
				{
					Reporter.log("Workitem is retrieved by performing GetNext ", true);
					ATUReports.add("Workitem is retrieved by performing GetNext which is not expected", 
							LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
					
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
					
					Assert.fail("Workitem is retrieved by performing GetNext which is not expected");
				}
			}
			catch(Exception e)
			{
				Reporter.log("Workitems are not retrieved to workitem list ", true);
				ATUReports.add("Verify none of the workitems are retrived to workitem list","",
						"None of the workitems should be present in the workitemlist", "workitems are not present in workitem list",true);
			}
			
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
		catch (Exception e1) {			
			ATUReports.add("Failed to Perform 'Get Next' functionality for multiple workitems in any queue for multiple times",
					LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Failed to perform 'Get Next' functionality for multiple workitems in any queue for multiple times"+e1.getMessage());

		}
		
		finally {
			Log.endTestCase("INFO_8790_GetNextInAnyQueueForMultipleTimes");


		}
	}

}
