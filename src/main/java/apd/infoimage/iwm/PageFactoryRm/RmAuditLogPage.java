package apd.infoimage.iwm.PageFactoryRm;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import apd.infoimage.iwm.projectLib.ServerClassIWM;

/**
 * @author SumanGaK
 *
 */
public class RmAuditLogPage extends ServerClassIWM{
	
	@FindBy(xpath = "//select[@id='objectType']")
	private WebElement objectTypeDropdown;

	public WebElement getObjectTypeDropdown() {
		return objectTypeDropdown;
	}
	
	@FindBy(xpath = "//input[@value='Go']")
	private WebElement goButtonInAuditLog;

	public WebElement getGoButtonInAuditLog() {
		return goButtonInAuditLog;
	}
	
	@FindBy(xpath="//tbody/tr[1]/td[1]")
	private WebElement firstRowFirstCell;
		
	public WebElement getFirstRowFirstCell() {
		return firstRowFirstCell;
	}

}

