package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import TestBase.baseClass;

public class Hooks {
	 public static WebDriver driver;
	 static Properties p;
     static baseClass bc=new baseClass();
     
    
	@BeforeAll
	public static void before_or_after_all() throws IOException
    {	//create driver
		driver=baseClass.initializeBrowser();
		p=baseClass.getProperties();
		// navigate to beCognizant
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}

      
	@AfterStep
    public void addScreenshot(Scenario scenario) {
   	// this is for cucumber junit report
        if(!scenario.isFailed()) {
        	TakesScreenshot ts=(TakesScreenshot) driver;
       		byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
       		scenario.attach(screenshot, "image/png",scenario.getName());
        }
	}
}
