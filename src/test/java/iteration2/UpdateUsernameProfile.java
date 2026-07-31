package iteration2;

import constants.ErrorMessages;
import generators.RandomData;
import models.CreateUserRequest;
import models.LoginUserRequest;
import models.UpdateProfileRequest;
import models.UserRole;
import org.junit.jupiter.api.Test;
import requests.AdminCreateUserRequester;
import requests.LoginUserRequester;
import requests.UpdateProfileRequester;
import specs.RequestSpecs;
import specs.ResponseSpecs;

public class UpdateUsernameProfile extends BaseTest {

    private CreateUserRequest createAndAuthorizeUser() {
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .username(RandomData.getUsername())
                .password(RandomData.getPassword())
                .role(UserRole.USER.toString())
                .build();

        LoginUserRequest loginUserRequest = LoginUserRequest.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();

        new AdminCreateUserRequester(
                RequestSpecs.adminSpec(),
                ResponseSpecs.entityWasCreated())
                .post(userRequest);

        new LoginUserRequester(
                RequestSpecs.unauthSpec(),
                ResponseSpecs.requestReturnsOK())
                .post(loginUserRequest)
                .extract()
                .header("Authorization");

        return userRequest;
    }

    @Test
    public void SuccessfulNameChangeToAValidFormat() {
        CreateUserRequest user = createAndAuthorizeUser();

        // Обновляем имя пользователя на валидное (Имя Фамилия)
        new UpdateProfileRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK()
        ).put(UpdateProfileRequest.builder()
                .name("Nikita Krapivin")
                .build());
    }

    @Test
    public void PasswordChangeAttempt() {
        CreateUserRequest user = createAndAuthorizeUser();

        // !БАГ! Эндпойнт предназначен для смены имени, но может поменять пароль.
        new UpdateProfileRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.PASSWORD_CHANGE_NOT_ALLOWED)
        ).put(UpdateProfileRequest.builder()
                .name("Nikita Krapivin")
                .password(user.getPassword() + "1") // Передаем измененный пароль
                .build());
    }

    @Test
    public void AttemptToSetANameConsistingOfOnlyOneWord() {
        CreateUserRequest user = createAndAuthorizeUser();

        // Негативный тест: имя только из одного слова
        new UpdateProfileRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.INVALID_NAME_FORMAT)
        ).put(UpdateProfileRequest.builder()
                .name("Krapivin")
                .build());
    }
}