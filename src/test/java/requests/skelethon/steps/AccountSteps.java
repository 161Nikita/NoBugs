package requests.skelethon.steps;

import models.CreateAccountResponse;
import io.restassured.specification.RequestSpecification;
import models.UserTopUpAccountRequest;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import specs.ResponseSpecs;

public class AccountSteps {

    // Шаг открытия счета для конкретного авторизованного юзера
    public static CreateAccountResponse createAccount(RequestSpecification userSpec) {
        return new ValidatedCrudRequester<CreateAccountResponse>(
                userSpec,
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post(null);
    }
    // Шаг пополнения счета
    public static void topUpAccount(RequestSpecification userSpec, long accountId, double amount) {
        new CrudRequester(userSpec, Endpoint.USER_TOP_UP_ACCOUNT, ResponseSpecs.requestReturnsOK())
                .post(UserTopUpAccountRequest.builder()
                        .accountId(accountId)
                        .amount(amount)
                        .build());
    }
}
