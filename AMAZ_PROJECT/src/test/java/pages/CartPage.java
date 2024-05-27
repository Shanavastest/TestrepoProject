package pages;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage {

	ArrayList<Double> ProductsPrize = new ArrayList<>();
	ArrayList<Integer> ProductList = new ArrayList<>();

	private static final String productName = "ball";

	public WebDriver driver;

	@FindBy(xpath = "//a[@id='nav-cart']")
	private WebElement cartIcon;

	@FindBy(xpath = "//input[@name='proceedToRetailCheckout']")
	private WebElement ProceedToBuyBtn;

//	@FindBy(xpath = "//span[contains(text(), '" + productName
//			+ "')/ancestor::div[@data-asin]//input[@type='text']//preceding-sibling::span")
//		private WebElement quantityInput;

	@FindBy(xpath = "//span[@id='nav-cart-count']")
	private WebElement cartqty;

	@FindBy(xpath = "//div[@class='sc-badge-price']/div/div")
	private List<WebElement> productPrizes;

//pan[contains(text(), '" + productName + "')]
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void updateItemQuantity(String productName, String quantity) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.navigate().refresh();

		// click on cart icon
		js.executeScript("arguments[0].click();", cartIcon);
		// cartIcon.click();
		// Update the quantity

		driver.findElement(By.xpath("//span[contains(text(), '" + productName
				+ "')]//ancestor::div[@data-asin]//input[@type='text']//preceding-sibling::span")).click();
		;
		// System.out.println(text);
		WebElement QtyInput = driver.findElement(By.xpath("//a[@id='quantity_10']"));

		wait.until(ExpectedConditions.elementToBeClickable(QtyInput));
		QtyInput.click();
		WebElement qty = driver.findElement(By.xpath("(//input[@name='quantityBox'])[1]"));
		// qty.clear();
		qty.sendKeys(Keys.BACK_SPACE);
		qty.sendKeys(quantity);
		qty.sendKeys(Keys.ENTER);
		// ProceedToBuyBtn.click();

	}

	// Validate cart quntity
	public void Validatecartquntity() {
		cartIcon.click();
		int totalProductQty = 0;
		List<WebElement> productsQty = driver
				.findElements(By.xpath("//div[@data-name='Active Items']//input[@aria-label='Quantity']"));

		// Adding all the product qty

		for (WebElement qty : productsQty) {
			String valueAttribute = qty.getAttribute("value");
			// String text = valueAttribute.getText();
			System.out.println(valueAttribute);
			int quantity = Integer.parseInt(valueAttribute);

			ProductList.add(quantity);
			// Total product qty
			totalProductQty = totalProductQty + quantity;

		}

		System.out.println(totalProductQty);
		System.out.println(cartqty.getText());
		int Cartquantity = Integer.parseInt(cartqty.getText());

		Assert.assertEquals(totalProductQty, Cartquantity);
		System.err.println("product list : " + ProductList.size());
	}

	public void ValidateCartPrice() {
		Validatecartquntity();
		cartIcon.click();

		for (WebElement product : productPrizes) {

			String text = product.getText().trim();
			String replace = text.replace(",", "");
			Double price = Double.parseDouble(replace);
			//Add all the products prize to the list
			ProductsPrize.add(price);

		}

		double result;
		double TotalPrice = 0;

		if (ProductList.size() == ProductsPrize.size()) {
			// Multiply corresponding values in the ArrayLists
			for (int i = 0; i < ProductList.size(); i++) {
				int product = ProductList.get(i);
				double price = ProductsPrize.get(i);
				result = product * price;
				// Total product prize in the cart(By add all the products in the cart list)
				TotalPrice = TotalPrice + result;
			}
		}
		// to have two digits after the decimal
		String formattedTotalPrice = String.format("%.2f", TotalPrice);
		// element for to get total card prize
		String text = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-buybox']")).getText();
		// Remove '&' and ',' symbols
		String CartPrize = text.replaceAll("[₹,]", "").trim();

		System.out.println(CartPrize);
		System.out.println(formattedTotalPrice);
		
		
		Assert.assertEquals(CartPrize, formattedTotalPrice);
		

	}
	
	
	
	public void ElementScreenshot() throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		cartIcon.click();
		
/*	// Explicit wait for the element to be present and visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      //  WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement elementToCapture = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='sc-buy-box']")));

		
		// WebElement elementToCapture = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-buybox']"));
		 
		 
		 // Take screenshot of the entire page
	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        
	        // Get the location and dimensions of the WebElement
            org.openqa.selenium.Point point = elementToCapture.getLocation();
            org.openqa.selenium.Dimension dimension = elementToCapture.getSize();
            
         // Read the screenshot into a BufferedImage
            BufferedImage fullScreenImage = ImageIO.read(screenshot);
            
         // Crop the screenshot to the dimensions of the WebElement
            BufferedImage elementScreenshot = fullScreenImage.getSubimage(point.getX(), point.getY(),
                    dimension.getWidth(), dimension.getHeight());

            // Save the cropped screenshot to a file
            ImageIO.write(elementScreenshot, "png", new File("C:\\Users\\vikram\\Desktop\\vijay\\AMAZ_PROJECT\\images.png"));*/
		
		
//		// Capture the screenshot as a File
//        TakesScreenshot scrShot = ((TakesScreenshot) driver);
//        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
//        
//        // Save the screenshot as a PNG file
//        File destFile = new File("C:\\Users\\vikram\\Desktop\\vijay\\AMAZ_PROJECT\\images.png");
//        FileUtils.copyFile(srcFile, destFile);
//        System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());
		
		
		
		

		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
