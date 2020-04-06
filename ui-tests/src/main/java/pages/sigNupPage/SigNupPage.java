package pages.sigNupPage;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class SigNupPage {

    public static final String SIGNUP_PAGE_URL = baseUrl + "/site/signup";

    public SelenideElement
            titleSigNupPage = $("h1.page_title"),
            formRegisterSimple = $x(".//a[@href='#register_simple']"),
            emailField = $x(".//input[@id='signupform-email']"),
            lastNameField = $x(".//input[@id='signupform-last_name']"),
            firstNameField = $x(".//input[@id='signupform-first_name']"),
            middleNameField = $x(".//input[@id='signupform-patronymic_name']"),
            positionNameField = $x(".//textarea[@id='signupform-position']"),
            telephoneNumberField = $x(".//input[@id='signupform-phone']"),
            passwordField = $x(".//input[@id='signupform-password']"),
            selectRegulator_Type =
                    $x(".//div[@class='form-group highlight-addon field-signupform-regulator_type required']//span[@class='select2-selection__arrow']"),
            selectRegulator_Id =
                    $x(".//div[@class='form-group highlight-addon field-signupform-regulator_id required']//span[@class='select2-selection__arrow']"),
            selectSphereOfActivity = $(".select2-search__field"),
            buttonNext = $x(".//div[@id='register_simple']//button[@type='button']"),
            titleSuccessfullyRegistered = $(".page_title_successfully");

    public ElementsCollection
            selectResult = $$(".select2-results__option"),
            helpBlockMessages = $$x(".//div[@class='help-block'][contains(text(),'.')]");

    @Step("Open registration page")
    public void openPage() {
        open(SIGNUP_PAGE_URL);
    }

    @Step("View title page")
    public void viewTitlePage() {
        titleSigNupPage.shouldHave(Condition.exactText("Реєстрація"));
    }

    @Step("Checking registration from")
    public void checkRegistrationForm() {
        formRegisterSimple.shouldHave(Condition.exactText("Реєстрація"));
    }

    public void registration(String email,
                             String lastName,
                             String firstName,
                             String middleName,
                             String position,
                             String telephone,
                             String password,
                             String typeOfControlBody,
                             String controllingAuthorityYouRepresent,
                             Boolean categories,
                             String areasOfStateSupervision) {
        emailField.setValue(email);
        lastNameField.setValue(lastName);
        firstNameField.setValue(firstName);
        middleNameField.setValue(middleName);
        positionNameField.setValue(position);
        telephoneNumberField.setValue(telephone);
        passwordField.setValue(password);
        selectRegulator_Type.shouldHave(Condition.exist).click();
        selectResult.find(Condition.text(typeOfControlBody)).click();
        selectRegulator_Id.shouldHave(Condition.exist).click();
        selectResult.find(Condition.text(controllingAuthorityYouRepresent)).click();
        selectSphereOfActivity.shouldHave(Condition.exist).click();
        if (!categories) {
            selectResult.shouldBe(CollectionCondition.exactTexts(areasOfStateSupervision));
        }
        else {
            selectResult.find(Condition.text(areasOfStateSupervision)).click();
        }
    }

    @Step("Click button - Next")
    public void clickButtonNext() {
        buttonNext.click();
    }

    @Step("Checking successfully registered user")
    public void checkRegistrationUser(String title) {
        titleSuccessfullyRegistered.shouldHave(Condition.text(title));
    }

    @Step("Checking display prompt messages")
    public void checkingDisplayPromptMessages(String prompts) {
        helpBlockMessages.shouldHaveSize(8);
        helpBlockMessages.find(Condition.exactText(prompts));
    }
}
