package iteration2.ui;

import common.annotations.Browsers;
import common.annotations.UserSession;
import common.storage.SessionStorage;
import common.utils.RetryUtils;
import extensions.Platform;
import generators.RandomData;
import iteration2.ui.pages.TransferPage;
import iteration2.ui.pages.UserDashboard;
import models.CreateAccountResponse;
import models.CreateUserRequest;
import org.junit.jupiter.api.Test;
import requests.skelethon.steps.AccountSteps;


public class TransferringMoneyTest extends BaseUiTest {

    @Test
    @UserSession
    public void SuccessfulTransferOfFundsBetweenYourOwnAccountsUITest() {
        CreateUserRequest user = SessionStorage.getUser();
        var authSpec = SessionStorage.getSteps().getUserSpec();

        CreateAccountResponse senderAccount = AccountSteps.createAccount(authSpec);
        SessionStorage.saveAccount(user, senderAccount);

        double initialAmount = RandomData.getAmount();
        AccountSteps.topUpAccount(authSpec, senderAccount.getId(), initialAmount);

        CreateAccountResponse receiverAccount = AccountSteps.createAccount(authSpec);
        SessionStorage.saveAccount(user, receiverAccount);

        double transferAmount = initialAmount / 2;

        new UserDashboard()
                .open()
                .navigateToTransfer()
                .selectSourceAccount(SessionStorage.getAccount(user, 0).getAccountNumber())
                .enterRecipientDetails(user.getUsername(),
                        SessionStorage.getAccount(user, 1).getAccountNumber())
                .enterAmount(transferAmount)
                .confirmCheckbox()
                .clickSubmit()
                .verifyTransferFormIsStillActive();
    }

    @Test
    @Browsers({"chrome"})
    @Platform(Platform.Type.MOBILE)
    @UserSession(value = 2, auth = 1)
    public void AttemptToTransferAnAmountExceedingTheMaximumLimitOverUiTest() {
        CreateUserRequest user = SessionStorage.getUser(1);
        CreateUserRequest user2 = SessionStorage.getUser(2);
        var authSpec = SessionStorage.getSteps(1).getUserSpec();

        CreateAccountResponse senderAccount = AccountSteps.createAccount(authSpec);
        SessionStorage.saveAccount(user, senderAccount);

        double transferAmountOverLimit = RandomData.getTransferOverLimit();
        double chunk = transferAmountOverLimit / 3.0;


        repeat(3, () -> AccountSteps.topUpAccount(authSpec, senderAccount.getId(), chunk));

        var authSpec2 = SessionStorage.getSteps(2).getUserSpec();
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(authSpec2);
        SessionStorage.saveAccount(user2, receiverAccount);

        new UserDashboard()
                .open()
                .navigateToTransfer()
                .selectSourceAccount(SessionStorage.getAccount(user, 0).getAccountNumber())
                .enterRecipientDetails(user2.getUsername(),
                        SessionStorage.getAccount(user2, 0).getAccountNumber()
                )
                .enterAmount((int) transferAmountOverLimit)
                .confirmCheckbox()
                .clickSubmit();

        TransferPage transferPage = new TransferPage();

        RetryUtils.retry(
                () -> {
                    try {
                        transferPage.verifyTransferFormIsStillActive();
                        return true;
                    } catch (Throwable e) {
                        return false;
                    }
                },
                success -> success,
                3,
                500
        );
    }

    @Test
    @Browsers({"chrome"})
    @Platform(Platform.Type.DESKTOP)
    @UserSession(value = 2, auth = 1)
    public void AttemptToTransferAnAmountExceedingTheSenderIsBalanceUiTest() {

        CreateUserRequest user = SessionStorage.getUser(1);
        CreateUserRequest user2 = SessionStorage.getUser(2);

        var authSpec = SessionStorage.getSteps(1).getUserSpec();

        CreateAccountResponse senderAccount = AccountSteps.createAccount(authSpec);
        SessionStorage.saveAccount(user, senderAccount);

        double initialAmount = RandomData.getAmount();
        AccountSteps.topUpAccount(authSpec, senderAccount.getId(), initialAmount);

        var authSpec2 = SessionStorage.getSteps(2).getUserSpec();
        CreateAccountResponse receiverAccount = AccountSteps.createAccount(authSpec2);
        SessionStorage.saveAccount(user2, receiverAccount);

        double invalidTransferAmount = initialAmount + RandomData.getAmount();

        new UserDashboard()
                .open()
                .navigateToTransfer()
                .selectSourceAccount(SessionStorage.getAccount(user, 0).getAccountNumber())
                .enterRecipientDetails(user2.getUsername(),
                        SessionStorage.getAccount(user2, 0).getAccountNumber())
                .enterAmount((int) invalidTransferAmount)
                .confirmCheckbox()
                .clickSubmit();

        TransferPage transferPage = new TransferPage();

        RetryUtils.retry(
                () -> {
                    try {
                        transferPage.verifyTransferFormIsStillActive();
                        return true;
                    } catch (Throwable e) {
                        return false;
                    }
                },
                success -> success,
                3,
                500
        );
    }

    private void repeat(int times, Runnable action) {
        for (int i = 0; i < times; i++) {
            action.run();
        }
    }
}