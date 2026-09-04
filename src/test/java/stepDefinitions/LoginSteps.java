package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

import java.time.Duration;

public class LoginSteps {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Given("User navigates to the login page")
    public void user_navigates_to_login_page() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @When("User enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("User clicks the submit button")
    public void user_clicks_submit() {
        driver.findElement(By.id("submit")).click();
    }

    @Then("User should be redirected to the success page containing {string}")
    public void verify_redirection(String expectedUrlFragment) {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue("Redirection failed!", currentUrl.contains(expectedUrlFragment));
    }

    @And("The logout button should be displayed")
    public void verify_logout_button() {
        boolean isLogoutVisible = driver.findElement(By.linkText("Log out")).isDisplayed();
        Assert.assertTrue("Logout button is missing", isLogoutVisible);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}