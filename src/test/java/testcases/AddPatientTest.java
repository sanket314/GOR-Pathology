package testcases;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Pages.AddpatientPage;
import Pages.LoginPage;
import base.BaseTest;


public class AddPatientTest extends BaseTest {
	
	LoginPage loginpage;
	AddpatientPage AddPatient;
	
	@BeforeClass
    public void initPage() {
	   wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
       loginpage = new LoginPage(driver,loc);
       AddPatient = new AddpatientPage(driver,loc);
       loginpage.enterEmail("test@kennect.io");
       loginpage.enterPassword("Qwerty@1234");
       loginpage.clickLogin(); 
       wait.until(ExpectedConditions.urlContains("dashboard"));
    }
	
	 @Test(priority=1)
	 public void TS19(){
	 driver.navigate().refresh();
	 AddPatient.addPatient();
	 AddPatient.clickGeneraldetail();
     Assert.assertTrue(AddPatient.getelement("Alert").isDisplayed());
	}
	 @Test(priority=2)
	 public void TS18(){
	 AddPatient.addPatientDetail("test", "test11@yopmail.com", "9988776655");
	 AddPatient.clickGeneraldetail();
	 List<WebElement> elements = driver.findElements(By.xpath(loc.getProperty("GeneralDetailsbtn")));
	 Assert.assertTrue(elements.isEmpty());
     }
	 
	 @Test(priority=3)
	 public void TS21() {
      AddPatient.enterHeight("");
	  Assert.assertTrue(AddPatient.getelement("HeightAlert").isDisplayed());
    }
	 
	 @Test(priority=4)
	 public void TS20() {
      AddPatient.enterHeight("0");
      Assert.assertTrue(AddPatient.getelement("HeightAlert").isDisplayed());		 
	 }
	 
	 @Test(priority=5)
	 public void TS22() throws InterruptedException {
      AddPatient.addPatient("175","70","29","90","100");
      AddPatient.gender("male");
      AddPatient.addTestsbtn();
      Thread.sleep(3000);
	  Assert.assertTrue(AddPatient.getelement("labsreco").isDisplayed());
	 } 
}
