package Qtrip;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class SeleniumWrapper {
    
    public static boolean click(WebElement elementToClick, WebDriver driver) throws InterruptedException {
        if (elementToClick.isDisplayed()) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToClick);
                Thread.sleep(2000);
                elementToClick.click();
                return true;        
            } catch (Exception e) {
                return false;
            }
        }
        return false;

    }

    public static boolean sendKeys(WebElement inputBox, String keysToSend) {
        try {
            inputBox.clear();
            inputBox.sendKeys(keysToSend);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean navigate(WebDriver driver, String url) {
        try {
            if (driver.getCurrentUrl().equals(url)) {
                return true;
            } else {
                driver.get(url);
                return driver.getCurrentUrl() == url;
            }
        } catch (Exception e) {
            return false;
        }
    }

     
    public static WebElement findElementWithRetry(WebDriver driver, By by, int retryCount) throws Exception {
        int counter = 0;
        Exception ex = new Exception();
        while (counter <= retryCount) {
            try {
                return driver.findElement(by);
            } catch (Exception e) {
                counter++;
                ex = e;
            }

        }
        throw new Exception(ex.getCause());
    }


   public static String takeScreenshot(WebDriver driver, String screenshotType, String Description) {
        try {
            File theDir = new File("/screenshots");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = java.time.LocalDateTime.now().format(formatter);
            // String timestamp = String.valueOf(java.time.LocalDateTime.now());
            String fileName = String.format("screenshot_%s_%s_%s.png", timestamp, screenshotType, Description);
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("screenshots/" + fileName);
            FileUtils.copyFile(SrcFile, DestFile);
            return DestFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
