package org.ibs.steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
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

    @Before("@CucumberTest=1")
    public static void beforeProduct() {
        InitManager.initFramework();
    }

    @After("@CucumberTest=2")
    public static void afterFruit() {
        InitManager.quitFramework();
    }

}
