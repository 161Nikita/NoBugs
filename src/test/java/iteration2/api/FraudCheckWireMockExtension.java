package iteration2.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import common.annotations.FraudCheckMock;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class FraudCheckWireMockExtension implements BeforeEachCallback, AfterEachCallback {

    private WireMockServer wireMockServer;

    @Override
    public void beforeEach(ExtensionContext context) {
        // Читаем аннотацию над текущим запущенным тестом
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
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8888));
        wireMockServer.start();
        WireMock.configureFor("0.0.0.0", 8888);

        // Кейс 5: Если порт 500 (или статус ошибки), симулируем не-200 ошибку сервиса фрода
        if (config.port() == 500 || "ERROR".equals(config.status())) {
            WireMock.stubFor(WireMock.post(WireMock.anyUrl())
                    .willReturn(aResponse().withStatus(500)));
            return;
        }

        // Вычисляем логические флаги строго на основе параметров аннотации конкретного теста!
        String decision = config.decision();
        boolean manualReview = config.requiresManualReview() || "REVIEW_REQUIRED".equals(decision) || "MANUAL_REVIEW_REQUIRED".equals(decision);
        boolean verification = config.additionalVerificationRequired() || "VERIFICATION_REQUIRED".equals(decision);

        // Полностью динамический JSON без String.format (защита от локали ОС и запятых)
        String responseBody = "{\n" +
                "  \"status\": \"" + config.status() + "\",\n" +
                "  \"decision\": \"" + decision + "\",\n" +
                "  \"riskScore\": " + config.riskScore() + ",\n" +
                "  \"fraudRiskScore\": " + config.riskScore() + ",\n" +
                "  \"reason\": \"" + config.reason() + "\",\n" +
                "  \"fraudReason\": \"" + config.reason() + "\",\n" +
                "  \"requiresManualReview\": " + manualReview + ",\n" +
                "  \"requiresVerification\": " + verification + ",\n" +
                "  \"additionalVerificationRequired\": " + verification + "\n" +
                "}";

        WireMock.stubFor(WireMock.post(WireMock.anyUrl())
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody)));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}