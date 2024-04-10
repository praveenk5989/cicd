package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.Retry;

public class LoginTest extends TestBase {
	@Test(retryAnalyzer = Retry.class)
	public void loginTest() throws InterruptedException {
		System.out.println(OR.getProperty("cust_login"));
		driver.findElement(By.cssSelector(OR.getProperty("cust_login"))).click();
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(isElementPresent(OR.getProperty("cust_login")));

	}
	
	
	
}
