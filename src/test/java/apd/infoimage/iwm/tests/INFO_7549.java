package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
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
 * @author SumanGaK - 12-Mar-2018 
 * INFO-7549 
 * This class verifies Document  Duplicate of Folder type of Workitem with index fields defined And
 * Auto Open
 */
public class INFO_7549 extends SuperClassIWM {

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
	public void testVerifyFolderWorkitemWithOutFormFieldsAutoOpenDocumentDuplicate() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7549 is for Verifying Document Duplicate of Folder type of Workitem with index fields defined And Auto Open");
		ATUReports.setAuthorInfo("Suman", "APR-2018", "0.3");

		try {
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("VerifyFolderWorkitemWithOutFormFieldsAutoOpenDocumentDuplicateTest", true);
			ATUReports.add("VerifyFolderWorkitemWithOutFormFieldsAutoOpenDocumentDuplicateTest", true);
			Log.info("VerifyFolderWorkitemWithOutFormFieldsAutoOpenDocumentDuplicateTest");

			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);

			cwp.CreateWorkitem(workitem,className,workitemType);

			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");

			Reporter.log("Folder: "+workitem+" has been created",true);
			ATUReports.add("Folder: "+workitem+" has been created",true);
			Log.info("Folder: "+workitem+" has been created");
			util.waitForPageToLoad();

			hp.getSearchFieldInWorkitemTab().clear();
			Reporter.log("Search Field In Workitem Tab is cleared", true);
			ATUReports.add("Search Field In Workitem Tab is cleared", true);
			Log.info("Search Field In Workitem Tab is cleared");

			hp.searchByNameInWorkitemTabAndDisplay(workitem);
			Reporter.log("Workitem is Searched and Displayed", true);
			ATUReports.add("Workitem is Searched and Displayed", true);
			Log.info("Workitem is Searched and Displayed");

			hp.getFirstRowFirstCell().click();
			Reporter.log("Action Button of Workitem is clicked", true);
			ATUReports.add("Action Button of Workitem is clicked", true);
			Log.info("Action Button of Workitem is clicked");

			hp.getDocumentDuplicateOption().click();
			Reporter.log("Document Duplicate Option is clicked", true);
			ATUReports.add("Document Duplicate Option is clicked", true);
			Log.info("Document Duplicate Option is clicked");

			util.wait(time);
			util.wait(time);
			hp.getDocumentDuplicateWorkitemNameTextBox().sendKeys(dupStr);
			Reporter.log("Workitem Duplicate Name is entered in text box", true);
			ATUReports.add("Workitem Duplicate Name is entered in text box", true);
			Log.info("Workitem Duplicate Name is entered in text box");

			hp.getAutoOpenCheckBox().click();
			Reporter.log("Auto Open CheckBox is checked", true);
			ATUReports.add("Auto Open CheckBox is checked", true);
			Log.info("Auto Open CheckBox is checked");

			hp.getDuplicateButton().click();
			Reporter.log("Duplicate Button is clicked", true);
			ATUReports.add("Duplicate Button is clicked", true);
			Log.info("Duplicate Button is clicked");

			hp.getWorkItemTab().click();
			Reporter.log("Workitem tab is clicked", true);
			ATUReports.add("Workitem tab is clicked", true);
			Log.info("Workitem tab is clicked");

			util.waitForPageToLoad();
			cwp.searchByNameInWorkitemList(workitem + dupStr);
			Reporter.log("Searching By Workitem Name In Workitem tab", true);
			ATUReports.add("Searching By Workitem Name In Workitem tab", true);

			boolean duplicateWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem + dupStr));
			if (duplicateWorkitemPresence) {
				Reporter.log("Duplicate Workitem is present in grid", true);
				ATUReports.add("Duplicate Workitem is present in grid", true);
				ATUReports.add("Verify Folder Workitem With Out Form Fields Auto Open Document Duplicate", "",
						"Document Duplicate of Folder Workitem With Out Form Fields Auto Open should be displayed",
						"Document Duplicate of Folder Workitem With Out Form Fields Auto Open is displayed", true);
			} else {
				Reporter.log("Duplicate Workitem is not present in grid", true);
				ATUReports.add("Duplicate Workitem is not present in grid", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Duplicate Workitem is not present in grid ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Verify Folder Workitem With Out Form Fields Auto Open Document Duplicate Test is failed.",
					true);
			ATUReports.add("Verify Folder Workitem With Out Form Fields Auto Open Document Duplicate Test is failed.",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Folder Workitem With Out Form Fields Auto Open Document Duplicate Test is failed.");
		}
	}
}
