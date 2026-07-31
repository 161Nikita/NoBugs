package requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.UserTransferAccountRequest;
import models.UserTransferAccountResponse;

import static io.restassured.RestAssured.given;

public class UserTransferAccountRequester extends Request<UserTransferAccountRequest> {
    public UserTransferAccountRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    @Override
    public ValidatableResponse post(UserTransferAccountRequest model) {
        return given()
                .spec(requestSpecification)
                .body(model)
                .post("/api/v1/accounts/transfer")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    public UserTransferAccountResponse postAndGetBody(UserTransferAccountRequest model) {
        return post(model)
                .extract()
                .as(UserTransferAccountResponse.class);
    }
}
