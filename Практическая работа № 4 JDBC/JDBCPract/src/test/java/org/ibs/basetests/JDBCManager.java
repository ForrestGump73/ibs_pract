package org.ibs.basetests;

import org.h2.jdbc.JdbcSQLDataException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.*;

public class JDBCManager {

    public static int countTests = 1;
    public static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb", "user", "pass");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * Метод выполняет запрос для добавления продукта в БД
     * @param foodId ID продукта
     * @param foodName наименование продукта
     * @param foodType тип продукта
     * @param foodExotic экзотичность продукта
     */
    public void insertIntoFood(Integer foodId, String foodName, String foodType, Integer foodExotic){
        String insert = "insert into food(food_id, food_name, food_type, food_exotic) values (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, foodId);
            preparedStatement.setString(2, foodName);
            preparedStatement.setString(3,foodType);
            preparedStatement.setInt(4,foodExotic);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Запись добавлена в БД");

    }

    /**
     * Метод выполняет запрос с некорректными данными для попытки добавления продукта в БД
     * @param foodId ID продукта
     * @param foodName наименование продукта
     * @param foodType тип продукта
     * @param foodExotic экзотичность продукта
     */
    public void insertInvalidValues(Integer foodId, String foodName, String foodType, Integer foodExotic) throws SQLException{
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

    /**
     * Удаляет продукт из БД по FOOD_ID
     * @param foodId ID продукта
     */
    public void deleteById(Integer foodId) {
        String delete = "delete from FOOD where food_id = " + foodId;
        try {
            getStatement().execute(delete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Запуск тестов...");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Запуск теста №" + countTests);
    }

    @AfterEach
    public void afterEach() {
        countTests++;
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Все тесты завершены");
    }


}
