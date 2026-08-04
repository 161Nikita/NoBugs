package requests;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.BaseModel;

import static io.restassured.RestAssured.given;

public class UserGetAccountsRequester extends Request<BaseModel> {

    public UserGetAccountsRequester(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        super(requestSpecification, responseSpecification);
    }

    // Реализуем обязательный метод родителя как заглушку (так как это GET)
    @Override
    public ValidatableResponse post(BaseModel model) {
        return null;
    }

    // Создаем собственный чистый GET-метод без параметров
    public ValidatableResponse get() {
        return given()
                .spec(requestSpecification)
                .get("/api/v1/customer/accounts")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }
}