package apd.infoimage.iwm.pageFactory;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.projectLib.ServerClassIWM;

/**
 * @author PradhanJ
 *
 */
public class WorkItemDetailViewPage extends ServerClassIWM {

	@FindBy(xpath = "//h2[contains(text(),'Form Fields')]")
	private WebElement Formfields_win;

	public WebElement getFormfields_win() {
		return Formfields_win;
	}
	
	@FindBy(xpath = "//input[@id='ID_CODE_1']") //Added by Chandan for 7.2 liferay
	//@FindBy(css = "input#ID_CODE_1")
	private WebElement Idcode_TF;

	public WebElement getIdcode_TF() {
		return Idcode_TF;
	}
	
	@FindBy(xpath = "//input[@id='INVOICE_NO_2']") //Added by Chandan for 7.2 liferay
	//@FindBy(css = "input#INVOICE_NO_2")
	private WebElement Invoiceno_TF;

	public WebElement getInvoiceno_TF() {
		return Invoiceno_TF;
	}

	@FindBy(xpath = ".//*[@name='TERMS']")
	private WebElement Terms_DD;

	public WebElement getTerms_DD() {
		return Terms_DD;
	}

	@FindBy(xpath = "//i[@class='icon-adImp']")
	private WebElement media_Import;

	public WebElement getmedia_Import() {
		return media_Import;
	}

	@FindBy(id = "mediaTypeImportFileType")
	private WebElement media_Import_Browse;

	public WebElement getmedia_Import_Browse() {
		return media_Import_Browse;
	}

	@FindBy(css = "input#ItemA_5")
	private WebElement ItemA_TF;

	public WebElement getItemA_TF() {
		return ItemA_TF;
	}
	
	@FindBy(xpath = "//span[@class='lfr-btn-label']") //Added by Chandan for 7.2 liferay
	//@FindBy(xpath = "//button[contains(text(),'Update')]")
	private WebElement Update_btn;

	public WebElement getUpdate_btn() {
		return Update_btn;
	}

	@FindBy(xpath = ".//*[@id='contentItem']/h2")
	private WebElement Contents_win;

	public WebElement getContents_win() {
		return Contents_win;
	}

	/*
	 * @FindBy(xpath="//button[contains(text(),'Cancel')]") private WebElement
	 * Cancel_btn;
	 * 
	 * public WebElement getCancel_btn() { return Cancel_btn; }
	 */

	@FindBy(xpath = "(//ul/li/i)[1]")
	private WebElement Cancel;

	public WebElement getCancel() {
		return Cancel;
	}

	@FindBy(xpath = "//div/button[2]")
	private WebElement Cancelbtn;

	public WebElement getCancelbtn() {
		return Cancelbtn;
	}

	@FindBy(xpath = "//a[@title='Add New Page']")
	private WebElement Addcontent_btn;

	public WebElement getAddcontent_btn() {
		return Addcontent_btn;
	}

	@FindBy(css = "input#PageAttachmentFileType")
	private WebElement Browse_btn;

	public WebElement getBrowse_btn() {
		return Browse_btn;
	}

	
	@FindBy(xpath = "//button[contains(text(),'Upload')]")
	private WebElement Upload_btn;

	public WebElement getUpload_btn() {
		return Upload_btn;
	}
	
	@FindBy(xpath = "//div[13]//button[1]//span[1]") //Added by Chandan for 7.2 liferay
	//@FindBy(xpath = "//input[@value='Close']") 
	private WebElement Close_btn;

	public WebElement getClose_btn() {
		return Close_btn;
	}

	@FindBy(xpath = "//h2[contains(text(),'Import')]")
	private WebElement Imports_win;

	public WebElement getImports_win() {
		return Imports_win;
	}

	@FindBy(xpath = "//input[@id='importFileType']")
	private WebElement Addimport_btn;

	public WebElement getAddimport_btn() {
		return Addimport_btn;
	}

	@FindBy(xpath = "(//h2[contains(text(),'Notes')])[1]")
	private WebElement Notes_win;

	public WebElement getNotes_win() {
		return Notes_win;
	}

	@FindBy(xpath = "//a[contains(@title,'Add New Note')]")
	private WebElement AddNotes_btn;

	public WebElement getAddNotes_btn() {
		return AddNotes_btn;
	}

	@FindBy(xpath = "//input[@name='newNoteName']")
	private WebElement NoteTitle_TF;

	public WebElement getNoteTitle_TF() {
		return NoteTitle_TF;
	}

	@FindBy(xpath = "(//textarea[(@class='noteTextArea')])[2]")
	private WebElement Notedesc_TA;

	public WebElement getNotedesc_TA() {
		return Notedesc_TA;
	}

	@FindBy(xpath = "//button[@class='btn btn-primary' and @id='addNote_btn']")
	private WebElement Addnote_btn;

	public WebElement getAddnote_btn() {
		return Addnote_btn;
	}

	@FindBy(xpath = "//div[@class='notesList']")
	private WebElement Noteslist_win;

	public WebElement getNoteslist_win() {
		return Noteslist_win;
	}

	@FindBy(xpath = "//span[contains(text(),'Unisys')]")
	private WebElement Note1;

	public WebElement getNote1() {
		return Note1;
	}

	@FindBy(xpath = "//span[contains(text(),'mp4')]")
	private WebElement Media_file;

	public WebElement getMedia_file() {
		return Media_file;
	}

	@FindBy(xpath = "(//h2[contains(text(),'Notes')])[2]")
	private WebElement Noteviewer_win;

	public WebElement getNoteviewer_win() {
		return Noteviewer_win;
	}

	@FindBy(xpath = "//span[text()='ID_CODE*']")
	private WebElement idCodePopupInWDP;

	public WebElement getIdCodePopupInWDP() {
		return idCodePopupInWDP;
	}

	// vinay
	public WebElement getWorkitemHeader(String workItem) {
		WebElement workitemHeader = Driver.driver.findElement(By.xpath("//a[text()='" + workItem + "']"));
		return workitemHeader;
	}

