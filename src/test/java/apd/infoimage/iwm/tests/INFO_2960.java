package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
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

/**
 * @author SumanGaK - 10-Nov-2017
 * INFO-2960
 * This class verifies User was able to change the dimensions of image. To Check if image is fit to width of the viewer or not
 */
public class INFO_2960 extends SuperClassIWM {

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
	@Test(enabled = true, priority = 1, groups = { "UploadFile" })
	public void testFitWidth() {

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-2960 verifies User was able to change the dimensions of image");
		ATUReports.setAuthorInfo("Suman", "APR-2018", "0.3");
		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_2960_FitWidthTest");

			Reporter.log("FitWidthTest : testFitWidth()", true);
			ATUReports.add("FitWidthTest : testFitWidth()", true);
			Log.info("FitWidthTest : testFitWidth()");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String fileName = ExcelLib.getCellValue(xlpath,sheet,26,1);
			int screenWidth = ExcelLib.getCellValueInt(xlpath,sheet,74,1);
			int screenHeight = ExcelLib.getCellValueInt(xlpath,sheet,75,1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

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

			util.wait(time);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");

			wdp.getContentField().click();
			wdp.getAddNewPageIcon().click();

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				util.wait(time);
				util.wait(time);				

				String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,25,1);
				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);
				Log.info("img path "+imagePath);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);


				// Validate document name in the add new page window

				boolean docNameInAddNewPageWin = wdp.getSelectedFile().getText().equals(fileName);
				if (docNameInAddNewPageWin) {
					Reporter.log("Selected singlepage file name validation successful.", true);
					ATUReports.add("Selected singlepage file name validation successful.", true);
					Log.info("Selected singlepage file name validation successful.");
				} else {
					Reporter.log("Selected singlepage file name validation failed.", true);
					ATUReports.add("Selected singlepage file name validation failed.", true);
					Assert.fail("Selected singlepage file name validation failed.");
				}
				util.wait(time);
				util.wait(time);
				wdp.getAddNewPageUploadBtn().click();
				util.waitForPageToLoad();
			}

			util.wait(time);
			util.wait(time);
			util.wait(time);
			util.wait(time);
			util.wait(time);
			util.wait(time);
			int width = wdp.getWorkitemDimensions().getSize().getWidth();
			ATUReports.add("width : " + width, true);
			Reporter.log("width : " + width, true);
			Log.info("width : " + width);

			int height = wdp.getWorkitemDimensions().getSize().getHeight();
			Reporter.log("height : " + height, true);
			ATUReports.add("height : " + height, true);
			Log.info("height : " + height);

			if (width > screenWidth) {
				wdp.getFitScreen().click();
				util.wait(time);
				int fitscreenwidth = wdp.getWorkitemDimensions().getSize().getWidth();
				Reporter.log("fitscreenwidth : " + fitscreenwidth, true);
				ATUReports.add("fitscreenwidth : " + fitscreenwidth, true);
				Log.info("fitscreenwidth : " + fitscreenwidth);

				int fitscreenheight = wdp.getWorkitemDimensions().getSize().getHeight();
				Reporter.log("fitscreenheight : " + fitscreenheight, true);
				ATUReports.add("fitscreenwidth : " + fitscreenwidth, true);
				Log.info("fitscreenwidth : " + fitscreenwidth);

				if (fitscreenwidth == screenWidth && fitscreenheight == screenHeight) {
					Reporter.log("Image is fit to screen", true);
					ATUReports.add("Image is fit to screen", true);
					Log.info("Image is fit to screen");
				} else {
					Reporter.log("Image is not fit to screen", true);
					ATUReports.add("Image is not fit to screen", true);
				}

				wdp.getFitWidth().click();
				util.wait(time);

				int fitwidthwidth = wdp.getWorkitemDimensions().getSize().getWidth();
				Reporter.log("fitwidthwidth : " + fitwidthwidth, true);
				ATUReports.add("fitwidthwidth : " + fitwidthwidth, true);
				Log.info("fitwidthwidth : " + fitwidthwidth);

				int fitwidthheight = wdp.getWorkitemDimensions().getSize().getHeight();
				Reporter.log("fitwidthheight : " + fitwidthheight, true);
				ATUReports.add("fitwidthheight : " + fitwidthheight, true);
				Log.info("fitwidthheight : " + fitwidthheight);

				if (fitwidthwidth != screenWidth) {
					Reporter.log("Image is not fit to width", true);
					ATUReports.add("Image is not fit to width", true);
					Log.info("Image is not fit to width");
				}

				else {
					Reporter.log("Image is fit to width", true);
					ATUReports.add("Image is fit to width", true);
				}

				wdp.getFitHeight().click();
				util.wait(time);

				int fitheightwidth = wdp.getWorkitemDimensions().getSize().getWidth();
				Reporter.log("fitheightwidth : " + fitheightwidth, true);
				ATUReports.add("fitheightwidth : " + fitheightwidth, true);
				Log.info("fitheightwidth : " + fitheightwidth);

				int fitheightheight = wdp.getWorkitemDimensions().getSize().getHeight();
				Reporter.log("fitheightheight : " + fitheightheight, true);
				ATUReports.add("fitheightheight : " + fitheightheight, true);
				Log.info("fitheightheight : " + fitheightheight);

				if (fitheightheight != screenHeight) {
					Reporter.log("Image is not fit to Height", true);
					ATUReports.add("Image is not fit to Height", true);
					Log.info("Image is not fit to Height");
				}

				else {
					Reporter.log("Image is fit to Height", true);
					ATUReports.add("Image is fit to Height", true);
				}

				wdp.getFitWidth().click();
				util.wait(time);
				int fitwidthwidth2 = wdp.getWorkitemDimensions().getSize().getWidth();
				Reporter.log("fitwidthwidth : " + fitwidthwidth2, true);
				ATUReports.add("fitwidthwidth : " + fitwidthwidth2, true);
				Log.info("fitwidthwidth : " + fitwidthwidth2);

				int fitwidthheight2 = wdp.getWorkitemDimensions().getSize().getHeight();
				Reporter.log("fitwidthheight : " + fitwidthheight2, true);
				ATUReports.add("fitwidthheight : " + fitwidthheight2, true);
				Log.info("fitwidthheight : " + fitwidthheight2);

				if (fitwidthwidth2 != screenWidth) {
					Reporter.log("Image is not fit to width", true);
					ATUReports.add("Image is not fit to width", true);
					Log.info("Image is not fit to width");
				}

				else {
					Reporter.log("Image is fit to width", true);
					ATUReports.add("Image is fit to width", true);
				}
			} else {
				Reporter.log("Please select an image whose width is more than iwm viewer inside content section", true);
				ATUReports.add("Please select an image whose width is more than iwm viewer inside content section", true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute Fit Width test", true);
			ATUReports.add("failed to execute Fit Width test", true);
			Assert.fail("failed to execute Fit Width test");
		} finally {
			Log.endTestCase("INFO_2960_FitWidthTest");
		}
	}
}
