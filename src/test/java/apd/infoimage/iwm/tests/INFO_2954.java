package apd.infoimage.iwm.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.interactions.Actions;
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

public class INFO_2954 extends SuperClassIWM {

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
	 * This test method will verify refined search for custom fields after
	 * applying customJs
	 * 
	 * @author pradhanJ
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true, groups = { "CustomJSForms" })
	public void testRefinedSearchForCustomField() {
		String workitm = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		try {
			Log.startTestCase("INFO_2954_RefinedSearchCustomField");
			// Fetch the query type from user data

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String query = prop.getProperty("queryType");

			// Create workitem

			Reporter.log("Creation of Workitem");
			ATUReports.add("Creation of Workitem", true);
			Log.info("Creation of Workitem");
			cwp.CreateWorkitem(workitm, "archive", "Document");
			Thread.sleep(2000);

			// Open workitem and update form fields

			Reporter.log("Selection of Workitem");
			ATUReports.add("Selection of Workitem", true);
			Log.info("Selection of Workitem");
			cwp.getWorkItemName(workitm).click();
			Thread.sleep(2000);
			util.waitForPageToLoad();

			Reporter.log("Updation of Formfield");
			ATUReports.add("Updation of Formfield", true);
			Log.info("Updation of Formfield");
			wdp.getFormfields_win().click();
			Thread.sleep(2000);
			Actions act = new Actions(Driver.driver);
			// wdp.getIdcode_TF().sendKeys("Unisys");
			Thread.sleep(2000);
			act.moveToElement(wdp.getIdcode_TF()).doubleClick().perform();

			boolean addNewFieldPopupPresent = util.verifyObjectPresentReturnsBool(wdp.getIdCodePopupInWDP());

			if (addNewFieldPopupPresent) {
				Reporter.log("Add New form field popup is displayed.", true);
				ATUReports.add("Add New form field popup is displayed.", true);
				Log.info("Add New form field popup is displayed.");
				// Add 2 fields in ID_CODE
				cjfp.getAddNewTextBox().clear();

				cjfp.getAddNewTextBox().sendKeys("abc");
				cjfp.getAddNewBtn().click();
				cjfp.getAddNewTextBox().clear();
				cjfp.getAddNewTextBox().sendKeys("123");
				cjfp.getAddNewBtn().click();

				cjfp.getUpdateFieldCloseBtn().click();
				Thread.sleep(5000);

				// Verify that added new fields are reflected in the formfield
				// with '#'
				String valueExp = "abc" + "#" + "123" + "#";
				System.out.println("Expected formfield value is " + valueExp);
				ATUReports.add("Expected formfield value is " + valueExp, true);
				Log.info("Expected formfield value is " + valueExp);

				util.jclick(wdp.getUpdate_btn());
				util.waitForPageToLoad();
				Thread.sleep(5000);
				String valueAct = wdp.getIdcode_TF().getAttribute("value");

				System.out.println("Actual formfield value is " + valueAct);
				ATUReports.add("Actual formfield value is " + valueAct, true);
				Log.info("Actual formfield value is " + valueAct);

				if (valueAct.equals(valueExp)) {
					Reporter.log("New formfield values are added ", true);
					ATUReports.add("New formfield values are added", true);
					Log.info("New formfield values are added");
				} else {
					Assert.fail("New formfield values are added validation failed");
				}

				util.jclick(hp.getWorkItemTab());
				util.waitForPageToLoad();
				util.wait(15000);
				cwp.searchByNameInWorkitemList(workitm);

				// Send the workitem to default queue
				Reporter.log("Sending Workitem to Workflow");
				ATUReports.add("Sending Workitem to Workflow", true);
				Log.info("Sending Workitem to Workflow");
				cwp.sendWorkItemToDefaultQueue(workitm);

				Thread.sleep(2000);
				util.waitForPageToLoad();
				Reporter.log("Performing Refined Search");
				ATUReports.add("Performing Refined Search", true);
				Log.info("Performing Refined Search");

				// Perform Refined search

				util.jclick(hp.getSearchTab());
				util.waitForPageToLoad();
				util.wait(15000);
				sp.getRefinedSearch().click();

				Thread.sleep(5000);

				Select sel = new Select(sp.getSelectQueryType());
				sel.selectByValue(query);
				Reporter.log("Option selected as " + query, true);
				ATUReports.add("Option selected as " + query, true);
				Log.info("Option selected as " + query);
				Thread.sleep(2000);

				sp.getIdCode().sendKeys(valueAct);
				sp.getRefinedSearchButton().click();
				Thread.sleep(5000);

				sp.searchWorkitemByNameInSearchpage(workitm);

				boolean f9 = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitm));
				if (f9) {
					Reporter.log("Workitem to be retreived using Refined Search is present in grid", true);
					ATUReports.add("Workitem to be retreived using Refined Search is present in grid", true);
					Log.info("Workitem to be retreived using Refined Search is present in grid");
				} else {
					Reporter.log("Workitem to be   retreived using Refined Search is not present in grid", true);
					Assert.fail("Workitem to be retreived using Refined Search is not present in grid ");
				}

				cwp.getCheckBoxWorkItemName(workitm).click();

				Thread.sleep(2000);
				sp.getRetrieveButton().click();
				util.waitForPageToLoad();

			} else {
				Reporter.log("Add New form field popup is Not presents", true);
				ATUReports.add("Add New form field popup is Not presents", true);
				Log.info("Add New form field popup is Not presents");
			}
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Add new formfield value tets failed", true);
			Log.error("Add new formfield value tets failed" + e.getMessage());
			Assert.fail("Add new formfield value tets failed");
		} finally {

			Log.endTestCase("INFO_2954_RefinedSearchCustomField");
		}

	}

}
