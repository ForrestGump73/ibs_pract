package org.ibs;

import org.h2.jdbc.JdbcSQLDataException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.sql.*;

public class JDBCManager {
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

    public void insertIntoFood(Integer foodId, String foodName, String foodType, Integer foodExotic) throws SQLException{
        String insert = "insert into food(food_id, food_name, food_type, food_exotic) values (?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, foodId);
            preparedStatement.setString(2, foodName);
            preparedStatement.setString(3,foodType);
            preparedStatement.setInt(4,foodExotic);
            preparedStatement.executeUpdate();

    }

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

    public void deleteById(Integer foodId) {
        String delete = "delete from FOOD where food_id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1,foodId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
