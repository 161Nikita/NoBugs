package iteration2.ui;

import common.annotations.Browsers;
import common.annotations.UserSession;
import common.storage.SessionStorage;
import extensions.Platform;
import generators.RandomData;
import iteration2.ui.pages.BankAlert;
import iteration2.ui.pages.UserDashboard;
import models.CreateAccountResponse;
import models.CreateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.skelethon.steps.AccountSteps;

@UserSession

public class UserDepositTest extends BaseUiTest {

    @BeforeEach
    public void createAccountPrecondition() {
        // достаем юзера
        CreateUserRequest user = SessionStorage.getUser();

        // создаем счет
        CreateAccountResponse account = AccountSteps.createAccount(SessionStorage.getSteps().getUserSpec());
        SessionStorage.saveAccount(user, account);
    }


    @Test
    public void depositTopUpTest() {
        // заходим на страницу и ищем локаторы для текста приветствия и кнопки депозита
        new UserDashboard()
                // переход по адресу /dashboard и проверяем "Welcome, noname!"
                .open()
                // клик по кнопке Deposit Money
                .depositMoney()
                // из выпадающего списка берем первый счет
                .selectFirstAccount()
                // в поле ввода передаем валидную сумму
                .enterAmount(RandomData.getAmount())
                // Нажимаем на кнопку Deposit и проверяем успешное пополнение баланса
                // !БАГ! в модальном окне ошибка пополнения
                .clickSubmitAndExpectError(BankAlert.TOP_UP_DEPOSIT_SUCCESSFUL);
    }

    @Test
    public void AttemptToTopUpByAnAmountExceedingTheLimitTest() {
        // заходим на страницу и ищем локаторы для текста приветствия и кнопки депозита
        new UserDashboard()
                // переход по адресу /dashboard и проверяем "Welcome, noname!"
                .open()
                // клик по кнопке Deposit Money
                .depositMoney()
                // из выпадающего списка берем первый счет
                .selectFirstAccount()
                // в поле ввода пишем число превышающий лимит
                .enterAmount(RandomData.getAmountOverLimit())
                // нажимаем на кнопку Deposit и видим алерт о превышении лимита
                .clickSubmitAndExpectError(BankAlert.TOP_UP_LIMIT_EXCEEDED);
    }
}