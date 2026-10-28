import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Q10_LibraryTestPlanTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get(System.getProperty(
            "app.url", "http://localhost:8080/library"));
    }

    private void login() {
        driver.findElement(By.id("username")).sendKeys("librarian");
        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();
    }

    @Test
    public void validLibrarianLogin() {
        login();

        Assert.assertTrue(
            driver.findElement(By.id("dashboard")).isDisplayed());
    }

    @Test
    public void searchExistingBook() {
        login();
        driver.findElement(By.id("bookSearch")).sendKeys("Java");
        driver.findElement(By.id("searchButton")).click();

        Assert.assertTrue(
            driver.findElement(By.id("bookResults")).isDisplayed());
    }

    @Test
    public void issueAvailableBook() {
        login();
        driver.findElement(By.id("bookSearch")).sendKeys("Java");
        driver.findElement(By.id("searchButton")).click();
        driver.findElement(By.id("issueBook")).click();

        Assert.assertTrue(
            driver.findElement(By.id("issueSuccess")).isDisplayed());
    }

    @Test
    public void returnIssuedBook() {
        login();
        driver.findElement(By.id("issuedBooks")).click();
        driver.findElement(By.id("returnBook")).click();

        Assert.assertTrue(
            driver.findElement(By.id("returnSuccess")).isDisplayed());
    }

    @Test
    public void rejectInvalidLogin() {
        driver.findElement(By.id("username")).sendKeys("wronguser");
        driver.findElement(By.id("password")).sendKeys("wrongpassword");
        driver.findElement(By.id("login")).click();

        Assert.assertTrue(
            driver.findElement(By.id("error")).isDisplayed());
    }

    @Test
    public void searchNonexistentBook() {
        login();
        driver.findElement(By.id("bookSearch"))
              .sendKeys("BookThatDoesNotExist123");
        driver.findElement(By.id("searchButton")).click();

        Assert.assertTrue(
            driver.findElement(By.id("noResults")).isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
