import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Q7_FoodDeliveryNegativeTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(System.getProperty(
            "app.url", "http://localhost:8080/food"));
    }

    @Test
    public void rejectInvalidLogin() {
        driver.findElement(By.id("email")).sendKeys("invalid@example.com");
        driver.findElement(By.id("password")).sendKeys("wrong");
        driver.findElement(By.id("login")).click();

        Assert.assertTrue(
            driver.findElement(By.id("error")).isDisplayed());
    }

    @Test
    public void rejectEmptyCartCheckout() {
        login();
        driver.findElement(By.id("viewCart")).click();

        Assert.assertFalse(
            driver.findElement(By.id("checkout")).isEnabled(),
            "Checkout should be disabled for an empty cart");
    }

    @Test
    public void rejectEmptyDeliveryAddress() {
        login();
        driver.findElement(By.id("restaurant-1")).click();
        driver.findElement(By.id("add-item-1")).click();
        driver.findElement(By.id("viewCart")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("placeOrder")).click();

        Assert.assertTrue(
            driver.findElement(By.id("addressError")).isDisplayed());
    }

    @Test
    public void unavailableItemCannotBeOrdered() {
        login();
        driver.findElement(By.id("restaurant-1")).click();
        driver.findElement(By.id("unavailable-item")).click();

        Assert.assertTrue(
            driver.findElement(By.id("unavailableMessage")).isDisplayed());
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
