package utilities;

import java.io.IOException;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class CustomListener implements ITestListener, ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	public static ExtentTest test;
	private static ExtentReports extent = ExtentManager
			.createInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);
ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();

	public void onStart(ISuite suite) {

	}

	public void onFinish(ISuite suite) {

		if (extent != null) {
			extent.flush();
		}
	}

	public void onTestStart(ITestResult result) {
		test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
	extenttest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test success");
		test.log(Status.PASS, "passed");
		String text = result.getMethod().getMethodName() + "has passed";
		MarkupHelper.createLabel(text, ExtentColor.GREEN);
	}

	public void onTestFailure(ITestResult result) {
		String text = result.getMethod().getMethodName() + "has failed";
		extenttest.get().fail(result.getThrowable());
		MarkupHelper.createLabel(text, ExtentColor.RED);
		String filepath=null;
		try {
			 filepath = Utils.takeScreenShot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filepath);
	}

	public void onTestSkipped(ITestResult result) {
		String text = result.getMethod().getMethodName() + "has skipped";
		MarkupHelper.createLabel(text, ExtentColor.YELLOW);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
