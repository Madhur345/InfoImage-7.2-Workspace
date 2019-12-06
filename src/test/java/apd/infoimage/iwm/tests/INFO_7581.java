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

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

/**
 * @author PradhanJ INFO-7581 To export JPG image to multipage TIF image format
 *         from document type of workitem in IWM *
 */
public class INFO_7581 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1)
	public void testExportJPGImageToMultipageTIF() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7581 is To export JPG image to "
				+ "multipage TIF image format from document type of workitem in IWM");
		ATUReports.setAuthorInfo("Jayashri", "MAY-2018", "0.3");

		try {
			Log.startTestCase("INFO_7581_ExportJPGImageToMultipageTIF");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String imagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 27, 1);
			String secondImagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 27, 1);
			int time = 3000;
			Reporter.log("Class Name : INFO_7581_ExportJPGImageToMultipageTIF", true);
			ATUReports.add("Class Name : INFO_7581_ExportJPGImageToMultipageTIF", true);

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);

			cwp.CreateWorkitem(workitem, className, workitemType);

			Reporter.log("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");
			ATUReports.add("WorkItemDetailView displayed", true);
			util.wait(time);
			wdp.getContentField().click();
			util.wait(time);
			wdp.getAddNewPageIcon().click();
			util.wait(time);
			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");
				ATUReports.add("Add new Page window is displayed", true);
				util.wait(time);

				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				wdp.getContentUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				ATUReports.add("Add New Page Upload Button is clicked", true);
				util.waitForPageToLoad();
				util.waitForElementEnabled(wdp.getAddNewPageIcon());
				util.wait(time);
				util.wait(time);
				util.wait(time);
				
				wdp.getAddNewPageIcon().click();
				util.wait(time);

				Reporter.log("second Image Path : " + secondImagePath, true);
				Log.info("second Image Path : " + secondImagePath);
				ATUReports.add("second Image Path : " + secondImagePath, true);

				Thread.sleep(3000);
				wdp.getContentUploadField().sendKeys(secondImagePath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				ATUReports.add("Add New Page Upload Button is clicked", true);
				util.waitForPageToLoad();
				util.waitForElementEnabled(wdp.getSelectAllCheckbox());
				util.wait(time);
				util.wait(time);

				wdp.getSelectAllCheckbox().click();
				Reporter.log("Select All Checkbox is clicked", true);
				Log.info("Select All Checkbox is clicked");
				ATUReports.add("Select All Checkbox is clicked", true);
				util.waitForPageToLoad();
				util.wait(time);

				wdp.getExportImage().click();
				Reporter.log("Export Image Icon is clicked", true);
				Log.info("Export Image Icon is clicked");
				ATUReports.add("Export Image Icon is clicked", true);
				util.wait(time);

				util.jclick(wdp.getUploadTIF());
				Reporter.log("TIF radio button is clicked", true);
				Log.info("TIF radio button is clicked");
				ATUReports.add("TIF radio button is clicked", true);

				util.wait(time);
				util.jclick(wdp.getDownloadButton());
				Reporter.log("Download link is clicked", true);
				Log.info("Download link is clicked");
				ATUReports.add("Download link is clicked", true);

				util.waitForPageToLoad();
				util.wait(time);

				Reporter.log("Multipage TIF file is downloaded successfully", true);
				Log.info("Multipage TIF file is downloaded successfully");
				ATUReports.add("Multipage TIF file is downloaded successfully", true);

			} else {
				Reporter.log("Add new Page in workitem window is not present", true);
				ATUReports.add("Add new Page in workitem window is not present", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Add new Page in workitem window is not present ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add("failed to Verify export JPG image to multipage TIF image format", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("export JPG image to multipage TIF image format");
		} finally {
			Log.endTestCase("INFO_7581_ExportJPGImageToMultipageTIF");
		}

	}
}
