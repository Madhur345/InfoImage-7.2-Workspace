package apd.infoimage.iwm.tests;

import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class INFO_5100 extends SuperClassIWM{
	

	/**
	 * @author PradhanJ
	 *
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

	/**
	 * This test method will perform desk delete of folder type and document type workitem combinely
	 * @author PradhanJ
	 */
	
	@SuppressWarnings("deprecation")
	@Test(enabled=true,priority=1,groups={"Workitem"})
	public void deskDeleteFolderAndDocTypeWitem()
	{
		
		Log.startTestCase("deskDeleteFolderAndDocTypeWitem");
		
		String folderName = "jayF"+util.getSysDate(0, "yyDDMMhhmmss");
		String docName = "jayD"+util.getSysDate(0, "yyDDMMhhmmss");
		
		try
		{
			ATUReports.setTestCaseReqCoverage("This Scenario is To verify"
					+ "Verify desk Delete Folder And DocType Witem");
			ATUReports.setAuthorInfo("Biswajit", "MAY-2018", "0.3");
			
			//Create the folder type and document type woworkitems
			cwp.CreateWorkitem(folderName,"archive","Folder");
			Reporter.log("Folder: "+folderName+" has been created",true);
			
			util.waitForPageToLoad();
			Thread.sleep(3000);
			cwp.CreateWorkitem(docName,"archive","Document");
			Reporter.log("Documet: "+docName+" has been created",true);
			
			cwp.searchByNameInWorkitemList(folderName);
			
			util.waitForPageToLoad();
			Thread.sleep(5000);
						
			util.jclick(cwp.getCheckBoxWorkItem(folderName));
			Reporter.log("Folder type workitem is seltected",true);
			ATUReports.add("Folder type workitem is seltected",true);

			cwp.getDeleteWorkitemBtn().click();
			util.waitForPageToLoad();
			Thread.sleep(3000);
			
			boolean deleteWIWinPresent=util.verifyObjectPresentReturnsBool(cwp.getDeleteWItemWin());
			if(deleteWIWinPresent)
			{
				Reporter.log("Delete Workitem window is present", true);
				ATUReports.add("Delete Workitem window is present", true);
				
			}
			else{
				Reporter.log("Delete Workitem window box not present",true);
				ATUReports.add("Delete Workitem window box not present",true);		}

			//perform the desk delete
			if(util.verifyObjectPresentReturnsBool(cwp.getDeskDeleteRadioBtn()))
			{
				Reporter.log("Click on desk delete radio button",true);
				ATUReports.add("Click on desk delete radio button",true);	
				cwp.getDeskDeleteRadioBtn().click();
			}
			cwp.getDeleteWItemAccept().click();
			Reporter.log("Delete workitem accept button is clicked",true);
			ATUReports.add("Delete workitem accept button is clicked",true);
			util.waitForPageToLoad();
			Thread.sleep(5000);
			
			cwp.searchByNameInWorkitemList(docName);
			
			util.waitForPageToLoad();
			Thread.sleep(5000);
			
			util.jclick(cwp.getCheckBoxWorkItem(docName));
			Reporter.log("Document type workitem is seltected",true);
			ATUReports.add("Document type workitem is seltected",true);
			
			cwp.getDeleteWorkitemBtn().click();
			util.waitForPageToLoad();
			Thread.sleep(3000);
		
			//perform the desk delete
			if(util.verifyObjectPresentReturnsBool(cwp.getDeskDeleteRadioBtn()))
			{
				Reporter.log("Click on desk delete radio button",true);
				ATUReports.add("Click on desk delete radio button",true);	
				cwp.getDeskDeleteRadioBtn().click();
			}
			cwp.getDeleteWItemAccept().click();
			Reporter.log("Delete workitem accept button is clicked",true);
			ATUReports.add("Delete workitem accept button is clicked",true);
			util.waitForPageToLoad();
			Thread.sleep(5000);
						
			try
			{
				cwp.searchByNameInWorkitemList(docName);
				WebElement deletedWorkitemDocument=cwp.getWorkItemName(docName);
				
				boolean deletedFWItemPresent=util.verifyObjectPresentReturnsBool(deletedWorkitemDocument);
				
				if(deletedFWItemPresent)
				{
					Reporter.log("Document and folder type WorkItem is not deleted", true);
					ATUReports.add("Document and folder type WorkItem is not deleted", true);
					Assert.fail("Document and folder type WorkItem is not deleted");
				}
			}
			catch(Exception e1)
			{
				Reporter.log("Document and folder type workItem is deleted successfully", true);
				ATUReports.add("Document and folder type workItem is deleted successfully", true);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute deleting Folder and Document type workitem together with DeskDelete ",true);
			ATUReports.add(
					"execute deleting Folder and Document type workitem together with DeskDelete",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute deleting Folder and Document type workitem together with DeskDelete");
		}
		finally {
			Log.endTestCase("deskDeleteFolderAndDocTypeWitem");
		}
	}
	
	/**
	 * This test method will perform sys delete of folder type and document type workitem combinely
	 * @author PradhanJ
	 */
	
	@Test(enabled=false,priority=2,groups={"Workitem"})
	public void sysDeleteFolderAndDocTypeWitem()
	{
		
		Log.startTestCase("sysDeleteFolderAndDocTypeWitem");
		
		String folderName = "jayF"+util.getSysDate(0, "yyDDMMhhmmss");
		String docName = "jayD"+util.getSysDate(0, "yyDDMMhhmmss");
		
		try
		{
			ATUReports.setTestCaseReqCoverage("This Scenario is To verify"
					+ "execute deleting Folder and Document type workitem together with SysDelete");
			ATUReports.setAuthorInfo("Biswajit", "MAY-2018", "0.3");
			
			//Create the folder type and document type woworkitems
			cwp.CreateWorkitem(folderName,"archive","Folder");
			Reporter.log("Folder: "+folderName+" has been created",true);
			Thread.sleep(3000);
			cwp.CreateWorkitem(docName,"archive","Folder");
			Reporter.log("Documet: "+docName+" has been created",true);
			
			Select sel = new Select(hp.getNumOfRowsDropDown());		
			sel.selectByValue("100");
			
			//Perform desk delete
			cwp.getCheckBoxWorkItem(folderName).click();
			Thread.sleep(2000);
			cwp.getCheckBoxWorkItem(docName).click();
			
			//Click on the delete workitem button 
			cwp.getDeleteWorkitemBtn().click();
			Thread.sleep(2000);

			//Check whether delete workitem window is appearing or not
			boolean deleteWIWinPresent=util.verifyObjectPresentReturnsBool(cwp.getDeleteWItemWin());
			if(deleteWIWinPresent)
			{
				Reporter.log("Delete Workitem window is present", true);
				ATUReports.add("Delete Workitem window is present", true);
			}
			else{
				Reporter.log("Delete Workitem window box not present",true);
				ATUReports.add("Delete Workitem window box not present",true);
				Assert.fail("Delete Workitem window box not present ");
			}

			//perform the desk delete
			if(util.verifyObjectPresentReturnsBool(cwp.getSysDeleteRadioBtn()))
			{
				//perform rhe desk delete
				cwp.getSysDeleteRadioBtn().click();
			}
			cwp.getDeleteWItemAccept().click();

			util.waitForPageToLoad();
			sel.selectByValue("100");
			Thread.sleep(2000);

			try
			{
				WebElement deletedWorkitemFolder=cwp.getWorkItemName(folderName);
				
				boolean deletedFWItemPresent=util.verifyObjectPresentReturnsBool(deletedWorkitemFolder);
				
				if(deletedFWItemPresent)
				{
					Reporter.log("Folder type WorkItem is not deleted", true);
					ATUReports.add("Folder type WorkItem is not deleted", true);
					Assert.fail("Folder type WorkItem is not deleted");
				}
			}
			catch(Exception e1)
			{
				Reporter.log("Folder type workItem is deleted successfully", true);
				ATUReports.add("Folder type workItem is deleted successfully", true);
			}
			try
			{
				WebElement deletedWorkitemDoc=cwp.getWorkItemName(docName);
				boolean deletedDWItemPresent=util.verifyObjectPresentReturnsBool(deletedWorkitemDoc);
				
				if(deletedDWItemPresent)
				{
					Reporter.log("Document type WorkItem is not deleted", true);
					ATUReports.add("Document type WorkItem is not deleted", true);
					Assert.fail("Document type WorkItem is not deleted");
				}
			}
			catch(Exception e1)
			{
				Reporter.log("Document type workItem is deleted successfully", true);
				ATUReports.add("Document type workItem is deleted successfully", true);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute deleting Folder and Document type workitem together with SysDelete ",true);
			ATUReports.add(
					"execute deleting Folder and Document type workitem together with SysDelete",
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("failed to execute deleting Folder and Document type workitem together with SysDelete");
		}
		finally {
			Log.endTestCase("sysDeleteFolderAndDocTypeWitem");
		}
	}
}
