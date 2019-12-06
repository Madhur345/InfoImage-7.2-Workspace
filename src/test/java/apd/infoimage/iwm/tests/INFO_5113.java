package apd.infoimage.iwm.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
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
 * @author PradhanJ
 *
 */
public class INFO_5113 extends SuperClassIWM {

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

	/**
	 * This test method will delete content from a workitem
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true, groups = { "Workitem" })
	public void testDeleteContent() {
		ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "single page document  to Workitem test");
		ATUReports.setAuthorInfo("PradhanJ", "MAY-2018", "0.3");

		try {
			Log.startTestCase("INFO_5113_DeletionOfContentImportNote");
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_5113_DeletionOfContentImportNote");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);

			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Class Name : INFO_5113_DeletionOfContentImportNote", true);
			ATUReports.add("Class Name : INFO_5113_DeletionOfContentImportNote", true);
			Log.info("Class Name : INFO_5113_DeletionOfContentImportNote");

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

			Reporter.log("Selection of Workitem", true);
			ATUReports.add("Selection of Workitem", true);
			Log.info("Selection of Workitem");

			cwp.getWorkItemName(workitem).click();
			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("WorkItem is clicked", true);
			ATUReports.add("WorkItem is clicked", true);
			Log.info("WorkItem is clicked");
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");
			util.wait(time);
			util.waitForPageToLoad();

			wdp.getContentField().click();
			Reporter.log("Content Tab is clicked", true);
			ATUReports.add("Content Tab is clicked", true);
			util.wait(time);

			wdp.getAddNewPageIcon().click();
			Reporter.log("Add New Page is clicked", true);
			ATUReports.add("Add New Page is clicked", true);
			util.wait(time);
			util.waitForPageToLoad();

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);

				// String imagePath = System.getProperty("user.dir") +
				// ExcelLib.getCellValue(xlpath, sheet, 25, 1);
				String tifFilePath = new File("src\\test\\resources").getAbsolutePath();

				String imagePath = tifFilePath + "\\samplePng.PNG";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				Reporter.log("Single Page TIf file is uploaded", true);
				ATUReports.add("Single Page TIf file is uploaded", true);

				// Validate document name in the add new page window
				String fileName = "samplePng.PNG";
				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals(fileName);
				if (docNameInAddNewPageWin) {
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
				util.waitForPageToLoad();
				util.wait(time);
				// Validate the document is listed under content and no of pages
				// in the document

				int contentList = wdp.getContentPageNo();
				if (contentList == 1) {
					Reporter.log("Content is added successfully ", true);
					ATUReports.add("Content is added successfully ", true);
				} else {
					Reporter.log("Adding content failed", true);
					ATUReports.add("Adding content failed", true);
					Assert.fail("Adding content failed");
				}
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}
			util.wait(time);
			util.waitForElementEnabled(wdp.getSelectAllContentBtn());
			// Select all contents and click on the delete icon.

			wdp.getSelectAllContentBtn().click();
			util.wait(time);
			wdp.getDeletePageIcon().click();
			util.waitForPageToLoad();
			util.waitForElementEnabled(wdp.getNoContentsMsgField());
			util.wait(time);
			util.wait(time);
			// Validate whether content is deleted or not

			String contentMsg = wdp.getNoContentsMsgField().getText();
			Reporter.log(contentMsg, true);
			if (contentMsg.contains("No items")) {
				Reporter.log("content is deleted successfully.", true);
				ATUReports.add("content is deleted successfully.", true);
			} else {
				Reporter.log("content is not deleted ", true);
				ATUReports.add("content is not deleted ", true);
				Assert.fail("content is not deleted ");

				Reporter.log("deleting of  single page document failed", true);
				ATUReports.add("deleting of  single page document failed", true);
				Assert.fail("deleting of  single page document test failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Adding single page document  to Workitem test failed", true);
			ATUReports.add("single page document  to Workitem test", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Adding single page document  to Workitem test failed");
		} finally {

			Log.endTestCase("INFO_5113_DeletionOfContentImportNote");
		}
	}

	/**
	 * This test method will delete content from a workitem
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = false, groups = { "Workitem" })
	public void testDeleteImport() {
		Log.startTestCase("Importing  document  to Workitem test");

		ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "deleting of notes");
		ATUReports.setAuthorInfo("PradhanJ", "MAY-2018", "0.3");

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
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 26, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
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
			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);

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
					Reporter.log("Selected tif file name validation is successful", true);
					ATUReports.add("Selected tif file name validation is successful", true);
					Log.info("Selected tif file name validation is successful");
				} else {
					Reporter.log("Selected tif file name validation is failed.", true);
					ATUReports.add("Selected tif file name validation is failed.", true);
					Assert.fail("Selected tif file name validation is failed.");
				}
				util.wait(time);

				wdp.getImportUploadBtn().click();
				Reporter.log("Import Upload Button is clicked", true);
				ATUReports.add("Import Upload Button is clicked", true);
				Log.info("Import Upload Button is clicked");
				util.waitForPageToLoad();

				// Validate the document is listed under content and no of pages
				// in the document
				try {
					int importList = wdp.getContentImportNo();
					if (importList >= 1) {
						Reporter.log("Tif File is imported successfully. ", true);
						ATUReports.add("Tif File is imported successfully. ", true);
						Log.info("Tif File is imported successfully. ");
					} else {
						Reporter.log("Tif File is NOT imported successfully.", true);
						ATUReports.add("Tif File is NOT imported successfully", true);
						Log.info("Tif File is NOT imported successfully");
						Assert.fail("Tif File is NOT imported successfully.");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Tif File is NOT imported successfully.", true);
					ATUReports.add("Tif File is NOT imported successfully.", true);
					Log.info("Tif File is NOT imported successfully");
					Assert.fail("Tif File is NOT imported successfully.");
				}

				// Select all imports and click on the delete icon.

				wdp.getSelectAllContentBtn().click();
				wdp.getDeletePageIcon().click();
				util.wait(time);

				// Validate whether import is deleted or not

				try {
					String contentMsg = wdp.getNoContentsMsgField().getText();
					System.out.println(contentMsg);
					if (contentMsg.contains("No items")) {
						Reporter.log("import is deleted successfully.", true);
					} else {
						Reporter.log("import is not deleted ", true);
						Assert.fail("import is not deleted ");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("deleting of  import failed", true);
					Assert.fail("deleting of  import test failed");
				} finally {
					hp.CreateWorkitemTab();
					cwp.deleteWorkItem(workitem);
				}
			} else {
				Reporter.log("Add new import  window is NOT displayed", true);
				ATUReports.add("Add new import  window is NOT displayed", true);
				Log.info("Add new import  window is NOT displayed");
				Assert.fail("Add new import window is NOT displayed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Importing  document  to Workitem test failed", true);
			ATUReports.add("Importing  document  to Workitem", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("importing document  to Workitem test failed");
		} finally {
			Log.endTestCase("Importing  document  to Workitem");
		}

	}

	/**
	 * This test method will delete content from a workitem
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = false, groups = { "Workitem" })
	public void testDeleteNotes()

	{
		ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "deleting of notes");
		ATUReports.setAuthorInfo("Biswajit", "MAY-2018", "0.3");

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
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

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
			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);

			util.wait(time);

			wdp.Notes("NoteTitle_TF");
			Reporter.log("WorkItem notes added", true);

			util.wait(time);

			// Select the added note, Delete and verify whether note is deleted
			// or not.

			wdp.getNote1().click();
			wdp.getDeleteNoteBtn().click();

			util.wait(time);

			String noNotesMsg = wdp.getNoNotesMsgField().getText();
			if (noNotesMsg.contains("No items")) {
				Reporter.log("Note is deleted successfully.", true);
			} else {
				Reporter.log("Note is NOT deleted ", true);
				Assert.fail("Ddeleting of notes is failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Ddeleting of notes test failed");
		} finally {
			hp.CreateWorkitemTab();
			Log.endTestCase("deleting of notes test");

		}
	}
}