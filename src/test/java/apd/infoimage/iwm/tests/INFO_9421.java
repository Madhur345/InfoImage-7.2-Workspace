package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
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
 * INFO-9421
 * This class is to verify Document split wich equal number of TIF and JPG images
 */
public class INFO_9421 extends SuperClassIWM{
	
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
	@Test(enabled=true,priority=1,groups={"DocumentSplit"})
	public void testVerifyDocumentSplitWithEqualNoTifImage()
	{
		Log.startTestCase("INFO_9421_VerifyDocumentSplitWithEqualNoTifImage");
		
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9421  is To verify Document split wich equal number of TIF and JPG images");					
		ATUReports.setAuthorInfo("Jayashri","APR-2018","0.3");
		
		//String xlpath="infoImageWorkflowManager\\infoImageWorkflowManager\\testData\\testData.xlsx";
		//String sheet="Sheet1";

		/*String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
		String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
		String workitem = str+util.getSysDate(0, date);
		String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
		String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
		String fileName = ExcelLib.getCellValue(xlpath,sheet,18,1);
		int time=3000;*/
		try
		{
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String fileName = ExcelLib.getCellValue(xlpath,sheet,18,1);
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
            int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			Reporter.log("VerifyKeyboardShortcutsSplittingPageInWorkitemTest",true);
			ATUReports.add("VerifyKeyboardShortcutsSplittingPageInWorkitemTest",true);
			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);

			cwp.CreateWorkitem(workitem,className,workitemType);
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			
			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView is displayed",true);
			ATUReports.add("WorkItemDetailView is displayed",true);
			util.wait(time);
			
			wdp.getContentField().click();
			Reporter.log("Content Tab is clicked",true);
			ATUReports.add("Content Tab is clicked",true);
			util.wait(time);
			
			wdp.getAddNewPageIcon().click();
			Reporter.log("Add New Page is clicked",true);
			ATUReports.add("Add New Page is clicked",true);
			util.wait(time);
			util.waitForPageToLoad();
			
			boolean addNewPageWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if(addNewPageWinPresent)
			{
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				
				String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,5,1);
				
				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				Reporter.log("multipage TIf file is uploaded", true);
				ATUReports.add("multipage TIf file is uploaded", true);
				
				//Validate document name in the add new page window				

				boolean docNameInAddNewPageWin=wdp.getSelectedFile().getText().equals(fileName);
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected multipage file name validation successful.", true);
					ATUReports.add("Selected multipage file name validation successful.", true);
				}
				else
				{
					Reporter.log("Selected multipage file name validation failed.", true);
					ATUReports.add("Selected multipage file name validation failed.", true);
					Assert.fail("Selected multipage file name validation failed.");
				}
				util.wait(time);
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				util.waitForPageToLoad();

				//Validate the document is listed under content  and no of pages in the document
				
				try
				{
					int contentList=wdp.getContentPageNo();

					Reporter.log("No of Pages in the multiple page tif file is : "+contentList, true);
					ATUReports.add("No of Pages in the multiple page tif file is : "+contentList, true);

					util.waitForPageToLoad();
					util.wait(time);

					wdp.getSelectAllCheckbox().click();
					Reporter.log("Select All Checkbox is clicked",true);
					ATUReports.add("Select All Checkbox is clicked",true);
					util.wait(time);
					
					wdp.getSplitIcon().click();		
					util.waitForPageToLoad();
					util.wait(time);
					boolean splitPageWindowPresent=util.verifyObjectPresentReturnsBool(wdp.getProceedButton());

					if(splitPageWindowPresent)
					{
						Reporter.log("Split workitem window is displayed", true);
						ATUReports.add("Split workitem window is displayed", true);
						ATUReports.add("Verify Split page in workitem using shortkut key", "","Split workitem window should be displayed",
								"Split workitem window is displayed", true);
					}

					else
					{
						Reporter.log("Split workitem window is not displayed", true);
						ATUReports.add("Split workitem window is not displayed", true);
						Assert.fail("Split workitem window is not displayed");
					}
					
					wdp.getCancelButton().click();
					Reporter.log("Split page window is closed	", true);
					ATUReports.add("Split page window is closed	", true);
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					Reporter.log("Validation of added  TIF file is failed", true);
					ATUReports.add("Validation of added  TIF file is failed", true);
					Assert.fail("Validation of added  TIF file is failed");
				}
			}
			else
			{
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("Verify document Splitting Page window  test is failed", true);
			ATUReports.add("Verify document Splitting Page window  test is failed", true);
			Assert.fail("Verify document Splitting Page window  test is failed");
		}
		finally {
			Log.endTestCase("INFO_9421_VerifyDocumentSplitWithEqualNoTifImage");


		}
	}
}

	


