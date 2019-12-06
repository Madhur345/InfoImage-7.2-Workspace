package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
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
 * @author SumanGaK - 07-May-2018 
 * INFO-7534 
 * This class verifies Document Duplicate with different class of Folder type of Workitem and
 * uploading files of different formats through Import
 */
public class INFO_7534
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
	@Test(enabled = true, priority = 1, groups = { "DocumentDuplicate" })
	public void testVerifyFolderWorkitemImportDifferentFormatsDocumentDuplicateDifferentClass() {
		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_7534_VerifyFolderWorkitemImportDifferentFormatsDocumentDuplicateDifferentClassTest");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 18, 1);
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			String secondImagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 28, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			int numberOne = ExcelLib.getCellValueInt(xlpath,sheet,83,1);			

			Reporter.log("VerifyFolderWorkitemImportDifferentFormatsDocumentDuplicateDifferentClassTest", true);
			ATUReports.add("VerifyFolderWorkitemImportDifferentFormatsDocumentDuplicateDifferentClassTest", true);
			Log.info("VerifyFolderWorkitemImportDifferentFormatsDocumentDuplicateDifferentClassTest");

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
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");

			util.wait(time);
			wdp.getImports_win().click();
			Reporter.log("Imports Tab is clicked", true);
			ATUReports.add("Imports Tab is clicked", true);
			Log.info("Imports Tab is clicked");

			util.wait(time);
			wdp.getAddImportIcon().click();
			Reporter.log("Add new import icon is clicked", true);
			ATUReports.add("Add new import icon is clicked", true);
			Log.info("Add new import icon is clicked");

			util.wait(time);
			boolean addNewImportsWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddImportWindow());
			if (addNewImportsWinPresent) {
				Reporter.log("Add new import window is displayed", true);
				ATUReports.add("Add new import window is displayed", true);
				Log.info("Add new import window is displayed");

				util.wait(time);

				String imagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 5, 1);
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				Log.info("img path " + imagePath);

				wdp.getImportUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedImportFile());
				util.wait(time);

				boolean docNameInAddNewPageWin = wdp.getSelectedImportFile().getText().equals(fileName);
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected tif file name validation is successful.", true);
					ATUReports.add("Selected tif file name validation is successful.", true);
					Log.info("Selected tif file name validation is successful.");
				} else {
					Reporter.log("Selected tif file name validation is failed.", true);
					ATUReports.add("Selected tif file name validation is failed.", true);
					Log.info("Selected tif file name validation is failed.");
					Assert.fail("Selected tif file name validation is failed.");
				}
				util.wait(time);

				wdp.getImportUploadBtn().click();
				Reporter.log("Import Upload Button is clicked", true);
				ATUReports.add("Import Upload Button is clicked", true);
				Log.info("Import Upload Button is clicked");
				util.waitForPageToLoad();

				// Validate the document is listed under content and no of pages in the document
				try {
					int importList = wdp.getContentImportNo();
					if (importList >= numberOne) {
						Reporter.log("Tif File is imported successfully. ", true);
						ATUReports.add("Tif File is imported successfully. ", true);
						Log.info("Tif File is imported successfully. ");
					} else {
						Reporter.log("Tif File is NOT imported successfully.", true);
						ATUReports.add("Tif File is NOT imported successfully.", true);
						Log.info("Tif File is NOT imported successfully.");
						Assert.fail("Tif File is NOT imported successfully.");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Tif File is NOT imported successfully.", true);
					ATUReports.add("Tif File is NOT imported successfully.", true);
					Log.info("Tif File is NOT imported successfully.");
					Assert.fail("Tif File is NOT imported successfully.");
				}
			} else {

				Reporter.log("Add new import  window is NOT displayed", true);
				ATUReports.add("Add new import  window is NOT displayed", true);
				Log.info("Add new import  window is NOT displayed");
				Assert.fail("Add new import window is NOT displayed");
			}

			Reporter.log("img path " + secondImagePath, true);
			ATUReports.add("img path " + secondImagePath, true);
			Log.info("img path " + secondImagePath);
			util.wait(time);
			util.wait(time);
			util.wait(time);

			wdp.getImportUploadField();
			util.waitForElementEnabled(wdp.getSelectedImportFile());
			util.wait(time);
			util.wait(time);
			util.wait(time);
			util.wait(time);

			wdp.getImportUploadBtn().click();
			Reporter.log("Import Upload Button is clicked", true);
			ATUReports.add("Import Upload Button is clicked", true);
			Log.info("Import Upload Button is clicked");
			util.waitForPageToLoad();

			wdp.getActionsDropDown().click();
			Reporter.log("Actions Drop Down is clicked", true);
			ATUReports.add("Actions Drop Down is clicked", true);
			Log.info("Actions Drop Down is clicked");

			wdp.getDocumentDuplicateOption().click();
			Reporter.log("Document Duplicate Option is clicked", true);
			ATUReports.add("Document Duplicate Option is clicked", true);
			Log.info("Document Duplicate Option is clicked");
			util.wait(time);

			wdp.getDocumentDuplicateWorkitemNameTextBox().sendKeys(dupStr);
			Reporter.log("Workitem Duplicate Name is entered in text box", true);
			ATUReports.add("Workitem Duplicate Name is entered in text box", true);
			Log.info("Workitem Duplicate Name is entered in text box");
			util.wait(time);

			wdp.getDuplicateButton().click();
			Reporter.log("Duplicate Button is clicked", true);
			ATUReports.add("Duplicate Button is clicked", true);
			Log.info("Duplicate Button is clicked");
			util.wait(time);

			hp.getWorkItemTab().click();
			Reporter.log("Workitem tab is clicked", true);
			ATUReports.add("Workitem tab is clicked", true);
			Log.info("Workitem tab is clicked");
			util.waitForPageToLoad();
			hp.searchByNameInWorkitemTab(workitem + dupStr);
			Reporter.log("Searching By Workitem Name In Workitem tab", true);
			ATUReports.add("Searching By Workitem Name In Workitem tab", true);
			Log.info("Searching By Workitem Name In Workitem tab");

			boolean duplicateWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem + dupStr));
			if (duplicateWorkitemPresence) {
				Reporter.log("Duplicate Workitem is present in grid", true);
				ATUReports.add("Duplicate Workitem is present in grid", true);
				Log.info("Duplicate Workitem is present in grid");
				ATUReports.add("Verify Document Duplicate of Folder Workitem", "",
						"Duplicate Workitem should be present in grid", "Duplicate Workitem is present in grid", true);
			} else {
				Reporter.log("Duplicate Workitem is not present in grid", true);
				Reporter.log("Duplicate Workitem is not present in grid", true);
				ATUReports.add("Duplicate Workitem is not present in grid", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Duplicate Workitem is not present in grid ");
			}

			hp.getSearchFieldInWorkitemTab().sendKeys(Keys.ENTER);
			util.wait(time);

			cwp.getActionsBtn().click();
			Reporter.log("Actions Button is Clicked", true);
			ATUReports.add("Actions Button is Clicked", true);
			Log.info("Actions Button is Clicked");
			cwp.getActionsMetaData().click();

			Reporter.log("Meta Data is Clicked", true);
			ATUReports.add("Meta Data is Clicked", true);
			Log.info("Meta Data is Clicked");
			String newClass = cwp.getNewClassName().getText().substring(10);

			Reporter.log("New Class Name : " + newClass, true);
			ATUReports.add("New Class Name : " + newClass, true);
			Log.info("New Class Name : " + newClass);

			boolean newClassPresence = util.verifyObjectPresentReturnsBool(cwp.getWebElementNewClass(newClass));

			if (newClassPresence) {
				Reporter.log("New Class Name is in metadata screen in workitems page", true);
				ATUReports.add("New Class Name is in metadata screen in workitems page", true);
				Log.info("New Class Name is in metadata screen in workitems page");
				ATUReports.add("Verify Document Duplicate with different class of Folder type of Workitem and uploading files of different formats through Import",
						"",
						"Document Duplicate of Folder type of Workitem with Different Class and uploading files of different formats through Import should be displayed",
						"Document Duplicate of Folder type of Workitem with Different Class and uploading files of different formats through Import is displayed",
						true);
			}
			else {
				Reporter.log("New Class Name is not in metadata screen in workitems page", true);
				Log.info("New Class Name is not in metadata screen in workitems page");
				ATUReports.add("New Class Name is not in metadata screen in workitems page", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("New Class Name is not in metadata screen in workitems page");
			}

			cwp.getMetaDataDialogBoxCloseBtn(workitem + dupStr).click();
			Reporter.log("Meta Data Dialog Box Close Button is clicked", true);
			ATUReports.add("Meta Data Dialog Box Close Button is clicked", true);
			Log.info("Meta Data Dialog Box Close Button is clicked");
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify Folder Workitem Import Different Formats Document Duplicate Different Class Test is failed.",
					true);
			Log.info("Verify Folder Workitem Import Different Formats Document Duplicate Different Class Test is failed.");
			ATUReports.add("Verify Folder Workitem Import Different Formats Document Duplicate Different Class Test is failed.",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Folder Workitem Import Different Formats Document Duplicate Different Class Test is failed.");
		} finally {
			Log.endTestCase("INFO_7534_VerifyFolderWorkitemImportDifferentFormatsDocumentDuplicateDifferentClassTest");
		}
	}
}
