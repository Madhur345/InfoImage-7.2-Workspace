package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
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

public class INFO_10704 extends SuperClassIWM {

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
	public void VerifyDuplicateButtonDisabled() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-10704 is for Duplicate button should be disabled after clicking on Duplicate for document type of workitem in document viewer page");
		ATUReports.setAuthorInfo("Princi", "AUG-2018", "0.3");
		DOMConfigurator.configure("log4j.xml");

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
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 26, 1);
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			Log.info("Creation of Workitem");
			cwp.CreateWorkitem(workitem, className, workitemType);
			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItem is clicked", true);
			ATUReports.add("WorkItem is clicked", true);
			Log.info("WorkItem is clicked");
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");
			util.wait(time);

			wdp.getContentField().click();
			wdp.getAddNewPageIcon().click();

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				util.wait(time);
				util.wait(time);

				String contentImagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 25, 1);
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

				wdp.getFormfields_win().click();
				Reporter.log("Formfield open button clicked", true);
				ATUReports.add("Formfield open button clicked", true);
				Log.info("Formfield open button clicked");
				util.wait(time);

				// wdp.getIdcode_TF().clear();
				wdp.getIdcode_TF().sendKeys(idCodeStr);
				Reporter.log("Idcode has been written", true);
				ATUReports.add("Idcode has been written", true);
				Log.info("Idcode has been written");
				util.wait(time);

				wdp.getupdateFormButton().click();
				Reporter.log("Update Button clicked", true);
				ATUReports.add("Update Button clicked", true);
				Log.info("Update Button clicked");
				util.wait(time);

				wdp.getActionsDropDown().click();
				Reporter.log("Actions Drop Down is clicked", true);
				ATUReports.add("Actions Drop Down is clicked", true);
				Log.info("Actions Drop Down is Clicked");

				wdp.getDocumentDuplicateOption().click();
				Reporter.log("Document Duplicate Option is clicked", true);
				ATUReports.add("Document Duplicate Option is clicked", true);
				Log.info("Document Duplicate Option is clicked");
				util.wait(time);
				util.wait(time);

				wdp.getDocumentDuplicateWorkitemNameTextBox().sendKeys(dupStr);
				Reporter.log("Workitem Duplicate Name is entered in text box", true);
				ATUReports.add("Workitem Duplicate Name is entered in text box", true);
				Log.info("Workitem Duplicate Name is entered in text box");

				wdp.getDifferentClassOption().click();
				Reporter.log("Different Class Option is selected", true);
				ATUReports.add("Different Class Option is selected", true);
				Log.info("Different Class Option is selected");
				util.wait(time);

				wdp.getDuplicateButton().click();
				Reporter.log("Duplicate Button is clicked", true);
				ATUReports.add("Duplicate Button is clicked", true);
				Log.info("Duplicate Button is clicked");

				boolean documentDuplicateOptionDisplay = wdp.getDocumentDuplicateOption().isDisplayed();
				System.out.println("++++++++++++++++++++++++++++++");
				
				if(documentDuplicateOptionDisplay) {
					Reporter.log("Duplicate button is displayed", true);
					ATUReports.add("Duplicate button is displayed", true);
					Log.info("Duplicate button is displayed");
					Assert.fail("Duplicate button is displayed");
				} else {
					Reporter.log("Duplicate button is not displayed", true);
					ATUReports.add("Duplicate button is not displayed", true);
					Log.info("Duplicate button is not displayed");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Verify Duplicate button should be disabled after clicking on Duplicate for document type of workitem in document viewer page", true);
			ATUReports.add("Verify Duplicate button should be disabled after clicking on Duplicate for document type of workitem in document viewer page is failed.",LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("Verify Duplicate button should be disabled after clicking on Duplicate for document type of workitem in document viewer page");
			Assert.fail("Verify Duplicate button should be disabled after clicking on Duplicate for document type of workitem in document viewer page is failed.");
		}

		finally {
			Log.endTestCase("INFO-10704  Duplicate button should be disabled after clicking on Duplicate for document type of workitem in document viewer page is Failed");
		}

	}
}