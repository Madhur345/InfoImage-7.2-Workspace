package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.support.ui.Select;
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
 * @author SumanGaK - 03-Sep-2018
 * INFO-12508
 * This class verifies the scroll bar under content for Less Than Ten Pages for document type of workitem Filed Inside Folder
 */
public class INFO_12508 extends SuperClassIWM{

	@BeforeMethod
	public void setUp() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown() {
		hp.logoutApp();
	}


	@SuppressWarnings("deprecation")
	@Test(enabled = true,priority=1,groups={"Content"})
	public void testVerifyScrollBarUnderContentForLessThanTenPagesForDocumentWorkitemFiledInsideFolder(){
		ATUReports.setTestCaseReqCoverage("This Scenario INFO_12508 is for Verifying the scroll bar under content for Less Than Ten Pages for document type of workitem Filed Inside Folder");
		ATUReports.setAuthorInfo("Suman","SEP-2018","0.3"); 
		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_12508_VerifyScrollBarUnderContentForLessThanTenPagesForDocumentWorkitemFiledInsideFolderTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String fileName = ExcelLib.getCellValue(xlpath,sheet,26,1);
			String secondFileName = ExcelLib.getCellValue(xlpath, sheet, 7, 1);
			String foldstr = ExcelLib.getCellValue(xlpath, sheet, 24, 1);
			String folderWorkitem = foldstr + util.getSysDate(0, date);
			String folderWorkitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			int maxcontentSectionHeight = ExcelLib.getCellValueInt(xlpath, sheet, 80, 1);

			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);
			cwp.CreateWorkitem(workitem, className, workitemType);

			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");

			Reporter.log("Document: " + workitem + " has been created", true);
			ATUReports.add("Document: " + workitem + " has been created", true);
			Log.info("Document: " + workitem + " has been created");

			Reporter.log("Workitem Type : " + folderWorkitemType, true);
			ATUReports.add("Workitem Type : " + folderWorkitemType, true);
			Log.info("Workitem Type : " + folderWorkitemType);

			cwp.CreateWorkitem(folderWorkitem, className, folderWorkitemType);
			Reporter.log("Folder: " + folderWorkitem + " has been created", true);
			ATUReports.add("Folder: " + folderWorkitem + " has been created", true);
			Log.info("Folder: " + folderWorkitem + " has been created");

			util.wait(time);

			cwp.getNameSearchTextbox().clear();

			cwp.searchByNameInWorkitemList(workitem);
			util.wait(time);

			cwp.getCheckBoxWorkItem(workitem).click();

			Reporter.log("Selected: " + workitem + " to file work item", true);
			ATUReports.add("Selected: " + workitem + " to file work item", true);
			Log.info("Selected: " + workitem + " to file work item");

			cwp.getFileworkitemBtn().click();
			util.wait(time);
			util.wait(time);

			boolean fileWorkitemDialogBoxPresence = util.verifyObjectPresentReturnsBool(cwp.getFileWorkItem_win());
			if (fileWorkitemDialogBoxPresence) {
				Reporter.log("File Work item dialog box is open", true);
				ATUReports.add("File Work item dialog box is open", true);
				Log.info("File Work item dialog box is open");
			} else {
				Reporter.log("File Work item dialog box is not open", true);
				ATUReports.add("File Work item dialog box is not open", true);
				Log.info("File Work item dialog box is not open");
				Assert.fail("File Work item dialog box is not open");
			}


			Select sel = new Select(cwp.getSelectFolderDropDown());
			sel.selectByVisibleText(folderWorkitem);
			Reporter.log("Selected destination folder as: " + folderWorkitem, true);
			ATUReports.add("Selected destination folder as: " + folderWorkitem, true);
			Log.info("Selected destination folder as: " + folderWorkitem);

			util.wait(time);
			cwp.getFileWorkitemAcceptBtn().click();
			util.waitForPageToLoad();
			util.wait(time);

			cwp.searchByNameInWorkitemList(folderWorkitem);
			util.waitForPageToLoad();

			cwp.getWorkItemName(folderWorkitem).click();
			util.waitForPageToLoad();

			wdp.getContentField().click();

			util.wait(time);
			boolean docWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (docWorkitemPresence) {
				Reporter.log(workitem + " Successfully filed in folder: " + folderWorkitem, true);
				ATUReports.add(workitem + " Successfully filed in folder: " + folderWorkitem, true);
			} else {
				Reporter.log(workitem + " failed to file in folder: " + folderWorkitem, true);
				ATUReports.add(workitem + " failed to file in folder: " + folderWorkitem, true);
				Assert.fail(workitem + " failed to file in folder: " + folderWorkitem);
			}

			util.waitForPageToLoad();

