package apd.infoimage.iwm.tests;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class INFO_3006 extends SuperClassIWM {
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

	/**
	 * This test method will verify whether a popup with add new ,update and close
	 * option is displayed for formfields
	 * 
	 * @author pradhanJ
	 * @throws Exception
	 */
	@Test(enabled = true, groups = { "CustomJSForms" })
	public void testVerifyCloseBtnFunction() {
		String witemName = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		ATUReports.setTestCaseReqCoverage(
				"This Scenario will verify whether a popup with add new ,update and close option is displayed for formfields");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		try {
			// Navigate to Custom JS Forms page
			Log.startTestCase("INFO_3006_VerifyCloseBtnInCustomJSForm");
			hp.getCustomJSFormsTab().click();
			util.waitForPageToLoad();

			// Apply and save the custom JS script
			cjfp.applySavePublishCustomJS();

			// Navigate to workitems page and cretae a workitem.
			hp.getWorkItemTab().click();

			cwp.CreateWorkitem(witemName, "archive", "Document");
			Reporter.log("Documet: " + witemName + " has been created", true);
			ATUReports.add("Documet: " + witemName + " has been created", true);

			Thread.sleep(5000);
			// Open form fiels in workitem page and validate the popup
			Actions act = new Actions(Driver.driver);

			act.moveToElement(cwp.getActionBtn(witemName)).click().perform();
			Thread.sleep(2000);
			act.moveToElement(cwp.getformFiledsBtn()).click().perform();

			util.waitForPageToLoad();

			// Double click on form field and verify the popup

			act.moveToElement(cwp.getID_CODE_Tf()).doubleClick(cwp.getID_CODE_Tf()).perform();

			Thread.sleep(3000);
			if (cjfp.getIdCodeCustomJSPopup().isDisplayed()) {
				Reporter.log("ID code add new popup is displayed after clicking on ID code field.", true);
				ATUReports.add("ID code add new popup is displayed after clicking on ID code field.", true);
				if (cjfp.getAddNewTextBox().isDisplayed()) {
					Reporter.log("Add new Field textbox is displayed in the popup", true);
					ATUReports.add("Add new Field textbox is displayed in the popup", true);
				} else {
					Assert.fail("Add new Field textbox is NOT displayed in the popup");
				}
				if (cjfp.getAddNewBtn().isDisplayed()) {
					Reporter.log("Add New button is displayed in the popup", true);
					ATUReports.add("Add New button is displayed in the popup", true);
				} else {
					Assert.fail("Add New button is NOT displayed in the popup");
				}
				if (cjfp.getUpdateFieldCloseBtn().isDisplayed()) {
					Reporter.log("Update field and close button is displayed", true);
					ATUReports.add("Update field and close button is displayed", true);
				} else {
					Assert.fail("Update field and close button is NOT displayed in the popup");
				}
				if (cjfp.getClosePopupBtn().isDisplayed()) {
					Reporter.log("Close add new formfield popup button is present", true);
					ATUReports.add("Close add new formfield popup button is present", true);
				} else {
					Assert.fail("Close add new formfield popup button is present");
				}

				cjfp.getClosePopupBtn().click();
				Thread.sleep(3000);
				cwp.getCloseFormBtn().click();

			} else {
				Assert.fail("ID code add new popup is NOT displayed after clicking on ID code field.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("validation for popup with add new,update and close option failed", true);
			ATUReports.add("validation for popup with add new,update and close option failed", true);
			ATUReports.add("failed to execute test INFO_3006_VerifyCloseBtnInCustomJSForm ", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
		} finally {
			Log.endTestCase("INFO_3006_VerifyCloseBtnInCustomJSForm");
		}
	}

}
