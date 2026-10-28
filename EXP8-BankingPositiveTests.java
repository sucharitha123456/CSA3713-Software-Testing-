import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Q8_BankingPositiveTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(System.getProperty(
            "app.url", "http://localhost:8080/banking"));
    }

    private void login() {
        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();
    }

    @Test
    public void validBankLogin() {
        login();

        Assert.assertTrue(
            driver.findElement(By.id("dashboard")).isDisplayed());
    }

    @Test
    public void checkAccountBalance() {
        login();
        driver.findElement(By.id("balance")).click();

        Assert.assertTrue(
            driver.findElement(By.id("balanceAmount")).isDisplayed());
    }

    @Test
    public void viewTransactionHistory() {
        login();
        driver.findElement(By.id("transactions")).click();

        Assert.assertTrue(
            driver.findElement(By.id("transactionTable")).isDisplayed());
    }

    @Test
    public void validFundTransfer() {
        login();
        driver.findElement(By.id("transfer")).click();
        driver.findElement(By.id("recipientAccount"))
              .sendKeys("TEST2002");
        driver.findElement(By.id("amount")).sendKeys("100");
        driver.findElement(By.id("confirmTransfer")).click();

        Assert.assertTrue(
            driver.findElement(By.id("transferSuccess")).isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
