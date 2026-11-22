package ru.netology.sql.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.sql.model.AuthenticationInformation;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginField = $("[data-test-id='login'] input");
    private final SelenideElement passwordField = $("[data-test-id='password'] input");
    private final SelenideElement loginButton = $("[data-test-id='action-login']");
    private final SelenideElement pageError = $("[data-test-id='error-notification']");
    private final SelenideElement loginError = $("[data-test-id='login'] .input__sub");
    private final SelenideElement passwordError = $("[data-test-id='password'] .input__sub");

    public LoginPage() {
        loginField.shouldBe(visible);
    }

    public void loginFieldError(String errorText){
        loginError.shouldBe(visible).shouldHave(text(errorText));
    }

    public void passwordFieldError(String errorText){
        passwordError.shouldBe(visible).shouldHave(text(errorText));
    }

    public void errorNotification(String errorText) {
        pageError.shouldBe(visible).shouldHave(text(errorText));
    }

    public void login(AuthenticationInformation information){
        loginField.setValue(information.getLogin());
        passwordField.setValue(information.getPassword());
        loginButton.click();
    }

    public VerificationPage createVerificationPage(AuthenticationInformation information) {
        loginField.setValue(information.getLogin());
        passwordField.setValue(information.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}