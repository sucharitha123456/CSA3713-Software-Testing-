import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Q2_ARMSPositiveTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(System.getProperty(
            "app.url", "http://saveethaarms/arms"));
    }

    @Test
    public void validLogin() {
        driver.findElement(By.id("username")).sendKeys("student01");
        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();

        Assert.assertTrue(
            driver.findElement(By.id("dashboard")).isDisplayed());
    }

    @Test
    public void viewStudentProfile() {
        login();
        driver.findElement(By.id("profile")).click();

        Assert.assertTrue(
            driver.findElement(By.id("studentDetails")).isDisplayed());
    }

    @Test
    public void viewAttendance() {
        login();
        driver.findElement(By.id("attendance")).click();

        Assert.assertTrue(
            driver.findElement(By.id("attendanceRecords")).isDisplayed());
    }

    @Test
    public void viewAcademicRecords() {
        login();
        driver.findElement(By.id("academicRecords")).click();

        Assert.assertTrue(
            driver.findElement(By.id("recordsTable")).isDisplayed());
    }

    private void login() {
        driver.findElement(By.id("username")).sendKeys("student01");
        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
