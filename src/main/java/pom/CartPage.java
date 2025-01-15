package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By productContainerAtCart = By.cssSelector(".cart h3");
    private By checkoutButton = By.cssSelector(".totalRow button");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public PaymentPage clickOnCheckOutButton(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productContainerAtCart));
        List<WebElement> cartProducts = driver.findElements(productContainerAtCart);

        if (cartProducts.stream().map(WebElement::getText)
                .anyMatch(productCartName -> productCartName
                        .equalsIgnoreCase(productName))){
            driver.findElement(checkoutButton).click();
            return new PaymentPage(driver,wait);
        }
        else {
            throw new NoSuchElementException("Product not found in the cart: " + productName);
        }
    }

    public boolean isProductInCart(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productContainerAtCart));
        List<WebElement> cartProducts = driver.findElements(productContainerAtCart);
        return cartProducts.stream()
                .map(WebElement::getText)
                .anyMatch(productCartName -> productCartName.equalsIgnoreCase(productName));
    }


}