	// Added By Jayashri

	@FindBy(xpath = "//div[@id='contentItem']")
	private WebElement contentField;

	public WebElement getContentField() {
		return contentField;
	}

	// Added By Avnish  19-Nov-2018
    @FindBy(xpath = "//input[@type='search']")
	private WebElement searchWorkItemTextBox;

	public WebElement getSearchWorkItem() {
		return searchWorkItemTextBox;
	}
	
	
	// Added By Suman - 9/11/2017
	@FindBy(xpath = "//div[@id='importsItem']")
	private WebElement importsItem;

	public WebElement getImportsItem() {
		return importsItem;
	}

	@FindBy(xpath = "//span[@class='pageFW']")
	private WebElement fitWidth;

	public WebElement getFitWidth() {
		return fitWidth;
	}

	@FindBy(xpath = "//span[@class='pageFS']")
	private WebElement fitScreen;

	public WebElement getFitScreen() {
		return fitScreen;
	}

	@FindBy(xpath = "//span[@class='pageFH']")
	private WebElement fitHeight;

	public WebElement getFitHeight() {
		return fitHeight;
	}

	@FindBy(tagName = "rect")
	private WebElement wiDimensions;

	public WebElement getWorkitemDimensions() {
		return wiDimensions;
	}

	// Added By Jayashri
	@FindBy(xpath = "//i[@class='icon-adNwPg']")
	private WebElement addNewPageIcon;

	public WebElement getAddNewPageIcon() {
		return addNewPageIcon;
	}

	// Added By Jayashri
	@FindBy(xpath = "//span[contains(text(),'Add New Page')]")
	private WebElement addNewPageWin;

	public WebElement getAddNewPageWin() {
		return addNewPageWin;
	}

	// Added By Jayashri
	@FindBy(xpath = "//input[@id='PageAttachmentFileType']")
	private WebElement addNewPageBrowseBtn;

	public WebElement getAddNewPageBrowseBtn() {
		return addNewPageBrowseBtn;
	}

	// Added By Jayashri
	@FindBy(xpath = "//span[@class='attachlabel']")
	private WebElement selectedFile;

	public WebElement getSelectedFile() {
		return selectedFile;
	}
	// Added By Jayashri

	public int getContentPageNo() {
		List<WebElement> pagesInContent = Driver.driver.findElements(By.xpath("//a[contains(@id,'thumbnail')]"));
		return pagesInContent.size();
	}

	// Added By Jayashri
	@FindBy(xpath = "//span[@id='countSpan']")
	private WebElement pageCount;

	public WebElement getPageCount() {
		return pageCount;
	}

	@FindBy(xpath = "//form[@name='UploadPage']//input[@class='btn btn-primary']")
	//@FindBy(xpath = "/html[1]/body[1]/div[12]/div[2]/div[1]/form[1]/div[1]/input[1]")
	// Added By Jayashri 
	//@FindBy(xpath = "//div[@id='addNewPageDialog']//input[@class='btn btn-primary']")
	private WebElement addNewPageUploadBtn;

	public WebElement getAddNewPageUploadBtn() {
		return addNewPageUploadBtn;
	}

	// Added By Suman - 9/11/2017
	// @FindBy(xpath="//button[@id='mediaTypeImportUploadBtn']")
	@FindBy(xpath = "//button[text()='Upload']")
	private WebElement addNewVidUploadBtn;

	public WebElement getAddNewVideoFileUploadBtn() {
		return addNewVidUploadBtn;
	}

	// Added By Suman - 9/11/2017
	@FindBy(id = "yui_patched_v3_11_0_1_1510228738829_468")
	private WebElement Mediafile;

	public WebElement getMediafile() {
		return Mediafile;
	}

	// Added By Suman - 15/11/2017
	@FindBy(xpath = "//input[@id='Date_3']")
	private WebElement date;

	public WebElement getDate() {
		return date;
	}

	@FindBy(xpath = "//img[@class='ui-datepicker-trigger']")
	private WebElement datewidget;

	public WebElement getDateWidget() {
		return datewidget;
	}

	// Added By Suman - 17/12/2017
	@FindBy(xpath = "//div[@id='addNewPageDialog']//input[@class='closebtndialogform']")
	private WebElement closeBtn;

	public WebElement getCloseBtn() {
		return closeBtn;
	}

	// Added By Suman - 17/12/2017
	@FindBy(xpath = "//div[@id='addMediaTypeImportDialog']//input[@class='closebtndialogform']")
	private WebElement videoCloseBtn;

	public WebElement getVideoCloseBtn() {
		return videoCloseBtn;
	}

	public WebElement getObjectId(String objId) {
		WebElement objectId = Driver.driver
				.findElement(By.xpath("//p[label[text()='ID']][contains(text(),'" + objId + "')]"));
		return objectId;
	}

	/*
	 * @FindBy(xpath="//button[text()='Update']") private WebElement updateBtn;
	 * 
	 * public WebElement getUpdateBtn() { return updateBtn; }
	 */

	// Added by Suman
	public WebElement getCheckBoxWorkItemName(String workItem) {
		WebElement checkBoxWorkItemName = Driver.driver
				.findElement(By.xpath("(//td[span/a/font[text()='" + workItem + "']]/preceding-sibling::td/input)[1]"));
		return checkBoxWorkItemName;
	}

	// Added By
	// Jayashri.........................................................
	
	//@FindBy(xpath = "//h2[@id='yui_patched_v3_11_0_1_1570775690018_491']")
	@FindBy(xpath = "//a[contains(@title,'Add text type import')]")
	private WebElement addImportIcon;

	public WebElement getAddImportIcon() {
		return addImportIcon;
	}

	// Added By Jayashri
	
	//@FindBy(xpath = "//i[@id='yui_patched_v3_11_0_1_1570775690018_815']")
	@FindBy(xpath = "//span[text()='Add text type import']")
	private WebElement addImportWindow;

	public WebElement getAddImportWindow() {
		return addImportWindow;
	}
	
