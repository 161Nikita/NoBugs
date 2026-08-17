package requests.skelethon.steps;

import common.helpers.StepLogger;
import io.restassured.specification.RequestSpecification;
import models.CreateAccountResponse;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.List;

public class UserSteps {
    private String username;
    private String password;

    public UserSteps(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public List<CreateAccountResponse> getAllAccounts() {
        return StepLogger.log("User " + username + " get all accounts", () -> {
            return new ValidatedCrudRequester<CreateAccountResponse>(
                    RequestSpecs.authAsUser(username, password),
                    Endpoint.CUSTOMER_ACCOUNTS,
                    ResponseSpecs.requestReturnsOK()).getAll(CreateAccountResponse[].class);
        });
    }

    public RequestSpecification getUserSpec() {
        return StepLogger.log("Получение API-спецификации для пользователя: " + username, () -> {
            return specs.RequestSpecs.authAsUser(username, password);
        });
    }
}