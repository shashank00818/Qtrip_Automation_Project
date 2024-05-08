package Qtrip.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Qtrip.SeleniumWrapper;

public class LoginPage {

    private final static String LOGIN_PAGE_IDENTIFIER = "/pages/login/";
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    @FindBy(name = "email")
    private WebElement emailTextBox;

    @FindBy(name = "password")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[text()='Login to QTrip']")
    private WebElement loginButton;

    public boolean checkLoginPageNavigation(){
        return driver.getCurrentUrl().contains(LOGIN_PAGE_IDENTIFIER);
    }

    public void performLoginForNewUser(String username, String password) throws InterruptedException{
        if(username == null || username.isEmpty()){
            username=RegisterPage.USER_EMAIL;
        }
        performLoginForExistingUser(username, password);
    }

    public void performLoginForExistingUser(String userName, String password) throws InterruptedException{
        // emailTextBox.sendKeys(userName);
        SeleniumWrapper.sendKeys(emailTextBox, userName);
        Thread.sleep(1000);
        // passwordTextBox.sendKeys(password);
        SeleniumWrapper.sendKeys(passwordTextBox, password);
        Thread.sleep(1000);
        // loginButton.click();
        SeleniumWrapper.click(loginButton, driver);
        Thread.sleep(3000);
    }

}
