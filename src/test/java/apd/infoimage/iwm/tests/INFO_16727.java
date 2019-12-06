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
 * @author SinghAvn 03-DEC-2018 INFO-16727 To verify the Reserve icon
 *         availability under the Actions button when workitem is retrieved &
 *         opened.
 **/
public class INFO_16727 extends SuperClassIWM {
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
	@Test(enabled = true, priority = 1, groups = { "DocumentDuplicate" })
	public void reserveAlert() {

		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-16727 will verify the Reserve icon availability under the Actions button when  workitem is retrieved & opened.");
		ATUReports.setAuthorInfo("Avnish ", "NOV-2018", "0");

		try {
			Log.startTestCase("INFO-16727 will verify the Reserve icon availability");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			Reporter.log(
					"Class Name : INFO_16727_Test_to_verify_Reserve_icon_availability_Actions_button_workitem_retrieved_opened",
					true);
			ATUReports.add(
					"Class Name : INFO_16727_Test_to_verify_Reserve_icon_availability_Actions_button_workitem_retrieved_opened",
					true);

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);
			Log.info("CreateWorkitem operation performed");
			util.wait(time);
			ip.getCheckBoxWorkItemName(workitem).click();
			Reporter.log("Workitem Check Box to is clicked", true);
			ATUReports.add("Workitem Check Box to is clicked", true);
			Log.info("Workitem Check Box to is clicked");

			cwp.getSendWorkItemButton().click();
			Reporter.log("send workitem button is clicked", true);
			ATUReports.add("send workitem button is clicked", true);
			Log.info("send workitem button is clicked");

			cwp.getSendButton().click();
			Reporter.log("send button is clicked", true);
			ATUReports.add("send button is clicked", true);
			Log.info("send button is clicked");
			util.wait(time);

			hp.getSearchTab().click();
			Reporter.log("Search tab is clicked", true);
			ATUReports.add("Search tab is clicked", true);
			Log.info("Search tab is clicked");
			util.wait(time);

			sp.getSearchByNameTab().click();
			Reporter.log("SearchByName  tab is clicked", true);
			ATUReports.add("SearchByName tab is clicked", true);
			Log.info("SearchByName tab is clicked");
			util.wait(time);

			sp.getwNameSearchTextbox().sendKeys(workitem);
			Reporter.log("writing in the textbox", true);
			ATUReports.add("writing in the textbox", true);
			Log.info("writing in the textbox");
			util.wait(time);

			sp.getSbnSearchBtn().click();
			Reporter.log("Searchbutton is clicked", true);
			ATUReports.add("Searchbutton is clicked", true);
			Log.info("Searchbutton is clicked");
			util.wait(time);

			sp.getsearchByNameCheckBox().click();
			Reporter.log("checkbox is clicked", true);
			ATUReports.add("checkbox is clicked", true);
			Log.info("checkbox is clicked");
			util.wait(time);

			util.jclick(sp.getRetrieveButton());
			Reporter.log("retrieve button is clicked", true);
			ATUReports.add("retrieve button is clicked", true);
			Log.info("retrieve button is clicked");
			util.wait(time);

			hp.getWorkItemTab().click();
			Reporter.log("workitem tab is clicked", true);
			ATUReports.add("workitem tab is clicked", true);
			Log.info("workitem tab is clicked");
			util.wait(time);

			cwp.searchByNameInWorkitemList(workitem);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("workitem is opened", true);
			ATUReports.add("workitem is opened", true);
			Log.info("workitem is opened");

			wdp.getActionsDropDown().click();
			Reporter.log("Action drop down is clicked", true);
			ATUReports.add("Action drop down is clicked", true);
			Log.info("Action drop down is clicked");
			util.wait(time);

			// Validation

			boolean getReservePresence = wdp.isElementPresent(wdp.getReserveOption());

			if (getReservePresence == true) {
				Reporter.log("Reserve Button is present in Actions Menu", true);
				ATUReports.add("Reserve Button is present in Actions Menu", true);
				Log.info("Reserve Button is present in Actions Menu");
				ATUReports.add(
						"verify the Reserve icon availability under the Actions button when workitem is created and opened",
						"", "Reserve Option should be displayed", "Reserve Option is displayed", true);
			} else {
				Reporter.log("Reserve Button is not present in Actions Menu", true);
				ATUReports.add("Reserve Button is not present in Actions Menu", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
			}
			wdp.getReserveOption().click();
			Reporter.log("reserve button is clicked", true);
			ATUReports.add("reserve button is clicked", true);
			Log.info("reserve button is clicked");
			util.wait(time);

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log(
					"INFO_16727_Test_to_verify_Reserve_icon_availability_Actions_button_workitem_retrieved_opened Failed",
					true);
			Log.info(
					"INFO_16727_Test_to_verify_Reserve_icon_availability_Actions_button_workitem_retrieved_opened Failed");
			ATUReports.add(
					"INFO_16727_Test_to_verify_Reserve_icon_availability_Actions_button_workitem_retrieved_opened Failed",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail(
					"INFO_16727_Test_to_verify_Reserve_icon_availability_Actions_button_workitem_retrieved_opened Failed");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase(
					"INFO_16727_Test_to_verify_Reserve_icon_availability_Actions_button_workitem_retrieved_opened");
		}
	}
}