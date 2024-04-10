package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {
public static WebDriver driver ;
public static Properties prop=new Properties();
public static Properties OR =new Properties();
public static FileInputStream fis;
public static String userDir= System.getProperty("user.dir");
public static Logger log= Logger.getLogger("devpinoyLogger");

@BeforeTest	
public void setUp() throws IOException
	{
	
	PropertyConfigurator.configure("resources/properties/log4j.properties");
		if(driver==null)
			
		 fis = new FileInputStream(userDir+"/resources/properties/config.properties"); 
		prop.load(fis);
	
		String browser= System.getProperty("browser")==null?prop.getProperty("browser"):System.getProperty("browser");
		
		 fis = new FileInputStream(userDir+"/resources/properties/OR.properties"); 
			OR.load(fis);
			log.info("property fie is loaded");
			if(browser.equalsIgnoreCase("chrome"))
				driver= new ChromeDriver();
			else if(browser.equalsIgnoreCase("firefox"))
				driver= new FirefoxDriver();
			
			log.info("Browser is launched");
			
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
@AfterTest
	public void tearDown()
	{
		if(!(driver==null))
			driver.quit();
		log.info("browser is closed");
	}


public static boolean isElementPresent(String locator)
{
	try {
	driver.findElement(By.cssSelector(locator));
	return true;
	}
	catch(NoSuchElementException ex)
	{
	ex.printStackTrace();
	return false;
	}
}
	
}
