package utils.Listeners;

import com.codeborne.selenide.logevents.SimpleReport;
import org.testng.ITestResult;
import org.testng.TestNG;

public class TextReport extends TestNG.ExitCodeListener {

    protected SimpleReport report = new SimpleReport();


    @Override
    public void onTestStart(ITestResult testResult){
        report.start();

    }



    @Override
    public void onTestFailure(ITestResult testResult){
        report.finish(testResult.getName());


    }

    @Override
    public void onTestSuccess(ITestResult testResult){
        report.finish(testResult.getName());
    }

}