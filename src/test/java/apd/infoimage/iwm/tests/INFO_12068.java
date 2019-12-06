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
 * @author PradhanJ INFO-12068 Test to verify displaying of recent search
 *         actions using wrong index fields by adding the url extension
 */
public class INFO_12068 extends SuperClassIWM {

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
	public void testVerifyRecentSearchUsingWrongIndexFieldByAddingUrlExtension() {
		Log.startTestCase("INFO_12068_VerifyRecentSearchUsingWrongIndexFieldByAddingUrlExtension");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_12068  is Test to verify displaying of recent search"
					+ " actions using wrong index fields by adding the url extension");
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
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 11, 1);
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
			Thread.sleep(10000);
			util.waitForElementEnabled(hp.getSearchTab());
			util.jclick(hp.getSearchTab());
			util.waitForPageToLoad();
			
			String currentUrl = Driver.driver.getCurrentUrl();
			Reporter.log("Current Url is fetched", true);
			ATUReports.add("Current Url is fetched", true);
			Log.info("Current Url is fetched");

			String urlWdInvalidIndex = ExcelLib.getCellValue(xlpath, sheet2, 23, 1);
			Reporter.log("Url with Invalid Index field is fetched from test data", true);
			ATUReports.add("Url with Invalid Index field is fetched from test data", true);
			Log.info("Url with Invalid Index field is fetched from test data");

			String newUrlForVerify = currentUrl + urlWdInvalidIndex;

			Driver.driver.get(newUrlForVerify);
			Reporter.log("Concatinated Url of current url and Invalid index field "
					+ "entered in the URl field of the browser", true);
			ATUReports.add("Concatinated Url of current url and Invalid index field "
					+ "entered in the URl field of the browser", true);
			Log.info("Concatinated Url of current url and Invalid index field "
					+ "entered in the URl field of the browser");
			util.waitForPageToLoad();

			boolean noResultMsgPresent = util.verifyObjectPresentReturnsBool(sp.getNoResultMsg());
			if (noResultMsgPresent) {
				String errorMsg = sp.getNoResultMsg().getText();
				Reporter.log("Recent search Validation successful for url with invalid index field .."
						+ "Error Message dispalyed is " + errorMsg, true);
				ATUReports.add("Recent search Validation successful for url with invalid index field ", true);
				ATUReports.add("Error Message dispalyed is " + errorMsg, true);
				Log.info("Recent search Validation successful for url with invalid index field");
			} else {
				Reporter.log("Error Message is not dispalyed", true);
				ATUReports.add("Error Message is not dispalyed..", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Error Message is not dispalyed..");
			}
		} catch (Exception e) {
			Reporter.log("Recent search Validation FAILED for url with invalid index field ..", true);
			ATUReports.add("Recent search Validation successful for url with invalid index field ..", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Recent search Validation successful for url with invalid index field ..");
		} finally {
			Log.endTestCase("INFO_12068_VerifyRecentSearchUsingWrongIndexFieldByAddingUrlExtension");
		}
	}
}
