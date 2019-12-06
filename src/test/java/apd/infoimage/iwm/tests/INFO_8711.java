package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
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
 * @author SumanGaK - 1-Jun-2018
 * INFO_8711
 * This class Perform Document split operation using multpile images with jpg format and different class for a document type of workitem 
 */
public class INFO_8711 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1,groups={"DocumentSplit"})
	public void testPerformingDocumentSplitMultipleImagesWithJpgFormatDifferentClassForDocumentWorkitem() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-8711 is for Performing Document split operation using multiple images with jpg format and Different class for a document type of workitem");
		ATUReports.setAuthorInfo("Suman","JUN-2018","0.3"); 

		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_8711_PerformingDocumentSplitMultipleImagesWithJPGFormatDifferentClassForDocumentWorkitemTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");
			String sheet2=prop.getProperty("sheet2");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 55, 1);
			String secondFileName = ExcelLib.getCellValue(xlpath, sheet, 57, 1);
			String thirdFileName = ExcelLib.getCellValue(xlpath, sheet, 56, 1);
			String newWitemStr = ExcelLib.getCellValue(xlpath, sheet2, 3, 1);
			String newWitem = newWitemStr + util.getSysDate(0, date);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);
			int numberOne = ExcelLib.getCellValueInt(xlpath,sheet,83,1);			

			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);
			cwp.CreateWorkitem(workitem,className,workitemType);
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");

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
					Reporter.log("Selected multipage file name validation successful.", true);
					ATUReports.add("Selected multipage file name validation successful.", true);
					Log.info("Selected multipage file name validation successful.");
				} else {
					Reporter.log("Selected multipage file name validation failed.", true);
					ATUReports.add("Selected multipage file name validation failed.", true);
					Log.info("Selected multipage file name validation failed.");
					Assert.fail("Selected multipage file name validation failed.");
				}
				util.wait(time);
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				util.waitForPageToLoad();

				wdp.getAddNewPageIcon().click();
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

					String secondimagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 29, 1);

					Reporter.log("Second img path "+secondimagePath, true);
					ATUReports.add("Second img path "+secondimagePath, true);
					Log.info("Second img path "+secondimagePath);

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
						Log.info("Selected file name validation failed.");
						Assert.fail("Selected file name validation failed.");
					}
					util.wait(time);
					wdp.getAddNewPageUploadBtn().click();
					Reporter.log("Add New Page Upload Button is clicked", true);
					ATUReports.add("Add New Page Upload Button is clicked", true);
					Log.info("Add New Page Upload Button is clicked");
					util.waitForPageToLoad();
					util.wait(time);

					wdp.getAddNewPageIcon().click();
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

						Reporter.log("Third img path "+thirdImagePath, true);
						ATUReports.add("Third img path "+thirdImagePath, true);
						Log.info("Third img path "+thirdImagePath);

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
							Log.info("Selected file name validation failed.");
							Assert.fail("Selected file name validation failed.");
						}
						util.wait(time);
						wdp.getAddNewPageUploadBtn().click();
						Reporter.log("Add New Page Upload Button is clicked", true);
						ATUReports.add("Add New Page Upload Button is clicked", true);
						Log.info("Add New Page Upload Button is clicked");
						util.waitForPageToLoad();
					}
					else
					{
						Reporter.log("Add Third new Page window is NOT displayed", true);
						Log.info("Add Third new Page window is NOT displayed");
						ATUReports.add("Add Third new Page window is NOT displayed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
						Assert.fail("Add Third new Page window is NOT displayed");
					}	
				}
				else
				{
					Reporter.log("Add Second new Page window is NOT displayed", true);
					Log.info("Add Second new Page window is NOT displayed");
					ATUReports.add("Add Second new Page window is NOT displayed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Add Second new Page window is NOT displayed");
				}


				wdp.getSecondPageCheckbox().click();
				util.wait(time);

				wdp.getThirdPageCheckbox().click();
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
					Log.info("Split workitem window is not displayed");
					Assert.fail("Split workitem window is not displayed");
				}

				wdp.getProceedButton().click();
				util.waitForElementPresent(wdp.getSplitPageWindow());

				wdp.getSplitWitemNameTextbox().clear();
				wdp.getSplitWitemNameTextbox().sendKeys(newWitem);

				util.wait(time);

				wdp.getDifferentClassOption().click();
				Reporter.log("Different Class Option is selected",true);
				ATUReports.add("Different Class Option is selected",true);
				Log.info("Different Class Option is selected");
				util.wait(time);

				wdp.getSplitButton().click();
				ATUReports.add("Split button is clicked", true);
				Log.info("Split button is clicked");
				util.waitForPageToLoad();
				util.wait(time);

				String newSpliwtItem1=newWitem+"_1";
				String newSpliwtItem2=newWitem+"_2";

				int contentListRemain = wdp.getContentPageNo();
				if (contentListRemain == numberOne) {
					Reporter.log("Pages are removed from 2nd page and only first page is remaining ", true);
					ATUReports.add("Pages are removed from 2nd page and only first page is remaining", true);
					Log.info("Pages are removed from 2nd page and only first page is remaining");
				} else {
					Reporter.log("Pages are not removed from 2nd page ", true);
					Log.info("Pages are not removed from 2nd page ");
					ATUReports.add("Pages are not removed from 2nd page ", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Document split by adding multiple page test failed");
				}

				wdp.getCloseWorkitemIcon(workitem).click();
				util.waitForElementEnabled(cwp.getCreateWorkitem_btn());
				util.wait(time);

				cwp.searchByNameInWorkitemList(newSpliwtItem1);
				cwp.getActionsBtn().click();
				Reporter.log("Actions Button is Clicked",true);
				ATUReports.add("Actions Button is Clicked",true);
				Log.info("Actions Button is Clicked");
				cwp.getActionsMetaData().click();

				Reporter.log("Meta Data is Clicked",true);
				ATUReports.add("Meta Data is Clicked",true);
				Log.info("Meta Data is Clicked");
				String newClass=cwp.getNewClassName().getText().substring(10);

				Reporter.log("New Class Name : "+newClass,true);
				ATUReports.add("New Class Name : "+newClass,true);
				Log.info("New Class Name : "+newClass);

				boolean newClassPresence = util.verifyObjectPresentReturnsBool(cwp.getWebElementNewClass(newClass));
				if(newClassPresence)
				{
					Reporter.log("New Class Name is in metadata screen in workitems page",true);
					ATUReports.add("New Class Name is in metadata screen in workitems page",true);
					Log.info("New Class Name is in metadata screen in workitems page");
				}			
				else
				{
					Reporter.log("New Class Name is not in metadata screen in workitems page",true);
					Log.info("New Class Name is not in metadata screen in workitems page");
					ATUReports.add("New Class Name is not in metadata screen in workitems page",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("New Class Name is not in metadata screen in workitems page");
				}

				cwp.getMetaDataDialogBoxCloseBtn(newSpliwtItem1).click();
				Reporter.log("Meta Data Dialog Box Close Button is clicked",true);
				ATUReports.add("Meta Data Dialog Box Close Button is clicked",true);
				Log.info("Meta Data Dialog Box Close Button is clicked");

				cwp.getNameSearchTextbox().clear();
				cwp.getWorkItemName(newSpliwtItem1).click();
				util.waitForPageToLoad();
				util.wait(time);

				int contentListSplitted = wdp.getContentPageNo();
				if (contentListSplitted == numberOne) {
					Reporter.log("Pages are splitted from 2nd page ", true);
					Log.info("Pages are splitted from 2nd page ");
					ATUReports.add("Verify document Split by adding multiple pages and same class test is successfull",
							true);

				} else {
					Reporter.log("Pages are not splitted from 2nd page ", true);
					Log.info("Pages are not splitted from 2nd page ");
					ATUReports.add("Pages are not splitted from 2nd page ", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Document split by adding multiple pages and same class test failed");
				}

				wdp.getCloseWorkitemIcon(newSpliwtItem1).click();
				util.waitForElementEnabled(cwp.getCreateWorkitem_btn());
				util.wait(time);

				cwp.searchByNameInWorkitemList(newSpliwtItem2);

				cwp.getActionsBtn().click();
				Reporter.log("Actions Button is Clicked",true);
				ATUReports.add("Actions Button is Clicked",true);
				Log.info("Actions Button is Clicked");
				cwp.getActionsMetaData().click();

				Reporter.log("Meta Data is Clicked",true);
				ATUReports.add("Meta Data is Clicked",true);
				Log.info("Meta Data is Clicked");
				String newClassNextWorkitem=cwp.getNewClassName().getText().substring(10);

				Reporter.log("New Class Name : "+newClassNextWorkitem,true);
				ATUReports.add("New Class Name : "+newClassNextWorkitem,true);
				Log.info("New Class Name : "+newClassNextWorkitem);

				boolean newClassNextWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWebElementNewClass(newClassNextWorkitem));
				if(newClassNextWorkitemPresence)
				{
					Reporter.log("New Class Name is in metadata screen in workitems page",true);
					ATUReports.add("New Class Name is in metadata screen in workitems page",true);
					Log.info("New Class Name is in metadata screen in workitems page");
				}			
				else
				{
					Reporter.log("New Class Name is not in metadata screen in workitems page",true);
					Log.info("New Class Name is not in metadata screen in workitems page");
					ATUReports.add("New Class Name is not in metadata screen in workitems page",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("New Class Name is not in metadata screen in workitems page");
				}

				cwp.getMetaDataDialogBoxCloseBtn(newSpliwtItem2).click();
				Reporter.log("Meta Data Dialog Box Close Button is clicked",true);
				ATUReports.add("Meta Data Dialog Box Close Button is clicked",true);
				Log.info("Meta Data Dialog Box Close Button is clicked");

				cwp.getNameSearchTextbox().clear();				
				cwp.getWorkItemName(newSpliwtItem2).click();
				util.waitForPageToLoad();
				util.wait(time);

				int contentListInSecondSplitted = wdp.getContentPageNo();
				if (contentListInSecondSplitted == numberOne) {
					Reporter.log("Pages are splitted from 3rd page ", true);
					Log.info("Pages are splitted from 3rd page ");
					ATUReports.add("Verify document Split by adding form fields and multiple pages test is successfull",
							true);

				} else {
					Reporter.log("Pages are not splitted from 3rd page ", true);
					Log.info("Pages are not splitted from 3rd page ");
					ATUReports.add("Pages are not splitted from 3rd page ", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Document split by adding multiple page test failed");
				}
			}
			else
			{
				Reporter.log("Add new Page window is NOT displayed", true);
				Log.info("Add new Page window is NOT displayed");
				ATUReports.add("Add new Page window is NOT displayed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Add new Page window is NOT displayed");
			}	


		}
		catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify document Split by adding multiple Images With Jpg format and different class name test is failed",true);
			ATUReports.add("Verify document Split by adding multiple Images With Jpg format and different class name test is failed",true);
			Assert.fail("Verify document Split by adding multiple Images With Jpg format and different class name test is failed");
		} 
		finally {
			Log.endTestCase("INFO_8711_PerformingDocumentSplitMultipleImagesWithJpgFormatDifferentClassForDocumentWorkitem");
		}
	}

}