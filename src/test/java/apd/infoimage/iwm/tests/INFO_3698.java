package apd.infoimage.iwm.tests;

import org.testng.Assert;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;

/**
 * @author SumanGaK - 24-Nov-2017
 * INFO_3698
 * This class verifies whether Retrieve and open link is removed from actions for all the workitems in Inbox and Search.
 */
public class INFO_3698 extends SuperClassIWM {
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
	@Test(enabled = true, priority = 1, groups = { "Workitem" })
	public void testRetrieveAndOpenRemoved() {

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-3698 is for verifying whether Retrieve and open link is removed from actions for all the workitems in Inbox and Search.");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try {
			Log.startTestCase("INFO_3698_RetrieveAndOpenRemovedTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			int numberFour = ExcelLib.getCellValueInt(xlpath,sheet,87,1);			

			Reporter.log("RetrieveAndOpenRemovedTest : testRetrieveAndOpenRemoved()", true);
			ATUReports.add("RetrieveAndOpenRemovedTest : testRetrieveAndOpenRemoved()", true);
			Log.info("RetrieveAndOpenRemovedTest : testRetrieveAndOpenRemoved()");

			hp.getInbox().click();
			Reporter.log("Navigated to Inbox", true);
			ATUReports.add("Navigated to Inbox", true);
			Log.info("Navigated to Inbox");

			util.waitForPageToLoad();
			util.wait(time);
			ip.getDataEntry().click();
			// ip.getCustom().click();
			util.wait(time);
			util.waitForPageToLoad();

			Select sel = new Select(hp.getNumOfRowsDropDown());
			sel.selectByValue("100");
			util.wait(time);

			ip.getFirstRowFirstCell().click();
			if (ip.getInboxMetaData().getText() != "Retrieve and Open"
					&& ip.getInboxFormFields().getText() != "Retrieve and Open"
					&& ip.getInboxCopyURL().getText() != "Retrieve and Open") {
				Reporter.log("Retrieve and open link is removed from actions for the first workitem in Inbox Tab.", true);
				ATUReports.add("Retrieve and open link is removed from actions for the first workitem in Inbox Tab.", true);
				Log.info("Retrieve and open link is removed from actions for the first workitem in Inbox Tab.");
			}
			else {
				Reporter.log("Retrieve and open link is not removed from actions for the first workitem.", true);
			}

			util.wait(time);
			util.waitForPageToLoad();

			hp.getWorkItemTab().click();

			for (int i = 1; i <= numberFour; i++) {
				String workitem = str;
				Reporter.log("Creation of Workitem : " + workitem, true);
				ATUReports.add("Creation of Workitem : " + workitem, true);
				Log.info("Creation of Workitem : " + workitem);

				cwp.CreateWorkitem(workitem,className,workitemType);
				util.waitForPageToLoad();
				util.wait(time);

				cwp.getCheckBoxWorkItemName(workitem).click();
				util.wait(time);
				cwp.getSendWorkItemButton().click();
				cwp.getSendButton().click();
				util.waitForPageToLoad();
				util.wait(time);
			}

			util.waitForPageToLoad();
			util.wait(time);
			hp.getSearchTab().click();
			util.waitForPageToLoad();
			sp.getBasicSearch().click();

			util.waitForPageToLoad();
			util.wait(time);

			sp.getTextBox().sendKeys(str);
			sp.getSearchButton().click();

			util.waitForPageToLoad();
			util.wait(time);

			Select sele = new Select(hp.getNumOfRowsDropDown());
			sele.selectByValue("100");
			util.wait(time);

			sp.getFirstRowFirstCell().click();
			if (sp.getSearchCopyURL().getText() != "Retrieve and Open") {
				Reporter.log("Retrieve and open link is removed from actions for the first workitem in Search Tab.", true);
				ATUReports.add("Retrieve and open link is removed from actions for the first workitemin Search Tab.", true);
				Log.info("Retrieve and open link is removed from actions for the first workitem in Search Tab.");
			}

			else {
				Reporter.log("Retrieve and open link is not removed from actions for the first workitem.", true);
			}

			util.wait(time);
			util.waitForPageToLoad();

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute Retrieve And Open Removed test", true);
			Log.error(e.getMessage());
			Assert.fail("failed to execute Retrieve And Open Removed test");
		} finally {
			Log.endTestCase("INFO_3698_RetrieveAndOpenRemovedTest");
		}
	}
}
