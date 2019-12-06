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
 * INFO-13126
 * To verify Add Series,edit Series and delete Series in RM application
 */
public class INFO_13126_VerifyAddingSeriesInRM extends SuperClassIWM{
	
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
	public void testRM_AddSeriesEditSeriesDeleteSeries()
	{
		try {
			Log.startTestCase("INFO_13126_RM_AddSeriesEditSeriesDeleteSeries");
			ATUReports.setTestCaseReqCoverage(
					"This Scenario INFO-13126  is To verify Add Series,edit Series and delete Series in RM application ");
			ATUReports.setAuthorInfo("Jayashri", "JUNE-2018", "0.3");

			Reporter.log("User is in Homepage of RM", true);
			ATUReports.add("User is in Homepage of RM", true);
			Log.info("User is in Homepage of RM");
			
			String sheet = rmProp.getProperty("sheet");
			String xlpath = rmProp.getProperty("xlpath");			
			
			String str = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String seriesName = str + util.getSysDate(0, date);
			
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
			
			boolean addSeriessucMsg=util.verifyObjectPresentReturnsBool(rmfpp.getAddSeriesSucMsg());
			if(addSeriessucMsg)
			{
				Reporter.log("Series is added successfully message is dispalyed", true);
				ATUReports.add("Series is added successfully message is dispalyed", true);
				Log.info("Series is added successfully message is dispalyed");
			}
			else
			{
				Reporter.log("Add series success message is not dispalyed", true);
				ATUReports.add("Add series success message is not dispalyed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Add series success message is not dispalyed");
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
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.error("Add Series in File Plan test failed " + e.getMessage());
			Reporter.log("Add Series in File Plan test failed", true);
			Assert.fail("Add Series in File Plan test failed");
		}
		finally {
			Log.endTestCase("INFO_13126_VerifyAddingSeriesInRM");
		}
	}
}
