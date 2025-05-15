package Pages;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	WebDriverWait wait;
	WebDriver driver;
	Properties loc;
	
     public BasePage(WebDriver driver, Properties loc) {
    	 this.driver=driver;
    	 this.loc=loc;
    	 this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
     }
	
     public WebElement getelement(String key) {
    	 return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty(key))));
      }
     
    public String getAttribute(String key , String attributename) {
    	By locator = By.xpath(loc.getProperty(key));
    	return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute(attributename); 	
    }
    
    public void click(String key) {
    	By locator =By.xpath(loc.getProperty(key));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    
     public void type(String key,String value) {
    	 By locator =By.xpath(loc.getProperty(key));
    	 WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    	 element.clear();
    	 element.sendKeys(value);
     }
}
