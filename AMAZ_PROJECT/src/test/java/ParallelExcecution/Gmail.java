package ParallelExcecution;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Gmail {

	@Test
	public void Gmailtest() {

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.gmail.com");

	}

	@DataProvider(name = "loginData")
	public void readExcelData() throws IOException {
		
		
	    	FileInputStream inputstream = new FileInputStream("C:\\Users\\vikram\\Desktop\\vijay\\AMAZ_PROJECT\\AmazonExcel.xlsx");
	    	
	    	XSSFWorkbook workbook = new  	XSSFWorkbook(inputstream); 	
	    	
		
		
		
	}
}