	//@FindBy(xpath = "//input[@id='importFileType']")
	@FindBy(xpath = "//span[@class='attachimport']")
	private WebElement selectedImportFile;

	public WebElement getSelectedImportFile() {
		return selectedImportFile;
	}

	//@FindBy(xpath = "//form[@name='UploadImport']//input[@class='btn btn-primary']")
	@FindBy(xpath = "//div[@id='addNewImportDialog']//input[@class='btn btn-primary']")
	private WebElement importUploadBtn;

	public WebElement getImportUploadBtn() {
		return importUploadBtn;
	}

	// Suman - 12-Dec-2017
	@FindBy(xpath = "//span[@class='printPage']")
	private WebElement printIcon;

	public WebElement getPrintIcon() {
		return printIcon;
	}

	@FindBy(xpath = "//li/span[@id='proceed']")
	private WebElement printButton;

	public WebElement getPrintButton() {
		return printButton;
	}

	@FindBy(xpath = "//button[contains(text(),'Print')]")
	private WebElement printPreviewPrintButton;

	public WebElement getPrintPreviewPrintButton() {
		return printPreviewPrintButton;
	}

	// Vinay
	public WebElement getCloseWorkitemIcon(String workItem) {
		WebElement closeWorkitemIcon = Driver.driver
				.findElement(By.xpath("//li/a[text()='" + workItem + "']/preceding-sibling::i"));
		return closeWorkitemIcon;
	}

	public int getContentImportNo() {
		List<WebElement> pagesInImport = Driver.driver.findElements(By.xpath("//a[contains(@id,'iwmImport')]"));
		return pagesInImport.size();
	}

	// Added by Suman - 14-Dec-2017
	@FindBy(xpath = "//i[@class='zoomPlus']")
	private WebElement zoomIn;

	public WebElement getZoomIn() {
		return zoomIn;
	}

	@FindBy(id = "pageViewer")
	private WebElement zoomTag;

	public WebElement getzoomTag() {
		return zoomTag;
	}

	// Added by Suman - 15-Dec-2017
	@FindBy(xpath = "//i[@class='zoomMinus']")
	private WebElement zoomOut;

	public WebElement getZoomOut() {
		return zoomOut;
	}

	// Added by Suman - 3-Jan-2018
	@FindBy(xpath = "//li/span/input[@id='printAllRadioBtn']")
	private WebElement allPagesRadioButton;

	public WebElement getAllPagesRadioButton() {
		return allPagesRadioButton;
	}

	@FindBy(xpath = "//li/span/input[@id='printRangeRadioBtn']")
	private WebElement selectRangePagesRadioButton;

	public WebElement getSelectRangePagesRadioButton() {
		return selectRangePagesRadioButton;
	}

	@FindBy(xpath = "//li/span/input[@id='printrange']")
	private WebElement selectRangePagesTextBox;

	public WebElement getSelectRangePagesTextBox() {
		return selectRangePagesTextBox;
	}

	@FindBy(xpath = "//span[@class='icon-right']")
	private WebElement rotateImageRight;

	public WebElement getRotateImageRight() {
		return rotateImageRight;
	}

	@FindBy(xpath = "//span[@class='icon-left']")
	private WebElement rotateImageLeft;

	public WebElement getRotateImageLeft() {
		return rotateImageLeft;
	}

	@FindBy(xpath = "//input[@id='contentCheckAll']")
	private WebElement selectAllContentBtn;

	public WebElement getSelectAllContentBtn() {
		return selectAllContentBtn;
	}

	@FindBy(xpath = "//i[@class='icon-dltPg']")
	private WebElement deletePageIcon;

	public WebElement getDeletePageIcon() {
		return deletePageIcon;
	}

	@FindBy(xpath = "//div[@id='thumbnailContent']//p")
	private WebElement noContentsMsgField;

	public WebElement getNoContentsMsgField() {
		return noContentsMsgField;
	}

	@FindBy(xpath = "//button[@id='undoChangesButton']")
	private WebElement closeViewerOkBtn;

	public WebElement getCloseViewerOkBtn() {
		return closeViewerOkBtn;
	}

	// added By jayashri.................
	@FindBy(xpath = "//i[@class='icon-delNte']")
	private WebElement deleteNoteBtn;

	public WebElement getDeleteNoteBtn() {
		return deleteNoteBtn;
	}

	@FindBy(xpath = "//div[@id='NoNotesLabel']//p")
	private WebElement noNotesMsgField;

	public WebElement getNoNotesMsgField() {
		return noNotesMsgField;
	}

	// Added by Suman - 8-Mar-2018
	@FindBy(xpath = "//a[@class='download']")
	private WebElement download;

	public WebElement getDownload() {
		return download;
	}
	
	//Added by Chandan 10-oct-1019
	/*@FindBy(xpath = "//i[@id='yui_patched_v3_11_0_1_1571047781184_751']")
	private WebElement actionsDropDown;

	public WebElement getActionsDropDown() {
		return actionsDropDown;
	}
	*/
	

	@FindBy(xpath = "//button[@class='actBtn btn ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")
	private WebElement actionsDropDown;

	public WebElement getActionsDropDown() {
		return actionsDropDown;
	}
	
	
	@FindBy(xpath = "//li[@id='docDuplicate']")
	private WebElement documentDuplicateOption;

	public WebElement getDocumentDuplicateOption() {
		return documentDuplicateOption;
	}

	@FindBy(xpath = "//input[contains(@id,'workitemName')]")
	private WebElement documentDuplicateWorkitemNameTextBox;

	public WebElement getDocumentDuplicateWorkitemNameTextBox() {
		return documentDuplicateWorkitemNameTextBox;
	}

	@FindBy(xpath = "//button[contains(@id,'docDuplicateSubmit')]")
	private WebElement duplicateButton;

	public WebElement getDuplicateButton() {
		return duplicateButton;
	}

