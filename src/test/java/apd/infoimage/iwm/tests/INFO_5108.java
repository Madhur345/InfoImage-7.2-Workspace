package apd.infoimage.iwm.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.projectLib.SuperClassIWM;

/**
 * @author PradhanJ
 *
 */
public class INFO_5108 extends SuperClassIWM{
	

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

	/**
	 * This test method will create a defined search, validate the defined search by retrieving workitem.
	 * @throws Exception 
	 */

	@Test(enabled=false,groups={"Search"})
	public void testDefinedSearch()
	{
		String workitm = "jay"+util.getSysDate(0, "yyDDMMhhmmss");
		try{
			//Fetch the query type from user data

			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			String query=prop.getProperty("queryType");

			//Create workitem

			Reporter.log("Creation of Workitem");
			cwp.CreateWorkitem(workitm,"archive","Document");
			
			Thread.sleep(2000);

			//Open workitem and update form fields

			Reporter.log("Selection of Workitem");
			cwp.getWorkItemName(workitm).click();
			Thread.sleep(2000);
			util.waitForPageToLoad();

			Reporter.log("Updation of Formfield");
			wdp.getFormfields_win().click();
			Thread.sleep(2000);
			
			wdp.getIdcode_TF().sendKeys("Unisys");
			Thread.sleep(2000);
			wdp.getUpdate_btn().click();
			Thread.sleep(2000);

			cwp.getWorkItems().click();
			Thread.sleep(2000);
			util.waitForPageToLoad();

			//Send the workitem to default queue
			Reporter.log("Sending Workitem to Workflow");
			cwp.sendWorkItemToDefaultQueue(workitm);	 

			Thread.sleep(2000);
			util.waitForPageToLoad();
			Reporter.log("Performing Defined Search....");

			hp.getSearchTab().click();
			sp.getRefinedSearch().click();

			Thread.sleep(2000);	

			Select sel = new Select(sp.getSelectQueryType());
			sel.selectByValue(query);
			Reporter.log("Option selected as "+query,true);
			Thread.sleep(2000);

			sp.getIdCode().sendKeys("Unisys");
			
			sp.getRefinedSearchSaveBtn().click();
			
			//Validate whether the create defined search popup is dispalyed or not
			boolean definedPopupPresent=util.verifyObjectPresentReturnsBool(sp.getDefineSearchPopup());			
			
			if(definedPopupPresent)
			{
				Reporter.log("Defined search creation popup is present", true);
			}
			else
			{
				Reporter.log("Defined search creation popup is present", true);
				Assert.fail("Defined search creation popup is present");
				
			}
			String defSearchName="Def"+util.getSysDate(0, "yyDDMMhhmmss");
			sp.getDefSearchNameTextbox().sendKeys(defSearchName);
			sp.getDefSearchPopupSaveBtn().click();
			
			Thread.sleep(3000);
			//Validate the cretaed defined search name
			
			boolean defSrchNamePresent=util.verifyObjectPresentReturnsBool(sp.getDefinedSearchName(defSearchName));
			if(defSrchNamePresent)
			{
				Reporter.log("Defined search created is displayed successfully", true);
			}
			else
			{
				Reporter.log("Defined search is not created ", true);
				Assert.fail("Validation of defined search creation failed..");
			}
			
			//Validate whether clicking on the created defined search gives the serach result or not.
			
			sp.getDefinedSearchName(defSearchName).click();
			Thread.sleep(5000);
			
			boolean defSearchResultPresent=util.verifyObjectPresentReturnsBool(sp.getDefSearchResultTable());
			
			if(defSearchResultPresent)
			{
				Reporter.log("Defined search Result is displayed successfully", true);
			}
			else
			{
				Reporter.log("Defined search Result is not dispalyed ", true);
				Assert.fail("Validation of Defined search Result failed..");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Reporter.log("failed to execute  defined search test",true);
			Assert.fail("failed to execute definedsearch test");
		}

		finally
		{
			hp.CreateWorkitemTab();
			//cwp.deleteWorkItem(workitm);
		}



	}

}
