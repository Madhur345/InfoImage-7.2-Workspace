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
 * @author SumanGaK - 24-Aug-2018 
 * INFO-12187
 * This test method Performs 'Get Next' functionality for multiple workitems in any queue , off get next and check the workitems in invoice processing.
 * Pre-requisite : Data Entry should be empty
 */
public class INFO_12187 extends SuperClassIWM {
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
	public void testPerformCreateWorkitemsGetNextSendToDefaultOffGetNextCheckWorkitemsInInvoiceProcessing() {
		Log.startTestCase("INFO_12187_PerformCreateWorkitemsGetNextSendToDefaultOffGetNextCheckWorkitemsInInvoiceProcessingTest");
		try {
			Reporter.log("PerformCreateWorkitemsGetNextSendToDefaultOffGetNextCheckWorkitemsInInvoiceProcessingTest", true);
			ATUReports.add("PerformCreateWorkitemsGetNextSendToDefaultOffGetNextCheckWorkitemsInInvoiceProcessingTest", true);
			Log.info("PerformCreateWorkitemsGetNextSendToDefaultOffGetNextCheckWorkitemsInInvoiceProcessingTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			int counter = 1 , arrCounter = 0;
			int wiCountVal = ExcelLib.getCellValueInt(xlpath, sheet, 15, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			String[] workitemarr = new String[100];

			for (int loopCounter = 1; loopCounter <= wiCountVal; loopCounter++) {
				String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
				String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
				String workitem = str + util.getSysDate(0, date);
				workitemarr[arrCounter] = workitem;
				arrCounter++;
				String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
				String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);

				Reporter.log("Creation of Workitem", true);
				ATUReports.add("Creation of Workitem", true);
				Log.info("Creation of Workitem");

				cwp.CreateWorkitem(workitem, className, workitemType);
				Reporter.log("Sending Workitem to Workflow", true);
				ATUReports.add("Sending Workitem to Workflow", true);
				Log.info("Sending Workitem to Workflow");

				util.wait(time);
				cwp.sendWorkItemToDefaultQueue(workitem);
				util.wait(time);
				util.waitForPageToLoad();

				if (counter != wiCountVal) {
					counter++;
					ip.getWorkItemsTab().click();
					Reporter.log("WorkItem tab is clicked", true);
					ATUReports.add("WorkItem tab is clicked", true);
					Log.info("WorkItem tab is clicked");

					util.wait(time);
					util.waitForPageToLoad();
				}
				util.wait(time);
				util.waitForPageToLoad();
			}

			ip.getNextONButton().click();
			Reporter.log("get Next Button is clicked", true);
			ATUReports.add("get Next Button is clicked", true);
			Log.info("get Next Button is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			util.jclick(ip.getNextAutoOpenCheckbox());
			Reporter.log("get Next Auto Open Checkbox is clicked", true);
			ATUReports.add("get Next Auto Open Checkbox is clicked", true);
			Log.info("get Next Auto Open Checkbox is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().clear();
			util.wait(time);
			util.waitForPageToLoad();

			ip.getRequiredNoOfWorkitems().sendKeys("" + wiCountVal);
			Reporter.log("get Next Required No Of Workitems is sent to textbox", true);
			ATUReports.add("get Next Required No Of Workitems is sent to textbox", true);
			Log.info("get Next Required No Of Workitems is sent to textbox");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getSaveButton().click();
			Reporter.log("SaveButton is clicked", true);
			ATUReports.add("SaveButton is clicked", true);
			Log.info("SaveButton is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked", true);
			ATUReports.add("Data Entry is clicked", true);
			Log.info("Data Entry is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getFirstWorkitem().click();
			util.wait(time);
			util.waitForPageToLoad();

			/*while(wdp.getCloseWorkitem().isDisplayed()) {
				
				util.wait(time);
				util.waitForPageToLoad();
				wdp.getActionsDropDown().click();
				Reporter.log("Actions Drop Down is clicked", true);
				ATUReports.add("Actions Drop Down is clicked", true);
				Log.info("Actions Drop Down is clicked");

				util.wait(time);
				util.waitForPageToLoad();

				wdp.getSendToDefaultOption().click();
				Reporter.log("Send To Default Option is selected", true);
				ATUReports.add("Send To Default Option is selected", true);
				Log.info("Send To Default Option is selected");

				util.wait(time);
				util.waitForPageToLoad();
				continue;				
			}*/
			
			for(int loopCount=1;loopCount<=wiCountVal;loopCount++)
			{
			util.wait(time);
			util.waitForPageToLoad();
			wdp.getActionsDropDown().click();
			Reporter.log("Actions Drop Down is clicked", true);
			ATUReports.add("Actions Drop Down is clicked", true);
			Log.info("Actions Drop Down is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			wdp.getSendToDefaultOption().click();
			Reporter.log("Send To Default Option is selected", true);
			ATUReports.add("Send To Default Option is selected", true);
			Log.info("Send To Default Option is selected");

			util.wait(time);
			util.waitForPageToLoad();
			}

			ip.getNextOFFButton().click();
			Reporter.log("get Next Button is clicked and turned off", true);
			ATUReports.add("get Next Button is clicked and turned off", true);
			Log.info("get Next Button is clicked and turned off");

			util.wait(time);
			util.wait(time);
			ip.getWorkItemsTab().click();
			Reporter.log("WorkItems Tab is clicked", true);
			util.wait(time);
			util.wait(time);
			util.waitForPageToLoad();
			
			hp.getInbox().click();
			Reporter.log("Inbox is clicked", true);
			util.wait(time);
			util.wait(time);
			util.waitForPageToLoad();
			ip.getInvoiceProcessing().click();
			Reporter.log("Invoice Processing tab is clicked", true);
			ATUReports.add("Invoice Processing tab is clicked", true);
			Log.info("Invoice Processing tab is clicked");

			util.wait(time);
			util.wait(time);
			util.wait(time);
			util.wait(time);
			util.waitForPageToLoad();


			for(arrCounter = 0; arrCounter < wiCountVal; arrCounter++)
			{
				if(arrCounter < wiCountVal)
				{
				ip.searchByNameInInvoiceProcessing(workitemarr[arrCounter]);
				Reporter.log("Searching Workitem By Name In Invoice Processing", true);
				
				util.wait(time);
				boolean workitemPresence = util.verifyObjectPresentReturnsBool(ip.getWorkItemName(workitemarr[arrCounter]));

				if(workitemPresence){
					Reporter.log("Workitem is present in Invoice Processing grid",true);
					ATUReports.add("Workitem is present in Invoice Processing grid",true);
					ATUReports.add("Performs 'Get Next' functionality for multiple workitems in any queue , off get next and check the workitems in invoice processing", "","Workitem should be displayed",
							"Workitem is displayed", true);
				}else{
					Reporter.log("Workitem is not present in Invoice Processing grid",true);
					ATUReports.add("Workitem is not present in Invoice Processing grid",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				}
				}
				
			}
			util.wait(time);
			util.waitForPageToLoad();

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute PerformCreateWorkitemsGetNextSendToDefaultOffGetNextCheckWorkitemsInInvoiceProcessing test", true);
			ATUReports.add("failed to execute PerformCreateWorkitemsGetNextSendToDefaultOffGetNextCheckWorkitemsInInvoiceProcessing test", true);

		} finally {
			Log.endTestCase("INFO_12187_PerformCreateWorkitemsGetNextSendToDefaultOffGetNextCheckWorkitemsInInvoiceProcessingTest");

		}
	}
}