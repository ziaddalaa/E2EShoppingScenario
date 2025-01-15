package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PaymentPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By paymentInfoContainer = By.className("payment__info");
    private By selectCountryField = By.cssSelector("input[placeholder='Select Country']");
    private By countryResultsList = By.className("ta-results");
    private By specificCountryResult = By.className("ta-item");
    private By placeOrderButton = By.className("btnn");

    public PaymentPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.actions = new Actions(driver); // Initialize the Actions object
    }

    public void enterCountryName(String country) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentInfoContainer));
        actions.sendKeys(driver.findElement(selectCountryField), country).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(countryResultsList));
    }

    public void selectCountryFromList(String country) {
        List<WebElement> countries = driver.findElements(specificCountryResult);
        countries.stream()
                .filter(countryName -> countryName.getText().equalsIgnoreCase(country))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public boolean isCountrySelected(String country) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectCountryField));
        String selectedCountry = driver.findElement(selectCountryField).getAttribute("value");
        return selectedCountry.equalsIgnoreCase(country);
    }

    public ThankYouPage clickOnPlaceOrder() {
        driver.findElement(placeOrderButton).click();
        return new ThankYouPage(driver, wait);
    }
}