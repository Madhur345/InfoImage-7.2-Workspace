package apd.infoimage.iwm.PageFactoryRm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.projectLib.ServerClassIWM;

/**
 * @author PradhanJ
 *
 */
public class RmFilePlanPage extends ServerClassIWM {

	@FindBy(xpath = "//button[@onclick='createSeries()']")
	private WebElement addSeriesButton;

	public WebElement getAddSeriesButton() {
		return addSeriesButton;
	}

	@FindBy(xpath = "//h3[contains(text(),'Add Series')]")
	private WebElement addSeriesPopup;

	public WebElement getAddSeriesPopup() {
		return addSeriesPopup;
	}

	@FindBy(xpath = "//h3[contains(text(),'Add Series')]/../..//input[@id='name']")
	private WebElement seriesName_TF;

	public WebElement getSeriesName_TF() {
		return seriesName_TF;
	}

	@FindBy(xpath = "//h3[contains(text(),'Add Series')]/../..//input[@id='title']")
	private WebElement seriesTitle_TF;

	@FindBy(xpath = "//h3[contains(text(),'Add Series')]/../..//textarea[@id='description']")
	private WebElement seriesDescription_TF;

	@FindBy(xpath = "//h3[contains(text(),'Add Series')]/../..//input[@id='saveBtn']")
	private WebElement addSeries_SaveBtn;

	@FindBy(xpath = "//label[contains(text(),'Rows: ')]/select")
	private WebElement rowsDropdown;

	public WebElement getSeriesTitle_TF() {
		return seriesTitle_TF;
	}

	public WebElement getSeriesDescription_TF() {
		return seriesDescription_TF;
	}

	public WebElement getAddSeries_SaveBtn() {
		return addSeries_SaveBtn;
	}

	public WebElement getRowsDropdown() {
		return rowsDropdown;
	}

	public WebElement getSeriesNameRadioInSeriesView(String seriesName) {
		WebElement series = Driver.driver.findElement(By.xpath("//h4[contains(text(),'" + seriesName + "')]"));
		return series;
	}

	@FindBy(xpath = "//button[contains(text(),'Edit Series')]")
	private WebElement editSeriesButton;

	@FindBy(xpath = "//h3[contains(text(),' Edit Series')]")
	private WebElement editSeriesPopup;

	@FindBy(xpath = "//h3[contains(text(),' Edit Series')]/../..//input[@id='name']")
	private WebElement seriesNameInEditSeries;

	@FindBy(xpath = "//h3[contains(text(),' Edit Series')]/../..//input[@value='Update']")
	private WebElement updateBtnInEditSeries;

	@FindBy(xpath = "//button[@id='del']")
	private WebElement deleteSeriesButton;

	@FindBy(xpath = "//span[contains(text(),'Series Deleted Successfully')]")
	private WebElement deleteSeriesSucMsg;

	@FindBy(xpath = "//span[contains(text(),'Series Updated Successfully ')]")
	private WebElement editSeriesSucMsg;

	@FindBy(xpath = "//span[contains(text(),'Series Added Successfully')]")
	private WebElement addSeriesSucMsg;

	public WebElement getEditSeriesButton() {
		return editSeriesButton;
	}

	public WebElement getEditSeriesPopup() {
		return editSeriesPopup;
	}

	public WebElement getSeriesNameInEditSeries() {
		return seriesNameInEditSeries;
	}

	public WebElement getUpdateBtnInEditSeries() {
		return updateBtnInEditSeries;
	}

	public WebElement getDeleteSeriesButton() {
		return deleteSeriesButton;
	}

	public WebElement getDeleteSeriesSucMsg() {
		return deleteSeriesSucMsg;
	}

	public WebElement getEditSeriesSucMsg() {
		return editSeriesSucMsg;
	}

	public WebElement getAddSeriesSucMsg() {
		return addSeriesSucMsg;
	}

	@FindBy(xpath = "//span[text()='File Plan']/..//a[text()='+']")
	private WebElement filePlanPlusSymbol;

	public WebElement getFilePlanPlusSymbol() {
		return filePlanPlusSymbol;
	}

	public WebElement getSeriesUnderFilePlan(String seriesName) {
		WebElement series = Driver.driver.findElement(By.xpath("//span[contains(text(),'" + seriesName + "')]"));
		return series;
	}

	@FindBy(xpath = "//h3[text()='Add Category']")
	private WebElement addCategoryPopup;

	public WebElement getAddCategoryButton() {
		return addCategoryButton;
	}

	@FindBy(xpath = "//button[@onclick='createCategory()']")
	private WebElement addCategoryButton;

	@FindBy(xpath = "//h3[text()='Add Category']/../..//input[@id='name']")
	private WebElement categoryName_TF;

