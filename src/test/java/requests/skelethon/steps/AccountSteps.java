package requests.skelethon.steps;

import helpers.StepLogger;
import models.*;
import io.restassured.specification.RequestSpecification;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

public class AccountSteps {
    private String username;
    private String password;

    public AccountSteps(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Шаг открытия счета для конкретного авторизованного юзера
    public static CreateAccountResponse createAccount(RequestSpecification userSpec) {
        return new ValidatedCrudRequester<CreateAccountResponse>(
                userSpec,
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post(null);
    }
    public DepositResponse depositToAccount(Long accountId, double amount) {
        return StepLogger.log("User " + username + " deposits " + amount + " to account " + accountId, () -> {
            DepositRequest depositRequest = DepositRequest.builder()
                    .accountId(accountId)
                    .amount(amount)
                    .description("Test deposit")
                    .build();

            return new ValidatedCrudRequester<DepositResponse>(
                    RequestSpecs.authAsUser(username, password),
                    Endpoint.ACCOUNT_DEPOSIT,
                    ResponseSpecs.requestReturnsOK()).post(depositRequest);
        });
    }

    public TransferResponse transferWithFraudCheck(Long senderAccountId, Long receiverAccountId, double amount) {
        return StepLogger.log("User " + username + " transfers " + amount + " to " + receiverAccountId + " with fraud check", () -> {
            TransferRequest transferRequest = TransferRequest.builder()
                    .senderAccountId(senderAccountId)
                    .receiverAccountId(receiverAccountId)
                    .amount(amount)
                    .description("Test transfer with fraud check")
                    .build();

            return new ValidatedCrudRequester<TransferResponse>(
                    RequestSpecs.authAsUser(username, password),
                    Endpoint.TRANSFER_WITH_FRAUD_CHECK,
                    ResponseSpecs.requestReturnsOK()).post(transferRequest);
        });
    }
}
