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
 * @author Biswajit - 04-June-2018 INFO_8710 This class Perform Document split
 *         operation using multpile images with PDF format and Different class
 *         for a document type of workitem
 */
public class INFO_8710 extends SuperClassIWM {
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
	public void testDocSplitDiffClass() {
		Log.startTestCase(
				"INFO_8710_PerformingDocumentSplitOperationUsingMultipleImagesWithPDFFormatAndDifferentClassForADocumentTypeOfWorkitem");

		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-8710  is To verify 'Document split' operation Using Multiple Images With PDF Format"
						+ "with different class for a document type of workitem ");
		ATUReports.setAuthorInfo("Biswajit", "June-2018", "0.3");

		try {
			String xlpath = "src\\test\\resources\\testData.xlsx";
			String sheet = "Sheet1";
			String sheet2 = "Sheet_J";

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String newClassName = ExcelLib.getCellValue(xlpath, sheet2, 1, 1);
			String id_Code_Value = ExcelLib.getCellValue(xlpath, sheet2, 2, 1);

			int time = 3000;

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

			cwp.getActionBtn(workitem).click();
			util.wait(time);
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();

			cwp.getID_CODE_Tf().sendKeys(id_Code_Value);
			cwp.getUpdateFormBtn().click();
			Reporter.log("Update formfield for ID_CODE value", true);
			ATUReports.add("Update formfield for ID_CODE value", true);
			Log.info("Update formfield for ID_CODE value");
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(workitem);
			cwp.getActionBtn(workitem).click();
			Log.info("Action button clicked");
			util.wait(time);

			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			String formFieldvalue = cwp.getID_CODE_Tf().getAttribute("value");
			if (formFieldvalue.equals(id_Code_Value)) {
				Reporter.log("Form field ID_CODE updated successfully", true);
				ATUReports.add("Form field ID_CODE updated successfully", true);
				Log.info("Form field ID_CODE updated successfully");
			} else {
				Reporter.log("Form field ID_CODE failed to update", true);
				ATUReports.add("Form field ID_CODE failed to update", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Log.info("Form field ID_CODE failed to update");
				Assert.fail("Form field ID_CODE failed to update");
			}

			util.wait(time);
			cwp.getCloseFormBtn().click();
			Log.info("Close form button clicked");
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(workitem);
			cwp.getWorkItemName(workitem).click();
			util.wait(time);

			wdp.getContentField().click();
			Reporter.log("Content Tab is clicked", true);
			ATUReports.add("Content Tab is clicked", true);
			Log.info("Content Tab is clicked");
			util.wait(time);

			wdp.getAddNewPageIcon().click();
			Reporter.log("Add New Page is clicked", true);
			ATUReports.add("Add New Page is clicked", true);
			Log.info("Add New Page is clicked");
			util.wait(time);
			util.waitForPageToLoad();

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);

				String pdfFilePath = new File("src\\test\\resources").getAbsolutePath();
				String imagePath = pdfFilePath + "\\SampleDoc.pdf";

				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				Reporter.log("Sample pdf file is uploaded", true);
				ATUReports.add("Sample pdf file is uploaded", true);
				Log.info("Sample pdf file is uploaded");

				util.wait(time);
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				util.waitForPageToLoad();

				wdp.getAddNewPageIcon().click();
				Reporter.log("Add New Page is clicked", true);
				ATUReports.add("Add New Page is clicked", true);
				Log.info("Add New Page is clicked");
				util.wait(time);
				util.waitForPageToLoad();

				boolean addNewPageWinPresent1 = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
				if (addNewPageWinPresent1) {
					Reporter.log("Add new Page window is displayed", true);
					ATUReports.add("Add new Page window is displayed", true);
					Log.info("Add new Page window is displayed");

					String pdfFilePath2 = new File("src\\test\\resources").getAbsolutePath();
					String imagePath2 = pdfFilePath2 + "\\SampleDoc.pdf";

					Reporter.log("img path 2" + imagePath2, true);
					ATUReports.add("img path2 " + imagePath2, true);

					wdp.getContentUploadField().sendKeys(imagePath2);

					util.waitForElementEnabled(wdp.getSelectedFile());
					util.wait(time);

					Reporter.log("SampleDc pdf file is uploaded", true);
					ATUReports.add("SampleDc pdf file is uploaded", true);
					Log.info("SampleDc pdf file is uploaded");

					util.wait(time);
					wdp.getAddNewPageUploadBtn().click();
					Reporter.log("Add New Page Upload Button is clicked", true);
					ATUReports.add("Add New Page Upload Button is clicked", true);
					Log.info("Add New Page Upload Button is clicked");
					util.waitForPageToLoad();

					// Validate the document is listed under content and no of pages in the document
					int contentList = wdp.getContentPageNo();

					Reporter.log("No of Pages in the multiple page pdf file is : " + contentList, true);
					ATUReports.add("No of Pages in the multiple page pdf file is : " + contentList, true);

					util.waitForPageToLoad();
					util.wait(time);

					wdp.getFourthPageCheckbox().click();
					Reporter.log("Fourth page Checkbox is clicked", true);
					ATUReports.add("Fourth page Checkbox is clicked", true);
					Log.info("Fourth page Checkbox is clicked");
					util.wait(time);

					wdp.getSplitIcon().click();
					Reporter.log("Split Pages Icon is clicked", true);
					ATUReports.add("Split Pages Icon is clicked", true);
					Log.info("Split Pages Icon is clicked");
					util.waitForPageToLoad();
					util.wait(time);

					boolean splitPageWindowPresent = util.verifyObjectPresentReturnsBool(wdp.getProceedButton());
					if (splitPageWindowPresent) {
						Reporter.log("Split workitem window is displayed", true);
						ATUReports.add("Split workitem window is displayed", true);
						Log.info("Split workitem window is displayed");
						ATUReports.add("Verify Split page in workitem using shortkut key", "",
								"Split workitem window should be displayed", "Split workitem window is displayed",
								true);
					} else {
						Reporter.log("Split workitem window is not displayed", true);
						ATUReports.add("Split workitem window is not displayed", true);
						Log.info("Split workitem window is not displayed");
						Assert.fail("Split workitem window is not displayed");
					}
					String splitwitem = wdp.getSplitWitemName();

					wdp.getProceedButton().click();
					util.waitForElementPresent(wdp.getSplitPageWindow());

					Select selectClass = new Select(wdp.getSplitWIDropdown());
					selectClass.selectByValue(newClassName);

					Reporter.log("different class name is selected in split page class dropdown	", true);
					ATUReports.add("different class name is selected in split page class dropdown", true);
					Log.info("different class name is selected in split page class dropdown");

					wdp.getSplitButton().click();
					ATUReports.add("different class name is selected in split page class dropdown", true);
					Log.info("different class name is selected in split page class dropdown");
					util.waitForPageToLoad();
					util.wait(time);

					int contentListRemain = wdp.getContentPageNo();
					if (contentListRemain == 3) {
						Reporter.log("Pages are removed from 4th page ", true);
						ATUReports.add("Pages are removed from 4th page", true);
						Log.info("Pages are removed from 4th page");

					} else {
						Reporter.log("Pages are not removed from 4th page ", true);
						Log.info("Pages are not removed from 4th page");
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
						Log.info("Pages are splitted from 4th page ");
						ATUReports.add(
								"Verify document Split by adding form fields and multiple pages test is successfull",
								true);

					} else {
						Reporter.log("Pages are splitted from 4th page ", true);
						ATUReports.add("Pages are splitted from 4th page ", true);

					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify document Split by adding form fields and multiple pages test is failed", true);
			ATUReports.add(
					"verify 'Document split' operation Using Multiple Images With pdf Format with different class for a document type of workitem test is failed",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail(
					"verify 'Document split' operation Using Multiple Images With pdf Format with different class for a document type of workitem test is failed");
		} finally {
			Log.endTestCase(
					"INFO_8710_PerformingDocumentSplitOperationUsingMultipleImagesWithPDFFormatAndDifferentClassForADocumentTypeOfWorkitem");
		}
	}
}
