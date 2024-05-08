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
import Qtrip.pages.AdventureDetailsPage;
import Qtrip.pages.AdventurePage;
import Qtrip.pages.HistoryPage;
import Qtrip.pages.HomePage;
import Qtrip.pages.LoginPage;
import Qtrip.pages.RegisterPage;

public class testCase_03 {

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


    @Test(description = "Test Case 3",   dataProvider = "testdata", dataProviderClass = DP.class, priority = 3, groups = {"Booking and Cancellation Flow"})
    public void TestCase03(String username, String password, String CityName, String Adventure, String guest, String date, String count) throws InterruptedException{
        softAssert=new SoftAssert();

        HomePage homePage=new HomePage(driver);
        homePage.navigateToHome();

        RegisterPage registerPage=new RegisterPage(driver);
        registerPage.navigateToRegisterPage();
        registerPage.registerANewUser(username, password, password, true);
        username=RegisterPage.USER_EMAIL;
        LoginPage loginPage=new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.performLoginForNewUser(username, password);

        softAssert.assertTrue(homePage.city_Found(CityName), "City not found");
        Thread.sleep(1000);
        AdventurePage adventurePage = new AdventurePage(driver);
        adventurePage.enter_adventure(Adventure);
        Thread.sleep(1000);
        adventurePage.select_adventure(Adventure);
        Thread.sleep(3000);

        AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);
        adventureDetailsPage.booking(guest, date, count);
        Thread.sleep(3000);
        softAssert.assertTrue(adventureDetailsPage.bookingconfirmation(), "Booking unsuccessfull");

        HistoryPage historyPage = new HistoryPage(driver);
        historyPage.clickHere();
        Thread.sleep(2000);
        historyPage.cancelReservation();
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(3000);
        softAssert.assertTrue(historyPage.verifycancelReservation(), "Booking not cancelled");
        Thread.sleep(2000);
        homePage.selectOptionsOnHomePage("logout");
        test.log(LogStatus.INFO, test.addScreenCapture(SeleniumWrapper.takeScreenshot(driver, "PASS", "Login")));
    }

    @AfterSuite
    public static void quitDriver() {
        driver.quit();
    }
}
