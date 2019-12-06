package apd.infoimage.iwm.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
 *
 */
public class INFO_3800 extends SuperClassIWM {

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
	 * This test method will check the switching role functionality
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true, groups = { "LoginPage" })
	public void testSwitchRole() {
		ATUReports.setTestCaseReqCoverage("This Scenario will check the switching role functionality");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		try {
			Log.startTestCase("INFO_3800_SelectingOrSwitchingRoles");

			// Take the cursor to role field and validate change role tooltip is  displayed  or not	
			
			Thread.sleep(2000);
			Actions a1 = new Actions(Driver.driver);
			a1.moveToElement(hp.getRoleField()).click().perform();
			Thread.sleep(5000);

			if (hp.getRoleField().getAttribute("title").equalsIgnoreCase("Change Role")) {
				Reporter.log("Change Role tooltip is dispalyed", true);
				ATUReports.add("Change Role tooltip is dispalyed", true);
			} else {
				Reporter.log("Change Role tooltip is NOT dispalyed", true);
				ATUReports.add("Change Role tooltip is NOT dispalyed", true);
			}
			String roleValue = prop.getProperty("role");
			System.out.println(roleValue);

			String selectedRole = hp.getSelectedRole().getText();
			System.out.println("**Selcected role is : " + selectedRole);

			Reporter.log("User is logged in with Role  :" + selectedRole, true);
			ATUReports.add("User is logged in with Role  :" + selectedRole, true);

			// switch to a different role.
			List<WebElement> unselectedRoles = Driver.driver.findElements(By.xpath("//li[not(@class)]/a/span"));
			if (unselectedRoles.size() > 1) {
				hp.getRoleField().click();
				Thread.sleep(2000);
				hp.getOtherRole().click();
				util.waitForPageToLoad();
				Thread.sleep(2000);
				Actions a2 = new Actions(Driver.driver);
				a2.moveToElement(hp.getRoleField()).click().perform();
				String switchedRole = hp.getSelectedRole().getText();
				Reporter.log("Role is switched to " + switchedRole, true);
				ATUReports.add("Role is switched to " + switchedRole, true);
			} else {
				Reporter.log("Only one role is present\n" + "So switching role is not possible.", true);
				ATUReports.add("Only one role is present\n" + "So switching role is not possible.", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Execution of switching Role test failed", true);
			Assert.fail("Execution of switching Role test failed");
			ATUReports.add("failed to execute test INFO_3800_SelectingOrSwitchingRoles ", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
		} finally {
			Log.endTestCase("INFO_3800_SelectingOrSwitchingRoles");
		}
	}
}
