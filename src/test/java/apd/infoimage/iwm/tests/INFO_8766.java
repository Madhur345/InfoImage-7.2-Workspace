package apd.infoimage.iwm.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
 * @author PradhanJ INFO-8766 This class will Perform 'Get Next' functionality
 *         for multiple workitems by disabling 'Auto view' and modifing the
 *         workitem and sending the workitem to default queue
 */
public class INFO_8766 extends SuperClassIWM {
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
	@Test(enabled = true, groups = { "GetNext" })
	public void testGetNextByDisablingAutoViewAndModifyingWorkitem() {
		Log.startTestCase("INFO_8766_GetNextByDisablingAutoViewAndModifyingWorkitem");
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-8766  is for Performing 'Get Next' functionality for multiple workitems by "
						+ "disabling 'Auto view' and modifing the workitem and sending the workitem to default queue");
		ATUReports.setAuthorInfo("Jayashri", "APR-2018", "0.3");

		// Fetch the test data

		String xlpath = "src\\test\\resources\\testData.xlsx";
		String sheet = "Sheet1";
		String sheet2 = "Sheet_J";

		String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
		String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
		String workitem = str + util.getSysDate(0, date);
		String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
		String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
		String fileName = ExcelLib.getCellValue(xlpath, sheet, 18, 1);
		String newClassName = ExcelLib.getCellValue(xlpath, sheet2, 1, 1);
		String id_Code_Value = ExcelLib.getCellValue(xlpath, sheet2, 2, 1);
		String newWitemStr = ExcelLib.getCellValue(xlpath, sheet2, 3, 1);
		String newWitem = newWitemStr + util.getSysDate(0, date);
		int time = 3000;
		int noOfWitems = 3;
		String[] witems = new String[noOfWitems];
		try {

			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			cwp.CreateWorkitem(workitem, className, workitemType);

			Reporter.log("Sending Workitem to Workflow.", true);
			ATUReports.add("Sending Workitem to Workflow", true);
			Log.info("Sending Workitem to Workflow");
			Thread.sleep(2000);
			cwp.sendWorkItemToDefaultQueue(workitem);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getNextONButton().click();
			Reporter.log("get Next Button is clicked", true);
			ATUReports.add("get Next Button is clicked", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			// disable auto view if in case it is checked
			if (ip.getNextAutoOpenCheckbox().isSelected()) {
				ip.getNextAutoOpenCheckbox().click();
				Reporter.log("get Next Auto Open Checkbox is clicked", true);
				ATUReports.add("Verify Auto View option disabled", "", "Auto view checkbox should be unchecked",
						"Auto View checkbox is disabled", true);
				Thread.sleep(2000);
				util.waitForPageToLoad();
			} else {
				ATUReports.add("Verify Auto View option disabled", "", "Auto view checkbox should be unchecked",
						"Auto View checkbox is disabled", true);
			}

			ip.getRequiredNoOfWorkitems().clear();
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().sendKeys("3");
			Reporter.log("get Next Required No Of Workitems is sent to textbox ", true);
			ATUReports.add("get Next Required No Of Workitems is sent to textbox ", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ip.getSaveButton().click();
			Reporter.log("SaveButton is clicked", true);
			ATUReports.add("SaveButton is clicked", true);
			Thread.sleep(2000);
			util.waitForPageToLoad();

			ATUReports.add(" Set the required no of workitem In Get Next to 3", "No Of workitems =" + noOfWitems,
					"No should be set to 3", "No is set to 3", true);

			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked", true);
			ATUReports.add("Data Entry is clicked", true);
			Thread.sleep(3000);
			util.waitForPageToLoad();

			Reporter.log("Open the first workitem", true);
			ATUReports.add("Open the first workitem", true);
			ip.getFirstWorkitem().click();
			Thread.sleep(2000);
			util.waitForPageToLoad();

			Reporter.log("Get the count of open workitems in workitem detail page ", true);
			ATUReports.add("Get the count of open workitems in workitem detail page ", true);
			List<WebElement> closeIcons = Driver.driver
					.findElements(By.xpath("//li/i[contains(@class,'icon-remove tabClose getNextWorkitemClose')]"));
			int count = closeIcons.size();

			Reporter.log("Update workitem by adding formfields and note", true);
			ATUReports.add("Update workitem by adding formfields and note", true);
			wdp.WorkItemDetailView("ID_CODE_1", "INVOICE_NO_2", "TERMS_4", "ItemA_5");
			Reporter.log("Formfields updated", true);
			ATUReports.add("Formfields updated", true);
			util.waitForPageToLoad();
			Thread.sleep(2000);

			for (int k = 1; k <= noOfWitems; k++) {
				wdp.getActionsDropDown().click();
				Reporter.log("Actions Drop Down is clicked", true);
				ATUReports.add("Actions Drop Down is clicked", true);
				Thread.sleep(2000);
				util.waitForPageToLoad();

				wdp.getSendToDefaultOption().click();
				Reporter.log("Send To Default Option is selected", true);
				ATUReports.add("Send To Default Option is selected", true);

				ATUReports.add("First opened workitem will be sent to default", "",
						"workitem should be sent to default queue", "First workitem is sent to defalt queue", true);
				Thread.sleep(2000);
				util.waitForPageToLoad();

				// Validate if control came back to Queues
				String queueName = ip.getQueueNameLabelInInbox().getText();
				if (queueName.contains("Data Entry")) {
					ATUReports.add(
							"Verify After sending the first workitem to default it should navigate data Entry page", "",
							"Control should be in data Entry page", "Control is in data Entry page", true);
					break;
				}

			}
			Reporter.log("Verify remaining 2 workitems are in data entry", true);
			ATUReports.add("Verify remaining 2 workitems are in data entry", true);
			List<WebElement> witemsInDataEntry = Driver.driver
					.findElements(By.xpath("//font[contains(@class,'WorkitemName')]"));
			int witemsCount = witemsInDataEntry.size();
			if (witemsCount == (noOfWitems - 1)) {
				ATUReports.add("Verify remaining workitems are in data entry", "No Of workitems =" + witemsCount,
						"Remaining workitems should be in Data Entry", "Workitems are in Data entry", true);
			} else {
				Reporter.log("Remaining workitems are NOT  in data entry", true);
				Assert.fail("Remaining workitems are NOT  in data entry");
			}
			
			Thread.sleep(3000);
			hp.getInbox().click();
			util.waitForPageToLoad();
			util.wait(10000);
			ip.getRequiredNoOfWorkitems().clear();
			ATUReports.add("get Next Required No Of Workitems is cleared",true);
			Reporter.log("get Next Required No Of Workitems is cleared",true);
			Thread.sleep(3000);
			
			ip.getNextAutoOpenCheckbox().click();
			Reporter.log("get Next Auto Open Checkbox is clicked",true);
			ATUReports.add("get Next Auto Open Checkbox is clicked",true);
			Thread.sleep(3000);
			
			ip.getSaveButton().click();
			Reporter.log("SaveButton is clicked",true);
			ATUReports.add("SaveButton is clicked",true);
			Thread.sleep(3000);
			
			ip.getNextOFFButton().click();
			ATUReports.add("Get Next option is changed to off state", true);
			Reporter.log("Get Next option is changed to off state", true);
			Thread.sleep(5000);
			util.waitForPageToLoad();
			
		} catch (Exception e) {
			Assert.fail("Failed to execute erform 'Get Next' functionality by disabling Auto view" + e.getMessage());
			ATUReports.add(
					"Failed to Perform 'Get Next' functionality for multiple workitems by disabling Auto view"
							+ "and modifing the workitem and sending the workitem to default queue",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));

		}
		// INFO_8766_GetNextByDisablingAutoViewAndModifyingWorkitem
		finally {
			Log.endTestCase("INFO_8766_GetNextByDisablingAutoViewAndModifyingWorkitem");

		}

	}
}
