package iteration2;

import constants.ErrorMessages;
import generators.RandomData;
import models.*;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import requests.AdminCreateUserRequester;
import requests.LoginUserRequester;
import requests.UpdateProfileRequester;
import requests.UserGetProfileRequester;
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
                .header(HttpHeaders.AUTHORIZATION);

        return userRequest;
    }

    @Test
    public void SuccessfulNameChangeToAValidFormat() {
        CreateUserRequest user = createAndAuthorizeUser();
        String randomName = RandomData.getUsername() + " " + RandomData.getUsername();
        // Обновляем имя пользователя на валидное (Имя Фамилия)
        new UpdateProfileRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK()
        ).put(UpdateProfileRequest.builder()
                .name(randomName)
                .build());
        // Проверяем методом гет, что имя действительно записалось в бд. !БАГ! имя по прежнему null
        CreateUserResponse profile = new UserGetProfileRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(
                        CreateUserResponse.class
                );
        softly.assertThat(profile.getName()).as("Проверка обновленного имени пользователя")
                .isEqualTo(randomName);
    }

    @Test
    public void PasswordChangeAttempt() {
        CreateUserRequest user = createAndAuthorizeUser();

        // !БАГ! Эндпойнт предназначен для смены имени, но может поменять пароль.
        new UpdateProfileRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.PASSWORD_CHANGE_NOT_ALLOWED)
        ).put(UpdateProfileRequest.builder()
                .name(RandomData.getUsername() + " " + RandomData.getUsername())
                .password(user.getPassword() + "1") // Передаем измененный пароль
                .build());
        // Проверка с помощью гет запроса, что из-за ошибки запроса имя пользователя не изменилось (осталось null)
        CreateUserResponse profile = new UserGetProfileRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(CreateUserResponse.class);
        softly.assertThat(profile.getName()).as("Имя должно остаться null при ошибке запроса")
                .isNull();
    }

    @Test
    public void AttemptToSetANameConsistingOfOnlyOneWord() {
        CreateUserRequest user = createAndAuthorizeUser();

        // Негативный тест: имя только из одного слова
        new UpdateProfileRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsBadRequest(ErrorMessages.INVALID_NAME_FORMAT)
        ).put(UpdateProfileRequest.builder()
                .name(RandomData.getUsername())
                .build());
        // Проверка с помощью гет запроса, что поле name осталось null
        CreateUserResponse profile = new UserGetProfileRequester(
                RequestSpecs.authAsUser(user.getUsername(), user.getPassword()),
                ResponseSpecs.requestReturnsOK())
                .get()
                .extract()
                .as(CreateUserResponse.class);
        softly.assertThat(profile.getName()).as("Поле name должно остаться null").isNull();
    }
}