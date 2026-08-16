package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import common.helpers.StepLogger;
import extensions.BrowserMatchExtension;
import extensions.UserSessionExtension;
import api.BaseTest;
import io.qameta.allure.selenide.AllureSelenide;
import models.CreateUserRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import specs.RequestSpecs;

import java.util.Map;

import static com.codeborne.selenide.Selenide.executeJavaScript;

@ExtendWith(UserSessionExtension.class)
@ExtendWith(BrowserMatchExtension.class)
public class BaseUiTest extends BaseTest {
    @BeforeAll
    public static void setupSelenoid() {
        Configuration.remote = configs.Config.getProperty("uiRemote");

        Configuration.baseUrl = configs.Config.getProperty("uiBaseUrl");

        Configuration.browser = configs.Config.getProperty("browser");
        Configuration.browserVersion = configs.Config.getProperty("browserVersion");

        Configuration.browserSize = configs.Config.getProperty("browserSize");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableLog", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    public static void authAsUser(String username, String password) {
        StepLogger.log("Открыть стартовую страницу приложения для авторизации", () -> {
            Selenide.open("/");
            return null;
        });
        // Получаем токен авторизации
        String userAuthHeader = RequestSpecs.getUserAuthHeader(username, password);
        // Прописываем токен в память localStorage текущей страницы
        executeJavaScript(
                "localStorage.setItem('authToken', arguments[0]);", userAuthHeader);
        StepLogger.log("Переход в личный кабинет (Дашборд)", () -> {
            Selenide.open("/dashboard");
            return null;
        });
    }

    public static void authAsUser(CreateUserRequest createUserRequest) {
        authAsUser(createUserRequest.getUsername(), createUserRequest.getPassword());
    }
}