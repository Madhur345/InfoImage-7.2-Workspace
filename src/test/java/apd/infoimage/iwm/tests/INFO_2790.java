package apd.infoimage.iwm.tests;

import org.testng.Assert;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.Keys;
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
 * @author SumanGaK - 14-Nov-2017
 *
 */
public class INFO_2790 extends SuperClassIWM{

	@BeforeMethod
	public void beforMethod(){
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName1", "password1", "domain1", "role1");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod(){
		hp.logoutApp();
	}


	/**
	 * This test method verify the Use Of Back Space in Date Field in IWM
	 * @author SumanGaK
	 */
	@SuppressWarnings("deprecation")
	@Test(enabled = true,priority=1,groups={"Inbox"})
	public void testVerifyTheUseOfBackSpaceInDateField(){
		System.out.println("I entered apd.infoimage.iwm.tests.INFO_2790_UseOfBackSpaceInDateFieldTest");	
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-2790  is To verify" + " Verify The Use Of Back Space In Date Field");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");
		try{
			Log.startTestCase("INFO_2790_UseOfBackSpaceInDateFieldTest");

			Reporter.log("UseOfBackSpaceInDateFieldTest : testVerifyTheUseOfBackSpaceInDateField()",true);
			ATUReports.add("UseOfBackSpaceInDateFieldTest : testVerifyTheUseOfBackSpaceInDateField()",true);
			Log.info("UseOfBackSpaceInDateFieldTest : testVerifyTheUseOfBackSpaceInDateField()");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			int time = ExcelLib.getCellValueInt(xlpath, sheet, 58, 1);


			Reporter.log("Workitem : " + workitem, true);
			ATUReports.add("Workitem : " + workitem, true);
			Log.info("Workitem : " + workitem);
			Reporter.log("Class Name : " + className, true);
			ATUReports.add("Class Name : " + className, true);
			Log.info("Class Name : " + className);
			Reporter.log("Workitem Type : " + workitemType, true);
			ATUReports.add("Workitem Type : " + workitemType, true);
			Log.info("Workitem Type : " + workitemType);

			Reporter.log("Creation of Workitem", true);
			ATUReports.add("Creation of Workitem", true);
			Log.info("Creation of Workitem");

			cwp.CreateWorkitem(workitem, className, workitemType);

			cwp.getWorkItemName(workitem).click();

			Reporter.log("WorkItemDetailView displayed",true);
			ATUReports.add("WorkItemDetailView displayed",true);
			Log.info("WorkItemDetailView displayed");

			util.wait(time);

			wdp.getFormfields_win().click();
			util.wait(time);

			wdp.getDate().click();
			util.wait(time);
			wdp.getDate().sendKeys("2017-06-06");
			util.wait(time);

			for(int i=1;i<=10;i++)
			{
				wdp.getDate().sendKeys(Keys.BACK_SPACE);
			}

			Reporter.log(wdp.getDate().getText(),true);

			if(wdp.getDate().getText().isEmpty())						
			{
				Reporter.log("Back Space in Date Field is verified",true);
				ATUReports.add("Back Space in Date Field is verified",true);
				Log.info("Back Space in Date Field is verified");
			}

			else
			{
				Reporter.log("Back Space in Date Field is not verified",true);
				ATUReports.add("Back Space in Date Field is not verified",true);
			}				
			util.wait(time);
			wdp.getDate().sendKeys("2017-10-11");
			util.wait(time);
			wdp.getIdcode_TF().sendKeys("Unisys");
			wdp.getInvoiceno_TF().sendKeys("1");
			wdp.getUpdate_btn().click();

			if(wdp.getDate().getText()!=null)
			{
				Reporter.log("Date fields are allowed to edit the content",true);
				ATUReports.add("Date fields are allowed to edit the content",true);
				Log.info("Date fields are allowed to edit the content");
			}

			else
			{
				Reporter.log("Date fields are not allowed to edit the content.",true);
				ATUReports.add("Date fields are not allowed to edit the content.",true);
			}
			util.wait(time);
			util.wait(time);
			System.out.println("About to Exit apd.infoimage.iwm.tests.INFO_2790_UseOfBackSpaceInDateFieldTest");			
		}catch(Exception e){
			e.printStackTrace();
			Reporter.log("failed to execute Verify the Use Of Back Space In Date Field",true);

			ATUReports.add("Verify The Use Of Back Space In Date Field", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute Verify the Use Of Back Space In Date Field");
			Log.error(e.getMessage());
		}		
		finally {
			Log.endTestCase("INFO_2790_UseOfBackSpaceInDateFieldTest");
		}
	}		
}

