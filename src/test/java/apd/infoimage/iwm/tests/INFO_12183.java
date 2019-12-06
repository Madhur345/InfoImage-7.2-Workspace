package apd.infoimage.iwm.tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
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
 * @author SumanGaK - 30-Jul-2018
 * INFO-12183
 * This class Perform Document split operation using multpile images with jpg format for a document type of workitem and delete the workitem 
 */
@SuppressWarnings("deprecation")
public class INFO_12183 extends SuperClassIWM {

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

	@Test(enabled = true, priority = 1,groups={"DocumentSplit"})
	public void testPerformDocumentSplitAndDeletionOfDocumentWorkitem() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-12183 is for Performing Document split operation using multpile images with jpg format for a document type of workitem and delete the workitem");
		ATUReports.setAuthorInfo("Suman","JUL-2018","0.3"); 

		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_12183_PerformDocumentSplitAndDeletionOfDocumentWorkitemTest");

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			String sheet2 = prop.getProperty("sheet2");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 55, 1);
			String secondFileName = ExcelLib.getCellValue(xlpath, sheet, 57, 1);
			String thirdFileName = ExcelLib.getCellValue(xlpath, sheet, 56, 1);
			String newWitemStr = ExcelLib.getCellValue(xlpath, sheet2, 3, 1);
			String newWitem = newWitemStr + util.getSysDate(0, date);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Log.info("Workitem : " + workitem);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Log.info("Class Name : " + className);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Log.info("Workitem Type : " + workitemType);

			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			Log.info("Creation of Workitem");
			cwp.CreateWorkitem(workitem, className, workitemType);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView is displayed", true);
			ATUReports.add("WorkItemDetailView is displayed", true);
			Log.info("WorkItemDetailView is displayed");
			util.wait(time);

			wdp.getContentField().click();
			Reporter.log("Content Tab is clicked", true);
			ATUReports.add("Content Tab is clicked", true);
			Log.info("Content Tab is clicked");
			util.wait(time);

			wdp.getAddNewPageIcon().click();
			Reporter.log("Add New Page is clicked", true);
			ATUReports.add("Add New Page is clicked", true);
			Log.info("Add New Page is clicked");
			util.wait(time);
			util.waitForPageToLoad();

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				String imagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 27, 1);

				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				Log.info("img path " + imagePath);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				Reporter.log("multipage TIf file is uploaded", true);
				ATUReports.add("multipage TIf file is uploaded", true);
				Log.info("multipage TIf file is uploaded");

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals(fileName);
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected file name validation successful.", true);
					ATUReports.add("Selected file name validation successful.", true);
					Log.info("Selected file name validation successful.");
				} else {
					Reporter.log("Selected file name validation failed.", true);
					ATUReports.add("Selected file name validation failed.", true);
					Assert.fail("Selected file name validation failed.");
				}
				util.wait(time);
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				util.waitForPageToLoad();

				util.jclick(wdp.getAddNewPageIcon());
				Reporter.log("Add New Page is clicked", true);
				ATUReports.add("Add New Page is clicked", true);
				Log.info("Add New Page is clicked");
				util.wait(time);
				util.waitForPageToLoad();

				boolean addSecondNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
				if (addSecondNewPageWinPresent) {
					Reporter.log("Add new Page window is displayed", true);
					ATUReports.add("Add new Page window is displayed", true);
					Log.info("Add new Page window is displayed");

					String secondimagePath = System.getProperty("user.dir")	+ ExcelLib.getCellValue(xlpath, sheet, 29, 1);

					Reporter.log("Second img path " + secondimagePath, true);
					ATUReports.add("Second img path " + secondimagePath, true);
					Log.info("Second img path " + secondimagePath);

					wdp.getContentUploadField().sendKeys(secondimagePath);

					util.waitForElementEnabled(wdp.getSelectedFile());
					util.wait(time);

					Reporter.log("door Jpg file is uploaded", true);
					ATUReports.add("door Jpg file is uploaded", true);
					Log.info("door Jpg file is uploaded");

					// Validate document name in the add new page window

					boolean docNameInAddSecondNewPageWin = wdp.getSelectedFile().getText().equals(secondFileName);
					if (docNameInAddSecondNewPageWin) {
						Reporter.log("Selected file name validation successful.", true);
						ATUReports.add("Selected file name validation successful.", true);
						Log.info("Selected file name validation successful.");
					} else {
						Reporter.log("Selected file name validation failed.", true);
						ATUReports.add("Selected file name validation failed.", true);
						Assert.fail("Selected file name validation failed.");
					}
					util.wait(time);
					wdp.getAddNewPageUploadBtn().click();
					Reporter.log("Add New Page Upload Button is clicked", true);
					ATUReports.add("Add New Page Upload Button is clicked", true);
					Log.info("Add New Page Upload Button is clicked");
					util.waitForPageToLoad();
					util.wait(time);

					util.jclick(wdp.getAddNewPageIcon());
					Reporter.log("Add New Page is clicked", true);
					ATUReports.add("Add New Page is clicked", true);
					Log.info("Add New Page is clicked");
					util.wait(time);
					util.waitForPageToLoad();

					boolean addThirdNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
					if (addThirdNewPageWinPresent) {
						Reporter.log("Add Third new Page window is displayed", true);
						ATUReports.add("Add Third new Page window is displayed", true);
						Log.info("Add Third new Page window is displayed");

						String thirdImagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 54, 1);

						Reporter.log("Third img path " + thirdImagePath, true);
						ATUReports.add("Third img path " + thirdImagePath, true);
						Log.info("Third img path " + thirdImagePath);

						wdp.getContentUploadField().sendKeys(thirdImagePath);

						util.waitForElementEnabled(wdp.getSelectedFile());
						util.wait(time);

						Reporter.log("Unisys jpg file is uploaded", true);
						ATUReports.add("Unisys jpg file is uploaded", true);
						Log.info("Unisys jpg file is uploaded");

						// Validate document name in the add new page window

						boolean docNameInAddThirdNewPageWin = wdp.getSelectedFile().getText().equals(thirdFileName);
						if (docNameInAddThirdNewPageWin) {
							Reporter.log("Selected file name validation successful.", true);
							ATUReports.add("Selected file name validation successful.", true);
							Log.info("Selected file name validation successful.");
						} else {
							Reporter.log("Selected file name validation failed.", true);
							ATUReports.add("Selected file name validation failed.", true);
							Assert.fail("Selected file name validation failed.");
						}
						util.wait(time);
						wdp.getAddNewPageUploadBtn().click();
						Reporter.log("Add New Page Upload Button is clicked", true);
						ATUReports.add("Add New Page Upload Button is clicked", true);
						Log.info("Add New Page Upload Button is clicked");
						util.waitForPageToLoad();
					} else {
						Reporter.log("Add Third new Page window is NOT displayed", true);
						ATUReports.add("Add Third new Page window is NOT displayed", LogAs.FAILED,
								new CaptureScreen(ScreenshotOf.DESKTOP));
						Assert.fail("Add Third new Page window is NOT displayed");
					}
				} else {
					Reporter.log("Add Second new Page window is NOT displayed", true);
					ATUReports.add("Add Second new Page window is NOT displayed", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Add Second new Page window is NOT displayed");
				}

				util.jclick(wdp.getSecondPageCheckbox());
				util.wait(time);

				util.jclick(wdp.getThirdPageCheckbox());
				Reporter.log("Second and Third page Checkbox is clicked", true);
				ATUReports.add("Second and Third page Checkbox is clicked", true);
				Log.info("Second and Third page Checkbox is clicked");
				util.wait(time);

				wdp.getSplitIcon().click();
				Reporter.log("Split Pages Icon is clicked", true);
				ATUReports.add("Split Pages Icon is clicked", true);
				Log.info("Split Pages Icon is clicked");
				util.waitForPageToLoad();
				util.wait(time);

				boolean splitPageWindowPresent = util.verifyObjectPresentReturnsBool(wdp.getProceedButton());
				if (splitPageWindowPresent) {
					Reporter.log("Split workitem window is displayed", true);
					ATUReports.add("Split workitem window is displayed", true);
					Log.info("Split workitem window is displayed");
					ATUReports.add("Verify Split page in workitem using shortkut key", "",
							"Split workitem window should be displayed", "Split workitem window is displayed", true);
				} else {
					Reporter.log("Split workitem window is not displayed", true);
					ATUReports.add("Split workitem window is not displayed", true);
					Assert.fail("Split workitem window is not displayed");
				}

				wdp.getProceedButton().click();
				util.waitForElementPresent(wdp.getSplitPageWindow());

				wdp.getSplitWitemNameTextbox().clear();
				wdp.getSplitWitemNameTextbox().sendKeys(newWitem);

				wdp.getSplitButton().click();
				ATUReports.add("Split button is clicked", true);
				util.waitForPageToLoad();
				util.wait(time);

				String newSpliwtItem1 = newWitem + "_1";
				String newSpliwtItem2 = newWitem + "_2";

				int contentListRemain = wdp.getContentPageNo();
				if (contentListRemain == 1) {
					Reporter.log("Pages are removed from 2nd page and only first page is remaining ", true);
					ATUReports.add("Pages are removed from 2nd page and only first page is remaining", true);
					Log.info("Pages are removed from 2nd page and only first page is remaining");
				} else {
					Reporter.log("Pages are not removed from 2nd page ", true);
					ATUReports.add("Pages are not removed from 2nd page ", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Document split by adding multiple page test failed");
				}

				wdp.getCloseWorkitemIcon(workitem).click();
				util.waitForElementEnabled(cwp.getCreateWorkitem_btn());
				util.wait(time);

				cwp.searchByNameInWorkitemList(newSpliwtItem1);
				cwp.getWorkItemName(newSpliwtItem1).click();
				util.waitForPageToLoad();
				util.wait(time);

				int contentListSplitted = wdp.getContentPageNo();
				if (contentListSplitted == 1) {
					Reporter.log("Pages are splitted from 2nd page ", true);
					ATUReports.add("Verify document Split by adding multiple pages and same class test is successfull",
							true);

				} else {
					Reporter.log("Pages are not splitted from 2nd page ", true);
					ATUReports.add("Pages are not splitted from 2nd page ", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Document split by adding multiple pages and same class test failed");
				}

				wdp.getCloseWorkitemIcon(newSpliwtItem1).click();
				util.waitForElementEnabled(cwp.getCreateWorkitem_btn());
				util.wait(time);

				cwp.searchByNameInWorkitemList(newSpliwtItem2);
				cwp.getWorkItemName(newSpliwtItem2).click();
				util.waitForPageToLoad();
				util.wait(time);

				int contentListInSecondSplitted = wdp.getContentPageNo();
				if (contentListInSecondSplitted == 1) {
					Reporter.log("Pages are splitted from 3rd page ", true);
					ATUReports.add("Verify document Split by adding form fields and multiple pages test is successfull",
							true);

				} else {
					Reporter.log("Pages are not splitted from 3rd page ", true);
					ATUReports.add("Pages are not splitted from 3rd page ", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Document split by adding multiple page test failed");
				}


				hp.getWorkItemTab().click();
				Reporter.log("Workitem tab is clicked",true);
				ATUReports.add("Workitem tab is clicked",true);
				Log.info("Workitem tab is clicked");
				util.waitForPageToLoad();
				hp.searchByNameInWorkitemTab(workitem);
				Reporter.log("Searching By Workitem Name In Workitem tab",true);
				ATUReports.add("Searching By Workitem Name In Workitem tab",true);
				Log.info("Searching By Workitem Name In Workitem tab");

				boolean workitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
				if(workitemPresence){
					Reporter.log("Workitem is present in grid",true);
					ATUReports.add("Workitem is present in grid",true);
					Log.info("Workitem is present in grid");
					ATUReports.add("Verify Document Workitem Retrieve From Inbox Different Class Document Duplicate Test", "","Document Duplicate of Document Workitem Retrieved From Inbox with Different Class should be displayed",
							"Document Duplicate of Document Workitem Retrieved From Inbox with Different Class is displayed", true);
				}else{
					Reporter.log("Workitem is not present in grid",true);
					ATUReports.add("Workitem is not present in grid",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Workitem is not present in grid ");
				}

				hp.getSearchFieldInWorkitemTab().sendKeys(Keys.ENTER);
				util.wait(time);

				cwp.getCheckBoxWorkItemName(workitem).click();
				Reporter.log("Workitem to be deleted is clicked", true);
				ATUReports.add("Workitem to be deleted is clicked", true);
				Log.info("Workitem to be deleted is clicked");

				util.wait(time);

				cwp.getDeleteWorkitemBtn().click();
				util.wait(time);

				boolean deleteWIWinPresent = util.verifyObjectPresentReturnsBool(cwp.getDeleteWItemWin());
				if (deleteWIWinPresent) {
					Reporter.log("Delete Workitem window is present", true);
					ATUReports.add("Delete Workitem window is present.", true);
					Log.info("Delete Workitem window is present.");
				} else {
					Reporter.log("Delete Workitem window box not present", true);
					ATUReports.add("Delete Workitem window box not present", true);
					Assert.fail("Delete Workitem window box not present ");
				}

				/* perform the desk delete
					if (util.verifyObjectPresentReturnsBool(cwp.getDeskDeleteRadioBtn())) {
						cwp.getDeskDeleteRadioBtn().click();
						Reporter.log("Delete Radio Button is clicked", true);
					}*/
				util.wait(time);
				cwp.getDeleteWItemAccept().click();
				Reporter.log("Delete WotkItem Accept Button is clicked", true);
				ATUReports.add("Delete WotkItem Accept Button is clicked", true);
				Log.info("Delete WotkItem Accept Button is clicked");

				util.wait(time);
				Reporter.log("Workitem is Deleted Successfully.", true);
				ATUReports.add("Workitem is Deleted Successfully.", true);
				Log.info("Workitem is Deleted Successfully.");

				util.waitForPageToLoad();
				/*cwp.searchByNameInWorkitemList(workitem);
					util.wait(time);

					WebElement deletedWorkitem = cwp.getWorkItemName(workitem);
					boolean deletedWItemPresent = util.verifyObjectPresentReturnsBool(deletedWorkitem);
					if(deletedWItemPresent) {
						Reporter.log("WorkItem is not deleted", true);
						ATUReports.add("WorkItem is not deleted", true);
						Log.info("WorkItem is not deleted");
						Assert.fail("WorkItem is not deleted");
					} else {
						Reporter.log("workItem is deleted successfully", true);
						ATUReports.add("workItem is deleted successfully", true);
						Log.info("workItem is deleted successfully");
					}*/				
			}

			else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Add new Page window is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("PerformDocumentSplitAndDeletionOfDocumentWorkitemTest is failed", true);
			ATUReports.add("PerformDocumentSplitAndDeletionOfDocumentWorkitemTest is failed", true);
			Assert.fail("PerformDocumentSplitAndDeletionOfDocumentWorkitemTest is failed");
		} finally {
			Log.endTestCase("INFO_12183_PerformDocumentSplitAndDeletionOfDocumentWorkitemTest");
		}
	}
}