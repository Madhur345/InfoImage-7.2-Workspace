package apd.infoimage.iwm.tests;

import org.testng.Assert;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

/**
 * @author PradhanJ
 *
 */
public class INFO_3697 extends SuperClassIWM {

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

	// please make sure demo routing works as follows Data entry> invoice process>
	// Terms
	@Test(enabled = true, groups = { "Workitem" })
	public void routingWorkItem() {
		String documentName = null;
		String currentClass = "archive";
		ATUReports.setTestCaseReqCoverage("This Scenario is INFO_3697_RoutingWorkitemTest");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		try {
			Log.startTestCase("INFO_3697_RoutingWorkitemTest");

			documentName = "vin" + util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(documentName, currentClass, "Document");
			Reporter.log("Documet: " + documentName + " has been created", true);
			ATUReports.add("Documet: " + documentName + " has been created", true);
			Thread.sleep(2000);

			cwp.sendWorkItemToDefaultQueue(documentName);
			ip.retrieveWorkItem(documentName);
			util.waitForPageToLoad();

			// invoice processing
			cwp.getCheckBoxWorkItemName(documentName).click();
			Thread.sleep(2000);
			cwp.getSendWorkItemButton().click();

			cwp.getSendButton().click();
			util.waitForPageToLoad();
			Thread.sleep(5000);
			hp.logoutApp();
			lp.loginToApp("userName", "password", "domain", "role");
			util.waitForPageToLoad();

			hp.getInbox().click();
			util.waitForPageToLoad();
			ip.getInvoiceProcessing().click();
			util.waitForPageToLoad();
			ip.getDataEntry().click();
			util.waitForPageToLoad();
			ip.getInvoiceProcessing().click();
			util.waitForPageToLoad();

			boolean f = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(documentName));
			if (f) {
				Reporter.log("Sent Workitem is present in InvoiceProcessing", true);
				ATUReports.add("Sent Workitem is present in InvoiceProcessing", true);
			} else {
				Reporter.log("Sent Workitem is not present in InvoiceProcessing", true);
				ATUReports.add("Sent Workitem is not present in InvoiceProcessing", true);
				Assert.fail("Sent Workitem is not present in InvoiceProcessing ");
			}
			Thread.sleep(5000);
			cwp.getCheckBoxWorkItemName(documentName).click();
			Thread.sleep(2000);
			ip.getRetrieve(documentName).click();
			util.waitForPageToLoad();
			hp.getWorkItemTab().click();
			util.waitForPageToLoad();
			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute routingWorkItem test", true);
			ATUReports.add("failed to execute routingWorkItem test", true);
			ATUReports.add("failed to execute test INFO_3697_RoutingWorkitemTest ",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute routingWorkItem test");
		} finally {

			util.waitForPageToLoad();
			Log.endTestCase("INFO_3697_RoutingWorkitemTest");
		}
	}

}
