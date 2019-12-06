package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.lang3.time.StopWatch;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author PradhanJ
 *
 */
public class Mp3AndVideoUpload extends SuperClassIWM {
	
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
	@Test(enabled = true, priority = 1, groups = { "UploadFile" })
	public void testMp3AndVideoUpload() {
		Log.startTestCase("Mp3AndVideoUpload");

		try {
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String sheet1 = "Sheet_J";

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);

			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String vidPath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 32, 1);
			String vidPath1 = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 34, 1);
			String vidPath2 = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 42, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			int timeToExecuteUpload = ExcelLib.getCellValueInt(xlpath, sheet1, 36, 1);

			Reporter.log("VerifyUploadOneMBVideoFileTest : testUploadOneMBVideoFile()", true);
			ATUReports.add("VerifyUploadOneMBVideoFileTest : testUploadOneMBVideoFile()", true);
			Log.info("VerifyUploadOneMBVideoFileTest : testUploadOneMBVideoFile()");

			StopWatch sw = new StopWatch();

			sw.start();

			for (int i = 0; i < 1000; i++) {

				String workitem = str + util.getSysDate(0, date);
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

				Reporter.log("Document: " + workitem + " has been created", true);
				ATUReports.add("Document: " + workitem + " has been created", true);
				Log.info("Document: " + workitem + " has been created");

				util.wait(time);

				cwp.getWorkItemName(workitem).click();
				Reporter.log("WorkItemDetailView displayed", true);
				ATUReports.add("WorkItemDetailView displayed", true);
				Log.info("WorkItemDetailView displayed");

				util.waitForElementEnabled(wdp.getImports_win());
				wdp.getImports_win().click();
				Reporter.log("Imports Tab is clicked", true);
				ATUReports.add("Imports Tab is clicked", true);
				Log.info("Imports Tab is clicked");

				util.waitForElementEnabled(wdp.getmedia_Import());
				wdp.getmedia_Import().click();
				Reporter.log("Add Media Type File Import Button is clicked", true);
				ATUReports.add("Add Media Type File Import Button is clicked", true);
				Log.info("Add Media Type File Import Button is clicked");

				boolean addNewMediaFileWinPresent = util.verifyObjectPresentReturnsBool(wdp.getmedia_Import());
				if (addNewMediaFileWinPresent) {
					Reporter.log("Add new Media File window is displayed", true);
					ATUReports.add("Add new Media File window is displayed", true);
					Log.info("Add new Media File window is displayed");

					Reporter.log("vid path " + vidPath, true);
					ATUReports.add("vid path " + vidPath, true);
					Log.info("vid path " + vidPath);

					wdp.getVideoUploadField().sendKeys(vidPath);
					util.waitForPageToLoad();
					util.waitForElementEnabled(wdp.getImportedFile());
					// Validate document name in the add new page window

					boolean docNameInAddNewMediaFileWin = util.verifyObjectPresentReturnsBool(wdp.getImportedFile());
					if (docNameInAddNewMediaFileWin) {
						Reporter.log("Selected media file name validation successful", true);
						ATUReports.add("Selected media file name validation successful", true);
						Log.info("Selected media file name validation successful");
					} else {
						Reporter.log("Selected media file name validation failed.", true);
						Assert.fail("Selected media file name validation failed.");
					}

				} else {
					Reporter.log("Add new Media file window is NOT displayed", true);
					ATUReports.add("Add new Media file window is NOT displayed", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Add new Media file window is NOT displayed");
				}

				util.waitForElementEnabled(hp.getWorkItemTab());
				hp.getWorkItemTab().click();
				util.waitForPageToLoad();
				
				String workitem1 = str + util.getSysDate(0, date);
				Reporter.log("Workitem : " + workitem1, true);
				ATUReports.add("Workitem : " + workitem1, true);
				Log.info("Workitem : " + workitem1);
				Reporter.log("Class Name : " + className, true);
				ATUReports.add("Class Name : " + className, true);
				Log.info("Class Name : " + className);
				Reporter.log("Workitem Type : " + workitemType, true);
				ATUReports.add("Workitem Type : " + workitemType, true);
				Log.info("Workitem Type : " + workitemType);

				cwp.CreateWorkitem(workitem1, className, workitemType);

				Reporter.log("CreateWorkitem operation performed", true);
				ATUReports.add("CreateWorkitem operation performed", true);
				Log.info("CreateWorkitem operation performed");

				Reporter.log("Document: " + workitem1 + " has been created", true);
				ATUReports.add("Document: " + workitem1 + " has been created", true);
				Log.info("Document: " + workitem1 + " has been created");

				util.wait(time);

				cwp.getWorkItemName(workitem1).click();
				Reporter.log("WorkItemDetailView displayed", true);
				ATUReports.add("WorkItemDetailView displayed", true);
				Log.info("WorkItemDetailView displayed");

				util.waitForElementEnabled(wdp.getImports_win());
				//wdp.getImports_win().click();
				Reporter.log("Imports Tab is clicked", true);
				ATUReports.add("Imports Tab is clicked", true);
				Log.info("Imports Tab is clicked");

				util.waitForElementEnabled(wdp.getmedia_Import());
				wdp.getmedia_Import().click();
				Reporter.log("Add Media Type File Import Button is clicked", true);
				ATUReports.add("Add Media Type File Import Button is clicked", true);
				Log.info("Add Media Type File Import Button is clicked");

				boolean addNewMediaFileWinPresent1 = util.verifyObjectPresentReturnsBool(wdp.getmedia_Import());
				if (addNewMediaFileWinPresent1) {
					Reporter.log("Add new Media File window is displayed", true);
					ATUReports.add("Add new Media File window is displayed", true);
					Log.info("Add new Media File window is displayed");

					Reporter.log("vid path " + vidPath1, true);
					ATUReports.add("vid path " + vidPath1, true);
					Log.info("vid path " + vidPath1);

					wdp.getVideoUploadField().sendKeys(vidPath1);
					util.waitForPageToLoad();
					util.waitForElementEnabled(wdp.getImportedFile());
					// Validate document name in the add new page window

					boolean docNameInAddNewMediaFileWin = util.verifyObjectPresentReturnsBool(wdp.getImportedFile());
					if (docNameInAddNewMediaFileWin) {
						Reporter.log("Selected media file name validation successful", true);
						ATUReports.add("Selected media file name validation successful", true);
						Log.info("Selected media file name validation successful");
					} else {
						Reporter.log("Selected media file name validation failed.", true);
						Assert.fail("Selected media file name validation failed.");
					}

				} else {
					Reporter.log("Add new Media file window is NOT displayed", true);
					ATUReports.add("Add new Media file window is NOT displayed", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Add new Media file window is NOT displayed");
				}
				
				util.waitForElementEnabled(hp.getWorkItemTab());
				hp.getWorkItemTab().click();
				util.waitForPageToLoad();
				
				String workitem2 = str + util.getSysDate(0, date);
				Reporter.log("Workitem : " + workitem2, true);
				ATUReports.add("Workitem : " + workitem2, true);
				Log.info("Workitem : " + workitem2);
				Reporter.log("Class Name : " + className, true);
				ATUReports.add("Class Name : " + className, true);
				Log.info("Class Name : " + className);
				Reporter.log("Workitem Type : " + workitemType, true);
				ATUReports.add("Workitem Type : " + workitemType, true);
				Log.info("Workitem Type : " + workitemType);

				cwp.CreateWorkitem(workitem2, className, workitemType);

				Reporter.log("CreateWorkitem operation performed", true);
				ATUReports.add("CreateWorkitem operation performed", true);
				Log.info("CreateWorkitem operation performed");

				Reporter.log("Document: " + workitem2 + " has been created", true);
				ATUReports.add("Document: " + workitem2 + " has been created", true);
				Log.info("Document: " + workitem2 + " has been created");

				util.wait(time);

				cwp.getWorkItemName(workitem2).click();
				Reporter.log("WorkItemDetailView displayed", true);
				ATUReports.add("WorkItemDetailView displayed", true);
				Log.info("WorkItemDetailView displayed");

				util.waitForElementEnabled(wdp.getImports_win());
				//wdp.getImports_win().click();
				Reporter.log("Imports Tab is clicked", true);
				ATUReports.add("Imports Tab is clicked", true);
				Log.info("Imports Tab is clicked");

				util.waitForElementEnabled(wdp.getmedia_Import());
				wdp.getmedia_Import().click();
				Reporter.log("Add Media Type File Import Button is clicked", true);
				ATUReports.add("Add Media Type File Import Button is clicked", true);
				Log.info("Add Media Type File Import Button is clicked");

				boolean addNewMediaFileWinPresent2 = util.verifyObjectPresentReturnsBool(wdp.getmedia_Import());
				if (addNewMediaFileWinPresent2) {
					Reporter.log("Add new Media File window is displayed", true);
					ATUReports.add("Add new Media File window is displayed", true);
					Log.info("Add new Media File window is displayed");

					Reporter.log("vid path " + vidPath2, true);
					ATUReports.add("vid path " + vidPath2, true);
					Log.info("vid path " + vidPath2);

					wdp.getVideoUploadField().sendKeys(vidPath2);
					util.waitForPageToLoad();
					util.waitForElementEnabled(wdp.getImportedFile());
					// Validate document name in the add new page window

					boolean docNameInAddNewMediaFileWin = util.verifyObjectPresentReturnsBool(wdp.getImportedFile());
					if (docNameInAddNewMediaFileWin) {
						Reporter.log("Selected media file name validation successful", true);
						ATUReports.add("Selected media file name validation successful", true);
						Log.info("Selected media file name validation successful");
					} else {
						Reporter.log("Selected media file name validation failed.", true);
						Assert.fail("Selected media file name validation failed.");
					}

				} else {
					Reporter.log("Add new Media file window is NOT displayed", true);
					ATUReports.add("Add new Media file window is NOT displayed", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Add new Media file window is NOT displayed");
				}
				
				sw.suspend();
				Reporter.log("Elapsed Time::" + sw.getTime(), true);
				if (sw.getTime() > timeToExecuteUpload) {
					break;
				} else {
					sw.resume();
					util.waitForElementEnabled(hp.getWorkItemTab());
					hp.getWorkItemTab().click();
					util.waitForPageToLoad();
					
				}
				
			}
			util.waitForElementEnabled(hp.getSignOutBtn());

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Verify the Upload of One MB Video File test failed", true);
			Log.error(e.getMessage());
			ATUReports.add("Verify the Upload of One MB Video File test failed", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify the Upload of One MB Video File test failed");
		} finally {
			Log.endTestCase("Mp3AndVideoUpload");
		}
	}

}
