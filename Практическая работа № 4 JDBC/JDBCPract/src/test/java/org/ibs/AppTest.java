package org.ibs;

import org.ibs.basetests.NullableConverter;
import org.junit.jupiter.api.Test;
import org.ibs.basetests.JDBCManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.*;


public class AppTest extends JDBCManager {


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
    })
    public void testInvalidValues(int food_id,
                                  @ConvertWith(NullableConverter.class) String food_name,
                                  @ConvertWith(NullableConverter.class) String food_type,
                                  int food_exotic)
            throws SQLException {
        getStatement();
        insertInvalidValues(food_id, food_name, food_type, food_exotic);

    }

    @Test
    public void testValidValues() throws SQLException {
        getStatement();
        int foodId = getLastFoodID() + 1;
        insertIntoFood(foodId, "Банан", "FRUIT", 0);
        checkInsert(foodId);
        deleteById(foodId);
    }


}
