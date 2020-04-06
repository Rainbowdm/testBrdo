package baseTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.sigNupPage.SigNupPage;
import utils.Listeners.EventListener;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Configuration.reopenBrowserOnFail;
import static com.codeborne.selenide.Configuration.savePageSource;
import static com.codeborne.selenide.Configuration.screenshots;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    public static SigNupPage signupPage;

    @Step("Configurations browser and run")
    @BeforeMethod
    public void setUp() {
        Configuration.timeout = 10000;
        Configuration.fastSetValue = true;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1600x1050";
        WebDriverManager.chromedriver().version("80.0.3987.106").setup();
        setUpBrowser();
        Configuration.baseUrl = "http://inspections.staging.brdo.com.ua/";
        WebDriverRunner.addListener(new EventListener());
        initPages();
    }

    @Step("Taking a screenshot from FAILURE, SKIP test, and delete all cookies. Set properties close driver instance")
    @AfterMethod
    public void tearDown(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File("./screenshots/" + testResult.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (testResult.getStatus() == ITestResult.SKIP) {
                scrFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(scrFile, new File("./screenshots/" + testResult.getName() + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        WebDriver currentDriver = WebDriverRunner.getWebDriver();

        if (currentDriver.getWindowHandles().size() > 1) {
            switchTo().window(0);
            currentDriver.manage().deleteAllCookies();
            currentDriver.quit();
        } else if (currentDriver.getWindowHandles().size() == 1) {
            currentDriver.manage().deleteAllCookies();
            currentDriver.quit();
        }
    }

    public void setUpBrowser() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        //1-Allow, 2-Block, 0-default
        prefs.put("profile.default_content_setting_values.notifications", 1);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("disable-infobars");
        options.addArguments("--window-size=1600,1050");
        WebDriverRunner.setWebDriver(new ChromeDriver(options));

        screenshots = true;
        savePageSource = true;
        reopenBrowserOnFail = true;
    }

    private void initPages(){
        signupPage = new SigNupPage();
    }
}
