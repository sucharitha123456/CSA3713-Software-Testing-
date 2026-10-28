import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Q5_ECommerceNegativeTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void invalidLogin() {
        driver.findElement(By.id("user-name")).sendKeys("wrong_user");
        driver.findElement(By.id("password")).sendKeys("wrong_password");
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(
            driver.findElement(By.cssSelector("[data-test='error']"))
                  .isDisplayed());
    }

    @Test
    public void checkoutWithoutFirstName() {
        login();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack"))
              .click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("last-name")).sendKeys("User");
        driver.findElement(By.id("postal-code")).sendKeys("600001");
        driver.findElement(By.id("continue")).click();

        Assert.assertTrue(
            driver.findElement(By.cssSelector("[data-test='error']"))
                  .isDisplayed());
    }

    @Test
    public void removeProductFromCart() {
        login();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack"))
              .click();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        Assert.assertEquals(
            driver.findElements(By.className("shopping_cart_badge")).size(),
            0);
    }

    private void login() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
