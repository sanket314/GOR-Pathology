package testcases;

import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
	
	 @Test(priority=1,dataProvider="testData")
	 public void TS09(String testname) {
		 
		  dashpage.scrolltoview();
	      dashpage.selectfromdropdown(testname);
	      String chipXpath = String.format("//span[contains(@class,'MuiChip-label') and contains(text(),'%s')]", testname);
          WebElement chip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(chipXpath)));
          Assert.assertTrue(chip.isDisplayed());
	     
	 }
	 @Test(priority=2,dataProvider="testData")
	 public void TS10(String dicount) {
		 dashpage.selectdiscount("5%");
         String selectedDiscountXpath = "//div[normalize-space()='5%']";
         WebElement selectedDiscount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectedDiscountXpath)));
         Assert.assertTrue(selectedDiscount.isDisplayed());
	 }
	 
	 @Test(priority=3,dataProvider="testData")
	 public void TS11(String discount1) {
		 
        Double sum= dashpage.calculateNetSum();
		Double actualTotal = dashpage.applyDiscount(sum, discount1);
		Double expectedTotal = dashpage.expectedTotal();
		Assert.assertEquals(actualTotal, expectedTotal);	 
   	 } 
	 
	 @Test(priority=4,dataProvider="testData")
	 public void TS13(String testname1 ,String testname2 ,String testname3 ,String discount2,String discount3) {
	    driver.get(prop.getProperty("dashurl"));

        dashpage.scrolltoview();
        dashpage.selectfromdropdown(testname1);
        dashpage.selectfromdropdown(testname2);
        dashpage.selectfromdropdown(testname3);
        dashpage.selectdiscount(discount2);
        
        Double sum =dashpage.calculateNetSum();
	    Double actualTotal = dashpage.applyDiscount(sum, discount3);
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
	 
	 @Test(priority=6,dataProvider="testData")
	 public void TS15(String discount4){
     driver.get(prop.getProperty("dashurl"));

	 dashpage.scrolltoview();
     dashpage.selectdiscount(discount4);
	 
	 Double expectedTotal =dashpage.expectedTotal();
	 Double actualTotal = 0.0;
	 
	 Assert.assertEquals(expectedTotal, actualTotal);
	 
	 } 
	 
	 @DataProvider(name="testData")
	 public Object[][] provideTestdata(Method method){
		 String testname = method.getName();
		 
		 switch(testname) {
			 case "TS09" :
		          return new Object[][] {{"UR CHEMICAL"}};
				 
			 case "TS10":
				 return new Object[][] {{"5%"}};
				
			 case "TS11":
				 return new Object[][] {{"5"}};	
				
			 case "TS13":
				 return new Object[][] {{"abc","mno","xyz","10%","10"}};
				 
			 case "TS15":
				 return new Object[][] {{"15%"}};	
			
			default:
				return new Object [0][];
		 }
	 }
	 
}
