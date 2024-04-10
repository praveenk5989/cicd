package rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperies {

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		String userDir= System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(userDir+"/resources/properties/config.properties");
		prop.load(fis);
		System.out.println(prop.getProperty("url"));
	}

}
