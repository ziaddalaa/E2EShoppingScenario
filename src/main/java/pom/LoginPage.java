package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By emailField = By.id("userEmail");
    private By passwordField = By.id("userPassword");
    private By loginButton = By.id("login");
    private By productContainer = By.cssSelector(".mb-3");


    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void addEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    public void addPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public HomePage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new HomePage(driver, wait);
    }


}
