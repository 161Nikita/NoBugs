package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

public class ResponseSpecs {
    private ResponseSpecs() {
    }

    private static ResponseSpecBuilder defaultResponseBuilder() {
        return new ResponseSpecBuilder();
    }

    public static ResponseSpecification entityWasCreated() {
        return defaultResponseBuilder()
                .expectStatusCode(HttpStatus.SC_CREATED)
                .build();
    }

    public static ResponseSpecification requestReturnsOK() {
        return defaultResponseBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static ResponseSpecification requestReturnsBadRequest(String errorKey, String errorValue) {
        return defaultResponseBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .expectBody(errorKey, Matchers.equalTo(errorValue))
                .build();
    }

    public static ResponseSpecification requestReturnsBadRequest(String errorValue) {
        return requestReturnsBadRequest("message", errorValue);
    }

    public static ResponseSpecification requestReturnsForbidden(String errorKey, String errorValue) {
        return defaultResponseBuilder()
                .expectStatusCode(HttpStatus.SC_FORBIDDEN)
                .expectBody(errorKey, Matchers.equalTo(errorValue))
                .build();
    }

    public static ResponseSpecification requestReturnsForbidden(String errorValue) {
        return requestReturnsForbidden("message", errorValue);
    }

    public static ResponseSpecification requestReturnsPlainBadRequest(String errorValue) {
        return defaultResponseBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                // Передаем только матчер, чтобы проверить весь текст ответа целиком, а не JSON-поле
                .expectBody(Matchers.equalTo(errorValue))
                .build();
    }

    public static ResponseSpecification requestReturnsPlainForbidden(String errorValue) {
        return defaultResponseBuilder()
                .expectStatusCode(org.apache.http.HttpStatus.SC_FORBIDDEN) // Проверяем строго 403 status
                // Проверяем весь текст ответа целиком (без привязки к JSON-ключам)
                .expectBody(org.hamcrest.Matchers.equalTo(errorValue))
                .build();
    }
}
