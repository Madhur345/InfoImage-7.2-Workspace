package apd.infoimage.iwm.tests;

import org.testng.Assert;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.support.ui.Select;
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
 * @author SumanGaK
 * INFO_5106
 */
public class INFO_5106 extends SuperClassIWM{
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
	@Test(enabled=true,priority=1,groups={"Workitem"})
	public void testRetriveAndOpenFromDefaultQueue(){

		ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "test Retrieve Reserve And Open From Default Queue");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try{
			Log.startTestCase("test Retrive And Open From Default Queue");

			Reporter.log("RetriveAndOpenWorkItemTest : testRetriveAndOpenFromDefaultQueue()",true);
			ATUReports.add("RetriveAndOpenWorkItemTest : testRetriveAndOpenFromDefaultQueue()",true);
			Log.info("RetriveAndOpenWorkItemTest : testRetriveAndOpenFromDefaultQueue()");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath,sheet,9,1);
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

			Select sel = new Select(hp.getNumOfRowsDropDown());		
			sel.selectByValue("100");
			util.wait(time);

			cwp.getActionBtn(workitem).click();
			cwp.getformFiledsBtn().click();

			util.waitForPageToLoad();

			cwp.getID_CODE_Tf().sendKeys(idCodeStr);
			cwp.getUpdateFormBtn().click();
			util.waitForPageToLoad();

			cwp.sendWorkItemToDefaultQueue(workitem);

			ip.getRetriveAndOpenBtn(workitem).click();
			util.waitForPageToLoad();

			wdp.getFormfields_win().click();
			String val = wdp.getIdcode_TF().getAttribute("value");
			Reporter.log("Value in IDCode text field is: "+val,true);
			ATUReports.add("Value in IDCode text field is: "+val,true);
			Log.info("Value in IDCode text field is: "+val);

			boolean workitemHeaderPresence =util.verifyObjectPresentReturnsBool(wdp.getWorkitemHeader(workitem));

			if((val.equals(idCodeStr))&& workitemHeaderPresence){
				Reporter.log("Retrive and open of "+workitem+"is successful in default queue",true);
				ATUReports.add("Retrive and open of "+workitem+"is successful in default queue",true);
				Log.info("Retrive and open of "+workitem+"is successful in default queue");
			}else{
				Reporter.log("Retrive and open of "+workitem+"failed in default queue",true);
				ATUReports.add("Retrive and open of "+workitem+"failed in default queue",true);
				Assert.fail("Retrive and open of "+workitem+"failed in default queue");
			}

		}catch(Exception e){
			e.printStackTrace();
			Reporter.log("failed to execute retriveAndOpenFromDefaultQueue test",true);
			ATUReports.add("execute Retrieve Reserve And Open From Default Queue test", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute retriveAndOpenFromDefaultQueue test");
		}finally {
			hp.getWorkItemTab().click();
			util.waitForPageToLoad();
			Log.endTestCase("Retrieves and reserves workitem");
		}
	}

	@SuppressWarnings("deprecation")
	@Test(enabled=true,priority=2,groups={"Workitem"})
	public void testRetriveAndOpenFromSearch(){

		ATUReports.setTestCaseReqCoverage("This Scenario is To verify" + "test Retrieve And Open From Search");
		ATUReports.setAuthorInfo("Suman", "MAY-2018", "0.3");

		try{
			Log.startTestCase("test Retrieve And Open From Search");			

			Reporter.log("RetriveAndOpenWorkItemTest : testRetriveAndOpenFromSearch()",true);
			ATUReports.add("RetriveAndOpenWorkItemTest : testRetriveAndOpenFromSearch()",true);
			Log.info("RetriveAndOpenWorkItemTest : testRetriveAndOpenFromSearch()");

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet = prop.getProperty("sheet");
			String xlpath = prop.getProperty("xlpath");

			String str = ExcelLib.getCellValue(xlpath, sheet, 1, 1);
			String date = ExcelLib.getCellValue(xlpath, sheet, 2, 1);
			String workitem = str + util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath, sheet, 3, 1);
			String workitemType = ExcelLib.getCellValue(xlpath, sheet, 4, 1);
			String idCodeStr = ExcelLib.getCellValue(xlpath,sheet,9,1);
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

			Select sel = new Select(hp.getNumOfRowsDropDown());		
			sel.selectByValue("100");
			util.wait(time);

			cwp.getActionBtn(workitem).click();
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			cwp.getID_CODE_Tf().sendKeys(idCodeStr);
			cwp.getUpdateFormBtn().click();
			util.waitForPageToLoad();

			cwp.sendWorkItemToDefaultQueue(workitem);

			hp.getSearchTab().click();
			sp.getBasicSearch().click();

			util.wait(time);		

			sp.getTextBox().sendKeys(workitem);
			sp.getSearchButton().click();

			util.wait(time);

			boolean workItemNamePresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if(workItemNamePresence){
				Reporter.log("Workitem to be retreived is present in Search result",true);
				ATUReports.add("Workitem to be retreived is present in Search result",true);
				Log.info("Workitem to be retreived is present in Search result");
			}else{
				Reporter.log("Workitem to be retreived is not present in Search result",true);
				ATUReports.add("Workitem to be retreived is not present in Search result",true);
				Assert.fail("Workitem to be retreived is not present in Search result ");
			}

			cwp.getCheckBoxWorkItemName(workitem).click();

			sp.getRetriveandOpenWorkitem(workitem).click();
			util.waitForPageToLoad();

			wdp.getFormfields_win().click();
			String val =wdp.getIdcode_TF().getAttribute("value");
			Reporter.log("Value in IDCode text field is: "+val,true);

			boolean workitemHeaderPresence =util.verifyObjectPresentReturnsBool(wdp.getWorkitemHeader(workitem));

			if((val.equals(idCodeStr))&& workitemHeaderPresence){
				Reporter.log("Retrive and open of "+workitem+"is successful in default queue",true);
				ATUReports.add("Retrive and open of "+workitem+"is successful in default queue",true);
				Log.info("Retrive and open of "+workitem+"is successful in default queue");
			}else{
				Reporter.log("Retrive and open of "+workitem+"failed in default queue",true);
				ATUReports.add("Retrive and open of "+workitem+"failed in default queue",true);
				Assert.fail("Retrive and open of "+workitem+"failed in default queue");
			}

		}catch(Exception e){
			e.printStackTrace();
			Reporter.log("failed to execute retriveAndOpenFromDefaultQueue test",true);
			ATUReports.add("execute Retrieve And Open From Default Queue test", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute retriveAndOpenFromDefaultQueue test");
		}finally {
			hp.getWorkItemTab().click();
			util.waitForPageToLoad();
		}
	}
	
}
