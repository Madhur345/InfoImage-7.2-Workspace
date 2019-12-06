package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.Properties;

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
 * @author SumanGaK - 13-Nov-2017
 * INFO_2789
 * This class verify the Object Id in Meta data in IWM
 */
public class INFO_2789 extends SuperClassIWM{

	@BeforeMethod
	public void beforMethod() {			
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName1", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod(){
		hp.logoutApp();
	}


	@SuppressWarnings("deprecation")
	@Test(enabled = true,priority=1,groups={"Workitem"})
	public void testVerifyTheObjectIdInMetaData(){

		ATUReports.setTestCaseReqCoverage("This Scenario INFO-2789  is To verify" + "  Verify The Object Id In MetaData");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");
		try{
			Log.startTestCase("INFO_2789_ObjectIdInMetaDataOfWorkitemTest");

			Reporter.log("ObjectIdInMetaDataOfWorkitemTest : testVerifyTheObjectIdInMetaData()",true);
			ATUReports.add("ObjectIdInMetaDataOfWorkitemTest : testVerifyTheObjectIdInMetaData()",true);
			Log.info("ObjectIdInMetaDataOfWorkitemTest : testVerifyTheObjectIdInMetaData()");

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

			cwp.getWorkItemName(workitem);
			Reporter.log("Finding Workitem",true);
			ATUReports.add("Finding Workitem",true);
			Log.info("Finding Workitem");

			cwp.getActionsBtn().click();
			Reporter.log("Actions Button is Clicked",true);
			ATUReports.add("Actions Button is Clicked",true);
			Log.info("Actions Button is Clicked");

			cwp.getActionsMetaData().click();
			Reporter.log("Meta Data is Clicked",true);
			ATUReports.add("Meta Data is Clicked",true);
			Log.info("Meta Data is Clicked");

			String objid=cwp.getObjectId().getText().substring(2);

			Reporter.log("Object Id is : "+objid,true);
			ATUReports.add("Object Id is : "+objid,true);
			Log.info("Object Id is : "+objid);

			boolean objectIdPresence = util.verifyObjectPresentReturnsBool(cwp.getWebElementObjectId(objid));
			if(objectIdPresence)
			{
				Reporter.log("Object id is in metadata screen in workitems page",true);
				ATUReports.add("Object id is in metadata screen in workitems page",true);
				Log.info("Object id is in metadata screen in workitems page");
			}
			else
			{
				Reporter.log("Object id is not in metadata screen in workitems page",true);
			}
			
			util.wait(time);
			util.waitForPageToLoad();
			cwp.getMetaDataCloseMark().click();
			util.wait(time);
			util.waitForPageToLoad();
			
		}catch(Exception e){
			e.printStackTrace();
			Reporter.log("failed to execute Verify the Object Id in Meta Data test",true);
			ATUReports.add("execute Verify the Object Id in Meta Data",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute Verify the Object Id in Meta Data test");
			Log.error(e.getMessage());
		}	
		finally {
			Log.endTestCase("INFO_2789_ObjectIdInMetaDataOfWorkitemTest");
		}
	}
}