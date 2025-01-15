import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.CartPage;
import pom.HomePage;
import pom.LoginPage;
import pom.PaymentPage;

import java.time.Duration;

public class BaseTest {

    private WebDriverWait wait;
    private WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected CartPage cartPage;
    protected PaymentPage paymentPage;
    private String URL = "https://rahulshettyacademy.com/client/";


    @BeforeMethod
    public void setUp(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver, wait);
    }




    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
