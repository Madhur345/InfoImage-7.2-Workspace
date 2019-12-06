package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
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
 * @author SumanGaK - 26-Mar-2018 
 * INFO-8764 
 * This class Performs 'Get Next' functionality for multiple workitems in any queue by modifying a workitem and navigating to workitems list
 * Pre-requisite : Data Entry should be empty
 */
public class INFO_8764 extends SuperClassIWM {
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
	public void testGetNextMultipleWorkitemsInQueueModifyingWorkitemNavigatingToWorkitems() {
		Log.startTestCase("INFO_8764_GetNextMultipleWorkitemsInQueueModifyingWorkitemNavigatingToWorkitemsTest");
		try {
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-8764 is for Performing 'Get Next' functionality for multiple workitems in any queue by modifying a workitem and navigating to workitems list");
			ATUReports.setAuthorInfo("Suman", "MAR-2018", "0.3");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			Reporter.log("GetNextMultipleWorkitemsInQueueModifyingWorkitemNavigatingToWorkitemsTest", true);
			ATUReports.add("GetNextMultipleWorkitemsInQueueModifyingWorkitemNavigatingToWorkitemsTest", true);
			Log.info("GetNextMultipleWorkitemsInQueueModifyingWorkitemNavigatingToWorkitemsTest");

			int counter = 1, arrCounter = 0;
			String idCodeStr = ExcelLib.getCellValue(xlpath, sheet, 9, 1);
			String invoiceNoStr = ExcelLib.getCellValue(xlpath, sheet, 10, 1);
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 7, 1);
			int wiCountVal = ExcelLib.getCellValueInt(xlpath, sheet, 15, 1);
			String noteTitle = ExcelLib.getCellValue(xlpath, sheet, 17, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			int numberOne = ExcelLib.getCellValueInt(xlpath,sheet,83,1);
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

				if(counter <= wiCountVal) {
					counter++;
					ip.getWorkItemsTab().click();
					Reporter.log("WorkItem tab is clicked", true);
					ATUReports.add("WorkItem tab is clicked", true);
					Log.info("WorkItem tab is clicked");

					util.wait(time);
					util.waitForPageToLoad();
				}
			}

			util.wait(time);
			util.waitForPageToLoad();
			hp.getInbox().click();
			util.wait(time);
			util.waitForPageToLoad();

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
			util.wait(time);
			util.wait(time);

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
			util.wait(time);
			util.wait(time);

			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked", true);
			ATUReports.add("Data Entry is clicked", true);
			Log.info("Data Entry is clicked");

			util.wait(time);
			util.waitForPageToLoad();

			ip.getFirstWorkitem().click();
			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Updation of Formfield", true);
			ATUReports.add("Updation of Formfield", true);
			Log.info("Updation of Formfield");

			wdp.getFormfields_win().click();
			Reporter.log("Form fields Tab is clicked", true);
			ATUReports.add("Form fields Tab is clicked", true);
			Log.info("Form fields Tab is clicked");

			util.wait(time);
			// ID_CODE text field
			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys(idCodeStr);
			Reporter.log("Idcode has been written", true);
			ATUReports.add("Idcode has been written", true);
			Log.info("Idcode has been written");

			util.wait(time);

			wdp.getInvoiceno_TF().clear();
			wdp.getInvoiceno_TF().sendKeys(invoiceNoStr);
			Reporter.log("Invoice Number has been written", true);
			ATUReports.add("Invoice Number has been written", true);
			Log.info("Invoice Number has been written");

			util.wait(time);

			wdp.getUpdate_btn().click();
			Reporter.log("Update Button has been Clicked", true);
			ATUReports.add("Update Button has been Clicked", true);
			Log.info("Update Button has been Clicked");

			util.waitForPageToLoad();
			util.wait(time);

			Reporter.log("Formfields updated", true);
			ATUReports.add("Formfields updated", true);
			Log.info("Formfields updated");

			util.wait(time);

			wdp.getFormfields_win().click();
			Reporter.log("Form fields Tab is clicked", true);
			ATUReports.add("Form fields Tab is clicked", true);
			Log.info("Form fields Tab is clicked");

			util.wait(time);

			util.wait(time);
			wdp.Notes(noteTitle);
			Reporter.log("WorkItem notes added", true);
			ATUReports.add("WorkItem notes added", true);
			Log.info("WorkItem notes added");

			util.wait(time);

			wdp.getContentField().click();
			Reporter.log("Content Tab is clicked", true);
			ATUReports.add("Content Tab is clicked", true);
			Log.info("Content Tab is clicked");

			wdp.getAddNewPageIcon().click();
			Reporter.log("Add New Page is clicked", true);
			ATUReports.add("Add New Page is clicked", true);
			Log.info("Add New Page is clicked");

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,21,1);

