package apd.infoimage.iwm.genericLib;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class ReportScreenShot<X> implements ITestListener {

	@Override

	public void onStart(ITestContext result) {

		System.out.println("Start Of Execution-> " + result.getName());

	}

	@Override

	public void onTestStart(ITestResult result) {

		System.out.println("Test Started-> " + result.getName());

	}

	@Override

	public void onTestSuccess(ITestResult result) {

		System.out.println("Test Pass-> " + result.getName());

	}

	@Override

	public void onTestFailure(ITestResult result) {
		String workingDirectory = System.getProperty("user.dir");
		String fileName = workingDirectory + File.separator + "screenshots" + File.separator
				+ result.getMethod().getMethodName() + "().png";// filename
		String fileNameReport = "file:///" + workingDirectory + "/" + "screenshots" + "/"
				+ result.getMethod().getMethodName() + "().png";
		Driver.driver.switchTo().defaultContent();
		File scrFile = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (IOException e) {
			ATUReports.add("IOException Occured", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			e.printStackTrace();
		}
		Reporter.log("<a href=\"" + fileNameReport + "\">  ScreenShot  </a>");
		Reporter.setCurrentTestResult(null);
		System.out.println("Test Failed-> " + result.getName());
	}

	@Override

	public void onTestSkipped(ITestResult result) {

		System.out.println("Test Skipped->" + result.getName());

	}

	@Override

	public void onFinish(ITestContext result) {

		System.out.println("END Of Execution(TEST)->" + result.getName());

	}

	@Override

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("");

	}

}
