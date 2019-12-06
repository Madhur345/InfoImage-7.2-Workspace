package apd.infoimage.iwm.tests;

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
/**
 * This test method will load the Custom JS script and apply it to a class and publish
 * @author pradhanJ
 * @throws Exception 
 */
public class INFO_11847 extends SuperClassIWM{
	
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
	@Test(enabled =true,groups={"CustomJSForms"})
	public void testVeifySavingCustomJSForm()
	{
		Log.startTestCase("INFO_11847_VeifySavingCustomJSForm");
		ATUReports.setTestCaseReqCoverage("This Scenario will test to verify saving of Custom JS scripts in IWM");
		ATUReports.setAuthorInfo("PradhanJ","JUL-2018","0.3");
		try
		{
			hp.getCustomJSFormsTab().click();
			util.waitForPageToLoad();
			
			//Validate whether custom JS Forms page is displayed or not
			
			boolean customJsFormsHeaderPresent=util.verifyObjectPresentReturnsBool(cjfp.getCustomJSFormsHeader());
			if(customJsFormsHeaderPresent)
			{
				Reporter.log("Custom JS Forms page  is dispalyed", true);
				ATUReports.add("Custom JS Forms page  is dispalyed", true);
				Log.info("Custom JS Forms page  is dispalyed");
				//Click on Archive class
				cjfp.getAvailableClassArchive().click();
				util.waitForPageToLoad();							
				cjfp.getApplyScriptButton().click();
				//Validate whether script is applied successfully or not.
				String sucMsg=cjfp.getSuccessMsgDialog().getText();
				
				if(sucMsg.contains("applied Successfully"))
				{
					Reporter.log("Script is applied sucessfully.", true);
					ATUReports.add("Script is applied sucessfully", true);	
					Log.info("Script is applied sucessfully");
				}
				else
				{
					Reporter.log("Script is Not applied   sucessfully.", true);
					ATUReports.add("Script is Not applied   sucessfully.", true);
					Assert.fail("Script is Not applied sucessfully.");
				}
				//Click on save script button and validate if saved or not.
				cjfp.getCloseSucMsgDialogIcon().click();
				Thread.sleep(2000);
				cjfp.getSaveScriptButton().click();
				util.waitForPageToLoad();
				//Thread.sleep(5000);
				util.waitForElementEnabled(cjfp.getSuccessMsgDialog());
				//Validate whether script saved successfully or not
				
				String sMsg=cjfp.getSuccessMsgDialog().getText();
				if(sMsg.contains("saved Successfully"))
				{
					Reporter.log("Script is saved sucessfully.", true);
					ATUReports.add("Script is saved sucessfully.", true);
					Log.info("Script is saved sucessfully.");
				}
				else
				{
					Reporter.log("Script is Not saved  sucessfully.", true);
					ATUReports.add("Script is Not saved  sucessfully.", true);
					Assert.fail("Script is Not saved sucessfully.");
				}
				//Click on Publish script button and validate 
				cjfp.getCloseSucMsgDialogIcon().click();
				Thread.sleep(5000);
				cjfp.getPublishScriptButton().click();
				util.waitForPageToLoad();
				//Thread.sleep(2000);
				util.waitForElementEnabled(cjfp.getSuccessMsgDialog());
				String publishMsg=cjfp.getSuccessMsgDialog().getText();
				
				if(publishMsg.contains("published Successfully"))
				{
					Reporter.log("Script is published sucessfully.", true);
					ATUReports.add("Script is published sucessfully", true);
					Log.info("Script is published sucessfully");
				}
				else
				{
					Reporter.log("Script is Not published  sucessfully.", true);
					ATUReports.add("Script is Not published  sucessfully.", true);
					Assert.fail("Script is Not publisheds sucessfully.");
				}
				cjfp.getCloseSucMsgDialogIcon().click();
				//Validate whether custom JS is applied in Formields color	
				util.waitForPageToLoad();
				
				String bgColor_IDCode=cjfp.getId_codeFormField().getAttribute("style");
				if(bgColor_IDCode.contains("background-color: rgb"))
				{
					Reporter.log("ID_CODE formField background color has been changed", true);
					ATUReports.add("ID_CODE formField background color has been changed", true);
					Log.info("ID_CODE formField background color has been changed");
				}
				else
				{
					Reporter.log("ID_CODE formField background color is not changed", true);
					ATUReports.add("ID_CODE formField background color is not changed", true);
					Assert.fail("ID_CODE formField background color is not changed");
				}
			}
			else
			{
				Reporter.log("Custom JS Forms Page not dispalyed", true);
				ATUReports.add("Custom JS Forms page  is dispalyed", true);
				Assert.fail("Custom JS Forms Page not dispalyed");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify custom JS Form test failed", true);
			ATUReports.add("Verify custom JS Form test failed", true);
			ATUReports.add("failed to execute Verify custom JS Form test",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			
		}
		finally{
			Log.endTestCase("INFO_11847_VeifySavingCustomJSForm");
			}

	}
}
