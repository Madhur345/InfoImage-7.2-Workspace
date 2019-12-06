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
 * @author PradhanJ
 * INFO-11889
 * 
 */
public class INFO_11889 extends SuperClassIWM{
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
	public void testVerifyrecentSearchUsingMultipleIndexFieldsInUrlExtensionForFolderType() {
		Log.startTestCase("INFO_11889_VerifyrecentSearchUsingMultipleIndexFieldsInUrlExtensionForFolderType");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_11889  is Test to verify the search criteria "
					+ "using multiple index fields of any form by adding the url extension for "
					+ "multiple folder type of workitems");
			ATUReports.setAuthorInfo("Jayashri", "AUG-2018", "0.3");

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
			
			String idCodeStr = randomNo.getRandomNo(6);
			Reporter.log("id_code value is "+idCodeStr, true);
			
			String invoice_No=randomNo.getRandomNo(4);
			Reporter.log("invoice_no value is "+invoice_No, true);
			
			cwp.CreateWorkitem(workitem, className, workitemType);
			Reporter.log("workitem Created sucessfully", true);
			ATUReports.add("workitem Created sucessfully", true);
			Log.info("workitem Created sucessfully");
			Thread.sleep(5000);

			cwp.getActionBtn(workitem).click();
			cwp.getFormFiledsBtn().click();
			Reporter.log("FormFields window is opened for the created workitem", true);
			Log.info("FormFields window is opened for the created workitem");
			ATUReports.add("FormFields window is opened for the created workitem", true);
			
			cwp.getID_CODE_Tf().sendKeys(idCodeStr);
			cwp.getInvoiceNoInFormFields().sendKeys(invoice_No);
			
			cwp.getUpdateFormBtn().click();
			Reporter.log("ID_CODE and INVOICE_NO field is updated", true);
			Log.info("ID_CODE and INVOICE_NO field is updated");
			ATUReports.add("ID_CODE and INVOICE_NO field is updated", true);
			
			util.waitForPageToLoad();
			Thread.sleep(5000);
									
			hp.getSearchTab().click();
			util.waitForPageToLoad();
			Reporter.log("Navigate to search Page", true);
			ATUReports.add("Navigate to search Page", true);
			Log.info("Navigate to search Page");

			String currentUrl = Driver.driver.getCurrentUrl();
			Reporter.log("Current Url is fetched", true);
			ATUReports.add("Current Url is fetched", true);
			Log.info("Current Url is fetched");

			String urlWdMultipleIndex = ExcelLib.getCellValue(xlpath, sheet2, 27, 1);
			Reporter.log("Url by using multiple index fields for folder type workitem is fetched from test data", true);
			ATUReports.add("Url by using multiple index fields for folder type workitem  is fetched from test data", true);
			Log.info("Url by using index multiple index fields for folder type workitem  is fetched from test data");

			ATUReports.add("Url by using multiple index fields for folder type workitem  is \n" + urlWdMultipleIndex, true);
			Reporter.log("Url by using multiple index fields for folder type workitem  is \n" + urlWdMultipleIndex, true);

			
			String urlWithActualIndexFieldvalue=urlWdMultipleIndex.replaceAll("Unisys", idCodeStr);
			urlWithActualIndexFieldvalue=urlWithActualIndexFieldvalue.replaceAll("abcd", invoice_No);
			
			Reporter.log("Url with actual index field data is "+urlWithActualIndexFieldvalue, true);
			ATUReports.add("Url with actual index field data is "+urlWithActualIndexFieldvalue, true);
			Log.info("Url with actual index field data is "+urlWithActualIndexFieldvalue);
			
			String newUrlForVerify = currentUrl + urlWithActualIndexFieldvalue;

			Driver.driver.get(newUrlForVerify);
			util.waitForPageToLoad();
			String newUrl=Driver.driver.getCurrentUrl();
			
			Reporter.log("Concatenated Url of current url and url with index field "					
					+ "entered in the URl field of the browser", true);
			Reporter.log("Conactenated Url is "+newUrl, true);
			ATUReports.add("Conactenated Url is "+newUrl, true);
			
			ATUReports.add("Concatenated Url of current url and url with index field "
					+ "entered in the URl field of the browser", true);
			Log.info("Concatenated Url of current url and url with index field "
					+ "entered in the URl field of the browser");
			util.waitForPageToLoad();

			boolean resultPresent = util.verifyObjectPresentReturnsBool(sp.getWorkItemName(workitem));
			if (resultPresent) {

				Reporter.log("Recent search Validation successful for url by using multiple index fields for folder type workitem",
						true);
				ATUReports.add("Recent search Validation successful for url by using multiple index fields for folder type workitem ",
						true);

				Log.info("Recent search Validation successful for url by using multiple index fields for folder type workitem");
			} else {
				
				Assert.fail("Recent search Validation FAILED for url by using multiple index fields for folder type workitem");
			}
		} catch (Exception e) {
			Reporter.log("Recent search Validation FAILED for url by using multiple index fields for folder type workitem", true);
			ATUReports.add("Recent search Validation FAILED for url by using multiple index fields for folder type workitem",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Recent search Validation FAILED for url by using multiple index fieldsfor folder type workitem");
		} finally {
			Log.endTestCase("INFO_11889_VerifyrecentSearchUsingMultipleIndexFieldsInUrlExtensionForFolderType");
		}
	}
}

