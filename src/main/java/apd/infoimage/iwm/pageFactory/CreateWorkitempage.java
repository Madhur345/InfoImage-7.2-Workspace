package apd.infoimage.iwm.pageFactory;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
 * @author KamiseRK
 *
 */
public class CreateWorkitempage extends ServerClassIWM {

	@FindBy(id = "createWorkitem_btn")
	private WebElement createWorkitem_btn;

	public WebElement getCreateWorkitem_btn() {
		return createWorkitem_btn;
	}

	@FindBy(xpath = "//input[@id='_workItem_WAR_iwmportlets_workItemName']") 
	private WebElement workItemname_TF;

	public WebElement getWorkItemname_TF() {
		return workItemname_TF;
	}


	@FindBy(xpath = "//select[@id='className']")		// added  by Chandan Modi
	//@FindBy(id = "className")
	private WebElement className_dd;

	public WebElement getClassName_dd() {
		return className_dd;
	}


	@FindBy(xpath = "//select[@id='workitemType']")  // added  by Chandan Modi
	//@FindBy(id = "workitemType")
	private WebElement workitemtype_dd;

	public WebElement getWorkitemtype_dd() {
		return workitemtype_dd;
	}

	@FindBy(xpath = "//span[contains(text(),'Create WorkItem')]")
	private WebElement createWorkitem_submitbtn;

	public WebElement getCreateWorkitem_submitbtn() {
		return createWorkitem_submitbtn;
	}

	@FindBy(xpath = "//*[@class='closebtndialogform']") 
	private WebElement workitemClose_btn;

	public WebElement getWorkitemClose_btn() {
		return workitemClose_btn;
	}

	@FindBy(xpath = ".//*[@id='createWorkItem_dialog-form']")
	private WebElement workitemCreate_win;

	public WebElement getWorkitemCreate_win() {
		return workitemCreate_win;
	}


	// Added by Vinay 
	@FindBy(xpath = "//button[@id='fileworkitem_btn']")
	private WebElement fileworkitem_btn;

	public WebElement getFileworkitemBtn() {
		return fileworkitem_btn;
	}

	// Added by Vinay
	@FindBy(xpath = "//span[contains(text(),'File WorkItem')]")
	private WebElement fileWorkItem_win;

	public WebElement getFileWorkItem_win() {
		return fileWorkItem_win;
	}

	// Added by Avnish
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	private WebElement fileWorkItem_textBox;

	public WebElement getFileWorkItem_textBox() {
		return fileWorkItem_textBox;
	}


	// Added by Vinay
	@FindBy(xpath = "//select[@id='file_workitem']")
	private WebElement selectFolderDropDown;

	public WebElement getSelectFolderDropDown() {
		return selectFolderDropDown;
	}

	// Added by Vinay
	@FindBy(xpath = "//span[text()='File Workitem']")     //Added by Vishal
	private WebElement fileWorkitemacceptBtn;

	public WebElement getFileWorkitemAcceptBtn() {
		return fileWorkitemacceptBtn;
	}

	// Added by Vinay
	@FindBy(xpath = "(//input[@value='Close'])[3]")
	private WebElement closeFileWorkitemBtn;

	public WebElement getCloseFileWorkitemBtn() {
		return closeFileWorkitemBtn;
	}

	// Added By Jayashri
	@FindBy(xpath = "//button[@id='renameDocument_btn']")
	private WebElement renameWorkItemBtn;

	public WebElement getRenameWorkItemBtn() {
		return renameWorkItemBtn;
	}

	// Added By Jayashri
	@FindBy(xpath = "//span[text()='Rename Workitem']")
	private WebElement renameWorkItemWin;

	public WebElement getRenameWorkItemWin() {
		return renameWorkItemWin;
	}

	// Added By Jayashri
	@FindBy(xpath = "//input[@id='renameDocName']")
	private WebElement newWorkItemName_TF;

	public WebElement getNewWorkItemName_TF() {
		return newWorkItemName_TF;
	}

	// Added By Jayashri
	@FindBy(xpath = "//button[contains(text(),'Rename')]")
	private WebElement renameWorkItemAcceptBtn;

	public WebElement getRenameWorkItemAcceptBtn() {
		return renameWorkItemAcceptBtn;
	}

	// Added By Jayashri
	@FindBy(xpath = "//button[@class='closebtndialogform']")
	private WebElement renameWorkitemCloseBtn;

	public WebElement getRenameWorkitemCloseBtn() {
		return renameWorkitemCloseBtn;
	}

	// Added By Jayashri
	@FindBy(xpath = "//button[@id='deleteWorkitem_btn']")
	private WebElement deleteWorkitemBtn;

	public WebElement getDeleteWorkitemBtn() {
		return deleteWorkitemBtn;
	}

	@FindBy(xpath = "//span[text()='Delete Workitem']")
	private WebElement deleteWItemWin;

	public WebElement getDeleteWItemWin() {
		return deleteWItemWin;
	}

	@FindBy(xpath = "//input[@value='deskDelete']")
	private WebElement deskDeleteRadioBtn;

	public WebElement getDeskDeleteRadioBtn() {
		return deskDeleteRadioBtn;
	}

	@FindBy(xpath = "//input[@value='systemDelete']")
	private WebElement sysDeleteRadioBtn;

	public WebElement getSysDeleteRadioBtn() {
		return sysDeleteRadioBtn;
	}

	@FindBy(xpath = "//button[@id='delete_btn']")
	private WebElement deleteWItemAccept;

	public WebElement getDeleteWItemAccept() {
		return deleteWItemAccept;
	}

	@FindBy(xpath = "//button[@id='delete_no_btn']")
	private WebElement deleteWItemClose;

	public WebElement getDeleteWItemClose() {
		return deleteWItemClose;
	}

	// Added by Rana
	@FindBy(xpath = "//span[@title='Change Role']")
	private WebElement Rolebutton;

	public WebElement getRolebutton() {
		return Rolebutton;
	}

	@FindBy(xpath = "//ul[@id='userRoleList']")
	private WebElement Rolelist_role;

	public WebElement getRolelist_role() {
		return Rolelist_role;
	}

	// 1st role
	@FindBy(xpath = "//ul[@id='userRoleList']/li[1]")
	private WebElement Role1;

	public WebElement getRole1() {
		return Role1;
	}

	// 2nd role
	@FindBy(xpath = "//ul[@id='userRoleList']/li[2]")
	private WebElement Role2;

	public WebElement getRole2() {
		return Role2;
	}

	@FindBy(xpath = "//button[@title='Export']")
	private WebElement export;

	public WebElement getExport() {
		return export;
	}

	@FindBy(xpath = "//span[contains(text(),'PDF')]")
	private WebElement Pdf_btn;

	public WebElement getPdf_btn() {
		return Pdf_btn;
	}

	// Added by Vinay
	public WebElement getWorkItemName(String workItem) {
		WebElement workItemName = Driver.driver.findElement(By.xpath("//font[text()='" + workItem + "']"));
		return workItemName;
	}

	// Added by Vinay
	public WebElement getCheckBoxWorkItem(String workItem) {
		WebElement checkBoxWorkItem = Driver.driver
				.findElement(By.xpath("//td[span/a/font[text()='" + workItem + "']]/preceding-sibling::td/input"));
		return checkBoxWorkItem;
	}

	// Added by Suman
	public WebElement getCheckBoxWorkItemName(String workItem) {
		//WebElement checkBoxWorkItemName = Driver.driver
		//.findElement(By.xpath("//i[@id='yui_patched_v3_11_0_1_1570790738344_914'] "));
		WebElement checkBoxWorkItemName = Driver.driver
				.findElement(By.xpath("(//td[span/a/font[text()='" + workItem + "']]/preceding-sibling::td/input)[1]"));
		return checkBoxWorkItemName;
	}

