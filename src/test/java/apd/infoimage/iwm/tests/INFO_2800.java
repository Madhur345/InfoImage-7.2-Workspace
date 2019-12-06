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
 * @author SumanGaK - 20-Nov-2017
 * INFO_2800
 * This class verify if calendar pop up is getting opened in IWM
 */
public class INFO_2800 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1, groups = { "Inbox" })
	public void testVerifyOpenCalendarPopup() {
		System.out.println("I entered apd.infoimage.iwm.tests.INFO_2800_OpenCalendarPopupTest");	

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-2800 verify if calendar pop up is getting opened in IWM");
		ATUReports.setAuthorInfo("SumanGaK", "MAR-2018", "0.3");
		try {
			Log.startTestCase("INFO_2800_OpenCalendarPopupTest");
			Reporter.log("OpenCalendarPopupTest : testVerifyOpenCalendarPopup()", true);
			ATUReports.add("OpenCalendarPopupTest : testVerifyOpenCalendarPopup()", true);
			Log.info("OpenCalendarPopupTest : testVerifyOpenCalendarPopup()");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");
			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

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
			cwp.getWorkItemName(workitem).click();

			Reporter.log("WorkItemDetailView displayed",true);
			ATUReports.add("WorkItemDetailView displayed",true);
			Log.info("WorkItemDetailView displayed");

			util.wait(time);

			wdp.getFormfields_win().click();
			util.wait(time);
			wdp.getDateWidget().click();
			util.wait(time);

			boolean verifyOpenCalendarPopup = util.verifyObjectPresentReturnsBool(wdp.getDateWidget());
			if (verifyOpenCalendarPopup) {
				Reporter.log("Calendar popup is opening on click of date field.", true);
				ATUReports.add("Calendar popup is opening on click of date field.", true);
				Log.info("Calendar popup is opening on click of date field.");
			}

			else {
				Reporter.log("Calendar popup is not opening on click of date field.", true);
				ATUReports.add("Calendar popup is not opening on click of date field.", true);
			}
			System.out.println("About to Exit apd.infoimage.iwm.tests.INFO_2800_OpenCalendarPopupTest");			

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute Verify to open the calendar pop up in Date Field", true);
			ATUReports.add("failed to execute Verify to open the calendar pop up in Date Field", true);
			ATUReports.add("failed to execute INFO_2800_OpenCalendarPopupTest", LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute Verify to open the calendar pop up in Date Field");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("INFO_2800_OpenCalendarPopupTest");
		}
	}
}
