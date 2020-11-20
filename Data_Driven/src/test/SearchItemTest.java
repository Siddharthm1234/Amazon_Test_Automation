package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import pages.AmazonHomePage;
import utilities.ReadExcel;

public class SearchItemTest {
	AmazonHomePage amazonHomePage;
	WebDriver driver;

	
	@BeforeTest
	public void initialisation(){
		System.setProperty("webdriver.chrome.driver","E:\\Selenium\\Selenium\\chromedriver\\chromedriver.exe");	
	}
	
	@Test(dataProvider = "search")
	public void test(String item, String item2) throws InterruptedException {
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://www.amazon.in/");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  
		amazonHomePage = new AmazonHomePage(driver);
		  
		amazonHomePage.searchItem(item);
		
	}
	
	@AfterMethod
	public void teardown(){
		driver.quit();
	}
	
	
  
  @DataProvider(name = "search")
  public String [][] searchItem() throws IOException{
	  
	  String [][] data = new String[4][1];
	  
	  ReadExcel readCredentials = new ReadExcel();
	  String filePath = System.getProperty("user.dir")+"\\src\\data";
	  String fileName = "AmazonSearch.xlsx";
	  String sheetName = "Sheet1";
	  data = readCredentials.readExcel(filePath, fileName, sheetName);
	  return data;
  }
}
