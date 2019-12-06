package apd.infoimage.iwm.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import atu.testng.reports.ATUReports;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

public class INFO_8563 extends SuperClassIWM {

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
	 * This test method will load the Custom JS script and apply it to a class and
	 * publish
	 * 
	 * @author pradhanJ
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true, groups = { "CustomJSForms" })
	public void testapplyCustomJS() {

		try {
			hp.getCustomJSFormsTab().click();
			util.waitForPageToLoad();

			// Validate whether custom JS Forms page is displayed or not

			boolean customJsFormsHeaderPresent = util.verifyObjectPresentReturnsBool(cjfp.getCustomJSFormsHeader());
			if (customJsFormsHeaderPresent) {
				Reporter.log("Custom JS Forms page  is dispalyed", true);
				ATUReports.add("Custom JS Forms page  is dispalyed", true);

				// Click on Archive class
				cjfp.getAvailableClassArchive().click();
				util.waitForPageToLoad();

				cjfp.getApplyScriptButton().click();

				// Validate whether script is applied successfully or not.

				String sucMsg = cjfp.getSuccessMsgDialog().getText();

				if (sucMsg.contains("applied Successfully")) {
					Reporter.log("Script is applied sucessfully.", true);
					ATUReports.add("Script is applied sucessfully.", true);
				} else {
					Reporter.log("Script is Not applied   sucessfully.", true);
					ATUReports.add("Script is Not applied   sucessfully.", true);
					Assert.fail("Script is Not applied sucessfully.");
				}
				// Click on save script button and validate if saved or not.
				util.jclick(cjfp.getCloseSucMsgDialogIcon());
				Thread.sleep(2000);
				cjfp.getSaveScriptButton().click();
				util.waitForPageToLoad();
				Thread.sleep(10000);
				// Validate whether script saved successfully or not

				String sMsg = cjfp.getSuccessMsgDialog().getText();
				if (sMsg.equalsIgnoreCase("Script saved Successfully")) {
					Reporter.log("Script is saved sucessfully.", true);
					ATUReports.add("Script is saved sucessfully.", true);

				} else {
					Reporter.log("Script is Not saved  sucessfully.", true);
					ATUReports.add("Script is Not saved  sucessfully.", true);
					Assert.fail("Script is Not saved sucessfully.");
				}
				// Click on Publish script button and validate
				cjfp.getCloseSucMsgDialogIcon().click();
				Thread.sleep(5000);
				cjfp.getPublishScriptButton().click();
				util.waitForPageToLoad();
				Thread.sleep(2000);
				String publishMsg = cjfp.getSuccessMsgDialog().getText();

				if (publishMsg.equalsIgnoreCase("Script published Successfully")) {
					Reporter.log("Script is published sucessfully.", true);
					ATUReports.add("Script is published sucessfully.", true);
				} else {
					Reporter.log("Script is Not published  sucessfully.", true);
					ATUReports.add("Script is Not published  sucessfully.", true);
					Assert.fail("Script is Not publisheds sucessfully.");
				}
				cjfp.getCloseSucMsgDialogIcon().click();

				// Validate whether custom JS is applied in Formields color

				util.waitForPageToLoad();
				util.wait(5000);
				String bgColor_IDCode = cjfp.getId_codeFormField().getAttribute("style");
				if (bgColor_IDCode.contains("background-color: rgb")) {
					Reporter.log("ID_CODE formField background color has been changed", true);
					ATUReports.add("ID_CODE formField background color has been changed", true);

				} else {
					Reporter.log("ID_CODE formField background color is not changed", true);
					ATUReports.add("ID_CODE formField background color is not changed", true);
					Assert.fail("ID_CODE formField background color is not changed");
				}
			} else {
				Reporter.log("Custom JS Forms Page not dispalyed", true);
				ATUReports.add("Custom JS Forms Page not dispalyed", true);
				Assert.fail("Custom JS Forms Page not dispalyed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Applying custom JS script test failed", true);
			ATUReports.add("Applying custom JS script test failed", true);

		}
	}

}
