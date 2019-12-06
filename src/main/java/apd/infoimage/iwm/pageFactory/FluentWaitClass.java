package apd.infoimage.iwm.pageFactory;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;

import apd.infoimage.iwm.genericLib.Driver;
import apd.infoimage.iwm.projectLib.ServerClassIWM;

public class FluentWaitClass extends ServerClassIWM {
	public static void WaitForElementPresent(WebElement element)    {
        int time = 10000;
        int second = 0;

        try{
              do{
                    if (IsElementEnable(element)==true) {
                          break;
                    } else {
                          second = second + 1;
                          Thread.sleep(1);
                    }

              } while (second < time);
        } catch (Exception e) {
              
        }
  }

public static boolean IsElementEnable(WebElement element) throws Exception {
        try {
        if(element.isEnabled() ==true);{
                    return true;      
              }
              

        } catch (NoSuchElementException e) {
              return false;
        }
  }

}
