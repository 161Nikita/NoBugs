package iteration2;

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

import java.util.List;
import java.util.stream.IntStream;


public class TransferringMoney extends BaseTest {

    @Test
    public void SuccessfulTransferOfFundsBetweenYourOwnAccounts() {
        CreateUserRequest user = createAndAuthorizeUser();

        CreateAccountResponse senderAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post();
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
        ).post();
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
        // проверка через гет
        List<CreateAccountResponse> accounts = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.CUSTOMER_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()
        ).getList();
        // округляем до копеек
        double expectedSenderBalance = Math.round((initialAmount - transferAmount) * 100.0) / 100.0;
        double expectedReceiverBalance = Math.round(transferAmount * 100.0) / 100.0;
        // проверяем актуальный баланс счёта отправителя
        softly.assertThat(accounts)
                .as("Проверка актуального баланса счёта отправителя после перевода")
                .filteredOn(account -> account.getId() == senderAccountId)
                .extracting(CreateAccountResponse::getBalance)
                .containsExactly(expectedSenderBalance);
        // проверяем актуальный баланс счёта получателя
        softly.assertThat(accounts)
                .as("Проверка актуального баланса счёта получателя после перевода")
                .filteredOn(account -> account.getId() == receiverAccountId)
                .extracting(CreateAccountResponse::getBalance)
                .containsExactly(expectedReceiverBalance);

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
        ).post();
        long senderAccountId = senderAccount.getId();

        // Расчет суммы перевода (> 10000) и деление её на 3 равные части
        double transferAmountOverLimit = RandomData.getTransferOverLimit();
        double chunk = Math.round((transferAmountOverLimit / 3.0) * 100.0) / 100.0;

        // 2. Пополнение своего счета № 1 (копим баланс за 3 захода)
        CrudRequester topUp = new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TOP_UP_ACCOUNT,
                ResponseSpecs.requestReturnsOK());
        IntStream.range(0, 3).forEach(i ->
                topUp.post(UserTopUpAccountRequest.builder().accountId(senderAccountId).amount(chunk).build())
        );

        // 3. Создаем счет № 2 - получатель
        CreateAccountResponse receiverAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post();
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

        List<CreateAccountResponse> senderAccounts = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.CUSTOMER_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()
        ).getList();

        // Округляем до копеек
        double expectedSenderBalance = Math.round((chunk * 3) * 100.0) / 100.0;

        softly.assertThat(senderAccounts)
                .as("Проверка, что баланс отправителя не изменился после неудавшегося перевода")
                .filteredOn(account -> account.getId() == senderAccountId)
                .extracting(CreateAccountResponse::getBalance)
                .containsExactly(expectedSenderBalance);

        // проверка, что баланс получателя не изменился, остался равен 0.0
        List<CreateAccountResponse> receiverAccounts = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                Endpoint.CUSTOMER_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()
        ).getList();

        double expectedReceiverBalance = 0.0;

        softly.assertThat(receiverAccounts)
                .as("Проверка, что баланс получателя остался нулевым после неудавшегося перевода")
                .filteredOn(account -> account.getId() == receiverAccountId)
                .extracting(CreateAccountResponse::getBalance)
                .containsExactly(expectedReceiverBalance);
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
        ).post();
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
        ).post();
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
        List<CreateAccountResponse> senderAccounts = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.CUSTOMER_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()
        ).getList();

        double expectedSenderBalance = Math.round(initialAmount * 100.0) / 100.0;

        // проверка, что средства не списались
        softly.assertThat(senderAccounts)
                .as("Проверка, что баланс отправителя не изменился после неудавшегося перевода")
                .filteredOn(account -> account.getId() == senderAccountId)
                .extracting(CreateAccountResponse::getBalance)
                .containsExactly(expectedSenderBalance);

        List<CreateAccountResponse> receiverAccounts = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                Endpoint.CUSTOMER_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()
        ).getList();

        // баланс остается 0, т.к. перевод отклонен
        double expectedReceiverBalance = 0.0;

        // проверка, что средтсва получателю не зачислились
        softly.assertThat(receiverAccounts)
                .as("Проверка, что баланс получателя остался нулевым после неудавшегося перевода")
                .filteredOn(account -> account.getId() == receiverAccountId)
                .extracting(CreateAccountResponse::getBalance)
                .containsExactly(expectedReceiverBalance);
    }
}