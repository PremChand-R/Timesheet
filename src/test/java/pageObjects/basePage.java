package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class basePage {
	 WebDriver driver;
	 
	 public basePage(WebDriver driver){
		 this.driver=driver;
		 //initialize WebElements
		 PageFactory.initElements(driver,this);
	 }   
}