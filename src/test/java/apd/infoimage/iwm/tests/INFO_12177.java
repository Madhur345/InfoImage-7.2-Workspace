package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
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
 * @author SumanGaK - 01-Aug-2018 
 * INFO-12177 
 * This class will Perform File Document To Folder Workitem,Send Folder Workitem to default queue,retrieve , add contents , imports , Document Duplicate of Document Workitem and delete duplicate
 **/
public class INFO_12177
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
	public void testPerformFileDocumentToFolderRetrievedAddContentsImportsDocumentDuplicateDeleteDuplicate() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO_12177 is for Performing File Document To Folder Workitem,Send Folder Workitem to default queue,retrieve , add contents , imports , Document Duplicate of Document Workitem and delete duplicate");
		ATUReports.setAuthorInfo("Suman", "AUG-2018", "0.3");
		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_12177_PerformFileDocumentToFolderRetrievedAddContentsImportsDocumentDuplicateDeleteDuplicateTest");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String foldstr = ExcelLib.getCellValue(xlpath, sheet, 24, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String folderWorkitem = foldstr + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String folderWorkitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 7, 1);
			String importFileName = ExcelLib.getCellValue(xlpath, sheet, 26, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("INFO_12177_PerformFileDocumentToFolderRetrievedAddContentsImportsDocumentDuplicateDeleteDuplicateTest", true);
			ATUReports.add("INFO_12177_PerformFileDocumentToFolderRetrievedAddContentsImportsDocumentDuplicateDeleteDuplicateTest",	true);
			Log.info("INFO_12177_PerformFileDocumentToFolderRetrievedAddContentsImportsDocumentDuplicateDeleteDuplicateTest");

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

			util.wait(time);
			util.wait(time);

			Reporter.log("Sending folder Workitem to Workflow", true);
			ATUReports.add("Sending folder Workitem to Workflow", true);
			Log.info("Sending folder Workitem to Workflow");
			cwp.sendWorkItemToDefaultQueue(folderWorkitem);

			util.waitForPageToLoad();
			util.wait(time);

			util.waitForPageToLoad();

			Reporter.log("Retrieving Workitem from Workflow", true);
			ATUReports.add("Retrieving Workitem from Workflow", true);
			Log.info("Retrieving Workitem from Workflow");

			ip.retrieveWorkItem(folderWorkitem);

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
				Log.info(workitem + " Successfully filed in folder: " + folderWorkitem);
			} else {
				Reporter.log(workitem + " failed to file in folder: " + folderWorkitem, true);
				ATUReports.add(workitem + " failed to file in folder: " + folderWorkitem, true);
				Assert.fail(workitem + " failed to file in folder: " + folderWorkitem);
			}

			util.waitForPageToLoad();

			Reporter.log("WorkItem is clicked", true);
			ATUReports.add("WorkItem is clicked", true);
			Log.info("WorkItem is clicked");
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");
			util.wait(time);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItem is opened", true);
			ATUReports.add("WorkItem is opened", true);			
			Log.info("WorkItem is opened");			

			wdp.getContentField().click();

			util.wait(time);

			wdp.getAddNewPageIcon().click();

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				util.wait(time);
				util.wait(time);

				String contentImagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 21, 1);
				Reporter.log("img path " + contentImagePath, true);
				ATUReports.add("img path " + contentImagePath, true);
				Log.info("img path " + contentImagePath);

				wdp.getContentUploadField().sendKeys(contentImagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals(fileName);
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected singlepage file name validation successful.", true);
					ATUReports.add("Selected singlepage file name validation successful.", true);
					Log.info("Selected singlepage file name validation successful.");
				} else {
					Reporter.log("Selected singlepage file name validation failed.", true);
					ATUReports.add("Selected singlepage file name validation failed.", true);
					Assert.fail("Selected singlepage file name validation failed.");
				}
				util.wait(time);
				util.wait(time);
				util.wait(time);
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				util.wait(time);
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

					String imagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 25, 1);
					Reporter.log("img path " + imagePath, true);
					ATUReports.add("img path " + imagePath, true);
					Log.info("img path " + imagePath);
					wdp.getImportUploadField().sendKeys(imagePath);
					util.waitForElementEnabled(wdp.getSelectedImportFile());
					util.wait(time);

					boolean docNameInAddNewImportWin = wdp.getSelectedImportFile().getText().equals(importFileName);
					if (docNameInAddNewImportWin) {
						Reporter.log("Selected tif file name validation is successful", true);
						ATUReports.add("Selected tif file name validation is successful", true);
						Log.info("Selected tif file name validation is successful");
					} else {
						Reporter.log("Selected tif file name validation is failed.", true);
						ATUReports.add("Selected tif file name validation is failed.", true);
						Assert.fail("Selected tif file name validation is failed.");
					}
					util.wait(time);
					util.wait(time);
					util.wait(time);
					wdp.getImportUploadBtn().click();
					Reporter.log("Import Upload Button is clicked", true);
					ATUReports.add("Import Upload Button is clicked", true);
					Log.info("Import Upload Button is clicked");
					util.waitForPageToLoad();
					util.wait(time);
					//util.jclick(wdp.getActionsDropDown());
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
						ATUReports.add("Verify Document Workitem Retrieve From Inbox Different Class Document Duplicate Test",
								"",
								"Document Duplicate of Document Workitem Retrieved From Inbox with Different Class should be displayed",
								"Document Duplicate of Document Workitem Retrieved From Inbox with Different Class is displayed",
								true);
					} else {
						Reporter.log("Duplicate Workitem is not present in grid", true);
						ATUReports.add("Duplicate Workitem is not present in grid", LogAs.FAILED,
								new CaptureScreen(ScreenshotOf.DESKTOP));
						Assert.fail("Duplicate Workitem is not present in grid ");
					}

					hp.getSearchFieldInWorkitemTab().sendKeys(Keys.ENTER);
					util.wait(time);

					cwp.getCheckBoxWorkItemName(workitem+dupStr).click();
					Reporter.log("CheckBox of Workitem to be deleted is clicked", true);
					ATUReports.add("CheckBox of Workitem to be deleted is clicked", true);
					Log.info("CheckBox of Workitem to be deleted is clicked");

					util.wait(time);

					cwp.getDeleteWorkitemBtn().click();
					util.wait(time);

					boolean deleteWIWinPresent = util.verifyObjectPresentReturnsBool(cwp.getDeleteWItemWin());
					if (deleteWIWinPresent) {
						Reporter.log("Delete Workitem window is present", true);
						ATUReports.add("Delete Workitem window is present.", true);
						Log.info("Delete Workitem window is present.");
					} else {
						Reporter.log("Delete Workitem window box not present", true);
						ATUReports.add("Delete Workitem window box not present", true);
						Assert.fail("Delete Workitem window box not present ");
					}

					/* perform the desk delete
					if (util.verifyObjectPresentReturnsBool(cwp.getDeskDeleteRadioBtn())) {
						cwp.getDeskDeleteRadioBtn().click();
						Reporter.log("Delete Radio Button is clicked", true);
					}*/
					util.wait(time);
					cwp.getDeleteWItemAccept().click();
					Reporter.log("Delete WotkItem Accept Button is clicked", true);
					ATUReports.add("Delete WotkItem Accept Button is clicked", true);
					Log.info("Delete WotkItem Accept Button is clicked");

					util.wait(time);
					Reporter.log("Workitem is Deleted Successfully.", true);
					ATUReports.add("Workitem is Deleted Successfully.", true);
					Log.info("Workitem is Deleted Successfully.");

					util.waitForPageToLoad();
					/*cwp.searchByNameInWorkitemList(workitem+dupStr);
					util.wait(time);

					boolean deletedWItemPresent = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem+dupStr));
					if(deletedWItemPresent) {
						Reporter.log("WorkItem is not deleted", true);
						ATUReports.add("WorkItem is not deleted", true);
						Log.info("WorkItem is not deleted");
						Assert.fail("WorkItem is not deleted");
					} else {
						Reporter.log("workItem is deleted successfully", true);
						ATUReports.add("workItem is deleted successfully", true);
						Log.info("workItem is deleted successfully");
					}*/
				}

				else {
					Reporter.log("Add new import  window is NOT displayed", true);
					ATUReports.add("Add new import  window is NOT displayed", true);
					Log.info("Add new import  window is NOT displayed");
					Assert.fail("Add new import window is NOT displayed");
				}
			}

			else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Log.info("Add new Page window is NOT displayed");
				Assert.fail("Add new Page window is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("PerformFileDocumentToFolderRetrievedAddContentsImportsDocumentDuplicateDeleteDuplicateTest is failed.", true);
			ATUReports.add("PerformFileDocumentToFolderRetrievedAddContentsImportsDocumentDuplicateDeleteDuplicateTest is failed.",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("PerformFileDocumentToFolderRetrievedAddContentsImportsDocumentDuplicateDeleteDuplicateTest is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO_12177_PerformFileDocumentToFolderRetrievedAddContentsImportsDocumentDuplicateDeleteDuplicateTest");
		}
	}
	
}