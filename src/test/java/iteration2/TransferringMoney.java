package iteration2;

import constants.ErrorMessages;
import generators.RandomData;
import models.*;
import models.comparison.ModelAssertions;
import models.comparison.ModelComparator;
import org.assertj.core.data.Offset;
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
        double expectedSenderBalance = initialAmount - transferAmount;
        double expectedReceiverBalance = transferAmount;

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
        double chunk = transferAmountOverLimit / 3.0;

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
        double expectedSenderBalance = (Double) ModelComparator.normalizeValue(chunk * 3);

        softly.assertThat(senderAccounts)
                .as("Проверка, что баланс отправителя не изменился после неудавшегося перевода")
                .filteredOn(account -> account.getId() == senderAccountId)
                .extracting(CreateAccountResponse::getBalance)
                .allSatisfy(balance -> softly.assertThat(balance)
                        .isCloseTo(expectedSenderBalance, Offset.offset(0.01)));

        // проверка, что баланс получателя не изменился, остался равен 0.0
        List<CreateAccountResponse> receiverAccounts = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                Endpoint.CUSTOMER_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()
        ).getList();

        softly.assertThat(receiverAccounts)
                .as("Проверка, что баланс получателя остался нулевым после неудавшегося перевода")
                .filteredOn(account -> account.getId() == receiverAccountId)
                .extracting(CreateAccountResponse::getBalance)
                .allSatisfy(balance -> softly.assertThat(balance).isZero());
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

        softly.assertThat(senderAccounts)
                .as("Проверка, что баланс отправителя не изменился после неудавшегося перевода")
                .filteredOn(account -> account.getId() == senderAccountId)
                .extracting(CreateAccountResponse::getBalance)
                .containsExactly(initialAmount);

        List<CreateAccountResponse> receiverAccounts = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                Endpoint.CUSTOMER_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()
        ).getList();

        // проверка, что средтсва получателю не зачислились
        softly.assertThat(receiverAccounts)
                .as("Проверка, что баланс получателя остался нулевым после неудавшегося перевода")
                .filteredOn(account -> account.getId() == receiverAccountId)
                .extracting(CreateAccountResponse::getBalance)
                .allSatisfy(balance -> softly.assertThat(balance).isZero());
    }
}