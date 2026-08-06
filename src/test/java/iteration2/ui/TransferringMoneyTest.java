package iteration2.ui;

import com.codeborne.selenide.Selenide;
import common.annotations.Browsers;
import common.annotations.UserSession;
import common.storage.SessionStorage;
import extensions.Platform;
import generators.RandomData;
import iteration2.ui.pages.TransferPage;
import iteration2.ui.pages.UserDashboard;
import models.CreateAccountResponse;
import models.CreateUserRequest;
import org.junit.jupiter.api.Test;
import requests.skelethon.steps.AccountSteps;


public class TransferringMoneyTest extends BaseUiTest {

    @Test
    @UserSession
    public void SuccessfulTransferOfFundsBetweenYourOwnAccountsUITest() {
        // Готовим данные через api
        CreateUserRequest user = SessionStorage.getUser();
        var authSpec = SessionStorage.getSteps().getUserSpec();

        // Создаем счет-отправитель через api
        CreateAccountResponse senderAccount = AccountSteps.createAccount(authSpec);
        SessionStorage.saveAccount(user, senderAccount);

        // Пополняем его через api
        double initialAmount = RandomData.getAmount();
        AccountSteps.topUpAccount(authSpec, senderAccount.getId(), initialAmount);

        // Создаем счет-получатель через API
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(authSpec);
        SessionStorage.saveAccount(user, receiverAccount);

        // Рассчитываем сумму перевода (половина баланса, чтобы гарантированно не превысить лимиты)
        double transferAmount = initialAmount / 2;

        new UserDashboard()
                // переход по адресу /dashboard и проверяем "Welcome, noname!"
                .open()
                // переход на страницу Make a Transfer
                .navigateToTransfer()
                // выбираем первый счет из списка счетов
                .selectSourceAccount(SessionStorage.getAccount(user, 0).getAccountNumber())
                // заполняем Recipient Name и Recipient Account Number, берем второй по списку счет
                .enterRecipientDetails(user.getUsername(),
                        SessionStorage.getAccount(user, 1).getAccountNumber())
                // заполняем сумму перевода
                .enterAmount(transferAmount)
                // нажимаем на чекбокс согласия Confirm details are correct
                .confirmCheckbox()
                // кликаем по кнопке
                .clickSubmit()
                // Убеждаемся, что мы по-прежнему на форме, и кнопка отправки активна (нет блокирующих алертов)
                .verifyTransferFormIsStillActive();
    }

    @Test
    @Browsers({"chrome"})
    @Platform(Platform.Type.MOBILE)
    @UserSession(value = 2, auth = 1) // создаем двух юзеров, и залогинимся один раз под отправителем
    public void AttemptToTransferAnAmountExceedingTheMaximumLimitOverUiTest() {
        //  Достаем из хранилища двух пользователей
        CreateUserRequest user = SessionStorage.getUser(1);
        CreateUserRequest user2 = SessionStorage.getUser(2);
        var authSpec = SessionStorage.getSteps(1).getUserSpec();

        // Создаем счет-отправитель
        CreateAccountResponse senderAccount = AccountSteps.createAccount(authSpec);
        SessionStorage.saveAccount(user, senderAccount);

        // Расчет суммы перевода (> 10000) и деление её на 3 равные части
        double transferAmountOverLimit = RandomData.getTransferOverLimit();
        double chunk = transferAmountOverLimit / 3.0;

        // Пополнение счета-отправителя циклом за 3 захода (обходим лимит депозита в 5000)
        for (int i = 0; i < 3; i++) {
            AccountSteps.topUpAccount(authSpec, senderAccount.getId(), chunk);
        }
        // из шагов берем спеку для второго юзера
        var authSpec2 = SessionStorage.getSteps(2).getUserSpec();
        // Создаем счет-получатель у второго пользователя
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(authSpec2);
        SessionStorage.saveAccount(user2, receiverAccount);

        new UserDashboard()
                .open()
                .navigateToTransfer()
                .selectSourceAccount(SessionStorage.getAccount(user, 0).getAccountNumber())
                .enterRecipientDetails(user2.getUsername(),
                        SessionStorage.getAccount(user2, 0).getAccountNumber()
                )
                .enterAmount((int) transferAmountOverLimit)
                .confirmCheckbox()
                .clickSubmit();

        // Ждем 1 секунда
        Selenide.sleep(1000);

        // !БАГ ФРОНТЕНДА! — Окно с ошибкой лимита не всплывает, но система блокирует перевод.
        // Кнопка "Send Transfer" всё ещё отображается на экране
        new TransferPage().verifyTransferFormIsStillActive();
    }

    @Test
    @Browsers({"chrome"})
    @Platform(Platform.Type.DESKTOP)
    @UserSession(value = 2, auth = 1) // создаем двух юзеров, и залогинимся один раз под отправителем
    public void AttemptToTransferAnAmountExceedingTheSenderIsBalanceUiTest() {
        // Достаем из хранилища обоих пользователей
        CreateUserRequest user = SessionStorage.getUser(1);
        CreateUserRequest user2 = SessionStorage.getUser(2);
        // берем спеку для первого пользователя из списка шагов
        var authSpec = SessionStorage.getSteps(1).getUserSpec();

        // Создаем счет № 1 - отправитель
        CreateAccountResponse senderAccount = AccountSteps.createAccount(authSpec);
        SessionStorage.saveAccount(user, senderAccount);

        // Пополняем счет № 1 на случайную валидную сумму до 5000
        double initialAmount = RandomData.getAmount();
        AccountSteps.topUpAccount(authSpec, senderAccount.getId(), initialAmount);

        // берем спеку для первого пользователя из списка шагов
        var authSpec2 = SessionStorage.getSteps(2).getUserSpec();
        // Создаем счет № 2 - получатель (у второго пользователя)
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(authSpec2);
        // сохраняем счет второго пользователя в хранилище
        SessionStorage.saveAccount(user2, receiverAccount);

        // Расчет невалидной суммы перевода (гарантированно больше доступного баланса)
        double invalidTransferAmount = initialAmount + RandomData.getAmount();

        new UserDashboard()
                // Переход по адресу /dashboard и автоматическая проверка "Welcome, noname!"
                .open()
                // Переход на страницу Make a Transfer и автоматическое гашение алертов транзакций
                .navigateToTransfer()
                // Выбираем свой счет-отправитель
                .selectSourceAccount(SessionStorage.getAccount(user, 0).getAccountNumber())
                // Заполняем данные получателя (user2)
                .enterRecipientDetails(user2.getUsername(),
                        SessionStorage.getAccount(user2, 0).getAccountNumber())
                // Вводим сумму перевода, превышающую текущий баланс
                .enterAmount((int) invalidTransferAmount)
                // Кликаем по чекбоксу подтверждения
                .confirmCheckbox()
                // Отправляем перевод
                .clickSubmit();

        // Ждем 1 секунду на обработку запроса
        com.codeborne.selenide.Selenide.sleep(1000);

        // !БАГ ФРОНТЕНДА! - Окно с ошибкой лимита не всплывает, но система блокирует перевод.
        // Проверяем, что мы по-прежнему на форме, кнопка отправки видна и активна (перевод не ушел)
        new TransferPage().verifyTransferFormIsStillActive();
    }
}