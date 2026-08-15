package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import extensions.BrowserMatchExtension;
import extensions.UserSessionExtension;
import api.BaseTest;
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

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableLog", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    public static void authAsUser(String username, String password) {
        Selenide.open("/dashboard");
        // Получаем токен авторизации
        String userAuthHeader = RequestSpecs.getUserAuthHeader(username, password);
        // Прописываем токен в память localStorage текущей страницы
        executeJavaScript(
                "localStorage.setItem('authToken', arguments[0]);", userAuthHeader);
        Selenide.open("/dashboard");
    }

    public static void authAsUser(CreateUserRequest createUserRequest) {
        authAsUser(createUserRequest.getUsername(),createUserRequest.getPassword());
    }
}