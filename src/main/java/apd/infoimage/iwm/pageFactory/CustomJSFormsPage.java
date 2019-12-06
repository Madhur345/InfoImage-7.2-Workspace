package apd.infoimage.iwm.pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import apd.infoimage.iwm.projectLib.ServerClassIWM;

/**
 * @author PradhanJ
 *
 */
public class CustomJSFormsPage extends ServerClassIWM{

	@FindBy(xpath="//div[@id='content']//span[contains(text(),'Custom JS - Forms')]")
	private WebElement customJSFormsHeader;

	public WebElement getCustomJSFormsHeader() {
		return customJSFormsHeader;
	}	
	@FindBy(xpath="//div[text()='archive']")
	private WebElement availableClassArchive;

	public WebElement getAvailableClassArchive() {
		return availableClassArchive;
	}
	@FindBy(xpath="//textarea[@id='_customJS_WAR_iwmportlet_INSTANCE_pi3FP7oA2BK9_javascript']")
	//@FindBy(xpath="//div[@id='formScript']//textarea")
	private WebElement javaScriptTextArea;

	public WebElement getJavaScriptTextArea() {
		return javaScriptTextArea;
	}
	@FindBy(xpath="//button[@id='applyJs']")
	private WebElement applyScriptButton;

	public WebElement getApplyScriptButton() {
		return applyScriptButton;
	}
	@FindBy(xpath="//button[@id='saveJs']")
	private WebElement saveScriptButton;

	public WebElement getSaveScriptButton() {
		return saveScriptButton;
	}
	@FindBy(xpath="//button[@id='publishJs']")
	private WebElement publishScriptButton;

	public WebElement getPublishScriptButton() {
		return publishScriptButton;
	}
	@FindBy(xpath="//div[@id='successDialog']")
	private WebElement successMsgDialog;

	public WebElement getSuccessMsgDialog() {
		return successMsgDialog;
	}
	@FindBy(xpath="//span[@id='ui-id-2']/..//button")
	private WebElement closeSucMsgDialogIcon;

	public WebElement getCloseSucMsgDialogIcon() {
		return closeSucMsgDialogIcon;
	}

	@FindBy(xpath="//input[@id='ID_CODE_1']")
	private WebElement id_codeFormField;

	public WebElement getId_codeFormField() {
		return id_codeFormField;
	}
	@FindBy(xpath="//span[@id='ui-id-2']")
	private WebElement idCodeCustomJSPopup;
		
	public WebElement getIdCodeCustomJSPopup() {
		return idCodeCustomJSPopup;
	}

	@FindBy(xpath="//input[@id='mvfAddNew']")
	private WebElement addNewTextBox;
	
	public WebElement getAddNewTextBox() {
		return addNewTextBox;
	}

	@FindBy(xpath="//input[@id='mvfAdd']")
	private WebElement addNewBtn;
	
	public WebElement getAddNewBtn() {
		return addNewBtn;
	}
	@FindBy(xpath="//input[@id='mvfUpdateForm']")
	private WebElement updateFieldCloseBtn;
	
	public WebElement getUpdateFieldCloseBtn() {
		return updateFieldCloseBtn;
	}
	
	@FindBy(xpath="//input[@id='close_btn']")
	private WebElement closePopupBtn;
	
	public WebElement getClosePopupBtn() {
		return closePopupBtn;
	}

	//Method to apply save and publish custom JS script
	public void applySavePublishCustomJS()
	{
		try
		{
			getAvailableClassArchive().click();
			util.waitForPageToLoad();

			getApplyScriptButton().click();

			//Validate whether script is applied successfully or not.

			String sucMsg=getSuccessMsgDialog().getText();

			if(sucMsg.contains("applied Successfully"))
			{
				Reporter.log("Script is applied sucessfully.", true);

			}
			else
			{
				Reporter.log("Script is Not applied   sucessfully.", true);
				Assert.fail("Script is Not applied sucessfully.");
			}
			//Click on save script button and validate if saved or not.
			getCloseSucMsgDialogIcon().click();
			Thread.sleep(2000);
			getSaveScriptButton().click();
			util.waitForPageToLoad();
			Thread.sleep(4000);
			//Validate whether script saved successfully or not

			String sMsg=getSuccessMsgDialog().getText();
			if(sMsg.contains("saved Successfully"))
			{
				Reporter.log("Script is saved sucessfully.", true);

			}
			else
			{
				Reporter.log("Script is Not saved  sucessfully.", true);
				Assert.fail("Script is Not saved sucessfully.");
			}
			Thread.sleep(2000);
			//Click on Publish script button and validate 
			getCloseSucMsgDialogIcon().click();
			Thread.sleep(5000);
			getPublishScriptButton().click();
			util.waitForPageToLoad();
			Thread.sleep(2000);
			String publishMsg=getSuccessMsgDialog().getText();

			if(publishMsg.contains("published Successfully"))
			{
				Reporter.log("Script is published sucessfully.", true);

			}
			else
			{
				Reporter.log("Script is Not published  sucessfully.", true);
				Assert.fail("Script is Not publisheds sucessfully.");
			}
			Thread.sleep(2000);
			getCloseSucMsgDialogIcon().click();

			//Validate whether custom JS is applied in Formields color

			util.waitForPageToLoad();

			String bgColor_IDCode=getId_codeFormField().getAttribute("style");
			if(bgColor_IDCode.contains("background-color: rgb"))
			{
				Reporter.log("ID_CODE formField background color has been changed", true);

			}
			else
			{
				Reporter.log("ID_CODE formField background color is not changed", true);
				Assert.fail("ID_CODE formField background color is not changed");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Reporter.log("Applying custom JS script failed..", true);
		}

	}

}
