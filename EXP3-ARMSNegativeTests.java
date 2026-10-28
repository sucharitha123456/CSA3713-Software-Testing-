import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Q3_ARMSNegativeTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(System.getProperty(
            "app.url", "http://saveethaarms.com/arms"));
    }

    @Test
    public void incorrectPassword() {
        driver.findElement(By.id("username")).sendKeys("student01");
        driver.findElement(By.id("password")).sendKeys("WrongPassword");
        driver.findElement(By.id("login")).click();

        Assert.assertTrue(
            driver.findElement(By.id("error")).isDisplayed());
    }

    @Test
    public void emptyCredentials() {
        driver.findElement(By.id("login")).click();

        Assert.assertTrue(
            driver.findElement(By.id("error")).isDisplayed()
            || driver.findElement(By.id("username"))
                     .getAttribute("required") != null);
    }

    @Test
    public void unauthorizedAccess() {
        driver.get(System.getProperty(
            "app.url", "http://vst")
            + "/admin");

        Assert.assertFalse(
            driver.getCurrentUrl().endsWith("/admin"),
            "Unauthorized user reached admin page");
    }

    @Test
    public void invalidStudentId() {
        driver.findElement(By.id("studentId")).sendKeys("INVALID-ID");
        driver.findElement(By.id("search")).click();

        Assert.assertTrue(
            driver.findElement(By.id("error")).isDisplayed()
            || driver.findElement(By.id("noRecords")).isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
