package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
 * @author PradhanJ
 * INFO-11775
 * To verify search criteria for multiple document type of workitems
 * present in the queue using url extension in DOMAIN level
 */
public class INFO_11775 extends SuperClassIWM{
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
	@Test(enabled =true,groups={"GetNext"})
	public void testVerifySearchForMultipleWitemsDomainLevelUsingUrlExtension()
	{
		Log.startTestCase("INFO_11775_VerifySearchForMultipleWitemsDomainLevelUsingUrlExtension");
		try
		{
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-11775  To verify search criteria for multiple document type of workitems"
					+ " present in the queue using url extension in DOMAIN level ");
					
			ATUReports.setAuthorInfo("Jayashri","AUG-2018","0.3");

			//Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String sheet2="Sheet_J";
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			
			String idCodeStr = randomNo.getRandomNo(6);
			Reporter.log("id_code value is "+idCodeStr, true);
			
			int noOfWitems= 2;
			String[] witems=new String[noOfWitems];

			int createProgress=1;
			//Create 1 workitem and send to default queue
			for(int witemCount=0;witemCount<=noOfWitems-1;witemCount++)
			{
				workitem = str + util.getSysDate(0, date);
				Reporter.log("Creation of Workitem",true);
				ATUReports.add("Creation of Workitem",true);
				cwp.CreateWorkitem(workitem,className,workitemType);
				
				cwp.getActionBtn(workitem).click();
				cwp.getFormFiledsBtn().click();
				Reporter.log("FormFields window is opened for the created workitem", true);
				Log.info("FormFields window is opened for the created workitem");
				ATUReports.add("FormFields window is opened for the created workitem", true);
				
				cwp.getID_CODE_Tf().clear();
				cwp.getID_CODE_Tf().sendKeys(idCodeStr);			
				
				cwp.getUpdateFormBtn().click();
				Reporter.log("ID_CODE field for  workitem is updated", true);
				Log.info("ID_CODE field for  workitem is updated");
				ATUReports.add("ID_CODE field for  workitem is updated", true);
				
				util.waitForPageToLoad();
				Thread.sleep(5000);
				
				cwp.searchByNameInWorkitemList(workitem);
				
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
			hp.getSearchTab().click();
			util.waitForPageToLoad();
			Reporter.log("Navigate to search Page", true);
			ATUReports.add("Navigate to search Page", true);
			Log.info("Navigate to search Page");

			String currentUrl = Driver.driver.getCurrentUrl();
			Reporter.log("Current Url is fetched", true);
			ATUReports.add("Current Url is fetched", true);
			Log.info("Current Url is fetched");

			String urlWdIndex = ExcelLib.getCellValue(xlpath, sheet2, 30, 1);
			Reporter.log("Url by using  index field is fetched from test data", true);
			ATUReports.add("Url by one index field  is fetched from test data", true);
			Log.info("Url by using  index field is fetched from test data");

			ATUReports.add("Url by using only one index field  is \n" + urlWdIndex, true);
			Reporter.log("Url by using only one index field is \n" + urlWdIndex, true);

			
			String urlWithActualIndexFieldvalue=urlWdIndex.replaceAll("Unisys", idCodeStr);
			
			Reporter.log("Url with actual index field data is "+urlWithActualIndexFieldvalue, true);
			ATUReports.add("Url with actual index field data is "+urlWithActualIndexFieldvalue, true);
			Log.info("Url with actual index field data is "+urlWithActualIndexFieldvalue);
			
			String newUrlForVerify = currentUrl + urlWithActualIndexFieldvalue;

			Driver.driver.get(newUrlForVerify);
			util.waitForPageToLoad();
			String newUrl=Driver.driver.getCurrentUrl();
			
			Reporter.log("Concatinated Url of current url and url with index field "					
					+ "entered in the URl field of the browser", true);
			Reporter.log("Conactenated Url is "+newUrl, true);
			ATUReports.add("Conactenated Url is "+newUrl, true);
			
			ATUReports.add("Concatinated Url of current url and url with index field "
					+ "entered in the URl field of the browser", true);
			Log.info("Concatinated Url of current url and url with index field "
					+ "entered in the URl field of the browser");
			util.waitForPageToLoad();
						
			List<WebElement> witemList=Driver.driver.findElements(By.xpath("//a[@class='workitemName']"));
			int noWItemsSearchResult=witemList.size();
			if(noWItemsSearchResult==2)
			{
				Reporter.log("Search for Multiple document type workitems validation successful"
						+ "using Url extenxion in Domain Level", true);
				ATUReports.add("Search for Multiple document type workitems validation successful"
						+ "using Url extenxion in Domain Level", true);
				Log.info("Search for Multiple document type workitems validation successful"
						+ "using Url extenxion in Domain Level");
			}
			else
			{
				Assert.fail("Search for Multiple document type workitems validation failed"
						+ "using Url extenxion in Domain Level");
			}
		}
		catch (Exception e) {
			Reporter.log("Search for Multiple document type workitems validation failed"
						+ "using Url extenxion in Domain Level", true);
			ATUReports.add("Search for Multiple document type workitems validation failed"
						+ "using Url extenxion in Domain Level",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Search for Multiple document type workitems validation failed"
						+ "using Url extenxion in Domain Level");
		} finally {
			Log.endTestCase("INFO_11775_VerifySearchForMultipleWitemsDomainLevelUsingUrlExtension");
		}
	}
}
