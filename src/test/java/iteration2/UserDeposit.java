package iteration2;

import constants.ErrorMessages;
import generators.RandomData;
import io.restassured.http.ContentType;
import models.CreateUserRequest;
import models.LoginUserRequest;
import models.UserRole;
import models.UserTopUpAccountRequest;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import requests.AdminCreateUserRequester;
import requests.CreateAccountRequester;
import requests.LoginUserRequester;
import requests.UserTopUpAccountRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.Random;

import static io.restassured.RestAssured.given;


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
        new UserTopUpAccountRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .post(UserTopUpAccountRequest.builder()
                        .accountId(accountId)
                        .amount(RandomData.getAmount())
                        .build());
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
    }
}
