package utils.Listeners;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.TestNG.ExitCodeListener;
import utils.Helpers;


public class TestListener extends ExitCodeListener {

    //Logger log = Logger.getLogger(getClass().getName());
    //public static final Logger logger = Logger.getLogger(TestListener.class);
    private static final Logger log= Logger.getLogger("rootLogger");


    @Override
    public void onTestStart(ITestResult testResult){
        super.onTestStart(testResult);
        log.info("\"" + testResult.getMethod().getMethodName() + "\"" + " test started________");
    }


    @Override
    public void onTestFailure(ITestResult testResult){
        super.onTestFailure(testResult);
        Helpers.takeScreenshot();
    }

    @Override
    public void onTestSuccess(ITestResult testResult){
        super.onTestSuccess(testResult);
        log.info("\"" + testResult.getMethod().getMethodName() + "\"" + " test finished with success________");
    }
}