package apd.infoimage.iwm.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.ServerClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

/**
 * @author PradhanJ
 *
 */
public class UserPreferencesPage extends ServerClassIWM{


	@FindBy(xpath="//span[text()='User Preferences']")
	private WebElement userPreferencePageHeader;

	public WebElement getUserPreferencePageHeader() {
		return userPreferencePageHeader;
	}

	@FindBy(xpath="//input[@value='Workitems']")
	private WebElement workitemRadioBtn;

	@FindBy(xpath="//input[@value='Inbox']")
	private WebElement inboxRadioBtn;

	@FindBy(xpath="//input[@value='Search']")
	private WebElement searchRadioBtn;

	@FindBy(xpath="//input[@id='viewNamePreference']")
	private WebElement viewNameTextbox;

	@FindBy(xpath="(//button[contains(@class,'btn move-left')])[1]")
	private WebElement moveAvailableToSelectedBtn;

	@FindBy(xpath="(//button[contains(@class,'btn move-right')])[1]")
	private WebElement moveSelectedToAvailableBtn;

	@FindBy(xpath="//button[@onclick='createView()']")
	private WebElement createViewBtn;

	@FindBy(xpath="(//button[contains(@class,'btn reorder-up')])[1]")
	private WebElement upArrowBtn;

	@FindBy(xpath="(//button[contains(@class,'btn reorder-down')])[1]")
	private WebElement downArrowBtn;

	public WebElement getWorkitemRadioBtn() {
		return workitemRadioBtn;
	}

	public WebElement getInboxRadioBtn() {
		return inboxRadioBtn;
	}

	public WebElement getSearchRadioBtn() {
		return searchRadioBtn;
	}

	public WebElement getViewNameTextbox() {
		return viewNameTextbox;
	}

	public WebElement getMoveAvailableToSelectedBtn() {
		return moveAvailableToSelectedBtn;
	}

	public WebElement getMoveSelectedToAvailableBtn() {
		return moveSelectedToAvailableBtn;
	}

	public WebElement getCreateViewBtn() {
		return createViewBtn;
	}

	public WebElement getUpArrowBtn() {
		return upArrowBtn;
	}

	public WebElement getDownArrowBtn() {
		return downArrowBtn;
	}

	@FindBy(xpath="(//select[contains(@id,'availableValues')])[1]")
	private WebElement availableFields;

	public WebElement getAvailableFields() {
		return availableFields;
	}

	@FindBy(xpath="//h4[contains(text(),'List of views created')]")
	private WebElement viewsCreatedMsg;

	public WebElement getViewsCreatedMsg() {
		return viewsCreatedMsg;
	}

	public WebElement getCreatedViewNameRadio(String vName) {
		WebElement createdViewNameRadio=Driver.driver.findElement(By.xpath("//input[@value='"+vName+"']"));
		return createdViewNameRadio;
	}
	@FindBy(xpath="//button[@id='deleteView']")
	private WebElement deleteViewBtn;

	@FindBy(xpath="//button[@id='applyView']")
	private WebElement applyViewBtn;

	@FindBy(xpath="//button[@id='resetView']")
	private WebElement resetViewBtn;

	public WebElement getDeleteViewBtn() {
		return deleteViewBtn;
	}

	public WebElement getApplyViewBtn() {
		return applyViewBtn;
	}

	public WebElement getResetViewBtn() {
		return resetViewBtn;
	}

	@FindBy(xpath="//span[text()='OK']")
	private WebElement deleteViewOKBtn;

	public WebElement getDeleteViewOKBtn() {
		return deleteViewOKBtn;
	}

	@FindBy(xpath="//span[text()='Cancel']")
	private WebElement deleteViewCancelBtn;

	public WebElement getDeleteViewCancelBtn() {
		return deleteViewCancelBtn;
	}

	@FindBy(xpath="//div[contains(text(),'Request Processed Successfully')]")
	private WebElement successMsg;

	public WebElement getSuccessMsg() {
		return successMsg;
	}


	//Added by Suman : 04-Jan-2018. Method to select field from available 

	@FindBy(xpath="//label[@class='control-label']")
	private WebElement availableBtn;

	public WebElement getAvailableBtn() {
		return availableBtn;
	}
	public void selectFieldFromAvailable(String f1)
	{
		getAvailableBtn().click();
		Select sel=new Select(availableFields);

		sel.selectByVisibleText(f1);		
		getMoveAvailableToSelectedBtn().click();
	}

