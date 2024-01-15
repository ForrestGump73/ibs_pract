package org.ibs.jdbc.tests;

import io.qameta.allure.*;
import org.ibs.jdbc.basetests.NullableConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ibs.jdbc.basetests.JDBCManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.*;


@DisplayName("JDBC")
public class AppTest extends JDBCManager{

    @Description("Тест выполняет проверку добавления продукта в БД с невалидными значениями")
    @Epic("IBS Стажировка")
    @Feature("Автотестирование")
    @Story("JDBC")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тестирование БД невалидными значениями")
    @ParameterizedTest
    @CsvSource({
            "5, null, null,12",
            "1, Яблоко, FRUIT, 0",
            "1, Банан, FRUIT, 12",
            "1, Банан, null, 0",
            "5, Банан, null, 0",
            "5, Банан, FRUIT, 12",
            "5, null, FRUIT, 0",
            "1, null, null, 12"
    } )
    public void testInvalidValues(int food_id,
                                  @ConvertWith(NullableConverter.class) String food_name,
                                  @ConvertWith(NullableConverter.class) String food_type,
                                  int food_exotic)
            throws SQLException{
        getStatement();
        insertInvalidValues(food_id,food_name,food_type,food_exotic);
    }

    @Description("Тест выполняет проверку добавления продукта в БД с валидными значениями")
    @Epic("IBS Стажировка")
    @Feature("Автотестирование")
    @Story("JDBC")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тестирование БД валидными значениями")
    @Test
    public void testValidValues() throws SQLException {
        getStatement();
        insertIntoFood(5, "Банан", "FRUIT", 0);
        deleteById(5);
    }

}

