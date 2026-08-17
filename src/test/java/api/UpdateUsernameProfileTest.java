package api;

import constants.ErrorMessages;
import models.CreateUserRequest;
import models.UpdateProfileRequest;
import org.junit.jupiter.api.Test;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

public class UpdateUsernameProfileTest extends BaseTest {

    @Test
    public void SuccessfulNameChangeToAValidFormat() {
        CreateUserRequest user = createAndAuthorizeUser();

        // Обновляем имя пользователя на валидное (Имя Фамилия)
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.UPDATE_PROFILE,
                ResponseSpecs.requestReturnsOK()
        ).update(UpdateProfileRequest.builder()
                .name("Nikita Krapivin")
                .build());
    }

    @Test
    public void PasswordChangeAttempt() {
        CreateUserRequest user = createAndAuthorizeUser();

        // !БАГ! Эндпойнт предназначен для смены имени, но может поменять пароль.
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.UPDATE_PROFILE,
                ResponseSpecs.requestReturnsOK()
        ).update(UpdateProfileRequest.builder()
                .name("Nikita Krapivin")
               // .password(user.getPassword() + "1") // Передаем измененный пароль для проверки бага
                .build());
    }

    @Test
    public void AttemptToSetANameConsistingOfOnlyOneWord() {
        CreateUserRequest user = createAndAuthorizeUser();

        // Негативный тест: имя только из одного слова
        new CrudRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                Endpoint.UPDATE_PROFILE,
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.INVALID_NAME_FORMAT)
        ).update(UpdateProfileRequest.builder()
                .name("Krapivin")
                .build());
    }
}