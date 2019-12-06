package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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


public class INFO_10679 extends SuperClassIWM {

	@BeforeMethod
	public void setUp() {
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown(){
		hp.logoutApp();
	}

	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 1, groups = { "DocumentDuplicate" })
	public void VerifyReturntoQueuFunctionalityForFolderTypeOfWorkitemWhereDocumentIsFiledInsideFolder() {

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-10679 is for Verifying 'Return to Queue' functionality for folder type of workitem where document is filed inside folder");
		ATUReports.setAuthorInfo("GuptaPr2", "Aug-2018", "0.3");

		try {
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
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("VerifyReturntoQueuFunctionalityForFolderTypeOfWorkitemWhereDocumentIsFiledInsideFolder",	true);
			ATUReports.add("VerifyReturntoQueuFunctionalityForFolderTypeOfWorkitemWhereDocumentIsFiledInsideFolder", true);
			Log.info("VerifyReturntoQueuFunctionalityForFolderTypeOfWorkitemWhereDocumentIsFiledInsideFolder");

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

			Reporter.log("Workitem Type : " + folderWorkitemType, true);
			ATUReports.add("Workitem Type : " + folderWorkitemType, true);
			Log.info("Workitem Type : " + folderWorkitemType);

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
				Log.info("File Work item dialog box is not open");
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

			util.wait(time);
			util.wait(time);


			Driver.driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click(); //Added by Vishal
			cwp.getFileworkitemBtn().click();
			

//			cwp.getWorkItemName(folderWorkitem).click();
////			WebElement list=Driver.driver.findElement(By.id("file_workitem"));  //Added by Vishal 
////			Select s = new Select(list);
////			sel.selectByVisibleText(folderWorkitem);
			cwp.getFileWorkitemAcceptBtn().click();
			util.waitForPageToLoad();
			util.wait(time);

//			wdp.getContentField().click();
//			Reporter.log("Content field is clicked", true);
//			ATUReports.add("Content field is clicked", true);
//			util.wait(time);

			wdp.getSearchWorkItem().sendKeys(workitem);

			boolean docWorkitemPresence = util.verifyObjectPresentReturnsBool(wdp.getContentList());
			if (docWorkitemPresence) {
				Reporter.log(workitem + " Successfully filed in folder: " + folderWorkitem, true);
				ATUReports.add(workitem + " Successfully filed in folder: " + folderWorkitem, true);
				Log.info(workitem + " Successfully filed in folder: " + folderWorkitem);
			} else {
				Reporter.log(workitem + " failed to file in folder: " + folderWorkitem, true);
				ATUReports.add(workitem + " failed to file in folder: " + folderWorkitem, true);
				Log.info(workitem + " failed to file in folder: " + folderWorkitem);
				Assert.fail(workitem + " failed to file in folder: " + folderWorkitem);
			}

			util.wait(time);
			hp.getWorkItemTab().click();
			cwp.searchByNameInWorkitemList(workitem);
			util.waitForPageToLoad();
			Reporter.log("Sending Workitem to Workflow", true);
			util.wait(time);

			cwp.sendWorkItemToDefaultQueue(workitem);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			util.wait(time);
			util.waitForPageToLoad();

			Reporter.log("Retrieving Workitem", true);
			ATUReports.add("Retrieving Workitem", true);
			Log.info("Retrieving Workitem");
			ip.retrieveWorkItem(workitem);

			cwp.searchByNameInWorkitemList(workitem);
			hp.getFirstRowFirstCell().click();
			Reporter.log("Action Button of Workitem is clicked", true);
			ATUReports.add("Action Button of Workitem is clicked", true);
			Log.info("Action Button of Workitem is clicked");
			util.wait(time);

			boolean returnToQueueOptionEnabled = hp.getreturnToQueueOption().isEnabled();

			if(returnToQueueOptionEnabled) {
				Reporter.log("return To Queue Option is enabled", true);
				ATUReports.add("return To Queue Option is enabled", true);
				Log.info("return To Queue Option is enabled");
			} else {
				Reporter.log("return To Queue Option is not enabled", true);
				ATUReports.add("return To Queue Option is not enabled", true);
				Log.info("return To Queue Option is not enabled");
				Assert.fail("return To Queue Option is not enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("INFO_10679 VerifyReturntoQueuFunctionalityForFolderTypeOfWorkitemWhereDocumentIsFiledInsideFolder is failed.", true);
			ATUReports.add("INFO_10679 VerifyReturntoQueuFunctionalityForFolderTypeOfWorkitemWhereDocumentIsFiledInsideFolder is failed.", true);
			Log.info("INFO_10679 VerifyReturntoQueuFunctionalityForFolderTypeOfWorkitemWhereDocumentIsFiledInsideFolder is failed.");
			Assert.fail("INFO_10679 VerifyReturntoQueuFunctionalityForFolderTypeOfWorkitemWhereDocumentIsFiledInsideFolder is failed.");
		} finally {
			Log.endTestCase("INFO_10679_VerifyReturntoQueuFunctionalityForFolderTypeOfWorkitemWhereDocumentIsFiledInsideFolder");
		}
	}
}
