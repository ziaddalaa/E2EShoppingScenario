package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By productContainer = By.cssSelector(".mb-3");

    private By productTitle = By.cssSelector("b");
    private By addToCartButton = By.xpath("//button[@class='btn w-10 rounded']");

    private By successToast = By.id("toast-container");
    private By cartButton = By.cssSelector("button[routerlink*='cart']");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public void addProductToCart(String productName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(productContainer));
        List<WebElement> products = driver.findElements(productContainer);
        actions = new Actions(driver);
        actions.scrollByAmount(0,300).pause(150).perform();

        WebElement neededElement = products.stream().filter(x -> x.findElement(productTitle).getText().
                contains(productName)).findFirst().orElse(null);
        assert neededElement != null;
        neededElement.findElement(addToCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
    }

    public CartPage clickOnCartIcon(){
        WebElement cartButton = driver.findElement(this.cartButton);
        actions.scrollToElement(cartButton).pause(300).perform();
        cartButton.click();
        return new CartPage(driver,wait);
    }
}
