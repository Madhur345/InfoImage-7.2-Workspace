package apd.infoimage.iwm.tests;

import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import atu.testng.reports.ATUReports;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author Biswajit
 *
 */
public class INFO_5098 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1, groups = { "Workitem" })
	public void deletingWorkitemOfDocumentTypeDeskDelete() {
		String documentName;
		Log.startTestCase("deletingWorkitemOfDocumentTypeDeskDelete");
		try {
			ATUReports.setTestCaseReqCoverage(
					"This Scenario is To verify" + "deleting Workitem Of Document Type Desk Delete");
			ATUReports.setAuthorInfo("Biswajit", "MAY-2018", "0.3");

			documentName = "vin" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(documentName, "archive", "Document");
			Reporter.log("Documet: " + documentName + " has been created", true);

			Thread.sleep(2000);
			cwp.getCheckBoxWorkItem(documentName).click();

			// Click on the delete workitem button
			cwp.getDeleteWorkitemBtn().click();
			Thread.sleep(2000);

			// Check whether delete workitem window is appearing or not
			boolean deleteWIWinPresent = util.verifyObjectPresentReturnsBool(cwp.getDeleteWItemWin());
			if (deleteWIWinPresent) {
				Reporter.log("Delete Workitem window is present", true);
				ATUReports.add("Delete Workitem window is present.", true);
			} else {
				Reporter.log("Delete Workitem window box not present", true);
				ATUReports.add("Delete Workitem window box not present", true);
				Assert.fail("Delete Workitem window box not present ");
			}

			// perform the desk delete
			if (util.verifyObjectPresentReturnsBool(cwp.getDeskDeleteRadioBtn())) {
				// perform rhe desk delete
				cwp.getDeskDeleteRadioBtn().click();
			}
			cwp.getDeleteWItemAccept().click();

			/*
			 * cwp.getDeskDeleteRadioBtn().click(); cwp.getDeleteWItemAccept().click();
			 */
			util.waitForPageToLoad();
			cwp.searchByNameInWorkitemList(documentName);
			Thread.sleep(2000);

			try {
				WebElement deletedWorkitem = cwp.getWorkItemName(documentName);
				boolean deletedWItemPresent = util.verifyObjectPresentReturnsBool(deletedWorkitem);
				if (deletedWItemPresent) {
					Reporter.log("WorkItem is not deleted", true);
					ATUReports.add("WorkItem is not deleted", true);
					Assert.fail("WorkItem is not deleted");
				}
			} catch (Exception e1) {
				Reporter.log("workItem is deleted successfully", true);
				ATUReports.add("workItem is deleted successfully", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute deletingWorkitemOfDocumentTypeDeskDelete test", true);
			ATUReports.add("failed to execute deletingWorkitemOfDocumentTypeDeskDelete test", true);
			Assert.fail("failed to execute deletingWorkitemOfDocumentTypeDeskDelete test");
		} finally {
			Log.endTestCase("deletingWorkitemOfDocumentTypeDeskDelete");
		}
	}

	@Test(enabled = true, priority = 2)
	public void deletingWorkitemOfFolderTypeDeskDelete() {

		Log.startTestCase("deletingWorkitemOfFolderTypeDeskDelete");
		String folderName;
		try {
			folderName = "vin" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(folderName, "archive", "Folder");
			Reporter.log("Folder: " + folderName + " has been created", true);

			Thread.sleep(2000);
			cwp.getCheckBoxWorkItem(folderName).click();

			// Click on the delete workitem button
			cwp.getDeleteWorkitemBtn().click();
			Thread.sleep(2000);

			// Check whether delete workitem window is appearing or not
			boolean deleteWIWinPresent = util.verifyObjectPresentReturnsBool(cwp.getDeleteWItemWin());
			if (deleteWIWinPresent) {
				Reporter.log("Delete Workitem window is present", true);
				ATUReports.add("Delete Workitem window is present", true);
			} else {
				Reporter.log("Delete Workitem window box not present", true);
				Assert.fail("Delete Workitem window box not present ");
			}

			// perform the desk delete
			if (util.verifyObjectPresentReturnsBool(cwp.getDeskDeleteRadioBtn())) {
				// perform rhe desk delete
				cwp.getDeskDeleteRadioBtn().click();
			}
			cwp.getDeleteWItemAccept().click();

			util.waitForPageToLoad();
			cwp.searchByNameInWorkitemList(folderName);
			Thread.sleep(2000);

			try {
				WebElement deletedWorkitem = cwp.getWorkItemName(folderName);
				boolean deletedWItemPresent = util.verifyObjectPresentReturnsBool(deletedWorkitem);
				if (deletedWItemPresent) {
					Reporter.log("WorkItem is not deleted", true);
					ATUReports.add("WorkItem is not deleted", true);
					Assert.fail("WorkItem is not deleted");
				}
			} catch (Exception e1) {
				Reporter.log("workItem is deleted successfully", true);
				ATUReports.add("workItem is deleted successfully", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute deletingWorkitemOfFolderTypeDeskDelete test", true);
			ATUReports.add("failed to execute deletingWorkitemOfFolderTypeDeskDelete test", true);
			Assert.fail("failed to execute deletingWorkitemOfFolderTypeDeskDelete test");
		} finally {
			Log.endTestCase("deletingWorkitemOfFolderTypeDeskDelete");
		}
	}

	@Test(enabled = true, priority = 3)
	public void deletingMultipleWorkitemOfDocumentTypeDeskDelete() {
		Log.startTestCase("deletingMultipleWorkitemOfDocumentTypeDeskDelete");

		String documentName;
		String documentName1;
		try {
			documentName = "vin" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(documentName, "archive", "Document");
			Reporter.log("Documet: " + documentName + " has been created", true);

			documentName1 = "vin" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(documentName1, "archive", "Document");
			Reporter.log("Documet: " + documentName1 + " has been created", true);

			Driver.driver.navigate().refresh();
			util.waitForPageToLoad();

			Select sel = new Select(hp.getNumOfRowsDropDown());
			sel.selectByValue("100");

			Thread.sleep(5000);
			cwp.getCheckBoxWorkItem(documentName).click();
			Thread.sleep(2000);
			cwp.getCheckBoxWorkItem(documentName1).click();

			// Click on the delete workitem button
			cwp.getDeleteWorkitemBtn().click();
			Thread.sleep(5000);

			// Check whether delete workitem window is appearing or not
			boolean deleteWIWinPresent = util.verifyObjectPresentReturnsBool(cwp.getDeleteWItemWin());
			if (deleteWIWinPresent) {
				Reporter.log("Delete Workitem window is present", true);
				ATUReports.add("failed to execute deletingWorkitemOfFolderTypeDeskDelete test", true);
			} else {
				Reporter.log("Delete Workitem window box not present", true);
				ATUReports.add("Delete Workitem window box not present", true);
				Assert.fail("Delete Workitem window box not present ");
			}

			// perform the desk delete
			if (util.verifyObjectPresentReturnsBool(cwp.getDeskDeleteRadioBtn())) {
				// perform rhe desk delete
				cwp.getDeskDeleteRadioBtn().click();
			}
			cwp.getDeleteWItemAccept().click();

			/*
			 * cwp.getDeskDeleteRadioBtn().click(); cwp.getDeleteWItemAccept().click();
			 */
			util.waitForPageToLoad();
			sel.selectByValue("100");
			Thread.sleep(5000);

			try {
				WebElement deletedWorkitem = cwp.getWorkItemName(documentName);
				WebElement deletedWorkitem1 = cwp.getWorkItemName(documentName1);
				boolean deletedWItemPresent = util.verifyObjectPresentReturnsBool(deletedWorkitem);
				boolean deletedWItemPresent1 = util.verifyObjectPresentReturnsBool(deletedWorkitem1);

				if (deletedWItemPresent && deletedWItemPresent1) {
					Reporter.log("multiple WorkItems of document type are not deleted", true);
					ATUReports.add("Delete Workitem window box not present", true);
					Assert.fail("multiple WorkItems of document type are not deleted");
				}
			} catch (Exception e1) {
				Reporter.log("multiple WorkItems of document type are deleted successfully", true);
				ATUReports.add("multiple WorkItems of document type are deleted successfully", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute deletingMultipleWorkitemOfDocumentTypeDeskDelete test", true);
			ATUReports.add("failed to execute deletingMultipleWorkitemOfDocumentTypeDeskDelete test", true);
			Assert.fail("failed to execute deletingMultipleWorkitemOfDocumentTypeDeskDelete test");
		} finally {
			Log.endTestCase("deletingMultipleWorkitemOfDocumentTypeDeskDelete");
		}
	}

	@Test(enabled = true, priority = 4)
	public void deletingMultipleWorkitemOfFolderTypeDeskDelete() {

		Log.startTestCase("deletingMultipleWorkitemOfFolderTypeDeskDelete");
		String folderName;
		String folderName1;
		try {
			folderName = "vin" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(folderName, "archive", "Folder");
			Reporter.log("Folder: " + folderName + " has been created", true);

			folderName1 = "vin" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(folderName1, "archive", "Folder");
			Reporter.log("Folder: " + folderName1 + " has been created", true);

			Driver.driver.navigate().refresh();
			util.waitForPageToLoad();

			Select sel = new Select(hp.getNumOfRowsDropDown());
			sel.selectByValue("100");

			Thread.sleep(5000);
			cwp.getCheckBoxWorkItem(folderName).click();
			Thread.sleep(2000);
			cwp.getCheckBoxWorkItem(folderName1).click();

			// Click on the delete workitem button
			cwp.getDeleteWorkitemBtn().click();
			Thread.sleep(5000);

			// Check whether delete workitem window is appearing or not
			boolean deleteWIWinPresent = util.verifyObjectPresentReturnsBool(cwp.getDeleteWItemWin());
			if (deleteWIWinPresent) {
				Reporter.log("Delete Workitem window is present", true);
				ATUReports.add("Delete Workitem window is present", true);
			} else {
				Reporter.log("Delete Workitem window box not present", true);
				ATUReports.add("Delete Workitem window box not present", true);
				Assert.fail("Delete Workitem window box not present ");
			}

			// perform the desk delete
			if (util.verifyObjectPresentReturnsBool(cwp.getDeskDeleteRadioBtn())) {
				// perform rhe desk delete
				cwp.getDeskDeleteRadioBtn().click();
			}
			cwp.getDeleteWItemAccept().click();

			/*
			 * cwp.getDeskDeleteRadioBtn().click(); cwp.getDeleteWItemAccept().click();
			 */
			util.waitForPageToLoad();
			sel.selectByValue("100");
			Thread.sleep(5000);

			try {
				WebElement deletedWorkitem = cwp.getWorkItemName(folderName);
				WebElement deletedWorkitem1 = cwp.getWorkItemName(folderName1);
				boolean deletedWItemPresent = util.verifyObjectPresentReturnsBool(deletedWorkitem);
				boolean deletedWItemPresent1 = util.verifyObjectPresentReturnsBool(deletedWorkitem1);

				if (deletedWItemPresent && deletedWItemPresent1) {
					Reporter.log("multiple WorkItems of Folder type are not deleted", true);
					ATUReports.add("multiple WorkItems of Folder type are not deleted", true);
					Assert.fail("multiple WorkItems of Folder type are not deleted");
				}
			} catch (Exception e1) {
				Reporter.log("multiple WorkItems of Folder type are deleted successfully", true);
				ATUReports.add("multiple WorkItems of Folder type are deleted successfully", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute deletingMultipleWorkitemOfDocumentTypeDeskDelete test", true);
			Log.error(e.getMessage());
			ATUReports.add("failed to execute deletingMultipleWorkitemOfDocumentTypeDeskDelete test", true);
			Assert.fail("failed to execute deletingMultipleWorkitemOfDocumentTypeDeskDelete test");
		} finally {
			Log.endTestCase("deletingMultipleWorkitemOfFolderTypeDeskDelete");
		}
	}

	@Test(enabled = false, priority = 5)
	public void abortingDeletionOfWorkitemUsingClose() {

		Log.startTestCase("abortingDeletionOfWorkitemUsingClose");
		String documentName = null;
		try {
			documentName = "vin" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(documentName, "archive", "Document");
			Reporter.log("Documet: " + documentName + " has been created", true);

			// sel.selectByValue("100");

			cwp.searchByNameInWorkitemList(documentName);
			Thread.sleep(5000);
			cwp.getCheckBoxWorkItem(documentName).click();
			Thread.sleep(5000);
			// Click on the delete workitem button
			cwp.getDeleteWorkitemBtn().click();
			Thread.sleep(5000);

			// Check whether delete workitem window is appearing or not
			boolean deleteWIWinPresent = util.verifyObjectPresentReturnsBool(cwp.getDeleteWItemWin());
			if (deleteWIWinPresent) {
				Reporter.log("Delete Workitem window is present", true);
			} else {
				Reporter.log("Delete Workitem window box not present", true);
				ATUReports.add("Delete Workitem window box not present", true);
				Assert.fail("Delete Workitem window box not present ");
			}

			// perform the desk delete
			if (util.verifyObjectPresentReturnsBool(cwp.getDeskDeleteRadioBtn())) {
				// perform rhe desk delete
				cwp.getDeskDeleteRadioBtn().click();
			}
			cwp.getDeleteWItemClose().click();

			/*
			 * cwp.getDeskDeleteRadioBtn().click(); cwp.getDeleteWItemClose().click();
			 */
			util.waitForPageToLoad();
			Select sel = new Select(hp.getNumOfRowsDropDown());
			sel.selectByValue("100");
			Thread.sleep(5000);

			WebElement deletedWorkitem = cwp.getWorkItemName(documentName);
			boolean deletedWItemPresent = util.verifyObjectPresentReturnsBool(deletedWorkitem);
			if (deletedWItemPresent) {
				Reporter.log("WorkItem is not deleted When clicked on close as expected", true);
				ATUReports.add("WorkItem is not deleted When clicked on close as expected", true);
			} else {
				Reporter.log("WorkItem is deleted When clicked on close", true);
				ATUReports.add("WorkItem is deleted When clicked on close", true);
				Assert.fail("WorkItem is deleted When clicked on close");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute abortingDeletionOfWorkitemUsingClose test", true);
			ATUReports.add("failed to execute abortingDeletionOfWorkitemUsingClose test", true);
			Assert.fail("failed to execute abortingDeletionOfWorkitemUsingClose test");
		} finally {

			Log.endTestCase("abortingDeletionOfWorkitemUsingClose");
		}
	}

}
