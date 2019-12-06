package apd.infoimage.iwm.tests;

import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

/**
 * @author Biswajit
 *
 */
public class INFO_5104 extends SuperClassIWM {

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

	@Test(enabled = true, priority = 1, groups = { "Workitem" })
	public void fileDocumentIntoFolder() {

		Log.startTestCase("fileDocumentIntoFolder");
		String documentName = null;
		String folderName = null;
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "file Document Into Folder");
			ATUReports.setAuthorInfo("Biswajit", "MAY-2018", "0.3");

			documentName = "jay" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(documentName, "archive", "Document");
			Reporter.log("Documet: " + documentName + " has been created", true);

			folderName = "jay" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(folderName, "archive", "Folder");
			Reporter.log("Folder: " + folderName + " has been created", true);

			Thread.sleep(3000);
			cwp.getNameSearchTextbox().clear();
			cwp.searchByNameInWorkitemList(documentName);
			Thread.sleep(3000);
			cwp.getCheckBoxWorkItem(documentName).click();
			Reporter.log("Selected: " + documentName + " to file work item", true);

			cwp.getFileworkitemBtn().click();
			boolean f = util.verifyObjectPresentReturnsBool(cwp.getFileWorkItem_win());
			if (f) {
				Reporter.log("File Work item dilog box is open", true);
				ATUReports.add("File Work item dilog box is open", true);
			} else {
				Reporter.log("File Work item dilog box is not open", true);
				ATUReports.add("File Work item dilog box is not open", true);
				//Assert.fail("File Work item dilog box is not open");
			}

			Select sel = new Select(cwp.getSelectFolderDropDown());
			sel.selectByVisibleText(folderName);
			Reporter.log("Selected destination folder as: " + folderName, true);
			ATUReports.add("Selected destination folder as: " + folderName, true);
		//util.WaitForElementPresent(folderTypeWorkItem);
			Thread.sleep(15000);
			cwp.getFileWorkitemAcceptBtn().click();
			util.waitForPageToLoad();
			Thread.sleep(2000);
			cwp.getWorkItemName(folderName).click();
			util.waitForPageToLoad();

