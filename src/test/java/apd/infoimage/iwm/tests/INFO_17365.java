package apd.infoimage.iwm.tests;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
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
 * @author SumanGaK - 26-Nov-2018
 * INFO-17365
 * This class verify the mail functionality in the IWM Psers viewer.
 */
public class INFO_17365 extends SuperClassIWM{

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
	@Test(enabled = true,priority=1)
	public void testVerifyMailFunctionalityInIWMPsersViewer()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-16993 is for Verifying the mail functionality in the IWM Psers viewer.");
		ATUReports.setAuthorInfo("Suman","NOV-2018","0.3"); 
		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_17365_VerifyMailFunctionalityInIWMPsersViewerTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String imagePath = System.getProperty("user.dir") + ExcelLib.getCellValue(xlpath,sheet,25,1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);
			String newUrlFirstString=ExcelLib.getCellValue(xlpath,sheet,92,1);
			String newUrlSecondString=ExcelLib.getCellValue(xlpath,sheet,93,1);
			
			Reporter.log("Class Name : VerifyMailFunctionalityInIWMPsersViewerTest",true);
			ATUReports.add("Class Name : VerifyMailFunctionalityInIWMPsersViewerTest",true);
			Log.info("Class Name : VerifyMailFunctionalityInIWMPsersViewerTest");

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
			Reporter.log("Selection of Workitem",true);
			ATUReports.add("Selection of Workitem",true);
			Log.info("Selection of Workitem");

			cwp.getWorkItemName(workitem).click();
			util.wait(time);
			util.waitForPageToLoad();
			util.wait(time);

			wdp.getContentField().click();
			util.wait(time);
			wdp.getAddNewPageIcon().click();
			util.wait(time);
			boolean addNewPageWinPresent=util.verifyObjectPresentReturnsBool(wdp.getAddNewPageWin());
			if(addNewPageWinPresent)
			{
				Reporter.log("Add new Page window is displayed", true);
				ATUReports.add("Add new Page window is displayed", true);
				Log.info("Add new Page window is displayed");

				util.wait(time);
				util.wait(time);

				Reporter.log("img path "+imagePath,true);
				ATUReports.add("img path "+imagePath,true);
				Log.info("img path "+imagePath);

				util.wait(time);
				util.waitForPageToLoad();

				wdp.getContentUploadField().sendKeys(imagePath);
				util.waitForElementEnabled(wdp.getSelectedFile());
				util.wait(time);

				wdp.getAddNewPageUploadBtn().click();
				Reporter.log("Add New Page Upload Button is clicked", true);
				ATUReports.add("Add New Page Upload Button is clicked", true);
				Log.info("Add New Page Upload Button is clicked");

				util.waitForPageToLoad();				
				
				String urlDetailView = Driver.driver.getCurrentUrl();
				Reporter.log(urlDetailView,true);
				ATUReports.add(urlDetailView,true);
				Log.info(urlDetailView);

				int length=urlDetailView.length();
				System.out.println("Url Length: "+length);
				Reporter.log("Url Length: "+length,true);
				ATUReports.add("Url Length: "+length,true);
				Log.info("Url Length: "+length);

				
				int charPosition = length-15;
				System.out.println("charPosition : "+charPosition);
				Reporter.log("charPosition : "+charPosition,true);
				ATUReports.add("charPosition : "+charPosition,true);
				Log.info("charPosition : "+charPosition);				
				
				String objectId = urlDetailView.substring(charPosition);
				System.out.println("objectId : "+objectId);
				Reporter.log(objectId,true);
				ATUReports.add(objectId,true);
				Log.info(objectId);

				
				String urlFullView = newUrlFirstString + objectId + newUrlSecondString;

				System.out.println("urlFullView : "+urlFullView);
				Reporter.log(urlFullView,true);
				ATUReports.add(urlFullView,true);
				Log.info(urlFullView);

				Robot rb = new Robot();
				rb.keyPress(KeyEvent.VK_CONTROL);
				rb.keyPress(KeyEvent.VK_T);					
								
				util.wait(time);
				util.waitForPageToLoad();					
				
				ArrayList<String> tabs = new ArrayList<String> (Driver.driver.getWindowHandles());
				Driver.driver.switchTo().window(tabs.get(1)); 
				Driver.driver.get(urlFullView);
				
				wdp.isElementPresent(wdp.getEmailFile());

				if(wdp.isElementPresent(wdp.getEmailFile()))
				{
						Reporter.log("Mail Functionality In IWM Psers Viewer is Displayed",true);
						ATUReports.add("Mail Functionality In IWM Psers Viewer is Displayed",true);
						ATUReports.add("Performs verify the Mail Functionality In IWM Psers Viewer is Displayed", "","Mail Functionality In IWM Psers Viewer should be Displayed",
								"Mail Functionality In IWM Psers Viewer is Displayed", true);
					}
					else{
					Reporter.log("Mail Functionality In IWM Psers Viewer is not Displayed",true);
				}
				
				util.wait(time);
				util.waitForPageToLoad();					
				wdp.getFullViewSignoutButton().click();
				Reporter.log("Full View Signout Button is clicked", true);
				ATUReports.add("Full View Signout Button is clicked", true);
				Log.info("Full View Signout Button is clicked");
				
				Driver.driver.close();

				Driver.driver.switchTo().window(tabs.get(0));
				
				util.wait(time);
				util.waitForPageToLoad();
				
				
				rb.keyRelease(KeyEvent.VK_CONTROL);
				rb.keyRelease(KeyEvent.VK_T);				
			}
			else{
				Reporter.log("Add new Page in workitem window is not present",true);
				ATUReports.add("Add new Page in workitem window is not present",true);
				Assert.fail("Add new Page in workitem window is not present ");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("VerifyMailFunctionalityInIWMPsersViewerTest is failed.", true);
			ATUReports.add("VerifyMailFunctionalityInIWMPsersViewerTest is failed.",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("VerifyMailFunctionalityInIWMPsersViewerTest is failed.");
		}
	}
	
}
