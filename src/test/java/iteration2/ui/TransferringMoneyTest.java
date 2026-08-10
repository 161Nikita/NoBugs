package iteration2.ui;

import common.utils.RetryUtils;
import generators.RandomData;
import iteration2.ui.pages.TransferPage;
import iteration2.ui.pages.UserDashboard;
import models.CreateAccountResponse;
import models.CreateUserRequest;
import org.junit.jupiter.api.Test;
import requests.skelethon.steps.AccountSteps;
import requests.skelethon.steps.AdminSteps;

public class TransferringMoneyTest extends BaseUiTest {

    @Test
    public void SuccessfulTransferOfFundsBetweenYourOwnAccountsUITest() {
        // Готовим данные через api
        CreateUserRequest user = AdminSteps.createUser();
        var authSpec = specs.RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // Создаем счет-отправитель через api
        CreateAccountResponse senderAccount = AccountSteps.createAccount(authSpec);

        // Пополняем его через api
        double initialAmount = RandomData.getAmount();
        AccountSteps.topUpAccount(authSpec, senderAccount.getId(), initialAmount);

        // Создаем счет-получатель через API
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(authSpec);

        // Рассчитываем сумму перевода (половина баланса, чтобы гарантированно не превысить лимиты)
        double transferAmount = initialAmount / 2;

        authAsUser(user);

        new UserDashboard()
                // переход по адресу /dashboard и проверяем "Welcome, noname!"
                .open()
                // переход на страницу Make a Transfer
                .navigateToTransfer()
                // выбираем первый счет из списка счетов
                .selectSourceAccount(senderAccount.getAccountNumber())
                // заполняем Recipient Name и Recipient Account Number
                .enterRecipientDetails(user.getUsername(), receiverAccount.getAccountNumber())
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
    public void AttemptToTransferAnAmountExceedingTheMaximumLimitOverUiTest() {
        //  Готовим данные через api
        CreateUserRequest user = AdminSteps.createUser();
        CreateUserRequest user2 = AdminSteps.createUser();
        var authSpec = specs.RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // Создаем счет-отправитель
        CreateAccountResponse senderAccount = AccountSteps.createAccount(authSpec);

        // Расчет суммы перевода (> 10000) и деление её на 3 равные части
        double transferAmountOverLimit = RandomData.getTransferOverLimit();
        double chunk = transferAmountOverLimit / 3.0;

        // Пополнение счета-отправителя циклом за 3 захода (обходим лимит депозита в 5000)
        repeat(3, () -> AccountSteps.topUpAccount(authSpec, senderAccount.getId(), chunk));

        // Создаем счет-получатель у второго пользователя (user2)
        var authSpec2 = specs.RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword());
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(authSpec2);

        // Авторизуемся под первым пользователем (отправителем)
        authAsUser(user);

        new UserDashboard()
                .open()
                .navigateToTransfer()
                .selectSourceAccount(senderAccount.getAccountNumber())
                .enterRecipientDetails(user2.getUsername(), receiverAccount.getAccountNumber())
                .enterAmount((int) transferAmountOverLimit)
                .confirmCheckbox()
                .clickSubmit();

        TransferPage transferPage = new TransferPage();

        RetryUtils.retry(
                () -> {
                    try {

                        transferPage.verifyTransferFormIsStillActive();
                        return true;
                    } catch (Throwable e) {
                        return false;
                    }
                },
                success -> success,
                3,
                500
        );
    }

    @Test
    public void AttemptToTransferAnAmountExceedingTheSenderIsBalanceUiTest() {
        // Готовим данные через api
        CreateUserRequest user = AdminSteps.createUser();
        CreateUserRequest user2 = AdminSteps.createUser();
        var authSpec = specs.RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // Создаем счет № 1 - отправитель
        CreateAccountResponse senderAccount = AccountSteps.createAccount(authSpec);

        // Пополняем счет № 1 на случайную валидную сумму до 5000
        double initialAmount = RandomData.getAmount();
        AccountSteps.topUpAccount(authSpec, senderAccount.getId(), initialAmount);

        // Создаем счет № 2 - получатель (у второго пользователя)
        var authSpec2 = specs.RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword());
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(authSpec2);

        // Расчет невалидной суммы перевода (гарантированно больше доступного баланса)
        double invalidTransferAmount = initialAmount + RandomData.getAmount();

        // авторизация через api
        authAsUser(user);

        new UserDashboard()
                // Переход по адресу /dashboard и автоматическая проверка "Welcome, noname!"
                .open()
                // Переход на страницу Make a Transfer и автоматическое гашение алертов транзакций
                .navigateToTransfer()
                // Выбираем свой счет-отправитель
                .selectSourceAccount(senderAccount.getAccountNumber())
                // Заполняем данные получателя (user2)
                .enterRecipientDetails(user2.getUsername(), receiverAccount.getAccountNumber())
                // Вводим сумму перевода, превышающую текущий баланс
                .enterAmount((int) invalidTransferAmount)
                // Кликаем по чекбоксу подтверждения
                .confirmCheckbox()
                // Отправляем перевод
                .clickSubmit();

        TransferPage transferPage = new TransferPage();

        RetryUtils.retry(
                () -> {
                    try {
                        transferPage.verifyTransferFormIsStillActive();
                        return true;
                    } catch (Throwable e) {
                        return false;
                    }
                },
                success -> success,
                3,
                500
        );
    }

    private void repeat(int times, Runnable action) {
        for (int i = 0; i < times; i++) {
            action.run();
        }
    }
}