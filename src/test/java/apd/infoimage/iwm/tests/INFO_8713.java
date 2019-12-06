package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.support.ui.Select;
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
 * INFO_8713
 * This class Perform Document split operation using multpile images with Different format , Same Class and File Document To Folder Workitem 
 */
public class INFO_8713 extends SuperClassIWM {

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
	public void testPerformingDocumentSplitMultipleImagesWithDifferentFormatSameClassFileDocumentToFolderWorkitem() {
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-8713 is for Performing Document split operation using multiple images with Different format , Same Class and File Document To Folder Workitem");
		ATUReports.setAuthorInfo("Suman","JUN-2018","0.3"); 

		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_8713_PerformingDocumentSplitMultipleImagesWithDifferentFormatSameClassFileDocumentToFolderWorkitemTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");
			String sheet2=prop.getProperty("sheet2");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String foldstr=ExcelLib.getCellValue(xlpath,sheet,24,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String folderWorkitem = foldstr+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 18, 1);
			String secondFileName = ExcelLib.getCellValue(xlpath, sheet, 57, 1);
			String thirdFileName = ExcelLib.getCellValue(xlpath, sheet, 60, 1);
			String newWitemStr = ExcelLib.getCellValue(xlpath, sheet2, 3, 1);
			String newWitem = newWitemStr + util.getSysDate(0, date);
			String folderWorkitemType = ExcelLib.getCellValue(xlpath,sheet,11,1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);			
			int numberOne = ExcelLib.getCellValueInt(xlpath,sheet,83,1);			
			int numberTwo = ExcelLib.getCellValueInt(xlpath,sheet,84,1);			

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

			Reporter.log("Document: "+workitem+" has been created",true);
			ATUReports.add("Document: "+workitem+" has been created",true);
			Log.info("Document: "+workitem+" has been created");

			Reporter.log("Workitem Type : "+folderWorkitemType,true);
			ATUReports.add("Workitem Type : "+folderWorkitemType,true);
			Log.info("Workitem Type : "+folderWorkitemType);

			cwp.CreateWorkitem(folderWorkitem,className,folderWorkitemType);
			Reporter.log("Folder: "+folderWorkitem+" has been created",true);
			ATUReports.add("Folder: "+folderWorkitem+" has been created",true);
			Log.info("Folder: "+folderWorkitem+" has been created");

			util.wait(time);

			cwp.getNameSearchTextbox().clear();			

			cwp.searchByNameInWorkitemList(workitem);			
			util.wait(time);

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

				String imagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 5, 1);

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
				//wdp.getSelectAllCheckbox1().click();

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

