package Pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class TodoPage extends BasePage {

	public TodoPage(WebDriver driver, Properties loc) {
		super(driver, loc);
	}
	
	
	public void clickAdd() {
		click("TodoAddbtn");
	}
	public void enterTodo(String name) {
		type("TodoField",name);
	}
	
	public void clicksave() {
		click("TodoSavebtn");
	}
	

}
