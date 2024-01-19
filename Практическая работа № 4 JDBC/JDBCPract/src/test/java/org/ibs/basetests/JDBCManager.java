package org.ibs.basetests;

import org.h2.jdbc.JdbcSQLDataException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.*;

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
     *
     * @param foodId     ID продукта
     * @param foodName   наименование продукта
     * @param foodType   тип продукта
     * @param foodExotic экзотичность продукта
     */
    public void insertIntoFood(Integer foodId, String foodName, String foodType, Integer foodExotic) {
        String insert = "insert into food(food_id, food_name, food_type, food_exotic) values (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, foodId);
            preparedStatement.setString(2, foodName);
            preparedStatement.setString(3, foodType);
            preparedStatement.setInt(4, foodExotic);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        try {
//            checkInsert(foodId);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


    }

    /**
     * Метод выполняет запрос с некорректными данными для попытки добавления продукта в БД
     *
     * @param foodId     ID продукта
     * @param foodName   наименование продукта
     * @param foodType   тип продукта
     * @param foodExotic экзотичность продукта
     */
    public void insertInvalidValues(Integer foodId, String foodName, String foodType, Integer foodExotic) throws SQLException {
        String insert = "insert into food(food_id, food_name, food_type, food_exotic) values (?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, foodId);
            preparedStatement.setString(2, foodName);
            preparedStatement.setString(3, foodType);
            preparedStatement.setInt(4, foodExotic);
            int count = preparedStatement.executeUpdate();
            if (count == 1) System.out.println("Запись добавлена в БД");
        } catch (JdbcSQLIntegrityConstraintViolationException | JdbcSQLDataException e) {
            System.out.println(e.getMessage());
            System.out.println("Запись не добавлена в БД");
        }


    }

    public Integer getLastFoodID() throws SQLException {
        int lastFoodID = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT food_id FROM FOOD");
        if (resultSet.last()) {
            lastFoodID = resultSet.getInt(1);
            return lastFoodID;
        }
        return null;
    }

    public void checkInsert(Integer foodId) throws SQLException {
        if (getLastFoodID().equals(foodId)) {
            System.out.println("Запись не добавлена в БД");
        } else System.out.println("Запись добавлена в БД");
    }

    /**
     * Удаляет продукт из БД по FOOD_ID
     *
     * @param foodId ID продукта
     */
    public void deleteById(Integer foodId) {
        String delete = "delete from FOOD where food_id = " + foodId;
        try {
            getStatement().execute(delete);
            System.out.println("Запись удалена из БД");
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
