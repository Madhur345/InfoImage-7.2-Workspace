package apd.infoimage.iwm.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
 *
 */
public class INFO_2950 extends SuperClassIWM {

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

	/**
	 * This test method will validate the available fields in user preference
	 * are not repeated
	 * 
	 * @throws Exception
	 */

	@SuppressWarnings("deprecation")
	@Test(enabled = true, groups = { "UserPreference" })
	public void testVerifyAvailableFieldsTest() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-2950 will validate the available fields in user preference are not repeated");
		ATUReports.setAuthorInfo("PradhanJ", "MAR-2018", "0.3");
		try {
			Log.startTestCase("INFO_2950_UserPreference_NoFieldsRepeated");
			// Navigate to user preference tab.
			hp.clickUserPreferenceTab();
			Reporter.log("Navigated to user preference tab", true);
			ATUReports.add("Navigated to user preference tab ", true);
			Thread.sleep(5000);
			
			List<WebElement> avlFields = Driver.driver
					.findElements(By.xpath("(//select[contains(@id,'availableValues')])[1]/option"));

			int totalNoFields = avlFields.size();
			Reporter.log("Total no of fields is " + totalNoFields, true);
			ATUReports.add("Total no of fields is " + totalNoFields, true);
			int c = 0;
			String field = "";
			Thread.sleep(5000);
			for (int i = 0; i <= totalNoFields - 1; i++) {
				
				field = avlFields.get(i).getText();

				for (int j = 0; j <= totalNoFields - 1; j++) {

					String eachfield = avlFields.get(j).getText();
					if (field.equals(eachfield)) {
						c++;
					}
				}
			}

				if (c > 1) {
					Reporter.log("Available field " + field + " is repeated.", true);
					ATUReports.add("Available field " + field + " is repeated.", true);

				} else {
					Reporter.log("Field " + field + " is not repeated.", true);
					ATUReports.add("Field " + field + " is not repeated.", true);
					ATUReports.add("Field is not repeated.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));

				}
			
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Available fields validation failed.", true);
			ATUReports.add("Available fields validation failed ", true);
			ATUReports.add("INFO_2950_UserPreference_NoFieldsRepeated", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));

			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO_2950_UserPreference_NoFieldsRepeated");
		}
	}
}
