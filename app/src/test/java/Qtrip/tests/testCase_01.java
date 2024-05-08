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
import Qtrip.pages.HomePage;
import Qtrip.pages.LoginPage;
import Qtrip.pages.RegisterPage;

public class testCase_01 {

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

    // @Test(description = "Normal Data test")
    // public void user_onboarding_flow_test() throws InterruptedException{
    //     softAssert=new SoftAssert();

    //     HomePage homePage=new HomePage(driver);
    //     homePage.navigateToHome();
    //     softAssert.assertTrue(homePage.checkTheNavigationToHomePage(),"Navigation to HomePage is failed");
    //     homePage.selectOptionsOnHomePage("register");

    //     RegisterPage registerPage=new RegisterPage(driver);
    //     registerPage.navigateToRegisterPage();
    //     registerPage.checkRegisterPageNavigation();
    //     registerPage.registerANewUser("", "test123", "test123", true);

    //     LoginPage loginPage=new LoginPage(driver);
    //     loginPage.navigateToLoginPage();
    //     loginPage.checkLoginPageNavigation();
    //     loginPage.performLoginForNewUser("", "test123");

    //     softAssert.assertTrue(homePage.checkUserIsLoggedIn(), "User is unable to login");
    //     homePage.selectOptionsOnHomePage("logout");
    //     softAssert.assertTrue(homePage.checkTheNavigationToHomePage(), "User is unable to logout");
    //     softAssert.assertAll();

    // }


    @Test(description = "Excel Login Data test",   dataProvider = "testdata", dataProviderClass = DP.class, priority = 1, groups = {"Login Flow"})
    public void TestCase01(String username, String password) throws InterruptedException{
        softAssert=new SoftAssert();

        HomePage homePage=new HomePage(driver);
        homePage.navigateToHome();
        softAssert.assertTrue(homePage.checkTheNavigationToHomePage(),"Navigation to HomePage is failed");
        homePage.selectOptionsOnHomePage("register");

        RegisterPage registerPage=new RegisterPage(driver);
        registerPage.navigateToRegisterPage();
        registerPage.checkRegisterPageNavigation();
        registerPage.registerANewUser(username, password, password, true);
        username=RegisterPage.USER_EMAIL;
        LoginPage loginPage=new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.checkLoginPageNavigation();
        loginPage.performLoginForNewUser(username, password);

        softAssert.assertTrue(homePage.checkUserIsLoggedIn(), "User is unable to login");
        homePage.selectOptionsOnHomePage("logout");
        softAssert.assertTrue(homePage.checkTheNavigationToHomePage(), "User is unable to logout");
        softAssert.assertAll();
        test.log(LogStatus.INFO, test.addScreenCapture(SeleniumWrapper.takeScreenshot(driver, "PASS", "Login")));

    }

    @AfterSuite
    public static void quitDriver() {
        report.endTest(test);
        driver.quit();
        report.flush();
    }
}