	//Method to select fields from available 

	@SuppressWarnings("deprecation")
	public void selectFieldsFromAvailable(String f1,String f2,String f3)
	{
		try
		{
			Select sel=new Select(availableFields);

			sel.selectByVisibleText(f1);
			Thread.sleep(2000);
			moveAvailableToSelectedBtn.click();
			Thread.sleep(2000);
			sel.selectByVisibleText(f2);
			Thread.sleep(2000);
			moveAvailableToSelectedBtn.click();
			Thread.sleep(2000);
			sel.selectByVisibleText(f3);
			Thread.sleep(2000);
			moveAvailableToSelectedBtn.click();
			Thread.sleep(4000);
			Reporter.log("Fields selected..	", true);
			ATUReports.add("Fields  selected are "+f1+" "+f2+"  "+f3, true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("Moving available fields to selected is failed", LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Moving available fields to selected is failed");
		}

	}
	//Method to validate view  is created or not.
	@SuppressWarnings("deprecation")
	public void validateViewCreated(String viewName)
	{
		try
		{
			boolean createdViewMsgPresent=util.verifyObjectPresentReturnsBool(viewsCreatedMsg);
			if(createdViewMsgPresent)
			{
				Reporter.log("List of views created message is dispalyed.", true);
				ATUReports.add("List of views created message is dispalyed.", true);
			}
			else
			{
				Reporter.log("List of views created message is Not dispalyed.", true);
				ATUReports.add("List of views created message is Not dispalyed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("List of views created message is Not dispalyed.");
			}
			String vNameDispalyed=getCreatedViewNameRadio(viewName).getAttribute("value");

			if(vNameDispalyed.equalsIgnoreCase(viewName))
			{
				Reporter.log("Created View name is successfully validated ", true);
				ATUReports.add("Created View name is successfully validated ", true);
			}
			else
			{
				Reporter.log("Created View name validation failed", true);
				ATUReports.add("Created View name validation failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
				Assert.fail("Created View name validation failed");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public WebElement getCreatedViewNameRadioInbox(String vName) {
		WebElement createdViewNameRadioInbox=Driver.driver.findElement(By.xpath("//input[@value='"+vName+"']"));
		return createdViewNameRadioInbox;
	} 

	//Method to delete a view 
	@SuppressWarnings("deprecation")
	public void deleteView(String viewType,String vname)
	{
		try
		{
			if(viewType.equals("Workitems"))
			{
				workitemRadioBtn.click();
				Reporter.log("Workitem Viewtype radio button is clicked", true);
				ATUReports.add("Workitem Viewtype radio button is clicked", true);
			}
			if(viewType.equals("Inbox"))
			{
				inboxRadioBtn.click();
				Reporter.log("Inbox Viewtype radio button is clicked", true);
				ATUReports.add("Inbox Viewtype radio button is clicked", true);
			}
			if(viewType.equals("Search"))
			{
				searchRadioBtn.click();
				Reporter.log("Search Viewtype radio button is clicked", true);
				ATUReports.add("Search Viewtype radio button is clicked", true);
			}
			util.wait(5000);
			upp.getCreatedViewNameRadio(vname).click();
			Reporter.log("Created view name radio button is clicked", true);
			ATUReports.add("Created view name radio button is clicked", true);

			upp.getDeleteViewBtn().click();
			Reporter.log("Delete view button is clicked", true);
			ATUReports.add("Delete view button is clicked", true);

			upp.getDeleteViewOKBtn().click();
			Reporter.log("OK button in alert is clicked",true);
			ATUReports.add("OK button in alert is clicked", true);

			util.waitForPageToLoad();
			Thread.sleep(2000);
			Reporter.log("View is deleted successfully.", true);
			ATUReports.add("View is deleted successfully.", true);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("Deletion of view test failed", true);
			Log.error("Deletion of view test failed"+e.getMessage());
			Assert.fail("Deletion of view test failed");
		}
	}

	//Suman - 23-May-2018
	public WebElement getCreatedViewNameRadioWorkitems(String vName) {
		WebElement createdViewNameRadioWorkitems=Driver.driver.findElement(By.xpath("//input[@value='"+vName+"']"));
		return createdViewNameRadioWorkitems;
	}
	
	//Added By Suman - 27-Nov-2018
	@FindBy(xpath="//span[contains(text(),'OK')]")
	private WebElement alertOKButton;

	public WebElement getAlertOKButton() {
		return alertOKButton;
	}
	
}
