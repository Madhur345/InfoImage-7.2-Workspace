package apd.infoimage.iwm.projectLib;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.support.PageFactory;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import apd.infoimage.iwm.PageFactoryRm.RmAuditLogPage;
import apd.infoimage.iwm.PageFactoryRm.RmFilePlanPage;
import apd.infoimage.iwm.PageFactoryRm.RmHomePage;
import apd.infoimage.iwm.PageFactoryRm.RmLoginPage;
import apd.infoimage.iwm.genericLib.CommonUtils;
import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.genericLib.RandomNo;
import apd.infoimage.iwm.pageFactory.CreateWorkitempage;
import apd.infoimage.iwm.pageFactory.CustomJSFormsPage;
import apd.infoimage.iwm.pageFactory.FluentWaitClass;
import apd.infoimage.iwm.pageFactory.HomePage;
import apd.infoimage.iwm.pageFactory.InboxPage;
import apd.infoimage.iwm.pageFactory.LoginPage;
import apd.infoimage.iwm.pageFactory.SearchPage;
import apd.infoimage.iwm.pageFactory.UserPreferencesPage;
import apd.infoimage.iwm.pageFactory.WorkItemDetailViewPage;

/**
 * @author KencharV
 *
 */
public class SuperClassIWM {

	public static CommonUtils util ;
	public static LoginPage lp ;
	public static HomePage hp ;
	public static CreateWorkitempage cwp;
	public static WorkItemDetailViewPage wdp;
	public static Properties prop;
	public static Properties rmProp;
	public static InputStream input = null;
	public static InboxPage ip;
	public static SearchPage sp;
	public static UserPreferencesPage upp;
	public static CustomJSFormsPage cjfp;
	public static RmLoginPage rmlp;
	public static RmHomePage rmhp;
	public static RmAuditLogPage rmalp;
	public static RmFilePlanPage rmfpp;
	public static RandomNo randomNo;
	public static FluentWaitClass fwc;
	{
    	
    	System.setProperty("atu.reporter.config", "src\\main\\resources\\atu.properties");
    } 
	public static String[] getEnvBuildDetails() {
		String[] BuildArray = { "", "" };
		/*
		 * try{ //switchToFrameByName(f0); sleep(2); Robot r = new Robot();
		 * driver.findElement(By.linkText("About")).sendKeys(Keys.RETURN);
		 * r.keyPress(KeyEvent.VK_ENTER); r.keyRelease(KeyEvent.VK_ENTER);
		 * sleep(3);
		 * 
		 * String parentWindow = driver.getWindowHandle();
		 * switchToSecondWindow(parentWindow); sleep(2); String Env =
		 * Driver.driver.findElement(By.xpath(
		 * "//*[@id='aboutPop']/div/div/div[2]/div/div[2]/div[1]/div[2]")).
		 * getText().toString(); String Bld =
		 * Driver.driver.findElement(By.xpath(
		 * "//*[@id='aboutPop']/div/div/div[2]/div/div[2]/div[2]/div[2]")).
		 * getText().toString(); System.out.println(Env+Bld);
		 * 
		 * BuildArray[0] = Env; BuildArray[1] = Bld;
		 * 
		 * Click("//*[@id='aboutPop']/div/div/div[3]/button"); } catch
		 * (Exception e) { e.printStackTrace(); }
		 */
		return BuildArray;
	}


	@BeforeSuite
	public void beforeSuite() throws IOException{
		//Load properties
		prop = new Properties();		
		prop.load(new FileInputStream("src/main/resources/userData.properties"));

		rmProp=new Properties();
		rmProp.load(new FileInputStream("src/main/resources/rmData.properties"));
		
		Driver.openBroser(prop.getProperty("browser"));

		//Create instance of generic libs
		util = new CommonUtils();

		//Create instance of pagefactory classes
		lp = PageFactory.initElements(Driver.driver, LoginPage.class);
		hp = PageFactory.initElements(Driver.driver, HomePage.class);
		cwp = PageFactory.initElements(Driver.driver, CreateWorkitempage.class);
		ip = PageFactory.initElements(Driver.driver, InboxPage.class);
		sp = PageFactory.initElements(Driver.driver, SearchPage.class);
		upp=PageFactory.initElements(Driver.driver, UserPreferencesPage.class);
		wdp = PageFactory.initElements(Driver.driver, WorkItemDetailViewPage.class);
		cjfp=PageFactory.initElements(Driver.driver, CustomJSFormsPage.class);
		rmlp=PageFactory.initElements(Driver.driver, RmLoginPage.class);
		rmhp=PageFactory.initElements(Driver.driver, RmHomePage.class);
		rmalp=PageFactory.initElements(Driver.driver, RmAuditLogPage.class);
		rmfpp=PageFactory.initElements(Driver.driver, RmFilePlanPage.class);
		fwc=PageFactory.initElements(Driver.driver, FluentWaitClass.class);
		randomNo=new RandomNo();
		System.out.println("In before Suite");
		
		DOMConfigurator.configure("log4j.xml");
	}

	@AfterSuite
	public void afterSuite(){
		Driver.closeBrowser();
		System.out.println("In after Suite");
	}



}
