package org.ibs.jdbc.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import org.ibs.jdbc.basetests.JDBCManager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class JDBCsteps {


    @И("Создано подключение к БД")
    public void getConnect() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb", "user", "pass");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @И("Тест")
    public void test() {

    }

    @И("Введены некорректные данные:")
    public void fillIncorrectValues(DataTable dataTable) throws SQLException {
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            JDBCManager.insertInvalidValues(
                    Integer.parseInt(columns.get(0)),
                    JDBCManager.checkNull(columns.get(1)),
                    JDBCManager.checkNull(columns.get(2)),
                    Integer.parseInt(columns.get(3)));
        }
    }

    @И("Введены корректные данные:")
    public void fillCorrectValues(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            JDBCManager.insertIntoFood(
                    Integer.parseInt(columns.get(0)),
                    columns.get(1),
                    columns.get(2),
                    Integer.parseInt(columns.get(3)));
        }
    }


    @И("Удалить продукт из таблицы по ID={int}")
    public void deleteFromTable(Integer id) {
        JDBCManager.deleteById(id);
    }


    @И("Проверка по ID = {int} добавления товара")
    public void checkInsertFood(Integer ID) throws SQLException {
        JDBCManager.checkInsert(ID);
    }



}
