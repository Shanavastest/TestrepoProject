package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import Utilities.Base_class;
import Utilities.ExtendReport;
import pages.CartPage;
import pages.LoginPage;
import pages.SearchPage;

public class CartTest extends Base_class {

	public static WebDriver driver;

	@BeforeTest
	public static void setUp() {

		driver = Base_class.driverInitialization("chrome");
		LoginPage loginpage = new LoginPage(driver);
		// driver.get("https://www.amazon.in/");
		loginpage.startPortal();
	}

	//@Test
	public void AddProductstoTheShoppingCart() throws InterruptedException, IOException {
		// ExtendReport.extenttest =extentReports.createTest("Search Functionatity"+ " :
		// "+
		// Thread.currentThread().getStackTrace()[1].getMethodName().toString()).info("Validate
		// Search Functinality");
		SearchPage search = new SearchPage(driver);
		CartPage cart = new CartPage(driver);
		String product = "Shoe";
		String productyqty = "6";
		// search.ValidateSearchFunctionality(ExtendReport.extenttest);

		search.SearchProduct(product);
		search.addProductToCart_1(product);
		// search.addProductToCart(product);
		cart.updateItemQuantity(product, productyqty);

		cart.Validatecartquntity();
		
	}
	@Test(priority = 1)
	public void ValidateCartQtyTest() {
		
		CartPage cart = new CartPage(driver);
		
		cart.Validatecartquntity();
		System.out.println("done");
		
	}
	@Test(priority = 2)
	public void ValidateCartPriceTest() {
		CartPage cart = new CartPage(driver);
		cart.ValidateCartPrice();	
		
	}
	
	
	
	@Test()
	public void ElementScreenshotTest() throws IOException{
		
		CartPage cart = new CartPage(driver);
		
		cart.ElementScreenshot();
		
	}
	
	
	
	
}
