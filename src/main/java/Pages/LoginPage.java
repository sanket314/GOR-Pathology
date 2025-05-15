package Pages;

import java.util.Properties;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver, Properties loc) {
		super(driver, loc);
	}
	
	public void enterEmail(String email) {
		type("email",email);
	}
	
	public void enterPassword(String password) {
		type("password",password);
	}
	
	public void clickLogin() {
		click("loginbtn");
	}

}
