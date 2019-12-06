package apd.infoimage.iwm.tests;

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
 * @author PradhanJ INFO-12065 Test to verify displaying of recent search
 *         actions using index fields by adding the url extension for 'no
 *         results'
 */
public class INFO_12065 extends SuperClassIWM {
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
	public void testVerifyRecentSearchUsingIndexFieldsAddingUrlExtensionForNoResults() {
		Log.startTestCase("INFO_12065_VerifyRecentSearchUsingIndexFieldsAddingUrlExtensionForNoResults");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_12065  is Test to verify displaying of recent search"
					+ " actions using index fields by adding the url extension for 'no results'");
			ATUReports.setAuthorInfo("Jayashri", "JULY-2018", "0.3");

			// Fetch the test data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String sheet2 = "Sheet_J";
			String str1 = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str1 + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);

			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("workitem Created sucessfully", true);
			ATUReports.add("workitem Created sucessfully", true);
			Log.info("workitem Created sucessfully");
			Thread.sleep(5000);

			Reporter.log("Selection of Workitem", true);
			ATUReports.add("Selection of Workitem", true);
			Log.info("Selection of Workitem");
			cwp.getWorkItemName(workitem).click();
			Thread.sleep(2000);
			util.waitForPageToLoad();
			Reporter.log("Updation of Formfield", true);
			ATUReports.add("Updation of Formfield", true);
			Log.info("Updation of Formfield");

			wdp.getFormfields_win().click();
			Thread.sleep(1000);
			// ID_CODE text field
			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys(idCodeStr);
			Reporter.log("Idcode has been written", true);
			ATUReports.add("Idcode has been written", true);
			Log.info("Idcode has been written");
			Thread.sleep(2000);

			util.jclick(wdp.getUpdate_btn());
			Reporter.log("Update Button has been Clicked", true);
			ATUReports.add("Update Button has been Clicked", true);
			Log.info("Update Button has been Clicked");
			util.waitForPageToLoad();
			util.wait(10000);
			util.waitForElementEnabled(hp.getSearchTab());
				
			util.jclick(hp.getSearchTab());
			//hp.getSearchTab().click();
			util.waitForPageToLoad();
			
			String currentUrl = Driver.driver.getCurrentUrl();
			Reporter.log("Current Url is fetched", true);
			ATUReports.add("Current Url is fetched", true);
			Log.info("Current Url is fetched");

			String urlWdIndex = ExcelLib.getCellValue(xlpath, sheet2, 24, 1);

			Reporter.log("Url by assigning  Index field to index search is fetched from test data", true);
			ATUReports.add("Url by assigning  Index field to index search is fetched from test data", true);
			Log.info("Url by assigning  Index field to index search is fetched from test data");

			ATUReports.add("Url by assigning  Index field to index search is \n" + urlWdIndex, true);
			Reporter.log("Url by assigning  Index field to index search is \n" + urlWdIndex, true);

			String newUrlForVerify = currentUrl + urlWdIndex;

			Driver.driver.get(newUrlForVerify);
			Reporter.log("Concatinated Url of current url and new url with index field "
					+ "entered in the URl field of the browser", true);
			ATUReports.add("Concatinated Url of current url and new url with index field "
					+ "entered in the URl field of the browser", true);
			Log.info("Concatinated Url of current url and new url with index field "
					+ "entered in the URl field of the browser");
			util.waitForPageToLoad();
			util.waitForElementPresent(sp.getNoSearchResutFrame());
			boolean noResultMsgPresent = util.verifyObjectPresentReturnsBool(sp.getNoSearchResutFrame());
			if (noResultMsgPresent) {

				Reporter.log("Recent search Validation successful for url by assigning index field to Index Search",
						true);
				ATUReports.add("Recent search Validation successful for url by assigning index field to Index Search ",
						true);

				Log.info("Recent search Validation successful for url by assigning index field to Index Search");
			} else {
				
				Assert.fail("Recent search Validation FAILED for url by assigning index field to Index Search");
			}
		} catch (Exception e) {
			Reporter.log("Recent search Validation FAILED for url by assigning index field to Index Search", true);
			ATUReports.add("Recent search Validation FAILED for url by assigning index field to Index Search",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Recent search Validation FAILED for url by assigning index field to Index Search");
		} finally {
			Log.endTestCase("INFO_12065_VerifyRecentSearchUsingIndexFieldsAddingUrlExtensionForNoResults");
		}
	}
}