	/**
	 * This method updates the WorkItem.
	 * 
	 * @author KamiseRK
	 * @param Idcode_TF
	 * @param Invoiceno_TF
	 * @param Terms_DD
	 * @param ItemA_TF
	 * @throws InterruptedException 
	 * @throws Exception
	 */
	// this has to be here psvm and elements//
	public void WorkItemDetailView(String ID_CODE_1, String INVOICE_NO_2, String TERMS_4, String ItemA_5) throws InterruptedException {

		
			boolean f = util.verifyObjectPresentReturnsBool(getFormfields_win());
			if (f) {
				Reporter.log("Form field window present", true);
			} else {
				Reporter.log("Form field window  present", true);
				Assert.fail("Form field window  present ");
			}
			getFormfields_win().click();
			Thread.sleep(2000);
			// ID_CODE text field
			getIdcode_TF().clear();
			getIdcode_TF().sendKeys("Unisys");
			Reporter.log("Idcode: " + getIdcode_TF() + " has been written", true);
			// Invoice_TF
			Thread.sleep(2000);
			getInvoiceno_TF().clear();
			getInvoiceno_TF().sendKeys("Uni1234");
			Reporter.log("Invoice: " + getInvoiceno_TF() + " has been written", true);
			Thread.sleep(2000);
			Select sel = new Select(getTerms_DD());
			sel.selectByValue("30 days");
			Reporter.log("Terms selected as " + Terms_DD, true);
			Thread.sleep(2000);

			getItemA_TF().clear();
			getItemA_TF().sendKeys("itemA");
			Reporter.log("itemA: " + getItemA_TF() + " has been written", true);
			Thread.sleep(2000);
			// getUpdate_btn().submit();
			getUpdate_btn().click();
			Reporter.log("Update: " + getUpdate_btn() + " has been Clicked", true);
			util.waitForPageToLoad();
			Thread.sleep(10000);
		
	}

	/**
	 * This method updates the WorkItem.
	 * 
	 * @author SumanGaK
	 * @param Idcode_TF
	 * @param Invoiceno_TF
	 * @param Terms_DD
	 * @param ItemA_TF
	 * @throws Exception
	 */
	// this has to be here psvm and elements//
	public void UpdateFormFieldsAndCancel(String ID_CODE_1, String INVOICE_NO_2, String TERMS_4, String ItemA_5) {

		try {
			boolean f = util.verifyObjectPresentReturnsBool(getFormfields_win());
			if (f) {
				Reporter.log("Create Workitem page box present", true);
			} else {
				Reporter.log("Create Workitem page box not present", true);
				Assert.fail("Create Workitem page box not present ");
			}
			getFormfields_win().click();
			Thread.sleep(1000);
			// ID_CODE text field
			getIdcode_TF().clear();
			getIdcode_TF().sendKeys("Unisys");
			Reporter.log("Idcode: " + getInvoiceno_TF() + " has been written", true);
			// Invoice_TF
			getInvoiceno_TF().clear();
			getInvoiceno_TF().sendKeys("Uni1234");
			Reporter.log("Invoice: " + getIdcode_TF() + " has been written", true);
			Select sel = new Select(getTerms_DD());
			sel.selectByValue("30 days");
			Reporter.log("Terms selected as " + Terms_DD, true);
			Thread.sleep(2000);

			getItemA_TF().clear();
			getItemA_TF().sendKeys("itemA");
			Reporter.log("itemA: " + getItemA_TF() + " has been written", true);
			Thread.sleep(2000);
			// getUpdate_btn().submit();
			getCancel().click();
			Reporter.log("Cancel: " + getCancel() + " has been Clicked", true);

			getCancelbtn().click();
			Reporter.log("Cancel: " + getCancelbtn() + " has been Clicked", true);

			Thread.sleep(8000);
			util.waitForPageToLoad();
		}

		catch (Exception e) {
			Reporter.log("Update Form Fields And Cancel failed due to " + e.getMessage());
			Assert.fail("Update Form Fields And Cancel failed Assert");
		}
	}

