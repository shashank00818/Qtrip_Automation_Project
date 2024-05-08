package Qtrip.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Qtrip.SeleniumWrapper;

public class AdventurePage {

    WebDriver driver;

    public AdventurePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "duration-select")
    private WebElement hours;

    @FindBy(id = "category-select")
    private WebElement category;

    @FindBy(xpath = "//div[@onclick='clearDuration(event)']")
    private WebElement clear_hours;

    @FindBy(xpath = "//div[@onclick='clearCategory(event)']")
    private WebElement clear_category;

    @FindBy(xpath = "//div[@class='activity-card-text text-md-center w-100 mt-3']")
    private List<WebElement> search_results;

    @FindBy(id = "search-adventures")
    private WebElement adventureElement;

    public void set_hours(String value) throws InterruptedException{
        Select FilterDropdown = new Select(hours);
        FilterDropdown.selectByVisibleText(value);
        Thread.sleep(1000);
    }

    public void set_category(String value) throws InterruptedException{
        Select FilterDropdown = new Select(category);
        FilterDropdown.selectByVisibleText(value);
        Thread.sleep(1000);
    }

    public void clear_filters() throws InterruptedException{
        // clear_hours.click();
        SeleniumWrapper.click(clear_hours, driver);
        Thread.sleep(1000);
        // clear_category.click();
        SeleniumWrapper.click(clear_category, driver);
        Thread.sleep(1000);
    }

    // public int total_results() throws InterruptedException{
    //     clear_filters();
    //     Thread.sleep(2000);
    //     int results = search_results.size();
    //     return results;
    // }

    // public int total_current_results() throws InterruptedException{
    //     Thread.sleep(2000);
    //     int results = search_results.size();
    //     return results;
    // }

    public boolean total_results_displayed(String expected) throws InterruptedException{
        boolean status = false;
        int current_results = search_results.size();
        int exp_results = Integer.parseInt(expected);
        status = (current_results == exp_results);
        return status; 
    }

    public void enter_adventure(String adventure) throws InterruptedException{
        // adventureElement.sendKeys(adventure);
        SeleniumWrapper.sendKeys(adventureElement, adventure);
        Thread.sleep(2000);
    }

    public void select_adventure(String adventure) throws InterruptedException{
        // driver.findElement(By.xpath("//h5[text()='"+adventure+"']")).click();
        // search_results.get(0).click();
        SeleniumWrapper.click(search_results.get(0), driver);
        Thread.sleep(3000);
    }


}
