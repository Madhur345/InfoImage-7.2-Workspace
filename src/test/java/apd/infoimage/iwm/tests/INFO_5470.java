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

@Test
public class INFO_5470 extends SuperClassIWM { 
	
	
	@Test(enabled=true,groups={"Workitem"})
	public void test(){
		Log.startTestCase("INFO_5470_Export");
		try{
			ATUReports.setTestCaseReqCoverage("This Scenario INFO_5470 is To verify" + "Verify Export");
			ATUReports.setAuthorInfo("Biswajit", "MAY-2018", "0.3");
			Driver.driver.get(prop.getProperty("appUrl"));
			util.waitForPageToLoad();
			Thread.sleep(5000);
			lp.loginToApp("userName", "password", "domain", "role");
			util.waitForPageToLoad();
			Thread.sleep(5000);
			cwp.export();
			Reporter.log("Export operation completed",true);
			Thread.sleep(5000);
			}catch(Exception e){
				e.printStackTrace();
				ATUReports.add(
						"Verify Export",
						LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
	}finally{
		hp.logoutApp();
		Log.endTestCase("Verify Export");
}
	}
}
