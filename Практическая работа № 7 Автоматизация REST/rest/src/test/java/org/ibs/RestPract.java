package org.ibs;


import io.qameta.allure.*;
import io.restassured.http.Cookies;
import org.ibs.pojos.FoodPojo;
import org.ibs.spec.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

@DisplayName("Тестирование REST API")
public class RestPract {


    @Description("Тест выполняет GET-запрос и добавляет в Pojo")
    @Epic("IBS Стажировка")
    @Feature("Автотестирование")
    @Story("Тестирование REST API")
    @Severity(SeverityLevel.MINOR)
    @Test
    @DisplayName("Тест GET-запроса")
    public void testGet() {
        Specifications.installSpecification(Specifications.requestSpecification("http://localhost:8080/"),
                Specifications.responseSpecification(200, "application/json"));

        List<FoodPojo> foodList = given()
                .basePath("api/food")
                .when()
                .log().all()
                .get()
                .then()
                .log().all()
                .extract()
                .jsonPath().getList(basePath, FoodPojo.class);
        for (FoodPojo pojo : foodList) {
            System.out.printf("%s %s %s \n", pojo.getName(), pojo.getType(), pojo.isExotic());

        }

    }

    @Description("Тест выполняет POST-запрос")
    @Epic("IBS Стажировка")
    @Feature("Автотестирование")
    @Story("Тестирование REST API")
    @Severity(SeverityLevel.MINOR)
    @Test
    @DisplayName("Тест POST-запроса")
    public void testPost() {
        Specifications.installSpecification(Specifications.requestSpecification("http://localhost:8080/", "application/json"),
                Specifications.responseSpecification(200));


        given()
                .body("{\n" +
                        "  \"name\": \"Картошка\",\n" +
                        "  \"type\": \"VEGETABLE\",\n" +
                        "  \"exotic\": false\n" +
                        "}")
                .when()
                .log().all()
                .post("api/food")
                .then()
                .log().all();
    }

    @Description("Тест выполняет проверку добавления овоща")
    @Epic("IBS Стажировка")
    @Feature("Автотестирование")
    @Story("Тестирование REST API")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Тест овоща")
    public void testVeg() {
        Specifications.installSpecification(Specifications.requestSpecification("http://localhost:8080/", "application/json"),
                Specifications.responseSpecification(200));


        Cookies cookies = given()
                .body("{\n" +
                        "  \"name\": \"Картошка\",\n" +
                        "  \"type\": \"VEGETABLE\",\n" +
                        "  \"exotic\": false\n" +
                        "}")
                .when()
                .log().all()
                .post("api/food")
                .then()
                .log().all()
                .extract()
                .response()
                .getDetailedCookies();


        List<FoodPojo> foodList = given()
                .cookies(cookies)
                .basePath("api/food")
                .when()
                .log().all()
                .get()
                .then()
                .log().all()
                .extract()
                .jsonPath().getList(basePath, FoodPojo.class);

        String[] strings = {"Картошка", "VEGETABLE", "false"};
        Assertions.assertAll(
                () -> Assertions.assertEquals(strings[0], foodList.get(foodList.size() - 1).getName()),
                () -> Assertions.assertEquals(strings[1], foodList.get(foodList.size() - 1).getType()),
                () -> Assertions.assertFalse(foodList.get(foodList.size() - 1).isExotic()));
    }

    @Description("Тест выполняет проверку добавления фрукта")
    @Epic("IBS Стажировка")
    @Feature("Автотестирование")
    @Story("Тестирование REST API")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Тест фрукта")
    public void testFruit() {
        Specifications.installSpecification(Specifications.requestSpecification("http://localhost:8080/", "application/json"),
                Specifications.responseSpecification(200));


        Cookies cookies = given()
                .body("{\n" +
                        "  \"name\": \"Манго\",\n" +
                        "  \"type\": \"FRUIT\",\n" +
                        "  \"exotic\": true\n" +
                        "}")
                .when()
                .log().all()
                .post("api/food")
                .then()
                .log().all()
                .extract()
                .response()
                .getDetailedCookies();


        List<FoodPojo> foodList = given()
                .cookies(cookies)
                .basePath("api/food")
                .when()
                .log().all()
                .get()
                .then()
                .log().all()
                .extract()
                .jsonPath().getList(basePath, FoodPojo.class);

        String[] strings = {"Манго", "FRUIT", "true"};
        Assertions.assertAll(
                () -> Assertions.assertEquals(strings[0], foodList.get(foodList.size() - 1).getName()),
                () -> Assertions.assertEquals(strings[1], foodList.get(foodList.size() - 1).getType()),
                () -> Assertions.assertTrue(foodList.get(foodList.size() - 1).isExotic()));
    }
}

