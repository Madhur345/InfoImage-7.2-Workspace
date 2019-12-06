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
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

/**
 * @author SumanGaK - 10-Nov-2017
 * INFO_5114
 * This class verifies User was able to click on different languages icons. The Liferay portal containing IWM is displayed in different languages.
 */
public class INFO_5114 extends SuperClassIWM{

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
	@Test(enabled = true,priority=1,groups={"Workitem"})
	public void testVerifyTheMultiLingual(){
		System.out.println("I entered apd.infoimage.iwm.tests.INFO_5114_MultiLingualTest");	

		ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "Verify The MultiLingual");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("Verify The MultiLingual");

			Reporter.log("MultiLingualTest : testVerifyTheMultiLingual()",true);
			ATUReports.add("MultiLingualTest : testVerifyTheMultiLingual()",true);
			Log.info("MultiLingualTest : testVerifyTheMultiLingual()");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);

			cwp.getFrench().click();
			util.waitForPageToLoad();
			util.wait(time);
			util.wait(time);
			util.wait(time);
			util.wait(time);
			
			boolean frenchSearchTabPresence = util.verifyObjectPresentReturnsBool(hp.getFrenchSearchTab());
			if(frenchSearchTabPresence)
			{
				Reporter.log("User was able to click on French language icon. The Liferay portal containing IWM is now displayed in French language",true);
				ATUReports.add("User was able to click on French language icon. The Liferay portal containing IWM is now displayed in French language",true);
				Log.info("User was able to click on French language icon. The Liferay portal containing IWM is now displayed in French language");
			}

			else
			{
				Reporter.log("The Liferay portal containing IWM is not displayed in French language",true);
				ATUReports.add("The Liferay portal containing IWM is not displayed in French language",true);
			}


			cwp.getSpanish().click();
			util.waitForPageToLoad();
			util.wait(time);
			util.wait(time);
			util.wait(time);
			util.wait(time);
			
			boolean spanishSearchTab = util.verifyObjectPresentReturnsBool(hp.getSpanishSearchTab());
			if(spanishSearchTab)
			{
				Reporter.log("User was able to click on Spanish language icon. The Liferay portal containing IWM is now displayed in Spanish language",true);
				ATUReports.add("User was able to click on Spanish language icon. The Liferay portal containing IWM is now displayed in Spanish language",true);
				Log.info("User was able to click on Spanish language icon. The Liferay portal containing IWM is now displayed in Spanish language");
			}
			else
			{
				Reporter.log("The Liferay portal containing IWM is not displayed in Spanish language",true);
				ATUReports.add("The Liferay portal containing IWM is not displayed in Spanish language",true);
			}

			cwp.getPortuguese().click();
			util.waitForPageToLoad();
			util.wait(time);
			util.wait(time);
			util.wait(time);
			util.wait(time);
			
			boolean portugueseInboxTab = util.verifyObjectPresentReturnsBool(hp.getPortugueseInboxTab());
			if(portugueseInboxTab)
			{
				Reporter.log("User was able to click on Portuguese language icon. The Liferay portal containing IWM is now displayed in Portuguese language",true);
				ATUReports.add("User was able to click on Portuguese language icon. The Liferay portal containing IWM is now displayed in Portuguese language",true);
				Log.info("User was able to click on Portuguese language icon. The Liferay portal containing IWM is now displayed in Portuguese language");
			}

			else
			{
				Reporter.log("The Liferay portal containing IWM is not displayed in Portuguese language",true);
				ATUReports.add("The Liferay portal containing IWM is not displayed in Portuguese language",true);
			}

			cwp.getDutch().click();				
			util.waitForPageToLoad();
			util.wait(time);
			util.wait(time);
			util.wait(time);
			util.wait(time);
			
			boolean dutchSearchTab = util.verifyObjectPresentReturnsBool(hp.getDutchSearchTab());
			if(dutchSearchTab)
			{
				Reporter.log("User was able to click on Dutch language icon. The Liferay portal containing IWM is now displayed in Dutch language",true);
				ATUReports.add("User was able to click on Dutch language icon. The Liferay portal containing IWM is now displayed in Dutch language",true);
				Log.info("User was able to click on Dutch language icon. The Liferay portal containing IWM is now displayed in Dutch language");
			}

			else
			{
				Reporter.log("The Liferay portal containing IWM is not displayed in Dutch language",true);
				ATUReports.add("The Liferay portal containing IWM is not displayed in Dutch language",true);
			}

			cwp.getEnglish().click();
			util.waitForPageToLoad();
			util.wait(time);
			util.wait(time);
			util.wait(time);
			util.wait(time);
			
			boolean searchTab= util.verifyObjectPresentReturnsBool(hp.getSearchTab());
			if(searchTab)
			{
				Reporter.log("User was able to click on English language icon. The Liferay portal containing IWM is now displayed in English language",true);
				ATUReports.add("User was able to click on English language icon. The Liferay portal containing IWM is now displayed in English language",true);
				Log.info("User was able to click on English language icon. The Liferay portal containing IWM is now displayed in English language");
			}

			else
			{
				Reporter.log("The Liferay portal containing IWM is not displayed in English language",true);
				ATUReports.add("The Liferay portal containing IWM is not displayed in English language",true);
			}				

			System.out.println("About to Exit apd.infoimage.iwm.tests.INFO_5114_MultiLingualTest");			

		}catch(Exception e){
			e.printStackTrace();
			Reporter.log("failed to execute Verify the Multi Lingual test",true);
			ATUReports.add("execute Verify the Multi Lingual test",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute Verify the Multi Lingual test");
		}
		finally
		{
			Log.endTestCase("Verify The MultiLingual");
		}
	}
}
