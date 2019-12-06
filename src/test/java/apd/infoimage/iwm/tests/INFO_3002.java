package apd.infoimage.iwm.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class INFO_3002 extends SuperClassIWM {

	/**
	 * This method will login with valid user credentials and selecting a Role
	 * 
	 * @author PradhanJ
	 */
	@Test(enabled = true, priority = 1, groups = { "LoginPage" })
	public void loginValidRoleSelection() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-3002 will login with valid user credentials and selecting a Role");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		try {
			Log.startTestCase("loginValidRoleSelection");
			Driver.driver.navigate().refresh();
			util.waitForPageToLoad();
			Driver.driver.get(prop.getProperty("appUrl"));

			util.waitForPageToLoad();
			lp.loginToApp("userName", "password", "domain", "role");
			util.waitForPageToLoad();

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to login with Valid data and role selection", true);
			ATUReports.add("failed to login with Valid data and role selection", true);
			Assert.fail("failed to execute LoginAndRoleSelection test");
			Log.error(e.getMessage());
		} finally {
			hp.logoutApp();
			Log.endTestCase("INFO_3002_LoginAndRoleSelection");

		}
	}

	/**
	 * This test method will login with valid user credentials and clicking on
	 * remember me and check if login credentials are saved for the page
	 * 
	 * @author PradhanJ
	 */
	/*@Test(enabled = true, priority = 2, groups = { "LoginPage" })
	public void loginValidWithRememberMe() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario will login with valid user credentials and clicking on remember me\r\n"
						+ "and check if login credentials are saved for the page");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		try {
			Log.startTestCase("loginValidWithRememberMe");
			Driver.driver.navigate().refresh();
			util.waitForPageToLoad();
			Driver.driver.get(prop.getProperty("appUrl"));

			util.waitForPageToLoad();
			prop = new Properties();
			String propertiesFile = new File(
					"InfoImageWorkflowManager\\infoImageWorkflowManager\\configFiles\\userData.properties")
							.getAbsolutePath();
			input = new FileInputStream(propertiesFile);
			prop.load(input);

			lp.getUserName_TF().clear();
			lp.getUserName_TF().sendKeys(prop.getProperty("userName"));

			lp.getPassword_TF().clear();
			lp.getPassword_TF().sendKeys(prop.getProperty("password"));

			lp.getDomain_TF().clear();
			lp.getDomain_TF().sendKeys(prop.getProperty("domain"));

			Thread.sleep(4000);

			// Check the remember me checkbox

			lp.getRememberMeCheckbox().click();

			Thread.sleep(2000);
			lp.getSigninBtn().click();
			Thread.sleep(10000);

			String role = prop.getProperty("role");
			if (role != null) {

				boolean f = util.verifyObjectPresentReturnsBool(lp.getSelectRolesPopUp());
				System.out.println("boolean value is " + f);

				if (f) {
					Reporter.log("Roles dialog box present", true);
					ATUReports.add("Roles dialog box present", true);
					Select sel = new Select(lp.getSelectRolesPopUp());
					sel.selectByValue(role);
					Reporter.log("Role selected as " + role, true);
					ATUReports.add("Role selected as " + role, true);
					Thread.sleep(2000);
					util.clickOnButton(lp.getSelectRolesOk());
				} else {
					Reporter.log("Roles dialog box not present.", true);
					ATUReports.add("Roles dialog box not present.", true);
				}
			}

			util.waitForPageToLoad();

			boolean f = util.verifyObjectPresentReturnsBool(hp.getWorkItemTab());
			System.out.println("boolean value is " + f);

			if (f) {
				Reporter.log("Login successful", true);
				ATUReports.add("Login successful", true);
			} else {
				Reporter.log("Login failed", true);
				ATUReports.add("Login failed", true);
				Assert.fail("Login failed");
			}

			// Logout and check username and domain are displayed in the login page.

			hp.logoutApp();

			String uid = lp.getUserName_TF().getAttribute("value");
			String dom = lp.getDomain_TF().getAttribute("value");
			System.out.println(uid);
			System.out.println(dom);
			if (uid.equals(prop.getProperty("userName")) && dom.equals(prop.getProperty("domain"))) {
				Reporter.log("Login with remember me is working successfully", true);
				ATUReports.add("Login with remember me is working successfully", true);
			} else {
				Reporter.log("Login with remember me validation failed", true);
				ATUReports.add("Login with remember me validation failed", true);
				Assert.fail("failed to execute loginValidWithRememberMe test");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to login with Valid data and remember me option", true);
			ATUReports.add("failed to login with Valid data and remember me option", true);
			Assert.fail("failed to login with Valid data and remember me option");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("loginValidWithRememberMe");
		}

	}*/

	/**
	 * This test method will select the role, hit cancel and Validate cancel role
	 * popup is appearing with proper message and after clicking on Yes to continue
	 * it should come back to IWM home page.
	 * 
	 * @author PradhanJ
	 */
	/*@Test(enabled = true, priority = 3, groups = { "LoginPage" })
	public void selectRoleHitCancel() {
		ATUReports.setTestCaseReqCoverage("This Scenario This test method will select the role, hit cancel\r\n"
				+ "and Validate cancel role popup is appearing with proper message \r\n"
				+ "and after clicking on Yes to continue it should come back to IWM home page");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		try {
			Log.startTestCase("selectRoleHitCancel");
			Driver.driver.navigate().refresh();
			util.waitForPageToLoad();
			Driver.driver.get(prop.getProperty("appUrl"));
			util.waitForPageToLoad();
			prop = new Properties();
			String propertiesFile = new File(
					"InfoImageWorkflowManager\\infoImageWorkflowManager\\configFiles\\userData.properties")
							.getAbsolutePath();
			input = new FileInputStream(propertiesFile);
			prop.load(input);

			lp.getUserName_TF().clear();
			lp.getUserName_TF().sendKeys(prop.getProperty("userName"));

			lp.getPassword_TF().clear();
			lp.getPassword_TF().sendKeys(prop.getProperty("password"));

			lp.getDomain_TF().clear();
			lp.getDomain_TF().sendKeys(prop.getProperty("domain"));

			Thread.sleep(4000);
			Thread.sleep(2000);
			util.clickOnButton(lp.getSigninBtn());

			Thread.sleep(10000);

			String role = prop.getProperty("role");

			if (role != null) {

				boolean f = util.verifyObjectPresentReturnsBool(lp.getSelectRolesPopUp());
				System.out.println("boolean value is " + f);

				if (f) {
					Reporter.log("Roles dialog box present", true);
					ATUReports.add("Roles dialog box present", true);
					Select sel = new Select(lp.getSelectRolesPopUp());
					sel.selectByValue(role);
					Reporter.log("Role selected as " + role, true);
					ATUReports.add("Role selected as " + role, true);
					Thread.sleep(2000);

					// Click on the cancel button
					util.clickOnButton(lp.getSelectRolesCancel());

					if (lp.getCancelRolePopup().isDisplayed()) {
						Reporter.log("Cancel Role Popup is present", true);
						ATUReports.add("Cancel Role Popup is present", true);
						String cancelRlTxt = lp.getCancelRoleText().getText();
						Reporter.log("Message displayed in cancel Role popup is....\n" + cancelRlTxt, true);
						ATUReports.add("Message displayed in cancel Role popup is....\n" + cancelRlTxt, true);
						// Click on Yes button
						util.clickOnButton(lp.getCancelRoleYes());

						Thread.sleep(4000);

						boolean home = util.verifyObjectPresentReturnsBool(lp.getHomeIWM());
						System.out.println("boolean value is " + home);

						if (home) {
							Reporter.log("After cancelling role selection it navigates to Home page. ", true);
							ATUReports.add("After cancelling role selection it navigates to Home page. ", true);
						} else {
							Reporter.log("Cancel Role selection Validation failed", true);
							ATUReports.add("Cancel Role selection Validation failed", true);
							Assert.fail("Cancel Role selection Validation failed");
						}
					} else {
						Reporter.log("Cancel role poupup is not present", true);
						ATUReports.add("Cancel role poupup is not present", true);
					}

				} else {
					Reporter.log("Roles dialog box not present", true);
					ATUReports.add("Roles dialog box not present", true);
					hp.logoutApp();
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Cancel Role selection test failed", true);
			ATUReports.add("Cancel Role selection test failed", true);
			Assert.fail("Cancel Role selection test failed");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("selectRoleHitCancel");
		}
	}*/

	/**
	 * This test method will login with invalid user id and validate the error
	 * message
	 * 
	 * @author PradhanJ
	 */
	/*@Test(enabled = true, priority = 4, groups = { "LoginPage" })
	public void loginInvalidUserId() {

		ATUReports.setTestCaseReqCoverage("This Scenario login with invalid user id and validate the error");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		try {
			Log.startTestCase("loginInvalidUserId");
			Driver.driver.navigate().refresh();
			util.waitForPageToLoad();
			Driver.driver.get(prop.getProperty("appUrl"));
			util.waitForPageToLoad();
			prop = new Properties();
			String propertiesFile = new File(
					"InfoImageWorkflowManager\\infoImageWorkflowManager\\configFiles\\userData.properties")
							.getAbsolutePath();
			input = new FileInputStream(propertiesFile);
			prop.load(input);

			lp.getUserName_TF().clear();

			// Enter Invalid user Id
			lp.getUserName_TF().sendKeys(prop.getProperty("invalidUid"));

			lp.getPassword_TF().clear();
			lp.getPassword_TF().sendKeys(prop.getProperty("password"));

			lp.getDomain_TF().clear();
			lp.getDomain_TF().sendKeys(prop.getProperty("domain"));

			Thread.sleep(4000);

			util.clickOnButton(lp.getSigninBtn());

			Thread.sleep(10000);

			boolean m = util.verifyObjectPresentReturnsBool(lp.getInvalidUidPwdMessage());
			if (m) {

				String invalidUidErrMsg = lp.getInvalidUidPwdMessage().getText();

				if (invalidUidErrMsg.contains("Invalid user")) {
					Reporter.log("Error message dispalyed is : " + invalidUidErrMsg, true);
					ATUReports.add("Error message dispalyed is : " + invalidUidErrMsg, true);
				} else {
					Reporter.log("Error message dispalyed is : " + invalidUidErrMsg, true);
					ATUReports.add("Error message dispalyed is : " + invalidUidErrMsg, true);
					Assert.fail("Wrong error message is displayed.");
				}

			} else {
				Reporter.log("Login with invalid user id does not throw error message", true);
				ATUReports.add("Login with invalid user id does not throw error message", true);
				Assert.fail("Login with invalid user id does not throw error message");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Login with invalid user id  test failed", true);
			ATUReports.add("Login with invalid user id  test failed", true);
			Assert.fail("Login with invalid user id  test failed");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("loginInvalidUserId");
		}
	}
*/
	/**
	 * This test method will login with invalid password and validate the error
	 * message
	 * 
	 * @author PradhanJ
	 */
	/*@Test(enabled = true, priority = 5, groups = { "LoginPage" })
	public void loginInvalidPassword() {
		ATUReports.setTestCaseReqCoverage("This Scenario login with invalid password and validate the error");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");

		try {
			Log.startTestCase("loginInvalidPassword");
			Driver.driver.navigate().refresh();
			util.waitForPageToLoad();
			Driver.driver.get(prop.getProperty("appUrl"));
			util.waitForPageToLoad();
			prop = new Properties();
			String propertiesFile = new File(
					"InfoImageWorkflowManager\\infoImageWorkflowManager\\configFiles\\userData.properties")
							.getAbsolutePath();
			input = new FileInputStream(propertiesFile);
			prop.load(input);

			lp.getUserName_TF().clear();
			lp.getUserName_TF().sendKeys(prop.getProperty("userName"));

			lp.getPassword_TF().clear();
			// enter invalid password
			lp.getPassword_TF().sendKeys(prop.getProperty("invalidPwd"));

			lp.getDomain_TF().clear();
			lp.getDomain_TF().sendKeys(prop.getProperty("domain"));

			Thread.sleep(4000);

			util.clickOnButton(lp.getSigninBtn());

			Thread.sleep(10000);

			boolean m = util.verifyObjectPresentReturnsBool(lp.getInvalidUidPwdMessage());
			if (m) {

				String invalidPwdErrMsg = lp.getInvalidUidPwdMessage().getText();
				if (invalidPwdErrMsg.contains("password")) {
					Reporter.log("Error message dispalyed is : " + invalidPwdErrMsg, true);
					ATUReports.add("Error message dispalyed is : " + invalidPwdErrMsg, true);
				} else {
					Reporter.log("Error message dispalyed is : " + invalidPwdErrMsg, true);
					ATUReports.add("Error message dispalyed is : " + invalidPwdErrMsg, true);
					Assert.fail("Wrong error message is displayed.");
				}

			} else {
				Reporter.log("Login with invalid password does not throw error message", true);
				ATUReports.add("Login with invalid password does not throw error message", true);
				Assert.fail("Login with invalid password does not throw error message");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Login with invalid password  test failed", true);
			ATUReports.add("Login with invalid password  test failed", true);
			Assert.fail("Login with invalid password  test failed");
			ATUReports.add("failed to execute test INFO_3002_LoginAndRoleSelection", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
		} finally {
			Log.endTestCase("loginInvalidPassword");
		}
	}
*/
	/**
	 * This test method will login with invalid domain name and validate the error
	 * message
	 * 
	 * @author PradhanJ
	 */
	/*@Test(enabled = true, priority = 6, groups = { "LoginPage" })
	public void loginInvalidDomain() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario login will login with invalid domain name and validate the error");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");

		try {
			Log.startTestCase("loginInvalidDomain");
			Driver.driver.navigate().refresh();
			util.waitForPageToLoad();
			Driver.driver.get(prop.getProperty("appUrl"));
			util.waitForPageToLoad();
			prop = new Properties();
			String propertiesFile = new File(
					"InfoImageWorkflowManager\\infoImageWorkflowManager\\configFiles\\userData.properties")
							.getAbsolutePath();
			input = new FileInputStream(propertiesFile);
			prop.load(input);

			lp.getUserName_TF().clear();
			Thread.sleep(2000);
			lp.getUserName_TF().sendKeys(prop.getProperty("userName"));
			Thread.sleep(2000);
			lp.getPassword_TF().clear();
			Thread.sleep(2000);
			lp.getPassword_TF().sendKeys(prop.getProperty("password"));
			Thread.sleep(2000);
			lp.getDomain_TF().clear();
			Thread.sleep(2000);
			// enter invalid domain
			lp.getDomain_TF().sendKeys(prop.getProperty("invalidDom"));

			Thread.sleep(4000);

			util.clickOnButton(lp.getSigninBtn());

			Thread.sleep(10000);

			boolean d = util.verifyObjectPresentReturnsBool(lp.getInvalidDomMessage());
			if (d) {
				String invalidDomErrMsg = lp.getInvalidDomMessage().getText();

				if (invalidDomErrMsg.contains("invalid domain")) {
					Reporter.log("Error message dispalyed is : " + invalidDomErrMsg, true);
					ATUReports.add("Error message dispalyed is : " + invalidDomErrMsg, true);
				} else {
					Reporter.log("Error message dispalyed is : " + invalidDomErrMsg, true);
					ATUReports.add("Error message dispalyed is : " + invalidDomErrMsg, true);
					Assert.fail("Wrong error message is displayed.");
				}
			} else {
				Reporter.log("Login with invalid Domain does not throw error message", true);
				ATUReports.add("Login with invalid Domain does not throw error message", true);
				Assert.fail("Login with invalid Domain does not throw error message");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Login with invalid domain  test failed", true);
			Log.error(e.getMessage());
			ATUReports.add("Login with invalid domain  test failed", true);
			ATUReports.add("failed to execute test loginInvalidDomain",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Login with invalid domain  test failed");			
		} finally {
			Log.endTestCase("loginInvalidDomain");
		}
	}
*/
	/**
	 * This test method will enter the login credentials and click cancel
	 * 
	 */
	}
