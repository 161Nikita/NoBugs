package iteration2.api;

import constants.ErrorMessages;
import generators.RandomData;
import iteration2.BaseTest;
import iteration2.api.comparison.DaoAndModelAssertions;
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

        // Проверяем, что пользователь успешно СОЗДАН в Postgres
        UserDao userDao = DataBaseSteps.getUserByUsername(user.getUsername());
        softly.assertThat(userDao)
                .as("Проверка в Postgres: Создание пользователя прошло успешно")
                .isNotNull();

        long accountId = requests.skelethon.steps.AccountSteps.createAccount(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword())
        ).getId();
        // Проверяем, что счет успешно СОЗДАН в Postgres
        AccountDao accountAfterCreationDb = DataBaseSteps.getAccountById(accountId);
        softly.assertThat(accountAfterCreationDb)
                .as("Проверка в Postgres: Создание сущности счета прошло успешно")
                .isNotNull();

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

        CreateAccountResponse actualDto = accounts.stream()
                .filter(account -> account.getId() == accountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Аккаунт с ID " + accountId + " не найден в списке"));

        AccountDao accountDao = DataBaseSteps.getAccountById(accountId);
        //Проверяем ОБНОВЛЕНИЕ баланса сущности в БД напрямую по полю
        softly.assertThat(accountDao.getBalance())
                .as("Проверка в Postgres: Обновление баланса счета в БД выполнено корректно")
                .isEqualTo(expectedBalance);

        DaoAndModelAssertions.assertThat(actualDto, accountDao).match();

        //Сначала удаляем зависимые сущности (счет привязан к юзеру, трем сначала его)
        DataBaseSteps.deleteAccountById(accountId);
        AccountDao accountAfterDeleteDb = DataBaseSteps.getAccountById(accountId);
        softly.assertThat(accountAfterDeleteDb)
                .as("Проверка в Postgres: Сущность счета успешно УДАЛЕНА из базы данных (равна null)")
                .isNull();

        // Затем удаляем самого пользователя
        DataBaseSteps.deleteUserByUsername(user.getUsername());
        UserDao userAfterDeleteDb = DataBaseSteps.getUserByUsername(user.getUsername());
        softly.assertThat(userAfterDeleteDb)
                .as("Проверка в Postgres: Сущность пользователя успешно УДАЛЕНА из базы данных (равна null)")
                .isNull();

        softly.assertAll();
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
        // Фиксируем создание счета в БД
        AccountDao accountAfterCreationDb = DataBaseSteps.getAccountById(accountId);
        softly.assertThat(accountAfterCreationDb)
                .as("Проверка в Postgres: Сущность счета успешно создана")
                .isNotNull();
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

        //Находим актуальный DTO из списка accounts (где баланс должен быть нулевым)
        CreateAccountResponse actualDto = accounts.stream()
                .filter(account -> account.getId() == accountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Аккаунт с ID " + accountId + " не найден в списке"));

        // Делаем SQL-запрос в БД и получаем DAO
        AccountDao accountDao = DataBaseSteps.getAccountById(accountId);
        // Убеждаемся, что при ошибке баланс в базе НЕ ОБНОВИЛСЯ (остался нулевым)
        softly.assertThat(accountDao.getBalance())
                .as("Проверка в Postgres: При превышении лимита баланс счета в БД остался неизменным (0.0)")
                .isZero();
        DaoAndModelAssertions.assertThat(actualDto, accountDao).match();

        // Удаляем счет
        DataBaseSteps.deleteAccountById(accountId);
        AccountDao accountAfterDeleteDb = DataBaseSteps.getAccountById(accountId);
        softly.assertThat(accountAfterDeleteDb)
                .as("Проверка в Postgres: Сущность счета успешно УДАЛЕНА из базы данных (равна null)")
                .isNull();

        // Удаляем пользователя
        DataBaseSteps.deleteUserByUsername(user.getUsername());
        UserDao userAfterDeleteDb = DataBaseSteps.getUserByUsername(user.getUsername());
        softly.assertThat(userAfterDeleteDb)
                .as("Проверка в Postgres: Сущность пользователя успешно УДАЛЕНА из базы данных (равна null)")
                .isNull();

        softly.assertAll();
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
                .id(nonExistentId)
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

        // Доказываем отсутствие невалидной сущности счета в БД
        softly.assertThat(accountDao)
                .as("Проверка в Postgres: фейковый счет действительно отсутствует в таблице accounts (равен null)")
                .isNull();

        // Удаляем созданного тестового пользователя
        DataBaseSteps.deleteUserByUsername(user.getUsername());
        UserDao userAfterDeleteDb = DataBaseSteps.getUserByUsername(user.getUsername());
        softly.assertThat(userAfterDeleteDb)
                .as("Проверка в Postgres: Сущность пользователя успешно УДАЛЕНА из базы данных (равна null)")
                .isNull();

        softly.assertAll();
    }
}