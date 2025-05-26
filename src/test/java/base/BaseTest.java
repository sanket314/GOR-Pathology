package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public Properties loc = new Properties();
	public Properties prop = new Properties();
		
   @BeforeSuite
	private void loadProperties() {
		try {
			FileInputStream configFis = new FileInputStream("src/test/resources/configfiles/config.properties");
			prop.load(configFis);

			FileInputStream locFis = new FileInputStream("src/test/resources/configfiles/locators.properties");
			loc.load(locFis);

		} catch (IOException e) {
			throw new RuntimeException("Failed to load property files", e);
		}
	}
	
	@BeforeClass
	public void setup() {
		
		loadProperties();
		if(prop.getProperty("browser").equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("homeurl"));
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
