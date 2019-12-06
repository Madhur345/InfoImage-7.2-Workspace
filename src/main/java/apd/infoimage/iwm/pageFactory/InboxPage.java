package apd.infoimage.iwm.pageFactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
 * @author SumanGaK
 *
 */
public class InboxPage extends ServerClassIWM {

	@FindBy(linkText = "Data Entry")
	private WebElement DataEntry;

	public WebElement getDataEntry() {
		return DataEntry;
	}

	//@FindBy(linkText = " Invoice Processing ")
	@FindBy(xpath = "//a[text()=' Invoice Processing ']")
	private WebElement invoiceProcessing;

	public WebElement getInvoiceProcessing() {
		return invoiceProcessing;
	}

	@FindBy(linkText = "Invoice ProcError")
	private WebElement InvoiceProcessingError;

	public WebElement getInvoiceProcessingError() {
		return InvoiceProcessingError;
	}

	@FindBy(linkText = "Faxin")
	private WebElement Faxin;

	public WebElement getFaxin() {
		return Faxin;
	}

	@FindBy(linkText = "Custom")
	private WebElement Custom;

	public WebElement getCustom() {
		return Custom;
	}

	@FindBy(id = "reserveWorkitem_btn")
	private WebElement Retrieve;

	public WebElement getRetrieve() {
		return Retrieve;
	}

	// vinay
	@FindBy(xpath = "//a[contains(@href,'Invoice+ProcError')]")
	private WebElement invoiceProcessingErr;

	public WebElement getInvoiceProcessingErr() {
		return invoiceProcessingErr;
	}

	// vinay
	@FindBy(xpath = "//a[contains(@href,'Terms')]")
	private WebElement termsQueue;

	public WebElement getTermsQueue() {
		return termsQueue;
	}

	// vinay
	@FindBy(xpath = "//a[contains(@href,'RM+Auto+Filed')][not(contains(@href,'RM+Auto+FiledError'))]")
	private WebElement rmAutoField;

	public WebElement getRmAutoField() {
		return rmAutoField;
	}

	// Added By Suman - 13/11/2017
	@FindBy(xpath = "//i[@class='actionRetvOpnIcon']")
	private WebElement retrieveAndOpen;

	public WebElement getRetrieveAndOpen() {
		return retrieveAndOpen;
	}

	@FindBy(xpath = "//span[@class='openRetrieveActionTD openRetrieveIconTD']")
	private WebElement retrieveAndOpenBtn;

	public WebElement getRetrieveAndOpenButton() {
		return retrieveAndOpenBtn;
	}

	@FindBy(xpath = "//button[@class='actionBtn']")
	private WebElement actionsBtn;

	public WebElement getActionsBtn() {
		return actionsBtn;
	}

	// Suman - 08-DEC-2017
	public void searchByNameInFaxIn(String wiName) {
		try {
			Thread.sleep(3000);
			Select s1 = new Select(nameSearchingDropdown);
			s1.selectByVisibleText("Name");
			Thread.sleep(5000);
			searchFieldInWorkStep.clear();
			searchFieldInWorkStep.sendKeys(wiName);
			searchFieldInWorkStep.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		} catch (Exception e) {
			Reporter.log("Searching item failed", true);
		}
	}

	// vinay
	public WebElement getRetriveAndOpenBtn(String workItem) {
		WebElement retriveAndOpenBtn = Driver.driver.findElement(By.xpath("//td[span/a/font[text()='" + workItem
				+ "']]/preceding-sibling::td/span/i[@class='actionRetvOpnIcon']"));
		return retriveAndOpenBtn;
	}

	// Jayashri
	@FindBy(xpath = "//select[@id='columnNameForSearching']")
	private WebElement nameSearchingDropdown;

	public WebElement getNameSearchingDropdown() {
		return nameSearchingDropdown;
	}

	// Jayashri
	@FindBy(xpath = "//input[@id='searchField']")
	private WebElement searchFieldInDataEntry;

	public WebElement getSearchFieldInDataEntry() {
		return searchFieldInDataEntry;
	}

