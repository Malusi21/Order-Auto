package Registration;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class RegistrationSteps {
    WebDriver driver = null;
    JavascriptExecutor js = null;
    Variables var = new Variables();


    private class Variables {
        String MyStor_Home = "http://automationpractice.com";
        String chrome_path = "C:\\Users\\malusi.msomi\\IdeaProjects\\Order-Cucumber\\src\\main\\resources\\drivers\\chromedriver.exe";
        String firefox_path = "C:\\Users\\malusi.msomi\\IdeaProjects\\Order-Cucumber\\src\\main\\resources\\drivers\\geckodriver.exe";
        String Sign_in_button = "//*[@id=\"header\"]//a[contains(.,\"Sign in\")]";
        String Authentication_page = "//*[@id=\"center_column\"]/h1[contains(.,\"Authentication\")]";
        String Create_account_email = "email_create";
        String Create_account_button = "SubmitCreate";
        String email_exists_errormessage = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
        String email_invalid_errormessage = "Invalid email address";
        String email_already_exists = "exists";
        String email_format_invalid = "invalid";
    }
    public void SetChromeBrowserDriver(){
        System.setProperty("webdriver.chrome.driver",var.chrome_path);
        driver = new ChromeDriver();

    }
    public void SetFirefoxBrowserDriver(){
        System.setProperty("webdriver.gecko.driver",var.firefox_path);
        driver = new FirefoxDriver();
    }

    @Before
    public void Load_Browser_and_navigate_to_registration(){

        String projectPath = System.getProperty("user.dir");
        //SetChromeBrowserDriver();
        SetFirefoxBrowserDriver();
        js = (JavascriptExecutor) driver;
        driver.get(var.MyStor_Home);
        //driver.get("http://automationpractice.com");
        //driver.navigate().to("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("page")).isDisplayed();
        verifyHomePageLoad();
        //The_user_enters_an_already_existing_email();
    }
    @Given("The user enters an already existing email")
    public void The_user_enters_an_already_existing_email() {
        // This function validates that the user is redirected to the correct page
        driver.findElement(By.id("header_logo")).isDisplayed();
        driver.findElement(By.className("ajax_cart_no_product")).isDisplayed();
        driver.findElement(By.className("login")).isDisplayed();

    }

    @Given("The user attempts to register with an existing account")
    public void The_user_attempts_to_register_with_an_existing_account(){
        driver.findElement(By.id("header_logo")).isDisplayed();
        driver.findElement(By.xpath(var.Sign_in_button)).isDisplayed();
        driver.findElement(By.xpath(var.Sign_in_button)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath(var.Authentication_page)).isDisplayed();
        //enter_user_email(email);

    }

    @Given("the user enters their {string} and clicks create")
    public void enter_user_email(String email){
        driver.findElement(By.id(var.Create_account_email)).isDisplayed();
        driver.findElement(By.id(var.Create_account_email)).clear();
        driver.findElement(By.id(var.Create_account_email)).click();
        driver.findElement(By.id(var.Create_account_email)).sendKeys(email);
        //driver.findElement(By.id(var.Create_account_label_create)).click();
        driver.findElement(By.id(var.Create_account_button)).click();
        //validate_user_already_exists();
    }

    @And("Error message is displayed on clicking create")
    public void validate_user_already_exists(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.findElement(By.id(var.email_exists_errormessage)).isDisplayed();
        Verify_error_message(var.email_exists_errormessage, var.email_already_exists);
    }

    public void verify_user_email_valid(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Verify_error_message(var.email_invalid_errormessage, var.email_format_invalid);
        //driver.findElement(By.id(var.email_invalid_errormessage)).isDisplayed();
    }

    public void Verify_error_message(String message, String error ){
        String pageSource= driver.getPageSource();

        if (error == "invalid"){
            boolean invalidDisplayed = pageSource.contains(message);
            if (invalidDisplayed){
                System.out.println("Invalid email address entered error message is displayed");
            } else {
                System.out.println("Invalid email address entered error message is not displayed to the user");
            }
        } else if (error == "exists"){
            boolean existsDisplayed = pageSource.contains(message);
            if (existsDisplayed){
                System.out.println("Already exists email address entered error message is displayed");
            } else {
                System.out.println("Already exists email address entered error message is not displayed to the user");
            }
        }

    }

    public void verifyHomePageLoad(){

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = driver.getCurrentUrl();
        UserCurrentPage(url);
    }
    public void UserCurrentPage(String url){
        if(url.equals("http://automationpractice.com/index.php")){
            System.out.println("Correct page is being presented to the user");
        } else {
            System.out.println("User is presented with another page");
        }
    }


}
