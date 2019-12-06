package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.support.ui.Select;
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
 * @author PradhanJ
 * INFO-16774
 * Test to verify the search results without search criteria in the refined search.
 */
public class INFO_16774 extends SuperClassIWM{
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
	@Test(enabled = true)
	public void testVerifySendToDefaultUnderActionInWorkitemDetailPage() {

		ATUReports.setTestCaseReqCoverage(
				"This Scenario is to Test to verify the search results "
				+ "without search criteria in the refined search");
		ATUReports.setAuthorInfo("PradhanJ", "NOV-2018", "0.3");
		try {
			Log.startTestCase("INFO_16774_VerifySearchResultsWithoutSearchCriteriaInRefinedSearch");
			DOMConfigurator.configure("log4j.xml");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String query = prop.getProperty("queryType");
			
			hp.getSearchTab().click();
			Reporter.log("Click on Search Tab",true);
			ATUReports.add("Click on Search Tab",true);
			Log.info("Click on Search Tab");
			util.waitForPageToLoad();
			util.waitForElementEnabled(sp.getRefinedSearch());
			Thread.sleep(10000);
			
			sp.getRefinedSearch().click();
			Reporter.log("Click on Refined Search page",true);
			ATUReports.add("Click on Refined Search page",true);
			Log.info("Click on Refined Search page");
			util.waitForPageToLoad();
			Thread.sleep(5000);

			Select sel = new Select(sp.getSelectQueryType());
			sel.selectByValue(query);
			Reporter.log("Query selected as " + query, true);
			ATUReports.add("Query selected as " + query,true);
			Log.info("Query selected as " + query);
			Thread.sleep(10000);

			sp.getRefinedSearchButton().click();
			Reporter.log("Refined Search button is clicked",true);
			ATUReports.add("Refined Search button is clicked",true);
			Log.info("Refined Search button is clicked");
			util.waitForPageToLoad();
			util.wait(5000);

			if(sp.getRefinedSearchErrorMsg().isDisplayed())
			{
			String errMsg=sp.getRefinedSearchErrorMsg().getText();
			Reporter.log("Error message displayed is "+errMsg,true);
			ATUReports.add("Error message displayed is "+errMsg,true);
			Log.info("Error message displayed is "+errMsg);
			}
			else
			{
				Reporter.log("Error message is NOT displayed after clicking search button without search criteria",true);
				ATUReports.add("Error message is NOT displayed after clicking search button without search criteria",true);
			    Assert.fail("Error message is NOT displayed after clicking search button without search criteria");
			}
				
		}

		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("This script is failed due to " + e.getMessage());
			ATUReports.add("Failed to verify the search results without search criteria in the refined search.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.info("Failed to verify the search results without search criteria in the refined search");
		} finally {

			Log.endTestCase("INFO_16774_VerifySearchResultsWithoutSearchCriteriaInRefinedSearch");
		}
	}
}
