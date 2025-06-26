package baseUtility;

import java.com.comcast.crm.generic.databaseutility.DataBaseUtility;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;

public class Base1Class {
	// public static TakesScreenshot sdriver;

	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	// creation of object
	public DataBaseUtility dblib = new DataBaseUtility();
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib;

	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
	public void configBS() throws SQLException {
		System.out.println("===Connect To DB, Report Config===");
//		((Object) dblib).getDbconnection();

	}

	@Parameters("BROWSER")
	@BeforeClass
	public void configBC(@Optional("chrome") String browser) throws Exception {
		System.out.println("==Launch Browser==");
		String BROWSER = browser;
		// String BROWSER = flib.getDataFromPropertiesFile("browser");
		System.out.println("B:" + BROWSER);
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		sdriver = driver;
//		UtilityClassObject.setdriver(driver);
		// wlib = new WebDriverUtility(driver);
	}

	@BeforeMethod
	public void configBM() throws Exception {
		System.out.println("==Login==");
//		String URL = flib.getDataFromPropertiesFile("url");
//		String USERNAME = flib.getDataFromPropertiesFile("username");
//		String PASSWORD = flib.getDataFromPropertiesFile("password");
//		LoginPage lp = new LoginPage(driver);
//		lp.loginToApp(URL, USERNAME, PASSWORD);

	}

	@AfterMethod
	public void configAM() {
		System.out.println("==LogOut==");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	@AfterClass
	public void configAC() {
		System.out.println("==close browse==");
		driver.quit();
	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configAS() throws Exception {
		System.out.println("===close DB, Report BackUp");
		// dblib.closeDbconnection();

	}

}
