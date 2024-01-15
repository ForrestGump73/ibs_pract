package org.ibs.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ibs.basetestclass.BaseTests;

@DisplayName("Проверка добавления товара в БД")
public class PracticeTests extends BaseTests {

    @Description("Тест выполняет проверку добавления овоща в БД")
    @Epic("IBS Стажировка")
    @Feature("Автотестирование")
    @Story("Тестирование добавления товаров в БД")
    @Severity(SeverityLevel.MINOR)
    @Test
    @DisplayName("Тестирование добавления овоща")
    public void startVegTest() {
        app.getHomePage()
                .selectBaseMenu()
                .selectProducts()
                .checkOpenProductsPage()
                .selectButtonEdit()
                .checkOpenAddProduct()
                .fillFieldName("Картошка")
                .selectTypeOfProduct("Овощ")
                .checkboxExoticOff()
                .saveProduct()
                .checkLastProductAdded("Картошка", "Овощ", "false");

    }

    @Description("Тест выполняет проверку добавления овоща в БД")
    @Epic("IBS Стажировка")
    @Feature("Автотестирование")
    @Story("Тестирование добавления товаров в БД")
    @Test
    @DisplayName("Тестирование добавления фрукта")
    public void startFruitTest() {
        app.getHomePage()
                .selectBaseMenu()
                .selectProducts()
                .checkOpenProductsPage()
                .selectButtonEdit()
                .checkOpenAddProduct()
                .fillFieldName("Манго")
                .selectTypeOfProduct("Фрукт")
                .checkboxExoticOn()
                .saveProduct()
                .checkLastProductAdded("Манго", "Фрукт", "true");

    }
}
