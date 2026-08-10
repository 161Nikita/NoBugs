package iteration2.ui;

import generators.RandomData;
import iteration2.ui.pages.BankAlert;
import iteration2.ui.pages.UserDashboard;
import models.CreateUserRequest;
import org.junit.jupiter.api.Test;
import requests.skelethon.steps.AdminSteps;

public class UpdateUsernameProfileTest extends BaseUiTest {

    @Test
    public void SuccessfulNameChangeToAValidFormatUiTest() {
        // создаем юзера
        CreateUserRequest user = AdminSteps.createUser();
        String randomName = RandomData.getUsername() + " " + RandomData.getUsername();

        // авторизация
        authAsUser(user);
        new UserDashboard()
                // Переход по адресу /dashboard и автоматическая проверка "Welcome, noname!"
                .open()
                // Кликаем по блоку профиля в шапке сайта и переходим на EditProfilePage
                .navigateToEditProfile()
                // Вводим новое валидное имя
                .enterNewName(randomName)
                // Нажимаем кнопку Save Changes
                .clickSave()
                // Ловим и подтверждаем алерт (метод унаследован в BasePage, используем ваш enum)
                // Примечание: Укажите точный элемент BankAlert из вашего проекта, например NAME_UPDATED
                .checkAlertMessageAndAccept(BankAlert.NAME_UPDATED)
                // Проверка, что имя в шапке профиля успешно обновилось
                .verifyUpdatedName(randomName);
        // !БАГ! — запрос put не уходит
    }

    @Test
    public void PasswordChangeAttemptUiTest() {
        // создаем юзера
        CreateUserRequest user = AdminSteps.createUser();

        authAsUser(user);

        new UserDashboard()
                // Переход по адресу /dashboard и автоматическая проверка "Welcome, noname!"
                .open()
                // Переходим на страницу профиля
                .navigateToEditProfile()
                // Убеждаемся, что полей для ввода пароля физически нет на этой форме
                .verifyPasswordFieldsDoNotExist();
    }

    @Test
    public void AttemptToSetANameConsistingOfOnlyOneWordUiTest() {

        // создаем юзера
        CreateUserRequest user = AdminSteps.createUser();

        authAsUser(user);

        // 3. UI-шаги через цепочку Page Object
        new UserDashboard()
                // Переход по адресу /dashboard и автоматическая проверка "Welcome, noname!"
                .open()
                // Переходим на страницу профиля через наш готовый метод
                .navigateToEditProfile()
                // Вводим некорректное имя, состоящее всего из одного слова
                .enterNewName(RandomData.getUsername())
                // Нажимаем кнопку Save Changes
                .clickSave()
                // Проверяем алерт валидации
                .checkAlertMessageAndAccept(BankAlert.INVALID_NAME_FORMAT);
        //БАГ ФРОНТА. Некорректно выводит сообщение.
    }
}