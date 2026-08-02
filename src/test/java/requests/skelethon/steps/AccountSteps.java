package requests.skelethon.steps;

import models.CreateAccountResponse;
import io.restassured.specification.RequestSpecification;
import requests.skelethon.Endpoint;
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
}
