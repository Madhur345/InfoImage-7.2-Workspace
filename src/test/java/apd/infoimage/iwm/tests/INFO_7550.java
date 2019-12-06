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
 * @author SumanGaK - 09-Mar-2018 
 * INFO-7550 
 * This class verifies Document Duplicate of Document type of Workitem With out Name
 */
public class INFO_7550 extends SuperClassIWM {

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
	public void testVerifyDocumentWorkitemWithoutNameDocumentDuplicate() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7550 is for Verifying Document Duplicate of Document Workitem Without Name");
		ATUReports.setAuthorInfo("Suman", "MAR-2018", "0.3");

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

			Reporter.log("Class Name : VerifyDocumentWorkitemWithoutNameDocumentDuplicateTest", true);
			ATUReports.add("Class Name : VerifyDocumentWorkitemWithoutNameDocumentDuplicateTest", true);
			Log.info("Class Name : VerifyDocumentWorkitemWithoutNameDocumentDuplicateTest");

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

			Reporter.log("Document: "+workitem+" has been created",true);
			ATUReports.add("Document: "+workitem+" has been created",true);
			Log.info("Document: "+workitem+" has been created");

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
			hp.getDocumentDuplicateWorkitemNameTextBox().clear();
			Reporter.log("Workitem Duplicate Name text box is  cleared", true);
			ATUReports.add("Workitem Duplicate Name text box is cleared", true);
			Log.info("Workitem Duplicate Name text box is cleared");

			util.wait(time);
			util.wait(time);
			hp.getDuplicateButton().click();
			Reporter.log("Duplicate Button is clicked", true);
			ATUReports.add("Duplicate Button is clicked", true);
			Log.info("Duplicate Button is clicked");

			util.wait(time);
			util.wait(time);		

			if (hp.getDocumentDuplicateWorkitemNameTextBox().getText().isEmpty()) {
				Reporter.log("Workitem Duplicate Name text box cannot be empty", true);
				ATUReports.add("Workitem Duplicate Name text box cannot be empty", true);
				Log.info("Workitem Duplicate Name text box cannot be empty");
			} else {
				Reporter.log("Clear Workitem Duplicate Name text box", true);
				ATUReports.add("Clear Workitem Duplicate Name text box", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
			}
			util.wait(time);
			util.wait(time);
			hp.getDocumentDuplicateCloseButton().click();
			util.wait(time);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Verify Document Workitem With out Name Document Duplicate Test is failed.", true);
			ATUReports.add("Verify Document Workitem With out Name Document Duplicate Test is failed.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Document Workitem With out Name Document Duplicate Test is failed.");
		}
	}
}
