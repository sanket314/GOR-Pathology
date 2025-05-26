package Pages;

import java.util.Properties;
import org.openqa.selenium.WebDriver;

public class AddpatientPage extends BasePage{

	public AddpatientPage(WebDriver driver, Properties loc) {
		super(driver, loc);
	}


	public void addPatient() {
		click("PatientsTab");
		click("AddPatientbtn");
	}
	
	public void addPatientDetail(String name ,String email ,String phone) {
		type("Name",name);
		type("Email" ,email);
		type("Phone",phone);
		
	}
	
	public void clickGeneraldetail() {
		click("GeneralDetailsbtn");
	}
	
	public void clickCancel() {
		click("Cancelbtn");
	}
	
	public void enterHeight(String height) {
		type("Height", height);
	}
	
	public void addTestsbtn() {
		click("AddTestsbtn");
	}
	
	public void addPatient(String height,String weight,String age ,String systolic, String diastolic) {
		type("Height",height);
		type("Weight",weight);
		type("Age",age);
		type("Systolic",systolic);
		type("Diastolic",diastolic);
	}
	
	public void gender(String gender) {
		click("Gender");
		if(gender=="male") {
			click("male");
		}
		else {
			click("female");
		}
	}	
}
