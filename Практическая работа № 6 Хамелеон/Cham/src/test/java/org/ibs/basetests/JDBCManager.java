package org.ibs.basetests;


import io.qameta.allure.Step;
import org.h2.jdbc.JdbcSQLDataException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCManager {

    public static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb", "user", "pass");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод выполняет запрос с некорректными данными для попытки добавления продукта в БД
     * @param foodId ID продукта
     * @param foodName наименование продукта
     * @param foodType тип продукта
     * @param foodExotic экзотичность продукта
     */

    @Step("Ввод неверных значений {foodId}, {foodName}, {foodType}, {foodExotic}")
    public static void insertInvalidValues(Integer foodId, String foodName, String foodType, Integer foodExotic) throws SQLException{
        String insert = "insert into food(food_id, food_name, food_type, food_exotic) values (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, foodId);
            preparedStatement.setString(2, foodName);
            preparedStatement.setString(3,foodType);
            preparedStatement.setInt(4,foodExotic);
            preparedStatement.executeUpdate();
        } catch (JdbcSQLIntegrityConstraintViolationException | JdbcSQLDataException e) {
            System.out.println(e.getMessage());
        }


    }

    public static String checkNull(String string) {
        if (string.equals("NULL") || string.equals("null")) {
            return null;
        } return string;
    }


}
