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
 * @author SumanGaK - 3-Dec-2018
 * INFO_2784
 * This class verifies Rotate image right and Zoom slider Actions in IWM are displaying and actions are performed on currently loaded image
 */
public class INFO_2784 extends SuperClassIWM {

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
	public void testRotateImageRightAndZoomSlider() {

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-2784  is To verify" + " Performing Rotate Image Right And Zoom Slider");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");
		try {
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("testRotateImageRightAndZoomSlider");

			Reporter.log("RotateImageRightAndZoomSliderTest : testRotateImageRightAndZoomSlider()", true);
			ATUReports.add("RotateImageRightAndZoomSliderTest : testRotateImageRightAndZoomSlider()", true);
			Log.info("RotateImageRightAndZoomSliderTest : testRotateImageRightAndZoomSlider()");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String fileName = ExcelLib.getCellValue(xlpath, sheet, 26, 1);
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
			Log.info("Document: "+workitem+" has been created");

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView displayed", true);
			ATUReports.add("WorkItemDetailView displayed", true);
			Log.info("WorkItemDetailView displayed");
			util.wait(time);

			wdp.getContentField().click();
			Reporter.log("Content field is clicked", true);
			ATUReports.add("Content field is clicked", true);
			Log.info("Content field is clicked");
			util.wait(time);

			wdp.getAddNewPageIcon().click();
			util.waitForPageToLoad();
			util.wait(time);

			boolean addNewPageWinPresent = util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if (addNewPageWinPresent) {
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				String imagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath, sheet, 25, 1);
				Reporter.log("img path " + imagePath, true);
				ATUReports.add("img path " + imagePath, true);
				Log.info("img path " + imagePath);

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
					Assert.fail("Selected singlepage file name validation failed.");
				}
				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");
				util.waitForPageToLoad();

				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);

				int width = wdp.getWorkitemDimensions().getSize().getWidth();
				Reporter.log("width : " + width, true);
				ATUReports.add("width : " + width, true);
				Log.info("width : " + width);

				int height = wdp.getWorkitemDimensions().getSize().getHeight();
				Reporter.log("height : " + height, true);
				ATUReports.add("height : " + height, true);
				Log.info("height : " + height);

				wdp.getRotateImageRight().click();

				util.wait(time);
				util.waitForPageToLoad();
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);

				int rirwidth = wdp.getWorkitemDimensions().getSize().getWidth();
				Reporter.log("Rotated image right width : " + rirwidth, true);
				ATUReports.add("Rotated image right width : " + rirwidth, true);
				Log.info("Rotated image right width : " + rirwidth);

				int rirheight = wdp.getWorkitemDimensions().getSize().getHeight();
				Reporter.log("Rotated image right height : " + rirheight, true);
				ATUReports.add("Rotated image right height : " + rirheight, true);
				Log.info("Rotated image right height : " + rirheight);

				if (width == rirheight && height == rirwidth) {
					Reporter.log("Rotate image right Action in IWM is performed on currently loaded image.", true);
					ATUReports.add("Rotate image right Action in IWM is performed on currently loaded image", true);
					Log.info("Rotate image right Action in IWM is performed on currently loaded image");
				} else {
					Reporter.log("Rotate image right Action in IWM is not performed on currently loaded image.", true);
				}

				wdp.getRotateImageLeft().click();
				util.wait(time);
				util.waitForPageToLoad();
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				util.wait(time);
				wdp.getZoomIn().click();

				Reporter.log("Zoom In Button is clicked", true);
				ATUReports.add("Zoom In Button is clicked", true);
				Log.info("Zoom In Button is clicked");

				util.wait(time);
				int zinwidth = wdp.getWorkitemDimensions().getSize().getWidth();
				Reporter.log("Zoomed In width : " + zinwidth, true);
				ATUReports.add("Zoomed In width : " + zinwidth, true);
				Log.info("Zoomed In width : " + zinwidth);

				int zinheight = wdp.getWorkitemDimensions().getSize().getHeight();
				Reporter.log("Zoomed In height : " + zinheight, true);
				ATUReports.add("Zoomed In height : " + zinheight, true);
				Log.info("Zoomed In height : " + zinheight);

				String[] stylezinvals = wdp.getzoomTag().getAttribute("style").split(";");
				String zoominvalue = stylezinvals[1];
				String[] zinval = zoominvalue.split(":");

				float zif = Float.parseFloat(zinval[1]);
				System.out.println(zif);
				Reporter.log("Zoom In value is : " + zif, true);
				ATUReports.add("Zoom In value is : " + zif, true);
				Log.info("Zoom In value is : " + zif);

				wdp.getZoomIn().click();

				int zinswidth = wdp.getWorkitemDimensions().getSize().getWidth();
				Reporter.log("Zoomed In width : " + zinswidth, true);
				ATUReports.add("Zoomed In width : " + zinswidth, true);
				Log.info("Zoomed In width : " + zinswidth);

				int zinsheight = wdp.getWorkitemDimensions().getSize().getHeight();
				Reporter.log("Zoomed In height : " + zinsheight, true);
				ATUReports.add("Zoomed In height : " + zinsheight, true);
				Log.info("Zoomed In height : " + zinsheight);

				String[] stylezinsvals = wdp.getzoomTag().getAttribute("style").split(";");
				String zoominsvalue = stylezinsvals[1];
				String[] zinsval = zoominsvalue.split(":");

				float zisf = Float.parseFloat(zinsval[1]);

				if (zisf > zif) {
					Reporter.log("Image is getting Zoomed In", true);
					ATUReports.add("Image is getting Zoomed In", true);
					Log.info("Image is getting Zoomed In");
				}

				else {
					Reporter.log("Image is not getting Zoomed In", true);
				}

				util.wait(time);
				Reporter.log("Zoom out test ", true);
				ATUReports.add("Zoom out test ", true);
				Log.info("Zoom out test ");

				wdp.getZoomOut().click();
				Reporter.log("Zoom Out Button is clicked", true);
				ATUReports.add("Zoom Out Button is clicked", true);
				Log.info("Zoom Out Button is clicked");

				util.wait(time);
				int zoutwidth = wdp.getWorkitemDimensions().getSize().getWidth();
				Reporter.log("Zoomed Out width : " + zoutwidth, true);
				ATUReports.add("Zoomed Out width : " + zoutwidth, true);
				Log.info("Zoomed Out width : " + zoutwidth);

				int zoutheight = wdp.getWorkitemDimensions().getSize().getHeight();
				Reporter.log("Zoomed Out height : " + zoutheight, true);
				ATUReports.add("Zoomed Out height : " + zoutheight, true);
				Log.info("Zoomed Out height : " + zoutheight);

				String[] stylezoutvals = wdp.getzoomTag().getAttribute("style").split(";");
				String zoomoutvalue = stylezoutvals[1];

				String[] zoutval = zoomoutvalue.split(":");

				float zof = Float.parseFloat(zoutval[1]);

				System.out.println(zof);
				Reporter.log("Zoom Out value is : " + zof, true);
				ATUReports.add("Zoom Out value is : " + zof, true);
				Log.info("Zoom Out value is : " + zof);

				if (zof < zisf) {
					Reporter.log("Image is getting Zoomed Out", true);
					ATUReports.add("Image is getting Zoomed Out", true);
					Log.info("Image is getting Zoomed Out");
				}

				else {
					Reporter.log("Image is not getting Zoomed Out", true);
				}

			}

			else {
				Reporter.log("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute Rotate Image Right And Zoom Slider test", true);
			ATUReports.add("Performing Rotate Image Right And Zoom Slider test", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute Rotate Image Right And Zoom Slider test");
			Log.error(e.getMessage());
		} finally {
			Log.endTestCase("testRotateImageRightAndZoomSlider");
		}
	}
}
