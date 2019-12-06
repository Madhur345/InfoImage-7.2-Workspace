package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
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
 * INFO-5107
 * This class will Perform Running A Query In Refined Search
 **/
public class INFO_5107 extends SuperClassIWM {
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
	@Test(enabled = true, groups = { "Search" })
	public void testRunningAQueryInRefinedSearch() {

		ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "Running A Query In Refined Search");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("Running A Query In Refined Search");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));


			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");
			String queryType=prop.getProperty("queryType");
			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String idCodeStr=ExcelLib.getCellValue(xlpath,sheet,9,1);
			int time=ExcelLib.getCellValueInt(xlpath,sheet,58,1);

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
			Reporter.log("Document: "+workitem+" has been created",true);
			ATUReports.add("Document: "+workitem+" has been created",true);
			Log.info("Document: "+workitem+" has been created");
			util.wait(time);
			Reporter.log("Selection of Workitem",true);
			ATUReports.add("Selection of Workitem",true);
			Log.info("Selection of Workitem");
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

			util.jclick(wdp.getUpdate_btn());
			Reporter.log("Update Button has been Clicked", true);
			ATUReports.add("Update Button has been Clicked", true);
			Log.info("Update Button has been Clicked");

			util.waitForPageToLoad();
			util.wait(time);

			util.jclick(wdp.getWorkItemTab());
			Reporter.log("WorkItems Tab is clicked", true);
			ATUReports.add("WorkItems Tab is clicked", true);
			Log.info("WorkItems Tab is clicked");
			util.wait(time);
			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(workitem);
			Reporter.log("Sending Workitem to Workflow",true);
			ATUReports.add("Sending Workitem to Workflow",true);
			Log.info("Sending Workitem to Workflow");

			cwp.sendWorkItemToDefaultQueue(workitem);

			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Performing Refined Search",true);
			ATUReports.add("Performing Refined Search",true);
			Log.info("Performing Refined Search");

			sp.runQueryInRefinedSearch(workitem, queryType);

			util.waitForPageToLoad();
			util.wait(time);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Failed to execute RunningAQueryInRefinedSearch test", true);
			ATUReports.add("Execute Running A Query In Refined Search",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Failed to execute RunningAQueryInRefinedSearch test");
		}
		finally {
			Log.endTestCase("Running A Query In Refined Search");
		}
	}
}