package pageObjects;

import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Utilitis.ExcelUtils;

public class BeCognizantPage extends basePage {
	public BeCognizantPage(WebDriver driver) throws IOException {
		super(driver);
		
	}
	
	//Be Cognizant Elements 
	@FindBy(xpath="//*[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']")
	WebElement userprofile;
	
	@FindBy(id="mectrl_currentAccount_primary")
	WebElement name;
	
	@FindBy(id="mectrl_currentAccount_secondary")
	WebElement emailId;
	
	@FindBy(xpath="//*[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']")
	WebElement profileXpathClose;
	
	@FindBy(id="mectrl_headerPicture")
	WebElement profileIdClose;
	
	@FindBy(xpath="//div[@title='OneCognizant']")
	WebElement oneCognizant;

	
	public void clickProfile()
	{	try {
		userprofile.click();
		if(!name.isDisplayed()) {
			profileIdClose.click();
		}
		}catch(Exception e) {}
	}
	
	public void getProfile() throws IOException
	{
		String Name = name.getText();
		String Email = emailId.getText();
		System.out.println("--------------- Personal Info ---------------");
		System.out.println("Name     : "+Name+"\nMail Id  : "+Email);
		System.out.println("---------------------------------------------");
		System.out.println("");
		ExcelUtils.write("Profile Info", 0, 0, Name);
		ExcelUtils.write("Profile Info", 0, 1, Email);
	}
	
	public void closeProfile()
	{	try
		{
			profileXpathClose.click();
		}
		catch(Exception e)
		{	
			profileIdClose.click();
		}
	}
	
	public void openOneCognizant(WebDriver driver) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('.a_a_beed2cf1.c_a_beed2cf1.e_a_beed2cf1').scrollTop=300");
		Thread.sleep(4000);
		System.out.println("OneCognizant Button is Displayed -"+oneCognizant.isDisplayed());
		System.out.println("");
		oneCognizant.click();
	}
}
