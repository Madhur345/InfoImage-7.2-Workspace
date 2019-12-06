package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
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
 * @author SumanGaK - 06-Aug-2018 
 * INFO-12540 
 * This class verifies the scroll bar under content for Pages Less Than Ten for Unreserved document type of workitem
 */
public class INFO_12540
extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1, groups = { "Content" })
	public void testVerifyScrollBarUnderContentsWithPagesLessThanTenForUnreservedDocumentTypeOfWorkitem() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO_12540 is for Verifying the scroll bar under content for Pages Less Than Ten for Unreserved document type of workitem");
		ATUReports.setAuthorInfo("Suman", "AUG-2018", "0.3");
		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_12540_VerifyScrollBarUnderContentsWithPagesLessThanTenForUnreservedDocumentTypeOfWorkitemTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 26, 1);
			String secondFileName = ExcelLib.getCellValue(xlpath, sheet, 7, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			int maxcontentSectionHeight = ExcelLib.getCellValueInt(xlpath, sheet, 80, 1);

			Reporter.log("VerifyScrollBarUnderContentsWithPagesLessThanTenForUnreservedDocumentTypeOfWorkitemTest", true);
			ATUReports.add("VerifyScrollBarUnderContentsWithPagesLessThanTenForUnreservedDocumentTypeOfWorkitemTest", true);
			Log.info("VerifyScrollBarUnderContentsWithPagesLessThanTenForUnreservedDocumentTypeOfWorkitemTest");
			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Log.info("Workitem : " + workitem);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Log.info("Class Name : " + className);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Log.info("Workitem Type : " + workitemType);
			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");
			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView is displayed", true);
			ATUReports.add("WorkItemDetailView is displayed", true);
			Log.info("WorkItemDetailView is displayed");
			util.wait(time);
			wdp.getContentField().click();
			Reporter.log("Content Tab is clicked", true);
			ATUReports.add("Content Tab is clicked", true);
			Log.info("Content Tab is clicked");
			util.wait(time);
			wdp.getAddNewPageIcon().click();
			Reporter.log("Add New Page is clicked", true);
			ATUReports.add("Add New Page is clicked", true);
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

					String secondImagePath = System.getProperty("user.dir")
							+ ExcelLib.getCellValue(xlpath, sheet, 21, 1);

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

					util.jclick(hp.getWorkItemTab());
					Reporter.log("WorkItems Tab is clicked", true);
					ATUReports.add("WorkItems Tab is clicked", true);
					Log.info("WorkItems Tab is clicked");

					Reporter.log("Sending WorkItem To Default Queue", true);
					ATUReports.add("Sending WorkItem To Default Queue", true);
					Log.info("Sending WorkItem To Default Queue");
					cwp.sendWorkItemToDefaultQueue(workitem);
					util.wait(time);
					util.waitForPageToLoad();

					Reporter.log("Retrieving Workitem Using Basic Search", true);
					ATUReports.add("Retrieving Workitem Using Basic Search", true);
					Log.info("Retrieving Workitem Using Basic Search");
					sp.retrieveWorkItemFromSearch(workitem);

					util.waitForPageToLoad();

					cwp.getWorkItemName(workitem).click();
					Reporter.log("Opening Workitem", true);
					ATUReports.add("Opening Workitem", true);
					Log.info("Opening Workitem");

					int contentSectionHeight = wdp.getContentSection().getSize().getHeight();

					Reporter.log(""+contentSectionHeight, true);
					Reporter.log(""+maxcontentSectionHeight, true);

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

				} else {
					Reporter.log("Add Second new Page window is NOT displayed", true);
					ATUReports.add("Add Second new Page window is NOT displayed", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
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

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("VerifyScrollBarUnderContentForSinglePageForUnreservedDocumentWorkitem test is failed", true);
			Log.info("VerifyScrollBarUnderContentForSinglePageForUnreservedDocumentWorkitemTest is failed");
			ATUReports.add("VerifyScrollBarUnderContentForSinglePageForUnreservedDocumentWorkitemTest is failed",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyScrollBarUnderContentForSinglePageForUnreservedDocumentWorkitemTest is failed");
		} finally {
			Log.endTestCase("INFO_12539_VerifyScrollBarUnderContentForSinglePageForUnreservedDocumentWorkitemTest");
		}
	}
}
