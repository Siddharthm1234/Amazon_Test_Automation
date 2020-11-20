package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.ReadExcel;
import pages.AmazonHomePage;


public class LoginTest {
	AmazonHomePage amazonHomePage;
	WebDriver driver;
	
	@BeforeTest
	public void initialisation(){
		System.setProperty("webdriver.chrome.driver","E:\\Selenium\\Selenium\\chromedriver\\chromedriver.exe");	
	}
	
	@Test(dataProvider = "loginData")
	public void test(String username, String password) throws InterruptedException {
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://www.amazon.in/");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  
		amazonHomePage = new AmazonHomePage(driver);
		
		amazonHomePage.loginAmazon(username, password); 
		  
		amazonHomePage.logoutAmazon();
	}
	
	@AfterMethod
	public void teardown(){
		driver.quit();
	}
  
  @DataProvider(name = "loginData")
  public String [][] loginData() throws IOException{
	  
	  String [][] data = new String[4][2];
	  
	  ReadExcel readCredentials = new ReadExcel();
	  String filePath = System.getProperty("user.dir")+"\\src\\data";
	  /*PLEASE PROVIDE GENUINE AMAZON ACCOUNTS HERE */ 
	  String fileName = "AmazonCreds.xlsx";
	  String sheetName = "Sheet1";
	  data = readCredentials.readExcel(filePath, fileName, sheetName);
	  return data;
  }
}
