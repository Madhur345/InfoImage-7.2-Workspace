package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
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
 * @author SumanGaK
 * INFO-4302
 * This class is used to verify the Nested search functionality with Blank Data using query builder in iwm. 
 */
public class INFO_4302 extends SuperClassIWM{
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
	@Test(enabled = true, priority = 1)
	public void testVerifyNestedSearchFunctionalityUsingQueryBuilderWithBlankData() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-4302 is Test to verify the Nested search functionality with Blank Data using query builder in iwm.");
		ATUReports.setAuthorInfo("Suman", "JUN-2018", "0.3");

		try {
			Log.startTestCase("INFO_4302_VerifyNestedSearchFunctionalityUsingQueryBuilderWithBlankDataTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String nestedQuery=ExcelLib.getCellValue(xlpath, sheet, 66, 1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);	

			hp.getSearchTab().click();
			Reporter.log("Search Tab is clicked", true);
			Log.info("Search Tab is clicked");
			ATUReports.add("Search Tab is clicked", true);

			sp.getNestedSearchTab().click();
			Reporter.log("Nested Search tab is selected", true);
			Log.info("Nested Search tab is selected");
			ATUReports.add("Nested Search tab is selected", true);

			Select selectQueryType=new Select(sp.getQueryTypeDropdown());
			selectQueryType.selectByValue(nestedQuery);
			Reporter.log("Nested Query selected is "+nestedQuery, true);
			Log.info("Nested Query selected is "+nestedQuery);
			ATUReports.add("Nested Query selected is "+nestedQuery, true);

			sp.getIdCodeFieldInNestedSearch().clear();
			Reporter.log("ID Code value is cleared", true);
			Log.info("ID Code value is cleared");
			ATUReports.add("ID Code value is cleared", true);

			sp.getInvoiceNoFieldInNestedSearch().clear();
			Reporter.log("Invoice Number value is cleared", true);
			Log.info("Invoice Number value is cleared");
			ATUReports.add("Invoice Number value is cleared", true);

			sp.getMultiANDTermsFieldInNestedSearch().clear();
			Reporter.log("Terms value is cleared", true);
			Log.info("Terms value is cleared");
			ATUReports.add("Terms value is cleared", true);
			util.wait(time);
			util.wait(time);
			util.waitForPageToLoad();

			sp.getSearchButtonInNestedSearch().click();
			Reporter.log("Search button is clicked", true);
			Log.info("Search button is clicked");
			ATUReports.add("Search button is clicked", true);

			util.wait(time);
			boolean messagePresence = util.verifyObjectPresentReturnsBool(sp.getMessagePresence());
			if(!messagePresence){
				Reporter.log("Query Results Not Found Message is displayed",true);
				ATUReports.add("Query Results Not Found Message is displayed", true);
				Log.info("Query Results Not Found Message is displayed");
			}else{
				ATUReports.add("Nested Search Functionality Using Query Builder With Blank Data test is failed",LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Nested Search Functionality Using Query Builder With Blank Data test is failed");
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add("Failed to verify the Nested Search Functionality Using Query Builder With Blank Data in iwm.",LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail(" Nested Search Functionality Using Query Builder With Blank Data test is failed");
		}
		finally {
			Log.endTestCase("INFO_4302_VerifyNestedSearchFunctionalityUsingQueryBuilderWithBlankDataTest");

		}
	}
}

