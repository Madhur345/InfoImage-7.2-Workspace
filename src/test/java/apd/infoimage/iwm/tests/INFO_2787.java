package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;
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

public class INFO_2787 extends SuperClassIWM{

	/**
	 * @author SumanGaK - 13-Nov-2017
	 * INFO_2787
	 * This class verify the Missing tool tips issue in IWM
	 */

	@BeforeMethod
	public void beforMethod(){
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod(){
		hp.logoutApp();
	}


	@SuppressWarnings("deprecation")
	@Test(enabled =true,priority=1,groups={"Inbox"})
	public void testMissingToolTips()
	{	
		System.out.println("I entered apd.infoimage.iwm.tests.INFO_2787_MissingToolTipsTest");	

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-2787  is To verify" + " Performing Missing Tool Tips");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");
		try{	
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_2787_MissingToolTipsTest");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			Reporter.log("MissingToolTipsTest : testMissingToolTips()",true);
			ATUReports.add("MissingToolTipsTest : testMissingToolTips()",true);
			Log.info("MissingToolTipsTest : testMissingToolTips()");

			hp.getInbox().click();

			Reporter.log("Clicked on Inbox",true);
			ATUReports.add("Clicked on Inbox",true);
			Log.info("Clicked on Inbox");

			util.wait(time);
			ip.getDataEntry().click();
			//ip.getCustom().click();
			util.wait(time);
			Actions actions=new Actions(Driver.driver);		 
			actions.moveToElement(ip.getRetrieveAndOpen()).perform();

			String toolTipText = ip.getRetrieveAndOpenButton().getAttribute("title");
			if(toolTipText!=null)
			{
				Reporter.log("Tool tip text of Retrieve And Open Button is visible",true);
				ATUReports.add("Tool tip text of Retrieve And Open Button is visible",true);
				Log.info("Tool tip text of Retrieve And Open Button is visible");
			}
			else
			{
				Reporter.log("Tool tip text of Retrieve And Open Button is not visible",true); 
				ATUReports.add("Tool tip text of Retrieve And Open Button is not visible",true); 
			}

			util.wait(time);
			Actions actions1=new Actions(Driver.driver);		 
			actions1.moveToElement(ip.getActionsBtn()).perform();

			String toolTipText1 = ip.getActionsBtn().getAttribute("title");
			if(toolTipText1!=null)
			{
				Reporter.log("Tool tip text of Actions Button is visible",true);
				ATUReports.add("Tool tip text of Actions Button is visible",true);
				Log.info("Tool tip text of Actions Button is visible");
			}
			else
			{
				Reporter.log("Tool tip text of Actions Button is not visible",true); 
			}
			util.wait(time);
			System.out.println("About to Exit apd.infoimage.iwm.tests.INFO_2787_MissingToolTipsTest");			

		}
		catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute MissingToolTips test",true);
			ATUReports.add("Performing Missing Tool Tips",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute MissingToolTips test");
			Log.error(e.getMessage());

		}
		finally {
			Log.endTestCase("INFO_2787_MissingToolTipsTest");
		}
	}
}
