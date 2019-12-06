package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
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
 * @author SumanGaK - 28-May-2018 
 * INFO-8016 
 * This class will Add Document Duplicate under Workitems tab
 **/
public class INFO_8016 extends SuperClassIWM {

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
	public void testAddDocumentDuplicateUnderWorkitems() {

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-8016 is for Adding Document Duplicate under Workitems tab");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_8016_AddDocumentDuplicateUnderWorkitemsTest");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
            int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("INFO_8016_AddDocumentDuplicateUnderWorkitemsTest", true);
			ATUReports.add("INFO_8016_AddDocumentDuplicateUnderWorkitemsTest", true);
			Log.info("INFO_8016_AddDocumentDuplicateUnderWorkitemsTest");

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

			Reporter.log("Document: " + workitem + " has been created", true);
			ATUReports.add("Document: " + workitem + " has been created", true);
			Log.info("Document: " + workitem + " has been created");

			util.wait(time);

			hp.getSearchFieldInWorkitemTab().clear();
			hp.getSearchFieldInWorkitemTab().sendKeys(workitem + Keys.ENTER);
			Reporter.log("Searching By Workitem Name In Workitem tab", true);
			ATUReports.add("Searching By Workitem Name In Workitem tab", true);
			Log.info("Searching By Workitem Name In Workitem tab");

			cwp.getActionsBtn().click();
			Reporter.log("Actions Button is Clicked", true);
			ATUReports.add("Actions Button is Clicked", true);
			Log.info("Actions Button is Clicked");
			util.wait(time);

			cwp.getDocDuplicateInAction().click();
			Reporter.log("Document Duplicate is Clicked", true);
			ATUReports.add("Document Duplicate is Clicked", true);
			Log.info("Document Duplicate is Clicked");
			util.wait(time);

			wdp.getDocumentDuplicateWorkitemNameTextBox().sendKeys(dupStr);
			Reporter.log("Workitem Duplicate Name is entered in text box", true);
			ATUReports.add("Workitem Duplicate Name is entered in text box", true);
			Log.info("Workitem Duplicate Name is entered in text box");
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

			hp.getWorkItemTab().click();
			Reporter.log("Workitem tab is clicked", true);
			ATUReports.add("Workitem tab is clicked", true);
			Log.info("Workitem tab is clicked");
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(workitem + dupStr);
			Reporter.log("Searching By Workitem Name In Workitem tab", true);
			ATUReports.add("Searching By Workitem Name In Workitem tab", true);
			Log.info("Searching By Workitem Name In Workitem tab");

			boolean duplicateWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem + dupStr));

			if (duplicateWorkitemPresence) {
				Reporter.log("Duplicate Workitem is present in grid", true);
				ATUReports.add("Duplicate Workitem is present in grid", true);
				Log.info("Duplicate Workitem is present in grid");
				ATUReports.add("Perform Document Workitem update form fields and Document Duplicate with Different Name and Different Class",
						"",
						"Document Duplicate of Document Workitem with Different Name and Different Class should be displayed",
						"Document Duplicate of Document Workitem with Different Name and Different Class is displayed",
						true);
			} else {
				Reporter.log("Duplicate Workitem is not present in grid", true);
				Log.info("Duplicate Workitem is not present in grid");
				ATUReports.add("Duplicate Workitem is not present in grid", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Duplicate Workitem is not present in grid ");
			}

			hp.getSearchFieldInWorkitemTab().sendKeys(Keys.ENTER);
			util.wait(time);

			cwp.getActionsBtn().click();
			Reporter.log("Actions Button is Clicked", true);
			ATUReports.add("Actions Button is Clicked", true);
			Log.info("Actions Button is Clicked");
			cwp.getActionsMetaData().click();

			Reporter.log("Meta Data is Clicked", true);
			ATUReports.add("Meta Data is Clicked", true);
			Log.info("Meta Data is Clicked");
			String newClass = cwp.getNewClassName().getText().substring(10);

			Reporter.log("New Class Name : " + newClass, true);
			ATUReports.add("New Class Name : " + newClass, true);
			Log.info("New Class Name : " + newClass);

			boolean newClassPresence = util.verifyObjectPresentReturnsBool(cwp.getWebElementNewClass(newClass));
			if (newClassPresence) {
				Reporter.log("New Class Name is in metadata screen in workitems page", true);
				ATUReports.add("New Class Name is in metadata screen in workitems page", true);
				Log.info("New Class Name is in metadata screen in workitems page");
			} else {
				Reporter.log("New Class Name is not in metadata screen in workitems page", true);
				Log.info("New Class Name is not in metadata screen in workitems page");
				ATUReports.add("New Class Name is not in metadata screen in workitems page", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("New Class Name is not in metadata screen in workitems page");
			}

			cwp.getMetaDataDialogBoxCloseBtn(workitem + dupStr).click();
			Reporter.log("Meta Data Dialog Box Close Button is clicked", true);
			ATUReports.add("Meta Data Dialog Box Close Button is clicked", true);
			Log.info("Meta Data Dialog Box Close Button is clicked");
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("VerifyDocumentWorkitemDifferentNameDifferentClassDocumentDuplicateTest is failed.", true);
			Log.info("VerifyDocumentWorkitemDifferentNameDifferentClassDocumentDuplicateTest is failed.");
			ATUReports.add("VerifyDocumentWorkitemDifferentNameDifferentClassDocumentDuplicateTest is failed.",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyDocumentWorkitemDifferentNameDifferentClassDocumentDuplicateTest is failed.");
		}
		finally {
			Log.endTestCase("INFO_8016_AddDocumentDuplicateUnderWorkitemsTest");
		}
	}
}