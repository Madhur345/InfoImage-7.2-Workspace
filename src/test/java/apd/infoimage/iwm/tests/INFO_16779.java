package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
 * @author SinghAvn 26-NOV-2018 INFO-16779 This class will verify the
 *         search results without search criteria in the basic search.
 **/
public class INFO_16779 extends SuperClassIWM {
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
	@Test(enabled = true)
	public void searchCriteria() {

		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO_16779 will verify the search results without search criteria in the basic search.");
		ATUReports.setAuthorInfo("Avnish ", "NOV-2018", "0");

		try {
			Log.startTestCase("INFO_16779_To_verify_search_without_search_criteria_basic_search");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			
			Reporter.log("Class Name : INFO_16779_To_verify_search_without_search_criteria_basic_search", true);
			ATUReports.add("Class Name : INFO_16779_To_verify_search_without_search_criteria_basic_search", true);
			
			hp.getSearchTab().click();
			Reporter.log("Search tab is clicked", true);
			ATUReports.add("Search tab is clicked", true);
			Log.info("Search tab is clicked");
			
			sp.getBasicSearch().click();
			Reporter.log("Basic Search button is clicked", true);
			ATUReports.add("Basic Search button is clicked", true);
			Log.info("Basic Search button is clicked");
			
			sp.getBasicSearchButton().click();
			Reporter.log(" Search button is clicked", true);
			ATUReports.add(" Search button is clicked", true);
			Log.info(" Search button is clicked");
	
			
			//Validation
			List<WebElement> wicheckboxes = Driver.driver.findElements(By.xpath("//input[@type='checkbox']"));
			System.err.println("The number of workitem visible on the first Page :" +(wicheckboxes.size()-1));
		
			

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("INFO_16779_To_verify_search_without_search_criteria_basic_search  Failed", true);
			Log.info("INFO_16779_To_verify_search_without_search_criteria_basic_search Failed");
			ATUReports.add("INFO_16779_To_verify_search_without_search_criteria_basic_search Failed", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("INFO_16779_To_verify_search_without_search_criteria_basic_search Failed");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO_16779_To_verify_search_without_search_criteria_basic_search");
		}
	}
}