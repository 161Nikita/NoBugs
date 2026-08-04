package iteration2;

import constants.ErrorMessages;
import generators.RandomData;
import models.CreateUserRequest;
import models.CreateUserResponse;
import models.UpdateProfileRequest;
import org.junit.jupiter.api.Test;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.requesters.ValidatedCrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

public class UpdateUsernameProfile extends BaseTest {

    @Test
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
                Endpoint.CUSTOMER_PROFILE, // Используем эндпоинт профиля
                ResponseSpecs.requestReturnsOK()
        ).getSingle();

        // Проверяем актуальное имя в профиле через softly
        softly.assertThat(profile.getName())
                .as("Проверка актуального имени пользователя после обновления профиля")
                .isEqualTo(randomName);
    }

    @Test
    public void PasswordChangeAttempt() {
        CreateUserRequest user = createAndAuthorizeUser();
        String invalidName = RandomData.getUsername() + " " + RandomData.getUsername();
        // !БАГ! Эндпойнт предназначен для смены имени, но может поменять пароль.
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.UPDATE_PROFILE,
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.PASSWORD_CHANGE_NOT_ALLOWED)
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
    }

    @Test
    public void AttemptToSetANameConsistingOfOnlyOneWord() {
        CreateUserRequest user = createAndAuthorizeUser();
        String invalidName = RandomData.getUsername();
        // Негативный тест: имя только из одного слова
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.UPDATE_PROFILE,
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.INVALID_NAME_FORMAT)
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
    }
}