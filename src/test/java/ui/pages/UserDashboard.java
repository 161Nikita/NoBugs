package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import common.helpers.StepLogger;
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
        return StepLogger.log("Открытие главной страницы дашборда и проверка приветствия", () -> {
            super.open();
            welcomeText.shouldBe(Condition.visible)
                    .shouldHave(Condition.text("Welcome, noname!"));
            return this;
        });
    }

    public DepositPage depositMoney() {
        return StepLogger.log("Нажатие на кнопку 'Deposit Money' и переход к пополнению", () -> {
            depositMoney.click();
            return new DepositPage();
        });
    }

    public TransferPage navigateToTransfer() {
        return StepLogger.log("Нажатие на кнопку 'Make a Transfer' и переход к форме перевода денег", () -> {
            $(Selectors.withText("Make a Transfer"))
                    .shouldBe(Condition.visible) // Проверяем видимость внутри шага
                    .click();                     // Кликаем внутри шага
            return new TransferPage();            // Переходим на страницу перевода
        });
    }

        // гасим два автоматических бажных алерта, которые мешают работе со страницей
       /* com.codeborne.selenide.Selenide.confirm("❌ Unable to fetch transactions. Please try again.");
        com.codeborne.selenide.Selenide.confirm("❌ Unable to fetch transactions. Please try again.");*/

       // return new TransferPage(); // Переход на страницу перевода денег

    public EditProfilePage navigateToEditProfile() {
        return StepLogger.log("Клик по профилю пользователя и переход на страницу редактирования", () -> {
            $(Selectors.byClassName("user-info"))
                    .shouldBe(com.codeborne.selenide.Condition.visible)
                    .click();
            return new EditProfilePage();
        });
    }
    public UserDashboard verifyUpdatedName(String expectedName) {
        return StepLogger.log("Проверка измененного имени в приветствии на дашборде", () -> {
            Selenide.refresh();

            $(".welcome-text span")
                    .shouldBe(Condition.visible)

                    .shouldHave(Condition.exactText(expectedName));
            return this;
        });
    }

}
