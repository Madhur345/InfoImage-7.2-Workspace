package apd.infoimage.iwm.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

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
 * @author Biswajit - 13-June-2018 INFO_7570 This class Perform To Export PNG
 *         Image To DIB Format From Document Type Of Workitem In IWM
 */
public class INFO_7184 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1)
	public void testExportPNGImageAsDIBFormatFromDocTypeWorkitem() {

		Log.startTestCase("INFO_7184_ExportPNGImageAsDIBFormatFromDocTypeWorkitem");

		ATUReports.setTestCaseReqCoverage(
				"INFO-7184 This Scenario is To export PNG image as DIB format from document type of workitem in IWM");

		ATUReports.setAuthorInfo("	Biswajit", "June-2018", "1.0");
		try {
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			int time = 3000;

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
			Reporter.log("Content field is clicked", true);
			Log.info("Content field is clicked");
			ATUReports.add("Content field is clicked.", true);
			util.wait(time);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			util.wait(time);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");
				ATUReports.add("Add new Page window is displayed", true);

				String tifFilePath = new File("src\\test\\resources").getAbsolutePath();

				String imagePath = tifFilePath + "\\samplePng.PNG";
				Reporter.log("img path " + imagePath, true);
				Log.info("img path " + imagePath);
				ATUReports.add("img path " + imagePath, true);

				wdp.getContentUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("samplePng.PNG");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected PNG file name validation successfull.", true);
					Log.info("Selected PNG file name validation successfull.");
					ATUReports.add("Selected PNG file name validation successfull.", true);
				} else {
					Reporter.log("Selected PNG file name validation failed.", true);
					ATUReports.add("Selected PNG file name validation failed", true);
					Assert.fail("Selected PNG file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				util.wait(time);

				wdp.getSelectPageCheckbox().click();
				Reporter.log("checkbox button clicked", true);
				ATUReports.add("checkbox button clicked", true);
				util.wait(time);

				wdp.getExportImage().click();
				Reporter.log("Export Image button clicked", true);
				ATUReports.add("Export Image button clicked", true);
				util.wait(time);

				util.jclick(wdp.getexportDIB());
				Reporter.log("DIB button clicked", true);
				ATUReports.add("DIB button clicked", true);
				Log.info("DIB button clicked");
				util.wait(time);

				util.jclick(wdp.getDownloadButton());
				Reporter.log("DIB format document successfully downloaded", true);
				ATUReports.add("DIB format document successfully downloaded", true);
				Log.info("DIB format document successfully downloaded");
				util.waitForPageToLoad();
				util.wait(time);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Exporting PNG image to DIB format from document type of workitem is failed.", true);
			ATUReports.add("Exporting PNG image to DIB format from document type of workitem is failed", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Exporting PNG image to DIB format from document type of workitem is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO_7184_ExportPNGImageAsDIBFormatFromDocTypeWorkitem");

		}
	}

}