						String thirdImagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 59, 1);

						Reporter.log("Third img path "+thirdImagePath, true);
						ATUReports.add("Third img path "+thirdImagePath, true);
						Log.info("Third img path "+thirdImagePath);

						wdp.getContentUploadField().sendKeys(thirdImagePath);

						util.waitForElementEnabled(wdp.getSelectedFile());
						util.wait(time);

						Reporter.log("samplePng PNG file is uploaded", true);
						ATUReports.add("samplePng PNG file is uploaded", true);
						Log.info("samplePng PNG file is uploaded");

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
			}
			else
			{
				Reporter.log("Add new Page window is NOT displayed", true);
				Log.info("Add new Page window is NOT displayed");
				ATUReports.add("Add new Page window is NOT displayed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Add new Page window is NOT displayed");
			}

			wdp.getWorkItemsTab().click();
			Reporter.log("WorkItemsTab is clicked", true);
			ATUReports.add("WorkItemsTab is clicked", true);
			Log.info("WorkItemsTab is clicked");

			util.wait(time);

			cwp.getNameSearchTextbox().clear();			

			cwp.searchByNameInWorkitemList(workitem);			
			util.wait(time);

			cwp.getCheckBoxWorkItem(workitem).click();

			Reporter.log("Selected: "+workitem+" to file work item",true);
			ATUReports.add("Selected: "+workitem+" to file work item",true);
			Log.info("Selected: "+workitem+" to file work item");

			cwp.getFileworkitemBtn().click();
			boolean fileWorkitemDialogBoxPresence = util.verifyObjectPresentReturnsBool(cwp.getFileWorkItem_win());
			if(fileWorkitemDialogBoxPresence){
				Reporter.log("File Work item dialog box is open",true);
				ATUReports.add("File Work item dialog box is open",true);
				Log.info("File Work item dialog box is open");
			}else{
				Reporter.log("File Work item dialog box is not open",true);
				ATUReports.add("File Work item dialog box is not open",true);
				Log.info("File Work item dialog box is not open");
				Assert.fail("File Work item dialog box is not open");
			}

			Select sel = new Select(cwp.getSelectFolderDropDown());
			sel.selectByVisibleText(folderWorkitem);
			Reporter.log("Selected destination folder as: "+folderWorkitem,true);
			ATUReports.add("Selected destination folder as: "+folderWorkitem,true);
			Log.info("Selected destination folder as: "+folderWorkitem);

			util.wait(time);
			cwp.getFileWorkitemAcceptBtn().click();
			util.waitForPageToLoad();
			util.wait(time);
			cwp.getWorkItemName(folderWorkitem).click();
			util.waitForPageToLoad();

			wdp.getContentField().click();

			util.wait(time);
			boolean docWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if(docWorkitemPresence){
				Reporter.log(workitem +" Successfully filed in folder: "+folderWorkitem,true);
				ATUReports.add(workitem +" Successfully filed in folder: "+folderWorkitem,true);
				Log.info(workitem +" Successfully filed in folder: "+folderWorkitem);
			}else{
				Reporter.log(workitem +" failed to file in folder: "+folderWorkitem,true);
				ATUReports.add(workitem +" failed to file in folder: "+folderWorkitem,true);
				Log.info(workitem +" failed to file in folder: "+folderWorkitem);
				Assert.fail(workitem +" failed to file in folder: "+folderWorkitem);
			}

			util.waitForPageToLoad();

			Reporter.log("WorkItem is clicked",true);
			ATUReports.add("WorkItem is clicked",true);
			Log.info("WorkItem is clicked");
			Reporter.log("WorkItemDetailView displayed",true);
			ATUReports.add("WorkItemDetailView displayed",true);
			Log.info("WorkItemDetailView displayed");
			util.wait(time);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItem is opened",true);
			ATUReports.add("WorkItem is opened",true);
			Log.info("WorkItem is opened");	

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

			wdp.getSplitButton().click();
			Reporter.log("Split button is clicked", true);
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
			cwp.getWorkItemName(newSpliwtItem1).click();
			util.waitForPageToLoad();
			util.wait(time);

			int contentListSplitted = wdp.getContentPageNo();
			if (contentListSplitted == numberOne) {
				Reporter.log("Pages are splitted from 2nd page ", true);
				Log.info("Pages are splitted from 2nd page ");
				ATUReports.add("Verify document Split by adding multiple pages and Different class test is successfull",
						true);

			} else {
				Reporter.log("Pages are not splitted from 2nd page ", true);
				Log.info("Pages are not splitted from 2nd page ");
				ATUReports.add("Pages are not splitted from 2nd page ", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Document split by adding multiple pages and Different Class test failed");
			}

			wdp.getCloseWorkitemIcon(newSpliwtItem1).click();
			util.waitForElementEnabled(cwp.getCreateWorkitem_btn());
			util.wait(time);

			cwp.searchByNameInWorkitemList(newSpliwtItem2);
			cwp.getWorkItemName(newSpliwtItem2).click();
			util.waitForPageToLoad();
			util.wait(time);

			int contentListInSecondSplitted = wdp.getContentPageNo();
			if (contentListInSecondSplitted == numberTwo) {
				Reporter.log("Pages are splitted from 3rd page ", true);
				Log.info("Pages are splitted from 3rd page ");
				ATUReports.add("Verify document Split by adding form fields and multiple pages test is successful",true);

			} else {
				Reporter.log("Pages are not splitted from 3rd page ", true);
				Log.info("Pages are not splitted from 3rd page ");
				ATUReports.add("Pages are not splitted from 3rd page ", LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Document split by adding multiple page test failed");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify document Split by adding multiple Images With Different format and Different Class name test is failed",true);
			ATUReports.add("Verify document Split by adding multiple Images With Different format and Different Class name test is failed",true);
			Log.info("Verify document Split by adding multiple Images With Different format and Different Class name test is failed");
			Assert.fail("Verify document Split by adding multiple Images With Different format and Different Class name test is failed");
		} 
		finally {
			Log.endTestCase("INFO_8713_PerformingDocumentSplitMultipleImagesWithDifferentFormatSameClassFileDocumentToFolderWorkitem");
		}
	}
}