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


public class UserDeposit extends BaseTest {

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
    public void depositTopUpTest() {

        CreateUserRequest user = createAndAuthorizeUser();

        // создаем счет
        long accountId = new CreateAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.entityWasCreated())
                .postAndGetBody()
                .getId();

        // пополнение своего счета
        UserTopUpAccountResponse topUpResponse = new UserTopUpAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .post(UserTopUpAccountRequest.builder()
                        .accountId(accountId)
                        .amount(RandomData.getAmount())
                        .build())
                .extract()
                .as(UserTopUpAccountResponse.class);
        double actualDepositedAmount = topUpResponse.getDepositAmount();

        // Проверяем баланс через get
        CreateAccountResponse[] accounts = new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(CreateAccountResponse[].class);

        // Поиск аккаунта
        CreateAccountResponse userAccount = Arrays.stream(accounts)
                .filter(acc -> acc.getId() == accountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Аккаунт с ID " + accountId + " не найден"));

        double expectedBalance = MoneyHelper.round(actualDepositedAmount);

        // Проверка через AssertJ
        softly.assertThat(userAccount.getBalance())
                .as("Проверка баланса после пополнения")
                .isEqualTo(expectedBalance);
    }

    @Test
    public void AttemptToTopUpByAnAmountExceedingTheLimitTest() {

        CreateUserRequest user = createAndAuthorizeUser();

        // создаем счет
        long accountId = new CreateAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.entityWasCreated())
                .postAndGetBody()
                .getId();

        // пополнение своего счета превышающий лимит
        new UserTopUpAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.DEPOSIT_EXCEEDS_LIMIT)
        )
                .post(UserTopUpAccountRequest.builder()
                        .accountId(accountId)
                        .amount(RandomData.getAmountOverLimit())
                        .build());
        // Проверяем баланс через get
        CreateAccountResponse[] accounts = new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(CreateAccountResponse[].class);

        CreateAccountResponse userAccount = Arrays.stream(accounts)
                .filter(acc -> acc.getId() == accountId)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Аккаунт с ID " + accountId + " не найден"));

        softly.assertThat(userAccount.getBalance())
                .as("Баланс не должен измениться при ошибке лимита")
                .isZero();
    }

    @Test
    public void AttemptToTopUpSomeoneElseIsOrANonExistentAccountTest() {

        CreateUserRequest user = createAndAuthorizeUser();

        // пополнение несуществующего счета
        new UserTopUpAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsForbidden(ErrorMessages.UNAUTHORIZED_ACCOUNT_ACCESS)
        )
                .post(UserTopUpAccountRequest.builder()
                        .accountId(RandomData.getNonExistentAccountId())
                        .amount(RandomData.getAmount())
                        .build());
        // Проверяем что у нашего юзера по-прежнему нет счетов
        CreateAccountResponse[] accounts = new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(CreateAccountResponse[].class);

        softly.assertThat(accounts.length)
                .as("У пользователя не должно быть открытых счетов")
                .isZero();
    }
}