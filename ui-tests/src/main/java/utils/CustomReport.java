package utils;

import com.codeborne.selenide.logevents.EventsCollector;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.logevents.SimpleReport;

import org.apache.log4j.Logger;

public class CustomReport extends SimpleReport {

    private static final Logger log = Logger.getLogger("rootLogger");

    private EventsCollector logEventListener;


    public void start() {
        SelenideLogger.addListener("AllureListener", logEventListener = new EventsCollector());
    }

    public void finish(String title){
        SelenideLogger.removeListener("AllureListener");
        log.info("In future add log to Allure");
    }
}

