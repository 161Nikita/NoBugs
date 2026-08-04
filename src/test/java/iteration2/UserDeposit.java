package iteration2;

import constants.ErrorMessages;
import generators.RandomData;
import models.CreateUserRequest;
import models.LoginUserRequest;
import models.UserRole;
import models.UserTopUpAccountRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import requests.*;
import specs.RequestSpecs;
import specs.ResponseSpecs;


public class UserDeposit {

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
                .header("Authorization");

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
        Number actualDepositedAmount = new UserTopUpAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .post(UserTopUpAccountRequest.builder()
                        .accountId(accountId)
                        .amount(RandomData.getAmount())
                        .build())
                .extract()
                .path("depositAmount");
        // Проверяем баланс через get
        new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .body("find { it.id == " + accountId + " }.balance",
                        Matchers.equalTo((float) (Math.round(actualDepositedAmount.doubleValue() * 100.0) / 100.0)));
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
        new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .body("find { it.id == " + accountId + " }.balance", Matchers.equalTo(0.0f));
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
        new UserGetAccountsRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .body("size()", Matchers.equalTo(0));
    }
}
