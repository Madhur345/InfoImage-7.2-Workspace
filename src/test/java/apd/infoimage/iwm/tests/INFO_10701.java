package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
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

public class INFO_10701 extends SuperClassIWM {

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
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-10701 is for Duplicate button should be disabled after clicking on Duplicate for document type of workitem in workitems page");
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

			util.waitForPageToLoad();
			Reporter.log("Updation of Formfield", true);
			ATUReports.add("Updation of Formfield", true);
			Log.info("Updation of Formfield");

			wdp.getFormfields_win().click();
			util.wait(time);
			// ID_CODE text field

			wdp.getIdcode_TF().sendKeys(idCodeStr);
			Reporter.log("Idcode has been written", true);
			ATUReports.add("Idcode has been written", true);
			Log.info("Idcode has been written");
			util.wait(time);

			wdp.getUpdate_btn().click();
			Reporter.log("Update Button has been Clicked", true);
			ATUReports.add("Update Button has been Clicked", true);
			Log.info("Update Button has been Clicked");
			util.waitForPageToLoad();
			util.wait(time);
			util.wait(time);

			hp.getWorkItemTab().click();
			Reporter.log("Workitem tab is clicked", true);
			ATUReports.add("Workitem tab is clicked", true);
			Log.info("Workitem tab is clicked");
			util.waitForPageToLoad();

			hp.getSearchFieldInWorkitemTab().sendKeys(Keys.ENTER);
			util.wait(time);

			cwp.getActionsBtn().click();
			Reporter.log("Actions Button is Clicked", true);
			ATUReports.add("Actions Button is Clicked", true);
			Log.info("Actions Button is Clicked");

			cwp.getDocDuplicateInAction().click();
			Reporter.log("Document Duplicate Option is clicked", true);
			ATUReports.add("Document Duplicate Option is clicked", true);
			Log.info("Document Duplicate Option is clicked");
			util.wait(time);
			util.wait(time);
			wdp.getDocumentDuplicateWorkitemNameTextBox().sendKeys(dupStr);
			Reporter.log("Workitem Duplicate Name is entered in text box", true);
			ATUReports.add("Workitem Duplicate Name is entered in text box", true);
			Log.info("Workitem Duplicate Name is entered in text box");

			util.wait(time);
			util.wait(time);

			wdp.getDifferentClassOption().click();
			Reporter.log("Different Class Option is selected", true);
			ATUReports.add("Different Class Option is selected", true);
			Log.info("Different Class Option is selected");
			util.wait(time);

			wdp.getDuplicateButton().click();
			Reporter.log("Duplicate Button is clicked", true);
			ATUReports.add("Duplicate Button is clicked", true);
			Log.info("Duplicate Button is clicked");
			util.wait(time);
			util.wait(time);

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

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Verify Duplicate button should be disabled after clicking on Duplicate for document type of workitem in workitems page", true);
			ATUReports.add("Verify Duplicate button should be disabled after clicking on Duplicate for document type of workitem in workitems page is failed.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("Verify Duplicate button should be disabled after clicking on Duplicate for document type of workitem in workitems page");
			Assert.fail("Verify Duplicate button should be disabled after clicking on Duplicate for document type of workitem in workitems page is failed.");
		}

		finally {
			Log.endTestCase("INFO-10701_Duplicate button should be disabled after clicking on Duplicate for document type of workitem in workitems page Failed");
		}

	}
}