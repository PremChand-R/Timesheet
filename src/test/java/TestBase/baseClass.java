package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.util.*;

public class baseClass {
	static WebDriver driver;
    static Properties p;
    static Logger logger;
 	     
public static WebDriver initializeBrowser() throws IOException
{
	if(getProperties().getProperty("execution_env").equalsIgnoreCase("remote"))
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		//os
		if (getProperties().getProperty("os").equalsIgnoreCase("windows")) {
		    capabilities.setPlatform(Platform.WIN11);
		} else if (getProperties().getProperty("os").equalsIgnoreCase("mac")) {
		    capabilities.setPlatform(Platform.MAC);
		} else {
		    System.out.println("No matching OS..");
	    }
		
		//browser
		switch (getProperties().getProperty("browser").toLowerCase()) {
		    case "chrome":
		        capabilities.setBrowserName("chrome");
		        break;
		    case "edge":
		        capabilities.setBrowserName("MicrosoftEdge");
		        break;
		    default:
		        System.out.println("No matching browser");
		}
      
       driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		
	}
	else if(getProperties().getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(getProperties().getProperty("browser").toLowerCase()) 
			{
			case "chrome":
		        driver=new ChromeDriver();
		        break;
		    case "edge":
		    	driver=new EdgeDriver();
		        break;
		    default:
		        System.out.println("No matching browser");
		        driver=null;
			}
		}
//	String appUrl=getProperties().getProperty("appURL");
	 driver.manage().deleteAllCookies(); 
	// driver.get(p.getProperty("appURL"));
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 driver.navigate().refresh();
	 return driver;
	 
}

public static WebDriver getDriver() {
		return driver;
	}
public String getHandle() {
	return driver.getWindowHandle();
}
public void setHandle(WebDriver driver,List<String> windowHandles) throws InterruptedException
{
	for (String windowHandle : driver.getWindowHandles()) {
	    if(!windowHandles.contains(windowHandle)) {
	        driver.switchTo().window(windowHandle);
	        break;
	    }
	}
	
}
public static Properties getProperties() throws IOException
{		 
   FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
  		
   p=new Properties();
	p.load(file);
	return p;
}

public static Logger getLogger() 
{		 
	logger=LogManager.getLogger(); //Log4j
	return logger;
}
public void captureScreen(String name) throws IOException 

{
	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File target=new File(System.getProperty("user.dir")+"\\screenshot\\"+name + "_" + timeStamp +".jpg");
    FileUtils.copyFile(screenshotFile,target);
}
public String captureScreenshot(String name) 

{
	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + name + "_" + timeStamp + ".png";
	File targetFile=new File(targetFilePath);
	sourceFile.renameTo(targetFile);
	return targetFilePath;
}




}
