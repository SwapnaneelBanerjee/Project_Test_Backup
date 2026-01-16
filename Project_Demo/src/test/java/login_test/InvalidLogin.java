package login_test;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InvalidLogin {
	
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	@BeforeTest
	public void setup() {
		System.out.println("From ---> setup()");

		driver = new ChromeDriver();

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("http://localhost:3000/sign-in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		System.out.println("From ---> tearDown()");
		//Thread.sleep(2000);
		driver.close();
	}
  @Test
  public void invalidLogin_1() {
	  System.out.println("Login Test Function TCL-004: InvalidLogin");
	  driver.findElement(By.name("email")).sendKeys("user@tddbank.com");
	  driver.findElement(By.name("password")).sendKeys("123456");
	  driver.findElement(By.xpath("//form//button[@type='submit']")).click();
	  
	  // issue here fix the xpath ----------
	  WebElement msgText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div/div/div[2]/p")));
	  System.out.println(msgText.getText());
	  assertEquals(msgText.getText(), "Access Denied: This account is not part of the authorized banking group.");
  }
}
