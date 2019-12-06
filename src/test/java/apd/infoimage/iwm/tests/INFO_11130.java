package apd.infoimage.iwm.tests;

/**
 * @author DashBisw INFO_11130 This class is To Performing Document Duplicate Operation 
 * With Same Different Class For Document Type Of Workitem Which is  Filed Inside
 * Folder With out Index Fields Defined". 27/08/2018
 */
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
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

public class INFO_11130 extends SuperClassIWM {

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
	public void testPerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitem() {

		ATUReports.setTestCaseReqCoverage(
				"INFO_11130_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWhichisFiledInsideFolderWithoutIndexFieldsDefined");
		ATUReports.setAuthorInfo("Biswajit", "AUG-2018", "0.3");

		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase(
					"INFO_11130_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWhichisFiledInsideFolderWithoutIndexFieldsDefined");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String sheet2 = "Sheet_J";

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String foldstr = ExcelLib.getCellValue(xlpath, sheet, 24, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String folderWorkitem = foldstr + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String folderWorkitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 26, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);
			String invoiceNoStr = ExcelLib.getCellValue(xlpath, sheet, 10, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			String newWitemStr = ExcelLib.getCellValue(xlpath, sheet2, 3, 1);

			Reporter.log(
					"INFO_11130_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWhichisFiledInsideFolderWithoutIndexFieldsDefined",
					true);
			ATUReports.add(
					"INFO_11130_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWhichisFiledInsideFolderWithoutIndexFieldsDefined",
					true);
			Log.info(
					"INFO_11130_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWhichisFiledInsideFolderWithoutIndexFieldsDefined");

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed.", true);
			Log.info("CreateWorkitem operation performed");

			Reporter.log("Document: " + workitem + " has been created", true);
			ATUReports.add("Document: " + workitem + " has been created", true);
			Log.info("Document: " + workitem + " has been created");

			cwp.CreateWorkitem(folderWorkitem, className, folderWorkitemType);
			Reporter.log("Folder: " + folderWorkitem + " has been created", true);
			ATUReports.add("Folder: " + folderWorkitem + " has been created", true);
			Log.info("Folder: " + folderWorkitem + " has been created");

			Reporter.log("Workitem Type : " + folderWorkitemType, true);
			ATUReports.add("Workitem Type : " + folderWorkitemType, true);
			Log.info("Workitem Type : " + folderWorkitemType);

			util.wait(time);

			cwp.getNameSearchTextbox().clear();

			cwp.searchByNameInWorkitemList(workitem);
			util.wait(time);

			cwp.getCheckBoxWorkItem(workitem).click();
			Reporter.log("Selected: " + workitem + " to file work item", true);
			ATUReports.add("Selected: " + workitem + " to file work item", true);
			Log.info("Selected: " + workitem + " to file work item");

			cwp.getFileworkitemBtn().click();
			Reporter.log("File Work item dialog box is open", true);
			ATUReports.add("File Work item dialog box is open", true);
			Log.info("File Work item dialog box is open");
			Thread.sleep(2000);

			Select sel = new Select(cwp.getSelectFolderDropDown());
			int selectOptions = sel.getOptions().size();
			sel.selectByIndex(selectOptions - 1);
			Thread.sleep(2000);

			Reporter.log("Selected destination folder as: " + folderWorkitem, true);
			ATUReports.add("Selected destination folder as: " + folderWorkitem, true);
			Log.info("Selected destination folder as: " + folderWorkitem);

			util.wait(time);
			cwp.getFileWorkitemAcceptBtn().click();
			Reporter.log("File Workitem Accept Button clicked", true);
			ATUReports.add("File Workitem Accept Button clicked", true);
			Log.info("File Workitem Accept Button clicked");

			util.waitForPageToLoad();
			util.wait(time);

			hp.getWorkItemTab().click();
			Thread.sleep(2000);

			hp.searchByNameInWorkitemTabAndDisplay(workitem);
			Reporter.log("Searching By Workitem Name And Display In Workitem tab", true);
			ATUReports.add("Searching By Workitem Name And Display In Workitem tab", true);
			boolean createdWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (createdWorkitemPresence) {
				Reporter.log("Created Workitem is present in grid", true);
				ATUReports.add("Created Workitem is present in grid", true);
			} else {
				Reporter.log("Created Workitem is not present in grid", true);
				ATUReports.add("Created Workitem is not present in grid", true);
				Assert.fail("Created Workitem is not present in grid ");
			}

			wdp.getwiView().click();
			Reporter.log("WorkItem successfully viewed", true);
			ATUReports.add("WorkItem successfully viewed", true);
			Log.info("WorkItem successfully viewed");
			Thread.sleep(2000);

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
				Log.info("Add new Page window is displayed");
				ATUReports.add("Add new Page window is displayed", true);

				String tifFilePath = new File("src\\test\\resources").getAbsolutePath();

				String imagePath = tifFilePath + "\\samplePng.PNG";
				Reporter.log("img path " + imagePath, true);
				Log.info("img path " + imagePath);
				ATUReports.add("img path " + imagePath, true);

				wdp.getContentUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				util.wait(time);
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				util.waitForPageToLoad();

				Reporter.log("samplePng PNG file is uploaded", true);
				ATUReports.add("samplePng PNG file is uploaded", true);
				Log.info("samplePng PNG file is uploaded");
				Thread.sleep(3000);

				wdp.getAddNewPageIcon().click();
				Reporter.log("Add New Page is clicked", true);
				ATUReports.add("Add New Page is clicked", true);
				Log.info("Add New Page is clicked");
				util.wait(time);
				util.waitForPageToLoad();

				String tifFilePath2 = new File("src\\test\\resources").getAbsolutePath();
				String imagePath2 = tifFilePath + "\\multiPage.tif";

				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);

				wdp.getContentUploadField().sendKeys(imagePath2);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				Reporter.log("multipage TIf file is uploaded", true);
				ATUReports.add("multipage TIf file is uploaded", true);
				Log.info("multipage TIf file is uploaded");

				util.wait(time);
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				util.waitForPageToLoad();

				// wdp.getinputFirstCheckBox().click();
				util.jclick(wdp.getinputFirstCheckBox());
				util.wait(time);
				Reporter.log("First Checkbox is clicked", true);
				ATUReports.add("First  page Checkbox is clicked", true);
				Log.info("First  page Checkbox is clicked");
				util.wait(time);

				wdp.getSplitIcon().click();
				Reporter.log("Split Pages Icon is clicked", true);
				ATUReports.add("Split Pages Icon is clicked", true);
				Log.info("Split Pages Icon is clicked");
				util.waitForPageToLoad();

				wdp.getProceedButton().click();
				util.waitForElementPresent(wdp.getSplitPageWindow());

				String newSpliwtItem = str + util.getSysDate(0, date);

				wdp.getSplitWitemNameTextbox().clear();
				wdp.getSplitWitemNameTextbox().sendKeys(newSpliwtItem.trim());
				Thread.sleep(2000);

				wdp.getsplitWIIncludeFormData().click();
				Reporter.log("Include Form Data CheckBox clicked", true);
				ATUReports.add("clicking on the new class name", true);
				Log.info("clicking on the new class name");
				Thread.sleep(2000);

				String alertmsg = "* Please fill the mandatory fields to include form data";
				String alertmsg1 = wdp.getMandatoryFieldErrorMsg().getText();

				if (alertmsg.equals(alertmsg1)) {
					ATUReports.add("Alert message is displaying 'This field is required'", true);
					Log.info("Alert message is displaying 'This field is required'");
					Reporter.log("Alert message is displaying 'This field is required'", true);

				} else {
					ATUReports.add("Alert message is not coming", true);
					Log.info("Alert message is not coming.");
					Reporter.log("Alert message is not coming.", true);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("INFO_11130 Failed", true);
			Log.info("INFO_11130 Failed");
			ATUReports.add("INFO_11130 Failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing text type file is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase(
					"INFO_11130_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWhichisFiledInsideFolderWithoutIndexFieldsDefined");
		}
	}

}
