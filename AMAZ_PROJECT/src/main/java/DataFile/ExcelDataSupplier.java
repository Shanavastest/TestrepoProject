package DataFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.testng.annotations.DataProvider;

public class ExcelDataSupplier {

	//@DataProvider(name = "loginData")
	public static String[][] getData() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\vikram\\Desktop\\vijay\\AMAZ_PROJECT\\src\\main\\java\\Amazon.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheet("sheet1");

		int noOfRows = sheet.getPhysicalNumberOfRows();

		short noOfColumns = sheet.getRow(0).getLastCellNum();
		// To store the data
		String[][] data = new String[noOfRows - 1][noOfColumns];

		for (int i = 0; i < noOfRows - 1; i++) {

			for (int j = 0; j < noOfColumns; j++) {

				DataFormatter df = new DataFormatter();

				data[i][j] = df.formatCellValue(sheet.getRow(i + 1).getCell(j));

			}
		}
		workbook.close();
		fis.close();

		for (String[] strings : data) {
			System.out.println(Arrays.toString(strings));

		}

		return data;

	}

	public static void main(String[] args) throws IOException {
		getData();
	}

}
