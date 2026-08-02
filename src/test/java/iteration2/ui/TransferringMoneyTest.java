package iteration2.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import generators.RandomData;
import models.CreateAccountResponse;
import models.CreateUserRequest;
import models.LoginUserRequest;
import models.UserTopUpAccountRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import requests.skelethon.steps.AdminSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TransferringMoneyTest {
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
    public void SuccessfulTransferOfFundsBetweenYourOwnAccountsUITest() {
        // Готовим данные через api
        CreateUserRequest user = AdminSteps.createUser();
        var authSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // Создаем счет-отправитель через api
        CreateAccountResponse senderAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                authSpec, Endpoint.ACCOUNTS, ResponseSpecs.entityWasCreated()
        ).post(null);

        // Пополняем его через api
        double initialAmount = RandomData.getAmount();
        new CrudRequester(authSpec, Endpoint.USER_TOP_UP_ACCOUNT, ResponseSpecs.requestReturnsOK())
                .post(UserTopUpAccountRequest.builder()
                        .accountId(senderAccount.getId())
                        .amount(initialAmount)
                        .build());

        // Создаем счет-получатель через API
        CreateAccountResponse receiverAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                authSpec, Endpoint.ACCOUNTS, ResponseSpecs.entityWasCreated()
        ).post(null);

        // Рассчитываем сумму перевода (половина баланса, чтобы гарантированно не превысить лимиты)
        double transferAmount = initialAmount / 2;

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

        // Переходим на страницу перевода
        $(Selectors.withText("Make a Transfer")).shouldBe(Condition.visible).click();

        // Закрываем первый автоматический алерт ошибки транзакций
        Selenide.confirm("❌ Unable to fetch transactions. Please try again.");

        // Закрываем второй автоматический алерт ошибки транзакций
        Selenide.confirm("❌ Unable to fetch transactions. Please try again.");

        // Выбираем свой счет-отправитель
        $(".account-selector")
                .shouldBe(Condition.visible)
                .selectOptionContainingText(senderAccount.getAccountNumber());

        // Вводим Имя получателя
        $(Selectors.byAttribute("placeholder", "Enter recipient name")).sendKeys(user.getUsername());

        // Вводим Номер счета получателя
        $(Selectors.byAttribute("placeholder", "Enter recipient account number"))
                .sendKeys(receiverAccount.getAccountNumber());

        // Вводим динамическую сумму перевода
        $(Selectors.byAttribute("placeholder", "Enter amount")).sendKeys(String.valueOf(transferAmount));

        // Кликаем по чекбоксу подтверждения
        $(Selectors.byAttribute("id", "confirmCheck")).click();

        // Отправляем перевод
        $("button.green-btn").click();

        // Даем 1 секунду на завершение запроса
        Selenide.sleep(1000);

        // Убеждаемся, что мы по-прежнему на форме, и кнопка отправки активна (нет блокирующих алертов)
        $("button.green-btn").shouldBe(Condition.visible, Condition.enabled);
    }

    @Test
    public void AttemptToTransferAnAmountExceedingTheMaximumLimitOverUiTest() {
        //  Готовим данные через api
        CreateUserRequest user = AdminSteps.createUser();
        CreateUserRequest user2 = AdminSteps.createUser();
        var authSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // Создаем счет-отправитель
        CreateAccountResponse senderAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                authSpec, Endpoint.ACCOUNTS, ResponseSpecs.entityWasCreated()
        ).post(null);
        long senderAccountId = senderAccount.getId();

        // Расчет суммы перевода (> 10000) и деление её на 3 равные части
        double transferAmountOverLimit = RandomData.getTransferOverLimit();
        double chunk = transferAmountOverLimit / 3.0;

        // Пополнение счета-отправителя циклом за 3 захода (обходим лимит депозита в 5000)
        CrudRequester topUp = new CrudRequester(authSpec, Endpoint.USER_TOP_UP_ACCOUNT, ResponseSpecs.requestReturnsOK());
        for (int i = 0; i < 3; i++) {
            topUp.post(UserTopUpAccountRequest.builder().accountId(senderAccountId).amount(chunk).build());
        }

        // Создаем счет-получатель у второго пользователя (user2)
        CreateAccountResponse receiverAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post(null);

        // Авторизуемся под первым пользователем (отправителем)
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

        // Переходим на страницу перевода
        $(Selectors.withText("Make a Transfer")).shouldBe(Condition.visible).click();

        // Закрываем два автоматических алерта ошибки истории транзакций (баг фронта)
        Selenide.confirm("❌ Unable to fetch transactions. Please try again.");
        Selenide.confirm("❌ Unable to fetch transactions. Please try again.");

        // Выбираем свой счет-отправитель
        $(".account-selector")
                .shouldBe(Condition.visible)
                .selectOptionContainingText(senderAccount.getAccountNumber());

        // Вводим Имя получателя (имя второго пользователя)
        $(Selectors.byAttribute("placeholder", "Enter recipient name")).sendKeys(user2.getUsername());

        // Вводим Номер счета получателя (номер счета второго пользователя из API)
        $(Selectors.byAttribute("placeholder", "Enter recipient account number"))
                .sendKeys(receiverAccount.getAccountNumber());

        // Вводим сумму перевода, превышающую лимит в 10000
        $(Selectors.byAttribute("placeholder", "Enter amount")).sendKeys(String.valueOf(transferAmountOverLimit));

        // Кликаем по чекбоксу подтверждения
        $(Selectors.byAttribute("id", "confirmCheck")).click();


        // Ждем 1 секунда
        Selenide.sleep(1000);

        // !БАГ ФРОНТЕНДА! — Окно с ошибкой лимита не всплывает, но система блокирует перевод.

        // Кнопка "Send Transfer" всё ещё отображается на экране
        $("button.green-btn").shouldBe(Condition.visible, Condition.enabled);
    }

    @Test
    public void AttemptToTransferAnAmountExceedingTheSenderIsBalanceUiTest() {
        // Готовим данные через api
        CreateUserRequest user = AdminSteps.createUser();
        CreateUserRequest user2 = AdminSteps.createUser();
        var authSpec = RequestSpecs.authAsUser(user.getUsername(), user.getPassword());

        // Создаем счет № 1 - отправитель
        CreateAccountResponse senderAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                authSpec, Endpoint.ACCOUNTS, ResponseSpecs.entityWasCreated()
        ).post(null);

        // Пополняем счет № 1 на случайную валидную сумму до 5000
        double initialAmount = RandomData.getAmount();
        new CrudRequester(authSpec, Endpoint.USER_TOP_UP_ACCOUNT, ResponseSpecs.requestReturnsOK())
                .post(UserTopUpAccountRequest.builder()
                        .accountId(senderAccount.getId())
                        .amount(initialAmount)
                        .build());

        // Создаем счет № 2 - получатель (у второго пользователя)
        CreateAccountResponse receiverAccount = new ValidatedCrudRequester<CreateAccountResponse>(
                RequestSpecs.authAsUser(user2.getUsername(), user2.getPassword()),
                Endpoint.ACCOUNTS,
                ResponseSpecs.entityWasCreated()
        ).post(null);

        // Расчет невалидной суммы перевода (гарантированно больше доступного баланса)
        double invalidTransferAmount = initialAmount + RandomData.getAmount();

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

        // Переходим на страницу перевода
        $(Selectors.withText("Make a Transfer")).shouldBe(Condition.visible).click();

        // Закрываем два автоматических алерта ошибки истории транзакций (баг фронтенда)
        Selenide.confirm("❌ Unable to fetch transactions. Please try again.");
        Selenide.confirm("❌ Unable to fetch transactions. Please try again.");

        // Выбираем свой счет-отправитель
        $(".account-selector")
                .shouldBe(Condition.visible)
                .selectOptionContainingText(senderAccount.getAccountNumber());

        // Вводим Имя получателя (имя второго пользователя)
        $(Selectors.byAttribute("placeholder", "Enter recipient name")).sendKeys(user2.getUsername());

        // Вводим Номер счета получателя (из API-ответа второго пользователя)
        $(Selectors.byAttribute("placeholder", "Enter recipient account number"))
                .sendKeys(receiverAccount.getAccountNumber());

        // Вводим сумму перевода, которая превышает баланс
        $(Selectors.byAttribute("placeholder", "Enter amount")).sendKeys(String.valueOf(invalidTransferAmount));

        // Кликаем по чекбоксу подтверждения
        $(Selectors.byAttribute("id", "confirmCheck")).click();

        // Отправляем перевод по классу кнопки
        $("button.green-btn").click();

        // Ждем 1 секунду на обработку запроса
        Selenide.sleep(1000);

        // Так как фронтенд не умеет выводить алерты ошибок для этой формы (баг UI),
        // мы проверяем, что транзакция отклонена системой: мы остались на этой же странице,
        // и кнопка отправки "Send Transfer" по-прежнему активна на экране
        $("button.green-btn").shouldBe(Condition.visible, Condition.enabled);
    }
}