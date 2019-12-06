package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;
//import org.apache.xmlbeans.impl.jam.JClass;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebElement;
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
 * @author SumanGaK - 27-Jul-2018 
 * INFO-12147 
 * This class will Perform Document Duplicate operation for a document type of workitem which is
 * retrieved from the default queue , add contents,imports and delete duplicated workitem
 **/
public class INFO_12147 extends SuperClassIWM {

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
	public void testPerformDeletionOfDuplicateDocumentWorkitemUsingDocumentDuplicate() {

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-12147 is for Performing Document Duplicate operation for a document type of workitem which is retrieved from the default queue , add contents,imports and delete duplicated workitem ");
		ATUReports.setAuthorInfo("Suman", "JUL-2018", "0.3");

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
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Class Name : PerformDeletionOfDuplicateDocumentWorkitemUsingDocumentDuplicateTest", true);
			ATUReports.add("Class Name : PerformDeletionOfDuplicateDocumentWorkitemUsingDocumentDuplicateTest", true);
			Log.info("Class Name : PerformDeletionOfDuplicateDocumentWorkitemUsingDocumentDuplicateTest");

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

			util.wait(time);

			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			cwp.sendWorkItemToDefaultQueue(workitem);

			util.waitForPageToLoad();
			util.wait(time);

			Reporter.log("Retrieving Workitem From Inbox", true);
			ATUReports.add("Retrieving Workitem From Inbox", true);
			Log.info("Retrieving Workitem From Inbox");

			ip.retrieveWorkItem(workitem);
			util.wait(time);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItem is clicked", true);
			ATUReports.add("WorkItem is clicked", true);
			Log.info("WorkItem is clicked");
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");
			util.wait(time);


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
			Reporter.log("Workitem to be deleted is clicked", true);
			ATUReports.add("Workitem to be deleted is clicked", true);
			Log.info("Workitem to be deleted is clicked");

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

					WebElement deletedWorkitem = cwp.getWorkItemName(workitem+dupStr);
					boolean deletedWItemPresent = util.verifyObjectPresentReturnsBool(deletedWorkitem);
					if(deletedWItemPresent) {
						Reporter.log("WorkItem is not deleted", true);
						ATUReports.add("WorkItem is not deleted", true);
						Log.info("WorkItem is not deleted");
						Assert.fail("WorkItem is not deleted");
					} else {
						Reporter.log("workItem is deleted successfully", true);
						ATUReports.add("workItem is deleted successfully", true);
						Log.info("workItem is deleted successfully");
					}
					/*}

				else {
					Reporter.log("Add new import  window is NOT displayed", true);
					ATUReports.add("Add new import  window is NOT displayed", true);
					Log.info("Add new import  window is NOT displayed");
					Assert.fail("Add new import window is NOT displayed");
				}*/



		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("PerformDeletionOfDuplicateDocumentWorkitemUsingDocumentDuplicateTest is failed.", true);
			ATUReports.add("PerformDeletionOfDuplicateDocumentWorkitemUsingDocumentDuplicateTest is failed.",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("PerformDeletionOfDuplicateDocumentWorkitemUsingDocumentDuplicateTest is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO_12147_PerformDeletionOfDuplicateDocumentWorkitemUsingDocumentDuplicateTest");
		}
	}
}