	// Added by Suman
	@FindBy(id = "sendWorkitem_btn")
	private WebElement SendWorkItemButton;

	public WebElement getSendWorkItemButton() {
		return SendWorkItemButton;
	}

	// Added by Suman
	@FindBy(id = "workflow")
	private WebElement WorkFlowButton;

	public WebElement getWorkFlowButton() {
		return WorkFlowButton;
	}

	// Added by Suman
	@FindBy(xpath = "//button[contains(text(),'Send')]")
	private WebElement SendButton;

	public WebElement getSendButton() {
		return SendButton;
	}

	/*
	 * Added by Suman
	 * 
	 * @FindBy(xpath = "(//select[@id='queueName'])[1]") private WebElement
	 * WorkFlowListBox;
	 * 
	 * public WebElement getWorkFlowListBox() { return WorkFlowListBox; }
	 */

	// Added by Suman
	@FindBy(id = "queueName")
	private WebElement WorkFlowListBox;

	public WebElement getWorkFlowListBox() {
		return WorkFlowListBox;
	}

	// Added by Suman
	@FindBy(xpath = "//a/span[contains(text(),'Workitems')]")
	private WebElement WorkItems;

	public WebElement getWorkItems() {
		return WorkItems;
	}

	// Added by Suman
	@FindBy(id = "reserveWorkitem_btn")
	private WebElement ReserveWorkItemButton;

	public WebElement getReserveWorkItemButton() {
		return ReserveWorkItemButton;
	}

	// Added by Suman
	@FindBy(xpath = "//span[contains(text(),'Send Workitem')]")
	private WebElement SendToWorkflow;

	public WebElement getSendToWorkflow() {
		return SendToWorkflow;
	}

	// Added by Suman
	@FindBy(xpath = "//td[contains(text(),'Workitem is locked by user')]")
	private WebElement Alerttext;

	public WebElement getAlerText() {
		return Alerttext;
	}

	// Added By Jayashri
	@FindBy(xpath = "//button[@id='workitemHistory_btn']")
	private WebElement workItemHistoryBtn;

	public WebElement getWorkItemHistoryBtn() {
		return workItemHistoryBtn;
	}

	@FindBy(xpath = "//div[text()='Workitems Report']")
	private WebElement exportPdfReportHeading;

	public WebElement getExportPdfReportHeading() {
		return exportPdfReportHeading;
	}

	// Added By Jayashri
	@FindBy(xpath = "//h3[text()='Workflow History']")
	private WebElement workflowHistoryWin;

	public WebElement getWorkflowHistoryWin() {
		return workflowHistoryWin;
	}

	// Added By Jayashri
	@FindBy(xpath = "(//td[text()='Work Introduction'])[1]")
	private WebElement dstnWorkIntroduction;

	public WebElement getDstnWorkIntroduction() {
		return dstnWorkIntroduction;
	}

	// Added By Jayashri
	@FindBy(xpath = "//td[text()='Data Entry']")
	private WebElement dstnDataEntry;

	public WebElement getDstnDataEntry() {
		return dstnDataEntry;
	}

	@FindBy(xpath = "//td[text()='Custom']")
	private WebElement dstnCustom;

	@FindBy(xpath = "(//td[text()='Work Introduction'])[2]/../td[3]")
	private WebElement dstnInHistory;

	// Added By Jayashri
	@FindBy(xpath = "//button[text()='×']")
	private WebElement wFlowHistoryWinCloseBtn;

	public WebElement getwFlowHistoryWinCloseBtn() {
		return wFlowHistoryWinCloseBtn;
	}

	// Added by Suman - 10-Nov-2017
	@FindBy(xpath = ".//*[@id='rlip_null_null']")
	private WebElement french;

	public WebElement getFrench() {
		return french;
	}

	// Added by Suman - 10-Nov-2017
	@FindBy(xpath = ".//*[@id='hhdj_null_null']")
	private WebElement spanish;

	public WebElement getSpanish() {
		return spanish;
	}

	// Added by Suman - 10-Nov-2017
	@FindBy(xpath = ".//*[@id='kwje_null_null']")
	private WebElement portuguese;

	public WebElement getPortuguese() {
		return portuguese;
	}

	// Added by Suman - 10-Nov-2017
	@FindBy(xpath = ".//*[@id='rpwo_null_null']")
	private WebElement dutch;

	public WebElement getDutch() {
		return dutch;
	}

	// Added by Suman - 10-Nov-2017
	@FindBy(xpath = ".//*[@id='bhfa_null_null']")
	private WebElement english;

	public WebElement getEnglish() {
		return english;
	}

	// Added by Suman
	@FindBy(xpath = ".//*[@id='workitemPopupResultTable']/p[1]")
	private WebElement objectid;

	public WebElement getObjectId() {
		return objectid;
	}

	/**
	 * Method to return no of workitems displayed in workitem history window
	 * 
	 * @author PradhanJ
	 * @param workitemName
	 * @return
	 */
	public int getNoOfRowsInWorkItemHiostory(String workitemName) {
		List<WebElement> items = Driver.driver.findElements(By.xpath("//td[text()='" + workitemName + "']"));
		int n = items.size();
		return n;
	}

	@FindBy(xpath = "//select[@id='columnNameForSearching']")
	private WebElement nameSearchingDropdown;

	@FindBy(xpath = "//input[@id='searchField']")
	private WebElement nameSearchTextbox;

	public WebElement getNameSearchTextbox() {
		return nameSearchTextbox;
	}

