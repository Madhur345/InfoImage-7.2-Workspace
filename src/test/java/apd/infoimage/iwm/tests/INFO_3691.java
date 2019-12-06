package apd.infoimage.iwm.tests;


import org.testng.Reporter;
import org.testng.annotations.Test;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

//@SuppressWarnings("unused")
public class INFO_3691 extends SuperClassIWM {

	@SuppressWarnings("deprecation")
	@Test(groups = { "Workitem" })
	public void test() {
		ATUReports.setTestCaseReqCoverage("This Scenario is INFO_3691_WorkitemUpdateTest");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		try {

			Log.startTestCase("INFO_3691_WorkitemUpdateTest");
			String workitm = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
			Driver.driver.get(prop.getProperty("appUrl"));
			util.waitForPageToLoad();
			lp.loginToApp("userName", "password", "domain", "role");
			util.waitForPageToLoad();
			Reporter.log("User logged in successfully", true);
			ATUReports.add("User logged in successfully", true);

			cwp.CreateWorkitem(workitm, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitm).click();
			Thread.sleep(3000);
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);


			Thread.sleep(3000);
			wdp.WorkItemDetailView("ID_CODE_1", "INVOICE_NO_2", "TERMS_4", "ItemA_5");
			Reporter.log("Formfields updated", true);
			ATUReports.add("Formfields updated", true);			

			Thread.sleep(2000);
			
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			ATUReports.add("failed to execute test INFO_3691_WorkitemUpdateTest ",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
		} finally {

			hp.logoutApp();
			Log.endTestCase("INFO_3691_WorkitemUpdateTest");
		}
	}
}