				Reporter.log("img path " + imagePath, true);
				Log.info("img path " + imagePath);
				ATUReports.add("img path " + imagePath, true);
				util.wait(time);
				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				// Validate document name in the add new page window
				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals(fileName);
				if (docNameInAddNewPageWin) {
					Assert.assertEquals(wdp.getSelectedFile().getText(), fileName);
					Reporter.log("Selected TIF file name validation is successful", true);
					ATUReports.add("Selected TIF file name validation is successful", true);
				} else {
					Reporter.log("Selected TIF file name validation is failed.", true);
					ATUReports.add("Selected TIF file name validation is failed.", true);
					Assert.fail("Selected TIF file name validation is failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");

				util.waitForPageToLoad();

				// Validate the document is listed under content and no of pages in the document
				try {
					int contentList = wdp.getContentPageNo();
					if (contentList >= numberOne) {
						Reporter.log("1 Page is listed under content on the side bar ", true);
						ATUReports.add("1 Page is listed under content on the side bar ", true);
						Log.info("1 Page is listed under content on the side bar ");
					} else {
						Reporter.log("1 Page is NOT listed under content on the side bar", true);
						ATUReports.add("1 Page is NOT listed under content on the side bar", true);
						Assert.fail("1 Page is NOT listed under content on the side bar");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Validation of added  TIF file is failed", true);
					ATUReports.add("Validation of added  TIF file is failed", true);
					Assert.fail("Validation of added  TIF file is failed");
				}


				util.jclick(wdp.getActionsDropDown());
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
				ip.getWorkItemsTab().click();
				Reporter.log("WorkItem tab is clicked", true);
				ATUReports.add("WorkItem tab is clicked", true);
				Log.info("WorkItem tab is clicked");

				util.wait(time);
				util.waitForPageToLoad();

				/*for(arrCounter = 0; arrCounter < wiCountVal; arrCounter++)
				{
					if(arrCounter < wiCountVal)
					{
						cwp.getSearchFieldInWorkitemTab().sendKeys(workitemarr[arrCounter]);
						Reporter.log("Searching Workitem By Name In Workitem Tab :"+workitemarr[arrCounter], true);

						util.wait(time);
						boolean workitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitemarr[arrCounter]));
								
						if(workitemPresence){
							Reporter.log("Workitem is present in WorkItem tab",true);
							ATUReports.add("Workitem is present in WorkItem tab",true);

						}else{
							Reporter.log("Workitem is not present in WorkItem tab",true);
							ATUReports.add("Performs 'Get Next' functionality for multiple workitems from any queue by sending the workitems to default queue", "","Workitem should not be displayed",
									"Workitem is not displayed", true);
						}
					}
				}*/

				util.wait(time);
				util.waitForPageToLoad();
				hp.getInbox().click();
				util.wait(time);
				util.waitForPageToLoad();

				util.jclick(ip.getNextAutoOpenCheckbox());
				Reporter.log("get Next Auto Open Checkbox is clicked and turned OFF", true);
				ATUReports.add("get Next Auto Open Checkbox is clicked and turned OFF", true);
				Log.info("get Next Auto Open Checkbox is clicked and turned OFF");

				util.wait(time);
				util.waitForPageToLoad();

				ip.getNextOFFButton().click();
				Reporter.log("get Next Button is clicked and turned off", true);
				ATUReports.add("get Next Button is clicked and turned off", true);
				Log.info("get Next Button is clicked and turned off");

				util.wait(time);
				util.waitForPageToLoad();
				ip.getWorkItemsTab().click();
				Reporter.log("WorkItem tab is clicked", true);
				ATUReports.add("WorkItem tab is clicked", true);
				Log.info("WorkItem tab is clicked");

				util.wait(time);
				util.waitForPageToLoad();
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute GetNextMultipleWorkitemsInQueueModifyingWorkitemNavigatingToWorkitems test", true);
			ATUReports.add("failed to execute GetNextMultipleWorkitemsInQueueModifyingWorkitemNavigatingToWorkitems test",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute GetNextMultipleWorkitemsInQueueModifyingWorkitemNavigatingToWorkitems test");
		}
		finally {
			Log.endTestCase("INFO_8764_GetNextMultipleWorkitemsInQueueModifyingWorkitemNavigatingToWorkitemsTest");
		}
	}

}