			Reporter.log("WorkItem is clicked", true);
			ATUReports.add("WorkItem is clicked", true);
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			util.wait(time);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItem is opened", true);
			ATUReports.add("WorkItem is opened", true);
			wdp.getAddNewPageIcon().click();
			Reporter.log("Add New Page is clicked",true);
			ATUReports.add("Add New Page is clicked",true);
			Log.info("Add New Page is clicked");
			util.wait(time);
			boolean addNewPageWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if(addNewPageWinPresent)
			{
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				util.wait(time);				

				String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,25,1);
				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);
				Log.info("img path "+imagePath);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				//Validate document name in the add new page window
				boolean docNameInAddNewPageWin=wdp.getSelectedFile().getText().equals(fileName);
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected TIF file name validation is successful", true);
					ATUReports.add("Selected TIF file name validation is successful", true);
					Log.info("Selected TIF file name validation is successful");
				}
				else
				{
					Reporter.log("Selected TIF file name validation is failed.", true);
					ATUReports.add("Selected TIF file name validation is failed.", true);
					Log.info("Selected TIF file name validation is failed.");
					Assert.fail("Selected TIF file name validation is failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked",true);
				ATUReports.add("Add New Page Upload Button is clicked",true);
				Log.info("Add New Page Upload Button is clicked");
				util.waitForPageToLoad();
				util.wait(time);

				wdp.getAddNewPageIcon().click();
				Reporter.log("Add New Page is clicked", true);
				ATUReports.add("Add New Page is clicked", true);
				util.wait(time);
				util.waitForPageToLoad();

				boolean addSecondNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
				if (addSecondNewPageWinPresent) {
					Reporter.log("Add Second new Page window is displayed", true);
					ATUReports.add("Add Second new Page window is displayed", true);

					String secondImagePath = System.getProperty("user.dir")	+ ExcelLib.getCellValue(xlpath, sheet, 21, 1);

					Reporter.log("second img path " + secondImagePath, true);
					ATUReports.add("second img path " + secondImagePath, true);

					wdp.getContentUploadField().sendKeys(secondImagePath);

					util.waitForElementEnabled(wdp.getSelectedFile());
					util.wait(time);

					Reporter.log("singlePage TIf file is uploaded", true);
					ATUReports.add("singlePage TIf file is uploaded", true);

					// Validate document name in the add new page window

					boolean docNameInAddSecondNewPageWin = wdp.getSelectedFile().getText().equals(secondFileName);
					if (docNameInAddSecondNewPageWin) {
						Reporter.log("Selected file name validation successful.", true);
						ATUReports.add("Selected file name validation successful.", true);
					} else {
						Reporter.log("Selected file name validation failed.", true);
						ATUReports.add("Selected file name validation failed.", true);
						Assert.fail("Selected file name validation failed.");
					}
					util.wait(time);
					wdp.getAddNewPageUploadBtn().click();
					Reporter.log("Add New Page Upload Button is clicked", true);
					ATUReports.add("Add New Page Upload Button is clicked", true);
					util.waitForPageToLoad();

					int contentSectionHeight = wdp.getContentSection().getSize().getHeight();

					if(contentSectionHeight<=maxcontentSectionHeight)
					{
						Reporter.log("Vertical scroll bar is not displayed", true);
						ATUReports.add("Vertical scroll bar is not displayed", true);
						Log.info("Vertical scroll bar is not displayed");
					}				
					else
					{
						Reporter.log("Vertical scroll bar is displayed", true);
						ATUReports.add("Vertical scroll bar is displayed", true);
						Log.info("Vertical scroll bar is displayed");
					}
					util.wait(time);
				}
				else {
					Reporter.log("Add Second new Page window is NOT displayed", true);
					ATUReports.add("Add Second new Page window is NOT displayed", LogAs.FAILED,	new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Add Second new Page window is NOT displayed");
				}
			}
			else
			{
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Log.info("Add new Page window is NOT displayed");
				Assert.fail("Add new Page window is NOT displayed");
			}
			util.wait(time);
			util.waitForPageToLoad();
			util.wait(time);		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("VerifyScrollBarUnderContentForLessThanTenPagesForDocumentWorkitemFiledInsideFolder test is failed", true);
			Log.info("VerifyScrollBarUnderContentForLessThanTenPagesForDocumentWorkitemFiledInsideFolder is failed");
			ATUReports.add("VerifyScrollBarUnderContentForLessThanTenPagesForDocumentWorkitemFiledInsideFolderTest is failed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyScrollBarUnderContentForLessThanTenPagesForDocumentWorkitemFiledInsideFolderTest is failed");		
		}		
		finally {
			Log.endTestCase("INFO_12508_VerifyScrollBarUnderContentForLessThanTenPagesForDocumentWorkitemFiledInsideFolderTest");
		}
	}
}
