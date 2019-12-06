package apd.infoimage.rm.tests;

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
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

/**
 * @author PradhanJ
 * INFO-13168
 * To verify add and edit series in RM application
 */
public class INFO_13168_VerifyEditSeriesafterAddingInRM extends SuperClassIWM{

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
	public void testVerifyEditSeriesafterAddingInRM()
	{
		try {
			Log.startTestCase("INFO_13168_VerifyEditSeriesafterAddingInRM");
			ATUReports.setTestCaseReqCoverage(
					"This Scenario INFO-13168  is To verify add and edit series in RM application");
			ATUReports.setAuthorInfo("Jayashri", "JUNE-2018", "0.3");

			Reporter.log("User is in Homepage of RM", true);
			ATUReports.add("User is in Homepage of RM", true);
			Log.info("User is in Homepage of RM");
			
			String sheet = rmProp.getProperty("sheet");
			String xlpath = rmProp.getProperty("xlpath");
			
			String serString = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String seriesName = serString + util.getSysDate(0, date);
			String newSerString = ExcelLib.getCellValue(xlpath, sheet, 6, 1);
			String newSeriesName = newSerString + util.getSysDate(0, date);
			
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
			
			boolean addSeriessucMsg=util.verifyObjectPresentReturnsBool(rmfpp.getAddSeriesSucMsg());
			if(addSeriessucMsg)
			{
				Reporter.log("Series is added successfully", true);
				ATUReports.add("Series is added successfully", true);
				Log.info("Series is added successfully ");
			}
			else
			{
				Reporter.log("Series is NOT added successfully", true);
				ATUReports.add("Series is NOT added successfully", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Series is NOT added successfully");
			}
			
			Select select=new Select(rmfpp.getRowsDropdown());
			select.selectByVisibleText("Show All");;
			util.waitForPageToLoad();
			
			boolean sereiesPresent=util.verifyObjectPresentReturnsBool(rmfpp.getSeriesNameRadioInSeriesView(seriesName));
			
			if(sereiesPresent)
			{
				Reporter.log("Added series is listed under seried view", true);
				ATUReports.add("Added series is listed under seried view", true);
				Log.info("Added series is listed under seried view");
			}
			else
			{
				Reporter.log("Add series test failed", true);
				ATUReports.add("Series is not added", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Add series test failed");
			}
			rmfpp.getSeriesNameRadioInSeriesView(seriesName).click();
			Reporter.log("Created series radio button is clicked", true);
			ATUReports.add("Created series radio button is clicked", true);
			Log.info("Created series radio button is clicked ");
			
			rmfpp.getEditSeriesButton().click();
			Reporter.log("Edit series button is clicked", true);
			ATUReports.add("Edit series button is clicked", true);
			Log.info("Edit series button is clicked ");
			util.waitForElementPresent(rmfpp.getEditSeriesPopup());
			
			rmfpp.getSeriesNameInEditSeries().clear();
			rmfpp.getSeriesNameInEditSeries().sendKeys(newSeriesName);
			Reporter.log("New series name is enterd in the text box in edit series popup", true);
			ATUReports.add("New series name is enterd in the text box in edit series popup", true);
			Log.info("New series name is enterd in the text box in edit series popup ");
			
			rmfpp.getUpdateBtnInEditSeries().click();
			util.waitForPageToLoad();
			
			boolean editSeriesSucMsg=util.verifyObjectPresentReturnsBool(rmfpp.getEditSeriesSucMsg());
			if(editSeriesSucMsg)
			{
				Reporter.log("Series is updated successfully message is dispalyed", true);
				ATUReports.add("Series is updated successfully message is dispalyed", true);
				Log.info("Series is updated successfully message is dispalyed");
			}
			else
			{
				Reporter.log("Series is NOT updated successfully", true);
				ATUReports.add("Series is NOT updated successfully", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Series is NOT updated successfully");
			}
			boolean newSereiesPresent=util.verifyObjectPresentReturnsBool(rmfpp.getSeriesNameRadioInSeriesView(newSeriesName));
			
			if(newSereiesPresent)
			{
				Reporter.log("Updated series is listed under series view", true);
				ATUReports.add("Updated series is listed under series view", true);
				Log.info("Updated series is listed under series view");
			}
			else
			{
				Reporter.log("Edit series test failed", true);
				ATUReports.add("Edit series test failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Edit series test failed");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.error("Edit Series in File Plan test failed " + e.getMessage());
			Reporter.log("Edit Series in File Plan test failed ", true);
			Assert.fail("Edit Series in File Plan test failed ");
		}
		finally {
			Log.endTestCase("INFO_13168_VerifyEditSeriesafterAddingInRM");
		}
	}
}
