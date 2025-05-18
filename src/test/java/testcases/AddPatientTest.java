package testcases;

import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
	

    @Test(priority = 1)
    public void TS19() {
        driver.navigate().refresh();
        AddPatient.addPatient();
        AddPatient.clickGeneraldetail();
        Assert.assertTrue(AddPatient.getelement("Alert").isDisplayed());
    }

    @Test(priority = 2, dataProvider = "allPatientData")
    public void TS18(String name, String email, String phone) {
        AddPatient.addPatientDetail(name, email, phone);
        AddPatient.clickGeneraldetail();
        List<WebElement> elements = driver.findElements(By.xpath(loc.getProperty("GeneralDetailsbtn")));
        Assert.assertTrue(elements.isEmpty());
    }

    @Test(priority = 3, dataProvider = "allPatientData")
    public void TS21(String Height) {
        AddPatient.enterHeight(Height);
        Assert.assertTrue(AddPatient.getelement("HeightAlert").isDisplayed());
    }

    @Test(priority = 4, dataProvider = "allPatientData")
    public void TS20(String Height) {
        AddPatient.enterHeight(Height);
        Assert.assertTrue(AddPatient.getelement("HeightAlert").isDisplayed());
    }

    @Test(priority = 5, dataProvider = "allPatientData")
    public void TS22(String height, String weight, String age, String gender, String systolic, String diastolic) throws InterruptedException {
        AddPatient.addPatient(height, weight, age, systolic, diastolic);
        AddPatient.gender(gender);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        AddPatient.addTestsbtn();
        Thread.sleep(3000);
        Assert.assertTrue(AddPatient.getelement("labsreco").isDisplayed());
    }
    
    @DataProvider(name = "allPatientData")
    public Object[][] provideData(Method method) {
        switch (method.getName()) {
            case "TS18":
                return new Object[][] {
                    {"John Doe", "john@example.com", "9876543210"}
                };

            case "TS21":
                return new Object[][] {
                    {""}
                };

            case "TS20":
                return new Object[][] {
                    {"0"}
                };

            case "TS22":
                return new Object[][] {
                    {"170", "70", "30", "male", "120", "80"}
                };

            default:
                return new Object[0][];
        }
    }
    
}
