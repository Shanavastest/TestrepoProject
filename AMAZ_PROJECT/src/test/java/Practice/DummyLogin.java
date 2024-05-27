package Practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import DataFile.ExcelDataSupplier;
import Listeners.ITestListerClass;
import Utilities.Base_class;
import Utilities.ExtendReport;
import pages.LoginPage;

@Listeners(ITestListerClass.class)
public class DummyLogin extends Base_class {

	public static WebDriver driver;

	@BeforeTest
	public static void setUp() {
		driver = Base_class.driverInitialization("chrome");

	}

	@Test(dataProviderClass = ExcelDataSupplier.class, dataProvider = "loginData")
	public void invalidLoginTestData(String email, String password) {
		ExtendReport.extenttest = extentReports
				.createTest("InValid Data" + " : " + Thread.currentThread().getStackTrace()[1].getMethodName().toString())
				.info("Valid Data Login Test");
		LoginPage loginpage = new LoginPage(driver);

		loginpage.dataProviderLogin(email, password);

	}

	@AfterTest
	public void tearDown() throws InterruptedException {

		driver.close();

	}

	@BeforeSuite
	public void extentReportSetUp() {

		extentReportStart();
	}

	@AfterSuite
	public void extentReportTeardown() throws IOException {
		extentReportTearDown();
	}

}
