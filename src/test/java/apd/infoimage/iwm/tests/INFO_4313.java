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
 * INFO-4313
 * This class is used to verify the Nested search functionality with Multi AND using query builder in iwm. 
 */
public class INFO_4313 extends SuperClassIWM{
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
	public void testVerifyNestedSearchFunctionalityUsingQueryBuilderWithMultiAND() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-4313 is Test to verify the Nested search functionality with Multi AND using query builder in iwm.");
		ATUReports.setAuthorInfo("Suman", "JUN-2018", "0.3");

		try {
			Log.startTestCase("INFO_4313_VerifyNestedSearchFunctionalityUsingQueryBuilderWithMultiANDTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			
			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String nestedQuery=ExcelLib.getCellValue(xlpath, sheet, 66, 1);
			String idCodeStr=ExcelLib.getCellValue(xlpath,sheet,9,1);
			String invoiceNoStr=ExcelLib.getCellValue(xlpath,sheet,10,1);	
			String terms=ExcelLib.getCellValue(xlpath, sheet, 67, 1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);	
			
			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);
			cwp.CreateWorkitem(workitem,className,workitemType);
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");
			
			cwp.getWorkItemName(workitem).click();
			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Updation of Formfield",true);
			ATUReports.add("Updation of Formfield",true);
			Log.info("Updation of Formfield");
			
			wdp.getFormfields_win().click();
			util.wait(time);
			//ID_CODE text field
			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys(idCodeStr);
			Reporter.log("Idcode has been written",true);
			ATUReports.add("Idcode has been written",true);
			Log.info("Idcode has been written");
			util.wait(time);
			
			wdp.getInvoiceno_TF().clear();
			wdp.getInvoiceno_TF().sendKeys(invoiceNoStr);
			Reporter.log("Invoice Number has been written",true);
			ATUReports.add("Invoice Number has been written",true);
			Log.info("Invoice Number has been written");
			util.wait(time);
			
			wdp.getTerms_DD().sendKeys(terms);
			Reporter.log("Terms has been written",true);
			ATUReports.add("Terms has been written",true);
			Log.info("Terms has been written");
			util.wait(time);			
			
			wdp.getUpdate_btn().click();
			Reporter.log("Update Button has been Clicked",true);
			ATUReports.add("Update Button has been Clicked",true);
			Log.info("Update Button has been Clicked");
			util.waitForPageToLoad();
			util.wait(time);
                        util.wait(time);
			
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
			sp.getIdCodeFieldInNestedSearch().sendKeys(idCodeStr);
			Reporter.log("ID Code value is entered", true);
			Log.info("ID Code value is entered");
			ATUReports.add("ID Code value is entered", true);
			
			sp.getInvoiceNoFieldInNestedSearch().clear();
			sp.getInvoiceNoFieldInNestedSearch().sendKeys(invoiceNoStr);
			Reporter.log("Invoice Number value is entered", true);
			Log.info("Invoice Number value is entered");
			ATUReports.add("Invoice Number value is entered", true);
			
			sp.getMultiANDTermsFieldInNestedSearch().clear();
			sp.getMultiANDTermsFieldInNestedSearch().sendKeys(terms);
			Reporter.log("Terms value is entered", true);
			Log.info("Terms value is entered");
			ATUReports.add("Terms value is entered", true);
			util.wait(time);
                        util.wait(time);
			util.waitForPageToLoad();
			
			sp.getSearchButtonInNestedSearch().click();
			Reporter.log("Search button is clicked", true);
			Log.info("Search button is clicked");
			ATUReports.add("Search button is clicked", true);
			
			sp.searchWorkitemByNameInSearchpage(workitem);
			util.wait(time);
			boolean workitemPresence = util.verifyObjectPresentReturnsBool(sp.getWorkItemName(workitem));
			if(workitemPresence){
				Reporter.log("Workitem is searched successfully by Nested query using multi AND",true);
				ATUReports.add("Workitem is searched successfully by Nested query using multi AND", true);
				Log.info("Workitem is searched successfully by Nested query using multi AND");
			}else{
				ATUReports.add("Nested query using multi AND test failed",LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Nested query using multi AND test failed ");
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add("Failed to verify the Nested search functionality with multi AND using query builder in iwm.",LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail(" Nested search functionality with multi AND using query builder test failed");
		}
		finally {
			Log.endTestCase("INFO_4313_VerifyNestedSearchFunctionalityUsingQueryBuilderWithMultiANDTest");
			
		}
	}
}