	@FindBy(xpath = "//h3[text()='Add Category']/../..//input[@id='title']")
	private WebElement categorytTitle_TF;

	@FindBy(xpath = "//h3[text()='Add Category']/../..//textarea[@id='description']")
	private WebElement categoryDesc_TF;

	@FindBy(xpath = "//h3[text()='Add Category']/../..//input[@type='submit']")
	private WebElement addCategorySaveButton;

	public WebElement getAddCategoryPopup() {
		return addCategoryPopup;
	}

	public WebElement getCategoryName_TF() {
		return categoryName_TF;
	}

	public WebElement getCategorytTitle_TF() {
		return categorytTitle_TF;
	}

	public WebElement getCategoryDesc_TF() {
		return categoryDesc_TF;
	}

	public WebElement getAddCategorySaveButton() {
		return addCategorySaveButton;
	}

	@FindBy(xpath = "//span[contains(text(),'Category Added Successfully')]")
	private WebElement addCategorySucMsg;

	public WebElement getAddCategorySucMsg() {
		return addCategorySucMsg;
	}

	public WebElement getCategoryNameRadioInCategoryView(String categoryName) {
		WebElement category = Driver.driver.findElement(By.xpath("//h4[contains(text(),'" + categoryName + "')]"));
		return category;
	}

	@FindBy(xpath = "//button[@id='edit']")
	private WebElement editCategoryButton;

	public WebElement getEditCategoryButton() {
		return editCategoryButton;
	}

	@FindBy(xpath = "//h3[contains(text(),'Edit Category')]")
	private WebElement editCategoryPopup;

	@FindBy(xpath = "//h3[contains(text(),'Folder Disposition Schedule')]")
	private WebElement ViewDispositionSchedulerPopup;

	public WebElement getViewDispositionSchedulerPopup() {
		return ViewDispositionSchedulerPopup;
	}

	@FindBy(xpath = "//h3[contains(text(),'Edit Category')]/../..//input[@id='name']")
	private WebElement catNameInEditCategory;

	@FindBy(xpath = "//h3[contains(text(),'Edit Category')]/../..//input[@value='Update']")
	private WebElement updateBtnInEditCategory;

	@FindBy(xpath = "//span[contains(text(),'Category Updated Successfully')]")
	private WebElement editCategorySucMsg;

	@FindBy(xpath = "//button[@id='del']")
	private WebElement deleteCategoryButton;

	@FindBy(xpath = "//span[contains(text(),'Category Deleted Successfully')]")
	private WebElement deleteCategorySucMsg;

	public WebElement getEditCategoryPopup() {
		return editCategoryPopup;
	}

	public WebElement getCatNameInEditCategory() {
		return catNameInEditCategory;
	}

	public WebElement getUpdateBtnInEditCategory() {
		return updateBtnInEditCategory;
	}

	public WebElement getEditCategorySucMsg() {
		return editCategorySucMsg;
	}

	public WebElement getDeleteCategoryButton() {
		return deleteCategoryButton;
	}

	public WebElement getDeleteCategorySucMsg() {
		return deleteCategorySucMsg;
	}

	@FindBy(xpath = "//div[@id='dispEdit' and @class='ui blue mini button']")
	private WebElement ViewDispostionSchedule;

	public WebElement getViewDispostionSchedule() {
		return ViewDispostionSchedule;
	}

	//// input[@id='showAddStep']
	@FindBy(xpath = "//input[@class='btn btn-info' and @id='showAddStep']")
	private WebElement CategoryDispostionScheduleAddstep;

	public WebElement getCategoryDispostionScheduleAddstep() {
		return CategoryDispostionScheduleAddstep;
	}

	//// select[@id='dispositionStep']

	@FindBy(xpath = "//select[@id='dispositionStep']")
	private WebElement CategoryDispositionScheduleNewStep;

	public WebElement getCategoryDispositionScheduleNewStep() {
		return CategoryDispositionScheduleNewStep;
	}
	//// button[@class='btn btn-info dispUpdateButton']

	@FindBy(xpath = "//button[@class='btn btn-info dispUpdateButton']")
	private WebElement Submit_CategoryDispositionSchedule;

	public WebElement getCategoryDispositionSchedule_Submit() {
		return Submit_CategoryDispositionSchedule;
	}

	//// input[@id='periodCheck']

	@FindBy(xpath = "//input[@id='periodCheck']")
	private WebElement checkbox_PeriodCheck;

	public WebElement getperiodcheck_checkbox() {
		return checkbox_PeriodCheck;
		//// div[@id='invalidStep']
	}

	@FindBy(xpath = "//div[contains(text(),'Invalid Step - First Step should always be Cutoff')]")
	private WebElement InvalidStepMsg;

	public WebElement getInvalidStepMsg() {
		return InvalidStepMsg;
	}

}
