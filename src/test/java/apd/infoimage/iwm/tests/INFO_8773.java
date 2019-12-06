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
 * @Zypher Id :INFO-8773
 * This class will verify 'Auto view' functionality for a workitem from any queue without opening any workitem
 *
 */
public class INFO_8773 extends SuperClassIWM{

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
	public void testVerifyAutoViewWithoutOpeningWorkitem()
	{ Log.startTestCase("INFO_8773_VerifyAutoViewWithoutOpeningWorkitem");
		try
		{
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8773  is for verifying"
					+ " 'Auto view' functionality for a workitem from any queue without opening any workitem");
			ATUReports.setAuthorInfo("Jayashri","APR-2018","0.3");

			//Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			
			int noOfWitems=1;
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

			ip.getNextAutoOpenCheckbox().click();
			Reporter.log("get Next Auto Open Checkbox is clicked",true);
			ATUReports.add("get Next Auto Open Checkbox is clicked",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().clear();
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().sendKeys(String.valueOf(noOfWitems));
			Reporter.log("get Next Required No Of Workitems is sent to textbox",true);
			ATUReports.add("get Next Required No Of Workitems is sent to textbox ",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getSaveButton().click();
			Reporter.log("SaveButton is clicked",true);
			ATUReports.add("SaveButton is clicked",true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ATUReports.add(" Set the required no of workitem In Get Next to "+noOfWitems,"No Of workitems ="+noOfWitems,
					"No should be set to "+noOfWitems, "No is set to "+noOfWitems,true);
			
			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked",true);
			ATUReports.add("Data Entry is clicked", true);
			Thread.sleep(3000);
			util.waitForPageToLoad();
			
			Reporter.log("Verify "+noOfWitems+"  workitems are dispalyed in data entry",true);
			List<WebElement> witemsInDataEntry=Driver.driver.findElements(By.xpath("//font[contains(@class,'WorkitemName')]"));
			int witemsCount=witemsInDataEntry.size();
			if(witemsCount==noOfWitems)
			{
				Reporter.log("No of Workitems are  Data entry is verified successfully", true);
				ATUReports.add("Verify "+noOfWitems+" workitems are in data entry", "No Of workitems ="+noOfWitems, 
						noOfWitems+" workitems should be in Data Entry", witemsCount+"  Workitems are in Data entry", true);
			}
			else
			{
				Reporter.log("No of workitems in Data Entry doesn't match with the count",true);
				Assert.fail("No of workitems in Data Entry doesn't match with the count");
			}
			
			Reporter.log("Navigate to workitems tab and validate  workitem is not in workitems list", true);			
			hp.getWorkItemTab().click();
			util.waitForPageToLoad();
			util.waitForElementEnabled(cwp.getNameSearchTextbox());
			Thread.sleep(3000);

			Reporter.log("Search for the workitem in workitem list", true);
			try
			{
				String workitemNameToSearch=witems[0];
				cwp.searchByNameInWorkitemList(workitemNameToSearch);
				boolean witemPresent=util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitemNameToSearch));

				if(witemPresent==true)
				{
					Reporter.log("Workitem is retrieved by performing GetNext ", true);
					ATUReports.add("Workitem is retrieved by performing GetNext which is not expected", 
							LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Workitem is retrieved by performing GetNext which is not expected");
				}
			}
			catch(Exception e)
			{
				Reporter.log("Workitem is not retrieved to workitem list ", true);
				ATUReports.add("Verify workitem is not retrived to workitem list","",
						"workitem  should not be present in the workitemlist", "workitem is not present in workitem list",true);
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
		catch (Exception e) {
			
			Reporter.log("Verify AutoView functionality without opening workitem test failed", true);
			ATUReports.add("Verify AutoView functionality without opening workitem test failed", 
					LogAs.FAILED	, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify AutoView functionality without opening workitem test failed "+e.getMessage());
		}
		finally {
			Log.endTestCase("INFO_8773_VerifyAutoViewWithoutOpeningWorkitem");
		}
	}
}
