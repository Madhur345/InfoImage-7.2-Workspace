package apd.infoimage.iwm.tests;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.util.Properties;

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
 * INFO-16732
 * Test to verify the Reserve icon availability under the Actions button when workitem opened from search.	
 */
public class INFO_16732 extends SuperClassIWM{
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
	@Test
	public void testVerifyReserveIconUnderActionWhenWorkitemOpenedFromSearch() {
		Log.startTestCase("INFO_16732_VerifyReserveIconUnderActionWhenWorkitemOpenedFromSearch");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_16732  is Test to verify the Reserve icon availability "
					+ "under the Actions button when workitem opened from search.");
			ATUReports.setAuthorInfo("PradhanJ", "Nov-2018", "0.3");
			
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			int time=5000;
			
			hp.getSearchTab().click();
			Reporter.log("Click on Search Tab",true);
			ATUReports.add("Click on Search Tab",true);
			Log.info("Click on Search Tab");
			util.waitForPageToLoad();
			util.waitForElementEnabled(sp.getRefinedSearch());
			Thread.sleep(10000);
			
			sp.getBasicSearch().click();
			Reporter.log("Click on Basic Search Tab",true);
			ATUReports.add("Click on Basic Search Tab",true);
			Log.info("Click on Basic Search Tab");
			util.wait(time);
			
		    sp.getSearchBoxInBasicsearch().sendKeys("%");
		    Reporter.log("% is entered in the workitem name field",true);
			ATUReports.add("% is entered in the workitem name field",true);
			Log.info("% is entered in the workitem name field");
		    util.wait(time);
		    
		    sp.getSearchButton().click();
		    Reporter.log("Search button is clicked",true);
			ATUReports.add("Search button is clicked",true);
			Log.info("Search button is clicked");
			
			sp.getRetrievOpenBtnOfFirstWitem().click();
			Reporter.log("Retrive open button of workitem is clicked",true);
			ATUReports.add("Retrive open button of workitem is clicked",true);
			Log.info("Retrive open button of workitem is clicked");
			util.waitForPageToLoad();
			util.waitForElementEnabled(wdp.getActionButton());
			util.wait(10000);
			
			if(wdp.getActionButton().isDisplayed())
			{
				Reporter.log("Workitem detail page is opened",true);
				ATUReports.add("Workitem detail page is opened",true);
				Log.info("Workitem detail page is opened");
			}
			else
			{
				Reporter.log("Retrive open workitem failed in basic search",true);
				ATUReports.add("Retrive open workitem failed in basic search",true);
				Log.info("Retrive open workitem failed in basic search");
			}
						
			wdp.getActionsDropDown().click();
			Reporter.log("Actions Drop Down is clicked", true);
			ATUReports.add("Actions Drop Down is clicked", true);
			Log.info("Actions Drop Down is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			boolean reserveOptionPresence = wdp.getReserveOption().isDisplayed();

			if(reserveOptionPresence){
				Reporter.log("Reserve Option is present in Actions Menu",true);
				ATUReports.add("Reserve Option is present in Actions Menu",true);
				Log.info("Reserve Option is present in Actions Menu");
				ATUReports.add("verify the Reserve icon availability under the Actions button when workitem is created and opened", "","Reserve Option should be displayed",
						"Reserve Option is displayed", true);
			}else{
				Reporter.log("Reserve Option is not present in Actions Menu",true);
				ATUReports.add("Reserve Option is not present in Actions Menu",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Reserve Option is not present in Actions Menu");
			}
			
		}
		catch (Exception e) {
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add("Failed to verify the Reserve icon availability "
					+ "under the Actions button when workitem opened from search", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("Failed to verify the Reserve icon availability "
					+ "under the Actions button when workitem opened from search");
			
		}
		
		finally {
			Log.endTestCase("INFO_16732_VerifyReserveIconUnderActionWhenWorkitemOpenedFromSearch");

		}
	}
}
