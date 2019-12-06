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
 * @author SumanGaK - 25-May-2018
 * INFO_4375
 * This class verify the search functionality with INVOICENO value in Search page.
 */

public class INFO_4375 extends SuperClassIWM{

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
	public void testVerifySearchFunctionalityWithINVOICENOValueInSearchPage()
	{
		ATUReports.setTestCaseReqCoverage("This Scenario INFO-4375 is for Verifying search functionality with INVOICENO value in Search Page");
		ATUReports.setAuthorInfo("Suman","MAY-2018","0.3");

		try
		{
			DOMConfigurator.configure("log4j.xml");
			Log.startTestCase("INFO_4375_VerifySearchFunctionalityWithINVOICENOValueInSearchPageTest");
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));

			String sheet=prop.getProperty("sheet");
			String xlpath=prop.getProperty("xlpath");

			String str=ExcelLib.getCellValue(xlpath,sheet,1,1);
			String date=ExcelLib.getCellValue(xlpath,sheet,2,1);
			String workitem = str+util.getSysDate(0, date);
			String className = ExcelLib.getCellValue(xlpath,sheet,3,1);
			String workitemType = ExcelLib.getCellValue(xlpath,sheet,4,1);
			String searchViewStr=ExcelLib.getCellValue(xlpath,sheet,51,1);
			String searchViewName = searchViewStr+util.getSysDate(0, date);
			String columnName = ExcelLib.getCellValue(xlpath,sheet,50,1);
			String columnName1 = ExcelLib.getCellValue(xlpath,sheet,48,1);
			String columnName2 = ExcelLib.getCellValue(xlpath,sheet,49,1);
			String idCodeStr=ExcelLib.getCellValue(xlpath,sheet,9,1);
			String invoiceNoStr=ExcelLib.getCellValue(xlpath,sheet,10,1);
			int rowCount=ExcelLib.getCellValueInt(xlpath,sheet,52,1);
			int time = ExcelLib.getCellValueInt(xlpath,sheet,58,1);
			int columnsCount = ExcelLib.getCellValueInt(xlpath,sheet,76,1);

			Reporter.log("Class Name : INFO_4375_VerifySearchFunctionalityWithINVOICENOValueInSearchPageTest ",true);
			ATUReports.add("Class Name : INFO_4375_VerifySearchFunctionalityWithINVOICENOValueInSearchPageTest ",true);
			Log.info("Class Name : INFO_4375_VerifySearchFunctionalityWithINVOICENOValueInSearchPageTest ");

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
			Thread.sleep(1000);
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
			util.wait(time);

			hp.clickUserPreferenceTab();
			Reporter.log("User Preferences Tab is clicked.",true);
			ATUReports.add("User Preferences Tab is clicked.",true);
			Log.info("User Preferences Tab is clicked.");
			util.waitForPageToLoad();
			util.wait(time);

			upp.getViewNameTextbox().sendKeys(searchViewName);
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


			upp.getCreatedViewNameRadioWorkitems(searchViewName).click();
			Reporter.log("Created View Name Radio Button is clicked.",true);
			ATUReports.add("Created View Name Radio Button is clicked.",true);
			Log.info("Created View Name Radio Button is clicked.");

			upp.getApplyViewBtn().click();
			Reporter.log("Apply View Button is clicked.",true);
			ATUReports.add("Apply View Button is clicked.",true);
			Log.info("Apply View Button is clicked.");

			hp.getSearchTab().click();
			Reporter.log("Search Tab is clicked.",true);
			ATUReports.add("Search Tab is clicked.",true);
			Log.info("Search Tab is clicked.");

			sp.getBasicSearch().click();
			Reporter.log("Basic Search is clicked.",true);
			ATUReports.add("Basic Search is clicked.",true);
			Log.info("Basic Search is clicked.");

			util.wait(time);		

			sp.getTextBox().sendKeys(workitem);
			Reporter.log("Workitem to be Searched is sent to text box.",true);
			ATUReports.add("Workitem to be Searched is sent to text box.",true);
			Log.info("Workitem to be Searched is sent to text box.");
			util.wait(time);

			sp.getSearchButton().click();
			Reporter.log("Search Button is clicked.",true);
			ATUReports.add("Search Button is clicked.",true);
			Log.info("Search Button is clicked.");

			util.wait(time);

			Select select=new Select(sp.getWorkitemsDropDownForSearch());

			int found=0;

			List<WebElement> allOptions = select.getOptions();

			for(int counter=0;counter<columnsCount;counter++)
			{
				for(int loopCounter=0;loopCounter<allOptions.size();loopCounter++)
				{
					String dropDownSearchColumn = allOptions.get(loopCounter).getText();
					if(column[counter].equalsIgnoreCase(dropDownSearchColumn))
					{
						found++;
					}
				}
				if(found==0)
				{
					System.out.println("Assigned field : "+column[counter]+" is not displayed in the dropdown list.");
				}
				else
				{
					System.out.println("Assigned field : "+column[counter]+" is displayed in the dropdown list.");
				}				
			}

			util.wait(time);
			util.waitForPageToLoad();

			Select sel = new Select(sp.getNumOfRowsDropDown());
			sel.selectByIndex(rowCount);

			util.wait(time);

			select.selectByVisibleText(columnName);
			util.wait(time);

			cwp.getNameSearchTextbox().sendKeys(invoiceNoStr+Keys.ENTER);
			Reporter.log("Search by Invoice Number",true);
			ATUReports.add("Search by Invoice Number",true);
			Log.info("Search by Invoice Number");

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
			
			upp.getCreatedViewNameRadioWorkitems(searchViewName).click();
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
			Log.endTestCase("INFO_4375_VerifySearchFunctionalityWithINVOICENOValueInSearchPageTest");
		}
	}
}
