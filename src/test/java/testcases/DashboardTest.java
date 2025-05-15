package testcases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import Pages.DashboardPage;
import Pages.LoginPage;
import base.BaseTest;

public class DashboardTest extends BaseTest {

	LoginPage loginpage;
	DashboardPage dashpage;
	
	   @BeforeClass
	    public void initPage() {
		   wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
           loginpage=new LoginPage(driver,loc);
           dashpage=new DashboardPage(driver,loc);
           loginpage.enterEmail("test@kennect.io");
           loginpage.enterPassword("Qwerty@1234");
           loginpage.clickLogin(); 
	       wait.until(ExpectedConditions.urlContains("dashboard"));
	    }
	
	 @Test(priority=1)
	 public void TS09() throws InterruptedException{
		 
		  dashpage.scrolltoview();
		  String testname ="Beans";
	      dashpage.selectfromdropdown(testname);
	      String chipXpath = String.format("//span[contains(@class,'MuiChip-label') and contains(text(),'%s')]", testname);
          WebElement chip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(chipXpath)));
          Assert.assertTrue(chip.isDisplayed());
	     
	 }
	 @Test(priority=2)
	 public void TS10() throws InterruptedException{
		 dashpage.selectdiscount("5%");
         String selectedDiscountXpath = "//div[normalize-space()='5%']";
         WebElement selectedDiscount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectedDiscountXpath)));
         Assert.assertTrue(selectedDiscount.isDisplayed());
	 }
	 
	 @Test(priority=3)
	 public void TS11() throws InterruptedException{
		 
        Double sum= dashpage.calculateNetSum();
		Double actualTotal = dashpage.applyDiscount(sum, "5");
		Double expectedTotal = dashpage.expectedTotal();
		Assert.assertEquals(actualTotal, expectedTotal);	 
   	 } 
	 
	 @Test(priority=4)
	 public void TS13() throws InterruptedException{
	    driver.get(prop.getProperty("dashurl"));

        dashpage.scrolltoview();
        dashpage.selectfromdropdown("Beans");
        dashpage.selectfromdropdown("Shad Johns");
        dashpage.selectfromdropdown("UR URIC ACID");
        dashpage.selectdiscount("10%");
        
        Double sum =dashpage.calculateNetSum();
	    Double actualTotal = dashpage.applyDiscount(sum, "10");
		Double expectedTotal = dashpage.expectedTotal();
		Assert.assertEquals(actualTotal, expectedTotal);	 
     
	   
	   }
	   
	 @Test(priority=5)
	 public void TS14(){
     driver.get(prop.getProperty("dashurl"));

	 dashpage.scrolltoview();
	 
	 Double expectedTotal =dashpage.expectedTotal();
	 Double actualTotal = 0.0;
	 
	 Assert.assertEquals(expectedTotal, actualTotal);
	 
	 }	 
	 
	 @Test(priority=6)
	 public void TS15(){
     driver.get(prop.getProperty("dashurl"));

	 dashpage.scrolltoview();
     dashpage.selectdiscount("10%");
	 
	 Double expectedTotal =dashpage.expectedTotal();
	 Double actualTotal = 0.0;
	 
	 Assert.assertEquals(expectedTotal, actualTotal);
	 
	 }
	
	 
}