	// Suman - 08-DEC-2017
	@FindBy(xpath = "//input[@id='searchField']")
	private WebElement searchFieldInWorkStep;

	public WebElement getSearchFieldInWorkStep() {
		return searchFieldInWorkStep;
	}

	// suman - 24-Nov-2017
	@FindBy(xpath = "//tr[1]/td[1]/button[@class='actionBtn']")
	private WebElement firstRowFirstCell;

	public WebElement getFirstRowFirstCell() {
		return firstRowFirstCell;
	}

	@FindBy(xpath = "//tr[2]/td[1]/button[@class='actionBtn']")
	private WebElement secondRowFirstCell;

	public WebElement getSecondRowFirstCell() {
		return secondRowFirstCell;
	}

	@FindBy(xpath = "//i[@class='actionMetaDataIcon']")
	private WebElement inboxMetaData;

	public WebElement getInboxMetaData() {
		return inboxMetaData;
	}

	@FindBy(xpath = "//i[@class='actionFormFieldIcon']")
	private WebElement inboxFormFields;

	public WebElement getInboxFormFields() {
		return inboxFormFields;
	}

	@FindBy(xpath = "//i[@class='copyURLicn']")
	private WebElement inboxCopyURL;

	public WebElement getInboxCopyURL() {
		return inboxCopyURL;
	}

	// Suman - 16-Jan-2018
	@FindBy(xpath = "//input[@id='searchField']")
	private WebElement searchFieldInWorkitemTab;

	public WebElement getSearchFieldInWorkitemTab() {
		return searchFieldInWorkitemTab;
	}

	@FindBy(xpath = "//select[@id='columnNameForSearching']")
	private WebElement inboxDropDownForSearch;

	public WebElement getInboxDropDownForSearch() {
		return inboxDropDownForSearch;
	}

	// Added By Suman - 12-Jan-2018
	@FindBy(xpath = "//div[text()='OBJECT_ID']")
	private WebElement QueryFieldAsOBJECTIDColumn;

	public WebElement getQueryFieldAsOBJECTIDColumn() {
		return QueryFieldAsOBJECTIDColumn;
	}

	@FindBy(xpath = "//div[text()='OBJECT_NAME']")
	private WebElement QueryFieldAsOBJECTNAMEColumn;

	public WebElement getQueryFieldAsOBJECTNAMEColumn() {
		return QueryFieldAsOBJECTNAMEColumn;
	}

	@FindBy(xpath = "//div[text()='OBJECT_TYPE']")
	private WebElement QueryFieldAsOBJECTTYPEColumn;

	public WebElement getQueryFieldAsOBJECTTYPEColumn() {
		return QueryFieldAsOBJECTTYPEColumn;
	}

	// Added by Suman - 15-Jan-2018
	public WebElement getWorkItemName(String workItem) {
		WebElement createdWorkItem = Driver.driver.findElement(By.xpath("//font[text()='" + workItem + "']"));
		return createdWorkItem;
	}

	// Added By Suman - 4-Jan-2018
	@FindBy(xpath = "//div[text()='INTVAR1']")
	private WebElement QueryFieldAsINTVAR1Column;

	public WebElement getQueryFieldAsINTVAR1Column() {
		return QueryFieldAsINTVAR1Column;
	}

	public WebElement getRetrieve(String witem) {
		WebElement retrieve = Driver.driver.findElement(By.xpath(
				"//td[span/a/font[text()='" + witem + "']]/preceding-sibling::td/span/i[@class='actionRetvOpnIcon']"));
		return retrieve;
	}

