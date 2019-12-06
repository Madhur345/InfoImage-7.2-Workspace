package apd.infoimage.iwm.PageFactoryRm;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;
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
public class RmHomePage extends ServerClassIWM {

	@FindBy(xpath = "(//a[@role='menuitem']//span[contains(text(),'Dashboard')])[2]")
	private WebElement dashboardTab;

	public WebElement getDashboardTab() {
		return dashboardTab;
	}

	@FindBy(xpath = "//span[contains(text(),'My Sites')]")
	private WebElement mySitesField;

	public WebElement getMySitesField() {
		return mySitesField;
	}

	@FindBy(xpath = "//i[@class='icon-eye-close']/..")
	private WebElement iwmSite;

	public WebElement getIwmSite() {
		return iwmSite;
	}

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	private WebElement logoutRM;

	public WebElement getLogoutRM() {
		return logoutRM;
	}

	@FindBy(xpath = "(//a[@role='menuitem']//span[contains(text(),'File Plan')])[2]")
	private WebElement filePlanTab;

	public WebElement getFilePlanTab() {
		return filePlanTab;
	}

	/**
	 * This method is to Log out of RM application
	 * 
	 * @author PradhanJ
	 * @param userName
	 * @param password
	 * @param domain
	 */
	@SuppressWarnings("deprecation")
	public void logoutOfRM() {
		try {
			logoutRM.click();
			util.waitForPageToLoad();
			boolean f = util.verifyObjectPresentReturnsBool(rmlp.getRmPwdTextBox());
			if (f) {
				Reporter.log("RM Log out successfull ", true);
				ATUReports.add("RM Log out successfull", true);
				Log.info("RM Log out successfull ");
			} else {

				Reporter.log("RM Log out failed ", true);
				Log.error("RM Log out failed");
				Assert.fail("RM Log out failed ");
			}
		} catch (Exception e) {
			Reporter.log("RM Log out failed ", true);
			Log.error("RM Log out failed " + e.getMessage());
			ATUReports.add("RM Log out failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("RM Log out failed ");
		}

	}

	// Added by Suman - 28-Jun-2018
	@FindBy(xpath = "(//a[@role='menuitem']//span[contains(text(),'Audit Log')])[2]")
	private WebElement auditLogTab;

	public WebElement getAuditLogTab() {
		return auditLogTab;
	}

	@FindBy(xpath = "//button[contains(text(),'Alert Messages')]")
	private WebElement alertMessagesButton;

	public WebElement getAlertMessagesButton() {
		return alertMessagesButton;
	}

	@FindBy(xpath = "//input[@value='Delete all Alerts']")
	private WebElement deleteAllAlertsButton;

	public WebElement getDeleteAllAlertsButton() {
		return deleteAllAlertsButton;
	}

	@FindBy(xpath = "(//button[@type='button'])[10]")
	private WebElement deleteAllAlertsYesButton;

	public WebElement getDeleteAllAlertsYesButton() {
		return deleteAllAlertsYesButton;
	}

	@FindBy(xpath = "//tbody/tr[1]/td[1]")
	private WebElement firstRowFirstCell;

	public WebElement getFirstRowFirstCell() {
		return firstRowFirstCell;
	}

	@FindBy(linkText = "Run Scheduler")
	private WebElement runSchedulerLink;

	public WebElement getRunSchedulerLink() {
		return runSchedulerLink;
	}

	@FindBy(xpath = "//span[ text()=' InfoImage Record Manager ']")
	private List<WebElement> rmBannerSize;

	public List<WebElement> getRmBannerSize() {
				return rmBannerSize;		
	}


	@FindBy(xpath = "//div/div[contains(@class,'alert alert-success')]")
	private WebElement runSchedulerSuccessMessage;

	public WebElement getRunSchedulerSuccessMessage() {
		return runSchedulerSuccessMessage;
	}

}
