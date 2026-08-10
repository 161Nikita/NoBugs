package iteration2.ui;

import generators.RandomData;
import iteration2.ui.pages.BankAlert;
import iteration2.ui.pages.UserDashboard;
import models.CreateUserRequest;
import org.junit.jupiter.api.Test;
import requests.skelethon.steps.AccountSteps;
import requests.skelethon.steps.AdminSteps;

public class UserDepositTest extends BaseUiTest {


    @Test
    public void depositTopUpTest() {
        // создаем юзера
        CreateUserRequest user = AdminSteps.createUser();
        // создаем счет
        AccountSteps.createAccount(
                specs.RequestSpecs.authAsUser(user.getUsername(), user.getPassword())
        );
        // авторизация
        authAsUser(user);
        // заходим на страницу и ищем локаторы для текста приветствия и кнопки депозита
        new UserDashboard()
                // переход по адресу /dashboard и проверяем "Welcome, noname!"
                .open()
                // клик по кнопке Deposit Money
                .depositMoney()
                // из выпадающего списка берем первый счет
                .selectFirstAccount()
                // в поле ввода пишем число превышающий лимит
                .enterAmount(RandomData.getAmount())
                // Нажимаем на кнопку Deposit и проверяем успешное пополнение баланса
                // !БАГ! в модальном окне ошибка пополнения
                .clickSubmitAndExpectError(BankAlert.TOP_UP_DEPOSIT_SUCCESSFUL);
    }

    @Test
    public void AttemptToTopUpByAnAmountExceedingTheLimitTest() {
        // создаем юзера
        CreateUserRequest user = AdminSteps.createUser();
        // создаем счет для этого юзера
        requests.skelethon.steps.AccountSteps.createAccount(
                specs.RequestSpecs.authAsUser(user.getUsername(), user.getPassword())
        );
        // авторизация
        authAsUser(user);

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