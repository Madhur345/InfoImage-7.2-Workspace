package apd.infoimage.iwm.tests;

/**
 * @author DashBisw INFO_11124 This class is To Performing Document Duplicate Operation 
 * With Same Different Class For Document Type Of Workitem Which is  Filed Inside
 * Folder With Index Fields Defined". 16/08/2018
 */
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

public class INFO_11128 extends SuperClassIWM {

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
				"INFO_11128_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWhichisFiledInsideFolderWithoutIndexFieldsDefined");
		ATUReports.setAuthorInfo("Biswajit", "AUG-2018", "0.3");

		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase(
					"INFO_11128_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWhichisFiledInsideFolderWithoutIndexFieldsDefined");
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
			String duplicateworkitem = workitem + "new";
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 26, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);
			String invoiceNoStr = ExcelLib.getCellValue(xlpath, sheet, 10, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log(
					"INFO_11128_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWhichisFiledInsideFolderWithoutIndexFieldsDefined",
					true);
			ATUReports.add(
					"INFO_11128_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWhichisFiledInsideFolderWithoutIndexFieldsDefined",
					true);
			Log.info(
					"INFO_11128_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWhichisFiledInsideFolderWithoutIndexFieldsDefined");

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
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

			util.waitForPageToLoad();
			util.wait(time);

			hp.getWorkItemTab().click();
			Thread.sleep(2000);

			hp.searchByNameInWorkitemTabAndDisplay(workitem);
			Reporter.log("Searching By Workitem Name And Display In Workitem tab", true);
			ATUReports.add("Searching By Workitem Name And Display In Workitem tab", true);
			Log.info("Searching By Workitem Name And Display In Workitem tab");

			wdp.getwiView().click();
			Reporter.log("WorkItem successfully viewed", true);
			ATUReports.add("WorkItem successfully viewed", true);
			Log.info("WorkItem successfully viewed");
			Thread.sleep(2000);

			wdp.getFormfields_win().click();
			Reporter.log("Formfield open button clicked", true);
			ATUReports.add("Formfield open button clicked", true);
			Log.info("Formfield open button clicked");
			Thread.sleep(2000);

			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys(idCodeStr);
			Reporter.log("Idcode has been written", true);
			ATUReports.add("Idcode has been written", true);
			Log.info("Idcode has been written");
			Thread.sleep(2000);

			wdp.getupdateFormButton().click();
			Reporter.log("Update Button clicked", true);
			ATUReports.add("Update Button clicked", true);
			Log.info("Update Button clicked");
			util.waitForelementEnable();
			Thread.sleep(10000);
			util.wait(time);

			hp.getWorkItemTab().click();
			Reporter.log("Workitem tab is clicked", true);
			ATUReports.add("Workitem tab is clicked", true);
			Log.info("Workitem tab is clicked");
			util.waitForPageToLoad();

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

			wdp.getActionBtn().click();
			Reporter.log("Action Button clicked", true);
			ATUReports.add("Action Button clicked", true);
			Log.info("Action Button clicked");

			wdp.getDocumentDuplicateOption().click();
			Reporter.log("clicked on Document Duplicate Link", true);
			ATUReports.add("clicked on Document Duplicate Link", true);
			Log.info("clicked on Document Duplicate Link");
			Thread.sleep(3000);

			wdp.getDocumentDuplicateWorkitemNameTextBox().clear();
			Reporter.log("clearing the Document Duplicate textbox", true);
			ATUReports.add("clearing the Document Duplicate textbox", true);
			Log.info("clearing the Document Duplicate textbox");

			wdp.getDocumentDuplicateWorkitemNameTextBox().sendKeys(duplicateworkitem);
			Reporter.log("passing the duplicate workitem name", true);
			ATUReports.add("passing the duplicate workitem name", true);
			Log.info("passing the duplicate workitem name");
			Thread.sleep(3000);

			wdp.getinvoice().click();
			Reporter.log("clicking on the new class name", true);
			ATUReports.add("clicking on the new class name", true);
			Log.info("clicking on the new class name");
			Thread.sleep(3000);

			wdp.getIncludeFormDataCheckBox().click();
			Reporter.log("Include Form Data CheckBox clicked", true);
			ATUReports.add("clicking on the new class name", true);
			Log.info("clicking on the new class name");

			wdp.getDuplicateButton().click();
			Reporter.log("clicked on Document Duplicate button", true);
			ATUReports.add("clicked on Document Duplicate button", true);
			Log.info("clicked on Document Duplicate button");
			Thread.sleep(3000);

			hp.getWorkItemTab().click();

			hp.searchByNameInWorkitemTabAndDisplay(workitem);
			Reporter.log("Searching By Workitem Name And Display In Workitem tab", true);
			ATUReports.add("Searching By Workitem Name And Display In Workitem tab", true);
			Log.info("Searching By Workitem Name And Display In Workitem tab");

			boolean createdWorkitemPresent = util
					.verifyObjectPresentReturnsBool(cwp.getWorkItemName(duplicateworkitem));
			if (createdWorkitemPresent) {
				Reporter.log("Created Workitem is present in grid", true);
				ATUReports.add("Created Workitem is present in grid", true);
			} else {
				Reporter.log("Created Workitem is not present in grid", true);
				ATUReports.add("Created Workitem is not present in grid", true);
				Assert.fail("Created Workitem is not present in grid ");
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			Reporter.log("INFO_11128 Failed", true);
			Log.info("INFO_11128 Failed");
			ATUReports.add("INFO_11128 Failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Importing text type file is failed.");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase(
					"INFO_11128_PerformingDocumentDuplicateOperationWithSameDifferentClassForDocumentTypeOfWorkitemWhichisFiledInsideFolderWithoutIndexFieldsDefined");
		}
	}

}
