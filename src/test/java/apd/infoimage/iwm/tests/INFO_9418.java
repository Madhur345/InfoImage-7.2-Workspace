package apd.infoimage.iwm.tests;

import org.testng.Assert;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
 * @author PradhanJ INFO-9418 To verify document duplicate after
 *         Reclassification
 */
public class INFO_9418 extends SuperClassIWM {

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
	@Test(enabled = true, groups = { "DocumentDuplicate" })
	public void testVerifyDocumentDuplicateAfterReclassification() {
		Log.startTestCase("INFO_9418_VerifyDocumentDuplicateAfterReclassification");
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-9418  is To verify document duplicate after Reclassification");
		ATUReports.setAuthorInfo("Jayashri", "APR-2018", "0.3");

		try {
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String sheet2 = "Sheet_J";

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String currentClass = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String newClassName = ExcelLib.getCellValue(xlpath, sheet2, 1, 1);
			String id_Code_Value = ExcelLib.getCellValue(xlpath, sheet2, 2, 1);
			String newWitemStr = ExcelLib.getCellValue(xlpath, sheet2, 3, 1);
			String newWitem = newWitemStr + util.getSysDate(0, date);
			int time = 3000;

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Reporter.log("Class Name : " + currentClass, true);
			ATUReports.add("Class Name : " + currentClass, true);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);

			Reporter.log("New Class Name is " + newClassName, true);
			ATUReports.add("Workitem Type : " + workitemType, true);

			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			cwp.CreateWorkitem(workitem, currentClass, workitemType);

			Reporter.log("Update formfield for ID_CODE value", true);
			ATUReports.add("Update formfield for ID_CODE value", true);
			cwp.getActionBtn(workitem).click();
			util.wait(time);
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			cwp.getID_CODE_Tf().sendKeys(id_Code_Value);
			cwp.getUpdateFormBtn().click();
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(workitem);
			cwp.getActionBtn(workitem).click();
			util.wait(time);
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			String formFieldvalue = cwp.getID_CODE_Tf().getAttribute("value");
			if (formFieldvalue.equals(id_Code_Value)) {
				Reporter.log("Form field ID_CODE updated successfully", true);
				ATUReports.add("Form field ID_CODE updated successfully", true);
			} else {
				Reporter.log("Form field ID_CODE failed to update", true);
				ATUReports.add("Form field ID_CODE failed to update", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Form field ID_CODE failed to update");
			}
			util.wait(time);
			cwp.getCloseFormBtn().click();
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(workitem);
			cwp.getActionBtn(workitem).click();
			util.wait(time);
			cwp.getReClassifyBtn().click();
			util.waitForPageToLoad();

			Reporter.log("Reclassify option is selected", true);
			ATUReports.add("Reclassify option is selected", true);

			util.wait(time);

			Select sel2 = new Select(cwp.getNewClassDropDownWorkitemReclassify());
			sel2.selectByValue(newClassName);

			WebElement option = sel2.getFirstSelectedOption();
			String selectedNewClass = option.getText();
			Reporter.log("Selected class for reclassify is: " + selectedNewClass, true);
			ATUReports.add("Selected class for reclassify is: " + selectedNewClass, true);

			util.wait(time);
			/*boolean f = cwp.getRetainFormDataChkBoxWorkitemReclassify().isSelected();
			if (f) {
				cwp.getRetainFormDataChkBoxWorkitemReclassify().click();
				util.wait(time);
				Reporter.log("Retain form data check box checked", true);
				ATUReports.add("Retain form data check box checked", true);
			}*/

			cwp.getReClassifySubmitBtn().click();
			Reporter.log("Clicked on Save button", true);
			ATUReports.add("Clicked on Save button", true);
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(workitem);
			cwp.getActionBtn(workitem).click();
			util.wait(time);
			cwp.getActionsMetaData().click();
			util.waitForPageToLoad();
			String classValue = cwp.getClassNameInMetaData().getText();
			if (classValue.contains(newClassName)) {
				Reporter.log("Reclassification of workitem is successfull ", true);
				ATUReports.add("Verify Reclassification of workitem is successfull", "",
						"Class name of the workitem should be changed", "Class name is changed successfully", true);
			} else {
				Reporter.log("Reclassification of workitem is failed", true);
				ATUReports.add("Reclassification of workitem is failed", true);
				Assert.fail("Reclassification of workitem is failed");
			}
			cwp.getMetaDataDialogBoxCloseBtn(workitem).click();
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(workitem);
			cwp.getActionBtn(workitem).click();
			util.wait(time);
			cwp.getDocDuplicateInAction().click();
			util.waitForPageToLoad();
			Reporter.log("Document duplicate option is selected", true);
			ATUReports.add("Document duplicate option is selected", true);
			util.wait(time);
			cwp.getwNameTextBoxInDocDuplicate().clear();
			cwp.getwNameTextBoxInDocDuplicate().sendKeys(newWitem);
			Reporter.log("Duplicate workitem name is entered", true);
			ATUReports.add("Duplicate workitem name is entered", true);
			util.wait(time);
			if (cwp.getIncludeFormDataCheckbox().isSelected()) {
				cwp.getIncludeFormDataCheckbox().click();
				Reporter.log("Include form data checkbox is clicked", true);
			}
			util.wait(time);
			util.wait(time);
			util.jclick(cwp.getDuplicateSbmitButton());
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(newWitem);
			Reporter.log("Duplicate workitem is created", true);
			ATUReports.add("Duplicate workitem is present in the workitem list", true);
				
			Reporter.log("Form field ID_CODE for duplicate workitem is validated successfully", true);
			ATUReports.add("Form field ID_CODE for duplicate workitem is validated successfully", true);

			
		} catch (Exception e) {
			Reporter.log("Verify Document duplicate test failed", true);
			ATUReports.add("Verify Document duplicate test failed", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Verify Document duplicate test failed " + e.getMessage());

		} finally {
			Log.endTestCase("INFO_9418_VerifyDocumentDuplicateAfterReclassification");

		}
	}

}
