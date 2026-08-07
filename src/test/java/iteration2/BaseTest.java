package iteration2;

import models.CreateUserRequest;
import models.CreateUserResponse;
import models.LoginUserRequest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import requests.skelethon.Endpoint;
import requests.skelethon.requesters.CrudRequester;
import requests.skelethon.steps.AdminSteps;
import specs.RequestSpecs;
import specs.ResponseSpecs;

public class BaseTest {
    protected SoftAssertions softly;
    protected long testUserId = -1;

    @BeforeEach
    public void setupTest() {
        this.softly = new SoftAssertions();
        this.testUserId = -1;
    }

    @AfterEach
    public void afterTest() {
        try {
            if (testUserId != -1) {
                AdminSteps.deleteUser(testUserId);
            }
        } catch (Exception e) {
            System.err.println("Не удалось очистить данные после теста: " + e.getMessage());
        } finally {
            softly.assertAll();
        }
    }

    protected CreateUserRequest createAndAuthorizeUser() {

        CreateUserRequest userRequest = generators.RandomModelGenerator.generate(CreateUserRequest.class);
        CreateUserResponse userResponse = AdminSteps.createUserFromRequest(userRequest);

        this.testUserId = userResponse.getId();

        LoginUserRequest loginUserRequest = LoginUserRequest.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();

        new CrudRequester(
                RequestSpecs.unauthSpec(),
                Endpoint.LOGIN,
                ResponseSpecs.requestReturnsOK())
                .post(loginUserRequest)
                .extract()
                .header("Authorization");

        return userRequest;
    }
}