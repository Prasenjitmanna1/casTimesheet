package CasTimeSheet;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TimeSheetTest {
	
	WebDriver driver;
	
	@BeforeMethod
	public void intialSetup() {
		WebDriverManager.edgedriver().setup();
		 driver = new EdgeDriver();
		 driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx"); 
		  System.out.println("Enter the url successfully......!!!");
	  
		
		
	}
	
	public void driverSwitch1() {
		
		 String mainWindowHandle = driver.getWindowHandle();
	        Set<String> allWindowHandles = driver.getWindowHandles();
	        Iterator<String> iterator = allWindowHandles.iterator();

	        // Here we will check if child window has other child windows and will fetch the heading of the child window
	        while (iterator.hasNext()) {
	            String ChildWindow = iterator.next();
	                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
	                driver.switchTo().window(ChildWindow);

	            }
	        }
	}

	
	
	@Test(priority=1)
	public void gotoTimeSheet() {
		
		driver.findElement(By.linkText("OneCognizant")).click();
		
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		driver.findElement(By.xpath("//li[@class='searchTopBar']")).click();
		driver.findElement(By.xpath("//input[@id='oneCSearchTop']")).sendKeys("Time Sheet");
		
		JavascriptExecutor ab=(JavascriptExecutor)driver;
		WebElement views=driver.findElement(By.xpath("//*[contains(text(),'View Timesheet')]"));
		ab.executeScript("arguments[0].click()",views);
	}
	
	@Test(priority=2)
	public void threeWeekDetails() throws InterruptedException {
		//dates 
//		for(String winHandle : driver.getWindowHandles()){
//		    driver.switchTo().window(winHandle);
//		}
		
		driverSwitch1();
		Thread.sleep(4000);
		List<WebElement> dates = driver.findElements(By.xpath("//div[@id='win0div$ICField44$0']/div/div[2]/div[2]/div[1]"));

		System.out.println(dates.size());
		
//		for(int i=0;i<dates.size();i++) {
//			System.out.println(dates.get(i).getText());
//		}
	
	}

	
	

}
