import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Q6_FoodDeliveryPositiveTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(System.getProperty(
            "app.url", "http://localhost:8080/food"));
    }

    @Test
    public void validCustomerLogin() {
        driver.findElement(By.id("email"))
              .sendKeys("customer@example.com");
        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();

        Assert.assertTrue(
            driver.findElement(By.id("homePage")).isDisplayed());
    }

    @Test
    public void selectRestaurantAndViewMenu() {
        login();
        driver.findElement(By.id("restaurant-1")).click();

        Assert.assertTrue(
            driver.findElement(By.id("menu")).isDisplayed());
    }

    @Test
    public void addFoodToCart() {
        login();
        driver.findElement(By.id("restaurant-1")).click();
        driver.findElement(By.id("add-item-1")).click();

        Assert.assertEquals(
            driver.findElement(By.id("cartCount")).getText(), "1");
    }

    @Test
    public void placeFoodOrder() {
        login();
        driver.findElement(By.id("restaurant-1")).click();
        driver.findElement(By.id("add-item-1")).click();
        driver.findElement(By.id("viewCart")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("address"))
              .sendKeys("Test Address, Chennai");
        driver.findElement(By.id("placeOrder")).click();

        Assert.assertTrue(
            driver.findElement(By.id("orderConfirmation")).isDisplayed());
    }

    private void login() {
        driver.findElement(By.id("email"))
              .sendKeys("customer@example.com");
        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
