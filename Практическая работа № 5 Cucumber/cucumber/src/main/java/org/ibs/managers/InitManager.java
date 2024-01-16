package org.ibs.managers;

import java.time.Duration;


import static org.ibs.utils.PropConst.*;

/**
 * Класс для инициализации фреймворка
 */
public class InitManager {

    /**
     * Менеджер properties
     *
     * @see TestPropManager#getTestPropManager()
     */
    private static final TestPropManager props = TestPropManager.getTestPropManager();

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private static final DriverManager driverManager = DriverManager.getDriverManager();

    /**
     * Инициализация framework и запуск браузера со страницей приложения
     *
     * @see DriverManager
     * @see TestPropManager#getProperty(String)
     * @see org.ibs.utils.PropConst
     */
    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait((Duration.ofSeconds(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)))));
        driverManager.getDriver().manage().timeouts().pageLoadTimeout((Duration.ofSeconds(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)))));
    }

    /**
     * Завершения работы framework - гасит драйвер и закрывает сессию с браузером
     *
     * @see DriverManager#quitDriver()
     */
    public static void quitFramework() {
        driverManager.quitDriver();
    }
}

