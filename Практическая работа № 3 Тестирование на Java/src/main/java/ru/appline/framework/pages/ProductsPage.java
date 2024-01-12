package ru.appline.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage extends BasePage {

    @FindBy(xpath = "//a[@id = 'navbarDropdown']")
    private WebElement dropMenu;

    @FindBy(xpath = "//a[text() = 'Товары']")
    private WebElement products;

    @FindBy(xpath = "//h5[text() = 'Список товаров']")
    private WebElement title;


    @FindBy(xpath = "//button[text() = 'Добавить']")
    private WebElement btnEdit;

    @FindBy(xpath = "//div[@class = 'modal fade show']")
    private WebElement editModal;

    @FindBy(id = "name")
    private WebElement fieldName;

    @FindBy(id = "type")
    private WebElement selectType;

    @FindBy(xpath = "//option[@value = \"VEGETABLE\"]")
    private WebElement vegType;

    @FindBy(xpath = "//option[@value = \"FRUIT\"]")
    private WebElement fruitType;

    @FindBy(id = "exotic")
    private WebElement checkboxExotic;

    @FindBy(id = "save")
    private WebElement btnSave;

    @FindBy(xpath = "//tbody/tr[last()]/td[1]")
    private WebElement lastProductName;

    @FindBy(xpath = "//tbody/tr[last()]/td[2]")
    private WebElement lastProductType;

    @FindBy(xpath = "//tbody/tr[last()]/td[3]")
    private WebElement lastProductExotic;

    @FindBy(xpath = "//tbody/tr[last()]/th")
    private WebElement lastProductID;


    /**
     * Метод проверяет нахождение на странице Товары
     */
    public ProductsPage checkOpenProductsPage() {
        waitVisibilityOfElementLocated("//h5[text() = 'Список товаров']");
        Assertions.assertEquals("Список товаров", title.getText());
        return this;
    }


    /**
     * Метод нажимает на кнопку "Добавить"
     */
    public ProductsPage selectButtonEdit() {
        scrollToElementJs(btnEdit);
        waitUtilElementToBeClickable(btnEdit).click();
        return this;
    }


    /**
     * Метод проверяет открытие модального окна "Добавление товара"
     */
    public ProductsPage checkOpenAddProduct() {
        Assertions.assertEquals("modal fade show", editModal.getAttribute("class"));
        return this;
    }


    /**
     * Метод заполняет поле "Наименование"
     *
     * @param value  Наименование продукта
     */
    public ProductsPage fillFieldName(String value) {
        scrollToElementJs(fieldName);
        waitUtilElementToBeClickable(fieldName).click();
        fieldName.sendKeys(value);
        return this;
    }

    /**
     * Метод выбирает тип Продукта
     *
     * @param value  Тип продукта
     */
    public ProductsPage selectTypeOfProduct(String value) {
        scrollToElementJs(selectType);
        waitUtilElementToBeClickable(selectType).click();
        switch (value) {
            case "Овощ":
                vegType.click();
                break;
            case "Фрукт":
                fruitType.click();
                break;
            default:
                Assertions.fail("Тип " + value + " отсуствует в перечне типов");
        }
        return this;
    }

    /**
     * Метод включает чекбокс Экзотический
     */
    public ProductsPage checkboxExoticOn() {
        if (!checkboxExotic.isSelected()) {
            checkboxExotic.click();
        }
        return this;

    }

    /**
     * Метод выключает чекбокс Экзотический
     */
    public ProductsPage checkboxExoticOff() {
        if (checkboxExotic.isSelected()) {
            checkboxExotic.click();
        }
        return this;

    }


    /**
     * Метод добавляет продукт в БД
     */
    public ProductsPage saveProduct() {
        scrollToElementJs(btnSave);
        waitUtilElementToBeClickable(btnSave).click();
        return this;
    }


    /**
     * Проверка соответствия Наименования добавленного товара требуемому значению
     *
     * @param value требуемое наименование товара
     */
    public ProductsPage checkLastProductName(String value) {
        waitVisibilityOfElementLocated("//tbody/tr[last()]/td[1]");
        Assertions.assertEquals(value.trim().toLowerCase(),lastProductName.getText().trim().toLowerCase());
        return this;
    }


    /**
     * Проверка соответствия Типа добавленного товара требуемому значению
     *
     *@param value требуемый тип товара
     */
    public ProductsPage checkLastProductType(String value) {
        waitVisibilityOfElementLocated("//tbody/tr[last()]/td[2]");
        Assertions.assertEquals(value.trim().toLowerCase(),lastProductType.getText().trim().toLowerCase());
        return this;
    }


    /**
     * Проверка соответствия Экзотичности добавленного товара требуемому значению
     *
     * @param value требуемая экзотичность товара
     */
    public ProductsPage checkLastProductExotic(String value) {
        waitVisibilityOfElementLocated("//tbody/tr[last()]/td[3]");
        Assertions.assertEquals(value.trim().toLowerCase(),lastProductExotic.getText().trim().toLowerCase());
        return this;
    }

    /**
     * Проверка соответствия добавленного товара требуемому значению
     *
     * @param name требуемое наименование товара
     * @param type требуемый тип товара
     * @param exotic требуемая экзотичность товара
     */
    public ProductsPage checkLastProductAdded(String name, String type, String exotic) {
        checkLastProductName(name);
        checkLastProductType(type);
        checkLastProductExotic(exotic);
        return this;
    }
}