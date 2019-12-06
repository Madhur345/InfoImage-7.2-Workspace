package apd.infoimage.iwm.tests;

import org.testng.Assert;
import org.openqa.selenium.WebElement;
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
public class INFO_3696 extends SuperClassIWM {
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


	@Test(enabled =true,priority=1,groups={"WorkitemDetailView"})
	public void performReclassifyWithSaveOptionWithoutRetainingData(){
		String documentName = null;
		String currentClass="archive";
		String formField="formtest";
		try{
			documentName = "wi"+util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(documentName,currentClass,"Document");
			Reporter.log("Documet: "+documentName+" has been created",true);

			Select sel = new Select(hp.getNumOfRowsDropDown());		
			sel.selectByValue("100");
			Thread.sleep(2000);
		
			cwp.getActionBtn(documentName).click();
			cwp.getformFiledsBtn().click();
			
			util.waitForPageToLoad();
			cwp.getID_CODE_Tf().sendKeys(formField);
			cwp.getUpdateFormBtn().click();
			util.waitForPageToLoad();
			
			cwp.getActionBtn(documentName).click();
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			String formFieldvalue = cwp.getID_CODE_Tf().getAttribute("value");
			if(formFieldvalue.equals(formField)){
				Reporter.log("Form field ID_CODE updated successfully",true);
			}else{
				Reporter.log("Form field ID_CODE failed to update",true);
				Assert.fail("Form field ID_CODE failed to update");
			}
			cwp.getCloseFormBtn().click();
			
			cwp.getActionBtn(documentName).click();
			cwp.getReClassifyBtn().click();
			util.waitForPageToLoad();
			
			String crrClass= cwp.getCurrentClassWorkitemReclassify().getText();
			if(crrClass.equals(currentClass)){
				Reporter.log("Current class is: "+currentClass,true);
			}else{
				Reporter.log("Current class is is not as expected",true);
				Assert.fail("Current class is is not as expected");
			}
			
			Select sel2 = new Select(cwp.getNewClassDropDownWorkitemReclassify());
			sel2.selectByIndex(0);
			
			WebElement option = sel2.getFirstSelectedOption();
			String selectedNewClass = option.getText();
			Reporter.log("Selected class is: "+selectedNewClass,true);
			
			
			boolean f =cwp.getRetainFormDataChkBoxWorkitemReclassify().isSelected();
			if(f){
				cwp.getRetainFormDataChkBoxWorkitemReclassify().click();
				Reporter.log("Retain form data check box checked",true);
			}
			
			cwp.getReClassifySubmitBtn().click();
			Reporter.log("Clicked on Save button",true);
			util.waitForPageToLoad();
			
			cwp.getActionBtn(documentName).click();
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			String formFieldvalue1 = cwp.getID_CODE_Tf().getAttribute("value");
			if(formFieldvalue1==null){
				Reporter.log("Form Data Retained after unchecking the checkbox",true);
				Assert.fail("Form Data Retained after unchecking the checkbox");
			}else{
				Reporter.log("Form Data is not Retained after unchecking the checkbox as expected",true);
			}
			cwp.getCloseFormBtn().click();
			
			cwp.getActionBtn(documentName).click();
			cwp.getMetaData().click();
			util.waitForPageToLoad();
			
			boolean f1 = util.verifyObjectPresentReturnsBool(cwp.getMetaDataWorkItem(documentName));
			boolean f2 = util.verifyObjectPresentReturnsBool(cwp.getMetaDataClassName(selectedNewClass));
			
			if(f1 && f2){
				Reporter.log("Reclassification successful without retaining data as expected",true);
			}else{
				Reporter.log("Reclassification failed",true);
				Assert.fail("Reclassification failed");
			}
			
			cwp.getMetaDataDialogBoxCloseBtn(documentName).click();
				

		}catch(Exception e){
			e.printStackTrace();
			Reporter.log("failed to execute performReclassifyWithSaveOptionWithoutRetainingData test",true);
			Assert.fail("failed to execute performReclassifyWithSaveOptionWithoutRetainingData test");
		}finally {
			hp.getWorkItemTab().click();
			util.waitForPageToLoad();
			cwp.deleteWorkItem(documentName);
		}
	}
	
