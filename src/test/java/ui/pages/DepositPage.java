package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

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
        accountSelector
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("-- Choose an account --"))
                .selectOption(1);
        return this;
    }

    public DepositPage enterAmount(double amount) {
        amountInput.setValue(String.valueOf(amount));
        return this;
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void clickSubmitAndExpectError(BankAlert bankAlert) {
        submitButton.click();
        this.checkAlertMessageAndAccept(bankAlert);
    }
}
