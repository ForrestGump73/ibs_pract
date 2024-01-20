package org.ibs.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.restassured.http.Cookies;
import org.ibs.pojos.FoodPojo;
import org.ibs.specs.Specifications;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class RestSteps {

    public static Cookies cookies;

    public static List<FoodPojo> foodPojos;

    public static List<String> fields;

    public static List<String> values;

    @И("Установка спек {string}, {string}, {int} c response.content-type")
    public void specInstall(String url, String responseContentType, int statusCode) {
        Specifications.installSpecification(Specifications.requestSpecification(url),
                Specifications.responseSpecification(statusCode, responseContentType));
    }

    @И("Установка спек url={string}, statuscode= {int}, request.content-type = {string} , basePath= {string}")
    public void specInstall(String url, int statusCode, String requestContentType, String basePath) {
        Specifications.installSpecification(Specifications.requestSpecification(url, requestContentType, basePath),
                Specifications.responseSpecification(statusCode));
    }

    @И("Выполнение GET-запроса, basePath = {string}")
    public void getRequest(String basePath) {
        given()
                .basePath(basePath)
                .when()
                .log().all()
                .get()
                .then()
                .log().all();
    }

    @И("Выполнение POST-запроса")
    public void postRequest(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        fields = new ArrayList<>(rows.get(0));
        values = new ArrayList<>(rows.get(1));
        given()
                .basePath(basePath)
                .body("{\n" +
                        "  "+fields.get(0)+": "+values.get(0)+",\n" +
                        "  "+fields.get(1)+": "+values.get(1)+",\n" +
                        "  "+fields.get(2)+": "+values.get(2)+"\n" +
                        "}")
                .when()
                .log().all()
                .post()
                .then()
                .log().all();
    }

    @И("Выполнение POST-запроса и получение Cookies")
    public void postRequestAndGetCookies(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        fields = new ArrayList<>(rows.get(0));
        values = new ArrayList<>(rows.get(1));
        cookies = given()
                .body("{\n" +
                        "  \""+fields.get(0)+"\": \""+values.get(0)+"\",\n" +
                        "  \""+fields.get(1)+"\": \""+values.get(1)+"\",\n" +
                        "  \""+fields.get(2)+"\": "+values.get(2)+"\n" +
                        "}")
                .when()
                .log().all()
                .post()
                .then()
                .log().all()
                .extract()
                .response()
                .getDetailedCookies();
    }

    @И("Получение Pojo")
    public void getPojo() {
        foodPojos = given()
                .cookies(cookies)
                .basePath("api/food")
                .when()
                .log().all()
                .get()
                .then()
                .log().all()
                .extract()
                .jsonPath().getList(basePath, FoodPojo.class);
    }

    @И("Проверка добавления продукта по API")
    public void checkAdd() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(values.get(0), foodPojos.get(foodPojos.size() - 1).getName()),
                () -> Assertions.assertEquals(values.get(1), foodPojos.get(foodPojos.size() - 1).getType()),
                () -> Assertions.assertEquals(values.get(2), String.valueOf(foodPojos.get(foodPojos.size() - 1).isExotic())));
    }
}