	@Test(enabled =true,priority=2,groups={"WorkitemDetailView"})
	public void performReclassifyWithSaveOptionWithRetainingData(){
		String documentName = null;
		String currentClass="archive";
		String formField="vintest";
		try{
			documentName = "vin"+util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(documentName,currentClass,"Document");
			Reporter.log("Documet: "+documentName+" has been created",true);

			Select sel = new Select(hp.getNumOfRowsDropDown());		
			sel.selectByValue("100");
			Thread.sleep(2000);
		
			cwp.getActionBtn(documentName).click();
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			cwp.getID_CODE_Tf().sendKeys(formField);
			cwp.getUpdateFormBtn().click();
			util.waitForPageToLoad();
			
			cwp.getActionBtn(documentName).click();
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			String formFieldvalue = cwp.getID_CODE_Tf().getAttribute("value");
			if(formFieldvalue.equals(formField)){
				Reporter.log("Form field ID_CODE updated successfully",true);
			}else{
				Reporter.log("Form field ID_CODE failed to update",true);
				Assert.fail("Form field ID_CODE failed to update");
			}
			cwp.getCloseFormBtn().click();
			
			cwp.getActionBtn(documentName).click();
			cwp.getReClassifyBtn().click();
			util.waitForPageToLoad();
			
			String crrClass= cwp.getCurrentClassWorkitemReclassify().getText();
			if(crrClass.equals(currentClass)){
				Reporter.log("Current class is: "+currentClass,true);
			}else{
				Reporter.log("Current class is is not as expected",true);
				Assert.fail("Current class is is not as expected");
			}
			
			Select sel2 = new Select(cwp.getNewClassDropDownWorkitemReclassify());
			sel2.selectByIndex(0);
			
			WebElement option = sel2.getFirstSelectedOption();
			String selectedNewClass = option.getText();
			Reporter.log("Selected class is: "+selectedNewClass,true);
			
			
			boolean f =cwp.getRetainFormDataChkBoxWorkitemReclassify().isSelected();
			if(!f){
				cwp.getRetainFormDataChkBoxWorkitemReclassify().click();
				Reporter.log("Retain form data check box checked",true);
			}
			
			cwp.getReClassifySubmitBtn().click();
			Reporter.log("Clicked on Save button",true);
			util.waitForPageToLoad();
			
			cwp.getActionBtn(documentName).click();
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			String formFieldvalue1 = cwp.getID_CODE_Tf().getAttribute("value");
			if(formFieldvalue1.equals(formField)){
				Reporter.log("Form Data Retained after unchecking the checkbox",true);
			}else{
				Reporter.log("Form Data is not Retained after unchecking the checkbox as expected",true);
				Assert.fail("Form Data is not Retained after unchecking the checkbox as expected");
			}
			cwp.getCloseFormBtn().click();
			
			cwp.getActionBtn(documentName).click();
			cwp.getMetaData().click();
			util.waitForPageToLoad();
			
			boolean f1 = util.verifyObjectPresentReturnsBool(cwp.getMetaDataWorkItem(documentName));
			boolean f2 = util.verifyObjectPresentReturnsBool(cwp.getMetaDataClassName(selectedNewClass));
			
			if(f1 && f2){
				Reporter.log("Reclassification successful with retaining data as expected",true);
			}else{
				Reporter.log("Reclassification failed",true);
				Assert.fail("Reclassification failed");
			}
			
			cwp.getMetaDataDialogBoxCloseBtn(documentName).click();
				

		}catch(Exception e){
			e.printStackTrace();
			Reporter.log("failed to execute performReclassifyWithSaveOptionWithRetainingData test",true);
			Assert.fail("failed to execute performReclassifyWithSaveOptionWithRetainingData test");
		}finally {
			hp.getWorkItemTab().click();
			util.waitForPageToLoad();
			cwp.deleteWorkItem(documentName);
		}
	}
	
