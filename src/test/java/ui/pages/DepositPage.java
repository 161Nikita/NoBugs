package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import common.helpers.StepLogger;

import static com.codeborne.selenide.Selenide.$;

public class DepositPage extends BasePage<DepositPage> {
    private final SelenideElement accountSelector = $(".account-selector");
    private final SelenideElement amountInput = $(Selectors.byAttribute("placeholder", "Enter amount"));
    private final SelenideElement submitButton = $("button.btn-primary");

    @Override
    public String url() {
        return "/deposit";
    }

    public DepositPage selectFirstAccount() {
        return StepLogger.log("Выбор первого доступного счета из списка", () -> {
            accountSelector
                    .shouldBe(Condition.visible)
                    .shouldHave(Condition.text("-- Choose an account --"))
                    .selectOption(1);
            return this;
        });
    }

    public DepositPage enterAmount(double amount) {
        return StepLogger.log("Ввод суммы пополнения: " + amount, () -> {
            amountInput.setValue(String.valueOf(amount));
            return this;
        });
    }

    public void clickSubmit() {
        StepLogger.log("Нажатие кнопки подтверждения депозита", () -> {
            submitButton.click();
            return null;
        });
    }

    public void clickSubmitAndExpectError(BankAlert bankAlert) {
        StepLogger.log("Отправка формы депозита и проверка уведомления: " + bankAlert, () -> {
            submitButton.click();
            this.checkAlertMessageAndAccept(bankAlert);
            return null;
        });
    }
}
