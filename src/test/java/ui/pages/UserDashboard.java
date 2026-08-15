package iteration2.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class UserDashboard extends BasePage<UserDashboard> {
    private SelenideElement welcomeText = $(Selectors.byClassName("welcome-text"));
    private SelenideElement depositMoney = $(Selectors.byText("\uD83D\uDCB0 Deposit Money"));

    @Override
    public String url() {
        return "/dashboard";
    }

    @Override
    public UserDashboard open() {
        super.open();

        welcomeText.shouldBe(Condition.visible)
                .shouldHave(Condition.text("Welcome, noname!"));
        return this;
    }

    public DepositPage depositMoney() {
        depositMoney.click();
        return new DepositPage();
    }

    public TransferPage navigateToTransfer() {
        // Находим ссылку/кнопку и кликаем
        $(com.codeborne.selenide.Selectors.withText("Make a Transfer"))
                .shouldBe(com.codeborne.selenide.Condition.visible)
                .click();

        // гасим два автоматических бажных алерта, которые мешают работе со страницей
        com.codeborne.selenide.Selenide.confirm("❌ Unable to fetch transactions. Please try again.");
        com.codeborne.selenide.Selenide.confirm("❌ Unable to fetch transactions. Please try again.");

        return new TransferPage(); // Переход на страницу перевода денег
    }

    public EditProfilePage navigateToEditProfile() {
        $(com.codeborne.selenide.Selectors.byClassName("user-info"))
                .shouldBe(com.codeborne.selenide.Condition.visible)
                .click();
        return new EditProfilePage();
    }
}