	// Added by Suman
	@SuppressWarnings("deprecation")
	public void sendWorkItemToDefaultQueue(String workitem) {
		try {
			getCheckBoxWorkItemName(workitem).click();
			Reporter.log("Workitem to be sent to Default Queue is clicked", true);
			Thread.sleep(2000);
			SendWorkItemButton.click();
			Reporter.log("Send WorkItem Button is clicked", true);
			Thread.sleep(6000);
			boolean sendWindowBoxPresence = util.verifyObjectPresentReturnsBool(getSendToWorkflow());
			if (sendWindowBoxPresence) {
				Reporter.log("Send Workitem window box is present", true);
			} else {
				Reporter.log("Send Workitem window box not present", true);
				Assert.fail("Send Workitem window box not present ");
			}

			SendButton.click();
			Reporter.log("Send Button is clicked", true);
			util.waitForPageToLoad();
			util.waitForElementEnabled(hp.getInbox());
			Thread.sleep(5000);
			util.jclick(hp.getInbox());
			util.waitForPageToLoad();
			Reporter.log("Inbox tab is clicked", true);
			ATUReports.add("Inbox tab is clicked", true);
			Thread.sleep(10000);
			if (util.verifyObjectEnabledReturnsBool(ip.getDataEntry())) {
				ip.getDataEntry().click();
				Reporter.log("Data Entry is clicked", true);
				ATUReports.add("Data Entry is clicked", true);
			} else {
				ip.getCustom().click();
				Reporter.log("Custom is clicked", true);
			}
			// ip.getInvoiceProcessing().click();
			util.waitForPageToLoad();
			Thread.sleep(10000);
			ip.searchByNameInDataEntry(workitem);
			/*Reporter.log("Searching Workitem By Name In Data Entry", true);
		boolean workitemPresence = util.verifyObjectPresentReturnsBool(getWorkItemName(workitem));
		if (workitemPresence) {
			Reporter.log("Sent Workitem is present in grid", true);
			ATUReports.add("Verify Sent workitem present in Data entry", "workitem Name: " + workitem,
					"Workitem should be present in the list", "Workitem is successfully dispalyed under Data Entry",
					true);
		} else {
			Reporter.log("Sent Workitem is not present in grid", true);
			Assert.fail("Sent Workitem is not present in grid");
		}
			 */
		} catch (Exception e) {
			e.printStackTrace();
			ATUReports.add("Send WorkItem To Default Queue failed ", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));

		}
	}

	/**
	 * @author KencharV
	 * @param workitem
	 * @param ele
	 *            - WebElement
	 */
	public void sendWorkItemToDefaultQueue(String workitem, String workstep) {
		try {
			getCheckBoxWorkItemName(workitem).click();
			Reporter.log("Workitem Check Box is clicked", true);
			Thread.sleep(2000);
			SendWorkItemButton.click();
			Reporter.log("Send WorkItem Button is clicked", true);

			util.wait(3000);
			util.waitForElementEnabled(cwp.getSendToWorkflow());
			boolean sendWorkitemWindowBoxPresence = util.verifyObjectPresentReturnsBool(cwp.getSendToWorkflow());
			if (sendWorkitemWindowBoxPresence) {
				Reporter.log("Send Workitem window box is present", true);
			} else {
				Reporter.log("Send Workitem window box not present", true);
				Assert.fail("Send Workitem window box not present ");
			}

			WorkFlowButton.click();
			Reporter.log("WorkFlow Button is clicked", true);

			Select WorkFlowListBox = new Select(getWorkFlowListBox());
			WorkFlowListBox.selectByValue(workstep);
			Reporter.log("Workstep selected as " + workstep, true);
			Thread.sleep(2000);

			SendButton.click();
			Reporter.log("Send Button is clicked", true);
			util.waitForPageToLoad();
			Thread.sleep(3000);

		} catch (Exception e) {
			Reporter.log("Send WorkItem To Default Queue failed due to " + e.getMessage());
			Assert.fail("Send WorkItem To Default Queue failed Assert");

		}
	}

	/**
	 * This method sends a WorkItem to default queue in Workflow.
	 * 
	 * @author SumanGaK
	 * @param Workitem
	 * @throws Exception
	 */

	public void sendWorkItemToDefaultQueueInvProcErr(String workitem) {
		try {
			getCheckBoxWorkItemName(workitem).click();

			Thread.sleep(2000);

			SendWorkItemButton.click();

			boolean sendWindowBoxPresence = util.verifyObjectPresentReturnsBool(getSendToWorkflow());
			if (sendWindowBoxPresence) {
				Reporter.log("Send Workitem window box is present", true);
			} else {
				Reporter.log("Send Workitem window box not present", true);
				Assert.fail("Send Workitem window box not present ");
			}

			SendButton.click();
			Thread.sleep(2000);
			util.waitForPageToLoad();
			hp.getInbox().click();
			ip.getInvoiceProcessingError().click();

			Thread.sleep(2000);
			boolean workitemPresence = util.verifyObjectPresentReturnsBool(getWorkItemName(workitem));
			if (workitemPresence) {
				Reporter.log("Sent Workitem is present in grid", true);
			} else {
				Reporter.log("Sent Workitem is not present in grid", true);
				Assert.fail("Sent Workitem is not present in grid ");
			}

		} catch (Exception e) {
			Reporter.log("Send WorkItem To Default Queue failed due to " + e.getMessage());
			Assert.fail("Send WorkItem To Default Queue failed Assert");

		}
	}

	// Added by Suman

	/**
	 * This method sends a WorkItem to desired queue in Workflow.
	 * 
	 * @author SumanGaK
	 * @param Workitem
	 * @param WorkSetOption
	 * @throws Exception
	 */
	public void sendWorkItemToDesiredQueue(String workitems, String worksetoption) {
		try {
			getCheckBoxWorkItemName(workitems).click();
			Reporter.log("Check Box of Workitem is clicked", true);

			Thread.sleep(2000);

			SendWorkItemButton.click();
			Reporter.log("Send WorkItem Button is clicked", true);

			Thread.sleep(2000);

			boolean sendWindowBoxPresence = util.verifyObjectPresentReturnsBool(getSendToWorkflow());
			if (sendWindowBoxPresence) {
				Reporter.log("Send Workitem window box is present", true);
			} else {
				Reporter.log("Send Workitem window box not present", true);
				Assert.fail("Send Workitem window box not present ");
			}

			WorkFlowButton.click();
			Reporter.log("WorkFlow Button is clicked", true);

			Select WorkFlowListBox = new Select(getWorkFlowListBox());
			WorkFlowListBox.selectByValue(worksetoption);
			Reporter.log("Workset selected as " + worksetoption, true);
			Thread.sleep(2000);
			// WorkFlowListBox.click();

			// Thread.sleep(2000);

			SendButton.click();
			Reporter.log("Send Button is clicked", true);
			// util.waitForPageToLoad();
			util.waitForPageToLoad();
			Thread.sleep(3000);
			hp.getInbox().click();
			Reporter.log("Inbox tab is clicked", true);
			Thread.sleep(5000);
			util.waitForPageToLoad();
			// ip.getCustom().click();
			// Reporter.log("Custom is clicked",true);
			ip.getFaxin().click();
			Reporter.log("Faxin is clicked", true);
			// ip.getDataEntry().click();
			// Reporter.log("Data Entry is clicked",true);

			Thread.sleep(3000);

			ip.searchByNameInFaxIn(workitems);
			Reporter.log("Searching Workitem By Name In Fax In", true);

			boolean workitempPresence = util.verifyObjectPresentReturnsBool(getWorkItemName(workitems));
			if (workitempPresence) {
				Reporter.log("Sent Workitem is present in grid", true);
			} else {
				Reporter.log("Sent Workitem is not present in grid", true);
				Assert.fail("Sent Workitem is not present in grid ");
			}

		} catch (Exception e) {
			Reporter.log("Send WorkItem To Desired Queue failed due to " + e.getMessage());
			Assert.fail("Send WorkItem To Desired Queue failed Assert");

		}
	}

	// Added by Suman

	/**
	 * This method reserves a WorkItem.
	 * 
	 * @author SumanGaK
	 * @param Workitem
	 * @throws Exception
	 */
	public void reserveWorkItem(String workitem) {
		try {
			WorkItems.click();
			Reporter.log("WorkItem tab is clicked", true);

			Thread.sleep(2000);

			//			util.waitForPageToLoad();

			//			getCheckBoxWorkItemName(workitem).click();
			cwp.searchByNameInWorkitemList(workitem);
			Driver.driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
			Reporter.log("WorkItem checkbox is clicked", true);

			Thread.sleep(2000);

			ReserveWorkItemButton.click();
			Reporter.log("Reserve WorkItem is done", true);
		} catch (Exception e) {
			Reporter.log("Reserve WorkItem failed due to " + e.getMessage());
			Assert.fail("Reserve WorkItem failed Assert");

		}

	}

	// Added by Suman

	/**
	 * This method verifies whether a WorkItem is reserved.
	 * 
	 * @author SumanGaK
	 * @param Workitem
	 * @throws Exception
	 */
	public void reserveVerifyWorkItem(String workitem) {
		try {
			WorkItems.click();
			Reporter.log("Workitem tab is clicked", true);

			Thread.sleep(3000);

//			getCheckBoxWorkItemName(workitem).click();
			cwp.searchByNameInWorkitemList(workitem);//Added by Vishal
			Driver.driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click(); //Added by Vishal
			Reporter.log("Workitem Check Box is clicked", true);

			Thread.sleep(2000);

			ReserveWorkItemButton.click();
			Reporter.log("Reserve WorkItem Button is clicked", true);

			boolean f6 = Alerttext.isDisplayed();
			if (f6) {
				Reporter.log("Workitem is locked by user", true);
			}

			else {
				Reporter.log("Workitem is not locked by user", true);
				Assert.fail("Workitem is not locked by user");
			}
		} catch (Exception e) {
			Reporter.log("Reserve Verify WorkItem failed due to " + e.getMessage());
			Assert.fail("Reserve Verify WorkItem failed Assert");
		}

	}

	// Added by Vinay
	public WebElement getActionBtn(String workItem) {
		WebElement actionBtn = Driver.driver.findElement(By
				.xpath("//td[span/a/font[text()='" + workItem + "']]/preceding-sibling::td/button[@title='Actions']"));
		return actionBtn;
	}

	// Added by Vinay
	@FindBy(id = "reClassify")
	private WebElement reClassifyBtn;

	public WebElement getReClassifyBtn() {
		return reClassifyBtn;
	}

	// Added by Vinay
	// @FindBy(name="actionFormFieldElement")
	@FindBy(xpath = "//li[@name='actionFormFieldElement']") // Modified By
	// Jayashri
	private WebElement formFiledsBtn;

	public WebElement getformFiledsBtn() {
		return formFiledsBtn;
	}

	// Added by Vinay
	@FindBy(xpath = "//div[@id='reClassifyDialogbox']//td[label[text()='Current Class:']]/following-sibling::td")
	private WebElement currentClassWorkitemReclassify;

	public WebElement getCurrentClassWorkitemReclassify() {
		return currentClassWorkitemReclassify;
	}

	// Added by Vinay
	@FindBy(xpath = "//select[@name='reClassifyDropdown']")
	private WebElement newClassDropDownWorkitemReclassify;

	public WebElement getNewClassDropDownWorkitemReclassify() {
		return newClassDropDownWorkitemReclassify;
	}

	// Added by Vinay
	@FindBy(xpath = "//div[@id='reClassifyDialogbox']//input[@name='restoreFormData']")
	private WebElement retainFormDataChkBoxWorkitemReclassify;

	public WebElement getRetainFormDataChkBoxWorkitemReclassify() {
		return retainFormDataChkBoxWorkitemReclassify;
	}

	// Added by Vinay
	@FindBy(id = "reClassifySubmit")
	private WebElement reClassifySubmitBtn;

	public WebElement getReClassifySubmitBtn() {
		return reClassifySubmitBtn;
	}

	// Added by Vinay
	@FindBy(xpath = "//button[@id='reClassifySubmit']/following-sibling::button[text()='Close']")
	private WebElement reClassifyCloseBtn;

	public WebElement getReClassifyCloseBtn() {
		return reClassifyCloseBtn;
	}

	// Added by Vinay
	@FindBy(xpath = "//span[text()='Workitem Reclassify']")
	private WebElement workitemReclassifyDialogBox;

	public WebElement getWorkitemReclassifyDialogBox() {
		return workitemReclassifyDialogBox;
	}

	// Added by Vinay
	public WebElement getClassName(String className) {
		WebElement classNameValue = Driver.driver
				.findElement(By.xpath("//p[label[text()='Class Name']][text()='" + className + "']"));
		return classNameValue;
	}

	// Added by Vinay
	public WebElement getWorkitemNameReclassify(String workItem) {
		WebElement workitemNameReclassify = Driver.driver
				.findElement(By.xpath("//p[label[text()='Workitem Name']][text()='" + workItem + "']"));
		return workitemNameReclassify;
	}

	// Added by Vinay
	public WebElement getMetaDataDialogBoxHeader(String workItem) {
		WebElement metaDataDialogHeader = Driver.driver
				.findElement(By.xpath("//span[@class='ui-dialog-title'][text()='" + workItem + "']"));
		return metaDataDialogHeader;
	}

	// Added by Vinay
	public WebElement getMetaDataDialogBoxCloseBtn(String workItem) {
		WebElement metaDataDialogCloseBtn = Driver.driver.findElement(
				By.xpath("//span[@class='ui-dialog-title'][text()='" + workItem + "']/following-sibling::button"));
		return metaDataDialogCloseBtn;
	}

	// Added by Vinay
	public WebElement getMetaDataWorkItem(String workItem) {
		WebElement metaDataWorkItem = Driver.driver
				.findElement(By.xpath("//p[label[text()='Workitem Name']][text()='" + workItem + "']"));
		return metaDataWorkItem;
	}

	// Added by Vinay
	public WebElement getMetaDataClassName(String className) {
		WebElement metaDataClassName = Driver.driver
				.findElement(By.xpath("//p[label[text()='Class Name']][text()='" + className + "']"));
		return metaDataClassName;
	}

	// Added by Vinay
	@FindBy(id = "ID_CODE_1")
	private WebElement ID_CODE_Tf;

	public WebElement getID_CODE_Tf() {
		return ID_CODE_Tf;
	}

	// Added by Vinay
	@FindBy(id = "form_validate")
	private WebElement updateFormBtn;

	public WebElement getUpdateFormBtn() {
		return updateFormBtn;
	}

	// Added by Vinay
	@FindBy(xpath = "//form[@id='formfieldform']//button[text()='Close']")
	private WebElement closeFormBtn;

	public WebElement getCloseFormBtn() {
		return closeFormBtn;
	}

	// Added by Vinay
	@FindBy(xpath = "//span/i[@class='actionMetaDataIcon']")
	private WebElement metaData;

	public WebElement getMetaData() {
		return metaData;
	}

	// Added by Suman
	@FindBy(xpath = "//button[@class='actionBtn']")
	private WebElement actionsBtn;

	public WebElement getActionsBtn() {
		return actionsBtn;
	}

	@FindBy(xpath = "//i[@class='actionMetaDataIcon']")
	private WebElement metadata;

	public WebElement getActionsMetaData() {
		return metadata;
	}

	// Xpaths required to validate views in workitem list page
	// Added By Jayashri-1/15/2018

	@FindBy(xpath = "//div[text()='ID_CODE']")
	private WebElement header_IDCODE;

	@FindBy(xpath = "//div[text()='INVOICE_NO']")
	private WebElement header_InvoiceNo;

	@FindBy(xpath = "//div[text()='TERMS']")
	private WebElement header_Terms;

	public WebElement getHeader_IDCODE() {
		return header_IDCODE;
	}

	public WebElement getHeader_InvoiceNo() {
		return header_InvoiceNo;
	}

	public WebElement getHeader_Terms() {
		return header_Terms;
	}

	// Validate whether headers are updated as per view or not
	public boolean verifyHeadersInWorkitemsList() {
		if (header_IDCODE.isDisplayed() && header_InvoiceNo.isDisplayed() && header_Terms.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	// Added By Suman
	public WebElement getWebElementObjectId(String objId) {
		WebElement objectId = Driver.driver
				.findElement(By.xpath("//p[label[text()='ID']][contains(text(),'" + objId + "')]"));
		return objectId;
	}

	public WebElement getDstnInHistory() {
		if (dstnInHistory.getText().equalsIgnoreCase("Data Entry")) {
			dstnInHistory = dstnDataEntry;
		}
		if (dstnInHistory.getText().equalsIgnoreCase("Custom")) {
			dstnInHistory = dstnCustom;
		}
		return dstnInHistory;
	}

	/**
	 * This method creates a WorkItem.
	 * 
	 * @author KamiseRK
	 * @param WorkitemName
	 * @param ClassName
	 * @param WorkitemType
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public void CreateWorkitem(String WorkitemName, String ClassName, String WorkitemType) {
		try {
			util.waitForElementEnabled(createWorkitem_btn);
			boolean f = util.verifyObjectPresentReturnsBool(getCreateWorkitem_btn());
			if (f) {
				Reporter.log("Create Workitem page box present", true);
				Log.info("Create Workitem page box present");
			} else {
				Reporter.log("Create Workitem page box not present", true);
				Assert.fail("Create Workitem page box not present ");
			}

			getCreateWorkitem_btn().click();
			Thread.sleep(2000);

			f = util.verifyObjectPresentReturnsBool(getWorkitemCreate_win());
			if (f) {
				Reporter.log("Create Workitem window box is present", true);
				Log.info("Create Workitem window box is present");
			} else {
				Reporter.log("Create Workitem window box not present", true);
				Assert.fail("Create Workitem window box not present ");
			}
			getWorkItemname_TF().clear();
			getWorkItemname_TF().sendKeys(WorkitemName);
			Reporter.log(" Workitem name inserted", true);
			Log.info(" Workitem name inserted");
			//			WebElement list=Driver.driver.findElement(By.id("className"));
			//			Select s = new Select(list);
			Select sel = new Select(getClassName_dd());
			sel.selectByValue(ClassName);
			//			s.selectByValue("Archive");
			Reporter.log("ClassName selected as " + ClassName, true);
			Log.info("ClassName selected as " + ClassName);
			Thread.sleep(2000);

			Select sel1 = new Select(getWorkitemtype_dd());
			sel1.selectByValue(WorkitemType);
			Reporter.log("WorkitemType selected as " + WorkitemType, true);
			Thread.sleep(2000);
			getCreateWorkitem_submitbtn().click();
			Reporter.log("Create Workitem submit button clicked", true);
			Log.info("Create Workitem submit button clicked");
			util.waitForPageToLoad();
			util.wait(5000);

			searchByNameInWorkitemList(WorkitemName);

			f = util.verifyObjectPresentReturnsBool(getWorkItemName(WorkitemName));
			if (f) {
				Log.info("Workitem is created successfully");
				ATUReports.add("Create workitem", "Workitem Name: " + WorkitemName,
						"Workitem should be created Successfully", "Workitem is created successfully", true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			ATUReports.add("Create workitem failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));

		}
	}

	public void searchByNameInWorkitemList(String wiName) {
		try {
			Thread.sleep(3000);
			Select s1 = new Select(nameSearchingDropdown);
			s1.selectByVisibleText("Name");
			Thread.sleep(3000);
			nameSearchTextbox.clear();
			util.wait(2000);
			nameSearchTextbox.sendKeys(wiName);
			nameSearchTextbox.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
		} catch (Exception e) {
			Reporter.log("Searching item failed", true);
		}
	}

	/**
	 * This method will delete a workitem @author PradhanJ @param
	 * workItem @throws
	 */
	@SuppressWarnings("deprecation")
	public void deleteWorkItem(String workItem) {
		try {
			// Select the created workitem

			cwp.searchByNameInWorkitemList(workItem);

			Thread.sleep(3000);
			hp.getSearchFieldInWorkitemTab().clear();
			Thread.sleep(3000);
			getCheckBoxWorkItem(workItem).click();

			// Click on the delete workitem button

			deleteWorkitemBtn.click();
			Thread.sleep(4000);
			// Check whether delete workitem window is appearing or not

			boolean deleteWIWinPresent = util.verifyObjectPresentReturnsBool(getDeleteWItemWin());
			if (deleteWIWinPresent) {
				Reporter.log("Delete Workitem window is present", true);

			} else {
				Reporter.log("Delete Workitem window box not present", true);
				Assert.fail("Delete Workitem window box not present ");
			}

			Thread.sleep(3000);
			deleteWItemAccept.click();
			Thread.sleep(2000);

			util.waitForPageToLoad();

			cwp.searchByNameInWorkitemList(workItem);
			Thread.sleep(2000);

			try {
				WebElement deletedWorkitem = getWorkItemName(workItem);
				Thread.sleep(2000);
				boolean deletedWItemPresent = util.verifyObjectPresentReturnsBool(deletedWorkitem);
				if (deletedWItemPresent) {
					Reporter.log("WorkItem is not deleted", true);
					Assert.fail("WorkItem is not deleted");

				}
			} catch (Exception e1) {
				Reporter.log("workItem is deleted successfully", true);
				ATUReports.add("Delete workitem", "Workitem name:" + workItem,
						"Workitem should be deleted successfully", "Workitem is deleted successfully", true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			ATUReports.add("Delete Workitem failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));

		}
	}

	// Add by Rana
	public void export() {
		try {
			boolean Export = util.verifyObjectPresentReturnsBool(getExport());
			if (Export) {
				Reporter.log("Export window is present", true);
				getExport().click();
				Thread.sleep(2000);
			} else {
				Reporter.log("Rolelist  window box not present", true);
				Assert.fail("Rolelist  window box not present ");
			}
			boolean Pdf = util.verifyObjectPresentReturnsBool(getPdf_btn());
			if (Pdf) {
				Reporter.log("PDF button is visible", true);
				Thread.sleep(2000);

				Actions builder = new Actions(Driver.driver);
				builder.click(Pdf_btn).perform();
				Reporter.log("Action performed", true);
				Thread.sleep(5000);
				System.out.println("executed");

			} else {
				Reporter.log("PDF not downloaded", true);
				Assert.fail("PDF not downloaded");
			}
		} catch (Exception e) {

		}

	}

	// Added By Suman - 12-Dec-2017
	/**
	 * This method will check document type of a workitem
	 * 
	 * @author SumanGaK
	 * @param workItem
	 * @throws Exception
	 */

	public void getDocumentType(String workItem) {
		try {
			WebElement modeWorkItemName = Driver.driver.findElement(By.xpath("//td[span/a/font[text()='" + workItem
					+ "']]/span[@class='docEdit documentType openedSpan document']"));
			if (modeWorkItemName.getAttribute("class").equals("docEdit documentType openedSpan document")) {
				Reporter.log("Workitem is not displayed as read-only", true);
			}

			else {
				Reporter.log("Workitem is displayed as read-only", true);
			}
		} catch (Exception e) {
			Reporter.log(e.toString(), true);
			Reporter.log("There is something wrong in getting Document Type", true);
			Assert.fail("There is something wrong in getting Document Type");
		}
	}

	// Added By Suman - 30-Mar-2018
	/**
	 * This method will check document type of a workitem whether RedX is
	 * displayed or not
	 * 
	 * @author SumanGaK
	 * @param workItem
	 * @throws Exception
	 */

	public void getRedXDocumentType(String workItem) {
		try {
			WebElement modeWorkItemName = Driver.driver.findElement(By.xpath("//td[span/a/font[text()='" + workItem
					+ "']]/span[@class='doc documentType openedSpan document']"));
			if (modeWorkItemName.getAttribute("class").equals("doc documentType openedSpan document")) {
				Reporter.log(
						"Read Only symbol is present.Pencil mark and Red X mark are not displayed on the workitem under column Name",
						true);
			}

			else if (modeWorkItemName.getAttribute("class").equals("docNonEdit documentType openedSpan document")) {
				Reporter.log("Pencil mark and Red X mark are displayed on the workitem under column Name", true);
			}

			else {
				Reporter.log("Required mark is not displayed on the workitem under column Name", true);
			}
		} catch (Exception e) {
			Reporter.log(e.toString(), true);
			Reporter.log("There is something wrong in getting Document Type", true);
			Assert.fail("There is something wrong in getting Document Type");
		}
	}

	// Added By Suman - 30-Mar-2018
	/**
	 * This method will check folder type of a workitem whether RedX is
	 * displayed or not
	 * 
	 * @author SumanGaK
	 * @param workItem
	 * @throws Exception
	 */

	public void getRedXFolderClass(String workItem) {
		try {
			WebElement modeWorkItemName = Driver.driver.findElement(By.xpath("//td[span/a/font[text()='" + workItem
					+ "']]/span[@class='fldrEdit folderType openedSpan folder']"));
			if (modeWorkItemName.getAttribute("class").equals("fldrEdit folderType openedSpan folder")) {
				Reporter.log("Red X mark is not displayed on the workitem under column Name", true);
			}

			else if (modeWorkItemName.getAttribute("class").equals("fldrNonEdit folderType openedSpan folder")) {
				Reporter.log("Pencil mark and Red X mark are displayed on the workitem under column Name", true);
			}

			else {
				Reporter.log("Required mark is not displayed on the workitem under column Name", true);
			}
		} catch (Exception e) {
			Reporter.log(e.toString(), true);
			Reporter.log("There is something wrong in getting Document Type", true);
			Assert.fail("There is something wrong in getting Document Type");
		}
	}

	// Biswajit 17/04/2018
	@FindBy(xpath = "//select[@id='queueName']")
	private WebElement selectworkflowqueueName;

	public WebElement getselectworkflowqueueName() {
		return selectworkflowqueueName;
	}

	// Biswajit 17/04/2018
	@FindBy(xpath = "//select[@id='userName']")
	private WebElement selectusername;

	public WebElement getselectusername() {
		return selectusername;
	}

	// Biswajit 17/04/2018
	@FindBy(xpath = "//input[@id='userQueue']")
	private WebElement selectuserqueue;

	public WebElement getselectuserqueue() {
		return selectuserqueue;
	}

	// Biswajit 17/04/2018
	@FindBy(xpath = "//input[@id='workflow']")
	private WebElement selectworkflow;

	public WebElement getselectworkflow() {
		return selectworkflow;
	}
	// Added by Biswajit 17/04/2018

	public void sendToAnyOtherUserQueue(String workitem, String userQueue, String workFlow) {
		try {
			getCheckBoxWorkItemName(workitem).click();
			Reporter.log("Workitem Check Box is clicked", true);
			Thread.sleep(3000);
			SendWorkItemButton.click();
			Thread.sleep(3000);
			Reporter.log("Send WorkItem Button is clicked", true);
			boolean f1 = util.verifyObjectPresentReturnsBool(getSendToWorkflow());
			if (f1) {
				Reporter.log("Send Workitem window box is present", true);
			} else {
				Reporter.log("Send Workitem window box not present", true);
				Assert.fail("Send Workitem window box not present ");
			}
			getselectuserqueue().click();

			Select sel = new Select(getselectusername());
			sel.selectByValue(userQueue);
			Reporter.log("UserQueue selected as " + userQueue, true);
			Thread.sleep(3000);

			getselectworkflow().click();
			Select sel1 = new Select(getselectworkflowqueueName());
			sel1.selectByValue(workFlow);
			Reporter.log("WorkFlow selected as " + workFlow, true);
			Thread.sleep(3000);
			SendButton.click();
			Reporter.log("Send Button is clicked", true);
			util.waitForPageToLoad();
			Thread.sleep(3000);

		} catch (Exception e) {
			Reporter.log("Send WorkItem To Default Queue failed due to " + e.getMessage());
			Assert.fail("Send WorkItem To Default Queue failed Assert");

		}
	}

	@FindBy(xpath = "//input[@id='userQueue']")
	private WebElement userQueueRadioButton;

	public WebElement getUserQueueRadioButton() {
		return userQueueRadioButton;
	}

	/**
	 * This method sends a WorkItem to user queue.
	 * 
	 * @author SumanGaK
	 * @param Workitem
	 * @param User
	 *            Queue
	 * @throws Exception
	 */

	public void sendWorkItemToUserQueue(String workitem, String userQueueOption) {
		try {
			getCheckBoxWorkItemName(workitem).click();
			Reporter.log("CheckBox of Workitem to be sent to User Queue is clicked", true);
			Thread.sleep(2000);
			SendWorkItemButton.click();
			Reporter.log("Send WorkItem Button is clicked", true);
			Thread.sleep(3000);
			boolean f1 = util.verifyObjectPresentReturnsBool(getSendToWorkflow());
			if (f1) {
				Reporter.log("Send Workitem window box is present", true);
			} else {
				Reporter.log("Send Workitem window box not present", true);
				Assert.fail("Send Workitem window box not present ");
			}

			getUserQueueRadioButton().click();
			Reporter.log("User Queue Radio Button is clicked", true);

			Select sel5 = new Select(getUserNameListBox());
			sel5.selectByValue(userQueueOption);
			Reporter.log("User Queue Option selected as " + userQueueOption, true);
			Thread.sleep(2000);

			SendButton.click();
			Reporter.log("Send Button is clicked", true);
			util.waitForPageToLoad();
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.log("Send WorkItem To User Queue failed due to " + e.getMessage());
			Assert.fail("Send WorkItem To User Queue failed Assert");
		}
	}

	@FindBy(xpath = "//select[@id='userName']")
	private WebElement userNameListBox;

	public WebElement getUserNameListBox() {
		return userNameListBox;
	}

	@FindBy(xpath = ".//*[@id='workitemPopupResultTable']/p[7]")
	private WebElement newClass;

	public WebElement getNewClassName() {
		return newClass;
	}

	// Added By Suman - 04-May-2018
	public WebElement getWebElementNewClass(String newClass) {
		WebElement newClassName = Driver.driver
				.findElement(By.xpath("//p[label[text()='Class Name']][contains(text(),'" + newClass + "')]"));
		return newClassName;
	}

	@FindBy(xpath = "//div[@class='metaData']//label[text()='Class Name']/..")
	private WebElement classNameInMetaData;

	public WebElement getFileworkitem_btn() {
		return fileworkitem_btn;
	}

	public WebElement getFileWorkitemacceptBtn() {
		return fileWorkitemacceptBtn;
	}

	public WebElement getDstnCustom() {
		return dstnCustom;
	}

	public WebElement getObjectid() {
		return objectid;
	}

	public WebElement getNameSearchingDropdown() {
		return nameSearchingDropdown;
	}

	public WebElement getFormFiledsBtn() {
		return formFiledsBtn;
	}

	public WebElement getMetadata() {
		return metadata;
	}

	public WebElement getSelectworkflowqueueName() {
		return selectworkflowqueueName;
	}

	public WebElement getSelectusername() {
		return selectusername;
	}

	public WebElement getSelectuserqueue() {
		return selectuserqueue;
	}

	public WebElement getSelectworkflow() {
		return selectworkflow;
	}

	public WebElement getNewClass() {
		return newClass;
	}

	public WebElement getClassNameInMetaData() {
		return classNameInMetaData;
	}

	@FindBy(xpath = "//li[@id='docDuplicate']")
	private WebElement docDuplicateInAction;

	public WebElement getAlerttext() {
		return Alerttext;
	}

	public WebElement getDocDuplicateInAction() {
		return docDuplicateInAction;
	}

	@FindBy(xpath = "//input[@id='workitemName']")
	private WebElement wNameTextBoxInDocDuplicate;

	public WebElement getwNameTextBoxInDocDuplicate() {
		return wNameTextBoxInDocDuplicate;
	}

	@FindBy(xpath = "//input[@id='restoreFormData']")
	private WebElement includeFormDataCheckbox;

	public WebElement getIncludeFormDataCheckbox() {
		return includeFormDataCheckbox;
	}

	@FindBy(xpath = "//button[@id='docDuplicateSubmit']")
	private WebElement duplicateSbmitButton;

	public WebElement getDuplicateSbmitButton() {
		return duplicateSbmitButton;
	}

	// Suman - 23-May-2018
	@FindBy(xpath = "//div/button/span[contains(@class,'ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only ui-dialog-titlebar-close')]")
	private WebElement metaDataWindowXMark;

	public WebElement getMetaDataWindowXMark() {
		return metaDataWindowXMark;
	}

	@FindBy(xpath = "//select[@id='columnNameForSearching']")
	private WebElement workitemsDropDownForSearch;

	public WebElement getWorkitemsDropDownForSearch() {
		return workitemsDropDownForSearch;
	}

	@FindBy(xpath = "//div[@id='MandatoryFieldRequired']")
	private WebElement errorMsgForMandatoryField;

	public WebElement getErrorMsgForMandatoryField() {
		return errorMsgForMandatoryField;
	}

	// Added by Suman
	public void sendWorkItemToArchiveQueue(String workitem) {
		try {
			getCheckBoxWorkItemName(workitem).click();
			Reporter.log("Workitem to be sent to Archive Queue is clicked", true);

			SendWorkItemButton.click();
			Reporter.log("Send WorkItem Button is clicked", true);
			Thread.sleep(3000);
			boolean workitemPresence = util.verifyObjectPresentReturnsBool(getSendToWorkflow());
			if (workitemPresence) {
				Reporter.log("Send Workitem window box is present", true);
			} else {
				Reporter.log("Send Workitem window box not present", true);
				Assert.fail("Send Workitem window box not present ");
			}

			Thread.sleep(2000);

			SendButton.click();
			Reporter.log("Send Button is clicked", true);
			util.waitForPageToLoad();
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
			ATUReports.add("Send WorkItem To Default Queue failed ", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));

		}
	}

	@FindBy(xpath = "//select[@id='TERMS_4']")
	private WebElement termsInFormField;

	public WebElement getTermsInFormField() {
		return termsInFormField;
	}

	// Added By Suman - 06-Jun-2018
	/**
	 * Method to return no of workitems displayed in workitems tab
	 * 
	 * @author SumanGaK
	 * @return int
	 */
	public int getWitemsInWorkitemsTab() {
		List<WebElement> witemsInWorkitemsTab = Driver.driver
				.findElements(By.xpath("//font[contains(@class,'WorkitemName')]"));
		int numberOfWorkitems = witemsInWorkitemsTab.size();
		return numberOfWorkitems;
	}

	@FindBy(xpath = "//input[@name='INVOICE_NO']")
	private WebElement invoiceNoInFormFields;

	public WebElement getInvoiceNoInFormFields() {
		return invoiceNoInFormFields;
	}

	// Added by Avnish on 20/06/2018
	@FindBy(xpath = "//select[@id='file_workitem']")
	private WebElement folderSearchDropdown;

	@FindBy(xpath = "//button[@class='btn refinedSearchSubmit btn-primary']")
	private WebElement fileWorkItemButton;

	public void selectByWorkitemInFileWorkItemDropDown(String wiName) {
		try {
			Thread.sleep(1000);
			Select s1 = new Select(folderSearchDropdown);
			s1.selectByVisibleText(wiName);
			Thread.sleep(1000);
			fileWorkItemButton.click();

		} catch (Exception e) {
			Reporter.log("Searching item failed", true);
		}
	}

	/**
	 * This method sends a WorkItem to default queue in Workflow.
	 * 
	 * @author SumanGaK
	 * @param Workitem
	 * @throws Exception
	 */

	@SuppressWarnings("deprecation")
	public void sendWorkItemToDefaultQueueInvoiceProcessing(String workitem) {
		try {
			getCheckBoxWorkItemName(workitem).click();
			Reporter.log("Workitem to be sent to Default Queue is clicked", true);
			Thread.sleep(2000);
			SendWorkItemButton.click();
			Reporter.log("Send WorkItem Button is clicked", true);
			Thread.sleep(3000);
			boolean sendWindowBoxPresence = util.verifyObjectPresentReturnsBool(getSendToWorkflow());
			if (sendWindowBoxPresence) {
				Reporter.log("Send Workitem window box is present", true);
			} else {
				Reporter.log("Send Workitem window box not present", true);
				Assert.fail("Send Workitem window box not present ");
			}

			SendButton.click();
			Reporter.log("Send Button is clicked", true);
			util.waitForPageToLoad();
			util.waitForElementEnabled(hp.getInbox());
			Thread.sleep(5000);
			util.jclick(hp.getInbox());
			util.waitForPageToLoad();
			Reporter.log("Inbox tab is clicked", true);
			ATUReports.add("Inbox tab is clicked", true);
			Thread.sleep(5000);

			if (util.verifyObjectEnabledReturnsBool(ip.getInvoiceProcessing())) {
				util.jclick(ip.getInvoiceProcessing());
				Reporter.log("Invoice Processing is clicked", true);
				ATUReports.add("Invoice Processing is clicked", true);
			} 

			util.waitForPageToLoad();
			Thread.sleep(10000);
			ip.searchByNameInInvoiceProcessing(workitem);
			Reporter.log("Searching Workitem By Name In Invoice Processing", true);

			Thread.sleep(2000);
			boolean workitemPresence = util.verifyObjectPresentReturnsBool(getWorkItemName(workitem));
			if (workitemPresence) {
				Reporter.log("Sent Workitem is present in grid", true);
			} else {
				Reporter.log("Sent Workitem is not present in grid", true);
				Assert.fail("Sent Workitem is not present in grid ");
			}

		} catch (Exception e) {
			Reporter.log("Send WorkItem To Default Queue failed due to " + e.getMessage());
			Assert.fail("Send WorkItem To Default Queue failed Assert");

		}
	}

	// Added By Suman-04-JUL-2018
	@FindBy(xpath = "//span[@class='ui-dialog-title']/following-sibling::button[1]")
	private WebElement createWorkitemWindowXMark;

	public WebElement getCreateWorkitemWindowXMark() {
		return createWorkitemWindowXMark;
	}

	@FindBy(xpath = "//span[@class='WorkSetIWMSearchList']")
	private WebElement worksetColumnValue;

	public WebElement getFolderSearchDropdown() {
		return folderSearchDropdown;
	}

	public WebElement getFileWorkItemButton() {
		return fileWorkItemButton;
	}

	public WebElement getWorksetColumnValue() {
		return worksetColumnValue;
	}

	// Biswajit 26/July/2018
	@FindBy(xpath = "//div[@class='alert alert-error']")
	private WebElement alertMessage;

	public WebElement getalertMessage() {
		return alertMessage;
	}

	@FindBy(xpath = "//div[contains(text(),'Characters are not allowed')]")
	private WebElement errorMsgForUnSupportedCharsInCreateworkitem;

	public WebElement getAlertMessage() {
		return alertMessage;
	}

	public WebElement getErrorMsgForUnSupportedCharsInCreateworkitem() {
		return errorMsgForUnSupportedCharsInCreateworkitem;
	}

	// Suman - 31-Jul-2018
	@FindBy(xpath = "//input[@id='searchField']")
	private WebElement searchFieldInWorkitemTab;

	public WebElement getSearchFieldInWorkitemTab() {
		return searchFieldInWorkitemTab;
	}

	// Suman - 31-Jul-2018
	public void searchByNameInWorkitemTab(String wiName) {
		try {
			Thread.sleep(3000);
			Select s1 = new Select(nameSearchingDropdown);
			s1.selectByVisibleText("Name");
			Thread.sleep(5000);
			searchFieldInWorkitemTab.sendKeys(wiName + Keys.ENTER);
			Thread.sleep(3000);
		} catch (Exception e) {
			Reporter.log("Searching item failed", true);
		}
	}
	// Added by Avnish on 21-Aug-2018
	@SuppressWarnings("deprecation")
	public void sendWorkItemInvoiceProcessing(String workitem) {
		try {
			getCheckBoxWorkItemName(workitem).click();
			Reporter.log("Workitem to be sent to Default Queue is clicked", true);
			Thread.sleep(1000);
			SendWorkItemButton.click();
			Reporter.log("Send WorkItem Button is clicked", true);
			Thread.sleep(1000);
			SendButton.click();
			Reporter.log("Send Button is clicked", true);
			util.waitForPageToLoad();
			Thread.sleep(3000);
			util.waitForElementEnabled(hp.getInbox());
			util.jclick(hp.getInbox());
			Reporter.log("Inbox tab is clicked", true);
			ATUReports.add("Inbox tab is clicked", true);
			Thread.sleep(6000);
			util.waitForElementEnabled(ip.getInvoiceProcessing());
			util.jclick(ip.getInvoiceProcessing());
			Reporter.log("Invoice Processing is clicked", true);
			ATUReports.add("Invoice Processing is clicked", true);
			Thread.sleep(5000);
			ip.searchByNameInDataEntry(workitem);
			Reporter.log("Searching Workitem By Name In Data Entry", true);
			boolean workitemPresence = util.verifyObjectPresentReturnsBool(getWorkItemName(workitem));
			if (workitemPresence) {
				Reporter.log("Sent Workitem is present in grid", true);
				ATUReports.add("Verify Sent workitem present in Data entry", "workitem Name: " + workitem,
						"Workitem should be present in the list", "Workitem is successfully dispalyed under Data Entry",
						true);
			} else {
				Reporter.log("Sent Workitem is not present in grid", true);
				Assert.fail("Sent Workitem is not present in grid");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ATUReports.add("Send WorkItem To Default Queue failed ", LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.DESKTOP));

		}
	}
	@SuppressWarnings("deprecation")
	public void sendWorkItemToDefaultQueue1(String workitem) throws InterruptedException {
		/*try {*/
		getCheckBoxWorkItemName(workitem).click();
		Reporter.log("Workitem to be sent to Default Queue is clicked", true);
		Thread.sleep(2000);
		SendWorkItemButton.click();
		Reporter.log("Send WorkItem Button is clicked", true);
		Thread.sleep(3000);
		boolean sendWindowBoxPresence = util.verifyObjectPresentReturnsBool(getSendToWorkflow());
		if (sendWindowBoxPresence) {
			Reporter.log("Send Workitem window box is present", true);
		} else {
			Reporter.log("Send Workitem window box not present", true);
			Assert.fail("Send Workitem window box not present ");
		}

		SendButton.click();
		Reporter.log("Send Button is clicked", true);
		util.waitForPageToLoad();
		Thread.sleep(10000);
		util.waitForElementEnabled(hp.getInbox());
		util.jclick(hp.getInbox());
		//hp.getInbox().click();
		Reporter.log("Inbox tab is clicked", true);
		ATUReports.add("Inbox tab is clicked", true);
		Thread.sleep(5000);
		if (util.verifyObjectEnabledReturnsBool(ip.getDataEntry())) {
			ip.getDataEntry().click();
			Reporter.log("Data Entry is clicked", true);
			ATUReports.add("Data Entry is clicked", true);
		} else {
			ip.getCustom().click();
			Reporter.log("Custom is clicked", true);
		}
		// ip.getInvoiceProcessing().click();
		//util.jclick(ip.getNextButton());
		//Thread.sleep(10000);
		ip.searchByNameInDataEntry(workitem);
		Reporter.log("Searching Workitem By Name In Data Entry", true);
		boolean workitemPresence = util.verifyObjectPresentReturnsBool(getWorkItemName(workitem));
		if (workitemPresence) {
			Reporter.log("Sent Workitem is present in grid", true);
			ATUReports.add("Verify Sent workitem present in Data entry", "workitem Name: " + workitem,
					"Workitem should be present in the list", "Workitem is successfully dispalyed under Data Entry",
					true);
		} else {
			Reporter.log("Sent Workitem is not present in grid", true);
			Assert.fail("Sent Workitem is not present in grid");
		}

		/*} catch (Exception e) {
				e.printStackTrace();
				ATUReports.add("Send WorkItem To Default Queue failed ", LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.DESKTOP));

			}*/
	}

	// Avnish 28-July-2018
	public void CreateWorkitemClickSubmit(String WorkitemName, String ClassName, String WorkitemType) {
		try {
			util.waitForElementEnabled(createWorkitem_btn);
			boolean f = util.verifyObjectPresentReturnsBool(getCreateWorkitem_btn());
			if (f) {
				Reporter.log("Create Workitem page box present", true);
				Log.info("Create Workitem page box present");
			} else {
				Reporter.log("Create Workitem page box not present", true);
				Assert.fail("Create Workitem page box not present ");
			}

			getCreateWorkitem_btn().click();
			Thread.sleep(2000);

			f = util.verifyObjectPresentReturnsBool(getWorkitemCreate_win());
			if (f) {
				Reporter.log("Create Workitem window box is present", true);
				Log.info("Create Workitem window box is present");
			} else {
				Reporter.log("Create Workitem window box not present", true);
				Assert.fail("Create Workitem window box not present ");
			}
			getWorkItemname_TF().clear();
			getWorkItemname_TF().sendKeys(WorkitemName);
			Reporter.log(" Workitem name inserted", true);
			Log.info(" Workitem name inserted");
			Select sel = new Select(getClassName_dd());
			sel.selectByValue(ClassName);
			Reporter.log("ClassName selected as " + ClassName, true);
			Log.info("ClassName selected as " + ClassName);
			Thread.sleep(2000);

			Select sel1 = new Select(getWorkitemtype_dd());
			sel1.selectByValue(WorkitemType);
			Reporter.log("WorkitemType selected as " + WorkitemType, true);
			Thread.sleep(2000);
			getCreateWorkitem_submitbtn().click();
			Reporter.log("Create Workitem submit button clicked", true);
			Log.info("Create Workitem submit button clicked");
			util.waitForPageToLoad();

		} catch (Exception e) {
			e.printStackTrace();
			ATUReports.add("Create workitem failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));

		}
	}


	@FindBy(xpath = "(//span[@class='ui-button-icon-primary ui-icon ui-icon-closethick'])[11] ")
	private WebElement workitemClose_button;

	public WebElement getWorkitemClose_button() {
		return workitemClose_button;
	}
	//@FindBy(xpath = "//input[@id='userQueue']")
	@FindBy(xpath = "//input[@id='selectall' and @class='mousetrap']")
	private WebElement selectAllCheckBox;

	public WebElement getselectAllCheckBox() {
		return selectAllCheckBox;
	}

	@FindBy(xpath = "//td[@id='emptyRenameWISubmitID']")
	private WebElement emptyRenameWorkItemAlert;

	public WebElement getemptyRenameWorkItemAlert() {
		return emptyRenameWorkItemAlert;
	}


	// Added by Suman - 26-Sep-2018
	@FindBy(xpath = "(//button[@class='actionBtn'])[2]")
	private WebElement secondActionsBtn;

	public WebElement getSecondActionsBtn() {
		return secondActionsBtn;
	}

	//Added by Avnish
	@FindBy(xpath = "//select[@id='userName']")
	private WebElement userDropDown;


	public WebElement getUserNameDropDown() {
		return userDropDown;
	}

	//Added by SumanGaK on 27-Nov-2018.
	public boolean isElementPresent(WebElement webElement) {
		try {
			webElement.isEnabled();
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}	

	@FindBy(xpath = "(//button/span[@class='ui-button-icon-primary ui-icon ui-icon-closethick'])[1]")
	private WebElement metaDataCloseMark;

	public WebElement getMetaDataCloseMark() {
		return metaDataCloseMark;
	}

}