	@Test(enabled =true,priority=3,groups={"WorkitemDetailView"})
	public void performReclassifyWithCloseOptionWithoutRetainingData(){
		String documentName = null;
		String currentClass="archive";
		String formField="vintest";
		try{
			documentName = "vin"+util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(documentName,currentClass,"Document");
			Reporter.log("Documet: "+documentName+" has been created",true);

			Select sel = new Select(hp.getNumOfRowsDropDown());		
			sel.selectByValue("100");
			Thread.sleep(2000);
		
			cwp.getActionBtn(documentName).click();
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			cwp.getID_CODE_Tf().sendKeys(formField);
			cwp.getUpdateFormBtn().click();
			util.waitForPageToLoad();
			
			cwp.getActionBtn(documentName).click();
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			String formFieldvalue = cwp.getID_CODE_Tf().getAttribute("value");
			if(formFieldvalue.equals(formField)){
				Reporter.log("Form field ID_CODE updated successfully",true);
			}else{
				Reporter.log("Form field ID_CODE failed to update",true);
				Assert.fail("Form field ID_CODE failed to update");
			}
			cwp.getCloseFormBtn().click();
			
			cwp.getActionBtn(documentName).click();
			cwp.getReClassifyBtn().click();
			util.waitForPageToLoad();
			
			String crrClass= cwp.getCurrentClassWorkitemReclassify().getText();
			if(crrClass.equals(currentClass)){
				Reporter.log("Current class is: "+currentClass,true);
			}else{
				Reporter.log("Current class is is not as expected",true);
				Assert.fail("Current class is is not as expected");
			}
			
			Select sel2 = new Select(cwp.getNewClassDropDownWorkitemReclassify());
			sel2.selectByIndex(0);
			
			WebElement option = sel2.getFirstSelectedOption();
			String selectedNewClass = option.getText();
			Reporter.log("Selected class is: "+selectedNewClass,true);
			
			
			boolean f =cwp.getRetainFormDataChkBoxWorkitemReclassify().isSelected();
			if(f){
				cwp.getRetainFormDataChkBoxWorkitemReclassify().click();
				Reporter.log("Retain form data check box checked",true);
			}
			
			cwp.getReClassifyCloseBtn().click();
			Reporter.log("Clicked on Close button",true);
			util.waitForPageToLoad();
			
			cwp.getActionBtn(documentName).click();
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			String formFieldvalue1 = cwp.getID_CODE_Tf().getAttribute("value");
			if(formFieldvalue1.equals(formField)){
				Reporter.log("Form Data Retained after canceling reclassify workitem as expected",true);
			}else{
				Reporter.log("Form Data not Retained after canceling reclassify workitem",true);
				Assert.fail("Form Data not Retained after canceling reclassify workitem");
			}
			cwp.getCloseFormBtn().click();
			
			cwp.getActionBtn(documentName).click();
			cwp.getMetaData().click();
			util.waitForPageToLoad();
			
			boolean f1 = util.verifyObjectPresentReturnsBool(cwp.getMetaDataWorkItem(documentName));
			boolean f2 = util.verifyObjectPresentReturnsBool(cwp.getMetaDataClassName(currentClass));
			
			if(f1 && f2){
				Reporter.log("Reclassification successfully canceled as expected",true);
			}else{
				Reporter.log("Reclassification failed",true);
				Assert.fail("Reclassification failed");
			}
			
			cwp.getMetaDataDialogBoxCloseBtn(documentName).click();
				

		}catch(Exception e){
			e.printStackTrace();
			Reporter.log("failed to execute performReclassifyWithCloseOptionWithoutRetainingData test",true);
			Assert.fail("failed to execute performReclassifyWithCloseOptionWithoutRetainingData test");
		}finally {
			hp.getWorkItemTab().click();
			util.waitForPageToLoad();
			cwp.deleteWorkItem(documentName);
		}
	}

