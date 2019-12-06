package apd.infoimage.iwm.tests;

import java.io.File;

import org.openqa.selenium.support.ui.Select;
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
 * @author PradhanJ INFO-5772 Performing 'Document Duplicate' operation by
 *         enabling 'retain form data' for a document/folder type of workitem
 *         without any form fields values
 */
public class INFO_7552 extends SuperClassIWM {

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
	public void testDocDuplicateByEnablingRetainFormData() {
		Log.startTestCase("INFO_7552_DocDuplicateByEnablingRetainFormData");

		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-7552 is Performing 'Document Duplicate' operation by enabling 'retain form data' "
						+ "for a document/folder type of workitem without any form fields values");
		ATUReports.setAuthorInfo("Jayashri", "MAY-2018", "0.3");

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

			cwp.searchByNameInWorkitemList(workitem);
			cwp.getWorkItemName(workitem).click();
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

				String tifFilePath = new File("src\\test\\resources").getAbsolutePath();
				String imagePath = tifFilePath + "\\multiPage.tif";

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

				wdp.getFourthPageCheckbox().click();
				Reporter.log("Fourth page Checkbox is clicked", true);
				ATUReports.add("Fourth page Checkbox is clicked", true);
				util.wait(time);

				wdp.getSplitIcon().click();
				Reporter.log("Split Pages Icon is clicked", true);
				ATUReports.add("Split Pages Icon is clicked", true);
				util.waitForPageToLoad();
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

				wdp.getProceedButton().click();
				util.waitForElementPresent(wdp.getSplitPageWindow());

				wdp.getSplitWIIncludeFormDataCheckbox().click();
				util.wait(time);
				boolean includeFormDataErrMsgPresent = util
						.verifyObjectPresentReturnsBool(wdp.getMandatoryFieldErrorMsg());
				String errMsg = wdp.getMandatoryFieldErrorMsg().getText();
				if (includeFormDataErrMsgPresent) {
					Reporter.log(
							"Error message is displayed after clicking on Retain form data checkbox as ; " + errMsg,
							true);
					ATUReports.add("Error message is displayed as : " + errMsg
							+ " after clicking on Retain form data checkbox", true);
				} else {
					ATUReports.add("Error message is NOT displayed after clicking on Retain form data checkbox",
							LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Error message is NOT displayed after clicking on Retain form data checkbox");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());

			ATUReports.add("Verify 'Document Duplicate' operation by enabling 'retain form data' test is failed", true);
			Assert.fail("Verify document Split by by enabling 'retain form data'  test is failed");
		} finally {
			Log.endTestCase("INFO_7552_DocDuplicateByEnablingRetainFormData");
		}
	}

}