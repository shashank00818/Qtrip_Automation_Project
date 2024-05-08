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

public class testCase_04 {

    static WebDriver driver;
    static ExtentReports report;
    static ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() {
        // Launch Browser using Zalenium
        DriverSingleton sbc1 = DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = sbc1.getDriver();
        ReportSingleton rs1 = ReportSingleton.getInstanceOfSingletonReportClass();
        report = rs1.getReport();
        test = report.startTest("ExtentDemo");
    }

    SoftAssert softAssert;


    @Test(description = "Test Case 4",   dataProvider = "testdata", dataProviderClass = DP.class, priority = 4, groups = {"Reliability Flow"})
    public void TestCase04(String username, String password, String dataset1, String dataset2, String dataset3) throws InterruptedException{
        String[] DS1 = dataset1.split(";");String[] DS2 = dataset2.split(";");String[] DS3 = dataset3.split(";");
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
        Thread.sleep(3000);

        homePage.city_Found(DS1[0]);
        AdventurePage adventurePage = new AdventurePage(driver);
        adventurePage.enter_adventure(DS1[1]);
        adventurePage.select_adventure(DS1[1]);
        Thread.sleep(3000);
        AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);
        adventureDetailsPage.booking(DS1[2], DS1[3], DS1[4]);
        Thread.sleep(3000);

        homePage.navigateToHome();
        Thread.sleep(3000);
        homePage.city_Found(DS2[0]);
        adventurePage.enter_adventure(DS2[1]);
        adventurePage.select_adventure(DS2[1]);
        Thread.sleep(3000);
        adventureDetailsPage.booking(DS2[2], DS2[3], DS2[4]);
        Thread.sleep(3000);

        homePage.navigateToHome();
        Thread.sleep(3000);
        homePage.city_Found(DS3[0]);
        adventurePage.enter_adventure(DS3[1]);
        adventurePage.select_adventure(DS3[1]);
        Thread.sleep(3000);
        adventureDetailsPage.booking(DS3[2], DS3[3], DS3[4]);
        Thread.sleep(3000);
        

        HistoryPage historyPage = new HistoryPage(driver);
        historyPage.clickHere();
        Thread.sleep(2000);
        softAssert.assertTrue(historyPage.total_bookings(),"All bookings are not found");
        Thread.sleep(2000);
        homePage.selectOptionsOnHomePage("logout");
        test.log(LogStatus.INFO, test.addScreenCapture(SeleniumWrapper.takeScreenshot(driver, "PASS", "Login")));
    }

    @AfterSuite
    public static void quitDriver() {
        driver.quit();
    }
}
