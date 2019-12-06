package apd.infoimage.iwm.projectLib;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import apd.infoimage.iwm.PageFactoryRm.RmAuditLogPage;
import apd.infoimage.iwm.PageFactoryRm.RmFilePlanPage;
import apd.infoimage.iwm.PageFactoryRm.RmHomePage;
import apd.infoimage.iwm.PageFactoryRm.RmLoginPage;
import apd.infoimage.iwm.genericLib.CommonUtils;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.pageFactory.CreateWorkitempage;
import apd.infoimage.iwm.pageFactory.FluentWaitClass;
import apd.infoimage.iwm.pageFactory.HomePage;
import apd.infoimage.iwm.pageFactory.InboxPage;
import apd.infoimage.iwm.pageFactory.LoginPage;
import apd.infoimage.iwm.pageFactory.SearchPage;
import apd.infoimage.iwm.pageFactory.UserPreferencesPage;
import apd.infoimage.iwm.pageFactory.WorkItemDetailViewPage;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

/**
 * @author KencharV
 *
 */
public class ServerClassIWM {
	
	public static CommonUtils util = new CommonUtils();
	public static LoginPage lp = PageFactory.initElements(Driver.driver, LoginPage.class);
	public static HomePage hp = PageFactory.initElements(Driver.driver, HomePage.class);
	public static CreateWorkitempage cwp = PageFactory.initElements(Driver.driver, CreateWorkitempage.class);
    public static WorkItemDetailViewPage wdp = PageFactory.initElements(Driver.driver, WorkItemDetailViewPage.class);
    public static InboxPage ip = PageFactory.initElements(Driver.driver, InboxPage.class);
	public static SearchPage sp = PageFactory.initElements(Driver.driver, SearchPage.class);
	public static UserPreferencesPage upp=PageFactory.initElements(Driver.driver,UserPreferencesPage.class);
	public static RmLoginPage rmlp=PageFactory.initElements(Driver.driver, RmLoginPage.class);
	public static RmHomePage rmhp=PageFactory.initElements(Driver.driver, RmHomePage.class); 
	public static RmAuditLogPage rmalp=PageFactory.initElements(Driver.driver, RmAuditLogPage.class); 
	public static RmFilePlanPage rmfpp=PageFactory.initElements(Driver.driver, RmFilePlanPage.class);
	public static FluentWaitClass fwc=PageFactory.initElements(Driver.driver, FluentWaitClass.class); 

	public static void testNewLogs() throws AWTException, IOException {
		ATUReports.add("INfo Step", LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		ATUReports.add("Pass Step", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		WebElement element = Driver.driver.findElement(By.xpath("/html/body/div/h1/a"));

		ATUReports.add("Warning Step", LogAs.WARNING, new CaptureScreen(element));
		ATUReports.add("Fail step", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
	}
}
