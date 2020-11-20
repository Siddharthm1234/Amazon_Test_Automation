package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AmazonHomePage {
	WebDriver driver;
	
	By profileButton = By.xpath("//*[@id='nav-link-accountList']");
	
	By amazonUsername = By.xpath("//*[@id='ap_email']");

    By amazonPassword = By.xpath("//*[@id='ap_password']");
    
    By continueButton = By.xpath("//*[@id='continue']");
    
    By loginButton = By.xpath("//*[@id='signInSubmit']");
    
    By searchBar = By.xpath("//*[@id='twotabsearchtextbox']");
    
    By items = By.cssSelector("div[data-index='1']");
    
    By profileButtom = By.xpath("//header/div[@id='navbar']/div[@id='nav- belt']/div[3]/div[1]/a[1]/span[1]");
    
    By signOutButton = By.xpath("//a[@id='nav-item-signout']");
    
    By amazonLoginErrorMessage = By.xpath("//*[@id='auth-error-message-box']/div/div/ul/li/span");
	
	public AmazonHomePage(WebDriver driver){
        this.driver = driver;
	}
    
    public void setUserName(String amazonUserName){
        driver.findElement(amazonUsername).sendKeys(amazonUserName);
    }
    
    public void setPassword(String amazonPassWord){
        driver.findElement(amazonPassword).sendKeys(amazonPassWord);
   }
    
    public void clickProfile(){
        driver.findElement(profileButton).click();
    }
    
    void clickContinue(){
        driver.findElement(continueButton).click();
        Boolean loginFailed = this.errorMessage();
        Assert.assertEquals(true , !loginFailed, "Invalid credentials, Login Failed!"); 
    }
    
    public boolean errorMessage(){
    	return driver.findElements(amazonLoginErrorMessage).size() > 0;
    }
    
    void clickLogin(){
        driver.findElement(loginButton).click();
    }
    
    
    
    /**
     * This POM method will be exposed in test case to login in the application
     * @param amazonUsername
     * @param amazonPassword
     * @return
     */
    
    public void loginAmazon(String amazonUsername,String amazonPassword){
        this.clickProfile();
    	this.setUserName(amazonUsername);
    	this.clickContinue();
        this.setPassword(amazonPassword);
        this.clickLogin();        
    }
    
    /**
     * This POM method will be exposed in test case to logout in the application
     */
    public void logoutAmazon() throws InterruptedException{
    	Actions actions = new Actions(driver);
    	WebDriverWait wait = new WebDriverWait(driver, 10);
    	WebElement profile =driver.findElement(profileButton);
    	actions.moveToElement(profile).perform();
    	WebElement signOutBtn =driver.findElement(signOutButton);   
    	wait.until(ExpectedConditions.elementToBeClickable(signOutBtn));
    	signOutBtn.click();
    	
    }
    public void itemFound(){
        Boolean itemFound = driver.findElements(items).size() > 0;
        Assert.assertEquals(false , !itemFound, "Invalid Search!");
    }
    
    
    
    /**
     * This POM method will be exposed in test case to search the item
     * @param item
     * @return
     */
    public void searchItem(String item){
        driver.findElement(searchBar).sendKeys(item);
        driver.findElement(searchBar).sendKeys(Keys.ENTER);
        this.itemFound();
   }
    
    /**
     * This POM method will be exposed in test case to search and add the item
     * @param item
     * @return
     */
    public void addToCart(String item) throws InterruptedException{
    	driver.findElement(searchBar).sendKeys(item);
        driver.findElement(searchBar).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();
        
        
        
    }
    
}
