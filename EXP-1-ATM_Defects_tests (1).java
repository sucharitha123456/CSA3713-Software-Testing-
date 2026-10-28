import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Q1_ATMDefectTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(System.getProperty(
            "app.url", "http://localhost:8080/atm"));
    }

    @Test
    public void invalidPinShouldBeRejected() {
        driver.findElement(By.id("account")).sendKeys("10001");
        driver.findElement(By.id("pin")).sendKeys("9999");
        driver.findElement(By.id("login")).click();

        Assert.assertTrue(
            driver.findElement(By.id("error")).isDisplayed(),
            "Defect: ATM accepts an invalid PIN");
    }

    @Test
    public void balanceShouldBeDisplayed() {
        driver.findElement(By.id("account")).sendKeys("10001");
        driver.findElement(By.id("pin")).sendKeys("1234");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("balance")).click();

        Assert.assertTrue(
            driver.findElement(By.id("balanceAmount"))
                  .isDisplayed(),
            "Balance enquiry failed");
    }

    @Test
    public void insufficientFundsShouldBeRejected() {
        driver.findElement(By.id("account")).sendKeys("10001");
        driver.findElement(By.id("pin")).sendKeys("1234");
        driver.findElement(By.id("login")).click();

        driver.findElement(By.id("withdraw")).click();
        driver.findElement(By.id("amount")).sendKeys("99999999");
        driver.findElement(By.id("confirm")).click();

        Assert.assertTrue(
            driver.findElement(By.id("error")).isDisplayed(),
            "Defect: Withdrawal exceeding balance was accepted");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
