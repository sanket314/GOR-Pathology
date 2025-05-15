package testcases;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import Pages.LoginPage;
import base.BaseTest;

public class LoginTest extends BaseTest {
	
	LoginPage loginpage;
	
	   @BeforeMethod
	    public void initPage() {
		   driver.get(prop.getProperty("homeurl"));
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
            loginpage=new LoginPage(driver,loc);
	    }
	
	@Test(priority=1)
	public void TC01() throws InterruptedException  {
		loginpage.enterEmail("test@kennect.io");
		loginpage.enterPassword("Qwerty@1234");
		Thread.sleep(3000);
		loginpage.clickLogin();
		wait.until(ExpectedConditions.urlContains("dashboard"));
		Assert.assertTrue(driver.getCurrentUrl().equals(prop.getProperty("dashurl")));
	}
}
