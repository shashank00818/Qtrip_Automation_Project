package Qtrip;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSingleton {
    private static DriverSingleton instanceOfSingletonBrowserClass = null;

    private WebDriver driver;

    private DriverSingleton(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static DriverSingleton getInstanceOfSingletonBrowserClass(){
        if (instanceOfSingletonBrowserClass == null) {
            instanceOfSingletonBrowserClass = new DriverSingleton();
        }
        return instanceOfSingletonBrowserClass;
    }

    public WebDriver getDriver() {
        return driver;
    }
}