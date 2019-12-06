package apd.infoimage.iwm.tests;

import java.util.List;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author PradhanJ
 * INFO_2208
 * This class will validate that OBJECT_ID and OBJECT cannot be add as column in the search grid
 */
public class INFO_2208 extends SuperClassIWM{
	
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
	@Test(groups={"UserPreference"})
	public void testObjectANdObjectIdNotAvailable()
	{
		int time=5000;		
		try
		{
			Log.startTestCase("INFO_2208_UserPreference_ObjAndObjIdNotAvailable");
			
			ATUReports.setTestCaseReqCoverage("This Scenario INFO-2208  is for Verifying OBJECT_ID and OBJECT "
					+ "cannot be add as column in the search grid");
			ATUReports.setAuthorInfo("Jayashri","MAY-2018","0.3");
			
			Reporter.log("Navigate to user preference tab.", true);
			ATUReports.add("Navigate to user preference tab.",true);
			hp.clickUserPreferenceTab();

			util.wait(time);
			List<WebElement> avlFields=Driver.driver.findElements(By.xpath("(//select[contains(@id,'availableValues')])[1]/option"));

			int totalNoFields=avlFields.size();

			util.wait(time);
			int c=0;
			for (int i=0; i<=totalNoFields-1;i++)
			{
				String field=avlFields.get(i).getText();
				
				if((field.equalsIgnoreCase("OBJECT"))||(field.equalsIgnoreCase("OBJECT ID")))
				{
					Reporter.log("OBJECT or OBJECT ID is present in the available fields", true);
					ATUReports.add("OBJECT or OBJECT ID is present in the available fields", LogAs.FAILED	,new CaptureScreen(ScreenshotOf.DESKTOP));
					Assert.fail("Object or object id in available fields validatio failed");
					c++;
				}
				else
				{
					
				}
				
			}
			if(c==0)
			{
				Reporter.log("object or Object Id is not present in available fields", true);
				ATUReports.add("validate that OBJECT ID and OBJECT not present in available fields", "", 
						"object or Object Id should not be present in available fields",
						"object or Object Id is not present in available fields", true);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("OBJECT or OBJECT ID is present in the available fields", true);
			Assert.fail("validate that OBJECT ID and OBJECT not present in available fields Test failed");
			
		}
		finally {
			Log.endTestCase("INFO_2208_UserPreference_ObjAndObjIdNotAvailable");
		}
	}
	
}
