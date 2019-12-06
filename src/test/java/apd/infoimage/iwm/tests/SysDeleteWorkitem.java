package apd.infoimage.iwm.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apd.infoimage.iwm.genericLib.CommonUtils;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.SuperClassIWM;
import atu.testng.reports.ATUReports;

public class SysDeleteWorkitem extends SuperClassIWM {
	

	@BeforeMethod
	public void beforMethod(){
		Driver.driver.get(prop.getProperty("appUrl"));
		util.waitForPageToLoad();
		lp.loginToApp("userName", "password", "domain", "role");
		util.waitForPageToLoad();
	}

	@AfterMethod
	public void afterMethod(){
		//hp.logoutApp();
		System.out.println("Application successfully logged out");
	}


	@SuppressWarnings("deprecation")
	@Test(enabled =true, priority=1,groups={"Workitem"})
	public void deletingWorkitemOfDocumentTypeSysDelete(){
		String documentName;
		Log.startTestCase("deletingWorkitemOfDocumentTypeSysDelete");
		try{
			ATUReports.setTestCaseReqCoverage("This Scenario is To verify"
					+ "deleting Workitem Of Document Type Sys Delete..");
			ATUReports.setAuthorInfo("Biswajit", "MAY-2018", "0.3");
			
			
			
			util.jclick(cwp.getselectAllCheckBox());
			Log.info("Select All Check Box clicked " );
			Reporter.log("Select All Check Box clicked", true);
			ATUReports.add("Select All Check Box clicked", true);
			
			//Click on the delete workitem button 
			cwp.getDeleteWorkitemBtn().click();
			Thread.sleep(2000);

			//Check whether delete workitem window is appearing or not
			boolean deleteWIWinPresent=util.verifyObjectPresentReturnsBool(cwp.getDeleteWItemWin());
			if(deleteWIWinPresent)
			{
				Reporter.log("Delete Workitem window is present", true);
				ATUReports.add("Delete Workitem window is present.", true);
			}
			else{
				Reporter.log("Delete Workitem window box not present",true);
				ATUReports.add("Delete Workitem window box not present",true);
				Assert.fail("Delete Workitem window box not present ");
			}

			//perform the Sys delete
			if(util.verifyObjectPresentReturnsBool(cwp.getSysDeleteRadioBtn()))
			{
				//perform rhe Sys delete.
				cwp.getSysDeleteRadioBtn().click();
			}
			
			cwp.getDeleteWItemAccept().click();
            Thread.sleep(5000);

			
		}catch(Exception e){
			e.printStackTrace();
			Log.error(e.getMessage());
			Reporter.log("failed to execute deletingWorkitemOfDocumentTypeSysDelete test",true);
			ATUReports.add("failed to execute deletingWorkitemOfDocumentTypeSysDelete test",true);
			Assert.fail("failed to execute deletingWorkitemOfDocumentTypeSysDelete test");
		}
		finally {
			hp.logoutApp();
			Log.endTestCase("deletingWorkitemOfDocumentTypeSysDelete");
		}
	}

	
}