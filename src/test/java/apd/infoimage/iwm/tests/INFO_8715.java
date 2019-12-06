package apd.infoimage.iwm.tests;

import java.io.File;

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
 * @author Biswajit INFO-8715 Performing 'Document split' operation by adding
 *         notes Imports MarkUps and multpile images with different formats and
 *         same class for a document type of workitems
 */
public class INFO_8715 extends SuperClassIWM {

	@BeforeMethod
	public void setUp() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName2", "password2", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown() {
		hp.logoutApp();
	}

	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 1)
	public void testDocSplitSameClass() {
		Log.startTestCase(
				"INFO_8715_PerformingDocumentsplitoperationbyaddingnotesImportsMarkUpsAndMultpileImagesWithTIFFormatAndSameClassForADocumentTypeOfWorkitem");

		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-8715  is To verify 'Document split' operation by adding notes Imports MarkUps and multpile images "
						+ "with different formats and same class for a document type of workitem ");
		ATUReports.setAuthorInfo("Biswajit", "May-2018", "0.3");

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
		String id_Code_Value = ExcelLib.getCellValue(xlpath, sheet2, 2, 1);
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
			Log.info("CreateWorkitem operation performed");

			Reporter.log("Update formfield for ID_CODE value", true);
			ATUReports.add("Update formfield for ID_CODE value", true);
			cwp.getActionBtn(workitem).click();
			util.wait(time);
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			cwp.getID_CODE_Tf().sendKeys(id_Code_Value);
			cwp.getUpdateFormBtn().click();
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(workitem);
			cwp.getActionBtn(workitem).click();
			util.wait(time);

			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			String formFieldvalue = cwp.getID_CODE_Tf().getAttribute("value");
			if (formFieldvalue.equals(id_Code_Value)) {
				Reporter.log("Form field ID_CODE updated successfully", true);
				ATUReports.add("Form field ID_CODE updated successfully", true);
			} else {
				Reporter.log("Form field ID_CODE failed to update", true);
				ATUReports.add("Form field ID_CODE failed to update", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Form field ID_CODE failed to update");
			}

			util.wait(time);
			System.err.println("hello");
			cwp.getCloseFormBtn().click();
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(workitem);
			cwp.getWorkItemName(workitem).click();
			util.wait(time);

			wdp.Notes("Unisys");
			Reporter.log("Notes added successfully", true);
			ATUReports.add("Notes added successfully", true);

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

				wdp.getAddNewPageIcon().click();
				Reporter.log("Add New Page is clicked", true);
				ATUReports.add("Add New Page is clicked", true);
				util.wait(time);
				util.waitForPageToLoad();

				boolean addNewPageWinPresent1 = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
				if (addNewPageWinPresent1) {
					Reporter.log("Add new Page window is displayed", true);
					ATUReports.add("Add new Page window is displayed", true);

					String PNGFilePath = new File("src\\test\\resources").getAbsolutePath();
					String imagePath2 = PNGFilePath + "\\samplePng.PNG";

					Reporter.log("img path 2" + imagePath2, true);
					ATUReports.add("img path22 " + imagePath2, true);

					wdp.getContentUploadField().sendKeys(imagePath2);

					util.waitForElementEnabled(wdp.getSelectedFile());
					util.wait(time);

					Reporter.log("Sample PNG file is uploaded", true);
					ATUReports.add("Sample PNG file is uploaded", true);

					util.wait(time);
					wdp.getAddNewPageUploadBtn().click();
					Reporter.log("Add New Page Upload Button is clicked", true);
					ATUReports.add("Add New Page Upload Button is clicked", true);
					util.waitForPageToLoad();

					// Validate the document is listed under content and no of pages in the document
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
								"Split workitem window should be displayed", "Split workitem window is displayed",
								true);
					} else {
						Reporter.log("Split workitem window is not displayed", true);
						ATUReports.add("Split workitem window is not displayed", true);
						Assert.fail("Split workitem window is not displayed");
					}
					String splitwitem = wdp.getSplitWitemName();

					wdp.getProceedButton().click();
					util.waitForElementPresent(wdp.getSplitPageWindow());

					Reporter.log("Same class name is selected in split page class dropdown	", true);
					ATUReports.add("Same class name is selected in split page class dropdown", true);

					wdp.getSplitButton().click();
					ATUReports.add("Split button is clicked", true);
					util.waitForPageToLoad();
					util.wait(time);

					int contentListRemain = wdp.getContentPageNo();
					if (contentListRemain == 3) {
						Reporter.log("Pages are removed from 4th page ", true);
						ATUReports.add("Pages are removed from 4th page", true);

					} else {
						Reporter.log("Pages are not removed from 4th page ", true);
						ATUReports.add("Pages are not removed from 4th page ", LogAs.FAILED,
								new CaptureScreen(ScreenshotOf.DESKTOP));
						Assert.fail("Document split by adding multiple page test failed");
					}

					wdp.getCloseWorkitemIcon(workitem).click();
					util.waitForElementEnabled(cwp.getCreateWorkitem_btn());
					util.wait(time);

					cwp.searchByNameInWorkitemList(splitwitem);
					cwp.getWorkItemName(splitwitem).click();
					util.waitForPageToLoad();
					util.wait(time);

					int contentListSplitted = wdp.getContentPageNo();
					if (contentListSplitted == 2) {
						Reporter.log("Pages are splitted from 4th page ", true);
						ATUReports.add(
								"Verify document Split by adding form fields and multiple pages test is successfull",
								true);

					} else {
						Reporter.log("Pages are not splitted from 4th page ", true);
						ATUReports.add("Pages are not splitted from 4th page ", true);

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify document Split by adding form fields and multiple pages test is failed", true);
			ATUReports.add("Form field ID_CODE failed to update", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify document Split by adding form fields and multiple pages test is failed");
		} finally {
			Log.endTestCase(
					"INFO_8715_PerformingDocumentsplitoperationbyaddingnotesImportsMarkUpsAndMultpileImagesWithTIFFormatAndSameClassForADocumentTypeOfWorkitem");
		}
	}
}
