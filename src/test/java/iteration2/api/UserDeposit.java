package iteration2.api;

import constants.ErrorMessages;
import generators.RandomData;
import iteration2.BaseTest;
import iteration2.api.dao.AccountDao;
import iteration2.api.dao.UserDao;
import models.CreateAccountResponse;
import models.CreateUserRequest;
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


public class UserDeposit extends BaseTest {

    @Test
    @APIVersion("with_database")
    public void depositTopUpTest() {
        CreateUserRequest user = createAndAuthorizeUser();

        long accountId = requests.skelethon.steps.AccountSteps.createAccount(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword())
        ).getId();
        // генерация суммы пополнения
        double depositAmount = RandomData.getAmount();

        // Пополнение своего счета
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TOP_UP_ACCOUNT,
                ResponseSpecs.requestReturnsOK()
        ).post(CreateAccountResponse.builder()
                .id(accountId)
                .balance(depositAmount)
                .build());

        List<CreateAccountResponse> accounts = new ValidatedCrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.CUSTOMER_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()
        ).getList();

        // Округляем отправленную сумму до копеек
        double expectedBalance = (Double) ModelComparator.normalizeValue(depositAmount);

        // проверка коллекции
        softly.assertThat(accounts)
                .as("Проверка, что баланс счета увеличился на сумму депозита")
                .filteredOn(account -> account.getId() == accountId)
                .extracting(CreateAccountResponse::getBalance)
                .containsExactly(expectedBalance);


        // проверка через базу данных
        AccountDao accountDao = DataBaseSteps.getAccountById(accountId);

        // 2. Проверяем баланс в базе данных Postgres
        softly.assertThat(accountDao.getBalance())
                .as("Проверка в Postgres: баланс в базе данных успешно обновился")
                .isEqualTo(expectedBalance);
    }

    @Test
    @APIVersion("with_database")
    public void AttemptToTopUpByAnAmountExceedingTheLimitTest() {
        CreateUserRequest user = createAndAuthorizeUser();

        // создаем счет
        CreateAccountResponse accountResponse = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post();

        long accountId = accountResponse.getId();

        // пополнение своего счета превышающий лимит
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TOP_UP_ACCOUNT,
                ResponseSpecs.requestReturnsPlainBadRequest(ErrorMessages.DEPOSIT_EXCEEDS_LIMIT)
        ).post(CreateAccountResponse.builder()
                .id(accountId)
                .balance(RandomData.getAmountOverLimit())
                .build());

        List<CreateAccountResponse> accounts = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.CUSTOMER_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()
        ).getList();

        softly.assertThat(accounts)
                .as("Проверка, что после отклонения операции лимита баланс остался нулевым")
                .filteredOn(account -> account.getId() == accountId)
                .extracting(CreateAccountResponse::getBalance)
                .allSatisfy(balance -> softly.assertThat(balance).isZero());

        // проверка через бд
        iteration2.api.dao.AccountDao accountDao = DataBaseSteps.getAccountById(accountId);
        softly.assertThat(accountDao.getBalance())
                .as("Проверка в Postgres: после ошибки лимита баланс в БД остался нулевым")
                .isZero();
    }

    @Test
    @APIVersion("with_database")
    public void AttemptToTopUpSomeoneElseIsOrANonExistentAccountTest() {

        CreateUserRequest user = createAndAuthorizeUser();
        long nonExistentId = RandomData.getNonExistentAccountId();

        // пополнение несуществующего счета
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TOP_UP_ACCOUNT,
                ResponseSpecs.requestReturnsPlainForbidden(ErrorMessages.UNAUTHORIZED_ACCOUNT_ACCESS)
        ).post(CreateAccountResponse.builder()
                .id(RandomData.getNonExistentAccountId())
                .balance(RandomData.getAmount())
                .build());
        List<CreateAccountResponse> accounts = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.CUSTOMER_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()
        ).getList();
        softly.assertThat(accounts)
                .as("Проверка, что у пользователя нет зарегистрированных счетов в системе")
                .isEmpty();

        // проверка через бд
        UserDao userDao = DataBaseSteps.getUserByUsername(user.getUsername());

        softly.assertThat(userDao.getId())
                .as("Проверка в Postgres: созданный пользователь успешно сохранен в базе данных и имеет ID")
                .isNotNull();

        AccountDao accountDao = DataBaseSteps.getAccountById(nonExistentId);

        softly.assertThat(accountDao)
                .as("Проверка в Postgres: фейковый счет действительно отсутствует в таблице accounts (равен null)")
                .isNull();
    }
}