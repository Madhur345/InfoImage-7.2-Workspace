package apd.infoimage.rm.tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apd.infoimage.iwm.genericLib.CommonUtils;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class INFO_9542_VerifyProperMessageIsDisplayedWhileAddingStepsInDispositionRules extends SuperClassIWM {
	@BeforeMethod
	public void beforMethod() {
		Driver.driver.get(rmProp.getProperty("rmUrl"));
		util.waitForPageToLoad();
		rmlp.loginToRM("uid", "pwd", "domain");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod() {
		rmhp.logoutOfRM();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void test_VerifyProperMessageIsDisplayedWhileAddingStepsInDispositionRules() {
		try {
			Log.startTestCase("INFO_9542_VerifyProperMessageIsDisplayedWhileAddingStepsInDispositionRules");
			ATUReports.setTestCaseReqCoverage(
					"This Scenario INFO-9542  is To  test proper message is displayed while adding steps in disposition rules");
			ATUReports.setAuthorInfo("Princi", "JUNE-2018", "0.3");

			Reporter.log("User is in Homepage of RM", true);
			ATUReports.add("User is in Homepage of RM", true);
			Log.info("User is in Homepage of RM");

			String sheet = rmProp.getProperty("sheet");
			String xlpath = rmProp.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String catStr = ExcelLib.getCellValue(xlpath, sheet, 5, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String seriesName = str + util.getSysDate(0, date);
			String categoryName = catStr + util.getSysDate(0, date);
			String newCatStr = ExcelLib.getCellValue(xlpath, sheet, 7, 1);
			String newCategoryName = newCatStr + util.getSysDate(0, date);

			rmhp.getFilePlanTab().click();
			Reporter.log("File Plan Tab is clicked", true);
			ATUReports.add("File Plan Tab is clicked", true);
			Log.info("File Plan Tab is clicked");

			rmfpp.getAddSeriesButton().click();
			Reporter.log("Add Series Button is clicked", true);
			ATUReports.add("Add Series Button is clicked", true);
			Log.info("Add Series Button is clicked");

			rmfpp.getSeriesName_TF().sendKeys(seriesName);
			Reporter.log("Series Name is sent to text box", true);
			ATUReports.add("Series Name is sent to text box", true);
			Log.info("Series Name is sent to text box");

			rmfpp.getSeriesTitle_TF().sendKeys(seriesName);
			Reporter.log("Series Name is sent to Title text box", true);
			ATUReports.add("Series Name is sent to Title text box", true);
			Log.info("Series Name is sent to Title text box");

			rmfpp.getSeriesDescription_TF().sendKeys(seriesName);
			Reporter.log("Series Name is sent to Description text box", true);
			ATUReports.add("Series Name is sent to Description text box", true);
			Log.info("Series Name is sent to Description text box");

			rmfpp.getAddSeries_SaveBtn().click();
			Reporter.log("Add Series Save Button is clicked", true);
			ATUReports.add("Add Series Save Button is clicked", true);
			Log.info("Add Series Save Button is clicked");
			util.waitForPageToLoad();

			boolean addSeriessucMsg = util.verifyObjectPresentReturnsBool(rmfpp.getAddSeriesSucMsg());
			if (addSeriessucMsg) {
				Reporter.log("Series is added successfully", true);
				ATUReports.add("Series is added successfully", true);
				Log.info("Series is added successfully ");
			} else {
				Reporter.log("Series is NOT added successfully", true);
				ATUReports.add("Series is NOT added successfully", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Series is NOT added successfully");
			}
			rmfpp.getFilePlanPlusSymbol().click();
			util.waitForPageToLoad();
			Reporter.log("Plus(Expand)file plan symbol is clicked  ", true);
			ATUReports.add("Plus(Expand)file plan symbol is clicked", true);
			Log.info("Plus(Expand)file plan symbol is clicked ");
			Thread.sleep(2000);

			rmfpp.getSeriesUnderFilePlan(seriesName).click();
			Reporter.log("Created Series is selected under file plan view", true);
			ATUReports.add("Created Series is selected under file plan view", true);
			Log.info("Created Series is selected under file plan view");

			util.waitForElementPresent(rmfpp.getAddCategoryButton());
			rmfpp.getAddCategoryButton().click();
			Reporter.log("Add Category button is clicked", true);
			ATUReports.add("Add Category button is clicked", true);
			Log.info("Add Category is button clicked");
			util.waitForElementPresent(rmfpp.getAddCategoryPopup());

			rmfpp.getCategoryName_TF().sendKeys(categoryName);
			Reporter.log("Categorty Name is enetred in the name box in add category popup", true);
			ATUReports.add("category Name is enetred in the name box in add category popup", true);
			Log.info("category Name is enetred in the name box in add category popup");

			rmfpp.getCategorytTitle_TF().sendKeys(categoryName);
			Reporter.log("Category tite is enetered", true);
			ATUReports.add("Category tite is enetered", true);
			Log.info("Category tite is enetered");

			rmfpp.getCategoryDesc_TF().sendKeys(categoryName);
			Reporter.log("Category description is enetred", true);
			ATUReports.add("Category description is enetred", true);
			Log.info("category description is enetred");

			rmfpp.getAddCategorySaveButton().click();
			Reporter.log("Add category save button is clicked", true);
			ATUReports.add("Add category save button is clicked", true);
			Log.info("Add category save button is clicked");
			util.waitForElementPresent(rmfpp.getAddCategorySucMsg());

			boolean addCategorysucMsg = util.verifyObjectPresentReturnsBool(rmfpp.getAddCategorySucMsg());
			if (addCategorysucMsg) {
				Reporter.log("Category is added successfully message is dispalyed", true);
				ATUReports.add("Category is added successfully message is dispalyed", true);
				Log.info("Category is added successfully message is dispalyed");
			} else {
				Reporter.log("Add Category success message is not dispalyed", true);
				ATUReports.add("Add Category success message is not dispalyed", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Add Category success message is not dispalyed");
			}

			Select select = new Select(rmfpp.getRowsDropdown());
			select.selectByVisibleText("Show All");
			;
			util.waitForPageToLoad();

			boolean CategoryPresent = util
					.verifyObjectPresentReturnsBool(rmfpp.getCategoryNameRadioInCategoryView(categoryName));
			if (CategoryPresent) {
				Reporter.log("Added Category is listed under seried view", true);
				ATUReports.add("Added Category is listed under seried view", true);
				Log.info("Added Category is listed under seried view");
			} else {
				Reporter.log("Add Category test failed", true);
				ATUReports.add("Category is not added", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Add Category test failed");
			}
			rmfpp.getCategoryNameRadioInCategoryView(categoryName).click();
			Reporter.log("Created Category radio button is selected", true);
			ATUReports.add("Created Category radio button is selected", true);
			Log.info("Created Category radio button is selected");

			rmfpp.getViewDispostionSchedule().click();
			Reporter.log("Dispostion Schedule View button is clicked", true);
			ATUReports.add("Dispostion Schedule View button is clicked", true);
			Log.info("Dispostion Schedule View button is clicked");

			Thread.sleep(2000);
			// util.waitForPageToLoad();
			rmfpp.getCategoryDispostionScheduleAddstep().click();
			Reporter.log("Dispostion Schedule Add step button is clicked", true);
			ATUReports.add("Dispostion Schedule Add step button is clicked", true);
			Log.info("Dispostion Schedule Add step button is clicked");
			Thread.sleep(2000);
			// util.waitForElementPresent(rmfpp.getViewDispositionSchedulerPopup());

			Select select1 = new Select(rmfpp.getCategoryDispositionScheduleNewStep());
			select1.selectByVisibleText("Retain");
			rmfpp.getperiodcheck_checkbox().click();
			rmfpp.getCategoryDispositionSchedule_Submit().click();
			util.waitForElementPresent(rmfpp.getInvalidStepMsg());

			boolean InvalidStepMsgPresent = util.verifyObjectPresentReturnsBool(rmfpp.getInvalidStepMsg());
			if (InvalidStepMsgPresent) {
				Reporter.log("Invalid step message is dispalyed", true);
				ATUReports.add("Invalid step message is dispalyed", true);
				Log.info("Invalid step message is dispalyed");
			} else {
				Reporter.log("Series is not deleted", true);
				ATUReports.add("Series is not deleted", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Series is not deleted");
			}

		}

		catch (Exception e) {
			e.printStackTrace();

		} finally {
			Log.endTestCase("INFO_9542_VerifyProperMessageIsDisplayedWhileAddingStepsInDispositionRules");
		}
	}
}
