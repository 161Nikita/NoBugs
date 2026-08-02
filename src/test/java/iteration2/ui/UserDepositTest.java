package iteration2.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import generators.RandomData;
import models.CreateUserRequest;
import models.LoginUserRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.steps.AdminSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDepositTest {
    @BeforeAll
    public static void setupSelenoid() {
        Configuration.remote = "http://localhost:4444/wd/hub";

        Configuration.baseUrl = "http://192.168.1.101:3000";

        Configuration.browser = "chrome";
        Configuration.browserVersion = "126.0";

        Configuration.browserSize = "1920x1080";

        org.openqa.selenium.remote.DesiredCapabilities capabilities = new org.openqa.selenium.remote.DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableLog", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    public void depositTopUpTest() {
        // создаем юзера
        CreateUserRequest user = AdminSteps.createUser();
        // создаем счет
        requests.skelethon.steps.AccountSteps.createAccount(
                specs.RequestSpecs.authAsUser(user.getUsername(), user.getPassword())
        );

        // авторизация через api
        String userAuthHeader = new CrudRequester(
                RequestSpecs.unauthSpec(),
                Endpoint.LOGIN,
                ResponseSpecs.requestReturnsOK())
                .post(LoginUserRequest.builder().username(user.getUsername()).password(user.getPassword()).build())
                .extract()
                .header("Authorization");

        Selenide.open("/");

        executeJavaScript(
                "localStorage.setItem('authToken', arguments[0]);", userAuthHeader);

        Selenide.open("/dashboard");

        $(Selectors.byClassName("welcome-text")).shouldBe(Condition.visible).shouldHave(Condition.text("Welcome, noname!"));
        // переходим на страницу пополнения баланса
        $(".custom-btn.action-btn").shouldHave(Condition.text("Deposit Money")).click();
        // проверяем плейсхолдер выпадающего списка и открываем первый элемент списка
        $(".account-selector")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("-- Choose an account --")).selectOption(1);
        // вводим сумму
        $(Selectors.byAttribute("placeholder", "Enter amount")).sendKeys(String.valueOf(RandomData.getAmount()));
        $("button.btn-primary").click();
        // проверяем пополнение баланса.
        Alert alert = switchTo().alert();
        assertEquals(alert.getText(), ""); // !БАГ! - пополнить баланс нельзя, появляется ошибка ❌ Failed to deposit. Please try again.
        alert.accept();
    }

    @Test
    public void AttemptToTopUpByAnAmountExceedingTheLimitTest() {
        // создаем юзера
        CreateUserRequest user = AdminSteps.createUser();
        // создаем счет
        requests.skelethon.steps.AccountSteps.createAccount(
                specs.RequestSpecs.authAsUser(user.getUsername(), user.getPassword())
        );
        // авторизация через api
        String userAuthHeader = new CrudRequester(
                RequestSpecs.unauthSpec(),
                Endpoint.LOGIN,
                ResponseSpecs.requestReturnsOK())
                .post(LoginUserRequest.builder().username(user.getUsername()).password(user.getPassword()).build())
                .extract()
                .header("Authorization");

        Selenide.open("/");

        executeJavaScript(
                "localStorage.setItem('authToken', arguments[0]);", userAuthHeader);

        Selenide.open("/dashboard");

        $(Selectors.byClassName("welcome-text")).shouldBe(Condition.visible).shouldHave(Condition.text("Welcome, noname!"));
        // переходим на страницу пополнения баланса
        Selenide.open("/deposit");
        // проверяем плейсхолдер выпадающего списка и открываем первый элемент списка
        $(".account-selector")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("-- Choose an account --")).selectOption(1);
        // вводим сумму
        $(Selectors.byAttribute("placeholder", "Enter amount")).sendKeys(String.valueOf(RandomData.getAmountOverLimit()));
        $("button.btn-primary").click();
        // проверяем пополнение баланса.
        Alert alert = switchTo().alert();
        assertEquals(alert.getText(), "❌ Please deposit less or equal to 5000$.");
        alert.accept();
    }
}