package apd.infoimage.iwm.PageFactoryRm;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
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
public class RmLoginPage extends ServerClassIWM{

	@FindBy(xpath="//input[@id='_58_login']")
	private WebElement rmUidTextBox;
	
	@FindBy(xpath="//input[@id='_58_password']")
	private WebElement rmPwdTextBox;
	
	@FindBy(xpath="//input[@id='_58_domain']")
	private WebElement rmDomainTextBox;
	
	@FindBy(xpath="//button[contains(text(),'Sign In')]")
	private WebElement rmSignInBtn;

	public WebElement getRmUidTextBox() {
		return rmUidTextBox;
	}

	public WebElement getRmPwdTextBox() {
		return rmPwdTextBox;
	}

	public WebElement getRmDomainTextBox() {
		return rmDomainTextBox;
	}

	public WebElement getRmSignInBtn() {
		return rmSignInBtn;
	}
	
	public static Properties rmProp;
	public static InputStream input = null;
	/**
	 * This method Logs into RM application 
	 * @author PradhanJ
	 * @param userName
	 * @param password
	 * @param domain
	 */
	@SuppressWarnings("deprecation")
	public void loginToRM(String uid, String pwd, String domain)
	{
		try{
			int time=2000;
			util.wait(time);
			rmProp = new Properties();
			rmProp.load(new FileInputStream("src/main/resources/rmData.properties"));
			util.wait(time);
			
			rmUidTextBox.clear();
			rmUidTextBox.sendKeys(rmProp.getProperty(uid));
			Reporter.log("username is entered", true);
			ATUReports.add("username is entered", true);
			Log.info("username is entered");			
			util.wait(time);
			
			rmPwdTextBox.clear();
			rmPwdTextBox.sendKeys(rmProp.getProperty(pwd));
			Reporter.log("password is entered", true);
			ATUReports.add("password is entered", true);
			Log.info("password is entered");
			util.wait(time);
			
			rmDomainTextBox.clear();
			rmDomainTextBox.sendKeys(rmProp.getProperty(domain));
			Reporter.log("Domain is entered", true);
			ATUReports.add("Domain is entered", true);
			Log.info("Domain is entered");
			util.wait(time);
			
			rmSignInBtn.click();
			Reporter.log("Sign In button is clicked", true);
			ATUReports.add("Sign In button is clicked", true);
			Log.info("Sign In button is clicked");
			util.wait(time);
			util.waitForPageToLoad();
			
			boolean f = util.verifyObjectPresentReturnsBool(rmhp.getDashboardTab());
			System.out.println("boolean value is "+f);

			if(f){
				Reporter.log("Login to RM successful",true);
				Log.info("Login to RM successful");
				ATUReports.add("Login to to RM ", "User Name: "+uid, "User should be logged in Successfully", "User is logged in to RM successfully", true);
			}else{
				Reporter.log("Login to RM failed",true);
				Log.error("Login to RM failed");
				Assert.fail("Login to RM Failed");
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ATUReports.add("Login to RM application Failed",LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			Log.error("Login to RM application Failed "+e.getMessage());
			Assert.fail("Login to RM application Failed");
		}
		
	}
}
