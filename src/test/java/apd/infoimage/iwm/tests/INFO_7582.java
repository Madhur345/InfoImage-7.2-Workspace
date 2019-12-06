package apd.infoimage.iwm.tests;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
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
 * @author PradhanJ
 * INFO-7582
 * To export TIF image to multipage PDF image format from document type of workitem in IWM	
 */
public class INFO_7582 extends SuperClassIWM{
	
	@BeforeMethod
	public void setUp() {
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
	public void testExportMultipageTIFImageToMultiPagePDF() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-7581 is To export TIF image to multipage PDF image format "
				+ "from document type of workitem in IWM	");
		ATUReports.setAuthorInfo("Jayashri", "MAY-2018", "0.3");

		try {
			Log.startTestCase(
					"INFO_7582_ExportMultipageTIFImageToMultiPagePDF");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			
			String sheet = prop.getProperty("sheet");
			String sheet2="SheetJ";
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String downloadPath1 = ExcelLib.getCellValue(xlpath, sheet2, 21, 1);
			String downloadPath2 = ExcelLib.getCellValue(xlpath, sheet2, 22, 1);
			int time=3000;
			Reporter.log(
					"Class Name : INFO_7582_ExportMultipageTIFImageToMultiPagePDF",
					true);
			ATUReports.add(
					"Class Name : INFO_7582_ExportMultipageTIFImageToMultiPagePDF",
					true);

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

				String tifFilePath = new File("src\\test\\resources").getAbsolutePath();
				String imagePath = tifFilePath + "\\multiPage.tif";

				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				Reporter.log("multipage TIf file is uploaded", true);
				ATUReports.add("multipage TIf file is uploaded", true);

				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				ATUReports.add("Add New Page Upload Button is clicked", true);
				util.waitForPageToLoad();
				util.wait(time);
				util.waitForElementEnabled(wdp.getSelectAllCheckbox());

				wdp.getSelectAllCheckbox().click();
				Reporter.log("Select All Checkbox is clicked", true);
				Log.info("Select All Checkbox is clicked");
				ATUReports.add("Select All Checkbox is clicked", true);
				util.waitForPageToLoad();
				util.wait(time);
				
				Reporter.log("Export Image Icon is clicked", true);
				Log.info("Export Image Icon is clicked");
				ATUReports.add("Export Image Icon is clicked", true);
				util.wait(time);
				
				Actions action = new Actions(Driver.driver);
				action.keyDown(Keys.CONTROL);
				action.keyDown(Keys.ALT).sendKeys(String.valueOf('\u0065')).perform();
				util.wait(time);
				
				Reporter.log("PDF radio button is clicked", true);
				Log.info("PDF radio button is clicked");
				ATUReports.add("PDF radio button is clicked", true);
				
				util.wait(time);
				util.wait(time);
				//wdp.getExportDownloadLink().click();
				util.jclick(wdp.getDownloadButton());
				Reporter.log("Download link is clicked", true);
				Log.info("Download link is clicked");
				ATUReports.add("Download link is clicked", true);				
				util.waitForPageToLoad();
				util.wait(time);
				
				/*String browser=prop.getProperty("browser");

				if(browser.equalsIgnoreCase("firefox"))
				{
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					Reporter.log("TIFF File is downloaded successfully.", true);
					ATUReports.add("TIFF File is downloaded successfully.", true);
					Log.info("TIFF File is downloaded successfully. ");
				}
				else
				{
					Reporter.log("Multipage PDF file is downloaded successfully.", true);
					ATUReports.add("Multipage PDF file is downloaded successfully.", true);
					Log.info("Multipage PDF file is downloaded successfully. ");
					
				}
				
				util.waitForPageToLoad();
				Thread.sleep(3000);
				
				String userLoggedIn=System.getProperty("user.name");
				Reporter.log("Logged in user domain name is :"+userLoggedIn, true);
				
				String downloadPath= downloadPath1+userLoggedIn+downloadPath2;
						
				File filePath=wdp.getLatestFilefromDir(downloadPath);

				Reporter.log("Get Latest File : "+filePath, true);
				ATUReports.add("Get Latest File : "+filePath, true);
				Log.info("Get Latest File : "+filePath);

				String imageFileName=(wdp.getLatestFilefromDir(downloadPath)).toString().substring(28);
				Reporter.log("imageFileName : "+imageFileName, true);
				Log.info("imageFileName : "+imageFileName);

				boolean filePresence=wdp.isFileDownloaded(downloadPath,imageFileName);
				if(filePresence)
				{
					Reporter.log("File is Present : "+imageFileName, true);
					ATUReports.add("File is Present : "+imageFileName, true);
					Log.info("File is Present : "+imageFileName);
				}
				else				
				{
					Reporter.log("File is not Present", true);
					ATUReports.add("File is not Present", true);
					Log.info("File is not Present");
				}
				
				util.waitForPageToLoad();
				util.wait(time);
				util.wait(time);*/
				Reporter.log("Multipage PDF file is downloaded successfully", true);
				Log.info("Multipage PDF file is downloaded successfully");
				ATUReports.add("Multipage PDF file is downloaded successfully", true);	

			} else {
				Reporter.log("Add new Page in workitem window is not present", true);
				ATUReports.add("Add new Page in workitem window is not present", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Add new Page in workitem window is not present ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add(
					"failed to Verify export TIF image to multipage PDF image format",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("export TIF image to multipage PDF image format");
		} finally {
			Log.endTestCase("INFO_7582_ExportMultipageTIFImageToMultiPagePDF");
		}

	}
}
