package org.ibs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@id = 'navbarDropdown']")
    private WebElement dropMenu;

    @FindBy(xpath = "//a[text() = 'Товары']")
    private WebElement products;

    /**
     * Метод, кликающий на меню "Песочница"
     */
    public HomePage selectBaseMenu() {
        waitUtilElementToBeClickable(dropMenu);
        dropMenu.click();
        return this;
    }


    /**
     * Метод выбирает из Песочницы "Товары"
     */
    public ProductsPage selectProducts() {
        waitUtilElementToBeClickable(products);
        products.click();
        return pageManager.getProductsPage();
    }


}
