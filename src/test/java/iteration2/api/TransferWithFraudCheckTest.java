package iteration2.api;

import common.annotations.FraudCheckMock;
import common.extensions.TimingExtension;
import constants.FraudDecision;
import constants.FraudStatus;
import generators.RandomData;
import iteration2.BaseTest;
import models.CreateAccountResponse;
import models.CreateUserRequest;
import models.TransferResponse;
import models.comparison.ModelComparator;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import requests.skelethon.steps.AccountSteps;
import requests.skelethon.steps.AdminSteps;
import specs.RequestSpecs;

import java.lang.reflect.Method;

@ExtendWith({TimingExtension.class, FraudCheckWireMockExtension.class})
public class TransferWithFraudCheckTest extends BaseTest {

    private CreateAccountResponse account1;
    private CreateAccountResponse account2;
    private AccountSteps accountSteps1;

    // сумма пополнения
    private double transferAmount;

    @BeforeEach
    public void setupTest() {
        this.softly = new SoftAssertions();

        double rawAmount = RandomData.getAmount();

        double depositAmount = 2000.00 + (rawAmount % 2900.00);
        depositAmount = (Double) ModelComparator.normalizeValue(depositAmount);

        this.transferAmount = (Double) ModelComparator.normalizeValue(depositAmount / 2.0);

        CreateUserRequest user1 = AdminSteps.createUser();
        var user1Spec = RequestSpecs.authAsUser(user1.getUsername(), user1.getPassword());
        account1 = AccountSteps.createAccount(user1Spec);
        accountSteps1 = new AccountSteps(user1.getUsername(), user1.getPassword());

        accountSteps1.depositToAccount(account1.getId(), depositAmount);

        CreateUserRequest user2 = AdminSteps.createUser();
        var user2Spec = RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword());
        account2 = AccountSteps.createAccount(user2Spec);
    }

    // === КЕЙС 1: APPROVED ===
    @Test
    @FraudCheckMock(
            status = FraudStatus.SUCCESS,
            decision = FraudDecision.APPROVED,
            reason = "Low risk"
    )
    public void testTransferApproved(TestInfo testInfo) {
        TransferResponse response = accountSteps1.transferWithFraudCheck(account1.getId(), account2.getId(), transferAmount);
        verifyResponseDynamically(response, "APPROVED", testInfo);
    }

    // === КЕЙС 2: BLOCKED ===
    @Test
    @FraudCheckMock(
            status = FraudStatus.SUCCESS,
            decision = FraudDecision.BLOCKED,
            reason = "High fraud risk",
            riskScore = 0.9
    )
    public void testTransferBlocked(TestInfo testInfo) {
        TransferResponse response = accountSteps1.transferWithFraudCheck(account1.getId(), account2.getId(), transferAmount);
        verifyResponseDynamically(response, "BLOCKED", testInfo);
    }

    // === КЕЙС 3: REVIEW_REQUIRED ===
    @Test
    @FraudCheckMock(
            status = FraudStatus.SUCCESS,
            decision = FraudDecision.REVIEW_REQUIRED,
            reason = "Suspicious activity",
            riskScore = 0.5,
            requiresManualReview = true
    )
    public void testTransferReviewRequired(TestInfo testInfo) {
        TransferResponse response = accountSteps1.transferWithFraudCheck(account1.getId(), account2.getId(), transferAmount);
        verifyResponseDynamically(response, "MANUAL_REVIEW_REQUIRED", testInfo);
    }

    // === КЕЙС 4: VERIFICATION_REQUIRED ===
    @Test
    @FraudCheckMock(
            status = FraudStatus.SUCCESS,
            decision = FraudDecision.VERIFICATION_REQUIRED,
            reason = "New device login",
            riskScore = 0.4,
            additionalVerificationRequired = true
    )
    public void testTransferVerificationRequired(TestInfo testInfo) {
        TransferResponse response = accountSteps1.transferWithFraudCheck(account1.getId(), account2.getId(), transferAmount);
        verifyResponseDynamically(response, "VERIFICATION_REQUIRED", testInfo);
    }

    // === КЕЙС 5: FALLBACK (Ошибка сервиса фрода) ===
    @Test
    @FraudCheckMock(
            port = 500,
            status = FraudStatus.ERROR,
            decision = FraudDecision.ERROR,
            reason = "Service Unavailable",
            riskScore = 0.5,
            requiresManualReview = true
    )
    public void testTransferFallbackOnServiceError(TestInfo testInfo) {
        TransferResponse response = accountSteps1.transferWithFraudCheck(account1.getId(), account2.getId(), transferAmount);
        verifyResponseDynamically(response, "MANUAL_REVIEW_REQUIRED", testInfo);
    }

    private void verifyResponseDynamically(TransferResponse actualResponse, String expectedStatus, TestInfo testInfo) {
        Method testMethod = testInfo.getTestMethod().orElseThrow(() -> new RuntimeException("Метод теста не определен"));
        FraudCheckMock mockConfig = testMethod.getAnnotation(FraudCheckMock.class);

        boolean expectedManualReview = mockConfig.requiresManualReview() || mockConfig.decision() == FraudDecision.REVIEW_REQUIRED;
        boolean expectedVerification = mockConfig.additionalVerificationRequired() || mockConfig.decision() == FraudDecision.VERIFICATION_REQUIRED;

        TransferResponse expectedResponse = TransferResponse.builder()
                .status(expectedStatus)
                .amount(transferAmount)
                .senderAccountId(account1.getId())
                .receiverAccountId(account2.getId())
                .fraudRiskScore(mockConfig.riskScore())
                .requiresManualReview(expectedManualReview)
                .requiresVerification(expectedVerification)
                .build();

        org.assertj.core.api.Assertions.assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("transactionId", "message", "fraudReason")
                .isEqualTo(expectedResponse);
    }
}