package iteration2.api;

import constants.ErrorMessages;
import generators.RandomData;
import models.*;
import models.comparison.ModelAssertions;
import org.junit.jupiter.api.Test;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;


public class TransferringMoney extends BaseTest {

    @Test
    public void SuccessfulTransferOfFundsBetweenYourOwnAccounts() {
        CreateUserRequest user = createAndAuthorizeUser();

        CreateAccountResponse senderAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post(null);
        long senderAccountId = senderAccount.getId();

        double initialAmount = RandomData.getAmount();
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TOP_UP_ACCOUNT,
                ResponseSpecs.requestReturnsOK()
        ).post(UserTopUpAccountRequest.builder()
                .accountId(senderAccountId)
                .amount(initialAmount)
                .build());

        CreateAccountResponse receiverAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post(null);
        long receiverAccountId = receiverAccount.getId();

        double transferAmount = initialAmount / 2;

        UserTransferAccountRequest request = UserTransferAccountRequest.builder()
                .senderAccountId(senderAccountId)
                .receiverAccountId(receiverAccountId)
                .amount(transferAmount)
                .build();

        UserTransferAccountResponse response = new ValidatedCrudRequester<UserTransferAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TRANSFER_ACCOUNT,
                ResponseSpecs.requestReturnsOK()
        ).post(request);

        // модель сравнения
        ModelAssertions.assertThatModels(request, response).match();

    }

    @Test
    public void AttemptToTransferAnAmountExceedingTheMaximumLimitOverTest() {
        CreateUserRequest user = createAndAuthorizeUser();
        CreateUserRequest user2 = createAndAuthorizeUser();

        // 1. Создаем счет № 1 - отправитель
        CreateAccountResponse senderAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post(null);
        long senderAccountId = senderAccount.getId();

        // Расчет суммы перевода (> 10000) и деление её на 3 равные части
        double transferAmountOverLimit = RandomData.getTransferOverLimit();
        double chunk = transferAmountOverLimit / 3.0;

        // 2. Пополнение своего счета № 1 (копим баланс за 3 захода)
        CrudRequester topUp = new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TOP_UP_ACCOUNT,
                ResponseSpecs.requestReturnsOK());
        for (int i = 0; i < 3; i++) {
            topUp.post(UserTopUpAccountRequest.builder().accountId(senderAccountId).amount(chunk).build());
        }

        // 3. Создаем счет № 2 - получатель
        CreateAccountResponse receiverAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post(null);
        long receiverAccountId = receiverAccount.getId();

        // 4. Попытка перевода чужого счета
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TRANSFER_ACCOUNT,
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.TRANSFER_EXCEEDS_LIMIT)
        ).post(UserTransferAccountRequest.builder()
                .senderAccountId(senderAccountId)
                .receiverAccountId(receiverAccountId)
                .amount(transferAmountOverLimit)
                .build());
    }

    @Test
    public void AttemptToTransferAnAmountExceedingTheSenderIsBalance() {
        CreateUserRequest user = createAndAuthorizeUser();
        CreateUserRequest user2 = createAndAuthorizeUser();

        // 1. Создаем счет № 1 - отправитель
        CreateAccountResponse senderAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post(null);
        long senderAccountId = senderAccount.getId();

        // 2. Пополняем счет № 1 на случайную валидную сумму (в пределах лимита 5000)
        double initialAmount = RandomData.getAmount();
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TOP_UP_ACCOUNT,
                ResponseSpecs.requestReturnsOK()
        ).post(UserTopUpAccountRequest.builder()
                .accountId(senderAccountId)
                .amount(initialAmount)
                .build());

        // 3. Создаем счет № 2 - получатель
        CreateAccountResponse receiverAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post(null);
        long receiverAccountId = receiverAccount.getId();

        // Расчет невалидной суммы перевода
        double invalidTransferAmount = initialAmount + RandomData.getAmount();

        // Пытаемся выполнить перевод при нехватке средств
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TRANSFER_ACCOUNT,
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.INSUFFICIENT_FUNDS)
        ).post(UserTransferAccountRequest.builder()
                .senderAccountId(senderAccountId)
                .receiverAccountId(receiverAccountId)
                .amount(invalidTransferAmount)
                .build());
    }
}
