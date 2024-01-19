package ru.ibsqa.chameleon.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import org.ibs.basetests.JDBCManager;

import java.sql.SQLException;
import java.util.List;
public class JDBCSteps {

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
}
