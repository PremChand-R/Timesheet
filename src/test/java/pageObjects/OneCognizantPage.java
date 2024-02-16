package pageObjects;

import java.io.IOException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class OneCognizantPage extends basePage {
	public OneCognizantPage(WebDriver driver) throws IOException {
		super(driver);
	}
	
	//OneCognizant Elements
	@FindBy(xpath="//*[@id='oneC_searchAutoComplete']")
	WebElement searchBar;
	@FindBy(className="searchInputIcon")
	WebElement searchIcon;
	@FindBy(xpath="//*[@id='newSearchQALST']//div[contains(text(),'Submit Timesheet')]")
	WebElement timeSheetIcon;
	
	public void inputSearchBar(String input){
		searchBar.sendKeys(input);
	}
	public void search(){
		searchIcon.click();
	}
	public void clickSubmitTimesheet(){
		timeSheetIcon.click();
	}
	
	
	

}
