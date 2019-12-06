package apd.infoimage.iwm.tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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
 * @author PradhanJ INFO-8723 Performing 'Document splits' operation using
 *         multiple images with different formats and same class for a document
 *         type of workitem which is retrieved from the workstep
 */
public class INFO_8723 extends SuperClassIWM {
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
	public void testDocSplitUsingSameClassAfterRetrieving() {
		Log.startTestCase("INFO_8723_DocSplitUsingSameClassAfterRetrieving");
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-8723  is To verify 'Document split' operation using multpile images "
						+ "with different formats and same class for a document type of workitem which is retrieved from the workstep");
		ATUReports.setAuthorInfo("Jayashri", "APR-2018", "0.3");
		String xlpath = "src\\test\\resources\\testData.xlsx";
		String sheet = "Sheet1";
		String sheet2 = "Sheet_J";
		String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
		String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
		String workitem = str + util.getSysDate(0, date);
		String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
		String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
		String fileName = ExcelLib.getCellValue(xlpath, sheet, 18, 1);
		String newClassName = ExcelLib.getCellValue(xlpath, sheet2, 1, 1);
		String newWitemStr = ExcelLib.getCellValue(xlpath, sheet2, 3, 1);
		String newWitem = newWitemStr + util.getSysDate(0, date);
		int time = 3000;
		try {
			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Reporter.log("New Class Name is " + newClassName, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("Sending Workitem to Workflow", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			cwp.sendWorkItemToDefaultQueue(workitem);
			util.waitForPageToLoad();
			Thread.sleep(2000);
			Reporter.log("Retrieving Workitem From Inbox", true);
			ATUReports.add("Retrieving Workitem From Inbox", true);
			ip.retrieveWorkItem(workitem);
			util.waitForPageToLoad();
			util.wait(time);
			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView is displayed", true);
			ATUReports.add("WorkItemDetailView is displayed", true);
			util.waitForPageToLoad();
			util.wait(time);
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
				String imagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 5, 1);
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				wdp.getContentUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);
				Reporter.log("multipage TIf file is uploaded", true);
				ATUReports.add("multipage TIf file is uploaded", true);
				// Validate document name in the add new page window
				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals(fileName);
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected multipage file name validation successful.", true);
					ATUReports.add("Selected multipage file name validation successful.", true);
				} else {
					Reporter.log("Selected multipage file name validation failed.", true);
					ATUReports.add("Selected multipage file name validation failed.", true);
					Assert.fail("Selected multipage file name validation failed.");
				}
				util.waitForPageToLoad();
				util.wait(time);
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				util.waitForPageToLoad();
				// Validate the document is listed under content and no of pages
				// in the document
				int contentList = wdp.getContentPageNo();
				Reporter.log("No of Pages in the multiple page tif file is : " + contentList, true);
				ATUReports.add("No of Pages in the multiple page tif file is : " + contentList, true);
				util.waitForPageToLoad();
				util.wait(time);
				wdp.getSecondPageCheckbox().click();
				util.wait(time);
				util.wait(time);
				wdp.getThirdPageCheckbox().click();
				Reporter.log("Second and Fourth page Checkbox is clicked", true);
				ATUReports.add("Second and Fourth page Checkbox is clicked", true);
				util.wait(time);
				wdp.getSplitIcon().click();
				Reporter.log("Split Pages Icon is clicked", true);
				ATUReports.add("Split Pages Icon is clicked", true);
				util.waitForPageToLoad();
				util.wait(time);
				util.wait(time);
				boolean splitPageWindowPresent = util.verifyObjectPresentReturnsBool(wdp.getProceedButton());
				if (splitPageWindowPresent) {
					Reporter.log("Split workitem window is displayed", true);
					ATUReports.add("Split workitem window is displayed", true);
					ATUReports.add("Verify Split page in workitem using shortkut key", "",
							"Split workitem window should be displayed", "Split workitem window is displayed", true);
				} else {
					Reporter.log("Split workitem window is not displayed", true);
					ATUReports.add("Split workitem window is not displayed", true);
					Assert.fail("Split workitem window is not displayed");
				}
				String splitwitem = wdp.getSplitWitemName();
				String secondSplitWitem = wdp.getSplitSecondWitemName();
				wdp.getProceedButton().click();
				util.waitForElementPresent(wdp.getSplitPageWindow());
				wdp.getSplitWitemNameTextbox().clear();
				wdp.getSplitWitemNameTextbox().sendKeys(newWitem);
				wdp.getSplitButton().click();
				ATUReports.add("Split button is clicked", true);
				util.waitForPageToLoad();
				util.wait(time);
				String newSpliwtItem1 = newWitem + "_1";
				String newSpliwtItem2 = newWitem + "_2";
				int contentListRemain = wdp.getContentPageNo();
				if (contentListRemain == 1) {
					Reporter.log("Pages are removed from 2nd page and only first page is remaining ", true);
					ATUReports.add("Pages are removed from 2nd page and only first page is remaining", true);

				} else {
					Reporter.log("Pages are not removed from 2nd page ", true);
					ATUReports.add("Pages are not removed from 2nd page ", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Document split by adding multiple page test failed");
				}
				util.wait(time);
				wdp.getCloseWorkitemIcon(workitem).click();
				util.waitForElementEnabled(cwp.getCreateWorkitem_btn());
				util.wait(time);
				cwp.searchByNameInWorkitemList(newSpliwtItem1);
				cwp.getWorkItemName(newSpliwtItem1).click();
				util.waitForPageToLoad();
				util.wait(time);
				int contentListSplitted = wdp.getContentPageNo();
				if (contentListSplitted == 1) {
					Reporter.log("Pages are splitted from 2nd page ", true);
					ATUReports.add("Verify document Split by adding multiple pages and same class test is successfull",
							true);
				} else {
					Reporter.log("Pages are not splitted from 2nd page ", true);
					ATUReports.add("Pages are not splitted from 2nd page ", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Document split by adding multiple pages and same class test failed");
				}
				util.wait(time);
				wdp.getCloseWorkitemIcon(newSpliwtItem1).click();
				util.waitForElementEnabled(cwp.getCreateWorkitem_btn());
				util.wait(time);
				cwp.searchByNameInWorkitemList(newSpliwtItem2);
				cwp.getWorkItemName(newSpliwtItem2).click();
				util.waitForPageToLoad();
				util.wait(time);
				int contentListInSecondSplitted = wdp.getContentPageNo();
				if (contentListInSecondSplitted == 3) {
					Reporter.log("Pages are splitted from 4th page ", true);
					ATUReports.add("Verify document Split by adding form fields and multiple pages "
							+ "using same class after retriving from workstep test is successfull", true);

				} else {
					Reporter.log("Pages are not splitted from 4th page ", true);
					ATUReports.add("Pages are not splitted from 4th page ", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Document split by adding multiple page test failed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify document Split for a document type of "
					+ "workitem using same class which is retrieved from the workstep test is failed", true);
			ATUReports.add("failed to execute test INFO_8723_DocSplitUsingSameClassAfterRetrieving", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));

			ATUReports.add(
					"Verify document Split for a document"
							+ " type of workitem with same class which is retrieved from the workstep test is failed",
					true);
			Assert.fail("Verify document Split for a document "
					+ "type of workitem with same class which is retrieved from the workstep test is failed");
		} finally {
			Log.endTestCase("INFO_8723_DocSplitUsingSameClassAfterRetrieving");
		}
	}

}
