package ru.netology.sql.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.sql.model.VerificationCode;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id='code'] input");
    private final SelenideElement verificationButton = $("[data-test-id='action-verify']");
    private final SelenideElement codeError = $("[data-test-id='code'] .input__sub");
    private final SelenideElement pageError = $("[data-test-id='error-notification']");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public void codeFieldError(String errorText){
        codeError.shouldBe(visible).shouldHave(text(errorText));
    }

    public void errorNotification(String errorText) {
        pageError.shouldBe(visible).shouldHave(text(errorText));
    }

    public DashboardPage createDashboardPage(VerificationCode code) {
        codeField.setValue(code.getCode());
        verificationButton.click();
        return new DashboardPage();
    }
}