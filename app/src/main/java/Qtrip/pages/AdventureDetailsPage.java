package Qtrip.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Qtrip.SeleniumWrapper;

public class AdventureDetailsPage {

    WebDriver driver;

    public AdventureDetailsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name")
    private WebElement cust_name;

    @FindBy(name = "date")
    private WebElement trip_date;

    @FindBy(name = "person")
    private WebElement person_count;

    @FindBy(className = "reserve-button")
    private WebElement reserve_btn;

    @FindBy(id = "reserved-banner")
    private WebElement booking_conf;

    public void booking(String name, String date, String count) throws InterruptedException{
        // cust_name.sendKeys(name);
        SeleniumWrapper.sendKeys(cust_name, name);
        Thread.sleep(1000);
        // trip_date.sendKeys(date);
        SeleniumWrapper.sendKeys(trip_date, date);
        Thread.sleep(1000);
        // person_count.clear();
        // person_count.sendKeys(count);
        SeleniumWrapper.sendKeys(person_count, count);
        Thread.sleep(1000);
        // reserve_btn.click();
        SeleniumWrapper.click(reserve_btn, driver);
        Thread.sleep(3000);
    }

    public boolean bookingconfirmation() throws InterruptedException{
        return booking_conf.isDisplayed();
    }
}
