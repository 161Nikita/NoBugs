package iteration2;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;


/*
 Тест "SuccessfulNameChangeToAValidFormat" падает, так как обнаружился баг бэкенда:
 метод PUT возвращает 200 OK, но последующий GET-запрос показывает, что в базе данных имя не сохранилось и осталось равным null.
 Проверил этот момент в Postman и поведение подтвердилось, данные действительно не перезаписываются в системе.
 Автотест написан корректно, я считаю, и правильно подсвечивает ошибку приложения.
 */

public class UpdateUsernameProfile {
    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.filters(
                List.of(new RequestLoggingFilter(),
                        new ResponseLoggingFilter()));
    }

    @Test
    public void SuccessfulNameChangeToAValidFormat() {
        Random random = new Random();
        int randomNumber = random.nextInt(500);
        String userName = "Nikita" + randomNumber;

        // создание пользователя
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body("""
                        {
                          "username": "%s",
                          "password": "Nikita133$",
                          "role": "USER"
                        }
                        """.formatted(userName))
                .post("http://localhost:4111/api/v1/admin/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);

        // получаем токен юзера
        String userAuthHeader = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        
                            {
                          "username": "%s",
                          "password": "Nikita133$"
                        }
                        """.formatted(userName))
                .post("http://localhost:4111/api/v1/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .header("Authorization");

        // изменение имени пользователя
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "name": "Nikita Krapivin"
                        }
                        """)
                // тут уязвимость, пароль можно поменять в методе если поставить в тело еще и "password": "Nikita133$1"
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        // проверка через GET, что имя поменялось
        given()
                .header("Authorization", userAuthHeader)
                .accept(ContentType.JSON)
                .get("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.equalTo("Nikita Krapivin"));
    }

    @Test
    public void PasswordChangeAttempt() { // падает потому что можно поменять пароль в теле метода
        Random random = new Random();
        int randomNumber = random.nextInt(500);
        String userName = "Nikita" + randomNumber;

        // создание пользователя
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body("""
                        {
                          "username": "%s",
                          "password": "Nikita133$",
                          "role": "USER"
                        }
                        """.formatted(userName))
                .post("http://localhost:4111/api/v1/admin/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);

        // получаем токен юзера
        String userAuthHeader = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        
                            {
                          "username": "%s",
                          "password": "Nikita133$"
                        }
                        """.formatted(userName))
                .post("http://localhost:4111/api/v1/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .header("Authorization");

        // изменение имени пользователя
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "name": "Nikita Krapivin",
                          "password": "Nikita133$1"
                        }
                        """)
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

        // проверка через GET, что имя НЕ поменялось (осталось null)
        given()
                .header("Authorization", userAuthHeader)
                .accept(ContentType.JSON)
                .get("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.nullValue());
    }

    @Test
    public void AttemptToSetANameConsistingOfOnlyOneWord() {
        Random random = new Random();
        int randomNumber = random.nextInt(500);
        String userName = "Nikita" + randomNumber;

        // создание пользователя
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .body("""
                        {
                          "username": "%s",
                          "password": "Nikita133$",
                          "role": "USER"
                        }
                        """.formatted(userName))
                .post("http://localhost:4111/api/v1/admin/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);

        // получаем токен юзера
        String userAuthHeader = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        
                            {
                          "username": "%s",
                          "password": "Nikita133$"
                        }
                        """.formatted(userName))
                .post("http://localhost:4111/api/v1/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .header("Authorization");

        // изменение имени пользователя
        given()
                .header("Authorization", userAuthHeader)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("""
                        {
                          "name": "Krapivin"
                        }
                        """)
                .put("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

        // проверка через GET, что некорректное имя НЕ применилось (осталось null)
        given()
                .header("Authorization", userAuthHeader)
                .accept(ContentType.JSON)
                .get("http://localhost:4111/api/v1/customer/profile")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("name", Matchers.nullValue());
    }
}
