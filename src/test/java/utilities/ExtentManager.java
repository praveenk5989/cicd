package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports reports = new ExtentReports();

	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
		htmlReporter.config().setDocumentTitle("Regression Results");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setEncoding("UTF-8");
		htmlReporter.config().setReportName(fileName);
		reports.attachReporter(htmlReporter);
		reports.setSystemInfo("Rahul", "Arora");
		reports.setSystemInfo("Test Type", "Smoke");
		reports.setSystemInfo("Build", "6.1.1");
		return reports;
	}
}
