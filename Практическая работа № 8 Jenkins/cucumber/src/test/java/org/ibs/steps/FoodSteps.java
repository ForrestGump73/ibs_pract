package org.ibs.steps;

import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.То;
import org.ibs.managers.DriverManager;
import org.ibs.managers.PageManager;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class FoodSteps {

    /**
     * Менеджер страничек
     *
     * @see PageManager#getPageManager()
     */
    protected PageManager app = PageManager.getPageManager();

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private final DriverManager driverManager = DriverManager.getDriverManager();


    @И("Открыта страница по адресу {string}")
    public void beforeStep(String url) {
        driverManager.getDriver().get(url);
    }

    @И("Выполнено нажатие на {string}")
    public void clickButton(String button) {
        switch (button) {
            case "Меню":
                app.getHomePage().selectBaseMenu();
                break;
            case "Товары":
                app.getHomePage().selectProducts();
                break;
            case "Добавить":
                app.getProductsPage().selectButtonEdit();
                break;
            case "Сохранить":
                app.getProductsPage().saveProduct();
                break;
            default:
                Assertions.fail("Такой кнопки нет");
                break;
        }
    }

    @И("Кнопка {string} видима")
    public void buttonIsVisible(String button) {
        switch (button) {
            case "Меню":
                Assertions.assertTrue(app.getHomePage().getDropMenu().isDisplayed());
                break;
            case "Товары":
                Assertions.assertTrue(app.getHomePage().getProducts().isDisplayed());
                break;
            case "Добавить":
                Assertions.assertTrue(app.getProductsPage().getBtnEdit().isDisplayed());
                break;
            case "Сохранить":
                Assertions.assertTrue(app.getProductsPage().getBtnSave().isDisplayed());
                break;
            default:
                Assertions.fail("Кнопки не видно либо некорректно указано название кнопки");
                break;
        }
    }

    @И("Открыта страница {string}")
    public void openPage(String page) {
        switch (page) {
            case "Добавление товара":
                app.getProductsPage().checkOpenAddProduct();
                break;
            case "Товары":
                app.getProductsPage().checkOpenProductsPage();
                break;
            default:
                Assertions.fail("404 Error - Not found");
                break;
        }
    }

    @И("Поле {string} видимо")
    public void fieldIsVisible(String field) {
        if (field.equals("Наименование")) {
            Assertions.assertTrue(app.getProductsPage().getFieldName().isDisplayed());
        } else
            Assertions.fail("Поля не видно");
    }

    @И("Поле {string} заполняется значением {string}")
    public void fillFieldName(String field, String value) {
        if (field.equals("Наименование")) {
            app.getProductsPage().fillFieldName(value);
        } else
            Assertions.fail("Поле с именем " + field + " отсутствует на странице");
    }

    @И("Селектор {string} виден")
    public void selectorToBeVisible(String selector) {
        if (selector.equals("Тип продукта")) {
            app.getProductsPage().getSelectType().isDisplayed();
        } else
            Assertions.fail("Селектор " + selector + " отсутствует на странице");
    }

    @И("Из опций селектора выбираем {string}")
    public void selectOptionOfSelector(String option) {
        app.getProductsPage().selectTypeOfProduct(option);
    }

    @И("Чекбокс {string} виден")
    public void checkboxIsVisible(String checkbox) {
        if (checkbox.equals("Экзотический")) {
            app.getProductsPage().getCheckboxExotic().isDisplayed();
        } else
            Assertions.fail("Чек бокс " + checkbox + " отсутствует на странице");
    }

    @Если("Чекбокс {string} выключен")
    public void ifCheckboxOn(String checkbox) {
        if (checkbox.equals("Экзотический")) {
            Assertions.assertFalse(app.getProductsPage().getCheckboxExotic().isSelected());
        } else
            Assertions.fail("Чек бокс " + checkbox + " отсутствует на странице");
    }

    @То("Включаем чекбокс {string}")
    public void checkboxOn(String checkbox) {
        if (checkbox.equals("Экзотический")) {
            app.getProductsPage().checkboxExoticOn();
        } else
            Assertions.fail("Чек бокс " + checkbox + " отсутствует на странице");
    }

    @Если("Чекбокс {string} включен")
    public void ifCheckboxOff(String checkbox) {
        if (checkbox.equals("Экзотический")) {
            app.getProductsPage().getCheckboxExotic().isSelected();
        } else
            Assertions.fail("Чек бокс " + checkbox + " отсутствует на странице");
    }

    @То("Выключаем чекбокс {string}")
    public void checkboxOff(String checkbox) {
        if (checkbox.equals("Экзотический")) {
            app.getProductsPage().checkboxExoticOff();
        } else
            Assertions.fail("Чек бокс " + checkbox + " отсутствует на странице");
    }


    @И("Поля добавленного товара соответствуют значениям:")
    public void checkLastProductAdded(Map<String, String> map) {
        app.getProductsPage().checkLastProductName(map.get("Наименование"));
        app.getProductsPage().checkLastProductType(map.get("Тип"));
        app.getProductsPage().checkLastProductExotic(map.get("Экзотический"));


    }


}
