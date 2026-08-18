package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import common.helpers.StepLogger;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage extends BasePage<TransferPage> {
    private final SelenideElement sourceAccountSelector = $(".account-selector");
    private final SelenideElement recipientNameInput = $(Selectors.byAttribute("placeholder", "Enter recipient name"));
    private final SelenideElement recipientAccountInput = $(Selectors.byAttribute("placeholder", "Enter recipient account number"));
    private final SelenideElement amountInput = $(Selectors.byAttribute("placeholder", "Enter amount"));
    private final SelenideElement confirmCheckbox = $(Selectors.byAttribute("id", "confirmCheck"));
    private final SelenideElement submitButton = $("button.green-btn");

    @Override
    public String url() {
        return "/transfer";
    }

    public TransferPage selectSourceAccount(String accountNumber) {
        return StepLogger.log("Выбор счета списания: " + accountNumber, () -> {
            sourceAccountSelector
                    .shouldBe(Condition.visible)
                    .selectOptionContainingText(accountNumber);
            return this;
        });
    }

    public TransferPage enterRecipientDetails(String name, String accountNumber) {
        return StepLogger.log("Ввод данных получателя: " + name + " (Счет: " + accountNumber + ")", () -> {
            recipientNameInput.setValue(name);
            recipientAccountInput.setValue(accountNumber);
            return this;
        });
    }

    public TransferPage enterAmount(double amount) {
        return StepLogger.log("Ввод суммы перевода: " + amount, () -> {
            amountInput.setValue(String.valueOf(amount));
            return this;
        });
    }

    public TransferPage confirmCheckbox() {
        return StepLogger.log("Активация чекбокса подтверждения перевода", () -> {
            confirmCheckbox.click();
            return this;
        });
    }

    public TransferPage clickSubmit() {
        return StepLogger.log("Нажатие кнопки подтверждения перевода 'Transfer'", () -> {
            submitButton.click();
            return this;
        });
    }

    public void verifyTransferFormIsStillActive() {
        StepLogger.log("Проверка, что форма перевода активна и кнопка отправки доступна", () -> {
            submitButton.shouldBe(Condition.visible, Condition.enabled);
            return null;
        });
    }
}