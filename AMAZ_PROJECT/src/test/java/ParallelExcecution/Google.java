package ParallelExcecution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Google {
	
@Test	
public void	goodletest(){
		
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://www.google.com");
		
	}


}
