package iteration2;

import constants.ErrorMessages;
import generators.RandomData;
import models.CreateAccountResponse;
import models.CreateUserRequest;
import models.UserTopUpAccountRequest;
import models.comparison.ModelComparator;
import org.junit.jupiter.api.Test;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.List;


public class UserDeposit extends BaseTest {

    @Test
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
        ).post(UserTopUpAccountRequest.builder()
                .accountId(accountId)
                .amount(depositAmount)
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
    }

    @Test
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
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.DEPOSIT_EXCEEDS_LIMIT)
        ).post(UserTopUpAccountRequest.builder()
                .accountId(accountId)
                .amount(RandomData.getAmountOverLimit())
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
    }

    @Test
    public void AttemptToTopUpSomeoneElseIsOrANonExistentAccountTest() {

        CreateUserRequest user = createAndAuthorizeUser();

        // пополнение несуществующего счета
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TOP_UP_ACCOUNT,
                ResponseSpecs.requestReturnsForbidden(ErrorMessages.UNAUTHORIZED_ACCOUNT_ACCESS)
        ).post(UserTopUpAccountRequest.builder()
                .accountId(RandomData.getNonExistentAccountId())
                .amount(RandomData.getAmount())
                .build());
        List<CreateAccountResponse> accounts = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.CUSTOMER_ACCOUNTS,
                ResponseSpecs.requestReturnsOK()
        ).getList();
        softly.assertThat(accounts)
                .as("Проверка, что у пользователя нет зарегистрированных счетов в системе")
                .isEmpty();
    }
}
