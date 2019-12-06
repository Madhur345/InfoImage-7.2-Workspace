package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class INFO_4374 extends SuperClassIWM {

	@BeforeMethod
	public void beforeMethod() {
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
	 * This test method will verify search for custom fields after applying
	 * customJs
	 * 
	 * @author pradhanJ
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true, groups = { "Search" })
	public void testSearchFunctionalityWithFieldValue() throws FileNotFoundException, IOException {
		Log.startTestCase("INFO_4374_SearchFunctionalityWithFieldValue");

		try {
			// Fetch the query type from user data
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String query = prop.getProperty("queryType");
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);

			// Create workitem
			Reporter.log("Creation of Workitem");
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Thread.sleep(2000);

			// Open workitem and update form fields

			cwp.searchByNameInWorkitemList(workitem);
			Reporter.log("Selection of Workitem");
			cwp.getWorkItemName(workitem).click();
			Thread.sleep(5000);
			util.waitForPageToLoad();

			Reporter.log("Updation of Formfield");
			wdp.getFormfields_win().click();
			Thread.sleep(2000);
			wdp.getIdcode_TF().sendKeys("Unisys");
			wdp.getInvoiceno_TF().sendKeys("1234");
			Thread.sleep(2000);
			wdp.getUpdate_btn().click();
			Thread.sleep(15000);
			util.waitForPageToLoad();
			util.waitForElementEnabled(cwp.getWorkItems());

			cwp.getWorkItems().click();
			Thread.sleep(2000);
			util.waitForPageToLoad();
			util.waitForElementEnabled(cwp.getSearchFieldInWorkitemTab());
			cwp.searchByNameInWorkitemList(workitem);
			Reporter.log("searching the workitem in the List", true);
			ATUReports.add("searching the workitem in the List", true);
			Log.info("searching the workitem in the List");
			Thread.sleep(1000);

			Reporter.log("clicking on the workitem checkbox", true);
			ATUReports.add("clicking on the workitem checkbox", true);
			Log.info("clicking on the workitem checkbox");
			Thread.sleep(1000);

			// Send the workitem to default queue
			Reporter.log("Sending Workitem to Workflow");
			cwp.sendWorkItemToDefaultQueue(workitem);
			Thread.sleep(5000);
			util.waitForPageToLoad();
			Reporter.log("Performing Refined Search");
			util.waitForElementEnabled(hp.getSearchTab());

			sp.refinedSearchWithInvoiceNo(workitem, query);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute RunningAQueryInRefinedSearch test", true);
			Assert.fail("failed to execute RunningAQueryInRefinedSearch test");
		}

		finally {
			Log.endTestCase("INFO_4374_SearchFunctionalityWithFieldValue");
		}

	}

}
