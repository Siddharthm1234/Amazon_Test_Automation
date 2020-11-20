package pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonItemPage {
	WebDriver driver;
	
	By addToCart = By.xpath("//*[@id='add-to-cart-button']");
	
	public AmazonItemPage(WebDriver driver){
        this.driver = driver;
	}
	
	/**
     * This POM method will be exposed in test case to add item to cart
     * @param item
     * @return
     */
	public void clickAddToCart(){
		Set<String> ids = driver.getWindowHandles();
        Iterator<String> it = ids.iterator();
        String parentId = it.next();
        String childId = it.next();
        driver.switchTo().window(childId);
        driver.findElement(By.id("add-to-cart-button")).click();
    }
}
