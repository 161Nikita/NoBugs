package iteration2.ui;

import common.annotations.UserSession;
import common.storage.SessionStorage;
import generators.RandomData;
import iteration2.ui.pages.BankAlert;
import iteration2.ui.pages.UserDashboard;
import models.CreateAccountResponse;
import models.CreateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requests.skelethon.steps.AccountSteps;

@UserSession

public class UserDepositTest extends BaseUiTest {

    @BeforeEach
    public void createAccountPrecondition() {
        CreateUserRequest user = SessionStorage.getUser();
        CreateAccountResponse account = AccountSteps.createAccount(SessionStorage.getSteps().getUserSpec());
        SessionStorage.saveAccount(user, account);
    }


    @Test
    public void depositTopUpTest() {
        new UserDashboard()
                .open()
                .depositMoney()
                .selectFirstAccount()
                .enterAmount(RandomData.getAmount())
                .clickSubmitAndExpectError(BankAlert.TOP_UP_DEPOSIT_SUCCESSFUL);
    }

    @Test
    public void AttemptToTopUpByAnAmountExceedingTheLimitTest() {
        new UserDashboard()
                .open()
                .depositMoney()
                .selectFirstAccount()
                .enterAmount(RandomData.getAmountOverLimit())
                .clickSubmitAndExpectError(BankAlert.TOP_UP_LIMIT_EXCEEDED);
    }
}