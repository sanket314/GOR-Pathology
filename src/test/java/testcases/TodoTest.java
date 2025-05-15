package testcases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Pages.TodoPage;
import base.BaseTest;

public class TodoTest extends BaseTest {
	
	LoginPage loginpage;
	TodoPage Todo;
	
	   @BeforeClass
	    public void initPage() {
		   wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
          loginpage=new LoginPage(driver,loc);
          Todo=new TodoPage(driver,loc);
          loginpage.enterEmail("test@kennect.io");
          loginpage.enterPassword("Qwerty@1234");
          loginpage.clickLogin(); 
	       wait.until(ExpectedConditions.urlContains("dashboard"));
	    }
	 
	
	@Test(priority=1)
	public void TS05() {
		Todo.clickAdd();
		Todo.enterTodo("");
		Todo.clicksave();
		WebElement alert = driver.findElement(By.xpath(loc.getProperty("SuccesTodoAlert")));
	    Assert.assertFalse(alert.isDisplayed());
	    System.out.println("Not able to create empty character task");		
	}
	
	@Test(priority=2)
	public void TS06() {
		Todo.enterTodo("NewOne");
		Todo.clicksave();
		WebElement alert = driver.findElement(By.xpath(loc.getProperty("SuccesTodoAlert")));
	    Assert.assertTrue(alert.isDisplayed());
	}
		
	@Test(priority=3)
	 public void TS07() {
	     driver.get(prop.getProperty("dashurl"));
	     driver.navigate().refresh();
	     Assert.assertTrue(driver.getCurrentUrl().equals(prop.getProperty("dashurl")));
	     System.out.println("User still logged in");
	 }

}
