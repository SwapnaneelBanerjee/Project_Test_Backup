package login_test;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ValidLogin {
	
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
	
	@AfterMethod
	public void logout() {
		driver.findElement(By.xpath("/html/body/main/nav/div/div/div/button")).click();
		driver.findElement(By.xpath("/html/body/main/nav/div/div/div/div/div[3]/button/span")).click();
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		System.out.println("From ---> tearDown()");
		//Thread.sleep(2000);
		driver.close();
	}
	
  @Test
  public void validLogin_User() {
	  System.out.println("Login Test Function TCL-001: ValidLogin");
	  driver.findElement(By.name("email")).sendKeys("user@tddbank.com");
	  driver.findElement(By.name("password")).sendKeys("password");
	  driver.findElement(By.xpath("//form//button[@type='submit']")).click();
	  
	  WebElement userTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/nav/div/div/div/button/span")));
	  assertEquals(userTitle.getText(), "Test User");
	  
  }
  
  @Test
  public void validLogin_Manager() {
	  System.out.println("Login Test Function TCL-002: ValidLogin");
	  driver.findElement(By.name("email")).sendKeys("manager@tddbank.com");
	  driver.findElement(By.name("password")).sendKeys("bankmanager");
	  driver.findElement(By.xpath("//form//button[@type='submit']")).click();
	  
	  WebElement userTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/nav/div/div/div/button/span")));
	  assertEquals(userTitle.getText(), "Operations Manager");
	  
  }
  
  @Test
  public void validLogin_Admin() {
	  System.out.println("Login Test Function TCL-003: ValidLogin");
	  driver.findElement(By.name("email")).sendKeys("admin@tddbank.com");
	  driver.findElement(By.name("password")).sendKeys("123456");
	  driver.findElement(By.xpath("//form//button[@type='submit']")).click();
	  
	  WebElement userTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/nav/div/div/div/button/span")));
	  assertEquals(userTitle.getText(), "Admin User");
	  
  }
  
}
