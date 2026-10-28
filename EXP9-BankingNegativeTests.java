import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Q9_BankingNegativeTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(System.getProperty(
            "app.url", "http://localhost:8080/banking"));
    }

    @Test
    public void rejectInvalidCredentials() {
        driver.findElement(By.id("username")).sendKeys("wronguser");
        driver.findElement(By.id("password")).sendKeys("wrongpassword");
        driver.findElement(By.id("login")).click();

        Assert.assertTrue(
            driver.findElement(By.id("error")).isDisplayed());
    }

    @Test
    public void rejectNegativeTransferAmount() {
        login();
        driver.findElement(By.id("transfer")).click();
        driver.findElement(By.id("recipientAccount"))
              .sendKeys("TEST2002");
        driver.findElement(By.id("amount")).sendKeys("-100");
        driver.findElement(By.id("confirmTransfer")).click();

        Assert.assertTrue(
            driver.findElement(By.id("transferError")).isDisplayed());
    }

    @Test
    public void rejectInsufficientFunds() {
        login();
        driver.findElement(By.id("transfer")).click();
        driver.findElement(By.id("recipientAccount"))
              .sendKeys("TEST2002");
        driver.findElement(By.id("amount")).sendKeys("999999999");
        driver.findElement(By.id("confirmTransfer")).click();

        Assert.assertTrue(
            driver.findElement(By.id("transferError")).isDisplayed());
    }

    @Test
    public void unauthorizedDashboardAccess() {
        driver.get(System.getProperty(
            "app.url", "http://localhost:8080/banking") + "/dashboard");

        Assert.assertFalse(
            driver.getCurrentUrl().endsWith("/dashboard"),
            "Unauthenticated user accessed dashboard");
    }

    private void login() {
        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
