package apd.infoimage.iwm.tests;

import java.io.File;

import org.testng.Assert;
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
 * @author PradhanJ This test method will add a single page PNG file to content
 *         of workitem and validate the same
 */
public class INFO_2957 extends SuperClassIWM {

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

	/**
	 * This test method will add a single page PNG file to content of workitem and
	 * validate the same
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 1, groups = { "UploadFile" })
	public void testAddPNGfileInContent() {
		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		ATUReports.setTestCaseReqCoverage(
				"This Scenario INFO-2957 will add a single page PNG file to content of workitem and validate the same");
		ATUReports.setAuthorInfo("PradhanJ", "MAR-2018", "0.3");
		try {
			Log.startTestCase("testAddPNGfileInContent");
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked.", true);
			Thread.sleep(3000);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			Thread.sleep(2000);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();

				String imagePath = tifFilePath + "\\samplePng.PNG";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				Thread.sleep(3000);
				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("samplePng.PNG");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected PNG file name validation successfull.", true);
					ATUReports.add("Selected PNG file name validation successfull.", true);
				} else {
					Reporter.log("Selected PNG file name validation failed.", true);
					ATUReports.add("Selected PNG file name validation failed", true);
					Assert.fail("Selected PNG file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				// Validate the document is listed under content and no of pages in the document
				try {
					int contentList = wdp.getContentPageNo();
					if (contentList >= 1) {
						Reporter.log("1 Page is listed under content on the side bar ", true);
						ATUReports.add("1 Page is listed under content on the side bar ", true);
					} else {
						Reporter.log("1 Page is NOT listed under content on the side bar", true);
						ATUReports.add("1 Page is NOT listed under content on the side bar", true);
						Assert.fail("1 Page is NOT listed under content on the side bar");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Validation of  added  PNG file failed", true);
					ATUReports.add("Validation of  added  PNG file failed", true);
					ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Validation of  added  PNG file failed");
				}
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}

			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Adding PNG file  to Workitem test failed", true);
			ATUReports.add("Adding PNG file  to Workitem test failed", true);
			ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Adding PNG file  to Workitem test failed");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("testAddPNGfileInContent");
		}
	}

	/**
	 * This test method will add a single page JPG file to content of workitem and
	 * validate the same
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 2, groups = { "UploadFile" })
	public void testAddJPGfileInContent() {
		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");

		ATUReports
				.setTestCaseReqCoverage("will add a single page JPG file to content of workitem and validate the same");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		try {
			Log.startTestCase("testAddJPGfileInContent");

			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			Thread.sleep(3000);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			Thread.sleep(2000);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();

				String imagePath = tifFilePath + "\\window.jpg";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				Thread.sleep(3000);

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("window.jpg");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected JPG file name validation successfull.", true);
					ATUReports.add("Selected JPG file name validation successfull.", true);
				} else {
					Reporter.log("Selected JPG file name validation failed.", true);
					ATUReports.add("Selected JPG file name validation failed.", true);
					Assert.fail("Selected JPG file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				// Validate the document is listed under content and no of pages in the document
				try {
					int contentList = wdp.getContentPageNo();
					if (contentList >= 1) {
						Reporter.log("1 Page is listed under content on the side bar ", true);
						ATUReports.add("1 Page is listed under content on the side bar ", true);
					} else {
						Reporter.log("1 Page is NOT listed under content on the side bar", true);
						ATUReports.add("1 Page is NOT listed under content on the side bar", true);
						Assert.fail("1 Page is NOT listed under content on the side bar");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Validation of  added  JPG file failed", true);
					ATUReports.add("Validation of  added  JPG file failed", true);
					Assert.fail("Validation of  added  JPG file failed");
					ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					Log.error(e1.getMessage());

				}
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}

			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Adding JPG file  to Workitem test failed", true);
			ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Adding JPG file  to Workitem test failed");
			Log.error(e.getMessage());
		}

		finally {
			Log.endTestCase("testAddJPGfileInContent");
		}

	}

	/**
	 * This test method will add a JPEG file to content of workitem and validate the
	 * same
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 3, groups = { "UploadFile" })
	public void testAddJPEGfileInContent() {
		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		ATUReports.setTestCaseReqCoverage(
				"This Scenario will add a JPEG file to content of workitem and validate the same");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		try {
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			Thread.sleep(3000);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			Thread.sleep(2000);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();

				String imagePath = tifFilePath + "\\what.jpeg";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				Thread.sleep(3000);

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("what.jpeg");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected JPEG file name validation successfull.", true);
					ATUReports.add("Selected JPEG file name validation successfull.", true);
				} else {
					Reporter.log("Selected JPEG file name validation failed.", true);
					ATUReports.add("Selected JPEG file name validation failed.", true);
					Assert.fail("Selected JPEG file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				// Validate the document is listed under content and no of pages in the document
				try {
					int contentList = wdp.getContentPageNo();
					if (contentList >= 1) {
						Reporter.log("1 Page is listed under content on the side bar ", true);
						ATUReports.add("1 Page is listed under content on the side bar ", true);
					} else {
						Reporter.log("1 Page is NOT listed under content on the side bar", true);
						ATUReports.add("1 Page is NOT listed under content on the side bar", true);
						Assert.fail("1 Page is NOT listed under content on the side bar");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Validation of  added  JPEG file failed", true);
					ATUReports.add("Validation of  added  JPEG file failed", true);
					ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Validation of  added  JPEG file failed");
					Log.error(e1.getMessage());

				}
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}

			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Adding JPEG file  to Workitem test failed", true);
			ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Adding JPEG file  to Workitem test failed");
			Log.error(e.getMessage());

		}
	}

	/**
	 * This test method will add a PDF file to content of workitem and validate the
	 * same
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 4, groups = { "UploadFile" })
	public void testAddPDFfileInContent() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario will add a PDF file to content of workitem and validate the same");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		try {
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			Thread.sleep(3000);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			Thread.sleep(2000);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();

				String imagePath = tifFilePath + "\\SampleDoc.pdf";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				Thread.sleep(3000);

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("SampleDoc.pdf");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected PDF file name validation successfull.", true);
					ATUReports.add("Selected PDF file name validation successfull.", true);
				} else {
					Reporter.log("Selected PDF file name validation failed.", true);
					Assert.fail("Selected PDF file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				// Validate the document is listed under content and no of pages in the document
				try {
					int contentList = wdp.getContentPageNo();
					if (contentList >= 1) {
						Reporter.log("1 Page is listed under content on the side bar ", true);
						ATUReports.add("1 Page is listed under content on the side bar ", true);
					} else {
						Reporter.log("1 Page is NOT listed under content on the side bar", true);
						ATUReports.add("1 Page is NOT listed under content on the side bar", true);
						Assert.fail("1 Page is NOT listed under content on the side bar");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Validation of  added  PDF file failed", true);
					ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					ATUReports.add("Validation of  added  PDF file failed", true);
					Assert.fail("Validation of  added  PDF file failed");
					Log.error(e1.getMessage());

				}
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}

			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Adding PDF file  to Workitem test failed", true);
			ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Adding PDF file  to Workitem test failed");
			Log.error(e.getMessage());

		}

	}

	/**
	 * This test method will add a TXT file to content of workitem and validate the
	 * same
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = false, priority = 5, groups = { "UploadFile" })
	public void testAddTXTfileInContent() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario will add a TXT file to content of workitem and validate the same");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		try {
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			Thread.sleep(3000);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			Thread.sleep(2000);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();

				String imagePath = tifFilePath + "\\selenium_Sample.txt";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				Thread.sleep(3000);

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("selenium_Sample.txt");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected TXT file name validation successfull.", true);
					ATUReports.add("Selected TXT file name validation successfull.", true);
				} else {
					Reporter.log("Selected TXT file name validation failed.", true);
					ATUReports.add("Selected TXT file name validation failed.", true);
					Assert.fail("Selected TXT file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				// Validate the document is listed under content and no of pages in the document
				try {
					int contentList = wdp.getContentPageNo();
					if (contentList >= 1) {
						Reporter.log("1 Page is listed under content on the side bar ", true);
						ATUReports.add("1 Page is listed under content on the side bar ", true);
					} else {
						Reporter.log("1 Page is NOT listed under content on the side bar", true);
						ATUReports.add("1 Page is NOT listed under content on the side bar", true);
						Assert.fail("1 Page is NOT listed under content on the side bar");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Validation of  added  TXT file failed", true);
					ATUReports.add("Validation of  added  TXT file failed", true);
					ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Validation of  added  TXT file failed");
					Log.error(e1.getMessage());

				}
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}

			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Adding TXT file  to Workitem test failed", true);
			ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Adding TXT file  to Workitem test failed");
		}

	}

	/**
	 * This test method will add a Excel file to content of workitem and validate
	 * the same
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = false, priority = 6, groups = { "UploadFile" })
	public void testAddExcelFileInContent() {
		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		ATUReports.setTestCaseReqCoverage(
				"This Scenario will add a Excel file to content of workitem and validate the same");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		try {
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);

			wdp.getContentField().click();
			ATUReports.add("Content field is clicked", true);
			Thread.sleep(3000);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			Thread.sleep(2000);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();

				String imagePath = tifFilePath + "\\testdata.xlsx";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				Thread.sleep(3000);
				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("testdata.xlsx");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected Excel file name validation successfull.", true);
					ATUReports.add("Selected Excel file name validation successfull.", true);
				} else {
					Reporter.log("Selected Excel file name validation failed.", true);
					ATUReports.add("Selected Excel file name validation failed.", true);
					Assert.fail("Selected Excel file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				// Validate the document is listed under content and no of pages in the document
				try {
					int contentList = wdp.getContentPageNo();
					if (contentList >= 1) {
						Reporter.log("1 Page is listed under content on the side bar ", true);
						ATUReports.add("1 Page is listed under content on the side bar ", true);
					} else {
						Reporter.log("1 Page is NOT listed under content on the side bar", true);
						ATUReports.add("1 Page is NOT listed under content on the side bar", true);
						Assert.fail("1 Page is NOT listed under content on the side bar");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Validation of  added  Excel file failed", true);
					ATUReports.add("Validation of  added  Excel file failed", true);
					ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Validation of  added  Excel file failed");
					Log.error(e1.getMessage());

				}
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}

			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Adding Excel file  to Workitem test failed", true);
			ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Adding Excel file  to Workitem test failed");
		}

	}

	/**
	 * This test method will add a TIF file to content of workitem and validate the
	 * same
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 7, groups = { "UploadFile" })
	public void testAddTIFfileInContent() {
		ATUReports.setTestCaseReqCoverage(
				"This Scenario will add a TIF file to content of workitem and validate the same");
		ATUReports.setAuthorInfo("PradhanJ", "APR-2018", "0.3");
		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		try {
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			Thread.sleep(3000);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			Thread.sleep(2000);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();

				String imagePath = tifFilePath + "\\singlePage.tif";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				Thread.sleep(3000);

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("singlePage.tif");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected TIF file name validation successfull.", true);
					ATUReports.add("Selected TIF file name validation successfull.", true);
				} else {
					Reporter.log("Selected TIF file name validation failed.", true);
					ATUReports.add("Selected TIF file name validation failed.", true);
					Assert.fail("Selected TIF file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				// Validate the document is listed under content and no of pages in the document
				try {
					int contentList = wdp.getContentPageNo();
					if (contentList >= 1) {
						Reporter.log("1 Page is listed under content on the side bar ", true);
						ATUReports.add("1 Page is listed under content on the side bar ", true);
					} else {
						Reporter.log("1 Page is NOT listed under content on the side bar", true);
						ATUReports.add("1 Page is NOT listed under content on the side bar", true);
						Assert.fail("1 Page is NOT listed under content on the side bar");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Validation of  added  TIF file failed", true);
					ATUReports.add("Validation of  added  TIF file failed", true);
					ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Validation of  added  TIF file failed");
					Log.error(e1.getMessage());

				}
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}

			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Adding TIF file  to Workitem test failed", true);
			ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Adding TIF file  to Workitem test failed");
			Log.error(e.getMessage());

		}

	}

	/**
	 * This test method will add a BMP file to content of workitem and validate the
	 * same
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true, priority = 8, groups = { "UploadFile" })
	public void testAddBMPfileInContent() {
		String workitem = "wi" + util.getSysDate(0, "yyDDMMhhmmss");
		ATUReports.setTestCaseReqCoverage(
				"This Scenario will add a BMP file to content of workitem and validate the same");
		ATUReports.setAuthorInfo("Suman", "APR-2018", "0.3");
		try {
			cwp.CreateWorkitem(workitem, "archive", "Document");
			Reporter.log("CreateWorkitem operation performed", true);
			ATUReports.add("CreateWorkitem operation performed", true);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Thread.sleep(3000);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			Thread.sleep(3000);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			Thread.sleep(2000);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);

				String tifFilePath = new File("src\\test\\resources")
						.getAbsolutePath();

				String imagePath = tifFilePath + "\\sampleBmp.BMP";
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("Add new Page window is displayed", true);
				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				Thread.sleep(3000);

				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals("sampleBmp.BMP");
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected BMP file name validation successfull.", true);
					ATUReports.add("Selected BMP file name validation successfull.", true);
				} else {
					Reporter.log("Selected BMP file name validation failed.", true);
					ATUReports.add("Selected BMP file name validation failed.", true);
					Assert.fail("Selected BMP file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
				Thread.sleep(3000);
				// Validate the document is listed under content and no of pages in the document
				try {
					int contentList = wdp.getContentPageNo();
					if (contentList >= 1) {
						Reporter.log("1 Page is listed under content on the side bar ", true);
						ATUReports.add("1 Page is listed under content on the side bar ", true);
					} else {
						Reporter.log("1 Page is NOT listed under content on the side bar", true);
						ATUReports.add("1 Page is NOT listed under content on the side bar", true);
						Assert.fail("1 Page is NOT listed under content on the side bar");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
					Reporter.log("Validation of  added  BMP file failed", true);
					ATUReports.add("Validation of  added  BMP file failed", true);
					ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Validation of  added  BMP file failed");
					Log.error(e1.getMessage());

				}
			} else {
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}

			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Adding BMP file  to Workitem test failed", true);
			ATUReports.add("failed to execute test INFO_2957_AddContentAllTypes", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Adding BMP file  to Workitem test failed");
		} finally {
			Log.endTestCase("INFO_2957_AddContentAllTypes");
		}

	}
}
