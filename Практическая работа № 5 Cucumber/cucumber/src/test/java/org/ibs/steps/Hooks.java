package org.ibs.steps;


import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.ibs.managers.DriverManager;
import org.ibs.managers.InitManager;
import org.ibs.managers.PageManager;


public class Hooks {

    /**
     * Менеджер страничек
     * @see PageManager#getPageManager()
     */
    protected PageManager app = PageManager.getPageManager();

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @Before("@product")
    public static void beforeProduct() {
        InitManager.initFramework();
    }

    @After("@product")
    public static void afterFruit() {
        InitManager.quitFramework();
        PageManager.newPageManager();
    }

}
