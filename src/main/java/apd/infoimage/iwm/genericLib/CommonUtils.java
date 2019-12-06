package apd.infoimage.iwm.genericLib;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.io.Files;

import apd.infoimage.iwm.pageFactory.FluentWaitClass;

/**
 * @author KencharV This class contains methods for Verification, Wait and other
 *         commonly used functionalities.
 */
public class CommonUtils extends FluentWaitClass {

	boolean flag = false;

	/**
	 * This method wait until page loading, and also performs implicit wait.
	 */
	


	public void waitForPageToLoad() {

		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					System.out.println("wait For page to load");
					return ((JavascriptExecutor) Driver.driver).executeScript("return document.readyState")
							.equals("complete");
				}
			};
			System.out.println("checking wait");
			WebDriverWait wait = new WebDriverWait(Driver.driver, 60);
			wait.until(pageLoadCondition);

			Driver.driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);

		} catch (Exception e) {
			Reporter.log("there an exception while loading page", true);

		}
	}

	public void navigate(String url) {
		try {
			Reporter.log("navigate to :" + url, true);
			Driver.driver.navigate().to(url);
			waitForPageToLoad();
		} catch (Exception e) {
			Reporter.log("there an exception while navigating to :" + url + " url ", true);
		}

	}

	public void getTextandVerify(WebElement element, String expTxt) {
		String actTxt = null;
		try {
			WaitForElementPresent(element);
			actTxt = element.getText();
			verifyText(actTxt, expTxt);
		} catch (Exception e) {
			Reporter.log("there is mismatch between actual text : " + actTxt + " and " + expTxt, true);

		}

	}

	/**
	 * This method wait maximum 80 seconds until required link present.
	 * 
	 * @author KencharV.
	 * @param link
	 *            name in string format.
	 * @return WebElement.
	 */

	public WebElement waitForLinkPresent(String wbLinkText) {
		WebElement link = null;
		try {
			WebDriverWait wait = new WebDriverWait(Driver.driver, 60);
			link = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(wbLinkText)));
		} catch (NullPointerException nullPointerException) {
			System.out.println(
					" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail(
					"you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
		} catch (NoSuchElementException ElementException) {
			System.out.println(" Required linktext  is not present on the browser");
			Assert.fail(" Required linktext  is not present on the browser ");
		}
		return link;
	}

	/**
	 * This method check whether element displayed or not.
	 * 
	 * @author KencharV.
	 * @param WebElement.
	 * @return true - if element present else it will return false.
	 */

	public boolean verifyObjectPresentReturnsBool(WebElement element) {
		boolean flag = false;
		try {
			flag = element.isDisplayed();

			return flag;
		} catch (Exception e) {

			return flag;
		}

	}

	/**
	 * This method check whether element displayed or not.
	 * 
	 * @author KencharV.
	 * @param WebElement.
	 * @return it fail the test case if element present else it will continue.
	 */

	public void verifyElementShouldnotPresent(WebElement element) {
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			if (flag) {
				Reporter.log("element present", true);
				Assert.fail("element present");
			}
		} catch (Exception e) {
			Reporter.log("element not present", true);
		}

	}

	/**
	 * This method wait specified amount of time.
	 * 
	 * @author KencharV.
	 * @param time
	 *            in milliseconds.
	 */

	public void wait(int time) {
		try {
			Thread.sleep(time);

		} catch (Exception e) {

		}

	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @author KencharV.
	 * @param WebElement.
	 * @return WebElement.
	 */

	public WebElement waitForClick(WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(Driver.driver, 60);
			WebElement clickItem = wait.until(ExpectedConditions.elementToBeClickable(element));

			return clickItem;
		} catch (Exception e) {

			System.out.println(" Required element  is not present on the browser");
			Assert.fail(" Required element  is not present on the browser ");
			return null;
		}

	}

	/**
	 * An expectation for checking that an element, known to be present on the DOM
	 * of a page, is visible.
	 * 
	 * @author KencharV.
	 * @param WebElement.
	 */
	public void waitForElementPresent(WebElement element) {
		try {
			waitForPageToLoad();
			WebDriverWait wait = new WebDriverWait(Driver.driver, 60);
			wait.until(ExpectedConditions.visibilityOf(element));
			System.out.println("webelement identified");
		}

		catch (NullPointerException nullPointerException) {
			System.out.println(
					" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail(
					"you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
		} catch (NoSuchElementException ElementException) {

			System.out.println("Required button/link/text/ is not present");
			Assert.fail(" Required button/link/text/ is not present ");

		}

	}

	/**
	 * This method check whether required check box is selected or not. if not
	 * selected, it select the check box.
	 * 
	 * @author KencharV.
	 * @param WebElement.
	 */
	public void selectCheckBox(WebElement element) {
		try {

			Boolean selectFlag = element.isSelected();
			if (selectFlag) {
				System.out.println("check box already selected");
			} else {
				element.click();
				System.out.println("webelement selected");
			}
		} catch (NoSuchElementException ElementException) {
			System.out.println("selectable element is not present");
			Assert.fail("selectable element is not present ");
		}

	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @author KencharV.
	 * @param WebElement.
	 */
	public void waitForElementEnabled(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(Driver.driver, 70);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			System.out.println("webelement enabled");
		}

		catch (NullPointerException nullPointerException) {
			System.out.println(
					" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail(
					"you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
		} catch (NoSuchElementException ElementException) {

			System.out.println("button is not enabled/present");
			Assert.fail(" Required button is not enabled/present ");

		}

	}

	/**
	 * An expectation for checking whether the given frame is available to switch
	 * to.
	 * 
	 * @author KencharV.
	 * @param frameLocator
	 *            - used to find the frame (id or name).
	 */
	public void waitForFramePresentAndSwitch(String frameLocator) {
		try {
			WebDriverWait wait = new WebDriverWait(Driver.driver, 60);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		} catch (NullPointerException nullPointerException) {
			System.out.println(
					" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail(
					"you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
		} catch (NoSuchFrameException frameException) {
			System.out.println(" no such frame is present on the browser");
			Assert.fail(" no such frame is present on the browser");
		}

	}

	/**
	 * This method clicks on link.
	 * 
	 * @author KencharV.
	 * @param link
	 *            text.
	 */
	public void clickingHyperLink(String hyperLinkText) {
		Driver.driver.findElement(By.linkText(hyperLinkText)).click();

	}

	/**
	 * This method compare actual text with expected text. If both text matched
	 * exactly then it will continue else it will fail the test case
	 * 
	 * @author KencharV
	 * @param actual
	 *            text and expected text.
	 * @throws IOException
	 */
	public void verifyText(String actText, String expText) {

		actText = actText.trim();
		expText = expText.trim();
		Assert.assertEquals(actText, expText, "expected text and actual text both are not matched");
		System.out.println("pass");

	}

	/**
	 * This method compare actual text with expected text it ignore case sensitive.
	 * 
	 * @author KencharV
	 * @param actual
	 *            text and expected text.
	 * @throws IOException.
	 */
	public void verifyTextIgnoreCase(String actText, String expectedText) {
		Assert.assertTrue(actText.equalsIgnoreCase(expectedText), "expected text and actual text both are not matched");
		System.out.println("true");
	}

	/**
	 * This method compare actual text with expected text and it fail if both texts
	 * matched.
	 * 
	 * @author KencharV
	 * @param actual
	 *            text and expected text.
	 */

	public void verifyTextNotPresent(String actText, String expectedText) {

		Assert.assertNotEquals(actText, expectedText, "expected text and actual text both matched");

	}

	public int conversionStringToInt(String valueToBeConverted) {
		int intVal = Integer.parseInt(valueToBeConverted);
		return intVal;
	}

	public String conversionIntToString(int valueToBeConverted) {
		String stringVal = "" + valueToBeConverted;
		return stringVal;
	}

	/**
	 * This method check whether expected text is part of actual text or not.
	 * 
	 * @author KencharV
	 * @param actual
	 *            text and expected text.
	 * @return true if actual text contains expected text.
	 */
	public boolean verifyTextPresentReturnsBool(String actualText, String expectedText) {

		boolean flag = expectedText.contains(actualText);
		return flag;

	}

	/**
	 * This method check text present or not using page source.
	 * 
	 * @author KencharV.
	 * @param expected
	 *            text.
	 */

	public void verifyTextNotPresentUsingPageSource(String expectedText) {

		try {

			boolean b = Driver.driver.getPageSource().contains(expectedText);
			System.out.println(b);
			Assert.assertFalse(b);

		}

		catch (AssertionError assertion) {
			System.out.println("Text shouldnt be present but is present");
			Assert.fail(" Text is present ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Text is present, see error message");
			Assert.fail("Text is  present, see error message");
		}
	}

	/**
	 * This method check text present or not using page source.
	 * 
	 * @author KencharV.
	 * @param expected
	 *            text.
	 */
	public void verifyTextPresentUsingPageSource(String expectedText) {
		Assert.assertTrue(Driver.driver.getPageSource().contains(expectedText),
				"expected text not present in pagasource");
	}

	/**
	 * This method check whether expected text is part of actual text or vice versa.
	 * 
	 * @author KencharV.
	 * @param actual
	 *            text and expected text.
	 * @throws IOException.
	 */
	public void verifyTextPresent(String actualText, String expectedText) {
		System.out.println("actualText" + actualText);
		System.out.println("expectedText" + expectedText);

		Assert.assertTrue(actualText.contains(expectedText), "expected text not present");

	}

	/**
	 * This method check whether element present or not , and fail the test case if
	 * not present.
	 * 
	 * @author KencharV.
	 * @param WebElement.
	 * @throws IOException.
	 */
	public void verifyElementPresent(WebElement webElement) {

		try {
			webElement.isDisplayed();
			Reporter.log(webElement + " element present", true);
		} catch (Exception e) {
			Reporter.log("required webElement not present in GUI", true);
			Assert.fail("required webElement not present in GUI");
		}

	}

	/**
	 * This method check list of webElements present or not on GUI.
	 * 
	 * @author KencharV.
	 * @param List<WebElement>
	 *            webElementList.
	 * @throws InterruptedException.
	 */

	public void verifyListOfElementsPresent(List<WebElement> webElementList) {

		try {

			CommonUtils util = new CommonUtils();

			for (WebElement element : webElementList) {

				util.verifyElementPresent(element);

			}

		}

		catch (NullPointerException nullPointerException) {
			System.out.println(
					" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail(
					"you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
		} catch (NoSuchElementException ElementException) {

			System.out.println("Required button/linktext/text data is not present");
			Assert.fail(" Required button/linktext/text/ is not present ");

		} catch (AssertionError assertion) {

			System.out.println("element is not present ");
			Assert.fail(" element is not present ");

		} catch (Exception e) {
			System.out.println("Element is not present in UI");
			Assert.fail("Element is not present in UI");
		}
	}

	/**
	 * This method check element enabled or not.
	 * 
	 * @author KencharV
	 * @param WebElement.
	 * @throws InterruptedException.
	 */
	public void verifyElementEnabled(WebElement webElement) {

		try {
			boolean flag = webElement.isEnabled();
			System.out.println(flag);
			Assert.assertTrue(flag);
			System.out.println("Element is enabled in UI");
		} catch (NullPointerException nullPointerException) {
			System.out.println(
					" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail(
					"you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
		} catch (NoSuchElementException ElementException) {

			System.out.println("Required button/linktext/text data is not present");
			Assert.fail(" Required button/linktext/text/ is not present ");

		}

		catch (AssertionError assertion) {

			System.out.println("the required element is not enabled");
			Assert.fail(" element is not enabled ");

		} catch (Exception e) {
			System.out.println("the required Element is not enabled in UI");
			Assert.fail("the required Element is not enabled in UI");
		}
	}

	/**
	 * This method accept alert popup if alert present else it wont give any
	 * exception.
	 * 
	 * @author KencharV
	 */

	public void alertOK() {
		try {
			if (waitForAlertPresent()) {
				Alert alert = Driver.driver.switchTo().alert();
				alert.accept();
				Thread.sleep(2000);
			}
		} catch (NullPointerException nullPointerException) {
			System.out.println(
					" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail(
					"you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
		} catch (NoAlertPresentException alertException) {
			System.out.println(" alert pop up is not present");

		}

		catch (Exception e) {
			System.out.println(" error occured while handling Alert pop-up ");
			Assert.fail("error occured while handling Alert pop-up ");
		}

	}

	/**
	 * This method cancel alert popup if alert present else it wont give any
	 * exception.
	 * 
	 * @author KencharV
	 */
	public void alertCancel() {

		try {
			if (waitForAlertPresent()) {
				Alert alt = Driver.driver.switchTo().alert();
				alt.dismiss();
				Thread.sleep(2000);
			}
		}

		catch (NullPointerException nullPointerException) {
			System.out.println(
					" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail(
					"you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
		} catch (NoAlertPresentException alertException) {
			System.out.println("alert pop up is not present");
			Assert.fail("alert pop up is not present");
		}

		catch (Exception e) {
			System.out.println(" error occured while handling Alert pop-up ");
			Assert.fail("error occured while handling Alert pop-up ");
		}

	}

	/**
	 * This method get the text on alert popup.
	 * 
	 * @author KencharV
	 * @return alert text.
	 */
	public String alertGetText() {
		try {
			Alert alt = Driver.driver.switchTo().alert();
			String alertText = alt.getText();
			return alertText;
		}

		catch (NullPointerException nullPointerException) {
			System.out.println(
					" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail(
					"you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			return null;
		}

		catch (NoAlertPresentException alertException) {
			System.out.println("alert pop up is not present");
			Assert.fail("alert pop up is not present");
			return null;
		} catch (Exception e) {
			System.out.println(" error occured while handling Alert pop-up ");
			Assert.fail("error occured while handling Alert pop-up ");
			return null;
		}
	}

	/**
	 * This method check whether element enabled or not.
	 * 
	 * @author KencharV
	 * @param webelement.
	 * @return true or false.
	 * @throws InterruptedException.
	 */
	public boolean verifyObjectEnabledReturnsBool(WebElement webElementXpath) {

		boolean flag = webElementXpath.isEnabled();

		return flag;
	}

	/**
	 * This method check alert popup present or not.
	 * 
	 * @author KencharV.
	 * @return true or false.
	 */
	public boolean isAlertPresent() {
		Boolean alertPresent = false;
		try {
			WebDriverWait wait = new WebDriverWait(Driver.driver, 20);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());

			if (alert != null && !alert.equals("")) {
				Reporter.log("alert is present ", true);
				alertPresent = true;
			}
			return alertPresent;
		}

		catch (NoAlertPresentException Ex) {
			Reporter.log("alert is not  present ", true);
			Assert.fail("alert is not  present ");
			return alertPresent;
		} // catch

		catch (NullPointerException nullPointerException) {
			System.out.println(
					" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail(
					"you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			return false;
		} catch (Exception e) {
			Reporter.log("alert is not  present ", true);
			return false;
		}
	}

	/**
	 * This method wait for alert popup.
	 * 
	 * @author KencharV.
	 * @return true if alert present else false.
	 */
	public boolean waitForAlertPresent() {
		Boolean alertPresent = false;
		try {
			WebDriverWait wait = new WebDriverWait(Driver.driver, 10);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if (alert != null) {
				Reporter.log("alert is present ", true);
				alertPresent = true;
			}
			return alertPresent;
		}

		catch (NoAlertPresentException Ex) {
			Reporter.log("alert is not  present ", true);
			return alertPresent;
		} // catch

		catch (NullPointerException nullPointerException) {
			System.out.println(
					" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail(
					"you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			return false;
		} catch (Exception e) {
			Reporter.log("alert is not  present ", true);
			return false;
		}
	}

	/**
	 * This method clicks on a button if present.
	 * 
	 * @author KencharV.
	 * @param webelement.
	 */
	public void clickOnButton(WebElement element) {
		try {
			element.click();
			Thread.sleep(3000);
		} catch (Exception ex) {
			System.out.println("the button not present");
		}

	}

	/**
	 * This method check whether expected text present or not on GUI.
	 * 
	 * @author KencharV.
	 * @param expected
	 *            text.
	 * @return True if text present else false.
	 */
	public boolean verifyTextPresent(String expectedText) {

		boolean b = false;

		try {

			b = Driver.driver.findElement(By.xpath(".//*[text()='" + expectedText + "']")).isDisplayed();
			System.out.println(b);
		} catch (Exception e) {
			b = false;
		}
		return b;
	}

	/**
	 * This method check whether expected text present or not on GUI.
	 * 
	 * @author KencharV.
	 * @param expected
	 *            text.
	 * @return True if text present else false.
	 */
	public boolean verifyPartialTextPresent(String expectedText) {

		boolean b = false;

		try {

			b = Driver.driver.findElement(By.xpath(".//*[contains(text(),'" + expectedText + "')]")).isDisplayed();
			System.out.println(b);
		} catch (Exception e) {
			b = false;
		}
		return b;
	}

	/**
	 * This method check for presence of alertPoPup and Validate the popup message.
	 * 
	 * @author KencharV.
	 * @param actualTextMessage
	 *            is the required expected text.
	 * 
	 */

	public void verifyAlertText(String actualTextMessage) {

		Boolean alertPresent = false;
		String alertText;
		int i = 0;

		do {
			alertPresent = isAlertPresent();
			i++;
		} while (alertPresent == false && i <= 3);

		if (alertPresent) {
			alertText = alertGetText();
			Reporter.log("alert  Text is: " + alertText, true);
			verifyText(alertText, actualTextMessage);
			alertOK();
		} else {
			Reporter.log("Alert is not Present", true);
			Assert.fail("Alert is not Present");

		}

	}

	/**
	 * This method compare actual text with expected text and returns a boolean
	 * value. It is not case sensitive.
	 * 
	 * @author KencharV
	 * @param actual
	 *            text and expected text.
	 * @throws IOException.
	 */
	public boolean verifyTextIgnoreCaseReturnsBool(String actText, String expectedText) {

		boolean b = actText.equalsIgnoreCase(expectedText);
		return b;

	}

	/**
	 * This method will move from one element to another element by using Action
	 * class
	 * 
	 * @author KencharV
	 * @param element
	 *            of type WebElement
	 * 
	 */
	public void mouseAction(WebElement element) {
		try {
			Actions action = new Actions(Driver.driver);
			action.moveToElement(element).click().perform();
			Reporter.log("Mouse action performed", true);
		} catch (Exception e) {
			Reporter.log("Could not perform mouse action", true);
			Assert.fail();
		}

	}

	/**
	 * This method enter text in specified webelement
	 * 
	 * @author KencharV
	 * @param element
	 *            the element paramenter of type WebElement
	 * 
	 */

	public void enterText(WebElement element, String text) {

		try {
			WaitForElementPresent(element);
			element.clear();
			element.sendKeys(text);

		}

		catch (Exception e) {
			Reporter.log("exception raised while enter data for the reason" + e.getMessage(), true);
			Assert.fail("exception raised while enter data for the reason" + e.getMessage());
		}

	}

	/**
	 * Description : This method will give random number of length less than or
	 * equal to 10 where every digit is occurred only once
	 * 
	 * @param size
	 * @author KencharV
	 */
	public String getRandomNo(int size) {
		try {
			Random r = new Random();
			List<Integer> digits = new ArrayList<Integer>();
			String number = "";
			for (int i = 0; i <= size; i++) {
				digits.add(i);
			}
			for (int i = size; i > 0; i--) {
				int randomDigit = r.nextInt(i);
				number += digits.get(randomDigit);
				digits.remove(randomDigit);
			}
			return number;
		} catch (NumberFormatException numberFormatException) {
			System.out.println(" Number format is wrong");
			Assert.fail(" Number format is wrong");
			return null;
		}

		catch (NullPointerException nullPointerException) {
			System.out.println(
					" you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			Assert.fail(
					"you are trying to get data from object which is referenced null/empty, i.e driver/any other object is not yet instantiated properly ");
			return null;
		}

		catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
			// TODO: handle exception
			System.out.println(" trying to retrive/access data morethan/negative-size than actual  size  ");
			Assert.fail(" trying to retrive/access  data morethan/negative than actual  size   ");
			return null;
		}

		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println(" error occured while generating random number ");
			Assert.fail("error occured while generating random number  ");
			return null;
		}

	}

	/**
	 * This method return current date of IST.
	 * 
	 * @author KencharV.
	 * @param period
	 *            (i.e. 0:today ,-1:yesterday,1:tomorrow),date
	 *            format(yyyy-MM-dd-hh-mm-ss).
	 * @return date.
	 */

	public String getSysDate(int period, String format)

	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date()); // Now use today date.
		calender.add(Calendar.DATE, period); // Adding period days
		String date = sdf.format(calender.getTime());
		return date;

	}

	public static String captureScreenshot(WebDriver driver, String screenShotName, String newFolderPath)
			throws IOException {
		/*
		 * TakesScreenshot ts = (TakesScreenshot)driver; File source =
		 * ts.getScreenshotAs(OutputType.FILE); String dest =
		 * System.getProperty("user.dir")
		 * +"\\src\\test\\test-output\\errorScreenShots\\"+screenShotName+".png";
		 * System.out.println("destination is:"+ dest); File destination = new
		 * File(dest); FileUtils.copyFile(source, destination);
		 * 
		 * return dest;
		 */
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest = new File(newFolderPath).getAbsolutePath() + "\\" + screenShotName + ".png";
			File destDir = new File(dest);
			Files.move(source, destDir);
			System.out.println("screenshot taken");
			return dest;
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot" + e.getMessage());
			return e.getMessage();
		}
	}

	@SuppressWarnings("unused")
	public void scrollToElementAndClick(WebElement element) {
		int yScrollPosition = element.getLocation().getY();
		JavascriptExecutor js = (JavascriptExecutor) (Driver.driver);
		js.executeScript("window.scroll(0, " + yScrollPosition + ");");
		element.click();
	}

	// Added by Avnish 01/06/2018
	/*public void jclick(WebElement element) {
		try {
			Thread.sleep(1000);
			if (element.isEnabled() && element.isDisplayed()) { //
				System.err.println("Clicking on element with using java script click");
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", element);
				Thread.sleep(1000);
			} else {
				System.err.println("Unable to click on element Trying with else block code");
				JavascriptLibrary js = new JavascriptLibrary();
				js.callEmbeddedSelenium(Driver.driver, "triggerMouseEventAt", element, "click", "0,0");
				Thread.sleep(1000);
				if (element.isEnabled() && element.isDisplayed()) {
					System.err.println("Clicking on element with using java script click");
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", element);
					Thread.sleep(1000);
				}
			}
		} catch (StaleElementReferenceException e) {
			((JavascriptExecutor) Driver.driver).executeScript("return !!window.jQuery && window.jQuery.active == 0");
		} catch (NoSuchElementException e) {
			((JavascriptExecutor) Driver.driver).executeScript("return jQuery.active == 0");
		} catch (Exception e) {
			System.err.println("Unable to click on element " + e.getStackTrace());
		} finally {
			System.err.println("Element is handled by Javascript Library");
		}

	}*/
	public void jclick(WebElement element) throws StaleElementReferenceException,InterruptedException{
		
			Thread.sleep(1000);
			if (element.isEnabled() && element.isDisplayed()) { //
				System.err.println("Clicking on element with using java script click");
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", element);
				Thread.sleep(1000);
			} else {
				System.err.println("Unable to click on element Trying with else block code");
				JavascriptLibrary js = new JavascriptLibrary();
				js.callEmbeddedSelenium(Driver.driver, "triggerMouseEventAt", element, "click", "0,0");
				Thread.sleep(1000);
				if (element.isEnabled() && element.isDisplayed()) {
					System.err.println("Clicking on element with using java script click");
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", element);
					Thread.sleep(1000);
				}
			}
		/* catch (StaleElementReferenceException e) {
			((JavascriptExecutor) Driver.driver).executeScript("return !!window.jQuery && window.jQuery.active == 0");
		} catch (NoSuchElementException e) {
			((JavascriptExecutor) Driver.driver).executeScript("return jQuery.active == 0");
		} catch (Exception e) {
			System.err.println("Unable to click on element " + e.getStackTrace());
		} finally {
			System.err.println("Element is handled by Javascript Library");
		}*/

	}
	

	public void waitForelementEnable() {

		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					System.out.println("wait For page to load");
					return ((JavascriptExecutor) Driver.driver).executeScript("return document.readyState")
							.equals("complete");
				}
			};
			System.out.println("checking wait");
			WebDriverWait wait = new WebDriverWait(Driver.driver, 360);
			wait.until(pageLoadCondition);

			Driver.driver.manage().timeouts().implicitlyWait(360, TimeUnit.SECONDS);

		} catch (Exception e) {
			Reporter.log("there an exception while loading page", true);

		}
	}
	
}