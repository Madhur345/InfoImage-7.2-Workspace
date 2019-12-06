package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author SumanGaK - 21-Nov-2018 
 * INFO-16726 
 * This class verify the Reserve icon availability under the Actions button when workitem is created and opened. 
 */
public class INFO_16726 extends SuperClassIWM {
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
	@Test(enabled = true, priority = 1, groups = { "GetNext" })
	public void testVerifyReserveAvailabilityUnderActionsWhenWorkitemIsCreatedAndOpened() {
		Log.startTestCase("INFO_16726_VerifyReserveAvailabilityUnderActionsWhenWorkitemIsCreatedAndOpenedTest");
		try {
			Reporter.log("VerifyReserveAvailabilityUnderActionsWhenWorkitemIsCreatedAndOpenedTest", true);
			ATUReports.add("VerifyReserveAvailabilityUnderActionsWhenWorkitemIsCreatedAndOpenedTest", true);
			Log.info("VerifyReserveAvailabilityUnderActionsWhenWorkitemIsCreatedAndOpenedTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);			


			util.wait(time);
			util.waitForPageToLoad();

			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);

			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			Log.info("Creation of Workitem");

			cwp.CreateWorkitem(workitem,className,workitemType);
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");
			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView is displayed",true);
			ATUReports.add("WorkItemDetailView is displayed",true);
			Log.info("WorkItemDetailView is displayed");
			util.wait(time);
			util.waitForPageToLoad();

			wdp.getActionsDropDown().click();
			Reporter.log("Actions Drop Down is clicked", true);
			ATUReports.add("Actions Drop Down is clicked", true);
			Log.info("Actions Drop Down is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			boolean reserveOptionPresence = wdp.getReserveOption().isDisplayed();

			if(reserveOptionPresence){
				Reporter.log("Reserve Option is present in Actions Menu",true);
				ATUReports.add("Reserve Option is present in Actions Menu",true);
				ATUReports.add("verify the Reserve icon availability under the Actions button when workitem is created and opened", "","Reserve Option should be displayed",
						"Reserve Option is displayed", true);
			}else{
				Reporter.log("Reserve Option is not present in Actions Menu",true);
				ATUReports.add("Reserve Option is not present in Actions Menu",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			}


			util.wait(time);
			util.waitForPageToLoad();

			String reserveOptionEnabled = wdp.getReserveOption().getAttribute("class");
			
			if(reserveOptionEnabled.equalsIgnoreCase("inactiveAction")){
				Reporter.log("Reserve Option is not enabled in Actions Menu",true);
				ATUReports.add("Reserve Option is not enabled in Actions Menu",true);
				ATUReports.add("verify the Reserve icon availability under the Actions button when workitem is created and opened", "","Reserve Option should be enabled",
						"Reserve Option is not enabled", true);
			}else{
				Reporter.log("Reserve Option is enabled in Actions Menu",true);
				ATUReports.add("Reserve Option is enabled in Actions Menu",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			}

			util.wait(time);
			util.waitForPageToLoad();

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute VerifyReserveAvailabilityUnderActionsWhenWorkitemIsCreatedAndOpened test", true);
			ATUReports.add("failed to execute VerifyReserveAvailabilityUnderActionsWhenWorkitemIsCreatedAndOpened test", true);

		} finally {
			Log.endTestCase("INFO_16726_VerifyReserveAvailabilityUnderActionsWhenWorkitemIsCreatedAndOpenedTest");
		}
	}
}
