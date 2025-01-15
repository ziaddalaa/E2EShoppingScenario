import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pom.*;

import java.time.Duration;

public class AddToCartTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private PaymentPage paymentPage;
    private ThankYouPage thankYouPage;

    @BeforeClass
    public void setUp() {
        // Initialize WebDriver and WebDriverWait
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Navigate to the login page
        driver.get("https://rahulshettyacademy.com/client/"); // Replace with your app's URL

        // Initialize Page Objects
        loginPage = new LoginPage(driver, wait);
        homePage = new HomePage(driver, wait);
    }

    @Test(priority = 1)
    public void testLogin() {
        // Log in to the application
        loginPage.addEmail("zikk2001@gmail.com"); // Replace with valid email
        loginPage.addPassword("Aghzy13697?"); // Replace with valid password
        homePage = loginPage.clickLoginButton();
        wait.until(ExpectedConditions.urlContains("dash"));
        // Verify login success by checking if the home page loads
        Assert.assertTrue(driver.getCurrentUrl().contains("/dash"), "Login failed!");
    }

    @Test(priority = 2)
    public void testAddProductToCart() {
        // Add a product to the cart
        String productName = "IPHONE 13 PRO"; // Replace with the actual product name
        homePage.addProductToCart(productName);

        // Verify the product is added to the cart
        cartPage = homePage.clickOnCartIcon();
        Assert.assertTrue(cartPage.isProductInCart(productName), "Product was not added to the cart!");
    }

    @Test(priority = 3)
    public void testCheckout() {
        // Proceed to checkout
        String productName = "IPHONE 13 PRO"; // Replace with the actual product name
        paymentPage = cartPage.clickOnCheckOutButton(productName);

        // Verify the checkout page loads
        Assert.assertTrue(driver.getCurrentUrl().contains("order"), "Checkout failed!");
    }

    @Test(priority = 4)
    public void testCountrySelection() {
        // Enter and select a country
        String countryName = "Egypt"; // Replace with the country you want to test
        paymentPage.enterCountryName(countryName);
        paymentPage.selectCountryFromList(countryName);

        // Verify the country is selected (you can add a method in PaymentPage to check this)
        Assert.assertTrue(paymentPage.isCountrySelected(countryName), "Country selection failed!");
    }

    @Test(priority = 5)
    public void testPlaceOrder() {
        // Place the order
        thankYouPage = paymentPage.clickOnPlaceOrder();

        // Verify the thank-you page loads
        wait.until(ExpectedConditions.urlContains("thanks"));
        Assert.assertTrue(driver.getCurrentUrl().contains("thanks"), "Order placement failed!");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}