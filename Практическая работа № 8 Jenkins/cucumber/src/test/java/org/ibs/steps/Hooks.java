package org.ibs.steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.ibs.managers.DriverManager;
import org.ibs.managers.InitManager;
import org.ibs.managers.PageManager;


public class Hooks {

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
