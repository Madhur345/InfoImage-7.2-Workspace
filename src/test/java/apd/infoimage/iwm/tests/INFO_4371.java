package apd.infoimage.iwm.tests;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
 * @author SumanGaK - 24-May-2018
 * INFO_4371
 * This class verify the search functionality with INVOICENO value in workitems page.
 */

public class INFO_4371 extends SuperClassIWM{

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
	@Test(enabled=true,groups={"UserPreference"})
	public void testVerifySearchFunctionalityWithINVOICENOValueInWorkitems()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-4371 is for Verifying search functionality with INVOICENO value in workitems page");
		ATUReports.setAuthorInfo("Suman","MAY-2018","0.3");

		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_4371_VerifySearchFunctionalityWithINVOICENOValueInWorkitemsTest");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String viewstr=ExcelLib.getCellValue(xlpath,sheet,45,1);
			String viewName = viewstr+util.getSysDate(0, date);
			String columnName = ExcelLib.getCellValue(xlpath,sheet,50,1);
			String columnName1 = ExcelLib.getCellValue(xlpath,sheet,48,1);
			String columnName2 = ExcelLib.getCellValue(xlpath,sheet,49,1);
			String idCodeStr=ExcelLib.getCellValue(xlpath,sheet,9,1);
			String invoiceNoStr=ExcelLib.getCellValue(xlpath,sheet,10,1);	
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);			


			Log.startTestCase("INFO_4371_VerifySearchFunctionalityWithINVOICENOValueInWorkitemsTest");

			Reporter.log("INFO_4371_VerifySearchFunctionalityWithINVOICENOValueInWorkitemsTest ",true);

			Reporter.log("Workitem : "+workitem,true);
			ATUReports.add("Workitem : "+workitem,true);
			Log.info("Workitem : "+workitem);
			Reporter.log("Class Name : "+className,true);
			ATUReports.add("Class Name : "+className,true);
			Log.info("Class Name : "+className);
			Reporter.log("Workitem Type : "+workitemType,true);
			ATUReports.add("Workitem Type : "+workitemType,true);
			Log.info("Workitem Type : "+workitemType);

			Reporter.log("Creation of Workitem",true);
			ATUReports.add("Creation of Workitem",true);
			Log.info("Creation of Workitem");
			cwp.CreateWorkitem(workitem,className,workitemType);
			Reporter.log("CreateWorkitem operation performed",true);
			ATUReports.add("CreateWorkitem operation performed",true);
			Log.info("CreateWorkitem operation performed");

			util.wait(time);

			Reporter.log("Selection of Workitem",true);
			ATUReports.add("Selection of Workitem",true);
			Log.info("Selection of Workitem");
			cwp.getWorkItemName(workitem).click();
			util.wait(time);
			util.waitForPageToLoad();
			Reporter.log("Updation of Formfield",true);
			ATUReports.add("Updation of Formfield",true);
			Log.info("Updation of Formfield");

			wdp.getFormfields_win().click();
			util.wait(time);
			//ID_CODE text field
			wdp.getIdcode_TF().clear();
			wdp.getIdcode_TF().sendKeys(idCodeStr);
			Reporter.log("Idcode has been written",true);
			ATUReports.add("Idcode has been written",true);
			Log.info("Idcode has been written");
			util.wait(time);

			wdp.getInvoiceno_TF().clear();
			wdp.getInvoiceno_TF().sendKeys(invoiceNoStr);
			Reporter.log("Invoice Number has been written",true);
			ATUReports.add("Invoice Number has been written",true);
			Log.info("Invoice Number has been written");
			util.wait(time);

			wdp.getUpdate_btn().click();
			Reporter.log("Update Button has been Clicked",true);
			ATUReports.add("Update Button has been Clicked",true);
			Log.info("Update Button has been Clicked");
			util.waitForPageToLoad();
			Thread.sleep(5000);

			hp.getWorkItemTab().click();
			util.wait(time);
			util.waitForPageToLoad();

			hp.clickUserPreferenceTab();
			Reporter.log("User Preferences Tab is clicked.",true);
			ATUReports.add("User Preferences Tab is clicked.",true);
			Log.info("User Preferences Tab is clicked.");
			util.waitForPageToLoad();
			util.wait(time);

			upp.getWorkitemRadioBtn().click();
			Reporter.log("Workitems Radio Button is clicked.",true);
			ATUReports.add("Workitems Radio Button is clicked.",true);
			Log.info("Workitems Radio Button is clicked.");

			util.wait(time);
			upp.getViewNameTextbox().sendKeys(viewName);
			Reporter.log("View Name is passed to text box.",true);
			ATUReports.add("View Name is passed to text box.",true);
			Log.info("View Name is passed to text box.");

			util.wait(time);

			upp.selectFieldsFromAvailable(columnName,columnName1,columnName2);

			Reporter.log("Workflow variables column names in Available Fields are moved to Selected",true);
			ATUReports.add("Workflow variables column names in Available Fields are moved to Selected",true);
			Log.info("Workflow variables column names in Available Fields are moved to Selected");

			String column[]={columnName,columnName1,columnName2};	        

			util.wait(time);
			upp.getCreateViewBtn().click();
			Reporter.log("Create View Button is clicked.",true);
			ATUReports.add("Create View Button is clicked.",true);
			Log.info("Create View Button is clicked.");
			util.waitForPageToLoad();
			util.wait(time);	


			upp.getCreatedViewNameRadioWorkitems(viewName).click();
			Reporter.log("Created View Name Radio Button is clicked.",true);
			ATUReports.add("Created View Name Radio Button is clicked.",true);
			Log.info("Created View Name Radio Button is clicked.");

			upp.getApplyViewBtn().click();
			Reporter.log("Apply View Button is clicked.",true);
			ATUReports.add("Apply View Button is clicked.",true);
			Log.info("Apply View Button is clicked.");

			hp.getWorkItemTab().click();

			Select select=new Select(cwp.getWorkitemsDropDownForSearch());

			int found=0;

			List<WebElement> allOptions = select.getOptions();

			for(int j=0;j<3;j++)
			{
				for(int i=0;i<allOptions.size();i++)
				{
					String columnname = allOptions.get(i).getText();
					if(column[j].equalsIgnoreCase(columnname))
					{
						found++;
					}
				}
				if(found==0)
				{
					System.out.println("Assigned field : "+column[j]+" is not displayed in the dropdown list.");
				}
				else
				{
					System.out.println("Assigned field : "+column[j]+" is displayed in the dropdown list.");
				}				
			}

			util.wait(time);
			util.waitForPageToLoad();

			Select sel = new Select(hp.getNumOfRowsDropDown());		
			sel.selectByValue("100");
			util.wait(time);

			select.selectByVisibleText(columnName);
			util.wait(time);

			cwp.getNameSearchTextbox().sendKeys(invoiceNoStr+Keys.ENTER);

			boolean workitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if(workitemPresence){
				Reporter.log("Workitem is present in grid",true);
				ATUReports.add("Workitem is present in grid",true);
				Log.info("Workitem is present in grid");
				ATUReports.add("Verify SearchFunctionalityWithINVOICENOValueInWorkitems Test", "","SearchFunctionalityWithINVOICENOValueInWorkitems should be displayed",
						"SearchFunctionalityWithINVOICENOValueInWorkitems is displayed", true);
			}else{
				Reporter.log("Workitem is not present in grid",true);
				ATUReports.add("Workitem is not present in grid",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
				Log.info("Workitem is not present in grid");
				Assert.fail("Workitem is not present in grid ");
			}	
			
			
			util.waitForPageToLoad();
			util.wait(time);
			hp.clickUserPreferenceTab();
			Reporter.log("User Preferences Tab is clicked.",true);
			ATUReports.add("User Preferences Tab is clicked.",true);
			Log.info("User Preferences Tab is clicked.");
			util.waitForPageToLoad();
			util.wait(time);

			upp.getWorkitemRadioBtn().click();
			Reporter.log("Workitems Radio Button is clicked.",true);
			ATUReports.add("Workitems Radio Button is clicked.",true);
			Log.info("Workitems Radio Button is clicked.");

			util.waitForPageToLoad();
			util.wait(time);			

			upp.getCreatedViewNameRadioWorkitems(viewName).click();
			Reporter.log("Created View Name Radio Button is clicked.",true);
			ATUReports.add("Created View Name Radio Button is clicked.",true);
			Log.info("Created View Name Radio Button is clicked.");

			upp.getDeleteViewBtn().click();
			Reporter.log("Delete View Button is clicked.", true);
			ATUReports.add("Delete View Button is clicked.", true);
			Log.info("Delete View Button is clicked.");
			
			util.wait(time);
			util.waitForPageToLoad();
			
			upp.getAlertOKButton().click();
			Reporter.log("Alert OK Button is clicked.", true);
			ATUReports.add("Alert OK Button is clicked.", true);
			Log.info("Alert OK Button is clicked.");
			
			util.wait(time);
			util.waitForPageToLoad();

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("Verify Search Functionality With INVOICENO Value In Workitems test failed.", true);
			Assert.fail("Verify Search Functionality With INVOICENO Value In Workitems test failed.");
		}
		finally {
			Log.endTestCase("INFO_4371_VerifySearchFunctionalityWithINVOICENOValueInWorkitemsTest");
		}
	}
}
