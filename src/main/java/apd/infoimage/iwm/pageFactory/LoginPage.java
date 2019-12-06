package apd.infoimage.iwm.pageFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import apd.infoimage.iwm.genericLib.Log;
import apd.infoimage.iwm.projectLib.ServerClassIWM;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;




	// for 7.2 liferay Modify by Chandan Modi

public class LoginPage extends ServerClassIWM {
	
	@FindBy(xpath = "//input[@id='_com_liferay_login_web_portlet_LoginPortlet_login']") // added  by Chandan Modi
	
	private WebElement userName_TF;

	public WebElement getUserName_TF(){
		return userName_TF;
	}
	
	@FindBy(xpath = "//input[@id='_com_liferay_login_web_portlet_LoginPortlet_password']") // added  by Chandan Modi
	private WebElement password_TF;

	public WebElement getPassword_TF(){
		return password_TF;
	}
	
	@FindBy(xpath = "//input[@id='_com_liferay_login_web_portlet_LoginPortlet_domain']") // added  by Chandan Modi
	private WebElement domain_TF;

	public WebElement getDomain_TF(){
		return domain_TF;
	}
	
	@FindBy(xpath = "//span[contains(text(),'Sign In')]")  // added  by Chandan Modi
	private WebElement signinBtn;

	public WebElement getSigninBtn(){
		return signinBtn;
	}

	 @FindBy(xpath = "//span[contains(text(),'Cancel')]")    // added  by Chandan Modi
	//@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	private WebElement cancelBtn;

	public WebElement getCancelBtn(){
		return cancelBtn;
	}
	
	@FindBy(xpath = "//input[@id='_com_liferay_login_web_portlet_LoginPortlet_rememberMe']")	// added  by Chandan Modi
	//@FindBy(id = "_58_rememberMeCheckbox")
	private WebElement rememberMeCheckbox;

	public WebElement getRememberMeCheckbox(){
		return rememberMeCheckbox;
	}

	@FindBy(xpath = "(//select[@id='_58_selectRoles'])[1]")
	private WebElement selectRolesPopUp;

	public WebElement getSelectRolesPopUp(){
		return selectRolesPopUp;
	}

	@FindBy(xpath = "//button[text()='Ok']")
	private WebElement selectRolesOk;

	public WebElement getSelectRolesOk(){
		return selectRolesOk;
	}
	
	//added By Jayashri		
	@FindBy(xpath="//button[text()='Cancel']")
	private WebElement selectRolesCancel;
	
	public WebElement getSelectRolesCancel(){
		return selectRolesCancel;
	}
	
	//Added By Jayashri
	@FindBy(xpath="//h3[text()='Cancel Role']/../..")
	private WebElement cancelRolePopup;
	
	public WebElement getCancelRolePopup() {
		return cancelRolePopup;
	}
	//Added By Jayashri
	@FindBy(xpath="//h3[text()='Cancel Role']/../..//div[contains(text(),'end your session')]")
	private WebElement cancelRoleText;
	
	public WebElement getCancelRoleText() {
		return cancelRoleText;
	}
	//Added By Jayashri
	@FindBy(xpath="//button[text()='Yes']")
	private WebElement cancelRoleYes;
	
	public WebElement getCancelRoleYes() {
		return cancelRoleYes;
	}
	//Added By Jayashri
	@FindBy(xpath="//button[text()='No']")
	private WebElement cancelRoleNo;
	
	public WebElement getCancelRoleNo() {
		return cancelRoleNo;
	}	
	//Added By Jayashri
	@FindBy(xpath="//span[contains(text(),'Home')]")
	private WebElement homeIWM;
	
	public WebElement getHomeIWM() {
		return homeIWM;
	}
	//Added By Jayashri
	@FindBy(xpath="//div[contains(text(),'Invalid user')]")
	private WebElement invalidUidPwdMessage;
	
	public WebElement getInvalidUidPwdMessage() {
		return invalidUidPwdMessage;
	}
	//Added By Jayashri
	@FindBy(xpath="//div[contains(text(),'invalid domain')]")
	private WebElement invalidDomMessage;
	
	public WebElement getInvalidDomMessage() {
		return invalidDomMessage;
	}

	public static Properties prop;
	public static InputStream input = null;


	/**
	 * This method Loggs into app with role.
	 * @author KencharV
	 * @param userName
	 * @param password
	 * @param domain
	 * @param role
	 */
	@SuppressWarnings("deprecation")
	public void loginToApp(String userName, String password, String domain, String role){
		try{
			Thread.sleep(5000);
			prop = new Properties();
			prop.load(new FileInputStream("src/main/resources/userData.properties"));
			Thread.sleep(2000);
			getUserName_TF().clear();
			getUserName_TF().sendKeys(prop.getProperty(userName));
			Thread.sleep(2000);
			getPassword_TF().clear();
			getPassword_TF().sendKeys(prop.getProperty(password));
			Thread.sleep(2000);
			getDomain_TF().clear();
			getDomain_TF().sendKeys(prop.getProperty(domain));
			Thread.sleep(2000);
			getSigninBtn().click();
			Thread.sleep(5000);

			if(role!=null){

				boolean f = util.verifyObjectPresentReturnsBool(getSelectRolesPopUp());
				System.out.println("boolean value is "+f);

				if(f){
					Reporter.log("Roles dialog box present",true);
					Select sel = new Select(getSelectRolesPopUp());
					sel.selectByValue(prop.getProperty(role));
					Reporter.log("Role selected as "+role,true);
					Thread.sleep(2000);
					util.clickOnButton(getSelectRolesOk());
				}else{
					Reporter.log("Roles dialog box not present",true);
				}
			}

			util.waitForPageToLoad();

			boolean f = util.verifyObjectPresentReturnsBool(hp.getWorkItemTab());
			System.out.println("boolean value is "+f);

			if(f){
				Reporter.log("Login successful",true);
				Log.info("Login successful");
				ATUReports.add("Login to IWM", "User Name: "+userName, "User should be logged in Successfully", "User is logged in successfully", true);
			}else{
				Reporter.log("Login failed",true);
				Log.error("Login failed");
				Assert.fail("Login Failed");
				
			}


		}catch(Exception e){
			e.printStackTrace();
			ATUReports.add("Login Failed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Assert.fail("Login Failed");
			
		}
	}



}
