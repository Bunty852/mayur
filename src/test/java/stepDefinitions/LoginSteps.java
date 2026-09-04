package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        
        // Assertion 1: Verify the page title to ensure correct page is loaded
        String actualTitle = driver.getTitle();
        Assert.assertEquals("Page title mismatch!", "Test Login | Practice Test Automation", actualTitle);
    }

    @When("User enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        
        // Assertion 2: Verify the input fields correctly accepted the typed values
        Assert.assertEquals("Username field value mismatch!", username, usernameField.getAttribute("value"));
        Assert.assertEquals("Password field value mismatch!", password, passwordField.getAttribute("value"));
    }

    @And("User clicks the submit button")
    public void user_clicks_submit() {
        WebElement submitButton = driver.findElement(By.id("submit"));
        
        // Assertion 3: Verify submit button is enabled before clicking
        Assert.assertTrue("Submit button is not enabled!", submitButton.isEnabled());
        submitButton.click();
    }

    @Then("User should be redirected to the success page containing {string}")
    public void verify_redirection(String expectedUrlFragment) {
        String currentUrl = driver.getCurrentUrl();
        
        // Assertion 4: Verify URL redirection contains the expected slug
        Assert.assertTrue("URL redirection failed! Expected fragment: " + expectedUrlFragment + " but found: " + currentUrl, 
                currentUrl.contains(expectedUrlFragment));
        
        // Assertion 5: Verify the success header text on the landing page
        String successHeader = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals("Header text mismatch on success page!", "Logged In Successfully", successHeader);
        System.out.println("Rahul");
    }

    @And("The logout button should be displayed")
    public void verify_logout_button() {
        WebElement logoutButton = driver.findElement(By.linkText("Log out"));
        
        // Assertion 6: Verify logout button visibility
        Assert.assertTrue("Logout button is not displayed on the screen!", logoutButton.isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}