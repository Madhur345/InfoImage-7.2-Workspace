package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
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
 * @author SumanGaK - 26-May-2018 
 * INFO-7533 
 * This class will Verify File Document To Folder Workitem and Document Duplicate with Different Class of
 * Document Workitem
 **/
public class INFO_7533
extends SuperClassIWM {

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
	public void testVerifyFileDocumentToFolderWorkitemDocumentWorkitemDifferentClassDocumentDuplicate() {

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-7533 is for Verifying File Document To Folder Workitem Different Class Document Duplicate of Document Workitem");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_7533_VerifyFileDocumentToFolderWorkitemDocumentWorkitemDifferentClassDocumentDuplicateTest");
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
			String dupStr = ExcelLib.getCellValue(xlpath, sheet, 8, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("INFO_7533_VerifyFileDocumentToFolderWorkitemDocumentWorkitemDifferentClassDocumentDuplicateTest",	true);
			ATUReports.add("INFO_7533_VerifyFileDocumentToFolderWorkitemDocumentWorkitemDifferentClassDocumentDuplicateTest", true);
			Log.info("INFO_7533_VerifyFileDocumentToFolderWorkitemDocumentWorkitemDifferentClassDocumentDuplicateTest");

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

			Reporter.log("Workitem Type : "+folderWorkitemType,true);
			ATUReports.add("Workitem Type : "+folderWorkitemType,true);
			Log.info("Workitem Type : "+folderWorkitemType);

			cwp.CreateWorkitem(folderWorkitem, className, folderWorkitemType);
			Reporter.log("Folder: " + folderWorkitem + " has been created", true);
			ATUReports.add("Folder: " + folderWorkitem + " has been created", true);
			Log.info("Folder: " + folderWorkitem + " has been created");

			util.wait(time);

			cwp.getNameSearchTextbox().clear();

			cwp.searchByNameInWorkitemList(workitem);
			util.wait(time);

			cwp.getCheckBoxWorkItem(workitem).click();

			Reporter.log("Selected: " + workitem + " to file work item", true);
			ATUReports.add("Selected: " + workitem + " to file work item", true);
			Log.info("Selected: " + workitem + " to file work item");

			cwp.getFileworkitemBtn().click();
			util.wait(time);
			util.wait(time);

			boolean fileWorkitemDialogBoxPresence = util.verifyObjectPresentReturnsBool(cwp.getFileWorkItem_win());
			if (fileWorkitemDialogBoxPresence) {
				Reporter.log("File Work item dialog box is open", true);
				ATUReports.add("File Work item dialog box is open", true);
				Log.info("File Work item dialog box is open");
			} else {
				Reporter.log("File Work item dialog box is not open", true);
				ATUReports.add("File Work item dialog box is not open", true);
				Assert.fail("File Work item dialog box is not open");
			}


			Select sel = new Select(cwp.getSelectFolderDropDown());
			sel.selectByVisibleText(folderWorkitem);
			Reporter.log("Selected destination folder as: " + folderWorkitem, true);
			ATUReports.add("Selected destination folder as: " + folderWorkitem, true);
			Log.info("Selected destination folder as: " + folderWorkitem);

			util.wait(time);
			cwp.getFileWorkitemAcceptBtn().click();
			util.waitForPageToLoad();
			util.wait(time);

			cwp.searchByNameInWorkitemList(folderWorkitem);
			util.waitForPageToLoad();

			cwp.getWorkItemName(folderWorkitem).click();
			util.waitForPageToLoad();

			wdp.getContentField().click();

			util.wait(time);
			boolean docWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (docWorkitemPresence) {
				Reporter.log(workitem + " Successfully filed in folder: " + folderWorkitem, true);
				ATUReports.add(workitem + " Successfully filed in folder: " + folderWorkitem, true);
				Log.info(workitem + " Successfully filed in folder: " + folderWorkitem);
			} else {
				Reporter.log(workitem + " failed to file in folder: " + folderWorkitem, true);
				ATUReports.add(workitem + " failed to file in folder: " + folderWorkitem, true);
				Assert.fail(workitem + " failed to file in folder: " + folderWorkitem);
			}

			util.waitForPageToLoad();

			Reporter.log("WorkItem is clicked", true);
			ATUReports.add("WorkItem is clicked", true);
			Log.info("WorkItem is clicked");
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");
			util.wait(time);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItem is opened", true);
			ATUReports.add("WorkItem is opened", true);
			Log.info("WorkItem is opened");

			wdp.getActionsDropDown().click();
			Reporter.log("Actions Drop Down is clicked", true);
			ATUReports.add("Actions Drop Down is clicked", true);
			Log.info("Actions Drop Down is clicked");

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
				ATUReports.add("Verify File Document To Folder Workitem Different Class Document Duplicate of Document Workitem",
						"",
						"Document Duplicate of Document Workitem which is Filed Document To Folder Workitem Different Class should be displayed",
						"Document Duplicate of Document Workitem which is Filed Document To Folder Workitem Different Class is displayed",
						true);
			} else {
				Reporter.log("Duplicate Workitem is not present in grid", true);
				ATUReports.add("Duplicate Workitem is not present in grid", true);
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
				ATUReports.add("Verify File Document To Folder Workitem Different Class Document Duplicate of Document Workitem",
						"",
						"Document Duplicate of Document Workitem which is Filed Document To Folder Workitem Different Class should be displayed",
						"Document Duplicate of Document Workitem which is Filed Document To Folder Workitem Different Class is displayed",
						true);
			}

			else {
				Reporter.log("New Class Name is not in metadata screen in workitems page", true);
				ATUReports.add("New Class Name is not in metadata screen in workitems page", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("New Class Name is not in metadata screen in workitems page");
			}

			cwp.getMetaDataDialogBoxCloseBtn(workitem + dupStr).click();
			Reporter.log("Meta Data Dialog Box Close Button is clicked", true);
			ATUReports.add("Meta Data Dialog Box Close Button is clicked", true);

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("VerifyFileDocumentToFolderWorkitemDocumentWorkitemDifferentClassDocumentDuplicateTest is failed.", true);
			ATUReports.add("VerifyFileDocumentToFolderWorkitemDocumentWorkitemDifferentClassDocumentDuplicateTest is failed.", true);
			Assert.fail("VerifyFileDocumentToFolderWorkitemDocumentWorkitemDifferentClassDocumentDuplicateTest is failed.");
		}
	}
}
