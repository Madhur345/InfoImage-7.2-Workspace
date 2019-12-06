package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
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
 * @author SumanGaK - 21-Nov-2018
 * INFO-16792
 * This class verify the save search without search criteria in the refined search
 **/
public class INFO_16792 extends SuperClassIWM {
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
	@Test(enabled = true, groups = { "Search" })
	public void testVerifySaveSearchWithoutSearchCriteriaInRefinedSearch() {

		ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "the save search without search criteria in the Refined search");
		ATUReports.setAuthorInfo("Suman", "NOV-2018", "0.3");

		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("VerifySaveSearchWithoutSearchCriteriaInRefinedSearch");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));


			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");
			int time=ExcelLib.getCellValueInt(xlpath,sheet,58,1);			

			Reporter.log("Performing Refined Search",true);
			ATUReports.add("Performing Refined Search",true);
			Log.info("Performing Refined Search");

			hp.getSearchTab().click();
			Reporter.log("Search Tab is clicked",true);
			ATUReports.add("Search Tab is clicked",true);
			Log.info("Search Tab is clicked");
			util.waitForPageToLoad();
			
			sp.getRefinedSearch().click();
			Reporter.log("Refined Search is clicked",true);
			ATUReports.add("Refined Search is clicked",true);
			Log.info("Refined Search is clicked");	
			util.waitForPageToLoad();

			util.wait(time);
			
			sp.getRefinedSearchSaveSearchButton().click();
			Reporter.log("Refined Search Save Search Button is clicked",true);
			ATUReports.add("Refined Search Save Search Buttonis clicked",true);
			Log.info("Refined Search Save Search Button is clicked");			

			util.wait(time);
			util.waitForElementPresent(sp.getSaveSearchAlertErrorMessage());
			
			boolean alertErrorMessagePresence = sp.getSaveSearchAlertErrorMessage().isDisplayed();
			if (alertErrorMessagePresence) {
				Reporter.log("Alert Error Message is present", true);
			} else {
				Reporter.log("Alert Error Message is not present", true);
				Assert.fail("Alert Error Message is not present");
			}		

			util.waitForPageToLoad();
			util.wait(time);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Failed to execute VerifySaveSearchWithoutSearchCriteriaInRefinedSearch test", true);
			ATUReports.add("Execute VerifySaveSearchWithoutSearchCriteriaInRefinedSearch",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Failed to execute VerifySaveSearchWithoutSearchCriteriaInRefinedSearch test");
		}
		finally {
			Log.endTestCase("INFO_16792_VerifySaveSearchWithoutSearchCriteriaInRefinedSearchTest");
		}
	}
}

