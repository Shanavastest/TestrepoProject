package ParallelExcecution;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class YouTube {

	
	
	@Test	
	public void	Youtube(){
			
			WebDriver driver= new ChromeDriver();
			
			driver.get("https://www.youtube.com/");
			
		}
	
}
