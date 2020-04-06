package sigNupTests;

import baseTests.BaseTest;
import com.codeborne.selenide.testng.BrowserPerClass;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Listeners.TestListener;
import utils.Listeners.TextReport;
import utils.Listeners.TextReportAllure;

@Listeners({ScreenShooter.class,
        BrowserPerClass.class,
        TextReport.class,
        TextReportAllure.class,
        TestListener.class})
public class SiNupTest extends BaseTest {

    @Description("This is positive registration user")
    @Test(priority = 1)
    public void testOnPositiveRegistrationUser() {
        signupPage.openPage();
        signupPage.viewTitlePage();
        signupPage.checkRegistrationForm();
        signupPage.registration(
                "test21@gmail.com",
                "тест",
                "тест",
                "тест",
                "testt",
                "0906753256",
                "qwerty",
                "Центральний",
                "Міністерство внутрішніх справ України",
                true,
                "Провадження охоронної діяльності, що підлягає ліцензуванню");
        signupPage.clickButtonNext();
        signupPage.checkRegistrationUser("Ви успішно зареєстровані!");
    }

    @Description("This is negative registration")
    @Test(priority = 2)
    public void testCheckingViewPromptMessagesOnEmptyFields() {
        signupPage.openPage();
        signupPage.viewTitlePage();
        signupPage.checkRegistrationForm();
        signupPage.clickButtonNext();
        signupPage.checkingDisplayPromptMessages("Необхідно заповнити");
    }

    @Description("This is positive registration user 2")
    @Test(priority = 3)
    public void testOnPositiveRegistrationUser2() {
        signupPage.openPage();
        signupPage.viewTitlePage();
        signupPage.checkRegistrationForm();
        signupPage.registration(
                "test22@gmail.com",
                "Віталій",
                "Попов",
                "Володимирович",
                "Юрист",
                "0635748712",
                "vitaluk199021",
                "Місцевий",
                "Відділ містобудування та архітектури виконкому Дружківської міської ради",
                false,
                "Нічого не знайдено");
        signupPage.clickButtonNext();
        signupPage.checkRegistrationUser("Ви успішно зареєстровані!");
    }

}
