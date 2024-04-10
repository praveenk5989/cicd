package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	int count = 0;
	int max_retry_count = 2;

	public boolean retry(ITestResult result) {
		if (count < max_retry_count) {
			count++;
			return true;
		}
		return false;
	}

}
