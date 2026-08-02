package iteration2.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import models.CreateUserRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import requests.skelethon.steps.AdminSteps;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class UpdateUsernameProfileTest {
    @BeforeAll
    public static void setupSelenoid() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.baseUrl = "http://192.168.1.101:3000";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "126.0";
        Configuration.browserSize = "1920x1080";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableLog", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    public void SuccessfulNameChangeToAValidFormatUiTest() {
        // создаем юзера
        CreateUserRequest user = AdminSteps.createUser();

        // Авторизация
        Selenide.open("/login");
        $(Selectors.byAttribute("placeholder", "Username")).sendKeys(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Password")).sendKeys(user.getPassword());
        $("button").click();

        // Ожидаем загрузку личного кабинета
        $(Selectors.byClassName("welcome-text")).shouldBe(Condition.visible).shouldHave(Condition.text("Welcome, noname!"));
        // Кликаем по блоку профиля в шапке сайта
        $(Selectors.byClassName("user-info")).shouldBe(Condition.visible).click();
        // Переходим на страницу профиля
        Selenide.open("/edit-profile");
        // Находим инпут для ввода имени и вводим новое имя
        $(Selectors.byAttribute("placeholder", "Enter new name")).sendKeys("Nikita Krapivin");
        // Нажимаем кнопку Save Changes
        $(Selectors.withText("Save Changes")).click();
        Selenide.confirm("✅ Name updated successfully!");
        // Проверка, что в блоке профиля имя "Noname" успешно изменилось
        $(".profile-header")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Nikita Krapivin"));
        // !БАГ! — запрос put не уходит
    }

    @Test
    public void PasswordChangeAttemptUiTest() {

        CreateUserRequest user = AdminSteps.createUser();

        Selenide.open("/login");
        $(Selectors.byAttribute("placeholder", "Username")).sendKeys(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Password")).sendKeys(user.getPassword());
        $("button").click();

        $(".profile-header").shouldBe(Condition.visible).click();

        // Убеждаемся, что поля для ввода пароля или изменения пароля физически нет на этой форме
        $(Selectors.byAttribute("placeholder", "Enter new password")).shouldNotBe(Condition.exist);
        $(Selectors.byAttribute("type", "password")).shouldNotBe(Condition.exist);
    }

    @Test
    public void AttemptToSetANameConsistingOfOnlyOneWordUiTest() {

        CreateUserRequest user = AdminSteps.createUser(); // или ваш createAndAuthorizeUser()

        // Авторизация
        Selenide.open("/login");
        $(Selectors.byAttribute("placeholder", "Username")).sendKeys(user.getUsername());
        $(Selectors.byAttribute("placeholder", "Password")).sendKeys(user.getPassword());
        $("button").click();

        // Ожидаем загрузку личного кабинета
        $(Selectors.byClassName("welcome-text")).shouldBe(Condition.visible);

        // Переходим на страницу профиля
        Selenide.open("/edit-profile");

        // Находим инпут для ввода имени и вводим новое имя
        $(Selectors.byAttribute("placeholder", "Enter new name")).sendKeys("Krapivin");

        // Нажимаем кнопку Save Changes
        $(Selectors.withText("Save Changes")).click();

        // Должен быть алерт с текстом о двух словах в имени
        Selenide.confirm("Name must contain two words with letters only");
        //БАГ ФРОНТА. Некорректно выводит сообщение.
    }
}