package Pages;

import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DashboardPage extends BasePage {

	public DashboardPage(WebDriver driver, Properties loc) {
		super(driver, loc);
	}


	public void scrolltoview()  {
       try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	public void selectfromdropdown(String testname) {
         click("dropdown");
         try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
         type("dropdown",testname);
         driver.findElement(By.xpath(loc.getProperty("firstDropdownele"))).click();
	}

	public void selectdiscount(String percentage)  {
		click("discountdrop");
	    String xpath = String.format("//li[normalize-space()='%s']", percentage);
	    WebElement dropdownItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	    dropdownItem.click();
	}

	public double getChipPrice(String chipText) {
	    String price = chipText.replaceAll("[^0-9]", ""); 
	    return Double.parseDouble(price);
	}

	public double calculateNetSum() {
	    List<WebElement> tests = driver.findElements(By.xpath("//span[@class='MuiChip-label']"));
	    double total = 0;

	    for (WebElement test : tests) {
	        String text = test.getText(); 
	        total += getChipPrice(text);
	    }
	    return total;
	}
	
	public double applyDiscount(double total, String percentage) {
	    double discountPercentage = Double.parseDouble(percentage) / 100;
	    double discountAmount = total * discountPercentage;
	    return total - discountAmount;
	}
	
	
	public double expectedTotal() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

		 WebElement element = driver.findElement(By.xpath("(//div[contains(@class,'MuiBox-root') and contains(text(),'₹')])[2]"));
	     String text = element.getText(); 
    	 String price = text.replaceAll("[^0-9.]", ""); 
    	 try {
		 Thread.sleep(3000);
    	 }
    	 catch(Exception e) {
    		 System.out.println();
    	 }
		 return Double.parseDouble(price);
	}
	
}
