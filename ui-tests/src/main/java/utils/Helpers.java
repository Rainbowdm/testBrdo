package utils;

import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Helpers {

    private static final Logger log= Logger.getLogger("rootLogger");

    @Attachment(value = "Page screenShot", type = "image/png")
    public static byte[] takeScreenshot(){
        log.info("Taking screenshot to allure report");
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);

    }
}
