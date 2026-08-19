package iteration2.api;

import constants.ErrorMessages;
import generators.RandomData;
import iteration2.BaseTest;
import iteration2.api.comparison.DaoAndModelAssertions;
import iteration2.api.dao.AccountDao;
import iteration2.api.dao.UserDao;
import models.CreateAccountResponse;
import models.CreateUserRequest;
import models.UserTransferAccountRequest;
import models.UserTransferAccountResponse;
import models.comparison.ModelAssertions;
import models.comparison.ModelComparator;
import org.junit.jupiter.api.Test;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import requests.skelethon.steps.DataBaseSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;
import utils.APIVersion;

import java.util.List;


public class TransferringMoney extends BaseTest {

    @Test
    @APIVersion("with_database")
    public void SuccessfulTransferOfFundsBetweenYourOwnAccounts() {
        CreateUserRequest user = createAndAuthorizeUser();

        // Фиксируем создание пользователя
        UserDao userDaoBefore = DataBaseSteps.getUserByUsername(user.getUsername());
        softly.assertThat(userDaoBefore)
                .as("Проверка в Postgres: Пользователь успешно создан")
                .isNotNull();

        CreateAccountResponse senderAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post();
        long senderAccountId = senderAccount.getId();

        // Фиксируем создание счета отправителя
        AccountDao senderDbBefore = DataBaseSteps.getAccountById(senderAccountId);
        softly.assertThat(senderDbBefore)
                .as("Проверка в Postgres: Счет отправителя успешно создан")
                .isNotNull();

        double initialAmount = (Double) ModelComparator.normalizeValue(RandomData.getAmount());
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TOP_UP_ACCOUNT,
                ResponseSpecs.requestReturnsOK()
        ).post(CreateAccountResponse.builder()
                .id(senderAccountId)
                .balance(initialAmount)
                .build());

        CreateAccountResponse receiverAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post();
        long receiverAccountId = receiverAccount.getId();

        //  Фиксируем создание счета получателя
        AccountDao receiverDbBefore = DataBaseSteps.getAccountById(receiverAccountId);
        softly.assertThat(receiverDbBefore)
                .as("Проверка in Postgres: Счет получателя успешно создан")
                .isNotNull();

        double transferAmount = (Double) ModelComparator.normalizeValue(initialAmount / 2);

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
        double expectedSenderBalance = (Double) ModelComparator.normalizeValue(initialAmount - transferAmount);
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

        // проверка через бд
        CreateAccountResponse actualSenderDto = accounts.stream()
                .filter(account -> account.getId() == senderAccountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Счет отправителя не найден"));

        CreateAccountResponse actualReceiverDto = accounts.stream()
                .filter(account -> account.getId() == receiverAccountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Счет получателя не найден"));

        // Запрос в БД для отправителя
        AccountDao senderAccountDb = DataBaseSteps.getAccountById(senderAccountId);

        // Списание денег у отправителя
        softly.assertThat(senderAccountDb.getBalance())
                .as("Проверка в Postgres: Списание средств со счета отправителя выполнено корректно")
                .isEqualTo(expectedSenderBalance);

        DaoAndModelAssertions.assertThat(actualSenderDto, senderAccountDb).match();

        // Запрос в БД для получателя
        AccountDao receiverAccountDb = DataBaseSteps.getAccountById(receiverAccountId);

        // Зачисление денег получателю
        softly.assertThat(receiverAccountDb.getBalance())
                .as("Проверка в Postgres: Зачисление средств на счет получателя выполнено корректно")
                .isEqualTo(expectedReceiverBalance);

        DaoAndModelAssertions.assertThat(actualReceiverDto, receiverAccountDb).match();

        // 1. Удаляем счета
        requests.skelethon.steps.DataBaseSteps.deleteAccountById(senderAccountId);
        requests.skelethon.steps.DataBaseSteps.deleteAccountById(receiverAccountId);

        softly.assertThat(requests.skelethon.steps.DataBaseSteps.getAccountById(senderAccountId))
                .as("Проверка в Postgres: Счет отправителя успешно удален")
                .isNull();
        softly.assertThat(requests.skelethon.steps.DataBaseSteps.getAccountById(receiverAccountId))
                .as("Проверка в Postgres: Счет получателя успешно удален")
                .isNull();

        // 2. Удаляем пользователя
        requests.skelethon.steps.DataBaseSteps.deleteUserByUsername(user.getUsername());
        softly.assertThat(requests.skelethon.steps.DataBaseSteps.getUserByUsername(user.getUsername()))
                .as("Проверка в Postgres: Пользователь успешно удален")
                .isNull();

        softly.assertAll();
    }


    @Test
    @APIVersion("with_database")
    public void AttemptToTransferAnAmountExceedingTheMaximumLimitOverTest() {
        CreateUserRequest user = createAndAuthorizeUser();
        CreateUserRequest user2 = createAndAuthorizeUser();

        // Убеждаемся, что оба пользователя созданы в Postgres
        softly.assertThat(DataBaseSteps.getUserByUsername(user.getUsername()))
                .as("Проверка в Postgres: Первый пользователь успешно создан")
                .isNotNull();
        softly.assertThat(DataBaseSteps.getUserByUsername(user2.getUsername()))
                .as("Проверка в Postgres: Второй пользователь успешно создан")
                .isNotNull();

        // 1. Создаем счет № 1 - отправитель
        CreateAccountResponse senderAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post();
        long senderAccountId = senderAccount.getId();

        // Проверяем создание счета отправителя
        softly.assertThat(DataBaseSteps.getAccountById(senderAccountId))
                .as("Проверка в Postgres: Счет отправителя успешно создан")
                .isNotNull();

        // Расчет суммы перевода (> 10000) и деление её на 3 равные части
        double transferAmountOverLimit = RandomData.getTransferOverLimit();
        double chunk = (Double) ModelComparator.normalizeValue(transferAmountOverLimit / 3.0);

        // 2. Пополнение своего счета № 1 (копим баланс за 3 захода)
        CrudRequester topUp = new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TOP_UP_ACCOUNT,
                ResponseSpecs.requestReturnsOK());
        java.util.stream.IntStream.range(0, 3).forEach(i ->
                topUp.post(CreateAccountResponse.builder().id(senderAccountId).balance(chunk).build())
        );

        // 3. Создаем счет № 2 - получатель
        CreateAccountResponse receiverAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post();
        long receiverAccountId = receiverAccount.getId();

        //  Проверяем создание счета получателя
        softly.assertThat(DataBaseSteps.getAccountById(receiverAccountId))
                .as("Проверка в Postgres: Счет получателя успешно создан")
                .isNotNull();

        // 4. Попытка перевода чужого счета
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TRANSFER_ACCOUNT,
                ResponseSpecs.requestReturnsPlainBadRequest(ErrorMessages.TRANSFER_EXCEEDS_LIMIT)
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
                        .isCloseTo(expectedSenderBalance, org.assertj.core.data.Offset.offset(0.01)));

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

