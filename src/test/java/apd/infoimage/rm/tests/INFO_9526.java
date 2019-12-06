package apd.infoimage.rm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
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
 * @author SumanGaK - 29-Jun-2018 
 * INFO-9526 
 * This class will Verify whether RM banner is always at the top of the screen even after scrolling down
 **/
public class INFO_9526 extends SuperClassIWM {

	@BeforeMethod
	public void beforMethod() {
		Driver.driver.get(rmProp.getProperty("rmUrl"));
		util.waitForPageToLoad();
		rmlp.loginToRM("userid", "pwd", "domain");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod() {
		rmhp.logoutOfRM();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testVerifyRMBannerTopPositionOfScreenAterScrollingDown() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-9526 is to Verify whether RM banner is always at the top of the screen even after scrolling down");
		ATUReports.setAuthorInfo("SumanGaK", "JUN-2018", "0.3");

		try {
			Log.startTestCase("INFO_9526_VerifyRMBannerTopPositionOfScreenAterScrollingDownTest");

			Reporter.log("VerifyRMBannerTopPositionOfScreenAterScrollingDownTest",true);
			ATUReports.add("VerifyRMBannerTopPositionOfScreenAterScrollingDownTest",true);
			Log.info("VerifyRMBannerTopPositionOfScreenAterScrollingDownTest");

			rmProp = new Properties();
			rmProp.load(new FileInputStream("src/main/resources/rmData.properties"));

			String sheet = rmProp.getProperty("sheet");
			String xlpath = rmProp.getProperty("xlpath");
			int time = ExcelLib.getCellValueInt(xlpath,sheet,8,1);	

			Reporter.log("User is in Homepage of RM", true);
			ATUReports.add("User is in Homepage of RM", true);
			Log.info("User is in Homepage of RM");

			if(rmhp.getRmBannerSize().size()==1)
			{
				Reporter.log("Rm Banner is Displayed", true);
				ATUReports.add("Rm Banner is Displayed", true);
				Log.info("Rm Banner is Displayed");
			}
			else
			{
				Reporter.log("Rm Banner is not Displayed", true);
				ATUReports.add("Rm Banner is not Displayed", true);
				Log.info("Rm Banner is not Displayed");
			}

			JavascriptExecutor js = (JavascriptExecutor)Driver.driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

			util.waitForPageToLoad();
			util.wait(time);
			if(rmhp.getRmBannerSize().size() == 1)
			{
				Reporter.log("Rm Banner is not Displayed", true);
				ATUReports.add("Rm Banner is not Displayed", true);
				Log.info("Rm Banner is not Displayed");
			}
			else
			{
				Reporter.log("Rm Banner is Displayed", true);
				ATUReports.add("Rm Banner is Displayed", true);
				Log.info("Rm Banner is Displayed");
				ATUReports.add("VerifyRMBannerTopPositionOfScreenAterScrollingDown test failed", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("VerifyRMBannerTopPositionOfScreenAterScrollingDown test failed");
			}			

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Log.error("VerifyRMBannerTopPositionOfScreenAterScrollingDown test failed " + e.getMessage());
			ATUReports.add("Failed to VerifyRMBannerTopPositionOfScreenAterScrollingDown test in rm.", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Reporter.log("VerifyRMBannerTopPositionOfScreenAterScrollingDown test failed", true);
			Assert.fail("VerifyRMBannerTopPositionOfScreenAterScrollingDown test failed");
		} finally {
			Log.endTestCase("INFO_9526_VerifyRMBannerTopPositionOfScreenAterScrollingDownTest");
		}
	}	
}

