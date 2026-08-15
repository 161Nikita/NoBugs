package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

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
        sourceAccountSelector
                .shouldBe(Condition.visible)
                .selectOptionContainingText(accountNumber);
        return this;
    }

    public TransferPage enterRecipientDetails(String name, String accountNumber) {
        recipientNameInput.setValue(name);
        recipientAccountInput.setValue(accountNumber);
        return this;
    }

    public TransferPage enterAmount(double amount) {
        amountInput.setValue(String.valueOf(amount));
        return this;
    }

    public TransferPage confirmCheckbox() {
        confirmCheckbox.click();
        return this;
    }

    public TransferPage clickSubmit() {
        submitButton.click();
        return this;
    }

    public void verifyTransferFormIsStillActive() {
        submitButton.shouldBe(Condition.visible, Condition.enabled);
    }
}