	@Test(enabled =true,priority=4,groups={"WorkitemDetailView"})
	public void performReclassifyWithCloseOptionWithRetainingData(){
		String documentName = null;
		String currentClass="archive";
		String formField="vintest";
		try{
			documentName = "vin"+util.getSysDate(0, "yyDDMMhhmmss");
			cwp.CreateWorkitem(documentName,currentClass,"Document");
			Reporter.log("Documet: "+documentName+" has been created",true);

			Select sel = new Select(hp.getNumOfRowsDropDown());		
			sel.selectByValue("100");
			Thread.sleep(2000);
		
			cwp.getActionBtn(documentName).click();
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			cwp.getID_CODE_Tf().sendKeys(formField);
			cwp.getUpdateFormBtn().click();
			util.waitForPageToLoad();
			
			cwp.getActionBtn(documentName).click();
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			String formFieldvalue = cwp.getID_CODE_Tf().getAttribute("value");
			if(formFieldvalue.equals(formField)){
				Reporter.log("Form field ID_CODE updated successfully",true);
			}else{
				Reporter.log("Form field ID_CODE failed to update",true);
				Assert.fail("Form field ID_CODE failed to update");
			}
			cwp.getCloseFormBtn().click();
			
			cwp.getActionBtn(documentName).click();
			cwp.getReClassifyBtn().click();
			util.waitForPageToLoad();
			
			String crrClass= cwp.getCurrentClassWorkitemReclassify().getText();
			if(crrClass.equals(currentClass)){
				Reporter.log("Current class is: "+currentClass,true);
			}else{
				Reporter.log("Current class is not as expected",true);
				Assert.fail("Current class is not as expected");
			}
			
			Select sel2 = new Select(cwp.getNewClassDropDownWorkitemReclassify());
			sel2.selectByIndex(0);
			
			WebElement option = sel2.getFirstSelectedOption();
			String selectedNewClass = option.getText();
			Reporter.log("Selected class is: "+selectedNewClass,true);
			
			
			boolean f =cwp.getRetainFormDataChkBoxWorkitemReclassify().isSelected();
			if(!f){
				cwp.getRetainFormDataChkBoxWorkitemReclassify().click();
				Reporter.log("Retain form data check box checked",true);
			}
			
			cwp.getReClassifyCloseBtn().click();
			Reporter.log("Clicked on Close button",true);
			util.waitForPageToLoad();
			
			cwp.getActionBtn(documentName).click();
			cwp.getformFiledsBtn().click();
			util.waitForPageToLoad();
			String formFieldvalue1 = cwp.getID_CODE_Tf().getAttribute("value");
			if(formFieldvalue1.equals(formField)){
				Reporter.log("Form Data Retained after canceling reclassify workitem as expected",true);
			}else{
				Reporter.log("Form Data not Retained after canceling reclassify workitem",true);
				Assert.fail("Form Data not Retained after canceling reclassify workitem");
			}
			cwp.getCloseFormBtn().click();
			
			cwp.getActionBtn(documentName).click();
			cwp.getMetaData().click();
			util.waitForPageToLoad();
			
			boolean f1 = util.verifyObjectPresentReturnsBool(cwp.getMetaDataWorkItem(documentName));
			boolean f2 = util.verifyObjectPresentReturnsBool(cwp.getMetaDataClassName(currentClass));
			
			if(f1 && f2){
				Reporter.log("Reclassification successfully canceled as expected",true);
			}else{
				Reporter.log("Reclassification failed",true);
				Assert.fail("Reclassification failed");
			}
			
			cwp.getMetaDataDialogBoxCloseBtn(documentName).click();
				

		}catch(Exception e){
			e.printStackTrace();
			Reporter.log("failed to execute performReclassifyWithCloseOptionWithRetainingData test",true);
			Assert.fail("failed to execute performReclassifyWithCloseOptionWithRetainingData test");
		}finally {
			hp.getWorkItemTab().click();
			util.waitForPageToLoad();
			cwp.deleteWorkItem(documentName);
		}
	}
}
