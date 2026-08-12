package iteration2.api;

import constants.ErrorMessages;
import generators.RandomData;
import iteration2.BaseTest;
import iteration2.api.dao.UserDao;
import models.CreateUserRequest;
import models.CreateUserResponse;
import models.UpdateProfileRequest;
import org.junit.jupiter.api.Test;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import requests.skelethon.steps.DataBaseSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;
import utils.APIVersion;

public class UpdateUsernameProfile extends BaseTest {

    @Test
    @APIVersion("with_database")
    public void SuccessfulNameChangeToAValidFormat() {
        CreateUserRequest user = createAndAuthorizeUser();
        String randomName = RandomData.getUsername() + " " + RandomData.getUsername();
        // Обновляем имя пользователя на валидное (Имя Фамилия)
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.UPDATE_PROFILE,
                ResponseSpecs.requestReturnsOK()
        ).update(UpdateProfileRequest.builder()
                .name(randomName)
                .build());
        CreateUserResponse profile = new ValidatedCrudRequester<CreateUserResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.CUSTOMER_PROFILE,
                ResponseSpecs.requestReturnsOK()
        ).getSingle();

        // Проверяем актуальное имя в профиле через softly
        softly.assertThat(profile.getName())
                .as("Проверка актуального имени пользователя после обновления профиля")
                .isEqualTo(randomName);

        // проверка через бд
        UserDao userDb = DataBaseSteps.getUserByUsername(user.getUsername());
        softly.assertThat(userDb.getName())
                .as("Проверка в Postgres: измененное имя пользователя успешно записалось в БД")
                .isEqualTo(randomName);
    }

    @Test
    @APIVersion("with_database")
    // !БАГ! Эндпойнт предназначен для смены имени, но может поменять пароль.
    public void PasswordChangeAttempt() {
        CreateUserRequest user = createAndAuthorizeUser();
        String invalidName = RandomData.getUsername() + " " + RandomData.getUsername();

        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.UPDATE_PROFILE,
                ResponseSpecs.requestReturnsPlainBadRequest(ErrorMessages.PASSWORD_CHANGE_NOT_ALLOWED)
        ).update(UpdateProfileRequest.builder()
                .name(invalidName)
                .password(user.getPassword() + "1") // Передаем измененный пароль для проверки бага
                .build());
        CreateUserResponse profile = new ValidatedCrudRequester<CreateUserResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.CUSTOMER_PROFILE,
                ResponseSpecs.requestReturnsOK()
        ).getSingle();
        // Проверяем актуальное имя в профиле через softly
        softly.assertThat(profile.getName())
                .as("Проверка, что имя профиля не изменилось на невалидное при ошибке смены пароля")
                .isNotEqualTo(invalidName);

        // проверка через бд
        UserDao userDb = DataBaseSteps.getUserByUsername(user.getUsername());
        softly.assertThat(userDb.getName())
                .as("Проверка в Postgres: имя пользователя в БД не изменилось")
                .isNotEqualTo(invalidName);
        // проверяем в БД, что пароль остался старым и безопасным
        softly.assertThat(userDb.getPassword())
                .as("Проверка в Postgres: пароль пользователя в БД остался прежним и не обновился")
                .isEqualTo(user.getPassword());
    }

    @Test
    @APIVersion("with_database")
    public void AttemptToSetANameConsistingOfOnlyOneWord() {
        CreateUserRequest user = createAndAuthorizeUser();
        String invalidName = RandomData.getUsername();
        // Негативный тест: имя только из одного слова
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.UPDATE_PROFILE,
                ResponseSpecs.requestReturnsPlainBadRequest(ErrorMessages.INVALID_NAME_FORMAT)
        ).update(UpdateProfileRequest.builder()
                .name(RandomData.getUsername())
                .build());
        CreateUserResponse profile = new ValidatedCrudRequester<CreateUserResponse>(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.CUSTOMER_PROFILE,
                ResponseSpecs.requestReturnsOK()
        ).getSingle();
        // Проверяем актуальное имя в профиле через softly
        softly.assertThat(profile.getName())
                .as("Проверка, что невалидное имя из одного слова не сохранилось в профиле")
                .isNotEqualTo(invalidName);

        // проверяем через бд
        UserDao userDb = DataBaseSteps.getUserByUsername(user.getUsername());
        // транзакция откатилась и невалидное имя не попало в таблицы
        softly.assertThat(userDb.getName())
                .as("Проверка в Postgres: невалидное имя пользователя не записалось в базу данных")
                .isNotEqualTo(invalidName);
    }
}