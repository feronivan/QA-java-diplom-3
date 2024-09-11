package steps;

import config.BaseConfig;
import io.qameta.allure.Step;
import user.User;

import static config.EndPoints.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;

public class UserSteps {

    @Step("Создание пользователя")
    public void userCreate(User user) {
        given()
                .spec(BaseConfig.spec())
                .body(user)
                .when()
                .post(USER_CREATE)
                .then()
                .statusCode(SC_OK);
    }

    @Step("Получение токена пользователя")
    public String getAccessToken(User user) {
        return given()
                .spec(BaseConfig.spec())
                .body(user)
                .when()
                .post(USER_LOGIN)
                .then()
                .extract()
                .path("accessToken")
                .toString();
    }

    @Step("Удаление пользователя")
    public void userDelete(String accessToken) {
        given()
                .spec(BaseConfig.spec())
                .headers("authorization", accessToken)
                .when()
                .delete(USER_EDIT)
                .then()
                .statusCode(SC_ACCEPTED);
    }
}