	public void videoupload(String mediaFilePath) {
		try {
			Thread.sleep(9000);
			wdp.getmedia_Import().click();
			Reporter.log("Add Media Type File Import Button is clicked", true);
			String autoItFilePath = new File("Commons\\exe\\autoIt").getAbsolutePath();
			String uploadexe = autoItFilePath + "\\upload.exe";

			boolean addNewMediaFileWinPresent = util.verifyObjectPresentReturnsBool(wdp.getmedia_Import());
			if (addNewMediaFileWinPresent) {
				Reporter.log("Add new Media File window is displayed", true);

				String vidPath = mediaFilePath + "\\big1.mp4";

				Reporter.log("vid path " + vidPath, true);

				// wdp.getmedia_Import_Browse().click();

				String[] dialog = new String[] { uploadexe, vidPath };
				// Reporter.log("Browse button is clicked", true);

				Thread.sleep(8000);
				wdp.getAddNewVideoFileUploadBtn().click();
				// util.waitForPageToLoad();
				Reporter.log("Add New Video File Upload Button is clicked", true);
				Thread.sleep(5000);
				util.waitForPageToLoad();
				Runtime.getRuntime().exec(dialog);

				Thread.sleep(14000);
			} else {
				Reporter.log("Add new Media file window is NOT displayed", true);
				Assert.fail("Add new Media file window is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Notes creation
	public void Notes(String NoteTitle_TF) throws InterruptedException {
		
			boolean f1 = util.verifyObjectPresentReturnsBool(getNotes_win());
			if (f1) {
				Reporter.log("Notes window is present", true);
			} else {
				Reporter.log("Notes window  not present", true);
				Assert.fail("Notes window not present ");
			}
			Thread.sleep(5000);

			wdp.getNotes_win().click();
			Thread.sleep(5000);

			getAddNotes_btn().click();
			Thread.sleep(5000);

			Reporter.log("Notes_win: " + getNotes_win() + " has been Clicked", true);
			// Note title
			getNoteTitle_TF().click();
			Thread.sleep(2000);
			getNoteTitle_TF().sendKeys("Unisys");
			Reporter.log("NoteTitle: " + getNoteTitle_TF() + " has been entered", true);
			// getNotedesc_TA().click();
			Thread.sleep(2000);
			Reporter.log("text is entered into the Desc area");
			getNotedesc_TA().sendKeys("Note 1 in unisys");
			Reporter.log("Notedesc: " + getNotedesc_TA() + " has been entered", true);
			// submission of the note
			getAddnote_btn().click();
			Reporter.log("Addnote: " + getAddnote_btn() + " has been Clicked", true);
			Thread.sleep(3000);
		
	}

	@FindBy(xpath = "//li[@name='actionFormFieldElement']")
	private WebElement sendToDefaultOption;

	public WebElement getSendToDefaultOption() {
		return sendToDefaultOption;
	}

	/*
	 * @FindBy(xpath="//button[contains(@id,'pageEditToggle')]") private WebElement
	 * editButton;
	 * 
	 * public WebElement getEditButton() { return editButton; }
	 * 
	 * @FindBy(xpath="//span[contains(@class,'applyStamps')]") private WebElement
	 * stampsButton;
	 * 
	 * public WebElement getStampsButton() { return stampsButton; }
	 */

	// Added by Suman - 27-Mar-2018
	@FindBy(xpath = "//li/i[contains(@class,'icon-remove tabClose getNextWorkitemClose')]")
	private WebElement closeWorkitem;

	public WebElement getCloseWorkitem() {
		return closeWorkitem;
	}

	// Added by Suman - 09-Apr-2018
	@FindBy(xpath = "//input[@id='contentCheckAll']")
	private WebElement selectAllCheckbox;

	public WebElement getSelectAllCheckbox() {
		return selectAllCheckbox;
	}

	@FindBy(xpath = "//input[@name='Select All']")
	private WebElement selectAllCheckbox1;

	public WebElement getSelectAllCheckbox1() {
		return selectAllCheckbox1;
	}

	@FindBy(xpath = "//input[contains(@class,'win-commandimage win-commandring notes_button')]")
	private WebElement splitPageIcon;

	public WebElement getSplitPageIcon() {
		return splitPageIcon;
	}

	@FindBy(xpath = "//span[contains(text(),'Split Page')]")
	private WebElement splitPageWindow;

	public WebElement getSplitPageWindow() {
		return splitPageWindow;
	}

	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	private WebElement proceedButton;

	public WebElement getProceedButton() {
		return proceedButton;
	}

	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	private WebElement cancelButton;

	public WebElement getCancelButton() {
		return cancelButton;
	}

	// Added By Suman
	@FindBy(xpath = "//a[@id='pageEditToggle']")
	private WebElement addedNewPagePresence;

	public WebElement getAddedNewPagePresence() {
		return addedNewPagePresence;
	}

	@FindBy(xpath = "//input[@class='contentCheckbox mousetrap']")
	private WebElement selectPageCheckbox;

	public WebElement getSelectPageCheckbox() {
		return selectPageCheckbox;
	}

	@FindBy(xpath = "//input[@class='contentCheckbox mousetrap' and @value= 2]")
	private WebElement selectPageCheckbox2;

	public WebElement getSelectPageCheckbox2() {

		return selectPageCheckbox2;
	}

	@FindBy(xpath = "//input[@class='contentCheckbox mousetrap' and @value= 3]")
	private WebElement selectPageCheckbox3;

	public WebElement getSelectPageCheckbox3() {
		return selectPageCheckbox3;
	}

	@FindBy(linkText = "Download")
	private WebElement exportDownloadLink;

	public WebElement getExportDownloadLink() {
		return exportDownloadLink;
	}

	// Added by Suman - 23-Apr-2018
	@FindBy(xpath = "//input[@id='importCheckAll']")
	private WebElement selectAllCheckboxImport;

	public WebElement getSelectAllCheckboxImport() {
		return selectAllCheckboxImport;
	}

	@FindBy(xpath = "//li/i[contains(@class,'icon-remove tabClose workitemClose')]")
	private WebElement closingWorkitemXMark;

	public WebElement getClosingWorkitemXMark() {
		return closingWorkitemXMark;
	}

	@FindBy(xpath = "//div/input[@class='closebtndialogform']")
	private WebElement importCloseBtn;

	public WebElement getImportCloseBtn() {
		return importCloseBtn;
	}

	// Added By Jayashri 4/26/2018
	@FindBy(xpath = "//input[@name='PageAttachment']")
	private WebElement contentUploadField;

	@FindBy(xpath = "//input[@name='importFile']")
	private WebElement importUploadField;

	@FindBy(xpath = "//input[@id='importVideoId']")
	private WebElement videoUploadField;

	public WebElement getContentUploadField() {
		return contentUploadField;
	}

	public WebElement getImportUploadField() {
		return importUploadField;
	}

	public WebElement getVideoUploadField() {
		return videoUploadField;
	}

	@FindBy(xpath = "//input[@name='import_select']")
	private WebElement importedFile;

	public WebElement getImportedFile() {
		return importedFile;
	}

	@FindBy(xpath = "//td/select/option[@value='invoice']")
	private WebElement differentClassOption;

	public WebElement getDifferentClassOption() {
		return differentClassOption;
	}

	@FindBy(xpath = "//h2[contains(text(),'Notes')]")
	private WebElement notesTab;

	public WebElement getNotesTab() {
		return notesTab;
	}

	@FindBy(xpath = "//span[contains(text(),'Create New Note')]")
	private WebElement createNewNoteWindow;

	public WebElement getCreateNewNoteWindow() {
		return createNewNoteWindow;
	}

	@FindBy(xpath = "//div/a[@id='note1']")
	private WebElement createdNewNote;

	public WebElement getCreatedNewNote() {
		return createdNewNote;
	}

	@FindBy(xpath = "//i[@class='icon-splitPg']")
	private WebElement splitIcon;

	public WebElement getMedia_Import() {
		return media_Import;
	}

	public WebElement getMedia_Import_Browse() {
		return media_Import_Browse;
	}

	public WebElement getWiDimensions() {
		return wiDimensions;
	}

	public WebElement getAddNewVidUploadBtn() {
		return addNewVidUploadBtn;
	}

	public WebElement getDatewidget() {
		return datewidget;
	}

	public WebElement getZoomTag() {
		return zoomTag;
	}

	public WebElement getSplitIcon() {
		return splitIcon;
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean filePresence = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return filePresence = true;
		}
		return filePresence;
	}

	/* Get the latest file from a specific directory */
	public File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	// Added by Suman - 09-May-2018
	public WebElement getWorkItemName(String workItem) {
		WebElement createdWorkItem = Driver.driver.findElement(By.xpath("//font[text()='" + workItem + "']"));
		return createdWorkItem;
	}

	
	// Added by Avnish
	@FindBy(xpath = "//a[@class='workitemName']")
	private WebElement contentList;

	public WebElement getContentList() {
		return contentList;
	}
	
	@FindBy(xpath = "//input[@id='restoreFormData']")
	private WebElement includeFormDataCheckBox;

	public WebElement getIncludeFormDataCheckBox() {
		return includeFormDataCheckBox;
	}

	// Added by Suman - 11-May-2018
	@FindBy(xpath = "//div/a/i[contains(@class,'copyPg')]")
	private WebElement copyPage;

	public WebElement getCopyPage() {
		return copyPage;
	}

	@FindBy(xpath = "//div/a/i[contains(@class,'pastePg')]")
	private WebElement pastePage;

	public WebElement getPastePage() {
		return pastePage;
	}

	@FindBy(xpath = "//div/button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only ui-dialog-titlebar-close']")
	private WebElement pageCopiedXMark;

	public WebElement getPageCopiedXMark() {
		return pageCopiedXMark;
	}

	@FindBy(xpath = "//input[@value='4']")
	private WebElement fourthPageCheckbox;

	public WebElement getFourthPageCheckbox() {
		return fourthPageCheckbox;
	}

	@FindBy(xpath = "//input[@id='splitWorkItemName']")
	private WebElement splitWitemNameTextbox;

	public WebElement getsplitWitemNameTextbox() {
		return splitWitemNameTextbox;
	}

	@FindBy(xpath = "//select[@name='splitWIDropdown']")
	private WebElement splitWIDropdown;

	public WebElement getSplitWIDropdown() {
		return splitWIDropdown;
	}

	@FindBy(xpath = "//input[@name='splitWIIncludeFormData']")
	private WebElement splitWIIncludeFormDataCheckbox;

	public WebElement getSplitWIIncludeFormDataCheckbox() {
		return splitWIIncludeFormDataCheckbox;
	}

	@FindBy(xpath = "//button[@name='splitWISubmit']")
	private WebElement splitButton;

	public WebElement getSplitButton() {
		return splitButton;
	}

	@FindBy(xpath = "//table[@class='splitListtable']//tr[2]/td[2]")
	private WebElement splitWitemName;

	public String getSplitWitemName() {
		return splitWitemName.getText();
	}

	@FindBy(xpath = "//input[@value='2']")
	private WebElement secondPageCheckbox;

	public WebElement getSplitWitemNameTextbox() {
		return splitWitemNameTextbox;
	}

	public WebElement getSecondPageCheckbox() {
		return secondPageCheckbox;
	}

	@FindBy(xpath = "//table[@class='splitListtable']//tr[3]/td[2]")
	private WebElement splitSecondWitemName;

	public String getSplitSecondWitemName() {
		return splitSecondWitemName.getText();
	}

	@FindBy(xpath = "//input[@value='3']")
	private WebElement thirdPageCheckbox;

	public WebElement getThirdPageCheckbox() {
		return thirdPageCheckbox;
	}

	@FindBy(xpath = "//div[@id='MandatoryFieldRequiredSplit']/div")
	private WebElement mandatoryFieldErrorMsg;

	public WebElement getMandatoryFieldErrorMsg() {
		return mandatoryFieldErrorMsg;
	}

	@FindBy(xpath = "//button[@class='print default']")
	private WebElement printButtonInPrintWindow;

	public WebElement getPrintButtonInPrintWindow() {
		return printButtonInPrintWindow;
	}

	@FindBy(xpath = "//input[@id='printCurrentRadioBtn']")
	private WebElement currentPageRadio;

	public WebElement getCurrentPageRadio() {
		return currentPageRadio;
	}

	// Added by Avnish on 30/05/2018
	@FindBy(xpath = "//a/span[contains(text(),'Workitems')]")
	private WebElement WorkItems;

	public WebElement getWorkItemsTab() {
		return WorkItems;
	}

	// Added by Avnish on 30/05/2018
	@FindBy(xpath = "//i[@class='icon-exportPg']")
	private WebElement exportImage;

	public WebElement getExportImage() {
		return exportImage;
	}

	// Added by Avnish on 31/05/2018
	@FindBy(xpath = "//input[@id='exportRadioBtn' and @value='GIF']")
	private WebElement uploadGIF;

	public WebElement getUploadGIF() {
		return uploadGIF;
	}

	@FindBy(xpath = "//input[@id='exportRadioBtn' and @value='PDF']")
	private WebElement uploadPDF;

	public WebElement getUploadPDF() {
		return uploadPDF;
	}

	// Added by Avnish on 13/05/2018
	@FindBy(xpath = "(//span[@class='txtAlgn'])[4]")
	private WebElement actionButton;

	public WebElement getActionButton() {
		return actionButton;
	}

	@FindBy(xpath = "(//input[@id='exportRadioBtn' and @value='TIF' and @type='radio'])[2]")
	private WebElement uploadTIF;

	public WebElement getUploadTIF() {
		return uploadTIF;
	}

	// Added by Avnish on 31/05/2018
	@FindBy(xpath = "(//a[@id='exportPage' and text()='Download'])[1]")
	private WebElement downloadButton;

	public WebElement getDownloadButton() {
		return downloadButton;
	}

	// Added by Biswajit on 06/05/2018.
	@FindBy(xpath = "//input[@type='radio'][@value='JPG']")
	private WebElement uploadJPG;

	public WebElement getuploadJPG() {
		return uploadJPG;
	}

	// Added by Biswajit on 06/05/2018.
	@FindBy(xpath = "//input[@name='exportoption'][@value='JPEG']")
	private WebElement uploadJPEG;

	public WebElement getuploadJPEG() {
		return uploadJPEG;
	}

	// Added by Biswajit on 06/05/2018
	@FindBy(xpath = "//input[@name='exportoption'][@value='PNG']")
	private WebElement uploadPNG;

	public WebElement getuploadPNG() {
		return uploadPNG;
	}

	// Added by Biswajit on 06/06/2018.
	@FindBy(xpath = "//input[@name='exportoption'][@value='TIF']")
	private WebElement exportTIF;

	public WebElement getexportTIF() {
		return exportTIF;
	}

	// Added by Biswajit on 11/06/2018.
	@FindBy(xpath = "//input[@name='exportoption'][@value='BMP']")
	private WebElement exportBMP;

	public WebElement getexportBMP() {
		return exportBMP;
	}

	// Added by Biswajit on 11/06/2018.
	@FindBy(xpath = "//input[@name='exportoption'][@value='GIF']")
	private WebElement exportGIF;

	public WebElement getexportGIF() {
		return exportGIF;
	}

	// Added by Biswajit on 11/06/2018.
	@FindBy(xpath = "//input[@name='exportoption'][@value='JPG']")
	private WebElement exportJPG;

	public WebElement getexportJPG() {
		return exportJPG;
	}

	// Added By Suman-12-JUN-2018
	@FindBy(xpath = "//span[@id='ui-id-6']/following-sibling::button[1]")
	private WebElement pageCopiedCloseButton;

	public WebElement getPageCopiedCloseButton() {
		return pageCopiedCloseButton;
	}

	// Added by Biswajit on 14/06/2018.
	@FindBy(xpath = "//input[@name='exportoption'][@value='DIB']")
	private WebElement exportDIB;

	public WebElement getexportDIB() {
		return exportJPG;
	}

	// Added by Avnish on 16/06/2018
	@FindBy(xpath = "//option[@value='class2']")
	private WebElement newClass;

	public WebElement getNewClass() {
		return newClass;
	}

	// Added by Avnish on 19/06/2018
	@FindBy(xpath = "(//span[@class='ui-button-icon-primary ui-icon ui-icon-closethick'])[6]")
	private WebElement pageCopiedCancelButton;

	public WebElement getPageCopiedCancelButton() {
		return pageCopiedCancelButton;
	}

	// Added by Biswajit on 02/08/2018.
	@FindBy(xpath = "//button[@class='actionBtn']")
	private WebElement ActionBtn;

	public WebElement getActionBtn() {
		return ActionBtn;
	}

	// Added by Biswajit on 02/08/2018.
	@FindBy(xpath = "//p[@id='formFieldalertTextWI']	")
	private WebElement RequiredFieldError;

	public WebElement getRequiredFieldError() {
		return RequiredFieldError;
	}

	//
	// Added by Biswajit on 02/08/2018.
	@FindBy(xpath = "//li[@name='actionFormFieldElement']")
	private WebElement formField;

	public WebElement getformField() {
		return formField;
	}

	//
	// Added by Biswajit on 02/08/2018.
	@FindBy(xpath = "//button[@type='reset']")
	private WebElement closebtn;

	public WebElement getclosebtn() {
		return closebtn;
	}

	// Added By Suman-2-AUG-2018
	@FindBy(xpath = "//div[@id='contentItem']/div")
	private WebElement contentSection;

	public WebElement getContentSection() {
		return contentSection;
	}

	// Added By Suman-3-AUG-2018
	@FindBy(xpath = "//a/span[contains(text(),'Workitems')]")
	private WebElement workItemTab;

	public WebElement getWorkItemTab() {
		return workItemTab;
	}

	// Added by Biswajit on 03/08/2018.
	@FindBy(xpath = "//a[@class='workitemName']")
	private WebElement wiView;

	public WebElement getwiView() {
		return wiView;
	}

	// Added by Biswajit on 03/08/2018.
	@FindBy(xpath = "//button[@id='form_validate']")
	private WebElement updateFormButton;

	public WebElement getupdateFormButton() {
		return updateFormButton;
	}

	// Added by Biswajit on 03/08/2018.
	@FindBy(xpath = "//p[@id='formFieldalertText']")
	private WebElement formFieldAlertText;

	public WebElement getformFieldAlertText() {
		return formFieldAlertText;
	}

	// Added by SumanGaK on 07/08/2018.
	@FindBy(xpath = "//input[@value='2_']")
	private WebElement secondImportPageCheckbox;

	public WebElement getSecondImportPageCheckbox() {
		return secondImportPageCheckbox;
	}

	@FindBy(xpath = "//a[contains(@title,'Delete Import')]")
	private WebElement deleteImportIcon;

	public WebElement getDeleteImportIcon() {
		return deleteImportIcon;
	}

	// Added by Biswajit on 08/Aug/2018
	@FindBy(xpath = "//option[@value='invoice']")
	private WebElement invoice;

	public WebElement getinvoice() {
		return invoice;
	}

	// Added by Biswajit on 13/Aug/2018
	@FindBy(xpath = "//div[contains(text(),'* Please fill the mandatory fields to include form data')]")
	private WebElement mandatoryFieldError;

	public WebElement getmandatoryFieldError() {
		return mandatoryFieldError;
	}

	// Added by Biswajit on 20/Aug/2018
	@FindBy(xpath = "//input[@class='contentCheckbox mousetrap' and @type='checkbox']")
	private WebElement inputFirstCheckBox;

	public WebElement getinputFirstCheckBox() {
		return inputFirstCheckBox;
	}

	// Added by Biswajit on 27/Aug/2018
	@FindBy(xpath = "//input[@type='checkbox' and @id='splitWIIncludeFormData']")
	private WebElement splitWIIncludeFormData;

	public WebElement getsplitWIIncludeFormData() {
		return splitWIIncludeFormData;
	}

	////ul[@class='gtNxtPrnt']/li[position()=3]
	// Added by Biswajit on 27/Aug/2018
	@FindBy(xpath = "//ul[@class='gtNxtPrnt']/li[position()=3]")
	private WebElement DataEntry1;

	public WebElement getDataEntry1() {
		return DataEntry1;
	}

	////div[@id='fielditem0']	
	// Added by Biswajit on 29/Aug/2018
	@FindBy(xpath = "//div[@id='fielditem0']")
	private WebElement InvoicenoTB;

	public WebElement getInvoicenoTB() {
		return InvoicenoTB;
	}

	// Added by SumanGaK on 05/09/2018.
	@FindBy(xpath = "//input[@value='3_']")
	private WebElement thirdImportPageCheckbox;

	public WebElement getThirdImportPageCheckbox(){
		return thirdImportPageCheckbox;
	}
	
	@FindBy(xpath = "//input[@value='1_']")
	private WebElement firstImportPageCheckbox;

	public WebElement getFirstImportPageCheckbox(){
		return firstImportPageCheckbox;
	}
	
	// Added by Biswajit on 01/Sept/2018
		@FindBy(xpath = "//I[@class='icon-splitPg']")
		private WebElement clickonSpitIcon;

		public WebElement getclickonSpitIcon() {
			return clickonSpitIcon;
		}

		
		// Added by Biswajit on 04/Sept/2018
		@FindBy(xpath = "//input[@type='button' and @onclick='AddPageAttachment()']	")
		private WebElement clickonuploadButton;

		public WebElement getclickonuploadButton() {
			return clickonuploadButton;
		}

		//Added by Avnish on 11/09/2018
		@FindBy(xpath = "//td/select/option[@value='invoice']")
		private WebElement differentInvoice;

		public WebElement getDifferentInvoiceOption() {
			return differentInvoice;
		}
		
		//Added by SumanGaK on 20-Nov-2018.
		@FindBy(xpath = "//li[@name='Reserve']")
		private WebElement reserveOption;

		public WebElement getReserveOption() {
			return reserveOption;
		}
		
		//Added by SumanGaK on 21-Nov-2018.
		@FindBy(xpath = "//li/span[text()='Send to default']")
		private WebElement sendToDefaultOptionForArchivedFromSearch;

		public WebElement getSendToDefaultOptionForArchivedFromSearch() {
			return sendToDefaultOptionForArchivedFromSearch;
		}
		
		@FindBy(xpath = "//ul[@class='actionRoot custWdth']")
		private WebElement actionsList;

		public WebElement getActionsList() {
			return actionsList;
		}
		
		
		//Added by SumanGaK on 26-Nov-2018.
		public boolean isElementPresent(WebElement webElement) {
		    try {
		    	webElement.isEnabled();
		        return true;
		    } catch (org.openqa.selenium.NoSuchElementException e) {
		        return false;
		    }
		}

		@FindBy(tagName = "body")
		private WebElement newTab;

		public WebElement getNewTab() {
			return newTab;
		}
		
		@FindBy(xpath = "//button[@id='emailFile']")
		private WebElement emailFile;

		public WebElement getEmailFile() {
			return emailFile;
		}
		
		@FindBy(xpath = "//button[@id='signout']")
		private WebElement fullViewSignoutButton;

		public WebElement getFullViewSignoutButton() {
			return fullViewSignoutButton;
		}
		
		@FindBy(xpath="//button[@id='emailFile']")
		private WebElement emailButton;

		public WebElement getSearchWorkItemTextBox() {
			return searchWorkItemTextBox;
		}

		public WebElement getWorkItems() {
			return WorkItems;
		}

		public WebElement getUploadJPG() {
			return uploadJPG;
		}

		public WebElement getUploadJPEG() {
			return uploadJPEG;
		}

		public WebElement getUploadPNG() {
			return uploadPNG;
		}

		public WebElement getExportTIF() {
			return exportTIF;
		}

		public WebElement getExportBMP() {
			return exportBMP;
		}

		public WebElement getExportGIF() {
			return exportGIF;
		}

		public WebElement getExportJPG() {
			return exportJPG;
		}

		public WebElement getExportDIB() {
			return exportDIB;
		}

		public WebElement getFormField() {
			return formField;
		}

		public WebElement getClosebtn() {
			return closebtn;
		}

		public WebElement getWiView() {
			return wiView;
		}

		public WebElement getUpdateFormButton() {
			return updateFormButton;
		}

		public WebElement getFormFieldAlertText() {
			return formFieldAlertText;
		}

		public WebElement getInvoice() {
			return invoice;
		}

		public WebElement getMandatoryFieldError() {
			return mandatoryFieldError;
		}

		public WebElement getInputFirstCheckBox() {
			return inputFirstCheckBox;
		}

		public WebElement getSplitWIIncludeFormData() {
			return splitWIIncludeFormData;
		}

		public WebElement getClickonSpitIcon() {
			return clickonSpitIcon;
		}

		public WebElement getClickonuploadButton() {
			return clickonuploadButton;
		}

		public WebElement getDifferentInvoice() {
			return differentInvoice;
		}

		public WebElement getEmailButton() {
			return emailButton;
		}
		@FindBy(xpath="//div[@id='iwmmessage']")
		private WebElement witemDetailsErrMsg;

		public WebElement getWitemDetailsErrMsg() {
			return witemDetailsErrMsg;
		}
		@FindBy(xpath="//iframe[@id='importDisplayContent']")
		private WebElement importDisplayFrame;

		public WebElement getImportDisplayFrame() {
			return importDisplayFrame;
		}
		
			//Added By Avnish 29/11/2018
					@FindBy(xpath="//div[@class='alert alert-error']")
					private WebElement ErrorMessage;
					
					public WebElement getErrorMessage() {
						return ErrorMessage;
					} 
			 

		
				
}
