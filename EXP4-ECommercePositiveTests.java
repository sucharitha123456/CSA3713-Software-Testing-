import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Q4_ECommercePositiveTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    private void login() {
        driver.findElement(By.id("user-name"))
              .sendKeys("standard_user");
        driver.findElement(By.id("password"))
              .sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void successfulLogin() {
        login();
        Assert.assertTrue(
            driver.findElement(By.className("inventory_list"))
                  .isDisplayed());
    }

    @Test
    public void addProductToCart() {
        login();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack"))
              .click();

        Assert.assertEquals(
            driver.findElement(By.className("shopping_cart_badge"))
                  .getText(), "1");
    }

    @Test
    public void completeCheckout() {
        login();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack"))
              .click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Test");
        driver.findElement(By.id("last-name")).sendKeys("User");
        driver.findElement(By.id("postal-code")).sendKeys("600001");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        Assert.assertTrue(
            driver.findElement(By.className("complete-header"))
                  .isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
