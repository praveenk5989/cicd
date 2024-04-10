package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelReader {
	public static DataFormatter formatter = new DataFormatter();

	@Test(dataProvider = "dpp")
	public static void testop(Hashtable<String, String> table) throws IOException {
		// Object[][] dataOO = getDataOO();

		// System.out.println(table.get("password")+ table.get("username"));
	}

	public static Object[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "\\resources\\excel\\testdata.xlsx";
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");

		int rc = sheet.getLastRowNum();
		int cc = sheet.getRow(0).getLastCellNum();
		System.out.println(rc);
		System.out.println(cc);
		Object data[][] = new Object[rc][1];
		Hashtable<String, String> table = null;

		for (int i = 1; i <= rc; i++) {

			table = new Hashtable<String, String>();
			;
			for (int j = 0; j < cc; j++) {
				String header = formatter.formatCellValue(sheet.getRow(0).getCell(i));
				String val = formatter.formatCellValue(sheet.getRow(i).getCell(j));
				table.put(header, val);
				data[i - 1][j] = table;
				System.out.println("data[" + i + "][" + j + "]" + data[i - 1][j]);
			}
		}
		wb.close();
		return data;
	}

	@DataProvider(name = "dpp")
	public static Object[][] getDataOO() throws IOException {

		String excelPath = System.getProperty("user.dir") + "\\resources\\excel\\testdata.xlsx";
		FileInputStream fis = new FileInputStream(excelPath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		int rc = sheet.getLastRowNum();
		int cc = sheet.getRow(0).getLastCellNum();
		Hashtable<String, String> table = null;
		Object[][] data = new Object[rc][1];
		for (int i = 1; i <= rc; i++) {
			table = new Hashtable<String, String>();
			for (int j = 0; j < cc; j++) {
				String header = formatter.formatCellValue(sheet.getRow(0).getCell(j));
				String val = formatter.formatCellValue(sheet.getRow(i).getCell(j));
				table.put(header, val);

				// System.out.println("data[" + i + "][" + j + "]" + data[i - 1][j]);
			}
			data[i - 1][0] = table;
			System.out.println(data[i - 1][0]);
		}

		wb.close();
		return data;
	}
}