			util.wait(2000);
			f = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(documentName));
			if (f) {
				Reporter.log(documentName + " Successfully filed in folder: " + folderName, true);
				ATUReports.add(documentName + " Successfully filed in folder: " + folderName, true);

			} else {
				Reporter.log(documentName + " failed to file in folder: " + folderName, true);
				ATUReports.add(documentName + " failed to file in folder: " + folderName, true);
				Assert.fail(documentName + " failed to file in folder: " + folderName);
			}
			wdp.getCloseWorkitemIcon(folderName);
			util.waitForPageToLoad();

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute fileDocumentIntoFolder test", true);
			ATUReports.add("execute file Document Into Folder", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute fileDocumentIntoFolder test");
		} finally {
			Log.endTestCase("fileDocumentIntoFolder");
		}
	}

	@Test(enabled = false, priority = 2)
	public void fileFolderIntoFolder() {

		Log.startTestCase("fileFolderIntoFolder");
		String folderName = null;
		String folderName1 = null;
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "file Folder Into Folder");
			ATUReports.setAuthorInfo("Biswajit", "MAY-2018", "0.3");

			folderName1 = "jay" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(folderName1, "archive", "Folder");
			Reporter.log("Folder: " + folderName1 + " has been created", true);
			ATUReports.add("Folder: " + folderName1 + " has been created", true);

			folderName = "jay" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(folderName, "archive", "Folder");
			Reporter.log("Folder: " + folderName + " has been created", true);
			ATUReports.add("Folder: " + folderName + " has been created", true);

			cwp.searchByNameInWorkitemList(folderName1);
			Thread.sleep(3000);
			cwp.getCheckBoxWorkItem(folderName1).click();
			Reporter.log("Selected: " + folderName1 + " to file work item", true);
			ATUReports.add("Selected: " + folderName1 + " to file work item", true);

			cwp.getFileworkitemBtn().click();
			boolean f = util.verifyObjectPresentReturnsBool(cwp.getFileWorkItem_win());
			if (f) {
				Reporter.log("File Work item dilog box is open", true);
				ATUReports.add("File Work item dilog box is open", true);
			} else {
				Reporter.log("File Work item dilog box is not open", true);
				ATUReports.add("File Work item dilog box is not open", true);
				Assert.fail("File Work item dilog box is not open");
			}

			Select sel = new Select(cwp.getSelectFolderDropDown());
			sel.selectByVisibleText(folderName);
			Reporter.log("Selected destination folder as: " + folderName, true);
			ATUReports.add("Selected destination folder as: " + folderName, true);

			Thread.sleep(3000);
			cwp.getFileWorkitemAcceptBtn().click();
			util.waitForPageToLoad();

			cwp.getWorkItemName(folderName).click();
			util.waitForPageToLoad();
			Thread.sleep(2000);

			util.wait(2000);
			f = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(folderName1));
			if (f) {
				Reporter.log(folderName1 + " Successfully filed in folder: " + folderName, true);
				ATUReports.add(folderName1 + " Successfully filed in folder: " + folderName, true);
			} else {
				Reporter.log(folderName1 + " failed to file in folder: " + folderName, true);
				ATUReports.add(folderName1 + " failed to file in folder: " + folderName, true);
				Assert.fail(folderName1 + " failed to file in folder: " + folderName);
			}
			wdp.getCloseWorkitemIcon(folderName);
			util.waitForPageToLoad();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute fileDocumentIntoFolder test", true);
			ATUReports.add("execute file Folder Into Folder", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute fileDocumentIntoFolder test");
		} finally {
			Log.endTestCase("fileFolderIntoFolder");
		}
	}

	@Test(enabled = false, priority = 3, groups = { "Workitem" })
	public void clickFileWorkitemClose() {

		Log.startTestCase("clickFileWorkitemClose");
		String folderName1 = null;
		String folderName = null;
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "click File Workitem Close");
			ATUReports.setAuthorInfo("Biswajit", "MAY-2018", "0.3");

			folderName1 = "jay" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(folderName1, "archive", "Folder");
			Reporter.log("Folder: " + folderName1 + " has been created", true);
			ATUReports.add("Folder: " + folderName1 + " has been created", true);

			folderName = "jay" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(folderName, "archive", "Folder");
			Reporter.log("Folder: " + folderName + " has been created", true);
			ATUReports.add("Folder: " + folderName1 + " has been created", true);

			cwp.searchByNameInWorkitemList(folderName1);
			Thread.sleep(3000);
			cwp.getCheckBoxWorkItem(folderName1).click();
			Reporter.log("Selected: " + folderName1 + " to file work item", true);
			ATUReports.add("Selected: " + folderName1 + " to file work item", true);

			Thread.sleep(2000);
			cwp.getFileworkitemBtn().click();
			Thread.sleep(2000);
			boolean f = util.verifyObjectPresentReturnsBool(cwp.getFileWorkItem_win());
			if (f) {
				Reporter.log("File Work item dilog box is open", true);
				ATUReports.add("File Work item dilog box is open", true);
			} else {
				Reporter.log("File Work item dilog box is not open", true);
				ATUReports.add("File Work item dilog box is not open", true);
				Assert.fail("File Work item dilog box is not open");
			}

			Select sel = new Select(cwp.getSelectFolderDropDown());
			sel.selectByVisibleText(folderName);
			Reporter.log("Selected destination folder as: " + folderName, true);
			ATUReports.add("Selected destination folder as: " + folderName, true);

			cwp.getCloseFileWorkitemBtn().click();
			Reporter.log("Clicked on close button ", true);
			ATUReports.add("Clicked on close button ", true);

			cwp.getWorkItemName(folderName).click();
			util.waitForPageToLoad();

			try {
				f = cwp.getWorkItemName(folderName1).isDisplayed();
				if (f) {
					Reporter.log(folderName1 + " filed in folder: " + folderName + " when clicked on close", true);
					ATUReports.add(folderName1 + " filed in folder: " + folderName + " when clicked on close", true);
					Assert.fail(folderName1 + " filed in folder: " + folderName + " when clicked on close");
				}
			} catch (Exception e) {
				Reporter.log(folderName1 + " failed to file in folder: " + folderName + " when clicked on close ",
						true);
				ATUReports.add(folderName1 + " failed to file in folder: " + folderName + " when clicked on close ",
						true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute fileDocumentIntoFolder test", true);
			ATUReports.add("execute click File Workitem Close", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute fileDocumentIntoFolder test");
		} finally {
			Log.endTestCase("clickFileWorkitemClose");
		}
	}
}
