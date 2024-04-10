package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.TestBase;

public class Utils extends TestBase {
	public static String takeScreenShot() throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		String path = date.toString().replace(" ", "_").replace("-", "");
		String filepath = System.getProperty("user.dir") +"//Screenshots//"+ path+".png";
		File tgt = new File(filepath);
		FileUtils.copyFile(src, tgt);
		return filepath;
	}
	public static void main(String[] args) {
		Date date = new Date();
		String path = date.toString().replace(" ", "_").replace("-", "");
		String filepath = System.getProperty("user.dir") +"\\Screenshots"+ path+".png";
		System.out.println(filepath);
	}
}
