package iteration2;

import constants.ErrorMessages;
import generators.MoneyHelper;
import generators.RandomData;
import models.*;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import requests.*;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.Arrays;


public class TransferringMoney extends BaseTest {
    private CreateUserRequest createAndAuthorizeUser() {

        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        LoginUserRequest loginUserRequest = LoginUserRequest.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();

        // создание пользователя
        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        // получаем токен юзера
        new LoginUserRequester(
                RequestSpecs.unauthSpec(),
                ResponseSpecs.requestReturnsOK())
                .post(loginUserRequest)
                .extract()
                .header(HttpHeaders.AUTHORIZATION);

        return userRequest;
    }

    @Test
    public void SuccessfulTransferOfFundsBetweenYourOwnAccounts() {
        CreateUserRequest user = createAndAuthorizeUser();

        // создаем счет № 1 - отправитель
        long senderAccountId = new CreateAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.entityWasCreated())
                .postAndGetBody()
                .getId();

        // пополнение своего счета № 1
        double initialAmount = RandomData.getAmount();
        new UserTopUpAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .post(UserTopUpAccountRequest.builder()
                        .accountId(senderAccountId)
                        .amount(initialAmount)
                        .build());

        // создаем счет № 2 - получатель
        long receiverAccountId = new CreateAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.entityWasCreated())
                .postAndGetBody()
                .getId();

        // пополнение своего счета № 2
        double transferAmount = MoneyHelper.round(initialAmount / 2);
        UserTransferAccountResponse response = new UserTransferAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .postAndGetBody(UserTransferAccountRequest.builder()
                        .senderAccountId(senderAccountId)
                        .receiverAccountId(receiverAccountId)
                        .amount(transferAmount)
                        .build());

        softly.assertThat(response.getSenderAccountId())
                .as("Неверный ID отправителя в ответе")
                .isEqualTo(senderAccountId);

        softly.assertThat(response.getReceiverAccountId())
                .as("Неверный ID получателя в ответе")
                .isEqualTo(receiverAccountId);

        softly.assertThat(response.getAmount())
                .as("Сумма перевода в ответе не совпадает с отправленной")
                .isEqualTo(transferAmount);

        // Проверка с помощью гет, что произошло фактическое изменение балансов
        double expectedSenderBalance = MoneyHelper.round(initialAmount - transferAmount);
        double expectedReceiverBalance = MoneyHelper.round(transferAmount);

        CreateAccountResponse[] accounts = new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(CreateAccountResponse[].class);

        // Находим аккаунт отправителя в массиве через Stream API
        CreateAccountResponse senderAccount = Arrays.stream(accounts)
                .filter(acc -> acc.getId() == senderAccountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Аккаунт отправителя не найден"));

        // Находим аккаунт получателя в массиве через Stream API
        CreateAccountResponse receiverAccount = Arrays.stream(accounts)
                .filter(acc -> acc.getId() == receiverAccountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Аккаунт получателя не найден"));

        // Делаем чистые проверки через softly без использования RestAssured .body()
        softly.assertThat(senderAccount.getBalance())
                .as("Проверка итогового баланса отправителя")
                .isEqualTo(expectedSenderBalance);

        softly.assertThat(receiverAccount.getBalance())
                .as("Проверка итогового баланса получателя")
                .isEqualTo(expectedReceiverBalance);
    }

    @Test
    public void AttemptToTransferAnAmountExceedingTheMaximumLimitOverTest() {
        CreateUserRequest user = createAndAuthorizeUser();
        CreateUserRequest user2 = createAndAuthorizeUser();

        // создаем счет № 1 - отправитель
        long senderAccountId = new CreateAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.entityWasCreated())
                .postAndGetBody()
                .getId();
        // Получаем сумму перевода (> 10000) и делим её на 3 равные части
        double transferAmountOverLimit = RandomData.getTransferOverLimit();
        double chunk = MoneyHelper.round(transferAmountOverLimit / 3.0);
        // переменная для хранения баланса

        // пополнение своего счета № 1
        UserTopUpAccountRequester topUp = new UserTopUpAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK());
        final UserTopUpAccountResponse[] topUpResponseContainer = new UserTopUpAccountResponse[1];

        // вызываем метод repeat, внутри извлекаем баланс в массив
        repeat(3, () -> {
            topUpResponseContainer[0] = topUp.post(UserTopUpAccountRequest.builder()
                            .accountId(senderAccountId)
                            .amount(chunk).build())
                    .extract()
                    .as(UserTopUpAccountResponse.class);
        });

        double actualSenderBalance = topUpResponseContainer[0].getBalance();

        // создаем счет № 2 - получатель
        long receiverAccountId = new CreateAccountRequester(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                ResponseSpecs.entityWasCreated())
                .postAndGetBody()
                .getId();

        // пополнение чужого счета
        new UserTransferAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.TRANSFER_EXCEEDS_LIMIT))
                .post(UserTransferAccountRequest.builder()
                        .senderAccountId(senderAccountId)
                        .receiverAccountId(receiverAccountId)
                        .amount(transferAmountOverLimit)
                        .build());

        // Проверяем через гет, что после ошибки, балансы не изменились
        CreateAccountResponse[] senderAccounts = new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(CreateAccountResponse[].class);

        CreateAccountResponse senderAccount = Arrays.stream(senderAccounts)
                .filter(acc -> acc.getId() == senderAccountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Аккаунт отправителя не найден"));

        softly.assertThat(senderAccount.getBalance())
                .as("Баланс отправителя не должен измениться после неудачного перевода")
                .isEqualTo(MoneyHelper.round(actualSenderBalance));

        CreateAccountResponse[] receiverAccounts = new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(CreateAccountResponse[].class);

        CreateAccountResponse receiverAccount = Arrays.stream(receiverAccounts)
                .filter(acc -> acc.getId() == receiverAccountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Аккаунт получателя не найден"));

        softly.assertThat(receiverAccount.getBalance())
                .as("Баланс получателя должен остаться нулевым")
                .isEqualTo(0.0);
    }

    @Test
    public void AttemptToTransferAnAmountExceedingTheSenderIsBalance() {
        CreateUserRequest user = createAndAuthorizeUser();
        CreateUserRequest user2 = createAndAuthorizeUser();

        // Создаем счет № 1 - отправитель
        long senderAccountId = new CreateAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.entityWasCreated())
                .postAndGetBody()
                .getId();

        // Пополняем счет № 1 на случайную валидную сумму (в пределах лимита 5000)
        double initialAmount = RandomData.getAmount();
        new UserTopUpAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .post(UserTopUpAccountRequest.builder()
                        .accountId(senderAccountId)
                        .amount(initialAmount)
                        .build());

        // Создаем счет № 2 - получатель
        long receiverAccountId = new CreateAccountRequester(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                ResponseSpecs.entityWasCreated())
                .postAndGetBody()
                .getId();
        // Вычисляем заведомо невалидную сумму перевода (больше, чем есть на балансе)
        double invalidTransferAmount = initialAmount + RandomData.getAmount();

        new UserTransferAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.INSUFFICIENT_FUNDS))
                .post(UserTransferAccountRequest.builder()
                        .senderAccountId(senderAccountId)
                        .receiverAccountId(receiverAccountId)
                        .amount(invalidTransferAmount)
                        .build());
        // Округляем исходный баланс до копеек
        double expectedSenderBalance = MoneyHelper.round(initialAmount);
        // Проверяем через GET-реквестер, что после ошибки нехватки средств балансы НЕ изменились
        CreateAccountResponse[] senderAccounts = new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(CreateAccountResponse[].class);

        CreateAccountResponse senderAccount = Arrays.stream(senderAccounts)
                .filter(acc -> acc.getId() == senderAccountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Аккаунт отправителя не найден"));

        softly.assertThat(senderAccount.getBalance())
                .as("После ошибки нехватки средств баланс отправителя не должен измениться")
                .isEqualTo(expectedSenderBalance);

        CreateAccountResponse[] receiverAccounts = new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(CreateAccountResponse[].class);

        CreateAccountResponse receiverAccount = Arrays.stream(receiverAccounts)
                .filter(acc -> acc.getId() == receiverAccountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Аккаунт получателя не найден"));

        softly.assertThat(receiverAccount.getBalance())
                .as("Баланс получателя должен остаться нулевым")
                .isEqualTo(0.0);
    }

    private void repeat(int times, Runnable action) {
        for (int i = 0; i < times; i++) {
            action.run();
        }
    }
}
