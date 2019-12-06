package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.ExcelLib;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 *  @author SumanGaK
 *  INFO-2792
 *  This class verifies Image viewer is not getting resized instead the image is zooming in and zooming out itself.
 */
public class INFO_2792 extends SuperClassIWM{

	@BeforeMethod
	public void setUp(){
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void tearDown(){
		hp.logoutApp();
	}


	@SuppressWarnings("deprecation")
	@Test(enabled = true,priority=1,groups={"UploadFile"})
	public void testZoomIn(){
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-2792 is for Verifying Image viewer is not getting resized instead the image is zooming in itself");
		ATUReports.setAuthorInfo("Suman","MAY-2017","0.3");

		try{

			Log.startTestCase("testZoomIn");

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
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("Class Name : ZoomTest & test method name : testZoomIn()",true);
			ATUReports.add("Class Name : ZoomTest & test method name : testZoomIn()",true);
			Log.info("Class Name : ZoomTest & test method name : testZoomIn()");

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

			util.wait(time);

			Reporter.log("Opening WorkItem",true);
			ATUReports.add("Opening WorkItem",true);
			Log.info("Opening WorkItem");

			util.wait(time);

			cwp.getWorkItemName(workitem).click();
			Reporter.log("WorkItemDetailView is displayed",true);
			ATUReports.add("WorkItemDetailView is displayed",true);
			Log.info("WorkItemDetailView is displayed");

			util.wait(time);

			wdp.getContentField().click();
			Reporter.log("Content Field is clicked",true);
			ATUReports.add("Content Field is clicked",true);
			Log.info("Content Field is clicked");

			wdp.getAddNewPageIcon().click();
			Reporter.log("Add New Page Icon is clicked",true);
			ATUReports.add("Add New Page Icon is clicked",true);
			Log.info("Add New Page Icon is clicked");

			boolean addNewPageWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if(addNewPageWinPresent)
			{
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				String imagePath = System.getProperty("user.dir")+ExcelLib.getCellValue(xlpath,sheet,25,1);
				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);
				Log.info("img path "+imagePath);

				wdp.getContentUploadField().sendKeys(imagePath);

				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				//Validate document name in the add new page window

				boolean docNameInAddNewPageWin=wdp.getSelectedFile().getText().equals(fileName);
				if(docNameInAddNewPageWin)
				{
					Reporter.log("Selected singlepage file name validation successful.", true);
					ATUReports.add("Selected singlepage file name validation successful.", true);
					Log.info("Selected singlepage file name validation successful.");
				}
				else
				{
					Reporter.log("Selected singlepage file name validation failed.", true);
					ATUReports.add("Selected singlepage file name validation failed.", true);
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
				int width=wdp.getWorkitemDimensions().getSize().getWidth();
				Reporter.log("width : "+width,true);
				ATUReports.add("width : "+width,true);
				Log.info("width : "+width);
				int height=wdp.getWorkitemDimensions().getSize().getHeight();
				Reporter.log("height : "+height,true);
				ATUReports.add("height : "+height,true);
				Log.info("height : "+height);

				util.wait(time);
				util.wait(time);
				wdp.getZoomIn().click();
				Reporter.log("Zoom In Button is clicked", true);
				ATUReports.add("Zoom In Button is clicked", true);
				Log.info("Zoom In Button is clicked");

				util.wait(time);
				int zinwidth=wdp.getWorkitemDimensions().getSize().getWidth();
				Reporter.log("Zoomed In width : "+zinwidth,true);
				ATUReports.add("Zoomed In width : "+zinwidth,true);
				Log.info("Zoomed In width : "+zinwidth);

				int zinheight=wdp.getWorkitemDimensions().getSize().getHeight();
				Reporter.log("Zoomed In height : "+zinheight,true);
				ATUReports.add("Zoomed In height : "+zinheight,true);
				Log.info("Zoomed In height : "+zinheight);

				if(width==zinwidth && height==zinheight)
				{
					Reporter.log("Image viewer is not getting resized.",true);
					ATUReports.add("Image viewer is not getting resized.",true);
					ATUReports.add("Verify Image viewer is not getting resized and Image is getting Zoomed In", "","Image getting Zoomed In should be displayed",
							"Image getting Zoomed In is displayed", true);
				}

				else
				{
					Reporter.log("Image viewer is getting resized.",true);
					ATUReports.add("Image viewer is getting resized.",true);
				}

				String[] stylezinvals=wdp.getzoomTag().getAttribute("style").split(";");
				String zoominvalue=stylezinvals[1];

				float zif = Float.parseFloat(zoominvalue.substring(7));
				System.out.println(zif);
				Reporter.log("Zoom In value is : "+zoominvalue.substring(7),true);
				ATUReports.add("Zoom In value is : "+zoominvalue.substring(7),true);
				Log.info("Zoom In value is : "+zoominvalue.substring(7));

				wdp.getZoomIn().click();
				String[] stylezinsvals=wdp.getzoomTag().getAttribute("style").split(";");
				String zoominsvalue=stylezinsvals[1];
				String[] zinval=zoominsvalue.split(":");

				float zisf = Float.parseFloat(zinval[1]);   

				if(zisf > zif)
				{
					Reporter.log("Image is getting Zoomed In",true);	
					ATUReports.add("Image is getting Zoomed In",true);
					Log.info("Image is getting Zoomed In");
				}

				else
				{
					Reporter.log("Image is not getting Zoomed In",true);
					ATUReports.add("Image is not getting Zoomed In",true);
				}	
			}

			else
			{
				Reporter.log("Add new Page window is NOT displayed", true);
				ATUReports.add("Add new Page window is NOT displayed", true);
				Assert.fail("Add new Page window is NOT displayed");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute Zoom In test",true);
			ATUReports.add("failed to execute Zoom In test",true);
			Assert.fail("failed to execute Zoom In test");

		}
		finally {
			Log.endTestCase("testZoomIn");
		}
	}
}

