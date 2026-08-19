package iteration2.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import common.annotations.FraudCheckMock;
import constants.FraudDecision;
import constants.FraudStatus;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class FraudCheckWireMockExtension implements BeforeEachCallback, AfterEachCallback {

    private WireMockServer wireMockServer;

    @Override
    public void beforeEach(ExtensionContext context) {
        FraudCheckMock mockConfig = context.getTestMethod()
                .map(method -> method.getAnnotation(FraudCheckMock.class))
                .orElseGet(() -> context.getTestClass()
                        .map(clazz -> clazz.getAnnotation(FraudCheckMock.class))
                        .orElse(null));

        if (mockConfig != null) {
            setupWireMock(mockConfig);
        }
    }

    private void setupWireMock(FraudCheckMock config) {
        if (wireMockServer == null || !wireMockServer.isRunning()) {
            wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8888).bindAddress("0.0.0.0"));
            wireMockServer.start();
            WireMock.configureFor("0.0.0.0", 8888);
        }

        WireMock.reset();

        // КЕЙС 5: Если в тесте указан port = 500 или статус ERROR — честно роняем фрод-сервис (HTTP 500) для ВСЕХ запросов этого теста
        if (config.port() == 500 || config.status() == FraudStatus.ERROR) {
            WireMock.stubFor(WireMock.post(WireMock.anyUrl())
                    .willReturn(aResponse().withStatus(500)));
            return;
        }

        // КЕЙСЫ 1-4: Формируем эталонный динамический JSON на основе Enum из аннотации
        FraudDecision decision = config.decision();
        boolean manualReview = config.requiresManualReview() || decision == FraudDecision.REVIEW_REQUIRED;
        boolean verification = config.additionalVerificationRequired() || decision == FraudDecision.VERIFICATION_REQUIRED;

        String responseBody = "{\n" +
                "  \"status\": \""+ config.status().name() + "\",\n" +
                "  \"decision\": \"" + decision.name() + "\",\n" +
                "  \"riskScore\": " + config.riskScore() + ",\n" +
                "  \"fraudRiskScore\": " + config.riskScore() + ",\n" +
                "  \"reason\": \"" + config.reason() + "\",\n" +
                "  \"fraudReason\": \"" + config.reason() + "\",\n" +
                "  \"requiresManualReview\": " + manualReview + ",\n" +
                "  \"requiresVerification\": " + verification + ",\n" +
                "  \"additionalVerificationRequired\": " + verification + "\n" +
                "}";

        // Для успешных тестов отдаем этот JSON на любые запросы
        WireMock.stubFor(WireMock.post(WireMock.anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
            wireMockServer = null;
        }
    }
}