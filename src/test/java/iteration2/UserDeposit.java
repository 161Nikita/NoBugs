package iteration2;

import constants.ErrorMessages;
import generators.RandomData;
import models.*;
import org.junit.jupiter.api.Test;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;


public class UserDeposit extends BaseTest {

    @Test
    public void depositTopUpTest() {
        CreateUserRequest user = createAndAuthorizeUser();

        long accountId = requests.skelethon.steps.AccountSteps.createAccount(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword())
        ).getId();

        // Пополнение своего счета
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.USER_TOP_UP_ACCOUNT,
                ResponseSpecs.requestReturnsOK()
        ).post(UserTopUpAccountRequest.builder()
                .accountId(accountId)
                .amount(RandomData.getAmount())
                .build());
    }

    @Test
    public void AttemptToTopUpByAnAmountExceedingTheLimitTest() {
        CreateUserRequest user = createAndAuthorizeUser();

        // создаем счет
        CreateAccountResponse accountResponse = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post(null);

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
    }
}