        // Проверяем баланс отправителя в бд

        // Извлекаем актуальные DTO из ответов API
        CreateAccountResponse actualSenderDto = senderAccounts.stream()
                .filter(account -> account.getId() == senderAccountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Счет отправителя не найден в списке"));

        CreateAccountResponse actualReceiverDto = receiverAccounts.stream()
                .filter(account -> account.getId() == receiverAccountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Счет получателя не найден в списке"));

        // Запрос в БД и комплексное сравнение для отправителя
        AccountDao senderAccountDb = requests.skelethon.steps.DataBaseSteps.getAccountById(senderAccountId);

        // Убеждаемся, что баланс в базе остался прежним
        softly.assertThat(senderAccountDb.getBalance())
                .as("Проверка в Postgres: При ошибке лимита баланс отправителя в БД не изменился")
                .isCloseTo(expectedSenderBalance, org.assertj.core.data.Offset.offset(0.01));

        DaoAndModelAssertions.assertThat(actualSenderDto, senderAccountDb).match();

        // Запрос в БД и комплексное сравнение для получателя
        AccountDao receiverAccountDb = DataBaseSteps.getAccountById(receiverAccountId);

        // Убеждаемся, что баланс получателя остался нулевым
        softly.assertThat(receiverAccountDb.getBalance())
                .as("Проверка в Postgres: При ошибке лимита баланс получателя в БД остался нулевым")
                .isZero();

        DaoAndModelAssertions.assertThat(actualReceiverDto, receiverAccountDb).match();

        // 1. Стираем счета из базы данных
        DataBaseSteps.deleteAccountById(senderAccountId);
        DataBaseSteps.deleteAccountById(receiverAccountId);

        softly.assertThat(DataBaseSteps.getAccountById(senderAccountId))
                .as("Проверка в Postgres: Счет отправителя успешно стерт")
                .isNull();
        softly.assertThat(DataBaseSteps.getAccountById(receiverAccountId))
                .as("Проверка в Postgres: Счет получателя успешно стерт")
                .isNull();

        // 2. Стираем обоих пользователей из базы данных
        DataBaseSteps.deleteUserByUsername(user.getUsername());
        DataBaseSteps.deleteUserByUsername(user2.getUsername());

        softly.assertThat(DataBaseSteps.getUserByUsername(user.getUsername()))
                .as("Проверка в Postgres: Первый пользователь успешно стерт")
                .isNull();
        softly.assertThat(DataBaseSteps.getUserByUsername(user2.getUsername()))
                .as("Проверка в Postgres: Второй пользователь успешно стерт")
                .isNull();

        softly.assertAll();
    }

    @Test
    @APIVersion("with_database")
    public void AttemptToTransferAnAmountExceedingTheSenderIsBalance() {
        CreateUserRequest user = createAndAuthorizeUser();
        CreateUserRequest user2 = createAndAuthorizeUser();

        // Убеждаемся, что оба пользователя успешно созданы в Postgres
        softly.assertThat(DataBaseSteps.getUserByUsername(user.getUsername()))
                .as("Проверка в Postgres: Первый пользователь успешно создан")
                .isNotNull();
        softly.assertThat(DataBaseSteps.getUserByUsername(user2.getUsername()))
                .as("Проверка в Postgres: Второй пользователь успешно создан")
                .isNotNull();

        // 1. Создаем счет № 1 - отправитель
        CreateAccountResponse senderAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post();
        long senderAccountId = senderAccount.getId();

        // Проверяем создание счета отправителя
        softly.assertThat(DataBaseSteps.getAccountById(senderAccountId))
                .as("Проверка в Postgres: Счет отправителя успешно создан")
                .isNotNull();

        // 2. Пополняем счет № 1 на случайную валидную сумму (в пределах лимита 5000)
        double initialAmount = RandomData.getAmount();
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TOP_UP_ACCOUNT,
                ResponseSpecs.requestReturnsOK()
        ).post(CreateAccountResponse.builder()
                .id(senderAccountId)
                .balance(initialAmount)
                .build());

        // 3. Создаем счет № 2 - получатель
        CreateAccountResponse receiverAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post();
        long receiverAccountId = receiverAccount.getId();

        // Проверяем создание счета получателя
        softly.assertThat(DataBaseSteps.getAccountById(receiverAccountId))
                .as("Проверка в Postgres: Счет получателя успешно создан")
                .isNotNull();

        // Расчет невалидной суммы перевода
        double invalidTransferAmount = initialAmount + RandomData.getAmount();

        // Пытаемся выполнить перевод при нехватке средств
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TRANSFER_ACCOUNT,
                ResponseSpecs.requestReturnsPlainBadRequest(ErrorMessages.INSUFFICIENT_FUNDS)
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

        // проверка, что средства получателю не зачислились
        softly.assertThat(receiverAccounts)
                .as("Проверка, что баланс получателя остался нулевым после неудавшегося перевода")
                .filteredOn(account -> account.getId() == receiverAccountId)
                .extracting(CreateAccountResponse::getBalance)
                .allSatisfy(balance -> softly.assertThat(balance).isZero());


        // Извлекаем актуальные DTO из ответов API
        CreateAccountResponse actualSenderDto = senderAccounts.stream()
                .filter(account -> account.getId() == senderAccountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Счет отправителя не найден в списке"));

        CreateAccountResponse actualReceiverDto = receiverAccounts.stream()
                .filter(account -> account.getId() == receiverAccountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Счет получателя не найден в списке"));

        // Запрос в БД для отправителя
        AccountDao senderAccountDb = DataBaseSteps.getAccountById(senderAccountId);

        //  Убеждаемся, что из-за ошибки баланс отправителя в БД остался прежним
        softly.assertThat(senderAccountDb.getBalance())
                .as("Проверка в Postgres: При ошибке нехватки средств баланс отправителя в БД не изменился")
                .isEqualTo(initialAmount);

        DaoAndModelAssertions.assertThat(actualSenderDto, senderAccountDb).match();

        // Запрос в БД для получателя
        AccountDao receiverAccountDb = DataBaseSteps.getAccountById(receiverAccountId);

        // [Убеждаемся, что из-за ошибки баланс получателя в БД остался нулевым
        softly.assertThat(receiverAccountDb.getBalance())
                .as("Проверка в Postgres: При ошибке нехватки средств баланс получателя в БД остался нулевым")
                .isZero();

        DaoAndModelAssertions.assertThat(actualReceiverDto, receiverAccountDb).match();

        // 1. Стираем созданные счета из базы данных
        DataBaseSteps.deleteAccountById(senderAccountId);
        DataBaseSteps.deleteAccountById(receiverAccountId);

        softly.assertThat(DataBaseSteps.getAccountById(senderAccountId))
                .as("Проверка в Postgres: Счет отправителя успешно стерт")
                .isNull();
        softly.assertThat(DataBaseSteps.getAccountById(receiverAccountId))
                .as("Проверка в Postgres: Счет получателя успешно стерт")
                .isNull();

        // 2. Стираем созданных пользователей из базы данных
        DataBaseSteps.deleteUserByUsername(user.getUsername());
        DataBaseSteps.deleteUserByUsername(user2.getUsername());

        softly.assertThat(DataBaseSteps.getUserByUsername(user.getUsername()))
                .as("Проверка в Postgres: Первый пользователь успешно стерт")
                .isNull();
        softly.assertThat(DataBaseSteps.getUserByUsername(user2.getUsername()))
                .as("Проверка в Postgres: Второй пользователь успешно стерт")
                .isNull();

        softly.assertAll();
    }
}