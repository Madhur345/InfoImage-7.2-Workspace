package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
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
 * @author SumanGaK - 30-Mar-2018 
 * INFO-7607 
 * This test method verifies Creating a document type of workitem,adding pages and Sending the workitem to
 * default Queue and to check whether the workitem has Red X sign in the Default Queue
 */
public class INFO_7607 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1, groups = { "UploadFile" })
	public void testRedXDocumentPages() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO_7607 is for Creating a document type of workitem,adding pages and Sending the workitem to default Queue and to check whether the workitem has Red X sign in the Default Queue");
		ATUReports.setAuthorInfo("Suman","MAR-2018","0.3"); 

		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_7607_RedXDocumentPagesTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			Driver.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 18, 1);	
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			int numberOne = ExcelLib.getCellValueInt(xlpath,sheet,83,1);			

			Reporter.log("RedXTest", true);
			ATUReports.add("RedXTest", true);
			Log.info("RedXTest");

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

			wdp.getContentField().click();
			Reporter.log("Content Tab is clicked", true);
			ATUReports.add("Content Tab is clicked", true);
			Log.info("Content Tab is clicked");

			wdp.getAddNewPageIcon().click();
			Reporter.log("Add New Page is clicked", true);
			ATUReports.add("Add New Page is clicked", true);
			Log.info("Add New Page is clicked");

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				String imagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 5, 1);
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				Log.info("img path " + imagePath);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals(fileName);
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected TIF file name validation is successful", true);
					ATUReports.add("Selected TIF file name validation is successful", true);
					Log.info("Selected TIF file name validation is successful");
				} else {
					Reporter.log("Selected TIF file name validation is failed.", true);
					ATUReports.add("Selected TIF file name validation is failed.", true);
					Log.info("Selected TIF file name validation is failed.");
					Assert.fail("Selected TIF file name validation is failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");

				util.waitForPageToLoad();

				// Validate the document is listed under content and no of pages in the document
				try {
					int contentList = wdp.getContentPageNo();
					if (contentList >= numberOne) {
						Reporter.log("1 Page is listed under content on the side bar ", true);
						ATUReports.add("1 Page is listed under content on the side bar ", true);
						Log.info("1 Page is listed under content on the side bar ");
					} else {
						Reporter.log("1 Page is NOT listed under content on the side bar", true);
						ATUReports.add("1 Page is NOT listed under content on the side bar", true);
						Log.info("1 Page is NOT listed under content on the side bar");
						Assert.fail("1 Page is NOT listed under content on the side bar");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Log.error(e1.getMessage());
					Reporter.log("Validation of added  TIF file is failed", true);
					ATUReports.add("Validation of added  TIF file is failed", true);
					Log.info("Validation of added  TIF file is failed");
					Assert.fail("Validation of added  TIF file is failed");
				}


				hp.getWorkItemTab().click();
				util.wait(time);
				Reporter.log("WorkItems Tab is clicked", true);
				ATUReports.add("WorkItems Tab is clicked", true);
				Log.info("WorkItems Tab is clicked");

				util.wait(time);
				cwp.sendWorkItemToDefaultQueue(workitem);
				cwp.getRedXDocumentType(workitem);

				util.waitForPageToLoad();
				util.wait(time);
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Log.info("Add new Page window is NOT displayed");
				Assert.fail("Add new Page window is NOT displayed");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify RedX Document Pages test is failed", true);
			Log.info("Verify RedX Document Pages test is failed");
			ATUReports.add("failed to execute test INFO_7607_RedXDocumentPagesTest",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			ATUReports.add("Verify RedX Document Pages test is failed", true);
			Assert.fail("Verify RedX Document Pages test is failed");
		} finally {
			Log.endTestCase("INFO_7607_RedXDocumentPagesTest");

		}
	}
}