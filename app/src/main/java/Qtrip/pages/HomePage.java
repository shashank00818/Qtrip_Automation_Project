package Qtrip.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Qtrip.SeleniumWrapper;

public class HomePage {

    WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    @FindBy(xpath = "//a[text()='Register']")
    private WebElement registerButton;

    @FindBy(xpath = "//a[text()='Login Here']")
    private WebElement loginHereButton;

    @FindBy(xpath = "//a[text()='Home']")
    private WebElement homeButton;

    @FindBy(xpath = "//div[text()='Logout']")
    private WebElement logoutButton;

    public boolean checkTheNavigationToHomePage(){
        return registerButton.isDisplayed() && loginHereButton.isDisplayed();
    }

    public void selectOptionsOnHomePage(String option) throws InterruptedException{
        if(option.equalsIgnoreCase("register")){
            // registerButton.click();
            SeleniumWrapper.click(registerButton, driver);
            Thread.sleep(3000);
        }
        else if(option.equalsIgnoreCase("login")){
            // loginHereButton.click();
            SeleniumWrapper.click(loginHereButton, driver);
            Thread.sleep(3000);
        }
        else if(option.equalsIgnoreCase("logout")){
            // logoutButton.click();
            SeleniumWrapper.click(logoutButton, driver);
            Thread.sleep(3000);
        }
        else{
            // homeButton.click();
            SeleniumWrapper.click(homeButton, driver);
            Thread.sleep(3000);
        }
    }

    public boolean checkUserIsLoggedIn(){
        return !driver.getCurrentUrl().contains("/login") && logoutButton.isDisplayed();
    }



    //Test Case 2

    @FindBy(id = "autocomplete")
    private WebElement searchbox;

    @FindBy(xpath = "//h5[text()='No City found']")
    private WebElement no_City_Found_Element;

    @FindBy(id = "results")
    private WebElement city_found_Element;

    public boolean no_City_Found(String city){
        try {
            return no_City_Found_Element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean city_Found(String city) throws InterruptedException{
        boolean status = false;
        // searchbox.sendKeys(city);
        SeleniumWrapper.sendKeys(searchbox, city);
        Thread.sleep(2000);
        status = city_found_Element.isDisplayed();
        // city_found_Element.click();
        SeleniumWrapper.click(city_found_Element, driver);
        Thread.sleep(2000);
        return status;
    }
    
}