	// Jayashri
	public void searchByNameInDataEntry(String wiName) {
		try {
			Thread.sleep(3000);
			Select s1 = new Select(nameSearchingDropdown);
			s1.selectByVisibleText("Name");
			Thread.sleep(2000);
			searchFieldInDataEntry.clear();
			Thread.sleep(2000);
			searchFieldInDataEntry.sendKeys(wiName);
			searchFieldInDataEntry.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		} catch (Exception e) {
			Reporter.log("Searching item failed", true);
			ATUReports.add("Searching workitem in data entry failed", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.error(e.getMessage());
		}
	}

	/**
	 * This method retrieves a WorkItem.
	 * 
	 * @author SumanGaK
	 * @param Workitem
	 * @throws Exception
	 */

	/*
	 * public void retrieveWorkItem(String workitem) { try{ //hp.getInbox().click();
	 * //DataEntry.click();
	 * 
	 * Thread.sleep(2000); boolean f7 =
	 * util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem)); if(f7){
	 * Reporter.log("Workitem to be Retrieved is present in grid",true); }else{
	 * Reporter.log("Workitem to be Retrieved is not present in grid",true);
	 * Assert.fail("Workitem to be Retrieved is not present in grid "); }
	 * 
	 * cwp.getCheckBoxWorkItemName(workitem).click();
	 * 
	 * Thread.sleep(2000);
	 * 
	 * Retrieve.click();
	 * 
	 * hp.getWorkItemTab().click(); util.waitForPageToLoad(); boolean f4 =
	 * util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem)); if(f4){
	 * Reporter.log("Retrieved Workitem is present in grid",true); }else{
	 * Reporter.log("Retrieved Workitem is not present in grid",true);
	 * Assert.fail("Retrieved Workitem is not present in grid "); } } catch
	 * (Exception e) {
	 * Reporter.log("Retrieved Workitem failed due to "+e.getMessage());
	 * Assert.fail("Retrieved Workitem failed Assert"); } }
	 */

	@SuppressWarnings("deprecation")
	public void retrieveWorkItem(String workitem) {
		try {
			// hp.getInbox().click();
			// DataEntry.click();

			// Thread.sleep(2000);
			boolean f7 = util.verifyObjectPresentReturnsBool(getWorkItemName(workitem));
			if (f7) {
				Reporter.log("Workitem to be Retrieved is present in grid", true);
				ATUReports.add("Workitem to be Retrieved is present in grid", true);
			} else {
				Reporter.log("Workitem to be Retrieved is not present in grid", true);
				Assert.fail("Workitem to be Retrieved is not present in grid ");
			}
			Thread.sleep(3000);
//			cwp.searchByNameInWorkitemList(workitem);
			getCheckBoxWorkItemName(workitem).click();
			Reporter.log("Workitem Check Box to be Retrieved is clicked", true);

			// Thread.sleep(2000);

			Retrieve.click();
			Reporter.log("Retrieve Button is clicked", true);
			ATUReports.add("Retrieve Button is clicked", true);
			// WebElement
			// retrieve=Driver.driver.findElement(By.xpath("//td[span/a/font[text()='"+workitem+"']]/preceding-sibling::td/span/i[@class='actionRetvOpnIcon']"));
			// retrieve.click();

			// Thread.sleep(5000);
			// wdp.getCloseWorkitemIcon(workitem);
			Thread.sleep(5000);
			util.waitForPageToLoad();
			hp.getWorkItemTab().click();
			Thread.sleep(3000);
			util.waitForPageToLoad();
			Reporter.log("Workitem tab is clicked", true);
			ATUReports.add("Workitem tab is clicked", true);
			util.waitForPageToLoad();
			searchByNameInDataEntry(workitem);
			Reporter.log("Searching By Workitem Name in workitem list", true);
			ATUReports.add("Searching By Workitem Name in workitem list", true);
			boolean f4 = util.verifyObjectPresentReturnsBool(getWorkItemName(workitem));
			if (f4) {
				Reporter.log("Retrieved Workitem is present in grid", true);
				ATUReports.add("Retrieved Workitem is present in grid", true);
			} else {
				Reporter.log("Retrieved Workitem is not present in grid", true);
				Assert.fail("Retrieved Workitem is not present in grid ");
			}
		} catch (Exception e) {
			Reporter.log("Retrieved Workitem failed due to " + e.getMessage());
			Log.error(e.getMessage());
			Assert.fail("Retrieved Workitem failed Assert");
		}
	}

	// Suman - 16-Jan-2018
	public void searchByNameInWorkitemTab(String wiName) {
		try {
			Thread.sleep(3000);
			Select s1 = new Select(nameSearchingDropdown);
			s1.selectByVisibleText("Name");
			Thread.sleep(5000);
			searchFieldInWorkitemTab.sendKeys(wiName);
			Thread.sleep(3000);
		} catch (Exception e) {
			Reporter.log("Searching item failed", true);
		}
	}

	/**
	 * This method retrieves a WorkItem.
	 * 
	 * @author SumanGaK
	 * @param Workitem
	 * @throws Exception
	 */

	public void retrieveWorkItemFaxin(String workitem) {
		try {
			hp.getInbox().click();
			Faxin.click();

			Thread.sleep(2000);
			boolean workitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (workitemPresence) {
				Reporter.log("Workitem to be Retrieved is present in grid", true);
			} else {
				Reporter.log("Workitem to be Retrieved is not present in grid", true);
				Assert.fail("Workitem to be Retrieved is not present in grid ");
			}

			cwp.getCheckBoxWorkItemName(workitem).click();

			Thread.sleep(2000);

			Retrieve.click();
			Thread.sleep(2000);
			ip.getWorkItemTab().click();
			util.waitForPageToLoad();
			boolean retrievedWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (retrievedWorkitemPresence) {
				Reporter.log("Retrieved Workitem is present in grid", true);
			} else {
				Reporter.log("Retrieved Workitem is not present in grid", true);
				Assert.fail("Retrieved Workitem is not present in grid ");
			}
		} catch (Exception e) {
			Reporter.log("Retrieved Workitem failed due to " + e.getMessage());
			Assert.fail("Retrieved Workitem failed Assert");
		}
	}

	// Xpaths required to validate views in Inbox page
	// Added by Jayashri-01/16/2018

	@FindBy(xpath = "//div[text()='CLASS']")
	private WebElement header_Class;

	@FindBy(xpath = "//div[text()='DOMAIN']")
	private WebElement header_Domain;

	@FindBy(xpath = "//div[text()='OBJECT_ID']")
	private WebElement header_ObjectId;

	public WebElement getHeader_Class() {
		return header_Class;
	}

	public WebElement getHeader_Domain() {
		return header_Domain;
	}

	public WebElement getHeader_ObjectId() {
		return header_ObjectId;
	}

	// Validate whether headers are updated as per view or not
	public boolean verifyHeadersInInbox() {
		if (header_Class.isDisplayed() && header_Domain.isDisplayed() && header_ObjectId.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	// Added by Suman - 26-Mar-2018
	@FindBy(xpath = "//a/span[contains(text(),'Workitems')]")
	private WebElement WorkItems;

	public WebElement getWorkItemsTab() {
		return WorkItems;
	}

	// Added by Suman - 27-Mar-2018
	@FindBy(xpath = "//font[@class='WorkitemNameColor']")
	private WebElement firstWorkitem;

	public WebElement getFirstWorkitem() {
		return firstWorkitem;
	}

	// Added By Suman - 27-Mar-2018
	@FindBy(xpath = "//span[@id='getNextStatus']")
	private WebElement getnextONButton;

	public WebElement getNextONButton() {
		return getnextONButton;
	}

	@FindBy(xpath = "//input[@id='getNextAutoOpen']")
	private WebElement getnextAutoOpenCheckbox;

	public WebElement getNextAutoOpenCheckbox() {
		return getnextAutoOpenCheckbox;
	}

	@FindBy(xpath = "//input[@id='requiredNoOfWorkitems']")
	private WebElement getrequiredNoOfWorkitems;

	public WebElement getRequiredNoOfWorkitems() {
		return getrequiredNoOfWorkitems;
	}

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement saveButton;

	public WebElement getSaveButton() {
		return saveButton;
	}

	// Biswajit 17/04/2018
	@FindBy(xpath = "//tbody[@id='itemContainer']/tr[1]/td[2]/span/a/font")
	private WebElement QueueWorkItem1;

	public WebElement getQueueWorkItem1() {
		return QueueWorkItem1;
	}

	// Biswajit 17/04/2018
	@FindBy(xpath = "//a[contains(@href,'MyPersonal')]")
	private WebElement myPersonal;

	public WebElement getmyPersonal() {
		return myPersonal;
	}

	// Biswajit 16/04/2018
	@FindBy(xpath = "//input[@id='requiredNoOfWorkitems']")
	private WebElement requiredNoOfWorkitems;

	public WebElement getrequiredNoOfWorkitems() {
		return requiredNoOfWorkitems;
	}

	// Biswajit 16/04/2018
	@FindBy(xpath = "//input[@type='checkbox'][@name='getNextAutoOpen']")
	private WebElement autoViewOn;

	public WebElement getautoViewOn() {
		return autoViewOn;
	}

	// Biswajit 16/04/2018
	@FindBy(xpath = "//span[@id='getNextStatus']")
	private WebElement nextStatusOn;

	public WebElement getNextStatusOn() {
		return nextStatusOn;
	}

	// Jayashri 04/18/2018
	@FindBy(xpath = "//LABEL[@id='QueueuNames']")
	private WebElement queueNameLabelInInbox;

	public WebElement getQueueNameLabelInInbox() {
		return queueNameLabelInInbox;
	}

	// Biswajit 19/04/2018
	@FindBy(xpath = "//table[@id='workitemTable']/tbody/tr")
	private List<WebElement> workItemList;

	public List<WebElement> getworkItemList() {
		return workItemList;
	}

	// Biswajit 19/04/2018
	@FindBy(xpath = "//tbody[@id='itemContainer']/tr")
	private List<WebElement> QueueWorkItemCount;

	public List<WebElement> getQueueWorkItemCount() {
		return QueueWorkItemCount;
	}

	// Biswajit 18/04/2018
	@FindBy(xpath = "//tbody[@id='itemContainer']/tr[2]/td[2]/span//font")
	private WebElement QueueWorkItem2;

	public WebElement getQueueWorkItem2() {
		return QueueWorkItem2;
	}

	// Biswajit 18/04/2018
	@FindBy(xpath = "//div[@id='recentWoritemsTab']/ul/li/i")
	private WebElement WorkItemCloseBt;

	public WebElement getWorkItemCloseBt() {
		return WorkItemCloseBt;
	}

	// Biswajit 19/04/2018
	@FindBy(xpath = "//div[@id='recentWoritemsTab']/ul/li/a")
	private WebElement WorkItemTextValue;

	public WebElement getWorkItemTextValue() {
		return WorkItemTextValue;
	}

	// Biswajit 18/04/2018
	@FindBy(xpath = "//span[@id='yui_patched_v3_11_0_1_1524017631634_526']")
	private WebElement WorkItemName1;

	public WebElement getWorkItemName1() {
		return WorkItemName1;
	}

	@FindBy(linkText = "MyPersonal")
	private WebElement myPersonalQueue;

	public WebElement getMyPersonalQueue() {
		return myPersonalQueue;
	}

	// Suman - 11-Apr-2018
	@FindBy(xpath = "//input[@id='searchField']")
	private WebElement searchFieldInMyPersonalQueue;

	public WebElement getSearchFieldInMyPersonalQueue() {
		return searchFieldInMyPersonalQueue;
	}

	/**
	 * This method searches a WorkItem in My Personal queue.
	 * 
	 * @author SumanGaK
	 * @param Workitem
	 * @throws Exception
	 */
	public void searchByNameInMyPersonalQueue(String wiName) {
		try {
			Thread.sleep(3000);
			Select s1 = new Select(nameSearchingDropdown);
			s1.selectByVisibleText("Name");

			Thread.sleep(5000);
			searchFieldInMyPersonalQueue.sendKeys(wiName);
			searchFieldInMyPersonalQueue.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		} catch (Exception e) {
			Reporter.log("Searching item failed", true);
		}
	}

	// Biswajit 25/04/2018
	@FindBy(xpath = "//table[@id='workitemTable']/tbody/tr")
	private List<WebElement> DataEntryworkItemList;

	public List<WebElement> getDataEntryworkItemList() {
		return DataEntryworkItemList;
	}

	// Biswajit 25/04/2018
	@FindBy(xpath = "//tbody[@id='itemContainer']/tr[1]/td[2]/span[2]/a/font")
	private WebElement DataEntryFirstWorkItem;

	public WebElement getDataEntryFirstWorkItem() {
		return DataEntryFirstWorkItem;
	}

	// Biswajit 25/04/2018
	@FindBy(xpath = "//div[@class='actBtnHldr']/span/button[1]/i")
	private WebElement Actions;

	public WebElement getActions() {
		return Actions;
	}

	// Biswajit 26/04/2018
	@FindBy(xpath = "//div[@id='actionDetailPopup']//li[3]")
	private WebElement SendToDefault;

	public WebElement getSendToDefault() {
		return SendToDefault;
	}

	// Biswajit 07/05/2018
	@FindBy(xpath = "//div[@id='formFieldItem']/h2[1]")
	private WebElement MaximizeFormField;

	public WebElement getMaximizeFormField() {
		return MaximizeFormField;
	}

	// Biswajit 09/05/2018
	@FindBy(xpath = "//div[@id='noWorkitemsMessage']/center")
	private WebElement noWorkitemsMessage;

	public WebElement getnoWorkitemsMessage() {
		return noWorkitemsMessage;
	}

	// Added by Suman
	public WebElement getCheckBoxWorkItemName(String workItem) {
		WebElement checkBoxWorkItemName = Driver.driver
				.findElement(By.xpath("(//td[span/a/font[text()='" + workItem + "']]/preceding-sibling::td/input)[1]"));
		return checkBoxWorkItemName;
	}

	/**
	 * This method retrieves a WorkItem from Invoice Processing.
	 * 
	 * @author SumanGaK
	 * @param Workitem
	 * @throws Exception
	 */

	public void retrieveWorkItemFromInvoiceProcessing(String workitem) {
		try {
			hp.getInbox().click();
			Thread.sleep(3000);
			getInvoiceProcessing().click();

			Thread.sleep(2000);
			boolean workitemToBeRetrievedPresence = util.verifyObjectPresentReturnsBool(ip.getWorkItemName(workitem));
			if (workitemToBeRetrievedPresence) {
				Reporter.log("Workitem to be Retrieved is present in grid", true);
			} else {
				Reporter.log("Workitem to be Retrieved is not present in grid", true);
				Assert.fail("Workitem to be Retrieved is not present in grid ");
			}

			ip.getCheckBoxWorkItemName(workitem).click();

			Thread.sleep(2000);

			Retrieve.click();
			Thread.sleep(2000);

			hp.getWorkItemTab().click();
			Thread.sleep(2000);
			util.waitForPageToLoad();
			boolean RetrievedWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (RetrievedWorkitemPresence) {
				Reporter.log("Retrieved Workitem is present in grid", true);
			} else {
				Reporter.log("Retrieved Workitem is not present in grid", true);
				Assert.fail("Retrieved Workitem is not present in grid ");
			}
		} catch (Exception e) {
			Reporter.log("Retrieved Workitem failed due to " + e.getMessage());
			Assert.fail("Retrieved Workitem failed Assert");
		}
	}

	// Added by Suman - 20-Jun-2018
	@FindBy(xpath = "//a/span[contains(text(),'Workitems')]")
	private WebElement workItemTab;

	public WebElement getWorkItemTab() {
		return workItemTab;
	}

	@FindBy(xpath = "//td[contains(text(),'Request Processed Successfully')]")
	private WebElement witemRetrievedSucMsg;

	public WebElement getRetrieveAndOpenBtn() {
		return retrieveAndOpenBtn;
	}

	public WebElement getWorkItems() {
		return WorkItems;
	}

	public WebElement getGetnextAutoOpenCheckbox() {
		return getnextAutoOpenCheckbox;
	}

	public WebElement getGetrequiredNoOfWorkitems() {
		return getrequiredNoOfWorkitems;
	}

	public WebElement getMyPersonal() {
		return myPersonal;
	}

	public WebElement getAutoViewOn() {
		return autoViewOn;
	}

	public List<WebElement> getWorkItemList() {
		return workItemList;
	}

	public WebElement getNoWorkitemsMessage() {
		return noWorkitemsMessage;
	}

	public WebElement getWitemRetrievedSucMsg() {
		return witemRetrievedSucMsg;
	}

	@FindBy(xpath = "//div[@aria-describedby='workitemreserveResult']//button")
	private WebElement closeIconRetrieveSucMsgAlert;

	public WebElement getCloseIconRetrieveSucMsgAlert() {
		return closeIconRetrieveSucMsgAlert;
	}

	/**
	 * This method retrieves a WorkItem from My personal queue
	 * 
	 * @author PradhanJ
	 * @param Workitem
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public void retrieveWorkItemFromMyPersonalqueue(String workitem) {
		try {

			boolean workitemToBeRetrievedPresence = util.verifyObjectPresentReturnsBool(ip.getWorkItemName(workitem));
			if (workitemToBeRetrievedPresence) {
				Reporter.log("Workitem to be Retrieved is present in grid", true);
			} else {
				Reporter.log("Workitem to be Retrieved is not present in grid", true);
				Assert.fail("Workitem to be Retrieved is not present in grid ");
			}

			ip.getCheckBoxWorkItemName(workitem).click();
			Thread.sleep(2000);

			Retrieve.click();
			Thread.sleep(2000);

			boolean successMsgAlertPresent = util.verifyObjectPresentReturnsBool(witemRetrievedSucMsg);
			if (successMsgAlertPresent) {
				Reporter.log("Workitem retrieved success message alert is present", true);
				ATUReports.add("Workitem retrieved success message alert is present", true);
				ip.getCloseIconRetrieveSucMsgAlert().click();
			} else {
				Reporter.log("Workitem retrieved success message alert is NOT present", true);
				ATUReports.add("Workitem retrieved success message alert is NOT present", true);
			}
			Thread.sleep(2000);
			hp.getWorkItemTab().click();
			Reporter.log("Navigate to workitem tab", true);
			ATUReports.add("Navigate to workitem tab", true);
			Log.info("Navigate to workitem tab");
			Thread.sleep(2000);
			util.waitForPageToLoad();
			cwp.searchByNameInWorkitemList(workitem);
			
			boolean RetrievedWorkitemPresence = util.verifyObjectPresentReturnsBool(cwp.getWorkItemName(workitem));
			if (RetrievedWorkitemPresence) {
				Reporter.log("Retrieved Workitem is present in grid", true);
			} else {
				Reporter.log("Retrieved Workitem is not present in grid", true);
				Assert.fail("Retrieved Workitem is not present in grid ");
			}
		} catch (Exception e) {
			Reporter.log("Retrieved Workitem failed due to " + e.getMessage());
			Assert.fail("Retrieved Workitem failed Assert");
		}
	}
	// Added by Biswajit on 27/Aug/2018
	@FindBy(xpath = "//ul[@class='gtNxtPrnt']/li[position()=3]")
	private WebElement DataEntry1;

	public WebElement getDataEntry1() {
		return DataEntry1;
	}


	//Added by SumanGaK on 30-Aug-2018
	@FindBy(xpath = "//input[@id='searchField']")
	private WebElement searchFieldInInvoiceProcessing;

	public WebElement getSearchFieldInInvoiceProcessing() {
		return searchFieldInInvoiceProcessing;
	}


	public void searchByNameInInvoiceProcessing(String wiName) {
		try {
			Thread.sleep(3000);
			Select s1 = new Select(nameSearchingDropdown);
			s1.selectByVisibleText("Name");
			Thread.sleep(2000);
			searchFieldInInvoiceProcessing.clear();
			Thread.sleep(2000);
			searchFieldInInvoiceProcessing.sendKeys(wiName);
			searchFieldInInvoiceProcessing.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		} catch (Exception e) {
			Reporter.log("Searching item failed", true);
			ATUReports.add("Searching workitem in data entry failed", LogAs.FAILED,	new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.error(e.getMessage());
		}
	}

	//Added By Suman - 02-Nov-2018
	@FindBy(xpath = "//span[@id='getNextStatus']/button[contains(text(),'OFF')]")
	private WebElement getNextOFFButton;

	public WebElement getNextOFFButton() {
		return getNextOFFButton;
	}
}
