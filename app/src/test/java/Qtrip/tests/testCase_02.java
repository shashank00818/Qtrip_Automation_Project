package Qtrip.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Qtrip.DP;
import Qtrip.DriverSingleton;
import Qtrip.ReportSingleton;
import Qtrip.SeleniumWrapper;
import Qtrip.pages.AdventurePage;
import Qtrip.pages.HomePage;

public class testCase_02 {

    static WebDriver driver;
    static ExtentReports report;
    static ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public static void createDriver(){
        // Launch Browser using Zalenium
        DriverSingleton sbc1 = DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = sbc1.getDriver();
        ReportSingleton rs1 = ReportSingleton.getInstanceOfSingletonReportClass();
        report = rs1.getReport();
        test = report.startTest("ExtentDemo");
    }

    SoftAssert softAssert;


    @Test(description = "Test Case 2",   dataProvider = "testdata", dataProviderClass = DP.class, priority = 2, groups = {"Search and Filter flow"})
    public void TestCase02(String CityName, String Category_Filter, String DurationFilter, String ExpextedFilteredResults, String ExpectedUnfiltered) throws InterruptedException{
        softAssert=new SoftAssert();

        HomePage homePage=new HomePage(driver);
        homePage.navigateToHome();
        Thread.sleep(1000);
        softAssert.assertTrue(homePage.no_City_Found("Lucknow"), "No city found msg is not displaying");
        Thread.sleep(1000);
        softAssert.assertTrue(homePage.city_Found(CityName), "City not found");
        Thread.sleep(1000);
        AdventurePage adventurePage = new AdventurePage(driver);
        adventurePage.set_hours(DurationFilter);
        Thread.sleep(1000);
        softAssert.assertTrue(adventurePage.total_results_displayed(ExpextedFilteredResults), "hours Filtered results did not match");
        adventurePage.set_category(Category_Filter);
        Thread.sleep(1000);
        softAssert.assertTrue(adventurePage.total_results_displayed(ExpextedFilteredResults), "category Filtered results did not match");
        adventurePage.clear_filters();
        Thread.sleep(1000);
        softAssert.assertTrue(adventurePage.total_results_displayed(ExpectedUnfiltered), "Without Filter results did not match");
        Thread.sleep(1000);
        test.log(LogStatus.INFO, test.addScreenCapture(SeleniumWrapper.takeScreenshot(driver, "PASS", "Login")));
    }

    @AfterSuite
    public static void quitDriver() {
        driver.quit();
    }
    
}
