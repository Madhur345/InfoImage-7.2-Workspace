package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
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
 * @authorPradhanJ INFO-2799 To verify the fix for the issue with duplication
 *         of the Retrieve and Open button in Search Result and Inbox
 */
public class INFO_2799 extends SuperClassIWM {
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
	@Test(enabled = true, priority = 1, groups = { "Search" })
	public void testDuplicationOfRetriveAndOpenFromSearchAndInbox() {
		try {
			Log.startTestCase("INFO_2799_DuplicationOfRetriveAndOpenFromSearchAndInbox");

			ATUReports.setTestCaseReqCoverage("This class verifies the fix for the issue with duplication of "
					+ "the Retrieve and Open button in Search Result and Inbox");
			ATUReports.setAuthorInfo("Jayashri", "MAY-2018", "0.3");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));			
			int time = 3000;
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);
			String invoiceNoStr = ExcelLib.getCellValue(xlpath, sheet, 10, 1);

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Log.info("Workitem : " + workitem);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Log.info("Class Name : " + className);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Log.info("Workitem Type : " + workitemType);

			cwp.CreateWorkitem(workitem, className, workitemType);

			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");
			util.wait(time);

			Reporter.log("Selection of Workitem", true);
			ATUReports.add("Selection of Workitem", true);
			Log.info("Selection of Workitem");

			cwp.getWorkItemName(workitem).click();
			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Updation of Formfield", true);
			ATUReports.add("Updation of Formfield", true);
			Log.info("Updation of Formfield");

			wdp.getFormfields_win().click();
			util.wait(time);
			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys(idCodeStr);
			wdp.getInvoiceno_TF().clear();
			wdp.getInvoiceno_TF().sendKeys(invoiceNoStr);

			Reporter.log("Idcode and Invoice Number has been written", true);
			ATUReports.add("Idcode and Invoice Number has been written", true);
			Log.info("Idcode and Invoice Number has been written");
			util.wait(time);

			util.jclick(wdp.getUpdate_btn());
			Reporter.log("Update Button has been Clicked", true);
			ATUReports.add("Update Button has been Clicked", true);
			Log.info("Update Button has been Clicked");
			util.waitForPageToLoad();
			util.wait(time);
			util.wait(time);
			util.wait(time);

			util.jclick(hp.getWorkItemTab());
			util.waitForPageToLoad();
			util.wait(time);
			cwp.searchByNameInWorkitemList(workitem);
			Reporter.log("Sending Workitem to Workflow",true);
			ATUReports.add("Sending Workitem to Workflow",true);
			util.wait(time);
			cwp.sendWorkItemToDefaultQueue(workitem);
			
			ip.getFirstRowFirstCell().click();
			if (ip.getInboxMetaData().getText() != "Retrieve and Open"
					&& ip.getInboxFormFields().getText() != "Retrieve and Open"
					&& ip.getInboxCopyURL().getText() != "Retrieve and Open") {
				Reporter.log("Retrieve and open link is removed from actions for "
						+ "the first workitem in Inbox", true);
				ATUReports.add("Retrieve and open link is removed from actions for "
						+ "the first workitem in Inbox", true);
			}

			else {
				Reporter.log("Retrieve and open link is not removed from actions for "
						+ "the first workitem in Inbox", true);
				ATUReports.add("Retrieve and open link is not removed from actions for "
						+ "the first workitem in Inbox",
						LogAs.FAILED	, new CaptureScreen(ScreenshotOf.DESKTOP));
				 Assert.fail("Retrieve and open link is not removed from actions for "
				 		+ "the first workitem in Inbox");
			}
			
			util.waitForPageToLoad();			
			hp.getSearchTab().click();
			Reporter.log("Search Tab is navigated", true);
			ATUReports.add("Search Tab is navigated", true);
			util.waitForPageToLoad();
			
			sp.getBasicSearch().click();
			Reporter.log("Basic Search menu is selected", true);
			ATUReports.add("Basic Search Tab menu is selected", true);
			util.waitForPageToLoad();
			util.wait(time);

			sp.getTextBox().sendKeys(workitem);
			sp.getSearchButton().click();
			util.waitForPageToLoad();
			util.wait(time);
			Reporter.log(" Search button is clicked", true);
			ATUReports.add("Search button menu is clicked", true);

			sp.getFirstRowFirstCell().click();
			if (sp.getSearchCopyURL().getText() != "Retrieve and Open") {
				Reporter.log("Retrieve and open link is removed from actions for "
						+ "the first workitem in search result", true);
				ATUReports.add("Retrieve and open link is removed from actions"
						+ " for the first workitem in search result", true);
			}

			else {
				Reporter.log("Retrieve and open link is not removed from actions "
						+ "for the first workitem in search result", true);
				ATUReports.add("Retrieve and open link is not removed from actions "
						+ "for the first workitem in search result",
						LogAs.FAILED	, new CaptureScreen(ScreenshotOf.DESKTOP));
				 Assert.fail("Retrieve and open link is not removed from actions "
				 		+ "for the first workitem in search result");
			}
			util.wait(time);
			util.waitForPageToLoad();

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add("failed to execute duplication of the Retrieve and"
					+ " Open button in Search Result and Inbox test", true);
			Assert.fail("failed to execute duplication of the Retrieve and"
					+ " Open button in Search Result and Inbox test");
		} finally {
			Log.endTestCase("INFO_2799_DuplicationOfRetriveAndOpenFromSearchAndInbox");
		}
	}
}
