
package Qtrip.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Qtrip.SeleniumWrapper;

public class HistoryPage {

    WebDriver driver;

    public HistoryPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//strong[text()='here']")
    private WebElement booking_redirection;

    @FindBy(xpath = "//*[@id='reservation-table']/tr/th")
    private WebElement transaction_id;

    @FindBy(className = "cancel-button")
    private WebElement cancel_btn;

    @FindBy(xpath = "//th[@scope='row']")
    private List<WebElement> bookings;

    public void clickHere() throws InterruptedException{
        // booking_redirection.click();
        SeleniumWrapper.click(booking_redirection, driver);
    }

    public void cancelReservation() throws InterruptedException{
        // cancel_btn.click();
        SeleniumWrapper.click(cancel_btn, driver);
    }

    public boolean verifycancelReservation(){
        try {
            transaction_id.isDisplayed();
        } catch (Exception e) {
            
            return true;
        }
        return false;
    }

    public boolean total_bookings(){
        int total = bookings.size();
        if(total==3){
            return true;
        }
        return false;
    }
}