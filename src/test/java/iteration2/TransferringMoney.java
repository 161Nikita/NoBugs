package iteration2;

import constants.ErrorMessages;
import generators.RandomData;
import models.*;
import org.apache.http.HttpHeaders;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import requests.*;
import specs.RequestSpecs;
import specs.ResponseSpecs;


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
        double transferAmount = initialAmount / 2;
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
        float expectedSenderBalance = (float) (Math.round((initialAmount - transferAmount) * 100.0) / 100.0);
        float expectedReceiverBalance = (float) (Math.round(transferAmount * 100.0) / 100.0);

        new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .body("find { it.id == " + senderAccountId + " }.balance", Matchers.equalTo(expectedSenderBalance))
                .body("find { it.id == " + receiverAccountId + " }.balance", Matchers.equalTo(expectedReceiverBalance));
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
        double chunk = transferAmountOverLimit / 3.0;
        // переменная для хранения баланса

        // пополнение своего счета № 1
        UserTopUpAccountRequester topUp = new UserTopUpAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK());
        // создаем массив из 1 элемента
        float[] actualSenderBalance = new float[1];

        // вызываем метод repeat, внутри извлекаем баланс в массив
        repeat(3, () -> {
            actualSenderBalance[0] = topUp.post(UserTopUpAccountRequest.builder().accountId(senderAccountId)
                            .amount(chunk).build())
                    .extract()
                    .path("balance");
        });

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
        new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .body("find { it.id == " + senderAccountId + " }.balance",
                        Matchers.equalTo((float) (Math.round(actualSenderBalance[0] * 100.0) / 100.0)));

        new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .body("find { it.id == " + receiverAccountId + " }.balance", Matchers.equalTo(0.0f));
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
        float expectedSenderBalance = (float) (Math.round(initialAmount * 100.0) / 100.0);
        // Проверяем через GET-реквестер, что после ошибки нехватки средств балансы НЕ изменились
        new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .body("find { it.id == " + senderAccountId + " }.balance", Matchers.equalTo(expectedSenderBalance));

        new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .body("find { it.id == " + receiverAccountId + " }.balance", Matchers.equalTo(0.0f));
    }
    private void repeat(int times, Runnable action) {
        for (int i = 0; i < times; i++) {
            action.run();
        }
    }
}
